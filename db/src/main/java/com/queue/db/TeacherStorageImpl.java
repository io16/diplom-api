package com.queue.db;

import com.queue.core.Advice;
import com.queue.core.AdviceStorage;
import com.queue.core.Student;
import com.queue.core.Teacher;
import com.queue.core.teacher.TeacherStorage;
import com.queue.db.model.TeacherImpl;
import io.reactiverse.reactivex.pgclient.PgPool;
import io.reactiverse.reactivex.pgclient.Row;
import io.reactiverse.reactivex.pgclient.Tuple;
import io.reactivex.Single;

import javax.inject.Inject;
import java.time.LocalDateTime;

public class TeacherStorageImpl implements TeacherStorage {
  @Inject PgPool client;
  @Inject AdviceStorage adviceStorage;

  @Override
  public Single<Teacher> getTeacher(Integer id) {
    var query = "select * from teacher where id = $1";
    return client.rxPreparedQuery(query, Tuple.of(id))
        .map(rs -> teacherMapper(rs.iterator().next()));
  }

  @Override
  public Single<Advice> createAdvice(Advice advice, Teacher teacher) {
    return null;
  }

  @Override
  public Single<Advice> editAdvice(Advice advice, Teacher teacher) {
//    adviceStorage.getAdvice(advice.getId())
//
//    var query = "update advice set  ";
//    return client.rxPreparedQuery(query, Tuple.of(id))
//        .map(rs -> teacherMapper(rs.iterator().next()));
    return null;
  }

  @Override
  public Single<Advice> cancelAdvice(Advice advice, Teacher teacher) {
    var query = "Delete advice where id = $1 and teacher_id = $2";

    return client.rxPreparedQuery(query, Tuple.of(advice.getId(), teacher.getId()))
        .map(rs -> advice);
  }

  @Override
  public Single<Advice> startStudentAdvice(Advice advice,  Student student) {
    var query = "update student_advice set actual_start_date = $1 where  advice_id = $2 and student_id = $3 ";
    return client.rxPreparedQuery(query, Tuple.of(LocalDateTime.now(), advice.getId(), student.getId()))
        .map(rs -> advice);
  }

  @Override
  public Single<Advice> stopStudentAdvice(Advice advice, Student student, String description) {
    var query = "update student_advice set actual_end_date = $1, desctiption = $4 where  advice_id = $2 and student_id = $3 ";
    return client.rxPreparedQuery(query, Tuple.of(LocalDateTime.now(), advice.getId(), student.getId(), description))
        .map(rs -> advice);
  }

  private TeacherImpl teacherMapper(Row row) {
    return new TeacherImpl(
        row.getInteger("id"),
        row.getString("email"),
        row.getString("first_name"),
        row.getString("last_name"),
        row.getString("hash")
    );
  }
}
