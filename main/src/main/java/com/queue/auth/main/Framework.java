package com.queue.auth.main;

import io.vertx.reactivex.core.Vertx;

import javax.inject.Singleton;

@Singleton
public class Framework implements com.queue.auth.util.Framework {
  private Vertx mainVertx;

  public Framework() {
    mainVertx = Vertx.vertx();
  }

  @Override
  public Vertx vertx() {
    return mainVertx;
  }
}
