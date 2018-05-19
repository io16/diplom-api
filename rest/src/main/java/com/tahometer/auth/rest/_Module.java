package com.tahometer.auth.rest;

import com.google.inject.AbstractModule;

public class _Module extends AbstractModule {
  @Override
  protected void configure() {
    install(new com.tahometer.auth.rest.auth._Module());
    install(new Conf.Module());
    install(new Routing());
  }
}
