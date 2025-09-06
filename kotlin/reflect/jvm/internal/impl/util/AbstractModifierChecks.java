package kotlin.reflect.jvm.internal.impl.util;

import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;

public abstract class AbstractModifierChecks {
    public final CheckResult check(FunctionDescriptor functionDescriptor0) {
        Intrinsics.checkNotNullParameter(functionDescriptor0, "functionDescriptor");
        for(Object object0: this.getChecks$descriptors()) {
            Checks checks0 = (Checks)object0;
            if(checks0.isApplicable(functionDescriptor0)) {
                return checks0.checkAll(functionDescriptor0);
            }
            if(false) {
                break;
            }
        }
        return IllegalFunctionName.INSTANCE;
    }

    public abstract List getChecks$descriptors();
}

