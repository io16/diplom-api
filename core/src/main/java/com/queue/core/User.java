package com.queue.core;

@Deprecated
public interface User {
  Integer getId();
  String getEmail();
  String getSalt();
  String getHash();
}
