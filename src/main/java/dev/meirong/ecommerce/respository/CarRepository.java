package dev.meirong.ecommerce.respository;

import dev.meirong.ecommerce.domain.Car;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CarRepository extends CrudRepository<Car, Long> {
  // Fetch cars by brand
  List<Car> findByBrand(@Param("brand") String brand);
}
