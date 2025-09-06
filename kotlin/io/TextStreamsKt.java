package kotlin.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import kotlin.text.Charsets;

@Metadata(d1 = {"\u0000X\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001A\u0017\u0010\u0000\u001A\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001A\u00020\u0004H\u0087\b\u001A\u0017\u0010\u0000\u001A\u00020\u0005*\u00020\u00062\b\b\u0002\u0010\u0003\u001A\u00020\u0004H\u0087\b\u001A\u001C\u0010\u0007\u001A\u00020\b*\u00020\u00022\u0006\u0010\t\u001A\u00020\u00062\b\b\u0002\u0010\u0003\u001A\u00020\u0004\u001A\u001E\u0010\n\u001A\u00020\u000B*\u00020\u00022\u0012\u0010\f\u001A\u000E\u0012\u0004\u0012\u00020\u000E\u0012\u0004\u0012\u00020\u000B0\r\u001A\u0010\u0010\u000F\u001A\b\u0012\u0004\u0012\u00020\u000E0\u0010*\u00020\u0001\u001A\n\u0010\u0011\u001A\u00020\u0012*\u00020\u0013\u001A\u0010\u0010\u0014\u001A\b\u0012\u0004\u0012\u00020\u000E0\u0015*\u00020\u0002\u001A\n\u0010\u0016\u001A\u00020\u000E*\u00020\u0002\u001A\u0017\u0010\u0016\u001A\u00020\u000E*\u00020\u00132\b\b\u0002\u0010\u0017\u001A\u00020\u0018H\u0087\b\u001A\r\u0010\u0019\u001A\u00020\u001A*\u00020\u000EH\u0087\b\u001A5\u0010\u001B\u001A\u0002H\u001C\"\u0004\b\u0000\u0010\u001C*\u00020\u00022\u0018\u0010\u001D\u001A\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000E0\u0010\u0012\u0004\u0012\u0002H\u001C0\rH\u0086\bø\u0001\u0000¢\u0006\u0002\u0010\u001E\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u001F"}, d2 = {"buffered", "Ljava/io/BufferedReader;", "Ljava/io/Reader;", "bufferSize", "", "Ljava/io/BufferedWriter;", "Ljava/io/Writer;", "copyTo", "", "out", "forEachLine", "", "action", "Lkotlin/Function1;", "", "lineSequence", "Lkotlin/sequences/Sequence;", "readBytes", "", "Ljava/net/URL;", "readLines", "", "readText", "charset", "Ljava/nio/charset/Charset;", "reader", "Ljava/io/StringReader;", "useLines", "T", "block", "(Ljava/io/Reader;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "kotlin-stdlib"}, k = 2, mv = {1, 9, 0}, xi = 0x30)
public final class TextStreamsKt {
    private static final BufferedReader buffered(Reader reader0, int v) {
        Intrinsics.checkNotNullParameter(reader0, "<this>");
        return reader0 instanceof BufferedReader ? ((BufferedReader)reader0) : new BufferedReader(reader0, v);
    }

    private static final BufferedWriter buffered(Writer writer0, int v) {
        Intrinsics.checkNotNullParameter(writer0, "<this>");
        return writer0 instanceof BufferedWriter ? ((BufferedWriter)writer0) : new BufferedWriter(writer0, v);
    }

    static BufferedReader buffered$default(Reader reader0, int v, int v1, Object object0) {
        if((v1 & 1) != 0) {
            v = 0x2000;
        }
        Intrinsics.checkNotNullParameter(reader0, "<this>");
        return reader0 instanceof BufferedReader ? ((BufferedReader)reader0) : new BufferedReader(reader0, v);
    }

    static BufferedWriter buffered$default(Writer writer0, int v, int v1, Object object0) {
        if((v1 & 1) != 0) {
            v = 0x2000;
        }
        Intrinsics.checkNotNullParameter(writer0, "<this>");
        return writer0 instanceof BufferedWriter ? ((BufferedWriter)writer0) : new BufferedWriter(writer0, v);
    }

    public static final long copyTo(Reader reader0, Writer writer0, int v) {
        Intrinsics.checkNotNullParameter(reader0, "<this>");
        Intrinsics.checkNotNullParameter(writer0, "out");
        char[] arr_c = new char[v];
        int v1 = reader0.read(arr_c);
        long v2 = 0L;
        while(v1 >= 0) {
            writer0.write(arr_c, 0, v1);
            v2 += (long)v1;
            v1 = reader0.read(arr_c);
        }
        return v2;
    }

    public static long copyTo$default(Reader reader0, Writer writer0, int v, int v1, Object object0) {
        if((v1 & 2) != 0) {
            v = 0x2000;
        }
        return TextStreamsKt.copyTo(reader0, writer0, v);
    }

    public static final void forEachLine(Reader reader0, Function1 function10) {
        Intrinsics.checkNotNullParameter(reader0, "<this>");
        Intrinsics.checkNotNullParameter(function10, "action");
        BufferedReader bufferedReader0 = reader0 instanceof BufferedReader ? ((BufferedReader)reader0) : new BufferedReader(reader0, 0x2000);
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

    public static final Sequence lineSequence(BufferedReader bufferedReader0) {
        Intrinsics.checkNotNullParameter(bufferedReader0, "<this>");
        return SequencesKt.constrainOnce(new LinesSequence(bufferedReader0));
    }

    public static final byte[] readBytes(URL uRL0) {
        byte[] arr_b;
        Intrinsics.checkNotNullParameter(uRL0, "<this>");
        Closeable closeable0 = uRL0.openStream();
        try {
            Intrinsics.checkNotNullExpressionValue(((InputStream)closeable0), "it");
            arr_b = ByteStreamsKt.readBytes(((InputStream)closeable0));
        }
        catch(Throwable throwable0) {
            CloseableKt.closeFinally(closeable0, throwable0);
            throw throwable0;
        }
        CloseableKt.closeFinally(closeable0, null);
        return arr_b;
    }

    public static final List readLines(Reader reader0) {
        Intrinsics.checkNotNullParameter(reader0, "<this>");
        ArrayList arrayList0 = new ArrayList();
        TextStreamsKt.forEachLine(reader0, new Function1(arrayList0) {
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

    public static final String readText(Reader reader0) {
        Intrinsics.checkNotNullParameter(reader0, "<this>");
        StringWriter stringWriter0 = new StringWriter();
        TextStreamsKt.copyTo$default(reader0, stringWriter0, 0, 2, null);
        String s = stringWriter0.toString();
        Intrinsics.checkNotNullExpressionValue(s, "buffer.toString()");
        return s;
    }

    private static final String readText(URL uRL0, Charset charset0) {
        Intrinsics.checkNotNullParameter(uRL0, "<this>");
        Intrinsics.checkNotNullParameter(charset0, "charset");
        return new String(TextStreamsKt.readBytes(uRL0), charset0);
    }

    static String readText$default(URL uRL0, Charset charset0, int v, Object object0) {
        if((v & 1) != 0) {
            charset0 = Charsets.UTF_8;
        }
        Intrinsics.checkNotNullParameter(uRL0, "<this>");
        Intrinsics.checkNotNullParameter(charset0, "charset");
        return new String(TextStreamsKt.readBytes(uRL0), charset0);
    }

    private static final StringReader reader(String s) {
        Intrinsics.checkNotNullParameter(s, "<this>");
        return new StringReader(s);
    }

    public static final Object useLines(Reader reader0, Function1 function10) {
        Object object0;
        Intrinsics.checkNotNullParameter(reader0, "<this>");
        Intrinsics.checkNotNullParameter(function10, "block");
        BufferedReader bufferedReader0 = reader0 instanceof BufferedReader ? ((BufferedReader)reader0) : new BufferedReader(reader0, 0x2000);
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
}

