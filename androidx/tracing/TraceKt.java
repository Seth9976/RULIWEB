package androidx.tracing;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000(\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000E\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\u001A3\u0010\u0000\u001A\u0002H\u0001\"\u0004\b\u0000\u0010\u00012\f\u0010\u0002\u001A\b\u0012\u0004\u0012\u00020\u00040\u00032\f\u0010\u0005\u001A\b\u0012\u0004\u0012\u0002H\u00010\u0003H\u0086\bø\u0001\u0000¢\u0006\u0002\u0010\u0006\u001A/\u0010\u0000\u001A\u0002H\u0001\"\u0004\b\u0000\u0010\u00012\u0006\u0010\u0007\u001A\u00020\u00042\u000E\b\u0004\u0010\u0005\u001A\b\u0012\u0004\u0012\u0002H\u00010\u0003H\u0086\bø\u0001\u0000¢\u0006\u0002\u0010\b\u001AA\u0010\t\u001A\u0002H\u0001\"\u0004\b\u0000\u0010\u00012\f\u0010\n\u001A\b\u0012\u0004\u0012\u00020\u00040\u00032\f\u0010\u000B\u001A\b\u0012\u0004\u0012\u00020\f0\u00032\f\u0010\u0005\u001A\b\u0012\u0004\u0012\u0002H\u00010\u0003H\u0086\bø\u0001\u0000¢\u0006\u0002\u0010\r\u001AG\u0010\t\u001A\u0002H\u0001\"\u0004\b\u0000\u0010\u00012\u0006\u0010\u000E\u001A\u00020\u00042\u0006\u0010\u000F\u001A\u00020\f2\u001E\b\u0004\u0010\u0005\u001A\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u0011\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u0010H\u0086Hø\u0001\u0001¢\u0006\u0002\u0010\u0013\u0082\u0002\u000B\n\u0005\b\u009920\u0001\n\u0002\b\u0019¨\u0006\u0014"}, d2 = {"trace", "T", "lazyLabel", "Lkotlin/Function0;", "", "block", "(Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "label", "(Ljava/lang/String;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "traceAsync", "lazyMethodName", "lazyCookie", "", "(Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "methodName", "cookie", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "", "(Ljava/lang/String;ILkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "tracing-ktx_release"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class TraceKt {
    public static final Object trace(String s, Function0 function00) {
        Intrinsics.checkNotNullParameter(s, "label");
        Intrinsics.checkNotNullParameter(function00, "block");
        Trace.beginSection(s);
        try {
            return function00.invoke();
        }
        finally {
            Trace.endSection();
        }
    }

    public static final Object trace(Function0 function00, Function0 function01) {
        Object object0;
        Intrinsics.checkNotNullParameter(function00, "lazyLabel");
        Intrinsics.checkNotNullParameter(function01, "block");
        boolean z = Trace.isEnabled();
        if(z) {
            Trace.beginSection(((String)function00.invoke()));
        }
        try {
            object0 = function01.invoke();
        }
        catch(Throwable throwable0) {
            if(z) {
                Trace.endSection();
            }
            throw throwable0;
        }
        if(z) {
            Trace.endSection();
        }
        return object0;
    }

    public static final Object traceAsync(String s, int v, Function1 function10, Continuation continuation0) {
        androidx.tracing.TraceKt.traceAsync.1 traceKt$traceAsync$10;
        if(continuation0 instanceof androidx.tracing.TraceKt.traceAsync.1) {
            traceKt$traceAsync$10 = (androidx.tracing.TraceKt.traceAsync.1)continuation0;
            if((traceKt$traceAsync$10.label & 0x80000000) == 0) {
                traceKt$traceAsync$10 = new ContinuationImpl(continuation0) {
                    int I$0;
                    Object L$0;
                    int label;
                    Object result;

                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object object0) {
                        this.result = object0;
                        this.label |= 0x80000000;
                        return TraceKt.traceAsync(null, 0, null, this);
                    }
                };
            }
            else {
                traceKt$traceAsync$10.label ^= 0x80000000;
            }
        }
        else {
            traceKt$traceAsync$10 = new ContinuationImpl(continuation0) {
                int I$0;
                Object L$0;
                int label;
                Object result;

                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object object0) {
                    this.result = object0;
                    this.label |= 0x80000000;
                    return TraceKt.traceAsync(null, 0, null, this);
                }
            };
        }
        Object object0 = traceKt$traceAsync$10.result;
        Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch(traceKt$traceAsync$10.label) {
            case 0: {
                ResultKt.throwOnFailure(object0);
                Trace.beginAsyncSection(s, v);
                try {
                    traceKt$traceAsync$10.L$0 = s;
                    traceKt$traceAsync$10.I$0 = v;
                    traceKt$traceAsync$10.label = 1;
                    object0 = function10.invoke(traceKt$traceAsync$10);
                    if(object0 == object1) {
                        return object1;
                    }
                    break;
                }
                catch(Throwable throwable0) {
                    Trace.endAsyncSection(s, v);
                    throw throwable0;
                }
            }
            case 1: {
                v = traceKt$traceAsync$10.I$0;
                s = (String)traceKt$traceAsync$10.L$0;
                try {
                    ResultKt.throwOnFailure(object0);
                    break;
                }
                catch(Throwable throwable0) {
                    Trace.endAsyncSection(s, v);
                    throw throwable0;
                }
            }
            default: {
                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
            }
        }
        Trace.endAsyncSection(s, v);
        return object0;
    }

    public static final Object traceAsync(Function0 function00, Function0 function01, Function0 function02) {
        Object object0;
        int v;
        String s;
        Intrinsics.checkNotNullParameter(function00, "lazyMethodName");
        Intrinsics.checkNotNullParameter(function01, "lazyCookie");
        Intrinsics.checkNotNullParameter(function02, "block");
        if(Trace.isEnabled()) {
            s = (String)function00.invoke();
            v = ((Number)function01.invoke()).intValue();
            Trace.beginAsyncSection(s, v);
        }
        else {
            s = null;
            v = 0;
        }
        try {
            object0 = function02.invoke();
        }
        catch(Throwable throwable0) {
            if(s != null) {
                Trace.endAsyncSection(s, v);
            }
            throw throwable0;
        }
        if(s != null) {
            Trace.endAsyncSection(s, v);
        }
        return object0;
    }

    private static final Object traceAsync$$forInline(String s, int v, Function1 function10, Continuation continuation0) {
        Trace.beginAsyncSection(s, v);
        try {
            return function10.invoke(continuation0);
        }
        finally {
            Trace.endAsyncSection(s, v);
        }
    }
}

