package com.queue.db;

import com.queue.core.Advice;
import com.queue.core.Student;
import com.queue.core.Teacher;
import com.queue.core.teacher.TeacherStorage;
import io.reactivex.Single;

import java.time.LocalDate;
import java.util.List;

public class TeacherStorageImpl implements TeacherStorage {
  @Override
  public Single<Teacher> getTeacher(Integer id) {
    return null;
  }

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
  public Single<Advice> stopStudentAdvice(Advice advice, Teacher teacher, Student student) {
    return null;
  }

  @Override
  public Single<List<Advice>> getAdvices(LocalDate startDate, LocalDate endDate) {
    return null;
  }
}
