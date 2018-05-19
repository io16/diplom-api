package com.tahometer.auth.rest.auth;

import com.google.inject.AbstractModule;
import com.tahometer.auth.rest.Route;

public class _Module extends AbstractModule {
  @Override
  protected void configure() {
    bind(Route.class).to(HttpRequestHandler.class);
  }
}
