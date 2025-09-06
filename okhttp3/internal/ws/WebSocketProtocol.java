package okhttp3.internal.ws;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okio.Buffer.UnsafeCursor;
import okio.ByteString;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\t\n\u0002\b\u0011\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0002\b\u0002\b\u00C6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00A2\u0006\u0002\u0010\u0002J\u000E\u0010\u001C\u001A\u00020\u00042\u0006\u0010\u001D\u001A\u00020\u0004J\u0010\u0010\u001E\u001A\u0004\u0018\u00010\u00042\u0006\u0010\u001F\u001A\u00020\u0006J\u0016\u0010 \u001A\u00020!2\u0006\u0010\"\u001A\u00020#2\u0006\u0010\u001D\u001A\u00020$J\u000E\u0010%\u001A\u00020!2\u0006\u0010\u001F\u001A\u00020\u0006R\u000E\u0010\u0003\u001A\u00020\u0004X\u0080T\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u0005\u001A\u00020\u0006X\u0080T\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u0007\u001A\u00020\u0006X\u0080T\u00A2\u0006\u0002\n\u0000R\u000E\u0010\b\u001A\u00020\u0006X\u0080T\u00A2\u0006\u0002\n\u0000R\u000E\u0010\t\u001A\u00020\u0006X\u0080T\u00A2\u0006\u0002\n\u0000R\u000E\u0010\n\u001A\u00020\u0006X\u0080T\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u000B\u001A\u00020\u0006X\u0080T\u00A2\u0006\u0002\n\u0000R\u000E\u0010\f\u001A\u00020\u0006X\u0080T\u00A2\u0006\u0002\n\u0000R\u000E\u0010\r\u001A\u00020\u0006X\u0080T\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u000E\u001A\u00020\u000FX\u0080T\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u0010\u001A\u00020\u0006X\u0080T\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u0011\u001A\u00020\u0006X\u0080T\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u0012\u001A\u00020\u0006X\u0080T\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u0013\u001A\u00020\u0006X\u0080T\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u0014\u001A\u00020\u0006X\u0080T\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u0015\u001A\u00020\u0006X\u0080T\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u0016\u001A\u00020\u0006X\u0080T\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u0017\u001A\u00020\u0006X\u0080T\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u0018\u001A\u00020\u000FX\u0080T\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u0019\u001A\u00020\u0006X\u0080T\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u001A\u001A\u00020\u0006X\u0080T\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u001B\u001A\u00020\u000FX\u0080T\u00A2\u0006\u0002\n\u0000\u00A8\u0006&"}, d2 = {"Lokhttp3/internal/ws/WebSocketProtocol;", "", "()V", "ACCEPT_MAGIC", "", "B0_FLAG_FIN", "", "B0_FLAG_RSV1", "B0_FLAG_RSV2", "B0_FLAG_RSV3", "B0_MASK_OPCODE", "B1_FLAG_MASK", "B1_MASK_LENGTH", "CLOSE_CLIENT_GOING_AWAY", "CLOSE_MESSAGE_MAX", "", "CLOSE_NO_STATUS_CODE", "OPCODE_BINARY", "OPCODE_CONTINUATION", "OPCODE_CONTROL_CLOSE", "OPCODE_CONTROL_PING", "OPCODE_CONTROL_PONG", "OPCODE_FLAG_CONTROL", "OPCODE_TEXT", "PAYLOAD_BYTE_MAX", "PAYLOAD_LONG", "PAYLOAD_SHORT", "PAYLOAD_SHORT_MAX", "acceptHeader", "key", "closeCodeExceptionMessage", "code", "toggleMask", "", "cursor", "Lokio/Buffer$UnsafeCursor;", "", "validateCloseCode", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
public final class WebSocketProtocol {
    public static final String ACCEPT_MAGIC = "258EAFA5-E914-47DA-95CA-C5AB0DC85B11";
    public static final int B0_FLAG_FIN = 0x80;
    public static final int B0_FLAG_RSV1 = 0x40;
    public static final int B0_FLAG_RSV2 = 0x20;
    public static final int B0_FLAG_RSV3 = 16;
    public static final int B0_MASK_OPCODE = 15;
    public static final int B1_FLAG_MASK = 0x80;
    public static final int B1_MASK_LENGTH = 0x7F;
    public static final int CLOSE_CLIENT_GOING_AWAY = 1001;
    public static final long CLOSE_MESSAGE_MAX = 0x7BL;
    public static final int CLOSE_NO_STATUS_CODE = 1005;
    public static final WebSocketProtocol INSTANCE = null;
    public static final int OPCODE_BINARY = 2;
    public static final int OPCODE_CONTINUATION = 0;
    public static final int OPCODE_CONTROL_CLOSE = 8;
    public static final int OPCODE_CONTROL_PING = 9;
    public static final int OPCODE_CONTROL_PONG = 10;
    public static final int OPCODE_FLAG_CONTROL = 8;
    public static final int OPCODE_TEXT = 1;
    public static final long PAYLOAD_BYTE_MAX = 0x7DL;
    public static final int PAYLOAD_LONG = 0x7F;
    public static final int PAYLOAD_SHORT = 0x7E;
    public static final long PAYLOAD_SHORT_MAX = 0xFFFFL;

    static {
        WebSocketProtocol.INSTANCE = new WebSocketProtocol();
    }

    public final String acceptHeader(String s) {
        Intrinsics.checkNotNullParameter(s, "key");
        return ByteString.Companion.encodeUtf8(s + "258EAFA5-E914-47DA-95CA-C5AB0DC85B11").sha1().base64();
    }

    public final String closeCodeExceptionMessage(int v) {
        if(v >= 1000 && v < 5000) {
            return (1004 > v || v >= 1007) && (0x3F7 > v || v >= 3000) ? null : "Code " + v + " is reserved and may not be used.";
        }
        return "Code must be in range [1000,5000): " + v;
    }

    public final void toggleMask(UnsafeCursor buffer$UnsafeCursor0, byte[] arr_b) {
        Intrinsics.checkNotNullParameter(buffer$UnsafeCursor0, "cursor");
        Intrinsics.checkNotNullParameter(arr_b, "key");
        do {
            byte[] arr_b1 = buffer$UnsafeCursor0.data;
            int v1 = buffer$UnsafeCursor0.start;
            int v2 = buffer$UnsafeCursor0.end;
            if(arr_b1 != null) {
                for(int v = 0; v1 < v2; v = v3 + 1) {
                    int v3 = v % arr_b.length;
                    arr_b1[v1] = (byte)(arr_b1[v1] ^ arr_b[v3]);
                    ++v1;
                }
            }
        }
        while(buffer$UnsafeCursor0.next() != -1);
    }

    public final void validateCloseCode(int v) {
        String s = this.closeCodeExceptionMessage(v);
        if(s == null) {
            return;
        }
        Intrinsics.checkNotNull(s);
        throw new IllegalArgumentException(s.toString());
    }
}

