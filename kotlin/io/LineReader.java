package kotlin.io;

import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;
import jeb.synthetic.FIN;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0014\u001A\u00020\u0004H\u0002J\u0010\u0010\u0015\u001A\u00020\u00042\u0006\u0010\u0016\u001A\u00020\u0010H\u0002J\u0018\u0010\u0017\u001A\u00020\u00042\u0006\u0010\u0018\u001A\u00020\u00042\u0006\u0010\u0019\u001A\u00020\u0004H\u0002J\u0018\u0010\u001A\u001A\u0004\u0018\u00010\u001B2\u0006\u0010\u001C\u001A\u00020\u001D2\u0006\u0010\u001E\u001A\u00020\u001FJ\b\u0010 \u001A\u00020!H\u0002J\b\u0010\"\u001A\u00020!H\u0002J\u0010\u0010#\u001A\u00020!2\u0006\u0010\u001E\u001A\u00020\u001FH\u0002R\u000E\u0010\u0003\u001A\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000E\u0010\u0005\u001A\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0007\u001A\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\t\u001A\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u000B\u001A\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\r\u001A\u00020\u000EX\u0082.¢\u0006\u0002\n\u0000R\u000E\u0010\u000F\u001A\u00020\u0010X\u0082\u000E¢\u0006\u0002\n\u0000R\u0012\u0010\u0011\u001A\u00060\u0012j\u0002`\u0013X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lkotlin/io/LineReader;", "", "()V", "BUFFER_SIZE", "", "byteBuf", "Ljava/nio/ByteBuffer;", "bytes", "", "charBuf", "Ljava/nio/CharBuffer;", "chars", "", "decoder", "Ljava/nio/charset/CharsetDecoder;", "directEOL", "", "sb", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "compactBytes", "decode", "endOfInput", "decodeEndOfInput", "nBytes", "nChars", "readLine", "", "inputStream", "Ljava/io/InputStream;", "charset", "Ljava/nio/charset/Charset;", "resetAll", "", "trimStringBuilder", "updateCharset", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public final class LineReader {
    private static final int BUFFER_SIZE = 0x20;
    public static final LineReader INSTANCE;
    private static final ByteBuffer byteBuf;
    private static final byte[] bytes;
    private static final CharBuffer charBuf;
    private static final char[] chars;
    private static CharsetDecoder decoder;
    private static boolean directEOL;
    private static final StringBuilder sb;

    static {
        LineReader.INSTANCE = new LineReader();
        byte[] arr_b = new byte[0x20];
        LineReader.bytes = arr_b;
        char[] arr_c = new char[0x20];
        LineReader.chars = arr_c;
        ByteBuffer byteBuffer0 = ByteBuffer.wrap(arr_b);
        Intrinsics.checkNotNullExpressionValue(byteBuffer0, "wrap(bytes)");
        LineReader.byteBuf = byteBuffer0;
        CharBuffer charBuffer0 = CharBuffer.wrap(arr_c);
        Intrinsics.checkNotNullExpressionValue(charBuffer0, "wrap(chars)");
        LineReader.charBuf = charBuffer0;
        LineReader.sb = new StringBuilder();
    }

    private final int compactBytes() {
        LineReader.byteBuf.compact();
        LineReader.byteBuf.position(0);
        return LineReader.byteBuf.position();
    }

    private final int decode(boolean z) {
        int v;
        while(true) {
            CharsetDecoder charsetDecoder0 = LineReader.decoder;
            if(charsetDecoder0 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("decoder");
                charsetDecoder0 = null;
            }
            CharBuffer charBuffer0 = LineReader.charBuf;
            CoderResult coderResult0 = charsetDecoder0.decode(LineReader.byteBuf, charBuffer0, z);
            Intrinsics.checkNotNullExpressionValue(coderResult0, "decoder.decode(byteBuf, charBuf, endOfInput)");
            if(coderResult0.isError()) {
                this.resetAll();
                coderResult0.throwException();
            }
            v = charBuffer0.position();
            if(!coderResult0.isOverflow()) {
                break;
            }
            LineReader.sb.append(LineReader.chars, 0, v - 1);
            charBuffer0.position(0);
            charBuffer0.limit(0x20);
            charBuffer0.put(LineReader.chars[v - 1]);
        }
        return v;
    }

    private final int decodeEndOfInput(int v, int v1) {
        ByteBuffer byteBuffer0 = LineReader.byteBuf;
        byteBuffer0.limit(v);
        LineReader.charBuf.position(v1);
        int v2 = this.decode(true);
        CharsetDecoder charsetDecoder0 = LineReader.decoder;
        if(charsetDecoder0 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("decoder");
            charsetDecoder0 = null;
        }
        charsetDecoder0.reset();
        byteBuffer0.position(0);
        return v2;
    }

    public final String readLine(InputStream inputStream0, Charset charset0) {
        int v4;
        __monitor_enter(this);
        int v = FIN.finallyOpen$NT();
        Intrinsics.checkNotNullParameter(inputStream0, "inputStream");
        Intrinsics.checkNotNullParameter(charset0, "charset");
        CharsetDecoder charsetDecoder0 = LineReader.decoder;
        if(charsetDecoder0 == null) {
            this.updateCharset(charset0);
        }
        else {
            if(charsetDecoder0 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("decoder");
                charsetDecoder0 = null;
            }
            if(!Intrinsics.areEqual(charsetDecoder0.charset(), charset0)) {
                this.updateCharset(charset0);
            }
        }
        int v1 = 0;
        int v2 = 0;
        while(true) {
            int v3 = inputStream0.read();
            if(v3 == -1) {
                if(LineReader.sb.length() == 0 && v1 == 0 && v2 == 0) {
                    FIN.finallyCodeBegin$NT(v);
                    __monitor_exit(this);
                    FIN.finallyCodeEnd$NT(v);
                    return null;
                }
                v4 = this.decodeEndOfInput(v1, v2);
            }
            else {
                LineReader.bytes[v1] = (byte)v3;
                if(v3 == 10 || v1 + 1 == 0x20 || !LineReader.directEOL) {
                    ByteBuffer byteBuffer0 = LineReader.byteBuf;
                    byteBuffer0.limit(v1 + 1);
                    LineReader.charBuf.position(v2);
                    v2 = this.decode(false);
                    if(v2 <= 0 || LineReader.chars[v2 - 1] != 10) {
                        goto label_52;
                    }
                    else {
                        byteBuffer0.position(0);
                        v4 = v2;
                    }
                }
                else {
                    ++v1;
                    continue;
                }
            }
            if(v4 > 0) {
                char[] arr_c = LineReader.chars;
                if(arr_c[v4 - 1] == 10) {
                    v4 = v4 - 1 <= 0 || arr_c[v4 - 2] != 13 ? v4 - 1 : v4 - 2;
                }
            }
            StringBuilder stringBuilder0 = LineReader.sb;
            if(stringBuilder0.length() == 0) {
                String s = new String(LineReader.chars, 0, v4);
                FIN.finallyExec$NT(v);
                return s;
            }
            stringBuilder0.append(LineReader.chars, 0, v4);
            String s1 = stringBuilder0.toString();
            Intrinsics.checkNotNullExpressionValue(s1, "sb.toString()");
            if(stringBuilder0.length() > 0x20) {
                this.trimStringBuilder();
            }
            stringBuilder0.setLength(0);
            FIN.finallyExec$NT(v);
            return s1;
        label_52:
            v1 = this.compactBytes();
        }
    }

    private final void resetAll() {
        CharsetDecoder charsetDecoder0 = LineReader.decoder;
        if(charsetDecoder0 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("decoder");
            charsetDecoder0 = null;
        }
        charsetDecoder0.reset();
        LineReader.byteBuf.position(0);
        LineReader.sb.setLength(0);
    }

    private final void trimStringBuilder() {
        LineReader.sb.setLength(0x20);
        LineReader.sb.trimToSize();
    }

    private final void updateCharset(Charset charset0) {
        CharsetDecoder charsetDecoder0 = charset0.newDecoder();
        Intrinsics.checkNotNullExpressionValue(charsetDecoder0, "charset.newDecoder()");
        LineReader.decoder = charsetDecoder0;
        ByteBuffer byteBuffer0 = LineReader.byteBuf;
        byteBuffer0.clear();
        CharBuffer charBuffer0 = LineReader.charBuf;
        charBuffer0.clear();
        byteBuffer0.put(10);
        byteBuffer0.flip();
        CharsetDecoder charsetDecoder1 = LineReader.decoder;
        if(charsetDecoder1 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("decoder");
            charsetDecoder1 = null;
        }
        boolean z = false;
        charsetDecoder1.decode(byteBuffer0, charBuffer0, false);
        if(charBuffer0.position() == 1 && charBuffer0.get(0) == 10) {
            z = true;
        }
        LineReader.directEOL = z;
        this.resetAll();
    }
}

