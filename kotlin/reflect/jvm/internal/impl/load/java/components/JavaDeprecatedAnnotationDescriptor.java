package kotlin.reflect.jvm.internal.impl.load.java.components;

import java.util.Map;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.reflect.jvm.internal.impl.builtins.StandardNames.FqNames;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotation;
import kotlin.reflect.jvm.internal.impl.resolve.constants.StringValue;
import kotlin.reflect.jvm.internal.impl.storage.NotNullLazyValue;
import kotlin.reflect.jvm.internal.impl.storage.StorageKt;

public final class JavaDeprecatedAnnotationDescriptor extends JavaAnnotationDescriptor {
    static final KProperty[] $$delegatedProperties;
    private final NotNullLazyValue allValueArguments$delegate;

    static {
        JavaDeprecatedAnnotationDescriptor.$$delegatedProperties = new KProperty[]{Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(JavaDeprecatedAnnotationDescriptor.class), "allValueArguments", "getAllValueArguments()Ljava/util/Map;"))};
    }

    public JavaDeprecatedAnnotationDescriptor(JavaAnnotation javaAnnotation0, LazyJavaResolverContext lazyJavaResolverContext0) {
        Intrinsics.checkNotNullParameter(lazyJavaResolverContext0, "c");
        super(lazyJavaResolverContext0, javaAnnotation0, FqNames.deprecated);
        this.allValueArguments$delegate = lazyJavaResolverContext0.getStorageManager().createLazyValue(kotlin.reflect.jvm.internal.impl.load.java.components.JavaDeprecatedAnnotationDescriptor.allValueArguments.2.INSTANCE);

        final class kotlin.reflect.jvm.internal.impl.load.java.components.JavaDeprecatedAnnotationDescriptor.allValueArguments.2 extends Lambda implements Function0 {
            public static final kotlin.reflect.jvm.internal.impl.load.java.components.JavaDeprecatedAnnotationDescriptor.allValueArguments.2 INSTANCE;

            static {
                kotlin.reflect.jvm.internal.impl.load.java.components.JavaDeprecatedAnnotationDescriptor.allValueArguments.2.INSTANCE = new kotlin.reflect.jvm.internal.impl.load.java.components.JavaDeprecatedAnnotationDescriptor.allValueArguments.2();
            }

            kotlin.reflect.jvm.internal.impl.load.java.components.JavaDeprecatedAnnotationDescriptor.allValueArguments.2() {
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final Map invoke() {
                return MapsKt.mapOf(TuplesKt.to(JavaAnnotationMapper.INSTANCE.getDEPRECATED_ANNOTATION_MESSAGE$descriptors_jvm(), new StringValue("Deprecated in Java")));
            }
        }

    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.components.JavaAnnotationDescriptor
    public Map getAllValueArguments() {
        return (Map)StorageKt.getValue(this.allValueArguments$delegate, this, JavaDeprecatedAnnotationDescriptor.$$delegatedProperties[0]);
    }
}

