package okhttp3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal._UtilJvmKt;
import okio.Buffer;
import okio.BufferedSink;
import okio.ByteString;

@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u0000 #2\u00020\u0001:\u0003\"#$B%\b\u0000\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0005\u0012\f\u0010\u0006\u001A\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0002\u0010\tJ\r\u0010\n\u001A\u00020\u000BH\u0007¢\u0006\u0002\b\u0015J\b\u0010\r\u001A\u00020\u000EH\u0016J\b\u0010\u000F\u001A\u00020\u0005H\u0016J\u000E\u0010\u0016\u001A\u00020\b2\u0006\u0010\u0017\u001A\u00020\u0012J\u0013\u0010\u0006\u001A\b\u0012\u0004\u0012\u00020\b0\u0007H\u0007¢\u0006\u0002\b\u0018J\r\u0010\u0011\u001A\u00020\u0012H\u0007¢\u0006\u0002\b\u0019J\r\u0010\u0004\u001A\u00020\u0005H\u0007¢\u0006\u0002\b\u001AJ\u001A\u0010\u001B\u001A\u00020\u000E2\b\u0010\u001C\u001A\u0004\u0018\u00010\u001D2\u0006\u0010\u001E\u001A\u00020\u001FH\u0002J\u0010\u0010 \u001A\u00020!2\u0006\u0010\u001C\u001A\u00020\u001DH\u0016R\u0011\u0010\n\u001A\u00020\u000B8G¢\u0006\u0006\u001A\u0004\b\n\u0010\fR\u000E\u0010\u0002\u001A\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\r\u001A\u00020\u000EX\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\u000F\u001A\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010\u0006\u001A\b\u0012\u0004\u0012\u00020\b0\u00078\u0007¢\u0006\b\n\u0000\u001A\u0004\b\u0006\u0010\u0010R\u0011\u0010\u0011\u001A\u00020\u00128G¢\u0006\u0006\u001A\u0004\b\u0011\u0010\u0013R\u0013\u0010\u0004\u001A\u00020\u00058\u0007¢\u0006\b\n\u0000\u001A\u0004\b\u0004\u0010\u0014¨\u0006%"}, d2 = {"Lokhttp3/MultipartBody;", "Lokhttp3/RequestBody;", "boundaryByteString", "Lokio/ByteString;", "type", "Lokhttp3/MediaType;", "parts", "", "Lokhttp3/MultipartBody$Part;", "(Lokio/ByteString;Lokhttp3/MediaType;Ljava/util/List;)V", "boundary", "", "()Ljava/lang/String;", "contentLength", "", "contentType", "()Ljava/util/List;", "size", "", "()I", "()Lokhttp3/MediaType;", "-deprecated_boundary", "part", "index", "-deprecated_parts", "-deprecated_size", "-deprecated_type", "writeOrCountBytes", "sink", "Lokio/BufferedSink;", "countBytes", "", "writeTo", "", "Builder", "Companion", "Part", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
public final class MultipartBody extends RequestBody {
    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0011\b\u0007\u0012\b\b\u0002\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\u000B\u001A\u00020\u00002\u0006\u0010\f\u001A\u00020\u00032\u0006\u0010\r\u001A\u00020\u0003J \u0010\u000B\u001A\u00020\u00002\u0006\u0010\f\u001A\u00020\u00032\b\u0010\u000E\u001A\u0004\u0018\u00010\u00032\u0006\u0010\u000F\u001A\u00020\u0010J\u0018\u0010\u0011\u001A\u00020\u00002\b\u0010\u0012\u001A\u0004\u0018\u00010\u00132\u0006\u0010\u000F\u001A\u00020\u0010J\u000E\u0010\u0011\u001A\u00020\u00002\u0006\u0010\u0014\u001A\u00020\bJ\u000E\u0010\u0011\u001A\u00020\u00002\u0006\u0010\u000F\u001A\u00020\u0010J\u0006\u0010\u0015\u001A\u00020\u0016J\u000E\u0010\u0017\u001A\u00020\u00002\u0006\u0010\t\u001A\u00020\nR\u000E\u0010\u0002\u001A\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001A\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\t\u001A\u00020\nX\u0082\u000E¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lokhttp3/MultipartBody$Builder;", "", "boundary", "", "(Ljava/lang/String;)V", "Lokio/ByteString;", "parts", "", "Lokhttp3/MultipartBody$Part;", "type", "Lokhttp3/MediaType;", "addFormDataPart", "name", "value", "filename", "body", "Lokhttp3/RequestBody;", "addPart", "headers", "Lokhttp3/Headers;", "part", "build", "Lokhttp3/MultipartBody;", "setType", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    public static final class Builder {
        private final ByteString boundary;
        private final List parts;
        private MediaType type;

