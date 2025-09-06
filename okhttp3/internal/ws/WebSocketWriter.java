package okhttp3.internal.ws;

import java.io.Closeable;
import java.io.IOException;
import java.util.Random;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okio.Buffer.UnsafeCursor;
import okio.Buffer;
import okio.BufferedSink;
import okio.ByteString;

@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0005\u0012\u0006\u0010\u0006\u001A\u00020\u0007\u0012\u0006\u0010\b\u001A\u00020\u0003\u0012\u0006\u0010\t\u001A\u00020\u0003\u0012\u0006\u0010\n\u001A\u00020\u000B¢\u0006\u0002\u0010\fJ\b\u0010\u001B\u001A\u00020\u001CH\u0016J\u0018\u0010\u001D\u001A\u00020\u001C2\u0006\u0010\u001E\u001A\u00020\u001F2\b\u0010 \u001A\u0004\u0018\u00010!J\u0018\u0010\"\u001A\u00020\u001C2\u0006\u0010#\u001A\u00020\u001F2\u0006\u0010$\u001A\u00020!H\u0002J\u0016\u0010%\u001A\u00020\u001C2\u0006\u0010&\u001A\u00020\u001F2\u0006\u0010\'\u001A\u00020!J\u000E\u0010(\u001A\u00020\u001C2\u0006\u0010$\u001A\u00020!J\u000E\u0010)\u001A\u00020\u001C2\u0006\u0010$\u001A\u00020!R\u000E\u0010\u0002\u001A\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001A\u0004\u0018\u00010\u000EX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000F\u001A\u0004\u0018\u00010\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0011\u001A\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001A\u0004\u0018\u00010\u0014X\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\n\u001A\u00020\u000BX\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\t\u001A\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\b\u001A\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001A\u00020\u0007¢\u0006\b\n\u0000\u001A\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0004\u001A\u00020\u0005¢\u0006\b\n\u0000\u001A\u0004\b\u0017\u0010\u0018R\u000E\u0010\u0019\u001A\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u001A\u001A\u00020\u0003X\u0082\u000E¢\u0006\u0002\n\u0000¨\u0006*"}, d2 = {"Lokhttp3/internal/ws/WebSocketWriter;", "Ljava/io/Closeable;", "isClient", "", "sink", "Lokio/BufferedSink;", "random", "Ljava/util/Random;", "perMessageDeflate", "noContextTakeover", "minimumDeflateSize", "", "(ZLokio/BufferedSink;Ljava/util/Random;ZZJ)V", "maskCursor", "Lokio/Buffer$UnsafeCursor;", "maskKey", "", "messageBuffer", "Lokio/Buffer;", "messageDeflater", "Lokhttp3/internal/ws/MessageDeflater;", "getRandom", "()Ljava/util/Random;", "getSink", "()Lokio/BufferedSink;", "sinkBuffer", "writerClosed", "close", "", "writeClose", "code", "", "reason", "Lokio/ByteString;", "writeControlFrame", "opcode", "payload", "writeMessageFrame", "formatOpcode", "data", "writePing", "writePong", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
public final class WebSocketWriter implements Closeable, AutoCloseable {
    private final boolean isClient;
    private final UnsafeCursor maskCursor;
    private final byte[] maskKey;
    private final Buffer messageBuffer;
    private MessageDeflater messageDeflater;
    private final long minimumDeflateSize;
    private final boolean noContextTakeover;
    private final boolean perMessageDeflate;
    private final Random random;
    private final BufferedSink sink;
    private final Buffer sinkBuffer;
    private boolean writerClosed;

    public WebSocketWriter(boolean z, BufferedSink bufferedSink0, Random random0, boolean z1, boolean z2, long v) {
        Intrinsics.checkNotNullParameter(bufferedSink0, "sink");
        Intrinsics.checkNotNullParameter(random0, "random");
        super();
        this.isClient = z;
        this.sink = bufferedSink0;
        this.random = random0;
        this.perMessageDeflate = z1;
        this.noContextTakeover = z2;
        this.minimumDeflateSize = v;
        this.messageBuffer = new Buffer();
        this.sinkBuffer = bufferedSink0.getBuffer();
        UnsafeCursor buffer$UnsafeCursor0 = null;
        this.maskKey = z ? new byte[4] : null;
        if(z) {
            buffer$UnsafeCursor0 = new UnsafeCursor();
        }
        this.maskCursor = buffer$UnsafeCursor0;
    }

    @Override
    public void close() {
        MessageDeflater messageDeflater0 = this.messageDeflater;
        if(messageDeflater0 != null) {
            messageDeflater0.close();
        }
    }

    public final Random getRandom() {
        return this.random;
    }

    public final BufferedSink getSink() {
        return this.sink;
    }

    public final void writeClose(int v, ByteString byteString0) throws IOException {
        ByteString byteString1 = ByteString.EMPTY;
        if(v != 0 || byteString0 != null) {
            if(v != 0) {
                WebSocketProtocol.INSTANCE.validateCloseCode(v);
            }
            Buffer buffer0 = new Buffer();
            buffer0.writeShort(v);
            if(byteString0 != null) {
                buffer0.write(byteString0);
            }
            byteString1 = buffer0.readByteString();
        }
        try {
            this.writeControlFrame(8, byteString1);
            this.writerClosed = true;
        }
        catch(Throwable throwable0) {
            this.writerClosed = true;
            throw throwable0;
        }
    }

    private final void writeControlFrame(int v, ByteString byteString0) throws IOException {
        if(this.writerClosed) {
            throw new IOException("closed");
        }
        int v1 = byteString0.size();
        if(((long)v1) > 0x7DL) {
            throw new IllegalArgumentException("Payload size must be less than or equal to 125");
        }
        this.sinkBuffer.writeByte(v | 0x80);
        if(this.isClient) {
            this.sinkBuffer.writeByte(v1 | 0x80);
            Intrinsics.checkNotNull(this.maskKey);
            this.random.nextBytes(this.maskKey);
            this.sinkBuffer.write(this.maskKey);
            if(v1 > 0) {
                this.sinkBuffer.write(byteString0);
                Intrinsics.checkNotNull(this.maskCursor);
                this.sinkBuffer.readAndWriteUnsafe(this.maskCursor);
                this.maskCursor.seek(this.sinkBuffer.size());
                WebSocketProtocol.INSTANCE.toggleMask(this.maskCursor, this.maskKey);
                this.maskCursor.close();
            }
        }
        else {
            this.sinkBuffer.writeByte(v1);
            this.sinkBuffer.write(byteString0);
        }
        this.sink.flush();
    }

    public final void writeMessageFrame(int v, ByteString byteString0) throws IOException {
        Intrinsics.checkNotNullParameter(byteString0, "data");
        if(this.writerClosed) {
            throw new IOException("closed");
        }
        this.messageBuffer.write(byteString0);
        int v1 = v | 0x80;
        if(this.perMessageDeflate && ((long)byteString0.size()) >= this.minimumDeflateSize) {
            MessageDeflater messageDeflater0 = this.messageDeflater;
            if(messageDeflater0 == null) {
                messageDeflater0 = new MessageDeflater(this.noContextTakeover);
                this.messageDeflater = messageDeflater0;
            }
            messageDeflater0.deflate(this.messageBuffer);
            v1 = v | 0xC0;
        }
        long v2 = this.messageBuffer.size();
        this.sinkBuffer.writeByte(v1);
        int v3 = this.isClient ? 0x80 : 0;
        if(v2 <= 0x7DL) {
            this.sinkBuffer.writeByte(v3 | ((int)v2));
        }
        else if(v2 <= 0xFFFFL) {
            this.sinkBuffer.writeByte(v3 | 0x7E);
            this.sinkBuffer.writeShort(((int)v2));
        }
        else {
            this.sinkBuffer.writeByte(v3 | 0x7F);
            this.sinkBuffer.writeLong(v2);
        }
        if(this.isClient) {
            Intrinsics.checkNotNull(this.maskKey);
            this.random.nextBytes(this.maskKey);
            this.sinkBuffer.write(this.maskKey);
            if(v2 > 0L) {
                Intrinsics.checkNotNull(this.maskCursor);
                this.messageBuffer.readAndWriteUnsafe(this.maskCursor);
                this.maskCursor.seek(0L);
                WebSocketProtocol.INSTANCE.toggleMask(this.maskCursor, this.maskKey);
                this.maskCursor.close();
            }
        }
        this.sinkBuffer.write(this.messageBuffer, v2);
        this.sink.emit();
    }

    public final void writePing(ByteString byteString0) throws IOException {
        Intrinsics.checkNotNullParameter(byteString0, "payload");
        this.writeControlFrame(9, byteString0);
    }

    public final void writePong(ByteString byteString0) throws IOException {
        Intrinsics.checkNotNullParameter(byteString0, "payload");
        this.writeControlFrame(10, byteString0);
    }
}

