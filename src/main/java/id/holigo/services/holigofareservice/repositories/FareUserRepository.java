package id.holigo.services.holigofareservice.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import id.holigo.services.holigofareservice.domain.FareUser;

public interface FareUserRepository extends JpaRepository<FareUser, UUID> {

}
