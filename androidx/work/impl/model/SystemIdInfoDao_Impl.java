package androidx.work.impl.model;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class SystemIdInfoDao_Impl implements SystemIdInfoDao {
    private final RoomDatabase __db;
    private final EntityInsertionAdapter __insertionAdapterOfSystemIdInfo;
    private final SharedSQLiteStatement __preparedStmtOfRemoveSystemIdInfo;
    private final SharedSQLiteStatement __preparedStmtOfRemoveSystemIdInfo_1;

    public SystemIdInfoDao_Impl(RoomDatabase roomDatabase0) {
        this.__db = roomDatabase0;
        this.__insertionAdapterOfSystemIdInfo = new EntityInsertionAdapter(roomDatabase0) {
            protected void bind(SupportSQLiteStatement supportSQLiteStatement0, SystemIdInfo systemIdInfo0) {
                supportSQLiteStatement0.bindString(1, systemIdInfo0.workSpecId);
                supportSQLiteStatement0.bindLong(2, ((long)systemIdInfo0.getGeneration()));
                supportSQLiteStatement0.bindLong(3, ((long)systemIdInfo0.systemId));
            }

            @Override  // androidx.room.EntityInsertionAdapter
            protected void bind(SupportSQLiteStatement supportSQLiteStatement0, Object object0) {
                this.bind(supportSQLiteStatement0, ((SystemIdInfo)object0));
            }

            @Override  // androidx.room.SharedSQLiteStatement
            protected String createQuery() {
                return "INSERT OR REPLACE INTO `SystemIdInfo` (`work_spec_id`,`generation`,`system_id`) VALUES (?,?,?)";
            }
        };
        this.__preparedStmtOfRemoveSystemIdInfo = new SharedSQLiteStatement(roomDatabase0) {
            @Override  // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM SystemIdInfo where work_spec_id=? AND generation=?";
            }
        };
        this.__preparedStmtOfRemoveSystemIdInfo_1 = new SharedSQLiteStatement(roomDatabase0) {
            @Override  // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM SystemIdInfo where work_spec_id=?";
            }
        };
    }

    public static List getRequiredConverters() {
        return Collections.EMPTY_LIST;
    }

    @Override  // androidx.work.impl.model.SystemIdInfoDao
    public SystemIdInfo getSystemIdInfo(WorkGenerationalId workGenerationalId0) {
        return SystemIdInfoDao.-CC.$default$getSystemIdInfo(this, workGenerationalId0);
    }

    @Override  // androidx.work.impl.model.SystemIdInfoDao
    public SystemIdInfo getSystemIdInfo(String s, int v) {
        RoomSQLiteQuery roomSQLiteQuery0 = RoomSQLiteQuery.acquire("SELECT * FROM SystemIdInfo WHERE work_spec_id=? AND generation=?", 2);
        roomSQLiteQuery0.bindString(1, s);
        roomSQLiteQuery0.bindLong(2, ((long)v));
        this.__db.assertNotSuspendingTransaction();
        SystemIdInfo systemIdInfo0 = null;
        Cursor cursor0 = DBUtil.query(this.__db, roomSQLiteQuery0, false, null);
        try {
            int v2 = CursorUtil.getColumnIndexOrThrow(cursor0, "work_spec_id");
            int v3 = CursorUtil.getColumnIndexOrThrow(cursor0, "generation");
            int v4 = CursorUtil.getColumnIndexOrThrow(cursor0, "system_id");
            if(cursor0.moveToFirst()) {
                systemIdInfo0 = new SystemIdInfo(cursor0.getString(v2), cursor0.getInt(v3), cursor0.getInt(v4));
            }
            return systemIdInfo0;
        }
        finally {
            cursor0.close();
            roomSQLiteQuery0.release();
        }
    }

    @Override  // androidx.work.impl.model.SystemIdInfoDao
    public List getWorkSpecIds() {
        RoomSQLiteQuery roomSQLiteQuery0 = RoomSQLiteQuery.acquire("SELECT DISTINCT work_spec_id FROM SystemIdInfo", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursor0 = DBUtil.query(this.__db, roomSQLiteQuery0, false, null);
        try {
            List list0 = new ArrayList(cursor0.getCount());
            while(cursor0.moveToNext()) {
                list0.add(cursor0.getString(0));
            }
            return list0;
        }
        finally {
            cursor0.close();
            roomSQLiteQuery0.release();
        }
    }

    @Override  // androidx.work.impl.model.SystemIdInfoDao
    public void insertSystemIdInfo(SystemIdInfo systemIdInfo0) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfSystemIdInfo.insert(systemIdInfo0);
            this.__db.setTransactionSuccessful();
        }
        finally {
            this.__db.endTransaction();
        }
    }

    @Override  // androidx.work.impl.model.SystemIdInfoDao
    public void removeSystemIdInfo(WorkGenerationalId workGenerationalId0) {
        SystemIdInfoDao.-CC.$default$removeSystemIdInfo(this, workGenerationalId0);
    }

    @Override  // androidx.work.impl.model.SystemIdInfoDao
    public void removeSystemIdInfo(String s) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatement0 = this.__preparedStmtOfRemoveSystemIdInfo_1.acquire();
        supportSQLiteStatement0.bindString(1, s);
        try {
            this.__db.beginTransaction();
            try {
                supportSQLiteStatement0.executeUpdateDelete();
                this.__db.setTransactionSuccessful();
            }
            finally {
                this.__db.endTransaction();
            }
        }
        finally {
            this.__preparedStmtOfRemoveSystemIdInfo_1.release(supportSQLiteStatement0);
        }
    }

    @Override  // androidx.work.impl.model.SystemIdInfoDao
    public void removeSystemIdInfo(String s, int v) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatement0 = this.__preparedStmtOfRemoveSystemIdInfo.acquire();
        supportSQLiteStatement0.bindString(1, s);
        supportSQLiteStatement0.bindLong(2, ((long)v));
        try {
            this.__db.beginTransaction();
            try {
                supportSQLiteStatement0.executeUpdateDelete();
                this.__db.setTransactionSuccessful();
            }
            finally {
                this.__db.endTransaction();
            }
        }
        finally {
            this.__preparedStmtOfRemoveSystemIdInfo.release(supportSQLiteStatement0);
        }
    }
}

