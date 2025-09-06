package kotlin.reflect.jvm.internal.impl.types.error;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;

public final class ErrorUtils {
    public static final ErrorUtils INSTANCE;
    private static final ErrorClassDescriptor errorClass;
    private static final ModuleDescriptor errorModule;
    private static final PropertyDescriptor errorProperty;
    private static final Set errorPropertyGroup;
    private static final KotlinType errorPropertyType;
    private static final KotlinType errorTypeForLoopInSupertypes;

    static {
        ErrorUtils.INSTANCE = new ErrorUtils();
        ErrorUtils.errorModule = ErrorModuleDescriptor.INSTANCE;
        Arrays.copyOf(new Object[]{"unknown class"}, 1);
        Intrinsics.checkNotNullExpressionValue("<Error class: unknown class>", "format(this, *args)");
        Name name0 = Name.special("<Error class: unknown class>");
        Intrinsics.checkNotNullExpressionValue(name0, "special(ErrorEntity.ERRO….format(\"unknown class\"))");
        ErrorUtils.errorClass = new ErrorClassDescriptor(name0);
        ErrorUtils.errorTypeForLoopInSupertypes = ErrorUtils.createErrorType(ErrorTypeKind.CYCLIC_SUPERTYPES, new String[0]);
        ErrorUtils.errorPropertyType = ErrorUtils.createErrorType(ErrorTypeKind.ERROR_PROPERTY_TYPE, new String[0]);
        PropertyDescriptor propertyDescriptor0 = new ErrorPropertyDescriptor();
        ErrorUtils.errorProperty = propertyDescriptor0;
        ErrorUtils.errorPropertyGroup = SetsKt.setOf(propertyDescriptor0);
    }

    @JvmStatic
    public static final ErrorScope createErrorScope(ErrorScopeKind errorScopeKind0, boolean z, String[] arr_s) {
        Intrinsics.checkNotNullParameter(errorScopeKind0, "kind");
        Intrinsics.checkNotNullParameter(arr_s, "formatParams");
        return z ? new ThrowingScope(errorScopeKind0, ((String[])Arrays.copyOf(arr_s, arr_s.length))) : new ErrorScope(errorScopeKind0, ((String[])Arrays.copyOf(arr_s, arr_s.length)));
    }

    @JvmStatic
    public static final ErrorScope createErrorScope(ErrorScopeKind errorScopeKind0, String[] arr_s) {
        Intrinsics.checkNotNullParameter(errorScopeKind0, "kind");
        Intrinsics.checkNotNullParameter(arr_s, "formatParams");
        return ErrorUtils.createErrorScope(errorScopeKind0, false, ((String[])Arrays.copyOf(arr_s, arr_s.length)));
    }

    @JvmStatic
    public static final ErrorType createErrorType(ErrorTypeKind errorTypeKind0, String[] arr_s) {
        Intrinsics.checkNotNullParameter(errorTypeKind0, "kind");
        Intrinsics.checkNotNullParameter(arr_s, "formatParams");
        List list0 = CollectionsKt.emptyList();
        String[] arr_s1 = (String[])Arrays.copyOf(arr_s, arr_s.length);
        return ErrorUtils.INSTANCE.createErrorTypeWithArguments(errorTypeKind0, list0, arr_s1);
    }

    public final ErrorType createErrorType(ErrorTypeKind errorTypeKind0, TypeConstructor typeConstructor0, String[] arr_s) {
        Intrinsics.checkNotNullParameter(errorTypeKind0, "kind");
        Intrinsics.checkNotNullParameter(typeConstructor0, "typeConstructor");
        Intrinsics.checkNotNullParameter(arr_s, "formatParams");
        return this.createErrorTypeWithArguments(errorTypeKind0, CollectionsKt.emptyList(), typeConstructor0, ((String[])Arrays.copyOf(arr_s, arr_s.length)));
    }

