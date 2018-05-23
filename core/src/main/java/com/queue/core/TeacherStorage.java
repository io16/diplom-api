package com.queue.core;

import io.reactivex.Single;

import java.time.LocalDate;
import java.util.List;

public interface TeacherStorage {
  Single<Advice> createAdvice(Advice advice, Teacher teacher);

  Single<Advice> editAdvice(Advice advice, Teacher teacher);

  Single<Advice> cancelAdvice(Advice advice, Teacher teacher);

  Single<Advice> startStudentAdvice(Advice advice, Teacher teacher, Student student);

  Single<Advice> stopStudentAdvice(Advice advice, Teacher teacher, Student student);

  Single<List<Advice>> getAdvices(LocalDate startDate, LocalDate endDate);


}