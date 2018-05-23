package com.queue.core.student;

import com.google.inject.Inject;
import com.queue.core.Advice;
import com.queue.core.Storage;
import com.queue.core.Student;
import com.queue.core.Teacher;
import io.reactivex.Single;

import javax.inject.Singleton;
import java.time.LocalDate;
import java.util.List;

@Singleton
public class StudentService implements StudentAdvice {
  @Inject Storage storage;

  @Override
  public Single<List<Advice>> getAdvices(Teacher teacher, LocalDate startDate, LocalDate endDate) {
    storage.getAdvices(teacher, startDate, endDate);

    return null;
  }

  @Override
  public Single<Advice> reserveAdvice(Advice advice, Student student) {
    storage.reserveAdvice(advice, student);

    return null;
  }

  @Override
  public Single<Advice> cancelAdviceReservation(Advice advice, Student student) {
    storage.cancelAdviceReservation(advice, student);
    return null;
  }
}
