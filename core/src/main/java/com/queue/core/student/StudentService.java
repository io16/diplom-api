package com.queue.core.student;

import com.google.inject.Inject;
import com.queue.core.Advice;
import com.queue.core.Storage;
import com.queue.core.student.request.StudentAdviceRequest;
import io.reactivex.Single;

import javax.inject.Singleton;
import java.util.List;

@Singleton
public class StudentService implements StudentAdvice {
  @Inject Storage storage;

  @Override
  public Single<List<Advice>> getAdvices(StudentAdviceRequest request) {
//    storage.getAdvices(teacher, startDate, endDate);

    return null;
  }

  @Override
  public Single<Advice> reserveAdvice(StudentAdviceRequest request) {
//    storage.reserveAdvice(advice, student);

    return null;
  }

  @Override
  public Single<Advice> cancelAdviceReservation(StudentAdviceRequest request) {
//    storage.cancelAdviceReservation(advice, student);

    return null;
  }
}
