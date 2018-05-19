package com.queue.auth.main;

import com.google.inject.AbstractModule;

public class _Module extends AbstractModule {
  @Override
  protected void configure() {
    bind(com.queue.auth.util.Framework.class).to(Framework.class);
    install(new com.queue.auth.rest._Module());
    install(new com.queue.auth.core._Module());
    install(new com.queue.auth.db._Module());
    install(new com.queue.auth.jwt._Module());
  }
}