    public final ErrorTypeConstructor createErrorTypeConstructor(ErrorTypeKind errorTypeKind0, String[] arr_s) {
        Intrinsics.checkNotNullParameter(errorTypeKind0, "kind");
        Intrinsics.checkNotNullParameter(arr_s, "formatParams");
        return new ErrorTypeConstructor(errorTypeKind0, ((String[])Arrays.copyOf(arr_s, arr_s.length)));
    }

    public final ErrorType createErrorTypeWithArguments(ErrorTypeKind errorTypeKind0, List list0, TypeConstructor typeConstructor0, String[] arr_s) {
        Intrinsics.checkNotNullParameter(errorTypeKind0, "kind");
        Intrinsics.checkNotNullParameter(list0, "arguments");
        Intrinsics.checkNotNullParameter(typeConstructor0, "typeConstructor");
        Intrinsics.checkNotNullParameter(arr_s, "formatParams");
        return new ErrorType(typeConstructor0, ErrorUtils.createErrorScope(ErrorScopeKind.ERROR_TYPE_SCOPE, new String[]{typeConstructor0.toString()}), errorTypeKind0, list0, false, ((String[])Arrays.copyOf(arr_s, arr_s.length)));
    }

    public final ErrorType createErrorTypeWithArguments(ErrorTypeKind errorTypeKind0, List list0, String[] arr_s) {
        Intrinsics.checkNotNullParameter(errorTypeKind0, "kind");
        Intrinsics.checkNotNullParameter(list0, "arguments");
        Intrinsics.checkNotNullParameter(arr_s, "formatParams");
        return this.createErrorTypeWithArguments(errorTypeKind0, list0, this.createErrorTypeConstructor(errorTypeKind0, ((String[])Arrays.copyOf(arr_s, arr_s.length))), ((String[])Arrays.copyOf(arr_s, arr_s.length)));
    }

    public final ErrorClassDescriptor getErrorClass() {
        return ErrorUtils.errorClass;
    }

    public final ModuleDescriptor getErrorModule() {
        return ErrorUtils.errorModule;
    }

    public final Set getErrorPropertyGroup() {
        return ErrorUtils.errorPropertyGroup;
    }

    public final KotlinType getErrorPropertyType() {
        return ErrorUtils.errorPropertyType;
    }

    public final KotlinType getErrorTypeForLoopInSupertypes() {
        return ErrorUtils.errorTypeForLoopInSupertypes;
    }

    // 去混淆评级： 低(20)
    @JvmStatic
    public static final boolean isError(DeclarationDescriptor declarationDescriptor0) {
        return declarationDescriptor0 != null && (ErrorUtils.INSTANCE.isErrorClass(declarationDescriptor0) || ErrorUtils.INSTANCE.isErrorClass(declarationDescriptor0.getContainingDeclaration()) || declarationDescriptor0 == ErrorUtils.errorModule);
    }

    private final boolean isErrorClass(DeclarationDescriptor declarationDescriptor0) {
        return declarationDescriptor0 instanceof ErrorClassDescriptor;
    }

    @JvmStatic
    public static final boolean isUninferredTypeVariable(KotlinType kotlinType0) {
        if(kotlinType0 == null) {
            return false;
        }
        TypeConstructor typeConstructor0 = kotlinType0.getConstructor();
        return typeConstructor0 instanceof ErrorTypeConstructor && ((ErrorTypeConstructor)typeConstructor0).getKind() == ErrorTypeKind.UNINFERRED_TYPE_VARIABLE;
    }

    public final String unresolvedTypeAsItIs(KotlinType kotlinType0) {
        Intrinsics.checkNotNullParameter(kotlinType0, "type");
        TypeUtilsKt.isUnresolvedType(kotlinType0);
        TypeConstructor typeConstructor0 = kotlinType0.getConstructor();
        Intrinsics.checkNotNull(typeConstructor0, "null cannot be cast to non-null type org.jetbrains.kotlin.types.error.ErrorTypeConstructor");
        return ((ErrorTypeConstructor)typeConstructor0).getParam(0);
    }
}

