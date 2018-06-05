package com.queue.rest.student.adivice;

import com.queue.core.student.request.StudentAdviceRequest;

import java.time.LocalDateTime;

public class GetAdviceRequest implements StudentAdviceRequest {
  private Integer teacherId;
  private LocalDateTime startDate;
  private LocalDateTime endDate;

  @Override
  public Integer getStudentId() {
    return null;
  }

  @Override
  public Integer getAdviceId() {
    return null;
  }

  @Override
  public Integer getTeacherId() {
    return teacherId;
  }

  public void setTeacherId(Integer teacherId) {
    this.teacherId = teacherId;
  }

  @Override
  public LocalDateTime getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDateTime startDate) {
    this.startDate = startDate;
  }

  @Override
  public LocalDateTime getEndDate() {
    return endDate;
  }

  public void setEndDate(LocalDateTime endDate) {
    this.endDate = endDate;
  }
}
//  private String email;
//  private String password;
//
//  public void setEmail(String email) { this.email = email; }
//  public void setPassword(String password) { this.password = password; }
//
//  public String getEmail() { return email; }
//  public String getPassword() { return password; }

