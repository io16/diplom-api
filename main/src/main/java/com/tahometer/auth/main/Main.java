package com.tahometer.auth.main;

import com.google.inject.Guice;
import com.tahometer.auth.rest.RestVerticle;
import org.slf4j.LoggerFactory;

public class Main {
  public static void main(String[] args) {
    var log = LoggerFactory.getLogger(Main.class);

    var injector = Guice.createInjector(new _Module());
    var framework = injector.getInstance(Framework.class);

    var restVerticle = injector.getInstance(RestVerticle.class);
    framework.vertx().getDelegate().deployVerticle(restVerticle, ar -> {
      if (ar.failed())
        framework.vertx().close(cr -> {
          log.error("Could not deploy {}.", restVerticle.getClass().getName(), ar.cause());
          System.exit(101);
        });
    });
  }
}
