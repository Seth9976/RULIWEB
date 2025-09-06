package kotlin.reflect.jvm.internal.impl.types.checker;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;

final class SubtypePathNode {
    private final SubtypePathNode previous;
    private final KotlinType type;

    public SubtypePathNode(KotlinType kotlinType0, SubtypePathNode subtypePathNode0) {
        Intrinsics.checkNotNullParameter(kotlinType0, "type");
        super();
        this.type = kotlinType0;
        this.previous = subtypePathNode0;
    }

    public final SubtypePathNode getPrevious() {
        return this.previous;
    }

    public final KotlinType getType() {
        return this.type;
    }
}

