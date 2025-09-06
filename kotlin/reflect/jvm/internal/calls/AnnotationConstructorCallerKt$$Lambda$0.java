package kotlin.reflect.jvm.internal.calls;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;

class AnnotationConstructorCallerKt..Lambda.0 implements InvocationHandler {
    private final Class arg$0;
    private final Map arg$1;
    private final Lazy arg$2;
    private final Lazy arg$3;
    private final List arg$4;

    public AnnotationConstructorCallerKt..Lambda.0(Class class0, Map map0, Lazy lazy0, Lazy lazy1, List list0) {
        this.arg$0 = class0;
        this.arg$1 = map0;
        this.arg$2 = lazy0;
        this.arg$3 = lazy1;
        this.arg$4 = list0;
    }

    @Override
    public Object invoke(Object object0, Method method0, Object[] arr_object) {
        return AnnotationConstructorCallerKt.accessor$AnnotationConstructorCallerKt$lambda0(this.arg$0, this.arg$1, this.arg$2, this.arg$3, this.arg$4, object0, method0, arr_object);
    }
}

