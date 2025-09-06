package com.google.android.datatransport.runtime.backends;

import android.content.Context;
import com.google.android.datatransport.runtime.time.Clock;

public abstract class CreationContext {
    private static final String DEFAULT_BACKEND_NAME = "cct";

    public static CreationContext create(Context context0, Clock clock0, Clock clock1) {
        return new AutoValue_CreationContext(context0, clock0, clock1, "cct");
    }

    public static CreationContext create(Context context0, Clock clock0, Clock clock1, String s) {
        return new AutoValue_CreationContext(context0, clock0, clock1, s);
    }

    public abstract Context getApplicationContext();

    public abstract String getBackendName();

    public abstract Clock getMonotonicClock();

    public abstract Clock getWallClock();
}

