package com.queue.core.student;

import com.queue.core.Advice;
import com.queue.core.student.request.StudentAdviceRequest;
import io.reactivex.Single;

import java.util.List;

public interface StudentAdvice {
  Single<List<Advice>> getAdvices(StudentAdviceRequest request);

  Single<Advice> reserveAdvice(StudentAdviceRequest request);

  Single<Advice> cancelAdviceReservation(StudentAdviceRequest request);
}
