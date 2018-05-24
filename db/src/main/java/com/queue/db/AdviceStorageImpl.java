package com.queue.db;

import com.queue.core.Advice;
import com.queue.core.AdviceStorage;
import com.queue.db.model.AdviceImpl;
import io.reactiverse.reactivex.pgclient.PgPool;
import io.reactiverse.reactivex.pgclient.Row;
import io.reactiverse.reactivex.pgclient.Tuple;
import io.reactivex.Single;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AdviceStorageImpl implements AdviceStorage {
  @Inject PgPool client;

  @Override
  public Single<Advice> getAdvice(Integer id) {
    var query = "select * from advice where id = $1";

    return client.rxPreparedQuery(query, Tuple.of(id))
        .map(rowPgResult -> {
          Row row = rowPgResult.iterator().next();
          return adviceMapper(row);
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

   Advice adviceMapper(Row row) {
    return new AdviceImpl(
        row.getInteger("id"),
        row.getInteger("teacher_id"),
        (LocalDateTime) row.getValue("start_date"),
        (LocalDateTime) row.getValue("end_date"),
        row.getInteger("duration_per_student"),
        row.getInteger("type"));
  }
}
