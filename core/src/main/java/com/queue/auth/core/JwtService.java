package com.queue.auth.core;

public interface JwtService {
  Jwt generateJwt(User user);
}
