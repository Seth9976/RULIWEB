package kotlin.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.NoSuchElementException;
import kotlin.Deprecated;
import kotlin.DeprecatedSinceKotlin;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.ByteIterator;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;

@Metadata(d1 = {"\u0000Z\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001A\u0017\u0010\u0000\u001A\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001A\u00020\u0004H\u0087\b\u001A\u0017\u0010\u0000\u001A\u00020\u0005*\u00020\u00062\b\b\u0002\u0010\u0003\u001A\u00020\u0004H\u0087\b\u001A\u0017\u0010\u0007\u001A\u00020\b*\u00020\u00022\b\b\u0002\u0010\t\u001A\u00020\nH\u0087\b\u001A\u0017\u0010\u000B\u001A\u00020\f*\u00020\u00062\b\b\u0002\u0010\t\u001A\u00020\nH\u0087\b\u001A\u0017\u0010\r\u001A\u00020\u000E*\u00020\u000F2\b\b\u0002\u0010\t\u001A\u00020\nH\u0087\b\u001A\u001C\u0010\u0010\u001A\u00020\u0011*\u00020\u00022\u0006\u0010\u0012\u001A\u00020\u00062\b\b\u0002\u0010\u0003\u001A\u00020\u0004\u001A\r\u0010\u0013\u001A\u00020\u000E*\u00020\u0014H\u0087\b\u001A\u001D\u0010\u0013\u001A\u00020\u000E*\u00020\u00142\u0006\u0010\u0015\u001A\u00020\u00042\u0006\u0010\u0016\u001A\u00020\u0004H\u0087\b\u001A\r\u0010\u0017\u001A\u00020\u0018*\u00020\u0001H\u0086\u0002\u001A\f\u0010\u0019\u001A\u00020\u0014*\u00020\u0002H\u0007\u001A\u0016\u0010\u0019\u001A\u00020\u0014*\u00020\u00022\b\b\u0002\u0010\u001A\u001A\u00020\u0004H\u0007\u001A\u0017\u0010\u001B\u001A\u00020\u001C*\u00020\u00022\b\b\u0002\u0010\t\u001A\u00020\nH\u0087\b\u001A\u0017\u0010\u001D\u001A\u00020\u001E*\u00020\u00062\b\b\u0002\u0010\t\u001A\u00020\nH\u0087\b¨\u0006\u001F"}, d2 = {"buffered", "Ljava/io/BufferedInputStream;", "Ljava/io/InputStream;", "bufferSize", "", "Ljava/io/BufferedOutputStream;", "Ljava/io/OutputStream;", "bufferedReader", "Ljava/io/BufferedReader;", "charset", "Ljava/nio/charset/Charset;", "bufferedWriter", "Ljava/io/BufferedWriter;", "byteInputStream", "Ljava/io/ByteArrayInputStream;", "", "copyTo", "", "out", "inputStream", "", "offset", "length", "iterator", "Lkotlin/collections/ByteIterator;", "readBytes", "estimatedSize", "reader", "Ljava/io/InputStreamReader;", "writer", "Ljava/io/OutputStreamWriter;", "kotlin-stdlib"}, k = 2, mv = {1, 9, 0}, xi = 0x30)
public final class ByteStreamsKt {
    private static final BufferedInputStream buffered(InputStream inputStream0, int v) {
        Intrinsics.checkNotNullParameter(inputStream0, "<this>");
        return inputStream0 instanceof BufferedInputStream ? ((BufferedInputStream)inputStream0) : new BufferedInputStream(inputStream0, v);
    }

    private static final BufferedOutputStream buffered(OutputStream outputStream0, int v) {
        Intrinsics.checkNotNullParameter(outputStream0, "<this>");
        return outputStream0 instanceof BufferedOutputStream ? ((BufferedOutputStream)outputStream0) : new BufferedOutputStream(outputStream0, v);
    }

    static BufferedInputStream buffered$default(InputStream inputStream0, int v, int v1, Object object0) {
        if((v1 & 1) != 0) {
            v = 0x2000;
        }
        Intrinsics.checkNotNullParameter(inputStream0, "<this>");
        return inputStream0 instanceof BufferedInputStream ? ((BufferedInputStream)inputStream0) : new BufferedInputStream(inputStream0, v);
    }

    static BufferedOutputStream buffered$default(OutputStream outputStream0, int v, int v1, Object object0) {
        if((v1 & 1) != 0) {
            v = 0x2000;
        }
        Intrinsics.checkNotNullParameter(outputStream0, "<this>");
        return outputStream0 instanceof BufferedOutputStream ? ((BufferedOutputStream)outputStream0) : new BufferedOutputStream(outputStream0, v);
    }

