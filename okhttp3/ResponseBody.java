package okhttp3;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.Internal;
import okhttp3.internal._ResponseBodyCommonKt;
import okhttp3.internal._UtilJvmKt;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;

@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0003\b&\u0018\u0000 \u00192\u00020\u0001:\u0002\u0018\u0019B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0005\u001A\u00020\u0006J\u0006\u0010\u0007\u001A\u00020\bJ\u0006\u0010\t\u001A\u00020\nJ\u0006\u0010\u000B\u001A\u00020\u0004J\b\u0010\f\u001A\u00020\rH\u0002J\b\u0010\u000E\u001A\u00020\u000FH\u0016J\b\u0010\u0010\u001A\u00020\u0011H&J\n\u0010\u0012\u001A\u0004\u0018\u00010\u0013H&J\b\u0010\u0014\u001A\u00020\u0015H&J\u0006\u0010\u0016\u001A\u00020\u0017R\u0010\u0010\u0003\u001A\u0004\u0018\u00010\u0004X\u0082\u000E¢\u0006\u0002\n\u0000¨\u0006\u001A"}, d2 = {"Lokhttp3/ResponseBody;", "Ljava/io/Closeable;", "()V", "reader", "Ljava/io/Reader;", "byteStream", "Ljava/io/InputStream;", "byteString", "Lokio/ByteString;", "bytes", "", "charStream", "charset", "Ljava/nio/charset/Charset;", "close", "", "contentLength", "", "contentType", "Lokhttp3/MediaType;", "source", "Lokio/BufferedSource;", "string", "", "BomAwareReader", "Companion", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
public abstract class ResponseBody implements Closeable, AutoCloseable {
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0019\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\n\u001A\u00020\u000BH\u0016J \u0010\f\u001A\u00020\r2\u0006\u0010\u000E\u001A\u00020\u000F2\u0006\u0010\u0010\u001A\u00020\r2\u0006\u0010\u0011\u001A\u00020\rH\u0016R\u000E\u0010\u0004\u001A\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0007\u001A\u00020\bX\u0082\u000E¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001A\u0004\u0018\u00010\u0001X\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\u0002\u001A\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lokhttp3/ResponseBody$BomAwareReader;", "Ljava/io/Reader;", "source", "Lokio/BufferedSource;", "charset", "Ljava/nio/charset/Charset;", "(Lokio/BufferedSource;Ljava/nio/charset/Charset;)V", "closed", "", "delegate", "close", "", "read", "", "cbuf", "", "off", "len", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    public static final class BomAwareReader extends Reader implements AutoCloseable {
        private final Charset charset;
        private boolean closed;
        private Reader delegate;
        private final BufferedSource source;

        public BomAwareReader(BufferedSource bufferedSource0, Charset charset0) {
            Intrinsics.checkNotNullParameter(bufferedSource0, "source");
            Intrinsics.checkNotNullParameter(charset0, "charset");
            super();
            this.source = bufferedSource0;
            this.charset = charset0;
        }

        @Override
        public void close() throws IOException {
            Unit unit0;
            this.closed = true;
            Reader reader0 = this.delegate;
            if(reader0 == null) {
                unit0 = null;
            }
            else {
                reader0.close();
                unit0 = Unit.INSTANCE;
            }
            if(unit0 == null) {
                this.source.close();
            }
        }

