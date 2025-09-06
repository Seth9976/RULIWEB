package okhttp3;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bæ\u0080\u0001\u0018\u0000 \u00072\u00020\u0001:\u0002\u0006\u0007J\u0010\u0010\u0002\u001A\u00020\u00032\u0006\u0010\u0004\u001A\u00020\u0005H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\bÀ\u0006\u0001"}, d2 = {"Lokhttp3/Interceptor;", "", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "Chain", "Companion", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
public interface Interceptor {
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001A\u00020\u0003H&J\b\u0010\u0004\u001A\u00020\u0005H&J\n\u0010\u0006\u001A\u0004\u0018\u00010\u0007H&J\u0010\u0010\b\u001A\u00020\t2\u0006\u0010\n\u001A\u00020\u000BH&J\b\u0010\f\u001A\u00020\u0005H&J\b\u0010\n\u001A\u00020\u000BH&J\u0018\u0010\r\u001A\u00020\u00002\u0006\u0010\u000E\u001A\u00020\u00052\u0006\u0010\u000F\u001A\u00020\u0010H&J\u0018\u0010\u0011\u001A\u00020\u00002\u0006\u0010\u000E\u001A\u00020\u00052\u0006\u0010\u000F\u001A\u00020\u0010H&J\u0018\u0010\u0012\u001A\u00020\u00002\u0006\u0010\u000E\u001A\u00020\u00052\u0006\u0010\u000F\u001A\u00020\u0010H&J\b\u0010\u0013\u001A\u00020\u0005H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0014À\u0006\u0001"}, d2 = {"Lokhttp3/Interceptor$Chain;", "", "call", "Lokhttp3/Call;", "connectTimeoutMillis", "", "connection", "Lokhttp3/Connection;", "proceed", "Lokhttp3/Response;", "request", "Lokhttp3/Request;", "readTimeoutMillis", "withConnectTimeout", "timeout", "unit", "Ljava/util/concurrent/TimeUnit;", "withReadTimeout", "withWriteTimeout", "writeTimeoutMillis", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    public interface Chain {
        Call call();

        int connectTimeoutMillis();

        Connection connection();

        Response proceed(Request arg1) throws IOException;

        int readTimeoutMillis();

        Request request();

        Chain withConnectTimeout(int arg1, TimeUnit arg2);

        Chain withReadTimeout(int arg1, TimeUnit arg2);

        Chain withWriteTimeout(int arg1, TimeUnit arg2);

        int writeTimeoutMillis();
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J1\u0010\u0003\u001A\u00020\u00042#\b\u0004\u0010\u0005\u001A\u001D\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000B0\u0006H\u0086\nø\u0001\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\f"}, d2 = {"Lokhttp3/Interceptor$Companion;", "", "()V", "invoke", "Lokhttp3/Interceptor;", "block", "Lkotlin/Function1;", "Lokhttp3/Interceptor$Chain;", "Lkotlin/ParameterName;", "name", "chain", "Lokhttp3/Response;", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    public static final class Companion {
        static final Companion $$INSTANCE;

        static {
            Companion.$$INSTANCE = new Companion();
        }

        public final Interceptor invoke(Function1 function10) {
            Intrinsics.checkNotNullParameter(function10, "block");
            return new Interceptor() {
                @Override  // okhttp3.Interceptor
                public final Response intercept(Chain interceptor$Chain0) {
                    Intrinsics.checkNotNullParameter(interceptor$Chain0, "it");
                    return (Response)this.$block.invoke(interceptor$Chain0);
                }
            };
        }
    }

    public static final Companion Companion;

    static {
        Interceptor.Companion = Companion.$$INSTANCE;
    }

    Response intercept(Chain arg1) throws IOException;
}

