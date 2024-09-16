package dev.meirong.ecommerce.web;

import org.springframework.web.bind.annotation.RestController;
import dev.meirong.ecommerce.domain.Car;
import dev.meirong.ecommerce.respository.CarRepository;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class CarController {
	private final CarRepository carRepository;

	public CarController(CarRepository carRepository) {
		this.carRepository = carRepository;
	}

	@GetMapping("/cars")
	public Iterable<Car> getCars() {
		return carRepository.findAll();
	}
}
