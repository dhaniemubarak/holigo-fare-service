package id.holigo.services.holigofareservice.repositories;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import id.holigo.services.common.model.UserGroupEnum;
import id.holigo.services.holigofareservice.domain.Fare;

public interface FareRepository extends JpaRepository<Fare, Long> {
    Optional<Fare> findByProductIdAndUserGroupAndNtaAmountAndNraAmount(Integer productId, UserGroupEnum userGroup,
            BigDecimal ntaAmount, BigDecimal nraAmount);
}
