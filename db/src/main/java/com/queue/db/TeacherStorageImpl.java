package com.queue.db;

import com.queue.core.*;
import com.queue.core.teacher.TeacherStorage;
import com.queue.db.model.TeacherImpl;
import io.reactiverse.reactivex.pgclient.PgPool;
import io.reactiverse.reactivex.pgclient.Row;
import io.reactiverse.reactivex.pgclient.Tuple;
import io.reactivex.Single;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.List;

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
  public Single<Advice> createStudentAdvice(Teacher teacher, LocalDateTime startDate, LocalDateTime endDate, Integer durationPerStudent) {
    var adviceQuery = "insert into advice (teacher_id, start_date, end_date, type) values " +
        "($1, $2, $3 ,$4) returning id";

    var start = startDate;

    var studentAdviceQuery = new StringBuilder("insert into student_advice (advice_id, reserved_start_date, reserved_end_date)" +
        "values ");
    var adviceId = 1;
    while (start.isBefore(endDate)) {

      var tempStudentAdvice = "( " +  adviceId + ", " + start + ","  + start.plusMinutes(durationPerStudent) + ")";
      start = startDate.plusMinutes(durationPerStudent);

      if (start.plusMinutes(durationPerStudent).isBefore(endDate)) tempStudentAdvice += ",";

      studentAdviceQuery.append(tempStudentAdvice);
    }

    client.rxPreparedQuery(adviceQuery, Tuple.of(teacher.getId(), startDate, endDate, AdviceType.Student.id))
//        .flatMap.....

    return null;
  }

  @Override
  public Single<Integer> createGroupAdvice(Teacher teacher, List<Group> groups, LocalDateTime startDate, LocalDateTime endDate) {
    var adviceQuery = "insert into advice (teacher_id, start_date, end_date, type ) values " +
        "($1, $2, $3 ,$4) returning *";

    var adviceId = 1;

    var groupAdviceQuery = new StringBuilder("insert into group_advice (advice_id, group_id) values");

    for (int i = 0; i < groups.size(); i++) {
      var temp = "(" + adviceId + "," + groups.get(i) + ")";

      if (i + 1 < groups.size()) temp += ",";

      groupAdviceQuery.append(temp);

    }

//todo add advice variable
    return client.rxPreparedQuery(adviceQuery, Tuple.of(teacher.getId(), startDate, endDate, 10))
        .flatMap(rs -> client.rxPreparedQuery(getStringBuilder(groups, rs.iterator().next().getInteger("id"))))
        .map(rs -> 1);
  }

  private String getStringBuilder(List<Group> groups, Integer adviceId) {
    if (groups.size() == 0) throw new RuntimeException();
    StringBuilder groupAdviceQuery = new StringBuilder("Insert into group_advice (group_id, advice_id) values ");
    for (int i = 0; i < groups.size(); i++) {
      groupAdviceQuery.append("(").append(groups.get(i).getId()).append(",").append(adviceId).append(")");
      if (i + 1 < groups.size())
        groupAdviceQuery.append(",");
    }
    return groupAdviceQuery.toString();
  }

  @Override
  public Single<Advice> editStudentAdvice(Advice advice, Teacher teacher) {

    //todo Edit student_advice like : edit advice per student ( time, duration, desc)
//    adviceStorage.getAdvice(advice.getId())
//        .flatMap(oldAdvice -> removeAdvices(oldAdvice, advice))
//        .flatMap(this::uppendAdvices);
//
//    var query = "update advice set  ";
//    return client.rxPreparedQuery(query, Tuple.of(id))
//        .map(rs -> teacherMapper(rs.iterator().next()));
    return null;
  }

  @Override
  public Single<Advice> editGroupAdvice(Advice advice, Teacher teacher, List<Group> groups) {
    var query = "";
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
