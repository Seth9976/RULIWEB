package kotlin.reflect.jvm.internal.impl.load.java;

import kotlin.collections.MapsKt;
import kotlin.reflect.jvm.internal.impl.name.FqName;

public interface NullabilityAnnotationStates {
    public static final class Companion {
        static final Companion $$INSTANCE;
        private static final NullabilityAnnotationStates EMPTY;

        static {
            Companion.$$INSTANCE = new Companion();
            Companion.EMPTY = new NullabilityAnnotationStatesImpl(MapsKt.emptyMap());
        }

        public final NullabilityAnnotationStates getEMPTY() {
            return Companion.EMPTY;
        }
    }

    public static final Companion Companion;

    static {
        NullabilityAnnotationStates.Companion = Companion.$$INSTANCE;
    }

    Object get(FqName arg1);
}

