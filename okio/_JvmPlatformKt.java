package okio;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;

@Metadata(d1 = {"\u0000B\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\u0010\u000E\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001A-\u0010\u0000\u001A\u0002H\u0001\"\u0004\b\u0000\u0010\u00012\u0006\u0010\u0002\u001A\u00020\u00032\f\u0010\u0004\u001A\b\u0012\u0004\u0012\u0002H\u00010\u0005H\u0080\bø\u0001\u0000¢\u0006\u0002\u0010\u0006\u001A\f\u0010\u0007\u001A\u00020\b*\u00020\tH\u0000\u001A\f\u0010\n\u001A\u00020\t*\u00020\bH\u0000*\n\u0010\u000B\"\u00020\f2\u00020\f*\n\u0010\r\"\u00020\u000E2\u00020\u000E*\n\u0010\u000F\"\u00020\u00102\u00020\u0010*\n\u0010\u0011\"\u00020\u00122\u00020\u0012*\n\u0010\u0013\"\u00020\u00142\u00020\u0014*\n\u0010\u0015\"\u00020\u00162\u00020\u0016\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0017"}, d2 = {"synchronized", "R", "lock", "", "block", "Lkotlin/Function0;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "asUtf8ToByteArray", "", "", "toUtf8String", "ArrayIndexOutOfBoundsException", "Ljava/lang/ArrayIndexOutOfBoundsException;", "Closeable", "Ljava/io/Closeable;", "EOFException", "Ljava/io/EOFException;", "FileNotFoundException", "Ljava/io/FileNotFoundException;", "IOException", "Ljava/io/IOException;", "ProtocolException", "Ljava/net/ProtocolException;", "okio"}, k = 2, mv = {1, 6, 0}, xi = 0x30)
public final class _JvmPlatformKt {
    public static final byte[] asUtf8ToByteArray(String s) {
        Intrinsics.checkNotNullParameter(s, "<this>");
        byte[] arr_b = s.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(arr_b, "this as java.lang.String).getBytes(charset)");
        return arr_b;
    }

    public static final Object synchronized(Object object0, Function0 function00) {
        Intrinsics.checkNotNullParameter(object0, "lock");
        Intrinsics.checkNotNullParameter(function00, "block");
        synchronized(object0) {
            return function00.invoke();
        }
    }

    public static final String toUtf8String(byte[] arr_b) {
        Intrinsics.checkNotNullParameter(arr_b, "<this>");
        return new String(arr_b, Charsets.UTF_8);
    }
}

