package com.queue.core;

public interface AuthRequest extends JwtRequest {
  String getEmail();
  String getPassword();
}
