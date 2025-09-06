package androidx.activity;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function1;

@Metadata(d1 = {"\u0000\u001C\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\u001A0\u0010\u0000\u001A\u00020\u0001*\u00020\u00022\u001C\u0010\u0003\u001A\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0004H\u0086H¢\u0006\u0002\u0010\u0007¨\u0006\b"}, d2 = {"reportWhenComplete", "", "Landroidx/activity/FullyDrawnReporter;", "reporter", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "", "(Landroidx/activity/FullyDrawnReporter;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "activity_release"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class FullyDrawnReporterKt {
    public static final Object reportWhenComplete(FullyDrawnReporter fullyDrawnReporter0, Function1 function10, Continuation continuation0) {
        androidx.activity.FullyDrawnReporterKt.reportWhenComplete.1 fullyDrawnReporterKt$reportWhenComplete$10;
        if(continuation0 instanceof androidx.activity.FullyDrawnReporterKt.reportWhenComplete.1) {
            fullyDrawnReporterKt$reportWhenComplete$10 = (androidx.activity.FullyDrawnReporterKt.reportWhenComplete.1)continuation0;
            if((fullyDrawnReporterKt$reportWhenComplete$10.label & 0x80000000) == 0) {
                fullyDrawnReporterKt$reportWhenComplete$10 = new ContinuationImpl(continuation0) {
                    Object L$0;
                    int label;
                    Object result;

                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object object0) {
                        this.result = object0;
                        this.label |= 0x80000000;
                        return FullyDrawnReporterKt.reportWhenComplete(null, null, this);
                    }
                };
            }
            else {
                fullyDrawnReporterKt$reportWhenComplete$10.label ^= 0x80000000;
            }
        }
        else {
            fullyDrawnReporterKt$reportWhenComplete$10 = new ContinuationImpl(continuation0) {
                Object L$0;
                int label;
                Object result;

                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object object0) {
                    this.result = object0;
                    this.label |= 0x80000000;
                    return FullyDrawnReporterKt.reportWhenComplete(null, null, this);
                }
            };
        }
        Object object0 = fullyDrawnReporterKt$reportWhenComplete$10.result;
        Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch(fullyDrawnReporterKt$reportWhenComplete$10.label) {
            case 0: {
                ResultKt.throwOnFailure(object0);
                fullyDrawnReporter0.addReporter();
                if(fullyDrawnReporter0.isFullyDrawnReported()) {
                    return Unit.INSTANCE;
                }
                try {
                    fullyDrawnReporterKt$reportWhenComplete$10.L$0 = fullyDrawnReporter0;
                    fullyDrawnReporterKt$reportWhenComplete$10.label = 1;
                    if(function10.invoke(fullyDrawnReporterKt$reportWhenComplete$10) == object1) {
                        return object1;
                    }
                    break;
                }
                catch(Throwable throwable0) {
                    fullyDrawnReporter0.removeReporter();
                    throw throwable0;
                }
            }
            case 1: {
                fullyDrawnReporter0 = (FullyDrawnReporter)fullyDrawnReporterKt$reportWhenComplete$10.L$0;
                try {
                    ResultKt.throwOnFailure(object0);
                    break;
                }
                catch(Throwable throwable0) {
                    fullyDrawnReporter0.removeReporter();
                    throw throwable0;
                }
            }
            default: {
                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
            }
        }
        fullyDrawnReporter0.removeReporter();
        return Unit.INSTANCE;
    }

    private static final Object reportWhenComplete$$forInline(FullyDrawnReporter fullyDrawnReporter0, Function1 function10, Continuation continuation0) {
        fullyDrawnReporter0.addReporter();
        if(fullyDrawnReporter0.isFullyDrawnReported()) {
            return Unit.INSTANCE;
        }
        try {
            function10.invoke(continuation0);
            return Unit.INSTANCE;
        }
        finally {
            fullyDrawnReporter0.removeReporter();
        }
    }
}

