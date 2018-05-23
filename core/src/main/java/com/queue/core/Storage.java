package com.queue.core;

import io.reactivex.Single;

import java.time.LocalDate;
import java.util.List;

public interface Storage {
  Single<List<Advice>>  getAdvices(Teacher teacher, LocalDate startDate, LocalDate endDate);

  Single<Advice> reserveAdvice(Advice advice, Student student);

  Single<Advice> cancelAdviceReservation(Advice advice, Student student);
}
