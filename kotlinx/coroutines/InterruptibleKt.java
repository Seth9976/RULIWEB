package kotlinx.coroutines;

import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;

@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001A/\u0010\u0005\u001A\u0002H\u0006\"\u0004\b\u0000\u0010\u00062\b\b\u0002\u0010\u0007\u001A\u00020\b2\f\u0010\t\u001A\b\u0012\u0004\u0012\u0002H\u00060\nH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u000B\u001A)\u0010\f\u001A\u0002H\u0006\"\u0004\b\u0000\u0010\u00062\u0006\u0010\r\u001A\u00020\b2\f\u0010\t\u001A\b\u0012\u0004\u0012\u0002H\u00060\nH\u0002¢\u0006\u0002\u0010\u000E\"\u000E\u0010\u0000\u001A\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000E\u0010\u0002\u001A\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000E\u0010\u0003\u001A\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000E\u0010\u0004\u001A\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000F"}, d2 = {"FINISHED", "", "INTERRUPTED", "INTERRUPTING", "WORKING", "runInterruptible", "T", "context", "Lkotlin/coroutines/CoroutineContext;", "block", "Lkotlin/Function0;", "(Lkotlin/coroutines/CoroutineContext;Lkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "runInterruptibleInExpectedContext", "coroutineContext", "(Lkotlin/coroutines/CoroutineContext;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class InterruptibleKt {
    private static final int FINISHED = 1;
    private static final int INTERRUPTED = 3;
    private static final int INTERRUPTING = 2;
    private static final int WORKING;

    public static final Object runInterruptible(CoroutineContext coroutineContext0, Function0 function00, Continuation continuation0) {
        return BuildersKt.withContext(coroutineContext0, new Function2(function00, null) {
            final Function0 $block;
            private Object L$0;
            int label;

            {
                this.$block = function00;
                super(2, continuation0);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object object0, Continuation continuation0) {
                kotlinx.coroutines.InterruptibleKt.runInterruptible.2 interruptibleKt$runInterruptible$20 = new kotlinx.coroutines.InterruptibleKt.runInterruptible.2(this.$block, continuation0);
                interruptibleKt$runInterruptible$20.L$0 = object0;
                return interruptibleKt$runInterruptible$20;
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(((CoroutineScope)object0), ((Continuation)object1));
            }

            public final Object invoke(CoroutineScope coroutineScope0, Continuation continuation0) {
                return ((kotlinx.coroutines.InterruptibleKt.runInterruptible.2)this.create(coroutineScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object object0) {
                if(this.label != 0) {
                    throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                }
                ResultKt.throwOnFailure(object0);
                return InterruptibleKt.runInterruptibleInExpectedContext(((CoroutineScope)this.L$0).getCoroutineContext(), this.$block);
            }
        }, continuation0);
    }

    public static Object runInterruptible$default(CoroutineContext coroutineContext0, Function0 function00, Continuation continuation0, int v, Object object0) {
        if((v & 1) != 0) {
            coroutineContext0 = EmptyCoroutineContext.INSTANCE;
        }
        return InterruptibleKt.runInterruptible(coroutineContext0, function00, continuation0);
    }

    private static final Object runInterruptibleInExpectedContext(CoroutineContext coroutineContext0, Function0 function00) {
        try {
            ThreadState threadState0 = new ThreadState(JobKt.getJob(coroutineContext0));
            threadState0.setup();
            try {
                return function00.invoke();
            }
            finally {
                threadState0.clearInterrupt();
            }
        }
        catch(InterruptedException interruptedException0) {
            throw new CancellationException("Blocking call was interrupted due to parent cancellation").initCause(interruptedException0);
        }
    }
}

