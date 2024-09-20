package dev.meirong.ecommerce;

import dev.meirong.ecommerce.domain.account.AppUser;
import dev.meirong.ecommerce.domain.account.AppUserRepository;
import dev.meirong.ecommerce.domain.car.Car;
import dev.meirong.ecommerce.domain.car.CarRepository;
import dev.meirong.ecommerce.domain.car.Owner;
import dev.meirong.ecommerce.domain.car.OwnerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@EnableMethodSecurity
public class EcommerceApplication implements CommandLineRunner {
  private static final Logger logger = LoggerFactory.getLogger(EcommerceApplication.class);

  private final CarRepository carRepository;
  private final OwnerRepository ownerRepository;
  private final AppUserRepository appUserRepository;

  public EcommerceApplication(
      CarRepository carRepository,
      OwnerRepository ownerRepository,
      AppUserRepository appUserRepository) {
    this.carRepository = carRepository;
    this.ownerRepository = ownerRepository;
    this.appUserRepository = appUserRepository;
  }

  public static void main(String[] args) {
    SpringApplication.run(EcommerceApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {

    Owner owner = new Owner("Xi", "Huang");
    ownerRepository.save(owner);

    var car = new Car("Toyota", "Camry", "Black", "A1000", 20000, 1000);
    car.setOwner(owner);
    carRepository.save(car);
    logger.info("Car is created {}", car.getId());

    // for (Car c : repository.findAll()) {
    // logger.info(
    // "brand: {}, model{}, color: {}, registrationNumber: {}, modelYear: {}, price: {},
    // description: {}",
    // c.getBrand(), c.getModel(), c.getColor(), c.getRegistrationNumber(), c.getModelYear(),
    // c.getPrice(),
    // c.getDescription());
    // }

    for (Car c : carRepository.findByBrand("Toyota")) {
      logger.info(
          "owner:{}, registrationNumber: {}",
          c.getOwner().getFirstname(),
          c.getRegistrationNumber());
    }

    // Username: user, password: user
    appUserRepository.save(
        new AppUser(
            "user", "$2a$10$B7hf/JY5Zg5B9ukY3XE5RO4lh6MXFi1JO4.QAMSxRpzNGgvq3EOLi", "USER"));
    // Username: admin, password: admin
    appUserRepository.save(
        new AppUser(
            "admin", "$2a$10$HCfIKB7n5c3kFwd9OHNSOeLaxDWwC/mZw93VFhkVujFCUwSSaEIoK", "ADMIN"));
  }
}
