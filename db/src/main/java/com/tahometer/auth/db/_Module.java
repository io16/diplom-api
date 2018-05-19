package com.tahometer.auth.db;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.tahometer.auth.core.MetaDataStorage;
import com.tahometer.auth.util.Framework;
import io.vertx.reactivex.ext.asyncsql.MySQLClient;
import io.vertx.reactivex.ext.sql.SQLClient;

import javax.inject.Named;
import javax.inject.Singleton;

public class _Module extends AbstractModule {
  @Override
  protected void configure() {
    bind(MetaDataStorage.class).to(MySqlStorage.class);
    install(new Conf.Module());
  }

  @Provides @Singleton @Named("SqlClient")
  SQLClient sqlClient(Framework framework, Conf conf) {
    return MySQLClient.createShared(framework.vertx(), conf.getJson());
  }
}
