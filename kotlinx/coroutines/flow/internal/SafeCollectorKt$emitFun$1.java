package kotlinx.coroutines.flow.internal;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendFunction;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlinx.coroutines.flow.FlowCollector;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 0x30)
final class SafeCollectorKt.emitFun.1 extends FunctionReferenceImpl implements SuspendFunction, Function3 {
    public static final SafeCollectorKt.emitFun.1 INSTANCE;

    static {
        SafeCollectorKt.emitFun.1.INSTANCE = new SafeCollectorKt.emitFun.1();
    }

    SafeCollectorKt.emitFun.1() {
        super(3, FlowCollector.class, "emit", "emit(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", 0);
    }

    @Override  // kotlin.jvm.functions.Function3
    public Object invoke(Object object0, Object object1, Object object2) {
        return this.invoke(((FlowCollector)object0), object1, ((Continuation)object2));
    }

    public final Object invoke(FlowCollector flowCollector0, Object object0, Continuation continuation0) {
        return flowCollector0.emit(object0, continuation0);
    }
}

