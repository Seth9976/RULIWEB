package okhttp3;

import java.io.Closeable;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ReplaceWith;
import kotlin.io.CloseableKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.Internal;
import okhttp3.internal._RequestBodyCommonKt;
import okio.BufferedSink;
import okio.ByteString;
import okio.FileSystem;
import okio.GzipSink;
import okio.Okio;
import okio.Path;
import okio.Source;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u0000 \u000E2\u00020\u0001:\u0001\u000EB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001A\u00020\u0004H\u0016J\n\u0010\u0005\u001A\u0004\u0018\u00010\u0006H&J\b\u0010\u0007\u001A\u00020\bH\u0016J\b\u0010\t\u001A\u00020\bH\u0016J\u0010\u0010\n\u001A\u00020\u000B2\u0006\u0010\f\u001A\u00020\rH&¨\u0006\u000F"}, d2 = {"Lokhttp3/RequestBody;", "", "()V", "contentLength", "", "contentType", "Lokhttp3/MediaType;", "isDuplex", "", "isOneShot", "writeTo", "", "sink", "Lokio/BufferedSink;", "Companion", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
public abstract class RequestBody {
    @Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000E\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001A\u0010\u0003\u001A\u00020\u00042\b\u0010\u0005\u001A\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001A\u00020\bH\u0007J.\u0010\u0003\u001A\u00020\u00042\b\u0010\u0005\u001A\u0004\u0018\u00010\u00062\u0006\u0010\t\u001A\u00020\n2\b\b\u0002\u0010\u000B\u001A\u00020\f2\b\b\u0002\u0010\r\u001A\u00020\fH\u0007J\u001A\u0010\u0003\u001A\u00020\u00042\b\u0010\u0005\u001A\u0004\u0018\u00010\u00062\u0006\u0010\t\u001A\u00020\u000EH\u0007J\u001A\u0010\u0003\u001A\u00020\u00042\b\u0010\u0005\u001A\u0004\u0018\u00010\u00062\u0006\u0010\t\u001A\u00020\u000FH\u0007J\u001D\u0010\u0010\u001A\u00020\u0004*\u00020\b2\n\b\u0002\u0010\u0005\u001A\u0004\u0018\u00010\u0006H\u0007¢\u0006\u0002\b\u0003J%\u0010\u0010\u001A\u00020\u0004*\u00020\u00112\u0006\u0010\u0012\u001A\u00020\u00132\n\b\u0002\u0010\u0005\u001A\u0004\u0018\u00010\u0006H\u0007¢\u0006\u0002\b\u0003J\f\u0010\u0014\u001A\u00020\u0004*\u00020\u0004H\u0007J\u001D\u0010\u0015\u001A\u00020\u0004*\u00020\u00162\n\b\u0002\u0010\u0005\u001A\u0004\u0018\u00010\u0006H\u0007¢\u0006\u0002\b\u0003J1\u0010\u0015\u001A\u00020\u0004*\u00020\n2\n\b\u0002\u0010\u0005\u001A\u0004\u0018\u00010\u00062\b\b\u0002\u0010\u000B\u001A\u00020\f2\b\b\u0002\u0010\r\u001A\u00020\fH\u0007¢\u0006\u0002\b\u0003J\u001D\u0010\u0015\u001A\u00020\u0004*\u00020\u000E2\n\b\u0002\u0010\u0005\u001A\u0004\u0018\u00010\u0006H\u0007¢\u0006\u0002\b\u0003J\u001D\u0010\u0015\u001A\u00020\u0004*\u00020\u000F2\n\b\u0002\u0010\u0005\u001A\u0004\u0018\u00010\u0006H\u0007¢\u0006\u0002\b\u0003¨\u0006\u0017"}, d2 = {"Lokhttp3/RequestBody$Companion;", "", "()V", "create", "Lokhttp3/RequestBody;", "contentType", "Lokhttp3/MediaType;", "file", "Ljava/io/File;", "content", "", "offset", "", "byteCount", "", "Lokio/ByteString;", "asRequestBody", "Lokio/Path;", "fileSystem", "Lokio/FileSystem;", "gzip", "toRequestBody", "Ljava/io/FileDescriptor;", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        @JvmStatic
        public final RequestBody create(File file0, MediaType mediaType0) {
            Intrinsics.checkNotNullParameter(file0, "<this>");
            return new RequestBody() {
                @Override  // okhttp3.RequestBody
                public long contentLength() {
                    return this.$this_asRequestBody.length();
                }

                @Override  // okhttp3.RequestBody
                public MediaType contentType() {
                    return file0;
                }

                @Override  // okhttp3.RequestBody
                public void writeTo(BufferedSink bufferedSink0) {
                    Intrinsics.checkNotNullParameter(bufferedSink0, "sink");
                    Closeable closeable0 = Okio.source(this.$this_asRequestBody);
                    try {
                        bufferedSink0.writeAll(((Source)closeable0));
                    }
                    catch(Throwable throwable0) {
                        CloseableKt.closeFinally(closeable0, throwable0);
                        throw throwable0;
                    }
                    CloseableKt.closeFinally(closeable0, null);
                }
            };
        }

