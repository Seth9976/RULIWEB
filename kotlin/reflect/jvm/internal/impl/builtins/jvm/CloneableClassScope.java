package kotlin.reflect.jvm.internal.impl.builtins.jvm;

import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor.Kind;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.SimpleFunctionDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.GivenFunctionsMemberScope;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;

public final class CloneableClassScope extends GivenFunctionsMemberScope {
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final Name getCLONE_NAME() {
            return CloneableClassScope.CLONE_NAME;
        }
    }

    private static final Name CLONE_NAME;
    public static final Companion Companion;

    static {
        CloneableClassScope.Companion = new Companion(null);
        Name name0 = Name.identifier("clone");
        Intrinsics.checkNotNullExpressionValue(name0, "identifier(\"clone\")");
        CloneableClassScope.CLONE_NAME = name0;
    }

    public CloneableClassScope(StorageManager storageManager0, ClassDescriptor classDescriptor0) {
        Intrinsics.checkNotNullParameter(storageManager0, "storageManager");
        Intrinsics.checkNotNullParameter(classDescriptor0, "containingClass");
        super(storageManager0, classDescriptor0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.GivenFunctionsMemberScope
    protected List computeDeclaredFunctions() {
        SimpleFunctionDescriptorImpl simpleFunctionDescriptorImpl0 = SimpleFunctionDescriptorImpl.create(this.getContainingClass(), Annotations.Companion.getEMPTY(), CloneableClassScope.CLONE_NAME, Kind.DECLARATION, SourceElement.NO_SOURCE);
        simpleFunctionDescriptorImpl0.initialize(null, this.getContainingClass().getThisAsReceiverParameter(), CollectionsKt.emptyList(), CollectionsKt.emptyList(), CollectionsKt.emptyList(), DescriptorUtilsKt.getBuiltIns(this.getContainingClass()).getAnyType(), Modality.OPEN, DescriptorVisibilities.PROTECTED);
        return CollectionsKt.listOf(simpleFunctionDescriptorImpl0);
    }
}

