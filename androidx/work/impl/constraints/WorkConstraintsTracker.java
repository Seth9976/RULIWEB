package androidx.work.impl.constraints;

import android.os.Build.VERSION;
import androidx.work.Logger;
import androidx.work.impl.constraints.controllers.BatteryChargingController;
import androidx.work.impl.constraints.controllers.BatteryNotLowController;
import androidx.work.impl.constraints.controllers.ConstraintController;
import androidx.work.impl.constraints.controllers.NetworkConnectedController;
import androidx.work.impl.constraints.controllers.NetworkMeteredController;
import androidx.work.impl.constraints.controllers.NetworkNotRoamingController;
import androidx.work.impl.constraints.controllers.NetworkUnmeteredController;
import androidx.work.impl.constraints.controllers.StorageNotLowController;
import androidx.work.impl.constraints.trackers.Trackers;
import androidx.work.impl.model.WorkSpec;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.internal.CombineKt;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000F\b\u0016\u0012\u0006\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0013\u0012\f\u0010\u0005\u001A\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\u0010\bJ\u000E\u0010\t\u001A\u00020\n2\u0006\u0010\u000B\u001A\u00020\fJ\u0014\u0010\r\u001A\b\u0012\u0004\u0012\u00020\u000F0\u000E2\u0006\u0010\u0010\u001A\u00020\fR\u0014\u0010\u0005\u001A\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Landroidx/work/impl/constraints/WorkConstraintsTracker;", "", "trackers", "Landroidx/work/impl/constraints/trackers/Trackers;", "(Landroidx/work/impl/constraints/trackers/Trackers;)V", "controllers", "", "Landroidx/work/impl/constraints/controllers/ConstraintController;", "(Ljava/util/List;)V", "areAllConstraintsMet", "", "workSpec", "Landroidx/work/impl/model/WorkSpec;", "track", "Lkotlinx/coroutines/flow/Flow;", "Landroidx/work/impl/constraints/ConstraintsState;", "spec", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class WorkConstraintsTracker {
    private final List controllers;

    public WorkConstraintsTracker(Trackers trackers0) {
        Intrinsics.checkNotNullParameter(trackers0, "trackers");
        this(CollectionsKt.listOfNotNull(new ConstraintController[]{new BatteryChargingController(trackers0.getBatteryChargingTracker()), new BatteryNotLowController(trackers0.getBatteryNotLowTracker()), new StorageNotLowController(trackers0.getStorageNotLowTracker()), new NetworkConnectedController(trackers0.getNetworkStateTracker()), new NetworkUnmeteredController(trackers0.getNetworkStateTracker()), new NetworkNotRoamingController(trackers0.getNetworkStateTracker()), new NetworkMeteredController(trackers0.getNetworkStateTracker()), (Build.VERSION.SDK_INT < 28 ? null : WorkConstraintsTrackerKt.NetworkRequestConstraintController(trackers0.getContext()))}));
    }

    public WorkConstraintsTracker(List list0) {
        Intrinsics.checkNotNullParameter(list0, "controllers");
        super();
        this.controllers = list0;
    }

    public final boolean areAllConstraintsMet(WorkSpec workSpec0) {
        Intrinsics.checkNotNullParameter(workSpec0, "workSpec");
        Collection collection0 = new ArrayList();
        for(Object object0: this.controllers) {
            if(((ConstraintController)object0).isCurrentlyConstrained(workSpec0)) {
                collection0.add(object0);
            }
        }
        if(!((List)collection0).isEmpty()) {
            Logger.get().debug("WM-WorkConstraintsTrack", "Work " + workSpec0.id + " constrained by " + CollectionsKt.joinToString$default(((List)collection0), null, null, null, 0, null, androidx.work.impl.constraints.WorkConstraintsTracker.areAllConstraintsMet.1.INSTANCE, 0x1F, null));
        }
        return ((List)collection0).isEmpty();

        @Metadata(d1 = {"\u0000\u000E\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Landroidx/work/impl/constraints/controllers/ConstraintController;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 0x30)
        final class androidx.work.impl.constraints.WorkConstraintsTracker.areAllConstraintsMet.1 extends Lambda implements Function1 {
            public static final androidx.work.impl.constraints.WorkConstraintsTracker.areAllConstraintsMet.1 INSTANCE;

            static {
                androidx.work.impl.constraints.WorkConstraintsTracker.areAllConstraintsMet.1.INSTANCE = new androidx.work.impl.constraints.WorkConstraintsTracker.areAllConstraintsMet.1();
            }

            androidx.work.impl.constraints.WorkConstraintsTracker.areAllConstraintsMet.1() {
                super(1);
            }

            public final CharSequence invoke(ConstraintController constraintController0) {
                Intrinsics.checkNotNullParameter(constraintController0, "it");
                String s = constraintController0.getClass().getSimpleName();
                Intrinsics.checkNotNullExpressionValue(s, "it.javaClass.simpleName");
                return s;
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((ConstraintController)object0));
            }
        }

    }

    public final Flow track(WorkSpec workSpec0) {
        Intrinsics.checkNotNullParameter(workSpec0, "spec");
        Collection collection0 = new ArrayList();
        for(Object object0: this.controllers) {
            if(((ConstraintController)object0).hasConstraint(workSpec0)) {
                collection0.add(object0);
            }
        }
        ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(((List)collection0), 10));
        for(Object object1: ((List)collection0)) {
            arrayList0.add(((ConstraintController)object1).track(workSpec0.constraints));
        }
        return FlowKt.distinctUntilChanged(new Flow() {
            @Override  // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector flowCollector0, Continuation continuation0) {
                androidx.work.impl.constraints.WorkConstraintsTracker.track..inlined.combine.1.2 workConstraintsTracker$track$$inlined$combine$1$20 = new Function0() {
                    final Flow[] $flowArray;

                    {
                        this.$flowArray = arr_flow;
                        super(0);
                    }

                    @Override  // kotlin.jvm.functions.Function0
                    public Object invoke() {
                        return this.invoke();
                    }

                    public final Object[] invoke() {
                        return new ConstraintsState[this.$flowArray.length];
                    }
                };
                androidx.work.impl.constraints.WorkConstraintsTracker.track..inlined.combine.1.3 workConstraintsTracker$track$$inlined$combine$1$30 = new Function3() {
                    private Object L$0;
                    Object L$1;
                    int label;

                    {
                        super(3, continuation0);
                    }

                    @Override  // kotlin.jvm.functions.Function3
                    public Object invoke(Object object0, Object object1, Object object2) {
                        return this.invoke(((FlowCollector)object0), ((Object[])object1), ((Continuation)object2));
                    }

                    public final Object invoke(FlowCollector flowCollector0, Object[] arr_object, Continuation continuation0) {
                        androidx.work.impl.constraints.WorkConstraintsTracker.track..inlined.combine.1.3 workConstraintsTracker$track$$inlined$combine$1$30 = new androidx.work.impl.constraints.WorkConstraintsTracker.track..inlined.combine.1.3(continuation0);
                        workConstraintsTracker$track$$inlined$combine$1$30.L$0 = flowCollector0;
                        workConstraintsTracker$track$$inlined$combine$1$30.L$1 = arr_object;
                        return workConstraintsTracker$track$$inlined$combine$1$30.invokeSuspend(Unit.INSTANCE);
                    }

                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object object0) {
                        ConstraintsState constraintsState0;
                        Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        switch(this.label) {
                            case 0: {
                                ResultKt.throwOnFailure(object0);
                                FlowCollector flowCollector0 = (FlowCollector)this.L$0;
                                Object[] arr_object = (Object[])this.L$1;
                                for(int v = 0; true; ++v) {
                                    constraintsState0 = null;
                                    if(v >= ((ConstraintsState[])arr_object).length) {
                                        break;
                                    }
                                    ConstraintsState constraintsState1 = ((ConstraintsState[])arr_object)[v];
                                    if(!Intrinsics.areEqual(constraintsState1, ConstraintsMet.INSTANCE)) {
                                        constraintsState0 = constraintsState1;
                                        break;
                                    }
                                }
                                if(constraintsState0 == null) {
                                    constraintsState0 = ConstraintsMet.INSTANCE;
                                }
                                this.label = 1;
                                return flowCollector0.emit(constraintsState0, this) == object1 ? object1 : Unit.INSTANCE;
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
                Object object0 = CombineKt.combineInternal(flowCollector0, this.$flowArray$inlined, workConstraintsTracker$track$$inlined$combine$1$20, workConstraintsTracker$track$$inlined$combine$1$30, continuation0);
                return object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? object0 : Unit.INSTANCE;
            }
        });
    }
}

