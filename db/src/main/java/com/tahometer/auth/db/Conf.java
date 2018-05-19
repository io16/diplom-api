package com.tahometer.auth.db;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import io.vertx.core.json.JsonObject;

import static java.util.Objects.requireNonNullElse;
import static org.apache.commons.lang3.math.NumberUtils.toInt;

public class Conf {
  private JsonObject json;

  public JsonObject getJson() { return json; }

  static class Module extends AbstractModule {
    @Provides @Singleton
    Conf conf() {
        var c = new Conf();
        c.json = new JsonObject();

        var mysqlHost = System.getenv("AUTH_METADATA_HOST");
        var mysqlPort = System.getenv("AUTH_METADATA_PORT");
        var mysqlUsername = System.getenv("AUTH_METADATA_USERNAME");
        var mysqlPassword = System.getenv("AUTH_METADATA_PASSWORD");
        var mysqlDatabase = System.getenv("AUTH_METADATA_DATABASE");

        c.json.put("host", requireNonNullElse(mysqlHost, "127.0.0.1"));
        c.json.put("port", toInt(mysqlPort, 3306));
        c.json.put("username", requireNonNullElse(mysqlUsername, "root"));
        c.json.put("password", requireNonNullElse(mysqlPassword, "root"));
        c.json.put("database", requireNonNullElse(mysqlDatabase, "layer24"));

        return c;
    }
  }
}
