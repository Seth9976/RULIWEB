package kotlin.reflect.jvm.internal.impl.types.checker;

import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeAttributes;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;
import kotlin.reflect.jvm.internal.impl.types.error.ErrorScopeKind;
import kotlin.reflect.jvm.internal.impl.types.error.ErrorUtils;
import kotlin.reflect.jvm.internal.impl.types.model.CaptureStatus;
import kotlin.reflect.jvm.internal.impl.types.model.CapturedTypeMarker;

public final class NewCapturedType extends SimpleType implements CapturedTypeMarker {
    private final TypeAttributes attributes;
    private final CaptureStatus captureStatus;
    private final NewCapturedTypeConstructor constructor;
    private final boolean isMarkedNullable;
    private final boolean isProjectionNotNull;
    private final UnwrappedType lowerType;

    public NewCapturedType(CaptureStatus captureStatus0, UnwrappedType unwrappedType0, TypeProjection typeProjection0, TypeParameterDescriptor typeParameterDescriptor0) {
        Intrinsics.checkNotNullParameter(captureStatus0, "captureStatus");
        Intrinsics.checkNotNullParameter(typeProjection0, "projection");
        Intrinsics.checkNotNullParameter(typeParameterDescriptor0, "typeParameter");
        this(captureStatus0, new NewCapturedTypeConstructor(typeProjection0, null, null, typeParameterDescriptor0, 6, null), unwrappedType0, null, false, false, 56, null);
    }

    public NewCapturedType(CaptureStatus captureStatus0, NewCapturedTypeConstructor newCapturedTypeConstructor0, UnwrappedType unwrappedType0, TypeAttributes typeAttributes0, boolean z, boolean z1) {
        Intrinsics.checkNotNullParameter(captureStatus0, "captureStatus");
        Intrinsics.checkNotNullParameter(newCapturedTypeConstructor0, "constructor");
        Intrinsics.checkNotNullParameter(typeAttributes0, "attributes");
        super();
        this.captureStatus = captureStatus0;
        this.constructor = newCapturedTypeConstructor0;
        this.lowerType = unwrappedType0;
        this.attributes = typeAttributes0;
        this.isMarkedNullable = z;
        this.isProjectionNotNull = z1;
    }

    public NewCapturedType(CaptureStatus captureStatus0, NewCapturedTypeConstructor newCapturedTypeConstructor0, UnwrappedType unwrappedType0, TypeAttributes typeAttributes0, boolean z, boolean z1, int v, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v & 8) != 0) {
            typeAttributes0 = TypeAttributes.Companion.getEmpty();
        }
        this(captureStatus0, newCapturedTypeConstructor0, unwrappedType0, typeAttributes0, ((v & 16) == 0 ? z : false), ((v & 0x20) == 0 ? z1 : false));
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.KotlinType
    public List getArguments() {
        return CollectionsKt.emptyList();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.KotlinType
    public TypeAttributes getAttributes() {
        return this.attributes;
    }

    public final CaptureStatus getCaptureStatus() {
        return this.captureStatus;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.KotlinType
    public TypeConstructor getConstructor() {
        return this.getConstructor();
    }

    public NewCapturedTypeConstructor getConstructor() {
        return this.constructor;
    }

    public final UnwrappedType getLowerType() {
        return this.lowerType;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.KotlinType
    public MemberScope getMemberScope() {
        return ErrorUtils.createErrorScope(ErrorScopeKind.CAPTURED_TYPE_SCOPE, true, new String[0]);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.KotlinType
    public boolean isMarkedNullable() {
        return this.isMarkedNullable;
    }

    public final boolean isProjectionNotNull() {
        return this.isProjectionNotNull;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.SimpleType
    public SimpleType makeNullableAsSpecified(boolean z) {
        return this.makeNullableAsSpecified(z);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.UnwrappedType
    public UnwrappedType makeNullableAsSpecified(boolean z) {
        return this.makeNullableAsSpecified(z);
    }

    public NewCapturedType makeNullableAsSpecified(boolean z) {
        return new NewCapturedType(this.captureStatus, this.getConstructor(), this.lowerType, this.getAttributes(), z, false, 0x20, null);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.KotlinType
    public KotlinType refine(KotlinTypeRefiner kotlinTypeRefiner0) {
        return this.refine(kotlinTypeRefiner0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.UnwrappedType
    public UnwrappedType refine(KotlinTypeRefiner kotlinTypeRefiner0) {
        return this.refine(kotlinTypeRefiner0);
    }

    public NewCapturedType refine(KotlinTypeRefiner kotlinTypeRefiner0) {
        Intrinsics.checkNotNullParameter(kotlinTypeRefiner0, "kotlinTypeRefiner");
        NewCapturedTypeConstructor newCapturedTypeConstructor0 = this.getConstructor().refine(kotlinTypeRefiner0);
        return this.lowerType == null ? new NewCapturedType(this.captureStatus, newCapturedTypeConstructor0, null, this.getAttributes(), this.isMarkedNullable(), false, 0x20, null) : new NewCapturedType(this.captureStatus, newCapturedTypeConstructor0, kotlinTypeRefiner0.refineType(this.lowerType).unwrap(), this.getAttributes(), this.isMarkedNullable(), false, 0x20, null);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.SimpleType
    public SimpleType replaceAttributes(TypeAttributes typeAttributes0) {
        Intrinsics.checkNotNullParameter(typeAttributes0, "newAttributes");
        return new NewCapturedType(this.captureStatus, this.getConstructor(), this.lowerType, typeAttributes0, this.isMarkedNullable(), this.isProjectionNotNull);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.UnwrappedType
    public UnwrappedType replaceAttributes(TypeAttributes typeAttributes0) {
        return this.replaceAttributes(typeAttributes0);
    }
}

