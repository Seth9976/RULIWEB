package androidx.work.impl.model;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public final class WorkTagDao_Impl implements WorkTagDao {
    private final RoomDatabase __db;
    private final EntityInsertionAdapter __insertionAdapterOfWorkTag;
    private final SharedSQLiteStatement __preparedStmtOfDeleteByWorkSpecId;

    public WorkTagDao_Impl(RoomDatabase roomDatabase0) {
        this.__db = roomDatabase0;
        this.__insertionAdapterOfWorkTag = new EntityInsertionAdapter(roomDatabase0) {
            protected void bind(SupportSQLiteStatement supportSQLiteStatement0, WorkTag workTag0) {
                supportSQLiteStatement0.bindString(1, workTag0.getTag());
                supportSQLiteStatement0.bindString(2, workTag0.getWorkSpecId());
            }

            @Override  // androidx.room.EntityInsertionAdapter
            protected void bind(SupportSQLiteStatement supportSQLiteStatement0, Object object0) {
                this.bind(supportSQLiteStatement0, ((WorkTag)object0));
            }

            @Override  // androidx.room.SharedSQLiteStatement
            protected String createQuery() {
                return "INSERT OR IGNORE INTO `WorkTag` (`tag`,`work_spec_id`) VALUES (?,?)";
            }
        };
        this.__preparedStmtOfDeleteByWorkSpecId = new SharedSQLiteStatement(roomDatabase0) {
            @Override  // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM worktag WHERE work_spec_id=?";
            }
        };
    }

    @Override  // androidx.work.impl.model.WorkTagDao
    public void deleteByWorkSpecId(String s) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatement0 = this.__preparedStmtOfDeleteByWorkSpecId.acquire();
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
            this.__preparedStmtOfDeleteByWorkSpecId.release(supportSQLiteStatement0);
        }
    }

    public static List getRequiredConverters() {
        return Collections.EMPTY_LIST;
    }

    @Override  // androidx.work.impl.model.WorkTagDao
    public List getTagsForWorkSpecId(String s) {
        RoomSQLiteQuery roomSQLiteQuery0 = RoomSQLiteQuery.acquire("SELECT DISTINCT tag FROM worktag WHERE work_spec_id=?", 1);
        roomSQLiteQuery0.bindString(1, s);
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

    @Override  // androidx.work.impl.model.WorkTagDao
    public List getWorkSpecIdsWithTag(String s) {
        RoomSQLiteQuery roomSQLiteQuery0 = RoomSQLiteQuery.acquire("SELECT work_spec_id FROM worktag WHERE tag=?", 1);
        roomSQLiteQuery0.bindString(1, s);
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

    @Override  // androidx.work.impl.model.WorkTagDao
    public void insert(WorkTag workTag0) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfWorkTag.insert(workTag0);
            this.__db.setTransactionSuccessful();
        }
        finally {
            this.__db.endTransaction();
        }
    }

    @Override  // androidx.work.impl.model.WorkTagDao
    public void insertTags(String s, Set set0) {
        WorkTagDao.-CC.$default$insertTags(this, s, set0);
    }
}

