package com.queue.db;

import com.queue.core.Advice;
import com.queue.core.Student;
import com.queue.core.Teacher;
import com.queue.core.student.StudentStorage;
import io.reactivex.Single;

import java.time.LocalDate;
import java.util.List;

public class StudentStorageImp implements StudentStorage {
  @Override
  public Single<Student> getStudent(Integer id) {
    return null;
  }

  @Override
  public Single<List<Advice>> getAdvices(Teacher teacher, LocalDate startDate, LocalDate endDate) {
    return null;
  }

  @Override
  public Single<List<Advice>> getAdvices(LocalDate startDate, LocalDate endDate) {
    return null;
  }

  @Override
  public Single<Advice> reserveAdvice(Advice advice, Student student) {
    return Single.just(new text());
  }

  @Override
  public Single<Student> cancelAdviceReservation(Advice advice, Student student) {
    return null;
  }
  class text implements Advice{

  }
}
