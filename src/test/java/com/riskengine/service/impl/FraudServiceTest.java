package com.riskengine.service.impl;

import com.riskengine.data.FraudulentTransactionJpaRepository;
import com.riskengine.domain.FraudulentTransactionEntity;
import com.riskengine.service.BlacklistedCountryService;
import com.riskengine.testCommon.FraudulentTransactionFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FraudServiceTest {

    @Mock
    private BlacklistedCountryService blacklistedCountryService;

    @Mock
    private FraudulentTransactionJpaRepository fraudulentTransactionJpaRepository;

    @InjectMocks
    private FraudServiceImpl fraudService;

    @Test
    public void givenUserIdAndMoreThanTenTransactionsWhenHasExceededTransactionLimitThenVerifyResult() throws ExecutionException, InterruptedException {
        // GIVEN
        String userId = "userId";
        Long previousTransactionsCount = 11L;

        when(fraudulentTransactionJpaRepository.countByUserIdAndTimestampBetween(eq(userId), any(), any()))
                .thenReturn(previousTransactionsCount);

        // WHEN
        CompletableFuture<Boolean> result = fraudService.hasExceededTransactionLimit(userId);

        // THEN
        assertTrue(result.get());
    }

    @Test
    public void givenUserIdAndLessThanTenTransactionsWhenHasExceededTransactionLimitThenVerifyResult() throws ExecutionException, InterruptedException {
        // GIVEN
        String userId = "userId";
        Long previousTransactionsCount = 1L;

        when(fraudulentTransactionJpaRepository.countByUserIdAndTimestampBetween(eq(userId), any(), any()))
                .thenReturn(previousTransactionsCount);

        // WHEN
        CompletableFuture<Boolean> result = fraudService.hasExceededTransactionLimit(userId);

        // THEN
        assertFalse(result.get());
    }

    @Test
    public void givenUserIdWhenHasExceededTransactionLimitThenVerifyInteractions() {
        // GIVEN
        String userId = "userId";
        Long previousTransactionsCount = 1L;

        when(fraudulentTransactionJpaRepository.countByUserIdAndTimestampBetween(eq(userId), any(), any()))
                .thenReturn(previousTransactionsCount);

        // WHEN
        fraudService.hasExceededTransactionLimit(userId);

        // THEN
        verify(fraudulentTransactionJpaRepository).countByUserIdAndTimestampBetween(eq(userId), any(), any());
    }

    @Test
    public void givenUserIdAndTransactionsWithMoreThan300KmApartWhenHasSuspiciousLocationChangeThenVerifyResult() throws ExecutionException, InterruptedException {
        // GIVEN
        String userId = "userId";
        List<FraudulentTransactionEntity> fraudulentTransactionEntities =
                FraudulentTransactionFixture.buildFraudulentTransactionEntities300KmApart();

        when(fraudulentTransactionJpaRepository.findAllByUserIdAndTimestampBetween(eq(userId), any(), any()))
                .thenReturn(fraudulentTransactionEntities);

        // WHEN
        CompletableFuture<Boolean> result = fraudService.hasSuspiciousLocationChange(userId);

        // THEN
        assertTrue(result.get());
    }

    @Test
    public void givenUserIdAndTransactionsWithin300KmWhenHasSuspiciousLocationChangeThenVerifyResult() throws ExecutionException, InterruptedException {
        // GIVEN
        String userId = "userId";
        List<FraudulentTransactionEntity> fraudulentTransactionEntities =
                FraudulentTransactionFixture.buildFraudulentTransactionEntitiesWithin300Km();

        when(fraudulentTransactionJpaRepository.findAllByUserIdAndTimestampBetween(eq(userId), any(), any()))
                .thenReturn(fraudulentTransactionEntities);

        // WHEN
        CompletableFuture<Boolean> result = fraudService.hasSuspiciousLocationChange(userId);

        // THEN
        assertFalse(result.get());
    }

    @Test
    public void givenUserIWhenHasSuspiciousLocationChangeThenVerifyInteractions() throws ExecutionException, InterruptedException {
        // GIVEN
        String userId = "userId";
        List<FraudulentTransactionEntity> fraudulentTransactionEntities =
                FraudulentTransactionFixture.buildFraudulentTransactionEntitiesWithin300Km();

        when(fraudulentTransactionJpaRepository.findAllByUserIdAndTimestampBetween(eq(userId), any(), any()))
                .thenReturn(fraudulentTransactionEntities);

        // WHEN
        fraudService.hasSuspiciousLocationChange(userId);

        // THEN
        verify(fraudulentTransactionJpaRepository).findAllByUserIdAndTimestampBetween(eq(userId), any(), any());
    }

    @Test
    public void givenCountryWhenIsInBlacklistedCountryThenVerifyResult() throws ExecutionException, InterruptedException {
        // GIVEN
        String country = "country";

        when(blacklistedCountryService.isCountryBlacklisted(country)).thenReturn(true);

        // WHEN
        CompletableFuture<Boolean> result = fraudService.isInBlacklistedCountry(country);

        // THEN
        assertTrue(result.get());
    }

    @Test
    public void givenCountryWhenIsInBlacklistedCountryThenVerifyInteractions() throws ExecutionException, InterruptedException {
        // GIVEN
        String country = "country";

        when(blacklistedCountryService.isCountryBlacklisted(country)).thenReturn(true);

        // WHEN
        fraudService.isInBlacklistedCountry(country);

        // THEN
        verify(blacklistedCountryService).isCountryBlacklisted(country);
    }

    @Test
    public void givenUserIdAndTransactionsInMultipleCountriesWhenHasTransactionsInMultipleCountriesThenVerifyResult() throws ExecutionException, InterruptedException {
        // GIVEN
        String userId = "userId";
        Long transactionsInDifferentCountriesCount = 3L;

        when(fraudulentTransactionJpaRepository.countDistinctByUserIdAndTimestampBetween(eq(userId), any(), any()))
                .thenReturn(transactionsInDifferentCountriesCount);

        // WHEN
        CompletableFuture<Boolean> result = fraudService.hasTransactionsInMultipleCountries(userId);

        // THEN
        assertTrue(result.get());
    }

    @Test
    public void givenUserIdAndTransactionsInTwoCountriesWhenHasTransactionsInMultipleCountriesThenVerifyResult() throws ExecutionException, InterruptedException {
        // GIVEN
        String userId = "userId";
        Long transactionsInDifferentCountriesCount = 2L;

        when(fraudulentTransactionJpaRepository.countDistinctByUserIdAndTimestampBetween(eq(userId), any(), any()))
                .thenReturn(transactionsInDifferentCountriesCount);

        // WHEN
        CompletableFuture<Boolean> result = fraudService.hasTransactionsInMultipleCountries(userId);

        // THEN
        assertFalse(result.get());
    }

    @Test
    public void givenUserIdWhenHasTransactionsInMultipleCountriesThenVerifyInteractions() throws ExecutionException, InterruptedException {
        // GIVEN
        String userId = "userId";
        Long transactionsInDifferentCountriesCount = 2L;

        when(fraudulentTransactionJpaRepository.countDistinctByUserIdAndTimestampBetween(eq(userId), any(), any()))
                .thenReturn(transactionsInDifferentCountriesCount);

        // WHEN
        fraudService.hasTransactionsInMultipleCountries(userId);

        // THEN
        verify(fraudulentTransactionJpaRepository).countDistinctByUserIdAndTimestampBetween(eq(userId), any(), any());
    }
}
