package kotlin.reflect.jvm.internal.impl.descriptors.runtime.components;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import kotlin.collections.ArraysKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.PrimitiveType;
import kotlin.reflect.jvm.internal.impl.builtins.StandardNames.FqNames;
import kotlin.reflect.jvm.internal.impl.builtins.jvm.JavaToKotlinClassMap;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectClassUtilKt;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationArgumentVisitor;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationArrayArgumentVisitor;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationVisitor;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.MemberVisitor;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.MethodAnnotationVisitor;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.name.SpecialNames;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ClassLiteralValue;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmPrimitiveType;

final class ReflectClassStructure {
    public static final ReflectClassStructure INSTANCE;

    static {
        ReflectClassStructure.INSTANCE = new ReflectClassStructure();
    }

    private final ClassLiteralValue classLiteralValue(Class class0) {
        int v = 0;
        while(class0.isArray()) {
            ++v;
            class0 = class0.getComponentType();
            Intrinsics.checkNotNullExpressionValue(class0, "currentClass.componentType");
        }
        if(class0.isPrimitive()) {
            if(Intrinsics.areEqual(class0, Void.TYPE)) {
                ClassId classId0 = ClassId.topLevel(FqNames.unit.toSafe());
                Intrinsics.checkNotNullExpressionValue(classId0, "topLevel(StandardNames.FqNames.unit.toSafe())");
                return new ClassLiteralValue(classId0, v);
            }
            PrimitiveType primitiveType0 = JvmPrimitiveType.get(class0.getName()).getPrimitiveType();
            Intrinsics.checkNotNullExpressionValue(primitiveType0, "get(currentClass.name).primitiveType");
            if(v > 0) {
                ClassId classId1 = ClassId.topLevel(primitiveType0.getArrayTypeFqName());
                Intrinsics.checkNotNullExpressionValue(classId1, "topLevel(primitiveType.arrayTypeFqName)");
                return new ClassLiteralValue(classId1, v - 1);
            }
            ClassId classId2 = ClassId.topLevel(primitiveType0.getTypeFqName());
            Intrinsics.checkNotNullExpressionValue(classId2, "topLevel(primitiveType.typeFqName)");
            return new ClassLiteralValue(classId2, v);
        }
        ClassId classId3 = ReflectClassUtilKt.getClassId(class0);
        FqName fqName0 = classId3.asSingleFqName();
        Intrinsics.checkNotNullExpressionValue(fqName0, "javaClassId.asSingleFqName()");
        ClassId classId4 = JavaToKotlinClassMap.INSTANCE.mapJavaToKotlin(fqName0);
        if(classId4 != null) {
            classId3 = classId4;
        }
        return new ClassLiteralValue(classId3, v);
    }

    public final void loadClassAnnotations(Class class0, AnnotationVisitor kotlinJvmBinaryClass$AnnotationVisitor0) {
        Intrinsics.checkNotNullParameter(class0, "klass");
        Intrinsics.checkNotNullParameter(kotlinJvmBinaryClass$AnnotationVisitor0, "visitor");
        Annotation[] arr_annotation = class0.getDeclaredAnnotations();
        Intrinsics.checkNotNullExpressionValue(arr_annotation, "klass.declaredAnnotations");
        for(int v = 0; v < arr_annotation.length; ++v) {
            Annotation annotation0 = arr_annotation[v];
            Intrinsics.checkNotNullExpressionValue(annotation0, "annotation");
            this.processAnnotation(kotlinJvmBinaryClass$AnnotationVisitor0, annotation0);
        }
        kotlinJvmBinaryClass$AnnotationVisitor0.visitEnd();
    }

