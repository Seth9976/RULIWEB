package kotlin.reflect.jvm.internal.impl.load.java.lazy;

import java.util.Map;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaTypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaTypeParameter;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaTypeParameterListOwner;
import kotlin.reflect.jvm.internal.impl.storage.MemoizedFunctionToNullable;
import kotlin.reflect.jvm.internal.impl.utils.CollectionsKt;

public final class LazyJavaTypeParameterResolver implements TypeParameterResolver {
    private final LazyJavaResolverContext c;
    private final DeclarationDescriptor containingDeclaration;
    private final MemoizedFunctionToNullable resolve;
    private final Map typeParameters;
    private final int typeParametersIndexOffset;

    public LazyJavaTypeParameterResolver(LazyJavaResolverContext lazyJavaResolverContext0, DeclarationDescriptor declarationDescriptor0, JavaTypeParameterListOwner javaTypeParameterListOwner0, int v) {
        Intrinsics.checkNotNullParameter(lazyJavaResolverContext0, "c");
        Intrinsics.checkNotNullParameter(declarationDescriptor0, "containingDeclaration");
        Intrinsics.checkNotNullParameter(javaTypeParameterListOwner0, "typeParameterOwner");
        super();
        this.c = lazyJavaResolverContext0;
        this.containingDeclaration = declarationDescriptor0;
        this.typeParametersIndexOffset = v;
        this.typeParameters = CollectionsKt.mapToIndex(javaTypeParameterListOwner0.getTypeParameters());
        this.resolve = lazyJavaResolverContext0.getStorageManager().createMemoizedFunctionWithNullableValues(new Function1() {
            {
                LazyJavaTypeParameterResolver.this = lazyJavaTypeParameterResolver0;
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((JavaTypeParameter)object0));
            }

            public final LazyJavaTypeParameterDescriptor invoke(JavaTypeParameter javaTypeParameter0) {
                Intrinsics.checkNotNullParameter(javaTypeParameter0, "typeParameter");
                Integer integer0 = (Integer)LazyJavaTypeParameterResolver.access$getTypeParameters$p(LazyJavaTypeParameterResolver.this).get(javaTypeParameter0);
                return integer0 == null ? null : new LazyJavaTypeParameterDescriptor(ContextKt.copyWithNewDefaultTypeQualifiers(ContextKt.child(LazyJavaTypeParameterResolver.access$getC$p(LazyJavaTypeParameterResolver.this), LazyJavaTypeParameterResolver.this), LazyJavaTypeParameterResolver.access$getContainingDeclaration$p(LazyJavaTypeParameterResolver.this).getAnnotations()), javaTypeParameter0, LazyJavaTypeParameterResolver.access$getTypeParametersIndexOffset$p(LazyJavaTypeParameterResolver.this) + integer0.intValue(), LazyJavaTypeParameterResolver.access$getContainingDeclaration$p(LazyJavaTypeParameterResolver.this));
            }
        });
    }

    public static final LazyJavaResolverContext access$getC$p(LazyJavaTypeParameterResolver lazyJavaTypeParameterResolver0) {
        return lazyJavaTypeParameterResolver0.c;
    }

    public static final DeclarationDescriptor access$getContainingDeclaration$p(LazyJavaTypeParameterResolver lazyJavaTypeParameterResolver0) {
        return lazyJavaTypeParameterResolver0.containingDeclaration;
    }

    public static final Map access$getTypeParameters$p(LazyJavaTypeParameterResolver lazyJavaTypeParameterResolver0) {
        return lazyJavaTypeParameterResolver0.typeParameters;
    }

    public static final int access$getTypeParametersIndexOffset$p(LazyJavaTypeParameterResolver lazyJavaTypeParameterResolver0) {
        return lazyJavaTypeParameterResolver0.typeParametersIndexOffset;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.lazy.TypeParameterResolver
    public TypeParameterDescriptor resolveTypeParameter(JavaTypeParameter javaTypeParameter0) {
        Intrinsics.checkNotNullParameter(javaTypeParameter0, "javaTypeParameter");
        LazyJavaTypeParameterDescriptor lazyJavaTypeParameterDescriptor0 = (LazyJavaTypeParameterDescriptor)this.resolve.invoke(javaTypeParameter0);
        return lazyJavaTypeParameterDescriptor0 != null ? lazyJavaTypeParameterDescriptor0 : this.c.getTypeParameterResolver().resolveTypeParameter(javaTypeParameter0);
    }
}

