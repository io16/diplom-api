package com.queue.db;

import com.queue.core.Advice;
import com.queue.core.AdviceStorage;
import io.reactivex.Single;

public class AdviceStorageImpl implements AdviceStorage {
  @Override
  public Single<Advice> getAdvice(Integer id) {
    return null;
  }
}
