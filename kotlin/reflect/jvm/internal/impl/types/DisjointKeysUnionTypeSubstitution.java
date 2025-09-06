package kotlin.reflect.jvm.internal.impl.types;

import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;

public final class DisjointKeysUnionTypeSubstitution extends TypeSubstitution {
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        @JvmStatic
        public final TypeSubstitution create(TypeSubstitution typeSubstitution0, TypeSubstitution typeSubstitution1) {
            Intrinsics.checkNotNullParameter(typeSubstitution0, "first");
            Intrinsics.checkNotNullParameter(typeSubstitution1, "second");
            if(typeSubstitution0.isEmpty()) {
                return typeSubstitution1;
            }
            return typeSubstitution1.isEmpty() ? typeSubstitution0 : new DisjointKeysUnionTypeSubstitution(typeSubstitution0, typeSubstitution1, null);
        }
    }

    public static final Companion Companion;
    private final TypeSubstitution first;
    private final TypeSubstitution second;

    static {
        DisjointKeysUnionTypeSubstitution.Companion = new Companion(null);
    }

    private DisjointKeysUnionTypeSubstitution(TypeSubstitution typeSubstitution0, TypeSubstitution typeSubstitution1) {
        this.first = typeSubstitution0;
        this.second = typeSubstitution1;
    }

    public DisjointKeysUnionTypeSubstitution(TypeSubstitution typeSubstitution0, TypeSubstitution typeSubstitution1, DefaultConstructorMarker defaultConstructorMarker0) {
        this(typeSubstitution0, typeSubstitution1);
    }

    // 去混淆评级： 低(20)
    @Override  // kotlin.reflect.jvm.internal.impl.types.TypeSubstitution
    public boolean approximateCapturedTypes() {
        return this.first.approximateCapturedTypes() || this.second.approximateCapturedTypes();
    }

    // 去混淆评级： 低(20)
    @Override  // kotlin.reflect.jvm.internal.impl.types.TypeSubstitution
    public boolean approximateContravariantCapturedTypes() {
        return this.first.approximateContravariantCapturedTypes() || this.second.approximateContravariantCapturedTypes();
    }

    @JvmStatic
    public static final TypeSubstitution create(TypeSubstitution typeSubstitution0, TypeSubstitution typeSubstitution1) {
        return DisjointKeysUnionTypeSubstitution.Companion.create(typeSubstitution0, typeSubstitution1);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.TypeSubstitution
    public Annotations filterAnnotations(Annotations annotations0) {
        Intrinsics.checkNotNullParameter(annotations0, "annotations");
        Annotations annotations1 = this.first.filterAnnotations(annotations0);
        return this.second.filterAnnotations(annotations1);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.TypeSubstitution
    public TypeProjection get(KotlinType kotlinType0) {
        Intrinsics.checkNotNullParameter(kotlinType0, "key");
        TypeProjection typeProjection0 = this.first.get(kotlinType0);
        return typeProjection0 == null ? this.second.get(kotlinType0) : typeProjection0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.TypeSubstitution
    public boolean isEmpty() {
        return false;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.TypeSubstitution
    public KotlinType prepareTopLevelType(KotlinType kotlinType0, Variance variance0) {
        Intrinsics.checkNotNullParameter(kotlinType0, "topLevelType");
        Intrinsics.checkNotNullParameter(variance0, "position");
        KotlinType kotlinType1 = this.first.prepareTopLevelType(kotlinType0, variance0);
        return this.second.prepareTopLevelType(kotlinType1, variance0);
    }
}

