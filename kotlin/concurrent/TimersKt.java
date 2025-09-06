package kotlin.concurrent;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00004\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u001AM\u0010\u0000\u001A\u00020\u00012\n\b\u0002\u0010\u0002\u001A\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001A\u00020\u00052\u0006\u0010\u0006\u001A\u00020\u00072\u0006\u0010\b\u001A\u00020\t2\u0019\b\u0004\u0010\n\u001A\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000B\u00A2\u0006\u0002\b\u000EH\u0087\b\u00F8\u0001\u0000\u001AO\u0010\u0000\u001A\u00020\u00012\n\b\u0002\u0010\u0002\u001A\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001A\u00020\u00052\b\b\u0002\u0010\u000F\u001A\u00020\t2\u0006\u0010\b\u001A\u00020\t2\u0019\b\u0004\u0010\n\u001A\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000B\u00A2\u0006\u0002\b\u000EH\u0087\b\u00F8\u0001\u0000\u001A\u001A\u0010\u0010\u001A\u00020\u00012\b\u0010\u0002\u001A\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001A\u00020\u0005H\u0001\u001AM\u0010\u0010\u001A\u00020\u00012\n\b\u0002\u0010\u0002\u001A\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001A\u00020\u00052\u0006\u0010\u0006\u001A\u00020\u00072\u0006\u0010\b\u001A\u00020\t2\u0019\b\u0004\u0010\n\u001A\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000B\u00A2\u0006\u0002\b\u000EH\u0087\b\u00F8\u0001\u0000\u001AO\u0010\u0010\u001A\u00020\u00012\n\b\u0002\u0010\u0002\u001A\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001A\u00020\u00052\b\b\u0002\u0010\u000F\u001A\u00020\t2\u0006\u0010\b\u001A\u00020\t2\u0019\b\u0004\u0010\n\u001A\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000B\u00A2\u0006\u0002\b\u000EH\u0087\b\u00F8\u0001\u0000\u001A\'\u0010\u0011\u001A\u00020\f2\u0019\b\u0004\u0010\n\u001A\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000B\u00A2\u0006\u0002\b\u000EH\u0087\b\u00F8\u0001\u0000\u001A3\u0010\u0012\u001A\u00020\f*\u00020\u00012\u0006\u0010\u0013\u001A\u00020\u00072\u0019\b\u0004\u0010\n\u001A\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000B\u00A2\u0006\u0002\b\u000EH\u0087\b\u00F8\u0001\u0000\u001A;\u0010\u0012\u001A\u00020\f*\u00020\u00012\u0006\u0010\u0013\u001A\u00020\u00072\u0006\u0010\b\u001A\u00020\t2\u0019\b\u0004\u0010\n\u001A\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000B\u00A2\u0006\u0002\b\u000EH\u0087\b\u00F8\u0001\u0000\u001A3\u0010\u0012\u001A\u00020\f*\u00020\u00012\u0006\u0010\u0014\u001A\u00020\t2\u0019\b\u0004\u0010\n\u001A\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000B\u00A2\u0006\u0002\b\u000EH\u0087\b\u00F8\u0001\u0000\u001A;\u0010\u0012\u001A\u00020\f*\u00020\u00012\u0006\u0010\u0014\u001A\u00020\t2\u0006\u0010\b\u001A\u00020\t2\u0019\b\u0004\u0010\n\u001A\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000B\u00A2\u0006\u0002\b\u000EH\u0087\b\u00F8\u0001\u0000\u001A;\u0010\u0015\u001A\u00020\f*\u00020\u00012\u0006\u0010\u0013\u001A\u00020\u00072\u0006\u0010\b\u001A\u00020\t2\u0019\b\u0004\u0010\n\u001A\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000B\u00A2\u0006\u0002\b\u000EH\u0087\b\u00F8\u0001\u0000\u001A;\u0010\u0015\u001A\u00020\f*\u00020\u00012\u0006\u0010\u0014\u001A\u00020\t2\u0006\u0010\b\u001A\u00020\t2\u0019\b\u0004\u0010\n\u001A\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000B\u00A2\u0006\u0002\b\u000EH\u0087\b\u00F8\u0001\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001\u00A8\u0006\u0016"}, d2 = {"fixedRateTimer", "Ljava/util/Timer;", "name", "", "daemon", "", "startAt", "Ljava/util/Date;", "period", "", "action", "Lkotlin/Function1;", "Ljava/util/TimerTask;", "", "Lkotlin/ExtensionFunctionType;", "initialDelay", "timer", "timerTask", "schedule", "time", "delay", "scheduleAtFixedRate", "kotlin-stdlib"}, k = 2, mv = {1, 9, 0}, xi = 0x30)
public final class TimersKt {
    private static final Timer fixedRateTimer(String s, boolean z, long v, long v1, Function1 function10) {
        Intrinsics.checkNotNullParameter(function10, "action");
        Timer timer0 = TimersKt.timer(s, z);
        timer0.scheduleAtFixedRate(new kotlin.concurrent.TimersKt.timerTask.1(function10), v, v1);
        return timer0;
    }

