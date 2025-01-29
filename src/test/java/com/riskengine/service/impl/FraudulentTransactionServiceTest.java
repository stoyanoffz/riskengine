package com.riskengine.service.impl;

import com.riskengine.domain.FraudulentTransactionEntity;
import com.riskengine.service.FraudService;
import com.riskengine.testCommon.FraudulentTransactionFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FraudulentTransactionServiceTest {

    @Mock
    private FraudService fraudService;

    @InjectMocks
    private FraudulentTransactionServiceImpl fraudulentTransactionService;

    @Test
    public void givenFraudulentTransactionEntityWhenIsFraudulentTransactionThenVerifyResult() {
        // WHEN
        FraudulentTransactionEntity fraudulentTransactionEntity =
                FraudulentTransactionFixture.buildFraudulentTransactionEntity();
        String userId = fraudulentTransactionEntity.getUserId();
        CompletableFuture<Boolean> fraudulentResult = CompletableFuture.completedFuture(true);
        CompletableFuture<Boolean> nonFraudulentResult = CompletableFuture.completedFuture(false);

        when(fraudService.hasExceededTransactionLimit(userId)).thenReturn(fraudulentResult);
        when(fraudService.hasTransactionsInMultipleCountries(userId)).thenReturn(nonFraudulentResult);
        when(fraudService.hasSuspiciousLocationChange(userId)).thenReturn(nonFraudulentResult);
        when(fraudService.isInBlacklistedCountry(fraudulentTransactionEntity.getCountry()))
                .thenReturn(nonFraudulentResult);

        // WHEN
        boolean result = fraudulentTransactionService.isFraudulentTransaction(fraudulentTransactionEntity);

        // THEN
        assertTrue(result);
    }

    @Test
    public void givenExceptionWhenIsFraudulentTransactionThenVerifyResult() {
        // WHEN
        FraudulentTransactionEntity fraudulentTransactionEntity =
                FraudulentTransactionFixture.buildFraudulentTransactionEntity();
        String userId = fraudulentTransactionEntity.getUserId();
        CompletableFuture<Boolean> nonFraudulentResult = CompletableFuture.completedFuture(false);

        when(fraudService.hasExceededTransactionLimit(userId)).thenReturn(nonFraudulentResult);
        when(fraudService.hasTransactionsInMultipleCountries(userId)).thenReturn(nonFraudulentResult);
        when(fraudService.hasSuspiciousLocationChange(userId)).thenReturn(nonFraudulentResult);
        when(fraudService.isInBlacklistedCountry(fraudulentTransactionEntity.getCountry()))
                .thenReturn(nonFraudulentResult);
        doThrow(new RuntimeException()).when(fraudService).save(fraudulentTransactionEntity);

        // THEN
        assertThrows(RuntimeException.class, () -> {
            // WHEN
            fraudulentTransactionService.isFraudulentTransaction(fraudulentTransactionEntity);
        });
    }

    @Test
    public void givenFraudulentTransactionEntityWhenIsFraudulentTransactionThenVerifyInteractions() {
        // WHEN
        FraudulentTransactionEntity fraudulentTransactionEntity =
                FraudulentTransactionFixture.buildFraudulentTransactionEntity();
        String userId = fraudulentTransactionEntity.getUserId();
        String country = fraudulentTransactionEntity.getCountry();
        CompletableFuture<Boolean> nonFraudulentResult = CompletableFuture.completedFuture(false);

        when(fraudService.hasExceededTransactionLimit(userId)).thenReturn(nonFraudulentResult);
        when(fraudService.hasTransactionsInMultipleCountries(userId)).thenReturn(nonFraudulentResult);
        when(fraudService.hasSuspiciousLocationChange(userId)).thenReturn(nonFraudulentResult);
        when(fraudService.isInBlacklistedCountry(country)).thenReturn(nonFraudulentResult);

        // WHEN
        fraudulentTransactionService.isFraudulentTransaction(fraudulentTransactionEntity);

        // THEN
        verify(fraudService).hasExceededTransactionLimit(userId);
        verify(fraudService).hasTransactionsInMultipleCountries(userId);
        verify(fraudService).hasSuspiciousLocationChange(userId);
        verify(fraudService).isInBlacklistedCountry(country);
        verify(fraudService).save(fraudulentTransactionEntity);
    }

}
