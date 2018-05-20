package com.queue.core;

import io.reactivex.Single;

public interface MetaDataStorage {
  Single<User> getUser(AuthRequest request);
}
