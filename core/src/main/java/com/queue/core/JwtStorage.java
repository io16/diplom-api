package com.queue.core;

import io.reactivex.Single;

@Deprecated
public interface JwtStorage {
  Single<Jwt> saveRenewal(Jwt jwt, User user);
}
