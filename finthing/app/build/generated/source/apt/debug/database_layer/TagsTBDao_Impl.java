package database_layer;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityDeletionOrUpdateAdapter;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.arch.persistence.room.SharedSQLiteStatement;
import android.database.Cursor;
import java.lang.Integer;
import java.lang.Override;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;

public class TagsTBDao_Impl implements TagsTBDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfTagsTB;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfTagsTB;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public TagsTBDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfTagsTB = new EntityInsertionAdapter<TagsTB>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `tags_tb`(`id`,`name`,`idn`,`type`,`category`) VALUES (?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, TagsTB value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.getId());
        }
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        if (value.getIdn() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getIdn());
        }
        if (value.getType() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getType());
        }
        if (value.getCategory() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getCategory());
        }
      }
    };
    this.__deletionAdapterOfTagsTB = new EntityDeletionOrUpdateAdapter<TagsTB>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `tags_tb` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, TagsTB value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.getId());
        }
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM tags_tb";
        return _query;
      }
    };
  }

  @Override
  public void insertAll(ArrayList<TagsTB> tags) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfTagsTB.insert(tags);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(TagsTB tag) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfTagsTB.handle(tag);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAll() {
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAll.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAll.release(_stmt);
    }
  }

  @Override
  public List<TagsTB> getTags() {
    final String _sql = "SELECT * FROM tags_tb order by name DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfName = _cursor.getColumnIndexOrThrow("name");
      final int _cursorIndexOfIdn = _cursor.getColumnIndexOrThrow("idn");
      final int _cursorIndexOfType = _cursor.getColumnIndexOrThrow("type");
      final int _cursorIndexOfCategory = _cursor.getColumnIndexOrThrow("category");
      final List<TagsTB> _result = new ArrayList<TagsTB>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final TagsTB _item;
        _item = new TagsTB();
        final Integer _tmpId;
        if (_cursor.isNull(_cursorIndexOfId)) {
          _tmpId = null;
        } else {
          _tmpId = _cursor.getInt(_cursorIndexOfId);
        }
        _item.setId(_tmpId);
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        _item.setName(_tmpName);
        final String _tmpIdn;
        _tmpIdn = _cursor.getString(_cursorIndexOfIdn);
        _item.setIdn(_tmpIdn);
        final String _tmpType;
        _tmpType = _cursor.getString(_cursorIndexOfType);
        _item.setType(_tmpType);
        final String _tmpCategory;
        _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
        _item.setCategory(_tmpCategory);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public TagsTB getTagByIdn(String idn) {
    final String _sql = "SELECT * FROM tags_tb where idn = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (idn == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, idn);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfName = _cursor.getColumnIndexOrThrow("name");
      final int _cursorIndexOfIdn = _cursor.getColumnIndexOrThrow("idn");
      final int _cursorIndexOfType = _cursor.getColumnIndexOrThrow("type");
      final int _cursorIndexOfCategory = _cursor.getColumnIndexOrThrow("category");
      final TagsTB _result;
      if(_cursor.moveToFirst()) {
        _result = new TagsTB();
        final Integer _tmpId;
        if (_cursor.isNull(_cursorIndexOfId)) {
          _tmpId = null;
        } else {
          _tmpId = _cursor.getInt(_cursorIndexOfId);
        }
        _result.setId(_tmpId);
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        _result.setName(_tmpName);
        final String _tmpIdn;
        _tmpIdn = _cursor.getString(_cursorIndexOfIdn);
        _result.setIdn(_tmpIdn);
        final String _tmpType;
        _tmpType = _cursor.getString(_cursorIndexOfType);
        _result.setType(_tmpType);
        final String _tmpCategory;
        _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
        _result.setCategory(_tmpCategory);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<String> getAllIdns() {
    final String _sql = "SELECT idn FROM tags_tb";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final List<String> _result = new ArrayList<String>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final String _item;
        _item = _cursor.getString(0);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
