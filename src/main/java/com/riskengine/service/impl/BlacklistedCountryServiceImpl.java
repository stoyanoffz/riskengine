package com.riskengine.service.impl;

import com.riskengine.data.BlacklistedCountryJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BlacklistedCountryServiceImpl implements com.riskengine.service.BlacklistedCountryService {

    private final BlacklistedCountryJpaRepository blacklistedCountryJpaRepository;

    @Override
    public boolean isCountryBlacklisted(String country) {
        return blacklistedCountryJpaRepository.existsByCountry(country);
    }

}
