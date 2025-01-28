package com.riskengine.web.rest.mapper;

import com.riskengine.domain.FraudulentTransactionEntity;
import com.riskengine.testCommon.FraudulentTransactionFixture;
import com.riskengine.web.rest.dto.Transaction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionMapperTest {

    @Test
    public void givenTransactionAndUserIdAndTransactionWhenToFraudulentTransactionEntityThenVerifyResult() {
        // GIVEN
        Transaction transaction = FraudulentTransactionFixture.buildTransaction();

        // WHEN
        FraudulentTransactionEntity result = TransactionMapper.toFraudulentTransactionEntity(
                FraudulentTransactionFixture.IDENTIFIER,
                FraudulentTransactionFixture.USER_ID,
                transaction
        );

        // THEN
        assertEquals(FraudulentTransactionFixture.IDENTIFIER, result.getIdentifier());
        assertEquals(FraudulentTransactionFixture.USER_ID, result.getUserId());
        assertEquals(transaction.amount(), result.getAmount());
        assertEquals(transaction.timestamp(), result.getTimestamp());
        assertEquals(transaction.country(), result.getCountry());
        assertEquals(transaction.latitude(), result.getLatitude());
        assertEquals(transaction.longitude(), result.getLongitude());
    }

}
