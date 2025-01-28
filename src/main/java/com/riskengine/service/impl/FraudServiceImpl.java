package com.riskengine.service.impl;

import com.riskengine.data.FraudulentTransactionJpaRepository;
import com.riskengine.domain.FraudulentTransactionEntity;
import com.riskengine.service.BlacklistedCountryService;
import com.riskengine.service.FraudService;
import com.riskengine.utils.DistanceCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class FraudServiceImpl implements FraudService {

    private final BlacklistedCountryService blacklistedCountryService;
    private final FraudulentTransactionJpaRepository fraudulentTransactionJpaRepository;

    @Override
    public void save(FraudulentTransactionEntity fraudulentTransactionEntity) {
        fraudulentTransactionJpaRepository.save(fraudulentTransactionEntity);
    }

    @Override
    public CompletableFuture<Boolean> hasExceededTransactionLimit(String userId) {
        LocalDateTime now = OffsetDateTime.now(ZoneOffset.UTC).toLocalDateTime();
        LocalDateTime from = now.minusMinutes(1);

        return CompletableFuture.supplyAsync(
                () -> fraudulentTransactionJpaRepository.countByUserIdAndTimestampBetween(userId, from, now) >= 10
        );
    }

    @Override
    public CompletableFuture<Boolean> hasSuspiciousLocationChange(String userId) {
        LocalDateTime now = OffsetDateTime.now(ZoneOffset.UTC).toLocalDateTime();
        LocalDateTime from = now.minusMinutes(1);

        return CompletableFuture.supplyAsync(
                () -> {
                    List<FraudulentTransactionEntity> fraudulentTransactions =
                            fraudulentTransactionJpaRepository.findAllByUserIdAndTimestampBetween(userId, from, now);

                    return hasSuspiciousLocationChange(fraudulentTransactions);
                }
        );
    }

    @Override
    public CompletableFuture<Boolean> isInBlacklistedCountry(String country) {
        return CompletableFuture.supplyAsync(() -> blacklistedCountryService.isCountryBlacklisted(country));
    }

    @Override
    public CompletableFuture<Boolean> hasTransactionsInMultipleCountries(String userId) {
        LocalDateTime now = OffsetDateTime.now(ZoneOffset.UTC).toLocalDateTime();
        LocalDateTime from = now.minusMinutes(10);

        return CompletableFuture.supplyAsync(
                () -> {
                    Long fraudulentTransactionsNumber =
                            fraudulentTransactionJpaRepository.countDistinctByUserIdAndTimestampBetween(userId, from, now);

                    return fraudulentTransactionsNumber >= 3;
                }
        );
    }

    private static boolean hasSuspiciousLocationChange(List<FraudulentTransactionEntity> fraudulentTransactions) {
        return fraudulentTransactions.parallelStream().anyMatch(trn ->
                fraudulentTransactions.parallelStream().anyMatch(otherTrn -> {
                    if (!trn.equals(otherTrn)) {
                        double distance = DistanceCalculator.calculateDistance(
                                trn.getLatitude(),
                                trn.getLongitude(),
                                otherTrn.getLatitude(),
                                otherTrn.getLongitude()
                        );

                        return distance > 300;
                    }

                    return false;
                })
        );
    }

}
