package okio;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001A\r\u0010\u0000\u001A\u00020\u0001*\u00020\u0002H\u0086\b¨\u0006\u0003"}, d2 = {"gzip", "Lokio/GzipSink;", "Lokio/Sink;", "okio"}, k = 2, mv = {1, 6, 0}, xi = 0x30)
public final class -GzipSinkExtensions {
    public static final GzipSink gzip(Sink sink0) {
        Intrinsics.checkNotNullParameter(sink0, "<this>");
        return new GzipSink(sink0);
    }
}

