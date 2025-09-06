package kotlin.collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequenceScope;
import kotlin.sequences.SequencesKt;

@Metadata(d1 = {"\u0000*\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010(\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001A\u0018\u0010\u0000\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u00032\u0006\u0010\u0004\u001A\u00020\u0003H\u0000\u001AH\u0010\u0005\u001A\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\b0\u00070\u0006\"\u0004\b\u0000\u0010\b2\f\u0010\t\u001A\b\u0012\u0004\u0012\u0002H\b0\u00062\u0006\u0010\u0002\u001A\u00020\u00032\u0006\u0010\u0004\u001A\u00020\u00032\u0006\u0010\n\u001A\u00020\u000B2\u0006\u0010\f\u001A\u00020\u000BH\u0000\u001AD\u0010\r\u001A\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\b0\u00070\u000E\"\u0004\b\u0000\u0010\b*\b\u0012\u0004\u0012\u0002H\b0\u000E2\u0006\u0010\u0002\u001A\u00020\u00032\u0006\u0010\u0004\u001A\u00020\u00032\u0006\u0010\n\u001A\u00020\u000B2\u0006\u0010\f\u001A\u00020\u000BH\u0000¨\u0006\u000F"}, d2 = {"checkWindowSizeStep", "", "size", "", "step", "windowedIterator", "", "", "T", "iterator", "partialWindows", "", "reuseBuffer", "windowedSequence", "Lkotlin/sequences/Sequence;", "kotlin-stdlib"}, k = 2, mv = {1, 9, 0}, xi = 0x30)
public final class SlidingWindowKt {
    public static final void checkWindowSizeStep(int v, int v1) {
        if(v <= 0 || v1 <= 0) {
            throw new IllegalArgumentException((v == v1 ? "size " + v + " must be greater than zero." : "Both size " + v + " and step " + v1 + " must be greater than zero.").toString());
        }
    }

    // 检测为 Lambda 实现
    public static final Iterator windowedIterator(Iterator iterator0, int v, int v1, boolean z, boolean z1) [...]