    private final void loadConstructorAnnotations(Class class0, MemberVisitor kotlinJvmBinaryClass$MemberVisitor0) {
        int v1;
        Constructor[] arr_constructor = class0.getDeclaredConstructors();
        Intrinsics.checkNotNullExpressionValue(arr_constructor, "klass.declaredConstructors");
        for(int v = 0; v < arr_constructor.length; v = v1 + 1) {
            Constructor constructor0 = arr_constructor[v];
            Intrinsics.checkNotNullExpressionValue(constructor0, "constructor");
            String s = SignatureSerializer.INSTANCE.constructorDesc(constructor0);
            MethodAnnotationVisitor kotlinJvmBinaryClass$MethodAnnotationVisitor0 = kotlinJvmBinaryClass$MemberVisitor0.visitMethod(SpecialNames.INIT, s);
            if(kotlinJvmBinaryClass$MethodAnnotationVisitor0 == null) {
                v1 = v;
            }
            else {
                Annotation[] arr_annotation = constructor0.getDeclaredAnnotations();
                Intrinsics.checkNotNullExpressionValue(arr_annotation, "constructor.declaredAnnotations");
                for(int v2 = 0; v2 < arr_annotation.length; ++v2) {
                    Annotation annotation0 = arr_annotation[v2];
                    Intrinsics.checkNotNullExpressionValue(annotation0, "annotation");
                    this.processAnnotation(kotlinJvmBinaryClass$MethodAnnotationVisitor0, annotation0);
                }
                Annotation[][] arr2_annotation = constructor0.getParameterAnnotations();
                Intrinsics.checkNotNullExpressionValue(arr2_annotation, "parameterAnnotations");
                Object[] arr_object = arr2_annotation;
                if(arr_object.length != 0) {
                    int v3 = constructor0.getParameterTypes().length - arr_object.length;
                    int v4 = arr_object.length;
                    for(int v5 = 0; v5 < v4; ++v5) {
                        Annotation[] arr_annotation1 = arr2_annotation[v5];
                        Intrinsics.checkNotNullExpressionValue(arr_annotation1, "annotations");
                        for(int v6 = 0; v6 < arr_annotation1.length; ++v6) {
                            Annotation annotation1 = arr_annotation1[v6];
                            Class class1 = JvmClassMappingKt.getJavaClass(JvmClassMappingKt.getAnnotationClass(annotation1));
                            ClassId classId0 = ReflectClassUtilKt.getClassId(class1);
                            Intrinsics.checkNotNullExpressionValue(annotation1, "annotation");
                            AnnotationArgumentVisitor kotlinJvmBinaryClass$AnnotationArgumentVisitor0 = kotlinJvmBinaryClass$MethodAnnotationVisitor0.visitParameterAnnotation(v5 + v3, classId0, new ReflectAnnotationSource(annotation1));
                            if(kotlinJvmBinaryClass$AnnotationArgumentVisitor0 != null) {
                                ReflectClassStructure.INSTANCE.processAnnotationArguments(kotlinJvmBinaryClass$AnnotationArgumentVisitor0, annotation1, class1);
                            }
                        }
                    }
                }
                v1 = v;
                kotlinJvmBinaryClass$MethodAnnotationVisitor0.visitEnd();
            }
        }
    }

    private final void loadFieldAnnotations(Class class0, MemberVisitor kotlinJvmBinaryClass$MemberVisitor0) {
        Field[] arr_field = class0.getDeclaredFields();
        Intrinsics.checkNotNullExpressionValue(arr_field, "klass.declaredFields");
        for(int v = 0; v < arr_field.length; ++v) {
            Field field0 = arr_field[v];
            Name name0 = Name.identifier(field0.getName());
            Intrinsics.checkNotNullExpressionValue(name0, "identifier(field.name)");
            Intrinsics.checkNotNullExpressionValue(field0, "field");
            AnnotationVisitor kotlinJvmBinaryClass$AnnotationVisitor0 = kotlinJvmBinaryClass$MemberVisitor0.visitField(name0, SignatureSerializer.INSTANCE.fieldDesc(field0), null);
            if(kotlinJvmBinaryClass$AnnotationVisitor0 != null) {
                Annotation[] arr_annotation = field0.getDeclaredAnnotations();
                Intrinsics.checkNotNullExpressionValue(arr_annotation, "field.declaredAnnotations");
                for(int v1 = 0; v1 < arr_annotation.length; ++v1) {
                    Annotation annotation0 = arr_annotation[v1];
                    Intrinsics.checkNotNullExpressionValue(annotation0, "annotation");
                    this.processAnnotation(kotlinJvmBinaryClass$AnnotationVisitor0, annotation0);
                }
                kotlinJvmBinaryClass$AnnotationVisitor0.visitEnd();
            }
        }
    }

