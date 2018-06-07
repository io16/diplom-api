package com.queue.db;

import com.queue.core.*;
import com.queue.core.teacher.TeacherStorage;
import com.queue.db.model.GroupImpl;
import com.queue.db.model.TeacherImpl;
import io.reactiverse.reactivex.pgclient.PgPool;
import io.reactiverse.reactivex.pgclient.Row;
import io.reactiverse.reactivex.pgclient.Tuple;
import io.reactivex.Single;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class GroupStorageImpl implements GroupStorage {
  @Inject PgPool client;

  @Override
  public Single<List<Group>> getGroups() {
    var query = "select * from \"group\"";
    List<Group> groups = new ArrayList<>();
    return client.rxPreparedQuery(query)
        .map(rs ->
        {
          var rows = rs.iterator();
          while (rows.hasNext()) {
            var row = rows.next();
            groups.add(groupMapper(row));
          }
          return groups;
        });
  }

  private GroupImpl groupMapper(Row row) {
    return new GroupImpl(
        row.getInteger("id"),
        row.getString("name")
    );
  }
}
