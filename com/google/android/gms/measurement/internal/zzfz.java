package com.google.android.gms.measurement.internal;

import android.os.Process;
import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.BlockingQueue;
import jeb.synthetic.FIN;

final class zzfz extends Thread {
    final zzga zza;
    private final Object zzb;
    private final BlockingQueue zzc;
    private boolean zzd;

    public zzfz(zzga zzga0, String s, BlockingQueue blockingQueue0) {
        this.zza = zzga0;
        super();
        this.zzd = false;
        Preconditions.checkNotNull(s);
        Preconditions.checkNotNull(blockingQueue0);
        this.zzb = new Object();
        this.zzc = blockingQueue0;
        this.setName(s);
    }

    @Override
    public final void run() {
        int v3;
        Object object1;
        for(boolean z = false; !z; z = true) {
            try {
                zzga.zzj(this.zza).acquire();
            }
            catch(InterruptedException interruptedException0) {
                this.zzc(interruptedException0);
            }
        }
        try {
            int v1 = Process.getThreadPriority(Process.myTid());
            while(true) {
                zzfy zzfy0 = (zzfy)this.zzc.poll();
                if(zzfy0 == null) {
                    synchronized(this.zzb) {
                        if(this.zzc.peek() == null) {
                            try {
                                this.zzb.wait(30000L);
                            }
                            catch(InterruptedException interruptedException1) {
                                this.zzc(interruptedException1);
                            }
                        }
                    }
                    object1 = zzga.zzc(this.zza);
                    __monitor_enter(object1);
                    v3 = FIN.finallyOpen$NT();
                    if(this.zzc.peek() == null) {
                        break;
                    }
                    FIN.finallyExec$NT(v3);
                }
                else {
                    Process.setThreadPriority((zzfy0.zza ? v1 : 10));
                    zzfy0.run();
                }
            }
            this.zzb();
            FIN.finallyCodeBegin$NT(v3);
            __monitor_exit(object1);
            FIN.finallyCodeEnd$NT(v3);
        }
        finally {
            this.zzb();
        }
    }

    public final void zza() {
        synchronized(this.zzb) {
            this.zzb.notifyAll();
        }
    }

    private final void zzb() {
        synchronized(zzga.zzc(this.zza)) {
            if(!this.zzd) {
                zzga.zzj(this.zza).release();
                zzga.zzc(this.zza).notifyAll();
                zzga zzga0 = this.zza;
                if(this == zzga.zzb(zzga0)) {
                    zzga.zzm(zzga0, null);
                }
                else if(this == zzga.zza(zzga0)) {
                    zzga.zzl(zzga0, null);
                }
                else {
                    zzga0.zzt.zzaA().zzd().zza("Current scheduler thread is neither worker nor network");
                }
                this.zzd = true;
            }
        }
    }

    private final void zzc(InterruptedException interruptedException0) {
        this.zza.zzt.zzaA().zzk().zzb(this.getName() + " was interrupted", interruptedException0);
    }
}

