package kotlin.reflect.jvm.internal.impl.builtins.functions;

import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.GivenFunctionsMemberScope;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;

public final class FunctionClassScope extends GivenFunctionsMemberScope {
    public final class WhenMappings {
        public static final int[] $EnumSwitchMapping$0;

        static {
            int[] arr_v = new int[FunctionClassKind.values().length];
            try {
                arr_v[FunctionClassKind.Function.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[FunctionClassKind.SuspendFunction.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            WhenMappings.$EnumSwitchMapping$0 = arr_v;
        }
    }

    public FunctionClassScope(StorageManager storageManager0, FunctionClassDescriptor functionClassDescriptor0) {
        Intrinsics.checkNotNullParameter(storageManager0, "storageManager");
        Intrinsics.checkNotNullParameter(functionClassDescriptor0, "containingClass");
        super(storageManager0, functionClassDescriptor0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.GivenFunctionsMemberScope
    protected List computeDeclaredFunctions() {
        ClassDescriptor classDescriptor0 = this.getContainingClass();
        Intrinsics.checkNotNull(classDescriptor0, "null cannot be cast to non-null type org.jetbrains.kotlin.builtins.functions.FunctionClassDescriptor");
        switch(WhenMappings.$EnumSwitchMapping$0[((FunctionClassDescriptor)classDescriptor0).getFunctionKind().ordinal()]) {
            case 1: {
                FunctionClassDescriptor functionClassDescriptor0 = (FunctionClassDescriptor)this.getContainingClass();
                return CollectionsKt.listOf(FunctionInvokeDescriptor.Factory.create(functionClassDescriptor0, false));
            }
            case 2: {
                FunctionClassDescriptor functionClassDescriptor1 = (FunctionClassDescriptor)this.getContainingClass();
                return CollectionsKt.listOf(FunctionInvokeDescriptor.Factory.create(functionClassDescriptor1, true));
            }
            default: {
                return CollectionsKt.emptyList();
            }
        }
    }
}

