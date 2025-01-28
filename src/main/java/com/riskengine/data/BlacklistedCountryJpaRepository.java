package com.riskengine.data;

import com.riskengine.domain.BlacklistedCountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlacklistedCountryJpaRepository extends JpaRepository<BlacklistedCountryEntity, Long> {

    boolean existsByCountry(String country);

}
