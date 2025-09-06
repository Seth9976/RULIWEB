package kotlin.reflect.jvm.internal.impl.load.java;

import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.util.capitalizeDecapitalize.CapitalizeDecapitalizeKt;
import kotlin.text.StringsKt;

public final class JvmAbi {
    public static final JvmAbi INSTANCE;
    public static final ClassId JVM_FIELD_ANNOTATION_CLASS_ID;
    public static final FqName JVM_FIELD_ANNOTATION_FQ_NAME;
    private static final ClassId REFLECTION_FACTORY_IMPL;
    private static final ClassId REPEATABLE_ANNOTATION_CONTAINER_META_ANNOTATION;

    static {
        JvmAbi.INSTANCE = new JvmAbi();
        FqName fqName0 = new FqName("kotlin.jvm.JvmField");
        JvmAbi.JVM_FIELD_ANNOTATION_FQ_NAME = fqName0;
        ClassId classId0 = ClassId.topLevel(fqName0);
        Intrinsics.checkNotNullExpressionValue(classId0, "topLevel(JVM_FIELD_ANNOTATION_FQ_NAME)");
        JvmAbi.JVM_FIELD_ANNOTATION_CLASS_ID = classId0;
        ClassId classId1 = ClassId.topLevel(new FqName("kotlin.reflect.jvm.internal.ReflectionFactoryImpl"));
        Intrinsics.checkNotNullExpressionValue(classId1, "topLevel(FqName(\"kotlin.….ReflectionFactoryImpl\"))");
        JvmAbi.REFLECTION_FACTORY_IMPL = classId1;
        ClassId classId2 = ClassId.fromString("kotlin/jvm/internal/RepeatableContainer");
        Intrinsics.checkNotNullExpressionValue(classId2, "fromString(\"kotlin/jvm/i…nal/RepeatableContainer\")");
        JvmAbi.REPEATABLE_ANNOTATION_CONTAINER_META_ANNOTATION = classId2;
    }

    public final ClassId getREPEATABLE_ANNOTATION_CONTAINER_META_ANNOTATION() {
        return JvmAbi.REPEATABLE_ANNOTATION_CONTAINER_META_ANNOTATION;
    }

    @JvmStatic
    public static final String getterName(String s) {
        Intrinsics.checkNotNullParameter(s, "propertyName");
        return JvmAbi.startsWithIsPrefix(s) ? s : "get" + CapitalizeDecapitalizeKt.capitalizeAsciiOnly(s);
    }

    @JvmStatic
    public static final boolean isGetterName(String s) {
        Intrinsics.checkNotNullParameter(s, "name");
        return StringsKt.startsWith$default(s, "get", false, 2, null) || StringsKt.startsWith$default(s, "is", false, 2, null);
    }

    @JvmStatic
    public static final boolean isSetterName(String s) {
        Intrinsics.checkNotNullParameter(s, "name");
        return StringsKt.startsWith$default(s, "set", false, 2, null);
    }

    @JvmStatic
    public static final String setterName(String s) {
        String s1;
        Intrinsics.checkNotNullParameter(s, "propertyName");
        StringBuilder stringBuilder0 = new StringBuilder("set");
        if(JvmAbi.startsWithIsPrefix(s)) {
            s1 = s.substring(2);
            Intrinsics.checkNotNullExpressionValue(s1, "this as java.lang.String).substring(startIndex)");
        }
        else {
            s1 = CapitalizeDecapitalizeKt.capitalizeAsciiOnly(s);
        }
        stringBuilder0.append(s1);
        return stringBuilder0.toString();
    }

    @JvmStatic
    public static final boolean startsWithIsPrefix(String s) {
        Intrinsics.checkNotNullParameter(s, "name");
        if(!StringsKt.startsWith$default(s, "is", false, 2, null)) {
            return false;
        }
        if(s.length() == 2) {
            return false;
        }
        int v = s.charAt(2);
        return Intrinsics.compare(97, v) > 0 || Intrinsics.compare(v, 0x7A) > 0;
    }
}

