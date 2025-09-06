package okhttp3.internal;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;
import okio.ByteString;

@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u001A\n\u0010\u0000\u001A\u00020\u0001*\u00020\u0002\u001A\n\u0010\u0003\u001A\u00020\u0004*\u00020\u0002\u001A\n\u0010\u0005\u001A\u00020\u0004*\u00020\u0002\u001A$\u0010\u0006\u001A\u00020\u0002*\u00020\u00072\b\u0010\b\u001A\u0004\u0018\u00010\t2\u0006\u0010\n\u001A\u00020\u000B2\u0006\u0010\f\u001A\u00020\u000B\u001A\u0014\u0010\u0006\u001A\u00020\u0002*\u00020\r2\b\u0010\b\u001A\u0004\u0018\u00010\t¨\u0006\u000E"}, d2 = {"commonContentLength", "", "Lokhttp3/RequestBody;", "commonIsDuplex", "", "commonIsOneShot", "commonToRequestBody", "", "contentType", "Lokhttp3/MediaType;", "offset", "", "byteCount", "Lokio/ByteString;", "okhttp"}, k = 2, mv = {1, 7, 1}, xi = 0x30)
public final class _RequestBodyCommonKt {
    public static final long commonContentLength(RequestBody requestBody0) {
        Intrinsics.checkNotNullParameter(requestBody0, "<this>");
        return -1L;
    }

    public static final boolean commonIsDuplex(RequestBody requestBody0) {
        Intrinsics.checkNotNullParameter(requestBody0, "<this>");
        return false;
    }

    public static final boolean commonIsOneShot(RequestBody requestBody0) {
        Intrinsics.checkNotNullParameter(requestBody0, "<this>");
        return false;
    }

    public static final RequestBody commonToRequestBody(ByteString byteString0, MediaType mediaType0) {
        Intrinsics.checkNotNullParameter(byteString0, "<this>");
        return new RequestBody() {
            @Override  // okhttp3.RequestBody
            public long contentLength() {
                return (long)byteString0.size();
            }

            @Override  // okhttp3.RequestBody
            public MediaType contentType() {
                return mediaType0;
            }

            @Override  // okhttp3.RequestBody
            public void writeTo(BufferedSink bufferedSink0) {
                Intrinsics.checkNotNullParameter(bufferedSink0, "sink");
                bufferedSink0.write(byteString0);
            }
        };
    }

    public static final RequestBody commonToRequestBody(byte[] arr_b, MediaType mediaType0, int v, int v1) {
        Intrinsics.checkNotNullParameter(arr_b, "<this>");
        _UtilCommonKt.checkOffsetAndCount(arr_b.length, v, v1);
        return new RequestBody() {
            @Override  // okhttp3.RequestBody
            public long contentLength() {
                return (long)v1;
            }

            @Override  // okhttp3.RequestBody
            public MediaType contentType() {
                return mediaType0;
            }

            @Override  // okhttp3.RequestBody
            public void writeTo(BufferedSink bufferedSink0) {
                Intrinsics.checkNotNullParameter(bufferedSink0, "sink");
                bufferedSink0.write(arr_b, v, v1);
            }
        };
    }
}

