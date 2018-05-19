package com.queue.auth.core;

import io.reactivex.Single;

public interface JwtStorage {
  Single<Jwt> saveRenewal(Jwt jwt, User user);
}
