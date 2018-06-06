package com.queue.core;

import com.google.inject.AbstractModule;
import com.queue.core.student.StudentAdviceService;
import com.queue.core.student.StudentServiceImpl;

public class _Module extends AbstractModule {
  @Override
  protected void configure() {
    bind(StudentAdviceService.class).to(StudentServiceImpl.class);
  }
}
