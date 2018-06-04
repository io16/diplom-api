package com.queue.rest.teacher.handler;

import com.alibaba.fastjson.JSON;
import com.queue.core.teacher.TeacherService;
import com.queue.rest.HttpRequestHandler;
import com.queue.rest.teacher.request.CreateAdviceRequestImpl;
import io.reactivex.Flowable;
import io.vertx.core.Handler;
import io.vertx.reactivex.ext.web.RoutingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

import static io.netty.handler.codec.http.HttpResponseStatus.BAD_REQUEST;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;

public class CreateAdviceHandler implements Handler<RoutingContext> {
  private final Logger log = LoggerFactory.getLogger(HttpRequestHandler.class);

  @Inject TeacherService service;

  @Override
  public void handle(RoutingContext request) {
    Flowable
        .just(request)
        .map(RoutingContext::getBodyAsString)
        .map(this::parseRequest)
        .flatMapSingle(service::createStudentAdvice)
        .subscribe(
            jwt -> {
              var json = JSON.toJSON(jwt).toString();
              request.response().setStatusCode(OK.code()).end(json);
            },
            error -> {
              log.error("Could not handle request.", error);
//              if (error instanceof HttpRequestHandler.BadRequest) {
                request.response().setStatusCode(BAD_REQUEST.code()).end();
//              } else {
//                request.response().setStatusCode(UNAUTHORIZED.code()).end();
//              }
            }
        );
  }

  private CreateAdviceRequestImpl parseRequest(String request) {
    var adviceRequest = JSON.parseObject(request, CreateAdviceRequestImpl.class);

    if (adviceRequest == null ||
        adviceRequest.getTeacherId() == null ||
        adviceRequest.getStartAt() == null ||
        adviceRequest.getEndDate() == null) throw new BadRequest();

    return adviceRequest;
  }

  private class BadRequest extends RuntimeException {}
}
