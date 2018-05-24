package com.queue.core;

import java.time.LocalDateTime;

public interface Advice {
  Integer getId();

  Integer getTeacherId();

  LocalDateTime getStartDate();

  LocalDateTime getEndDate();

  Integer getDurationPerStudent();

  AdviceType getType();
}
