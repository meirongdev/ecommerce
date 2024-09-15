package dev.meirong.ecommerce.respository;

import org.springframework.data.repository.CrudRepository;

import dev.meirong.ecommerce.domain.Owner;

public interface OwnerRepository extends CrudRepository<Owner, Long> {

}
