package androidx.work.impl.constraints;

import android.net.ConnectivityManager;
import android.net.NetworkRequest;
import androidx.work.Constraints;
import androidx.work.Logger;
import androidx.work.impl.constraints.controllers.ConstraintController;
import androidx.work.impl.model.WorkSpec;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.channels.SendChannel.DefaultImpls;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001A\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\nH\u0016J\u0010\u0010\u000B\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\nH\u0016J\u0016\u0010\f\u001A\b\u0012\u0004\u0012\u00020\u000E0\r2\u0006\u0010\u000F\u001A\u00020\u0010H\u0016R\u000E\u0010\u0002\u001A\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0004\u001A\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Landroidx/work/impl/constraints/NetworkRequestConstraintController;", "Landroidx/work/impl/constraints/controllers/ConstraintController;", "connManager", "Landroid/net/ConnectivityManager;", "timeoutMs", "", "(Landroid/net/ConnectivityManager;J)V", "hasConstraint", "", "workSpec", "Landroidx/work/impl/model/WorkSpec;", "isCurrentlyConstrained", "track", "Lkotlinx/coroutines/flow/Flow;", "Landroidx/work/impl/constraints/ConstraintsState;", "constraints", "Landroidx/work/Constraints;", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class NetworkRequestConstraintController implements ConstraintController {
    private final ConnectivityManager connManager;
    private final long timeoutMs;

    public NetworkRequestConstraintController(ConnectivityManager connectivityManager0, long v) {
        Intrinsics.checkNotNullParameter(connectivityManager0, "connManager");
        super();
        this.connManager = connectivityManager0;
        this.timeoutMs = v;
    }

    public NetworkRequestConstraintController(ConnectivityManager connectivityManager0, long v, int v1, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v1 & 2) != 0) {
            v = 1000L;
        }
        this(connectivityManager0, v);
    }

    @Override  // androidx.work.impl.constraints.controllers.ConstraintController
    public boolean hasConstraint(WorkSpec workSpec0) {
        Intrinsics.checkNotNullParameter(workSpec0, "workSpec");
        return workSpec0.constraints.getRequiredNetworkRequest() != null;
    }

    @Override  // androidx.work.impl.constraints.controllers.ConstraintController
    public boolean isCurrentlyConstrained(WorkSpec workSpec0) {
        Intrinsics.checkNotNullParameter(workSpec0, "workSpec");
        if(this.hasConstraint(workSpec0)) {
            throw new IllegalStateException("isCurrentlyConstrained() must never be called onNetworkRequestConstraintController. isCurrentlyConstrained() is called only on older platforms where NetworkRequest isn\'t supported");
        }
        return false;
    }

    @Override  // androidx.work.impl.constraints.controllers.ConstraintController
    public Flow track(Constraints constraints0) {
        Intrinsics.checkNotNullParameter(constraints0, "constraints");
        return FlowKt.callbackFlow(new Function2(this, null) {
            final Constraints $constraints;
            private Object L$0;
            int label;

            {
                this.$constraints = constraints0;
                NetworkRequestConstraintController.this = networkRequestConstraintController0;
                super(2, continuation0);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object object0, Continuation continuation0) {
                androidx.work.impl.constraints.NetworkRequestConstraintController.track.1 networkRequestConstraintController$track$10 = new androidx.work.impl.constraints.NetworkRequestConstraintController.track.1(this.$constraints, NetworkRequestConstraintController.this, continuation0);
                networkRequestConstraintController$track$10.L$0 = object0;
                return networkRequestConstraintController$track$10;
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(((ProducerScope)object0), ((Continuation)object1));
            }

            public final Object invoke(ProducerScope producerScope0, Continuation continuation0) {
                return ((androidx.work.impl.constraints.NetworkRequestConstraintController.track.1)this.create(producerScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object object0) {
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(this.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        ProducerScope producerScope0 = (ProducerScope)this.L$0;
                        NetworkRequest networkRequest0 = this.$constraints.getRequiredNetworkRequest();
                        if(networkRequest0 == null) {
                            DefaultImpls.close$default(producerScope0.getChannel(), null, 1, null);
                            return Unit.INSTANCE;
                        }
                        Function1 function10 = new Function1(producerScope0) {
                            final ProducerScope $$this$callbackFlow;
                            final Job $timeoutJob;

                            {
                                this.$timeoutJob = job0;
                                this.$$this$callbackFlow = producerScope0;
                                super(1);
                            }

                            @Override  // kotlin.jvm.functions.Function1
                            public Object invoke(Object object0) {
                                this.invoke(((ConstraintsState)object0));
                                return Unit.INSTANCE;
                            }

                            public final void invoke(ConstraintsState constraintsState0) {
                                Intrinsics.checkNotNullParameter(constraintsState0, "it");
                                kotlinx.coroutines.Job.DefaultImpls.cancel$default(this.$timeoutJob, null, 1, null);
                                this.$$this$callbackFlow.trySend-JP2dKIU(constraintsState0);
                            }
                        };
                        Function0 function00 = new Function0() {
                            final Function0 $tryUnregister;

                            {
                                this.$tryUnregister = function00;
                                super(0);
                            }

                            @Override  // kotlin.jvm.functions.Function0
                            public Object invoke() {
                                this.invoke();
                                return Unit.INSTANCE;
                            }

                            public final void invoke() {
                                this.$tryUnregister.invoke();
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
                @DebugMetadata(c = "androidx.work.impl.constraints.NetworkRequestConstraintController$track$1$timeoutJob$1", f = "WorkConstraintsTracker.kt", i = {}, l = {0x95}, m = "invokeSuspend", n = {}, s = {})
                final class androidx.work.impl.constraints.NetworkRequestConstraintController.track.1.timeoutJob.1 extends SuspendLambda implements Function2 {
                    final ProducerScope $$this$callbackFlow;
                    int label;

                    androidx.work.impl.constraints.NetworkRequestConstraintController.track.1.timeoutJob.1(NetworkRequestConstraintController networkRequestConstraintController0, ProducerScope producerScope0, Continuation continuation0) {
                        NetworkRequestConstraintController.this = networkRequestConstraintController0;
                        this.$$this$callbackFlow = producerScope0;
                        super(2, continuation0);
                    }

                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation create(Object object0, Continuation continuation0) {
                        return new androidx.work.impl.constraints.NetworkRequestConstraintController.track.1.timeoutJob.1(NetworkRequestConstraintController.this, this.$$this$callbackFlow, continuation0);
                    }

                    @Override  // kotlin.jvm.functions.Function2
                    public Object invoke(Object object0, Object object1) {
                        return this.invoke(((CoroutineScope)object0), ((Continuation)object1));
                    }

                    public final Object invoke(CoroutineScope coroutineScope0, Continuation continuation0) {
                        return ((androidx.work.impl.constraints.NetworkRequestConstraintController.track.1.timeoutJob.1)this.create(coroutineScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object object0) {
                        Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        switch(this.label) {
                            case 0: {
                                ResultKt.throwOnFailure(object0);
                                this.label = 1;
                                if(DelayKt.delay(NetworkRequestConstraintController.this.timeoutMs, this) == object1) {
                                    return object1;
                                }
                                break;
                            }
                            case 1: {
                                ResultKt.throwOnFailure(object0);
                                break;
                            }
                            default: {
                                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                            }
                        }
                        Logger.get().debug("WM-WorkConstraintsTrack", "NetworkRequestConstraintController didn\'t receive neither onCapabilitiesChanged/onLost callback, sending `ConstraintsNotMet` after " + NetworkRequestConstraintController.this.timeoutMs + " ms");
                        ConstraintsNotMet constraintsState$ConstraintsNotMet0 = new ConstraintsNotMet(7);
                        this.$$this$callbackFlow.trySend-JP2dKIU(constraintsState$ConstraintsNotMet0);
                        return Unit.INSTANCE;
                    }
                }

            }
        });
    }
}

