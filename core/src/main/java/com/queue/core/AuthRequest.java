package com.queue.core;

@Deprecated
public interface AuthRequest extends JwtRequest {
  String getEmail();
  String getPassword();
}
