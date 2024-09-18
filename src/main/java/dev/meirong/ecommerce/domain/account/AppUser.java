package dev.meirong.ecommerce.domain.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.meirong.ecommerce.domain.account.validator.CorrectPhoneNumber;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class AppUser {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false, updatable = false)
  @EqualsAndHashCode.Include
  private Long id;

  @NotEmpty(message = "Username is required")
  @Column(nullable = false, unique = true)
  private String username;

  @Column(nullable = false)
  @ToString.Exclude
  // Never return the password in the json response
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private String password;

  private String avatar;

  @CorrectPhoneNumber private String phoneNumber;

  @Email(message = "Invalid email")
  private String email;

  private String address;

  @Column(nullable = false)
  private String role;

  public AppUser(String username, String password, String role) {
    super();
    this.username = username;
    this.password = password;
    this.role = role;
  }
}
