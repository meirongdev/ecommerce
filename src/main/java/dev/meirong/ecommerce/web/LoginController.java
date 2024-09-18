package dev.meirong.ecommerce.web;

import dev.meirong.ecommerce.domain.account.AccountCredentials;
import dev.meirong.ecommerce.service.JwtService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public LoginController(JwtService jwtService, AuthenticationManager authenticationManager) {
    this.jwtService = jwtService;
    this.authenticationManager = authenticationManager;
  }

  // curl -X POST -H "Content-Type: application/json" -d '{"username":"admin","password":"admin"}'
  // http://localhost:8080/login -v
  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody AccountCredentials credentials) {
    UsernamePasswordAuthenticationToken token =
        new UsernamePasswordAuthenticationToken(credentials.username(), credentials.password());
    Authentication auth = authenticationManager.authenticate(token);
    String jwt = jwtService.getToken(auth.getName());

    return ResponseEntity.ok()
        .header(HttpHeaders.AUTHORIZATION, "Bearer" + jwt)
        .header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "Authorization")
        .build();
  }
}