    private static final Timer fixedRateTimer(String s, boolean z, Date date0, long v, Function1 function10) {
        Intrinsics.checkNotNullParameter(date0, "startAt");
        Intrinsics.checkNotNullParameter(function10, "action");
        Timer timer0 = TimersKt.timer(s, z);
        timer0.scheduleAtFixedRate(new kotlin.concurrent.TimersKt.timerTask.1(function10), date0, v);
        return timer0;
    }

    static Timer fixedRateTimer$default(String s, boolean z, long v, long v1, Function1 function10, int v2, Object object0) {
        if((v2 & 1) != 0) {
            s = null;
        }
        if((v2 & 2) != 0) {
            z = false;
        }
        if((v2 & 4) != 0) {
            v = 0L;
        }
        Intrinsics.checkNotNullParameter(function10, "action");
        Timer timer0 = TimersKt.timer(s, z);
        timer0.scheduleAtFixedRate(new kotlin.concurrent.TimersKt.timerTask.1(function10), v, v1);
        return timer0;
    }

    static Timer fixedRateTimer$default(String s, boolean z, Date date0, long v, Function1 function10, int v1, Object object0) {
        if((v1 & 1) != 0) {
            s = null;
        }
        if((v1 & 2) != 0) {
            z = false;
        }
        Intrinsics.checkNotNullParameter(date0, "startAt");
        Intrinsics.checkNotNullParameter(function10, "action");
        Timer timer0 = TimersKt.timer(s, z);
        timer0.scheduleAtFixedRate(new kotlin.concurrent.TimersKt.timerTask.1(function10), date0, v);
        return timer0;
    }

    private static final TimerTask schedule(Timer timer0, long v, long v1, Function1 function10) {
        Intrinsics.checkNotNullParameter(timer0, "<this>");
        Intrinsics.checkNotNullParameter(function10, "action");
        TimerTask timerTask0 = new kotlin.concurrent.TimersKt.timerTask.1(function10);
        timer0.schedule(timerTask0, v, v1);
        return timerTask0;
    }

    private static final TimerTask schedule(Timer timer0, long v, Function1 function10) {
        Intrinsics.checkNotNullParameter(timer0, "<this>");
        Intrinsics.checkNotNullParameter(function10, "action");
        TimerTask timerTask0 = new kotlin.concurrent.TimersKt.timerTask.1(function10);
        timer0.schedule(timerTask0, v);
        return timerTask0;
    }

