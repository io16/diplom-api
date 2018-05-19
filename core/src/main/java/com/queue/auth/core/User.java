package com.queue.auth.core;

public interface User {
  Integer getId();
  String getEmail();
  String getSalt();
  String getHash();
}
