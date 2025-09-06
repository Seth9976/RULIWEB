package com.squareup.moshi;

import com.squareup.moshi.internal.Util;
import java.lang.annotation.Annotation;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlin.reflect.KType;
import kotlin.reflect.TypesJVMKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0010\u001B\n\u0000\u001A\u0011\u0010\u0005\u001A\u00020\u0006\"\u0006\b\u0000\u0010\u0007\u0018\u0001H\u0087\b\u001A\u0011\u0010\b\u001A\u00020\u0006\"\u0006\b\u0000\u0010\u0007\u0018\u0001H\u0087\b\u001A\n\u0010\t\u001A\u00020\n*\u00020\u0002\u001A\u000E\u0010\t\u001A\u00020\n*\u0006\u0012\u0002\b\u00030\u000B\u001A\f\u0010\t\u001A\u00020\n*\u00020\fH\u0007\u001A\'\u0010\r\u001A\n\u0012\u0004\u0012\u00020\u000F\u0018\u00010\u000E\"\n\b\u0000\u0010\u0007\u0018\u0001*\u00020\u000F*\b\u0012\u0004\u0012\u00020\u000F0\u000EH\u0086\b\"\u0019\u0010\u0000\u001A\u0006\u0012\u0002\b\u00030\u0001*\u00020\u00028F¢\u0006\u0006\u001A\u0004\b\u0003\u0010\u0004¨\u0006\u0010"}, d2 = {"rawType", "Ljava/lang/Class;", "Ljava/lang/reflect/Type;", "getRawType", "(Ljava/lang/reflect/Type;)Ljava/lang/Class;", "subtypeOf", "Ljava/lang/reflect/WildcardType;", "T", "supertypeOf", "asArrayType", "Ljava/lang/reflect/GenericArrayType;", "Lkotlin/reflect/KClass;", "Lkotlin/reflect/KType;", "nextAnnotations", "", "", "moshi"}, k = 2, mv = {1, 4, 0})
public final class _MoshiKotlinTypesExtensionsKt {
    public static final GenericArrayType asArrayType(Type type0) {
        Intrinsics.checkNotNullParameter(type0, "$this$asArrayType");
        GenericArrayType genericArrayType0 = Types.arrayOf(type0);
        Intrinsics.checkNotNullExpressionValue(genericArrayType0, "Types.arrayOf(this)");
        return genericArrayType0;
    }

    public static final GenericArrayType asArrayType(KClass kClass0) {
        Intrinsics.checkNotNullParameter(kClass0, "$this$asArrayType");
        return _MoshiKotlinTypesExtensionsKt.asArrayType(JvmClassMappingKt.getJavaClass(kClass0));
    }

    public static final GenericArrayType asArrayType(KType kType0) {
        Intrinsics.checkNotNullParameter(kType0, "$this$asArrayType");
        return _MoshiKotlinTypesExtensionsKt.asArrayType(TypesJVMKt.getJavaType(kType0));
    }

    public static final Class getRawType(Type type0) {
        Intrinsics.checkNotNullParameter(type0, "$this$rawType");
        Class class0 = Types.getRawType(type0);
        Intrinsics.checkNotNullExpressionValue(class0, "Types.getRawType(this)");
        return class0;
    }

    public static final Set nextAnnotations(Set set0) {
        Intrinsics.checkNotNullParameter(set0, "$this$nextAnnotations");
        Intrinsics.reifiedOperationMarker(4, "T");
        return Types.nextAnnotations(set0, Annotation.class);
    }

    public static final WildcardType subtypeOf() {
        Intrinsics.reifiedOperationMarker(6, "T");
        Type type0 = TypesJVMKt.getJavaType(null);
        if(type0 instanceof Class) {
            Class class0 = Util.boxIfPrimitive(((Class)type0));
            Intrinsics.checkNotNullExpressionValue(class0, "Util.boxIfPrimitive(type)");
            type0 = class0;
        }
        WildcardType wildcardType0 = Types.subtypeOf(type0);
        Intrinsics.checkNotNullExpressionValue(wildcardType0, "Types.subtypeOf(type)");
        return wildcardType0;
    }

    public static final WildcardType supertypeOf() {
        Intrinsics.reifiedOperationMarker(6, "T");
        Type type0 = TypesJVMKt.getJavaType(null);
        if(type0 instanceof Class) {
            Class class0 = Util.boxIfPrimitive(((Class)type0));
            Intrinsics.checkNotNullExpressionValue(class0, "Util.boxIfPrimitive(type)");
            type0 = class0;
        }
        WildcardType wildcardType0 = Types.supertypeOf(type0);
        Intrinsics.checkNotNullExpressionValue(wildcardType0, "Types.supertypeOf(type)");
        return wildcardType0;
    }
}