        public Builder() {
            this(null, 1, null);
        }

        public Builder(String s) {
            Intrinsics.checkNotNullParameter(s, "boundary");
            super();
            this.boundary = ByteString.Companion.encodeUtf8(s);
            this.type = MultipartBody.MIXED;
            this.parts = new ArrayList();
        }

        public Builder(String s, int v, DefaultConstructorMarker defaultConstructorMarker0) {
            if((v & 1) != 0) {
                s = "efc5343d-f41f-425b-89cd-09ccc5702545";
                Intrinsics.checkNotNullExpressionValue("efc5343d-f41f-425b-89cd-09ccc5702545", "randomUUID().toString()");
            }
            this(s);
        }

        public final Builder addFormDataPart(String s, String s1) {
            Intrinsics.checkNotNullParameter(s, "name");
            Intrinsics.checkNotNullParameter(s1, "value");
            this.addPart(Part.Companion.createFormData(s, s1));
            return this;
        }

        public final Builder addFormDataPart(String s, String s1, RequestBody requestBody0) {
            Intrinsics.checkNotNullParameter(s, "name");
            Intrinsics.checkNotNullParameter(requestBody0, "body");
            this.addPart(Part.Companion.createFormData(s, s1, requestBody0));
            return this;
        }

        public final Builder addPart(Headers headers0, RequestBody requestBody0) {
            Intrinsics.checkNotNullParameter(requestBody0, "body");
            this.addPart(Part.Companion.create(headers0, requestBody0));
            return this;
        }

        public final Builder addPart(Part multipartBody$Part0) {
            Intrinsics.checkNotNullParameter(multipartBody$Part0, "part");
            this.parts.add(multipartBody$Part0);
            return this;
        }

        public final Builder addPart(RequestBody requestBody0) {
            Intrinsics.checkNotNullParameter(requestBody0, "body");
            this.addPart(Part.Companion.create(requestBody0));
            return this;
        }

        public final MultipartBody build() {
            if(this.parts.isEmpty()) {
                throw new IllegalStateException("Multipart body must have at least one part.");
            }
            MediaType mediaType0 = this.type;
            List list0 = _UtilJvmKt.toImmutableList(this.parts);
            return new MultipartBody(this.boundary, mediaType0, list0);
        }

