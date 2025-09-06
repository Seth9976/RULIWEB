package kotlin.reflect.jvm.internal.impl.types;

import kotlin.reflect.jvm.internal.impl.builtins.PrimitiveType;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe;
import kotlin.reflect.jvm.internal.impl.types.model.KotlinTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeConstructorMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeParameterMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext;

public interface TypeSystemCommonBackendContext extends TypeSystemContext {
    FqNameUnsafe getClassFqNameUnsafe(TypeConstructorMarker arg1);

    PrimitiveType getPrimitiveArrayType(TypeConstructorMarker arg1);

    PrimitiveType getPrimitiveType(TypeConstructorMarker arg1);

    KotlinTypeMarker getRepresentativeUpperBound(TypeParameterMarker arg1);

    KotlinTypeMarker getUnsubstitutedUnderlyingType(KotlinTypeMarker arg1);

    boolean hasAnnotation(KotlinTypeMarker arg1, FqName arg2);

    boolean isInlineClass(TypeConstructorMarker arg1);

    boolean isUnderKotlinPackage(TypeConstructorMarker arg1);

    KotlinTypeMarker makeNullable(KotlinTypeMarker arg1);
}

