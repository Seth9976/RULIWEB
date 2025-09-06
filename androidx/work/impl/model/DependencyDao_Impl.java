package androidx.work.impl.model;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class DependencyDao_Impl implements DependencyDao {
    private final RoomDatabase __db;
    private final EntityInsertionAdapter __insertionAdapterOfDependency;

    public DependencyDao_Impl(RoomDatabase roomDatabase0) {
        this.__db = roomDatabase0;
        this.__insertionAdapterOfDependency = new EntityInsertionAdapter(roomDatabase0) {
            protected void bind(SupportSQLiteStatement supportSQLiteStatement0, Dependency dependency0) {
                supportSQLiteStatement0.bindString(1, dependency0.getWorkSpecId());
                supportSQLiteStatement0.bindString(2, dependency0.getPrerequisiteId());
            }

            @Override  // androidx.room.EntityInsertionAdapter
            protected void bind(SupportSQLiteStatement supportSQLiteStatement0, Object object0) {
                this.bind(supportSQLiteStatement0, ((Dependency)object0));
            }

            @Override  // androidx.room.SharedSQLiteStatement
            protected String createQuery() {
                return "INSERT OR IGNORE INTO `Dependency` (`work_spec_id`,`prerequisite_id`) VALUES (?,?)";
            }
        };
    }

    @Override  // androidx.work.impl.model.DependencyDao
    public List getDependentWorkIds(String s) {
        RoomSQLiteQuery roomSQLiteQuery0 = RoomSQLiteQuery.acquire("SELECT work_spec_id FROM dependency WHERE prerequisite_id=?", 1);
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

    @Override  // androidx.work.impl.model.DependencyDao
    public List getPrerequisites(String s) {
        RoomSQLiteQuery roomSQLiteQuery0 = RoomSQLiteQuery.acquire("SELECT prerequisite_id FROM dependency WHERE work_spec_id=?", 1);
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

    public static List getRequiredConverters() {
        return Collections.EMPTY_LIST;
    }

    @Override  // androidx.work.impl.model.DependencyDao
    public boolean hasCompletedAllPrerequisites(String s) {
        boolean z = true;
        RoomSQLiteQuery roomSQLiteQuery0 = RoomSQLiteQuery.acquire("SELECT COUNT(*)=0 FROM dependency WHERE work_spec_id=? AND prerequisite_id IN (SELECT id FROM workspec WHERE state!=2)", 1);
        roomSQLiteQuery0.bindString(1, s);
        this.__db.assertNotSuspendingTransaction();
        boolean z1 = false;
        Cursor cursor0 = DBUtil.query(this.__db, roomSQLiteQuery0, false, null);
        try {
            if(cursor0.moveToFirst()) {
                if(cursor0.getInt(0) == 0) {
                    z = false;
                }
                z1 = z;
            }
            return z1;
        }
        finally {
            cursor0.close();
            roomSQLiteQuery0.release();
        }
    }

    @Override  // androidx.work.impl.model.DependencyDao
    public boolean hasDependents(String s) {
        boolean z = true;
        RoomSQLiteQuery roomSQLiteQuery0 = RoomSQLiteQuery.acquire("SELECT COUNT(*)>0 FROM dependency WHERE prerequisite_id=?", 1);
        roomSQLiteQuery0.bindString(1, s);
        this.__db.assertNotSuspendingTransaction();
        boolean z1 = false;
        Cursor cursor0 = DBUtil.query(this.__db, roomSQLiteQuery0, false, null);
        try {
            if(cursor0.moveToFirst()) {
                if(cursor0.getInt(0) == 0) {
                    z = false;
                }
                z1 = z;
            }
            return z1;
        }
        finally {
            cursor0.close();
            roomSQLiteQuery0.release();
        }
    }

    @Override  // androidx.work.impl.model.DependencyDao
    public void insertDependency(Dependency dependency0) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfDependency.insert(dependency0);
            this.__db.setTransactionSuccessful();
        }
        finally {
            this.__db.endTransaction();
        }
    }
}

