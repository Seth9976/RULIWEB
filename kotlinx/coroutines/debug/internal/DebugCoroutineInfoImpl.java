package kotlinx.coroutines.debug.internal;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequenceScope;
import kotlin.sequences.SequencesKt;

@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0001\u0018\u00002\u00020\u0001B#\b\u0000\u0012\b\u0010\u0002\u001A\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001A\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001A\u00020\u0007\u00A2\u0006\u0002\u0010\bJ\u000E\u0010\u0013\u001A\b\u0012\u0004\u0012\u00020\u00150\u0014H\u0002J\u0013\u0010%\u001A\b\u0012\u0004\u0012\u00020\u00150\u0014H\u0000\u00A2\u0006\u0002\b&J\b\u0010\'\u001A\u00020\u000EH\u0016J)\u0010(\u001A\u00020)2\u0006\u0010 \u001A\u00020\u000E2\n\u0010*\u001A\u0006\u0012\u0002\b\u00030+2\u0006\u0010,\u001A\u00020-H\u0000\u00A2\u0006\u0002\b.J%\u0010/\u001A\u00020)*\b\u0012\u0004\u0012\u00020\u0015002\b\u0010*\u001A\u0004\u0018\u00010\fH\u0082P\u00F8\u0001\u0000\u00A2\u0006\u0002\u00101R\u0016\u0010\t\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\nX\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u001A\u0010\u000B\u001A\n\u0012\u0004\u0012\u00020\f\u0018\u00010\n8\u0006@\u0006X\u0087\u000E\u00A2\u0006\u0002\n\u0000R\u0012\u0010\r\u001A\u00020\u000E8\u0006@\u0006X\u0087\u000E\u00A2\u0006\u0002\n\u0000R\u0013\u0010\u0002\u001A\u0004\u0018\u00010\u00038F\u00A2\u0006\u0006\u001A\u0004\b\u000F\u0010\u0010R\u0016\u0010\u0004\u001A\u0004\u0018\u00010\u0005X\u0080\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0013\u001A\b\u0012\u0004\u0012\u00020\u00150\u00148F\u00A2\u0006\u0006\u001A\u0004\b\u0016\u0010\u0017R(\u0010\u0019\u001A\u0004\u0018\u00010\f2\b\u0010\u0018\u001A\u0004\u0018\u00010\f8@@@X\u0080\u000E\u00A2\u0006\f\u001A\u0004\b\u001A\u0010\u001B\"\u0004\b\u001C\u0010\u001DR\u0014\u0010\u001E\u001A\u0004\u0018\u00010\u001F8\u0006@\u0006X\u0087\u000E\u00A2\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001A\u00020\u00078\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0014\u0010 \u001A\u00020\u000E8@X\u0080\u0004\u00A2\u0006\u0006\u001A\u0004\b!\u0010\"R\u000E\u0010#\u001A\u00020$X\u0082\u000E\u00A2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00A8\u00062"}, d2 = {"Lkotlinx/coroutines/debug/internal/DebugCoroutineInfoImpl;", "", "context", "Lkotlin/coroutines/CoroutineContext;", "creationStackBottom", "Lkotlinx/coroutines/debug/internal/StackTraceFrame;", "sequenceNumber", "", "(Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/debug/internal/StackTraceFrame;J)V", "_context", "Ljava/lang/ref/WeakReference;", "_lastObservedFrame", "Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "_state", "", "getContext", "()Lkotlin/coroutines/CoroutineContext;", "getCreationStackBottom$kotlinx_coroutines_core", "()Lkotlinx/coroutines/debug/internal/StackTraceFrame;", "creationStackTrace", "", "Ljava/lang/StackTraceElement;", "getCreationStackTrace", "()Ljava/util/List;", "value", "lastObservedFrame", "getLastObservedFrame$kotlinx_coroutines_core", "()Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "setLastObservedFrame$kotlinx_coroutines_core", "(Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;)V", "lastObservedThread", "Ljava/lang/Thread;", "state", "getState$kotlinx_coroutines_core", "()Ljava/lang/String;", "unmatchedResume", "", "lastObservedStackTrace", "lastObservedStackTrace$kotlinx_coroutines_core", "toString", "updateState", "", "frame", "Lkotlin/coroutines/Continuation;", "shouldBeMatched", "", "updateState$kotlinx_coroutines_core", "yieldFrames", "Lkotlin/sequences/SequenceScope;", "(Lkotlin/sequences/SequenceScope;Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class DebugCoroutineInfoImpl {
    private final WeakReference _context;
    public volatile WeakReference _lastObservedFrame;
    public volatile String _state;
    private final StackTraceFrame creationStackBottom;
    public volatile Thread lastObservedThread;
    public final long sequenceNumber;
    private int unmatchedResume;

    public DebugCoroutineInfoImpl(CoroutineContext coroutineContext0, StackTraceFrame stackTraceFrame0, long v) {
        this.creationStackBottom = stackTraceFrame0;
        this.sequenceNumber = v;
        this._context = new WeakReference(coroutineContext0);
        this._state = "CREATED";
    }

    private final List creationStackTrace() {
        return this.creationStackBottom == null ? CollectionsKt.emptyList() : SequencesKt.toList(SequencesKt.sequence(new Function2(this.creationStackBottom, null) {
            final StackTraceFrame $bottom;
            private Object L$0;
            int label;

            {
                DebugCoroutineInfoImpl.this = debugCoroutineInfoImpl0;
                this.$bottom = stackTraceFrame0;
                super(2, continuation0);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object object0, Continuation continuation0) {
                kotlinx.coroutines.debug.internal.DebugCoroutineInfoImpl.creationStackTrace.1 debugCoroutineInfoImpl$creationStackTrace$10 = new kotlinx.coroutines.debug.internal.DebugCoroutineInfoImpl.creationStackTrace.1(DebugCoroutineInfoImpl.this, this.$bottom, continuation0);
                debugCoroutineInfoImpl$creationStackTrace$10.L$0 = object0;
                return debugCoroutineInfoImpl$creationStackTrace$10;
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(((SequenceScope)object0), ((Continuation)object1));
            }

            public final Object invoke(SequenceScope sequenceScope0, Continuation continuation0) {
                return ((kotlinx.coroutines.debug.internal.DebugCoroutineInfoImpl.creationStackTrace.1)this.create(sequenceScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object object0) {
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(this.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        this.label = 1;
                        return DebugCoroutineInfoImpl.this.yieldFrames(((SequenceScope)this.L$0), this.$bottom.getCallerFrame(), this) == object1 ? object1 : Unit.INSTANCE;
                    }
                    case 1: {
                        ResultKt.throwOnFailure(object0);
                        return Unit.INSTANCE;
                    }
                    default: {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    }
                }
            }
        }));
    }

    public final CoroutineContext getContext() {
        return (CoroutineContext)this._context.get();
    }

    public final StackTraceFrame getCreationStackBottom$kotlinx_coroutines_core() {
        return this.creationStackBottom;
    }

    public final List getCreationStackTrace() {
        return this.creationStackTrace();
    }

    public final CoroutineStackFrame getLastObservedFrame$kotlinx_coroutines_core() {
        return this._lastObservedFrame == null ? null : ((CoroutineStackFrame)this._lastObservedFrame.get());
    }

    public final String getState$kotlinx_coroutines_core() {
        return this._state;
    }

    public final List lastObservedStackTrace$kotlinx_coroutines_core() {
        CoroutineStackFrame coroutineStackFrame0 = this.getLastObservedFrame$kotlinx_coroutines_core();
        if(coroutineStackFrame0 == null) {
            return CollectionsKt.emptyList();
        }
        ArrayList arrayList0 = new ArrayList();
        while(coroutineStackFrame0 != null) {
            StackTraceElement stackTraceElement0 = coroutineStackFrame0.getStackTraceElement();
            if(stackTraceElement0 != null) {
                arrayList0.add(stackTraceElement0);
            }
            coroutineStackFrame0 = coroutineStackFrame0.getCallerFrame();
        }
        return arrayList0;
    }

    public final void setLastObservedFrame$kotlinx_coroutines_core(CoroutineStackFrame coroutineStackFrame0) {
        this._lastObservedFrame = coroutineStackFrame0 == null ? null : new WeakReference(coroutineStackFrame0);
    }

    @Override
    public String toString() {
        return "DebugCoroutineInfo(state=" + this.getState$kotlinx_coroutines_core() + ",context=" + this.getContext() + ')';
    }

    public final void updateState$kotlinx_coroutines_core(String s, Continuation continuation0, boolean z) {
        synchronized(this) {
            if(Intrinsics.areEqual(this._state, "RUNNING") && Intrinsics.areEqual(s, "RUNNING") && z) {
                ++this.unmatchedResume;
            }
            else if(this.unmatchedResume > 0 && Intrinsics.areEqual(s, "SUSPENDED")) {
                --this.unmatchedResume;
                return;
            }
            if(Intrinsics.areEqual(this._state, s) && Intrinsics.areEqual(s, "SUSPENDED") && this.getLastObservedFrame$kotlinx_coroutines_core() != null) {
                return;
            }
            this._state = s;
            Thread thread0 = null;
            this.setLastObservedFrame$kotlinx_coroutines_core((continuation0 instanceof CoroutineStackFrame ? ((CoroutineStackFrame)continuation0) : null));
            if(Intrinsics.areEqual(s, "RUNNING")) {
                thread0 = Thread.currentThread();
            }
            this.lastObservedThread = thread0;
        }
    }

    private final Object yieldFrames(SequenceScope sequenceScope0, CoroutineStackFrame coroutineStackFrame0, Continuation continuation0) {
        SequenceScope sequenceScope1;
        CoroutineStackFrame coroutineStackFrame1;
        DebugCoroutineInfoImpl debugCoroutineInfoImpl0;
        kotlinx.coroutines.debug.internal.DebugCoroutineInfoImpl.yieldFrames.1 debugCoroutineInfoImpl$yieldFrames$10;
        if(continuation0 instanceof kotlinx.coroutines.debug.internal.DebugCoroutineInfoImpl.yieldFrames.1) {
            debugCoroutineInfoImpl$yieldFrames$10 = (kotlinx.coroutines.debug.internal.DebugCoroutineInfoImpl.yieldFrames.1)continuation0;
            if((debugCoroutineInfoImpl$yieldFrames$10.label & 0x80000000) == 0) {
                debugCoroutineInfoImpl$yieldFrames$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
                    Object L$0;
                    Object L$1;
                    Object L$2;
                    int label;
                    Object result;

                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object object0) {
                        this.result = object0;
                        this.label |= 0x80000000;
                        return continuation0.yieldFrames(null, null, this);
                    }
                };
            }
            else {
                debugCoroutineInfoImpl$yieldFrames$10.label ^= 0x80000000;
            }
        }
        else {
            debugCoroutineInfoImpl$yieldFrames$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
                Object L$0;
                Object L$1;
                Object L$2;
                int label;
                Object result;

                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object object0) {
                    this.result = object0;
                    this.label |= 0x80000000;
                    return continuation0.yieldFrames(null, null, this);
                }
            };
        }
        Object object0 = debugCoroutineInfoImpl$yieldFrames$10.result;
        Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch(debugCoroutineInfoImpl$yieldFrames$10.label) {
            case 0: {
                ResultKt.throwOnFailure(object0);
                debugCoroutineInfoImpl0 = this;
                goto label_24;
            }
            case 1: {
                coroutineStackFrame1 = (CoroutineStackFrame)debugCoroutineInfoImpl$yieldFrames$10.L$2;
                sequenceScope1 = (SequenceScope)debugCoroutineInfoImpl$yieldFrames$10.L$1;
                debugCoroutineInfoImpl0 = (DebugCoroutineInfoImpl)debugCoroutineInfoImpl$yieldFrames$10.L$0;
                ResultKt.throwOnFailure(object0);
                break;
            }
            default: {
                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
            }
        }
        while(true) {
            coroutineStackFrame0 = coroutineStackFrame1;
            sequenceScope0 = sequenceScope1;
            do {
                coroutineStackFrame0 = coroutineStackFrame0.getCallerFrame();
                if(coroutineStackFrame0 == null) {
                    return Unit.INSTANCE;
                }
            label_24:
                if(coroutineStackFrame0 == null) {
                    return Unit.INSTANCE;
                }
                StackTraceElement stackTraceElement0 = coroutineStackFrame0.getStackTraceElement();
            }
            while(stackTraceElement0 == null);
            debugCoroutineInfoImpl$yieldFrames$10.L$0 = debugCoroutineInfoImpl0;
            debugCoroutineInfoImpl$yieldFrames$10.L$1 = sequenceScope0;
            debugCoroutineInfoImpl$yieldFrames$10.L$2 = coroutineStackFrame0;
            debugCoroutineInfoImpl$yieldFrames$10.label = 1;
            if(sequenceScope0.yield(stackTraceElement0, debugCoroutineInfoImpl$yieldFrames$10) == object1) {
                return object1;
            }
            sequenceScope1 = sequenceScope0;
            coroutineStackFrame1 = coroutineStackFrame0;
        }
    }
}

