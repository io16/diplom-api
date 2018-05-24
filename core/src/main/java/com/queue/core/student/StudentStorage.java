package com.queue.core.student;

import com.queue.core.Advice;
import com.queue.core.Student;
import io.reactivex.Single;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface StudentStorage {
  Single<Student> getStudent(Integer id);

  Single<List<Advice>> getAdvices(Integer teacherId, LocalDateTime startDate, LocalDateTime endDate);

  Single<List<Advice>> getAdvices(LocalDateTime startDate, LocalDateTime endDate);

  Single<Advice> reserveAdvice(Advice advice, Student student, LocalDateTime startDate);

  Single<Advice> cancelAdviceReservation(Advice advice, Student student, LocalDateTime startDate);

}
