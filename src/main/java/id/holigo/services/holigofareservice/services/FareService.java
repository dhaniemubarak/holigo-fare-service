package id.holigo.services.holigofareservice.services;

import java.math.BigDecimal;

import id.holigo.services.holigofareservice.domain.Fare;

public interface FareService {
    Fare calculate(Long userId, Integer productId, BigDecimal nraAmount);
}
