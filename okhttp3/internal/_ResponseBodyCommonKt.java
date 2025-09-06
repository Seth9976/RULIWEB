package okhttp3.internal;

import java.io.Closeable;
import java.io.IOException;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;

@Metadata(d1 = {"\u0000@\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u001A\u001E\u0010\u0000\u001A\u00020\u0001*\u00020\u00022\b\u0010\u0003\u001A\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001A\u00020\u0006H\u0000\u001A\f\u0010\u0007\u001A\u00020\b*\u00020\u0001H\u0000\u001A\f\u0010\t\u001A\u00020\n*\u00020\u0001H\u0000\u001A\f\u0010\u000B\u001A\u00020\f*\u00020\u0001H\u0000\u001AG\u0010\r\u001A\u0002H\u000E\"\b\b\u0000\u0010\u000E*\u00020\u000F*\u00020\u00012\u0012\u0010\u0010\u001A\u000E\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u0002H\u000E0\u00112\u0012\u0010\u0012\u001A\u000E\u0012\u0004\u0012\u0002H\u000E\u0012\u0004\u0012\u00020\u00130\u0011H\u0080\bø\u0001\u0000¢\u0006\u0002\u0010\u0014\u001A\u0016\u0010\u0015\u001A\u00020\u0001*\u00020\n2\b\u0010\u0003\u001A\u0004\u0018\u00010\u0004H\u0000\u001A\u0016\u0010\u0015\u001A\u00020\u0001*\u00020\b2\b\u0010\u0003\u001A\u0004\u0018\u00010\u0004H\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0016"}, d2 = {"commonAsResponseBody", "Lokhttp3/ResponseBody;", "Lokio/BufferedSource;", "contentType", "Lokhttp3/MediaType;", "contentLength", "", "commonByteString", "Lokio/ByteString;", "commonBytes", "", "commonClose", "", "commonConsumeSource", "T", "", "consumer", "Lkotlin/Function1;", "sizeMapper", "", "(Lokhttp3/ResponseBody;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "commonToResponseBody", "okhttp"}, k = 2, mv = {1, 7, 1}, xi = 0x30)
public final class _ResponseBodyCommonKt {
    public static final ResponseBody commonAsResponseBody(BufferedSource bufferedSource0, MediaType mediaType0, long v) {
        Intrinsics.checkNotNullParameter(bufferedSource0, "<this>");
        return new ResponseBody() {
            @Override  // okhttp3.ResponseBody
            public long contentLength() {
                return v;
            }

            @Override  // okhttp3.ResponseBody
            public MediaType contentType() {
                return mediaType0;
            }

            @Override  // okhttp3.ResponseBody
            public BufferedSource source() {
                return bufferedSource0;
            }
        };
    }

    public static final ByteString commonByteString(ResponseBody responseBody0) {
        ByteString byteString0;
        Intrinsics.checkNotNullParameter(responseBody0, "<this>");
        long v = responseBody0.contentLength();
        if(v > 0x7FFFFFFFL) {
            throw new IOException("Cannot buffer entire body for content length: " + v);
        }
        Closeable closeable0 = responseBody0.source();
        Throwable throwable0 = null;
        try {
            byteString0 = ((BufferedSource)closeable0).readByteString();
        }
        catch(Throwable throwable1) {
            byteString0 = null;
            throwable0 = throwable1;
        }
        if(closeable0 != null) {
            try {
                closeable0.close();
            }
            catch(Throwable throwable2) {
                if(throwable0 == null) {
                    throwable0 = throwable2;
                }
                else {
                    ExceptionsKt.addSuppressed(throwable0, throwable2);
                }
            }
        }
        if(throwable0 != null) {
            throw throwable0;
        }
        Intrinsics.checkNotNull(byteString0);
        int v1 = byteString0.size();
        if(v != -1L && v != ((long)v1)) {
            throw new IOException("Content-Length (" + v + ") and stream length (" + v1 + ") disagree");
        }
        return byteString0;
    }

    public static final byte[] commonBytes(ResponseBody responseBody0) {
        byte[] arr_b;
        Intrinsics.checkNotNullParameter(responseBody0, "<this>");
        long v = responseBody0.contentLength();
        if(v > 0x7FFFFFFFL) {
            throw new IOException("Cannot buffer entire body for content length: " + v);
        }
        Closeable closeable0 = responseBody0.source();
        Throwable throwable0 = null;
        try {
            arr_b = ((BufferedSource)closeable0).readByteArray();
        }
        catch(Throwable throwable1) {
            arr_b = null;
            throwable0 = throwable1;
        }
        if(closeable0 != null) {
            try {
                closeable0.close();
            }
            catch(Throwable throwable2) {
                if(throwable0 == null) {
                    throwable0 = throwable2;
                }
                else {
                    ExceptionsKt.addSuppressed(throwable0, throwable2);
                }
            }
        }
        if(throwable0 != null) {
            throw throwable0;
        }
        Intrinsics.checkNotNull(arr_b);
        if(v != -1L && v != ((long)arr_b.length)) {
            throw new IOException("Content-Length (" + v + ") and stream length (" + arr_b.length + ") disagree");
        }
        return arr_b;
    }

    public static final void commonClose(ResponseBody responseBody0) {
        Intrinsics.checkNotNullParameter(responseBody0, "<this>");
        _UtilCommonKt.closeQuietly(responseBody0.source());
    }

    public static final Object commonConsumeSource(ResponseBody responseBody0, Function1 function10, Function1 function11) {
        Object object0;
        Intrinsics.checkNotNullParameter(responseBody0, "<this>");
        Intrinsics.checkNotNullParameter(function10, "consumer");
        Intrinsics.checkNotNullParameter(function11, "sizeMapper");
        long v = responseBody0.contentLength();
        if(v > 0x7FFFFFFFL) {
            throw new IOException("Cannot buffer entire body for content length: " + v);
        }
        Closeable closeable0 = responseBody0.source();
        Throwable throwable0 = null;
        try {
            object0 = function10.invoke(closeable0);
        }
        catch(Throwable throwable1) {
            throwable0 = throwable1;
            object0 = null;
        }
        if(closeable0 != null) {
            try {
                closeable0.close();
            }
            catch(Throwable throwable2) {
                if(throwable0 == null) {
                    throwable0 = throwable2;
                }
                else {
                    ExceptionsKt.addSuppressed(throwable0, throwable2);
                }
            }
        }
        if(throwable0 != null) {
            throw throwable0;
        }
        Intrinsics.checkNotNull(object0);
        int v1 = ((Number)function11.invoke(object0)).intValue();
        if(v != -1L && v != ((long)v1)) {
            throw new IOException("Content-Length (" + v + ") and stream length (" + v1 + ") disagree");
        }
        return object0;
    }

    public static final ResponseBody commonToResponseBody(ByteString byteString0, MediaType mediaType0) {
        Intrinsics.checkNotNullParameter(byteString0, "<this>");
        BufferedSource bufferedSource0 = new Buffer().write(byteString0);
        return ResponseBody.Companion.create(bufferedSource0, mediaType0, ((long)byteString0.size()));
    }

    public static final ResponseBody commonToResponseBody(byte[] arr_b, MediaType mediaType0) {
        Intrinsics.checkNotNullParameter(arr_b, "<this>");
        BufferedSource bufferedSource0 = new Buffer().write(arr_b);
        return ResponseBody.Companion.create(bufferedSource0, mediaType0, ((long)arr_b.length));
    }
}

