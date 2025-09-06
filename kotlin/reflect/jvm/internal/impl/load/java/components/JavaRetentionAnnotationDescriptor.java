package kotlin.reflect.jvm.internal.impl.load.java.components;

import java.util.Map;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.reflect.jvm.internal.impl.builtins.StandardNames.FqNames;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotation;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotationArgument;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue;
import kotlin.reflect.jvm.internal.impl.storage.NotNullLazyValue;
import kotlin.reflect.jvm.internal.impl.storage.StorageKt;

public final class JavaRetentionAnnotationDescriptor extends JavaAnnotationDescriptor {
    static final KProperty[] $$delegatedProperties;
    private final NotNullLazyValue allValueArguments$delegate;

    static {
        JavaRetentionAnnotationDescriptor.$$delegatedProperties = new KProperty[]{Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(JavaRetentionAnnotationDescriptor.class), "allValueArguments", "getAllValueArguments()Ljava/util/Map;"))};
    }

    public JavaRetentionAnnotationDescriptor(JavaAnnotation javaAnnotation0, LazyJavaResolverContext lazyJavaResolverContext0) {
        Intrinsics.checkNotNullParameter(javaAnnotation0, "annotation");
        Intrinsics.checkNotNullParameter(lazyJavaResolverContext0, "c");
        super(lazyJavaResolverContext0, javaAnnotation0, FqNames.retention);
        this.allValueArguments$delegate = lazyJavaResolverContext0.getStorageManager().createLazyValue(new Function0() {
            {
                JavaRetentionAnnotationDescriptor.this = javaRetentionAnnotationDescriptor0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final Map invoke() {
                JavaAnnotationArgument javaAnnotationArgument0 = JavaRetentionAnnotationDescriptor.this.getFirstArgument();
                ConstantValue constantValue0 = JavaAnnotationTargetMapper.INSTANCE.mapJavaRetentionArgument$descriptors_jvm(javaAnnotationArgument0);
                Map map0 = constantValue0 == null ? null : MapsKt.mapOf(TuplesKt.to(JavaAnnotationMapper.INSTANCE.getRETENTION_ANNOTATION_VALUE$descriptors_jvm(), constantValue0));
                return map0 == null ? MapsKt.emptyMap() : map0;
            }
        });
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.components.JavaAnnotationDescriptor
    public Map getAllValueArguments() {
        return (Map)StorageKt.getValue(this.allValueArguments$delegate, this, JavaRetentionAnnotationDescriptor.$$delegatedProperties[0]);
    }
}

