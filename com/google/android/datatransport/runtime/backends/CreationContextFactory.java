package com.google.android.datatransport.runtime.backends;

import android.content.Context;
import com.google.android.datatransport.runtime.time.Clock;
import javax.inject.Inject;

class CreationContextFactory {
    private final Context applicationContext;
    private final Clock monotonicClock;
    private final Clock wallClock;

    @Inject
    CreationContextFactory(Context context0, Clock clock0, Clock clock1) {
        this.applicationContext = context0;
        this.wallClock = clock0;
        this.monotonicClock = clock1;
    }

    CreationContext create(String s) {
        return CreationContext.create(this.applicationContext, this.wallClock, this.monotonicClock, s);
    }
}

