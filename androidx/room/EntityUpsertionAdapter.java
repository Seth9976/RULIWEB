package androidx.room;

import android.database.sqlite.SQLiteConstraintException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u001C\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0016\n\u0000\n\u0002\u0010\u001E\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0002\b\u0007\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B!\u0012\f\u0010\u0003\u001A\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\f\u0010\u0005\u001A\b\u0012\u0004\u0012\u00028\u00000\u0006¢\u0006\u0002\u0010\u0007J\u0010\u0010\b\u001A\u00020\t2\u0006\u0010\n\u001A\u00020\u000BH\u0002J\u0013\u0010\f\u001A\u00020\t2\u0006\u0010\r\u001A\u00028\u0000¢\u0006\u0002\u0010\u000EJ\u001B\u0010\f\u001A\u00020\t2\u000E\u0010\u000F\u001A\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u0010¢\u0006\u0002\u0010\u0011J\u0014\u0010\f\u001A\u00020\t2\f\u0010\u000F\u001A\b\u0012\u0004\u0012\u00028\u00000\u0012J\u0013\u0010\u0013\u001A\u00020\u00142\u0006\u0010\r\u001A\u00028\u0000¢\u0006\u0002\u0010\u0015J\u001B\u0010\u0016\u001A\u00020\u00172\u000E\u0010\u000F\u001A\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u0010¢\u0006\u0002\u0010\u0018J\u0014\u0010\u0016\u001A\u00020\u00172\f\u0010\u000F\u001A\b\u0012\u0004\u0012\u00028\u00000\u0019J#\u0010\u001A\u001A\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u00102\u000E\u0010\u000F\u001A\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u0010¢\u0006\u0002\u0010\u001BJ!\u0010\u001A\u001A\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u00102\f\u0010\u000F\u001A\b\u0012\u0004\u0012\u00028\u00000\u0019¢\u0006\u0002\u0010\u001CJ!\u0010\u001D\u001A\b\u0012\u0004\u0012\u00020\u00140\u001E2\u000E\u0010\u000F\u001A\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u0010¢\u0006\u0002\u0010\u001FJ\u001A\u0010\u001D\u001A\b\u0012\u0004\u0012\u00020\u00140\u001E2\f\u0010\u000F\u001A\b\u0012\u0004\u0012\u00028\u00000\u0019R\u0014\u0010\u0003\u001A\b\u0012\u0004\u0012\u00028\u00000\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001A\b\u0012\u0004\u0012\u00028\u00000\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Landroidx/room/EntityUpsertionAdapter;", "T", "", "insertionAdapter", "Landroidx/room/EntityInsertionAdapter;", "updateAdapter", "Landroidx/room/EntityDeletionOrUpdateAdapter;", "(Landroidx/room/EntityInsertionAdapter;Landroidx/room/EntityDeletionOrUpdateAdapter;)V", "checkUniquenessException", "", "ex", "Landroid/database/sqlite/SQLiteConstraintException;", "upsert", "entity", "(Ljava/lang/Object;)V", "entities", "", "([Ljava/lang/Object;)V", "", "upsertAndReturnId", "", "(Ljava/lang/Object;)J", "upsertAndReturnIdsArray", "", "([Ljava/lang/Object;)[J", "", "upsertAndReturnIdsArrayBox", "([Ljava/lang/Object;)[Ljava/lang/Long;", "(Ljava/util/Collection;)[Ljava/lang/Long;", "upsertAndReturnIdsList", "", "([Ljava/lang/Object;)Ljava/util/List;", "room-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class EntityUpsertionAdapter {
    private final EntityInsertionAdapter insertionAdapter;
    private final EntityDeletionOrUpdateAdapter updateAdapter;

    public EntityUpsertionAdapter(EntityInsertionAdapter entityInsertionAdapter0, EntityDeletionOrUpdateAdapter entityDeletionOrUpdateAdapter0) {
        Intrinsics.checkNotNullParameter(entityInsertionAdapter0, "insertionAdapter");
        Intrinsics.checkNotNullParameter(entityDeletionOrUpdateAdapter0, "updateAdapter");
        super();
        this.insertionAdapter = entityInsertionAdapter0;
        this.updateAdapter = entityDeletionOrUpdateAdapter0;
    }

    private final void checkUniquenessException(SQLiteConstraintException sQLiteConstraintException0) {
        String s = sQLiteConstraintException0.getMessage();
        if(s == null || !StringsKt.contains(s, "unique", true) && !StringsKt.contains$default(s, "2067", false, 2, null) && !StringsKt.contains$default(s, "1555", false, 2, null)) {
            throw sQLiteConstraintException0;
        }
    }

    public final void upsert(Iterable iterable0) {
        Intrinsics.checkNotNullParameter(iterable0, "entities");
        for(Object object0: iterable0) {
            try {
                this.insertionAdapter.insert(object0);
            }
            catch(SQLiteConstraintException sQLiteConstraintException0) {
                this.checkUniquenessException(sQLiteConstraintException0);
                this.updateAdapter.handle(object0);
            }
        }
    }

    public final void upsert(Object object0) {
        try {
            this.insertionAdapter.insert(object0);
        }
        catch(SQLiteConstraintException sQLiteConstraintException0) {
            this.checkUniquenessException(sQLiteConstraintException0);
            this.updateAdapter.handle(object0);
        }
    }

    public final void upsert(Object[] arr_object) {
        Intrinsics.checkNotNullParameter(arr_object, "entities");
        for(int v = 0; v < arr_object.length; ++v) {
            Object object0 = arr_object[v];
            try {
                this.insertionAdapter.insert(object0);
            }
            catch(SQLiteConstraintException sQLiteConstraintException0) {
                this.checkUniquenessException(sQLiteConstraintException0);
                this.updateAdapter.handle(object0);
            }
        }
    }

    public final long upsertAndReturnId(Object object0) {
        try {
            return this.insertionAdapter.insertAndReturnId(object0);
        }
        catch(SQLiteConstraintException sQLiteConstraintException0) {
            this.checkUniquenessException(sQLiteConstraintException0);
            this.updateAdapter.handle(object0);
            return -1L;
        }
    }

    public final long[] upsertAndReturnIdsArray(Collection collection0) {
        long v2;
        Intrinsics.checkNotNullParameter(collection0, "entities");
        Iterator iterator0 = collection0.iterator();
        int v = collection0.size();
        long[] arr_v = new long[v];
        for(int v1 = 0; v1 < v; ++v1) {
            Object object0 = iterator0.next();
            try {
                v2 = this.insertionAdapter.insertAndReturnId(object0);
            }
            catch(SQLiteConstraintException sQLiteConstraintException0) {
                this.checkUniquenessException(sQLiteConstraintException0);
                this.updateAdapter.handle(object0);
                v2 = -1L;
            }
            arr_v[v1] = v2;
        }
        return arr_v;
    }

    public final long[] upsertAndReturnIdsArray(Object[] arr_object) {
        long v1;
        Intrinsics.checkNotNullParameter(arr_object, "entities");
        long[] arr_v = new long[arr_object.length];
        for(int v = 0; v < arr_object.length; ++v) {
            try {
                v1 = this.insertionAdapter.insertAndReturnId(arr_object[v]);
            }
            catch(SQLiteConstraintException sQLiteConstraintException0) {
                this.checkUniquenessException(sQLiteConstraintException0);
                this.updateAdapter.handle(arr_object[v]);
                v1 = -1L;
            }
            arr_v[v] = v1;
        }
        return arr_v;
    }

    public final Long[] upsertAndReturnIdsArrayBox(Collection collection0) {
        long v2;
        Intrinsics.checkNotNullParameter(collection0, "entities");
        Iterator iterator0 = collection0.iterator();
        int v = collection0.size();
        Long[] arr_long = new Long[v];
        for(int v1 = 0; v1 < v; ++v1) {
            Object object0 = iterator0.next();
            try {
                v2 = this.insertionAdapter.insertAndReturnId(object0);
            }
            catch(SQLiteConstraintException sQLiteConstraintException0) {
                this.checkUniquenessException(sQLiteConstraintException0);
                this.updateAdapter.handle(object0);
                v2 = -1L;
            }
            arr_long[v1] = v2;
        }
        return arr_long;
    }

    public final Long[] upsertAndReturnIdsArrayBox(Object[] arr_object) {
        long v1;
        Intrinsics.checkNotNullParameter(arr_object, "entities");
        Long[] arr_long = new Long[arr_object.length];
        for(int v = 0; v < arr_object.length; ++v) {
            try {
                v1 = this.insertionAdapter.insertAndReturnId(arr_object[v]);
            }
            catch(SQLiteConstraintException sQLiteConstraintException0) {
                this.checkUniquenessException(sQLiteConstraintException0);
                this.updateAdapter.handle(arr_object[v]);
                v1 = -1L;
            }
            arr_long[v] = v1;
        }
        return arr_long;
    }

    public final List upsertAndReturnIdsList(Collection collection0) {
        Intrinsics.checkNotNullParameter(collection0, "entities");
        List list0 = CollectionsKt.createListBuilder();
        for(Object object0: collection0) {
            try {
                list0.add(this.insertionAdapter.insertAndReturnId(object0));
            }
            catch(SQLiteConstraintException sQLiteConstraintException0) {
                this.checkUniquenessException(sQLiteConstraintException0);
                this.updateAdapter.handle(object0);
                list0.add(-1L);
            }
        }
        return CollectionsKt.build(list0);
    }

    public final List upsertAndReturnIdsList(Object[] arr_object) {
        Intrinsics.checkNotNullParameter(arr_object, "entities");
        List list0 = CollectionsKt.createListBuilder();
        for(int v = 0; v < arr_object.length; ++v) {
            Object object0 = arr_object[v];
            try {
                list0.add(this.insertionAdapter.insertAndReturnId(object0));
            }
            catch(SQLiteConstraintException sQLiteConstraintException0) {
                this.checkUniquenessException(sQLiteConstraintException0);
                this.updateAdapter.handle(object0);
                list0.add(-1L);
            }
        }
        return CollectionsKt.build(list0);
    }
}

