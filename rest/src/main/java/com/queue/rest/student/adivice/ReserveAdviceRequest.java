package com.queue.rest.student.adivice;

import com.queue.core.student.request.StudentAdviceRequest;

public class ReserveAdviceRequest implements StudentAdviceRequest {
  private Integer studentId;
  private Integer adviceId;
  private Integer teacherId;

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

  @Override
  public Integer getTeacherId() {
    return teacherId;
  }

  public void setTeacherId(Integer teacherId) {
    this.teacherId = teacherId;
  }

  public void setAdviceId(Integer adviceId) {
    this.adviceId = adviceId;
  }
}
