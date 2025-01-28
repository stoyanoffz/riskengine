package com.riskengine.service;

import com.riskengine.domain.FraudulentTransactionEntity;

import java.util.concurrent.CompletableFuture;

public interface FraudService {

    void save(FraudulentTransactionEntity fraudulentTransactionEntity);

    CompletableFuture<Boolean> hasExceededTransactionLimit(String country);

    CompletableFuture<Boolean> hasSuspiciousLocationChange(String country);

    CompletableFuture<Boolean> isInBlacklistedCountry(String country);

    CompletableFuture<Boolean> hasTransactionsInMultipleCountries(String userId);

}
