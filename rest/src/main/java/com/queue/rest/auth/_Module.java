package com.queue.rest.auth;

import com.google.inject.AbstractModule;
import com.queue.rest.Route;

public class _Module extends AbstractModule {
  @Override
  protected void configure() {
    bind(Route.class).to(HttpRequestHandler.class);
  }
}
