package kotlin.reflect.jvm.internal.impl.incremental.components;

import kotlin.jvm.internal.Intrinsics;

public interface LookupTracker {
    public static final class DO_NOTHING implements LookupTracker {
        public static final DO_NOTHING INSTANCE;

        static {
            DO_NOTHING.INSTANCE = new DO_NOTHING();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.incremental.components.LookupTracker
        public boolean getRequiresPosition() {
            return false;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.incremental.components.LookupTracker
        public void record(String s, Position position0, String s1, ScopeKind scopeKind0, String s2) {
            Intrinsics.checkNotNullParameter(s, "filePath");
            Intrinsics.checkNotNullParameter(position0, "position");
            Intrinsics.checkNotNullParameter(s1, "scopeFqName");
            Intrinsics.checkNotNullParameter(scopeKind0, "scopeKind");
            Intrinsics.checkNotNullParameter(s2, "name");
        }
    }

    boolean getRequiresPosition();

    void record(String arg1, Position arg2, String arg3, ScopeKind arg4, String arg5);
}

