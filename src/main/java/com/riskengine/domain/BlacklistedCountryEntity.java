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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "identifier", nullable = false, length = 100)
    private String identifier;

    @Column(name = "country", nullable = false, length = 100)
    private String country;

}
