package com.queue.db;

import com.queue.core.Advice;
import com.queue.core.Student;
import com.queue.core.student.StudentStorage;
import com.queue.db.model.AdviceImpl;
import com.queue.db.model.StudentImpl;
import io.reactiverse.reactivex.pgclient.PgPool;
import io.reactiverse.reactivex.pgclient.Row;
import io.reactiverse.reactivex.pgclient.Tuple;
import io.reactivex.Single;

import javax.inject.Inject;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class StudentStorageImp implements StudentStorage {
  @Inject
  PgPool client;

  @Override
  public Single<Student> getStudent(Integer id) {
    var query = "select * from student where id = $1";
    return client
        .rxPreparedQuery(query, Tuple.of(id))
        .map(rowPgResult -> {
          Row row = rowPgResult.iterator().next();
          System.out.println(row.getInteger("id"));
          return new StudentImpl(
              row.getInteger("id"),
              row.getString("email"),
              row.getString("first_name"),
              row.getString("last_name"),
              row.getString("hash"));
        });
  }

  @Override
  public Single<List<Advice>> getAdvices(Integer teacherId, LocalDateTime startDate, LocalDateTime endDate) {
    var query = "Select * from advice where teacher_id = $1 and start_date >= $2 and start_date <= $3";

    List<Advice> advices = new ArrayList<>();
    return client.rxPreparedQuery(query, Tuple.of(teacherId, startDate, endDate))
        .map(rs -> {
              var rows = rs.iterator();
              while (rows.hasNext()) {
                var row = rows.next();
                advices.add(adviceMapper(row));
              }
              return advices;
            }
        );
  }

  @Override
  public Single<List<Advice>> getAdvices(LocalDateTime startDate, LocalDateTime endDate) {
    var query = "Select * from advice where  start_date >= $1 and start_date <= $2";

    List<Advice> advices = new ArrayList<>();
    return client.rxPreparedQuery(query, Tuple.of(startDate, endDate))
        .map(rs -> {
              var rows = rs.iterator();
              while (rows.hasNext()) {
                var row = rows.next();
                advices.add(adviceMapper(row));
              }
              return advices;
            }
        );
  }

  @Override
  public Single<Advice> reserveAdvice(Advice advice, Student student, LocalDateTime startDate) {
    var query = "Update student_advice set student_id = $1 where advice_id = $2 and reserved_start_date = $3";
    return client
        .rxPreparedQuery(query, Tuple.of(student.getId(), advice.getId(), startDate))
        .map(rs -> advice);
  }

  @Override
  public Single<Advice> cancelAdviceReservation(Advice advice, Student student, LocalDateTime startDate) {

    var query = "Update student_advice set student_id = null where user_id = $1 and advice_id = $2 and reserved_start_date = $3";
    return client
        .rxPreparedQuery(query, Tuple.of(student.getId(), advice.getId(), startDate))
        .map(rs -> advice);
  }

  private Advice adviceMapper(Row row) {
    return new AdviceImpl(
        row.getInteger("id"),
        row.getInteger("teacher_id"),
        (LocalDateTime) row.getValue("start_date"),
        (LocalDateTime) row.getValue("end_date"),
        row.getInteger("duration_per_student"),
        row.getInteger("type"));
  }
}
