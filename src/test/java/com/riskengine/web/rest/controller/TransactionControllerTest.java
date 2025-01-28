package com.riskengine.web.rest.controller;

import com.riskengine.domain.FraudulentTransactionEntity;
import com.riskengine.service.TransactionService;
import com.riskengine.testCommon.FraudulentTransactionFixture;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.mockito.Mockito.when;

//@AutoConfigureMockMvc
//@EnableAutoConfiguration
//@SpringBootTest
public class TransactionControllerTest {

//    private static final String CHECK_TRANSACTION_URL =
//            "/api/v1/transactions/{transactionId}/customers/{customerId}/status";
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private WebApplicationContext webApplicationContext;
//
//    @MockBean
//    private TransactionService transactionService;
//
//    @BeforeEach
//    public void setUp() {
//        RestAssuredMockMvc.webAppContextSetup(webApplicationContext);
//    }

    @Test
    @Disabled
    public void givenTransactionIdAndCustomerIdAndTransactionPayloadWhenCheckFraudulentTransactionThenResponseOk() {
//        FraudulentTransactionEntity fraudulentTransactionEntity =
//                FraudulentTransactionFixture.buildFraudulentTransactionEntity();
//
//        when(transactionService.isFraudulentTransaction(fraudulentTransactionEntity)).thenReturn(false);
//
//        given()
//                .mockMvc(mockMvc)
//                .contentType(ContentType.JSON)
//        .when()
//                .post(
//                        CHECK_TRANSACTION_URL,
//                        FraudulentTransactionFixture.IDENTIFIER,
//                        FraudulentTransactionFixture.USER_ID
//                )
//        .then()
//                .contentType(ContentType.JSON)
//                .statusCode(200);
    }

}
