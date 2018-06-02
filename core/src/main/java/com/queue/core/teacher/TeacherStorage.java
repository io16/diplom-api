package com.queue.core.teacher;

import com.queue.core.Advice;
import com.queue.core.Group;
import com.queue.core.Student;
import com.queue.core.Teacher;
import io.reactivex.Single;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface TeacherStorage {
  Single<Teacher> getTeacher(Integer id);

  Single<Advice> createStudentAdvice(Teacher teacher, LocalDateTime startAt, LocalDateTime endDate, Integer durationPerStudent);

  Single<Integer> createGroupAdvice(Teacher teacher, List<Group> groups, LocalDateTime startDate, LocalDateTime endDate);

  Single<Advice> editStudentAdvice(Advice advice, Teacher teacher);

  Single<Advice> editGroupAdvice(Advice advice, Teacher teacher, List<Group> groups );

  Single<Advice> cancelAdvice(Advice advice, Teacher teacher);

  Single<Advice> startStudentAdvice(Advice advice, Student student);

  Single<Advice> stopStudentAdvice(Advice advice, Student student, String description);
}
