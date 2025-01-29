package com.riskengine.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "blacklisted_countries")
@Getter
@Setter
public class BlacklistedCountryEntity {

    @Id
    @SequenceGenerator(name = "blacklisted_countries_seq", sequenceName = "blacklisted_countries_seq", allocationSize = 10)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "blacklisted_countries_seq")
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "identifier", nullable = false, length = 100)
    private String identifier;

    @Column(name = "country", nullable = false, length = 100)
    private String country;

}
