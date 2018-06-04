package com.queue.rest.teacher.request;

import com.queue.core.teacher.request.CreateAdviceRequest;

import java.time.LocalDateTime;

public class CreateAdviceRequestImpl implements CreateAdviceRequest {
  Integer teacherId;
  LocalDateTime startAt;
  LocalDateTime endDate;
  Integer durationPerStudent;

  @Override
  public Integer getTeacherId() {
    return teacherId;
  }

  public void setTeacherId(Integer teacherId) {
    this.teacherId = teacherId;
  }

  public LocalDateTime getStartAt() {
    return startAt;
  }

  public void setStartAt(LocalDateTime startAt) {
    this.startAt = startAt;
  }

  public LocalDateTime getEndDate() {
    return endDate;
  }

  public void setEndDate(LocalDateTime endDate) {
    this.endDate = endDate;
  }

  public Integer getDurationPerStudent() {
    return durationPerStudent;
  }

  public void setDurationPerStudent(Integer durationPerStudent) {
    this.durationPerStudent = durationPerStudent;
  }
}
