package okhttp3.internal.connection;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0010\u0001\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\n\u001A\u00020\u000BH\u0016J\b\u0010\f\u001A\u00020\u000BH\u0016J\b\u0010\r\u001A\u00020\u000BH\u0016J\b\u0010\u000E\u001A\u00020\u0003H\u0016J\b\u0010\u000F\u001A\u00020\u000BH\u0016R\u0011\u0010\u0002\u001A\u00020\u0003¢\u0006\b\n\u0000\u001A\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001A\u00020\bX\u0096D¢\u0006\b\n\u0000\u001A\u0004\b\u0007\u0010\t¨\u0006\u0010"}, d2 = {"Lokhttp3/internal/connection/ReusePlan;", "Lokhttp3/internal/connection/RoutePlanner$Plan;", "connection", "Lokhttp3/internal/connection/RealConnection;", "(Lokhttp3/internal/connection/RealConnection;)V", "getConnection", "()Lokhttp3/internal/connection/RealConnection;", "isReady", "", "()Z", "cancel", "", "connectTcp", "connectTlsEtc", "handleSuccess", "retry", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
public final class ReusePlan implements Plan {
    private final RealConnection connection;
    private final boolean isReady;

    public ReusePlan(RealConnection realConnection0) {
        Intrinsics.checkNotNullParameter(realConnection0, "connection");
        super();
        this.connection = realConnection0;
        this.isReady = true;
    }

    // 去混淆评级： 低(20)
    public Void cancel() {
        throw new IllegalStateException("unexpected cancel");
    }

    @Override  // okhttp3.internal.connection.RoutePlanner$Plan
    public void cancel() {
        this.cancel();
    }

    // 去混淆评级： 低(20)
    public Void connectTcp() {
        throw new IllegalStateException("already connected");
    }

    @Override  // okhttp3.internal.connection.RoutePlanner$Plan
    public ConnectResult connectTcp() {
        return (ConnectResult)this.connectTcp();
    }

    // 去混淆评级： 低(20)
    public Void connectTlsEtc() {
        throw new IllegalStateException("already connected");
    }

    @Override  // okhttp3.internal.connection.RoutePlanner$Plan
    public ConnectResult connectTlsEtc() {
        return (ConnectResult)this.connectTlsEtc();
    }

    public final RealConnection getConnection() {
        return this.connection;
    }

    @Override  // okhttp3.internal.connection.RoutePlanner$Plan
    public RealConnection handleSuccess() {
        return this.connection;
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

