package com.queue.core.teacher;

import com.google.inject.Inject;
import com.queue.core.Advice;
import com.queue.core.Storage;
import com.queue.core.teacher.request.TeacherAdviceRequest;
import io.reactivex.Single;

import javax.inject.Singleton;

@Singleton
public class TeacherService implements TeacherAdvice {
  @Inject Storage storage;

  @Override
  public Single<Advice> createAdvice(TeacherAdviceRequest request) {
//    storage.createAdvice(advice, teacher);

    return null;
  }

  @Override
  public Single<Advice> editAdvice(TeacherAdviceRequest request) {
//    storage.editAdvice(advice, teacher);

    return null;
  }

  @Override
  public Single<Advice> cancelAdvice(TeacherAdviceRequest request) {
//    storage.cancelAdvice(advice, teacher);

    return null;
  }

  @Override
  public Single<Advice> startStudentAdvice(TeacherAdviceRequest request) {
//    storage.startStudentAdvice(advice, teacher, student);

    return null;
  }

  @Override
  public Single<Advice> stopStudentAdvice(TeacherAdviceRequest request) {
//    storage.stopStudentAdvice(advice, teacher, student);

    return null;
  }
}
