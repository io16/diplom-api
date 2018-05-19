package com.tahometer.auth.main;

import com.google.inject.AbstractModule;

public class _Module extends AbstractModule {
  @Override
  protected void configure() {
    bind(com.tahometer.auth.util.Framework.class).to(Framework.class);
    install(new com.tahometer.auth.rest._Module());
    install(new com.tahometer.auth.core._Module());
    install(new com.tahometer.auth.db._Module());
    install(new com.tahometer.auth.jwt._Module());
  }
}
