package com.riskengine.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "fraudulent_transactions")
@Getter
@Setter
public class FraudulentTransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "identifier", length = 100, nullable = false)
    private String identifier;

    @Column(name = "user_id", length = 100, nullable = false)
    private String userId;

    @Column(name = "amount", scale = 10, precision = 2, nullable = false)
    private BigDecimal amount;

    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "lat_coord", nullable = false)
    private Double latitude;

    @Column(name = "long_coord", nullable = false)
    private Double longitude;

}
