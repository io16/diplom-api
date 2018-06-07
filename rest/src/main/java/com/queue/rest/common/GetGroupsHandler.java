package com.queue.rest.common;

import com.alibaba.fastjson.JSON;
import com.queue.core.GroupService;
import com.queue.core.student.StudentAdviceService;
import com.queue.rest.HttpRequestHandler;
import io.reactivex.Flowable;
import io.vertx.core.Handler;
import io.vertx.reactivex.ext.web.RoutingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

import static io.netty.handler.codec.http.HttpResponseStatus.OK;

public class GetGroupsHandler implements Handler<RoutingContext> {
  private final Logger log = LoggerFactory.getLogger(HttpRequestHandler.class);

  @Inject
  GroupService service;

  @Override
  public void handle(RoutingContext request) {
    Flowable
        .just(request)
//        .map(RoutingContext::getBodyAsString)
//        .map(this::parseRequest)
        .flatMapSingle(r ->service.getGroups())
        .subscribe(
            jwt -> {
              var json = JSON.toJSON(jwt).toString();
              request.response().setStatusCode(OK.code()).end(json);
            },
            error -> {
              log.error("Could not handle request.", error);
//              if (error instanceof HttpRequestHandler.BadRequest) {
//                request.response().setStatusCode(BAD_REQUEST.code()).end();
//              } else {
//                request.response().setStatusCode(UNAUTHORIZED.code()).end();
//              }
            }
        );
  }

  private class BadRequest extends RuntimeException {}
}