        @JvmStatic
        public final RequestBody create(FileDescriptor fileDescriptor0, MediaType mediaType0) {
            Intrinsics.checkNotNullParameter(fileDescriptor0, "<this>");
            return new RequestBody() {
                @Override  // okhttp3.RequestBody
                public MediaType contentType() {
                    return fileDescriptor0;
                }

                @Override  // okhttp3.RequestBody
                public boolean isOneShot() {
                    return true;
                }

                @Override  // okhttp3.RequestBody
                public void writeTo(BufferedSink bufferedSink0) {
                    Intrinsics.checkNotNullParameter(bufferedSink0, "sink");
                    Closeable closeable0 = new FileInputStream(this.$this_toRequestBody);
                    try {
                        bufferedSink0.getBuffer().writeAll(Okio.source(((FileInputStream)closeable0)));
                    }
                    catch(Throwable throwable0) {
                        CloseableKt.closeFinally(closeable0, throwable0);
                        throw throwable0;
                    }
                    CloseableKt.closeFinally(closeable0, null);
                }
            };
        }

        @JvmStatic
        public final RequestBody create(String s, MediaType mediaType0) {
            Intrinsics.checkNotNullParameter(s, "<this>");
            Pair pair0 = Internal.chooseCharset(mediaType0);
            byte[] arr_b = s.getBytes(((Charset)pair0.component1()));
            Intrinsics.checkNotNullExpressionValue(arr_b, "this as java.lang.String).getBytes(charset)");
            return this.create(arr_b, ((MediaType)pair0.component2()), 0, arr_b.length);
        }

        @Deprecated(level = DeprecationLevel.WARNING, message = "Moved to extension function. Put the \'file\' argument first to fix Java", replaceWith = @ReplaceWith(expression = "file.asRequestBody(contentType)", imports = {"okhttp3.RequestBody.Companion.asRequestBody"}))
        @JvmStatic
        public final RequestBody create(MediaType mediaType0, File file0) {
            Intrinsics.checkNotNullParameter(file0, "file");
            return this.create(file0, mediaType0);
        }

        @Deprecated(level = DeprecationLevel.WARNING, message = "Moved to extension function. Put the \'content\' argument first to fix Java", replaceWith = @ReplaceWith(expression = "content.toRequestBody(contentType)", imports = {"okhttp3.RequestBody.Companion.toRequestBody"}))
        @JvmStatic
        public final RequestBody create(MediaType mediaType0, String s) {
            Intrinsics.checkNotNullParameter(s, "content");
            return this.create(s, mediaType0);
        }

        @Deprecated(level = DeprecationLevel.WARNING, message = "Moved to extension function. Put the \'content\' argument first to fix Java", replaceWith = @ReplaceWith(expression = "content.toRequestBody(contentType)", imports = {"okhttp3.RequestBody.Companion.toRequestBody"}))
        @JvmStatic
        public final RequestBody create(MediaType mediaType0, ByteString byteString0) {
            Intrinsics.checkNotNullParameter(byteString0, "content");
            return this.create(byteString0, mediaType0);
        }

