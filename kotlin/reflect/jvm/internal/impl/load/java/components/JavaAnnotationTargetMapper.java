package kotlin.reflect.jvm.internal.impl.load.java.components;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.builtins.StandardNames.FqNames;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.KotlinRetention;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.KotlinTarget;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotationArgument;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaEnumValueAnnotationArgument;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ArrayValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.EnumValue;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.error.ErrorTypeKind;
import kotlin.reflect.jvm.internal.impl.types.error.ErrorUtils;

public final class JavaAnnotationTargetMapper {
    public static final JavaAnnotationTargetMapper INSTANCE;
    private static final Map retentionNameList;
    private static final Map targetNameLists;

    static {
        JavaAnnotationTargetMapper.INSTANCE = new JavaAnnotationTargetMapper();
        JavaAnnotationTargetMapper.targetNameLists = MapsKt.mapOf(new Pair[]{TuplesKt.to("PACKAGE", EnumSet.noneOf(KotlinTarget.class)), TuplesKt.to("TYPE", EnumSet.of(KotlinTarget.CLASS, KotlinTarget.FILE)), TuplesKt.to("ANNOTATION_TYPE", EnumSet.of(KotlinTarget.ANNOTATION_CLASS)), TuplesKt.to("TYPE_PARAMETER", EnumSet.of(KotlinTarget.TYPE_PARAMETER)), TuplesKt.to("FIELD", EnumSet.of(KotlinTarget.FIELD)), TuplesKt.to("LOCAL_VARIABLE", EnumSet.of(KotlinTarget.LOCAL_VARIABLE)), TuplesKt.to("PARAMETER", EnumSet.of(KotlinTarget.VALUE_PARAMETER)), TuplesKt.to("CONSTRUCTOR", EnumSet.of(KotlinTarget.CONSTRUCTOR)), TuplesKt.to("METHOD", EnumSet.of(KotlinTarget.FUNCTION, KotlinTarget.PROPERTY_GETTER, KotlinTarget.PROPERTY_SETTER)), TuplesKt.to("TYPE_USE", EnumSet.of(KotlinTarget.TYPE))});
        JavaAnnotationTargetMapper.retentionNameList = MapsKt.mapOf(new Pair[]{TuplesKt.to("RUNTIME", KotlinRetention.RUNTIME), TuplesKt.to("CLASS", KotlinRetention.BINARY), TuplesKt.to("SOURCE", KotlinRetention.SOURCE)});
    }

    public final ConstantValue mapJavaRetentionArgument$descriptors_jvm(JavaAnnotationArgument javaAnnotationArgument0) {
        JavaEnumValueAnnotationArgument javaEnumValueAnnotationArgument0 = javaAnnotationArgument0 instanceof JavaEnumValueAnnotationArgument ? ((JavaEnumValueAnnotationArgument)javaAnnotationArgument0) : null;
        if(javaEnumValueAnnotationArgument0 != null) {
            Name name0 = javaEnumValueAnnotationArgument0.getEntryName();
            String s = name0 == null ? null : name0.asString();
            KotlinRetention kotlinRetention0 = (KotlinRetention)JavaAnnotationTargetMapper.retentionNameList.get(s);
            if(kotlinRetention0 != null) {
                ClassId classId0 = ClassId.topLevel(FqNames.annotationRetention);
                Intrinsics.checkNotNullExpressionValue(classId0, "topLevel(StandardNames.F…ames.annotationRetention)");
                Name name1 = Name.identifier(kotlinRetention0.name());
                Intrinsics.checkNotNullExpressionValue(name1, "identifier(retention.name)");
                return new EnumValue(classId0, name1);
            }
        }
        return null;
    }

    public final Set mapJavaTargetArgumentByName(String s) {
        EnumSet enumSet0 = (EnumSet)JavaAnnotationTargetMapper.targetNameLists.get(s);
        return enumSet0 != null ? enumSet0 : SetsKt.emptySet();
    }

    public final ConstantValue mapJavaTargetArguments$descriptors_jvm(List list0) {
        Intrinsics.checkNotNullParameter(list0, "arguments");
        Collection collection0 = new ArrayList();
        for(Object object0: list0) {
            if(object0 instanceof JavaEnumValueAnnotationArgument) {
                collection0.add(object0);
            }
        }
        Collection collection1 = new ArrayList();
        for(Object object1: ((List)collection0)) {
            Name name0 = ((JavaEnumValueAnnotationArgument)object1).getEntryName();
            String s = name0 == null ? null : name0.asString();
            CollectionsKt.addAll(collection1, JavaAnnotationTargetMapper.INSTANCE.mapJavaTargetArgumentByName(s));
        }
        ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(((List)collection1), 10));
        for(Object object2: ((List)collection1)) {
            ClassId classId0 = ClassId.topLevel(FqNames.annotationTarget);
            Intrinsics.checkNotNullExpressionValue(classId0, "topLevel(StandardNames.FqNames.annotationTarget)");
            Name name1 = Name.identifier(((KotlinTarget)object2).name());
            Intrinsics.checkNotNullExpressionValue(name1, "identifier(kotlinTarget.name)");
            arrayList0.add(new EnumValue(classId0, name1));
        }
        return new ArrayValue(arrayList0, kotlin.reflect.jvm.internal.impl.load.java.components.JavaAnnotationTargetMapper.mapJavaTargetArguments.1.INSTANCE);

        final class kotlin.reflect.jvm.internal.impl.load.java.components.JavaAnnotationTargetMapper.mapJavaTargetArguments.1 extends Lambda implements Function1 {
            public static final kotlin.reflect.jvm.internal.impl.load.java.components.JavaAnnotationTargetMapper.mapJavaTargetArguments.1 INSTANCE;

            static {
                kotlin.reflect.jvm.internal.impl.load.java.components.JavaAnnotationTargetMapper.mapJavaTargetArguments.1.INSTANCE = new kotlin.reflect.jvm.internal.impl.load.java.components.JavaAnnotationTargetMapper.mapJavaTargetArguments.1();
            }

            kotlin.reflect.jvm.internal.impl.load.java.components.JavaAnnotationTargetMapper.mapJavaTargetArguments.1() {
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((ModuleDescriptor)object0));
            }

            public final KotlinType invoke(ModuleDescriptor moduleDescriptor0) {
                Intrinsics.checkNotNullParameter(moduleDescriptor0, "module");
                ValueParameterDescriptor valueParameterDescriptor0 = DescriptorResolverUtils.getAnnotationParameterByName(JavaAnnotationMapper.INSTANCE.getTARGET_ANNOTATION_ALLOWED_TARGETS$descriptors_jvm(), moduleDescriptor0.getBuiltIns().getBuiltInClassByFqName(FqNames.target));
                KotlinType kotlinType0 = valueParameterDescriptor0 == null ? null : valueParameterDescriptor0.getType();
                return kotlinType0 == null ? ErrorUtils.createErrorType(ErrorTypeKind.UNMAPPED_ANNOTATION_TARGET_TYPE, new String[0]) : kotlinType0;
            }
        }

    }
}

