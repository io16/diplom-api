package com.queue.core.teacher;

import com.queue.core.Advice;
import com.queue.core.teacher.request.CreateAdviceRequest;
import com.queue.core.teacher.request.TeacherAdviceRequest;
import com.queue.core.teacher.request.TeacherStudentAdviceRequest;
import io.reactivex.Single;

public interface TeacherAdvice {
  Single<Boolean> createStudentAdvice(CreateAdviceRequest request);

  Single<Advice> editStudentAdvice(TeacherAdviceRequest request);

  Single<Advice> cancelAdvice(TeacherAdviceRequest request);

  Single<Advice> startStudentAdvice(TeacherStudentAdviceRequest request);

  Single<Advice> stopStudentAdvice(TeacherStudentAdviceRequest request);
}
