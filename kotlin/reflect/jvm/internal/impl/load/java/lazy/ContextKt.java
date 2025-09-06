package kotlin.reflect.jvm.internal.impl.load.java.lazy;

import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassOrPackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.load.java.JavaTypeQualifiersByElementType;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaTypeParameterListOwner;

public final class ContextKt {
    private static final LazyJavaResolverContext child(LazyJavaResolverContext lazyJavaResolverContext0, DeclarationDescriptor declarationDescriptor0, JavaTypeParameterListOwner javaTypeParameterListOwner0, int v, Lazy lazy0) {
        JavaResolverComponents javaResolverComponents0 = lazyJavaResolverContext0.getComponents();
        return javaTypeParameterListOwner0 == null ? new LazyJavaResolverContext(javaResolverComponents0, lazyJavaResolverContext0.getTypeParameterResolver(), lazy0) : new LazyJavaResolverContext(javaResolverComponents0, new LazyJavaTypeParameterResolver(lazyJavaResolverContext0, declarationDescriptor0, javaTypeParameterListOwner0, v), lazy0);
    }

    public static final LazyJavaResolverContext child(LazyJavaResolverContext lazyJavaResolverContext0, TypeParameterResolver typeParameterResolver0) {
        Intrinsics.checkNotNullParameter(lazyJavaResolverContext0, "<this>");
        Intrinsics.checkNotNullParameter(typeParameterResolver0, "typeParameterResolver");
        return new LazyJavaResolverContext(lazyJavaResolverContext0.getComponents(), typeParameterResolver0, lazyJavaResolverContext0.getDelegateForDefaultTypeQualifiers$descriptors_jvm());
    }

    public static final LazyJavaResolverContext childForClassOrPackage(LazyJavaResolverContext lazyJavaResolverContext0, ClassOrPackageFragmentDescriptor classOrPackageFragmentDescriptor0, JavaTypeParameterListOwner javaTypeParameterListOwner0, int v) {
        Intrinsics.checkNotNullParameter(lazyJavaResolverContext0, "<this>");
        Intrinsics.checkNotNullParameter(classOrPackageFragmentDescriptor0, "containingDeclaration");
        Function0 function00 = new Function0(lazyJavaResolverContext0, classOrPackageFragmentDescriptor0) {
            final ClassOrPackageFragmentDescriptor $containingDeclaration;
            final LazyJavaResolverContext $this_childForClassOrPackage;

            {
                this.$this_childForClassOrPackage = lazyJavaResolverContext0;
                this.$containingDeclaration = classOrPackageFragmentDescriptor0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final JavaTypeQualifiersByElementType invoke() {
                Annotations annotations0 = this.$containingDeclaration.getAnnotations();
                return ContextKt.computeNewDefaultTypeQualifiers(this.$this_childForClassOrPackage, annotations0);
            }
        };
        return ContextKt.child(lazyJavaResolverContext0, classOrPackageFragmentDescriptor0, javaTypeParameterListOwner0, v, LazyKt.lazy(LazyThreadSafetyMode.NONE, function00));
    }

    public static LazyJavaResolverContext childForClassOrPackage$default(LazyJavaResolverContext lazyJavaResolverContext0, ClassOrPackageFragmentDescriptor classOrPackageFragmentDescriptor0, JavaTypeParameterListOwner javaTypeParameterListOwner0, int v, int v1, Object object0) {
        if((v1 & 2) != 0) {
            javaTypeParameterListOwner0 = null;
        }
        if((v1 & 4) != 0) {
            v = 0;
        }
        return ContextKt.childForClassOrPackage(lazyJavaResolverContext0, classOrPackageFragmentDescriptor0, javaTypeParameterListOwner0, v);
    }

    public static final LazyJavaResolverContext childForMethod(LazyJavaResolverContext lazyJavaResolverContext0, DeclarationDescriptor declarationDescriptor0, JavaTypeParameterListOwner javaTypeParameterListOwner0, int v) {
        Intrinsics.checkNotNullParameter(lazyJavaResolverContext0, "<this>");
        Intrinsics.checkNotNullParameter(declarationDescriptor0, "containingDeclaration");
        Intrinsics.checkNotNullParameter(javaTypeParameterListOwner0, "typeParameterOwner");
        return ContextKt.child(lazyJavaResolverContext0, declarationDescriptor0, javaTypeParameterListOwner0, v, lazyJavaResolverContext0.getDelegateForDefaultTypeQualifiers$descriptors_jvm());
    }

    public static LazyJavaResolverContext childForMethod$default(LazyJavaResolverContext lazyJavaResolverContext0, DeclarationDescriptor declarationDescriptor0, JavaTypeParameterListOwner javaTypeParameterListOwner0, int v, int v1, Object object0) {
        if((v1 & 4) != 0) {
            v = 0;
        }
        return ContextKt.childForMethod(lazyJavaResolverContext0, declarationDescriptor0, javaTypeParameterListOwner0, v);
    }

    public static final JavaTypeQualifiersByElementType computeNewDefaultTypeQualifiers(LazyJavaResolverContext lazyJavaResolverContext0, Annotations annotations0) {
        Intrinsics.checkNotNullParameter(lazyJavaResolverContext0, "<this>");
        Intrinsics.checkNotNullParameter(annotations0, "additionalAnnotations");
        return lazyJavaResolverContext0.getComponents().getAnnotationTypeQualifierResolver().extractAndMergeDefaultQualifiers(lazyJavaResolverContext0.getDefaultTypeQualifiers(), annotations0);
    }

    public static final LazyJavaResolverContext copyWithNewDefaultTypeQualifiers(LazyJavaResolverContext lazyJavaResolverContext0, Annotations annotations0) {
        Intrinsics.checkNotNullParameter(lazyJavaResolverContext0, "<this>");
        Intrinsics.checkNotNullParameter(annotations0, "additionalAnnotations");
        if(annotations0.isEmpty()) {
            return lazyJavaResolverContext0;
        }
        Function0 function00 = new Function0(lazyJavaResolverContext0, annotations0) {
            final Annotations $additionalAnnotations;
            final LazyJavaResolverContext $this_copyWithNewDefaultTypeQualifiers;

            {
                this.$this_copyWithNewDefaultTypeQualifiers = lazyJavaResolverContext0;
                this.$additionalAnnotations = annotations0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final JavaTypeQualifiersByElementType invoke() {
                return ContextKt.computeNewDefaultTypeQualifiers(this.$this_copyWithNewDefaultTypeQualifiers, this.$additionalAnnotations);
            }
        };
        return new LazyJavaResolverContext(lazyJavaResolverContext0.getComponents(), lazyJavaResolverContext0.getTypeParameterResolver(), LazyKt.lazy(LazyThreadSafetyMode.NONE, function00));
    }

    public static final LazyJavaResolverContext replaceComponents(LazyJavaResolverContext lazyJavaResolverContext0, JavaResolverComponents javaResolverComponents0) {
        Intrinsics.checkNotNullParameter(lazyJavaResolverContext0, "<this>");
        Intrinsics.checkNotNullParameter(javaResolverComponents0, "components");
        return new LazyJavaResolverContext(javaResolverComponents0, lazyJavaResolverContext0.getTypeParameterResolver(), lazyJavaResolverContext0.getDelegateForDefaultTypeQualifiers$descriptors_jvm());
    }
}

