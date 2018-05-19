package com.tahometer.auth.jwt;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.tahometer.auth.core.JwtService;
import com.tahometer.auth.util.Framework;
import io.vertx.reactivex.ext.auth.jwt.JWTAuth;

import javax.inject.Named;
import javax.inject.Singleton;

public class _Module extends AbstractModule {
  @Override
  protected void configure() {
    bind(JwtService.class).to(JwtServiceImpl.class);
    install(new Conf.Module());
  }

  @Provides @Singleton @Named("JWTAuth")
  JWTAuth sqlClient(Framework framework, Conf config) {
    return JWTAuth.create(framework.vertx(), config.getConfig());
  }
}
