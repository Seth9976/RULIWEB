package kotlin.io.encoding;

import java.io.IOException;
import java.io.InputStream;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000B\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u000F\b\u0003\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001A\u00020\u0001\u0012\u0006\u0010\u0003\u001A\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\u0013\u001A\u00020\u0014H\u0016J \u0010\u0015\u001A\u00020\u00142\u0006\u0010\u0016\u001A\u00020\u00072\u0006\u0010\u0017\u001A\u00020\t2\u0006\u0010\u0018\u001A\u00020\tH\u0002J(\u0010\u0019\u001A\u00020\t2\u0006\u0010\u0016\u001A\u00020\u00072\u0006\u0010\u0017\u001A\u00020\t2\u0006\u0010\u001A\u001A\u00020\t2\u0006\u0010\u001B\u001A\u00020\tH\u0002J\u0010\u0010\u001C\u001A\u00020\t2\u0006\u0010\u001B\u001A\u00020\tH\u0002J\b\u0010\u001D\u001A\u00020\tH\u0016J \u0010\u001D\u001A\u00020\t2\u0006\u0010\u001E\u001A\u00020\u00072\u0006\u0010\u001F\u001A\u00020\t2\u0006\u0010\u0018\u001A\u00020\tH\u0016J\b\u0010 \u001A\u00020\tH\u0002J\b\u0010!\u001A\u00020\u0014H\u0002J\b\u0010\"\u001A\u00020\u0014H\u0002R\u000E\u0010\u0003\u001A\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0006\u001A\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\b\u001A\u00020\tX\u0082\u000E¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001A\u00020\t8BX\u0082\u0004¢\u0006\u0006\u001A\u0004\b\u000B\u0010\fR\u000E\u0010\r\u001A\u00020\tX\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\u0002\u001A\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u000E\u001A\u00020\u000FX\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\u0010\u001A\u00020\u000FX\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\u0011\u001A\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0012\u001A\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Lkotlin/io/encoding/DecodeInputStream;", "Ljava/io/InputStream;", "input", "base64", "Lkotlin/io/encoding/Base64;", "(Ljava/io/InputStream;Lkotlin/io/encoding/Base64;)V", "byteBuffer", "", "byteBufferEndIndex", "", "byteBufferLength", "getByteBufferLength", "()I", "byteBufferStartIndex", "isClosed", "", "isEOF", "singleByteBuffer", "symbolBuffer", "close", "", "copyByteBufferInto", "dst", "dstOffset", "length", "decodeSymbolBufferInto", "dstEndIndex", "symbolBufferLength", "handlePaddingSymbol", "read", "destination", "offset", "readNextSymbol", "resetByteBufferIfEmpty", "shiftByteBufferToStartIfNeeded", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
final class DecodeInputStream extends InputStream implements AutoCloseable {
    private final Base64 base64;
    private final byte[] byteBuffer;
    private int byteBufferEndIndex;
    private int byteBufferStartIndex;
    private final InputStream input;
    private boolean isClosed;
    private boolean isEOF;
    private final byte[] singleByteBuffer;
    private final byte[] symbolBuffer;

    public DecodeInputStream(InputStream inputStream0, Base64 base640) {
        Intrinsics.checkNotNullParameter(inputStream0, "input");
        Intrinsics.checkNotNullParameter(base640, "base64");
        super();
        this.input = inputStream0;
        this.base64 = base640;
        this.singleByteBuffer = new byte[1];
        this.symbolBuffer = new byte[0x400];
        this.byteBuffer = new byte[0x400];
    }

    @Override
    public void close() {
        if(!this.isClosed) {
            this.isClosed = true;
            this.input.close();
        }
    }

    private final void copyByteBufferInto(byte[] arr_b, int v, int v1) {
        ArraysKt.copyInto(this.byteBuffer, arr_b, v, this.byteBufferStartIndex, this.byteBufferStartIndex + v1);
        this.byteBufferStartIndex += v1;
        this.resetByteBufferIfEmpty();
    }

    private final int decodeSymbolBufferInto(byte[] arr_b, int v, int v1, int v2) {
        this.byteBufferEndIndex += this.base64.decodeIntoByteArray(this.symbolBuffer, this.byteBuffer, this.byteBufferEndIndex, 0, v2);
        int v3 = Math.min(this.getByteBufferLength(), v1 - v);
        this.copyByteBufferInto(arr_b, v, v3);
        this.shiftByteBufferToStartIfNeeded();
        return v3;
    }

    private final int getByteBufferLength() {
        return this.byteBufferEndIndex - this.byteBufferStartIndex;
    }

    private final int handlePaddingSymbol(int v) {
        this.symbolBuffer[v] = 61;
        if((v & 3) == 2) {
            int v1 = this.readNextSymbol();
            if(v1 >= 0) {
                this.symbolBuffer[v + 1] = (byte)v1;
            }
            return v + 2;
        }
        return v + 1;
    }

    @Override
    public int read() {
        int v = this.byteBufferStartIndex;
        if(v < this.byteBufferEndIndex) {
            int v1 = this.byteBuffer[v] & 0xFF;
            this.byteBufferStartIndex = v + 1;
            this.resetByteBufferIfEmpty();
            return v1;
        }
        switch(this.read(this.singleByteBuffer, 0, 1)) {
            case -1: {
                return -1;
            }
            case 1: {
                return this.singleByteBuffer[0] & 0xFF;
            }
            default: {
                throw new IllegalStateException("Unreachable");
            }
        }
    }

    @Override
    public int read(byte[] arr_b, int v, int v1) {
        Intrinsics.checkNotNullParameter(arr_b, "destination");
        if(v >= 0 && v1 >= 0) {
            int v2 = v + v1;
            if(v2 <= arr_b.length) {
                if(this.isClosed) {
                    throw new IOException("The input stream is closed.");
                }
                if(this.isEOF) {
                    return -1;
                }
                if(v1 == 0) {
                    return 0;
                }
                if(this.getByteBufferLength() >= v1) {
                    this.copyByteBufferInto(arr_b, v, v1);
                    return v1;
                }
                int v3 = (v1 - this.getByteBufferLength() + 2) / 3 * 4;
                int v4 = v;
                boolean z;
                while(!z = this.isEOF && v3 > 0) {
                    int v5 = Math.min(this.symbolBuffer.length, v3);
                    int v6 = 0;
                    boolean z1;
                    while(!z1 = this.isEOF && v6 < v5) {
                        int v7 = this.readNextSymbol();
                        switch(v7) {
                            case -1: {
                                this.isEOF = true;
                                break;
                            }
                            case 61: {
                                v6 = this.handlePaddingSymbol(v6);
                                this.isEOF = true;
                                break;
                            }
                            default: {
                                this.symbolBuffer[v6] = (byte)v7;
                                ++v6;
                            }
                        }
                    }
                    if(!z1 && v6 != v5) {
                        throw new IllegalStateException("Check failed.");
                    }
                    v3 -= v6;
                    v4 += this.decodeSymbolBufferInto(arr_b, v4, v2, v6);
                }
                return v4 != v || !z ? v4 - v : -1;
            }
        }
        throw new IndexOutOfBoundsException("offset: " + v + ", length: " + v1 + ", buffer size: " + arr_b.length);
    }

    private final int readNextSymbol() {
        int v;
        if(!this.base64.isMimeScheme$kotlin_stdlib()) {
            return this.input.read();
        }
        do {
            v = this.input.read();
        }
        while(v != -1 && !Base64Kt.isInMimeAlphabet(v));
        return v;
    }

    private final void resetByteBufferIfEmpty() {
        if(this.byteBufferStartIndex == this.byteBufferEndIndex) {
            this.byteBufferStartIndex = 0;
            this.byteBufferEndIndex = 0;
        }
    }

    private final void shiftByteBufferToStartIfNeeded() {
        byte[] arr_b = this.byteBuffer;
        int v = this.byteBufferEndIndex;
        if(this.symbolBuffer.length / 4 * 3 > arr_b.length - v) {
            ArraysKt.copyInto(arr_b, arr_b, 0, this.byteBufferStartIndex, v);
            this.byteBufferEndIndex -= this.byteBufferStartIndex;
            this.byteBufferStartIndex = 0;
        }
    }
}

