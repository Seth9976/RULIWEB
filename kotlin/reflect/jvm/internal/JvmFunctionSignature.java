package kotlin.reflect.jvm.internal;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectClassUtilKt;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b0\u0018\u00002\u00020\u0001:\u0005\u0005\u0006\u0007\b\tB\u0007\b\u0004¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001A\u00020\u0004H&\u0082\u0001\u0005\n\u000B\f\r\u000E¨\u0006\u000F"}, d2 = {"Lkotlin/reflect/jvm/internal/JvmFunctionSignature;", "", "()V", "asString", "", "FakeJavaAnnotationConstructor", "JavaConstructor", "JavaMethod", "KotlinConstructor", "KotlinFunction", "Lkotlin/reflect/jvm/internal/JvmFunctionSignature$FakeJavaAnnotationConstructor;", "Lkotlin/reflect/jvm/internal/JvmFunctionSignature$JavaConstructor;", "Lkotlin/reflect/jvm/internal/JvmFunctionSignature$JavaMethod;", "Lkotlin/reflect/jvm/internal/JvmFunctionSignature$KotlinConstructor;", "Lkotlin/reflect/jvm/internal/JvmFunctionSignature$KotlinFunction;", "kotlin-reflection"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public abstract class JvmFunctionSignature {
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000E\n\u0000\u0018\u00002\u00020\u0001B\u0011\u0012\n\u0010\u0002\u001A\u0006\u0012\u0002\b\u00030\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\r\u001A\u00020\u000EH\u0016R\u0015\u0010\u0002\u001A\u0006\u0012\u0002\b\u00030\u0003¢\u0006\b\n\u0000\u001A\u0004\b\u0005\u0010\u0006R\u001F\u0010\u0007\u001A\u0010\u0012\f\u0012\n \n*\u0004\u0018\u00010\t0\t0\b¢\u0006\b\n\u0000\u001A\u0004\b\u000B\u0010\f¨\u0006\u000F"}, d2 = {"Lkotlin/reflect/jvm/internal/JvmFunctionSignature$FakeJavaAnnotationConstructor;", "Lkotlin/reflect/jvm/internal/JvmFunctionSignature;", "jClass", "Ljava/lang/Class;", "(Ljava/lang/Class;)V", "getJClass", "()Ljava/lang/Class;", "methods", "", "Ljava/lang/reflect/Method;", "kotlin.jvm.PlatformType", "getMethods", "()Ljava/util/List;", "asString", "", "kotlin-reflection"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class FakeJavaAnnotationConstructor extends JvmFunctionSignature {
        private final Class jClass;
        private final List methods;

        public FakeJavaAnnotationConstructor(Class class0) {
            Intrinsics.checkNotNullParameter(class0, "jClass");
            super(null);
            this.jClass = class0;
            Method[] arr_method = class0.getDeclaredMethods();
            Intrinsics.checkNotNullExpressionValue(arr_method, "jClass.declaredMethods");
            this.methods = ArraysKt.sortedWith(arr_method, new kotlin.reflect.jvm.internal.JvmFunctionSignature.FakeJavaAnnotationConstructor.special..inlined.sortedBy.1());

            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\u0010\u0000\u001A\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u000E\u0010\u0003\u001A\n \u0004*\u0004\u0018\u0001H\u0002H\u00022\u000E\u0010\u0005\u001A\n \u0004*\u0004\u0018\u0001H\u0002H\u0002H\n¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"<anonymous>", "", "T", "a", "kotlin.jvm.PlatformType", "b", "compare", "(Ljava/lang/Object;Ljava/lang/Object;)I", "kotlin/comparisons/ComparisonsKt__ComparisonsKt$compareBy$2"}, k = 3, mv = {1, 8, 0}, xi = 0x30)
            public final class kotlin.reflect.jvm.internal.JvmFunctionSignature.FakeJavaAnnotationConstructor.special..inlined.sortedBy.1 implements Comparator {
                @Override
                public final int compare(Object object0, Object object1) {
                    return ComparisonsKt.compareValues(((Method)object0).getName(), ((Method)object1).getName());
                }
            }

        }

        @Override  // kotlin.reflect.jvm.internal.JvmFunctionSignature
        public String asString() {
            return CollectionsKt.joinToString$default(this.methods, "", "<init>(", ")V", 0, null, kotlin.reflect.jvm.internal.JvmFunctionSignature.FakeJavaAnnotationConstructor.asString.1.INSTANCE, 24, null);

            @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001A\u00020\u00012\u000E\u0010\u0002\u001A\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Ljava/lang/reflect/Method;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 0x30)
            final class kotlin.reflect.jvm.internal.JvmFunctionSignature.FakeJavaAnnotationConstructor.asString.1 extends Lambda implements Function1 {
                public static final kotlin.reflect.jvm.internal.JvmFunctionSignature.FakeJavaAnnotationConstructor.asString.1 INSTANCE;

                static {
                    kotlin.reflect.jvm.internal.JvmFunctionSignature.FakeJavaAnnotationConstructor.asString.1.INSTANCE = new kotlin.reflect.jvm.internal.JvmFunctionSignature.FakeJavaAnnotationConstructor.asString.1();
                }

                kotlin.reflect.jvm.internal.JvmFunctionSignature.FakeJavaAnnotationConstructor.asString.1() {
                    super(1);
                }

                public final CharSequence invoke(Method method0) {
                    Class class0 = method0.getReturnType();
                    Intrinsics.checkNotNullExpressionValue(class0, "it.returnType");
                    return ReflectClassUtilKt.getDesc(class0);
                }

                @Override  // kotlin.jvm.functions.Function1
                public Object invoke(Object object0) {
                    return this.invoke(((Method)object0));
                }
            }

        }

        public final List getMethods() {
            return this.methods;
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000E\n\u0000\u0018\u00002\u00020\u0001B\u0011\u0012\n\u0010\u0002\u001A\u0006\u0012\u0002\b\u00030\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001A\u00020\bH\u0016R\u0015\u0010\u0002\u001A\u0006\u0012\u0002\b\u00030\u0003¢\u0006\b\n\u0000\u001A\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Lkotlin/reflect/jvm/internal/JvmFunctionSignature$JavaConstructor;", "Lkotlin/reflect/jvm/internal/JvmFunctionSignature;", "constructor", "Ljava/lang/reflect/Constructor;", "(Ljava/lang/reflect/Constructor;)V", "getConstructor", "()Ljava/lang/reflect/Constructor;", "asString", "", "kotlin-reflection"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class JavaConstructor extends JvmFunctionSignature {
        private final Constructor constructor;

        public JavaConstructor(Constructor constructor0) {
            Intrinsics.checkNotNullParameter(constructor0, "constructor");
            super(null);
            this.constructor = constructor0;
        }

        @Override  // kotlin.reflect.jvm.internal.JvmFunctionSignature
        public String asString() {
            Class[] arr_class = this.constructor.getParameterTypes();
            Intrinsics.checkNotNullExpressionValue(arr_class, "constructor.parameterTypes");
            return ArraysKt.joinToString$default(arr_class, "", "<init>(", ")V", 0, null, kotlin.reflect.jvm.internal.JvmFunctionSignature.JavaConstructor.asString.1.INSTANCE, 24, null);

            @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001A\u00020\u00012\u0016\u0010\u0002\u001A\u0012\u0012\u0002\b\u0003 \u0004*\b\u0012\u0002\b\u0003\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Ljava/lang/Class;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 0x30)
            final class kotlin.reflect.jvm.internal.JvmFunctionSignature.JavaConstructor.asString.1 extends Lambda implements Function1 {
                public static final kotlin.reflect.jvm.internal.JvmFunctionSignature.JavaConstructor.asString.1 INSTANCE;

                static {
                    kotlin.reflect.jvm.internal.JvmFunctionSignature.JavaConstructor.asString.1.INSTANCE = new kotlin.reflect.jvm.internal.JvmFunctionSignature.JavaConstructor.asString.1();
                }

                kotlin.reflect.jvm.internal.JvmFunctionSignature.JavaConstructor.asString.1() {
                    super(1);
                }

                public final CharSequence invoke(Class class0) {
                    Intrinsics.checkNotNullExpressionValue(class0, "it");
                    return ReflectClassUtilKt.getDesc(class0);
                }

                @Override  // kotlin.jvm.functions.Function1
                public Object invoke(Object object0) {
                    return this.invoke(((Class)object0));
                }
            }

        }

        public final Constructor getConstructor() {
            return this.constructor;
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000E\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001A\u00020\bH\u0016R\u0011\u0010\u0002\u001A\u00020\u0003¢\u0006\b\n\u0000\u001A\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Lkotlin/reflect/jvm/internal/JvmFunctionSignature$JavaMethod;", "Lkotlin/reflect/jvm/internal/JvmFunctionSignature;", "method", "Ljava/lang/reflect/Method;", "(Ljava/lang/reflect/Method;)V", "getMethod", "()Ljava/lang/reflect/Method;", "asString", "", "kotlin-reflection"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class JavaMethod extends JvmFunctionSignature {
        private final Method method;

        public JavaMethod(Method method0) {
            Intrinsics.checkNotNullParameter(method0, "method");
            super(null);
            this.method = method0;
        }

        @Override  // kotlin.reflect.jvm.internal.JvmFunctionSignature
        public String asString() {
            return RuntimeTypeMapperKt.access$getSignature(this.method);
        }

        public final Method getMethod() {
            return this.method;
        }
    }

    @Metadata(d1 = {"\u0000\u001A\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\f\u001A\u00020\u0006H\u0016R\u000E\u0010\u0005\u001A\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0007\u001A\u00020\u00068F¢\u0006\u0006\u001A\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001A\u00020\u0003¢\u0006\b\n\u0000\u001A\u0004\b\n\u0010\u000B¨\u0006\r"}, d2 = {"Lkotlin/reflect/jvm/internal/JvmFunctionSignature$KotlinConstructor;", "Lkotlin/reflect/jvm/internal/JvmFunctionSignature;", "signature", "Lkotlin/reflect/jvm/internal/impl/metadata/jvm/deserialization/JvmMemberSignature$Method;", "(Lorg/jetbrains/kotlin/metadata/jvm/deserialization/JvmMemberSignature$Method;)V", "_signature", "", "constructorDesc", "getConstructorDesc", "()Ljava/lang/String;", "getSignature", "()Lorg/jetbrains/kotlin/metadata/jvm/deserialization/JvmMemberSignature$Method;", "asString", "kotlin-reflection"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class KotlinConstructor extends JvmFunctionSignature {
        private final String _signature;
        private final kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMemberSignature.Method signature;

        public KotlinConstructor(kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMemberSignature.Method jvmMemberSignature$Method0) {
            Intrinsics.checkNotNullParameter(jvmMemberSignature$Method0, "signature");
            super(null);
            this.signature = jvmMemberSignature$Method0;
            this._signature = jvmMemberSignature$Method0.asString();
        }

        @Override  // kotlin.reflect.jvm.internal.JvmFunctionSignature
        public String asString() {
            return this._signature;
        }

        public final String getConstructorDesc() {
            return this.signature.getDesc();
        }
    }

    @Metadata(d1 = {"\u0000\u001A\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0002\b\t\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u000E\u001A\u00020\u0006H\u0016R\u000E\u0010\u0005\u001A\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0007\u001A\u00020\u00068F¢\u0006\u0006\u001A\u0004\b\b\u0010\tR\u0011\u0010\n\u001A\u00020\u00068F¢\u0006\u0006\u001A\u0004\b\u000B\u0010\tR\u0011\u0010\u0002\u001A\u00020\u0003¢\u0006\b\n\u0000\u001A\u0004\b\f\u0010\r¨\u0006\u000F"}, d2 = {"Lkotlin/reflect/jvm/internal/JvmFunctionSignature$KotlinFunction;", "Lkotlin/reflect/jvm/internal/JvmFunctionSignature;", "signature", "Lkotlin/reflect/jvm/internal/impl/metadata/jvm/deserialization/JvmMemberSignature$Method;", "(Lorg/jetbrains/kotlin/metadata/jvm/deserialization/JvmMemberSignature$Method;)V", "_signature", "", "methodDesc", "getMethodDesc", "()Ljava/lang/String;", "methodName", "getMethodName", "getSignature", "()Lorg/jetbrains/kotlin/metadata/jvm/deserialization/JvmMemberSignature$Method;", "asString", "kotlin-reflection"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class KotlinFunction extends JvmFunctionSignature {
        private final String _signature;
        private final kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMemberSignature.Method signature;

        public KotlinFunction(kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMemberSignature.Method jvmMemberSignature$Method0) {
            Intrinsics.checkNotNullParameter(jvmMemberSignature$Method0, "signature");
            super(null);
            this.signature = jvmMemberSignature$Method0;
            this._signature = jvmMemberSignature$Method0.asString();
        }

        @Override  // kotlin.reflect.jvm.internal.JvmFunctionSignature
        public String asString() {
            return this._signature;
        }

        public final String getMethodDesc() {
            return this.signature.getDesc();
        }

        public final String getMethodName() {
            return this.signature.getName();
        }
    }

    private JvmFunctionSignature() {
    }

    public JvmFunctionSignature(DefaultConstructorMarker defaultConstructorMarker0) {
    }

    public abstract String asString();
}

