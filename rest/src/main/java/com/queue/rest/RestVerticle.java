package com.queue.rest;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Future;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

public class RestVerticle extends AbstractVerticle {
  private final Logger log = LoggerFactory.getLogger(RestVerticle.class);

  @Inject
  HttpServerVerticleFactory httpServerVerticleFactory;

  @Override
  public void start(Future<Void> startFuture) {
    var options = new DeploymentOptions().setInstances(1); // TODO: envvar + some default like cores * 2 ?
    vertx.deployVerticle(httpServerVerticleFactory, options, dr -> {
      if (dr.failed())
        vertx.close(cr -> {
          log.error("Could not deploy HTTP server.", dr.cause());
          System.exit(101);
        });
    });
  }
}
