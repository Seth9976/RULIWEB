package kotlin.reflect.jvm.internal.impl.resolve.calls.inference;

import java.util.Collection;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;
import kotlin.reflect.jvm.internal.impl.types.checker.NewCapturedTypeConstructor;

public final class CapturedTypeConstructorImpl implements CapturedTypeConstructor {
    private NewCapturedTypeConstructor newTypeConstructor;
    private final TypeProjection projection;

    public CapturedTypeConstructorImpl(TypeProjection typeProjection0) {
        Intrinsics.checkNotNullParameter(typeProjection0, "projection");
        super();
        this.projection = typeProjection0;
        this.getProjection().getProjectionKind();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
    public KotlinBuiltIns getBuiltIns() {
        KotlinBuiltIns kotlinBuiltIns0 = this.getProjection().getType().getConstructor().getBuiltIns();
        Intrinsics.checkNotNullExpressionValue(kotlinBuiltIns0, "projection.type.constructor.builtIns");
        return kotlinBuiltIns0;
    }

    public Void getDeclarationDescriptor() [...] // Inlined contents

    @Override  // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
    public ClassifierDescriptor getDeclarationDescriptor() {
        return null;
    }

    public final NewCapturedTypeConstructor getNewTypeConstructor() {
        return this.newTypeConstructor;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
    public List getParameters() {
        return CollectionsKt.emptyList();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.calls.inference.CapturedTypeConstructor
    public TypeProjection getProjection() {
        return this.projection;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
    public Collection getSupertypes() {
        KotlinType kotlinType0 = this.getProjection().getProjectionKind() == Variance.OUT_VARIANCE ? this.getProjection().getType() : this.getBuiltIns().getNullableAnyType();
        Intrinsics.checkNotNullExpressionValue(kotlinType0, "if (projection.projectio… builtIns.nullableAnyType");
        return CollectionsKt.listOf(kotlinType0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
    public boolean isDenotable() {
        return false;
    }

    public CapturedTypeConstructorImpl refine(KotlinTypeRefiner kotlinTypeRefiner0) {
        Intrinsics.checkNotNullParameter(kotlinTypeRefiner0, "kotlinTypeRefiner");
        TypeProjection typeProjection0 = this.getProjection().refine(kotlinTypeRefiner0);
        Intrinsics.checkNotNullExpressionValue(typeProjection0, "projection.refine(kotlinTypeRefiner)");
        return new CapturedTypeConstructorImpl(typeProjection0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
    public TypeConstructor refine(KotlinTypeRefiner kotlinTypeRefiner0) {
        return this.refine(kotlinTypeRefiner0);
    }

    public final void setNewTypeConstructor(NewCapturedTypeConstructor newCapturedTypeConstructor0) {
        this.newTypeConstructor = newCapturedTypeConstructor0;
    }

    @Override
    public String toString() {
        return "CapturedTypeConstructor(" + this.getProjection() + ')';
    }
}