        @Deprecated(level = DeprecationLevel.WARNING, message = "Moved to extension function. Put the \'content\' argument first to fix Java", replaceWith = @ReplaceWith(expression = "content.toRequestBody(contentType, offset, byteCount)", imports = {"okhttp3.RequestBody.Companion.toRequestBody"}))
        @JvmStatic
        public final RequestBody create(MediaType mediaType0, byte[] arr_b) {
            Intrinsics.checkNotNullParameter(arr_b, "content");
            return Companion.create$default(this, mediaType0, arr_b, 0, 0, 12, null);
        }

        @Deprecated(level = DeprecationLevel.WARNING, message = "Moved to extension function. Put the \'content\' argument first to fix Java", replaceWith = @ReplaceWith(expression = "content.toRequestBody(contentType, offset, byteCount)", imports = {"okhttp3.RequestBody.Companion.toRequestBody"}))
        @JvmStatic
        public final RequestBody create(MediaType mediaType0, byte[] arr_b, int v) {
            Intrinsics.checkNotNullParameter(arr_b, "content");
            return Companion.create$default(this, mediaType0, arr_b, v, 0, 8, null);
        }

        @Deprecated(level = DeprecationLevel.WARNING, message = "Moved to extension function. Put the \'content\' argument first to fix Java", replaceWith = @ReplaceWith(expression = "content.toRequestBody(contentType, offset, byteCount)", imports = {"okhttp3.RequestBody.Companion.toRequestBody"}))
        @JvmStatic
        public final RequestBody create(MediaType mediaType0, byte[] arr_b, int v, int v1) {
            Intrinsics.checkNotNullParameter(arr_b, "content");
            return this.create(arr_b, mediaType0, v, v1);
        }

        @JvmStatic
        public final RequestBody create(ByteString byteString0, MediaType mediaType0) {
            Intrinsics.checkNotNullParameter(byteString0, "<this>");
            return _RequestBodyCommonKt.commonToRequestBody(byteString0, mediaType0);
        }

        @JvmStatic
        public final RequestBody create(Path path0, FileSystem fileSystem0, MediaType mediaType0) {
            Intrinsics.checkNotNullParameter(path0, "<this>");
            Intrinsics.checkNotNullParameter(fileSystem0, "fileSystem");
            return new RequestBody() {
                @Override  // okhttp3.RequestBody
                public long contentLength() {
                    Long long0 = path0.metadata(this.$this_asRequestBody).getSize();
                    return long0 == null ? -1L : ((long)long0);
                }

                @Override  // okhttp3.RequestBody
                public MediaType contentType() {
                    return fileSystem0;
                }

                @Override  // okhttp3.RequestBody
                public void writeTo(BufferedSink bufferedSink0) {
                    Intrinsics.checkNotNullParameter(bufferedSink0, "sink");
                    Closeable closeable0 = path0.source(this.$this_asRequestBody);
                    try {
                        bufferedSink0.writeAll(((Source)closeable0));
                    }
                    catch(Throwable throwable0) {
                        CloseableKt.closeFinally(closeable0, throwable0);
                        throw throwable0;
                    }
                    CloseableKt.closeFinally(closeable0, null);
                }
            };
        }

        @JvmStatic
        public final RequestBody create(byte[] arr_b) {
            Intrinsics.checkNotNullParameter(arr_b, "<this>");
            return Companion.create$default(this, arr_b, null, 0, 0, 7, null);
        }

        @JvmStatic
        public final RequestBody create(byte[] arr_b, MediaType mediaType0) {
            Intrinsics.checkNotNullParameter(arr_b, "<this>");
            return Companion.create$default(this, arr_b, mediaType0, 0, 0, 6, null);
        }

        @JvmStatic
        public final RequestBody create(byte[] arr_b, MediaType mediaType0, int v) {
            Intrinsics.checkNotNullParameter(arr_b, "<this>");
            return Companion.create$default(this, arr_b, mediaType0, v, 0, 4, null);
        }

