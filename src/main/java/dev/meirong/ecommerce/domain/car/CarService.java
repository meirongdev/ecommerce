package dev.meirong.ecommerce.domain.car;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class CarService {

  @PreAuthorize("hasRole('USER')")
  public void update(Car car) {
    // update car
  }

  @PreAuthorize("hasRole('ADMIN')")
  public void delete(Car car) {
    // delete car
  }
}
