package dev.meirong.ecommerce.domain.account;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

// Don't export the system data
@RepositoryRestResource(exported = false)
public interface AppUserRepository extends CrudRepository<AppUser, Long> {
  Optional<AppUser> findByUsername(String username);

  Optional<AppUser> findByEmail(String email);

  Optional<AppUser> findByPhoneNumber(String phoneNumber);
}
