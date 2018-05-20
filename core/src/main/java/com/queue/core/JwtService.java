package com.queue.core;

@Deprecated
public interface JwtService {
  Jwt generateJwt(User user);
}
