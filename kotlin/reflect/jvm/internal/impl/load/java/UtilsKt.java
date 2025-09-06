package kotlin.reflect.jvm.internal.impl.load.java;

import java.util.Iterator;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibility;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibility;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaMethodDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaAnnotations;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaWildcardType;
import kotlin.reflect.jvm.internal.impl.name.FqName;

public final class UtilsKt {
    public static final AnnotationDescriptor extractNullabilityAnnotationOnBoundedWildcard(LazyJavaResolverContext lazyJavaResolverContext0, JavaWildcardType javaWildcardType0) {
        Object object0;
        Intrinsics.checkNotNullParameter(lazyJavaResolverContext0, "c");
        Intrinsics.checkNotNullParameter(javaWildcardType0, "wildcardType");
        if(javaWildcardType0.getBound() == null) {
            throw new IllegalArgumentException("Nullability annotations on unbounded wildcards aren\'t supported");
        }
        Iterator iterator0 = new LazyJavaAnnotations(lazyJavaResolverContext0, javaWildcardType0, false, 4, null).iterator();
    alab1:
        while(true) {
            object0 = null;
            if(!iterator0.hasNext()) {
                break;
            }
            object0 = iterator0.next();
            AnnotationDescriptor annotationDescriptor0 = (AnnotationDescriptor)object0;
            FqName[] arr_fqName = JavaNullabilityAnnotationSettingsKt.getRXJAVA3_ANNOTATIONS();
            for(int v = 0; v < arr_fqName.length; ++v) {
                FqName fqName0 = arr_fqName[v];
                if(Intrinsics.areEqual(annotationDescriptor0.getFqName(), fqName0)) {
                    break alab1;
                }
            }
        }
        return (AnnotationDescriptor)object0;
    }

    public static final boolean hasErasedValueParameters(CallableMemberDescriptor callableMemberDescriptor0) {
        Intrinsics.checkNotNullParameter(callableMemberDescriptor0, "memberDescriptor");
        return callableMemberDescriptor0 instanceof FunctionDescriptor && Intrinsics.areEqual(callableMemberDescriptor0.getUserData(JavaMethodDescriptor.HAS_ERASED_VALUE_PARAMETERS), Boolean.TRUE);
    }

    public static final boolean isJspecifyEnabledInStrictMode(JavaTypeEnhancementState javaTypeEnhancementState0) {
        Intrinsics.checkNotNullParameter(javaTypeEnhancementState0, "javaTypeEnhancementState");
        return javaTypeEnhancementState0.getGetReportLevelForAnnotation().invoke(JavaNullabilityAnnotationSettingsKt.getJSPECIFY_ANNOTATIONS_PACKAGE()) == ReportLevel.STRICT;
    }

    public static final DescriptorVisibility toDescriptorVisibility(Visibility visibility0) {
        Intrinsics.checkNotNullParameter(visibility0, "<this>");
        DescriptorVisibility descriptorVisibility0 = JavaDescriptorVisibilities.toDescriptorVisibility(visibility0);
        Intrinsics.checkNotNullExpressionValue(descriptorVisibility0, "toDescriptorVisibility(this)");
        return descriptorVisibility0;
    }
}

