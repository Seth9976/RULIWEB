package kotlin.reflect.jvm.internal.impl.types;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;

public class DelegatedTypeSubstitution extends TypeSubstitution {
    private final TypeSubstitution substitution;

    public DelegatedTypeSubstitution(TypeSubstitution typeSubstitution0) {
        Intrinsics.checkNotNullParameter(typeSubstitution0, "substitution");
        super();
        this.substitution = typeSubstitution0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.TypeSubstitution
    public boolean approximateCapturedTypes() {
        return this.substitution.approximateCapturedTypes();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.TypeSubstitution
    public boolean approximateContravariantCapturedTypes() {
        return this.substitution.approximateContravariantCapturedTypes();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.TypeSubstitution
    public Annotations filterAnnotations(Annotations annotations0) {
        Intrinsics.checkNotNullParameter(annotations0, "annotations");
        return this.substitution.filterAnnotations(annotations0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.TypeSubstitution
    public TypeProjection get(KotlinType kotlinType0) {
        Intrinsics.checkNotNullParameter(kotlinType0, "key");
        return this.substitution.get(kotlinType0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.TypeSubstitution
    public boolean isEmpty() {
        return this.substitution.isEmpty();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.TypeSubstitution
    public KotlinType prepareTopLevelType(KotlinType kotlinType0, Variance variance0) {
        Intrinsics.checkNotNullParameter(kotlinType0, "topLevelType");
        Intrinsics.checkNotNullParameter(variance0, "position");
        return this.substitution.prepareTopLevelType(kotlinType0, variance0);
    }
}

