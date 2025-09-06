package kotlin.text;

import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u000E\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0000\u001A\u0011\u0010\u0000\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u0003H\u0087\b¨\u0006\u0004"}, d2 = {"charset", "Ljava/nio/charset/Charset;", "charsetName", "", "kotlin-stdlib"}, k = 2, mv = {1, 9, 0}, xi = 0x30)
public final class CharsetsKt {
    private static final Charset charset(String s) {
        Intrinsics.checkNotNullParameter(s, "charsetName");
        Charset charset0 = Charset.forName(s);
        Intrinsics.checkNotNullExpressionValue(charset0, "forName(charsetName)");
        return charset0;
    }
}