        @JvmStatic
        public final RequestBody create(byte[] arr_b, MediaType mediaType0, int v, int v1) {
            Intrinsics.checkNotNullParameter(arr_b, "<this>");
            return _RequestBodyCommonKt.commonToRequestBody(arr_b, mediaType0, v, v1);
        }

        public static RequestBody create$default(Companion requestBody$Companion0, File file0, MediaType mediaType0, int v, Object object0) {
            if((v & 1) != 0) {
                mediaType0 = null;
            }
            return requestBody$Companion0.create(file0, mediaType0);
        }

        public static RequestBody create$default(Companion requestBody$Companion0, FileDescriptor fileDescriptor0, MediaType mediaType0, int v, Object object0) {
            if((v & 1) != 0) {
                mediaType0 = null;
            }
            return requestBody$Companion0.create(fileDescriptor0, mediaType0);
        }

        public static RequestBody create$default(Companion requestBody$Companion0, String s, MediaType mediaType0, int v, Object object0) {
            if((v & 1) != 0) {
                mediaType0 = null;
            }
            return requestBody$Companion0.create(s, mediaType0);
        }

        public static RequestBody create$default(Companion requestBody$Companion0, MediaType mediaType0, byte[] arr_b, int v, int v1, int v2, Object object0) {
            if((v2 & 4) != 0) {
                v = 0;
            }
            if((v2 & 8) != 0) {
                v1 = arr_b.length;
            }
            return requestBody$Companion0.create(mediaType0, arr_b, v, v1);
        }

        public static RequestBody create$default(Companion requestBody$Companion0, ByteString byteString0, MediaType mediaType0, int v, Object object0) {
            if((v & 1) != 0) {
                mediaType0 = null;
            }
            return requestBody$Companion0.create(byteString0, mediaType0);
        }

        public static RequestBody create$default(Companion requestBody$Companion0, Path path0, FileSystem fileSystem0, MediaType mediaType0, int v, Object object0) {
            if((v & 2) != 0) {
                mediaType0 = null;
            }
            return requestBody$Companion0.create(path0, fileSystem0, mediaType0);
        }

        public static RequestBody create$default(Companion requestBody$Companion0, byte[] arr_b, MediaType mediaType0, int v, int v1, int v2, Object object0) {
            if((v2 & 1) != 0) {
                mediaType0 = null;
            }
            if((v2 & 2) != 0) {
                v = 0;
            }
            if((v2 & 4) != 0) {
                v1 = arr_b.length;
            }
            return requestBody$Companion0.create(arr_b, mediaType0, v, v1);
        }

