package com.queue.rest.student.adivice;

import com.queue.core.student.request.StudentAdviceRequest;

public class ReserveAdviceRequest implements StudentAdviceRequest {
  private Integer studentId;
  private Integer adviceId;

  @Override
  public Integer getStudentId() {
    return studentId;
  }

  public void setStudentId(Integer studentId) {
    this.studentId = studentId;
  }

  @Override
  public Integer getAdviceId() {
    return adviceId;
  }

  public void setAdviceId(Integer adviceId) {
    this.adviceId = adviceId;
  }
}
