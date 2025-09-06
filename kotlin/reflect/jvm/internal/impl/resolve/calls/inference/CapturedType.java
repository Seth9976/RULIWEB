package kotlin.reflect.jvm.internal.impl.resolve.calls.inference;

import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeAttributes;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;
import kotlin.reflect.jvm.internal.impl.types.error.ErrorScopeKind;
import kotlin.reflect.jvm.internal.impl.types.error.ErrorUtils;
import kotlin.reflect.jvm.internal.impl.types.model.CapturedTypeMarker;

public final class CapturedType extends SimpleType implements CapturedTypeMarker {
    private final TypeAttributes attributes;
    private final CapturedTypeConstructor constructor;
    private final boolean isMarkedNullable;
    private final TypeProjection typeProjection;

    public CapturedType(TypeProjection typeProjection0, CapturedTypeConstructor capturedTypeConstructor0, boolean z, TypeAttributes typeAttributes0) {
        Intrinsics.checkNotNullParameter(typeProjection0, "typeProjection");
        Intrinsics.checkNotNullParameter(capturedTypeConstructor0, "constructor");
        Intrinsics.checkNotNullParameter(typeAttributes0, "attributes");
        super();
        this.typeProjection = typeProjection0;
        this.constructor = capturedTypeConstructor0;
        this.isMarkedNullable = z;
        this.attributes = typeAttributes0;
    }

    public CapturedType(TypeProjection typeProjection0, CapturedTypeConstructor capturedTypeConstructor0, boolean z, TypeAttributes typeAttributes0, int v, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v & 2) != 0) {
            capturedTypeConstructor0 = new CapturedTypeConstructorImpl(typeProjection0);
        }
        if((v & 4) != 0) {
            z = false;
        }
        if((v & 8) != 0) {
            typeAttributes0 = TypeAttributes.Companion.getEmpty();
        }
        this(typeProjection0, capturedTypeConstructor0, z, typeAttributes0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.KotlinType
    public List getArguments() {
        return CollectionsKt.emptyList();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.KotlinType
    public TypeAttributes getAttributes() {
        return this.attributes;
    }

    public CapturedTypeConstructor getConstructor() {
        return this.constructor;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.KotlinType
    public TypeConstructor getConstructor() {
        return this.getConstructor();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.KotlinType
    public MemberScope getMemberScope() {
        return ErrorUtils.createErrorScope(ErrorScopeKind.CAPTURED_TYPE_SCOPE, true, new String[0]);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.KotlinType
    public boolean isMarkedNullable() {
        return this.isMarkedNullable;
    }

    public CapturedType makeNullableAsSpecified(boolean z) {
        return z == this.isMarkedNullable() ? this : new CapturedType(this.typeProjection, this.getConstructor(), z, this.getAttributes());
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.SimpleType
    public SimpleType makeNullableAsSpecified(boolean z) {
        return this.makeNullableAsSpecified(z);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.UnwrappedType
    public UnwrappedType makeNullableAsSpecified(boolean z) {
        return this.makeNullableAsSpecified(z);
    }

    public CapturedType refine(KotlinTypeRefiner kotlinTypeRefiner0) {
        Intrinsics.checkNotNullParameter(kotlinTypeRefiner0, "kotlinTypeRefiner");
        TypeProjection typeProjection0 = this.typeProjection.refine(kotlinTypeRefiner0);
        Intrinsics.checkNotNullExpressionValue(typeProjection0, "typeProjection.refine(kotlinTypeRefiner)");
        return new CapturedType(typeProjection0, this.getConstructor(), this.isMarkedNullable(), this.getAttributes());
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.KotlinType
    public KotlinType refine(KotlinTypeRefiner kotlinTypeRefiner0) {
        return this.refine(kotlinTypeRefiner0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.UnwrappedType
    public UnwrappedType refine(KotlinTypeRefiner kotlinTypeRefiner0) {
        return this.refine(kotlinTypeRefiner0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.SimpleType
    public SimpleType replaceAttributes(TypeAttributes typeAttributes0) {
        Intrinsics.checkNotNullParameter(typeAttributes0, "newAttributes");
        return new CapturedType(this.typeProjection, this.getConstructor(), this.isMarkedNullable(), typeAttributes0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.UnwrappedType
    public UnwrappedType replaceAttributes(TypeAttributes typeAttributes0) {
        return this.replaceAttributes(typeAttributes0);
    }

    // 去混淆评级： 低(20)
    @Override  // kotlin.reflect.jvm.internal.impl.types.SimpleType
    public String toString() {
        return "Captured(" + this.typeProjection + ')' + (this.isMarkedNullable() ? "?" : "");
    }
}

