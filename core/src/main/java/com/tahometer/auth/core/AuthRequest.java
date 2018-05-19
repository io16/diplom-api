package com.tahometer.auth.core;

public interface AuthRequest extends JwtRequest {
  String getEmail();
  String getPassword();
}
