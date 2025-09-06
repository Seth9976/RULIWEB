package kotlin.reflect.jvm.internal.impl.types.checker;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeCheckerState.SupertypesPolicy.DoCustomTransform;
import kotlin.reflect.jvm.internal.impl.types.TypeCheckerState;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.model.KotlinTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.SimpleTypeMarker;

public final class ClassicTypeSystemContext.substitutionSupertypePolicy.2 extends DoCustomTransform {
    final TypeSubstitutor $substitutor;

    ClassicTypeSystemContext.substitutionSupertypePolicy.2(ClassicTypeSystemContext classicTypeSystemContext0, TypeSubstitutor typeSubstitutor0) {
        ClassicTypeSystemContext.this = classicTypeSystemContext0;
        this.$substitutor = typeSubstitutor0;
        super();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.TypeCheckerState$SupertypesPolicy
    public SimpleTypeMarker transformType(TypeCheckerState typeCheckerState0, KotlinTypeMarker kotlinTypeMarker0) {
        Intrinsics.checkNotNullParameter(typeCheckerState0, "state");
        Intrinsics.checkNotNullParameter(kotlinTypeMarker0, "type");
        SimpleTypeMarker simpleTypeMarker0 = ClassicTypeSystemContext.this.lowerBoundIfFlexible(kotlinTypeMarker0);
        Intrinsics.checkNotNull(simpleTypeMarker0, "null cannot be cast to non-null type org.jetbrains.kotlin.types.KotlinType");
        KotlinType kotlinType0 = this.$substitutor.safeSubstitute(((KotlinType)simpleTypeMarker0), Variance.INVARIANT);
        Intrinsics.checkNotNullExpressionValue(kotlinType0, "substitutor.safeSubstitu…VARIANT\n                )");
        SimpleTypeMarker simpleTypeMarker1 = ClassicTypeSystemContext.this.asSimpleType(kotlinType0);
        Intrinsics.checkNotNull(simpleTypeMarker1);
        return simpleTypeMarker1;
    }
}

