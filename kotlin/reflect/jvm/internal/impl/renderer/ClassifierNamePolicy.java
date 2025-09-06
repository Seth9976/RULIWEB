package kotlin.reflect.jvm.internal.impl.renderer;

import java.util.ArrayList;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;

public interface ClassifierNamePolicy {
    public static final class FULLY_QUALIFIED implements ClassifierNamePolicy {
        public static final FULLY_QUALIFIED INSTANCE;

        static {
            FULLY_QUALIFIED.INSTANCE = new FULLY_QUALIFIED();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.renderer.ClassifierNamePolicy
        public String renderClassifier(ClassifierDescriptor classifierDescriptor0, DescriptorRenderer descriptorRenderer0) {
            Intrinsics.checkNotNullParameter(classifierDescriptor0, "classifier");
            Intrinsics.checkNotNullParameter(descriptorRenderer0, "renderer");
            if(classifierDescriptor0 instanceof TypeParameterDescriptor) {
                Name name0 = ((TypeParameterDescriptor)classifierDescriptor0).getName();
                Intrinsics.checkNotNullExpressionValue(name0, "classifier.name");
                return descriptorRenderer0.renderName(name0, false);
            }
            FqNameUnsafe fqNameUnsafe0 = DescriptorUtils.getFqName(classifierDescriptor0);
            Intrinsics.checkNotNullExpressionValue(fqNameUnsafe0, "getFqName(classifier)");
            return descriptorRenderer0.renderFqName(fqNameUnsafe0);
        }
    }

    public static final class SHORT implements ClassifierNamePolicy {
        public static final SHORT INSTANCE;

        static {
            SHORT.INSTANCE = new SHORT();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.renderer.ClassifierNamePolicy
        public String renderClassifier(ClassifierDescriptor classifierDescriptor0, DescriptorRenderer descriptorRenderer0) {
            Intrinsics.checkNotNullParameter(classifierDescriptor0, "classifier");
            Intrinsics.checkNotNullParameter(descriptorRenderer0, "renderer");
            if(classifierDescriptor0 instanceof TypeParameterDescriptor) {
                Name name0 = ((TypeParameterDescriptor)classifierDescriptor0).getName();
                Intrinsics.checkNotNullExpressionValue(name0, "classifier.name");
                return descriptorRenderer0.renderName(name0, false);
            }
            ArrayList arrayList0 = new ArrayList();
            DeclarationDescriptor declarationDescriptor0 = classifierDescriptor0;
            do {
                arrayList0.add(declarationDescriptor0.getName());
                declarationDescriptor0 = declarationDescriptor0.getContainingDeclaration();
            }
            while(declarationDescriptor0 instanceof ClassDescriptor);
            return RenderingUtilsKt.renderFqName(CollectionsKt.asReversedMutable(arrayList0));
        }
    }

    public static final class SOURCE_CODE_QUALIFIED implements ClassifierNamePolicy {
        public static final SOURCE_CODE_QUALIFIED INSTANCE;

        static {
            SOURCE_CODE_QUALIFIED.INSTANCE = new SOURCE_CODE_QUALIFIED();
        }

        private final String qualifiedNameForSourceCode(ClassifierDescriptor classifierDescriptor0) {
            Name name0 = classifierDescriptor0.getName();
            Intrinsics.checkNotNullExpressionValue(name0, "descriptor.name");
            String s = RenderingUtilsKt.render(name0);
            if(!(classifierDescriptor0 instanceof TypeParameterDescriptor)) {
                DeclarationDescriptor declarationDescriptor0 = classifierDescriptor0.getContainingDeclaration();
                Intrinsics.checkNotNullExpressionValue(declarationDescriptor0, "descriptor.containingDeclaration");
                String s1 = this.qualifierName(declarationDescriptor0);
                return s1 == null || Intrinsics.areEqual(s1, "") ? s : s1 + '.' + s;
            }
            return s;
        }

        private final String qualifierName(DeclarationDescriptor declarationDescriptor0) {
            if(declarationDescriptor0 instanceof ClassDescriptor) {
                return this.qualifiedNameForSourceCode(((ClassifierDescriptor)declarationDescriptor0));
            }
            if(declarationDescriptor0 instanceof PackageFragmentDescriptor) {
                FqNameUnsafe fqNameUnsafe0 = ((PackageFragmentDescriptor)declarationDescriptor0).getFqName().toUnsafe();
                Intrinsics.checkNotNullExpressionValue(fqNameUnsafe0, "descriptor.fqName.toUnsafe()");
                return RenderingUtilsKt.render(fqNameUnsafe0);
            }
            return null;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.renderer.ClassifierNamePolicy
        public String renderClassifier(ClassifierDescriptor classifierDescriptor0, DescriptorRenderer descriptorRenderer0) {
            Intrinsics.checkNotNullParameter(classifierDescriptor0, "classifier");
            Intrinsics.checkNotNullParameter(descriptorRenderer0, "renderer");
            return this.qualifiedNameForSourceCode(classifierDescriptor0);
        }
    }

    String renderClassifier(ClassifierDescriptor arg1, DescriptorRenderer arg2);
}

