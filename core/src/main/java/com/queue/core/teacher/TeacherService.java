package com.queue.core.teacher;

import com.google.inject.Inject;
import com.queue.core.Advice;
import com.queue.core.teacher.request.TeacherAdviceRequest;
import io.reactivex.Single;

import javax.inject.Singleton;

@Singleton
public class TeacherService implements TeacherAdvice {
  @Inject TeacherStorage teacherStorage;

  @Override
  public Single<Advice> createAdvice(TeacherAdviceRequest request) {
//    teacherStorage.createAdvice(advice, teacher);

    return null;
  }

  @Override
  public Single<Advice> editAdvice(TeacherAdviceRequest request) {
//    teacherStorage.editAdvice(advice, teacher);

    return null;
  }

  @Override
  public Single<Advice> cancelAdvice(TeacherAdviceRequest request) {
//    teacherStorage.cancelAdvice(advice, teacher);

    return null;
  }

  @Override
  public Single<Advice> startStudentAdvice(TeacherAdviceRequest request) {
//    teacherStorage.startStudentAdvice(advice, teacher, student);

    return null;
  }

  @Override
  public Single<Advice> stopStudentAdvice(TeacherAdviceRequest request) {
//    teacherStorage.stopStudentAdvice(advice, teacher, student);

    return null;
  }
}
