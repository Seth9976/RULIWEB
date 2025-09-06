package kotlin.reflect.jvm.internal.impl.resolve;

import java.util.Collection;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor.Kind;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner.Default;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;

public final class DescriptorEquivalenceForOverrides {
    public static final DescriptorEquivalenceForOverrides INSTANCE;

    static {
        DescriptorEquivalenceForOverrides.INSTANCE = new DescriptorEquivalenceForOverrides();
    }

    // 检测为 Lambda 实现
    static boolean accessor$DescriptorEquivalenceForOverrides$lambda0(boolean z, CallableDescriptor callableDescriptor0, CallableDescriptor callableDescriptor1, TypeConstructor typeConstructor0, TypeConstructor typeConstructor1) [...]

    public final boolean areCallableDescriptorsEquivalent(CallableDescriptor callableDescriptor0, CallableDescriptor callableDescriptor1, boolean z, boolean z1, boolean z2, KotlinTypeRefiner kotlinTypeRefiner0) {
        Intrinsics.checkNotNullParameter(callableDescriptor0, "a");
        Intrinsics.checkNotNullParameter(callableDescriptor1, "b");
        Intrinsics.checkNotNullParameter(kotlinTypeRefiner0, "kotlinTypeRefiner");
        if(Intrinsics.areEqual(callableDescriptor0, callableDescriptor1)) {
            return true;
        }
        if(!Intrinsics.areEqual(callableDescriptor0.getName(), callableDescriptor1.getName())) {
            return false;
        }
        if(z1 && callableDescriptor0 instanceof MemberDescriptor && callableDescriptor1 instanceof MemberDescriptor && ((MemberDescriptor)callableDescriptor0).isExpect() != ((MemberDescriptor)callableDescriptor1).isExpect()) {
            return false;
        }
        if(Intrinsics.areEqual(callableDescriptor0.getContainingDeclaration(), callableDescriptor1.getContainingDeclaration())) {
            if(!z) {
                return false;
            }
            if(!Intrinsics.areEqual(this.singleSource(callableDescriptor0), this.singleSource(callableDescriptor1))) {
                return false;
            }
        }
        if(DescriptorUtils.isLocal(callableDescriptor0) || DescriptorUtils.isLocal(callableDescriptor1) || !this.ownersEquivalent(callableDescriptor0, callableDescriptor1, kotlin.reflect.jvm.internal.impl.resolve.DescriptorEquivalenceForOverrides.areCallableDescriptorsEquivalent.1.INSTANCE, z)) {
            return false;
        }
        OverridingUtil overridingUtil0 = OverridingUtil.create(kotlinTypeRefiner0, (TypeConstructor typeConstructor0, TypeConstructor typeConstructor1) -> DescriptorEquivalenceForOverrides.areCallableDescriptorsEquivalent$lambda$0(z, callableDescriptor0, callableDescriptor1, typeConstructor0, typeConstructor1));
        Intrinsics.checkNotNullExpressionValue(overridingUtil0, "create(kotlinTypeRefiner…= a && y == b }\n        }");
        return overridingUtil0.isOverridableBy(callableDescriptor0, callableDescriptor1, null, !z2).getResult() == Result.OVERRIDABLE && overridingUtil0.isOverridableBy(callableDescriptor1, callableDescriptor0, null, !z2).getResult() == Result.OVERRIDABLE;

        final class kotlin.reflect.jvm.internal.impl.resolve.DescriptorEquivalenceForOverrides.areCallableDescriptorsEquivalent.1 extends Lambda implements Function2 {
            public static final kotlin.reflect.jvm.internal.impl.resolve.DescriptorEquivalenceForOverrides.areCallableDescriptorsEquivalent.1 INSTANCE;

            static {
                kotlin.reflect.jvm.internal.impl.resolve.DescriptorEquivalenceForOverrides.areCallableDescriptorsEquivalent.1.INSTANCE = new kotlin.reflect.jvm.internal.impl.resolve.DescriptorEquivalenceForOverrides.areCallableDescriptorsEquivalent.1();
            }

            kotlin.reflect.jvm.internal.impl.resolve.DescriptorEquivalenceForOverrides.areCallableDescriptorsEquivalent.1() {
                super(2);
            }

            public final Boolean invoke(DeclarationDescriptor declarationDescriptor0, DeclarationDescriptor declarationDescriptor1) {
                return false;
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(((DeclarationDescriptor)object0), ((DeclarationDescriptor)object1));
            }
        }


        final class kotlin.reflect.jvm.internal.impl.resolve.DescriptorEquivalenceForOverrides.areCallableDescriptorsEquivalent.overridingUtil.1.1 extends Lambda implements Function2 {
            final CallableDescriptor $a;
            final CallableDescriptor $b;

            kotlin.reflect.jvm.internal.impl.resolve.DescriptorEquivalenceForOverrides.areCallableDescriptorsEquivalent.overridingUtil.1.1(CallableDescriptor callableDescriptor0, CallableDescriptor callableDescriptor1) {
                this.$a = callableDescriptor0;
                this.$b = callableDescriptor1;
                super(2);
            }

            // 去混淆评级： 低(20)
            public final Boolean invoke(DeclarationDescriptor declarationDescriptor0, DeclarationDescriptor declarationDescriptor1) {
                return !Intrinsics.areEqual(declarationDescriptor0, this.$a) || !Intrinsics.areEqual(declarationDescriptor1, this.$b) ? false : true;
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(((DeclarationDescriptor)object0), ((DeclarationDescriptor)object1));
            }
        }

    }

