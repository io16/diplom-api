package com.queue.core;

public interface JwtService {
  Jwt generateJwt(User user);
}
