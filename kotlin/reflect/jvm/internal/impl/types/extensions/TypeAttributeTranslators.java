package kotlin.reflect.jvm.internal.impl.types.extensions;

import java.util.List;
import kotlin.jvm.internal.Intrinsics;

public final class TypeAttributeTranslators {
    private final List translators;

    public TypeAttributeTranslators(List list0) {
        Intrinsics.checkNotNullParameter(list0, "translators");
        super();
        this.translators = list0;
    }

    public final List getTranslators() {
        return this.translators;
    }
}

