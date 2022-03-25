package id.holigo.services.holigofareservice.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import id.holigo.services.common.model.UserGroupEnum;
import id.holigo.services.holigofareservice.domain.MarginAllocation;

public interface MarginAllocationRepository extends JpaRepository<MarginAllocation, Integer> {

    Optional<MarginAllocation> findByUserGroupAndProductId(UserGroupEnum userGroupEnum, Integer productId);
}
