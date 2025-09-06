package com.google.firebase.components;

import com.google.firebase.events.Event;
import java.util.Map.Entry;

public final class EventBus..ExternalSyntheticLambda0 implements Runnable {
    public final Map.Entry f$0;
    public final Event f$1;

    public EventBus..ExternalSyntheticLambda0(Map.Entry map$Entry0, Event event0) {
        this.f$0 = map$Entry0;
        this.f$1 = event0;
    }

    @Override
    public final void run() {
        EventBus.lambda$publish$0(this.f$0, this.f$1);
    }
}

