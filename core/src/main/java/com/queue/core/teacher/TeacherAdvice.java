package com.queue.core.teacher;

import com.queue.core.Advice;
import com.queue.core.teacher.request.TeacherAdviceRequest;
import com.queue.core.teacher.request.TeacherStudentAdviceRequest;
import io.reactivex.Single;

public interface TeacherAdvice {
  Single<Advice> createStudentAdvice(TeacherAdviceRequest request);

  Single<Advice> editStudentAdvice(TeacherAdviceRequest request);

  Single<Advice> cancelAdvice(TeacherAdviceRequest request);

  Single<Advice> startStudentAdvice(TeacherStudentAdviceRequest request);

  Single<Advice> stopStudentAdvice(TeacherStudentAdviceRequest request);
}
