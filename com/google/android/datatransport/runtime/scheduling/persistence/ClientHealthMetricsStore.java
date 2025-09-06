package com.google.android.datatransport.runtime.scheduling.persistence;

import com.google.android.datatransport.runtime.firebase.transport.ClientMetrics;
import com.google.android.datatransport.runtime.firebase.transport.LogEventDropped.Reason;

public interface ClientHealthMetricsStore {
    ClientMetrics loadClientMetrics();

    void recordLogEventDropped(long arg1, Reason arg2, String arg3);

    void resetClientMetrics();
}

