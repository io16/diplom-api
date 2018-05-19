package com.queue.auth.rest.auth;

import com.google.inject.AbstractModule;
import com.queue.auth.rest.Route;

public class _Module extends AbstractModule {
  @Override
  protected void configure() {
    bind(Route.class).to(HttpRequestHandler.class);
  }
}
