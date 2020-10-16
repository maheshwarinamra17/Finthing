package database_layer;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {TransactionTB.class, TagsTB.class}, version = 1)
public abstract class FinthingDB extends RoomDatabase {

    public abstract TransactionTBDao transactionTBDao();
    public abstract TagsTBDao tagsTBDao();

    private static FinthingDB INSTANCE;

    public static FinthingDB createDBInstance(Context mCtx){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(mCtx,
                    FinthingDB.class, DBConfig.DATABASE_NAME + "-" + DBConfig.DATABASE_VERSION).build();
        }
        return INSTANCE;
    }

    public static void destroyDBInstance() {
        INSTANCE = null;
    }
}