        @JvmStatic
        public final RequestBody gzip(RequestBody requestBody0) {
            Intrinsics.checkNotNullParameter(requestBody0, "<this>");
            return new RequestBody() {
                @Override  // okhttp3.RequestBody
                public long contentLength() {
                    return -1L;
                }

                @Override  // okhttp3.RequestBody
                public MediaType contentType() {
                    return this.$this_gzip.contentType();
                }

                @Override  // okhttp3.RequestBody
                public boolean isOneShot() {
                    return this.$this_gzip.isOneShot();
                }

                @Override  // okhttp3.RequestBody
                public void writeTo(BufferedSink bufferedSink0) throws IOException {
                    Intrinsics.checkNotNullParameter(bufferedSink0, "sink");
                    BufferedSink bufferedSink1 = Okio.buffer(new GzipSink(bufferedSink0));
                    this.$this_gzip.writeTo(bufferedSink1);
                    bufferedSink1.close();
                }
            };
        }
    }

    public static final Companion Companion;

    static {
        RequestBody.Companion = new Companion(null);
    }

    public long contentLength() throws IOException {
        return _RequestBodyCommonKt.commonContentLength(this);
    }

    public abstract MediaType contentType();

    @JvmStatic
    public static final RequestBody create(File file0, MediaType mediaType0) {
        return RequestBody.Companion.create(file0, mediaType0);
    }

    @JvmStatic
    public static final RequestBody create(FileDescriptor fileDescriptor0, MediaType mediaType0) {
        return RequestBody.Companion.create(fileDescriptor0, mediaType0);
    }

    @JvmStatic
    public static final RequestBody create(String s, MediaType mediaType0) {
        return RequestBody.Companion.create(s, mediaType0);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Moved to extension function. Put the \'file\' argument first to fix Java", replaceWith = @ReplaceWith(expression = "file.asRequestBody(contentType)", imports = {"okhttp3.RequestBody.Companion.asRequestBody"}))
    @JvmStatic
    public static final RequestBody create(MediaType mediaType0, File file0) {
        return RequestBody.Companion.create(mediaType0, file0);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Moved to extension function. Put the \'content\' argument first to fix Java", replaceWith = @ReplaceWith(expression = "content.toRequestBody(contentType)", imports = {"okhttp3.RequestBody.Companion.toRequestBody"}))
    @JvmStatic
    public static final RequestBody create(MediaType mediaType0, String s) {
        return RequestBody.Companion.create(mediaType0, s);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Moved to extension function. Put the \'content\' argument first to fix Java", replaceWith = @ReplaceWith(expression = "content.toRequestBody(contentType)", imports = {"okhttp3.RequestBody.Companion.toRequestBody"}))
    @JvmStatic
    public static final RequestBody create(MediaType mediaType0, ByteString byteString0) {
        return RequestBody.Companion.create(mediaType0, byteString0);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Moved to extension function. Put the \'content\' argument first to fix Java", replaceWith = @ReplaceWith(expression = "content.toRequestBody(contentType, offset, byteCount)", imports = {"okhttp3.RequestBody.Companion.toRequestBody"}))
    @JvmStatic
    public static final RequestBody create(MediaType mediaType0, byte[] arr_b) {
        return RequestBody.Companion.create(mediaType0, arr_b);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Moved to extension function. Put the \'content\' argument first to fix Java", replaceWith = @ReplaceWith(expression = "content.toRequestBody(contentType, offset, byteCount)", imports = {"okhttp3.RequestBody.Companion.toRequestBody"}))
    @JvmStatic
    public static final RequestBody create(MediaType mediaType0, byte[] arr_b, int v) {
        return RequestBody.Companion.create(mediaType0, arr_b, v);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Moved to extension function. Put the \'content\' argument first to fix Java", replaceWith = @ReplaceWith(expression = "content.toRequestBody(contentType, offset, byteCount)", imports = {"okhttp3.RequestBody.Companion.toRequestBody"}))
    @JvmStatic
    public static final RequestBody create(MediaType mediaType0, byte[] arr_b, int v, int v1) {
        return RequestBody.Companion.create(mediaType0, arr_b, v, v1);
    }

    @JvmStatic
    public static final RequestBody create(ByteString byteString0, MediaType mediaType0) {
        return RequestBody.Companion.create(byteString0, mediaType0);
    }

    @JvmStatic
    public static final RequestBody create(Path path0, FileSystem fileSystem0, MediaType mediaType0) {
        return RequestBody.Companion.create(path0, fileSystem0, mediaType0);
    }

    @JvmStatic
    public static final RequestBody create(byte[] arr_b) {
        return RequestBody.Companion.create(arr_b);
    }

    @JvmStatic
    public static final RequestBody create(byte[] arr_b, MediaType mediaType0) {
        return RequestBody.Companion.create(arr_b, mediaType0);
    }

    @JvmStatic
    public static final RequestBody create(byte[] arr_b, MediaType mediaType0, int v) {
        return RequestBody.Companion.create(arr_b, mediaType0, v);
    }

    @JvmStatic
    public static final RequestBody create(byte[] arr_b, MediaType mediaType0, int v, int v1) {
        return RequestBody.Companion.create(arr_b, mediaType0, v, v1);
    }

    @JvmStatic
    public static final RequestBody gzip(RequestBody requestBody0) {
        return RequestBody.Companion.gzip(requestBody0);
    }

    public boolean isDuplex() {
        return _RequestBodyCommonKt.commonIsDuplex(this);
    }

    public boolean isOneShot() {
        return _RequestBodyCommonKt.commonIsOneShot(this);
    }

    public abstract void writeTo(BufferedSink arg1) throws IOException;
}

