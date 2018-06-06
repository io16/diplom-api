package com.queue.core.student;

import java.time.LocalDateTime;

public interface StudentAdvice {
  Integer getId();

  Integer getStudentId();

  LocalDateTime getReservedStartDate();

  LocalDateTime getReservedEndDate();

  LocalDateTime getActualStartDate();

  LocalDateTime getActualEndDate();
}
