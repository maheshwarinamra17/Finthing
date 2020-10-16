package database_layer;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;
import android.os.AsyncTask;

public class DBBuilder {

    Context mCtx;
    FinthingDB dbContext;
    public DBBuilder(Context ctx) {
        mCtx = ctx;
    }

    public void createDB(){
        dbContext = Room.databaseBuilder(mCtx,
                FinthingDB.class, DBConfig.DATABASE_NAME + "-" + DBConfig.DATABASE_VERSION).build();
    }

    private static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("");
        }
    };

}