    private static final BufferedReader bufferedReader(InputStream inputStream0, Charset charset0) {
        Intrinsics.checkNotNullParameter(inputStream0, "<this>");
        Intrinsics.checkNotNullParameter(charset0, "charset");
        Reader reader0 = new InputStreamReader(inputStream0, charset0);
        return reader0 instanceof BufferedReader ? ((BufferedReader)reader0) : new BufferedReader(reader0, 0x2000);
    }

    static BufferedReader bufferedReader$default(InputStream inputStream0, Charset charset0, int v, Object object0) {
        if((v & 1) != 0) {
            charset0 = Charsets.UTF_8;
        }
        Intrinsics.checkNotNullParameter(inputStream0, "<this>");
        Intrinsics.checkNotNullParameter(charset0, "charset");
        Reader reader0 = new InputStreamReader(inputStream0, charset0);
        return reader0 instanceof BufferedReader ? ((BufferedReader)reader0) : new BufferedReader(reader0, 0x2000);
    }

    private static final BufferedWriter bufferedWriter(OutputStream outputStream0, Charset charset0) {
        Intrinsics.checkNotNullParameter(outputStream0, "<this>");
        Intrinsics.checkNotNullParameter(charset0, "charset");
        Writer writer0 = new OutputStreamWriter(outputStream0, charset0);
        return writer0 instanceof BufferedWriter ? ((BufferedWriter)writer0) : new BufferedWriter(writer0, 0x2000);
    }

    static BufferedWriter bufferedWriter$default(OutputStream outputStream0, Charset charset0, int v, Object object0) {
        if((v & 1) != 0) {
            charset0 = Charsets.UTF_8;
        }
        Intrinsics.checkNotNullParameter(outputStream0, "<this>");
        Intrinsics.checkNotNullParameter(charset0, "charset");
        Writer writer0 = new OutputStreamWriter(outputStream0, charset0);
        return writer0 instanceof BufferedWriter ? ((BufferedWriter)writer0) : new BufferedWriter(writer0, 0x2000);
    }

    private static final ByteArrayInputStream byteInputStream(String s, Charset charset0) {
        Intrinsics.checkNotNullParameter(s, "<this>");
        Intrinsics.checkNotNullParameter(charset0, "charset");
        byte[] arr_b = s.getBytes(charset0);
        Intrinsics.checkNotNullExpressionValue(arr_b, "this as java.lang.String).getBytes(charset)");
        return new ByteArrayInputStream(arr_b);
    }

    static ByteArrayInputStream byteInputStream$default(String s, Charset charset0, int v, Object object0) {
        if((v & 1) != 0) {
            charset0 = Charsets.UTF_8;
        }
        Intrinsics.checkNotNullParameter(s, "<this>");
        Intrinsics.checkNotNullParameter(charset0, "charset");
        byte[] arr_b = s.getBytes(charset0);
        Intrinsics.checkNotNullExpressionValue(arr_b, "this as java.lang.String).getBytes(charset)");
        return new ByteArrayInputStream(arr_b);
    }

    public static final long copyTo(InputStream inputStream0, OutputStream outputStream0, int v) {
        Intrinsics.checkNotNullParameter(inputStream0, "<this>");
        Intrinsics.checkNotNullParameter(outputStream0, "out");
        byte[] arr_b = new byte[v];
        int v1 = inputStream0.read(arr_b);
        long v2 = 0L;
        while(v1 >= 0) {
            outputStream0.write(arr_b, 0, v1);
            v2 += (long)v1;
            v1 = inputStream0.read(arr_b);
        }
        return v2;
    }

    public static long copyTo$default(InputStream inputStream0, OutputStream outputStream0, int v, int v1, Object object0) {
        if((v1 & 2) != 0) {
            v = 0x2000;
        }
        return ByteStreamsKt.copyTo(inputStream0, outputStream0, v);
    }

    private static final ByteArrayInputStream inputStream(byte[] arr_b) {
        Intrinsics.checkNotNullParameter(arr_b, "<this>");
        return new ByteArrayInputStream(arr_b);
    }

    private static final ByteArrayInputStream inputStream(byte[] arr_b, int v, int v1) {
        Intrinsics.checkNotNullParameter(arr_b, "<this>");
        return new ByteArrayInputStream(arr_b, v, v1);
    }

