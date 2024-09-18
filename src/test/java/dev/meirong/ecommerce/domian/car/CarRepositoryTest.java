package dev.meirong.ecommerce.domian.car;

import static org.assertj.core.api.Assertions.assertThat;

import dev.meirong.ecommerce.domain.car.Car;
import dev.meirong.ecommerce.domain.car.CarRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class CarRepositoryTest {

  @Autowired private CarRepository repositoy;

  @Test
  void saveCar() {
    repositoy.save(
        new Car("TestCarSave", "Camry", "Black", "A1000", 20000, 1000, "A car for the family"));
    assertThat(repositoy.findByBrand("TestCarSave")).hasSize(1);
  }

  @Test
  void deleteCars() {
    repositoy.save(
        new Car("TestCarDelete", "Camry", "Black", "A1000", 20000, 1000, "A car for the family"));
    repositoy.deleteAll();
    assertThat(repositoy.findByBrand("TestCarDelete")).isEmpty();
  }
}
