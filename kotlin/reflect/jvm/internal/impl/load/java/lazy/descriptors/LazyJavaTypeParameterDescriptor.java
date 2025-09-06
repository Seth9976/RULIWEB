package kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.SupertypeLoopChecker;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.AbstractLazyTypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaAnnotations;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.types.JavaTypeAttributes;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.types.JavaTypeAttributesKt;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClassifierType;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaTypeParameter;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeUsage;
import kotlin.reflect.jvm.internal.impl.types.Variance;

public final class LazyJavaTypeParameterDescriptor extends AbstractLazyTypeParameterDescriptor {
    private final LazyJavaResolverContext c;
    private final JavaTypeParameter javaTypeParameter;

    public LazyJavaTypeParameterDescriptor(LazyJavaResolverContext lazyJavaResolverContext0, JavaTypeParameter javaTypeParameter0, int v, DeclarationDescriptor declarationDescriptor0) {
        Intrinsics.checkNotNullParameter(lazyJavaResolverContext0, "c");
        Intrinsics.checkNotNullParameter(javaTypeParameter0, "javaTypeParameter");
        Intrinsics.checkNotNullParameter(declarationDescriptor0, "containingDeclaration");
        StorageManager storageManager0 = lazyJavaResolverContext0.getStorageManager();
        LazyJavaAnnotations lazyJavaAnnotations0 = new LazyJavaAnnotations(lazyJavaResolverContext0, javaTypeParameter0, false, 4, null);
        Name name0 = javaTypeParameter0.getName();
        SupertypeLoopChecker supertypeLoopChecker0 = lazyJavaResolverContext0.getComponents().getSupertypeLoopChecker();
        super(storageManager0, declarationDescriptor0, lazyJavaAnnotations0, name0, Variance.INVARIANT, false, v, SourceElement.NO_SOURCE, supertypeLoopChecker0);
        this.c = lazyJavaResolverContext0;
        this.javaTypeParameter = javaTypeParameter0;
    }

    private final List computeNotEnhancedBounds() {
        Collection collection0 = this.javaTypeParameter.getUpperBounds();
        if(collection0.isEmpty()) {
            SimpleType simpleType0 = this.c.getModule().getBuiltIns().getAnyType();
            Intrinsics.checkNotNullExpressionValue(simpleType0, "c.module.builtIns.anyType");
            SimpleType simpleType1 = this.c.getModule().getBuiltIns().getNullableAnyType();
            Intrinsics.checkNotNullExpressionValue(simpleType1, "c.module.builtIns.nullableAnyType");
            return CollectionsKt.listOf(KotlinTypeFactory.flexibleType(simpleType0, simpleType1));
        }
        ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(collection0, 10));
        for(Object object0: collection0) {
            JavaTypeAttributes javaTypeAttributes0 = JavaTypeAttributesKt.toAttributes$default(TypeUsage.COMMON, false, false, this, 3, null);
            arrayList0.add(this.c.getTypeResolver().transformJavaType(((JavaClassifierType)object0), javaTypeAttributes0));
        }
        return arrayList0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.AbstractTypeParameterDescriptor
    protected List processBoundsWithoutCycles(List list0) {
        Intrinsics.checkNotNullParameter(list0, "bounds");
        return this.c.getComponents().getSignatureEnhancement().enhanceTypeParameterBounds(this, list0, this.c);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.AbstractTypeParameterDescriptor
    protected void reportSupertypeLoopError(KotlinType kotlinType0) {
        Intrinsics.checkNotNullParameter(kotlinType0, "type");
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.AbstractTypeParameterDescriptor
    protected List resolveUpperBounds() {
        return this.computeNotEnhancedBounds();
    }
}

