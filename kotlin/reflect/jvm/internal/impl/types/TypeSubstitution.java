package kotlin.reflect.jvm.internal.impl.types;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;

public abstract class TypeSubstitution {
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }
    }

    public static final Companion Companion;
    public static final TypeSubstitution EMPTY;

    static {
        TypeSubstitution.Companion = new Companion(null);
        TypeSubstitution.EMPTY = new TypeSubstitution.Companion.EMPTY.1();
    }

    public boolean approximateCapturedTypes() {
        return false;
    }

    public boolean approximateContravariantCapturedTypes() {
        return false;
    }

    public final TypeSubstitutor buildSubstitutor() {
        TypeSubstitutor typeSubstitutor0 = TypeSubstitutor.create(this);
        Intrinsics.checkNotNullExpressionValue(typeSubstitutor0, "create(this)");
        return typeSubstitutor0;
    }

    public Annotations filterAnnotations(Annotations annotations0) {
        Intrinsics.checkNotNullParameter(annotations0, "annotations");
        return annotations0;
    }

    public abstract TypeProjection get(KotlinType arg1);

    public boolean isEmpty() {
        return false;
    }

    public KotlinType prepareTopLevelType(KotlinType kotlinType0, Variance variance0) {
        Intrinsics.checkNotNullParameter(kotlinType0, "topLevelType");
        Intrinsics.checkNotNullParameter(variance0, "position");
        return kotlinType0;
    }

    public final TypeSubstitution replaceWithNonApproximating() {
        return new TypeSubstitution() {
            @Override  // kotlin.reflect.jvm.internal.impl.types.TypeSubstitution
            public boolean approximateCapturedTypes() {
                return false;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.types.TypeSubstitution
            public boolean approximateContravariantCapturedTypes() {
                return false;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.types.TypeSubstitution
            public Annotations filterAnnotations(Annotations annotations0) {
                Intrinsics.checkNotNullParameter(annotations0, "annotations");
                return TypeSubstitution.this.filterAnnotations(annotations0);
            }

            @Override  // kotlin.reflect.jvm.internal.impl.types.TypeSubstitution
            public TypeProjection get(KotlinType kotlinType0) {
                Intrinsics.checkNotNullParameter(kotlinType0, "key");
                return TypeSubstitution.this.get(kotlinType0);
            }

            @Override  // kotlin.reflect.jvm.internal.impl.types.TypeSubstitution
            public boolean isEmpty() {
                return TypeSubstitution.this.isEmpty();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.types.TypeSubstitution
            public KotlinType prepareTopLevelType(KotlinType kotlinType0, Variance variance0) {
                Intrinsics.checkNotNullParameter(kotlinType0, "topLevelType");
                Intrinsics.checkNotNullParameter(variance0, "position");
                return TypeSubstitution.this.prepareTopLevelType(kotlinType0, variance0);
            }
        };
    }
}

