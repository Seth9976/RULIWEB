package com.google.firebase.analytics.connector;

import com.google.firebase.events.Event;
import com.google.firebase.events.EventHandler;

public final class zzb implements EventHandler {
    public static final zzb zza;

    static {
        zzb.zza = new zzb();
    }

    @Override  // com.google.firebase.events.EventHandler
    public final void handle(Event event0) {
        AnalyticsConnectorImpl.zza(event0);
    }
}

