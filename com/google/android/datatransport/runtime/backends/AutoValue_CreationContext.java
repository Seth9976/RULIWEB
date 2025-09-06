package com.google.android.datatransport.runtime.backends;

import android.content.Context;
import com.google.android.datatransport.runtime.time.Clock;

final class AutoValue_CreationContext extends CreationContext {
    private final Context applicationContext;
    private final String backendName;
    private final Clock monotonicClock;
    private final Clock wallClock;

    AutoValue_CreationContext(Context context0, Clock clock0, Clock clock1, String s) {
        if(context0 == null) {
            throw new NullPointerException("Null applicationContext");
        }
        this.applicationContext = context0;
        if(clock0 == null) {
            throw new NullPointerException("Null wallClock");
        }
        this.wallClock = clock0;
        if(clock1 == null) {
            throw new NullPointerException("Null monotonicClock");
        }
        this.monotonicClock = clock1;
        if(s == null) {
            throw new NullPointerException("Null backendName");
        }
        this.backendName = s;
    }

    @Override
    public boolean equals(Object object0) {
        if(object0 == this) {
            return true;
        }
        if(object0 instanceof CreationContext) {
            Context context0 = ((CreationContext)object0).getApplicationContext();
            if(this.applicationContext.equals(context0)) {
                Clock clock0 = ((CreationContext)object0).getWallClock();
                if(this.wallClock.equals(clock0)) {
                    Clock clock1 = ((CreationContext)object0).getMonotonicClock();
                    if(this.monotonicClock.equals(clock1)) {
                        String s = ((CreationContext)object0).getBackendName();
                        return this.backendName.equals(s);
                    }
                }
            }
        }
        return false;
    }

    @Override  // com.google.android.datatransport.runtime.backends.CreationContext
    public Context getApplicationContext() {
        return this.applicationContext;
    }

    @Override  // com.google.android.datatransport.runtime.backends.CreationContext
    public String getBackendName() {
        return this.backendName;
    }

    @Override  // com.google.android.datatransport.runtime.backends.CreationContext
    public Clock getMonotonicClock() {
        return this.monotonicClock;
    }

    @Override  // com.google.android.datatransport.runtime.backends.CreationContext
    public Clock getWallClock() {
        return this.wallClock;
    }

    @Override
    public int hashCode() {
        return (((this.applicationContext.hashCode() ^ 1000003) * 1000003 ^ this.wallClock.hashCode()) * 1000003 ^ this.monotonicClock.hashCode()) * 1000003 ^ this.backendName.hashCode();
    }

    @Override
    public String toString() {
        return "CreationContext{applicationContext=" + this.applicationContext + ", wallClock=" + this.wallClock + ", monotonicClock=" + this.monotonicClock + ", backendName=" + this.backendName + "}";
    }
}

