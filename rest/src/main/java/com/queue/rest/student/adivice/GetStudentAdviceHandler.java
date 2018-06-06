package com.queue.rest.student.adivice;

import com.alibaba.fastjson.JSON;
import com.queue.core.student.StudentAdviceService;
import com.queue.rest.HttpRequestHandler;
import io.reactivex.Flowable;
import io.vertx.core.Handler;
import io.vertx.reactivex.ext.web.RoutingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.List;

import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.rtsp.RtspResponseStatuses.BAD_REQUEST;

public class GetStudentAdviceHandler implements Handler<RoutingContext> {
  private final Logger log = LoggerFactory.getLogger(HttpRequestHandler.class);

  @Inject StudentAdviceService service;

  @Override
  public void handle(RoutingContext request) {
    Flowable
        .just(request)
        .map( r ->  parseRequest(r.queryParam("advice_id")))
        .flatMapSingle(service::getStudentAdvices)
        .subscribe(
            advices -> {
              var json =JSON.toJSONStringWithDateFormat(advices, "yyyy-MM-dd HH:mm:ss.SSS");
              request.response().setStatusCode(OK.code()).end(json);
            },
            error -> {
              log.error("Could not handle request.", error);
              log.error("Could not handle request.", error);
//              if (error instanceof HttpRequestHandler.BadRequest) {
//                request.response().setStatusCode(BAD_REQUEST.code()).end();
//              } else {
                request.response().setStatusCode(BAD_REQUEST.code()).end();
//              }
            }
        );
  }

  private GetStudentAdviceRequest parseRequest(List<String> adviceId) {
  var request = new GetStudentAdviceRequest();
    request.setAdviceId(Integer.valueOf(adviceId.get(0)));
    return request;
//    return JSON.parseObject(request, CancelAdviceRequest.class);

//    if (adviceRequest == null ||
//        adviceRequest.getEmail() == null ||
//        adviceRequest.getPassword() == null) throw new BadRequest();

//    return r;
  }

  private class BadRequest extends RuntimeException {}
}
