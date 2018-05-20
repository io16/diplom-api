package com.queue.core.student;

import com.queue.core.Advice;
import com.queue.core.Student;
import io.reactivex.Single;

public interface StudentAdvice {
  Single<Advice> reserveAdvice(Advice advice, Student student);

  Single<Advice> cancelAdviceReservation(Advice advice, Student student);
}
