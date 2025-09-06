package okhttp3.internal.http;

import java.io.IOException;
import java.net.ProtocolException;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.Protocol;
import okhttp3.Response;

@Metadata(d1 = {"\u0000\u001E\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0004\u0018\u0000 \n2\u00020\u0001:\u0001\nB\u001D\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0005\u0012\u0006\u0010\u0006\u001A\u00020\u0007¢\u0006\u0002\u0010\bJ\b\u0010\t\u001A\u00020\u0007H\u0016R\u0010\u0010\u0004\u001A\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001A\u00020\u00078\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0002\u001A\u00020\u00038\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u000B"}, d2 = {"Lokhttp3/internal/http/StatusLine;", "", "protocol", "Lokhttp3/Protocol;", "code", "", "message", "", "(Lokhttp3/Protocol;ILjava/lang/String;)V", "toString", "Companion", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
public final class StatusLine {
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000E\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u0006J\u000E\u0010\u0007\u001A\u00020\u00042\u0006\u0010\b\u001A\u00020\t¨\u0006\n"}, d2 = {"Lokhttp3/internal/http/StatusLine$Companion;", "", "()V", "get", "Lokhttp3/internal/http/StatusLine;", "response", "Lokhttp3/Response;", "parse", "statusLine", "", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final StatusLine get(Response response0) {
            Intrinsics.checkNotNullParameter(response0, "response");
            return new StatusLine(response0.protocol(), response0.code(), response0.message());
        }

        public final StatusLine parse(String s) throws IOException {
            Protocol protocol0;
            int v = 9;
            Intrinsics.checkNotNullParameter(s, "statusLine");
            if(StringsKt.startsWith$default(s, "HTTP/1.", false, 2, null)) {
                if(s.length() < 9 || s.charAt(8) != 0x20) {
                    throw new ProtocolException("Unexpected status line: " + s);
                }
                switch(s.charAt(7) - 0x30) {
                    case 0: {
                        protocol0 = Protocol.HTTP_1_0;
                        break;
                    }
                    case 1: {
                        protocol0 = Protocol.HTTP_1_1;
                        break;
                    }
                    default: {
                        throw new ProtocolException("Unexpected status line: " + s);
                    }
                }
            }
            else if(StringsKt.startsWith$default(s, "ICY ", false, 2, null)) {
                protocol0 = Protocol.HTTP_1_0;
                v = 4;
            }
            else if(StringsKt.startsWith$default(s, "SOURCETABLE ", false, 2, null)) {
                protocol0 = Protocol.HTTP_1_1;
                v = 12;
            }
            else {
                throw new ProtocolException("Unexpected status line: " + s);
            }
            if(s.length() < v + 3) {
                throw new ProtocolException("Unexpected status line: " + s);
            }
            String s1 = s.substring(v, v + 3);
            Intrinsics.checkNotNullExpressionValue(s1, "this as java.lang.String…ing(startIndex, endIndex)");
            Integer integer0 = StringsKt.toIntOrNull(s1);
            if(integer0 == null) {
                throw new ProtocolException("Unexpected status line: " + s);
            }
            int v1 = (int)integer0;
            if(s.length() > v + 3) {
                if(s.charAt(v + 3) != 0x20) {
                    throw new ProtocolException("Unexpected status line: " + s);
                }
                String s2 = s.substring(v + 4);
                Intrinsics.checkNotNullExpressionValue(s2, "this as java.lang.String).substring(startIndex)");
                return new StatusLine(protocol0, v1, s2);
            }
            return new StatusLine(protocol0, v1, "");
        }
    }

    public static final Companion Companion;
    public final int code;
    public final String message;
    public final Protocol protocol;

    static {
        StatusLine.Companion = new Companion(null);
    }

    public StatusLine(Protocol protocol0, int v, String s) {
        Intrinsics.checkNotNullParameter(protocol0, "protocol");
        Intrinsics.checkNotNullParameter(s, "message");
        super();
        this.protocol = protocol0;
        this.code = v;
        this.message = s;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder0 = new StringBuilder();
        if(this.protocol == Protocol.HTTP_1_0) {
            stringBuilder0.append("HTTP/1.0");
        }
        else {
            stringBuilder0.append("HTTP/1.1");
        }
        stringBuilder0.append(' ');
        stringBuilder0.append(this.code);
        stringBuilder0.append(' ');
        stringBuilder0.append(this.message);
        String s = stringBuilder0.toString();
        Intrinsics.checkNotNullExpressionValue(s, "StringBuilder().apply(builderAction).toString()");
        return s;
    }
}

