package com.queue.rest;

import com.alibaba.fastjson.JSON;
import com.queue.core.JwtGenerator;
import com.queue.rest.auth.HttpRequest;
import com.queue.rest.student.adivice.ReserveAdviceHandler;
import com.queue.rest.teacher.handler.CreateAdviceHandler;
import io.reactivex.Flowable;
import io.vertx.core.Handler;
import io.vertx.reactivex.ext.web.Router;
import io.vertx.reactivex.ext.web.RoutingContext;
import io.vertx.reactivex.ext.web.handler.BodyHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;

import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpResponseStatus.UNAUTHORIZED;
import static io.netty.handler.codec.rtsp.RtspResponseStatuses.BAD_REQUEST;
import static io.vertx.core.http.HttpMethod.GET;
import static io.vertx.core.http.HttpMethod.POST;

@Singleton
public class HttpRequestHandler implements Route, Handler<RoutingContext> {
  private final Logger log = LoggerFactory.getLogger(HttpRequestHandler.class);
  @Inject JwtGenerator generator;
  @Inject CreateAdviceHandler createAdviceHandler;
  @Inject ReserveAdviceHandler reserveAdviceHandler;

  @Override
  public void configure(Router router) {
    router.route(POST, "/auth")
        .handler(BodyHandler.create())
        .handler(this);

    router.route(POST,"/test")
        .handler(BodyHandler.create())
        .handler(reserveAdviceHandler);

    router.route(GET,"/reserveAdviceHandler")
        .handler(BodyHandler.create())
        .handler(createAdviceHandler);
  }

  @Override
  public void handle(RoutingContext request) {//TODO Extract
    Flowable
        .just(request)
        .map(RoutingContext::getBodyAsString)
        .map(this::parseRequest)
        .flatMapSingle(generator::generateJwt)
        .subscribe(
            jwt -> {
              var json = JSON.toJSON(jwt).toString();
              request.response().setStatusCode(OK.code()).end(json);
            },
            error -> {
              log.error("Could not handle request.", error);
              if (error instanceof BadRequest) {
                request.response().setStatusCode(BAD_REQUEST.code()).end();
              } else {
                request.response().setStatusCode(UNAUTHORIZED.code()).end();
              }
            }
        );
  }

  private HttpRequest parseRequest(String request) {
    var httpRequest = JSON.parseObject(request, HttpRequest.class);

    if (httpRequest == null ||
        httpRequest.getEmail() == null ||
        httpRequest.getPassword() == null) throw new BadRequest();

    return httpRequest;
  }

  private class BadRequest extends RuntimeException {}
}
