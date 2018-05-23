package com.queue.core.student;

import com.queue.core.Advice;
import com.queue.core.Student;
import com.queue.core.Teacher;
import io.reactivex.Single;

import java.time.LocalDate;
import java.util.List;

public interface StudentStorage {
  Single<Student> getStudent(Integer id);

  Single<List<Advice>> getAdvices(Teacher teacher, LocalDate startDate, LocalDate endDate);

  Single<List<Advice>> getAdvices(LocalDate startDate, LocalDate endDate);

  Single<Advice> reserveAdvice(Advice advice, Student student);

  Single<Student> cancelAdviceReservation(Advice advice, Student student);

}
