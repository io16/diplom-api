package com.tahometer.auth.rest;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

import javax.inject.Singleton;

import static java.lang.System.getenv;
import static java.util.Objects.requireNonNullElse;
import static org.apache.commons.lang3.math.NumberUtils.toInt;

public class Conf {
  private String httpHost;
  private Integer httpPort;

  public String getHttpHost() { return httpHost; }
  public Integer getHttpPort() { return httpPort; }

  static class Module extends AbstractModule {
    @Provides @Singleton
    Conf conf() {
      var c = new Conf();
      c.httpHost = requireNonNullElse(getenv("AUTH_HTTP_HOST"), "0.0.0.0");
      c.httpPort = toInt(getenv("AUTH_HTTP_PORT"), 8084);
      return c;
    }
  }
}
