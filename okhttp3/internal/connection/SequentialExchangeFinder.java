package okhttp3.internal.connection;

import java.io.IOException;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001A\u00020\bH\u0016R\u0014\u0010\u0002\u001A\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001A\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Lokhttp3/internal/connection/SequentialExchangeFinder;", "Lokhttp3/internal/connection/ExchangeFinder;", "routePlanner", "Lokhttp3/internal/connection/RoutePlanner;", "(Lokhttp3/internal/connection/RoutePlanner;)V", "getRoutePlanner", "()Lokhttp3/internal/connection/RoutePlanner;", "find", "Lokhttp3/internal/connection/RealConnection;", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
public final class SequentialExchangeFinder implements ExchangeFinder {
    private final RoutePlanner routePlanner;

    public SequentialExchangeFinder(RoutePlanner routePlanner0) {
        Intrinsics.checkNotNullParameter(routePlanner0, "routePlanner");
        super();
        this.routePlanner = routePlanner0;
    }

    @Override  // okhttp3.internal.connection.ExchangeFinder
    public RealConnection find() {
        Plan routePlanner$Plan1;
        IOException iOException0 = null;
        while(!this.getRoutePlanner().isCanceled()) {
            try {
                Plan routePlanner$Plan0 = this.getRoutePlanner().plan();
                if(routePlanner$Plan0.isReady()) {
                    return routePlanner$Plan0.handleSuccess();
                }
                else {
                    ConnectResult routePlanner$ConnectResult0 = routePlanner$Plan0.connectTcp();
                    if(routePlanner$ConnectResult0.isSuccess()) {
                        routePlanner$ConnectResult0 = routePlanner$Plan0.connectTlsEtc();
                    }
                    routePlanner$Plan1 = routePlanner$ConnectResult0.component2();
                    Throwable throwable0 = routePlanner$ConnectResult0.component3();
                    if(throwable0 != null) {
                        throw throwable0;
                    }
                    if(routePlanner$Plan1 == null) {
                        return routePlanner$Plan0.handleSuccess();
                    }
                }
                this.getRoutePlanner().getDeferredPlans().addFirst(routePlanner$Plan1);
            }
            catch(IOException iOException1) {
                if(iOException0 == null) {
                    iOException0 = iOException1;
                }
                else {
                    ExceptionsKt.addSuppressed(iOException0, iOException1);
                }
                if(!RoutePlanner.-CC.hasNext$default(this.getRoutePlanner(), null, 1, null)) {
                    throw iOException0;
                }
                if(false) {
                    break;
                }
            }
        }
        throw new IOException("Canceled");
    }

    @Override  // okhttp3.internal.connection.ExchangeFinder
    public RoutePlanner getRoutePlanner() {
        return this.routePlanner;
    }
}

