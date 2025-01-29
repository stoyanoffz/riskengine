package com.riskengine.service;

import com.riskengine.domain.FraudulentTransactionEntity;

public interface FraudulentTransactionService {

    boolean isFraudulentTransaction(FraudulentTransactionEntity fraudulentTransactionEntity);

}
