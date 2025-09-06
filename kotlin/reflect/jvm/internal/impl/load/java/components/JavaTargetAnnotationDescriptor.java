package kotlin.reflect.jvm.internal.impl.load.java.components;

import java.util.List;
import java.util.Map;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
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
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaArrayAnnotationArgument;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaEnumValueAnnotationArgument;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue;
import kotlin.reflect.jvm.internal.impl.storage.NotNullLazyValue;
import kotlin.reflect.jvm.internal.impl.storage.StorageKt;

public final class JavaTargetAnnotationDescriptor extends JavaAnnotationDescriptor {
    static final KProperty[] $$delegatedProperties;
    private final NotNullLazyValue allValueArguments$delegate;

    static {
        JavaTargetAnnotationDescriptor.$$delegatedProperties = new KProperty[]{Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(JavaTargetAnnotationDescriptor.class), "allValueArguments", "getAllValueArguments()Ljava/util/Map;"))};
    }

    public JavaTargetAnnotationDescriptor(JavaAnnotation javaAnnotation0, LazyJavaResolverContext lazyJavaResolverContext0) {
        Intrinsics.checkNotNullParameter(javaAnnotation0, "annotation");
        Intrinsics.checkNotNullParameter(lazyJavaResolverContext0, "c");
        super(lazyJavaResolverContext0, javaAnnotation0, FqNames.target);
        this.allValueArguments$delegate = lazyJavaResolverContext0.getStorageManager().createLazyValue(new Function0() {
            {
                JavaTargetAnnotationDescriptor.this = javaTargetAnnotationDescriptor0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final Map invoke() {
                ConstantValue constantValue0;
                JavaAnnotationArgument javaAnnotationArgument0 = JavaTargetAnnotationDescriptor.this.getFirstArgument();
                Map map0 = null;
                if(javaAnnotationArgument0 instanceof JavaArrayAnnotationArgument) {
                    List list0 = ((JavaArrayAnnotationArgument)JavaTargetAnnotationDescriptor.this.getFirstArgument()).getElements();
                    constantValue0 = JavaAnnotationTargetMapper.INSTANCE.mapJavaTargetArguments$descriptors_jvm(list0);
                }
                else if(javaAnnotationArgument0 instanceof JavaEnumValueAnnotationArgument) {
                    List list1 = CollectionsKt.listOf(JavaTargetAnnotationDescriptor.this.getFirstArgument());
                    constantValue0 = JavaAnnotationTargetMapper.INSTANCE.mapJavaTargetArguments$descriptors_jvm(list1);
                }
                else {
                    constantValue0 = null;
                }
                if(constantValue0 != null) {
                    map0 = MapsKt.mapOf(TuplesKt.to(JavaAnnotationMapper.INSTANCE.getTARGET_ANNOTATION_ALLOWED_TARGETS$descriptors_jvm(), constantValue0));
                }
                return map0 == null ? MapsKt.emptyMap() : map0;
            }
        });
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.components.JavaAnnotationDescriptor
    public Map getAllValueArguments() {
        return (Map)StorageKt.getValue(this.allValueArguments$delegate, this, JavaTargetAnnotationDescriptor.$$delegatedProperties[0]);
    }
}

