package com.google.android.gms.dynamite;

import android.os.Process;

final class zza extends Thread {
    zza(ThreadGroup threadGroup0, String s) {
        super(threadGroup0, "GmsDynamite");
    }

    @Override
    public final void run() {
        Process.setThreadPriority(19);
        synchronized(this) {
            try {
                while(true) {
                    this.wait();
                }
            }
            catch(InterruptedException unused_ex) {
            }
        }
    }
}

