package androidx.core.os;

import android.os.ProfilingResult;
import java.util.function.Consumer;
import kotlinx.coroutines.channels.ProducerScope;

public final class Profiling.registerForAllProfilingResults.1..ExternalSyntheticLambda0 implements Consumer {
    public final ProducerScope f$0;

    public Profiling.registerForAllProfilingResults.1..ExternalSyntheticLambda0(ProducerScope producerScope0) {
        this.f$0 = producerScope0;
    }

    @Override
    public final void accept(Object object0) {
        androidx.core.os.Profiling.registerForAllProfilingResults.1.invokeSuspend$lambda$0(this.f$0, ((ProfilingResult)object0));
    }
}

