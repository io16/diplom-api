package com.queue.main;

import com.google.inject.AbstractModule;

public class _Module extends AbstractModule {
  @Override
  protected void configure() {
    bind(com.queue.util.Framework.class).to(Framework.class);
    install(new com.queue.rest._Module());
    install(new com.queue.core._Module());
    install(new com.queue.db._Module());
    install(new com.queue.jwt._Module());
  }
}
