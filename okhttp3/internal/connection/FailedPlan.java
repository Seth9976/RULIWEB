package okhttp3.internal.connection;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0001\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\f\u001A\u00020\rH\u0016J\b\u0010\u000E\u001A\u00020\tH\u0016J\b\u0010\u000F\u001A\u00020\tH\u0016J\b\u0010\u0010\u001A\u00020\rH\u0016J\b\u0010\u0011\u001A\u00020\rH\u0016R\u0014\u0010\u0005\u001A\u00020\u0006X\u0096D¢\u0006\b\n\u0000\u001A\u0004\b\u0005\u0010\u0007R\u0011\u0010\b\u001A\u00020\t¢\u0006\b\n\u0000\u001A\u0004\b\n\u0010\u000B¨\u0006\u0012"}, d2 = {"Lokhttp3/internal/connection/FailedPlan;", "Lokhttp3/internal/connection/RoutePlanner$Plan;", "e", "", "(Ljava/lang/Throwable;)V", "isReady", "", "()Z", "result", "Lokhttp3/internal/connection/RoutePlanner$ConnectResult;", "getResult", "()Lokhttp3/internal/connection/RoutePlanner$ConnectResult;", "cancel", "", "connectTcp", "connectTlsEtc", "handleSuccess", "retry", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
public final class FailedPlan implements Plan {
    private final boolean isReady;
    private final ConnectResult result;

    public FailedPlan(Throwable throwable0) {
        Intrinsics.checkNotNullParameter(throwable0, "e");
        super();
        this.result = new ConnectResult(this, null, throwable0, 2, null);
    }

    // 去混淆评级： 低(20)
    public Void cancel() {
        throw new IllegalStateException("unexpected cancel");
    }

    @Override  // okhttp3.internal.connection.RoutePlanner$Plan
    public void cancel() {
        this.cancel();
    }

    @Override  // okhttp3.internal.connection.RoutePlanner$Plan
    public ConnectResult connectTcp() {
        return this.result;
    }

    @Override  // okhttp3.internal.connection.RoutePlanner$Plan
    public ConnectResult connectTlsEtc() {
        return this.result;
    }

    public final ConnectResult getResult() {
        return this.result;
    }

    // 去混淆评级： 低(20)
    public Void handleSuccess() {
        throw new IllegalStateException("unexpected call");
    }

    @Override  // okhttp3.internal.connection.RoutePlanner$Plan
    public RealConnection handleSuccess() {
        return (RealConnection)this.handleSuccess();
    }

    @Override  // okhttp3.internal.connection.RoutePlanner$Plan
    public boolean isReady() {
        return this.isReady;
    }

    // 去混淆评级： 低(20)
    public Void retry() {
        throw new IllegalStateException("unexpected retry");
    }

    @Override  // okhttp3.internal.connection.RoutePlanner$Plan
    public Plan retry() {
        return (Plan)this.retry();
    }
}

