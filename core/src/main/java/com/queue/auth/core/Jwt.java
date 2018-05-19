package com.queue.auth.core;

public interface Jwt {
  String getAccessToken();
  String getRenewalToken();
}
