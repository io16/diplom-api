package com.queue.core;

public interface User {
  Integer getId();
  String getEmail();
  String getSalt();
  String getHash();
}
