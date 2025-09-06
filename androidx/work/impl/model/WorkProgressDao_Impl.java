package androidx.work.impl.model;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import androidx.work.Data;
import java.util.Collections;
import java.util.List;

public final class WorkProgressDao_Impl implements WorkProgressDao {
    private final RoomDatabase __db;
    private final EntityInsertionAdapter __insertionAdapterOfWorkProgress;
    private final SharedSQLiteStatement __preparedStmtOfDelete;
    private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

    public WorkProgressDao_Impl(RoomDatabase roomDatabase0) {
        this.__db = roomDatabase0;
        this.__insertionAdapterOfWorkProgress = new EntityInsertionAdapter(roomDatabase0) {
            protected void bind(SupportSQLiteStatement supportSQLiteStatement0, WorkProgress workProgress0) {
                supportSQLiteStatement0.bindString(1, workProgress0.getWorkSpecId());
                supportSQLiteStatement0.bindBlob(2, Data.toByteArrayInternalV1(workProgress0.getProgress()));
            }

            @Override  // androidx.room.EntityInsertionAdapter
            protected void bind(SupportSQLiteStatement supportSQLiteStatement0, Object object0) {
                this.bind(supportSQLiteStatement0, ((WorkProgress)object0));
            }

            @Override  // androidx.room.SharedSQLiteStatement
            protected String createQuery() {
                return "INSERT OR REPLACE INTO `WorkProgress` (`work_spec_id`,`progress`) VALUES (?,?)";
            }
        };
        this.__preparedStmtOfDelete = new SharedSQLiteStatement(roomDatabase0) {
            @Override  // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE from WorkProgress where work_spec_id=?";
            }
        };
        this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(roomDatabase0) {
            @Override  // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM WorkProgress";
            }
        };
    }

    @Override  // androidx.work.impl.model.WorkProgressDao
    public void delete(String s) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatement0 = this.__preparedStmtOfDelete.acquire();
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
            this.__preparedStmtOfDelete.release(supportSQLiteStatement0);
        }
    }

    @Override  // androidx.work.impl.model.WorkProgressDao
    public void deleteAll() {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatement0 = this.__preparedStmtOfDeleteAll.acquire();
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
            this.__preparedStmtOfDeleteAll.release(supportSQLiteStatement0);
        }
    }

    @Override  // androidx.work.impl.model.WorkProgressDao
    public Data getProgressForWorkSpecId(String s) {
        RoomSQLiteQuery roomSQLiteQuery0 = RoomSQLiteQuery.acquire("SELECT progress FROM WorkProgress WHERE work_spec_id=?", 1);
        roomSQLiteQuery0.bindString(1, s);
        this.__db.assertNotSuspendingTransaction();
        Data data0 = null;
        Cursor cursor0 = DBUtil.query(this.__db, roomSQLiteQuery0, false, null);
        try {
            if(cursor0.moveToFirst()) {
                byte[] arr_b = cursor0.isNull(0) ? null : cursor0.getBlob(0);
                if(arr_b != null) {
                    data0 = Data.fromByteArray(arr_b);
                }
            }
            return data0;
        }
        finally {
            cursor0.close();
            roomSQLiteQuery0.release();
        }
    }

    public static List getRequiredConverters() {
        return Collections.EMPTY_LIST;
    }

    @Override  // androidx.work.impl.model.WorkProgressDao
    public void insert(WorkProgress workProgress0) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfWorkProgress.insert(workProgress0);
            this.__db.setTransactionSuccessful();
        }
        finally {
            this.__db.endTransaction();
        }
    }
}

