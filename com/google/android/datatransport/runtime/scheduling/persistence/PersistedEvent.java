package com.google.android.datatransport.runtime.scheduling.persistence;

import com.google.android.datatransport.runtime.EventInternal;
import com.google.android.datatransport.runtime.TransportContext;

public abstract class PersistedEvent {
    public static PersistedEvent create(long v, TransportContext transportContext0, EventInternal eventInternal0) {
        return new AutoValue_PersistedEvent(v, transportContext0, eventInternal0);
    }

    public abstract EventInternal getEvent();

    public abstract long getId();

    public abstract TransportContext getTransportContext();
}

