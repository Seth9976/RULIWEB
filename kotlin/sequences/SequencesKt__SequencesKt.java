package kotlin.sequences;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.random.Random;

@Metadata(d1 = {"\u0000L\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010(\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\b\u0005\n\u0002\u0010\u001C\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\u001A.\u0010\u0000\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0014\b\u0004\u0010\u0003\u001A\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00050\u0004H\u0087\b\u00F8\u0001\u0000\u001A\u0012\u0010\u0006\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002\u001Ab\u0010\u0007\u001A\b\u0012\u0004\u0012\u0002H\b0\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\t\"\u0004\b\u0002\u0010\b2\f\u0010\n\u001A\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0018\u0010\u000B\u001A\u0014\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\t0\f2\u0018\u0010\u0003\u001A\u0014\u0012\u0004\u0012\u0002H\t\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\b0\u00050\u000EH\u0000\u001A&\u0010\u000F\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00102\u000E\u0010\u0011\u001A\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00020\u0004\u001A<\u0010\u000F\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00102\u000E\u0010\u0012\u001A\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00020\u00042\u0014\u0010\u0011\u001A\u0010\u0012\u0004\u0012\u0002H\u0002\u0012\u0006\u0012\u0004\u0018\u0001H\u00020\u000E\u001A=\u0010\u000F\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00102\b\u0010\u0013\u001A\u0004\u0018\u0001H\u00022\u0014\u0010\u0011\u001A\u0010\u0012\u0004\u0012\u0002H\u0002\u0012\u0006\u0012\u0004\u0018\u0001H\u00020\u000EH\u0007\u00A2\u0006\u0002\u0010\u0014\u001A+\u0010\u0015\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0012\u0010\u0016\u001A\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0017\"\u0002H\u0002\u00A2\u0006\u0002\u0010\u0018\u001A\u001C\u0010\u0019\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0005\u001A\u001C\u0010\u001A\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0001\u001AC\u0010\u001B\u001A\b\u0012\u0004\u0012\u0002H\b0\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\b*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0018\u0010\u0003\u001A\u0014\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\b0\u00050\u000EH\u0002\u00A2\u0006\u0002\b\u001C\u001A)\u0010\u001B\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u001D0\u0001H\u0007\u00A2\u0006\u0002\b\u001E\u001A\"\u0010\u001B\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00010\u0001\u001A2\u0010\u001F\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0012\u0010 \u001A\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00010\u0004H\u0007\u001A!\u0010!\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0004\u0012\u0002H\u0002\u0018\u00010\u0001H\u0087\b\u001A\u001E\u0010\"\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0001H\u0007\u001A&\u0010\"\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010#\u001A\u00020$H\u0007\u001A@\u0010%\u001A\u001A\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\'\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\b0\'0&\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\b*\u0014\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\b0&0\u0001\u0082\u0002\u0007\n\u0005\b\u009920\u0001\u00A8\u0006("}, d2 = {"Sequence", "Lkotlin/sequences/Sequence;", "T", "iterator", "Lkotlin/Function0;", "", "emptySequence", "flatMapIndexed", "R", "C", "source", "transform", "Lkotlin/Function2;", "", "Lkotlin/Function1;", "generateSequence", "", "nextFunction", "seedFunction", "seed", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)Lkotlin/sequences/Sequence;", "sequenceOf", "elements", "", "([Ljava/lang/Object;)Lkotlin/sequences/Sequence;", "asSequence", "constrainOnce", "flatten", "flatten$SequencesKt__SequencesKt", "", "flattenSequenceOfIterable", "ifEmpty", "defaultValue", "orEmpty", "shuffled", "random", "Lkotlin/random/Random;", "unzip", "Lkotlin/Pair;", "", "kotlin-stdlib"}, k = 5, mv = {1, 9, 0}, xi = 49, xs = "kotlin/sequences/SequencesKt")
class SequencesKt__SequencesKt extends SequencesKt__SequencesJVMKt {
    private static final Sequence Sequence(Function0 function00) {
        Intrinsics.checkNotNullParameter(function00, "iterator");
        return new Sequence() {
            @Override  // kotlin.sequences.Sequence
            public Iterator iterator() {
                return (Iterator)function00.invoke();
            }
        };
    }

    public static final Sequence asSequence(Iterator iterator0) {
        Intrinsics.checkNotNullParameter(iterator0, "<this>");
        return SequencesKt.constrainOnce(new Sequence() {
            @Override  // kotlin.sequences.Sequence
            public Iterator iterator() {
                return iterator0;
            }
        });
    }

    public static final Sequence constrainOnce(Sequence sequence0) {
        Intrinsics.checkNotNullParameter(sequence0, "<this>");
        return sequence0 instanceof ConstrainedOnceSequence ? sequence0 : new ConstrainedOnceSequence(sequence0);
    }

    public static final Sequence emptySequence() {
        return EmptySequence.INSTANCE;
    }

    public static final Sequence flatMapIndexed(Sequence sequence0, Function2 function20, Function1 function10) {
        Intrinsics.checkNotNullParameter(sequence0, "source");
        Intrinsics.checkNotNullParameter(function20, "transform");
        Intrinsics.checkNotNullParameter(function10, "iterator");
        return SequencesKt.sequence(new Function2(sequence0, function20, function10, null) {
            final Function1 $iterator;
            final Sequence $source;
            final Function2 $transform;
            int I$0;
            private Object L$0;
            Object L$1;
            int label;

            {
                this.$source = sequence0;
                this.$transform = function20;
                this.$iterator = function10;
                super(2, continuation0);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object object0, Continuation continuation0) {
                kotlin.sequences.SequencesKt__SequencesKt.flatMapIndexed.1 sequencesKt__SequencesKt$flatMapIndexed$10 = new kotlin.sequences.SequencesKt__SequencesKt.flatMapIndexed.1(this.$source, this.$transform, this.$iterator, continuation0);
                sequencesKt__SequencesKt$flatMapIndexed$10.L$0 = object0;
                return sequencesKt__SequencesKt$flatMapIndexed$10;
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(((SequenceScope)object0), ((Continuation)object1));
            }

            public final Object invoke(SequenceScope sequenceScope0, Continuation continuation0) {
                return ((kotlin.sequences.SequencesKt__SequencesKt.flatMapIndexed.1)this.create(sequenceScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object object0) {
                int v;
                Iterator iterator0;
                SequenceScope sequenceScope0;
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(this.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        sequenceScope0 = (SequenceScope)this.L$0;
                        iterator0 = this.$source.iterator();
                        v = 0;
                        break;
                    }
                    case 1: {
                        int v1 = this.I$0;
                        iterator0 = (Iterator)this.L$1;
                        sequenceScope0 = (SequenceScope)this.L$0;
                        ResultKt.throwOnFailure(object0);
                        v = v1;
                        break;
                    }
                    default: {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    }
                }
                while(iterator0.hasNext()) {
                    Object object2 = iterator0.next();
                    Function2 function20 = this.$transform;
                    if(v < 0) {
                        CollectionsKt.throwIndexOverflow();
                    }
                    Object object3 = function20.invoke(Boxing.boxInt(v), object2);
                    Iterator iterator1 = (Iterator)this.$iterator.invoke(object3);
                    this.L$0 = sequenceScope0;
                    this.L$1 = iterator0;
                    this.I$0 = v + 1;
                    this.label = 1;
                    if(sequenceScope0.yieldAll(iterator1, this) == object1) {
                        return object1;
                    }
                    ++v;
                }
                return Unit.INSTANCE;
            }
        });
    }

    public static final Sequence flatten(Sequence sequence0) {
        Intrinsics.checkNotNullParameter(sequence0, "<this>");
        return SequencesKt__SequencesKt.flatten$SequencesKt__SequencesKt(sequence0, kotlin.sequences.SequencesKt__SequencesKt.flatten.1.INSTANCE);

        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010(\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\f\u0010\u0003\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "T", "it", "Lkotlin/sequences/Sequence;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 0x30)
        final class kotlin.sequences.SequencesKt__SequencesKt.flatten.1 extends Lambda implements Function1 {
            public static final kotlin.sequences.SequencesKt__SequencesKt.flatten.1 INSTANCE;

            static {
                kotlin.sequences.SequencesKt__SequencesKt.flatten.1.INSTANCE = new kotlin.sequences.SequencesKt__SequencesKt.flatten.1();
            }

            kotlin.sequences.SequencesKt__SequencesKt.flatten.1() {
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((Sequence)object0));
            }

            public final Iterator invoke(Sequence sequence0) {
                Intrinsics.checkNotNullParameter(sequence0, "it");
                return sequence0.iterator();
            }
        }

    }

    private static final Sequence flatten$SequencesKt__SequencesKt(Sequence sequence0, Function1 function10) {
        return sequence0 instanceof TransformingSequence ? ((TransformingSequence)sequence0).flatten$kotlin_stdlib(function10) : new FlatteningSequence(sequence0, kotlin.sequences.SequencesKt__SequencesKt.flatten.3.INSTANCE, function10);

        @Metadata(d1 = {"\u0000\u0004\n\u0002\b\u0006\u0010\u0000\u001A\u0002H\u0001\"\u0004\b\u0000\u0010\u0001\"\u0004\b\u0001\u0010\u00022\u0006\u0010\u0003\u001A\u0002H\u0001H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "T", "R", "it", "invoke", "(Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 9, 0}, xi = 0x30)
        final class kotlin.sequences.SequencesKt__SequencesKt.flatten.3 extends Lambda implements Function1 {
            public static final kotlin.sequences.SequencesKt__SequencesKt.flatten.3 INSTANCE;

            static {
                kotlin.sequences.SequencesKt__SequencesKt.flatten.3.INSTANCE = new kotlin.sequences.SequencesKt__SequencesKt.flatten.3();
            }

            kotlin.sequences.SequencesKt__SequencesKt.flatten.3() {
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public final Object invoke(Object object0) {
                return object0;
            }
        }

    }

    public static final Sequence flattenSequenceOfIterable(Sequence sequence0) {
        Intrinsics.checkNotNullParameter(sequence0, "<this>");
        return SequencesKt__SequencesKt.flatten$SequencesKt__SequencesKt(sequence0, kotlin.sequences.SequencesKt__SequencesKt.flatten.2.INSTANCE);

        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010(\n\u0002\b\u0002\n\u0002\u0010\u001C\n\u0000\u0010\u0000\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\f\u0010\u0003\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "T", "it", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 0x30)
        final class kotlin.sequences.SequencesKt__SequencesKt.flatten.2 extends Lambda implements Function1 {
            public static final kotlin.sequences.SequencesKt__SequencesKt.flatten.2 INSTANCE;

            static {
                kotlin.sequences.SequencesKt__SequencesKt.flatten.2.INSTANCE = new kotlin.sequences.SequencesKt__SequencesKt.flatten.2();
            }

            kotlin.sequences.SequencesKt__SequencesKt.flatten.2() {
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((Iterable)object0));
            }

            public final Iterator invoke(Iterable iterable0) {
                Intrinsics.checkNotNullParameter(iterable0, "it");
                return iterable0.iterator();
            }
        }

    }

    public static final Sequence generateSequence(Object object0, Function1 function10) {
        Intrinsics.checkNotNullParameter(function10, "nextFunction");
        return object0 == null ? EmptySequence.INSTANCE : new GeneratorSequence(new Function0(object0) {
            final Object $seed;

            {
                this.$seed = object0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return this.$seed;
            }
        }, function10);
    }

    public static final Sequence generateSequence(Function0 function00) {
        Intrinsics.checkNotNullParameter(function00, "nextFunction");
        return SequencesKt.constrainOnce(new GeneratorSequence(function00, new Function1(function00) {
            final Function0 $nextFunction;

            {
                this.$nextFunction = function00;
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public final Object invoke(Object object0) {
                Intrinsics.checkNotNullParameter(object0, "it");
                return this.$nextFunction.invoke();
            }
        }));
    }

    public static final Sequence generateSequence(Function0 function00, Function1 function10) {
        Intrinsics.checkNotNullParameter(function00, "seedFunction");
        Intrinsics.checkNotNullParameter(function10, "nextFunction");
        return new GeneratorSequence(function00, function10);
    }

    public static final Sequence ifEmpty(Sequence sequence0, Function0 function00) {
        Intrinsics.checkNotNullParameter(sequence0, "<this>");
        Intrinsics.checkNotNullParameter(function00, "defaultValue");
        return SequencesKt.sequence(new Function2(sequence0, function00, null) {
            final Function0 $defaultValue;
            final Sequence $this_ifEmpty;
            private Object L$0;
            int label;

            {
                this.$this_ifEmpty = sequence0;
                this.$defaultValue = function00;
                super(2, continuation0);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object object0, Continuation continuation0) {
                kotlin.sequences.SequencesKt__SequencesKt.ifEmpty.1 sequencesKt__SequencesKt$ifEmpty$10 = new kotlin.sequences.SequencesKt__SequencesKt.ifEmpty.1(this.$this_ifEmpty, this.$defaultValue, continuation0);
                sequencesKt__SequencesKt$ifEmpty$10.L$0 = object0;
                return sequencesKt__SequencesKt$ifEmpty$10;
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(((SequenceScope)object0), ((Continuation)object1));
            }

            public final Object invoke(SequenceScope sequenceScope0, Continuation continuation0) {
                return ((kotlin.sequences.SequencesKt__SequencesKt.ifEmpty.1)this.create(sequenceScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object object0) {
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(this.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        SequenceScope sequenceScope0 = (SequenceScope)this.L$0;
                        Iterator iterator0 = this.$this_ifEmpty.iterator();
                        if(iterator0.hasNext()) {
                            this.label = 1;
                            if(sequenceScope0.yieldAll(iterator0, this) == object1) {
                                return object1;
                            }
                        }
                        else {
                            Sequence sequence0 = (Sequence)this.$defaultValue.invoke();
                            this.label = 2;
                            if(sequenceScope0.yieldAll(sequence0, this) == object1) {
                                return object1;
                            }
                        }
                        return Unit.INSTANCE;
                    }
                    case 1: 
                    case 2: {
                        ResultKt.throwOnFailure(object0);
                        return Unit.INSTANCE;
                    }
                    default: {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    }
                }
            }
        });
    }

    private static final Sequence orEmpty(Sequence sequence0) {
        return sequence0 == null ? SequencesKt.emptySequence() : sequence0;
    }

    public static final Sequence sequenceOf(Object[] arr_object) {
        Intrinsics.checkNotNullParameter(arr_object, "elements");
        return arr_object.length == 0 ? SequencesKt.emptySequence() : ArraysKt.asSequence(arr_object);
    }

    public static final Sequence shuffled(Sequence sequence0) {
        Intrinsics.checkNotNullParameter(sequence0, "<this>");
        return SequencesKt.shuffled(sequence0, Random.Default);
    }

    public static final Sequence shuffled(Sequence sequence0, Random random0) {
        Intrinsics.checkNotNullParameter(sequence0, "<this>");
        Intrinsics.checkNotNullParameter(random0, "random");
        return SequencesKt.sequence(new Function2(sequence0, random0, null) {
            final Random $random;
            final Sequence $this_shuffled;
            private Object L$0;
            Object L$1;
            int label;

            {
                this.$this_shuffled = sequence0;
                this.$random = random0;
                super(2, continuation0);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object object0, Continuation continuation0) {
                kotlin.sequences.SequencesKt__SequencesKt.shuffled.1 sequencesKt__SequencesKt$shuffled$10 = new kotlin.sequences.SequencesKt__SequencesKt.shuffled.1(this.$this_shuffled, this.$random, continuation0);
                sequencesKt__SequencesKt$shuffled$10.L$0 = object0;
                return sequencesKt__SequencesKt$shuffled$10;
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(((SequenceScope)object0), ((Continuation)object1));
            }

            public final Object invoke(SequenceScope sequenceScope0, Continuation continuation0) {
                return ((kotlin.sequences.SequencesKt__SequencesKt.shuffled.1)this.create(sequenceScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object object0) {
                SequenceScope sequenceScope1;
                List list0;
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(this.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        SequenceScope sequenceScope0 = (SequenceScope)this.L$0;
                        list0 = SequencesKt.toMutableList(this.$this_shuffled);
                        sequenceScope1 = sequenceScope0;
                        break;
                    }
                    case 1: {
                        list0 = (List)this.L$1;
                        sequenceScope1 = (SequenceScope)this.L$0;
                        ResultKt.throwOnFailure(object0);
                        break;
                    }
                    default: {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    }
                }
                while(!list0.isEmpty()) {
                    int v = this.$random.nextInt(list0.size());
                    Object object2 = CollectionsKt.removeLast(list0);
                    if(v < list0.size()) {
                        object2 = list0.set(v, object2);
                    }
                    this.L$0 = sequenceScope1;
                    this.L$1 = list0;
                    this.label = 1;
                    if(sequenceScope1.yield(object2, this) == object1) {
                        return object1;
                    }
                    if(false) {
                        break;
                    }
                }
                return Unit.INSTANCE;
            }
        });
    }

    public static final Pair unzip(Sequence sequence0) {
        Intrinsics.checkNotNullParameter(sequence0, "<this>");
        ArrayList arrayList0 = new ArrayList();
        ArrayList arrayList1 = new ArrayList();
        for(Object object0: sequence0) {
            arrayList0.add(((Pair)object0).getFirst());
            arrayList1.add(((Pair)object0).getSecond());
        }
        return TuplesKt.to(arrayList0, arrayList1);
    }
}

