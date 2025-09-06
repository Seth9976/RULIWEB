package kotlin.reflect.jvm.internal.impl.util;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;

public interface ModuleVisibilityHelper {
    public static final class EMPTY implements ModuleVisibilityHelper {
        public static final EMPTY INSTANCE;

        static {
            EMPTY.INSTANCE = new EMPTY();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.util.ModuleVisibilityHelper
        public boolean isInFriendModule(DeclarationDescriptor declarationDescriptor0, DeclarationDescriptor declarationDescriptor1) {
            Intrinsics.checkNotNullParameter(declarationDescriptor0, "what");
            Intrinsics.checkNotNullParameter(declarationDescriptor1, "from");
            return true;
        }
    }

    boolean isInFriendModule(DeclarationDescriptor arg1, DeclarationDescriptor arg2);
}

