package com.riskengine.gatling;

import io.gatling.javaapi.core.ChainBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;

public class FraudulentTransactionsLoadTest extends Simulation {

    HttpProtocolBuilder httpProtocol = http
            .baseUrl("http://localhost:8080")
            .contentTypeHeader("application/json");

    ChainBuilder postTransactions = exec(
            session -> session
                    .set("userId", ThreadLocalRandom.current().nextInt(1, 10001))
                    .set("transactionId", System.currentTimeMillis() + "-" + UUID.randomUUID())
    )
            .exec(
                    http("PostTransaction")
                            .post("/api/v1/fraudulent-transactions/status")
                            .body(StringBody(
                                    """
                                    {
                                        "transactionId": "${transactionId}",
                                        "userId": "${userId}",
                                        "amount": 1234.56,
                                        "timestamp": "2025-01-25T12:28:00",
                                        "country": "FR",
                                        "latitude": 42.69770,
                                        "longitude": 23.32190
                                    }
                                    """
                            ))
                            .asJson()
            );

    ScenarioBuilder postTransactionScenario = scenario("PostTransactionsScenario")
            .exec(postTransactions);

    {
        setUp(
                postTransactionScenario.injectOpen(
                        constantUsersPerSec(100).during(60)
                )
        ).protocols(httpProtocol);
    }
}
