package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitution;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;

public abstract class ModuleAwareClassDescriptor implements ClassDescriptor {
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final MemberScope getRefinedMemberScopeIfPossible$descriptors(ClassDescriptor classDescriptor0, TypeSubstitution typeSubstitution0, KotlinTypeRefiner kotlinTypeRefiner0) {
            Intrinsics.checkNotNullParameter(classDescriptor0, "<this>");
            Intrinsics.checkNotNullParameter(typeSubstitution0, "typeSubstitution");
            Intrinsics.checkNotNullParameter(kotlinTypeRefiner0, "kotlinTypeRefiner");
            ModuleAwareClassDescriptor moduleAwareClassDescriptor0 = classDescriptor0 instanceof ModuleAwareClassDescriptor ? ((ModuleAwareClassDescriptor)classDescriptor0) : null;
            if(moduleAwareClassDescriptor0 != null) {
                MemberScope memberScope0 = moduleAwareClassDescriptor0.getMemberScope(typeSubstitution0, kotlinTypeRefiner0);
                if(memberScope0 != null) {
                    return memberScope0;
                }
            }
            MemberScope memberScope1 = classDescriptor0.getMemberScope(typeSubstitution0);
            Intrinsics.checkNotNullExpressionValue(memberScope1, "this.getMemberScope(\n   …ubstitution\n            )");
            return memberScope1;
        }

        public final MemberScope getRefinedUnsubstitutedMemberScopeIfPossible$descriptors(ClassDescriptor classDescriptor0, KotlinTypeRefiner kotlinTypeRefiner0) {
            Intrinsics.checkNotNullParameter(classDescriptor0, "<this>");
            Intrinsics.checkNotNullParameter(kotlinTypeRefiner0, "kotlinTypeRefiner");
            ModuleAwareClassDescriptor moduleAwareClassDescriptor0 = classDescriptor0 instanceof ModuleAwareClassDescriptor ? ((ModuleAwareClassDescriptor)classDescriptor0) : null;
            if(moduleAwareClassDescriptor0 != null) {
                MemberScope memberScope0 = moduleAwareClassDescriptor0.getUnsubstitutedMemberScope(kotlinTypeRefiner0);
                if(memberScope0 != null) {
                    return memberScope0;
                }
            }
            MemberScope memberScope1 = classDescriptor0.getUnsubstitutedMemberScope();
            Intrinsics.checkNotNullExpressionValue(memberScope1, "this.unsubstitutedMemberScope");
            return memberScope1;
        }
    }

    public static final Companion Companion;

    static {
        ModuleAwareClassDescriptor.Companion = new Companion(null);
    }

    protected abstract MemberScope getMemberScope(TypeSubstitution arg1, KotlinTypeRefiner arg2);

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor
    public ClassifierDescriptor getOriginal() {
        return this.getOriginal();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor
    public DeclarationDescriptor getOriginal() {
        return this.getOriginal();
    }

    protected abstract MemberScope getUnsubstitutedMemberScope(KotlinTypeRefiner arg1);
}

