package androidx.room;

import android.os.CancellationSignal;
import androidx.sqlite.db.SupportSQLiteCompat.Api16Impl;
import java.util.Set;
import java.util.concurrent.Callable;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job.DefaultImpls;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelIterator;
import kotlinx.coroutines.channels.ChannelKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Landroidx/room/CoroutinesRoom;", "", "()V", "Companion", "room-ktx_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class CoroutinesRoom {
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002JJ\u0010\u0003\u001A\r\u0012\t\u0012\u0007H\u0005¢\u0006\u0002\b\u00060\u0004\"\u0004\b\u0000\u0010\u00052\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\n2\f\u0010\u000B\u001A\b\u0012\u0004\u0012\u00020\r0\f2\f\u0010\u000E\u001A\b\u0012\u0004\u0012\u0002H\u00050\u000FH\u0007¢\u0006\u0002\u0010\u0010J?\u0010\u0011\u001A\u0002H\u0005\"\u0004\b\u0000\u0010\u00052\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\n2\b\u0010\u0012\u001A\u0004\u0018\u00010\u00132\f\u0010\u000E\u001A\b\u0012\u0004\u0012\u0002H\u00050\u000FH\u0087@ø\u0001\u0000¢\u0006\u0002\u0010\u0014J5\u0010\u0011\u001A\u0002H\u0005\"\u0004\b\u0000\u0010\u00052\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\n2\f\u0010\u000E\u001A\b\u0012\u0004\u0012\u0002H\u00050\u000FH\u0087@ø\u0001\u0000¢\u0006\u0002\u0010\u0015\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0016"}, d2 = {"Landroidx/room/CoroutinesRoom$Companion;", "", "()V", "createFlow", "Lkotlinx/coroutines/flow/Flow;", "R", "Lkotlin/jvm/JvmSuppressWildcards;", "db", "Landroidx/room/RoomDatabase;", "inTransaction", "", "tableNames", "", "", "callable", "Ljava/util/concurrent/Callable;", "(Landroidx/room/RoomDatabase;Z[Ljava/lang/String;Ljava/util/concurrent/Callable;)Lkotlinx/coroutines/flow/Flow;", "execute", "cancellationSignal", "Landroid/os/CancellationSignal;", "(Landroidx/room/RoomDatabase;ZLandroid/os/CancellationSignal;Ljava/util/concurrent/Callable;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "(Landroidx/room/RoomDatabase;ZLjava/util/concurrent/Callable;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "room-ktx_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        @JvmStatic
        public final Flow createFlow(RoomDatabase roomDatabase0, boolean z, String[] arr_s, Callable callable0) {
            return FlowKt.flow(new Function2(roomDatabase0, arr_s, callable0, null) {
                final Callable $callable;
                final RoomDatabase $db;
                final boolean $inTransaction;
                final String[] $tableNames;
                private Object L$0;
                int label;

                {
                    this.$inTransaction = z;
                    this.$db = roomDatabase0;
                    this.$tableNames = arr_s;
                    this.$callable = callable0;
                    super(2, continuation0);
                }

                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation create(Object object0, Continuation continuation0) {
                    androidx.room.CoroutinesRoom.Companion.createFlow.1 coroutinesRoom$Companion$createFlow$10 = new androidx.room.CoroutinesRoom.Companion.createFlow.1(this.$inTransaction, this.$db, this.$tableNames, this.$callable, continuation0);
                    coroutinesRoom$Companion$createFlow$10.L$0 = object0;
                    return coroutinesRoom$Companion$createFlow$10;
                }

                @Override  // kotlin.jvm.functions.Function2
                public Object invoke(Object object0, Object object1) {
                    return this.invoke(((FlowCollector)object0), ((Continuation)object1));
                }

                public final Object invoke(FlowCollector flowCollector0, Continuation continuation0) {
                    return ((androidx.room.CoroutinesRoom.Companion.createFlow.1)this.create(flowCollector0, continuation0)).invokeSuspend(Unit.INSTANCE);
                }

                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object object0) {
                    Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    switch(this.label) {
                        case 0: {
                            ResultKt.throwOnFailure(object0);
                            androidx.room.CoroutinesRoom.Companion.createFlow.1.1 coroutinesRoom$Companion$createFlow$1$10 = new Function2(this.$db, ((FlowCollector)this.L$0), this.$tableNames, this.$callable, null) {
                                final FlowCollector $$this$flow;
                                final Callable $callable;
                                final RoomDatabase $db;
                                final boolean $inTransaction;
                                final String[] $tableNames;
                                private Object L$0;
                                int label;

                                {
                                    this.$inTransaction = z;
                                    this.$db = roomDatabase0;
                                    this.$$this$flow = flowCollector0;
                                    this.$tableNames = arr_s;
                                    this.$callable = callable0;
                                    super(2, continuation0);
                                }

                                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                public final Continuation create(Object object0, Continuation continuation0) {
                                    androidx.room.CoroutinesRoom.Companion.createFlow.1.1 coroutinesRoom$Companion$createFlow$1$10 = new androidx.room.CoroutinesRoom.Companion.createFlow.1.1(this.$inTransaction, this.$db, this.$$this$flow, this.$tableNames, this.$callable, continuation0);
                                    coroutinesRoom$Companion$createFlow$1$10.L$0 = object0;
                                    return coroutinesRoom$Companion$createFlow$1$10;
                                }

                                @Override  // kotlin.jvm.functions.Function2
                                public Object invoke(Object object0, Object object1) {
                                    return this.invoke(((CoroutineScope)object0), ((Continuation)object1));
                                }

                                public final Object invoke(CoroutineScope coroutineScope0, Continuation continuation0) {
                                    return ((androidx.room.CoroutinesRoom.Companion.createFlow.1.1)this.create(coroutineScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
                                }

                                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                public final Object invokeSuspend(Object object0) {
                                    ContinuationInterceptor continuationInterceptor0;
                                    Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                    switch(this.label) {
                                        case 0: {
                                            ResultKt.throwOnFailure(object0);
                                            CoroutineScope coroutineScope0 = (CoroutineScope)this.L$0;
                                            Channel channel0 = ChannelKt.Channel$default(-1, null, null, 6, null);
                                            androidx.room.CoroutinesRoom.Companion.createFlow.1.1.observer.1 coroutinesRoom$Companion$createFlow$1$1$observer$10 = new Observer(channel0) {
                                                @Override  // androidx.room.InvalidationTracker$Observer
                                                public void onInvalidated(Set set0) {
                                                    this.$observerChannel.trySend-JP2dKIU(Unit.INSTANCE);
                                                }
                                            };
                                            channel0.trySend-JP2dKIU(Unit.INSTANCE);
                                            TransactionElement transactionElement0 = (TransactionElement)coroutineScope0.getCoroutineContext().get(TransactionElement.Key);
                                            if(transactionElement0 == null) {
                                                continuationInterceptor0 = this.$inTransaction ? CoroutinesRoomKt.getTransactionDispatcher(this.$db) : CoroutinesRoomKt.getQueryDispatcher(this.$db);
                                            }
                                            else {
                                                continuationInterceptor0 = transactionElement0.getTransactionDispatcher$room_ktx_release();
                                                if(continuationInterceptor0 == null) {
                                                    continuationInterceptor0 = this.$inTransaction ? CoroutinesRoomKt.getTransactionDispatcher(this.$db) : CoroutinesRoomKt.getQueryDispatcher(this.$db);
                                                }
                                            }
                                            Channel channel1 = ChannelKt.Channel$default(0, null, null, 7, null);
                                            BuildersKt.launch$default(coroutineScope0, continuationInterceptor0, null, new Function2(coroutinesRoom$Companion$createFlow$1$1$observer$10, channel0, this.$callable, channel1, null) {
                                                final Callable $callable;
                                                final RoomDatabase $db;
                                                final androidx.room.CoroutinesRoom.Companion.createFlow.1.1.observer.1 $observer;
                                                final Channel $observerChannel;
                                                final Channel $resultChannel;
                                                Object L$0;
                                                int label;

                                                {
                                                    this.$db = roomDatabase0;
                                                    this.$observer = coroutinesRoom$Companion$createFlow$1$1$observer$10;
                                                    this.$observerChannel = channel0;
                                                    this.$callable = callable0;
                                                    this.$resultChannel = channel1;
                                                    super(2, continuation0);
                                                }

                                                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                public final Continuation create(Object object0, Continuation continuation0) {
                                                    return new androidx.room.CoroutinesRoom.Companion.createFlow.1.1.1(this.$db, this.$observer, this.$observerChannel, this.$callable, this.$resultChannel, continuation0);
                                                }

                                                @Override  // kotlin.jvm.functions.Function2
                                                public Object invoke(Object object0, Object object1) {
                                                    return this.invoke(((CoroutineScope)object0), ((Continuation)object1));
                                                }

                                                public final Object invoke(CoroutineScope coroutineScope0, Continuation continuation0) {
                                                    return ((androidx.room.CoroutinesRoom.Companion.createFlow.1.1.1)this.create(coroutineScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
                                                }

                                                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                public final Object invokeSuspend(Object object0) {
                                                    ChannelIterator channelIterator1;
                                                    ChannelIterator channelIterator0;
                                                    Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                                    switch(this.label) {
                                                        case 0: {
                                                            ResultKt.throwOnFailure(object0);
                                                            this.$db.getInvalidationTracker().addObserver(this.$observer);
                                                            try {
                                                                channelIterator0 = this.$observerChannel.iterator();
                                                                goto label_13;
                                                            }
                                                            catch(Throwable throwable0) {
                                                                break;
                                                            }
                                                        }
                                                        case 1: {
                                                            channelIterator1 = (ChannelIterator)this.L$0;
                                                            try {
                                                                ResultKt.throwOnFailure(object0);
                                                                goto label_19;
                                                            }
                                                            catch(Throwable throwable0) {
                                                                break;
                                                            }
                                                        }
                                                        case 2: {
                                                            channelIterator1 = (ChannelIterator)this.L$0;
                                                            try {
                                                                ResultKt.throwOnFailure(object0);
                                                                while(true) {
                                                                    channelIterator0 = channelIterator1;
                                                                label_13:
                                                                    this.L$0 = channelIterator0;
                                                                    this.label = 1;
                                                                    Object object2 = channelIterator0.hasNext(this);
                                                                    if(object2 != object1) {
                                                                        channelIterator1 = channelIterator0;
                                                                        object0 = object2;
                                                                    label_19:
                                                                        if(!((Boolean)object0).booleanValue()) {
                                                                            goto label_29;
                                                                        }
                                                                        channelIterator1.next();
                                                                        Object object3 = this.$callable.call();
                                                                        this.L$0 = channelIterator1;
                                                                        this.label = 2;
                                                                        if(this.$resultChannel.send(object3, this) != object1) {
                                                                            continue;
                                                                        }
                                                                    }
                                                                    return object1;
                                                                }
                                                            }
                                                            catch(Throwable throwable0) {
                                                                break;
                                                            }
                                                        }
                                                        default: {
                                                            throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                                                        }
                                                    }
                                                    this.$db.getInvalidationTracker().removeObserver(this.$observer);
                                                    throw throwable0;
                                                label_29:
                                                    this.$db.getInvalidationTracker().removeObserver(this.$observer);
                                                    return Unit.INSTANCE;
                                                }
                                            }, 2, null);
                                            this.label = 1;
                                            return FlowKt.emitAll(this.$$this$flow, channel1, this) == object1 ? object1 : Unit.INSTANCE;
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
                            };
                            this.label = 1;
                            return CoroutineScopeKt.coroutineScope(coroutinesRoom$Companion$createFlow$1$10, this) == object1 ? object1 : Unit.INSTANCE;
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
            });
        }

        @JvmStatic
        public final Object execute(RoomDatabase roomDatabase0, boolean z, CancellationSignal cancellationSignal0, Callable callable0, Continuation continuation0) {
            ContinuationInterceptor continuationInterceptor0;
            if(roomDatabase0.isOpenInternal() && roomDatabase0.inTransaction()) {
                return callable0.call();
            }
            TransactionElement transactionElement0 = (TransactionElement)continuation0.getContext().get(TransactionElement.Key);
            if(transactionElement0 == null) {
                continuationInterceptor0 = z ? CoroutinesRoomKt.getTransactionDispatcher(roomDatabase0) : CoroutinesRoomKt.getQueryDispatcher(roomDatabase0);
            }
            else {
                continuationInterceptor0 = transactionElement0.getTransactionDispatcher$room_ktx_release();
                if(continuationInterceptor0 == null) {
                    continuationInterceptor0 = z ? CoroutinesRoomKt.getTransactionDispatcher(roomDatabase0) : CoroutinesRoomKt.getQueryDispatcher(roomDatabase0);
                }
            }
            CancellableContinuationImpl cancellableContinuationImpl0 = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation0), 1);
            cancellableContinuationImpl0.initCancellability();
            androidx.room.CoroutinesRoom.Companion.execute.4.job.1 coroutinesRoom$Companion$execute$4$job$10 = new Function2(cancellableContinuationImpl0, null) {
                final Callable $callable;
                final CancellableContinuation $continuation;
                int label;

                {
                    this.$callable = callable0;
                    this.$continuation = cancellableContinuation0;
                    super(2, continuation0);
                }

                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation create(Object object0, Continuation continuation0) {
                    return new androidx.room.CoroutinesRoom.Companion.execute.4.job.1(this.$callable, this.$continuation, continuation0);
                }

                @Override  // kotlin.jvm.functions.Function2
                public Object invoke(Object object0, Object object1) {
                    return this.invoke(((CoroutineScope)object0), ((Continuation)object1));
                }

                public final Object invoke(CoroutineScope coroutineScope0, Continuation continuation0) {
                    return ((androidx.room.CoroutinesRoom.Companion.execute.4.job.1)this.create(coroutineScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
                }

                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object object0) {
                    if(this.label != 0) {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    }
                    ResultKt.throwOnFailure(object0);
                    try {
                        Object object1 = Result.constructor-impl(this.$callable.call());
                        this.$continuation.resumeWith(object1);
                    }
                    catch(Throwable throwable0) {
                        Object object2 = Result.constructor-impl(ResultKt.createFailure(throwable0));
                        this.$continuation.resumeWith(object2);
                    }
                    return Unit.INSTANCE;
                }
            };
            cancellableContinuationImpl0.invokeOnCancellation(new Function1(BuildersKt.launch$default(GlobalScope.INSTANCE, continuationInterceptor0, null, coroutinesRoom$Companion$execute$4$job$10, 2, null)) {
                final CancellationSignal $cancellationSignal;
                final Job $job;

                {
                    this.$cancellationSignal = cancellationSignal0;
                    this.$job = job0;
                    super(1);
                }

                @Override  // kotlin.jvm.functions.Function1
                public Object invoke(Object object0) {
                    this.invoke(((Throwable)object0));
                    return Unit.INSTANCE;
                }

                public final void invoke(Throwable throwable0) {
                    CancellationSignal cancellationSignal0 = this.$cancellationSignal;
                    if(cancellationSignal0 != null) {
                        Api16Impl.cancel(cancellationSignal0);
                    }
                    DefaultImpls.cancel$default(this.$job, null, 1, null);
                }
            });
            Object object0 = cancellableContinuationImpl0.getResult();
            if(object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                DebugProbesKt.probeCoroutineSuspended(continuation0);
            }
            return object0;
        }

        @JvmStatic
        public final Object execute(RoomDatabase roomDatabase0, boolean z, Callable callable0, Continuation continuation0) {
            if(roomDatabase0.isOpenInternal() && roomDatabase0.inTransaction()) {
                return callable0.call();
            }
            TransactionElement transactionElement0 = (TransactionElement)continuation0.getContext().get(TransactionElement.Key);
            if(transactionElement0 != null) {
                ContinuationInterceptor continuationInterceptor0 = transactionElement0.getTransactionDispatcher$room_ktx_release();
                return continuationInterceptor0 == null ? BuildersKt.withContext((z ? CoroutinesRoomKt.getTransactionDispatcher(roomDatabase0) : CoroutinesRoomKt.getQueryDispatcher(roomDatabase0)), new Function2(null) {
                    final Callable $callable;
                    int label;

                    {
                        this.$callable = callable0;
                        super(2, continuation0);
                    }

                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation create(Object object0, Continuation continuation0) {
                        return new androidx.room.CoroutinesRoom.Companion.execute.2(this.$callable, continuation0);
                    }

                    @Override  // kotlin.jvm.functions.Function2
                    public Object invoke(Object object0, Object object1) {
                        return this.invoke(((CoroutineScope)object0), ((Continuation)object1));
                    }

                    public final Object invoke(CoroutineScope coroutineScope0, Continuation continuation0) {
                        return ((androidx.room.CoroutinesRoom.Companion.execute.2)this.create(coroutineScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object object0) {
                        if(this.label != 0) {
                            throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                        }
                        ResultKt.throwOnFailure(object0);
                        return this.$callable.call();
                    }
                }, continuation0) : BuildersKt.withContext(continuationInterceptor0, new Function2(null) {
                    final Callable $callable;
                    int label;

                    {
                        this.$callable = callable0;
                        super(2, continuation0);
                    }

                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation create(Object object0, Continuation continuation0) {
                        return new androidx.room.CoroutinesRoom.Companion.execute.2(this.$callable, continuation0);
                    }

                    @Override  // kotlin.jvm.functions.Function2
                    public Object invoke(Object object0, Object object1) {
                        return this.invoke(((CoroutineScope)object0), ((Continuation)object1));
                    }

                    public final Object invoke(CoroutineScope coroutineScope0, Continuation continuation0) {
                        return ((androidx.room.CoroutinesRoom.Companion.execute.2)this.create(coroutineScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object object0) {
                        if(this.label != 0) {
                            throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                        }
                        ResultKt.throwOnFailure(object0);
                        return this.$callable.call();
                    }
                }, continuation0);
            }
            return BuildersKt.withContext((z ? CoroutinesRoomKt.getTransactionDispatcher(roomDatabase0) : CoroutinesRoomKt.getQueryDispatcher(roomDatabase0)), new Function2(null) {
                final Callable $callable;
                int label;

                {
                    this.$callable = callable0;
                    super(2, continuation0);
                }

                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation create(Object object0, Continuation continuation0) {
                    return new androidx.room.CoroutinesRoom.Companion.execute.2(this.$callable, continuation0);
                }

                @Override  // kotlin.jvm.functions.Function2
                public Object invoke(Object object0, Object object1) {
                    return this.invoke(((CoroutineScope)object0), ((Continuation)object1));
                }

                public final Object invoke(CoroutineScope coroutineScope0, Continuation continuation0) {
                    return ((androidx.room.CoroutinesRoom.Companion.execute.2)this.create(coroutineScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
                }

                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object object0) {
                    if(this.label != 0) {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    }
                    ResultKt.throwOnFailure(object0);
                    return this.$callable.call();
                }
            }, continuation0);
        }
    }

    public static final Companion Companion;

    static {
        CoroutinesRoom.Companion = new Companion(null);
    }

    @JvmStatic
    public static final Flow createFlow(RoomDatabase roomDatabase0, boolean z, String[] arr_s, Callable callable0) {
        return CoroutinesRoom.Companion.createFlow(roomDatabase0, z, arr_s, callable0);
    }

    @JvmStatic
    public static final Object execute(RoomDatabase roomDatabase0, boolean z, CancellationSignal cancellationSignal0, Callable callable0, Continuation continuation0) {
        return CoroutinesRoom.Companion.execute(roomDatabase0, z, cancellationSignal0, callable0, continuation0);
    }

    @JvmStatic
    public static final Object execute(RoomDatabase roomDatabase0, boolean z, Callable callable0, Continuation continuation0) {
        return CoroutinesRoom.Companion.execute(roomDatabase0, z, callable0, continuation0);
    }
}

