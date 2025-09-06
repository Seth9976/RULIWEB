package kotlinx.coroutines;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.coroutines.AbstractCoroutineContextElement;
import kotlin.coroutines.AbstractCoroutineContextKey;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationInterceptor.DefaultImpls;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext.Element;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.internal.DispatchedContinuation;
import kotlinx.coroutines.internal.LimitedDispatcher;
import kotlinx.coroutines.internal.LimitedDispatcherKt;

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000E\n\u0002\b\u0002\b&\u0018\u0000 \u001A2\u00020\u00012\u00020\u0002:\u0001\u001AB\u0005¢\u0006\u0002\u0010\u0003J\u001C\u0010\u0004\u001A\u00020\u00052\u0006\u0010\u0006\u001A\u00020\u00072\n\u0010\b\u001A\u00060\tj\u0002`\nH&J\u001C\u0010\u000B\u001A\u00020\u00052\u0006\u0010\u0006\u001A\u00020\u00072\n\u0010\b\u001A\u00060\tj\u0002`\nH\u0017J \u0010\f\u001A\b\u0012\u0004\u0012\u0002H\u000E0\r\"\u0004\b\u0000\u0010\u000E2\f\u0010\u000F\u001A\b\u0012\u0004\u0012\u0002H\u000E0\rJ\u0010\u0010\u0010\u001A\u00020\u00112\u0006\u0010\u0006\u001A\u00020\u0007H\u0016J\u0010\u0010\u0012\u001A\u00020\u00002\u0006\u0010\u0013\u001A\u00020\u0014H\u0017J\u0011\u0010\u0015\u001A\u00020\u00002\u0006\u0010\u0016\u001A\u00020\u0000H\u0087\u0002J\u0012\u0010\u0017\u001A\u00020\u00052\n\u0010\u000F\u001A\u0006\u0012\u0002\b\u00030\rJ\b\u0010\u0018\u001A\u00020\u0019H\u0016¨\u0006\u001B"}, d2 = {"Lkotlinx/coroutines/CoroutineDispatcher;", "Lkotlin/coroutines/AbstractCoroutineContextElement;", "Lkotlin/coroutines/ContinuationInterceptor;", "()V", "dispatch", "", "context", "Lkotlin/coroutines/CoroutineContext;", "block", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "dispatchYield", "interceptContinuation", "Lkotlin/coroutines/Continuation;", "T", "continuation", "isDispatchNeeded", "", "limitedParallelism", "parallelism", "", "plus", "other", "releaseInterceptedContinuation", "toString", "", "Key", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public abstract class CoroutineDispatcher extends AbstractCoroutineContextElement implements ContinuationInterceptor {
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\u0003\u0018\u00002\u000E\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lkotlinx/coroutines/CoroutineDispatcher$Key;", "Lkotlin/coroutines/AbstractCoroutineContextKey;", "Lkotlin/coroutines/ContinuationInterceptor;", "Lkotlinx/coroutines/CoroutineDispatcher;", "()V", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class Key extends AbstractCoroutineContextKey {
        private Key() {
            super(ContinuationInterceptor.Key, kotlinx.coroutines.CoroutineDispatcher.Key.1.INSTANCE);

            @Metadata(d1 = {"\u0000\u000E\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001A\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001A\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Lkotlinx/coroutines/CoroutineDispatcher;", "it", "Lkotlin/coroutines/CoroutineContext$Element;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 0x30)
            final class kotlinx.coroutines.CoroutineDispatcher.Key.1 extends Lambda implements Function1 {
                public static final kotlinx.coroutines.CoroutineDispatcher.Key.1 INSTANCE;

                static {
                    kotlinx.coroutines.CoroutineDispatcher.Key.1.INSTANCE = new kotlinx.coroutines.CoroutineDispatcher.Key.1();
                }

                kotlinx.coroutines.CoroutineDispatcher.Key.1() {
                    super(1);
                }

                @Override  // kotlin.jvm.functions.Function1
                public Object invoke(Object object0) {
                    return this.invoke(((Element)object0));
                }

                // 去混淆评级： 低(20)
                public final CoroutineDispatcher invoke(Element coroutineContext$Element0) {
                    return coroutineContext$Element0 instanceof CoroutineDispatcher ? ((CoroutineDispatcher)coroutineContext$Element0) : null;
                }
            }

        }

        public Key(DefaultConstructorMarker defaultConstructorMarker0) {
        }
    }

    public static final Key Key;

    static {
        CoroutineDispatcher.Key = new Key(null);
    }

    public CoroutineDispatcher() {
        super(ContinuationInterceptor.Key);
    }

    public abstract void dispatch(CoroutineContext arg1, Runnable arg2);

    public void dispatchYield(CoroutineContext coroutineContext0, Runnable runnable0) {
        this.dispatch(coroutineContext0, runnable0);
    }

    @Override  // kotlin.coroutines.AbstractCoroutineContextElement, kotlin.coroutines.ContinuationInterceptor
    public Element get(kotlin.coroutines.CoroutineContext.Key coroutineContext$Key0) {
        return DefaultImpls.get(this, coroutineContext$Key0);
    }

    @Override  // kotlin.coroutines.ContinuationInterceptor
    public final Continuation interceptContinuation(Continuation continuation0) {
        return new DispatchedContinuation(this, continuation0);
    }

    public boolean isDispatchNeeded(CoroutineContext coroutineContext0) {
        return true;
    }

    public CoroutineDispatcher limitedParallelism(int v) {
        LimitedDispatcherKt.checkParallelism(v);
        return new LimitedDispatcher(this, v);
    }

    @Override  // kotlin.coroutines.AbstractCoroutineContextElement, kotlin.coroutines.ContinuationInterceptor
    public CoroutineContext minusKey(kotlin.coroutines.CoroutineContext.Key coroutineContext$Key0) {
        return DefaultImpls.minusKey(this, coroutineContext$Key0);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Operator \'+\' on two CoroutineDispatcher objects is meaningless. CoroutineDispatcher is a coroutine context element and `+` is a set-sum operator for coroutine contexts. The dispatcher to the right of `+` just replaces the dispatcher to the left.")
    public final CoroutineDispatcher plus(CoroutineDispatcher coroutineDispatcher0) {
        return coroutineDispatcher0;
    }

    @Override  // kotlin.coroutines.ContinuationInterceptor
    public final void releaseInterceptedContinuation(Continuation continuation0) {
        Intrinsics.checkNotNull(continuation0, "null cannot be cast to non-null type kotlinx.coroutines.internal.DispatchedContinuation<*>");
        ((DispatchedContinuation)continuation0).release$kotlinx_coroutines_core();
    }

    @Override
    public String toString() [...] // 潜在的解密器
}

