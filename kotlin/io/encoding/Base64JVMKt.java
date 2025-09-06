package kotlin.io.encoding;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;

@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0010\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000E\n\u0000\u001A%\u0010\u0000\u001A\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\u0006H\u0081\b\u001A5\u0010\b\u001A\u00020\u0006*\u00020\u00022\u0006\u0010\u0003\u001A\u00020\u00012\u0006\u0010\t\u001A\u00020\u00012\u0006\u0010\n\u001A\u00020\u00062\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\u0006H\u0081\b\u001A%\u0010\u000B\u001A\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001A\u00020\u00012\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\u0006H\u0081\b\u001A%\u0010\f\u001A\u00020\r*\u00020\u00022\u0006\u0010\u0003\u001A\u00020\u00012\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\u0006H\u0081\b¨\u0006\u000E"}, d2 = {"platformCharsToBytes", "", "Lkotlin/io/encoding/Base64;", "source", "", "startIndex", "", "endIndex", "platformEncodeIntoByteArray", "destination", "destinationOffset", "platformEncodeToByteArray", "platformEncodeToString", "", "kotlin-stdlib"}, k = 2, mv = {1, 9, 0}, xi = 0x30)
public final class Base64JVMKt {
    private static final byte[] platformCharsToBytes(Base64 base640, CharSequence charSequence0, int v, int v1) {
        Intrinsics.checkNotNullParameter(base640, "<this>");
        Intrinsics.checkNotNullParameter(charSequence0, "source");
        if(charSequence0 instanceof String) {
            base640.checkSourceBounds$kotlin_stdlib(charSequence0.length(), v, v1);
            String s = ((String)charSequence0).substring(v, v1);
            Intrinsics.checkNotNullExpressionValue(s, "this as java.lang.String…ing(startIndex, endIndex)");
            Intrinsics.checkNotNull(s, "null cannot be cast to non-null type java.lang.String");
            byte[] arr_b = s.getBytes(Charsets.ISO_8859_1);
            Intrinsics.checkNotNullExpressionValue(arr_b, "this as java.lang.String).getBytes(charset)");
            return arr_b;
        }
        return base640.charsToBytesImpl$kotlin_stdlib(charSequence0, v, v1);
    }

    private static final int platformEncodeIntoByteArray(Base64 base640, byte[] arr_b, byte[] arr_b1, int v, int v1, int v2) {
        Intrinsics.checkNotNullParameter(base640, "<this>");
        Intrinsics.checkNotNullParameter(arr_b, "source");
        Intrinsics.checkNotNullParameter(arr_b1, "destination");
        return base640.encodeIntoByteArrayImpl$kotlin_stdlib(arr_b, arr_b1, v, v1, v2);
    }

    private static final byte[] platformEncodeToByteArray(Base64 base640, byte[] arr_b, int v, int v1) {
        Intrinsics.checkNotNullParameter(base640, "<this>");
        Intrinsics.checkNotNullParameter(arr_b, "source");
        return base640.encodeToByteArrayImpl$kotlin_stdlib(arr_b, v, v1);
    }

    private static final String platformEncodeToString(Base64 base640, byte[] arr_b, int v, int v1) {
        Intrinsics.checkNotNullParameter(base640, "<this>");
        Intrinsics.checkNotNullParameter(arr_b, "source");
        return new String(base640.encodeToByteArrayImpl$kotlin_stdlib(arr_b, v, v1), Charsets.ISO_8859_1);
    }
}

