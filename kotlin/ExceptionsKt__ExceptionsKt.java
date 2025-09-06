package kotlin;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import kotlin.internal.PlatformImplementationsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00004\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0000\u001A\u0014\u0010\r\u001A\u00020\u000E*\u00020\u00032\u0006\u0010\u000F\u001A\u00020\u0003H\u0007\u001A\r\u0010\u0010\u001A\u00020\u000E*\u00020\u0003H\u0087\b\u001A\u0015\u0010\u0010\u001A\u00020\u000E*\u00020\u00032\u0006\u0010\u0011\u001A\u00020\u0012H\u0087\b\u001A\u0015\u0010\u0010\u001A\u00020\u000E*\u00020\u00032\u0006\u0010\u0013\u001A\u00020\u0014H\u0087\b\u001A\f\u0010\u0015\u001A\u00020\u0016*\u00020\u0003H\u0007\"!\u0010\u0000\u001A\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00038F¢\u0006\f\u0012\u0004\b\u0004\u0010\u0005\u001A\u0004\b\u0006\u0010\u0007\"$\u0010\b\u001A\b\u0012\u0004\u0012\u00020\u00030\t*\u00020\u00038FX\u0087\u0004¢\u0006\f\u0012\u0004\b\n\u0010\u0005\u001A\u0004\b\u000B\u0010\f¨\u0006\u0017"}, d2 = {"stackTrace", "", "Ljava/lang/StackTraceElement;", "", "getStackTrace$annotations", "(Ljava/lang/Throwable;)V", "getStackTrace", "(Ljava/lang/Throwable;)[Ljava/lang/StackTraceElement;", "suppressedExceptions", "", "getSuppressedExceptions$annotations", "getSuppressedExceptions", "(Ljava/lang/Throwable;)Ljava/util/List;", "addSuppressed", "", "exception", "printStackTrace", "stream", "Ljava/io/PrintStream;", "writer", "Ljava/io/PrintWriter;", "stackTraceToString", "", "kotlin-stdlib"}, k = 5, mv = {1, 9, 0}, xi = 49, xs = "kotlin/ExceptionsKt")
class ExceptionsKt__ExceptionsKt {
    public static final void addSuppressed(Throwable throwable0, Throwable throwable1) {
        Intrinsics.checkNotNullParameter(throwable0, "<this>");
        Intrinsics.checkNotNullParameter(throwable1, "exception");
        if(throwable0 != throwable1) {
            PlatformImplementationsKt.IMPLEMENTATIONS.addSuppressed(throwable0, throwable1);
        }
    }

    public static final StackTraceElement[] getStackTrace(Throwable throwable0) {
        Intrinsics.checkNotNullParameter(throwable0, "<this>");
        StackTraceElement[] arr_stackTraceElement = throwable0.getStackTrace();
        Intrinsics.checkNotNull(arr_stackTraceElement);
        return arr_stackTraceElement;
    }

    public static void getStackTrace$annotations(Throwable throwable0) {
    }

    public static final List getSuppressedExceptions(Throwable throwable0) {
        Intrinsics.checkNotNullParameter(throwable0, "<this>");
        return PlatformImplementationsKt.IMPLEMENTATIONS.getSuppressed(throwable0);
    }

    public static void getSuppressedExceptions$annotations(Throwable throwable0) {
    }

    private static final void printStackTrace(Throwable throwable0) {
        Intrinsics.checkNotNullParameter(throwable0, "<this>");
        throwable0.printStackTrace();
    }

    private static final void printStackTrace(Throwable throwable0, PrintStream printStream0) {
        Intrinsics.checkNotNullParameter(throwable0, "<this>");
        Intrinsics.checkNotNullParameter(printStream0, "stream");
        throwable0.printStackTrace(printStream0);
    }

    private static final void printStackTrace(Throwable throwable0, PrintWriter printWriter0) {
        Intrinsics.checkNotNullParameter(throwable0, "<this>");
        Intrinsics.checkNotNullParameter(printWriter0, "writer");
        throwable0.printStackTrace(printWriter0);
    }

    public static final String stackTraceToString(Throwable throwable0) {
        Intrinsics.checkNotNullParameter(throwable0, "<this>");
        StringWriter stringWriter0 = new StringWriter();
        PrintWriter printWriter0 = new PrintWriter(stringWriter0);
        throwable0.printStackTrace(printWriter0);
        printWriter0.flush();
        String s = stringWriter0.toString();
        Intrinsics.checkNotNullExpressionValue(s, "sw.toString()");
        return s;
    }
}