        public final Builder setType(MediaType mediaType0) {
            Intrinsics.checkNotNullParameter(mediaType0, "type");
            if(!Intrinsics.areEqual(mediaType0.type(), "multipart")) {
                throw new IllegalArgumentException(("multipart != " + mediaType0).toString());
            }
            this.type = mediaType0;
            return this;
        }
    }

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001D\u0010\r\u001A\u00020\u000E*\u00060\u000Fj\u0002`\u00102\u0006\u0010\u0011\u001A\u00020\u0012H\u0000¢\u0006\u0002\b\u0013R\u0010\u0010\u0003\u001A\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0005\u001A\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0007\u001A\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\b\u001A\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001A\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001A\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000B\u001A\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001A\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lokhttp3/MultipartBody$Companion;", "", "()V", "ALTERNATIVE", "Lokhttp3/MediaType;", "COLONSPACE", "", "CRLF", "DASHDASH", "DIGEST", "FORM", "MIXED", "PARALLEL", "appendQuotedString", "", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "key", "", "appendQuotedString$okhttp", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final void appendQuotedString$okhttp(StringBuilder stringBuilder0, String s) {
            Intrinsics.checkNotNullParameter(stringBuilder0, "<this>");
            Intrinsics.checkNotNullParameter(s, "key");
            stringBuilder0.append('\"');
            int v = s.length();
            for(int v1 = 0; v1 < v; ++v1) {
                int v2 = s.charAt(v1);
                if(v2 == 10) {
                    stringBuilder0.append("%0A");
                }
                else {
                    switch(v2) {
                        case 13: {
                            stringBuilder0.append("%0D");
                            break;
                        }
                        case 34: {
                            stringBuilder0.append("%22");
                            break;
                        }
                        default: {
                            stringBuilder0.append(((char)v2));
                        }
                    }
                }
            }
            stringBuilder0.append('\"');
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 \u000B2\u00020\u0001:\u0001\u000BB\u0019\b\u0002\u0012\b\u0010\u0002\u001A\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0005¢\u0006\u0002\u0010\u0006J\r\u0010\u0004\u001A\u00020\u0005H\u0007¢\u0006\u0002\b\tJ\u000F\u0010\u0002\u001A\u0004\u0018\u00010\u0003H\u0007¢\u0006\u0002\b\nR\u0013\u0010\u0004\u001A\u00020\u00058\u0007¢\u0006\b\n\u0000\u001A\u0004\b\u0004\u0010\u0007R\u0015\u0010\u0002\u001A\u0004\u0018\u00010\u00038\u0007¢\u0006\b\n\u0000\u001A\u0004\b\u0002\u0010\b¨\u0006\f"}, d2 = {"Lokhttp3/MultipartBody$Part;", "", "headers", "Lokhttp3/Headers;", "body", "Lokhttp3/RequestBody;", "(Lokhttp3/Headers;Lokhttp3/RequestBody;)V", "()Lokhttp3/RequestBody;", "()Lokhttp3/Headers;", "-deprecated_body", "-deprecated_headers", "Companion", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    public static final class Part {
        @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001A\u0010\u0003\u001A\u00020\u00042\b\u0010\u0005\u001A\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001A\u00020\bH\u0007J\u0010\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0007\u001A\u00020\bH\u0007J\u0018\u0010\t\u001A\u00020\u00042\u0006\u0010\n\u001A\u00020\u000B2\u0006\u0010\f\u001A\u00020\u000BH\u0007J\"\u0010\t\u001A\u00020\u00042\u0006\u0010\n\u001A\u00020\u000B2\b\u0010\r\u001A\u0004\u0018\u00010\u000B2\u0006\u0010\u0007\u001A\u00020\bH\u0007¨\u0006\u000E"}, d2 = {"Lokhttp3/MultipartBody$Part$Companion;", "", "()V", "create", "Lokhttp3/MultipartBody$Part;", "headers", "Lokhttp3/Headers;", "body", "Lokhttp3/RequestBody;", "createFormData", "name", "", "value", "filename", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
        public static final class okhttp3.MultipartBody.Part.Companion {
            private okhttp3.MultipartBody.Part.Companion() {
            }

            public okhttp3.MultipartBody.Part.Companion(DefaultConstructorMarker defaultConstructorMarker0) {
            }

            @JvmStatic
            public final Part create(Headers headers0, RequestBody requestBody0) {
                Intrinsics.checkNotNullParameter(requestBody0, "body");
                if((headers0 == null ? null : headers0.get("Content-Type")) != null) {
                    throw new IllegalArgumentException("Unexpected header: Content-Type");
                }
                if((headers0 == null ? null : headers0.get("Content-Length")) != null) {
                    throw new IllegalArgumentException("Unexpected header: Content-Length");
                }
                return new Part(headers0, requestBody0, null);
            }

            @JvmStatic
            public final Part create(RequestBody requestBody0) {
                Intrinsics.checkNotNullParameter(requestBody0, "body");
                return this.create(null, requestBody0);
            }

            @JvmStatic
            public final Part createFormData(String s, String s1) {
                Intrinsics.checkNotNullParameter(s, "name");
                Intrinsics.checkNotNullParameter(s1, "value");
                return this.createFormData(s, null, okhttp3.RequestBody.Companion.create$default(RequestBody.Companion, s1, null, 1, null));
            }

            @JvmStatic
            public final Part createFormData(String s, String s1, RequestBody requestBody0) {
                Intrinsics.checkNotNullParameter(s, "name");
                Intrinsics.checkNotNullParameter(requestBody0, "body");
                StringBuilder stringBuilder0 = new StringBuilder();
                stringBuilder0.append("form-data; name=");
                MultipartBody.Companion.appendQuotedString$okhttp(stringBuilder0, s);
                if(s1 != null) {
                    stringBuilder0.append("; filename=");
                    MultipartBody.Companion.appendQuotedString$okhttp(stringBuilder0, s1);
                }
                String s2 = stringBuilder0.toString();
                Intrinsics.checkNotNullExpressionValue(s2, "StringBuilder().apply(builderAction).toString()");
                return this.create(new okhttp3.Headers.Builder().addUnsafeNonAscii("Content-Disposition", s2).build(), requestBody0);
            }
        }

        public static final okhttp3.MultipartBody.Part.Companion Companion;
        private final RequestBody body;
        private final Headers headers;

        @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "body", imports = {}))
        public final RequestBody -deprecated_body() {
            return this.body;
        }

        @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "headers", imports = {}))
        public final Headers -deprecated_headers() {
            return this.headers;
        }

        static {
            Part.Companion = new okhttp3.MultipartBody.Part.Companion(null);
        }

        private Part(Headers headers0, RequestBody requestBody0) {
            this.headers = headers0;
            this.body = requestBody0;
        }

        public Part(Headers headers0, RequestBody requestBody0, DefaultConstructorMarker defaultConstructorMarker0) {
            this(headers0, requestBody0);
        }

        public final RequestBody body() {
            return this.body;
        }

        @JvmStatic
        public static final Part create(Headers headers0, RequestBody requestBody0) {
            return Part.Companion.create(headers0, requestBody0);
        }

        @JvmStatic
        public static final Part create(RequestBody requestBody0) {
            return Part.Companion.create(requestBody0);
        }

        @JvmStatic
        public static final Part createFormData(String s, String s1) {
            return Part.Companion.createFormData(s, s1);
        }

        @JvmStatic
        public static final Part createFormData(String s, String s1, RequestBody requestBody0) {
            return Part.Companion.createFormData(s, s1, requestBody0);
        }

        public final Headers headers() {
            return this.headers;
        }
    }

    public static final MediaType ALTERNATIVE;
    private static final byte[] COLONSPACE;
    private static final byte[] CRLF;
    public static final Companion Companion;
    private static final byte[] DASHDASH;
    public static final MediaType DIGEST;
    public static final MediaType FORM;
    public static final MediaType MIXED;
    public static final MediaType PARALLEL;
    private final ByteString boundaryByteString;
    private long contentLength;
    private final MediaType contentType;
    private final List parts;
    private final MediaType type;

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "boundary", imports = {}))
    public final String -deprecated_boundary() {
        return this.boundary();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "parts", imports = {}))
    public final List -deprecated_parts() {
        return this.parts;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "size", imports = {}))
    public final int -deprecated_size() {
        return this.size();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "type", imports = {}))
    public final MediaType -deprecated_type() {
        return this.type;
    }

    static {
        MultipartBody.Companion = new Companion(null);
        MultipartBody.MIXED = MediaType.Companion.get("multipart/mixed");
        MultipartBody.ALTERNATIVE = MediaType.Companion.get("multipart/alternative");
        MultipartBody.DIGEST = MediaType.Companion.get("multipart/digest");
        MultipartBody.PARALLEL = MediaType.Companion.get("multipart/parallel");
        MultipartBody.FORM = MediaType.Companion.get("multipart/form-data");
        MultipartBody.COLONSPACE = new byte[]{58, 0x20};
        MultipartBody.CRLF = new byte[]{13, 10};
        MultipartBody.DASHDASH = new byte[]{45, 45};
    }

    public MultipartBody(ByteString byteString0, MediaType mediaType0, List list0) {
        Intrinsics.checkNotNullParameter(byteString0, "boundaryByteString");
        Intrinsics.checkNotNullParameter(mediaType0, "type");
        Intrinsics.checkNotNullParameter(list0, "parts");
        super();
        this.boundaryByteString = byteString0;
        this.type = mediaType0;
        this.parts = list0;
        this.contentType = MediaType.Companion.get(mediaType0 + "; boundary=" + this.boundary());
        this.contentLength = -1L;
    }

    public final String boundary() {
        return this.boundaryByteString.utf8();
    }

    @Override  // okhttp3.RequestBody
    public long contentLength() throws IOException {
        long v = this.contentLength;
        if(v == -1L) {
            v = this.writeOrCountBytes(null, true);
            this.contentLength = v;
        }
        return v;
    }

    @Override  // okhttp3.RequestBody
    public MediaType contentType() {
        return this.contentType;
    }

    public final Part part(int v) {
        return (Part)this.parts.get(v);
    }

    public final List parts() {
        return this.parts;
    }

    public final int size() {
        return this.parts.size();
    }

    public final MediaType type() {
        return this.type;
    }

    private final long writeOrCountBytes(BufferedSink bufferedSink0, boolean z) throws IOException {
        Buffer buffer1;
        if(z) {
            Buffer buffer0 = new Buffer();
            buffer1 = buffer0;
            bufferedSink0 = buffer0;
        }
        else {
            buffer1 = null;
        }
        int v = this.parts.size();
        long v1 = 0L;
        for(int v2 = 0; v2 < v; ++v2) {
            Part multipartBody$Part0 = (Part)this.parts.get(v2);
            Headers headers0 = multipartBody$Part0.headers();
            RequestBody requestBody0 = multipartBody$Part0.body();
            Intrinsics.checkNotNull(bufferedSink0);
            bufferedSink0.write(MultipartBody.DASHDASH);
            bufferedSink0.write(this.boundaryByteString);
            bufferedSink0.write(MultipartBody.CRLF);
            if(headers0 != null) {
                int v3 = headers0.size();
                for(int v4 = 0; v4 < v3; ++v4) {
                    bufferedSink0.writeUtf8(headers0.name(v4)).write(MultipartBody.COLONSPACE).writeUtf8(headers0.value(v4)).write(MultipartBody.CRLF);
                }
            }
            MediaType mediaType0 = requestBody0.contentType();
            if(mediaType0 != null) {
                bufferedSink0.writeUtf8("Content-Type: ").writeUtf8(mediaType0.toString()).write(MultipartBody.CRLF);
            }
            long v5 = requestBody0.contentLength();
            if(v5 == -1L && z) {
                Intrinsics.checkNotNull(buffer1);
                buffer1.clear();
                return -1L;
            }
            byte[] arr_b = MultipartBody.CRLF;
            bufferedSink0.write(arr_b);
            if(z) {
                v1 += v5;
            }
            else {
                requestBody0.writeTo(bufferedSink0);
            }
            bufferedSink0.write(arr_b);
        }
        Intrinsics.checkNotNull(bufferedSink0);
        bufferedSink0.write(MultipartBody.DASHDASH);
        bufferedSink0.write(this.boundaryByteString);
        bufferedSink0.write(MultipartBody.DASHDASH);
        bufferedSink0.write(MultipartBody.CRLF);
        if(z) {
            Intrinsics.checkNotNull(buffer1);
            v1 += buffer1.size();
            buffer1.clear();
        }
        return v1;
    }

    @Override  // okhttp3.RequestBody
    public void writeTo(BufferedSink bufferedSink0) throws IOException {
        Intrinsics.checkNotNullParameter(bufferedSink0, "sink");
        this.writeOrCountBytes(bufferedSink0, false);
    }
}

