package com.riskengine.web.rest.controller;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

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
