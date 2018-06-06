package com.queue.core.student;

import com.queue.core.Advice;
import com.queue.core.student.request.StudentAdviceRequest;
import io.reactivex.Single;

import java.util.List;

public interface StudentAdviceService {
  Single<List<Advice>> getAdvices(StudentAdviceRequest request);

  Single<Advice> reserveAdvice(StudentAdviceRequest request);

  Single<Advice> cancelAdviceReservation(StudentAdviceRequest request);

  Single<List<StudentAdvice>> getStudentAdvices(StudentAdviceRequest request);
}
