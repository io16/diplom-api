package com.queue.core.student;

import com.google.inject.Inject;
import com.queue.core.Advice;
import com.queue.core.StudentStorage;
import com.queue.core.student.request.StudentAdviceRequest;
import io.reactivex.Single;

import javax.inject.Singleton;
import java.util.List;

@Singleton
public class StudentService implements StudentAdvice {
  @Inject StudentStorage studentStorage;

  @Override
  public Single<List<Advice>> getAdvices(StudentAdviceRequest request) {
//    studentStorage.getAdvices(teacher, startDate, endDate);

    return null;
  }

  @Override
  public Single<Advice> reserveAdvice(StudentAdviceRequest request) {
//    studentStorage.reserveAdvice(advice, student);

    return null;
  }

  @Override
  public Single<Advice> cancelAdviceReservation(StudentAdviceRequest request) {
//    studentStorage.cancelAdviceReservation(advice, student);

    return null;
  }
}
