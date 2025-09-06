package retrofit2;

import java.io.IOException;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import okhttp3.FormBody.Builder;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody.Part;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;

final class RequestBuilder {
    static class ContentTypeOverridingRequestBody extends RequestBody {
        private final MediaType contentType;
        private final RequestBody delegate;

        ContentTypeOverridingRequestBody(RequestBody requestBody0, MediaType mediaType0) {
            this.delegate = requestBody0;
            this.contentType = mediaType0;
        }

        @Override  // okhttp3.RequestBody
        public long contentLength() throws IOException {
            return this.delegate.contentLength();
        }

        @Override  // okhttp3.RequestBody
        public MediaType contentType() {
            return this.contentType;
        }

        @Override  // okhttp3.RequestBody
        public void writeTo(BufferedSink bufferedSink0) throws IOException {
            this.delegate.writeTo(bufferedSink0);
        }
    }

    private static final char[] HEX_DIGITS = null;
    private static final String PATH_SEGMENT_ALWAYS_ENCODE_SET = " \"<>^`{}|\\?#";
    private static final Pattern PATH_TRAVERSAL;
    private final HttpUrl baseUrl;
    @Nullable
    private RequestBody body;
    @Nullable
    private MediaType contentType;
    @Nullable
    private Builder formBuilder;
    private final boolean hasBody;
    private final okhttp3.Headers.Builder headersBuilder;
    private final String method;
    @Nullable
    private okhttp3.MultipartBody.Builder multipartBuilder;
    @Nullable
    private String relativeUrl;
    private final okhttp3.Request.Builder requestBuilder;
    @Nullable
    private okhttp3.HttpUrl.Builder urlBuilder;

    static {
        RequestBuilder.HEX_DIGITS = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        RequestBuilder.PATH_TRAVERSAL = Pattern.compile("(.*/)?(\\.|%2e|%2E){1,2}(/.*)?");
    }

    RequestBuilder(String s, HttpUrl httpUrl0, @Nullable String s1, @Nullable Headers headers0, @Nullable MediaType mediaType0, boolean z, boolean z1, boolean z2) {
        this.method = s;
        this.baseUrl = httpUrl0;
        this.relativeUrl = s1;
        this.requestBuilder = new okhttp3.Request.Builder();
        this.contentType = mediaType0;
        this.hasBody = z;
        this.headersBuilder = headers0 == null ? new okhttp3.Headers.Builder() : headers0.newBuilder();
        if(z1) {
            this.formBuilder = new Builder();
            return;
        }
        if(z2) {
            okhttp3.MultipartBody.Builder multipartBody$Builder0 = new okhttp3.MultipartBody.Builder();
            this.multipartBuilder = multipartBody$Builder0;
            multipartBody$Builder0.setType(MultipartBody.FORM);
        }
    }

    void addFormField(String s, String s1, boolean z) {
        if(z) {
            this.formBuilder.addEncoded(s, s1);
            return;
        }
        this.formBuilder.add(s, s1);
    }

    void addHeader(String s, String s1) {
        if("Content-Type".equalsIgnoreCase(s)) {
            try {
                this.contentType = MediaType.get(s1);
                return;
            }
            catch(IllegalArgumentException illegalArgumentException0) {
                throw new IllegalArgumentException("Malformed content type: " + s1, illegalArgumentException0);
            }
        }
        this.headersBuilder.add(s, s1);
    }

    void addHeaders(Headers headers0) {
        this.headersBuilder.addAll(headers0);
    }

    void addPart(Headers headers0, RequestBody requestBody0) {
        this.multipartBuilder.addPart(headers0, requestBody0);
    }

    void addPart(Part multipartBody$Part0) {
        this.multipartBuilder.addPart(multipartBody$Part0);
    }

    void addPathParam(String s, String s1, boolean z) {
        if(this.relativeUrl == null) {
            throw new AssertionError();
        }
        String s2 = RequestBuilder.canonicalizeForPath(s1, z);
        String s3 = this.relativeUrl.replace("{" + s + "}", s2);
        if(RequestBuilder.PATH_TRAVERSAL.matcher(s3).matches()) {
            throw new IllegalArgumentException("@Path parameters shouldn\'t perform path traversal (\'.\' or \'..\'): " + s1);
        }
        this.relativeUrl = s3;
    }

