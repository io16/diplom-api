package com.queue.db.model;

import com.queue.core.Advice;
import com.queue.core.AdviceType;

import java.time.LocalDateTime;

public class AdviceImpl implements Advice {
  private Integer id;
  private Integer teacherId;
  private LocalDateTime startDate;
  private LocalDateTime endDate;
  private Integer durationPerStudent;
  private AdviceType type;

  public AdviceImpl() {
  }

  public AdviceImpl(Integer id, Integer teacherId, LocalDateTime startDate, LocalDateTime endDate, Integer durationPerStudent, Integer type) {
    this.id = id;
    this.teacherId = teacherId;
    this.startDate = startDate;
    this.endDate = endDate;
    this.durationPerStudent = durationPerStudent;
    this.type = AdviceType.getById(type);
  }

  @Override
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
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

  @Override
  public Integer getDurationPerStudent() {
    return durationPerStudent;
  }

  public void setDurationPerStudent(Integer durationPerStudent) {
    this.durationPerStudent = durationPerStudent;
  }

  @Override
  public AdviceType getType() {
    return type;
  }

  public void setType(AdviceType type) {
    this.type = type;
  }
}
