package kotlinx.coroutines.flow;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u001A\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000B\n\u0002\b\u0004\bf\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003J\u001D\u0010\t\u001A\u00020\n2\u0006\u0010\u000B\u001A\u00028\u00002\u0006\u0010\f\u001A\u00028\u0000H&¢\u0006\u0002\u0010\rR\u0018\u0010\u0004\u001A\u00028\u0000X¦\u000E¢\u0006\f\u001A\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\u000E"}, d2 = {"Lkotlinx/coroutines/flow/MutableStateFlow;", "T", "Lkotlinx/coroutines/flow/StateFlow;", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "value", "getValue", "()Ljava/lang/Object;", "setValue", "(Ljava/lang/Object;)V", "compareAndSet", "", "expect", "update", "(Ljava/lang/Object;Ljava/lang/Object;)Z", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public interface MutableStateFlow extends MutableSharedFlow, StateFlow {
    boolean compareAndSet(Object arg1, Object arg2);

    @Override  // kotlinx.coroutines.flow.StateFlow
    Object getValue();

    void setValue(Object arg1);
}

