package com.queue.auth.rest.teacher;

import com.alibaba.fastjson.JSON;
import com.queue.auth.core.JwtGenerator;
import com.queue.auth.rest.Route;
import com.queue.auth.rest.auth.HttpRequestHandler;
import com.queue.auth.rest.student.adivice.ReserveAdvice;
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
import static io.vertx.core.http.HttpMethod.POST;

@Singleton
public class CreateAdviceHandler extends HttpRequestHandler {
  private final Logger log = LoggerFactory.getLogger(CreateAdviceHandler.class);
  @Inject JwtGenerator generator;

//  @Override
//  public void configure(Router router) {
//    router.route(POST, "/auth2")
//        .handler(BodyHandler.create())
//        .handler(this);
//  }

  @Override
  public  void handle(RoutingContext request) {
    Flowable
        .just(request)
        .map(RoutingContext::getBodyAsString)
        .map(this::parseRequest)
//        .flatMapSingle(generator::generateJwt)
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

  private ReserveAdvice parseRequest(String request) {
    var httpRequest = JSON.parseObject(request, ReserveAdvice.class);

    if (httpRequest == null ||
        httpRequest.getEmail() == null ||
        httpRequest.getPassword() == null) throw new BadRequest();

    return httpRequest;
  }

  private class BadRequest extends RuntimeException {}
}
