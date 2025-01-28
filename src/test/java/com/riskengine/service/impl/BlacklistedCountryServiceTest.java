package com.riskengine.service.impl;


import com.riskengine.data.BlacklistedCountryJpaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BlacklistedCountryServiceTest {

    @Mock
    private BlacklistedCountryJpaRepository blacklistedCountryJpaRepository;

    @InjectMocks
    private BlacklistedCountryServiceImpl blacklistedCountryService;

    @Test
    public void givenCountryNameWhenIsCountryBlacklistedThenVerifyResult() {
        // GIVEN
        String country = "BlacklistedCountry";
        when(blacklistedCountryJpaRepository.existsByCountry(country)).thenReturn(true);

        // WHEN
        boolean result = blacklistedCountryService.isCountryBlacklisted(country);

        // THEN
        assertTrue(result);
    }

    @Test
    public void givenCountryNameWhenIsCountryBlacklistedThenVerifyInteractions() {
        // GIVEN
        String country = "BlacklistedCountry";
        when(blacklistedCountryJpaRepository.existsByCountry(country)).thenReturn(true);

        // WHEN
        blacklistedCountryService.isCountryBlacklisted(country);

        // THEN
        verify(blacklistedCountryJpaRepository).existsByCountry(country);
    }

}
