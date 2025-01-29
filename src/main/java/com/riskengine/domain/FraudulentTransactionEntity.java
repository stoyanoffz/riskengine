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
    @SequenceGenerator(name = "fraudulent_transactions_seq", sequenceName = "fraudulent_transactions_seq", allocationSize = 10)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fraudulent_transactions_seq")
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "identifier", length = 100, nullable = false, updatable = false)
    private String identifier;

    @Column(name = "user_id", length = 100, nullable = false, updatable = false)
    private String userId;

    @Column(name = "amount", scale = 10, precision = 2, nullable = false, updatable = false)
    private BigDecimal amount;

    @Column(name = "timestamp", nullable = false, updatable = false)
    private LocalDateTime timestamp;

    @Column(name = "country", nullable = false, updatable = false)
    private String country;

    @Column(name = "lat_coord", nullable = false, updatable = false)
    private Double latitude;

    @Column(name = "long_coord", nullable = false, updatable = false)
    private Double longitude;

}
