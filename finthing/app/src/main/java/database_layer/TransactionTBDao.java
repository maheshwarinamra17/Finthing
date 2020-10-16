package database_layer;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface TransactionTBDao{

    @Query("SELECT * FROM "+ DBConfig.TRANSACTION_TB + " WHERE trans_ts BETWEEN :startTS AND :endTS order by trans_ts DESC")
    List<TransactionTB> getTransactions(Long startTS, Long endTS);

    @Query("SELECT * FROM "+ DBConfig.TRANSACTION_TB + " WHERE tag_id IS NULL")
    List<TransactionTB> getNonTaggedTrans();

    @Query("SELECT MAX(sms_ts) FROM " + DBConfig.TRANSACTION_TB)
    Long getLastSmsTS();

    @Query("SELECT MAX(trans_ts) FROM " + DBConfig.TRANSACTION_TB)
    Long getLastTransactionTS();

    @Query("DELETE FROM " + DBConfig.TRANSACTION_TB)
    void deleteAll();

    @Insert
    void insertAll(ArrayList<TransactionTB> transactions);

    @Update
    void update(TransactionTB transaction);

    @Delete
    void delete(TransactionTB transaction);
}

