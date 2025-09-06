package kotlin.reflect.jvm.internal.impl.resolve.jvm;

import java.util.Collection;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.StandardNames;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.InlineClassesUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;

public final class InlineClassManglingRulesKt {
    private static final boolean isDontMangleClass(ClassDescriptor classDescriptor0) {
        return Intrinsics.areEqual(DescriptorUtilsKt.getFqNameSafe(classDescriptor0), StandardNames.RESULT_FQ_NAME);
    }

    public static final boolean isInlineClassThatRequiresMangling(DeclarationDescriptor declarationDescriptor0) {
        Intrinsics.checkNotNullParameter(declarationDescriptor0, "<this>");
        return InlineClassesUtilsKt.isInlineClass(declarationDescriptor0) && !InlineClassManglingRulesKt.isDontMangleClass(((ClassDescriptor)declarationDescriptor0));
    }

    public static final boolean isInlineClassThatRequiresMangling(KotlinType kotlinType0) {
        Intrinsics.checkNotNullParameter(kotlinType0, "<this>");
        ClassifierDescriptor classifierDescriptor0 = kotlinType0.getConstructor().getDeclarationDescriptor();
        return classifierDescriptor0 != null && InlineClassManglingRulesKt.isInlineClassThatRequiresMangling(classifierDescriptor0);
    }

    private static final boolean isTypeParameterWithUpperBoundThatRequiresMangling(KotlinType kotlinType0) {
        ClassifierDescriptor classifierDescriptor0 = kotlinType0.getConstructor().getDeclarationDescriptor();
        TypeParameterDescriptor typeParameterDescriptor0 = classifierDescriptor0 instanceof TypeParameterDescriptor ? ((TypeParameterDescriptor)classifierDescriptor0) : null;
        return typeParameterDescriptor0 == null ? false : InlineClassManglingRulesKt.requiresFunctionNameManglingInParameterTypes(TypeUtilsKt.getRepresentativeUpperBound(typeParameterDescriptor0));
    }

    // 去混淆评级： 低(20)
    private static final boolean requiresFunctionNameManglingInParameterTypes(KotlinType kotlinType0) {
        return InlineClassManglingRulesKt.isInlineClassThatRequiresMangling(kotlinType0) || InlineClassManglingRulesKt.isTypeParameterWithUpperBoundThatRequiresMangling(kotlinType0);
    }

    public static final boolean shouldHideConstructorDueToInlineClassTypeValueParameters(CallableMemberDescriptor callableMemberDescriptor0) {
        Intrinsics.checkNotNullParameter(callableMemberDescriptor0, "descriptor");
        ClassConstructorDescriptor classConstructorDescriptor0 = callableMemberDescriptor0 instanceof ClassConstructorDescriptor ? ((ClassConstructorDescriptor)callableMemberDescriptor0) : null;
        if(classConstructorDescriptor0 == null) {
            return false;
        }
        if(DescriptorVisibilities.isPrivate(classConstructorDescriptor0.getVisibility())) {
            return false;
        }
        ClassDescriptor classDescriptor0 = classConstructorDescriptor0.getConstructedClass();
        Intrinsics.checkNotNullExpressionValue(classDescriptor0, "constructorDescriptor.constructedClass");
        if(InlineClassesUtilsKt.isInlineClass(classDescriptor0)) {
            return false;
        }
        if(DescriptorUtils.isSealedClass(classConstructorDescriptor0.getConstructedClass())) {
            return false;
        }
        List list0 = classConstructorDescriptor0.getValueParameters();
        Intrinsics.checkNotNullExpressionValue(list0, "constructorDescriptor.valueParameters");
        if(list0 instanceof Collection && list0.isEmpty()) {
            return false;
        }
        for(Object object0: list0) {
            KotlinType kotlinType0 = ((ValueParameterDescriptor)object0).getType();
            Intrinsics.checkNotNullExpressionValue(kotlinType0, "it.type");
            if(InlineClassManglingRulesKt.requiresFunctionNameManglingInParameterTypes(kotlinType0)) {
                return true;
            }
            if(false) {
                break;
            }
        }
        return false;
    }
}

