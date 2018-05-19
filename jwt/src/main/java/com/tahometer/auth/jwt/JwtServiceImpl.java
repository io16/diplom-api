package com.tahometer.auth.jwt;

import com.google.inject.Singleton;
import com.tahometer.auth.core.Jwt;
import com.tahometer.auth.core.JwtService;
import com.tahometer.auth.core.User;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.jwt.JWTOptions;
import io.vertx.reactivex.ext.auth.jwt.JWTAuth;

import javax.inject.Inject;
import javax.inject.Named;

@Singleton
public class JwtServiceImpl implements JwtService {
  private @Inject @Named("JWTAuth") JWTAuth provider;

  private final String JWT_ALGORITHM = "ES512";
  private final int EXPIRES_IN_MINUTES = 10;
  @Override
  public Jwt generateJwt(User user) {
    var jwt = new JwtImpl();
    var jwtAccess = provider
        .generateToken(
            new JsonObject()
                .put("userId", user.getId())
                .put("email", user.getEmail()),
            new JWTOptions()
                .setAlgorithm(JWT_ALGORITHM)
                .setExpiresInMinutes(EXPIRES_IN_MINUTES));

    jwt.setAccessToken(jwtAccess);
    return jwt;
  }

  class JwtImpl implements Jwt{
    String accessToken;
    String renewalToken;

    public void setAccessToken(String accessToken) {this.accessToken = accessToken;}
    public void setRenewalToken(String renewalToken) {this.renewalToken = renewalToken;}

    @Override public String getAccessToken() { return accessToken; }
    @Override public String getRenewalToken() { return accessToken; } //TODO implement logic
  }
}
