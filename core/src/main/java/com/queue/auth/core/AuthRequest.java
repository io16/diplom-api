package com.queue.auth.core;

public interface AuthRequest extends JwtRequest {
  String getEmail();
  String getPassword();
}
