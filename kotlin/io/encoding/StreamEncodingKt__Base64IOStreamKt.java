package kotlin.io.encoding;

import java.io.InputStream;
import java.io.OutputStream;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001A\u0014\u0010\u0000\u001A\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u0003H\u0007\u001A\u0014\u0010\u0004\u001A\u00020\u0005*\u00020\u00052\u0006\u0010\u0002\u001A\u00020\u0003H\u0007¨\u0006\u0006"}, d2 = {"decodingWith", "Ljava/io/InputStream;", "base64", "Lkotlin/io/encoding/Base64;", "encodingWith", "Ljava/io/OutputStream;", "kotlin-stdlib"}, k = 5, mv = {1, 9, 0}, xi = 49, xs = "kotlin/io/encoding/StreamEncodingKt")
class StreamEncodingKt__Base64IOStreamKt {
    public static final InputStream decodingWith(InputStream inputStream0, Base64 base640) {
        Intrinsics.checkNotNullParameter(inputStream0, "<this>");
        Intrinsics.checkNotNullParameter(base640, "base64");
        return new DecodeInputStream(inputStream0, base640);
    }

    public static final OutputStream encodingWith(OutputStream outputStream0, Base64 base640) {
        Intrinsics.checkNotNullParameter(outputStream0, "<this>");
        Intrinsics.checkNotNullParameter(base640, "base64");
        return new EncodeOutputStream(outputStream0, base640);
    }
}

