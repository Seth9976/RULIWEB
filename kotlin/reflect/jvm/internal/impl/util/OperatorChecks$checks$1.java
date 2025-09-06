package kotlin.reflect.jvm.internal.impl.util;

import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;

final class OperatorChecks.checks.1 extends Lambda implements Function1 {
    public static final OperatorChecks.checks.1 INSTANCE;

    static {
        OperatorChecks.checks.1.INSTANCE = new OperatorChecks.checks.1();
    }

    OperatorChecks.checks.1() {
        super(1);
    }

    @Override  // kotlin.jvm.functions.Function1
    public Object invoke(Object object0) {
        return this.invoke(((FunctionDescriptor)object0));
    }

    public final String invoke(FunctionDescriptor functionDescriptor0) {
        Intrinsics.checkNotNullParameter(functionDescriptor0, "$this$$receiver");
        List list0 = functionDescriptor0.getValueParameters();
        Intrinsics.checkNotNullExpressionValue(list0, "valueParameters");
        ValueParameterDescriptor valueParameterDescriptor0 = (ValueParameterDescriptor)CollectionsKt.lastOrNull(list0);
        return valueParameterDescriptor0 == null || DescriptorUtilsKt.declaresOrInheritsDefaultValue(valueParameterDescriptor0) || valueParameterDescriptor0.getVarargElementType() != null ? "last parameter should not have a default value or be a vararg" : null;
    }
}

