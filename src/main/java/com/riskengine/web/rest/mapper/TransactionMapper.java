package com.riskengine.web.rest.mapper;

import com.riskengine.domain.FraudulentTransactionEntity;
import com.riskengine.web.rest.dto.Transaction;

public final class TransactionMapper {

    private TransactionMapper() {}

    public static FraudulentTransactionEntity toFraudulentTransactionEntity(Transaction transaction) {
        final var transactionEntity = new FraudulentTransactionEntity();

        transactionEntity.setIdentifier(transaction.transactionId());
        transactionEntity.setUserId(transaction.userId());
        transactionEntity.setAmount(transaction.amount());
        transactionEntity.setTimestamp(transaction.timestamp());
        transactionEntity.setCountry(transaction.country());
        transactionEntity.setLatitude(transaction.latitude());
        transactionEntity.setLongitude(transaction.longitude());

        return transactionEntity;
    }

}
