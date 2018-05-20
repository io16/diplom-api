package com.queue.db;

import com.queue.core.AuthRequest;
import com.queue.core.MetaDataStorage;
import com.queue.core.User;
import io.reactivex.Single;
import io.vertx.core.json.JsonArray;
import io.vertx.reactivex.ext.sql.SQLClient;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

@Singleton
public class MySqlStorage implements MetaDataStorage {
  @Inject @Named("SqlClient") SQLClient sql;

  private static final String query = "SELECT id, email, password_salt, password_hash FROM users where email = ? ";
  @Override
  public Single<User> getUser(AuthRequest request) {
    return sql
        .rxQuerySingleWithParams(query, new JsonArray().add(request.getEmail()))
        .map(rs -> {
          if (rs == null) throw new UserNotFound();

          return new UserImpl(rs.getInteger(0), rs.getString(1), rs.getString(2), rs.getString(3));
        });
  }

  class UserImpl implements User {
    Integer id;
    String email;
    String salt;
    String hash;

    UserImpl(Integer id, String email, String salt, String hash) {
      this.id = id;
      this.email = email;
      this.salt = salt;
      this.hash = hash;
    }

    @Override public Integer getId() { return id; }
    @Override public String getEmail() { return email; }
    @Override public String getSalt() { return salt; }
    @Override public String getHash() { return hash; }
  }

  class UserNotFound extends RuntimeException {}
}
