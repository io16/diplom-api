package com.queue.core;

import io.reactivex.Single;

import java.time.LocalDate;
import java.util.List;

public interface Storage {
  Single<Advice> createAdvice(Advice advice, Teacher teacher);

  Single<Advice> editAdvice(Advice advice, Teacher teacher);

  Single<Advice> cancelAdvice(Advice advice, Teacher teacher);

  Single<Advice> startStudentAdvice(Advice advice, Teacher teacher, Student student);

  Single<Advice> endStudentAdvice(Advice advice, Teacher teacher, Student student);

  Single<List<Advice>>  getAdvices(Teacher teacher, LocalDate startDate, LocalDate endDate);

  Single<Advice> reserveAdvice(Advice advice, Student student);

  Single<Advice> cancelAdviceReservation(Advice advice, Student student);


}
