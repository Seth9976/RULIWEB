package kotlin.io.path;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.io.CloseableKt;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import kotlin.text.Charsets;

@Metadata(d1 = {"\u0000\u0082\u0001\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\u001C\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000E\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0002\u001A\u0015\u0010\u0000\u001A\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001A\u00020\u0004H\u0087\b\u001A%\u0010\u0005\u001A\u00020\u0002*\u00020\u00022\f\u0010\u0006\u001A\b\u0012\u0004\u0012\u00020\b0\u00072\b\b\u0002\u0010\t\u001A\u00020\nH\u0087\b\u001A%\u0010\u0005\u001A\u00020\u0002*\u00020\u00022\f\u0010\u0006\u001A\b\u0012\u0004\u0012\u00020\b0\u000B2\b\b\u0002\u0010\t\u001A\u00020\nH\u0087\b\u001A\u001E\u0010\f\u001A\u00020\u0001*\u00020\u00022\u0006\u0010\r\u001A\u00020\b2\b\b\u0002\u0010\t\u001A\u00020\nH\u0007\u001A:\u0010\u000E\u001A\u00020\u000F*\u00020\u00022\b\b\u0002\u0010\t\u001A\u00020\n2\b\b\u0002\u0010\u0010\u001A\u00020\u00112\u0012\u0010\u0012\u001A\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u0013\"\u00020\u0014H\u0087\b\u00A2\u0006\u0002\u0010\u0015\u001A:\u0010\u0016\u001A\u00020\u0017*\u00020\u00022\b\b\u0002\u0010\t\u001A\u00020\n2\b\b\u0002\u0010\u0010\u001A\u00020\u00112\u0012\u0010\u0012\u001A\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u0013\"\u00020\u0014H\u0087\b\u00A2\u0006\u0002\u0010\u0018\u001A=\u0010\u0019\u001A\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\t\u001A\u00020\n2!\u0010\u001A\u001A\u001D\u0012\u0013\u0012\u00110\u001C\u00A2\u0006\f\b\u001D\u0012\b\b\u001E\u0012\u0004\b\b(\u001F\u0012\u0004\u0012\u00020\u00010\u001BH\u0087\b\u00F8\u0001\u0000\u001A&\u0010 \u001A\u00020!*\u00020\u00022\u0012\u0010\u0012\u001A\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u0013\"\u00020\u0014H\u0087\b\u00A2\u0006\u0002\u0010\"\u001A&\u0010#\u001A\u00020$*\u00020\u00022\u0012\u0010\u0012\u001A\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u0013\"\u00020\u0014H\u0087\b\u00A2\u0006\u0002\u0010%\u001A\r\u0010&\u001A\u00020\u0004*\u00020\u0002H\u0087\b\u001A\u001D\u0010\'\u001A\b\u0012\u0004\u0012\u00020\u001C0(*\u00020\u00022\b\b\u0002\u0010\t\u001A\u00020\nH\u0087\b\u001A\u0016\u0010)\u001A\u00020\u001C*\u00020\u00022\b\b\u0002\u0010\t\u001A\u00020\nH\u0007\u001A0\u0010*\u001A\u00020+*\u00020\u00022\b\b\u0002\u0010\t\u001A\u00020\n2\u0012\u0010\u0012\u001A\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u0013\"\u00020\u0014H\u0087\b\u00A2\u0006\u0002\u0010,\u001A?\u0010-\u001A\u0002H.\"\u0004\b\u0000\u0010.*\u00020\u00022\b\b\u0002\u0010\t\u001A\u00020\n2\u0018\u0010/\u001A\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001C0\u000B\u0012\u0004\u0012\u0002H.0\u001BH\u0087\b\u00F8\u0001\u0000\u00A2\u0006\u0002\u00100\u001A.\u00101\u001A\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001A\u00020\u00042\u0012\u0010\u0012\u001A\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u0013\"\u00020\u0014H\u0087\b\u00A2\u0006\u0002\u00102\u001A>\u00103\u001A\u00020\u0002*\u00020\u00022\f\u0010\u0006\u001A\b\u0012\u0004\u0012\u00020\b0\u00072\b\b\u0002\u0010\t\u001A\u00020\n2\u0012\u0010\u0012\u001A\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u0013\"\u00020\u0014H\u0087\b\u00A2\u0006\u0002\u00104\u001A>\u00103\u001A\u00020\u0002*\u00020\u00022\f\u0010\u0006\u001A\b\u0012\u0004\u0012\u00020\b0\u000B2\b\b\u0002\u0010\t\u001A\u00020\n2\u0012\u0010\u0012\u001A\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u0013\"\u00020\u0014H\u0087\b\u00A2\u0006\u0002\u00105\u001A7\u00106\u001A\u00020\u0001*\u00020\u00022\u0006\u0010\r\u001A\u00020\b2\b\b\u0002\u0010\t\u001A\u00020\n2\u0012\u0010\u0012\u001A\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u0013\"\u00020\u0014H\u0007\u00A2\u0006\u0002\u00107\u001A0\u00108\u001A\u000209*\u00020\u00022\b\b\u0002\u0010\t\u001A\u00020\n2\u0012\u0010\u0012\u001A\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u0013\"\u00020\u0014H\u0087\b\u00A2\u0006\u0002\u0010:\u0082\u0002\u0007\n\u0005\b\u009920\u0001\u00A8\u0006;"}, d2 = {"appendBytes", "", "Ljava/nio/file/Path;", "array", "", "appendLines", "lines", "", "", "charset", "Ljava/nio/charset/Charset;", "Lkotlin/sequences/Sequence;", "appendText", "text", "bufferedReader", "Ljava/io/BufferedReader;", "bufferSize", "", "options", "", "Ljava/nio/file/OpenOption;", "(Ljava/nio/file/Path;Ljava/nio/charset/Charset;I[Ljava/nio/file/OpenOption;)Ljava/io/BufferedReader;", "bufferedWriter", "Ljava/io/BufferedWriter;", "(Ljava/nio/file/Path;Ljava/nio/charset/Charset;I[Ljava/nio/file/OpenOption;)Ljava/io/BufferedWriter;", "forEachLine", "action", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "line", "inputStream", "Ljava/io/InputStream;", "(Ljava/nio/file/Path;[Ljava/nio/file/OpenOption;)Ljava/io/InputStream;", "outputStream", "Ljava/io/OutputStream;", "(Ljava/nio/file/Path;[Ljava/nio/file/OpenOption;)Ljava/io/OutputStream;", "readBytes", "readLines", "", "readText", "reader", "Ljava/io/InputStreamReader;", "(Ljava/nio/file/Path;Ljava/nio/charset/Charset;[Ljava/nio/file/OpenOption;)Ljava/io/InputStreamReader;", "useLines", "T", "block", "(Ljava/nio/file/Path;Ljava/nio/charset/Charset;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "writeBytes", "(Ljava/nio/file/Path;[B[Ljava/nio/file/OpenOption;)V", "writeLines", "(Ljava/nio/file/Path;Ljava/lang/Iterable;Ljava/nio/charset/Charset;[Ljava/nio/file/OpenOption;)Ljava/nio/file/Path;", "(Ljava/nio/file/Path;Lkotlin/sequences/Sequence;Ljava/nio/charset/Charset;[Ljava/nio/file/OpenOption;)Ljava/nio/file/Path;", "writeText", "(Ljava/nio/file/Path;Ljava/lang/CharSequence;Ljava/nio/charset/Charset;[Ljava/nio/file/OpenOption;)V", "writer", "Ljava/io/OutputStreamWriter;", "(Ljava/nio/file/Path;Ljava/nio/charset/Charset;[Ljava/nio/file/OpenOption;)Ljava/io/OutputStreamWriter;", "kotlin-stdlib-jdk7"}, k = 5, mv = {1, 9, 0}, xi = 49, xs = "kotlin/io/path/PathsKt")
class PathsKt__PathReadWriteKt {
    private static final void appendBytes(Path path0, byte[] arr_b) throws IOException {
        Intrinsics.checkNotNullParameter(path0, "<this>");
        Intrinsics.checkNotNullParameter(arr_b, "array");
        PathTreeWalk..ExternalSyntheticApiModelOutline0.m(path0, arr_b, new OpenOption[]{PathTreeWalk..ExternalSyntheticApiModelOutline0.m()});
    }

    private static final Path appendLines(Path path0, Iterable iterable0, Charset charset0) throws IOException {
        Intrinsics.checkNotNullParameter(path0, "<this>");
        Intrinsics.checkNotNullParameter(iterable0, "lines");
        Intrinsics.checkNotNullParameter(charset0, "charset");
        Path path1 = Files.write(path0, iterable0, charset0, new OpenOption[]{PathTreeWalk..ExternalSyntheticApiModelOutline0.m()});
        Intrinsics.checkNotNullExpressionValue(path1, "write(this, lines, chars…tandardOpenOption.APPEND)");
        return path1;
    }

    private static final Path appendLines(Path path0, Sequence sequence0, Charset charset0) throws IOException {
        Intrinsics.checkNotNullParameter(path0, "<this>");
        Intrinsics.checkNotNullParameter(sequence0, "lines");
        Intrinsics.checkNotNullParameter(charset0, "charset");
        Path path1 = Files.write(path0, SequencesKt.asIterable(sequence0), charset0, new OpenOption[]{PathTreeWalk..ExternalSyntheticApiModelOutline0.m()});
        Intrinsics.checkNotNullExpressionValue(path1, "write(this, lines.asIter…tandardOpenOption.APPEND)");
        return path1;
    }

    static Path appendLines$default(Path path0, Iterable iterable0, Charset charset0, int v, Object object0) throws IOException {
        if((v & 2) != 0) {
            charset0 = Charsets.UTF_8;
        }
        Intrinsics.checkNotNullParameter(path0, "<this>");
        Intrinsics.checkNotNullParameter(iterable0, "lines");
        Intrinsics.checkNotNullParameter(charset0, "charset");
        Path path1 = Files.write(path0, iterable0, charset0, new OpenOption[]{PathTreeWalk..ExternalSyntheticApiModelOutline0.m()});
        Intrinsics.checkNotNullExpressionValue(path1, "write(this, lines, chars…tandardOpenOption.APPEND)");
        return path1;
    }

    static Path appendLines$default(Path path0, Sequence sequence0, Charset charset0, int v, Object object0) throws IOException {
        if((v & 2) != 0) {
            charset0 = Charsets.UTF_8;
        }
        Intrinsics.checkNotNullParameter(path0, "<this>");
        Intrinsics.checkNotNullParameter(sequence0, "lines");
        Intrinsics.checkNotNullParameter(charset0, "charset");
        Path path1 = Files.write(path0, SequencesKt.asIterable(sequence0), charset0, new OpenOption[]{PathTreeWalk..ExternalSyntheticApiModelOutline0.m()});
        Intrinsics.checkNotNullExpressionValue(path1, "write(this, lines.asIter…tandardOpenOption.APPEND)");
        return path1;
    }

    public static final void appendText(Path path0, CharSequence charSequence0, Charset charset0) throws IOException {
        Intrinsics.checkNotNullParameter(path0, "<this>");
        Intrinsics.checkNotNullParameter(charSequence0, "text");
        Intrinsics.checkNotNullParameter(charset0, "charset");
        OutputStream outputStream0 = Files.newOutputStream(path0, new OpenOption[]{PathTreeWalk..ExternalSyntheticApiModelOutline0.m()});
        Intrinsics.checkNotNullExpressionValue(outputStream0, "newOutputStream(this, StandardOpenOption.APPEND)");
        Closeable closeable0 = new OutputStreamWriter(outputStream0, charset0);
        try {
            ((OutputStreamWriter)closeable0).append(charSequence0);
        }
        catch(Throwable throwable0) {
            CloseableKt.closeFinally(closeable0, throwable0);
            throw throwable0;
        }
        CloseableKt.closeFinally(closeable0, null);
    }

    public static void appendText$default(Path path0, CharSequence charSequence0, Charset charset0, int v, Object object0) throws IOException {
        if((v & 2) != 0) {
            charset0 = Charsets.UTF_8;
        }
        PathsKt.appendText(path0, charSequence0, charset0);
    }

    private static final BufferedReader bufferedReader(Path path0, Charset charset0, int v, OpenOption[] arr_openOption) throws IOException {
        Intrinsics.checkNotNullParameter(path0, "<this>");
        Intrinsics.checkNotNullParameter(charset0, "charset");
        Intrinsics.checkNotNullParameter(arr_openOption, "options");
        return new BufferedReader(new InputStreamReader(Files.newInputStream(path0, ((OpenOption[])Arrays.copyOf(arr_openOption, arr_openOption.length))), charset0), v);
    }

    static BufferedReader bufferedReader$default(Path path0, Charset charset0, int v, OpenOption[] arr_openOption, int v1, Object object0) throws IOException {
        if((v1 & 1) != 0) {
            charset0 = Charsets.UTF_8;
        }
        if((v1 & 2) != 0) {
            v = 0x2000;
        }
        Intrinsics.checkNotNullParameter(path0, "<this>");
        Intrinsics.checkNotNullParameter(charset0, "charset");
        Intrinsics.checkNotNullParameter(arr_openOption, "options");
        return new BufferedReader(new InputStreamReader(Files.newInputStream(path0, ((OpenOption[])Arrays.copyOf(arr_openOption, arr_openOption.length))), charset0), v);
    }

    private static final BufferedWriter bufferedWriter(Path path0, Charset charset0, int v, OpenOption[] arr_openOption) throws IOException {
        Intrinsics.checkNotNullParameter(path0, "<this>");
        Intrinsics.checkNotNullParameter(charset0, "charset");
        Intrinsics.checkNotNullParameter(arr_openOption, "options");
        return new BufferedWriter(new OutputStreamWriter(Files.newOutputStream(path0, ((OpenOption[])Arrays.copyOf(arr_openOption, arr_openOption.length))), charset0), v);
    }

    static BufferedWriter bufferedWriter$default(Path path0, Charset charset0, int v, OpenOption[] arr_openOption, int v1, Object object0) throws IOException {
        if((v1 & 1) != 0) {
            charset0 = Charsets.UTF_8;
        }
        if((v1 & 2) != 0) {
            v = 0x2000;
        }
        Intrinsics.checkNotNullParameter(path0, "<this>");
        Intrinsics.checkNotNullParameter(charset0, "charset");
        Intrinsics.checkNotNullParameter(arr_openOption, "options");
        return new BufferedWriter(new OutputStreamWriter(Files.newOutputStream(path0, ((OpenOption[])Arrays.copyOf(arr_openOption, arr_openOption.length))), charset0), v);
    }

    private static final void forEachLine(Path path0, Charset charset0, Function1 function10) throws IOException {
        Intrinsics.checkNotNullParameter(path0, "<this>");
        Intrinsics.checkNotNullParameter(charset0, "charset");
        Intrinsics.checkNotNullParameter(function10, "action");
        BufferedReader bufferedReader0 = Files.newBufferedReader(path0, charset0);
        Intrinsics.checkNotNullExpressionValue(bufferedReader0, "newBufferedReader(this, charset)");
        try {
            for(Object object0: TextStreamsKt.lineSequence(bufferedReader0)) {
                function10.invoke(object0);
            }
        }
        catch(Throwable throwable0) {
            CloseableKt.closeFinally(bufferedReader0, throwable0);
            throw throwable0;
        }
        CloseableKt.closeFinally(bufferedReader0, null);
    }

    static void forEachLine$default(Path path0, Charset charset0, Function1 function10, int v, Object object0) throws IOException {
        if((v & 1) != 0) {
            charset0 = Charsets.UTF_8;
        }
        Intrinsics.checkNotNullParameter(path0, "<this>");
        Intrinsics.checkNotNullParameter(charset0, "charset");
        Intrinsics.checkNotNullParameter(function10, "action");
        BufferedReader bufferedReader0 = Files.newBufferedReader(path0, charset0);
        Intrinsics.checkNotNullExpressionValue(bufferedReader0, "newBufferedReader(this, charset)");
        try {
            for(Object object1: TextStreamsKt.lineSequence(bufferedReader0)) {
                function10.invoke(object1);
            }
        }
        catch(Throwable throwable0) {
            CloseableKt.closeFinally(bufferedReader0, throwable0);
            throw throwable0;
        }
        CloseableKt.closeFinally(bufferedReader0, null);
    }

    private static final InputStream inputStream(Path path0, OpenOption[] arr_openOption) throws IOException {
        Intrinsics.checkNotNullParameter(path0, "<this>");
        Intrinsics.checkNotNullParameter(arr_openOption, "options");
        InputStream inputStream0 = Files.newInputStream(path0, ((OpenOption[])Arrays.copyOf(arr_openOption, arr_openOption.length)));
        Intrinsics.checkNotNullExpressionValue(inputStream0, "newInputStream(this, *options)");
        return inputStream0;
    }

    private static final OutputStream outputStream(Path path0, OpenOption[] arr_openOption) throws IOException {
        Intrinsics.checkNotNullParameter(path0, "<this>");
        Intrinsics.checkNotNullParameter(arr_openOption, "options");
        OutputStream outputStream0 = Files.newOutputStream(path0, ((OpenOption[])Arrays.copyOf(arr_openOption, arr_openOption.length)));
        Intrinsics.checkNotNullExpressionValue(outputStream0, "newOutputStream(this, *options)");
        return outputStream0;
    }

    private static final byte[] readBytes(Path path0) throws IOException {
        Intrinsics.checkNotNullParameter(path0, "<this>");
        byte[] arr_b = PathTreeWalk..ExternalSyntheticApiModelOutline0.m(path0);
        Intrinsics.checkNotNullExpressionValue(arr_b, "readAllBytes(this)");
        return arr_b;
    }

    private static final List readLines(Path path0, Charset charset0) throws IOException {
        Intrinsics.checkNotNullParameter(path0, "<this>");
        Intrinsics.checkNotNullParameter(charset0, "charset");
        List list0 = PathTreeWalk..ExternalSyntheticApiModelOutline0.m(path0, charset0);
        Intrinsics.checkNotNullExpressionValue(list0, "readAllLines(this, charset)");
        return list0;
    }

    static List readLines$default(Path path0, Charset charset0, int v, Object object0) throws IOException {
        if((v & 1) != 0) {
            charset0 = Charsets.UTF_8;
        }
        Intrinsics.checkNotNullParameter(path0, "<this>");
        Intrinsics.checkNotNullParameter(charset0, "charset");
        List list0 = PathTreeWalk..ExternalSyntheticApiModelOutline0.m(path0, charset0);
        Intrinsics.checkNotNullExpressionValue(list0, "readAllLines(this, charset)");
        return list0;
    }

    public static final String readText(Path path0, Charset charset0) throws IOException {
        String s;
        Intrinsics.checkNotNullParameter(path0, "<this>");
        Intrinsics.checkNotNullParameter(charset0, "charset");
        Closeable closeable0 = new InputStreamReader(Files.newInputStream(path0, ((OpenOption[])Arrays.copyOf(new OpenOption[0], 0))), charset0);
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

    public static String readText$default(Path path0, Charset charset0, int v, Object object0) throws IOException {
        if((v & 1) != 0) {
            charset0 = Charsets.UTF_8;
        }
        return PathsKt.readText(path0, charset0);
    }

    private static final InputStreamReader reader(Path path0, Charset charset0, OpenOption[] arr_openOption) throws IOException {
        Intrinsics.checkNotNullParameter(path0, "<this>");
        Intrinsics.checkNotNullParameter(charset0, "charset");
        Intrinsics.checkNotNullParameter(arr_openOption, "options");
        return new InputStreamReader(Files.newInputStream(path0, ((OpenOption[])Arrays.copyOf(arr_openOption, arr_openOption.length))), charset0);
    }

    static InputStreamReader reader$default(Path path0, Charset charset0, OpenOption[] arr_openOption, int v, Object object0) throws IOException {
        if((v & 1) != 0) {
            charset0 = Charsets.UTF_8;
        }
        Intrinsics.checkNotNullParameter(path0, "<this>");
        Intrinsics.checkNotNullParameter(charset0, "charset");
        Intrinsics.checkNotNullParameter(arr_openOption, "options");
        return new InputStreamReader(Files.newInputStream(path0, ((OpenOption[])Arrays.copyOf(arr_openOption, arr_openOption.length))), charset0);
    }

    private static final Object useLines(Path path0, Charset charset0, Function1 function10) throws IOException {
        Object object0;
        Intrinsics.checkNotNullParameter(path0, "<this>");
        Intrinsics.checkNotNullParameter(charset0, "charset");
        Intrinsics.checkNotNullParameter(function10, "block");
        Closeable closeable0 = Files.newBufferedReader(path0, charset0);
        try {
            Intrinsics.checkNotNullExpressionValue(((BufferedReader)closeable0), "it");
            object0 = function10.invoke(TextStreamsKt.lineSequence(((BufferedReader)closeable0)));
        }
        catch(Throwable throwable0) {
            CloseableKt.closeFinally(closeable0, throwable0);
            throw throwable0;
        }
        CloseableKt.closeFinally(closeable0, null);
        return object0;
    }

    static Object useLines$default(Path path0, Charset charset0, Function1 function10, int v, Object object0) throws IOException {
        Object object1;
        if((v & 1) != 0) {
            charset0 = Charsets.UTF_8;
        }
        Intrinsics.checkNotNullParameter(path0, "<this>");
        Intrinsics.checkNotNullParameter(charset0, "charset");
        Intrinsics.checkNotNullParameter(function10, "block");
        Closeable closeable0 = Files.newBufferedReader(path0, charset0);
        try {
            Intrinsics.checkNotNullExpressionValue(((BufferedReader)closeable0), "it");
            object1 = function10.invoke(TextStreamsKt.lineSequence(((BufferedReader)closeable0)));
        }
        catch(Throwable throwable0) {
            CloseableKt.closeFinally(closeable0, throwable0);
            throw throwable0;
        }
        CloseableKt.closeFinally(closeable0, null);
        return object1;
    }

    private static final void writeBytes(Path path0, byte[] arr_b, OpenOption[] arr_openOption) throws IOException {
        Intrinsics.checkNotNullParameter(path0, "<this>");
        Intrinsics.checkNotNullParameter(arr_b, "array");
        Intrinsics.checkNotNullParameter(arr_openOption, "options");
        Files.write(path0, arr_b, ((OpenOption[])Arrays.copyOf(arr_openOption, arr_openOption.length)));
    }

    private static final Path writeLines(Path path0, Iterable iterable0, Charset charset0, OpenOption[] arr_openOption) throws IOException {
        Intrinsics.checkNotNullParameter(path0, "<this>");
        Intrinsics.checkNotNullParameter(iterable0, "lines");
        Intrinsics.checkNotNullParameter(charset0, "charset");
        Intrinsics.checkNotNullParameter(arr_openOption, "options");
        Path path1 = Files.write(path0, iterable0, charset0, ((OpenOption[])Arrays.copyOf(arr_openOption, arr_openOption.length)));
        Intrinsics.checkNotNullExpressionValue(path1, "write(this, lines, charset, *options)");
        return path1;
    }

    private static final Path writeLines(Path path0, Sequence sequence0, Charset charset0, OpenOption[] arr_openOption) throws IOException {
        Intrinsics.checkNotNullParameter(path0, "<this>");
        Intrinsics.checkNotNullParameter(sequence0, "lines");
        Intrinsics.checkNotNullParameter(charset0, "charset");
        Intrinsics.checkNotNullParameter(arr_openOption, "options");
        Path path1 = Files.write(path0, SequencesKt.asIterable(sequence0), charset0, ((OpenOption[])Arrays.copyOf(arr_openOption, arr_openOption.length)));
        Intrinsics.checkNotNullExpressionValue(path1, "write(this, lines.asIterable(), charset, *options)");
        return path1;
    }

    static Path writeLines$default(Path path0, Iterable iterable0, Charset charset0, OpenOption[] arr_openOption, int v, Object object0) throws IOException {
        if((v & 2) != 0) {
            charset0 = Charsets.UTF_8;
        }
        Intrinsics.checkNotNullParameter(path0, "<this>");
        Intrinsics.checkNotNullParameter(iterable0, "lines");
        Intrinsics.checkNotNullParameter(charset0, "charset");
        Intrinsics.checkNotNullParameter(arr_openOption, "options");
        Path path1 = Files.write(path0, iterable0, charset0, ((OpenOption[])Arrays.copyOf(arr_openOption, arr_openOption.length)));
        Intrinsics.checkNotNullExpressionValue(path1, "write(this, lines, charset, *options)");
        return path1;
    }

    static Path writeLines$default(Path path0, Sequence sequence0, Charset charset0, OpenOption[] arr_openOption, int v, Object object0) throws IOException {
        if((v & 2) != 0) {
            charset0 = Charsets.UTF_8;
        }
        Intrinsics.checkNotNullParameter(path0, "<this>");
        Intrinsics.checkNotNullParameter(sequence0, "lines");
        Intrinsics.checkNotNullParameter(charset0, "charset");
        Intrinsics.checkNotNullParameter(arr_openOption, "options");
        Path path1 = Files.write(path0, SequencesKt.asIterable(sequence0), charset0, ((OpenOption[])Arrays.copyOf(arr_openOption, arr_openOption.length)));
        Intrinsics.checkNotNullExpressionValue(path1, "write(this, lines.asIterable(), charset, *options)");
        return path1;
    }

    public static final void writeText(Path path0, CharSequence charSequence0, Charset charset0, OpenOption[] arr_openOption) throws IOException {
        Intrinsics.checkNotNullParameter(path0, "<this>");
        Intrinsics.checkNotNullParameter(charSequence0, "text");
        Intrinsics.checkNotNullParameter(charset0, "charset");
        Intrinsics.checkNotNullParameter(arr_openOption, "options");
        OutputStream outputStream0 = Files.newOutputStream(path0, ((OpenOption[])Arrays.copyOf(arr_openOption, arr_openOption.length)));
        Intrinsics.checkNotNullExpressionValue(outputStream0, "newOutputStream(this, *options)");
        Closeable closeable0 = new OutputStreamWriter(outputStream0, charset0);
        try {
            ((OutputStreamWriter)closeable0).append(charSequence0);
        }
        catch(Throwable throwable0) {
            CloseableKt.closeFinally(closeable0, throwable0);
            throw throwable0;
        }
        CloseableKt.closeFinally(closeable0, null);
    }

    public static void writeText$default(Path path0, CharSequence charSequence0, Charset charset0, OpenOption[] arr_openOption, int v, Object object0) throws IOException {
        if((v & 2) != 0) {
            charset0 = Charsets.UTF_8;
        }
        PathsKt.writeText(path0, charSequence0, charset0, arr_openOption);
    }

    private static final OutputStreamWriter writer(Path path0, Charset charset0, OpenOption[] arr_openOption) throws IOException {
        Intrinsics.checkNotNullParameter(path0, "<this>");
        Intrinsics.checkNotNullParameter(charset0, "charset");
        Intrinsics.checkNotNullParameter(arr_openOption, "options");
        return new OutputStreamWriter(Files.newOutputStream(path0, ((OpenOption[])Arrays.copyOf(arr_openOption, arr_openOption.length))), charset0);
    }

    static OutputStreamWriter writer$default(Path path0, Charset charset0, OpenOption[] arr_openOption, int v, Object object0) throws IOException {
        if((v & 1) != 0) {
            charset0 = Charsets.UTF_8;
        }
        Intrinsics.checkNotNullParameter(path0, "<this>");
        Intrinsics.checkNotNullParameter(charset0, "charset");
        Intrinsics.checkNotNullParameter(arr_openOption, "options");
        return new OutputStreamWriter(Files.newOutputStream(path0, ((OpenOption[])Arrays.copyOf(arr_openOption, arr_openOption.length))), charset0);
    }
}

