package com.queue.core.student;

import com.google.inject.Inject;
import com.queue.core.Advice;
import com.queue.core.AdviceStorage;
import com.queue.core.Student;
import com.queue.core.student.request.StudentAdviceRequest;
import io.reactivex.Single;

import javax.inject.Singleton;
import java.util.List;

@Singleton
public class StudentService implements StudentAdvice {
  @Inject StudentStorage studentStorage;
  @Inject AdviceStorage adviceStorage;

  @Override
  public Single<List<Advice>> getAdvices(StudentAdviceRequest request) {
//    studentStorage.getAdvices(teacher, startDate, endDate);

    return null;
  }

  @Override
  public Single<Advice> reserveAdvice(StudentAdviceRequest request) {
    return adviceStorage
        .getAdvice(request.getAdviceId())
        .zipWith(studentStorage.getStudent(request.getStudentId()), this::zipAdviceAndStudent)
        .flatMap(obj -> studentStorage.reserveAdvice(obj.advice, obj.student));
  }

  @Override
  public Single<Advice> cancelAdviceReservation(StudentAdviceRequest request) {
//    studentStorage.cancelAdviceReservation(advice, student);

    return null;

  }

  AdviceWithStudent zipAdviceAndStudent(Advice advice, Student student) {
    var obj= new AdviceWithStudent();
    obj.advice =advice;
    obj.student =student;
    return obj;

  }

  private class AdviceWithStudent {
    Student student;
    Advice advice;
  }
}
