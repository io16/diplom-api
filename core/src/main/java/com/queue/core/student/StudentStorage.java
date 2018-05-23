package com.queue.core.student;

import com.queue.core.Student;
import io.reactivex.Single;

public interface StudentStorage {
  Single<Student> getStudent(Integer id);
}
