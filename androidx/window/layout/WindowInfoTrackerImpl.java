package androidx.window.layout;

import android.app.Activity;
import androidx.core.util.Consumer;
import androidx.profileinstaller.ProfileInstallReceiver..ExternalSyntheticLambda0;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelIterator;
import kotlinx.coroutines.channels.ChannelKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000 \f2\u00020\u0001:\u0001\fB\u0015\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001A\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001A\u00020\u000BH\u0016R\u000E\u0010\u0004\u001A\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0002\u001A\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Landroidx/window/layout/WindowInfoTrackerImpl;", "Landroidx/window/layout/WindowInfoTracker;", "windowMetricsCalculator", "Landroidx/window/layout/WindowMetricsCalculator;", "windowBackend", "Landroidx/window/layout/WindowBackend;", "(Landroidx/window/layout/WindowMetricsCalculator;Landroidx/window/layout/WindowBackend;)V", "windowLayoutInfo", "Lkotlinx/coroutines/flow/Flow;", "Landroidx/window/layout/WindowLayoutInfo;", "activity", "Landroid/app/Activity;", "Companion", "window_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public final class WindowInfoTrackerImpl implements WindowInfoTracker {
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000E\u0010\u0003\u001A\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Landroidx/window/layout/WindowInfoTrackerImpl$Companion;", "", "()V", "BUFFER_CAPACITY", "", "window_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }
    }

    private static final int BUFFER_CAPACITY = 10;
    public static final Companion Companion;
    private final WindowBackend windowBackend;
    private final WindowMetricsCalculator windowMetricsCalculator;

    static {
        WindowInfoTrackerImpl.Companion = new Companion(null);
    }

    public WindowInfoTrackerImpl(WindowMetricsCalculator windowMetricsCalculator0, WindowBackend windowBackend0) {
        Intrinsics.checkNotNullParameter(windowMetricsCalculator0, "windowMetricsCalculator");
        Intrinsics.checkNotNullParameter(windowBackend0, "windowBackend");
        super();
        this.windowMetricsCalculator = windowMetricsCalculator0;
        this.windowBackend = windowBackend0;
    }

    @Override  // androidx.window.layout.WindowInfoTracker
    public Flow windowLayoutInfo(Activity activity0) {
        Intrinsics.checkNotNullParameter(activity0, "activity");
        return FlowKt.flow(new Function2(activity0, null) {
            final Activity $activity;
            private Object L$0;
            Object L$1;
            Object L$2;
            int label;

            // 检测为 Lambda 实现
            public static void $r8$lambda$hE-bCPgu4I5_fFHbNPK3KEKXF90(Channel channel0, WindowLayoutInfo windowLayoutInfo0) [...]

            {
                WindowInfoTrackerImpl.this = windowInfoTrackerImpl0;
                this.$activity = activity0;
                super(2, continuation0);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object object0, Continuation continuation0) {
                androidx.window.layout.WindowInfoTrackerImpl.windowLayoutInfo.1 windowInfoTrackerImpl$windowLayoutInfo$10 = new androidx.window.layout.WindowInfoTrackerImpl.windowLayoutInfo.1(WindowInfoTrackerImpl.this, this.$activity, continuation0);
                windowInfoTrackerImpl$windowLayoutInfo$10.L$0 = object0;
                return windowInfoTrackerImpl$windowLayoutInfo$10;
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(((FlowCollector)object0), ((Continuation)object1));
            }

            public final Object invoke(FlowCollector flowCollector0, Continuation continuation0) {
                return ((androidx.window.layout.WindowInfoTrackerImpl.windowLayoutInfo.1)this.create(flowCollector0, continuation0)).invokeSuspend(Unit.INSTANCE);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object object0) {
                FlowCollector flowCollector1;
                ChannelIterator channelIterator0;
                Consumer consumer0;
                FlowCollector flowCollector0;
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(this.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        flowCollector0 = (FlowCollector)this.L$0;
                        Channel channel0 = ChannelKt.Channel$default(10, BufferOverflow.DROP_OLDEST, null, 4, null);
                        consumer0 = (WindowLayoutInfo windowLayoutInfo0) -> androidx.window.layout.WindowInfoTrackerImpl.windowLayoutInfo.1.invokeSuspend$lambda-0(channel0, windowLayoutInfo0);
                        ProfileInstallReceiver..ExternalSyntheticLambda0 profileInstallReceiver$$ExternalSyntheticLambda00 = new ProfileInstallReceiver..ExternalSyntheticLambda0();
                        WindowInfoTrackerImpl.this.windowBackend.registerLayoutChangeCallback(this.$activity, profileInstallReceiver$$ExternalSyntheticLambda00, consumer0);
                        try {
                            channelIterator0 = channel0.iterator();
                            goto label_21;
                        }
                        catch(Throwable throwable0) {
                            break;
                        }
                    }
                    case 1: {
                        channelIterator0 = (ChannelIterator)this.L$2;
                        consumer0 = (Consumer)this.L$1;
                        flowCollector1 = (FlowCollector)this.L$0;
                        try {
                            ResultKt.throwOnFailure(object0);
                            goto label_29;
                        }
                        catch(Throwable throwable0) {
                            break;
                        }
                    }
                    case 2: {
                        channelIterator0 = (ChannelIterator)this.L$2;
                        consumer0 = (Consumer)this.L$1;
                        flowCollector1 = (FlowCollector)this.L$0;
                        try {
                            ResultKt.throwOnFailure(object0);
                            while(true) {
                                flowCollector0 = flowCollector1;
                            label_21:
                                this.L$0 = flowCollector0;
                                this.L$1 = consumer0;
                                this.L$2 = channelIterator0;
                                this.label = 1;
                                Object object2 = channelIterator0.hasNext(this);
                                if(object2 != object1) {
                                    flowCollector1 = flowCollector0;
                                    object0 = object2;
                                label_29:
                                    if(!((Boolean)object0).booleanValue()) {
                                        goto label_40;
                                    }
                                    WindowLayoutInfo windowLayoutInfo0 = (WindowLayoutInfo)channelIterator0.next();
                                    this.L$0 = flowCollector1;
                                    this.L$1 = consumer0;
                                    this.L$2 = channelIterator0;
                                    this.label = 2;
                                    if(flowCollector1.emit(windowLayoutInfo0, this) != object1) {
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
                WindowInfoTrackerImpl.this.windowBackend.unregisterLayoutChangeCallback(consumer0);
                throw throwable0;
            label_40:
                WindowInfoTrackerImpl.this.windowBackend.unregisterLayoutChangeCallback(consumer0);
                return Unit.INSTANCE;
            }

            private static final void invokeSuspend$lambda-0(Channel channel0, WindowLayoutInfo windowLayoutInfo0) {
                Intrinsics.checkNotNullExpressionValue(windowLayoutInfo0, "info");
                channel0.trySend-JP2dKIU(windowLayoutInfo0);
            }
        });
    }
}

