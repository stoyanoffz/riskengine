package com.riskengine.service.impl;

import com.riskengine.data.BlacklistedCountryJpaRepository;
import com.riskengine.service.BlacklistedCountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BlacklistedCountryServiceImpl implements BlacklistedCountryService {

    private final BlacklistedCountryJpaRepository blacklistedCountryJpaRepository;

    @Override
    public boolean isCountryBlacklisted(String country) {
        return blacklistedCountryJpaRepository.existsByCountry(country);
    }

}
