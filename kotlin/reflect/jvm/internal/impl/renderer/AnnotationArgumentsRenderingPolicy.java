package kotlin.reflect.jvm.internal.impl.renderer;

import kotlin.jvm.internal.DefaultConstructorMarker;

public enum AnnotationArgumentsRenderingPolicy {
    NO_ARGUMENTS(false, false, 3, null),
    UNLESS_EMPTY(true, false, 2, null),
    ALWAYS_PARENTHESIZED(true, true);

    private final boolean includeAnnotationArguments;
    private final boolean includeEmptyAnnotationArguments;

    private static final AnnotationArgumentsRenderingPolicy[] $values() [...] // Inlined contents

    private AnnotationArgumentsRenderingPolicy(boolean z, boolean z1) {
        this.includeAnnotationArguments = z;
        this.includeEmptyAnnotationArguments = z1;
    }

    AnnotationArgumentsRenderingPolicy(boolean z, boolean z1, int v1, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v1 & 1) != 0) {
            z = false;
        }
        if((v1 & 2) != 0) {
            z1 = false;
        }
        this(z, z1);
    }

    public final boolean getIncludeAnnotationArguments() {
        return this.includeAnnotationArguments;
    }

    public final boolean getIncludeEmptyAnnotationArguments() {
        return this.includeEmptyAnnotationArguments;
    }
}

