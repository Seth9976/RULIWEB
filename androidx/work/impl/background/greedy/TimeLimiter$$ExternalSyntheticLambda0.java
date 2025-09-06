package androidx.work.impl.background.greedy;

import androidx.work.impl.StartStopToken;

public final class TimeLimiter..ExternalSyntheticLambda0 implements Runnable {
    public final TimeLimiter f$0;
    public final StartStopToken f$1;

    public TimeLimiter..ExternalSyntheticLambda0(TimeLimiter timeLimiter0, StartStopToken startStopToken0) {
        this.f$0 = timeLimiter0;
        this.f$1 = startStopToken0;
    }

    @Override
    public final void run() {
        TimeLimiter.$r8$lambda$dwAYwAFpPKNbMC8c13ZgeRxadTI(this.f$0, this.f$1);
    }
}

