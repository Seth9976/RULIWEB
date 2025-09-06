package androidx.room;

import java.util.Set;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext.Element;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Job.DefaultImpls;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.ThreadContextElement;
import kotlinx.coroutines.ThreadContextElementKt;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;

@Metadata(d1 = {"\u0000N\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u001A\u0014\u0010\u0000\u001A\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001A\u00020\u0004H\u0002\u001A9\u0010\u0005\u001A\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006*\u00020\u00022\u0012\u0010\t\u001A\n\u0012\u0006\b\u0001\u0012\u00020\b0\n\"\u00020\b2\b\b\u0002\u0010\u000B\u001A\u00020\f¢\u0006\u0002\u0010\r\u001AL\u0010\u000E\u001A\u0002H\u000F\"\u0004\b\u0000\u0010\u000F*\u00020\u00022\u0006\u0010\u0010\u001A\u00020\u00012\'\u0010\u0011\u001A#\b\u0001\u0012\u0004\u0012\u00020\u0013\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000F0\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u00150\u0012¢\u0006\u0002\b\u0016H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\u0017\u001A9\u0010\u0018\u001A\u0002H\u000F\"\u0004\b\u0000\u0010\u000F*\u00020\u00022\u001C\u0010\u0019\u001A\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000F0\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u00150\u001AH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u001B\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001C"}, d2 = {"createTransactionContext", "Lkotlin/coroutines/CoroutineContext;", "Landroidx/room/RoomDatabase;", "dispatcher", "Lkotlin/coroutines/ContinuationInterceptor;", "invalidationTrackerFlow", "Lkotlinx/coroutines/flow/Flow;", "", "", "tables", "", "emitInitialState", "", "(Landroidx/room/RoomDatabase;[Ljava/lang/String;Z)Lkotlinx/coroutines/flow/Flow;", "startTransactionCoroutine", "R", "context", "transactionBlock", "Lkotlin/Function2;", "Lkotlinx/coroutines/CoroutineScope;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "(Landroidx/room/RoomDatabase;Lkotlin/coroutines/CoroutineContext;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "withTransaction", "block", "Lkotlin/Function1;", "(Landroidx/room/RoomDatabase;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "room-ktx_release"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class RoomDatabaseKt {
    public static final Object access$startTransactionCoroutine(RoomDatabase roomDatabase0, CoroutineContext coroutineContext0, Function2 function20, Continuation continuation0) {
        return RoomDatabaseKt.startTransactionCoroutine(roomDatabase0, coroutineContext0, function20, continuation0);
    }

    private static final CoroutineContext createTransactionContext(RoomDatabase roomDatabase0, ContinuationInterceptor continuationInterceptor0) {
        TransactionElement transactionElement0 = new TransactionElement(continuationInterceptor0);
        ThreadContextElement threadContextElement0 = ThreadContextElementKt.asContextElement(roomDatabase0.getSuspendingTransactionId(), System.identityHashCode(transactionElement0));
        return continuationInterceptor0.plus(transactionElement0).plus(threadContextElement0);
    }

    public static final Flow invalidationTrackerFlow(RoomDatabase roomDatabase0, String[] arr_s, boolean z) {
        return FlowKt.callbackFlow(new Function2(z, roomDatabase0, arr_s, null) {
            final boolean $emitInitialState;
            final String[] $tables;
            final RoomDatabase $this_invalidationTrackerFlow;
            private Object L$0;
            int label;

            {
                this.$emitInitialState = z;
                this.$this_invalidationTrackerFlow = roomDatabase0;
                this.$tables = arr_s;
                super(2, continuation0);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object object0, Continuation continuation0) {
                androidx.room.RoomDatabaseKt.invalidationTrackerFlow.1 roomDatabaseKt$invalidationTrackerFlow$10 = new androidx.room.RoomDatabaseKt.invalidationTrackerFlow.1(this.$emitInitialState, this.$this_invalidationTrackerFlow, this.$tables, continuation0);
                roomDatabaseKt$invalidationTrackerFlow$10.L$0 = object0;
                return roomDatabaseKt$invalidationTrackerFlow$10;
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(((ProducerScope)object0), ((Continuation)object1));
            }

            public final Object invoke(ProducerScope producerScope0, Continuation continuation0) {
                return ((androidx.room.RoomDatabaseKt.invalidationTrackerFlow.1)this.create(producerScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object object0) {
                ContinuationInterceptor continuationInterceptor0;
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(this.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        ProducerScope producerScope0 = (ProducerScope)this.L$0;
                        AtomicBoolean atomicBoolean0 = new AtomicBoolean(this.$emitInitialState);
                        androidx.room.RoomDatabaseKt.invalidationTrackerFlow.1.observer.1 roomDatabaseKt$invalidationTrackerFlow$1$observer$10 = new Observer(atomicBoolean0) {
                            @Override  // androidx.room.InvalidationTracker$Observer
                            public void onInvalidated(Set set0) {
                                if(producerScope0.get()) {
                                    return;
                                }
                                this.$$this$callbackFlow.trySend-JP2dKIU(set0);
                            }
                        };
                        TransactionElement transactionElement0 = (TransactionElement)producerScope0.getCoroutineContext().get(TransactionElement.Key);
                        if(transactionElement0 == null) {
                            continuationInterceptor0 = CoroutinesRoomKt.getQueryDispatcher(this.$this_invalidationTrackerFlow);
                        }
                        else {
                            continuationInterceptor0 = transactionElement0.getTransactionDispatcher$room_ktx_release();
                            if(continuationInterceptor0 == null) {
                                continuationInterceptor0 = CoroutinesRoomKt.getQueryDispatcher(this.$this_invalidationTrackerFlow);
                            }
                        }
                        Function0 function00 = new Function0() {
                            final Job $job;

                            {
                                this.$job = job0;
                                super(0);
                            }

                            @Override  // kotlin.jvm.functions.Function0
                            public Object invoke() {
                                this.invoke();
                                return Unit.INSTANCE;
                            }

                            public final void invoke() {
                                DefaultImpls.cancel$default(this.$job, null, 1, null);
                            }
                        };
                        this.label = 1;
                        return ProduceKt.awaitClose(producerScope0, function00, this) == object1 ? object1 : Unit.INSTANCE;
                    }
                    case 1: {
                        ResultKt.throwOnFailure(object0);
                        return Unit.INSTANCE;
                    }
                    default: {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    }
                }

                @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001A\u00020\u0001*\u00020\u0002H\u008A@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 0x30)
                @DebugMetadata(c = "androidx.room.RoomDatabaseKt$invalidationTrackerFlow$1$job$1", f = "RoomDatabaseExt.kt", i = {}, l = {230}, m = "invokeSuspend", n = {}, s = {})
                final class androidx.room.RoomDatabaseKt.invalidationTrackerFlow.1.job.1 extends SuspendLambda implements Function2 {
                    final ProducerScope $$this$callbackFlow;
                    final boolean $emitInitialState;
                    final AtomicBoolean $ignoreInvalidation;
                    final androidx.room.RoomDatabaseKt.invalidationTrackerFlow.1.observer.1 $observer;
                    final String[] $tables;
                    final RoomDatabase $this_invalidationTrackerFlow;
                    int label;

                    androidx.room.RoomDatabaseKt.invalidationTrackerFlow.1.job.1(RoomDatabase roomDatabase0, androidx.room.RoomDatabaseKt.invalidationTrackerFlow.1.observer.1 roomDatabaseKt$invalidationTrackerFlow$1$observer$10, boolean z, ProducerScope producerScope0, String[] arr_s, AtomicBoolean atomicBoolean0, Continuation continuation0) {
                        this.$this_invalidationTrackerFlow = roomDatabase0;
                        this.$observer = roomDatabaseKt$invalidationTrackerFlow$1$observer$10;
                        this.$emitInitialState = z;
                        this.$$this$callbackFlow = producerScope0;
                        this.$tables = arr_s;
                        this.$ignoreInvalidation = atomicBoolean0;
                        super(2, continuation0);
                    }

                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation create(Object object0, Continuation continuation0) {
                        return new androidx.room.RoomDatabaseKt.invalidationTrackerFlow.1.job.1(this.$this_invalidationTrackerFlow, this.$observer, this.$emitInitialState, this.$$this$callbackFlow, this.$tables, this.$ignoreInvalidation, continuation0);
                    }

                    @Override  // kotlin.jvm.functions.Function2
                    public Object invoke(Object object0, Object object1) {
                        return this.invoke(((CoroutineScope)object0), ((Continuation)object1));
                    }

                    public final Object invoke(CoroutineScope coroutineScope0, Continuation continuation0) {
                        return ((androidx.room.RoomDatabaseKt.invalidationTrackerFlow.1.job.1)this.create(coroutineScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object object0) {
                        Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        switch(this.label) {
                            case 0: {
                                ResultKt.throwOnFailure(object0);
                                this.$this_invalidationTrackerFlow.getInvalidationTracker().addObserver(this.$observer);
                                try {
                                    if(this.$emitInitialState) {
                                        Set set0 = ArraysKt.toSet(this.$tables);
                                        this.$$this$callbackFlow.trySend-JP2dKIU(set0);
                                    }
                                    this.$ignoreInvalidation.set(false);
                                    this.label = 1;
                                    if(DelayKt.awaitCancellation(this) == object1) {
                                        return object1;
                                    label_12:
                                        ResultKt.throwOnFailure(object0);
                                    }
                                    throw new KotlinNothingValueException();
                                }
                                catch(Throwable throwable0) {
                                    break;
                                }
                            }
                            case 1: {
                                goto label_12;
                            }
                            default: {
                                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                            }
                        }
                        this.$this_invalidationTrackerFlow.getInvalidationTracker().removeObserver(this.$observer);
                        throw throwable0;
                    }
                }

            }
        });
    }

    public static Flow invalidationTrackerFlow$default(RoomDatabase roomDatabase0, String[] arr_s, boolean z, int v, Object object0) {
        if((v & 2) != 0) {
            z = true;
        }
        return RoomDatabaseKt.invalidationTrackerFlow(roomDatabase0, arr_s, z);
    }

    private static final Object startTransactionCoroutine(RoomDatabase roomDatabase0, CoroutineContext coroutineContext0, Function2 function20, Continuation continuation0) {
        CancellableContinuationImpl cancellableContinuationImpl0 = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation0), 1);
        cancellableContinuationImpl0.initCancellability();
        try {
            roomDatabase0.getTransactionExecutor().execute(new Runnable() {
                @Override
                public final void run() {
                    try {
                        BuildersKt.runBlocking(coroutineContext0.minusKey(ContinuationInterceptor.Key), new Function2(cancellableContinuationImpl0, function20, null) {
                            final CancellableContinuation $continuation;
                            final RoomDatabase $this_startTransactionCoroutine;
                            final Function2 $transactionBlock;
                            private Object L$0;
                            int label;

                            {
                                this.$this_startTransactionCoroutine = roomDatabase0;
                                this.$continuation = cancellableContinuation0;
                                this.$transactionBlock = function20;
                                super(2, continuation0);
                            }

                            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            public final Continuation create(Object object0, Continuation continuation0) {
                                androidx.room.RoomDatabaseKt.startTransactionCoroutine.2.1.1 roomDatabaseKt$startTransactionCoroutine$2$1$10 = new androidx.room.RoomDatabaseKt.startTransactionCoroutine.2.1.1(this.$this_startTransactionCoroutine, this.$continuation, this.$transactionBlock, continuation0);
                                roomDatabaseKt$startTransactionCoroutine$2$1$10.L$0 = object0;
                                return roomDatabaseKt$startTransactionCoroutine$2$1$10;
                            }

                            @Override  // kotlin.jvm.functions.Function2
                            public Object invoke(Object object0, Object object1) {
                                return this.invoke(((CoroutineScope)object0), ((Continuation)object1));
                            }

                            public final Object invoke(CoroutineScope coroutineScope0, Continuation continuation0) {
                                return ((androidx.room.RoomDatabaseKt.startTransactionCoroutine.2.1.1)this.create(coroutineScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
                            }

                            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            public final Object invokeSuspend(Object object0) {
                                Continuation continuation1;
                                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                switch(this.label) {
                                    case 0: {
                                        ResultKt.throwOnFailure(object0);
                                        Element coroutineContext$Element0 = ((CoroutineScope)this.L$0).getCoroutineContext().get(ContinuationInterceptor.Key);
                                        Intrinsics.checkNotNull(coroutineContext$Element0);
                                        CoroutineContext coroutineContext0 = RoomDatabaseKt.createTransactionContext(this.$this_startTransactionCoroutine, ((ContinuationInterceptor)coroutineContext$Element0));
                                        Continuation continuation0 = this.$continuation;
                                        this.L$0 = continuation0;
                                        this.label = 1;
                                        object0 = BuildersKt.withContext(coroutineContext0, this.$transactionBlock, this);
                                        if(object0 == object1) {
                                            return object1;
                                        }
                                        continuation1 = continuation0;
                                        break;
                                    }
                                    case 1: {
                                        continuation1 = (Continuation)this.L$0;
                                        ResultKt.throwOnFailure(object0);
                                        break;
                                    }
                                    default: {
                                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                                    }
                                }
                                continuation1.resumeWith(object0);
                                return Unit.INSTANCE;
                            }
                        });
                    }
                    catch(Throwable throwable0) {
                        cancellableContinuationImpl0.cancel(throwable0);
                    }
                }
            });
        }
        catch(RejectedExecutionException rejectedExecutionException0) {
            cancellableContinuationImpl0.cancel(new IllegalStateException("Unable to acquire a thread to perform the database transaction.", rejectedExecutionException0));
        }
        Object object0 = cancellableContinuationImpl0.getResult();
        if(object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation0);
        }
        return object0;
    }

    public static final Object withTransaction(RoomDatabase roomDatabase0, Function1 function10, Continuation continuation0) {
        ContinuationInterceptor continuationInterceptor0 = null;
        androidx.room.RoomDatabaseKt.withTransaction.transactionBlock.1 roomDatabaseKt$withTransaction$transactionBlock$10 = new Function2(roomDatabase0, function10, null) {
            final Function1 $block;
            final RoomDatabase $this_withTransaction;
            private Object L$0;
            int label;

            {
                this.$this_withTransaction = roomDatabase0;
                this.$block = function10;
                super(2, continuation0);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object object0, Continuation continuation0) {
                androidx.room.RoomDatabaseKt.withTransaction.transactionBlock.1 roomDatabaseKt$withTransaction$transactionBlock$10 = new androidx.room.RoomDatabaseKt.withTransaction.transactionBlock.1(this.$this_withTransaction, this.$block, continuation0);
                roomDatabaseKt$withTransaction$transactionBlock$10.L$0 = object0;
                return roomDatabaseKt$withTransaction$transactionBlock$10;
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(((CoroutineScope)object0), ((Continuation)object1));
            }

            public final Object invoke(CoroutineScope coroutineScope0, Continuation continuation0) {
                return ((androidx.room.RoomDatabaseKt.withTransaction.transactionBlock.1)this.create(coroutineScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object object0) {
                Throwable throwable3;
                Object object2;
                Throwable throwable1;
                TransactionElement transactionElement1;
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(this.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        Element coroutineContext$Element0 = ((CoroutineScope)this.L$0).getCoroutineContext().get(TransactionElement.Key);
                        Intrinsics.checkNotNull(coroutineContext$Element0);
                        TransactionElement transactionElement0 = (TransactionElement)coroutineContext$Element0;
                        transactionElement0.acquire();
                        try {
                            this.$this_withTransaction.beginTransaction();
                        }
                        catch(Throwable throwable0) {
                            transactionElement1 = transactionElement0;
                            throwable1 = throwable0;
                            transactionElement1.release();
                            throw throwable1;
                        }
                        try {
                            this.L$0 = transactionElement0;
                            this.label = 1;
                            object2 = this.$block.invoke(this);
                        }
                        catch(Throwable throwable2) {
                            transactionElement1 = transactionElement0;
                            throwable3 = throwable2;
                            break;
                        }
                        if(object2 == object1) {
                            return object1;
                        }
                        transactionElement1 = transactionElement0;
                        object0 = object2;
                        goto label_29;
                    }
                    case 1: {
                        transactionElement1 = (TransactionElement)this.L$0;
                        try {
                            ResultKt.throwOnFailure(object0);
                        label_29:
                            this.$this_withTransaction.setTransactionSuccessful();
                            goto label_34;
                        }
                        catch(Throwable throwable3) {
                            break;
                        }
                    }
                    default: {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    }
                }
                try {
                    this.$this_withTransaction.endTransaction();
                    throw throwable3;
                label_34:
                    this.$this_withTransaction.endTransaction();
                }
                catch(Throwable throwable1) {
                    transactionElement1.release();
                    throw throwable1;
                }
                transactionElement1.release();
                return object0;
            }
        };
        TransactionElement transactionElement0 = (TransactionElement)continuation0.getContext().get(TransactionElement.Key);
        if(transactionElement0 != null) {
            continuationInterceptor0 = transactionElement0.getTransactionDispatcher$room_ktx_release();
        }
        return continuationInterceptor0 == null ? RoomDatabaseKt.startTransactionCoroutine(roomDatabase0, continuation0.getContext(), roomDatabaseKt$withTransaction$transactionBlock$10, continuation0) : BuildersKt.withContext(continuationInterceptor0, roomDatabaseKt$withTransaction$transactionBlock$10, continuation0);
    }
}

