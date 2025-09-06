package androidx.room;

import androidx.arch.core.executor.ArchTaskExecutor;
import androidx.lifecycle.LiveData;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000E\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0001\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B=\u0012\u0006\u0010\u0003\u001A\u00020\u0004\u0012\u0006\u0010\u0005\u001A\u00020\u0006\u0012\u0006\u0010\u0007\u001A\u00020\b\u0012\u000E\u0010\t\u001A\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000\n\u0012\u000E\u0010\u000B\u001A\n\u0012\u0006\b\u0001\u0012\u00020\r0\f¢\u0006\u0002\u0010\u000EJ\b\u0010+\u001A\u00020,H\u0014J\b\u0010-\u001A\u00020,H\u0014R\u0019\u0010\t\u001A\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000\n¢\u0006\b\n\u0000\u001A\u0004\b\u000F\u0010\u0010R\u0011\u0010\u0011\u001A\u00020\u0012¢\u0006\b\n\u0000\u001A\u0004\b\u0013\u0010\u0014R\u000E\u0010\u0005\u001A\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0003\u001A\u00020\u0004¢\u0006\b\n\u0000\u001A\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0007\u001A\u00020\b¢\u0006\b\n\u0000\u001A\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0019\u001A\u00020\u0012¢\u0006\b\n\u0000\u001A\u0004\b\u001A\u0010\u0014R\u0011\u0010\u001B\u001A\u00020\u001C¢\u0006\b\n\u0000\u001A\u0004\b\u001D\u0010\u001ER\u0011\u0010\u001F\u001A\u00020 ¢\u0006\b\n\u0000\u001A\u0004\b!\u0010\"R\u0011\u0010#\u001A\u00020$8F¢\u0006\u0006\u001A\u0004\b%\u0010&R\u0011\u0010\'\u001A\u00020\u001C¢\u0006\b\n\u0000\u001A\u0004\b(\u0010\u001ER\u0011\u0010)\u001A\u00020\u0012¢\u0006\b\n\u0000\u001A\u0004\b*\u0010\u0014¨\u0006."}, d2 = {"Landroidx/room/RoomTrackingLiveData;", "T", "Landroidx/lifecycle/LiveData;", "database", "Landroidx/room/RoomDatabase;", "container", "Landroidx/room/InvalidationLiveDataContainer;", "inTransaction", "", "computeFunction", "Ljava/util/concurrent/Callable;", "tableNames", "", "", "(Landroidx/room/RoomDatabase;Landroidx/room/InvalidationLiveDataContainer;ZLjava/util/concurrent/Callable;[Ljava/lang/String;)V", "getComputeFunction", "()Ljava/util/concurrent/Callable;", "computing", "Ljava/util/concurrent/atomic/AtomicBoolean;", "getComputing", "()Ljava/util/concurrent/atomic/AtomicBoolean;", "getDatabase", "()Landroidx/room/RoomDatabase;", "getInTransaction", "()Z", "invalid", "getInvalid", "invalidationRunnable", "Ljava/lang/Runnable;", "getInvalidationRunnable", "()Ljava/lang/Runnable;", "observer", "Landroidx/room/InvalidationTracker$Observer;", "getObserver", "()Landroidx/room/InvalidationTracker$Observer;", "queryExecutor", "Ljava/util/concurrent/Executor;", "getQueryExecutor", "()Ljava/util/concurrent/Executor;", "refreshRunnable", "getRefreshRunnable", "registeredObserver", "getRegisteredObserver", "onActive", "", "onInactive", "room-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class RoomTrackingLiveData extends LiveData {
    private final Callable computeFunction;
    private final AtomicBoolean computing;
    private final InvalidationLiveDataContainer container;
    private final RoomDatabase database;
    private final boolean inTransaction;
    private final AtomicBoolean invalid;
    private final Runnable invalidationRunnable;
    private final Observer observer;
    private final Runnable refreshRunnable;
    private final AtomicBoolean registeredObserver;

    // 检测为 Lambda 实现
    public static void $r8$lambda$9vDfc5rdg-jEg_oDEiirCIAjDcM(RoomTrackingLiveData roomTrackingLiveData0) [...]

    // 检测为 Lambda 实现
    public static void $r8$lambda$LoxQhNUdjL-YTm7h4N6PsLKEypI(RoomTrackingLiveData roomTrackingLiveData0) [...]

    public RoomTrackingLiveData(RoomDatabase roomDatabase0, InvalidationLiveDataContainer invalidationLiveDataContainer0, boolean z, Callable callable0, String[] arr_s) {
        Intrinsics.checkNotNullParameter(roomDatabase0, "database");
        Intrinsics.checkNotNullParameter(invalidationLiveDataContainer0, "container");
        Intrinsics.checkNotNullParameter(callable0, "computeFunction");
        Intrinsics.checkNotNullParameter(arr_s, "tableNames");
        super();
        this.database = roomDatabase0;
        this.container = invalidationLiveDataContainer0;
        this.inTransaction = z;
        this.computeFunction = callable0;
        this.observer = new Observer(this) {
            @Override  // androidx.room.InvalidationTracker$Observer
            public void onInvalidated(Set set0) {
                Intrinsics.checkNotNullParameter(set0, "tables");
                ArchTaskExecutor.getInstance().executeOnMainThread(RoomTrackingLiveData.this.getInvalidationRunnable());
            }
        };
        this.invalid = new AtomicBoolean(true);
        this.computing = new AtomicBoolean(false);
        this.registeredObserver = new AtomicBoolean(false);
        this.refreshRunnable = () -> RoomTrackingLiveData.refreshRunnable$lambda$0(this);
        this.invalidationRunnable = () -> RoomTrackingLiveData.invalidationRunnable$lambda$1(this);
    }

    public final Callable getComputeFunction() {
        return this.computeFunction;
    }

    public final AtomicBoolean getComputing() {
        return this.computing;
    }

    public final RoomDatabase getDatabase() {
        return this.database;
    }

    public final boolean getInTransaction() {
        return this.inTransaction;
    }

    public final AtomicBoolean getInvalid() {
        return this.invalid;
    }

    public final Runnable getInvalidationRunnable() {
        return this.invalidationRunnable;
    }

    public final Observer getObserver() {
        return this.observer;
    }

    // 去混淆评级： 低(20)
    public final Executor getQueryExecutor() {
        return this.inTransaction ? this.database.getTransactionExecutor() : this.database.getQueryExecutor();
    }

    public final Runnable getRefreshRunnable() {
        return this.refreshRunnable;
    }

    public final AtomicBoolean getRegisteredObserver() {
        return this.registeredObserver;
    }

    private static final void invalidationRunnable$lambda$1(RoomTrackingLiveData roomTrackingLiveData0) {
        Intrinsics.checkNotNullParameter(roomTrackingLiveData0, "this$0");
        boolean z = roomTrackingLiveData0.hasActiveObservers();
        if(roomTrackingLiveData0.invalid.compareAndSet(false, true) && z) {
            roomTrackingLiveData0.getQueryExecutor().execute(roomTrackingLiveData0.refreshRunnable);
        }
    }

    @Override  // androidx.lifecycle.LiveData
    protected void onActive() {
        super.onActive();
        Intrinsics.checkNotNull(this, "null cannot be cast to non-null type androidx.lifecycle.LiveData<kotlin.Any>");
        this.container.onActive(this);
        this.getQueryExecutor().execute(this.refreshRunnable);
    }

    @Override  // androidx.lifecycle.LiveData
    protected void onInactive() {
        super.onInactive();
        Intrinsics.checkNotNull(this, "null cannot be cast to non-null type androidx.lifecycle.LiveData<kotlin.Any>");
        this.container.onInactive(this);
    }

    private static final void refreshRunnable$lambda$0(RoomTrackingLiveData roomTrackingLiveData0) {
        boolean z;
        Intrinsics.checkNotNullParameter(roomTrackingLiveData0, "this$0");
        if(roomTrackingLiveData0.registeredObserver.compareAndSet(false, true)) {
            roomTrackingLiveData0.database.getInvalidationTracker().addWeakObserver(roomTrackingLiveData0.observer);
        }
        do {
            if(roomTrackingLiveData0.computing.compareAndSet(false, true)) {
                Object object0 = null;
                z = false;
                try {
                    while(roomTrackingLiveData0.invalid.compareAndSet(true, false)) {
                        try {
                            object0 = roomTrackingLiveData0.computeFunction.call();
                            z = true;
                        }
                        catch(Exception exception0) {
                            throw new RuntimeException("Exception while computing database live data.", exception0);
                        }
                    }
                    if(z) {
                        roomTrackingLiveData0.postValue(object0);
                    }
                }
                finally {
                    roomTrackingLiveData0.computing.set(false);
                }
            }
            else {
                z = false;
            }
        }
        while(z && roomTrackingLiveData0.invalid.get());
    }
}

