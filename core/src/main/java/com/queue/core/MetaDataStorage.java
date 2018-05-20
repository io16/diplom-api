package com.queue.core;

import io.reactivex.Single;

@Deprecated
public interface MetaDataStorage {
  Single<User> getUser(AuthRequest request);
}
