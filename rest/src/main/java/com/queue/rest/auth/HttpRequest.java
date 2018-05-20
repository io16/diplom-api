package com.queue.rest.auth;

import com.queue.core.AuthRequest;

public class HttpRequest implements AuthRequest {
  private String email;
  private String password;

  public void setEmail(String email) { this.email = email; }
  public void setPassword(String password) { this.password = password; }

  @Override public String getEmail() { return email; }
  @Override public String getPassword() { return password; }
}
