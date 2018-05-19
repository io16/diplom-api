package com.tahometer.auth.main;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import io.vertx.core.Handler;
import io.vertx.core.VertxOptions;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.KeyStoreOptions;
import io.vertx.ext.auth.jwt.JWTAuthOptions;
import io.vertx.ext.auth.jwt.JWTOptions;
import io.vertx.reactivex.core.AbstractVerticle;
import io.vertx.reactivex.core.Vertx;
import io.vertx.reactivex.core.http.HttpServer;
import io.vertx.reactivex.core.http.HttpServerRequest;
import io.vertx.reactivex.ext.asyncsql.MySQLClient;
import io.vertx.reactivex.ext.auth.jwt.JWTAuth;
import io.vertx.reactivex.ext.sql.SQLClient;
import io.vertx.reactivex.ext.web.Router;
import io.vertx.reactivex.ext.web.RoutingContext;
import io.vertx.reactivex.ext.web.handler.BodyHandler;

import static java.util.Objects.requireNonNullElse;

public class MainVerticle extends AbstractVerticle implements Handler<HttpServerRequest> {
  HttpServer httpServer;
  public SQLClient mySqlClient;
  private static final String query = "SELECT email, password_salt, password_hash FROM users where id = ? ";

  public static void main(String[] args) {
    Vertx v = Vertx.vertx(new VertxOptions()
        .setPreferNativeTransport(true)
    );
    System.out.println("Native transport: " + v.isNativeTransportEnabled());
    v.deployVerticle(MainVerticle.class.getName());
  }

  Router router;

  @Override
  public void start() throws Exception {
    router = Router.router(vertx);
    HttpServerOptions options = new HttpServerOptions()
        .setHost("0.0.0.0")
        .setPort(8080);
    httpServer = vertx.createHttpServer(options)
        .requestHandler(this::handle)
        .listen(res -> {
          if (res.succeeded()) {
            System.out.println("HTTP server is listening on {}:{} 0.0.0.0 8080");
          } else {
            System.out.println("Failed listening http server");
          }
        });
  }


  @Override
  public void handle(HttpServerRequest httpServerRequest) {

    router.accept(httpServerRequest);
    attachRoutes();
  }

  private void attachRoutes() {
    router.route().handler(BodyHandler.create());
    router.post("/api/v1/test").handler(this::generateJwt);
  }


  private void generateJwt(RoutingContext routingContext) {
    mySqlClient = MySQLClient.createShared(vertx, mysqlConfig());

    JWTAuthOptions config = new JWTAuthOptions()
        .setKeyStore(new KeyStoreOptions()
            .setPath("keystore.jceks")
            .setPassword("secret"));
    JWTAuth provider = JWTAuth.create(vertx, config);


    var response = routingContext.response();
    response.putHeader("content-type", "application/json");

    var request = JSON.parseObject(routingContext.getBodyAsJson().toString(), Request.class);
    String token = provider.generateToken(new JsonObject().put("sub", "paulo"), new JWTOptions().setAlgorithm("ES512"));

    provider.authenticate(new JsonObject().put("jwt", token), res -> {
      if (res.succeeded()) {
//                User theUser = res.result();
        System.out.println(res.result().principal());
      } else {
        // Failed!
        System.out.println("Failed");
      }
    });

//    List<User> user = new ArrayList<>();
    mySqlClient
        .rxQuerySingleWithParams(query, new JsonArray().add(13))
        .map(resultSet -> {
          if (resultSet == null)
            throw new IllegalStateException("Project not found.");

          return new User(resultSet.getString(0),resultSet.getString(1), resultSet.getString(2));
        })
        .doOnSuccess(u ->{
          isUserAuthenticated(request.password, u);
        })


        .subscribe();
//    isUserAuthenticated(request.password, user);

    response.end("{\"access\":\"" + token + "\"}");

  }
  boolean isUserAuthenticated (String password, User user) {
    String concat = password.concat(user.getSalt());
    String password_hash = Hashing.sha1().hashString(concat, Charsets.UTF_8).toString();

    return user.getHash().equals(password_hash);
  }

  JsonObject mysqlConfig() {
    var config = new JsonObject();
    var mysqlHost = System.getenv("TAHOMETER_ACTIVITY_MYSQL_HOST");
    var mysqlPort = System.getenv("TAHOMETER_ACTIVITY_MYSQL_PORT");
    var mysqlUsername = System.getenv("TAHOMETER_ACTIVITY_MYSQL_USERNAME");
    var mysqlPassword = System.getenv("TAHOMETER_ACTIVITY_MYSQL_PASSWORD");
    var mysqlDatabase = System.getenv("TAHOMETER_ACTIVITY_MYSQL_DATABASE");

    config.put("host", requireNonNullElse(mysqlHost, "127.0.0.1"));
    config.put("port", requireNonNullElse(mysqlPort, 3306));
    config.put("username", requireNonNullElse("root", "root"));
    config.put("password", requireNonNullElse("root", "root"));
    config.put("database", requireNonNullElse("layer24_migration_testing", "layer24"));

    return config;
  }
}
