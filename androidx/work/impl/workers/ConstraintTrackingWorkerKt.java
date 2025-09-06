package androidx.work.impl.workers;

import androidx.work.Logger;
import androidx.work.impl.constraints.ConstraintsState.ConstraintsNotMet;
import androidx.work.impl.constraints.ConstraintsState;
import androidx.work.impl.constraints.WorkConstraintsTracker;
import androidx.work.impl.model.WorkSpec;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;

@Metadata(d1 = {"\u0000\u001C\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001A\u001A\u0010\u0003\u001A\u00020\u0004*\u00020\u00052\u0006\u0010\u0006\u001A\u00020\u0007H\u0082@¢\u0006\u0002\u0010\b\"\u000E\u0010\u0000\u001A\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000E\u0010\u0002\u001A\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"ARGUMENT_CLASS_NAME", "", "TAG", "awaitConstraintsNotMet", "", "Landroidx/work/impl/constraints/WorkConstraintsTracker;", "workSpec", "Landroidx/work/impl/model/WorkSpec;", "(Landroidx/work/impl/constraints/WorkConstraintsTracker;Landroidx/work/impl/model/WorkSpec;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "work-runtime_release"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class ConstraintTrackingWorkerKt {
    public static final String ARGUMENT_CLASS_NAME = "androidx.work.impl.workers.ConstraintTrackingWorker.ARGUMENT_CLASS_NAME";
    private static final String TAG;

    // 去混淆评级： 低(20)
    static {
        Intrinsics.checkNotNullExpressionValue("WM-ConstraintTrkngWrkr", "tagWithPrefix(\"ConstraintTrkngWrkr\")");
        ConstraintTrackingWorkerKt.TAG = "WM-ConstraintTrkngWrkr";
    }

    // 去混淆评级： 低(20)
    public static final String access$getTAG$p() [...] // 潜在的解密器

    private static final Object awaitConstraintsNotMet(WorkConstraintsTracker workConstraintsTracker0, WorkSpec workSpec0, Continuation continuation0) {
        androidx.work.impl.workers.ConstraintTrackingWorkerKt.awaitConstraintsNotMet.1 constraintTrackingWorkerKt$awaitConstraintsNotMet$10;
        if(continuation0 instanceof androidx.work.impl.workers.ConstraintTrackingWorkerKt.awaitConstraintsNotMet.1) {
            constraintTrackingWorkerKt$awaitConstraintsNotMet$10 = (androidx.work.impl.workers.ConstraintTrackingWorkerKt.awaitConstraintsNotMet.1)continuation0;
            if((constraintTrackingWorkerKt$awaitConstraintsNotMet$10.label & 0x80000000) == 0) {
                constraintTrackingWorkerKt$awaitConstraintsNotMet$10 = new ContinuationImpl(continuation0) {
                    int label;
                    Object result;

                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object object0) {
                        this.result = object0;
                        this.label |= 0x80000000;
                        return ConstraintTrackingWorkerKt.awaitConstraintsNotMet(null, null, this);
                    }
                };
            }
            else {
                constraintTrackingWorkerKt$awaitConstraintsNotMet$10.label ^= 0x80000000;
            }
        }
        else {
            constraintTrackingWorkerKt$awaitConstraintsNotMet$10 = new ContinuationImpl(continuation0) {
                int label;
                Object result;

                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object object0) {
                    this.result = object0;
                    this.label |= 0x80000000;
                    return ConstraintTrackingWorkerKt.awaitConstraintsNotMet(null, null, this);
                }
            };
        }
        Object object0 = constraintTrackingWorkerKt$awaitConstraintsNotMet$10.result;
        Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch(constraintTrackingWorkerKt$awaitConstraintsNotMet$10.label) {
            case 0: {
                ResultKt.throwOnFailure(object0);
                Flow flow0 = new Flow() {
                    @Override  // kotlinx.coroutines.flow.Flow
                    public Object collect(FlowCollector flowCollector0, Continuation continuation0) {
                        FlowCollector flowCollector1 = new FlowCollector() {
                            @Override  // kotlinx.coroutines.flow.FlowCollector
                            public final Object emit(Object object0, Continuation continuation0) {
                                androidx.work.impl.workers.ConstraintTrackingWorkerKt.awaitConstraintsNotMet..inlined.filterIsInstance.1.2.1 constraintTrackingWorkerKt$awaitConstraintsNotMet$$inlined$filterIsInstance$1$2$10;
                                if(continuation0 instanceof androidx.work.impl.workers.ConstraintTrackingWorkerKt.awaitConstraintsNotMet..inlined.filterIsInstance.1.2.1) {
                                    constraintTrackingWorkerKt$awaitConstraintsNotMet$$inlined$filterIsInstance$1$2$10 = (androidx.work.impl.workers.ConstraintTrackingWorkerKt.awaitConstraintsNotMet..inlined.filterIsInstance.1.2.1)continuation0;
                                    if((constraintTrackingWorkerKt$awaitConstraintsNotMet$$inlined$filterIsInstance$1$2$10.label & 0x80000000) == 0) {
                                        constraintTrackingWorkerKt$awaitConstraintsNotMet$$inlined$filterIsInstance$1$2$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
                                            Object L$0;
                                            Object L$1;
                                            int label;
                                            Object result;

                                            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                            public final Object invokeSuspend(Object object0) {
                                                this.result = object0;
                                                this.label |= 0x80000000;
                                                return continuation0.emit(null, this);
                                            }
                                        };
                                    }
                                    else {
                                        constraintTrackingWorkerKt$awaitConstraintsNotMet$$inlined$filterIsInstance$1$2$10.label ^= 0x80000000;
                                    }
                                }
                                else {
                                    constraintTrackingWorkerKt$awaitConstraintsNotMet$$inlined$filterIsInstance$1$2$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
                                        Object L$0;
                                        Object L$1;
                                        int label;
                                        Object result;

                                        @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                        public final Object invokeSuspend(Object object0) {
                                            this.result = object0;
                                            this.label |= 0x80000000;
                                            return continuation0.emit(null, this);
                                        }
                                    };
                                }
                                Object object1 = constraintTrackingWorkerKt$awaitConstraintsNotMet$$inlined$filterIsInstance$1$2$10.result;
                                Object object2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                switch(constraintTrackingWorkerKt$awaitConstraintsNotMet$$inlined$filterIsInstance$1$2$10.label) {
                                    case 0: {
                                        ResultKt.throwOnFailure(object1);
                                        FlowCollector flowCollector0 = this.$this_unsafeFlow;
                                        if(object0 instanceof ConstraintsNotMet) {
                                            constraintTrackingWorkerKt$awaitConstraintsNotMet$$inlined$filterIsInstance$1$2$10.label = 1;
                                            if(flowCollector0.emit(object0, constraintTrackingWorkerKt$awaitConstraintsNotMet$$inlined$filterIsInstance$1$2$10) == object2) {
                                                return object2;
                                            }
                                        }
                                        return Unit.INSTANCE;
                                    }
                                    case 1: {
                                        ResultKt.throwOnFailure(object1);
                                        return Unit.INSTANCE;
                                    }
                                    default: {
                                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                                    }
                                }
                            }
                        };
                        Object object0 = FlowKt.onEach(workConstraintsTracker0.track(workSpec0), new androidx.work.impl.workers.ConstraintTrackingWorkerKt.awaitConstraintsNotMet.2(workSpec0, null)).collect(flowCollector1, continuation0);
                        return object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? object0 : Unit.INSTANCE;
                    }
                };
                constraintTrackingWorkerKt$awaitConstraintsNotMet$10.label = 1;
                object0 = FlowKt.first(flow0, constraintTrackingWorkerKt$awaitConstraintsNotMet$10);
                return object0 == object1 ? object1 : Boxing.boxInt(((ConstraintsNotMet)object0).getReason());
            }
            case 1: {
                ResultKt.throwOnFailure(object0);
                return Boxing.boxInt(((ConstraintsNotMet)object0).getReason());
            }
            default: {
                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u0003H\u008A@"}, d2 = {"<anonymous>", "", "it", "Landroidx/work/impl/constraints/ConstraintsState;"}, k = 3, mv = {1, 8, 0}, xi = 0x30)
        @DebugMetadata(c = "androidx.work.impl.workers.ConstraintTrackingWorkerKt$awaitConstraintsNotMet$2", f = "ConstraintTrackingWorker.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        final class androidx.work.impl.workers.ConstraintTrackingWorkerKt.awaitConstraintsNotMet.2 extends SuspendLambda implements Function2 {
            final WorkSpec $workSpec;
            int label;

            androidx.work.impl.workers.ConstraintTrackingWorkerKt.awaitConstraintsNotMet.2(WorkSpec workSpec0, Continuation continuation0) {
                this.$workSpec = workSpec0;
                super(2, continuation0);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object object0, Continuation continuation0) {
                return new androidx.work.impl.workers.ConstraintTrackingWorkerKt.awaitConstraintsNotMet.2(this.$workSpec, continuation0);
            }

            public final Object invoke(ConstraintsState constraintsState0, Continuation continuation0) {
                return ((androidx.work.impl.workers.ConstraintTrackingWorkerKt.awaitConstraintsNotMet.2)this.create(constraintsState0, continuation0)).invokeSuspend(Unit.INSTANCE);
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(((ConstraintsState)object0), ((Continuation)object1));
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object object0) {
                if(this.label != 0) {
                    throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                }
                ResultKt.throwOnFailure(object0);
                Logger.get().debug("WM-ConstraintTrkngWrkr", "Constraints changed for " + this.$workSpec);
                return Unit.INSTANCE;
            }
        }

    }
}

