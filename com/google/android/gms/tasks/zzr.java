package com.google.android.gms.tasks;

import java.util.ArrayDeque;
import java.util.Queue;
import jeb.synthetic.FIN;

final class zzr {
    private final Object zza;
    private Queue zzb;
    private boolean zzc;

    zzr() {
        this.zza = new Object();
    }

    public final void zza(zzq zzq0) {
        synchronized(this.zza) {
            if(this.zzb == null) {
                this.zzb = new ArrayDeque();
            }
            this.zzb.add(zzq0);
        }
    }

    public final void zzb(Task task0) {
        zzq zzq0;
        int v;
        synchronized(this.zza) {
            if(this.zzb != null && !this.zzc) {
                this.zzc = true;
                while(true) {
                    Object object1 = this.zza;
                    synchronized(object1) {
                        v = FIN.finallyOpen$NT();
                        zzq0 = (zzq)this.zzb.poll();
                        if(zzq0 == null) {
                            break;
                        }
                        FIN.finallyCodeBegin$NT(v);
                    }
                    FIN.finallyCodeEnd$NT(v);
                    zzq0.zzd(task0);
                }
                this.zzc = false;
                FIN.finallyExec$NT(v);
            }
        }
    }
}

