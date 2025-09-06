package kotlin.reflect.jvm.internal.impl.descriptors.annotations;

import java.util.Map;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.builtins.StandardNames.FqNames;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.constants.AnnotationValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ArrayValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.EnumValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.StringValue;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.Variance;

public final class AnnotationUtilKt {
    private static final Name DEPRECATED_LEVEL_NAME;
    private static final Name DEPRECATED_MESSAGE_NAME;
    private static final Name DEPRECATED_REPLACE_WITH_NAME;
    private static final Name REPLACE_WITH_EXPRESSION_NAME;
    private static final Name REPLACE_WITH_IMPORTS_NAME;

    static {
        Name name0 = Name.identifier("message");
        Intrinsics.checkNotNullExpressionValue(name0, "identifier(\"message\")");
        AnnotationUtilKt.DEPRECATED_MESSAGE_NAME = name0;
        Name name1 = Name.identifier("replaceWith");
        Intrinsics.checkNotNullExpressionValue(name1, "identifier(\"replaceWith\")");
        AnnotationUtilKt.DEPRECATED_REPLACE_WITH_NAME = name1;
        Name name2 = Name.identifier("level");
        Intrinsics.checkNotNullExpressionValue(name2, "identifier(\"level\")");
        AnnotationUtilKt.DEPRECATED_LEVEL_NAME = name2;
        Name name3 = Name.identifier("expression");
        Intrinsics.checkNotNullExpressionValue(name3, "identifier(\"expression\")");
        AnnotationUtilKt.REPLACE_WITH_EXPRESSION_NAME = name3;
        Name name4 = Name.identifier("imports");
        Intrinsics.checkNotNullExpressionValue(name4, "identifier(\"imports\")");
        AnnotationUtilKt.REPLACE_WITH_IMPORTS_NAME = name4;
    }

    public static final AnnotationDescriptor createDeprecatedAnnotation(KotlinBuiltIns kotlinBuiltIns0, String s, String s1, String s2) {
        Intrinsics.checkNotNullParameter(kotlinBuiltIns0, "<this>");
        Intrinsics.checkNotNullParameter(s, "message");
        Intrinsics.checkNotNullParameter(s1, "replaceWith");
        Intrinsics.checkNotNullParameter(s2, "level");
        Pair[] arr_pair = new Pair[2];
        StringValue stringValue0 = new StringValue(s1);
        arr_pair[0] = TuplesKt.to(AnnotationUtilKt.REPLACE_WITH_EXPRESSION_NAME, stringValue0);
        ArrayValue arrayValue0 = new ArrayValue(CollectionsKt.emptyList(), new Function1(kotlinBuiltIns0) {
            final KotlinBuiltIns $this_createDeprecatedAnnotation;

            {
                this.$this_createDeprecatedAnnotation = kotlinBuiltIns0;
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((ModuleDescriptor)object0));
            }

            public final KotlinType invoke(ModuleDescriptor moduleDescriptor0) {
                Intrinsics.checkNotNullParameter(moduleDescriptor0, "module");
                KotlinBuiltIns kotlinBuiltIns0 = moduleDescriptor0.getBuiltIns();
                KotlinType kotlinType0 = this.$this_createDeprecatedAnnotation.getStringType();
                SimpleType simpleType0 = kotlinBuiltIns0.getArrayType(Variance.INVARIANT, kotlinType0);
                Intrinsics.checkNotNullExpressionValue(simpleType0, "module.builtIns.getArray…ce.INVARIANT, stringType)");
                return simpleType0;
            }
        });
        arr_pair[1] = TuplesKt.to(AnnotationUtilKt.REPLACE_WITH_IMPORTS_NAME, arrayValue0);
        Map map0 = MapsKt.mapOf(arr_pair);
        BuiltInAnnotationDescriptor builtInAnnotationDescriptor0 = new BuiltInAnnotationDescriptor(kotlinBuiltIns0, FqNames.replaceWith, map0);
        Pair[] arr_pair1 = new Pair[3];
        StringValue stringValue1 = new StringValue(s);
        arr_pair1[0] = TuplesKt.to(AnnotationUtilKt.DEPRECATED_MESSAGE_NAME, stringValue1);
        AnnotationValue annotationValue0 = new AnnotationValue(builtInAnnotationDescriptor0);
        arr_pair1[1] = TuplesKt.to(AnnotationUtilKt.DEPRECATED_REPLACE_WITH_NAME, annotationValue0);
        ClassId classId0 = ClassId.topLevel(FqNames.deprecationLevel);
        Intrinsics.checkNotNullExpressionValue(classId0, "topLevel(StandardNames.FqNames.deprecationLevel)");
        Name name0 = Name.identifier(s2);
        Intrinsics.checkNotNullExpressionValue(name0, "identifier(level)");
        EnumValue enumValue0 = new EnumValue(classId0, name0);
        arr_pair1[2] = TuplesKt.to(AnnotationUtilKt.DEPRECATED_LEVEL_NAME, enumValue0);
        Map map1 = MapsKt.mapOf(arr_pair1);
        return new BuiltInAnnotationDescriptor(kotlinBuiltIns0, FqNames.deprecated, map1);
    }

    public static AnnotationDescriptor createDeprecatedAnnotation$default(KotlinBuiltIns kotlinBuiltIns0, String s, String s1, String s2, int v, Object object0) {
        if((v & 2) != 0) {
            s1 = "";
        }
        if((v & 4) != 0) {
            s2 = "WARNING";
        }
        return AnnotationUtilKt.createDeprecatedAnnotation(kotlinBuiltIns0, s, s1, s2);
    }
}

