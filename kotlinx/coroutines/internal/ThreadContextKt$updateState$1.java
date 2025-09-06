package kotlinx.coroutines.internal;

import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext.Element;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.ThreadContextElement;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u00012\u0006\u0010\u0003\u001A\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<no name provided>", "Lkotlinx/coroutines/internal/ThreadState;", "state", "element", "Lkotlin/coroutines/CoroutineContext$Element;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 0x30)
final class ThreadContextKt.updateState.1 extends Lambda implements Function2 {
    public static final ThreadContextKt.updateState.1 INSTANCE;

    static {
        ThreadContextKt.updateState.1.INSTANCE = new ThreadContextKt.updateState.1();
    }

    ThreadContextKt.updateState.1() {
        super(2);
    }

    @Override  // kotlin.jvm.functions.Function2
    public Object invoke(Object object0, Object object1) {
        return this.invoke(((ThreadState)object0), ((Element)object1));
    }

    public final ThreadState invoke(ThreadState threadState0, Element coroutineContext$Element0) {
        if(coroutineContext$Element0 instanceof ThreadContextElement) {
            threadState0.append(((ThreadContextElement)coroutineContext$Element0), ((ThreadContextElement)coroutineContext$Element0).updateThreadContext(threadState0.context));
        }
        return threadState0;
    }
}

