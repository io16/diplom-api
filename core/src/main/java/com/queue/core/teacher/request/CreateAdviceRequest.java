package com.queue.core.teacher.request;

import java.time.LocalDateTime;

public interface CreateAdviceRequest {

  public Integer getTeacherId();

  public LocalDateTime getStartAt();

  public LocalDateTime getEndDate();

  public Integer getDurationPerStudent();
}
