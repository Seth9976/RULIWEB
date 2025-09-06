package androidx.emoji2.text;

import java.util.concurrent.ThreadFactory;

public final class ConcurrencyHelpers..ExternalSyntheticLambda1 implements ThreadFactory {
    public final String f$0;

    public ConcurrencyHelpers..ExternalSyntheticLambda1(String s) {
        this.f$0 = s;
    }

    @Override
    public final Thread newThread(Runnable runnable0) {
        return ConcurrencyHelpers.lambda$createBackgroundPriorityExecutor$0(this.f$0, runnable0);
    }
}

