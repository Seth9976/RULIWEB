package kotlin.reflect.jvm.internal.impl.descriptors.annotations;

import java.util.Map;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.error.ErrorUtils;

public interface AnnotationDescriptor {
    public static final class DefaultImpls {
        public static FqName getFqName(AnnotationDescriptor annotationDescriptor0) {
            ClassDescriptor classDescriptor0 = DescriptorUtilsKt.getAnnotationClass(annotationDescriptor0);
            if(classDescriptor0 != null) {
                if(ErrorUtils.isError(classDescriptor0)) {
                    classDescriptor0 = null;
                }
                return classDescriptor0 == null ? null : DescriptorUtilsKt.fqNameOrNull(classDescriptor0);
            }
            return null;
        }
    }

    Map getAllValueArguments();

    FqName getFqName();

    SourceElement getSource();

    KotlinType getType();
}

