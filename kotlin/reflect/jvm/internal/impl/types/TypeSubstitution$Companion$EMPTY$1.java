package kotlin.reflect.jvm.internal.impl.types;

import kotlin.jvm.internal.Intrinsics;

public final class TypeSubstitution.Companion.EMPTY.1 extends TypeSubstitution {
    public Void get(KotlinType kotlinType0) {
        Intrinsics.checkNotNullParameter(kotlinType0, "key");
        return null;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.TypeSubstitution
    public TypeProjection get(KotlinType kotlinType0) {
        return (TypeProjection)this.get(kotlinType0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.TypeSubstitution
    public boolean isEmpty() {
        return true;
    }

    @Override
    public String toString() {
        return "Empty TypeSubstitution";
    }
}

