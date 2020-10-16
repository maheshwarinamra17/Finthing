package database_layer;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityDeletionOrUpdateAdapter;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.arch.persistence.room.SharedSQLiteStatement;
import android.database.Cursor;
import java.lang.Double;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;

public class TransactionTBDao_Impl implements TransactionTBDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfTransactionTB;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfTransactionTB;

  private final EntityDeletionOrUpdateAdapter __updateAdapterOfTransactionTB;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public TransactionTBDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfTransactionTB = new EntityInsertionAdapter<TransactionTB>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `transaction_tb`(`id`,`trans_ts`,`narration`,`amount`,`refer_id`,`balance`,`tag_id`,`bank_id`,`sms_ts`,`location`,`mode`,`temp_id`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, TransactionTB value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.getId());
        }
        if (value.getTransTS() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindLong(2, value.getTransTS());
        }
        if (value.getNarration() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getNarration());
        }
        if (value.getAmount() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindDouble(4, value.getAmount());
        }
        if (value.getReferID() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getReferID());
        }
        if (value.getBalance() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindDouble(6, value.getBalance());
        }
        if (value.getTagID() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindLong(7, value.getTagID());
        }
        if (value.getBankID() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getBankID());
        }
        if (value.getSmsTS() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindLong(9, value.getSmsTS());
        }
        if (value.getLocation() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.getLocation());
        }
        if (value.getMode() == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindString(11, value.getMode());
        }
        if (value.getTempID() == null) {
          stmt.bindNull(12);
        } else {
          stmt.bindString(12, value.getTempID());
        }
      }
    };
    this.__deletionAdapterOfTransactionTB = new EntityDeletionOrUpdateAdapter<TransactionTB>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `transaction_tb` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, TransactionTB value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.getId());
        }
      }
    };
    this.__updateAdapterOfTransactionTB = new EntityDeletionOrUpdateAdapter<TransactionTB>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `transaction_tb` SET `id` = ?,`trans_ts` = ?,`narration` = ?,`amount` = ?,`refer_id` = ?,`balance` = ?,`tag_id` = ?,`bank_id` = ?,`sms_ts` = ?,`location` = ?,`mode` = ?,`temp_id` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, TransactionTB value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.getId());
        }
        if (value.getTransTS() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindLong(2, value.getTransTS());
        }
        if (value.getNarration() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getNarration());
        }
        if (value.getAmount() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindDouble(4, value.getAmount());
        }
        if (value.getReferID() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getReferID());
        }
        if (value.getBalance() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindDouble(6, value.getBalance());
        }
        if (value.getTagID() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindLong(7, value.getTagID());
        }
        if (value.getBankID() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getBankID());
        }
        if (value.getSmsTS() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindLong(9, value.getSmsTS());
        }
        if (value.getLocation() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.getLocation());
        }
        if (value.getMode() == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindString(11, value.getMode());
        }
        if (value.getTempID() == null) {
          stmt.bindNull(12);
        } else {
          stmt.bindString(12, value.getTempID());
        }
        if (value.getId() == null) {
          stmt.bindNull(13);
        } else {
          stmt.bindLong(13, value.getId());
        }
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM transaction_tb";
        return _query;
      }
    };
  }

  @Override
  public void insertAll(ArrayList<TransactionTB> transactions) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfTransactionTB.insert(transactions);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(TransactionTB transaction) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfTransactionTB.handle(transaction);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(TransactionTB transaction) {
    __db.beginTransaction();
    try {
      __updateAdapterOfTransactionTB.handle(transaction);
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
  public List<TransactionTB> getTransactions(Long startTS, Long endTS) {
    final String _sql = "SELECT * FROM transaction_tb WHERE trans_ts BETWEEN ? AND ? order by trans_ts DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (startTS == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindLong(_argIndex, startTS);
    }
    _argIndex = 2;
    if (endTS == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindLong(_argIndex, endTS);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfTransTS = _cursor.getColumnIndexOrThrow("trans_ts");
      final int _cursorIndexOfNarration = _cursor.getColumnIndexOrThrow("narration");
      final int _cursorIndexOfAmount = _cursor.getColumnIndexOrThrow("amount");
      final int _cursorIndexOfReferID = _cursor.getColumnIndexOrThrow("refer_id");
      final int _cursorIndexOfBalance = _cursor.getColumnIndexOrThrow("balance");
      final int _cursorIndexOfTagID = _cursor.getColumnIndexOrThrow("tag_id");
      final int _cursorIndexOfBankID = _cursor.getColumnIndexOrThrow("bank_id");
      final int _cursorIndexOfSmsTS = _cursor.getColumnIndexOrThrow("sms_ts");
      final int _cursorIndexOfLocation = _cursor.getColumnIndexOrThrow("location");
      final int _cursorIndexOfMode = _cursor.getColumnIndexOrThrow("mode");
      final int _cursorIndexOfTempID = _cursor.getColumnIndexOrThrow("temp_id");
      final List<TransactionTB> _result = new ArrayList<TransactionTB>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final TransactionTB _item;
        _item = new TransactionTB();
        final Integer _tmpId;
        if (_cursor.isNull(_cursorIndexOfId)) {
          _tmpId = null;
        } else {
          _tmpId = _cursor.getInt(_cursorIndexOfId);
        }
        _item.setId(_tmpId);
        final Long _tmpTransTS;
        if (_cursor.isNull(_cursorIndexOfTransTS)) {
          _tmpTransTS = null;
        } else {
          _tmpTransTS = _cursor.getLong(_cursorIndexOfTransTS);
        }
        _item.setTransTS(_tmpTransTS);
        final String _tmpNarration;
        _tmpNarration = _cursor.getString(_cursorIndexOfNarration);
        _item.setNarration(_tmpNarration);
        final Double _tmpAmount;
        if (_cursor.isNull(_cursorIndexOfAmount)) {
          _tmpAmount = null;
        } else {
          _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
        }
        _item.setAmount(_tmpAmount);
        final String _tmpReferID;
        _tmpReferID = _cursor.getString(_cursorIndexOfReferID);
        _item.setReferID(_tmpReferID);
        final Double _tmpBalance;
        if (_cursor.isNull(_cursorIndexOfBalance)) {
          _tmpBalance = null;
        } else {
          _tmpBalance = _cursor.getDouble(_cursorIndexOfBalance);
        }
        _item.setBalance(_tmpBalance);
        final Integer _tmpTagID;
        if (_cursor.isNull(_cursorIndexOfTagID)) {
          _tmpTagID = null;
        } else {
          _tmpTagID = _cursor.getInt(_cursorIndexOfTagID);
        }
        _item.setTagID(_tmpTagID);
        final String _tmpBankID;
        _tmpBankID = _cursor.getString(_cursorIndexOfBankID);
        _item.setBankID(_tmpBankID);
        final Long _tmpSmsTS;
        if (_cursor.isNull(_cursorIndexOfSmsTS)) {
          _tmpSmsTS = null;
        } else {
          _tmpSmsTS = _cursor.getLong(_cursorIndexOfSmsTS);
        }
        _item.setSmsTS(_tmpSmsTS);
        final String _tmpLocation;
        _tmpLocation = _cursor.getString(_cursorIndexOfLocation);
        _item.setLocation(_tmpLocation);
        final String _tmpMode;
        _tmpMode = _cursor.getString(_cursorIndexOfMode);
        _item.setMode(_tmpMode);
        final String _tmpTempID;
        _tmpTempID = _cursor.getString(_cursorIndexOfTempID);
        _item.setTempID(_tmpTempID);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<TransactionTB> getNonTaggedTrans() {
    final String _sql = "SELECT * FROM transaction_tb WHERE tag_id IS NULL";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfTransTS = _cursor.getColumnIndexOrThrow("trans_ts");
      final int _cursorIndexOfNarration = _cursor.getColumnIndexOrThrow("narration");
      final int _cursorIndexOfAmount = _cursor.getColumnIndexOrThrow("amount");
      final int _cursorIndexOfReferID = _cursor.getColumnIndexOrThrow("refer_id");
      final int _cursorIndexOfBalance = _cursor.getColumnIndexOrThrow("balance");
      final int _cursorIndexOfTagID = _cursor.getColumnIndexOrThrow("tag_id");
      final int _cursorIndexOfBankID = _cursor.getColumnIndexOrThrow("bank_id");
      final int _cursorIndexOfSmsTS = _cursor.getColumnIndexOrThrow("sms_ts");
      final int _cursorIndexOfLocation = _cursor.getColumnIndexOrThrow("location");
      final int _cursorIndexOfMode = _cursor.getColumnIndexOrThrow("mode");
      final int _cursorIndexOfTempID = _cursor.getColumnIndexOrThrow("temp_id");
      final List<TransactionTB> _result = new ArrayList<TransactionTB>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final TransactionTB _item;
        _item = new TransactionTB();
        final Integer _tmpId;
        if (_cursor.isNull(_cursorIndexOfId)) {
          _tmpId = null;
        } else {
          _tmpId = _cursor.getInt(_cursorIndexOfId);
        }
        _item.setId(_tmpId);
        final Long _tmpTransTS;
        if (_cursor.isNull(_cursorIndexOfTransTS)) {
          _tmpTransTS = null;
        } else {
          _tmpTransTS = _cursor.getLong(_cursorIndexOfTransTS);
        }
        _item.setTransTS(_tmpTransTS);
        final String _tmpNarration;
        _tmpNarration = _cursor.getString(_cursorIndexOfNarration);
        _item.setNarration(_tmpNarration);
        final Double _tmpAmount;
        if (_cursor.isNull(_cursorIndexOfAmount)) {
          _tmpAmount = null;
        } else {
          _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
        }
        _item.setAmount(_tmpAmount);
        final String _tmpReferID;
        _tmpReferID = _cursor.getString(_cursorIndexOfReferID);
        _item.setReferID(_tmpReferID);
        final Double _tmpBalance;
        if (_cursor.isNull(_cursorIndexOfBalance)) {
          _tmpBalance = null;
        } else {
          _tmpBalance = _cursor.getDouble(_cursorIndexOfBalance);
        }
        _item.setBalance(_tmpBalance);
        final Integer _tmpTagID;
        if (_cursor.isNull(_cursorIndexOfTagID)) {
          _tmpTagID = null;
        } else {
          _tmpTagID = _cursor.getInt(_cursorIndexOfTagID);
        }
        _item.setTagID(_tmpTagID);
        final String _tmpBankID;
        _tmpBankID = _cursor.getString(_cursorIndexOfBankID);
        _item.setBankID(_tmpBankID);
        final Long _tmpSmsTS;
        if (_cursor.isNull(_cursorIndexOfSmsTS)) {
          _tmpSmsTS = null;
        } else {
          _tmpSmsTS = _cursor.getLong(_cursorIndexOfSmsTS);
        }
        _item.setSmsTS(_tmpSmsTS);
        final String _tmpLocation;
        _tmpLocation = _cursor.getString(_cursorIndexOfLocation);
        _item.setLocation(_tmpLocation);
        final String _tmpMode;
        _tmpMode = _cursor.getString(_cursorIndexOfMode);
        _item.setMode(_tmpMode);
        final String _tmpTempID;
        _tmpTempID = _cursor.getString(_cursorIndexOfTempID);
        _item.setTempID(_tmpTempID);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Long getLastSmsTS() {
    final String _sql = "SELECT MAX(sms_ts) FROM transaction_tb";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final Long _result;
      if(_cursor.moveToFirst()) {
        final Long _tmp;
        if (_cursor.isNull(0)) {
          _tmp = null;
        } else {
          _tmp = _cursor.getLong(0);
        }
        _result = _tmp;
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
  public Long getLastTransactionTS() {
    final String _sql = "SELECT MAX(trans_ts) FROM transaction_tb";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final Long _result;
      if(_cursor.moveToFirst()) {
        final Long _tmp;
        if (_cursor.isNull(0)) {
          _tmp = null;
        } else {
          _tmp = _cursor.getLong(0);
        }
        _result = _tmp;
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
