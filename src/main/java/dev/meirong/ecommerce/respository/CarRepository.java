package dev.meirong.ecommerce.respository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import dev.meirong.ecommerce.domain.Car;

public interface CarRepository extends CrudRepository<Car, Long> {
	// Fetch cars by brand
	List<Car> findByBrand(String brand);
}
