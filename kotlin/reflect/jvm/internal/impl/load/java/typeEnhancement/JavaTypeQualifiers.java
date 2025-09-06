package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import kotlin.jvm.internal.DefaultConstructorMarker;

public final class JavaTypeQualifiers {
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final JavaTypeQualifiers getNONE() {
            return JavaTypeQualifiers.NONE;
        }
    }

    public static final Companion Companion;
    private static final JavaTypeQualifiers NONE;
    private final boolean definitelyNotNull;
    private final boolean isNullabilityQualifierForWarning;
    private final MutabilityQualifier mutability;
    private final NullabilityQualifier nullability;

    static {
        JavaTypeQualifiers.Companion = new Companion(null);
        JavaTypeQualifiers.NONE = new JavaTypeQualifiers(null, null, false, false, 8, null);
    }

    public JavaTypeQualifiers(NullabilityQualifier nullabilityQualifier0, MutabilityQualifier mutabilityQualifier0, boolean z, boolean z1) {
        this.nullability = nullabilityQualifier0;
        this.mutability = mutabilityQualifier0;
        this.definitelyNotNull = z;
        this.isNullabilityQualifierForWarning = z1;
    }

    public JavaTypeQualifiers(NullabilityQualifier nullabilityQualifier0, MutabilityQualifier mutabilityQualifier0, boolean z, boolean z1, int v, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v & 8) != 0) {
            z1 = false;
        }
        this(nullabilityQualifier0, mutabilityQualifier0, z, z1);
    }

    public final boolean getDefinitelyNotNull() {
        return this.definitelyNotNull;
    }

    public final MutabilityQualifier getMutability() {
        return this.mutability;
    }

    public final NullabilityQualifier getNullability() {
        return this.nullability;
    }

    public final boolean isNullabilityQualifierForWarning() {
        return this.isNullabilityQualifierForWarning;
    }
}

