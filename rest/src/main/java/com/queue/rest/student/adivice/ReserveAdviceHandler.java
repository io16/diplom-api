package com.queue.rest.student.adivice;

import com.alibaba.fastjson.JSON;
import com.queue.core.student.StudentService;
import com.queue.rest.auth.HttpRequestHandler;
import com.queue.rest.teacher.request.CancelAdviceRequest;
import io.reactivex.Flowable;
import io.vertx.core.Handler;
import io.vertx.reactivex.ext.web.RoutingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

import static io.netty.handler.codec.http.HttpResponseStatus.OK;

public class ReserveAdviceHandler implements Handler<RoutingContext> {
  private final Logger log = LoggerFactory.getLogger(HttpRequestHandler.class);

  @Inject StudentService service;

  @Override
  public void handle(RoutingContext request) {
    Flowable
        .just(request)
        .map(RoutingContext::getBodyAsString)
        .map(this::parseRequest)
        .flatMapSingle(service::reserveAdvice)
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

  private ReserveAdviceRequest parseRequest(String request) {
    return JSON.parseObject(request, ReserveAdviceRequest.class);

//    if (adviceRequest == null ||
//        adviceRequest.getEmail() == null ||
//        adviceRequest.getPassword() == null) throw new BadRequest();

//    return r;
  }

  private class BadRequest extends RuntimeException {}
}