    private final void loadMethodAnnotations(Class class0, MemberVisitor kotlinJvmBinaryClass$MemberVisitor0) {
        Method[] arr_method = class0.getDeclaredMethods();
        Intrinsics.checkNotNullExpressionValue(arr_method, "klass.declaredMethods");
        for(int v = 0; v < arr_method.length; ++v) {
            Method method0 = arr_method[v];
            Name name0 = Name.identifier(method0.getName());
            Intrinsics.checkNotNullExpressionValue(name0, "identifier(method.name)");
            Intrinsics.checkNotNullExpressionValue(method0, "method");
            MethodAnnotationVisitor kotlinJvmBinaryClass$MethodAnnotationVisitor0 = kotlinJvmBinaryClass$MemberVisitor0.visitMethod(name0, SignatureSerializer.INSTANCE.methodDesc(method0));
            if(kotlinJvmBinaryClass$MethodAnnotationVisitor0 != null) {
                Annotation[] arr_annotation = method0.getDeclaredAnnotations();
                Intrinsics.checkNotNullExpressionValue(arr_annotation, "method.declaredAnnotations");
                for(int v1 = 0; v1 < arr_annotation.length; ++v1) {
                    Annotation annotation0 = arr_annotation[v1];
                    Intrinsics.checkNotNullExpressionValue(annotation0, "annotation");
                    this.processAnnotation(kotlinJvmBinaryClass$MethodAnnotationVisitor0, annotation0);
                }
                Annotation[][] arr2_annotation = method0.getParameterAnnotations();
                Intrinsics.checkNotNullExpressionValue(arr2_annotation, "method.parameterAnnotations");
                Annotation[][] arr2_annotation1 = arr2_annotation;
                for(int v2 = 0; v2 < arr2_annotation1.length; ++v2) {
                    Annotation[] arr_annotation1 = arr2_annotation1[v2];
                    Intrinsics.checkNotNullExpressionValue(arr_annotation1, "annotations");
                    for(int v3 = 0; v3 < arr_annotation1.length; ++v3) {
                        Annotation annotation1 = arr_annotation1[v3];
                        Class class1 = JvmClassMappingKt.getJavaClass(JvmClassMappingKt.getAnnotationClass(annotation1));
                        ClassId classId0 = ReflectClassUtilKt.getClassId(class1);
                        Intrinsics.checkNotNullExpressionValue(annotation1, "annotation");
                        AnnotationArgumentVisitor kotlinJvmBinaryClass$AnnotationArgumentVisitor0 = kotlinJvmBinaryClass$MethodAnnotationVisitor0.visitParameterAnnotation(v2, classId0, new ReflectAnnotationSource(annotation1));
                        if(kotlinJvmBinaryClass$AnnotationArgumentVisitor0 != null) {
                            ReflectClassStructure.INSTANCE.processAnnotationArguments(kotlinJvmBinaryClass$AnnotationArgumentVisitor0, annotation1, class1);
                        }
                    }
                }
                kotlinJvmBinaryClass$MethodAnnotationVisitor0.visitEnd();
            }
        }
    }

    private final void processAnnotation(AnnotationVisitor kotlinJvmBinaryClass$AnnotationVisitor0, Annotation annotation0) {
        Class class0 = JvmClassMappingKt.getJavaClass(JvmClassMappingKt.getAnnotationClass(annotation0));
        AnnotationArgumentVisitor kotlinJvmBinaryClass$AnnotationArgumentVisitor0 = kotlinJvmBinaryClass$AnnotationVisitor0.visitAnnotation(ReflectClassUtilKt.getClassId(class0), new ReflectAnnotationSource(annotation0));
        if(kotlinJvmBinaryClass$AnnotationArgumentVisitor0 != null) {
            ReflectClassStructure.INSTANCE.processAnnotationArguments(kotlinJvmBinaryClass$AnnotationArgumentVisitor0, annotation0, class0);
        }
    }

