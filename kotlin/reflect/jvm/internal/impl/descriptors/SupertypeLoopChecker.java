package kotlin.reflect.jvm.internal.impl.descriptors;

import java.util.Collection;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;

public interface SupertypeLoopChecker {
    public static final class EMPTY implements SupertypeLoopChecker {
        public static final EMPTY INSTANCE;

        static {
            EMPTY.INSTANCE = new EMPTY();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.descriptors.SupertypeLoopChecker
        public Collection findLoopsInSupertypesAndDisconnect(TypeConstructor typeConstructor0, Collection collection0, Function1 function10, Function1 function11) {
            Intrinsics.checkNotNullParameter(typeConstructor0, "currentTypeConstructor");
            Intrinsics.checkNotNullParameter(collection0, "superTypes");
            Intrinsics.checkNotNullParameter(function10, "neighbors");
            Intrinsics.checkNotNullParameter(function11, "reportLoop");
            return collection0;
        }
    }

    Collection findLoopsInSupertypesAndDisconnect(TypeConstructor arg1, Collection arg2, Function1 arg3, Function1 arg4);
}

