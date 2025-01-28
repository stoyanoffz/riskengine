package com.riskengine.testCommon;

import com.riskengine.domain.FraudulentTransactionEntity;
import com.riskengine.web.rest.dto.Transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public final class FraudulentTransactionFixture {

    public static final String IDENTIFIER = "identifier";
    public static final String USER_ID = "userId";

    private static final BigDecimal AMOUNT = new BigDecimal("10.00");
    private static final LocalDateTime TIMESTAMP = LocalDateTime.now();
    private static final String COUNTRY = "country";
    private static final Double FIRST_LATITUDE = 42.69770;
    private static final Double SECOND_LATITUDE = 100.69770;
    private static final Double LONGITUDE = 23.32190;

    private FraudulentTransactionFixture() {}

    public static FraudulentTransactionEntity buildFraudulentTransactionEntity() {
        var fraudulentTransaction = new FraudulentTransactionEntity();

        fraudulentTransaction.setId(1L);
        fraudulentTransaction.setIdentifier(IDENTIFIER);
        fraudulentTransaction.setUserId(USER_ID);
        fraudulentTransaction.setAmount(AMOUNT);
        fraudulentTransaction.setTimestamp(TIMESTAMP);
        fraudulentTransaction.setCountry(COUNTRY);
        fraudulentTransaction.setLatitude(FIRST_LATITUDE);
        fraudulentTransaction.setLongitude(LONGITUDE);

        return fraudulentTransaction;
    }

    public static List<FraudulentTransactionEntity> buildFraudulentTransactionEntities300KmApart() {
        FraudulentTransactionEntity firstFraudulentTransactionEntity = buildFraudulentTransactionEntity();
        var secondFraudulentTransactionEntity = new FraudulentTransactionEntity();
        secondFraudulentTransactionEntity.setId(2L);
        secondFraudulentTransactionEntity.setIdentifier(IDENTIFIER);
        secondFraudulentTransactionEntity.setUserId(USER_ID);
        secondFraudulentTransactionEntity.setAmount(AMOUNT);
        secondFraudulentTransactionEntity.setTimestamp(TIMESTAMP);
        secondFraudulentTransactionEntity.setCountry(COUNTRY);
        secondFraudulentTransactionEntity.setLatitude(SECOND_LATITUDE);
        secondFraudulentTransactionEntity.setLongitude(LONGITUDE);

        return List.of(firstFraudulentTransactionEntity, secondFraudulentTransactionEntity);
    }

    public static List<FraudulentTransactionEntity> buildFraudulentTransactionEntitiesWithin300Km() {
        FraudulentTransactionEntity firstFraudulentTransactionEntity = buildFraudulentTransactionEntity();
        var secondFraudulentTransactionEntity = new FraudulentTransactionEntity();
        secondFraudulentTransactionEntity.setId(2L);
        secondFraudulentTransactionEntity.setIdentifier(IDENTIFIER);
        secondFraudulentTransactionEntity.setUserId(USER_ID);
        secondFraudulentTransactionEntity.setAmount(AMOUNT);
        secondFraudulentTransactionEntity.setTimestamp(TIMESTAMP);
        secondFraudulentTransactionEntity.setCountry(COUNTRY);
        secondFraudulentTransactionEntity.setLatitude(FIRST_LATITUDE);
        secondFraudulentTransactionEntity.setLongitude(LONGITUDE);

        return List.of(firstFraudulentTransactionEntity, secondFraudulentTransactionEntity);
    }

    public static Transaction buildTransaction() {
        return new Transaction(
                AMOUNT,
                TIMESTAMP,
                COUNTRY,
                FIRST_LATITUDE,
                LONGITUDE
        );
    }

}
