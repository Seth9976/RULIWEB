package androidx.room;

import androidx.lifecycle.LiveData;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Set;
import java.util.concurrent.Callable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004J?\u0010\n\u001A\b\u0012\u0004\u0012\u0002H\u000B0\u0007\"\u0004\b\u0000\u0010\u000B2\u000E\u0010\f\u001A\n\u0012\u0006\b\u0001\u0012\u00020\u000E0\r2\u0006\u0010\u000F\u001A\u00020\u00102\u000E\u0010\u0011\u001A\n\u0012\u0006\u0012\u0004\u0018\u0001H\u000B0\u0012¢\u0006\u0002\u0010\u0013J\u0012\u0010\u0014\u001A\u00020\u00152\n\u0010\u0016\u001A\u0006\u0012\u0002\b\u00030\u0007J\u0012\u0010\u0017\u001A\u00020\u00152\n\u0010\u0016\u001A\u0006\u0012\u0002\b\u00030\u0007R\u000E\u0010\u0002\u001A\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001E\u0010\u0005\u001A\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070\u0006X\u0080\u0004¢\u0006\b\n\u0000\u001A\u0004\b\b\u0010\t¨\u0006\u0018"}, d2 = {"Landroidx/room/InvalidationLiveDataContainer;", "", "database", "Landroidx/room/RoomDatabase;", "(Landroidx/room/RoomDatabase;)V", "liveDataSet", "", "Landroidx/lifecycle/LiveData;", "getLiveDataSet$room_runtime_release", "()Ljava/util/Set;", "create", "T", "tableNames", "", "", "inTransaction", "", "computeFunction", "Ljava/util/concurrent/Callable;", "([Ljava/lang/String;ZLjava/util/concurrent/Callable;)Landroidx/lifecycle/LiveData;", "onActive", "", "liveData", "onInactive", "room-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class InvalidationLiveDataContainer {
    private final RoomDatabase database;
    private final Set liveDataSet;

    public InvalidationLiveDataContainer(RoomDatabase roomDatabase0) {
        Intrinsics.checkNotNullParameter(roomDatabase0, "database");
        super();
        this.database = roomDatabase0;
        Set set0 = Collections.newSetFromMap(new IdentityHashMap());
        Intrinsics.checkNotNullExpressionValue(set0, "newSetFromMap(IdentityHashMap())");
        this.liveDataSet = set0;
    }

    public final LiveData create(String[] arr_s, boolean z, Callable callable0) {
        Intrinsics.checkNotNullParameter(arr_s, "tableNames");
        Intrinsics.checkNotNullParameter(callable0, "computeFunction");
        return new RoomTrackingLiveData(this.database, this, z, callable0, arr_s);
    }

    public final Set getLiveDataSet$room_runtime_release() {
        return this.liveDataSet;
    }

    public final void onActive(LiveData liveData0) {
        Intrinsics.checkNotNullParameter(liveData0, "liveData");
        this.liveDataSet.add(liveData0);
    }

    public final void onInactive(LiveData liveData0) {
        Intrinsics.checkNotNullParameter(liveData0, "liveData");
        this.liveDataSet.remove(liveData0);
    }
}

