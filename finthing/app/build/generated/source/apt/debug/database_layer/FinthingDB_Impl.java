package database_layer;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Callback;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Configuration;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.RoomOpenHelper;
import android.arch.persistence.room.RoomOpenHelper.Delegate;
import android.arch.persistence.room.util.TableInfo;
import android.arch.persistence.room.util.TableInfo.Column;
import android.arch.persistence.room.util.TableInfo.ForeignKey;
import android.arch.persistence.room.util.TableInfo.Index;
import java.lang.IllegalStateException;
import java.lang.Override;
import java.lang.String;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class FinthingDB_Impl extends FinthingDB {
  private volatile TransactionTBDao _transactionTBDao;

  private volatile TagsTBDao _tagsTBDao;

  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `transaction_tb` (`id` INTEGER, `trans_ts` INTEGER, `narration` TEXT, `amount` REAL, `refer_id` TEXT, `balance` REAL, `tag_id` INTEGER, `bank_id` TEXT, `sms_ts` INTEGER, `location` TEXT, `mode` TEXT, `temp_id` TEXT, PRIMARY KEY(`id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `tags_tb` (`id` INTEGER, `name` TEXT, `idn` TEXT, `type` TEXT, `category` TEXT, PRIMARY KEY(`id`))");
        _db.execSQL("CREATE UNIQUE INDEX `index_tags_tb_idn` ON `tags_tb` (`idn`)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"74c277a14db4944291d7d2bfb63f8da6\")");
      }

      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `transaction_tb`");
        _db.execSQL("DROP TABLE IF EXISTS `tags_tb`");
      }

      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      protected void validateMigration(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsTransactionTb = new HashMap<String, TableInfo.Column>(12);
        _columnsTransactionTb.put("id", new TableInfo.Column("id", "INTEGER", false, 1));
        _columnsTransactionTb.put("trans_ts", new TableInfo.Column("trans_ts", "INTEGER", false, 0));
        _columnsTransactionTb.put("narration", new TableInfo.Column("narration", "TEXT", false, 0));
        _columnsTransactionTb.put("amount", new TableInfo.Column("amount", "REAL", false, 0));
        _columnsTransactionTb.put("refer_id", new TableInfo.Column("refer_id", "TEXT", false, 0));
        _columnsTransactionTb.put("balance", new TableInfo.Column("balance", "REAL", false, 0));
        _columnsTransactionTb.put("tag_id", new TableInfo.Column("tag_id", "INTEGER", false, 0));
        _columnsTransactionTb.put("bank_id", new TableInfo.Column("bank_id", "TEXT", false, 0));
        _columnsTransactionTb.put("sms_ts", new TableInfo.Column("sms_ts", "INTEGER", false, 0));
        _columnsTransactionTb.put("location", new TableInfo.Column("location", "TEXT", false, 0));
        _columnsTransactionTb.put("mode", new TableInfo.Column("mode", "TEXT", false, 0));
        _columnsTransactionTb.put("temp_id", new TableInfo.Column("temp_id", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTransactionTb = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTransactionTb = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoTransactionTb = new TableInfo("transaction_tb", _columnsTransactionTb, _foreignKeysTransactionTb, _indicesTransactionTb);
        final TableInfo _existingTransactionTb = TableInfo.read(_db, "transaction_tb");
        if (! _infoTransactionTb.equals(_existingTransactionTb)) {
          throw new IllegalStateException("Migration didn't properly handle transaction_tb(database_layer.TransactionTB).\n"
                  + " Expected:\n" + _infoTransactionTb + "\n"
                  + " Found:\n" + _existingTransactionTb);
        }
        final HashMap<String, TableInfo.Column> _columnsTagsTb = new HashMap<String, TableInfo.Column>(5);
        _columnsTagsTb.put("id", new TableInfo.Column("id", "INTEGER", false, 1));
        _columnsTagsTb.put("name", new TableInfo.Column("name", "TEXT", false, 0));
        _columnsTagsTb.put("idn", new TableInfo.Column("idn", "TEXT", false, 0));
        _columnsTagsTb.put("type", new TableInfo.Column("type", "TEXT", false, 0));
        _columnsTagsTb.put("category", new TableInfo.Column("category", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTagsTb = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTagsTb = new HashSet<TableInfo.Index>(1);
        _indicesTagsTb.add(new TableInfo.Index("index_tags_tb_idn", true, Arrays.asList("idn")));
        final TableInfo _infoTagsTb = new TableInfo("tags_tb", _columnsTagsTb, _foreignKeysTagsTb, _indicesTagsTb);
        final TableInfo _existingTagsTb = TableInfo.read(_db, "tags_tb");
        if (! _infoTagsTb.equals(_existingTagsTb)) {
          throw new IllegalStateException("Migration didn't properly handle tags_tb(database_layer.TagsTB).\n"
                  + " Expected:\n" + _infoTagsTb + "\n"
                  + " Found:\n" + _existingTagsTb);
        }
      }
    }, "74c277a14db4944291d7d2bfb63f8da6");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    return new InvalidationTracker(this, "transaction_tb","tags_tb");
  }

  @Override
  public TransactionTBDao transactionTBDao() {
    if (_transactionTBDao != null) {
      return _transactionTBDao;
    } else {
      synchronized(this) {
        if(_transactionTBDao == null) {
          _transactionTBDao = new TransactionTBDao_Impl(this);
        }
        return _transactionTBDao;
      }
    }
  }

  @Override
  public TagsTBDao tagsTBDao() {
    if (_tagsTBDao != null) {
      return _tagsTBDao;
    } else {
      synchronized(this) {
        if(_tagsTBDao == null) {
          _tagsTBDao = new TagsTBDao_Impl(this);
        }
        return _tagsTBDao;
      }
    }
  }
}
