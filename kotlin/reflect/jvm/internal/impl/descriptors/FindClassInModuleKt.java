package kotlin.reflect.jvm.internal.impl.descriptors;

import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;
import kotlin.reflect.jvm.internal.impl.incremental.components.NoLookupLocation;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.ResolutionAnchorProviderKt;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.sequences.SequencesKt;

public final class FindClassInModuleKt {
    public static final ClassDescriptor findClassAcrossModuleDependencies(ModuleDescriptor moduleDescriptor0, ClassId classId0) {
        Intrinsics.checkNotNullParameter(moduleDescriptor0, "<this>");
        Intrinsics.checkNotNullParameter(classId0, "classId");
        ClassifierDescriptor classifierDescriptor0 = FindClassInModuleKt.findClassifierAcrossModuleDependencies(moduleDescriptor0, classId0);
        return classifierDescriptor0 instanceof ClassDescriptor ? ((ClassDescriptor)classifierDescriptor0) : null;
    }

    public static final ClassifierDescriptor findClassifierAcrossModuleDependencies(ModuleDescriptor moduleDescriptor0, ClassId classId0) {
        Intrinsics.checkNotNullParameter(moduleDescriptor0, "<this>");
        Intrinsics.checkNotNullParameter(classId0, "classId");
        ModuleDescriptor moduleDescriptor1 = ResolutionAnchorProviderKt.getResolutionAnchorIfAny(moduleDescriptor0);
        if(moduleDescriptor1 == null) {
            FqName fqName0 = classId0.getPackageFqName();
            Intrinsics.checkNotNullExpressionValue(fqName0, "classId.packageFqName");
            PackageViewDescriptor packageViewDescriptor0 = moduleDescriptor0.getPackage(fqName0);
            List list0 = classId0.getRelativeClassName().pathSegments();
            Intrinsics.checkNotNullExpressionValue(list0, "classId.relativeClassName.pathSegments()");
            MemberScope memberScope0 = packageViewDescriptor0.getMemberScope();
            Object object0 = CollectionsKt.first(list0);
            Intrinsics.checkNotNullExpressionValue(object0, "segments.first()");
            ClassifierDescriptor classifierDescriptor0 = memberScope0.getContributedClassifier(((Name)object0), NoLookupLocation.FROM_DESERIALIZATION);
            if(classifierDescriptor0 == null) {
                return null;
            }
            for(Object object1: list0.subList(1, list0.size())) {
                if(!(classifierDescriptor0 instanceof ClassDescriptor)) {
                    return null;
                }
                MemberScope memberScope1 = ((ClassDescriptor)classifierDescriptor0).getUnsubstitutedInnerClassesScope();
                Intrinsics.checkNotNullExpressionValue(((Name)object1), "name");
                ClassifierDescriptor classifierDescriptor1 = memberScope1.getContributedClassifier(((Name)object1), NoLookupLocation.FROM_DESERIALIZATION);
                ClassDescriptor classDescriptor0 = classifierDescriptor1 instanceof ClassDescriptor ? ((ClassDescriptor)classifierDescriptor1) : null;
                if(classDescriptor0 != null) {
                    classifierDescriptor0 = classDescriptor0;
                    continue;
                }
                return null;
            }
            return classifierDescriptor0;
        }
        FqName fqName1 = classId0.getPackageFqName();
        Intrinsics.checkNotNullExpressionValue(fqName1, "classId.packageFqName");
        PackageViewDescriptor packageViewDescriptor1 = moduleDescriptor1.getPackage(fqName1);
        List list1 = classId0.getRelativeClassName().pathSegments();
        Intrinsics.checkNotNullExpressionValue(list1, "classId.relativeClassName.pathSegments()");
        MemberScope memberScope2 = packageViewDescriptor1.getMemberScope();
        Object object2 = CollectionsKt.first(list1);
        Intrinsics.checkNotNullExpressionValue(object2, "segments.first()");
        ClassifierDescriptor classifierDescriptor2 = memberScope2.getContributedClassifier(((Name)object2), NoLookupLocation.FROM_DESERIALIZATION);
        if(classifierDescriptor2 == null) {
        label_51:
            classifierDescriptor2 = null;
        }
        else {
            for(Object object3: list1.subList(1, list1.size())) {
                if(!(classifierDescriptor2 instanceof ClassDescriptor)) {
                    goto label_51;
                }
                MemberScope memberScope3 = ((ClassDescriptor)classifierDescriptor2).getUnsubstitutedInnerClassesScope();
                Intrinsics.checkNotNullExpressionValue(((Name)object3), "name");
                ClassifierDescriptor classifierDescriptor3 = memberScope3.getContributedClassifier(((Name)object3), NoLookupLocation.FROM_DESERIALIZATION);
                ClassDescriptor classDescriptor1 = classifierDescriptor3 instanceof ClassDescriptor ? ((ClassDescriptor)classifierDescriptor3) : null;
                if(classDescriptor1 == null) {
                    goto label_51;
                }
                classifierDescriptor2 = classDescriptor1;
            }
        }
        if(classifierDescriptor2 == null) {
            FqName fqName2 = classId0.getPackageFqName();
            Intrinsics.checkNotNullExpressionValue(fqName2, "classId.packageFqName");
            PackageViewDescriptor packageViewDescriptor2 = moduleDescriptor0.getPackage(fqName2);
            List list2 = classId0.getRelativeClassName().pathSegments();
            Intrinsics.checkNotNullExpressionValue(list2, "classId.relativeClassName.pathSegments()");
            MemberScope memberScope4 = packageViewDescriptor2.getMemberScope();
            Object object4 = CollectionsKt.first(list2);
            Intrinsics.checkNotNullExpressionValue(object4, "segments.first()");
            ClassifierDescriptor classifierDescriptor4 = memberScope4.getContributedClassifier(((Name)object4), NoLookupLocation.FROM_DESERIALIZATION);
            if(classifierDescriptor4 == null) {
                return null;
            }
            for(Object object5: list2.subList(1, list2.size())) {
                if(!(classifierDescriptor4 instanceof ClassDescriptor)) {
                    return null;
                }
                MemberScope memberScope5 = ((ClassDescriptor)classifierDescriptor4).getUnsubstitutedInnerClassesScope();
                Intrinsics.checkNotNullExpressionValue(((Name)object5), "name");
                ClassifierDescriptor classifierDescriptor5 = memberScope5.getContributedClassifier(((Name)object5), NoLookupLocation.FROM_DESERIALIZATION);
                ClassDescriptor classDescriptor2 = classifierDescriptor5 instanceof ClassDescriptor ? ((ClassDescriptor)classifierDescriptor5) : null;
                if(classDescriptor2 != null) {
                    classifierDescriptor4 = classDescriptor2;
                    continue;
                }
                return null;
            }
            return classifierDescriptor4;
        }
        return classifierDescriptor2;
    }

