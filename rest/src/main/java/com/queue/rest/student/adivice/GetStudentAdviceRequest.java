package com.queue.rest.student.adivice;

import com.queue.core.student.request.StudentAdviceRequest;

import java.time.LocalDateTime;

public class GetStudentAdviceRequest implements StudentAdviceRequest {
private Integer adviceId;

  @Override
  public Integer getStudentId() {
    return null;
  }

  @Override
  public Integer getAdviceId() {
    return adviceId;
  }

  @Override
  public Integer getTeacherId() {
    return null;
  }

  @Override
  public LocalDateTime getStartDate() {
    return null;
  }

  @Override
  public LocalDateTime getEndDate() {
    return null;
  }

  public void setAdviceId(Integer adviceId) {
    this.adviceId = adviceId;
  }
}


