package androidx.room.paging;

import android.database.Cursor;
import androidx.paging.PositionalDataSource.LoadInitialCallback;
import androidx.paging.PositionalDataSource.LoadInitialParams;
import androidx.paging.PositionalDataSource.LoadRangeCallback;
import androidx.paging.PositionalDataSource.LoadRangeParams;
import androidx.paging.PositionalDataSource;
import androidx.room.InvalidationTracker.Observer;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteQuery;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class LimitOffsetDataSource extends PositionalDataSource {
    private final String mCountQuery;
    private final RoomDatabase mDb;
    private final boolean mInTransaction;
    private final String mLimitOffsetQuery;
    private final Observer mObserver;
    private final AtomicBoolean mRegisteredObserver;
    private final RoomSQLiteQuery mSourceQuery;

    protected LimitOffsetDataSource(RoomDatabase roomDatabase0, RoomSQLiteQuery roomSQLiteQuery0, boolean z, boolean z1, String[] arr_s) {
        this.mRegisteredObserver = new AtomicBoolean(false);
        this.mDb = roomDatabase0;
        this.mSourceQuery = roomSQLiteQuery0;
        this.mInTransaction = z;
        this.mCountQuery = "SELECT COUNT(*) FROM ( " + roomSQLiteQuery0.getSql() + " )";
        this.mLimitOffsetQuery = "SELECT * FROM ( " + roomSQLiteQuery0.getSql() + " ) LIMIT ? OFFSET ?";
        this.mObserver = new Observer(arr_s) {
            @Override  // androidx.room.InvalidationTracker$Observer
            public void onInvalidated(Set set0) {
                LimitOffsetDataSource.this.invalidate();
            }
        };
        if(z1) {
            this.registerObserverIfNecessary();
        }
    }

    protected LimitOffsetDataSource(RoomDatabase roomDatabase0, RoomSQLiteQuery roomSQLiteQuery0, boolean z, String[] arr_s) {
        this(roomDatabase0, roomSQLiteQuery0, z, true, arr_s);
    }

    protected LimitOffsetDataSource(RoomDatabase roomDatabase0, SupportSQLiteQuery supportSQLiteQuery0, boolean z, boolean z1, String[] arr_s) {
        this(roomDatabase0, RoomSQLiteQuery.copyFrom(supportSQLiteQuery0), z, z1, arr_s);
    }

    protected LimitOffsetDataSource(RoomDatabase roomDatabase0, SupportSQLiteQuery supportSQLiteQuery0, boolean z, String[] arr_s) {
        this(roomDatabase0, RoomSQLiteQuery.copyFrom(supportSQLiteQuery0), z, arr_s);
    }

    protected abstract List convertRows(Cursor arg1);

    public int countItems() {
        this.registerObserverIfNecessary();
        RoomSQLiteQuery roomSQLiteQuery0 = RoomSQLiteQuery.acquire(this.mCountQuery, this.mSourceQuery.getArgCount());
        roomSQLiteQuery0.copyArgumentsFrom(this.mSourceQuery);
        Cursor cursor0 = this.mDb.query(roomSQLiteQuery0);
        try {
            return cursor0.moveToFirst() ? cursor0.getInt(0) : 0;
        }
        finally {
            cursor0.close();
            roomSQLiteQuery0.release();
        }
    }

    private RoomSQLiteQuery getSQLiteQuery(int v, int v1) {
        RoomSQLiteQuery roomSQLiteQuery0 = RoomSQLiteQuery.acquire(this.mLimitOffsetQuery, this.mSourceQuery.getArgCount() + 2);
        roomSQLiteQuery0.copyArgumentsFrom(this.mSourceQuery);
        roomSQLiteQuery0.bindLong(roomSQLiteQuery0.getArgCount() - 1, ((long)v1));
        roomSQLiteQuery0.bindLong(roomSQLiteQuery0.getArgCount(), ((long)v));
        return roomSQLiteQuery0;
    }

    public boolean isInvalid() {
        this.registerObserverIfNecessary();
        this.mDb.getInvalidationTracker().refreshVersionsSync();
        return super.isInvalid();
    }

    public void loadInitial(PositionalDataSource.LoadInitialParams positionalDataSource$LoadInitialParams0, PositionalDataSource.LoadInitialCallback positionalDataSource$LoadInitialCallback0) {
        int v2;
        RoomSQLiteQuery roomSQLiteQuery1;
        RoomSQLiteQuery roomSQLiteQuery0;
        int v;
        this.registerObserverIfNecessary();
        List list0 = Collections.EMPTY_LIST;
        this.mDb.beginTransaction();
        Cursor cursor0 = null;
        try {
            v = this.countItems();
            if(v == 0) {
                v2 = 0;
                roomSQLiteQuery1 = null;
            }
            else {
                int v1 = LimitOffsetDataSource.computeInitialLoadPosition(positionalDataSource$LoadInitialParams0, v);
                roomSQLiteQuery0 = this.getSQLiteQuery(v1, LimitOffsetDataSource.computeInitialLoadSize(positionalDataSource$LoadInitialParams0, v1, v));
                try {
                    cursor0 = this.mDb.query(roomSQLiteQuery0);
                    List list1 = this.convertRows(cursor0);
                    this.mDb.setTransactionSuccessful();
                    roomSQLiteQuery1 = roomSQLiteQuery0;
                    v2 = v1;
                    list0 = list1;
                }
                catch(Throwable throwable0) {
                    goto label_22;
                }
            }
            goto label_28;
        }
        catch(Throwable throwable0) {
            roomSQLiteQuery0 = null;
        }
    label_22:
        if(cursor0 != null) {
            cursor0.close();
        }
        this.mDb.endTransaction();
        if(roomSQLiteQuery0 != null) {
            roomSQLiteQuery0.release();
        }
        throw throwable0;
    label_28:
        if(cursor0 != null) {
            cursor0.close();
        }
        this.mDb.endTransaction();
        if(roomSQLiteQuery1 != null) {
            roomSQLiteQuery1.release();
        }
        positionalDataSource$LoadInitialCallback0.onResult(list0, v2, v);
    }

    public List loadRange(int v, int v1) {
        List list0;
        RoomSQLiteQuery roomSQLiteQuery0 = this.getSQLiteQuery(v, v1);
        if(this.mInTransaction) {
            this.mDb.beginTransaction();
            Cursor cursor0 = null;
            try {
                cursor0 = this.mDb.query(roomSQLiteQuery0);
                list0 = this.convertRows(cursor0);
                this.mDb.setTransactionSuccessful();
            }
            catch(Throwable throwable0) {
                if(cursor0 != null) {
                    cursor0.close();
                }
                this.mDb.endTransaction();
                roomSQLiteQuery0.release();
                throw throwable0;
            }
            if(cursor0 != null) {
                cursor0.close();
            }
            this.mDb.endTransaction();
            roomSQLiteQuery0.release();
            return list0;
        }
        Cursor cursor1 = this.mDb.query(roomSQLiteQuery0);
        try {
            return this.convertRows(cursor1);
        }
        finally {
            cursor1.close();
            roomSQLiteQuery0.release();
        }
    }

    public void loadRange(PositionalDataSource.LoadRangeParams positionalDataSource$LoadRangeParams0, PositionalDataSource.LoadRangeCallback positionalDataSource$LoadRangeCallback0) {
        positionalDataSource$LoadRangeCallback0.onResult(this.loadRange(positionalDataSource$LoadRangeParams0.startPosition, positionalDataSource$LoadRangeParams0.loadSize));
    }

    private void registerObserverIfNecessary() {
        if(this.mRegisteredObserver.compareAndSet(false, true)) {
            this.mDb.getInvalidationTracker().addWeakObserver(this.mObserver);
        }
    }
}

