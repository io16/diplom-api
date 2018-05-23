package com.queue.core;

import io.reactivex.Single;

public interface AdviceStorage {
  Single<Advice> getAdvice(Integer id);
}
