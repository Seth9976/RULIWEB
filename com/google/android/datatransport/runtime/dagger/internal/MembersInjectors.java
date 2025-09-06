package com.google.android.datatransport.runtime.dagger.internal;

import com.google.android.datatransport.runtime.dagger.MembersInjector;

public final class MembersInjectors {
    static enum NoOpMembersInjector implements MembersInjector {
        INSTANCE;

        @Override  // com.google.android.datatransport.runtime.dagger.MembersInjector
        public void injectMembers(Object object0) {
            Preconditions.checkNotNull(object0, "Cannot inject members into a null reference");
        }
    }

    public static MembersInjector noOp() {
        return NoOpMembersInjector.INSTANCE;
    }
}

