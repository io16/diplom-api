package com.queue.rest;

import io.vertx.reactivex.ext.web.Router;

public interface Route {
  void configure(Router router);
}
