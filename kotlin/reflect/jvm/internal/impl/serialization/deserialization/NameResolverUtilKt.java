package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.Name;

public final class NameResolverUtilKt {
    public static final ClassId getClassId(NameResolver nameResolver0, int v) {
        Intrinsics.checkNotNullParameter(nameResolver0, "<this>");
        ClassId classId0 = ClassId.fromString(nameResolver0.getQualifiedClassName(v), nameResolver0.isLocalClassName(v));
        Intrinsics.checkNotNullExpressionValue(classId0, "fromString(getQualifiedC… isLocalClassName(index))");
        return classId0;
    }

    public static final Name getName(NameResolver nameResolver0, int v) {
        Intrinsics.checkNotNullParameter(nameResolver0, "<this>");
        Name name0 = Name.guessByFirstCharacter(nameResolver0.getString(v));
        Intrinsics.checkNotNullExpressionValue(name0, "guessByFirstCharacter(getString(index))");
        return name0;
    }
}

