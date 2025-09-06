package okhttp3.internal.ws;

import java.io.Closeable;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal._UtilCommonKt;
import okhttp3.internal._UtilJvmKt;
import okio.Buffer.UnsafeCursor;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;

@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001:\u0001&B-\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0005\u0012\u0006\u0010\u0006\u001A\u00020\u0007\u0012\u0006\u0010\b\u001A\u00020\u0003\u0012\u0006\u0010\t\u001A\u00020\u0003¢\u0006\u0002\u0010\nJ\b\u0010\u001E\u001A\u00020\u001FH\u0016J\u0006\u0010 \u001A\u00020\u001FJ\b\u0010!\u001A\u00020\u001FH\u0002J\b\u0010\"\u001A\u00020\u001FH\u0002J\b\u0010#\u001A\u00020\u001FH\u0002J\b\u0010$\u001A\u00020\u001FH\u0002J\b\u0010%\u001A\u00020\u001FH\u0002R\u000E\u0010\u000B\u001A\u00020\u0003X\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\f\u001A\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0006\u001A\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u000E\u001A\u00020\u000FX\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\u0002\u001A\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0010\u001A\u00020\u0003X\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\u0011\u001A\u00020\u0003X\u0082\u000E¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001A\u0004\u0018\u00010\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001A\u0004\u0018\u00010\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0016\u001A\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001A\u0004\u0018\u00010\u0018X\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\t\u001A\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0019\u001A\u00020\u001AX\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\b\u001A\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u001B\u001A\u00020\u0003X\u0082\u000E¢\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001A\u00020\u0005¢\u0006\b\n\u0000\u001A\u0004\b\u001C\u0010\u001D¨\u0006\'"}, d2 = {"Lokhttp3/internal/ws/WebSocketReader;", "Ljava/io/Closeable;", "isClient", "", "source", "Lokio/BufferedSource;", "frameCallback", "Lokhttp3/internal/ws/WebSocketReader$FrameCallback;", "perMessageDeflate", "noContextTakeover", "(ZLokio/BufferedSource;Lokhttp3/internal/ws/WebSocketReader$FrameCallback;ZZ)V", "closed", "controlFrameBuffer", "Lokio/Buffer;", "frameLength", "", "isControlFrame", "isFinalFrame", "maskCursor", "Lokio/Buffer$UnsafeCursor;", "maskKey", "", "messageFrameBuffer", "messageInflater", "Lokhttp3/internal/ws/MessageInflater;", "opcode", "", "readingCompressedMessage", "getSource", "()Lokio/BufferedSource;", "close", "", "processNextFrame", "readControlFrame", "readHeader", "readMessage", "readMessageFrame", "readUntilNonControlFrame", "FrameCallback", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
public final class WebSocketReader implements Closeable, AutoCloseable {
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001A\u00020\u00032\u0006\u0010\u0004\u001A\u00020\u00052\u0006\u0010\u0006\u001A\u00020\u0007H&J\u0010\u0010\b\u001A\u00020\u00032\u0006\u0010\t\u001A\u00020\u0007H&J\u0010\u0010\b\u001A\u00020\u00032\u0006\u0010\n\u001A\u00020\u000BH&J\u0010\u0010\f\u001A\u00020\u00032\u0006\u0010\r\u001A\u00020\u000BH&J\u0010\u0010\u000E\u001A\u00020\u00032\u0006\u0010\r\u001A\u00020\u000BH&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000FÀ\u0006\u0001"}, d2 = {"Lokhttp3/internal/ws/WebSocketReader$FrameCallback;", "", "onReadClose", "", "code", "", "reason", "", "onReadMessage", "text", "bytes", "Lokio/ByteString;", "onReadPing", "payload", "onReadPong", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    public interface FrameCallback {
        void onReadClose(int arg1, String arg2);

        void onReadMessage(String arg1) throws IOException;

        void onReadMessage(ByteString arg1) throws IOException;

        void onReadPing(ByteString arg1);

        void onReadPong(ByteString arg1);
    }

    private boolean closed;
    private final Buffer controlFrameBuffer;
    private final FrameCallback frameCallback;
    private long frameLength;
    private final boolean isClient;
    private boolean isControlFrame;
    private boolean isFinalFrame;
    private final UnsafeCursor maskCursor;
    private final byte[] maskKey;
    private final Buffer messageFrameBuffer;
    private MessageInflater messageInflater;
    private final boolean noContextTakeover;
    private int opcode;
    private final boolean perMessageDeflate;
    private boolean readingCompressedMessage;
    private final BufferedSource source;

    public WebSocketReader(boolean z, BufferedSource bufferedSource0, FrameCallback webSocketReader$FrameCallback0, boolean z1, boolean z2) {
        Intrinsics.checkNotNullParameter(bufferedSource0, "source");
        Intrinsics.checkNotNullParameter(webSocketReader$FrameCallback0, "frameCallback");
        super();
        this.isClient = z;
        this.source = bufferedSource0;
        this.frameCallback = webSocketReader$FrameCallback0;
        this.perMessageDeflate = z1;
        this.noContextTakeover = z2;
        this.controlFrameBuffer = new Buffer();
        this.messageFrameBuffer = new Buffer();
        UnsafeCursor buffer$UnsafeCursor0 = null;
        this.maskKey = z ? null : new byte[4];
        if(!z) {
            buffer$UnsafeCursor0 = new UnsafeCursor();
        }
        this.maskCursor = buffer$UnsafeCursor0;
    }

    @Override
    public void close() throws IOException {
        MessageInflater messageInflater0 = this.messageInflater;
        if(messageInflater0 != null) {
            messageInflater0.close();
        }
    }

    public final BufferedSource getSource() {
        return this.source;
    }

    public final void processNextFrame() throws IOException {
        this.readHeader();
        if(this.isControlFrame) {
            this.readControlFrame();
            return;
        }
        this.readMessageFrame();
    }

    private final void readControlFrame() throws IOException {
        int v2;
        long v = this.frameLength;
        if(v > 0L) {
            this.source.readFully(this.controlFrameBuffer, v);
            if(!this.isClient) {
                Intrinsics.checkNotNull(this.maskCursor);
                this.controlFrameBuffer.readAndWriteUnsafe(this.maskCursor);
                this.maskCursor.seek(0L);
                Intrinsics.checkNotNull(this.maskKey);
                WebSocketProtocol.INSTANCE.toggleMask(this.maskCursor, this.maskKey);
                this.maskCursor.close();
            }
        }
        switch(this.opcode) {
            case 8: {
                long v1 = this.controlFrameBuffer.size();
                if(v1 == 1L) {
                    throw new ProtocolException("Malformed close payload length of 1.");
                }
                if(v1 == 0L) {
                    v2 = 1005;
                }
                else {
                    v2 = this.controlFrameBuffer.readShort();
                    String s = WebSocketProtocol.INSTANCE.closeCodeExceptionMessage(v2);
                    if(s != null) {
                        throw new ProtocolException(s);
                    }
                }
                this.frameCallback.onReadClose(v2, "");
                this.closed = true;
                return;
            }
            case 9: {
                ByteString byteString0 = this.controlFrameBuffer.readByteString();
                this.frameCallback.onReadPing(byteString0);
                return;
            }
            case 10: {
                ByteString byteString1 = this.controlFrameBuffer.readByteString();
                this.frameCallback.onReadPong(byteString1);
                return;
            }
            default: {
                throw new ProtocolException("Unknown control opcode: " + _UtilJvmKt.toHexString(this.opcode));
            }
        }
    }

    private final void readHeader() throws IOException, ProtocolException {
        int v2;
        boolean z4;
        if(this.closed) {
            throw new IOException("closed");
        }
        long v = this.source.timeout().timeoutNanos();
        this.source.timeout().clearTimeout();
        try {
            v2 = _UtilCommonKt.and(this.source.readByte(), ((byte)0xFF));
        }
        finally {
            this.source.timeout().timeout(v, TimeUnit.NANOSECONDS);
        }
        this.opcode = v2 & 15;
        boolean z = false;
        boolean z1 = (v2 & 0x80) != 0;
        this.isFinalFrame = z1;
        boolean z2 = (v2 & 8) != 0;
        this.isControlFrame = z2;
        if(z2 && !z1) {
            throw new ProtocolException("Control frames must be final.");
        }
        boolean z3 = (v2 & 0x40) != 0;
        if((v2 & 15) == 1 || (v2 & 15) == 2) {
            if(z3) {
                if(!this.perMessageDeflate) {
                    throw new ProtocolException("Unexpected rsv1 flag");
                }
                z4 = true;
            }
            else {
                z4 = false;
            }
            this.readingCompressedMessage = z4;
        }
        else if(z3) {
            throw new ProtocolException("Unexpected rsv1 flag");
        }
        if((v2 & 0x20) != 0) {
            throw new ProtocolException("Unexpected rsv2 flag");
        }
        if((v2 & 16) != 0) {
            throw new ProtocolException("Unexpected rsv3 flag");
        }
        int v3 = _UtilCommonKt.and(this.source.readByte(), ((byte)0xFF));
        if((v3 & 0x80) != 0) {
            z = true;
        }
        if(z == this.isClient) {
            throw new ProtocolException((this.isClient ? "Server-sent frames must not be masked." : "Client-sent frames must be masked."));
        }
        this.frameLength = (long)(v3 & 0x7F);
        if(((long)(v3 & 0x7F)) == 0x7EL) {
            this.frameLength = (long)_UtilCommonKt.and(this.source.readShort(), ((short)0xFFFF));
        }
        else if(((long)(v3 & 0x7F)) == 0x7FL) {
            long v4 = this.source.readLong();
            this.frameLength = v4;
            if(v4 < 0L) {
                throw new ProtocolException("Frame length 0x" + _UtilJvmKt.toHexString(this.frameLength) + " > 0x7FFFFFFFFFFFFFFF");
            }
        }
        if(this.isControlFrame && this.frameLength > 0x7DL) {
            throw new ProtocolException("Control frame must be less than 125B.");
        }
        if(z) {
            Intrinsics.checkNotNull(this.maskKey);
            this.source.readFully(this.maskKey);
        }
    }

    private final void readMessage() throws IOException {
        while(!this.closed) {
            long v = this.frameLength;
            if(v > 0L) {
                this.source.readFully(this.messageFrameBuffer, v);
                if(!this.isClient) {
                    Intrinsics.checkNotNull(this.maskCursor);
                    this.messageFrameBuffer.readAndWriteUnsafe(this.maskCursor);
                    this.maskCursor.seek(this.messageFrameBuffer.size() - this.frameLength);
                    Intrinsics.checkNotNull(this.maskKey);
                    WebSocketProtocol.INSTANCE.toggleMask(this.maskCursor, this.maskKey);
                    this.maskCursor.close();
                }
            }
            if(!this.isFinalFrame) {
                this.readUntilNonControlFrame();
                if(this.opcode != 0) {
                    throw new ProtocolException("Expected continuation opcode. Got: " + _UtilJvmKt.toHexString(this.opcode));
                }
                continue;
            }
            return;
        }
        throw new IOException("closed");
    }

    private final void readMessageFrame() throws IOException {
        int v = this.opcode;
        if(v != 1 && v != 2) {
            throw new ProtocolException("Unknown opcode: " + _UtilJvmKt.toHexString(v));
        }
        this.readMessage();
        if(this.readingCompressedMessage) {
            MessageInflater messageInflater0 = this.messageInflater;
            if(messageInflater0 == null) {
                messageInflater0 = new MessageInflater(this.noContextTakeover);
                this.messageInflater = messageInflater0;
            }
            messageInflater0.inflate(this.messageFrameBuffer);
        }
        if(v == 1) {
            this.frameCallback.onReadMessage("");
            return;
        }
        ByteString byteString0 = this.messageFrameBuffer.readByteString();
        this.frameCallback.onReadMessage(byteString0);
    }

    private final void readUntilNonControlFrame() throws IOException {
        while(!this.closed) {
            this.readHeader();
            if(!this.isControlFrame) {
                break;
            }
            this.readControlFrame();
        }
    }
}

