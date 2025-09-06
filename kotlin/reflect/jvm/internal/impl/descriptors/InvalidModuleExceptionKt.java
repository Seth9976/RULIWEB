package kotlin.reflect.jvm.internal.impl.descriptors;

import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

public final class InvalidModuleExceptionKt {
    private static final ModuleCapability INVALID_MODULE_NOTIFIER_CAPABILITY;

    static {
        InvalidModuleExceptionKt.INVALID_MODULE_NOTIFIER_CAPABILITY = new ModuleCapability("InvalidModuleNotifier");
    }

    public static final void moduleInvalidated(ModuleDescriptor moduleDescriptor0) {
        Unit unit0;
        Intrinsics.checkNotNullParameter(moduleDescriptor0, "<this>");
        InvalidModuleNotifier invalidModuleNotifier0 = (InvalidModuleNotifier)moduleDescriptor0.getCapability(InvalidModuleExceptionKt.INVALID_MODULE_NOTIFIER_CAPABILITY);
        if(invalidModuleNotifier0 == null) {
            unit0 = null;
        }
        else {
            invalidModuleNotifier0.notifyModuleInvalidated(moduleDescriptor0);
            unit0 = Unit.INSTANCE;
        }
        if(unit0 == null) {
            throw new InvalidModuleException("Accessing invalid module descriptor " + moduleDescriptor0);
        }
    }
}

