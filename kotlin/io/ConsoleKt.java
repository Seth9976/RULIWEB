package kotlin.io;

import java.io.InputStream;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000<\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0010\u000B\n\u0002\u0010\u0005\n\u0002\u0010\f\n\u0002\u0010\u0019\n\u0002\u0010\u0006\n\u0002\u0010\u0007\n\u0002\u0010\b\n\u0002\u0010\t\n\u0002\u0010\n\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0002\b\u0003\u001A\u0013\u0010\u0000\u001A\u00020\u00012\b\u0010\u0002\u001A\u0004\u0018\u00010\u0003H\u0087\b\u001A\u0011\u0010\u0000\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u0004H\u0087\b\u001A\u0011\u0010\u0000\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u0005H\u0087\b\u001A\u0011\u0010\u0000\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u0006H\u0087\b\u001A\u0011\u0010\u0000\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u0007H\u0087\b\u001A\u0011\u0010\u0000\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\bH\u0087\b\u001A\u0011\u0010\u0000\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\tH\u0087\b\u001A\u0011\u0010\u0000\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\nH\u0087\b\u001A\u0011\u0010\u0000\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u000BH\u0087\b\u001A\u0011\u0010\u0000\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\fH\u0087\b\u001A\t\u0010\r\u001A\u00020\u0001H\u0087\b\u001A\u0013\u0010\r\u001A\u00020\u00012\b\u0010\u0002\u001A\u0004\u0018\u00010\u0003H\u0087\b\u001A\u0011\u0010\r\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u0004H\u0087\b\u001A\u0011\u0010\r\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u0005H\u0087\b\u001A\u0011\u0010\r\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u0006H\u0087\b\u001A\u0011\u0010\r\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u0007H\u0087\b\u001A\u0011\u0010\r\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\bH\u0087\b\u001A\u0011\u0010\r\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\tH\u0087\b\u001A\u0011\u0010\r\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\nH\u0087\b\u001A\u0011\u0010\r\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u000BH\u0087\b\u001A\u0011\u0010\r\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\fH\u0087\b\u001A\b\u0010\u000E\u001A\u0004\u0018\u00010\u000F\u001A\b\u0010\u0010\u001A\u00020\u000FH\u0007\u001A\n\u0010\u0011\u001A\u0004\u0018\u00010\u000FH\u0007¨\u0006\u0012"}, d2 = {"print", "", "message", "", "", "", "", "", "", "", "", "", "", "println", "readLine", "", "readln", "readlnOrNull", "kotlin-stdlib"}, k = 2, mv = {1, 9, 0}, xi = 0x30)
public final class ConsoleKt {
    private static final void print(byte b) {
        System.out.print(b);
    }

    private static final void print(char c) {
        System.out.print(c);
    }

    private static final void print(double f) {
        System.out.print(f);
    }

    private static final void print(float f) {
        System.out.print(f);
    }

    private static final void print(int v) {
        System.out.print(v);
    }

    private static final void print(long v) {
        System.out.print(v);
    }

    private static final void print(Object object0) {
        System.out.print(object0);
    }

    private static final void print(short v) {
        System.out.print(v);
    }

    private static final void print(boolean z) {
        System.out.print(z);
    }

    private static final void print(char[] arr_c) {
        Intrinsics.checkNotNullParameter(arr_c, "message");
        System.out.print(arr_c);
    }

    private static final void println() {
        System.out.println();
    }

    private static final void println(byte b) {
        System.out.println(b);
    }

    private static final void println(char c) {
        System.out.println(c);
    }

    private static final void println(double f) {
        System.out.println(f);
    }

    private static final void println(float f) {
        System.out.println(f);
    }

    private static final void println(int v) {
        System.out.println(v);
    }

    private static final void println(long v) {
        System.out.println(v);
    }

    private static final void println(Object object0) {
        System.out.println(object0);
    }

    private static final void println(short v) {
        System.out.println(v);
    }

    private static final void println(boolean z) {
        System.out.println(z);
    }

    private static final void println(char[] arr_c) {
        Intrinsics.checkNotNullParameter(arr_c, "message");
        System.out.println(arr_c);
    }

    public static final String readLine() {
        InputStream inputStream0 = System.in;
        Intrinsics.checkNotNullExpressionValue(inputStream0, "`in`");
        Charset charset0 = Charset.defaultCharset();
        Intrinsics.checkNotNullExpressionValue(charset0, "defaultCharset()");
        return LineReader.INSTANCE.readLine(inputStream0, charset0);
    }

    public static final String readln() {
        String s = ConsoleKt.readlnOrNull();
        if(s == null) {
            throw new ReadAfterEOFException("EOF has already been reached");
        }
        return s;
    }

    public static final String readlnOrNull() {
        return ConsoleKt.readLine();
    }
}

