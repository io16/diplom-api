package com.tahometer.auth.rest;

import io.vertx.core.Verticle;
import io.vertx.reactivex.ext.web.Router;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.function.Supplier;

@Singleton
public class HttpServerVerticleFactory implements Supplier<Verticle> {
  @Inject Conf conf;
  @Inject Router router;

  @Override
  public Verticle get() {
    return new HttpServerVerticle(conf, router);
  }
}