    public static final ClassDescriptor findNonGenericClassAcrossDependencies(ModuleDescriptor moduleDescriptor0, ClassId classId0, NotFoundClasses notFoundClasses0) {
        Intrinsics.checkNotNullParameter(moduleDescriptor0, "<this>");
        Intrinsics.checkNotNullParameter(classId0, "classId");
        Intrinsics.checkNotNullParameter(notFoundClasses0, "notFoundClasses");
        ClassDescriptor classDescriptor0 = FindClassInModuleKt.findClassAcrossModuleDependencies(moduleDescriptor0, classId0);
        return classDescriptor0 == null ? notFoundClasses0.getClass(classId0, SequencesKt.toList(SequencesKt.map(SequencesKt.generateSequence(classId0, kotlin.reflect.jvm.internal.impl.descriptors.FindClassInModuleKt.findNonGenericClassAcrossDependencies.typeParametersCount.1.INSTANCE), kotlin.reflect.jvm.internal.impl.descriptors.FindClassInModuleKt.findNonGenericClassAcrossDependencies.typeParametersCount.2.INSTANCE))) : classDescriptor0;

        final class kotlin.reflect.jvm.internal.impl.descriptors.FindClassInModuleKt.findNonGenericClassAcrossDependencies.typeParametersCount.1 extends FunctionReference implements Function1 {
            public static final kotlin.reflect.jvm.internal.impl.descriptors.FindClassInModuleKt.findNonGenericClassAcrossDependencies.typeParametersCount.1 INSTANCE;

            static {
                kotlin.reflect.jvm.internal.impl.descriptors.FindClassInModuleKt.findNonGenericClassAcrossDependencies.typeParametersCount.1.INSTANCE = new kotlin.reflect.jvm.internal.impl.descriptors.FindClassInModuleKt.findNonGenericClassAcrossDependencies.typeParametersCount.1();
            }

            kotlin.reflect.jvm.internal.impl.descriptors.FindClassInModuleKt.findNonGenericClassAcrossDependencies.typeParametersCount.1() {
                super(1);
            }

            @Override  // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
            public final String getName() {
                return "getOuterClassId";
            }

            @Override  // kotlin.jvm.internal.CallableReference
            public final KDeclarationContainer getOwner() {
                return Reflection.getOrCreateKotlinClass(ClassId.class);
            }

            @Override  // kotlin.jvm.internal.CallableReference
            public final String getSignature() {
                return "getOuterClassId()Lorg/jetbrains/kotlin/name/ClassId;";
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((ClassId)object0));
            }

            public final ClassId invoke(ClassId classId0) {
                Intrinsics.checkNotNullParameter(classId0, "p0");
                return classId0.getOuterClassId();
            }
        }


        final class kotlin.reflect.jvm.internal.impl.descriptors.FindClassInModuleKt.findNonGenericClassAcrossDependencies.typeParametersCount.2 extends Lambda implements Function1 {
            public static final kotlin.reflect.jvm.internal.impl.descriptors.FindClassInModuleKt.findNonGenericClassAcrossDependencies.typeParametersCount.2 INSTANCE;

            static {
                kotlin.reflect.jvm.internal.impl.descriptors.FindClassInModuleKt.findNonGenericClassAcrossDependencies.typeParametersCount.2.INSTANCE = new kotlin.reflect.jvm.internal.impl.descriptors.FindClassInModuleKt.findNonGenericClassAcrossDependencies.typeParametersCount.2();
            }

            kotlin.reflect.jvm.internal.impl.descriptors.FindClassInModuleKt.findNonGenericClassAcrossDependencies.typeParametersCount.2() {
                super(1);
            }

            public final Integer invoke(ClassId classId0) {
                Intrinsics.checkNotNullParameter(classId0, "it");
                return 0;
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((ClassId)object0));
            }
        }

    }

    public static final TypeAliasDescriptor findTypeAliasAcrossModuleDependencies(ModuleDescriptor moduleDescriptor0, ClassId classId0) {
        Intrinsics.checkNotNullParameter(moduleDescriptor0, "<this>");
        Intrinsics.checkNotNullParameter(classId0, "classId");
        ClassifierDescriptor classifierDescriptor0 = FindClassInModuleKt.findClassifierAcrossModuleDependencies(moduleDescriptor0, classId0);
        return classifierDescriptor0 instanceof TypeAliasDescriptor ? ((TypeAliasDescriptor)classifierDescriptor0) : null;
    }
}