    public static final ByteIterator iterator(BufferedInputStream bufferedInputStream0) {
        Intrinsics.checkNotNullParameter(bufferedInputStream0, "<this>");
        return new ByteIterator() {
            private boolean finished;
            private int nextByte;
            private boolean nextPrepared;

            {
                BufferedInputStream bufferedInputStream0 = bufferedInputStream0;  // 捕获的参数 （可能与外部方法变量命名冲突；考虑手动重命名）
                this.nextByte = -1;
            }

            public final boolean getFinished() {
                return this.finished;
            }

            public final int getNextByte() {
                return this.nextByte;
            }

            public final boolean getNextPrepared() {
                return this.nextPrepared;
            }

            @Override
            public boolean hasNext() {
                this.prepareNext();
                return !this.finished;
            }

            @Override  // kotlin.collections.ByteIterator
            public byte nextByte() {
                this.prepareNext();
                if(this.finished) {
                    throw new NoSuchElementException("Input stream is over.");
                }
                this.nextPrepared = false;
                return (byte)this.nextByte;
            }

            private final void prepareNext() {
                if(!this.nextPrepared && !this.finished) {
                    int v = bufferedInputStream0.read();
                    this.nextByte = v;
                    boolean z = true;
                    this.nextPrepared = true;
                    if(v != -1) {
                        z = false;
                    }
                    this.finished = z;
                }
            }

            public final void setFinished(boolean z) {
                this.finished = z;
            }

            public final void setNextByte(int v) {
                this.nextByte = v;
            }

            public final void setNextPrepared(boolean z) {
                this.nextPrepared = z;
            }
        };
    }

    public static final byte[] readBytes(InputStream inputStream0) {
        Intrinsics.checkNotNullParameter(inputStream0, "<this>");
        ByteArrayOutputStream byteArrayOutputStream0 = new ByteArrayOutputStream(Math.max(0x2000, inputStream0.available()));
        ByteStreamsKt.copyTo$default(inputStream0, byteArrayOutputStream0, 0, 2, null);
        byte[] arr_b = byteArrayOutputStream0.toByteArray();
        Intrinsics.checkNotNullExpressionValue(arr_b, "buffer.toByteArray()");
        return arr_b;
    }

    @Deprecated(message = "Use readBytes() overload without estimatedSize parameter", replaceWith = @ReplaceWith(expression = "readBytes()", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", warningSince = "1.3")
    public static final byte[] readBytes(InputStream inputStream0, int v) {
        Intrinsics.checkNotNullParameter(inputStream0, "<this>");
        ByteArrayOutputStream byteArrayOutputStream0 = new ByteArrayOutputStream(Math.max(v, inputStream0.available()));
        ByteStreamsKt.copyTo$default(inputStream0, byteArrayOutputStream0, 0, 2, null);
        byte[] arr_b = byteArrayOutputStream0.toByteArray();
        Intrinsics.checkNotNullExpressionValue(arr_b, "buffer.toByteArray()");
        return arr_b;
    }

    public static byte[] readBytes$default(InputStream inputStream0, int v, int v1, Object object0) {
        if((v1 & 1) != 0) {
            v = 0x2000;
        }
        return ByteStreamsKt.readBytes(inputStream0, v);
    }

    private static final InputStreamReader reader(InputStream inputStream0, Charset charset0) {
        Intrinsics.checkNotNullParameter(inputStream0, "<this>");
        Intrinsics.checkNotNullParameter(charset0, "charset");
        return new InputStreamReader(inputStream0, charset0);
    }

    static InputStreamReader reader$default(InputStream inputStream0, Charset charset0, int v, Object object0) {
        if((v & 1) != 0) {
            charset0 = Charsets.UTF_8;
        }
        Intrinsics.checkNotNullParameter(inputStream0, "<this>");
        Intrinsics.checkNotNullParameter(charset0, "charset");
        return new InputStreamReader(inputStream0, charset0);
    }

    private static final OutputStreamWriter writer(OutputStream outputStream0, Charset charset0) {
        Intrinsics.checkNotNullParameter(outputStream0, "<this>");
        Intrinsics.checkNotNullParameter(charset0, "charset");
        return new OutputStreamWriter(outputStream0, charset0);
    }

    static OutputStreamWriter writer$default(OutputStream outputStream0, Charset charset0, int v, Object object0) {
        if((v & 1) != 0) {
            charset0 = Charsets.UTF_8;
        }
        Intrinsics.checkNotNullParameter(outputStream0, "<this>");
        Intrinsics.checkNotNullParameter(charset0, "charset");
        return new OutputStreamWriter(outputStream0, charset0);
    }
}

