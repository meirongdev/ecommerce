package dev.meirong.ecommerce;

import static org.assertj.core.api.Assertions.assertThat;

import dev.meirong.ecommerce.web.CarController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EcommerceApplicationTests {
  @Autowired private CarController controller;

  @Test
  @DisplayName("Context loads")
  void contextLoads() {
    assertThat(controller).isNotNull();
  }
}