        @Override
        public int read(char[] arr_c, int v, int v1) throws IOException {
            Intrinsics.checkNotNullParameter(arr_c, "cbuf");
            if(this.closed) {
                throw new IOException("Stream closed");
            }
            Reader reader0 = this.delegate;
            if(reader0 == null) {
                reader0 = new InputStreamReader(this.source.inputStream(), _UtilJvmKt.readBomAsCharset(this.source, this.charset));
                this.delegate = reader0;
            }
            return reader0.read(arr_c, v, v1);
        }
    }

    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\u0010\u000E\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001A\u0010\u0003\u001A\u00020\u00042\b\u0010\u0005\u001A\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001A\u00020\bH\u0007J\"\u0010\u0003\u001A\u00020\u00042\b\u0010\u0005\u001A\u0004\u0018\u00010\u00062\u0006\u0010\t\u001A\u00020\n2\u0006\u0010\u0007\u001A\u00020\u000BH\u0007J\u001A\u0010\u0003\u001A\u00020\u00042\b\u0010\u0005\u001A\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001A\u00020\fH\u0007J\u001A\u0010\u0003\u001A\u00020\u00042\b\u0010\u0005\u001A\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001A\u00020\rH\u0007J\'\u0010\u000E\u001A\u00020\u0004*\u00020\u000B2\n\b\u0002\u0010\u0005\u001A\u0004\u0018\u00010\u00062\b\b\u0002\u0010\t\u001A\u00020\nH\u0007¢\u0006\u0002\b\u0003J\u001D\u0010\u000F\u001A\u00020\u0004*\u00020\b2\n\b\u0002\u0010\u0005\u001A\u0004\u0018\u00010\u0006H\u0007¢\u0006\u0002\b\u0003J\u001D\u0010\u000F\u001A\u00020\u0004*\u00020\f2\n\b\u0002\u0010\u0005\u001A\u0004\u0018\u00010\u0006H\u0007¢\u0006\u0002\b\u0003J\u001D\u0010\u000F\u001A\u00020\u0004*\u00020\r2\n\b\u0002\u0010\u0005\u001A\u0004\u0018\u00010\u0006H\u0007¢\u0006\u0002\b\u0003¨\u0006\u0010"}, d2 = {"Lokhttp3/ResponseBody$Companion;", "", "()V", "create", "Lokhttp3/ResponseBody;", "contentType", "Lokhttp3/MediaType;", "content", "", "contentLength", "", "Lokio/BufferedSource;", "", "Lokio/ByteString;", "asResponseBody", "toResponseBody", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        @JvmStatic
        public final ResponseBody create(String s, MediaType mediaType0) {
            Intrinsics.checkNotNullParameter(s, "<this>");
            Pair pair0 = Internal.chooseCharset(mediaType0);
            Buffer buffer0 = new Buffer().writeString(s, ((Charset)pair0.component1()));
            return this.create(buffer0, ((MediaType)pair0.component2()), buffer0.size());
        }

        @Deprecated(level = DeprecationLevel.WARNING, message = "Moved to extension function. Put the \'content\' argument first to fix Java", replaceWith = @ReplaceWith(expression = "content.asResponseBody(contentType, contentLength)", imports = {"okhttp3.ResponseBody.Companion.asResponseBody"}))
        @JvmStatic
        public final ResponseBody create(MediaType mediaType0, long v, BufferedSource bufferedSource0) {
            Intrinsics.checkNotNullParameter(bufferedSource0, "content");
            return this.create(bufferedSource0, mediaType0, v);
        }

        @Deprecated(level = DeprecationLevel.WARNING, message = "Moved to extension function. Put the \'content\' argument first to fix Java", replaceWith = @ReplaceWith(expression = "content.toResponseBody(contentType)", imports = {"okhttp3.ResponseBody.Companion.toResponseBody"}))
        @JvmStatic
        public final ResponseBody create(MediaType mediaType0, String s) {
            Intrinsics.checkNotNullParameter(s, "content");
            return this.create(s, mediaType0);
        }

        @Deprecated(level = DeprecationLevel.WARNING, message = "Moved to extension function. Put the \'content\' argument first to fix Java", replaceWith = @ReplaceWith(expression = "content.toResponseBody(contentType)", imports = {"okhttp3.ResponseBody.Companion.toResponseBody"}))
        @JvmStatic
        public final ResponseBody create(MediaType mediaType0, ByteString byteString0) {
            Intrinsics.checkNotNullParameter(byteString0, "content");
            return this.create(byteString0, mediaType0);
        }

        @Deprecated(level = DeprecationLevel.WARNING, message = "Moved to extension function. Put the \'content\' argument first to fix Java", replaceWith = @ReplaceWith(expression = "content.toResponseBody(contentType)", imports = {"okhttp3.ResponseBody.Companion.toResponseBody"}))
        @JvmStatic
        public final ResponseBody create(MediaType mediaType0, byte[] arr_b) {
            Intrinsics.checkNotNullParameter(arr_b, "content");
            return this.create(arr_b, mediaType0);
        }

        @JvmStatic
        public final ResponseBody create(BufferedSource bufferedSource0, MediaType mediaType0, long v) {
            Intrinsics.checkNotNullParameter(bufferedSource0, "<this>");
            return _ResponseBodyCommonKt.commonAsResponseBody(bufferedSource0, mediaType0, v);
        }

        @JvmStatic
        public final ResponseBody create(ByteString byteString0, MediaType mediaType0) {
            Intrinsics.checkNotNullParameter(byteString0, "<this>");
            return _ResponseBodyCommonKt.commonToResponseBody(byteString0, mediaType0);
        }

        @JvmStatic
        public final ResponseBody create(byte[] arr_b, MediaType mediaType0) {
            Intrinsics.checkNotNullParameter(arr_b, "<this>");
            return _ResponseBodyCommonKt.commonToResponseBody(arr_b, mediaType0);
        }

        public static ResponseBody create$default(Companion responseBody$Companion0, String s, MediaType mediaType0, int v, Object object0) {
            if((v & 1) != 0) {
                mediaType0 = null;
            }
            return responseBody$Companion0.create(s, mediaType0);
        }

        public static ResponseBody create$default(Companion responseBody$Companion0, BufferedSource bufferedSource0, MediaType mediaType0, long v, int v1, Object object0) {
            if((v1 & 1) != 0) {
                mediaType0 = null;
            }
            if((v1 & 2) != 0) {
                v = -1L;
            }
            return responseBody$Companion0.create(bufferedSource0, mediaType0, v);
        }

        public static ResponseBody create$default(Companion responseBody$Companion0, ByteString byteString0, MediaType mediaType0, int v, Object object0) {
            if((v & 1) != 0) {
                mediaType0 = null;
            }
            return responseBody$Companion0.create(byteString0, mediaType0);
        }

        public static ResponseBody create$default(Companion responseBody$Companion0, byte[] arr_b, MediaType mediaType0, int v, Object object0) {
            if((v & 1) != 0) {
                mediaType0 = null;
            }
            return responseBody$Companion0.create(arr_b, mediaType0);
        }
    }

    public static final Companion Companion;
    private Reader reader;

    static {
        ResponseBody.Companion = new Companion(null);
    }

    public final InputStream byteStream() {
        return this.source().inputStream();
    }

    public final ByteString byteString() throws IOException {
        return _ResponseBodyCommonKt.commonByteString(this);
    }

    public final byte[] bytes() throws IOException {
        return _ResponseBodyCommonKt.commonBytes(this);
    }

    public final Reader charStream() {
        Reader reader0 = this.reader;
        if(reader0 == null) {
            reader0 = new BomAwareReader(this.source(), this.charset());
            this.reader = reader0;
        }
        return reader0;
    }

    private final Charset charset() {
        return Internal.charset$default(this.contentType(), null, 1, null);
    }

    @Override
    public void close() {
        _ResponseBodyCommonKt.commonClose(this);
    }

    public abstract long contentLength();

    public abstract MediaType contentType();

    @JvmStatic
    public static final ResponseBody create(String s, MediaType mediaType0) {
        return ResponseBody.Companion.create(s, mediaType0);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Moved to extension function. Put the \'content\' argument first to fix Java", replaceWith = @ReplaceWith(expression = "content.asResponseBody(contentType, contentLength)", imports = {"okhttp3.ResponseBody.Companion.asResponseBody"}))
    @JvmStatic
    public static final ResponseBody create(MediaType mediaType0, long v, BufferedSource bufferedSource0) {
        return ResponseBody.Companion.create(mediaType0, v, bufferedSource0);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Moved to extension function. Put the \'content\' argument first to fix Java", replaceWith = @ReplaceWith(expression = "content.toResponseBody(contentType)", imports = {"okhttp3.ResponseBody.Companion.toResponseBody"}))
    @JvmStatic
    public static final ResponseBody create(MediaType mediaType0, String s) {
        return ResponseBody.Companion.create(mediaType0, s);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Moved to extension function. Put the \'content\' argument first to fix Java", replaceWith = @ReplaceWith(expression = "content.toResponseBody(contentType)", imports = {"okhttp3.ResponseBody.Companion.toResponseBody"}))
    @JvmStatic
    public static final ResponseBody create(MediaType mediaType0, ByteString byteString0) {
        return ResponseBody.Companion.create(mediaType0, byteString0);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Moved to extension function. Put the \'content\' argument first to fix Java", replaceWith = @ReplaceWith(expression = "content.toResponseBody(contentType)", imports = {"okhttp3.ResponseBody.Companion.toResponseBody"}))
    @JvmStatic
    public static final ResponseBody create(MediaType mediaType0, byte[] arr_b) {
        return ResponseBody.Companion.create(mediaType0, arr_b);
    }

    @JvmStatic
    public static final ResponseBody create(BufferedSource bufferedSource0, MediaType mediaType0, long v) {
        return ResponseBody.Companion.create(bufferedSource0, mediaType0, v);
    }

    @JvmStatic
    public static final ResponseBody create(ByteString byteString0, MediaType mediaType0) {
        return ResponseBody.Companion.create(byteString0, mediaType0);
    }

    @JvmStatic
    public static final ResponseBody create(byte[] arr_b, MediaType mediaType0) {
        return ResponseBody.Companion.create(arr_b, mediaType0);
    }

    public abstract BufferedSource source();

    public final String string() throws IOException {
        String s;
        Closeable closeable0 = this.source();
        try {
            s = ((BufferedSource)closeable0).readString(_UtilJvmKt.readBomAsCharset(((BufferedSource)closeable0), this.charset()));
        }
        catch(Throwable throwable0) {
            CloseableKt.closeFinally(closeable0, throwable0);
            throw throwable0;
        }
        CloseableKt.closeFinally(closeable0, null);
        return s;
    }
}

