package com.queue.rest.student.adivice;

import com.queue.core.student.request.StudentAdviceRequest;

public class CancelAdviceRequest implements StudentAdviceRequest {
  @Override
  public Integer getTeacherId() {
    return null;
  }

  @Override
  public Integer getAdviceId() {
    return null;
  }
//  private String email;
//  private String password;
//
//  public void setEmail(String email) { this.email = email; }
//  public void setPassword(String password) { this.password = password; }
//
//  public String getEmail() { return email; }
//  public String getPassword() { return password; }
}
