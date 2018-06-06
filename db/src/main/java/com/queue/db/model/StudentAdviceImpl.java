package com.queue.db.model;

import com.queue.core.student.StudentAdvice;

import java.time.LocalDateTime;

public class StudentAdviceImpl implements StudentAdvice {
  private Integer id;
  private Integer studentId;
  private Integer adviceId;
  private LocalDateTime reservedStartDate;
  private LocalDateTime reservedEndDate;
  private LocalDateTime actualStartDate;
  private LocalDateTime actualEndDate;


  public StudentAdviceImpl() {
  }

  public StudentAdviceImpl(Integer id, Integer studentId, Integer adviceId, LocalDateTime reservedStartDate, LocalDateTime reservedEndDate) {
    this.id = id;
    this.studentId = studentId;
    this.adviceId = adviceId;
    this.reservedStartDate = reservedStartDate;
    this.reservedEndDate = reservedEndDate;

  }

  @Override
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  @Override
  public Integer getStudentId() {
    return studentId;
  }

  public void setStudentId(Integer studentId) {
    this.studentId = studentId;
  }

  public Integer getAdviceId() {
    return adviceId;
  }

  public void setAdviceId(Integer adviceId) {
    this.adviceId = adviceId;
  }

  @Override
  public LocalDateTime getReservedStartDate() {
    return reservedStartDate;
  }

  public void setReservedStartDate(LocalDateTime reservedStartDate) {
    this.reservedStartDate = reservedStartDate;
  }

  @Override
  public LocalDateTime getReservedEndDate() {
    return reservedEndDate;
  }

  public void setReservedEndDate(LocalDateTime reservedEndDate) {
    this.reservedEndDate = reservedEndDate;
  }

  @Override
  public LocalDateTime getActualStartDate() {
    return actualStartDate;
  }

  public void setActualStartDate(LocalDateTime actualStartDate) {
    this.actualStartDate = actualStartDate;
  }

  @Override
  public LocalDateTime getActualEndDate() {
    return actualEndDate;
  }

  public void setActualEndDate(LocalDateTime actualEndDate) {
    this.actualEndDate = actualEndDate;
  }
}
