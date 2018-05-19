package com.tahometer.auth.core;

public interface JwtService {
  Jwt generateJwt(User user);
}
