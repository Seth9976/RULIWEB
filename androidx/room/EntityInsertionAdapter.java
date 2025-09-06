package androidx.room;

import androidx.sqlite.db.SupportSQLiteStatement;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.ArrayIteratorKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u001C\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0016\n\u0000\n\u0002\u0010\u001E\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0002\b\'\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001A\u00020\u0004¢\u0006\u0002\u0010\u0005J\u001D\u0010\u0006\u001A\u00020\u00072\u0006\u0010\b\u001A\u00020\t2\u0006\u0010\n\u001A\u00028\u0000H$¢\u0006\u0002\u0010\u000BJ\u0013\u0010\f\u001A\u00020\u00072\u0006\u0010\n\u001A\u00028\u0000¢\u0006\u0002\u0010\rJ\u001B\u0010\f\u001A\u00020\u00072\u000E\u0010\u000E\u001A\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u000F¢\u0006\u0002\u0010\u0010J\u0014\u0010\f\u001A\u00020\u00072\f\u0010\u000E\u001A\b\u0012\u0004\u0012\u00028\u00000\u0011J\u0013\u0010\u0012\u001A\u00020\u00132\u0006\u0010\n\u001A\u00028\u0000¢\u0006\u0002\u0010\u0014J\u001B\u0010\u0015\u001A\u00020\u00162\u000E\u0010\u000E\u001A\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u000F¢\u0006\u0002\u0010\u0017J\u0014\u0010\u0015\u001A\u00020\u00162\f\u0010\u000E\u001A\b\u0012\u0004\u0012\u00028\u00000\u0018J#\u0010\u0019\u001A\n\u0012\u0006\b\u0001\u0012\u00020\u00130\u000F2\u000E\u0010\u000E\u001A\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u000F¢\u0006\u0002\u0010\u001AJ!\u0010\u0019\u001A\n\u0012\u0006\b\u0001\u0012\u00020\u00130\u000F2\f\u0010\u000E\u001A\b\u0012\u0004\u0012\u00028\u00000\u0018¢\u0006\u0002\u0010\u001BJ!\u0010\u001C\u001A\b\u0012\u0004\u0012\u00020\u00130\u001D2\u000E\u0010\u000E\u001A\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u000F¢\u0006\u0002\u0010\u001EJ\u001A\u0010\u001C\u001A\b\u0012\u0004\u0012\u00020\u00130\u001D2\f\u0010\u000E\u001A\b\u0012\u0004\u0012\u00028\u00000\u0018¨\u0006\u001F"}, d2 = {"Landroidx/room/EntityInsertionAdapter;", "T", "Landroidx/room/SharedSQLiteStatement;", "database", "Landroidx/room/RoomDatabase;", "(Landroidx/room/RoomDatabase;)V", "bind", "", "statement", "Landroidx/sqlite/db/SupportSQLiteStatement;", "entity", "(Landroidx/sqlite/db/SupportSQLiteStatement;Ljava/lang/Object;)V", "insert", "(Ljava/lang/Object;)V", "entities", "", "([Ljava/lang/Object;)V", "", "insertAndReturnId", "", "(Ljava/lang/Object;)J", "insertAndReturnIdsArray", "", "([Ljava/lang/Object;)[J", "", "insertAndReturnIdsArrayBox", "([Ljava/lang/Object;)[Ljava/lang/Long;", "(Ljava/util/Collection;)[Ljava/lang/Long;", "insertAndReturnIdsList", "", "([Ljava/lang/Object;)Ljava/util/List;", "room-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public abstract class EntityInsertionAdapter extends SharedSQLiteStatement {
    public EntityInsertionAdapter(RoomDatabase roomDatabase0) {
        Intrinsics.checkNotNullParameter(roomDatabase0, "database");
        super(roomDatabase0);
    }

    protected abstract void bind(SupportSQLiteStatement arg1, Object arg2);

    public final void insert(Iterable iterable0) {
        Intrinsics.checkNotNullParameter(iterable0, "entities");
        SupportSQLiteStatement supportSQLiteStatement0 = this.acquire();
        try {
            for(Object object0: iterable0) {
                this.bind(supportSQLiteStatement0, object0);
                supportSQLiteStatement0.executeInsert();
            }
        }
        finally {
            this.release(supportSQLiteStatement0);
        }
    }

    public final void insert(Object object0) {
        SupportSQLiteStatement supportSQLiteStatement0 = this.acquire();
        try {
            this.bind(supportSQLiteStatement0, object0);
            supportSQLiteStatement0.executeInsert();
        }
        finally {
            this.release(supportSQLiteStatement0);
        }
    }

    public final void insert(Object[] arr_object) {
        Intrinsics.checkNotNullParameter(arr_object, "entities");
        SupportSQLiteStatement supportSQLiteStatement0 = this.acquire();
        try {
            for(int v1 = 0; v1 < arr_object.length; ++v1) {
                this.bind(supportSQLiteStatement0, arr_object[v1]);
                supportSQLiteStatement0.executeInsert();
            }
        }
        finally {
            this.release(supportSQLiteStatement0);
        }
    }

    public final long insertAndReturnId(Object object0) {
        SupportSQLiteStatement supportSQLiteStatement0 = this.acquire();
        try {
            this.bind(supportSQLiteStatement0, object0);
            return supportSQLiteStatement0.executeInsert();
        }
        finally {
            this.release(supportSQLiteStatement0);
        }
    }

    public final long[] insertAndReturnIdsArray(Collection collection0) {
        Intrinsics.checkNotNullParameter(collection0, "entities");
        SupportSQLiteStatement supportSQLiteStatement0 = this.acquire();
        try {
            long[] arr_v = new long[collection0.size()];
            int v1 = 0;
            for(Object object0: collection0) {
                if(v1 < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                this.bind(supportSQLiteStatement0, object0);
                arr_v[v1] = supportSQLiteStatement0.executeInsert();
                ++v1;
            }
            return arr_v;
        }
        finally {
            this.release(supportSQLiteStatement0);
        }
    }

    public final long[] insertAndReturnIdsArray(Object[] arr_object) {
        Intrinsics.checkNotNullParameter(arr_object, "entities");
        SupportSQLiteStatement supportSQLiteStatement0 = this.acquire();
        try {
            long[] arr_v = new long[arr_object.length];
            int v1 = 0;
            for(int v2 = 0; v1 < arr_object.length; ++v2) {
                this.bind(supportSQLiteStatement0, arr_object[v1]);
                arr_v[v2] = supportSQLiteStatement0.executeInsert();
                ++v1;
            }
            return arr_v;
        }
        finally {
            this.release(supportSQLiteStatement0);
        }
    }

    public final Long[] insertAndReturnIdsArrayBox(Collection collection0) {
        Intrinsics.checkNotNullParameter(collection0, "entities");
        SupportSQLiteStatement supportSQLiteStatement0 = this.acquire();
        Iterator iterator0 = collection0.iterator();
        try {
            int v1 = collection0.size();
            Long[] arr_long = new Long[v1];
            for(int v2 = 0; v2 < v1; ++v2) {
                Object object0 = iterator0.next();
                this.bind(supportSQLiteStatement0, object0);
                arr_long[v2] = supportSQLiteStatement0.executeInsert();
            }
            return arr_long;
        }
        finally {
            this.release(supportSQLiteStatement0);
        }
    }

    public final Long[] insertAndReturnIdsArrayBox(Object[] arr_object) {
        Intrinsics.checkNotNullParameter(arr_object, "entities");
        SupportSQLiteStatement supportSQLiteStatement0 = this.acquire();
        Iterator iterator0 = ArrayIteratorKt.iterator(arr_object);
        try {
            Long[] arr_long = new Long[arr_object.length];
            for(int v1 = 0; v1 < arr_object.length; ++v1) {
                Object object0 = iterator0.next();
                this.bind(supportSQLiteStatement0, object0);
                arr_long[v1] = supportSQLiteStatement0.executeInsert();
            }
            return arr_long;
        }
        finally {
            this.release(supportSQLiteStatement0);
        }
    }

    public final List insertAndReturnIdsList(Collection collection0) {
        Intrinsics.checkNotNullParameter(collection0, "entities");
        SupportSQLiteStatement supportSQLiteStatement0 = this.acquire();
        try {
            List list0 = CollectionsKt.createListBuilder();
            for(Object object0: collection0) {
                this.bind(supportSQLiteStatement0, object0);
                list0.add(supportSQLiteStatement0.executeInsert());
            }
            return CollectionsKt.build(list0);
        }
        finally {
            this.release(supportSQLiteStatement0);
        }
    }

    public final List insertAndReturnIdsList(Object[] arr_object) {
        Intrinsics.checkNotNullParameter(arr_object, "entities");
        SupportSQLiteStatement supportSQLiteStatement0 = this.acquire();
        try {
            List list0 = CollectionsKt.createListBuilder();
            for(int v1 = 0; v1 < arr_object.length; ++v1) {
                this.bind(supportSQLiteStatement0, arr_object[v1]);
                list0.add(supportSQLiteStatement0.executeInsert());
            }
            return CollectionsKt.build(list0);
        }
        finally {
            this.release(supportSQLiteStatement0);
        }
    }
}

