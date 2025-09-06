package kotlin.reflect.jvm.internal.impl.load.java.descriptors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.ValueParameterDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaStaticClassScope;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;

public final class UtilKt {
    public static final List copyValueParameters(Collection collection0, Collection collection1, CallableDescriptor callableDescriptor0) {
        Intrinsics.checkNotNullParameter(collection0, "newValueParameterTypes");
        Intrinsics.checkNotNullParameter(collection1, "oldValueParameters");
        Intrinsics.checkNotNullParameter(callableDescriptor0, "newOwner");
        collection0.size();
        collection1.size();
        Iterable iterable0 = CollectionsKt.zip(collection0, collection1);
        ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable0, 10));
        for(Object object0: iterable0) {
            KotlinType kotlinType0 = (KotlinType)((Pair)object0).component1();
            ValueParameterDescriptor valueParameterDescriptor0 = (ValueParameterDescriptor)((Pair)object0).component2();
            int v = valueParameterDescriptor0.getIndex();
            Annotations annotations0 = valueParameterDescriptor0.getAnnotations();
            Name name0 = valueParameterDescriptor0.getName();
            Intrinsics.checkNotNullExpressionValue(name0, "oldParameter.name");
            boolean z = valueParameterDescriptor0.declaresDefaultValue();
            boolean z1 = valueParameterDescriptor0.isCrossinline();
            boolean z2 = valueParameterDescriptor0.isNoinline();
            KotlinType kotlinType1 = valueParameterDescriptor0.getVarargElementType() == null ? null : DescriptorUtilsKt.getModule(callableDescriptor0).getBuiltIns().getArrayElementType(kotlinType0);
            SourceElement sourceElement0 = valueParameterDescriptor0.getSource();
            Intrinsics.checkNotNullExpressionValue(sourceElement0, "oldParameter.source");
            arrayList0.add(new ValueParameterDescriptorImpl(callableDescriptor0, null, v, annotations0, name0, kotlinType0, z, z1, z2, kotlinType1, sourceElement0));
        }
        return arrayList0;
    }

    public static final LazyJavaStaticClassScope getParentJavaStaticClassScope(ClassDescriptor classDescriptor0) {
        Intrinsics.checkNotNullParameter(classDescriptor0, "<this>");
        ClassDescriptor classDescriptor1 = DescriptorUtilsKt.getSuperClassNotAny(classDescriptor0);
        LazyJavaStaticClassScope lazyJavaStaticClassScope0 = null;
        if(classDescriptor1 == null) {
            return null;
        }
        MemberScope memberScope0 = classDescriptor1.getStaticScope();
        if(memberScope0 instanceof LazyJavaStaticClassScope) {
            lazyJavaStaticClassScope0 = (LazyJavaStaticClassScope)memberScope0;
        }
        return lazyJavaStaticClassScope0 == null ? UtilKt.getParentJavaStaticClassScope(classDescriptor1) : lazyJavaStaticClassScope0;
    }
}

