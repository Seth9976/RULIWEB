package kotlin.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.Charsets;

@Metadata(d1 = {"\u0000z\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u001A\u0012\u0010\u0000\u001A\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001A\u00020\u0004\u001A\u001C\u0010\u0005\u001A\u00020\u0001*\u00020\u00022\u0006\u0010\u0006\u001A\u00020\u00072\b\b\u0002\u0010\b\u001A\u00020\t\u001A!\u0010\n\u001A\u00020\u000B*\u00020\u00022\b\b\u0002\u0010\b\u001A\u00020\t2\b\b\u0002\u0010\f\u001A\u00020\rH\u0087\b\u001A!\u0010\u000E\u001A\u00020\u000F*\u00020\u00022\b\b\u0002\u0010\b\u001A\u00020\t2\b\b\u0002\u0010\f\u001A\u00020\rH\u0087\b\u001AB\u0010\u0010\u001A\u00020\u0001*\u00020\u000226\u0010\u0011\u001A2\u0012\u0013\u0012\u00110\u0004\u00A2\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0015\u0012\u0013\u0012\u00110\r\u00A2\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0016\u0012\u0004\u0012\u00020\u00010\u0012\u001AJ\u0010\u0010\u001A\u00020\u0001*\u00020\u00022\u0006\u0010\u0017\u001A\u00020\r26\u0010\u0011\u001A2\u0012\u0013\u0012\u00110\u0004\u00A2\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0015\u0012\u0013\u0012\u00110\r\u00A2\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0016\u0012\u0004\u0012\u00020\u00010\u0012\u001A7\u0010\u0018\u001A\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\b\u001A\u00020\t2!\u0010\u0011\u001A\u001D\u0012\u0013\u0012\u00110\u0007\u00A2\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u001A\u0012\u0004\u0012\u00020\u00010\u0019\u001A\r\u0010\u001B\u001A\u00020\u001C*\u00020\u0002H\u0087\b\u001A\r\u0010\u001D\u001A\u00020\u001E*\u00020\u0002H\u0087\b\u001A\u0017\u0010\u001F\u001A\u00020 *\u00020\u00022\b\b\u0002\u0010\b\u001A\u00020\tH\u0087\b\u001A\n\u0010!\u001A\u00020\u0004*\u00020\u0002\u001A\u001A\u0010\"\u001A\b\u0012\u0004\u0012\u00020\u00070#*\u00020\u00022\b\b\u0002\u0010\b\u001A\u00020\t\u001A\u0014\u0010$\u001A\u00020\u0007*\u00020\u00022\b\b\u0002\u0010\b\u001A\u00020\t\u001A\u0017\u0010%\u001A\u00020&*\u00020\u00022\b\b\u0002\u0010\b\u001A\u00020\tH\u0087\b\u001A?\u0010\'\u001A\u0002H(\"\u0004\b\u0000\u0010(*\u00020\u00022\b\b\u0002\u0010\b\u001A\u00020\t2\u0018\u0010)\u001A\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070*\u0012\u0004\u0012\u0002H(0\u0019H\u0086\b\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010+\u001A\u0012\u0010,\u001A\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001A\u00020\u0004\u001A\u001C\u0010-\u001A\u00020\u0001*\u00020\u00022\u0006\u0010\u0006\u001A\u00020\u00072\b\b\u0002\u0010\b\u001A\u00020\t\u001A\u0017\u0010.\u001A\u00020/*\u00020\u00022\b\b\u0002\u0010\b\u001A\u00020\tH\u0087\b\u0082\u0002\u0007\n\u0005\b\u009920\u0001\u00A8\u00060"}, d2 = {"appendBytes", "", "Ljava/io/File;", "array", "", "appendText", "text", "", "charset", "Ljava/nio/charset/Charset;", "bufferedReader", "Ljava/io/BufferedReader;", "bufferSize", "", "bufferedWriter", "Ljava/io/BufferedWriter;", "forEachBlock", "action", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "buffer", "bytesRead", "blockSize", "forEachLine", "Lkotlin/Function1;", "line", "inputStream", "Ljava/io/FileInputStream;", "outputStream", "Ljava/io/FileOutputStream;", "printWriter", "Ljava/io/PrintWriter;", "readBytes", "readLines", "", "readText", "reader", "Ljava/io/InputStreamReader;", "useLines", "T", "block", "Lkotlin/sequences/Sequence;", "(Ljava/io/File;Ljava/nio/charset/Charset;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "writeBytes", "writeText", "writer", "Ljava/io/OutputStreamWriter;", "kotlin-stdlib"}, k = 5, mv = {1, 9, 0}, xi = 49, xs = "kotlin/io/FilesKt")
class FilesKt__FileReadWriteKt extends FilesKt__FilePathComponentsKt {
    public static final void appendBytes(File file0, byte[] arr_b) {
        Intrinsics.checkNotNullParameter(file0, "<this>");
        Intrinsics.checkNotNullParameter(arr_b, "array");
        Closeable closeable0 = new FileOutputStream(file0, true);
        try {
            ((FileOutputStream)closeable0).write(arr_b);
        }
        catch(Throwable throwable0) {
            CloseableKt.closeFinally(closeable0, throwable0);
            throw throwable0;
        }
        CloseableKt.closeFinally(closeable0, null);
    }

    public static final void appendText(File file0, String s, Charset charset0) {
        Intrinsics.checkNotNullParameter(file0, "<this>");
        Intrinsics.checkNotNullParameter(s, "text");
        Intrinsics.checkNotNullParameter(charset0, "charset");
        byte[] arr_b = s.getBytes(charset0);
        Intrinsics.checkNotNullExpressionValue(arr_b, "this as java.lang.String).getBytes(charset)");
        FilesKt.appendBytes(file0, arr_b);
    }

    public static void appendText$default(File file0, String s, Charset charset0, int v, Object object0) {
        if((v & 2) != 0) {
            charset0 = Charsets.UTF_8;
        }
        FilesKt.appendText(file0, s, charset0);
    }

    private static final BufferedReader bufferedReader(File file0, Charset charset0, int v) {
        Intrinsics.checkNotNullParameter(file0, "<this>");
        Intrinsics.checkNotNullParameter(charset0, "charset");
        InputStreamReader inputStreamReader0 = new InputStreamReader(new FileInputStream(file0), charset0);
        return inputStreamReader0 instanceof BufferedReader ? ((BufferedReader)inputStreamReader0) : new BufferedReader(inputStreamReader0, v);
    }

    static BufferedReader bufferedReader$default(File file0, Charset charset0, int v, int v1, Object object0) {
        if((v1 & 1) != 0) {
            charset0 = Charsets.UTF_8;
        }
        if((v1 & 2) != 0) {
            v = 0x2000;
        }
        Intrinsics.checkNotNullParameter(file0, "<this>");
        Intrinsics.checkNotNullParameter(charset0, "charset");
        InputStreamReader inputStreamReader0 = new InputStreamReader(new FileInputStream(file0), charset0);
        return inputStreamReader0 instanceof BufferedReader ? ((BufferedReader)inputStreamReader0) : new BufferedReader(inputStreamReader0, v);
    }

    private static final BufferedWriter bufferedWriter(File file0, Charset charset0, int v) {
        Intrinsics.checkNotNullParameter(file0, "<this>");
        Intrinsics.checkNotNullParameter(charset0, "charset");
        OutputStreamWriter outputStreamWriter0 = new OutputStreamWriter(new FileOutputStream(file0), charset0);
        return outputStreamWriter0 instanceof BufferedWriter ? ((BufferedWriter)outputStreamWriter0) : new BufferedWriter(outputStreamWriter0, v);
    }

    static BufferedWriter bufferedWriter$default(File file0, Charset charset0, int v, int v1, Object object0) {
        if((v1 & 1) != 0) {
            charset0 = Charsets.UTF_8;
        }
        if((v1 & 2) != 0) {
            v = 0x2000;
        }
        Intrinsics.checkNotNullParameter(file0, "<this>");
        Intrinsics.checkNotNullParameter(charset0, "charset");
        OutputStreamWriter outputStreamWriter0 = new OutputStreamWriter(new FileOutputStream(file0), charset0);
        return outputStreamWriter0 instanceof BufferedWriter ? ((BufferedWriter)outputStreamWriter0) : new BufferedWriter(outputStreamWriter0, v);
    }

    public static final void forEachBlock(File file0, int v, Function2 function20) {
        Intrinsics.checkNotNullParameter(file0, "<this>");
        Intrinsics.checkNotNullParameter(function20, "action");
        byte[] arr_b = new byte[RangesKt.coerceAtLeast(v, 0x200)];
        Closeable closeable0 = new FileInputStream(file0);
        try {
            int v1;
            while((v1 = ((FileInputStream)closeable0).read(arr_b)) > 0) {
                function20.invoke(arr_b, v1);
            }
        }
        catch(Throwable throwable0) {
            CloseableKt.closeFinally(closeable0, throwable0);
            throw throwable0;
        }
        CloseableKt.closeFinally(closeable0, null);
    }

    public static final void forEachBlock(File file0, Function2 function20) {
        Intrinsics.checkNotNullParameter(file0, "<this>");
        Intrinsics.checkNotNullParameter(function20, "action");
        FilesKt.forEachBlock(file0, 0x1000, function20);
    }

    public static final void forEachLine(File file0, Charset charset0, Function1 function10) {
        Intrinsics.checkNotNullParameter(file0, "<this>");
        Intrinsics.checkNotNullParameter(charset0, "charset");
        Intrinsics.checkNotNullParameter(function10, "action");
        TextStreamsKt.forEachLine(new BufferedReader(new InputStreamReader(new FileInputStream(file0), charset0)), function10);
    }

    public static void forEachLine$default(File file0, Charset charset0, Function1 function10, int v, Object object0) {
        if((v & 1) != 0) {
            charset0 = Charsets.UTF_8;
        }
        FilesKt.forEachLine(file0, charset0, function10);
    }

    private static final FileInputStream inputStream(File file0) {
        Intrinsics.checkNotNullParameter(file0, "<this>");
        return new FileInputStream(file0);
    }

    private static final FileOutputStream outputStream(File file0) {
        Intrinsics.checkNotNullParameter(file0, "<this>");
        return new FileOutputStream(file0);
    }

    private static final PrintWriter printWriter(File file0, Charset charset0) {
        Intrinsics.checkNotNullParameter(file0, "<this>");
        Intrinsics.checkNotNullParameter(charset0, "charset");
        OutputStreamWriter outputStreamWriter0 = new OutputStreamWriter(new FileOutputStream(file0), charset0);
        return outputStreamWriter0 instanceof BufferedWriter ? new PrintWriter(((BufferedWriter)outputStreamWriter0)) : new PrintWriter(new BufferedWriter(outputStreamWriter0, 0x2000));
    }

    static PrintWriter printWriter$default(File file0, Charset charset0, int v, Object object0) {
        if((v & 1) != 0) {
            charset0 = Charsets.UTF_8;
        }
        Intrinsics.checkNotNullParameter(file0, "<this>");
        Intrinsics.checkNotNullParameter(charset0, "charset");
        OutputStreamWriter outputStreamWriter0 = new OutputStreamWriter(new FileOutputStream(file0), charset0);
        return outputStreamWriter0 instanceof BufferedWriter ? new PrintWriter(((BufferedWriter)outputStreamWriter0)) : new PrintWriter(new BufferedWriter(outputStreamWriter0, 0x2000));
    }

    public static final byte[] readBytes(File file0) {
        byte[] arr_b;
        Intrinsics.checkNotNullParameter(file0, "<this>");
        Closeable closeable0 = new FileInputStream(file0);
        try {
            long v = file0.length();
            if(v > 0x7FFFFFFFL) {
                throw new OutOfMemoryError("File " + file0 + " is too big (" + v + " bytes) to fit in memory.");
            }
            arr_b = new byte[((int)v)];
            int v1 = (int)v;
            int v2;
            for(v2 = 0; v1 > 0; v2 += v3) {
                int v3 = ((FileInputStream)closeable0).read(arr_b, v2, v1);
                if(v3 < 0) {
                    break;
                }
                v1 -= v3;
            }
            if(v1 > 0) {
                arr_b = Arrays.copyOf(arr_b, v2);
                Intrinsics.checkNotNullExpressionValue(arr_b, "copyOf(this, newSize)");
            }
            else {
                int v4 = ((FileInputStream)closeable0).read();
                if(v4 != -1) {
                    ExposingBufferByteArrayOutputStream exposingBufferByteArrayOutputStream0 = new ExposingBufferByteArrayOutputStream(0x2001);
                    exposingBufferByteArrayOutputStream0.write(v4);
                    ByteStreamsKt.copyTo$default(((FileInputStream)closeable0), exposingBufferByteArrayOutputStream0, 0, 2, null);
                    int v5 = exposingBufferByteArrayOutputStream0.size() + ((int)v);
                    if(v5 < 0) {
                        throw new OutOfMemoryError("File " + file0 + " is too big to fit in memory.");
                    }
                    byte[] arr_b1 = exposingBufferByteArrayOutputStream0.getBuffer();
                    byte[] arr_b2 = Arrays.copyOf(arr_b, v5);
                    Intrinsics.checkNotNullExpressionValue(arr_b2, "copyOf(this, newSize)");
                    arr_b = ArraysKt.copyInto(arr_b1, arr_b2, ((int)v), 0, exposingBufferByteArrayOutputStream0.size());
                }
            }
        }
        catch(Throwable throwable0) {
            CloseableKt.closeFinally(closeable0, throwable0);
            throw throwable0;
        }
        CloseableKt.closeFinally(closeable0, null);
        return arr_b;
    }

    public static final List readLines(File file0, Charset charset0) {
        Intrinsics.checkNotNullParameter(file0, "<this>");
        Intrinsics.checkNotNullParameter(charset0, "charset");
        ArrayList arrayList0 = new ArrayList();
        FilesKt.forEachLine(file0, charset0, new Function1(arrayList0) {
            final ArrayList $result;

            {
                this.$result = arrayList0;
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                this.invoke(((String)object0));
                return Unit.INSTANCE;
            }

            public final void invoke(String s) {
                Intrinsics.checkNotNullParameter(s, "it");
                this.$result.add(s);
            }
        });
        return arrayList0;
    }

    public static List readLines$default(File file0, Charset charset0, int v, Object object0) {
        if((v & 1) != 0) {
            charset0 = Charsets.UTF_8;
        }
        return FilesKt.readLines(file0, charset0);
    }

    public static final String readText(File file0, Charset charset0) {
        String s;
        Intrinsics.checkNotNullParameter(file0, "<this>");
        Intrinsics.checkNotNullParameter(charset0, "charset");
        Closeable closeable0 = new InputStreamReader(new FileInputStream(file0), charset0);
        try {
            s = TextStreamsKt.readText(((InputStreamReader)closeable0));
        }
        catch(Throwable throwable0) {
            CloseableKt.closeFinally(closeable0, throwable0);
            throw throwable0;
        }
        CloseableKt.closeFinally(closeable0, null);
        return s;
    }

    public static String readText$default(File file0, Charset charset0, int v, Object object0) {
        if((v & 1) != 0) {
            charset0 = Charsets.UTF_8;
        }
        return FilesKt.readText(file0, charset0);
    }

    private static final InputStreamReader reader(File file0, Charset charset0) {
        Intrinsics.checkNotNullParameter(file0, "<this>");
        Intrinsics.checkNotNullParameter(charset0, "charset");
        return new InputStreamReader(new FileInputStream(file0), charset0);
    }

    static InputStreamReader reader$default(File file0, Charset charset0, int v, Object object0) {
        if((v & 1) != 0) {
            charset0 = Charsets.UTF_8;
        }
        Intrinsics.checkNotNullParameter(file0, "<this>");
        Intrinsics.checkNotNullParameter(charset0, "charset");
        return new InputStreamReader(new FileInputStream(file0), charset0);
    }

    public static final Object useLines(File file0, Charset charset0, Function1 function10) {
        Object object0;
        Intrinsics.checkNotNullParameter(file0, "<this>");
        Intrinsics.checkNotNullParameter(charset0, "charset");
        Intrinsics.checkNotNullParameter(function10, "block");
        InputStreamReader inputStreamReader0 = new InputStreamReader(new FileInputStream(file0), charset0);
        BufferedReader bufferedReader0 = inputStreamReader0 instanceof BufferedReader ? ((BufferedReader)inputStreamReader0) : new BufferedReader(inputStreamReader0, 0x2000);
        try {
            object0 = function10.invoke(TextStreamsKt.lineSequence(bufferedReader0));
        }
        catch(Throwable throwable0) {
            CloseableKt.closeFinally(bufferedReader0, throwable0);
            throw throwable0;
        }
        CloseableKt.closeFinally(bufferedReader0, null);
        return object0;
    }

    public static Object useLines$default(File file0, Charset charset0, Function1 function10, int v, Object object0) {
        Object object1;
        if((v & 1) != 0) {
            charset0 = Charsets.UTF_8;
        }
        InputStreamReader inputStreamReader0 = new InputStreamReader(new FileInputStream(file0), charset0);
        BufferedReader bufferedReader0 = inputStreamReader0 instanceof BufferedReader ? ((BufferedReader)inputStreamReader0) : new BufferedReader(inputStreamReader0, 0x2000);
        try {
            object1 = function10.invoke(TextStreamsKt.lineSequence(bufferedReader0));
        }
        catch(Throwable throwable0) {
            CloseableKt.closeFinally(bufferedReader0, throwable0);
            throw throwable0;
        }
        CloseableKt.closeFinally(bufferedReader0, null);
        return object1;
    }

    public static final void writeBytes(File file0, byte[] arr_b) {
        Intrinsics.checkNotNullParameter(file0, "<this>");
        Intrinsics.checkNotNullParameter(arr_b, "array");
        Closeable closeable0 = new FileOutputStream(file0);
        try {
            ((FileOutputStream)closeable0).write(arr_b);
        }
        catch(Throwable throwable0) {
            CloseableKt.closeFinally(closeable0, throwable0);
            throw throwable0;
        }
        CloseableKt.closeFinally(closeable0, null);
    }

    public static final void writeText(File file0, String s, Charset charset0) {
        Intrinsics.checkNotNullParameter(file0, "<this>");
        Intrinsics.checkNotNullParameter(s, "text");
        Intrinsics.checkNotNullParameter(charset0, "charset");
        byte[] arr_b = s.getBytes(charset0);
        Intrinsics.checkNotNullExpressionValue(arr_b, "this as java.lang.String).getBytes(charset)");
        FilesKt.writeBytes(file0, arr_b);
    }

    public static void writeText$default(File file0, String s, Charset charset0, int v, Object object0) {
        if((v & 2) != 0) {
            charset0 = Charsets.UTF_8;
        }
        FilesKt.writeText(file0, s, charset0);
    }

    private static final OutputStreamWriter writer(File file0, Charset charset0) {
        Intrinsics.checkNotNullParameter(file0, "<this>");
        Intrinsics.checkNotNullParameter(charset0, "charset");
        return new OutputStreamWriter(new FileOutputStream(file0), charset0);
    }

    static OutputStreamWriter writer$default(File file0, Charset charset0, int v, Object object0) {
        if((v & 1) != 0) {
            charset0 = Charsets.UTF_8;
        }
        Intrinsics.checkNotNullParameter(file0, "<this>");
        Intrinsics.checkNotNullParameter(charset0, "charset");
        return new OutputStreamWriter(new FileOutputStream(file0), charset0);
    }
}

