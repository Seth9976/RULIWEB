package kotlinx.coroutines;

import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.coroutines.EmptyCoroutineContext;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0014\u0010\u0005\u001A\u00020\u00062\n\u0010\u0007\u001A\u00060\bj\u0002`\tH\u0016J\b\u0010\n\u001A\u00020\u000BH\u0016R\u0010\u0010\u0002\u001A\u00020\u00038\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lkotlinx/coroutines/DispatcherExecutor;", "Ljava/util/concurrent/Executor;", "dispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "(Lkotlinx/coroutines/CoroutineDispatcher;)V", "execute", "", "block", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "toString", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
final class DispatcherExecutor implements Executor {
    public final CoroutineDispatcher dispatcher;

    public DispatcherExecutor(CoroutineDispatcher coroutineDispatcher0) {
        this.dispatcher = coroutineDispatcher0;
    }

    @Override
    public void execute(Runnable runnable0) {
        if(this.dispatcher.isDispatchNeeded(EmptyCoroutineContext.INSTANCE)) {
            this.dispatcher.dispatch(EmptyCoroutineContext.INSTANCE, runnable0);
            return;
        }
        runnable0.run();
    }

    // 去混淆评级： 低(20)
    @Override
    public String toString() {
        return "CoroutineDispatcher@3c81fe78";
    }
}

