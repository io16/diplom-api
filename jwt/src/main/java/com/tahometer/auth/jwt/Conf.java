package com.tahometer.auth.jwt;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import io.vertx.ext.auth.KeyStoreOptions;
import io.vertx.ext.auth.jwt.JWTAuthOptions;

import static java.util.Objects.requireNonNullElse;

public class Conf {
  private JWTAuthOptions config;

  public JWTAuthOptions getConfig() { return config; }

  static class Module extends AbstractModule {
    @Provides @Singleton
    Conf conf() {
      var c = new Conf();

      var path = System.getenv("AUTH_KEY_STORE_PATH");
      var password = System.getenv("AUTH_KEY_STORE_PASSWORD");

      c.config = new JWTAuthOptions()
          .setKeyStore(new KeyStoreOptions()
              .setPath(requireNonNullElse(path,"keystore.jceks"))
              .setPassword(requireNonNullElse(password,"secret")));
        return c;
    }
  }
}
