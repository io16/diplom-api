package com.queue.core;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import io.reactivex.Single;

import javax.inject.Inject;
import javax.inject.Singleton;

@Deprecated
@Singleton
public class JwtGenerator  {

  @Inject MetaDataStorage metaDataStorage;
  @Inject JwtService jwtService;
  public Single<Jwt> generateJwt(AuthRequest request) {

    return metaDataStorage
        .getUser(request)
        .map(user-> validatePassword(request.getPassword(), user))
        .map(jwtService::generateJwt);
  }

  private User validatePassword(String password, User user) {
    String concat = password.concat(user.getSalt());
    String password_hash = Hashing.sha1().hashString(concat, Charsets.UTF_8).toString();

    if (!user.getHash().equals(password_hash)) throw new InvalidPassword();

    return user;
  }

  class InvalidPassword extends RuntimeException {}
}