    private final void processAnnotationArgumentValue(AnnotationArgumentVisitor kotlinJvmBinaryClass$AnnotationArgumentVisitor0, Name name0, Object object0) {
        Class class0 = object0.getClass();
        if(Intrinsics.areEqual(class0, Class.class)) {
            Intrinsics.checkNotNull(object0, "null cannot be cast to non-null type java.lang.Class<*>");
            kotlinJvmBinaryClass$AnnotationArgumentVisitor0.visitClassLiteral(name0, this.classLiteralValue(((Class)object0)));
            return;
        }
        if(ReflectKotlinClassKt.access$getTYPES_ELIGIBLE_FOR_SIMPLE_VISIT$p().contains(class0)) {
            kotlinJvmBinaryClass$AnnotationArgumentVisitor0.visit(name0, object0);
            return;
        }
        if(ReflectClassUtilKt.isEnumClassOrSpecializedEnumEntryClass(class0)) {
            if(!class0.isEnum()) {
                class0 = class0.getEnclosingClass();
            }
            Intrinsics.checkNotNullExpressionValue(class0, "if (clazz.isEnum) clazz else clazz.enclosingClass");
            ClassId classId0 = ReflectClassUtilKt.getClassId(class0);
            Intrinsics.checkNotNull(object0, "null cannot be cast to non-null type kotlin.Enum<*>");
            Name name1 = Name.identifier(((Enum)object0).name());
            Intrinsics.checkNotNullExpressionValue(name1, "identifier((value as Enum<*>).name)");
            kotlinJvmBinaryClass$AnnotationArgumentVisitor0.visitEnum(name0, classId0, name1);
            return;
        }
        if(Annotation.class.isAssignableFrom(class0)) {
            Class[] arr_class = class0.getInterfaces();
            Intrinsics.checkNotNullExpressionValue(arr_class, "clazz.interfaces");
            Class class1 = (Class)ArraysKt.single(arr_class);
            Intrinsics.checkNotNullExpressionValue(class1, "annotationClass");
            AnnotationArgumentVisitor kotlinJvmBinaryClass$AnnotationArgumentVisitor1 = kotlinJvmBinaryClass$AnnotationArgumentVisitor0.visitAnnotation(name0, ReflectClassUtilKt.getClassId(class1));
            if(kotlinJvmBinaryClass$AnnotationArgumentVisitor1 == null) {
                return;
            }
            Intrinsics.checkNotNull(object0, "null cannot be cast to non-null type kotlin.Annotation");
            this.processAnnotationArguments(kotlinJvmBinaryClass$AnnotationArgumentVisitor1, ((Annotation)object0), class1);
            return;
        }
        if(!class0.isArray()) {
            throw new UnsupportedOperationException("Unsupported annotation argument value (" + class0 + "): " + object0);
        }
        AnnotationArrayArgumentVisitor kotlinJvmBinaryClass$AnnotationArrayArgumentVisitor0 = kotlinJvmBinaryClass$AnnotationArgumentVisitor0.visitArray(name0);
        if(kotlinJvmBinaryClass$AnnotationArrayArgumentVisitor0 == null) {
            return;
        }
        Class class2 = class0.getComponentType();
        int v = 0;
        if(class2.isEnum()) {
            Intrinsics.checkNotNullExpressionValue(class2, "componentType");
            ClassId classId1 = ReflectClassUtilKt.getClassId(class2);
            Intrinsics.checkNotNull(object0, "null cannot be cast to non-null type kotlin.Array<*>");
            while(v < ((Object[])object0).length) {
                Object object1 = ((Object[])object0)[v];
                Intrinsics.checkNotNull(object1, "null cannot be cast to non-null type kotlin.Enum<*>");
                Name name2 = Name.identifier(((Enum)object1).name());
                Intrinsics.checkNotNullExpressionValue(name2, "identifier((element as Enum<*>).name)");
                kotlinJvmBinaryClass$AnnotationArrayArgumentVisitor0.visitEnum(classId1, name2);
                ++v;
            }
        }
        else if(Intrinsics.areEqual(class2, Class.class)) {
            Intrinsics.checkNotNull(object0, "null cannot be cast to non-null type kotlin.Array<*>");
            while(v < ((Object[])object0).length) {
                Object object2 = ((Object[])object0)[v];
                Intrinsics.checkNotNull(object2, "null cannot be cast to non-null type java.lang.Class<*>");
                kotlinJvmBinaryClass$AnnotationArrayArgumentVisitor0.visitClassLiteral(this.classLiteralValue(((Class)object2)));
                ++v;
            }
        }
        else if(Annotation.class.isAssignableFrom(class2)) {
            Intrinsics.checkNotNull(object0, "null cannot be cast to non-null type kotlin.Array<*>");
            while(v < ((Object[])object0).length) {
                Object object3 = ((Object[])object0)[v];
                Intrinsics.checkNotNullExpressionValue(class2, "componentType");
                AnnotationArgumentVisitor kotlinJvmBinaryClass$AnnotationArgumentVisitor2 = kotlinJvmBinaryClass$AnnotationArrayArgumentVisitor0.visitAnnotation(ReflectClassUtilKt.getClassId(class2));
                if(kotlinJvmBinaryClass$AnnotationArgumentVisitor2 != null) {
                    Intrinsics.checkNotNull(object3, "null cannot be cast to non-null type kotlin.Annotation");
                    this.processAnnotationArguments(kotlinJvmBinaryClass$AnnotationArgumentVisitor2, ((Annotation)object3), class2);
                }
                ++v;
            }
        }
        else {
            Intrinsics.checkNotNull(object0, "null cannot be cast to non-null type kotlin.Array<*>");
            while(v < ((Object[])object0).length) {
                kotlinJvmBinaryClass$AnnotationArrayArgumentVisitor0.visit(((Object[])object0)[v]);
                ++v;
            }
        }
        kotlinJvmBinaryClass$AnnotationArrayArgumentVisitor0.visitEnd();
    }

