package androidx.room;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.util.Log;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0010\b\n\u0002\u0010\u000E\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0015\u001A\u00020\u00162\u0006\u0010\u0017\u001A\u00020\u0018H\u0016R\u000E\u0010\u0003\u001A\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001A\u0010\u0005\u001A\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0080\u0004¢\u0006\b\n\u0000\u001A\u0004\b\b\u0010\tR \u0010\n\u001A\u000E\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000BX\u0080\u0004¢\u0006\b\n\u0000\u001A\u0004\b\u000E\u0010\u000FR\u001A\u0010\u0010\u001A\u00020\fX\u0080\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006\u0019"}, d2 = {"Landroidx/room/MultiInstanceInvalidationService;", "Landroid/app/Service;", "()V", "binder", "Landroidx/room/IMultiInstanceInvalidationService$Stub;", "callbackList", "Landroid/os/RemoteCallbackList;", "Landroidx/room/IMultiInstanceInvalidationCallback;", "getCallbackList$room_runtime_release", "()Landroid/os/RemoteCallbackList;", "clientNames", "", "", "", "getClientNames$room_runtime_release", "()Ljava/util/Map;", "maxClientId", "getMaxClientId$room_runtime_release", "()I", "setMaxClientId$room_runtime_release", "(I)V", "onBind", "Landroid/os/IBinder;", "intent", "Landroid/content/Intent;", "room-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class MultiInstanceInvalidationService extends Service {
    private final Stub binder;
    private final RemoteCallbackList callbackList;
    private final Map clientNames;
    private int maxClientId;

    public MultiInstanceInvalidationService() {
        this.clientNames = new LinkedHashMap();
        this.callbackList = new RemoteCallbackList() {
            @Override  // android.os.RemoteCallbackList
            public void onCallbackDied(IInterface iInterface0, Object object0) {
                this.onCallbackDied(((IMultiInstanceInvalidationCallback)iInterface0), object0);
            }

            public void onCallbackDied(IMultiInstanceInvalidationCallback iMultiInstanceInvalidationCallback0, Object object0) {
                Intrinsics.checkNotNullParameter(iMultiInstanceInvalidationCallback0, "callback");
                Intrinsics.checkNotNullParameter(object0, "cookie");
                MultiInstanceInvalidationService.this.getClientNames$room_runtime_release().remove(((Integer)object0));
            }
        };
        this.binder = new Stub() {
            @Override  // androidx.room.IMultiInstanceInvalidationService
            public void broadcastInvalidation(int v, String[] arr_s) {
                Intrinsics.checkNotNullParameter(arr_s, "tables");
                RemoteCallbackList remoteCallbackList0 = MultiInstanceInvalidationService.this.getCallbackList$room_runtime_release();
                MultiInstanceInvalidationService multiInstanceInvalidationService0 = MultiInstanceInvalidationService.this;
                synchronized(remoteCallbackList0) {
                    String s = (String)multiInstanceInvalidationService0.getClientNames$room_runtime_release().get(v);
                    if(s == null) {
                        Log.w("ROOM", "Remote invalidation client ID not registered");
                        return;
                    }
                    int v2 = multiInstanceInvalidationService0.getCallbackList$room_runtime_release().beginBroadcast();
                    try {
                        for(int v3 = 0; v3 < v2; ++v3) {
                            Object object0 = multiInstanceInvalidationService0.getCallbackList$room_runtime_release().getBroadcastCookie(v3);
                            Intrinsics.checkNotNull(object0, "null cannot be cast to non-null type kotlin.Int");
                            if(v != ((int)(((Integer)object0))) && Intrinsics.areEqual(s, ((String)multiInstanceInvalidationService0.getClientNames$room_runtime_release().get(((Integer)object0))))) {
                                try {
                                    ((IMultiInstanceInvalidationCallback)multiInstanceInvalidationService0.getCallbackList$room_runtime_release().getBroadcastItem(v3)).onInvalidation(arr_s);
                                }
                                catch(RemoteException remoteException0) {
                                    Log.w("ROOM", "Error invoking a remote callback", remoteException0);
                                }
                            }
                        }
                    }
                    finally {
                        multiInstanceInvalidationService0.getCallbackList$room_runtime_release().finishBroadcast();
                    }
                }
            }

            @Override  // androidx.room.IMultiInstanceInvalidationService
            public int registerCallback(IMultiInstanceInvalidationCallback iMultiInstanceInvalidationCallback0, String s) {
                Intrinsics.checkNotNullParameter(iMultiInstanceInvalidationCallback0, "callback");
                int v = 0;
                if(s == null) {
                    return 0;
                }
                RemoteCallbackList remoteCallbackList0 = MultiInstanceInvalidationService.this.getCallbackList$room_runtime_release();
                MultiInstanceInvalidationService multiInstanceInvalidationService0 = MultiInstanceInvalidationService.this;
                synchronized(remoteCallbackList0) {
                    multiInstanceInvalidationService0.setMaxClientId$room_runtime_release(multiInstanceInvalidationService0.getMaxClientId$room_runtime_release() + 1);
                    int v2 = multiInstanceInvalidationService0.getMaxClientId$room_runtime_release();
                    if(multiInstanceInvalidationService0.getCallbackList$room_runtime_release().register(iMultiInstanceInvalidationCallback0, v2)) {
                        multiInstanceInvalidationService0.getClientNames$room_runtime_release().put(v2, s);
                        v = v2;
                    }
                    else {
                        multiInstanceInvalidationService0.setMaxClientId$room_runtime_release(multiInstanceInvalidationService0.getMaxClientId$room_runtime_release() - 1);
                    }
                    return v;
                }
            }

            @Override  // androidx.room.IMultiInstanceInvalidationService
            public void unregisterCallback(IMultiInstanceInvalidationCallback iMultiInstanceInvalidationCallback0, int v) {
                Intrinsics.checkNotNullParameter(iMultiInstanceInvalidationCallback0, "callback");
                synchronized(MultiInstanceInvalidationService.this.getCallbackList$room_runtime_release()) {
                    MultiInstanceInvalidationService.this.getCallbackList$room_runtime_release().unregister(iMultiInstanceInvalidationCallback0);
                    String s = (String)MultiInstanceInvalidationService.this.getClientNames$room_runtime_release().remove(v);
                }
            }
        };
    }

    public final RemoteCallbackList getCallbackList$room_runtime_release() {
        return this.callbackList;
    }

    public final Map getClientNames$room_runtime_release() {
        return this.clientNames;
    }

    public final int getMaxClientId$room_runtime_release() {
        return this.maxClientId;
    }

    @Override  // android.app.Service
    public IBinder onBind(Intent intent0) {
        Intrinsics.checkNotNullParameter(intent0, "intent");
        return this.binder;
    }

    public final void setMaxClientId$room_runtime_release(int v) {
        this.maxClientId = v;
    }
}

