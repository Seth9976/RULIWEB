package kotlin.reflect.jvm.internal.impl.load.java.lazy;

import java.util.Iterator;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.StandardNames.FqNames;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations.DefaultImpls;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.load.java.components.JavaAnnotationMapper;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotation;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotationOwner;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.storage.MemoizedFunctionToNullable;
import kotlin.sequences.SequencesKt;

public final class LazyJavaAnnotations implements Annotations {
    private final MemoizedFunctionToNullable annotationDescriptors;
    private final JavaAnnotationOwner annotationOwner;
    private final boolean areAnnotationsFreshlySupported;
    private final LazyJavaResolverContext c;

    public LazyJavaAnnotations(LazyJavaResolverContext lazyJavaResolverContext0, JavaAnnotationOwner javaAnnotationOwner0, boolean z) {
        Intrinsics.checkNotNullParameter(lazyJavaResolverContext0, "c");
        Intrinsics.checkNotNullParameter(javaAnnotationOwner0, "annotationOwner");
        super();
        this.c = lazyJavaResolverContext0;
        this.annotationOwner = javaAnnotationOwner0;
        this.areAnnotationsFreshlySupported = z;
        this.annotationDescriptors = lazyJavaResolverContext0.getComponents().getStorageManager().createMemoizedFunctionWithNullableValues(new Function1() {
            {
                LazyJavaAnnotations.this = lazyJavaAnnotations0;
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((JavaAnnotation)object0));
            }

            public final AnnotationDescriptor invoke(JavaAnnotation javaAnnotation0) {
                Intrinsics.checkNotNullParameter(javaAnnotation0, "annotation");
                LazyJavaResolverContext lazyJavaResolverContext0 = LazyJavaAnnotations.this.c;
                boolean z = LazyJavaAnnotations.this.areAnnotationsFreshlySupported;
                return JavaAnnotationMapper.INSTANCE.mapOrResolveJavaAnnotation(javaAnnotation0, lazyJavaResolverContext0, z);
            }
        });
    }

    public LazyJavaAnnotations(LazyJavaResolverContext lazyJavaResolverContext0, JavaAnnotationOwner javaAnnotationOwner0, boolean z, int v, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v & 4) != 0) {
            z = false;
        }
        this(lazyJavaResolverContext0, javaAnnotationOwner0, z);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations
    public AnnotationDescriptor findAnnotation(FqName fqName0) {
        Intrinsics.checkNotNullParameter(fqName0, "fqName");
        JavaAnnotation javaAnnotation0 = this.annotationOwner.findAnnotation(fqName0);
        if(javaAnnotation0 != null) {
            AnnotationDescriptor annotationDescriptor0 = (AnnotationDescriptor)this.annotationDescriptors.invoke(javaAnnotation0);
            return annotationDescriptor0 == null ? JavaAnnotationMapper.INSTANCE.findMappedJavaAnnotation(fqName0, this.annotationOwner, this.c) : annotationDescriptor0;
        }
        return JavaAnnotationMapper.INSTANCE.findMappedJavaAnnotation(fqName0, this.annotationOwner, this.c);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations
    public boolean hasAnnotation(FqName fqName0) {
        return DefaultImpls.hasAnnotation(this, fqName0);
    }

    // 去混淆评级： 低(20)
    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations
    public boolean isEmpty() {
        return this.annotationOwner.getAnnotations().isEmpty() && !this.annotationOwner.isDeprecatedInJavaDoc();
    }

    @Override
    public Iterator iterator() {
        return SequencesKt.filterNotNull(SequencesKt.plus(SequencesKt.map(CollectionsKt.asSequence(this.annotationOwner.getAnnotations()), this.annotationDescriptors), JavaAnnotationMapper.INSTANCE.findMappedJavaAnnotation(FqNames.deprecated, this.annotationOwner, this.c))).iterator();
    }
}

