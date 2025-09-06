package androidx.room;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import java.util.Arrays;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u000B\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0005\u0012\u0006\u0010\u0006\u001A\u00020\u0007\u0012\u0006\u0010\b\u001A\u00020\t\u0012\u0006\u0010\n\u001A\u00020\u000B¢\u0006\u0002\u0010\fJ\u0006\u00109\u001A\u00020:R\u0016\u0010\r\u001A\n \u000E*\u0004\u0018\u00010\u00030\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u000F\u001A\u00020\u0010¢\u0006\b\n\u0000\u001A\u0004\b\u0011\u0010\u0012R\u001A\u0010\u0013\u001A\u00020\u0014X\u0086\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u0011\u0010\n\u001A\u00020\u000B¢\u0006\b\n\u0000\u001A\u0004\b\u0019\u0010\u001AR\u0011\u0010\b\u001A\u00020\t¢\u0006\b\n\u0000\u001A\u0004\b\u001B\u0010\u001CR\u0011\u0010\u0004\u001A\u00020\u0005¢\u0006\b\n\u0000\u001A\u0004\b\u001D\u0010\u001ER\u001A\u0010\u001F\u001A\u00020 X\u0086.¢\u0006\u000E\n\u0000\u001A\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u0011\u0010%\u001A\u00020&¢\u0006\b\n\u0000\u001A\u0004\b\'\u0010(R\u001C\u0010)\u001A\u0004\u0018\u00010*X\u0086\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b+\u0010,\"\u0004\b-\u0010.R\u0011\u0010/\u001A\u000200¢\u0006\b\n\u0000\u001A\u0004\b1\u00102R\u0011\u00103\u001A\u00020&¢\u0006\b\n\u0000\u001A\u0004\b4\u0010(R\u0011\u00105\u001A\u000206¢\u0006\b\n\u0000\u001A\u0004\b7\u00108¨\u0006;"}, d2 = {"Landroidx/room/MultiInstanceInvalidationClient;", "", "context", "Landroid/content/Context;", "name", "", "serviceIntent", "Landroid/content/Intent;", "invalidationTracker", "Landroidx/room/InvalidationTracker;", "executor", "Ljava/util/concurrent/Executor;", "(Landroid/content/Context;Ljava/lang/String;Landroid/content/Intent;Landroidx/room/InvalidationTracker;Ljava/util/concurrent/Executor;)V", "appContext", "kotlin.jvm.PlatformType", "callback", "Landroidx/room/IMultiInstanceInvalidationCallback;", "getCallback", "()Landroidx/room/IMultiInstanceInvalidationCallback;", "clientId", "", "getClientId", "()I", "setClientId", "(I)V", "getExecutor", "()Ljava/util/concurrent/Executor;", "getInvalidationTracker", "()Landroidx/room/InvalidationTracker;", "getName", "()Ljava/lang/String;", "observer", "Landroidx/room/InvalidationTracker$Observer;", "getObserver", "()Landroidx/room/InvalidationTracker$Observer;", "setObserver", "(Landroidx/room/InvalidationTracker$Observer;)V", "removeObserverRunnable", "Ljava/lang/Runnable;", "getRemoveObserverRunnable", "()Ljava/lang/Runnable;", "service", "Landroidx/room/IMultiInstanceInvalidationService;", "getService", "()Landroidx/room/IMultiInstanceInvalidationService;", "setService", "(Landroidx/room/IMultiInstanceInvalidationService;)V", "serviceConnection", "Landroid/content/ServiceConnection;", "getServiceConnection", "()Landroid/content/ServiceConnection;", "setUpRunnable", "getSetUpRunnable", "stopped", "Ljava/util/concurrent/atomic/AtomicBoolean;", "getStopped", "()Ljava/util/concurrent/atomic/AtomicBoolean;", "stop", "", "room-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class MultiInstanceInvalidationClient {
    private final Context appContext;
    private final IMultiInstanceInvalidationCallback callback;
    private int clientId;
    private final Executor executor;
    private final InvalidationTracker invalidationTracker;
    private final String name;
    public Observer observer;
    private final Runnable removeObserverRunnable;
    private IMultiInstanceInvalidationService service;
    private final ServiceConnection serviceConnection;
    private final Runnable setUpRunnable;
    private final AtomicBoolean stopped;

    // 检测为 Lambda 实现
    public static void $r8$lambda$2favYSSWyM0gRx77r31pzjS1Occ(MultiInstanceInvalidationClient multiInstanceInvalidationClient0) [...]

    // 检测为 Lambda 实现
    public static void $r8$lambda$fYnJ2FTy9KIeZ6OKMBfkix53V3o(MultiInstanceInvalidationClient multiInstanceInvalidationClient0) [...]

    public MultiInstanceInvalidationClient(Context context0, String s, Intent intent0, InvalidationTracker invalidationTracker0, Executor executor0) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(s, "name");
        Intrinsics.checkNotNullParameter(intent0, "serviceIntent");
        Intrinsics.checkNotNullParameter(invalidationTracker0, "invalidationTracker");
        Intrinsics.checkNotNullParameter(executor0, "executor");
        super();
        this.name = s;
        this.invalidationTracker = invalidationTracker0;
        this.executor = executor0;
        Context context1 = context0.getApplicationContext();
        this.appContext = context1;
        this.callback = new Stub() {
            // 检测为 Lambda 实现
            public static void $r8$lambda$J77IKsfKUHkV39IbvPEBkb2BFYg(MultiInstanceInvalidationClient multiInstanceInvalidationClient0, String[] arr_s) [...]

            @Override  // androidx.room.IMultiInstanceInvalidationCallback
            public void onInvalidation(String[] arr_s) {
                Intrinsics.checkNotNullParameter(arr_s, "tables");
                MultiInstanceInvalidationClient.this.getExecutor().execute(() -> androidx.room.MultiInstanceInvalidationClient.callback.1.onInvalidation$lambda$0(MultiInstanceInvalidationClient.this, arr_s));
            }

            private static final void onInvalidation$lambda$0(MultiInstanceInvalidationClient multiInstanceInvalidationClient0, String[] arr_s) {
                Intrinsics.checkNotNullParameter(multiInstanceInvalidationClient0, "this$0");
                Intrinsics.checkNotNullParameter(arr_s, "$tables");
                multiInstanceInvalidationClient0.getInvalidationTracker().notifyObserversByTableNames(((String[])Arrays.copyOf(arr_s, arr_s.length)));
            }
        };
        this.stopped = new AtomicBoolean(false);
        ServiceConnection serviceConnection0 = new ServiceConnection() {
            @Override  // android.content.ServiceConnection
            public void onServiceConnected(ComponentName componentName0, IBinder iBinder0) {
                Intrinsics.checkNotNullParameter(componentName0, "name");
                Intrinsics.checkNotNullParameter(iBinder0, "service");
                IMultiInstanceInvalidationService iMultiInstanceInvalidationService0 = androidx.room.IMultiInstanceInvalidationService.Stub.asInterface(iBinder0);
                MultiInstanceInvalidationClient.this.setService(iMultiInstanceInvalidationService0);
                MultiInstanceInvalidationClient.this.getExecutor().execute(MultiInstanceInvalidationClient.this.getSetUpRunnable());
            }

            @Override  // android.content.ServiceConnection
            public void onServiceDisconnected(ComponentName componentName0) {
                Intrinsics.checkNotNullParameter(componentName0, "name");
                Runnable runnable0 = MultiInstanceInvalidationClient.this.getRemoveObserverRunnable();
                MultiInstanceInvalidationClient.this.getExecutor().execute(runnable0);
                MultiInstanceInvalidationClient.this.setService(null);
            }
        };
        this.serviceConnection = serviceConnection0;
        this.setUpRunnable = () -> MultiInstanceInvalidationClient.setUpRunnable$lambda$1(this);
        this.removeObserverRunnable = () -> MultiInstanceInvalidationClient.removeObserverRunnable$lambda$2(this);
        this.setObserver(new Observer(/*ERROR_MISSING_ARG_1/*) {
            @Override  // androidx.room.InvalidationTracker$Observer
            public boolean isRemote$room_runtime_release() {
                return true;
            }

            @Override  // androidx.room.InvalidationTracker$Observer
            public void onInvalidated(Set set0) {
                Intrinsics.checkNotNullParameter(set0, "tables");
                if(!((String[])invalidationTracker0.getTableIdLookup$room_runtime_release().keySet().toArray(new String[0])).getStopped().get()) {
                    try {
                        IMultiInstanceInvalidationService iMultiInstanceInvalidationService0 = ((String[])invalidationTracker0.getTableIdLookup$room_runtime_release().keySet().toArray(new String[0])).getService();
                        if(iMultiInstanceInvalidationService0 != null) {
                            iMultiInstanceInvalidationService0.broadcastInvalidation(((String[])invalidationTracker0.getTableIdLookup$room_runtime_release().keySet().toArray(new String[0])).getClientId(), ((String[])set0.toArray(new String[0])));
                        }
                    }
                    catch(RemoteException remoteException0) {
                        Log.w("ROOM", "Cannot broadcast invalidation", remoteException0);
                    }
                }
            }
        });
        context1.bindService(intent0, serviceConnection0, 1);
    }

    public final IMultiInstanceInvalidationCallback getCallback() {
        return this.callback;
    }

    public final int getClientId() {
        return this.clientId;
    }

    public final Executor getExecutor() {
        return this.executor;
    }

    public final InvalidationTracker getInvalidationTracker() {
        return this.invalidationTracker;
    }

    public final String getName() {
        return this.name;
    }

    public final Observer getObserver() {
        Observer invalidationTracker$Observer0 = this.observer;
        if(invalidationTracker$Observer0 != null) {
            return invalidationTracker$Observer0;
        }
        Intrinsics.throwUninitializedPropertyAccessException("observer");
        return null;
    }

    public final Runnable getRemoveObserverRunnable() {
        return this.removeObserverRunnable;
    }

    public final IMultiInstanceInvalidationService getService() {
        return this.service;
    }

    public final ServiceConnection getServiceConnection() {
        return this.serviceConnection;
    }

    public final Runnable getSetUpRunnable() {
        return this.setUpRunnable;
    }

    public final AtomicBoolean getStopped() {
        return this.stopped;
    }

    private static final void removeObserverRunnable$lambda$2(MultiInstanceInvalidationClient multiInstanceInvalidationClient0) {
        Intrinsics.checkNotNullParameter(multiInstanceInvalidationClient0, "this$0");
        Observer invalidationTracker$Observer0 = multiInstanceInvalidationClient0.getObserver();
        multiInstanceInvalidationClient0.invalidationTracker.removeObserver(invalidationTracker$Observer0);
    }

    public final void setClientId(int v) {
        this.clientId = v;
    }

    public final void setObserver(Observer invalidationTracker$Observer0) {
        Intrinsics.checkNotNullParameter(invalidationTracker$Observer0, "<set-?>");
        this.observer = invalidationTracker$Observer0;
    }

    public final void setService(IMultiInstanceInvalidationService iMultiInstanceInvalidationService0) {
        this.service = iMultiInstanceInvalidationService0;
    }

    private static final void setUpRunnable$lambda$1(MultiInstanceInvalidationClient multiInstanceInvalidationClient0) {
        Intrinsics.checkNotNullParameter(multiInstanceInvalidationClient0, "this$0");
        IMultiInstanceInvalidationService iMultiInstanceInvalidationService0 = multiInstanceInvalidationClient0.service;
        if(iMultiInstanceInvalidationService0 != null) {
            try {
                multiInstanceInvalidationClient0.clientId = iMultiInstanceInvalidationService0.registerCallback(multiInstanceInvalidationClient0.callback, multiInstanceInvalidationClient0.name);
                Observer invalidationTracker$Observer0 = multiInstanceInvalidationClient0.getObserver();
                multiInstanceInvalidationClient0.invalidationTracker.addObserver(invalidationTracker$Observer0);
            }
            catch(RemoteException remoteException0) {
                Log.w("ROOM", "Cannot register multi-instance invalidation callback", remoteException0);
            }
        }
    }

    public final void stop() {
        if(this.stopped.compareAndSet(false, true)) {
            Observer invalidationTracker$Observer0 = this.getObserver();
            this.invalidationTracker.removeObserver(invalidationTracker$Observer0);
            IMultiInstanceInvalidationService iMultiInstanceInvalidationService0 = this.service;
            if(iMultiInstanceInvalidationService0 != null) {
                try {
                    iMultiInstanceInvalidationService0.unregisterCallback(this.callback, this.clientId);
                }
                catch(RemoteException remoteException0) {
                    Log.w("ROOM", "Cannot unregister multi-instance invalidation callback", remoteException0);
                }
            }
            this.appContext.unbindService(this.serviceConnection);
        }
    }
}

