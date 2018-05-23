package com.queue.core.teacher;

import com.queue.core.Teacher;
import io.reactivex.Single;

public interface TeacherStorage {
  Single<Teacher> getTeacher(Integer id);
}
