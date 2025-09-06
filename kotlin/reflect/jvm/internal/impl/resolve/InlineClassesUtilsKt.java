package kotlin.reflect.jvm.internal.impl.resolve;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.InlineClassRepresentation;
import kotlin.reflect.jvm.internal.impl.descriptors.MultiFieldValueClassRepresentation;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyGetterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.VariableDescriptor;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;

public final class InlineClassesUtilsKt {
    private static final ClassId JVM_INLINE_ANNOTATION_CLASS_ID;
    private static final FqName JVM_INLINE_ANNOTATION_FQ_NAME;

    static {
        FqName fqName0 = new FqName("kotlin.jvm.JvmInline");
        InlineClassesUtilsKt.JVM_INLINE_ANNOTATION_FQ_NAME = fqName0;
        ClassId classId0 = ClassId.topLevel(fqName0);
        Intrinsics.checkNotNullExpressionValue(classId0, "topLevel(JVM_INLINE_ANNOTATION_FQ_NAME)");
        InlineClassesUtilsKt.JVM_INLINE_ANNOTATION_CLASS_ID = classId0;
    }

    public static final boolean isGetterOfUnderlyingPropertyOfInlineClass(CallableDescriptor callableDescriptor0) {
        Intrinsics.checkNotNullParameter(callableDescriptor0, "<this>");
        if(callableDescriptor0 instanceof PropertyGetterDescriptor) {
            PropertyDescriptor propertyDescriptor0 = ((PropertyGetterDescriptor)callableDescriptor0).getCorrespondingProperty();
            Intrinsics.checkNotNullExpressionValue(propertyDescriptor0, "correspondingProperty");
            return InlineClassesUtilsKt.isUnderlyingPropertyOfInlineClass(propertyDescriptor0);
        }
        return false;
    }

    public static final boolean isInlineClass(DeclarationDescriptor declarationDescriptor0) {
        Intrinsics.checkNotNullParameter(declarationDescriptor0, "<this>");
        return declarationDescriptor0 instanceof ClassDescriptor && ((ClassDescriptor)declarationDescriptor0).getValueClassRepresentation() instanceof InlineClassRepresentation;
    }

    public static final boolean isInlineClassType(KotlinType kotlinType0) {
        Intrinsics.checkNotNullParameter(kotlinType0, "<this>");
        ClassifierDescriptor classifierDescriptor0 = kotlinType0.getConstructor().getDeclarationDescriptor();
        return classifierDescriptor0 == null ? false : InlineClassesUtilsKt.isInlineClass(classifierDescriptor0);
    }

    public static final boolean isMultiFieldValueClass(DeclarationDescriptor declarationDescriptor0) {
        Intrinsics.checkNotNullParameter(declarationDescriptor0, "<this>");
        return declarationDescriptor0 instanceof ClassDescriptor && ((ClassDescriptor)declarationDescriptor0).getValueClassRepresentation() instanceof MultiFieldValueClassRepresentation;
    }

    public static final boolean isUnderlyingPropertyOfInlineClass(VariableDescriptor variableDescriptor0) {
        Intrinsics.checkNotNullParameter(variableDescriptor0, "<this>");
        if(variableDescriptor0.getExtensionReceiverParameter() == null) {
            DeclarationDescriptor declarationDescriptor0 = variableDescriptor0.getContainingDeclaration();
            Name name0 = null;
            ClassDescriptor classDescriptor0 = declarationDescriptor0 instanceof ClassDescriptor ? ((ClassDescriptor)declarationDescriptor0) : null;
            if(classDescriptor0 != null) {
                InlineClassRepresentation inlineClassRepresentation0 = DescriptorUtilsKt.getInlineClassRepresentation(classDescriptor0);
                if(inlineClassRepresentation0 != null) {
                    name0 = inlineClassRepresentation0.getUnderlyingPropertyName();
                }
            }
            return Intrinsics.areEqual(name0, variableDescriptor0.getName());
        }
        return false;
    }

    public static final boolean isValueClass(DeclarationDescriptor declarationDescriptor0) {
        Intrinsics.checkNotNullParameter(declarationDescriptor0, "<this>");
        return InlineClassesUtilsKt.isInlineClass(declarationDescriptor0) || InlineClassesUtilsKt.isMultiFieldValueClass(declarationDescriptor0);
    }

    public static final KotlinType unsubstitutedUnderlyingType(KotlinType kotlinType0) {
        Intrinsics.checkNotNullParameter(kotlinType0, "<this>");
        ClassifierDescriptor classifierDescriptor0 = kotlinType0.getConstructor().getDeclarationDescriptor();
        ClassDescriptor classDescriptor0 = classifierDescriptor0 instanceof ClassDescriptor ? ((ClassDescriptor)classifierDescriptor0) : null;
        if(classDescriptor0 != null) {
            InlineClassRepresentation inlineClassRepresentation0 = DescriptorUtilsKt.getInlineClassRepresentation(classDescriptor0);
            return inlineClassRepresentation0 == null ? null : ((SimpleType)inlineClassRepresentation0.getUnderlyingType());
        }
        return null;
    }
}

