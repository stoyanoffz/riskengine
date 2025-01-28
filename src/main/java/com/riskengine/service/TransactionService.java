package com.riskengine.service;

import com.riskengine.domain.FraudulentTransactionEntity;

public interface TransactionService {

    boolean isFraudulentTransaction(FraudulentTransactionEntity fraudulentTransactionEntity);

}
