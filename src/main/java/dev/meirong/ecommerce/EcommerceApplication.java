package dev.meirong.ecommerce;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import dev.meirong.ecommerce.domain.Car;
import dev.meirong.ecommerce.domain.Owner;
import dev.meirong.ecommerce.respository.CarRepository;
import dev.meirong.ecommerce.respository.OwnerRepository;

@SpringBootApplication
public class EcommerceApplication implements CommandLineRunner {
	private static final Logger logger = LoggerFactory.getLogger(EcommerceApplication.class);

	private final CarRepository carRepository;
	private final OwnerRepository ownerRepository;

	public EcommerceApplication(CarRepository carRepository, OwnerRepository ownerRepository) {
		this.carRepository = carRepository;
		this.ownerRepository = ownerRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {


		Owner owner = new Owner("user1");
		ownerRepository.save(owner);


		var car = new Car("Toyota", "Camry", "Black", "A1000", 20000, 1000, "A car for the family");
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
			logger.info("owner:{}, registrationNumber: {}", c.getOwner().getUsername(),
					c.getRegistrationNumber());
		}
	}
}
