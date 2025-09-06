package com.google.android.gms.common;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.google.android.gms.common.internal.Preconditions;
import com.google.errorprone.annotations.ResultIgnorabilityUnspecified;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class BlockingServiceConnection implements ServiceConnection {
    boolean zza;
    private final BlockingQueue zzb;

    public BlockingServiceConnection() {
        this.zza = false;
        this.zzb = new LinkedBlockingQueue();
    }

    @ResultIgnorabilityUnspecified
    public IBinder getService() throws InterruptedException {
        Preconditions.checkNotMainThread("BlockingServiceConnection.getService() called on main thread");
        if(this.zza) {
            throw new IllegalStateException("Cannot call get on this connection more than once");
        }
        this.zza = true;
        return (IBinder)this.zzb.take();
    }

    @ResultIgnorabilityUnspecified
    public IBinder getServiceWithTimeout(long v, TimeUnit timeUnit0) throws InterruptedException, TimeoutException {
        Preconditions.checkNotMainThread("BlockingServiceConnection.getServiceWithTimeout() called on main thread");
        if(this.zza) {
            throw new IllegalStateException("Cannot call get on this connection more than once");
        }
        this.zza = true;
        IBinder iBinder0 = (IBinder)this.zzb.poll(v, timeUnit0);
        if(iBinder0 == null) {
            throw new TimeoutException("Timed out waiting for the service connection");
        }
        return iBinder0;
    }

    @Override  // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName0, IBinder iBinder0) {
        this.zzb.add(iBinder0);
    }

    @Override  // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName0) {
    }
}

