package com.queue.core.student;

import com.queue.core.Advice;
import com.queue.core.Student;
import io.reactivex.Single;

import java.time.LocalDateTime;

public interface StudentStorage {
  Single<Student> getStudent(Integer id);

  Single<Advice> reserveAdvice(Advice advice, Student student, LocalDateTime startDate);

  Single<Advice> cancelAdviceReservation(Advice advice, Student student, LocalDateTime startDate);

}