    private static final TimerTask schedule(Timer timer0, Date date0, long v, Function1 function10) {
        Intrinsics.checkNotNullParameter(timer0, "<this>");
        Intrinsics.checkNotNullParameter(date0, "time");
        Intrinsics.checkNotNullParameter(function10, "action");
        TimerTask timerTask0 = new kotlin.concurrent.TimersKt.timerTask.1(function10);
        timer0.schedule(timerTask0, date0, v);
        return timerTask0;
    }

    private static final TimerTask schedule(Timer timer0, Date date0, Function1 function10) {
        Intrinsics.checkNotNullParameter(timer0, "<this>");
        Intrinsics.checkNotNullParameter(date0, "time");
        Intrinsics.checkNotNullParameter(function10, "action");
        TimerTask timerTask0 = new kotlin.concurrent.TimersKt.timerTask.1(function10);
        timer0.schedule(timerTask0, date0);
        return timerTask0;
    }

    private static final TimerTask scheduleAtFixedRate(Timer timer0, long v, long v1, Function1 function10) {
        Intrinsics.checkNotNullParameter(timer0, "<this>");
        Intrinsics.checkNotNullParameter(function10, "action");
        TimerTask timerTask0 = new kotlin.concurrent.TimersKt.timerTask.1(function10);
        timer0.scheduleAtFixedRate(timerTask0, v, v1);
        return timerTask0;
    }

    private static final TimerTask scheduleAtFixedRate(Timer timer0, Date date0, long v, Function1 function10) {
        Intrinsics.checkNotNullParameter(timer0, "<this>");
        Intrinsics.checkNotNullParameter(date0, "time");
        Intrinsics.checkNotNullParameter(function10, "action");
        TimerTask timerTask0 = new kotlin.concurrent.TimersKt.timerTask.1(function10);
        timer0.scheduleAtFixedRate(timerTask0, date0, v);
        return timerTask0;
    }

    public static final Timer timer(String s, boolean z) {
        return s == null ? new Timer(z) : new Timer(s, z);
    }

    private static final Timer timer(String s, boolean z, long v, long v1, Function1 function10) {
        Intrinsics.checkNotNullParameter(function10, "action");
        Timer timer0 = TimersKt.timer(s, z);
        timer0.schedule(new kotlin.concurrent.TimersKt.timerTask.1(function10), v, v1);
        return timer0;
    }

    private static final Timer timer(String s, boolean z, Date date0, long v, Function1 function10) {
        Intrinsics.checkNotNullParameter(date0, "startAt");
        Intrinsics.checkNotNullParameter(function10, "action");
        Timer timer0 = TimersKt.timer(s, z);
        timer0.schedule(new kotlin.concurrent.TimersKt.timerTask.1(function10), date0, v);
        return timer0;
    }

    static Timer timer$default(String s, boolean z, long v, long v1, Function1 function10, int v2, Object object0) {
        if((v2 & 1) != 0) {
            s = null;
        }
        if((v2 & 2) != 0) {
            z = false;
        }
        if((v2 & 4) != 0) {
            v = 0L;
        }
        Intrinsics.checkNotNullParameter(function10, "action");
        Timer timer0 = TimersKt.timer(s, z);
        timer0.schedule(new kotlin.concurrent.TimersKt.timerTask.1(function10), v, v1);
        return timer0;
    }

    static Timer timer$default(String s, boolean z, Date date0, long v, Function1 function10, int v1, Object object0) {
        if((v1 & 1) != 0) {
            s = null;
        }
        if((v1 & 2) != 0) {
            z = false;
        }
        Intrinsics.checkNotNullParameter(date0, "startAt");
        Intrinsics.checkNotNullParameter(function10, "action");
        Timer timer0 = TimersKt.timer(s, z);
        timer0.schedule(new kotlin.concurrent.TimersKt.timerTask.1(function10), date0, v);
        return timer0;
    }

    private static final TimerTask timerTask(Function1 function10) {
        Intrinsics.checkNotNullParameter(function10, "action");
        return new TimerTask() {
            @Override
            public void run() {
                function10.invoke(this);
            }
        };
    }
}

