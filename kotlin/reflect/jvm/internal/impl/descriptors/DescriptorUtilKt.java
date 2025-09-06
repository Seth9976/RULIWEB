package kotlin.reflect.jvm.internal.impl.descriptors;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.InlineClassesUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import kotlin.reflect.jvm.internal.impl.util.OperatorNameConventions;

public final class DescriptorUtilKt {
    public static final ClassifierDescriptor getTopLevelContainingClassifier(DeclarationDescriptor declarationDescriptor0) {
        Intrinsics.checkNotNullParameter(declarationDescriptor0, "<this>");
        DeclarationDescriptor declarationDescriptor1 = declarationDescriptor0.getContainingDeclaration();
        if(declarationDescriptor1 != null && !(declarationDescriptor0 instanceof PackageFragmentDescriptor)) {
            if(!DescriptorUtilKt.isTopLevelInPackage(declarationDescriptor1)) {
                return DescriptorUtilKt.getTopLevelContainingClassifier(declarationDescriptor1);
            }
            return declarationDescriptor1 instanceof ClassifierDescriptor ? ((ClassifierDescriptor)declarationDescriptor1) : null;
        }
        return null;
    }

    public static final boolean isTopLevelInPackage(DeclarationDescriptor declarationDescriptor0) {
        Intrinsics.checkNotNullParameter(declarationDescriptor0, "<this>");
        return declarationDescriptor0.getContainingDeclaration() instanceof PackageFragmentDescriptor;
    }

    public static final boolean isTypedEqualsInValueClass(FunctionDescriptor functionDescriptor0) {
        Intrinsics.checkNotNullParameter(functionDescriptor0, "<this>");
        DeclarationDescriptor declarationDescriptor0 = functionDescriptor0.getContainingDeclaration();
        ClassDescriptor classDescriptor0 = null;
        ClassDescriptor classDescriptor1 = declarationDescriptor0 instanceof ClassDescriptor ? ((ClassDescriptor)declarationDescriptor0) : null;
        if(classDescriptor1 != null) {
            if(InlineClassesUtilsKt.isValueClass(classDescriptor1)) {
                classDescriptor0 = classDescriptor1;
            }
            if(classDescriptor0 != null) {
                SimpleType simpleType0 = classDescriptor0.getDefaultType();
                if(simpleType0 != null) {
                    KotlinType kotlinType0 = TypeUtilsKt.replaceArgumentsWithStarProjections(simpleType0);
                    if(kotlinType0 != null) {
                        KotlinType kotlinType1 = functionDescriptor0.getReturnType();
                        if(kotlinType1 == null) {
                            return false;
                        }
                        if(Intrinsics.areEqual(functionDescriptor0.getName(), OperatorNameConventions.EQUALS) && (TypeUtilsKt.isBoolean(kotlinType1) || TypeUtilsKt.isNothing(kotlinType1)) && functionDescriptor0.getValueParameters().size() == 1) {
                            KotlinType kotlinType2 = ((ValueParameterDescriptor)functionDescriptor0.getValueParameters().get(0)).getType();
                            Intrinsics.checkNotNullExpressionValue(kotlinType2, "valueParameters[0].type");
                            return Intrinsics.areEqual(TypeUtilsKt.replaceArgumentsWithStarProjections(kotlinType2), kotlinType0) && functionDescriptor0.getContextReceiverParameters().isEmpty() && functionDescriptor0.getExtensionReceiverParameter() == null;
                        }
                    }
                }
            }
        }
        return false;
    }

    public static final ClassDescriptor resolveClassByFqName(ModuleDescriptor moduleDescriptor0, FqName fqName0, LookupLocation lookupLocation0) {
        Intrinsics.checkNotNullParameter(moduleDescriptor0, "<this>");
        Intrinsics.checkNotNullParameter(fqName0, "fqName");
        Intrinsics.checkNotNullParameter(lookupLocation0, "lookupLocation");
        if(fqName0.isRoot()) {
            return null;
        }
        FqName fqName1 = fqName0.parent();
        Intrinsics.checkNotNullExpressionValue(fqName1, "fqName.parent()");
        MemberScope memberScope0 = moduleDescriptor0.getPackage(fqName1).getMemberScope();
        Name name0 = fqName0.shortName();
        Intrinsics.checkNotNullExpressionValue(name0, "fqName.shortName()");
        ClassifierDescriptor classifierDescriptor0 = memberScope0.getContributedClassifier(name0, lookupLocation0);
        ClassDescriptor classDescriptor0 = classifierDescriptor0 instanceof ClassDescriptor ? ((ClassDescriptor)classifierDescriptor0) : null;
        if(classDescriptor0 != null) {
            return classDescriptor0;
        }
        FqName fqName2 = fqName0.parent();
        Intrinsics.checkNotNullExpressionValue(fqName2, "fqName.parent()");
        ClassDescriptor classDescriptor1 = DescriptorUtilKt.resolveClassByFqName(moduleDescriptor0, fqName2, lookupLocation0);
        if(classDescriptor1 != null) {
            MemberScope memberScope1 = classDescriptor1.getUnsubstitutedInnerClassesScope();
            if(memberScope1 != null) {
                Name name1 = fqName0.shortName();
                Intrinsics.checkNotNullExpressionValue(name1, "fqName.shortName()");
                ClassifierDescriptor classifierDescriptor1 = memberScope1.getContributedClassifier(name1, lookupLocation0);
                return classifierDescriptor1 instanceof ClassDescriptor ? ((ClassDescriptor)classifierDescriptor1) : null;
            }
        }
        return null;
    }
}

