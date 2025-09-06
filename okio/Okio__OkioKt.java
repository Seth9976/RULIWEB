package okio;

import java.io.Closeable;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001A\r\u0010\u0000\u001A\u00020\u0001H\u0007¢\u0006\u0002\b\u0002\u001A\n\u0010\u0003\u001A\u00020\u0004*\u00020\u0001\u001A\n\u0010\u0003\u001A\u00020\u0005*\u00020\u0006\u001AA\u0010\u0007\u001A\u0002H\b\"\u0010\b\u0000\u0010\t*\n\u0018\u00010\nj\u0004\u0018\u0001`\u000B\"\u0004\b\u0001\u0010\b*\u0002H\t2\u0012\u0010\f\u001A\u000E\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u0002H\b0\rH\u0086\bø\u0001\u0000¢\u0006\u0002\u0010\u000E\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u000F"}, d2 = {"blackholeSink", "Lokio/Sink;", "blackhole", "buffer", "Lokio/BufferedSink;", "Lokio/BufferedSource;", "Lokio/Source;", "use", "R", "T", "Ljava/io/Closeable;", "Lokio/Closeable;", "block", "Lkotlin/Function1;", "(Ljava/io/Closeable;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "okio"}, k = 5, mv = {1, 6, 0}, xi = 0x30, xs = "okio/Okio")
final class Okio__OkioKt {
    public static final Sink blackhole() {
        return new BlackholeSink();
    }

    public static final BufferedSink buffer(Sink sink0) {
        Intrinsics.checkNotNullParameter(sink0, "<this>");
        return new RealBufferedSink(sink0);
    }

    public static final BufferedSource buffer(Source source0) {
        Intrinsics.checkNotNullParameter(source0, "<this>");
        return new RealBufferedSource(source0);
    }

    public static final Object use(Closeable closeable0, Function1 function10) {
        Object object0;
        Intrinsics.checkNotNullParameter(function10, "block");
        Throwable throwable0 = null;
        try {
            object0 = function10.invoke(closeable0);
        }
        catch(Throwable throwable1) {
            throwable0 = throwable1;
            object0 = null;
        }
        if(closeable0 != null) {
            try {
                closeable0.close();
            }
            catch(Throwable throwable2) {
                if(throwable0 == null) {
                    throwable0 = throwable2;
                }
                else {
                    ExceptionsKt.addSuppressed(throwable0, throwable2);
                }
            }
        }
        if(throwable0 != null) {
            throw throwable0;
        }
        Intrinsics.checkNotNull(object0);
        return object0;
    }
}

