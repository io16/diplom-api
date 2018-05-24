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

public class AdviceStorageImpl implements AdviceStorage {
  @Inject PgPool client;

  @Override
  public Single<Advice> getAdvice(Integer id) {
    var query = "select * from advice where id = $1";

    return client.rxPreparedQuery(query, Tuple.of(id))
        .map(rowPgResult -> {
          Row row = rowPgResult.iterator().next();
          return new AdviceImpl(
              row.getInteger("id"),
              row.getInteger("teacher_id"),
              (LocalDateTime) row.getValue("start_date"),
              (LocalDateTime) row.getValue("end_date"),
              row.getInteger("duration_per_student"),
              row.getInteger("type"));
        });
  }
}
