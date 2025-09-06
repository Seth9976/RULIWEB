package kotlin.sequences;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010(\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000B\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u00032\b\u0012\u0004\u0012\u00020\u00050\u0004B\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0016\u001A\u00020\u0017H\u0002J\t\u0010\u0018\u001A\u00020\u0019H\u0096\u0002J\u000E\u0010\u001A\u001A\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\u001BJ\r\u0010\u001C\u001A\u00028\u0000H\u0002¢\u0006\u0002\u0010\u001BJ\u001E\u0010\u001D\u001A\u00020\u00052\f\u0010\u001E\u001A\b\u0012\u0004\u0012\u00020\u00050\u001FH\u0016ø\u0001\u0000¢\u0006\u0002\u0010 J\u0019\u0010!\u001A\u00020\u00052\u0006\u0010\"\u001A\u00028\u0000H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010#J\u001F\u0010$\u001A\u00020\u00052\f\u0010%\u001A\b\u0012\u0004\u0012\u00028\u00000\u0003H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010&R\u0014\u0010\u0007\u001A\u00020\b8VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\t\u0010\nR\u0016\u0010\u000B\u001A\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0003X\u0082\u000E¢\u0006\u0002\n\u0000R\"\u0010\f\u001A\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004X\u0086\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b\r\u0010\u000E\"\u0004\b\u000F\u0010\u0010R\u0012\u0010\u0011\u001A\u0004\u0018\u00018\u0000X\u0082\u000E¢\u0006\u0004\n\u0002\u0010\u0012R\u0012\u0010\u0013\u001A\u00060\u0014j\u0002`\u0015X\u0082\u000E¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\'"}, d2 = {"Lkotlin/sequences/SequenceBuilderIterator;", "T", "Lkotlin/sequences/SequenceScope;", "", "Lkotlin/coroutines/Continuation;", "", "()V", "context", "Lkotlin/coroutines/CoroutineContext;", "getContext", "()Lkotlin/coroutines/CoroutineContext;", "nextIterator", "nextStep", "getNextStep", "()Lkotlin/coroutines/Continuation;", "setNextStep", "(Lkotlin/coroutines/Continuation;)V", "nextValue", "Ljava/lang/Object;", "state", "", "Lkotlin/sequences/State;", "exceptionalState", "", "hasNext", "", "next", "()Ljava/lang/Object;", "nextNotReady", "resumeWith", "result", "Lkotlin/Result;", "(Ljava/lang/Object;)V", "yield", "value", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "yieldAll", "iterator", "(Ljava/util/Iterator;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
final class SequenceBuilderIterator extends SequenceScope implements Iterator, Continuation, KMappedMarker {
    private Iterator nextIterator;
    private Continuation nextStep;
    private Object nextValue;
    private int state;

    private final Throwable exceptionalState() {
        switch(this.state) {
            case 4: {
                return new NoSuchElementException();
            }
            case 5: {
                return new IllegalStateException("Iterator has failed.");
            }
            default: {
                return new IllegalStateException("Unexpected state of the iterator: " + this.state);
            }
        }
    }

    @Override  // kotlin.coroutines.Continuation
    public CoroutineContext getContext() {
        return EmptyCoroutineContext.INSTANCE;
    }

    public final Continuation getNextStep() {
        return this.nextStep;
    }

    @Override
    public boolean hasNext() {
        while(true) {
            int v = this.state;
            if(v != 0) {
                switch(v) {
                    case 1: {
                        Iterator iterator0 = this.nextIterator;
                        Intrinsics.checkNotNull(iterator0);
                        if(iterator0.hasNext()) {
                            this.state = 2;
                            return true;
                        }
                        this.nextIterator = null;
                        break;
                    }
                    case 2: 
                    case 3: {
                        return true;
                    }
                    case 4: {
                        return false;
                    }
                    default: {
                        throw this.exceptionalState();
                    }
                }
            }
            this.state = 5;
            Continuation continuation0 = this.nextStep;
            Intrinsics.checkNotNull(continuation0);
            this.nextStep = null;
            continuation0.resumeWith(Unit.INSTANCE);
        }
    }

    @Override
    public Object next() {
        switch(this.state) {
            case 0: 
            case 1: {
                return this.nextNotReady();
            }
            case 2: {
                this.state = 1;
                Iterator iterator0 = this.nextIterator;
                Intrinsics.checkNotNull(iterator0);
                return iterator0.next();
            }
            case 3: {
                this.state = 0;
                Object object1 = this.nextValue;
                this.nextValue = null;
                return object1;
            }
            default: {
                throw this.exceptionalState();
            }
        }
    }

    private final Object nextNotReady() {
        if(!this.hasNext()) {
            throw new NoSuchElementException();
        }
        return this.next();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override  // kotlin.coroutines.Continuation
    public void resumeWith(Object object0) {
        ResultKt.throwOnFailure(object0);
        this.state = 4;
    }

    public final void setNextStep(Continuation continuation0) {
        this.nextStep = continuation0;
    }

    @Override  // kotlin.sequences.SequenceScope
    public Object yield(Object object0, Continuation continuation0) {
        this.nextValue = object0;
        this.state = 3;
        this.nextStep = continuation0;
        Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if(object1 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation0);
        }
        return object1 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? object1 : Unit.INSTANCE;
    }

    @Override  // kotlin.sequences.SequenceScope
    public Object yieldAll(Iterator iterator0, Continuation continuation0) {
        if(!iterator0.hasNext()) {
            return Unit.INSTANCE;
        }
        this.nextIterator = iterator0;
        this.state = 2;
        this.nextStep = continuation0;
        Object object0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if(object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation0);
        }
        return object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? object0 : Unit.INSTANCE;
    }
}

