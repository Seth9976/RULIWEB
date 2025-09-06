package kotlin.reflect.jvm.internal.impl.types.error;

import java.util.Arrays;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeAttributes;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;

public final class ErrorType extends SimpleType {
    private final List arguments;
    private final TypeConstructor constructor;
    private final String debugMessage;
    private final String[] formatParams;
    private final boolean isMarkedNullable;
    private final ErrorTypeKind kind;
    private final MemberScope memberScope;

    public ErrorType(TypeConstructor typeConstructor0, MemberScope memberScope0, ErrorTypeKind errorTypeKind0, List list0, boolean z, String[] arr_s) {
        Intrinsics.checkNotNullParameter(typeConstructor0, "constructor");
        Intrinsics.checkNotNullParameter(memberScope0, "memberScope");
        Intrinsics.checkNotNullParameter(errorTypeKind0, "kind");
        Intrinsics.checkNotNullParameter(list0, "arguments");
        Intrinsics.checkNotNullParameter(arr_s, "formatParams");
        super();
        this.constructor = typeConstructor0;
        this.memberScope = memberScope0;
        this.kind = errorTypeKind0;
        this.arguments = list0;
        this.isMarkedNullable = z;
        this.formatParams = arr_s;
        Object[] arr_object = Arrays.copyOf(arr_s, arr_s.length);
        String s = String.format(errorTypeKind0.getDebugMessage(), Arrays.copyOf(arr_object, arr_object.length));
        Intrinsics.checkNotNullExpressionValue(s, "format(format, *args)");
        this.debugMessage = s;
    }

    public ErrorType(TypeConstructor typeConstructor0, MemberScope memberScope0, ErrorTypeKind errorTypeKind0, List list0, boolean z, String[] arr_s, int v, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v & 8) != 0) {
            list0 = CollectionsKt.emptyList();
        }
        this(typeConstructor0, memberScope0, errorTypeKind0, list0, ((v & 16) == 0 ? z : false), arr_s);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.KotlinType
    public List getArguments() {
        return this.arguments;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.KotlinType
    public TypeAttributes getAttributes() {
        return TypeAttributes.Companion.getEmpty();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.KotlinType
    public TypeConstructor getConstructor() {
        return this.constructor;
    }

    public final String getDebugMessage() {
        return this.debugMessage;
    }

    public final ErrorTypeKind getKind() {
        return this.kind;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.KotlinType
    public MemberScope getMemberScope() {
        return this.memberScope;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.KotlinType
    public boolean isMarkedNullable() {
        return this.isMarkedNullable;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.SimpleType
    public SimpleType makeNullableAsSpecified(boolean z) {
        Object[] arr_object = Arrays.copyOf(this.formatParams, this.formatParams.length);
        return new ErrorType(this.getConstructor(), this.getMemberScope(), this.kind, this.getArguments(), z, ((String[])arr_object));
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.UnwrappedType
    public UnwrappedType makeNullableAsSpecified(boolean z) {
        return this.makeNullableAsSpecified(z);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.KotlinType
    public KotlinType refine(KotlinTypeRefiner kotlinTypeRefiner0) {
        return this.refine(kotlinTypeRefiner0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.UnwrappedType
    public UnwrappedType refine(KotlinTypeRefiner kotlinTypeRefiner0) {
        return this.refine(kotlinTypeRefiner0);
    }

    public ErrorType refine(KotlinTypeRefiner kotlinTypeRefiner0) {
        Intrinsics.checkNotNullParameter(kotlinTypeRefiner0, "kotlinTypeRefiner");
        return this;
    }

    public final ErrorType replaceArguments(List list0) {
        Intrinsics.checkNotNullParameter(list0, "newArguments");
        Object[] arr_object = Arrays.copyOf(this.formatParams, this.formatParams.length);
        return new ErrorType(this.getConstructor(), this.getMemberScope(), this.kind, list0, this.isMarkedNullable(), ((String[])arr_object));
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.SimpleType
    public SimpleType replaceAttributes(TypeAttributes typeAttributes0) {
        Intrinsics.checkNotNullParameter(typeAttributes0, "newAttributes");
        return this;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.UnwrappedType
    public UnwrappedType replaceAttributes(TypeAttributes typeAttributes0) {
        return this.replaceAttributes(typeAttributes0);
    }
}

