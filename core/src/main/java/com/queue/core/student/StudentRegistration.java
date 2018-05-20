package com.queue.core.student;

import com.queue.core.Student;
import io.reactivex.Single;

public interface StudentRegistration {
  Single<Student> register(Student student);
}
