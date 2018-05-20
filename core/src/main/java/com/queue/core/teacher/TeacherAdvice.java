package com.queue.core.teacher;

import com.queue.core.Advice;
import com.queue.core.Student;
import com.queue.core.Teacher;
import io.reactivex.Single;

public interface TeacherAdvice {
  Single<Advice> createAdvice(Advice advice, Teacher teacher);

  Single<Advice> editAdvice(Advice advice, Teacher teacher);

  Single<Advice> cancelAdvice(Advice advice, Teacher teacher);

  Single<Advice> startStudentAdvice(Advice advice, Teacher teacher, Student student);

  Single<Advice> endStudentAdvice(Advice advice, Teacher teacher, Student student);
}
