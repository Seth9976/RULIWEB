package kotlinx.coroutines;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.Volatile;
import kotlin.jvm.functions.Function1;

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0001\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0002\u0018\u00002#\u0012\u0015\u0012\u0013\u0018\u00010\u0002¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0005\u0012\u0004\u0012\u00020\u00060\u0001j\u0002`\u0007B\r\u0012\u0006\u0010\b\u001A\u00020\t¢\u0006\u0002\u0010\nJ\u0006\u0010\u0012\u001A\u00020\u0006J\u0010\u0010\u0013\u001A\u00020\u00142\u0006\u0010\u0015\u001A\u00020\u0016H\u0002J\u0013\u0010\u0017\u001A\u00020\u00062\b\u0010\u0005\u001A\u0004\u0018\u00010\u0002H\u0096\u0002J\u0006\u0010\u0018\u001A\u00020\u0006R\t\u0010\u000B\u001A\u00020\fX\u0082\u0004R\u0010\u0010\r\u001A\u0004\u0018\u00010\u000EX\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\b\u001A\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000F\u001A\n \u0011*\u0004\u0018\u00010\u00100\u0010X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lkotlinx/coroutines/ThreadState;", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "cause", "", "Lkotlinx/coroutines/CompletionHandler;", "job", "Lkotlinx/coroutines/Job;", "(Lkotlinx/coroutines/Job;)V", "_state", "Lkotlinx/atomicfu/AtomicInt;", "cancelHandle", "Lkotlinx/coroutines/DisposableHandle;", "targetThread", "Ljava/lang/Thread;", "kotlin.jvm.PlatformType", "clearInterrupt", "invalidState", "", "state", "", "invoke", "setup", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
final class ThreadState implements Function1 {
    @Volatile
    private volatile int _state;
    private static final AtomicIntegerFieldUpdater _state$FU;
    private DisposableHandle cancelHandle;
    private final Job job;
    private final Thread targetThread;

    static {
        ThreadState._state$FU = AtomicIntegerFieldUpdater.newUpdater(ThreadState.class, "_state");
    }

    public ThreadState(Job job0) {
        this.job = job0;
        this.targetThread = Thread.currentThread();
    }

    public final void clearInterrupt() {
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater0 = ThreadState._state$FU;
        while(true) {
            int v = atomicIntegerFieldUpdater0.get(this);
            switch(v) {
                case 0: {
                    if(ThreadState._state$FU.compareAndSet(this, 0, 1)) {
                        DisposableHandle disposableHandle0 = this.cancelHandle;
                        if(disposableHandle0 != null) {
                            disposableHandle0.dispose();
                        }
                        return;
                    }
                    if(false) {
                        Thread.interrupted();
                        return;
                    }
                    break;
                }
                case 2: {
                    break;
                }
                case 3: {
                    Thread.interrupted();
                    return;
                }
                default: {
                    this.invalidState(v);
                    throw new KotlinNothingValueException();
                }
            }
        }
    }

    private final Void invalidState(int v) {
        throw new IllegalStateException(("Illegal state " + v).toString());
    }

    @Override  // kotlin.jvm.functions.Function1
    public Object invoke(Object object0) {
        this.invoke(((Throwable)object0));
        return Unit.INSTANCE;
    }

    public void invoke(Throwable throwable0) {
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater1;
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater0 = ThreadState._state$FU;
        do {
            int v = atomicIntegerFieldUpdater0.get(this);
            if(v != 0) {
                if(v != 1 && v != 2 && v != 3) {
                    this.invalidState(v);
                    throw new KotlinNothingValueException();
                }
                return;
            }
            atomicIntegerFieldUpdater1 = ThreadState._state$FU;
        }
        while(!atomicIntegerFieldUpdater1.compareAndSet(this, 0, 2));
        this.targetThread.interrupt();
        atomicIntegerFieldUpdater1.set(this, 3);
    }

    private final void loop$atomicfu(AtomicIntegerFieldUpdater atomicIntegerFieldUpdater0, Function1 function10, Object object0) {
        while(true) {
            function10.invoke(atomicIntegerFieldUpdater0.get(object0));
        }
    }

    public final void setup() {
        this.cancelHandle = this.job.invokeOnCompletion(true, true, this);
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater0 = ThreadState._state$FU;
    alab1:
        while(true) {
            int v = atomicIntegerFieldUpdater0.get(this);
            switch(v) {
                case 0: {
                    if(!ThreadState._state$FU.compareAndSet(this, 0, 0)) {
                        break;
                    }
                    break alab1;
                }
                case 2: 
                case 3: {
                    break alab1;
                }
                default: {
                    this.invalidState(v);
                    throw new KotlinNothingValueException();
                }
            }
        }
    }
}

