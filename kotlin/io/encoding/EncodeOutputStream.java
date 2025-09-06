package kotlin.io.encoding;

import java.io.IOException;
import java.io.OutputStream;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\r\b\u0003\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001A\u00020\u0001\u0012\u0006\u0010\u0003\u001A\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\u000E\u001A\u00020\u000FH\u0002J\b\u0010\u0010\u001A\u00020\u000FH\u0016J \u0010\u0011\u001A\u00020\t2\u0006\u0010\u0012\u001A\u00020\u00072\u0006\u0010\u0013\u001A\u00020\t2\u0006\u0010\u0014\u001A\u00020\tH\u0002J\b\u0010\u0015\u001A\u00020\u000FH\u0002J \u0010\u0016\u001A\u00020\t2\u0006\u0010\u0012\u001A\u00020\u00072\u0006\u0010\u0013\u001A\u00020\t2\u0006\u0010\u0014\u001A\u00020\tH\u0002J\b\u0010\u0017\u001A\u00020\u000FH\u0016J \u0010\u0018\u001A\u00020\u000F2\u0006\u0010\u0012\u001A\u00020\u00072\u0006\u0010\u0019\u001A\u00020\t2\u0006\u0010\u001A\u001A\u00020\tH\u0016J\u0010\u0010\u0018\u001A\u00020\u000F2\u0006\u0010\u001B\u001A\u00020\tH\u0016R\u000E\u0010\u0003\u001A\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0006\u001A\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\b\u001A\u00020\tX\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\n\u001A\u00020\u000BX\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\f\u001A\u00020\tX\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\u0002\u001A\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\r\u001A\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001C"}, d2 = {"Lkotlin/io/encoding/EncodeOutputStream;", "Ljava/io/OutputStream;", "output", "base64", "Lkotlin/io/encoding/Base64;", "(Ljava/io/OutputStream;Lkotlin/io/encoding/Base64;)V", "byteBuffer", "", "byteBufferLength", "", "isClosed", "", "lineLength", "symbolBuffer", "checkOpen", "", "close", "copyIntoByteBuffer", "source", "startIndex", "endIndex", "encodeByteBufferIntoOutput", "encodeIntoOutput", "flush", "write", "offset", "length", "b", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
final class EncodeOutputStream extends OutputStream implements AutoCloseable {
    private final Base64 base64;
    private final byte[] byteBuffer;
    private int byteBufferLength;
    private boolean isClosed;
    private int lineLength;
    private final OutputStream output;
    private final byte[] symbolBuffer;

    public EncodeOutputStream(OutputStream outputStream0, Base64 base640) {
        Intrinsics.checkNotNullParameter(outputStream0, "output");
        Intrinsics.checkNotNullParameter(base640, "base64");
        super();
        this.output = outputStream0;
        this.base64 = base640;
        this.lineLength = base640.isMimeScheme$kotlin_stdlib() ? 76 : -1;
        this.symbolBuffer = new byte[0x400];
        this.byteBuffer = new byte[3];
    }

    private final void checkOpen() {
        if(this.isClosed) {
            throw new IOException("The output stream is closed.");
        }
    }

    @Override
    public void close() {
        if(!this.isClosed) {
            this.isClosed = true;
            if(this.byteBufferLength != 0) {
                this.encodeByteBufferIntoOutput();
            }
            this.output.close();
        }
    }

    private final int copyIntoByteBuffer(byte[] arr_b, int v, int v1) {
        int v2 = Math.min(3 - this.byteBufferLength, v1 - v);
        ArraysKt.copyInto(arr_b, this.byteBuffer, this.byteBufferLength, v, v + v2);
        int v3 = this.byteBufferLength + v2;
        this.byteBufferLength = v3;
        if(v3 == 3) {
            this.encodeByteBufferIntoOutput();
        }
        return v2;
    }

    private final void encodeByteBufferIntoOutput() {
        if(this.encodeIntoOutput(this.byteBuffer, 0, this.byteBufferLength) != 4) {
            throw new IllegalStateException("Check failed.");
        }
        this.byteBufferLength = 0;
    }

    private final int encodeIntoOutput(byte[] arr_b, int v, int v1) {
        int v2 = this.base64.encodeIntoByteArray(arr_b, this.symbolBuffer, 0, v, v1);
        if(this.lineLength == 0) {
            this.output.write(new byte[]{13, 10});
            this.lineLength = 76;
            if(v2 > 76) {
                throw new IllegalStateException("Check failed.");
            }
        }
        this.output.write(this.symbolBuffer, 0, v2);
        this.lineLength -= v2;
        return v2;
    }

    @Override
    public void flush() {
        this.checkOpen();
        this.output.flush();
    }

    @Override
    public void write(int v) {
        this.checkOpen();
        int v1 = this.byteBufferLength;
        this.byteBufferLength = v1 + 1;
        this.byteBuffer[v1] = (byte)v;
        if(v1 + 1 == 3) {
            this.encodeByteBufferIntoOutput();
        }
    }

    @Override
    public void write(byte[] arr_b, int v, int v1) {
        Intrinsics.checkNotNullParameter(arr_b, "source");
        this.checkOpen();
        if(v >= 0 && v1 >= 0) {
            int v2 = v + v1;
            if(v2 <= arr_b.length) {
                if(v1 != 0) {
                    int v3 = this.byteBufferLength;
                    if(v3 >= 3) {
                        throw new IllegalStateException("Check failed.");
                    }
                    if(v3 == 0) {
                        goto label_11;
                    }
                    v += this.copyIntoByteBuffer(arr_b, v, v2);
                    if(this.byteBufferLength == 0) {
                    label_11:
                        while(v + 3 <= v2) {
                            int v4 = Math.min((this.base64.isMimeScheme$kotlin_stdlib() ? this.lineLength : this.symbolBuffer.length) / 4, (v2 - v) / 3);
                            int v5 = v4 * 3 + v;
                            if(this.encodeIntoOutput(arr_b, v, v5) != v4 * 4) {
                                throw new IllegalStateException("Check failed.");
                            }
                            v = v5;
                        }
                        ArraysKt.copyInto(arr_b, this.byteBuffer, 0, v, v2);
                        this.byteBufferLength = v2 - v;
                        return;
                    }
                }
                return;
            }
        }
        throw new IndexOutOfBoundsException("offset: " + v + ", length: " + v1 + ", source size: " + arr_b.length);
    }
}

