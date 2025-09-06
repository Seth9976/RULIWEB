package okio;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0005\n\u0002\b\u0004\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001A\u0015\u0010\n\u001A\u00020\u000B*\u00020\u00012\u0006\u0010\f\u001A\u00020\u0001H\u0082\b\u001A\r\u0010\r\u001A\u00020\u000E*\u00020\u000FH\u0086\b\"\u000E\u0010\u0000\u001A\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000E\u0010\u0002\u001A\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000E\u0010\u0003\u001A\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000E\u0010\u0004\u001A\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000E\u0010\u0005\u001A\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000\"\u000E\u0010\u0007\u001A\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000\"\u000E\u0010\b\u001A\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000\"\u000E\u0010\t\u001A\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"FCOMMENT", "", "FEXTRA", "FHCRC", "FNAME", "SECTION_BODY", "", "SECTION_DONE", "SECTION_HEADER", "SECTION_TRAILER", "getBit", "", "bit", "gzip", "Lokio/GzipSource;", "Lokio/Source;", "okio"}, k = 2, mv = {1, 6, 0}, xi = 0x30)
public final class -GzipSourceExtensions {
    private static final int FCOMMENT = 4;
    private static final int FEXTRA = 2;
    private static final int FHCRC = 1;
    private static final int FNAME = 3;
    private static final byte SECTION_BODY = 1;
    private static final byte SECTION_DONE = 3;
    private static final byte SECTION_HEADER = 0;
    private static final byte SECTION_TRAILER = 2;

    private static final boolean getBit(int v, int v1) {
        return (v >> v1 & 1) == 1;
    }

    public static final GzipSource gzip(Source source0) {
        Intrinsics.checkNotNullParameter(source0, "<this>");
        return new GzipSource(source0);
    }
}

