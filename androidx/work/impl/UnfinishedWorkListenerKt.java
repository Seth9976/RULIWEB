package androidx.work.impl;

import android.content.Context;
import androidx.work.Configuration;
import androidx.work.Logger;
import androidx.work.impl.background.systemalarm.RescheduleReceiver;
import androidx.work.impl.utils.PackageManagerHelper;
import androidx.work.impl.utils.ProcessUtils;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;

@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001A$\u0010\u0006\u001A\u00020\u0007*\u00020\b2\u0006\u0010\t\u001A\u00020\n2\u0006\u0010\u000B\u001A\u00020\f2\u0006\u0010\r\u001A\u00020\u000EH\u0000\"\u000E\u0010\u0000\u001A\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000E\u0010\u0002\u001A\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000E\u0010\u0004\u001A\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000F"}, d2 = {"DELAY_MS", "", "MAX_DELAY_MS", "", "TAG", "", "maybeLaunchUnfinishedWorkListener", "", "Lkotlinx/coroutines/CoroutineScope;", "appContext", "Landroid/content/Context;", "configuration", "Landroidx/work/Configuration;", "db", "Landroidx/work/impl/WorkDatabase;", "work-runtime_release"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class UnfinishedWorkListenerKt {
    private static final int DELAY_MS = 30000;
    private static final long MAX_DELAY_MS;
    private static final String TAG;

    static {
        Intrinsics.checkNotNullExpressionValue("WM-UnfinishedWorkListen", "tagWithPrefix(\"UnfinishedWorkListener\")");
        UnfinishedWorkListenerKt.TAG = "WM-UnfinishedWorkListen";
        UnfinishedWorkListenerKt.MAX_DELAY_MS = TimeUnit.HOURS.toMillis(1L);
    }

    public static final long access$getMAX_DELAY_MS$p() [...] // 潜在的解密器

    // 去混淆评级： 低(20)
    public static final String access$getTAG$p() [...] // 潜在的解密器

    public static final void maybeLaunchUnfinishedWorkListener(CoroutineScope coroutineScope0, Context context0, Configuration configuration0, WorkDatabase workDatabase0) {
        Intrinsics.checkNotNullParameter(coroutineScope0, "<this>");
        Intrinsics.checkNotNullParameter(context0, "appContext");
        Intrinsics.checkNotNullParameter(configuration0, "configuration");
        Intrinsics.checkNotNullParameter(workDatabase0, "db");
        if(ProcessUtils.isDefaultProcess(context0, configuration0)) {
            FlowKt.launchIn(FlowKt.onEach(FlowKt.distinctUntilChanged(FlowKt.conflate(FlowKt.retryWhen(workDatabase0.workSpecDao().hasUnfinishedWorkFlow(), new Function4(null) {
                long J$0;
                Object L$0;
                int label;

                {
                    super(4, continuation0);
                }

                @Override  // kotlin.jvm.functions.Function4
                public Object invoke(Object object0, Object object1, Object object2, Object object3) {
                    return this.invoke(((FlowCollector)object0), ((Throwable)object1), ((Number)object2).longValue(), ((Continuation)object3));
                }

                public final Object invoke(FlowCollector flowCollector0, Throwable throwable0, long v, Continuation continuation0) {
                    androidx.work.impl.UnfinishedWorkListenerKt.maybeLaunchUnfinishedWorkListener.1 unfinishedWorkListenerKt$maybeLaunchUnfinishedWorkListener$10 = new androidx.work.impl.UnfinishedWorkListenerKt.maybeLaunchUnfinishedWorkListener.1(continuation0);
                    unfinishedWorkListenerKt$maybeLaunchUnfinishedWorkListener$10.L$0 = throwable0;
                    unfinishedWorkListenerKt$maybeLaunchUnfinishedWorkListener$10.J$0 = v;
                    return unfinishedWorkListenerKt$maybeLaunchUnfinishedWorkListener$10.invokeSuspend(Unit.INSTANCE);
                }

                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object object0) {
                    Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    switch(this.label) {
                        case 0: {
                            ResultKt.throwOnFailure(object0);
                            Throwable throwable0 = (Throwable)this.L$0;
                            long v = this.J$0;
                            Logger.get().error("WM-UnfinishedWorkListen", "Cannot check for unfinished work", throwable0);
                            this.label = 1;
                            return DelayKt.delay(Math.min(v * 30000L, 3600000L), this) == object1 ? object1 : Boxing.boxBoolean(true);
                        }
                        case 1: {
                            ResultKt.throwOnFailure(object0);
                            return Boxing.boxBoolean(true);
                        }
                        default: {
                            throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                        }
                    }
                }
            }))), new Function2(context0, null) {
                final Context $appContext;
                boolean Z$0;
                int label;

                {
                    this.$appContext = context0;
                    super(2, continuation0);
                }

                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation create(Object object0, Continuation continuation0) {
                    androidx.work.impl.UnfinishedWorkListenerKt.maybeLaunchUnfinishedWorkListener.2 unfinishedWorkListenerKt$maybeLaunchUnfinishedWorkListener$20 = new androidx.work.impl.UnfinishedWorkListenerKt.maybeLaunchUnfinishedWorkListener.2(this.$appContext, continuation0);
                    unfinishedWorkListenerKt$maybeLaunchUnfinishedWorkListener$20.Z$0 = ((Boolean)object0).booleanValue();
                    return unfinishedWorkListenerKt$maybeLaunchUnfinishedWorkListener$20;
                }

                @Override  // kotlin.jvm.functions.Function2
                public Object invoke(Object object0, Object object1) {
                    return this.invoke(((Boolean)object0).booleanValue(), ((Continuation)object1));
                }

                public final Object invoke(boolean z, Continuation continuation0) {
                    return ((androidx.work.impl.UnfinishedWorkListenerKt.maybeLaunchUnfinishedWorkListener.2)this.create(Boolean.valueOf(z), continuation0)).invokeSuspend(Unit.INSTANCE);
                }

                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object object0) {
                    if(this.label != 0) {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    }
                    ResultKt.throwOnFailure(object0);
                    PackageManagerHelper.setComponentEnabled(this.$appContext, RescheduleReceiver.class, this.Z$0);
                    return Unit.INSTANCE;
                }
            }), coroutineScope0);
        }
    }
}

