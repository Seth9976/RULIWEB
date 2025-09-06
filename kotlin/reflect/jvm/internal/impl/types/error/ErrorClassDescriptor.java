package kotlin.reflect.jvm.internal.impl.types.error;

import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassKind;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorNonRoot;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.ClassConstructorDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.ClassDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitution;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;

public final class ErrorClassDescriptor extends ClassDescriptorImpl {
    public ErrorClassDescriptor(Name name0) {
        Intrinsics.checkNotNullParameter(name0, "name");
        List list0 = CollectionsKt.emptyList();
        super(ErrorUtils.INSTANCE.getErrorModule(), name0, Modality.OPEN, ClassKind.CLASS, list0, SourceElement.NO_SOURCE, false, LockBasedStorageManager.NO_LOCKS);
        ClassConstructorDescriptorImpl classConstructorDescriptorImpl0 = ClassConstructorDescriptorImpl.create(this, Annotations.Companion.getEMPTY(), true, SourceElement.NO_SOURCE);
        classConstructorDescriptorImpl0.initialize(CollectionsKt.emptyList(), DescriptorVisibilities.INTERNAL);
        Intrinsics.checkNotNullExpressionValue(classConstructorDescriptorImpl0, "create(this, Annotations…          )\n            }");
        String s = classConstructorDescriptorImpl0.getName().toString();
        Intrinsics.checkNotNullExpressionValue(s, "errorConstructor.name.toString()");
        ErrorScope errorScope0 = ErrorUtils.createErrorScope(ErrorScopeKind.SCOPE_FOR_ERROR_CLASS, new String[]{s, ""});
        classConstructorDescriptorImpl0.setReturnType(new ErrorType(ErrorUtils.INSTANCE.createErrorTypeConstructor(ErrorTypeKind.ERROR_CLASS, new String[0]), errorScope0, ErrorTypeKind.ERROR_CLASS, null, false, new String[0], 24, null));
        this.initialize(errorScope0, SetsKt.setOf(classConstructorDescriptorImpl0), classConstructorDescriptorImpl0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.AbstractClassDescriptor
    public MemberScope getMemberScope(TypeSubstitution typeSubstitution0, KotlinTypeRefiner kotlinTypeRefiner0) {
        Intrinsics.checkNotNullParameter(typeSubstitution0, "typeSubstitution");
        Intrinsics.checkNotNullParameter(kotlinTypeRefiner0, "kotlinTypeRefiner");
        String s = this.getName().toString();
        Intrinsics.checkNotNullExpressionValue(s, "name.toString()");
        return ErrorUtils.createErrorScope(ErrorScopeKind.SCOPE_FOR_ERROR_CLASS, new String[]{s, typeSubstitution0.toString()});
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.AbstractClassDescriptor
    public ClassDescriptor substitute(TypeSubstitutor typeSubstitutor0) {
        Intrinsics.checkNotNullParameter(typeSubstitutor0, "substitutor");
        return this;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.AbstractClassDescriptor
    public DeclarationDescriptorNonRoot substitute(TypeSubstitutor typeSubstitutor0) {
        return this.substitute(typeSubstitutor0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.ClassDescriptorImpl
    public String toString() {
        String s = this.getName().asString();
        Intrinsics.checkNotNullExpressionValue(s, "name.asString()");
        return s;
    }
}

