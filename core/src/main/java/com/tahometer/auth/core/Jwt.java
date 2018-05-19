package com.tahometer.auth.core;

public interface Jwt {
  String getAccessToken();
  String getRenewalToken();
}