    void addQueryParam(String s, @Nullable String s1, boolean z) {
        String s2 = this.relativeUrl;
        if(s2 != null) {
            okhttp3.HttpUrl.Builder httpUrl$Builder0 = this.baseUrl.newBuilder(s2);
            this.urlBuilder = httpUrl$Builder0;
            if(httpUrl$Builder0 == null) {
                throw new IllegalArgumentException("Malformed URL. Base: " + this.baseUrl + ", Relative: " + this.relativeUrl);
            }
            this.relativeUrl = null;
        }
        if(z) {
            this.urlBuilder.addEncodedQueryParameter(s, s1);
            return;
        }
        this.urlBuilder.addQueryParameter(s, s1);
    }

    void addTag(Class class0, @Nullable Object object0) {
        this.requestBuilder.tag(class0, object0);
    }

    private static String canonicalizeForPath(String s, boolean z) {
        int v = s.length();
        int v1 = 0;
        while(v1 < v) {
            int v2 = s.codePointAt(v1);
            if(v2 >= 0x20 && v2 < 0x7F && " \"<>^`{}|\\?#".indexOf(v2) == -1 && (z || v2 != 37 && v2 != 0x2F)) {
                v1 += Character.charCount(v2);
                continue;
            }
            Buffer buffer0 = new Buffer();
            buffer0.writeUtf8(s, 0, v1);
            RequestBuilder.canonicalizeForPath(buffer0, s, v1, v, z);
            return "";
        }
        return s;
    }

    private static void canonicalizeForPath(Buffer buffer0, String s, int v, int v1, boolean z) {
        Buffer buffer1 = null;
        while(v < v1) {
            int v2 = s.codePointAt(v);
            if(!z || v2 != 9 && v2 != 10 && v2 != 12 && v2 != 13) {
                if(v2 < 0x20 || v2 >= 0x7F || " \"<>^`{}|\\?#".indexOf(v2) != -1 || !z && (v2 == 0x2F || v2 == 37)) {
                    if(buffer1 == null) {
                        buffer1 = new Buffer();
                    }
                    buffer1.writeUtf8CodePoint(v2);
                    while(!buffer1.exhausted()) {
                        int v3 = buffer1.readByte();
                        buffer0.writeByte(37);
                        buffer0.writeByte(((int)RequestBuilder.HEX_DIGITS[(v3 & 0xFF) >> 4 & 15]));
                        buffer0.writeByte(((int)RequestBuilder.HEX_DIGITS[v3 & 15]));
                    }
                }
                else {
                    buffer0.writeUtf8CodePoint(v2);
                }
            }
            v += Character.charCount(v2);
        }
    }

    okhttp3.Request.Builder get() {
        HttpUrl httpUrl0;
        okhttp3.HttpUrl.Builder httpUrl$Builder0 = this.urlBuilder;
        if(httpUrl$Builder0 == null) {
            httpUrl0 = this.baseUrl.resolve(this.relativeUrl);
            if(httpUrl0 == null) {
                throw new IllegalArgumentException("Malformed URL. Base: " + this.baseUrl + ", Relative: " + this.relativeUrl);
            }
        }
        else {
            httpUrl0 = httpUrl$Builder0.build();
        }
        RequestBody requestBody0 = this.body;
        if(requestBody0 == null) {
            Builder formBody$Builder0 = this.formBuilder;
            if(formBody$Builder0 == null) {
                okhttp3.MultipartBody.Builder multipartBody$Builder0 = this.multipartBuilder;
                if(multipartBody$Builder0 != null) {
                    requestBody0 = multipartBody$Builder0.build();
                }
                else if(this.hasBody) {
                    requestBody0 = RequestBody.create(null, new byte[0]);
                }
            }
            else {
                requestBody0 = formBody$Builder0.build();
            }
        }
        MediaType mediaType0 = this.contentType;
        if(mediaType0 != null) {
            if(requestBody0 != null) {
                requestBody0 = new ContentTypeOverridingRequestBody(requestBody0, mediaType0);
                return this.requestBuilder.url(httpUrl0).headers(this.headersBuilder.build()).method(this.method, requestBody0);
            }
            this.headersBuilder.add("Content-Type", mediaType0.toString());
        }
        return this.requestBuilder.url(httpUrl0).headers(this.headersBuilder.build()).method(this.method, requestBody0);
    }

    void setBody(RequestBody requestBody0) {
        this.body = requestBody0;
    }

    void setRelativeUrl(Object object0) {
        this.relativeUrl = object0.toString();
    }
}

