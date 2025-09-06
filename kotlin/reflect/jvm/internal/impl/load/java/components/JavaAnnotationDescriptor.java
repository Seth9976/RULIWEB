package kotlin.reflect.jvm.internal.impl.load.java.components;

import java.util.Collection;
import java.util.Map;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.PossiblyExternalAnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext;
import kotlin.reflect.jvm.internal.impl.load.java.sources.JavaSourceElement;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotation;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotationArgument;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.storage.NotNullLazyValue;
import kotlin.reflect.jvm.internal.impl.storage.StorageKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;

public class JavaAnnotationDescriptor implements AnnotationDescriptor, PossiblyExternalAnnotationDescriptor {
    static final KProperty[] $$delegatedProperties;
    private final JavaAnnotationArgument firstArgument;
    private final FqName fqName;
    private final boolean isIdeExternalAnnotation;
    private final SourceElement source;
    private final NotNullLazyValue type$delegate;

    static {
        JavaAnnotationDescriptor.$$delegatedProperties = new KProperty[]{Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(JavaAnnotationDescriptor.class), "type", "getType()Lorg/jetbrains/kotlin/types/SimpleType;"))};
    }

    public JavaAnnotationDescriptor(LazyJavaResolverContext lazyJavaResolverContext0, JavaAnnotation javaAnnotation0, FqName fqName0) {
        Intrinsics.checkNotNullParameter(lazyJavaResolverContext0, "c");
        JavaAnnotationArgument javaAnnotationArgument0;
        SourceElement sourceElement0;
        Intrinsics.checkNotNullParameter(fqName0, "fqName");
        super();
        this.fqName = fqName0;
        if(javaAnnotation0 == null) {
            sourceElement0 = SourceElement.NO_SOURCE;
            Intrinsics.checkNotNullExpressionValue(sourceElement0, "NO_SOURCE");
        }
        else {
            JavaSourceElement javaSourceElement0 = lazyJavaResolverContext0.getComponents().getSourceElementFactory().source(javaAnnotation0);
            if(javaSourceElement0 == null) {
                sourceElement0 = SourceElement.NO_SOURCE;
                Intrinsics.checkNotNullExpressionValue(sourceElement0, "NO_SOURCE");
            }
            else {
                sourceElement0 = javaSourceElement0;
            }
        }
        this.source = sourceElement0;
        this.type$delegate = lazyJavaResolverContext0.getStorageManager().createLazyValue(new Function0(this) {
            final LazyJavaResolverContext $c;

            {
                this.$c = lazyJavaResolverContext0;
                JavaAnnotationDescriptor.this = javaAnnotationDescriptor0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final SimpleType invoke() {
                SimpleType simpleType0 = this.$c.getModule().getBuiltIns().getBuiltInClassByFqName(JavaAnnotationDescriptor.this.getFqName()).getDefaultType();
                Intrinsics.checkNotNullExpressionValue(simpleType0, "c.module.builtIns.getBui…qName(fqName).defaultType");
                return simpleType0;
            }
        });
        if(javaAnnotation0 == null) {
            javaAnnotationArgument0 = null;
        }
        else {
            Collection collection0 = javaAnnotation0.getArguments();
            javaAnnotationArgument0 = collection0 == null ? null : ((JavaAnnotationArgument)CollectionsKt.firstOrNull(collection0));
        }
        this.firstArgument = javaAnnotationArgument0;
        this.isIdeExternalAnnotation = javaAnnotation0 != null && javaAnnotation0.isIdeExternalAnnotation();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor
    public Map getAllValueArguments() {
        return MapsKt.emptyMap();
    }

    protected final JavaAnnotationArgument getFirstArgument() {
        return this.firstArgument;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor
    public FqName getFqName() {
        return this.fqName;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor
    public SourceElement getSource() {
        return this.source;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor
    public KotlinType getType() {
        return this.getType();
    }

    public SimpleType getType() {
        return (SimpleType)StorageKt.getValue(this.type$delegate, this, JavaAnnotationDescriptor.$$delegatedProperties[0]);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.descriptors.PossiblyExternalAnnotationDescriptor
    public boolean isIdeExternalAnnotation() {
        return this.isIdeExternalAnnotation;
    }
}