    public static boolean areCallableDescriptorsEquivalent$default(DescriptorEquivalenceForOverrides descriptorEquivalenceForOverrides0, CallableDescriptor callableDescriptor0, CallableDescriptor callableDescriptor1, boolean z, boolean z1, boolean z2, KotlinTypeRefiner kotlinTypeRefiner0, int v, Object object0) {
        boolean z3 = (v & 8) == 0 ? z1 : true;
        return (v & 16) == 0 ? descriptorEquivalenceForOverrides0.areCallableDescriptorsEquivalent(callableDescriptor0, callableDescriptor1, z, z3, z2, kotlinTypeRefiner0) : descriptorEquivalenceForOverrides0.areCallableDescriptorsEquivalent(callableDescriptor0, callableDescriptor1, z, z3, false, kotlinTypeRefiner0);
    }

    private static final boolean areCallableDescriptorsEquivalent$lambda$0(boolean z, CallableDescriptor callableDescriptor0, CallableDescriptor callableDescriptor1, TypeConstructor typeConstructor0, TypeConstructor typeConstructor1) {
        Intrinsics.checkNotNullParameter(callableDescriptor0, "$a");
        Intrinsics.checkNotNullParameter(callableDescriptor1, "$b");
        Intrinsics.checkNotNullParameter(typeConstructor0, "c1");
        Intrinsics.checkNotNullParameter(typeConstructor1, "c2");
        if(Intrinsics.areEqual(typeConstructor0, typeConstructor1)) {
            return true;
        }
        ClassifierDescriptor classifierDescriptor0 = typeConstructor0.getDeclarationDescriptor();
        ClassifierDescriptor classifierDescriptor1 = typeConstructor1.getDeclarationDescriptor();
        if(classifierDescriptor0 instanceof TypeParameterDescriptor && classifierDescriptor1 instanceof TypeParameterDescriptor) {
            Function2 function20 = new kotlin.reflect.jvm.internal.impl.resolve.DescriptorEquivalenceForOverrides.areCallableDescriptorsEquivalent.overridingUtil.1.1(callableDescriptor0, callableDescriptor1);
            return DescriptorEquivalenceForOverrides.INSTANCE.areTypeParametersEquivalent(((TypeParameterDescriptor)classifierDescriptor0), ((TypeParameterDescriptor)classifierDescriptor1), z, function20);
        }
        return false;
    }

    private final boolean areClassesEquivalent(ClassDescriptor classDescriptor0, ClassDescriptor classDescriptor1) {
        return Intrinsics.areEqual(classDescriptor0.getTypeConstructor(), classDescriptor1.getTypeConstructor());
    }

    public final boolean areEquivalent(DeclarationDescriptor declarationDescriptor0, DeclarationDescriptor declarationDescriptor1, boolean z, boolean z1) {
        if(declarationDescriptor0 instanceof ClassDescriptor && declarationDescriptor1 instanceof ClassDescriptor) {
            return this.areClassesEquivalent(((ClassDescriptor)declarationDescriptor0), ((ClassDescriptor)declarationDescriptor1));
        }
        if(declarationDescriptor0 instanceof TypeParameterDescriptor && declarationDescriptor1 instanceof TypeParameterDescriptor) {
            return DescriptorEquivalenceForOverrides.areTypeParametersEquivalent$default(this, ((TypeParameterDescriptor)declarationDescriptor0), ((TypeParameterDescriptor)declarationDescriptor1), z, null, 8, null);
        }
        if(declarationDescriptor0 instanceof CallableDescriptor && declarationDescriptor1 instanceof CallableDescriptor) {
            return DescriptorEquivalenceForOverrides.areCallableDescriptorsEquivalent$default(this, ((CallableDescriptor)declarationDescriptor0), ((CallableDescriptor)declarationDescriptor1), z, z1, false, Default.INSTANCE, 16, null);
        }
        return !(declarationDescriptor0 instanceof PackageFragmentDescriptor) || !(declarationDescriptor1 instanceof PackageFragmentDescriptor) ? Intrinsics.areEqual(declarationDescriptor0, declarationDescriptor1) : Intrinsics.areEqual(((PackageFragmentDescriptor)declarationDescriptor0).getFqName(), ((PackageFragmentDescriptor)declarationDescriptor1).getFqName());
    }

    public static boolean areEquivalent$default(DescriptorEquivalenceForOverrides descriptorEquivalenceForOverrides0, DeclarationDescriptor declarationDescriptor0, DeclarationDescriptor declarationDescriptor1, boolean z, boolean z1, int v, Object object0) {
        if((v & 8) != 0) {
            z1 = true;
        }
        return descriptorEquivalenceForOverrides0.areEquivalent(declarationDescriptor0, declarationDescriptor1, z, z1);
    }

