package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import com.google.android.datatransport.runtime.TransportContext;

public interface WorkScheduler {
    void schedule(TransportContext arg1, int arg2);

    void schedule(TransportContext arg1, int arg2, boolean arg3);
}

