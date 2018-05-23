package com.queue.db;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.queue.core.AdviceStorage;
import com.queue.core.MetaDataStorage;
import com.queue.core.student.StudentStorage;
import com.queue.core.teacher.TeacherStorage;
import com.queue.util.Framework;
import io.reactiverse.pgclient.PgPoolOptions;
import io.reactiverse.reactivex.pgclient.PgClient;
import io.reactiverse.reactivex.pgclient.PgPool;
import io.vertx.reactivex.ext.asyncsql.MySQLClient;
import io.vertx.reactivex.ext.sql.SQLClient;

import javax.inject.Named;
import javax.inject.Singleton;

public class _Module extends AbstractModule {
  @Override
  protected void configure() {
    bind(MetaDataStorage.class).to(MySqlStorage.class);
    bind(StudentStorage.class).to(StudentStorageImp.class);
    bind(TeacherStorage.class).to(TeacherStorageImpl.class);
    bind(AdviceStorage.class).to(AdviceStorageImpl.class);
    install(new Conf.Module());
  }

  @Provides @Singleton @Named("collectorSqlClient")
  PgPool pgSqlClient(Framework framework, Conf conf) {
    PgPoolOptions options = new PgPoolOptions()
        .setHost(conf.getTsdbHost())
        .setPort(conf.getTsdbPort())
        .setUser(conf.getTsdbUsername())
        .setPassword(conf.getTsdbPassword())
        .setDatabase(conf.getTsdbDatabase())
        .setMaxSize(10)
        .setCachePreparedStatements(false);
    return PgClient.pool(framework.vertx(), options);
  }
}
