package com.riskengine.web.rest.mapper;

import com.riskengine.domain.FraudulentTransactionEntity;
import com.riskengine.web.rest.dto.Transaction;

public final class TransactionMapper {

    private TransactionMapper() {}

    public static FraudulentTransactionEntity toFraudulentTransactionEntity(
            String transactionId,
            String userId,
            Transaction transaction
    ) {
        final var transactionEntity = new FraudulentTransactionEntity();

        transactionEntity.setIdentifier(transactionId);
        transactionEntity.setUserId(userId);
        transactionEntity.setAmount(transaction.amount());
        transactionEntity.setTimestamp(transaction.timestamp());
        transactionEntity.setCountry(transaction.country());
        transactionEntity.setLatitude(transaction.latitude());
        transactionEntity.setLongitude(transaction.longitude());

        return transactionEntity;
    }

}
