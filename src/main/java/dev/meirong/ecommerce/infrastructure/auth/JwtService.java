package dev.meirong.ecommerce.infrastructure.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import jakarta.servlet.http.HttpServletRequest;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

  @Value("${security.jwt.expiration-time}")
  private long jwtExpiration;

  private final Algorithm rsa256;
  private final JWTVerifier verifier;

  JwtService(
      @Value("classpath:certs/public.pem") final RSAPublicKey publicKey,
      @Value("classpath:certs/private.pem") final RSAPrivateKey privateKey) {
    this.rsa256 = Algorithm.RSA256(publicKey, privateKey);
    this.verifier = com.auth0.jwt.JWT.require(rsa256).build();
  }

  static final String PREFIX = "Bearer ";

  // Generate signed JWT token
  public String getToken(String username) {
    return JWT.create()
        .withClaim("username", username)
        .withExpiresAt(new Date(System.currentTimeMillis() + jwtExpiration))
        .sign(rsa256);
  }

  // Get a token from request Authorization header,
  // verify the token, and get username
  public String getAuthUser(HttpServletRequest request) {
    String token = request.getHeader(HttpHeaders.AUTHORIZATION);
    token = token.replace(PREFIX, "");
    if (token != null) {
      DecodedJWT jwt = verifier.verify(token);
      String user = jwt.getClaim("username").asString();
      if (user != null) return user;
    }
    return null;
  }
}
