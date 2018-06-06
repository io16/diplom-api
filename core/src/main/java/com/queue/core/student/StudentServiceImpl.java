package com.queue.core.student;

import com.google.inject.Inject;
import com.queue.core.Advice;
import com.queue.core.AdviceStorage;
import com.queue.core.Student;
import com.queue.core.student.request.StudentAdviceRequest;
import io.reactivex.Single;

import javax.inject.Singleton;
import java.time.LocalDateTime;
import java.util.List;

@Singleton
public class StudentServiceImpl implements StudentAdviceService {
  @Inject StudentStorage studentStorage;
  @Inject AdviceStorage adviceStorage;

  @Override
  public Single<List<Advice>> getAdvices(StudentAdviceRequest request) {
    return adviceStorage.getAdvices(request.getTeacherId(), request.getStartDate(), request.getEndDate());
  }


  @Override
  public Single<Advice> reserveAdvice(StudentAdviceRequest request) {
    return adviceStorage
        .getAdvice(request.getAdviceId())
        .zipWith(studentStorage.getStudent(request.getStudentId()), ZipAdviceWithStudent::new)
        .flatMap(obj -> studentStorage.reserveAdvice(obj.advice, obj.student, LocalDateTime.now()));
  }

  @Override
  public Single<Advice> cancelAdviceReservation(StudentAdviceRequest request) {
    return adviceStorage
        .getAdvice(request.getAdviceId())
        .zipWith(studentStorage.getStudent(request.getStudentId()), ZipAdviceWithStudent::new)
        .flatMap(obj -> studentStorage.cancelAdviceReservation(obj.advice, obj.student, LocalDateTime.now()));

  }

  @Override
  public Single<List<StudentAdvice>> getStudentAdvices(StudentAdviceRequest request) {
    return adviceStorage.getStudentAdvices(request.getAdviceId());
  }

  private class ZipAdviceWithStudent {
    Advice advice;
    Student student;

    ZipAdviceWithStudent(Advice advice, Student student) {
      this.advice = advice;
      this.student = student;
    }
  }

}
