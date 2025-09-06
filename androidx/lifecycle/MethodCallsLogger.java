package androidx.lifecycle;

import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000E\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0003\b\u0017\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\u00052\u0006\u0010\n\u001A\u00020\u0006H\u0017R\u001A\u0010\u0003\u001A\u000E\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000B"}, d2 = {"Landroidx/lifecycle/MethodCallsLogger;", "", "()V", "calledMethods", "", "", "", "approveCall", "", "name", "type", "lifecycle-common"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public class MethodCallsLogger {
    private final Map calledMethods;

    public MethodCallsLogger() {
        this.calledMethods = new HashMap();
    }

    public boolean approveCall(String s, int v) {
        Intrinsics.checkNotNullParameter(s, "name");
        Integer integer0 = (Integer)this.calledMethods.get(s);
        int v1 = 0;
        int v2 = integer0 == null ? 0 : ((int)integer0);
        if((v2 & v) != 0) {
            v1 = 1;
        }
        this.calledMethods.put(s, ((int)(v | v2)));
        return v1 ^ 1;
    }
}

