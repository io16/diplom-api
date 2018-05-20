package com.queue.main;

import io.vertx.reactivex.core.Vertx;

import javax.inject.Singleton;

@Singleton
public class Framework implements com.queue.util.Framework {
  private Vertx mainVertx;

  public Framework() {
    mainVertx = Vertx.vertx();
  }

  @Override
  public Vertx vertx() {
    return mainVertx;
  }
}
