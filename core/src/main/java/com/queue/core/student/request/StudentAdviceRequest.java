package com.queue.core.student.request;

import java.time.LocalDateTime;

public interface StudentAdviceRequest {
  Integer getStudentId();

  Integer getAdviceId();

  Integer getTeacherId();

  LocalDateTime getStartDate();

  LocalDateTime getEndDate();

}
