package com.queue.core.teacher;

import com.google.inject.Inject;
import com.queue.core.Advice;
import com.queue.core.Storage;
import com.queue.core.Student;
import com.queue.core.Teacher;
import io.reactivex.Single;

import javax.inject.Singleton;

@Singleton
public class TeacherService implements TeacherAdvice {
  @Inject Storage storage;

  @Override
  public Single<Advice> createAdvice(Advice advice, Teacher teacher) {
    storage.createAdvice(advice, teacher);

    return null;
  }

  @Override
  public Single<Advice> editAdvice(Advice advice, Teacher teacher) {
    storage.editAdvice(advice, teacher);

    return null;
  }

  @Override
  public Single<Advice> cancelAdvice(Advice advice, Teacher teacher) {
    storage.cancelAdvice(advice, teacher);

    return null;
  }

  @Override
  public Single<Advice> startStudentAdvice(Advice advice, Teacher teacher, Student student) {
    storage.startStudentAdvice(advice, teacher, student);

    return null;
  }

  @Override
  public Single<Advice> stopStudentAdvice(Advice advice, Teacher teacher, Student student) {
    storage.stopStudentAdvice(advice, teacher, student);

    return null;
  }
}
