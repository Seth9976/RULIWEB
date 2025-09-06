package okhttp3.internal.http2;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.internal._UtilJvmKt;
import okio.ByteString;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000E\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u001A\n\u0002\u0010\u000B\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\b\u00C6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00A2\u0006\u0002\u0010\u0002J\u0016\u0010\u001F\u001A\u00020\u00052\u0006\u0010 \u001A\u00020\u000B2\u0006\u0010!\u001A\u00020\u000BJ\u0015\u0010\"\u001A\u00020\u00052\u0006\u0010 \u001A\u00020\u000BH\u0000\u00A2\u0006\u0002\b#J.\u0010$\u001A\u00020\u00052\u0006\u0010%\u001A\u00020&2\u0006\u0010\'\u001A\u00020\u000B2\u0006\u0010(\u001A\u00020\u000B2\u0006\u0010 \u001A\u00020\u000B2\u0006\u0010!\u001A\u00020\u000BJ&\u0010)\u001A\u00020\u00052\u0006\u0010%\u001A\u00020&2\u0006\u0010\'\u001A\u00020\u000B2\u0006\u0010(\u001A\u00020\u000B2\u0006\u0010*\u001A\u00020+R\u0016\u0010\u0003\u001A\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00A2\u0006\u0004\n\u0002\u0010\u0006R\u0010\u0010\u0007\u001A\u00020\b8\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0018\u0010\t\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0004X\u0082\u0004\u00A2\u0006\u0004\n\u0002\u0010\u0006R\u000E\u0010\n\u001A\u00020\u000BX\u0086T\u00A2\u0006\u0002\n\u0000R\u000E\u0010\f\u001A\u00020\u000BX\u0086T\u00A2\u0006\u0002\n\u0000R\u000E\u0010\r\u001A\u00020\u000BX\u0086T\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u000E\u001A\u00020\u000BX\u0086T\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u000F\u001A\u00020\u000BX\u0086T\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u0010\u001A\u00020\u000BX\u0086T\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u0011\u001A\u00020\u000BX\u0086T\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u0012\u001A\u00020\u000BX\u0086T\u00A2\u0006\u0002\n\u0000R\u0016\u0010\u0013\u001A\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00A2\u0006\u0004\n\u0002\u0010\u0006R\u000E\u0010\u0014\u001A\u00020\u000BX\u0086T\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u0015\u001A\u00020\u000BX\u0086T\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u0016\u001A\u00020\u000BX\u0086T\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u0017\u001A\u00020\u000BX\u0086T\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u0018\u001A\u00020\u000BX\u0086T\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u0019\u001A\u00020\u000BX\u0086T\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u001A\u001A\u00020\u000BX\u0086T\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u001B\u001A\u00020\u000BX\u0086T\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u001C\u001A\u00020\u000BX\u0086T\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u001D\u001A\u00020\u000BX\u0086T\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u001E\u001A\u00020\u000BX\u0086T\u00A2\u0006\u0002\n\u0000\u00A8\u0006,"}, d2 = {"Lokhttp3/internal/http2/Http2;", "", "()V", "BINARY", "", "", "[Ljava/lang/String;", "CONNECTION_PREFACE", "Lokio/ByteString;", "FLAGS", "FLAG_ACK", "", "FLAG_COMPRESSED", "FLAG_END_HEADERS", "FLAG_END_PUSH_PROMISE", "FLAG_END_STREAM", "FLAG_NONE", "FLAG_PADDED", "FLAG_PRIORITY", "FRAME_NAMES", "INITIAL_MAX_FRAME_SIZE", "TYPE_CONTINUATION", "TYPE_DATA", "TYPE_GOAWAY", "TYPE_HEADERS", "TYPE_PING", "TYPE_PRIORITY", "TYPE_PUSH_PROMISE", "TYPE_RST_STREAM", "TYPE_SETTINGS", "TYPE_WINDOW_UPDATE", "formatFlags", "type", "flags", "formattedType", "formattedType$okhttp", "frameLog", "inbound", "", "streamId", "length", "frameLogWindowUpdate", "windowSizeIncrement", "", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
public final class Http2 {
    private static final String[] BINARY = null;
    public static final ByteString CONNECTION_PREFACE = null;
    private static final String[] FLAGS = null;
    public static final int FLAG_ACK = 1;
    public static final int FLAG_COMPRESSED = 0x20;
    public static final int FLAG_END_HEADERS = 4;
    public static final int FLAG_END_PUSH_PROMISE = 4;
    public static final int FLAG_END_STREAM = 1;
    public static final int FLAG_NONE = 0;
    public static final int FLAG_PADDED = 8;
    public static final int FLAG_PRIORITY = 0x20;
    private static final String[] FRAME_NAMES = null;
    public static final int INITIAL_MAX_FRAME_SIZE = 0x4000;
    public static final Http2 INSTANCE = null;
    public static final int TYPE_CONTINUATION = 9;
    public static final int TYPE_DATA = 0;
    public static final int TYPE_GOAWAY = 7;
    public static final int TYPE_HEADERS = 1;
    public static final int TYPE_PING = 6;
    public static final int TYPE_PRIORITY = 2;
    public static final int TYPE_PUSH_PROMISE = 5;
    public static final int TYPE_RST_STREAM = 3;
    public static final int TYPE_SETTINGS = 4;
    public static final int TYPE_WINDOW_UPDATE = 8;

    static {
        Http2.INSTANCE = new Http2();
        Http2.CONNECTION_PREFACE = ByteString.Companion.encodeUtf8("PRI * HTTP/2.0\r\n\r\nSM\r\n\r\n");
        Http2.FRAME_NAMES = new String[]{"DATA", "HEADERS", "PRIORITY", "RST_STREAM", "SETTINGS", "PUSH_PROMISE", "PING", "GOAWAY", "WINDOW_UPDATE", "CONTINUATION"};
        Http2.FLAGS = new String[0x40];
        String[] arr_s = new String[0x100];
        for(int v1 = 0; v1 < 0x100; ++v1) {
            String s = Integer.toBinaryString(v1);
            Intrinsics.checkNotNullExpressionValue(s, "toBinaryString(it)");
            arr_s[v1] = StringsKt.replace$default(_UtilJvmKt.format("%8s", new Object[]{s}), ' ', '0', false, 4, null);
        }
        Http2.BINARY = arr_s;
        Http2.FLAGS[0] = "";
        Http2.FLAGS[1] = "END_STREAM";
        Http2.FLAGS[8] = "PADDED";
        Http2.FLAGS[9] = Http2.FLAGS[1] + "|PADDED";
        Http2.FLAGS[4] = "END_HEADERS";
        Http2.FLAGS[0x20] = "PRIORITY";
        Http2.FLAGS[36] = "END_HEADERS|PRIORITY";
        for(int v2 = 0; v2 < 3; ++v2) {
            int v3 = new int[]{4, 0x20, 36}[v2];
            Http2.FLAGS[1 | v3] = Http2.FLAGS[1] + '|' + Http2.FLAGS[v3];
            Http2.FLAGS[1 | v3 | 8] = Http2.FLAGS[1] + '|' + Http2.FLAGS[v3] + "|PADDED";
        }
        for(int v = 0; v < Http2.FLAGS.length; ++v) {
            String[] arr_s1 = Http2.FLAGS;
            if(arr_s1[v] == null) {
                arr_s1[v] = Http2.BINARY[v];
            }
        }
    }

    public final String formatFlags(int v, int v1) {
        String s;
        if(v1 == 0) {
            return "";
        }
        switch(v) {
            case 4: 
            case 6: {
                return v1 == 1 ? "ACK" : Http2.BINARY[v1];
            }
            case 2: 
            case 3: 
            case 7: 
            case 8: {
                return Http2.BINARY[v1];
            }
            default: {
                String[] arr_s = Http2.FLAGS;
                if(v1 < arr_s.length) {
                    s = arr_s[v1];
                    Intrinsics.checkNotNull(s);
                }
                else {
                    s = Http2.BINARY[v1];
                }
                if(v == 5 && (v1 & 4) != 0) {
                    return StringsKt.replace$default(s, "HEADERS", "PUSH_PROMISE", false, 4, null);
                }
                return v != 0 || (v1 & 0x20) == 0 ? s : StringsKt.replace$default(s, "PRIORITY", "COMPRESSED", false, 4, null);
            }
        }
    }

    public final String formattedType$okhttp(int v) [...] // 潜在的解密器

    public final String frameLog(boolean z, int v, int v1, int v2, int v3) {
        String s = this.formattedType$okhttp(v2);
        String s1 = this.formatFlags(v2, v3);
        return z ? _UtilJvmKt.format("%s 0x%08x %5d %-13s %s", new Object[]{"<<", v, v1, s, s1}) : _UtilJvmKt.format("%s 0x%08x %5d %-13s %s", new Object[]{">>", v, v1, s, s1});
    }

    // 去混淆评级： 低(40)
    public final String frameLogWindowUpdate(boolean z, int v, int v1, long v2) {
        return z ? _UtilJvmKt.format("%s 0x%08x %5d %-13s %d", new Object[]{"<<", v, v1, "WINDOW_UPDATE", v2}) : _UtilJvmKt.format("%s 0x%08x %5d %-13s %d", new Object[]{">>", v, v1, "WINDOW_UPDATE", v2});
    }
}

