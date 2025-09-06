package com.google.android.datatransport.runtime.scheduling.persistence;

import com.google.android.datatransport.runtime.EventInternal;
import com.google.android.datatransport.runtime.TransportContext;

final class AutoValue_PersistedEvent extends PersistedEvent {
    private final EventInternal event;
    private final long id;
    private final TransportContext transportContext;

    AutoValue_PersistedEvent(long v, TransportContext transportContext0, EventInternal eventInternal0) {
        this.id = v;
        if(transportContext0 == null) {
            throw new NullPointerException("Null transportContext");
        }
        this.transportContext = transportContext0;
        if(eventInternal0 == null) {
            throw new NullPointerException("Null event");
        }
        this.event = eventInternal0;
    }

    @Override
    public boolean equals(Object object0) {
        if(object0 == this) {
            return true;
        }
        if(object0 instanceof PersistedEvent) {
            long v = ((PersistedEvent)object0).getId();
            if(this.id == v) {
                TransportContext transportContext0 = ((PersistedEvent)object0).getTransportContext();
                if(this.transportContext.equals(transportContext0)) {
                    EventInternal eventInternal0 = ((PersistedEvent)object0).getEvent();
                    return this.event.equals(eventInternal0);
                }
            }
        }
        return false;
    }

    @Override  // com.google.android.datatransport.runtime.scheduling.persistence.PersistedEvent
    public EventInternal getEvent() {
        return this.event;
    }

    @Override  // com.google.android.datatransport.runtime.scheduling.persistence.PersistedEvent
    public long getId() {
        return this.id;
    }

    @Override  // com.google.android.datatransport.runtime.scheduling.persistence.PersistedEvent
    public TransportContext getTransportContext() {
        return this.transportContext;
    }

    @Override
    public int hashCode() {
        return this.event.hashCode() ^ ((((int)(this.id ^ this.id >>> 0x20)) ^ 1000003) * 1000003 ^ this.transportContext.hashCode()) * 1000003;
    }

    @Override
    public String toString() {
        return "PersistedEvent{id=" + this.id + ", transportContext=" + this.transportContext + ", event=" + this.event + "}";
    }
}

