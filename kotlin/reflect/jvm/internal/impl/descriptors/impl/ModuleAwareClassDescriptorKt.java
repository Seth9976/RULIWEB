package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitution;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;

public final class ModuleAwareClassDescriptorKt {
    public static final MemberScope getRefinedMemberScopeIfPossible(ClassDescriptor classDescriptor0, TypeSubstitution typeSubstitution0, KotlinTypeRefiner kotlinTypeRefiner0) {
        Intrinsics.checkNotNullParameter(classDescriptor0, "<this>");
        Intrinsics.checkNotNullParameter(typeSubstitution0, "typeSubstitution");
        Intrinsics.checkNotNullParameter(kotlinTypeRefiner0, "kotlinTypeRefiner");
        return ModuleAwareClassDescriptor.Companion.getRefinedMemberScopeIfPossible$descriptors(classDescriptor0, typeSubstitution0, kotlinTypeRefiner0);
    }

    public static final MemberScope getRefinedUnsubstitutedMemberScopeIfPossible(ClassDescriptor classDescriptor0, KotlinTypeRefiner kotlinTypeRefiner0) {
        Intrinsics.checkNotNullParameter(classDescriptor0, "<this>");
        Intrinsics.checkNotNullParameter(kotlinTypeRefiner0, "kotlinTypeRefiner");
        return ModuleAwareClassDescriptor.Companion.getRefinedUnsubstitutedMemberScopeIfPossible$descriptors(classDescriptor0, kotlinTypeRefiner0);
    }
}

