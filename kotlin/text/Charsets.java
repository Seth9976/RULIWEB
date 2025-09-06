package kotlin.text;

import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0010\u0010\u0003\u001A\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001A\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001A\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001A\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001A\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\t\u001A\u00020\u00048G¢\u0006\u0006\u001A\u0004\b\n\u0010\u000BR\u0011\u0010\f\u001A\u00020\u00048G¢\u0006\u0006\u001A\u0004\b\r\u0010\u000BR\u0011\u0010\u000E\u001A\u00020\u00048G¢\u0006\u0006\u001A\u0004\b\u000F\u0010\u000BR\u0010\u0010\u0010\u001A\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001A\u0004\u0018\u00010\u0004X\u0082\u000E¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001A\u0004\u0018\u00010\u0004X\u0082\u000E¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001A\u0004\u0018\u00010\u0004X\u0082\u000E¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lkotlin/text/Charsets;", "", "()V", "ISO_8859_1", "Ljava/nio/charset/Charset;", "US_ASCII", "UTF_16", "UTF_16BE", "UTF_16LE", "UTF_32", "UTF32", "()Ljava/nio/charset/Charset;", "UTF_32BE", "UTF32_BE", "UTF_32LE", "UTF32_LE", "UTF_8", "utf_32", "utf_32be", "utf_32le", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public final class Charsets {
    public static final Charsets INSTANCE;
    public static final Charset ISO_8859_1;
    public static final Charset US_ASCII;
    public static final Charset UTF_16;
    public static final Charset UTF_16BE;
    public static final Charset UTF_16LE;
    public static final Charset UTF_8;
    private static volatile Charset utf_32;
    private static volatile Charset utf_32be;
    private static volatile Charset utf_32le;

    static {
        Charsets.INSTANCE = new Charsets();
        Charset charset0 = Charset.forName("UTF-8");
        Intrinsics.checkNotNullExpressionValue(charset0, "forName(\"UTF-8\")");
        Charsets.UTF_8 = charset0;
        Charset charset1 = Charset.forName("UTF-16");
        Intrinsics.checkNotNullExpressionValue(charset1, "forName(\"UTF-16\")");
        Charsets.UTF_16 = charset1;
        Charset charset2 = Charset.forName("UTF-16BE");
        Intrinsics.checkNotNullExpressionValue(charset2, "forName(\"UTF-16BE\")");
        Charsets.UTF_16BE = charset2;
        Charset charset3 = Charset.forName("UTF-16LE");
        Intrinsics.checkNotNullExpressionValue(charset3, "forName(\"UTF-16LE\")");
        Charsets.UTF_16LE = charset3;
        Charset charset4 = Charset.forName("US-ASCII");
        Intrinsics.checkNotNullExpressionValue(charset4, "forName(\"US-ASCII\")");
        Charsets.US_ASCII = charset4;
        Charset charset5 = Charset.forName("ISO-8859-1");
        Intrinsics.checkNotNullExpressionValue(charset5, "forName(\"ISO-8859-1\")");
        Charsets.ISO_8859_1 = charset5;
    }

    public final Charset UTF32() {
        Charset charset0 = Charsets.utf_32;
        if(charset0 == null) {
            charset0 = Charset.forName("UTF-32");
            Intrinsics.checkNotNullExpressionValue(charset0, "forName(\"UTF-32\")");
            Charsets.utf_32 = charset0;
        }
        return charset0;
    }

    public final Charset UTF32_BE() {
        Charset charset0 = Charsets.utf_32be;
        if(charset0 == null) {
            charset0 = Charset.forName("UTF-32BE");
            Intrinsics.checkNotNullExpressionValue(charset0, "forName(\"UTF-32BE\")");
            Charsets.utf_32be = charset0;
        }
        return charset0;
    }

    public final Charset UTF32_LE() {
        Charset charset0 = Charsets.utf_32le;
        if(charset0 == null) {
            charset0 = Charset.forName("UTF-32LE");
            Intrinsics.checkNotNullExpressionValue(charset0, "forName(\"UTF-32LE\")");
            Charsets.utf_32le = charset0;
        }
        return charset0;
    }
}

