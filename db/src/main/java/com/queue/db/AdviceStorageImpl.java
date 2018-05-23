package com.queue.db;

import com.queue.core.Advice;
import com.queue.core.AdviceStorage;
import io.reactiverse.reactivex.pgclient.PgPool;
import io.reactiverse.reactivex.pgclient.Tuple;
import io.reactivex.Single;

import javax.inject.Inject;
import javax.inject.Named;

public class AdviceStorageImpl implements AdviceStorage {
  @Inject @Named("collectorSqlClient")
  PgPool client;
  @Override
  public Single<Advice> getAdvice(Integer id) {
    var query = "select * from advice where id = $1";
    client.rxPreparedQuery(query, Tuple.of(id))
        .map(rowPgResult -> {
          System.out.println(rowPgResult);
          return rowPgResult;
        })
        .subscribe();
    return Single.just(new test());
  }

  class test implements Advice{}
}
