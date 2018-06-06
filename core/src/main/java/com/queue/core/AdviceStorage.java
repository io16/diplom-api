package com.queue.core;

import com.queue.core.student.StudentAdvice;
import io.reactivex.Single;

import java.time.LocalDateTime;
import java.util.List;

public interface AdviceStorage {
  Single<Advice> getAdvice(Integer id);

  Single<List<Advice>> getAdvices(LocalDateTime startDate, LocalDateTime endDate);

  Single<List<Advice>> getAdvices(Integer teacherId, LocalDateTime startDate, LocalDateTime endDate);

  Single<List<StudentAdvice>> getStudentAdvices(Integer adviceId);
}
