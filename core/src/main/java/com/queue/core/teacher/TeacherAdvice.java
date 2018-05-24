package com.queue.core.teacher;

import com.queue.core.Advice;
import com.queue.core.teacher.request.TeacherAdviceRequest;
import com.queue.core.teacher.request.TeacherStudentAdviceRequest;
import io.reactivex.Single;

public interface TeacherAdvice {
  Single<Advice> createAdvice(TeacherAdviceRequest request);

  Single<Advice> editAdvice(TeacherAdviceRequest request);

  Single<Advice> cancelAdvice(TeacherAdviceRequest request);

  Single<Advice> startStudentAdvice(TeacherStudentAdviceRequest request);

  Single<Advice> stopStudentAdvice(TeacherStudentAdviceRequest request);
}
