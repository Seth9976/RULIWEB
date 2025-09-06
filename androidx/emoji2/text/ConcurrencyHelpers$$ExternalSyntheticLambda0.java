package androidx.emoji2.text;

import android.os.Handler;
import java.util.concurrent.Executor;

public final class ConcurrencyHelpers..ExternalSyntheticLambda0 implements Executor {
    public final Handler f$0;

    public ConcurrencyHelpers..ExternalSyntheticLambda0(Handler handler0) {
        this.f$0 = handler0;
    }

    @Override
    public final void execute(Runnable runnable0) {
        this.f$0.post(runnable0);
    }
}

