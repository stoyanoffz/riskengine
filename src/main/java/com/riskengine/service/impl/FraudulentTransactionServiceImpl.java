package com.riskengine.service.impl;

import com.riskengine.domain.FraudulentTransactionEntity;
import com.riskengine.service.FraudService;
import com.riskengine.service.FraudulentTransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
@Slf4j
public class FraudulentTransactionServiceImpl implements FraudulentTransactionService {

    private final FraudService fraudService;

    @Override
    public boolean isFraudulentTransaction(FraudulentTransactionEntity fraudulentTransactionEntity) {
        String userId = fraudulentTransactionEntity.getUserId();

        CompletableFuture<Boolean> hasExceededTransactionLimit =
                fraudService.hasExceededTransactionLimit(userId);
        CompletableFuture<Boolean> hasTransactionsInMultipleCountries =
                fraudService.hasTransactionsInMultipleCountries(userId);
        CompletableFuture<Boolean> inBlacklistedCountry =
                fraudService.isInBlacklistedCountry(fraudulentTransactionEntity.getCountry());
        CompletableFuture<Boolean> hasSuspiciousLocationChange =
                fraudService.hasSuspiciousLocationChange(userId);

        CompletableFuture.allOf(
                hasExceededTransactionLimit,
                hasSuspiciousLocationChange,
                inBlacklistedCountry,
                hasTransactionsInMultipleCountries
        ).join();

        try {
            boolean isFraudulentTransaction = hasExceededTransactionLimit.get()
                    || hasTransactionsInMultipleCountries.get()
                    || inBlacklistedCountry.get()
                    || hasSuspiciousLocationChange.get();
            fraudService.save(fraudulentTransactionEntity);

            return isFraudulentTransaction;
        } catch (InterruptedException | ExecutionException e) {
            log.error(
                    "Error while checking fraudulent transaction. User id: {} | Transaction id: {}",
                    userId,
                    fraudulentTransactionEntity.getIdentifier(),
                    e
            );

            throw new RuntimeException(e);
        }
    }

}
