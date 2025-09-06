package androidx.work.impl.model;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

public final class PreferenceDao_Impl implements PreferenceDao {
    private final RoomDatabase __db;
    private final EntityInsertionAdapter __insertionAdapterOfPreference;

    public PreferenceDao_Impl(RoomDatabase roomDatabase0) {
        this.__db = roomDatabase0;
        this.__insertionAdapterOfPreference = new EntityInsertionAdapter(roomDatabase0) {
            protected void bind(SupportSQLiteStatement supportSQLiteStatement0, Preference preference0) {
                supportSQLiteStatement0.bindString(1, preference0.getKey());
                if(preference0.getValue() == null) {
                    supportSQLiteStatement0.bindNull(2);
                    return;
                }
                supportSQLiteStatement0.bindLong(2, ((long)preference0.getValue()));
            }

            @Override  // androidx.room.EntityInsertionAdapter
            protected void bind(SupportSQLiteStatement supportSQLiteStatement0, Object object0) {
                this.bind(supportSQLiteStatement0, ((Preference)object0));
            }

            @Override  // androidx.room.SharedSQLiteStatement
            protected String createQuery() {
                return "INSERT OR REPLACE INTO `Preference` (`key`,`long_value`) VALUES (?,?)";
            }
        };
    }

    @Override  // androidx.work.impl.model.PreferenceDao
    public Long getLongValue(String s) {
        RoomSQLiteQuery roomSQLiteQuery0 = RoomSQLiteQuery.acquire("SELECT long_value FROM Preference where `key`=?", 1);
        roomSQLiteQuery0.bindString(1, s);
        this.__db.assertNotSuspendingTransaction();
        Long long0 = null;
        Cursor cursor0 = DBUtil.query(this.__db, roomSQLiteQuery0, false, null);
        try {
            if(cursor0.moveToFirst() && !cursor0.isNull(0)) {
                long0 = cursor0.getLong(0);
            }
            return long0;
        }
        finally {
            cursor0.close();
            roomSQLiteQuery0.release();
        }
    }

    @Override  // androidx.work.impl.model.PreferenceDao
    public LiveData getObservableLongValue(String s) {
        RoomSQLiteQuery roomSQLiteQuery0 = RoomSQLiteQuery.acquire("SELECT long_value FROM Preference where `key`=?", 1);
        roomSQLiteQuery0.bindString(1, s);
        androidx.work.impl.model.PreferenceDao_Impl.2 preferenceDao_Impl$20 = new Callable() {
            public Long call() throws Exception {
                try(Cursor cursor0 = DBUtil.query(PreferenceDao_Impl.this.__db, roomSQLiteQuery0, false, null)) {
                    return cursor0.moveToFirst() && !cursor0.isNull(0) ? cursor0.getLong(0) : null;
                }
            }

            @Override
            public Object call() throws Exception {
                return this.call();
            }

            @Override
            protected void finalize() {
                roomSQLiteQuery0.release();
            }
        };
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"Preference"}, false, preferenceDao_Impl$20);
    }

    public static List getRequiredConverters() {
        return Collections.EMPTY_LIST;
    }

    @Override  // androidx.work.impl.model.PreferenceDao
    public void insertPreference(Preference preference0) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfPreference.insert(preference0);
            this.__db.setTransactionSuccessful();
        }
        finally {
            this.__db.endTransaction();
        }
    }
}

