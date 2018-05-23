package com.queue.core.teacher;

import com.queue.core.Advice;
import com.queue.core.Student;
import com.queue.core.Teacher;
import io.reactivex.Single;

import javax.inject.Singleton;

@Singleton
public class TeacherService implements TeacherAdvice {
  @Override
  public Single<Advice> createAdvice(Advice advice, Teacher teacher) {
    return null;
  }

  @Override
  public Single<Advice> editAdvice(Advice advice, Teacher teacher) {
    return null;
  }

  @Override
  public Single<Advice> cancelAdvice(Advice advice, Teacher teacher) {
    return null;
  }

  @Override
  public Single<Advice> startStudentAdvice(Advice advice, Teacher teacher, Student student) {
    return null;
  }

  @Override
  public Single<Advice> endStudentAdvice(Advice advice, Teacher teacher, Student student) {
    return null;
  }
}