    public static final Sequence windowedSequence(Sequence sequence0, int v, int v1, boolean z, boolean z1) {
        Intrinsics.checkNotNullParameter(sequence0, "<this>");
        SlidingWindowKt.checkWindowSizeStep(v, v1);
        return () -> {
            Intrinsics.checkNotNullParameter(this.$this_windowedSequence$inlined.iterator(), "iterator");
            return !this.$this_windowedSequence$inlined.iterator().hasNext() ? EmptyIterator.INSTANCE : SequencesKt.iterator(new Function2(this.$size$inlined, this.$step$inlined, this.$this_windowedSequence$inlined.iterator(), this.$reuseBuffer$inlined, this.$partialWindows$inlined, null) {
                final Iterator $iterator;
                final boolean $partialWindows;
                final boolean $reuseBuffer;
                final int $size;
                final int $step;
                int I$0;
                private Object L$0;
                Object L$1;
                Object L$2;
                int label;

                {
                    this.$size = v;
                    this.$step = v1;
                    this.$iterator = iterator0;
                    this.$reuseBuffer = z;
                    this.$partialWindows = z1;
                    super(2, continuation0);
                }

                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation create(Object object0, Continuation continuation0) {
                    kotlin.collections.SlidingWindowKt.windowedIterator.1 slidingWindowKt$windowedIterator$10 = new kotlin.collections.SlidingWindowKt.windowedIterator.1(this.$size, this.$step, this.$iterator, this.$reuseBuffer, this.$partialWindows, continuation0);
                    slidingWindowKt$windowedIterator$10.L$0 = object0;
                    return slidingWindowKt$windowedIterator$10;
                }

                @Override  // kotlin.jvm.functions.Function2
                public Object invoke(Object object0, Object object1) {
                    return this.invoke(((SequenceScope)object0), ((Continuation)object1));
                }

                public final Object invoke(SequenceScope sequenceScope0, Continuation continuation0) {
                    return ((kotlin.collections.SlidingWindowKt.windowedIterator.1)this.create(sequenceScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
                }

                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object object0) {
                    SequenceScope sequenceScope0;
                    RingBuffer ringBuffer0;
                    SequenceScope sequenceScope1;
                    RingBuffer ringBuffer1;
                    Iterator iterator0;
                    int v1;
                    SequenceScope sequenceScope2;
                    ArrayList arrayList0;
                    Iterator iterator1;
                    int v;
                    Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    switch(this.label) {
                        case 0: {
                            ResultKt.throwOnFailure(object0);
                            SequenceScope sequenceScope3 = (SequenceScope)this.L$0;
                            int v2 = RangesKt.coerceAtMost(this.$size, 0x400);
                            int v3 = this.$step - this.$size;
                            if(v3 >= 0) {
                                sequenceScope2 = sequenceScope3;
                                arrayList0 = new ArrayList(v2);
                                v1 = 0;
                                v = v3;
                                iterator1 = this.$iterator;
                                while(iterator1.hasNext()) {
                                    Object object2 = iterator1.next();
                                    if(v1 > 0) {
                                        --v1;
                                    }
                                    else {
                                        arrayList0.add(object2);
                                        if(arrayList0.size() == this.$size) {
                                            this.L$0 = sequenceScope2;
                                            this.L$1 = arrayList0;
                                            this.L$2 = iterator1;
                                            this.I$0 = v;
                                            this.label = 1;
                                            if(sequenceScope2.yield(arrayList0, this) == object1) {
                                                return object1;
                                            }
                                            v1 = v;
                                        label_48:
                                            if(this.$reuseBuffer) {
                                                arrayList0.clear();
                                            }
                                            else {
                                                arrayList0 = new ArrayList(this.$size);
                                            }
                                            v = v1;
                                        }
                                    }
                                }
                                if(!arrayList0.isEmpty() && (this.$partialWindows || arrayList0.size() == this.$size)) {
                                    this.L$0 = null;
                                    this.L$1 = null;
                                    this.L$2 = null;
                                    this.label = 2;
                                    if(sequenceScope2.yield(arrayList0, this) == object1) {
                                        return object1;
                                    }
                                }
                            }
                            else {
                                ringBuffer1 = new RingBuffer(v2);
                                sequenceScope1 = sequenceScope3;
                                iterator0 = this.$iterator;
                            label_64:
                                while(iterator0.hasNext()) {
                                    Object object3 = iterator0.next();
                                    ringBuffer1.add(object3);
                                    if(ringBuffer1.isFull()) {
                                        int v4 = this.$size;
                                        if(ringBuffer1.size() < v4) {
                                            ringBuffer1 = ringBuffer1.expanded(v4);
                                        }
                                        else {
                                            List list0 = this.$reuseBuffer ? ringBuffer1 : new ArrayList(ringBuffer1);
                                            this.L$0 = sequenceScope1;
                                            this.L$1 = ringBuffer1;
                                            this.L$2 = iterator0;
                                            this.label = 3;
                                            if(sequenceScope1.yield(list0, this) == object1) {
                                                return object1;
                                            }
                                            ringBuffer1.removeFirst(this.$step);
                                        }
                                    }
                                }
                                if(this.$partialWindows) {
                                    ringBuffer0 = ringBuffer1;
                                    sequenceScope0 = sequenceScope1;
                                label_86:
                                    while(ringBuffer0.size() > this.$step) {
                                        List list1 = this.$reuseBuffer ? ringBuffer0 : new ArrayList(ringBuffer0);
                                        this.L$0 = sequenceScope0;
                                        this.L$1 = ringBuffer0;
                                        this.L$2 = null;
                                        this.label = 4;
                                        if(sequenceScope0.yield(list1, this) == object1) {
                                            return object1;
                                        }
                                        ringBuffer0.removeFirst(this.$step);
                                    }
                                    if(!ringBuffer0.isEmpty()) {
                                        this.L$0 = null;
                                        this.L$1 = null;
                                        this.L$2 = null;
                                        this.label = 5;
                                        if(sequenceScope0.yield(ringBuffer0, this) == object1) {
                                            return object1;
                                        }
                                    }
                                }
                            }
                            break;
                        }
                        case 1: {
                            v = this.I$0;
                            iterator1 = (Iterator)this.L$2;
                            arrayList0 = (ArrayList)this.L$1;
                            sequenceScope2 = (SequenceScope)this.L$0;
                            ResultKt.throwOnFailure(object0);
                            v1 = v;
                            goto label_48;
                        }
                        case 3: {
                            iterator0 = (Iterator)this.L$2;
                            ringBuffer1 = (RingBuffer)this.L$1;
                            sequenceScope1 = (SequenceScope)this.L$0;
                            ResultKt.throwOnFailure(object0);
                            ringBuffer1.removeFirst(this.$step);
                            goto label_64;
                        }
                        case 4: {
                            ringBuffer0 = (RingBuffer)this.L$1;
                            sequenceScope0 = (SequenceScope)this.L$0;
                            ResultKt.throwOnFailure(object0);
                            ringBuffer0.removeFirst(this.$step);
                            goto label_86;
                        }
                        case 2: 
                        case 5: {
                            ResultKt.throwOnFailure(object0);
                            return Unit.INSTANCE;
                        }
                        default: {
                            throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                        }
                    }
                    return Unit.INSTANCE;
                }
            });
        };

        @Metadata(d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010(\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u000F\u0010\u0002\u001A\b\u0012\u0004\u0012\u00028\u00000\u0003H\u0096\u0002¨\u0006\u0004¸\u0006\u0000"}, d2 = {"kotlin/sequences/SequencesKt__SequencesKt$Sequence$1", "Lkotlin/sequences/Sequence;", "iterator", "", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
        public final class kotlin.collections.SlidingWindowKt.windowedSequence..inlined.Sequence.1 implements Sequence {
            public kotlin.collections.SlidingWindowKt.windowedSequence..inlined.Sequence.1(Sequence sequence0, int v, int v1, boolean z, boolean z1) {
            }

            @Override  // kotlin.sequences.Sequence
            public Iterator iterator() {
                return SlidingWindowKt.windowedIterator(this.$this_windowedSequence$inlined.iterator(), this.$size$inlined, this.$step$inlined, this.$partialWindows$inlined, this.$reuseBuffer$inlined);
            }
        }

    }
}

