package kotlin.reflect.jvm.internal.impl.resolve;

import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedHashSet;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeAliasDescriptor;
import kotlin.reflect.jvm.internal.impl.incremental.components.NoLookupLocation;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.DescriptorKindFilter;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope.DefaultImpls;

public final class CliSealedClassInheritorsProvider extends SealedClassInheritorsProvider {
    public static final CliSealedClassInheritorsProvider INSTANCE;

    static {
        CliSealedClassInheritorsProvider.INSTANCE = new CliSealedClassInheritorsProvider();
    }

    public Collection computeSealedSubclasses(ClassDescriptor classDescriptor0, boolean z) {
        DeclarationDescriptor declarationDescriptor0;
        Intrinsics.checkNotNullParameter(classDescriptor0, "sealedClass");
        if(classDescriptor0.getModality() != Modality.SEALED) {
            return CollectionsKt.emptyList();
        }
        LinkedHashSet linkedHashSet0 = new LinkedHashSet();
        if(z) {
            Object object0 = null;
            for(Object object1: DescriptorUtilsKt.getParents(classDescriptor0)) {
                if(((DeclarationDescriptor)object1) instanceof PackageFragmentDescriptor) {
                    object0 = object1;
                    break;
                }
            }
            declarationDescriptor0 = (DeclarationDescriptor)object0;
        }
        else {
            declarationDescriptor0 = classDescriptor0.getContainingDeclaration();
        }
        if(declarationDescriptor0 instanceof PackageFragmentDescriptor) {
            CliSealedClassInheritorsProvider.computeSealedSubclasses$collectSubclasses(classDescriptor0, linkedHashSet0, ((PackageFragmentDescriptor)declarationDescriptor0).getMemberScope(), z);
        }
        MemberScope memberScope0 = classDescriptor0.getUnsubstitutedInnerClassesScope();
        Intrinsics.checkNotNullExpressionValue(memberScope0, "sealedClass.unsubstitutedInnerClassesScope");
        CliSealedClassInheritorsProvider.computeSealedSubclasses$collectSubclasses(classDescriptor0, linkedHashSet0, memberScope0, true);
        return CollectionsKt.sortedWith(linkedHashSet0, new kotlin.reflect.jvm.internal.impl.resolve.CliSealedClassInheritorsProvider.computeSealedSubclasses..inlined.sortedBy.1());

        public final class kotlin.reflect.jvm.internal.impl.resolve.CliSealedClassInheritorsProvider.computeSealedSubclasses..inlined.sortedBy.1 implements Comparator {
            @Override
            public final int compare(Object object0, Object object1) {
                return ComparisonsKt.compareValues(DescriptorUtilsKt.getFqNameSafe(((ClassDescriptor)object0)).asString(), DescriptorUtilsKt.getFqNameSafe(((ClassDescriptor)object1)).asString());
            }
        }

    }

    private static final void computeSealedSubclasses$collectSubclasses(ClassDescriptor classDescriptor0, LinkedHashSet linkedHashSet0, MemberScope memberScope0, boolean z) {
        for(Object object0: DefaultImpls.getContributedDescriptors$default(memberScope0, DescriptorKindFilter.CLASSIFIERS, null, 2, null)) {
            DeclarationDescriptor declarationDescriptor0 = (DeclarationDescriptor)object0;
            if(declarationDescriptor0 instanceof ClassDescriptor) {
                ClassDescriptor classDescriptor1 = (ClassDescriptor)declarationDescriptor0;
                if(classDescriptor1.isExpect()) {
                    Name name0 = classDescriptor1.getName();
                    Intrinsics.checkNotNullExpressionValue(name0, "descriptor.name");
                    ClassifierDescriptor classifierDescriptor0 = memberScope0.getContributedClassifier(name0, NoLookupLocation.WHEN_GET_ALL_DESCRIPTORS);
                    if(classifierDescriptor0 instanceof ClassDescriptor) {
                        classDescriptor1 = (ClassDescriptor)classifierDescriptor0;
                    }
                    else {
                        classDescriptor1 = classifierDescriptor0 instanceof TypeAliasDescriptor ? ((TypeAliasDescriptor)classifierDescriptor0).getClassDescriptor() : null;
                    }
                }
                if(classDescriptor1 != null) {
                    if(DescriptorUtils.isDirectSubclass(classDescriptor1, classDescriptor0)) {
                        linkedHashSet0.add(classDescriptor1);
                    }
                    if(z) {
                        MemberScope memberScope1 = classDescriptor1.getUnsubstitutedInnerClassesScope();
                        Intrinsics.checkNotNullExpressionValue(memberScope1, "refinedDescriptor.unsubstitutedInnerClassesScope");
                        CliSealedClassInheritorsProvider.computeSealedSubclasses$collectSubclasses(classDescriptor0, linkedHashSet0, memberScope1, true);
                    }
                }
            }
        }
    }
}