    public final boolean areTypeParametersEquivalent(TypeParameterDescriptor typeParameterDescriptor0, TypeParameterDescriptor typeParameterDescriptor1, boolean z) {
        Intrinsics.checkNotNullParameter(typeParameterDescriptor0, "a");
        Intrinsics.checkNotNullParameter(typeParameterDescriptor1, "b");
        return DescriptorEquivalenceForOverrides.areTypeParametersEquivalent$default(this, typeParameterDescriptor0, typeParameterDescriptor1, z, null, 8, null);
    }

    public final boolean areTypeParametersEquivalent(TypeParameterDescriptor typeParameterDescriptor0, TypeParameterDescriptor typeParameterDescriptor1, boolean z, Function2 function20) {
        Intrinsics.checkNotNullParameter(typeParameterDescriptor0, "a");
        Intrinsics.checkNotNullParameter(typeParameterDescriptor1, "b");
        Intrinsics.checkNotNullParameter(function20, "equivalentCallables");
        if(Intrinsics.areEqual(typeParameterDescriptor0, typeParameterDescriptor1)) {
            return true;
        }
        if(Intrinsics.areEqual(typeParameterDescriptor0.getContainingDeclaration(), typeParameterDescriptor1.getContainingDeclaration())) {
            return false;
        }
        return this.ownersEquivalent(typeParameterDescriptor0, typeParameterDescriptor1, function20, z) ? typeParameterDescriptor0.getIndex() == typeParameterDescriptor1.getIndex() : false;
    }

    public static boolean areTypeParametersEquivalent$default(DescriptorEquivalenceForOverrides descriptorEquivalenceForOverrides0, TypeParameterDescriptor typeParameterDescriptor0, TypeParameterDescriptor typeParameterDescriptor1, boolean z, Function2 function20, int v, Object object0) {
        if((v & 8) != 0) {
            function20 = kotlin.reflect.jvm.internal.impl.resolve.DescriptorEquivalenceForOverrides.areTypeParametersEquivalent.1.INSTANCE;
        }
        return descriptorEquivalenceForOverrides0.areTypeParametersEquivalent(typeParameterDescriptor0, typeParameterDescriptor1, z, function20);

        final class kotlin.reflect.jvm.internal.impl.resolve.DescriptorEquivalenceForOverrides.areTypeParametersEquivalent.1 extends Lambda implements Function2 {
            public static final kotlin.reflect.jvm.internal.impl.resolve.DescriptorEquivalenceForOverrides.areTypeParametersEquivalent.1 INSTANCE;

            static {
                kotlin.reflect.jvm.internal.impl.resolve.DescriptorEquivalenceForOverrides.areTypeParametersEquivalent.1.INSTANCE = new kotlin.reflect.jvm.internal.impl.resolve.DescriptorEquivalenceForOverrides.areTypeParametersEquivalent.1();
            }

            kotlin.reflect.jvm.internal.impl.resolve.DescriptorEquivalenceForOverrides.areTypeParametersEquivalent.1() {
                super(2);
            }

            public final Boolean invoke(DeclarationDescriptor declarationDescriptor0, DeclarationDescriptor declarationDescriptor1) {
                return false;
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(((DeclarationDescriptor)object0), ((DeclarationDescriptor)object1));
            }
        }

    }

    private final boolean ownersEquivalent(DeclarationDescriptor declarationDescriptor0, DeclarationDescriptor declarationDescriptor1, Function2 function20, boolean z) {
        DeclarationDescriptor declarationDescriptor2 = declarationDescriptor0.getContainingDeclaration();
        DeclarationDescriptor declarationDescriptor3 = declarationDescriptor1.getContainingDeclaration();
        return declarationDescriptor2 instanceof CallableMemberDescriptor || declarationDescriptor3 instanceof CallableMemberDescriptor ? ((Boolean)function20.invoke(declarationDescriptor2, declarationDescriptor3)).booleanValue() : DescriptorEquivalenceForOverrides.areEquivalent$default(this, declarationDescriptor2, declarationDescriptor3, z, false, 8, null);
    }

    private final SourceElement singleSource(CallableDescriptor callableDescriptor0) {
        while(callableDescriptor0 instanceof CallableMemberDescriptor && ((CallableMemberDescriptor)callableDescriptor0).getKind() == Kind.FAKE_OVERRIDE) {
            Collection collection0 = ((CallableMemberDescriptor)callableDescriptor0).getOverriddenDescriptors();
            Intrinsics.checkNotNullExpressionValue(collection0, "overriddenDescriptors");
            CallableMemberDescriptor callableMemberDescriptor0 = (CallableMemberDescriptor)CollectionsKt.singleOrNull(collection0);
            if(callableMemberDescriptor0 != null) {
                callableDescriptor0 = callableMemberDescriptor0;
                continue;
            }
            return null;
        }
        return callableDescriptor0.getSource();
    }
}

