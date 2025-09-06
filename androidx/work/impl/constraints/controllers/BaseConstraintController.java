package androidx.work.impl.constraints.controllers;

import androidx.work.Constraints;
import androidx.work.impl.constraints.ConstraintListener;
import androidx.work.impl.constraints.ConstraintsState.ConstraintsMet;
import androidx.work.impl.constraints.ConstraintsState.ConstraintsNotMet;
import androidx.work.impl.constraints.trackers.ConstraintTracker;
import androidx.work.impl.model.WorkSpec;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000B\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b&\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0013\u0012\f\u0010\u0003\u001A\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\u0002\u0010\u0005J\u0015\u0010\f\u001A\u00020\r2\u0006\u0010\u000E\u001A\u00028\u0000H\u0014¢\u0006\u0002\u0010\u000FJ\u0010\u0010\u0010\u001A\u00020\r2\u0006\u0010\u0011\u001A\u00020\u0012H\u0016J\u0016\u0010\u0013\u001A\b\u0012\u0004\u0012\u00020\u00150\u00142\u0006\u0010\u0016\u001A\u00020\u0017H\u0016R\u0018\u0010\u0006\u001A\u00020\u0007X¤\u0004¢\u0006\f\u0012\u0004\b\b\u0010\t\u001A\u0004\b\n\u0010\u000BR\u0014\u0010\u0003\u001A\b\u0012\u0004\u0012\u00028\u00000\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Landroidx/work/impl/constraints/controllers/BaseConstraintController;", "T", "Landroidx/work/impl/constraints/controllers/ConstraintController;", "tracker", "Landroidx/work/impl/constraints/trackers/ConstraintTracker;", "(Landroidx/work/impl/constraints/trackers/ConstraintTracker;)V", "reason", "", "getReason$annotations", "()V", "getReason", "()I", "isConstrained", "", "value", "(Ljava/lang/Object;)Z", "isCurrentlyConstrained", "workSpec", "Landroidx/work/impl/model/WorkSpec;", "track", "Lkotlinx/coroutines/flow/Flow;", "Landroidx/work/impl/constraints/ConstraintsState;", "constraints", "Landroidx/work/Constraints;", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public abstract class BaseConstraintController implements ConstraintController {
    private final ConstraintTracker tracker;

    public BaseConstraintController(ConstraintTracker constraintTracker0) {
        Intrinsics.checkNotNullParameter(constraintTracker0, "tracker");
        super();
        this.tracker = constraintTracker0;
    }

    protected abstract int getReason();

    protected static void getReason$annotations() {
    }

    protected boolean isConstrained(Object object0) {
        return false;
    }

    @Override  // androidx.work.impl.constraints.controllers.ConstraintController
    public boolean isCurrentlyConstrained(WorkSpec workSpec0) {
        Intrinsics.checkNotNullParameter(workSpec0, "workSpec");
        return this.hasConstraint(workSpec0) && this.isConstrained(this.tracker.readSystemState());
    }

    @Override  // androidx.work.impl.constraints.controllers.ConstraintController
    public Flow track(Constraints constraints0) {
        Intrinsics.checkNotNullParameter(constraints0, "constraints");
        return FlowKt.callbackFlow(new Function2(null) {
            private Object L$0;
            int label;

            {
                BaseConstraintController.this = baseConstraintController0;
                super(2, continuation0);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object object0, Continuation continuation0) {
                androidx.work.impl.constraints.controllers.BaseConstraintController.track.1 baseConstraintController$track$10 = new androidx.work.impl.constraints.controllers.BaseConstraintController.track.1(BaseConstraintController.this, continuation0);
                baseConstraintController$track$10.L$0 = object0;
                return baseConstraintController$track$10;
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(((ProducerScope)object0), ((Continuation)object1));
            }

            public final Object invoke(ProducerScope producerScope0, Continuation continuation0) {
                return ((androidx.work.impl.constraints.controllers.BaseConstraintController.track.1)this.create(producerScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object object0) {
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(this.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        ProducerScope producerScope0 = (ProducerScope)this.L$0;
                        androidx.work.impl.constraints.controllers.BaseConstraintController.track.1.listener.1 baseConstraintController$track$1$listener$10 = new ConstraintListener() {
                            @Override  // androidx.work.impl.constraints.ConstraintListener
                            public void onConstraintChanged(Object object0) {
                                ConstraintsNotMet constraintsState$ConstraintsNotMet0 = producerScope0.isConstrained(object0) ? new ConstraintsNotMet(producerScope0.getReason()) : ConstraintsMet.INSTANCE;
                                this.$$this$callbackFlow.getChannel().trySend-JP2dKIU(constraintsState$ConstraintsNotMet0);
                            }
                        };
                        BaseConstraintController.this.tracker.addListener(baseConstraintController$track$1$listener$10);
                        androidx.work.impl.constraints.controllers.BaseConstraintController.track.1.1 baseConstraintController$track$1$10 = new Function0(baseConstraintController$track$1$listener$10) {
                            final androidx.work.impl.constraints.controllers.BaseConstraintController.track.1.listener.1 $listener;

                            {
                                BaseConstraintController.this = baseConstraintController0;
                                this.$listener = baseConstraintController$track$1$listener$10;
                                super(0);
                            }

                            @Override  // kotlin.jvm.functions.Function0
                            public Object invoke() {
                                this.invoke();
                                return Unit.INSTANCE;
                            }

                            public final void invoke() {
                                BaseConstraintController.this.tracker.removeListener(this.$listener);
                            }
                        };
                        this.label = 1;
                        return ProduceKt.awaitClose(producerScope0, baseConstraintController$track$1$10, this) == object1 ? object1 : Unit.INSTANCE;
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
}

