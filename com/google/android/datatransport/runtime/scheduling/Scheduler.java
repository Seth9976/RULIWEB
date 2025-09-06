package com.google.android.datatransport.runtime.scheduling;

import com.google.android.datatransport.TransportScheduleCallback;
import com.google.android.datatransport.runtime.EventInternal;
import com.google.android.datatransport.runtime.TransportContext;

public interface Scheduler {
    void schedule(TransportContext arg1, EventInternal arg2, TransportScheduleCallback arg3);
}

