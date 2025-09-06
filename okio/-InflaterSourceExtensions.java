package okio;

import java.util.zip.Inflater;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001A\u0017\u0010\u0000\u001A\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001A\u00020\u0004H\u0086\b¨\u0006\u0005"}, d2 = {"inflate", "Lokio/InflaterSource;", "Lokio/Source;", "inflater", "Ljava/util/zip/Inflater;", "okio"}, k = 2, mv = {1, 6, 0}, xi = 0x30)
public final class -InflaterSourceExtensions {
    public static final InflaterSource inflate(Source source0, Inflater inflater0) {
        Intrinsics.checkNotNullParameter(source0, "<this>");
        Intrinsics.checkNotNullParameter(inflater0, "inflater");
        return new InflaterSource(source0, inflater0);
    }

    public static InflaterSource inflate$default(Source source0, Inflater inflater0, int v, Object object0) {
        if((v & 1) != 0) {
            inflater0 = new Inflater();
        }
        Intrinsics.checkNotNullParameter(source0, "<this>");
        Intrinsics.checkNotNullParameter(inflater0, "inflater");
        return new InflaterSource(source0, inflater0);
    }
}

