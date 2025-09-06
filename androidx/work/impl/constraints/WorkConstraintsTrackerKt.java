package androidx.work.impl.constraints;

import android.content.Context;
import android.net.ConnectivityManager;
import androidx.work.impl.model.WorkSpec;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

@Metadata(d1 = {"\u0000D\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u001A\u0010\u0010\u0004\u001A\u00020\u00052\u0006\u0010\u0006\u001A\u00020\u0007H\u0007\u001A\"\u0010\b\u001A\u00020\t*\u00020\n2\u0006\u0010\u000B\u001A\u00020\f2\u0006\u0010\r\u001A\u00020\u000E2\u0006\u0010\u000F\u001A\u00020\u0010\"\u000E\u0010\u0000\u001A\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000E\u0010\u0002\u001A\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000*$\b\u0002\u0010\u0011\"\u000E\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00140\u00122\u000E\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00140\u0012¨\u0006\u0015"}, d2 = {"DefaultNetworkRequestTimeoutMs", "", "TAG", "", "NetworkRequestConstraintController", "Landroidx/work/impl/constraints/NetworkRequestConstraintController;", "context", "Landroid/content/Context;", "listen", "Lkotlinx/coroutines/Job;", "Landroidx/work/impl/constraints/WorkConstraintsTracker;", "spec", "Landroidx/work/impl/model/WorkSpec;", "dispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "listener", "Landroidx/work/impl/constraints/OnConstraintsStateChangedListener;", "OnConstraintState", "Lkotlin/Function1;", "Landroidx/work/impl/constraints/ConstraintsState;", "", "work-runtime_release"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class WorkConstraintsTrackerKt {
    private static final long DefaultNetworkRequestTimeoutMs = 1000L;
    private static final String TAG;

    // 去混淆评级： 低(20)
    static {
        Intrinsics.checkNotNullExpressionValue("WM-WorkConstraintsTrack", "tagWithPrefix(\"WorkConstraintsTracker\")");
        WorkConstraintsTrackerKt.TAG = "WM-WorkConstraintsTrack";
    }

    public static final NetworkRequestConstraintController NetworkRequestConstraintController(Context context0) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Object object0 = context0.getSystemService("connectivity");
        Intrinsics.checkNotNull(object0, "null cannot be cast to non-null type android.net.ConnectivityManager");
        return new NetworkRequestConstraintController(((ConnectivityManager)object0), 0L, 2, null);
    }

    public static final String access$getTAG$p() [...] // 潜在的解密器

    public static final Job listen(WorkConstraintsTracker workConstraintsTracker0, WorkSpec workSpec0, CoroutineDispatcher coroutineDispatcher0, OnConstraintsStateChangedListener onConstraintsStateChangedListener0) {
        Intrinsics.checkNotNullParameter(workConstraintsTracker0, "<this>");
        Intrinsics.checkNotNullParameter(workSpec0, "spec");
        Intrinsics.checkNotNullParameter(coroutineDispatcher0, "dispatcher");
        Intrinsics.checkNotNullParameter(onConstraintsStateChangedListener0, "listener");
        return BuildersKt.launch$default(CoroutineScopeKt.CoroutineScope(coroutineDispatcher0), null, null, new Function2(workConstraintsTracker0, workSpec0, onConstraintsStateChangedListener0, null) {
            final OnConstraintsStateChangedListener $listener;
            final WorkSpec $spec;
            final WorkConstraintsTracker $this_listen;
            int label;

            {
                this.$this_listen = workConstraintsTracker0;
                this.$spec = workSpec0;
                this.$listener = onConstraintsStateChangedListener0;
                super(2, continuation0);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object object0, Continuation continuation0) {
                return new androidx.work.impl.constraints.WorkConstraintsTrackerKt.listen.1(this.$this_listen, this.$spec, this.$listener, continuation0);
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(((CoroutineScope)object0), ((Continuation)object1));
            }

            public final Object invoke(CoroutineScope coroutineScope0, Continuation continuation0) {
                return ((androidx.work.impl.constraints.WorkConstraintsTrackerKt.listen.1)this.create(coroutineScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object object0) {
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(this.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        Flow flow0 = this.$this_listen.track(this.$spec);
                        androidx.work.impl.constraints.WorkConstraintsTrackerKt.listen.1.1 workConstraintsTrackerKt$listen$1$10 = new FlowCollector() {
                            public final Object emit(ConstraintsState constraintsState0, Continuation continuation0) {
                                this.$spec.onConstraintsStateChanged(this.$spec, constraintsState0);
                                return Unit.INSTANCE;
                            }

                            @Override  // kotlinx.coroutines.flow.FlowCollector
                            public Object emit(Object object0, Continuation continuation0) {
                                return this.emit(((ConstraintsState)object0), continuation0);
                            }
                        };
                        this.label = 1;
                        return flow0.collect(workConstraintsTrackerKt$listen$1$10, this) == object1 ? object1 : Unit.INSTANCE;
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
        }, 3, null);
    }
}

