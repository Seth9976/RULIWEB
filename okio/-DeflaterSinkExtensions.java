package okio;

import java.util.zip.Deflater;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001A\u0017\u0010\u0000\u001A\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001A\u00020\u0004H\u0086\b¨\u0006\u0005"}, d2 = {"deflate", "Lokio/DeflaterSink;", "Lokio/Sink;", "deflater", "Ljava/util/zip/Deflater;", "okio"}, k = 2, mv = {1, 6, 0}, xi = 0x30)
public final class -DeflaterSinkExtensions {
    public static final DeflaterSink deflate(Sink sink0, Deflater deflater0) {
        Intrinsics.checkNotNullParameter(sink0, "<this>");
        Intrinsics.checkNotNullParameter(deflater0, "deflater");
        return new DeflaterSink(sink0, deflater0);
    }

    public static DeflaterSink deflate$default(Sink sink0, Deflater deflater0, int v, Object object0) {
        if((v & 1) != 0) {
            deflater0 = new Deflater();
        }
        Intrinsics.checkNotNullParameter(sink0, "<this>");
        Intrinsics.checkNotNullParameter(deflater0, "deflater");
        return new DeflaterSink(sink0, deflater0);
    }
}

