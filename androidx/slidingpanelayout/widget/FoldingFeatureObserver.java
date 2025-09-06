package androidx.slidingpanelayout.widget;

import android.app.Activity;
import androidx.window.layout.DisplayFeature;
import androidx.window.layout.FoldingFeature;
import androidx.window.layout.WindowInfoTracker;
import androidx.window.layout.WindowLayoutInfo;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.ExecutorsKt;
import kotlinx.coroutines.Job.DefaultImpls;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001:\u0001\u0015B\u0015\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0012\u0010\u000B\u001A\u0004\u0018\u00010\f2\u0006\u0010\r\u001A\u00020\u000EH\u0002J\u000E\u0010\u000F\u001A\u00020\u00102\u0006\u0010\u0011\u001A\u00020\u0012J\u000E\u0010\u0013\u001A\u00020\u00102\u0006\u0010\t\u001A\u00020\nJ\u0006\u0010\u0014\u001A\u00020\u0010R\u000E\u0010\u0004\u001A\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001A\u0004\u0018\u00010\bX\u0082\u000E¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001A\u0004\u0018\u00010\nX\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\u0002\u001A\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Landroidx/slidingpanelayout/widget/FoldingFeatureObserver;", "", "windowInfoTracker", "Landroidx/window/layout/WindowInfoTracker;", "executor", "Ljava/util/concurrent/Executor;", "(Landroidx/window/layout/WindowInfoTracker;Ljava/util/concurrent/Executor;)V", "job", "Lkotlinx/coroutines/Job;", "onFoldingFeatureChangeListener", "Landroidx/slidingpanelayout/widget/FoldingFeatureObserver$OnFoldingFeatureChangeListener;", "getFoldingFeature", "Landroidx/window/layout/FoldingFeature;", "windowLayoutInfo", "Landroidx/window/layout/WindowLayoutInfo;", "registerLayoutStateChangeCallback", "", "activity", "Landroid/app/Activity;", "setOnFoldingFeatureChangeListener", "unregisterLayoutStateChangeCallback", "OnFoldingFeatureChangeListener", "slidingpanelayout_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public final class FoldingFeatureObserver {
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b`\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001A\u00020\u00032\u0006\u0010\u0004\u001A\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Landroidx/slidingpanelayout/widget/FoldingFeatureObserver$OnFoldingFeatureChangeListener;", "", "onFoldingFeatureChange", "", "foldingFeature", "Landroidx/window/layout/FoldingFeature;", "slidingpanelayout_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
    public interface OnFoldingFeatureChangeListener {
        void onFoldingFeatureChange(FoldingFeature arg1);
    }

    private final Executor executor;
    private Job job;
    private OnFoldingFeatureChangeListener onFoldingFeatureChangeListener;
    private final WindowInfoTracker windowInfoTracker;

    public FoldingFeatureObserver(WindowInfoTracker windowInfoTracker0, Executor executor0) {
        Intrinsics.checkNotNullParameter(windowInfoTracker0, "windowInfoTracker");
        Intrinsics.checkNotNullParameter(executor0, "executor");
        super();
        this.windowInfoTracker = windowInfoTracker0;
        this.executor = executor0;
    }

    private final FoldingFeature getFoldingFeature(WindowLayoutInfo windowLayoutInfo0) {
        for(Object object0: windowLayoutInfo0.getDisplayFeatures()) {
            if(((DisplayFeature)object0) instanceof FoldingFeature) {
                return object0 instanceof FoldingFeature ? ((FoldingFeature)object0) : null;
            }
            if(false) {
                break;
            }
        }
        return null;
    }

    public final void registerLayoutStateChangeCallback(Activity activity0) {
        Intrinsics.checkNotNullParameter(activity0, "activity");
        Job job0 = this.job;
        if(job0 != null) {
            DefaultImpls.cancel$default(job0, null, 1, null);
        }
        this.job = BuildersKt.launch$default(CoroutineScopeKt.CoroutineScope(ExecutorsKt.from(this.executor)), null, null, new Function2(activity0, null) {
            final Activity $activity;
            int label;

            {
                FoldingFeatureObserver.this = foldingFeatureObserver0;
                this.$activity = activity0;
                super(2, continuation0);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object object0, Continuation continuation0) {
                return new androidx.slidingpanelayout.widget.FoldingFeatureObserver.registerLayoutStateChangeCallback.1(FoldingFeatureObserver.this, this.$activity, continuation0);
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(((CoroutineScope)object0), ((Continuation)object1));
            }

            public final Object invoke(CoroutineScope coroutineScope0, Continuation continuation0) {
                return ((androidx.slidingpanelayout.widget.FoldingFeatureObserver.registerLayoutStateChangeCallback.1)this.create(coroutineScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object object0) {
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(this.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        Flow flow0 = FlowKt.distinctUntilChanged(new Flow() {
                            @Override  // kotlinx.coroutines.flow.Flow
                            public Object collect(FlowCollector flowCollector0, Continuation continuation0) {
                                androidx.slidingpanelayout.widget.FoldingFeatureObserver.registerLayoutStateChangeCallback.1.invokeSuspend..inlined.mapNotNull.1.2 foldingFeatureObserver$registerLayoutStateChangeCallback$1$invokeSuspend$$inlined$mapNotNull$1$20 = new FlowCollector() {
                                    @Override  // kotlinx.coroutines.flow.FlowCollector
                                    public Object emit(Object object0, Continuation continuation0) {
                                        androidx.slidingpanelayout.widget.FoldingFeatureObserver.registerLayoutStateChangeCallback.1.invokeSuspend..inlined.mapNotNull.1.2.1 foldingFeatureObserver$registerLayoutStateChangeCallback$1$invokeSuspend$$inlined$mapNotNull$1$2$10;
                                        if(continuation0 instanceof androidx.slidingpanelayout.widget.FoldingFeatureObserver.registerLayoutStateChangeCallback.1.invokeSuspend..inlined.mapNotNull.1.2.1) {
                                            foldingFeatureObserver$registerLayoutStateChangeCallback$1$invokeSuspend$$inlined$mapNotNull$1$2$10 = (androidx.slidingpanelayout.widget.FoldingFeatureObserver.registerLayoutStateChangeCallback.1.invokeSuspend..inlined.mapNotNull.1.2.1)continuation0;
                                            if((foldingFeatureObserver$registerLayoutStateChangeCallback$1$invokeSuspend$$inlined$mapNotNull$1$2$10.label & 0x80000000) == 0) {
                                                foldingFeatureObserver$registerLayoutStateChangeCallback$1$invokeSuspend$$inlined$mapNotNull$1$2$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
                                                    Object L$0;
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
                                                foldingFeatureObserver$registerLayoutStateChangeCallback$1$invokeSuspend$$inlined$mapNotNull$1$2$10.label ^= 0x80000000;
                                            }
                                        }
                                        else {
                                            foldingFeatureObserver$registerLayoutStateChangeCallback$1$invokeSuspend$$inlined$mapNotNull$1$2$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
                                                Object L$0;
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
                                        Object object1 = foldingFeatureObserver$registerLayoutStateChangeCallback$1$invokeSuspend$$inlined$mapNotNull$1$2$10.result;
                                        Object object2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                        switch(foldingFeatureObserver$registerLayoutStateChangeCallback$1$invokeSuspend$$inlined$mapNotNull$1$2$10.label) {
                                            case 0: {
                                                ResultKt.throwOnFailure(object1);
                                                FlowCollector flowCollector0 = FoldingFeatureObserver.this;
                                                FoldingFeature foldingFeature0 = FoldingFeatureObserver.this.getFoldingFeature(((WindowLayoutInfo)object0));
                                                if(foldingFeature0 != null) {
                                                    foldingFeatureObserver$registerLayoutStateChangeCallback$1$invokeSuspend$$inlined$mapNotNull$1$2$10.label = 1;
                                                    if(flowCollector0.emit(foldingFeature0, foldingFeatureObserver$registerLayoutStateChangeCallback$1$invokeSuspend$$inlined$mapNotNull$1$2$10) == object2) {
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
                                Object object0 = FoldingFeatureObserver.this.collect(foldingFeatureObserver$registerLayoutStateChangeCallback$1$invokeSuspend$$inlined$mapNotNull$1$20, continuation0);
                                return object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? object0 : Unit.INSTANCE;
                            }
                        });
                        FlowCollector flowCollector0 = new FlowCollector() {
                            @Override  // kotlinx.coroutines.flow.FlowCollector
                            public Object emit(Object object0, Continuation continuation0) {
                                OnFoldingFeatureChangeListener foldingFeatureObserver$OnFoldingFeatureChangeListener0 = FoldingFeatureObserver.this.onFoldingFeatureChangeListener;
                                if(foldingFeatureObserver$OnFoldingFeatureChangeListener0 == null) {
                                    return null == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? null : Unit.INSTANCE;
                                }
                                foldingFeatureObserver$OnFoldingFeatureChangeListener0.onFoldingFeatureChange(((FoldingFeature)object0));
                                return Unit.INSTANCE == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? Unit.INSTANCE : Unit.INSTANCE;
                            }
                        };
                        this.label = 1;
                        return flow0.collect(flowCollector0, this) == object1 ? object1 : Unit.INSTANCE;
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

    public final void setOnFoldingFeatureChangeListener(OnFoldingFeatureChangeListener foldingFeatureObserver$OnFoldingFeatureChangeListener0) {
        Intrinsics.checkNotNullParameter(foldingFeatureObserver$OnFoldingFeatureChangeListener0, "onFoldingFeatureChangeListener");
        this.onFoldingFeatureChangeListener = foldingFeatureObserver$OnFoldingFeatureChangeListener0;
    }

    public final void unregisterLayoutStateChangeCallback() {
        Job job0 = this.job;
        if(job0 == null) {
            return;
        }
        DefaultImpls.cancel$default(job0, null, 1, null);
    }
}

