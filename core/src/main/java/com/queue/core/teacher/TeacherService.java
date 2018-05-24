package com.queue.core.teacher;

import com.google.inject.Inject;
import com.queue.core.Advice;
import com.queue.core.AdviceStorage;
import com.queue.core.Student;
import com.queue.core.Teacher;
import com.queue.core.student.StudentStorage;
import com.queue.core.teacher.request.TeacherAdviceRequest;
import com.queue.core.teacher.request.TeacherStudentAdviceRequest;
import io.reactivex.Single;

import javax.inject.Singleton;

@Singleton
public class TeacherService implements TeacherAdvice {
  @Inject TeacherStorage teacherStorage;
  @Inject AdviceStorage adviceStorage;
  @Inject StudentStorage studentStorage;

  @Override
  public Single<Advice> createStudentAdvice(TeacherAdviceRequest request) {
    return adviceStorage
        .getAdvice(request.getAdviceId())
        .zipWith(teacherStorage.getTeacher(request.getTeacherId()), ZipAdviceWithTeacher::new)
        .flatMap(obj -> teacherStorage.createStudentAdvice(obj.advice, obj.teacher));
  }

  @Override
  public Single<Advice> editStudentAdvice(TeacherAdviceRequest request) {
    return adviceStorage
        .getAdvice(request.getAdviceId())
        .zipWith(teacherStorage.getTeacher(request.getTeacherId()), ZipAdviceWithTeacher::new)
        .flatMap(obj -> teacherStorage.editStudentAdvice(obj.advice, obj.teacher));

  }

  @Override
  public Single<Advice> cancelAdvice(TeacherAdviceRequest request) {
    return adviceStorage
        .getAdvice(request.getAdviceId())
        .zipWith(teacherStorage.getTeacher(request.getTeacherId()), ZipAdviceWithTeacher::new)
        .flatMap(obj -> teacherStorage.cancelAdvice(obj.advice, obj.teacher));
  }

  @Override
  public Single<Advice> startStudentAdvice(TeacherStudentAdviceRequest request) {
    return adviceStorage
        .getAdvice(request.getAdviceId())
        .zipWith(teacherStorage.getTeacher(request.getTeacherId()), ZipAdviceWithTeacher::new)
        .zipWith(studentStorage.getStudent(request.getStudentId()), (r, student) -> new ZipAdviceTeacherStudent(r.advice, r.teacher, student))
        .flatMap(z -> teacherStorage.startStudentAdvice(z.advice,  z.student));
  }

  @Override
  public Single<Advice> stopStudentAdvice(TeacherStudentAdviceRequest request) {
    return adviceStorage
        .getAdvice(request.getAdviceId())
        .zipWith(teacherStorage.getTeacher(request.getTeacherId()), ZipAdviceWithTeacher::new)
        .zipWith(studentStorage.getStudent(request.getStudentId()), (r, student) -> new ZipAdviceTeacherStudent(r.advice, r.teacher, student))
        .flatMap(z -> teacherStorage.stopStudentAdvice(z.advice,  z.student, "finished"));
  }

  private class ZipAdviceWithTeacher {
    Advice advice;
    Teacher teacher;

    ZipAdviceWithTeacher(Advice advice, Teacher teacher) {
      this.advice = advice;
      this.teacher = teacher;
    }
  }

  @Deprecated
  private class ZipAdviceTeacherStudent {
    Advice advice;
    Teacher teacher;
    Student student;

    public ZipAdviceTeacherStudent(Advice advice, Teacher teacher, Student student) {
      this.advice = advice;
      this.teacher = teacher;
      this.student = student;
    }
  }
}
