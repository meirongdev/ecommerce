package dev.meirong.ecommerce.resource;

import dev.meirong.ecommerce.domain.car.Car;
import dev.meirong.ecommerce.domain.car.CarRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarResource {
  private final CarRepository carRepository;

  public CarResource(CarRepository carRepository) {
    this.carRepository = carRepository;
  }

  @GetMapping("/cars")
  public Iterable<Car> getCars() {
    return carRepository.findAll();
  }
}
