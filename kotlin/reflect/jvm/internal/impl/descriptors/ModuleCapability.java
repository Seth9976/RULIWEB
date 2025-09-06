package kotlin.reflect.jvm.internal.impl.descriptors;

import kotlin.jvm.internal.Intrinsics;

public final class ModuleCapability {
    private final String name;

    public ModuleCapability(String s) {
        Intrinsics.checkNotNullParameter(s, "name");
        super();
        this.name = s;
    }

    @Override
    public String toString() {
        return this.name;
    }
}

