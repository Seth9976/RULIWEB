package kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SpreadBuilder;
import kotlin.reflect.KDeclarationContainer;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibilities.Private;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibilities.Public;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibility;
import kotlin.reflect.jvm.internal.impl.descriptors.java.JavaVisibilities.PackageVisibility;
import kotlin.reflect.jvm.internal.impl.descriptors.java.JavaVisibilities.ProtectedAndPackage;
import kotlin.reflect.jvm.internal.impl.descriptors.java.JavaVisibilities.ProtectedStaticVisibility;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotation;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass;
import kotlin.reflect.jvm.internal.impl.load.java.structure.LightClassOriginKind;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.sequences.SequencesKt;

public final class ReflectJavaClass extends ReflectJavaElement implements ReflectJavaAnnotationOwner, ReflectJavaModifierListOwner, JavaClass {
    private final Class klass;

    public ReflectJavaClass(Class class0) {
        Intrinsics.checkNotNullParameter(class0, "klass");
        super();
        this.klass = class0;
    }

    // 去混淆评级： 低(20)
    @Override
    public boolean equals(Object object0) {
        return object0 instanceof ReflectJavaClass && Intrinsics.areEqual(this.klass, ((ReflectJavaClass)object0).klass);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaAnnotationOwner
    public ReflectJavaAnnotation findAnnotation(FqName fqName0) {
        Intrinsics.checkNotNullParameter(fqName0, "fqName");
        AnnotatedElement annotatedElement0 = this.getElement();
        if(annotatedElement0 != null) {
            Annotation[] arr_annotation = annotatedElement0.getDeclaredAnnotations();
            return arr_annotation == null ? null : ReflectJavaAnnotationOwnerKt.findAnnotation(arr_annotation, fqName0);
        }
        return null;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotationOwner
    public JavaAnnotation findAnnotation(FqName fqName0) {
        return this.findAnnotation(fqName0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotationOwner
    public Collection getAnnotations() {
        return this.getAnnotations();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaAnnotationOwner
    public List getAnnotations() {
        AnnotatedElement annotatedElement0 = this.getElement();
        if(annotatedElement0 != null) {
            Annotation[] arr_annotation = annotatedElement0.getDeclaredAnnotations();
            if(arr_annotation != null) {
                List list0 = ReflectJavaAnnotationOwnerKt.getAnnotations(arr_annotation);
                return list0 == null ? CollectionsKt.emptyList() : list0;
            }
        }
        return CollectionsKt.emptyList();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass
    public Collection getConstructors() {
        return this.getConstructors();
    }

    public List getConstructors() {
        Constructor[] arr_constructor = this.klass.getDeclaredConstructors();
        Intrinsics.checkNotNullExpressionValue(arr_constructor, "klass.declaredConstructors");
        return SequencesKt.toList(SequencesKt.map(SequencesKt.filterNot(ArraysKt.asSequence(arr_constructor), kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaClass.constructors.1.INSTANCE), kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaClass.constructors.2.INSTANCE));

        final class kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaClass.constructors.1 extends FunctionReference implements Function1 {
            public static final kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaClass.constructors.1 INSTANCE;

            static {
                kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaClass.constructors.1.INSTANCE = new kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaClass.constructors.1();
            }

            kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaClass.constructors.1() {
                super(1);
            }

            @Override  // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
            public final String getName() {
                return "isSynthetic";
            }

            @Override  // kotlin.jvm.internal.CallableReference
            public final KDeclarationContainer getOwner() {
                return Reflection.getOrCreateKotlinClass(Member.class);
            }

            @Override  // kotlin.jvm.internal.CallableReference
            public final String getSignature() {
                return "isSynthetic()Z";
            }

            public final Boolean invoke(Member member0) {
                Intrinsics.checkNotNullParameter(member0, "p0");
                return Boolean.valueOf(member0.isSynthetic());
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((Member)object0));
            }
        }


        final class kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaClass.constructors.2 extends FunctionReference implements Function1 {
            public static final kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaClass.constructors.2 INSTANCE;

            static {
                kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaClass.constructors.2.INSTANCE = new kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaClass.constructors.2();
            }

            kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaClass.constructors.2() {
                super(1);
            }

            @Override  // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
            public final String getName() {
                return "<init>";
            }

            @Override  // kotlin.jvm.internal.CallableReference
            public final KDeclarationContainer getOwner() {
                return Reflection.getOrCreateKotlinClass(ReflectJavaConstructor.class);
            }

            @Override  // kotlin.jvm.internal.CallableReference
            public final String getSignature() {
                return "<init>(Ljava/lang/reflect/Constructor;)V";
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((Constructor)object0));
            }

            public final ReflectJavaConstructor invoke(Constructor constructor0) {
                Intrinsics.checkNotNullParameter(constructor0, "p0");
                return new ReflectJavaConstructor(constructor0);
            }
        }

    }

    public Class getElement() {
        return this.klass;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaAnnotationOwner
    public AnnotatedElement getElement() {
        return this.getElement();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass
    public Collection getFields() {
        return this.getFields();
    }

    public List getFields() {
        Field[] arr_field = this.klass.getDeclaredFields();
        Intrinsics.checkNotNullExpressionValue(arr_field, "klass.declaredFields");
        return SequencesKt.toList(SequencesKt.map(SequencesKt.filterNot(ArraysKt.asSequence(arr_field), kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaClass.fields.1.INSTANCE), kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaClass.fields.2.INSTANCE));

        final class kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaClass.fields.1 extends FunctionReference implements Function1 {
            public static final kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaClass.fields.1 INSTANCE;

            static {
                kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaClass.fields.1.INSTANCE = new kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaClass.fields.1();
            }

            kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaClass.fields.1() {
                super(1);
            }

            @Override  // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
            public final String getName() {
                return "isSynthetic";
            }

            @Override  // kotlin.jvm.internal.CallableReference
            public final KDeclarationContainer getOwner() {
                return Reflection.getOrCreateKotlinClass(Member.class);
            }

            @Override  // kotlin.jvm.internal.CallableReference
            public final String getSignature() {
                return "isSynthetic()Z";
            }

            public final Boolean invoke(Member member0) {
                Intrinsics.checkNotNullParameter(member0, "p0");
                return Boolean.valueOf(member0.isSynthetic());
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((Member)object0));
            }
        }


        final class kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaClass.fields.2 extends FunctionReference implements Function1 {
            public static final kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaClass.fields.2 INSTANCE;

            static {
                kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaClass.fields.2.INSTANCE = new kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaClass.fields.2();
            }

            kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaClass.fields.2() {
                super(1);
            }

            @Override  // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
            public final String getName() {
                return "<init>";
            }

            @Override  // kotlin.jvm.internal.CallableReference
            public final KDeclarationContainer getOwner() {
                return Reflection.getOrCreateKotlinClass(ReflectJavaField.class);
            }

            @Override  // kotlin.jvm.internal.CallableReference
            public final String getSignature() {
                return "<init>(Ljava/lang/reflect/Field;)V";
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((Field)object0));
            }

            public final ReflectJavaField invoke(Field field0) {
                Intrinsics.checkNotNullParameter(field0, "p0");
                return new ReflectJavaField(field0);
            }
        }

    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass
    public FqName getFqName() {
        FqName fqName0 = ReflectClassUtilKt.getClassId(this.klass).asSingleFqName();
        Intrinsics.checkNotNullExpressionValue(fqName0, "klass.classId.asSingleFqName()");
        return fqName0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass
    public Collection getInnerClassNames() {
        return this.getInnerClassNames();
    }

    public List getInnerClassNames() {
        Class[] arr_class = this.klass.getDeclaredClasses();
        Intrinsics.checkNotNullExpressionValue(arr_class, "klass.declaredClasses");
        return SequencesKt.toList(SequencesKt.mapNotNull(SequencesKt.filterNot(ArraysKt.asSequence(arr_class), kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaClass.innerClassNames.1.INSTANCE), kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaClass.innerClassNames.2.INSTANCE));

        final class kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaClass.innerClassNames.1 extends Lambda implements Function1 {
            public static final kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaClass.innerClassNames.1 INSTANCE;

            static {
                kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaClass.innerClassNames.1.INSTANCE = new kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaClass.innerClassNames.1();
            }

            kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaClass.innerClassNames.1() {
                super(1);
            }

            public final Boolean invoke(Class class0) {
                String s = class0.getSimpleName();
                Intrinsics.checkNotNullExpressionValue(s, "it.simpleName");
                return s.length() == 0;
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((Class)object0));
            }
        }


        final class kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaClass.innerClassNames.2 extends Lambda implements Function1 {
            public static final kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaClass.innerClassNames.2 INSTANCE;

            static {
                kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaClass.innerClassNames.2.INSTANCE = new kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaClass.innerClassNames.2();
            }

            kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaClass.innerClassNames.2() {
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((Class)object0));
            }

            public final Name invoke(Class class0) {
                String s = class0.getSimpleName();
                if(!Name.isValidIdentifier(s)) {
                    s = null;
                }
                return s == null ? null : Name.identifier(s);
            }
        }

    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass
    public LightClassOriginKind getLightClassOriginKind() {
        return null;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass
    public Collection getMethods() {
        return this.getMethods();
    }

    public List getMethods() {
        Method[] arr_method = this.klass.getDeclaredMethods();
        Intrinsics.checkNotNullExpressionValue(arr_method, "klass.declaredMethods");
        return SequencesKt.toList(SequencesKt.map(SequencesKt.filter(ArraysKt.asSequence(arr_method), new Function1() {
            {
                ReflectJavaClass.this = reflectJavaClass0;
                super(1);
            }

            public final Boolean invoke(Method method0) {
                boolean z = false;
                if(!method0.isSynthetic()) {
                    if(ReflectJavaClass.this.isEnum()) {
                        Intrinsics.checkNotNullExpressionValue(method0, "method");
                        return ReflectJavaClass.this.isEnumValuesOrValueOf(method0) ? false : true;
                    }
                    z = true;
                }
                return Boolean.valueOf(z);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((Method)object0));
            }
        }), kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaClass.methods.2.INSTANCE));

        final class kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaClass.methods.2 extends FunctionReference implements Function1 {
            public static final kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaClass.methods.2 INSTANCE;

            static {
                kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaClass.methods.2.INSTANCE = new kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaClass.methods.2();
            }

            kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaClass.methods.2() {
                super(1);
            }

            @Override  // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
            public final String getName() {
                return "<init>";
            }

            @Override  // kotlin.jvm.internal.CallableReference
            public final KDeclarationContainer getOwner() {
                return Reflection.getOrCreateKotlinClass(ReflectJavaMethod.class);
            }

            @Override  // kotlin.jvm.internal.CallableReference
            public final String getSignature() {
                return "<init>(Ljava/lang/reflect/Method;)V";
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((Method)object0));
            }

            public final ReflectJavaMethod invoke(Method method0) {
                Intrinsics.checkNotNullParameter(method0, "p0");
                return new ReflectJavaMethod(method0);
            }
        }

    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaModifierListOwner
    public int getModifiers() {
        return this.klass.getModifiers();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaNamedElement
    public Name getName() {
        Name name0 = Name.identifier(this.klass.getSimpleName());
        Intrinsics.checkNotNullExpressionValue(name0, "identifier(klass.simpleName)");
        return name0;
    }

    public ReflectJavaClass getOuterClass() {
        Class class0 = this.klass.getDeclaringClass();
        return class0 == null ? null : new ReflectJavaClass(class0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass
    public JavaClass getOuterClass() {
        return this.getOuterClass();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass
    public Collection getPermittedTypes() {
        Class[] arr_class = Java16SealedRecordLoader.INSTANCE.loadGetPermittedSubclasses(this.klass);
        if(arr_class != null) {
            ArrayList arrayList0 = new ArrayList(arr_class.length);
            for(int v = 0; v < arr_class.length; ++v) {
                arrayList0.add(new ReflectJavaClassifierType(arr_class[v]));
            }
            return arrayList0;
        }
        return CollectionsKt.emptyList();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass
    public Collection getRecordComponents() {
        Object[] arr_object = Java16SealedRecordLoader.INSTANCE.loadGetRecordComponents(this.klass);
        if(arr_object == null) {
            arr_object = new Object[0];
        }
        ArrayList arrayList0 = new ArrayList(arr_object.length);
        for(int v = 0; v < arr_object.length; ++v) {
            arrayList0.add(new ReflectJavaRecordComponent(arr_object[v]));
        }
        return arrayList0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass
    public Collection getSupertypes() {
        if(Intrinsics.areEqual(this.klass, Object.class)) {
            return CollectionsKt.emptyList();
        }
        SpreadBuilder spreadBuilder0 = new SpreadBuilder(2);
        Type type0 = this.klass.getGenericSuperclass();
        if(type0 == null) {
            type0 = Object.class;
        }
        spreadBuilder0.add(type0);
        Type[] arr_type = this.klass.getGenericInterfaces();
        Intrinsics.checkNotNullExpressionValue(arr_type, "klass.genericInterfaces");
        spreadBuilder0.addSpread(arr_type);
        Iterable iterable0 = CollectionsKt.listOf(spreadBuilder0.toArray(new Type[spreadBuilder0.size()]));
        ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable0, 10));
        for(Object object0: iterable0) {
            arrayList0.add(new ReflectJavaClassifierType(((Type)object0)));
        }
        return arrayList0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaTypeParameterListOwner
    public List getTypeParameters() {
        TypeVariable[] arr_typeVariable = this.klass.getTypeParameters();
        Intrinsics.checkNotNullExpressionValue(arr_typeVariable, "klass.typeParameters");
        ArrayList arrayList0 = new ArrayList(arr_typeVariable.length);
        for(int v = 0; v < arr_typeVariable.length; ++v) {
            arrayList0.add(new ReflectJavaTypeParameter(arr_typeVariable[v]));
        }
        return arrayList0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaModifierListOwner
    public Visibility getVisibility() {
        int v = this.getModifiers();
        if(Modifier.isPublic(v)) {
            return Public.INSTANCE;
        }
        if(Modifier.isPrivate(v)) {
            return Private.INSTANCE;
        }
        if(Modifier.isProtected(v)) {
            return Modifier.isStatic(v) ? ProtectedStaticVisibility.INSTANCE : ProtectedAndPackage.INSTANCE;
        }
        return PackageVisibility.INSTANCE;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass
    public boolean hasDefaultConstructor() {
        return false;
    }

    @Override
    public int hashCode() {
        return this.klass.hashCode();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaModifierListOwner
    public boolean isAbstract() {
        return Modifier.isAbstract(this.getModifiers());
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass
    public boolean isAnnotationType() {
        return this.klass.isAnnotation();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotationOwner
    public boolean isDeprecatedInJavaDoc() {
        return false;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass
    public boolean isEnum() {
        return this.klass.isEnum();
    }

    private final boolean isEnumValuesOrValueOf(Method method0) {
        String s = method0.getName();
        if(Intrinsics.areEqual(s, "values")) {
            Class[] arr_class = method0.getParameterTypes();
            Intrinsics.checkNotNullExpressionValue(arr_class, "method.parameterTypes");
            return arr_class.length == 0;
        }
        return Intrinsics.areEqual(s, "valueOf") ? Arrays.equals(method0.getParameterTypes(), new Class[]{String.class}) : false;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaModifierListOwner
    public boolean isFinal() {
        return Modifier.isFinal(this.getModifiers());
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass
    public boolean isInterface() {
        return this.klass.isInterface();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass
    public boolean isRecord() {
        Boolean boolean0 = Java16SealedRecordLoader.INSTANCE.loadIsRecord(this.klass);
        return boolean0 == null ? false : boolean0.booleanValue();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass
    public boolean isSealed() {
        Boolean boolean0 = Java16SealedRecordLoader.INSTANCE.loadIsSealed(this.klass);
        return boolean0 == null ? false : boolean0.booleanValue();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaModifierListOwner
    public boolean isStatic() {
        return Modifier.isStatic(this.getModifiers());
    }

    @Override
    public String toString() {
        return this.getClass().getName() + ": " + this.klass;
    }
}