    private final void processAnnotationArguments(AnnotationArgumentVisitor kotlinJvmBinaryClass$AnnotationArgumentVisitor0, Annotation annotation0, Class class0) {
        Object object0;
        Method[] arr_method = class0.getDeclaredMethods();
        Intrinsics.checkNotNullExpressionValue(arr_method, "annotationType.declaredMethods");
        for(int v = 0; v < arr_method.length; ++v) {
            Method method0 = arr_method[v];
            try {
                object0 = method0.invoke(annotation0, null);
                Intrinsics.checkNotNull(object0);
            }
            catch(IllegalAccessException unused_ex) {
                continue;
            }
            Name name0 = Name.identifier(method0.getName());
            Intrinsics.checkNotNullExpressionValue(name0, "identifier(method.name)");
            this.processAnnotationArgumentValue(kotlinJvmBinaryClass$AnnotationArgumentVisitor0, name0, object0);
        }
        kotlinJvmBinaryClass$AnnotationArgumentVisitor0.visitEnd();
    }

    public final void visitMembers(Class class0, MemberVisitor kotlinJvmBinaryClass$MemberVisitor0) {
        Intrinsics.checkNotNullParameter(class0, "klass");
        Intrinsics.checkNotNullParameter(kotlinJvmBinaryClass$MemberVisitor0, "memberVisitor");
        this.loadMethodAnnotations(class0, kotlinJvmBinaryClass$MemberVisitor0);
        this.loadConstructorAnnotations(class0, kotlinJvmBinaryClass$MemberVisitor0);
        this.loadFieldAnnotations(class0, kotlinJvmBinaryClass$MemberVisitor0);
    }
}

