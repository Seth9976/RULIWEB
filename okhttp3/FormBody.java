package okhttp3;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal._UtilJvmKt;
import okio.Buffer;
import okio.BufferedSink;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000E\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000 \u001C2\u00020\u0001:\u0002\u001B\u001CB#\b\u0000\u0012\f\u0010\u0002\u001A\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001A\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0006J\b\u0010\n\u001A\u00020\u000BH\u0016J\b\u0010\f\u001A\u00020\rH\u0016J\u000E\u0010\u000E\u001A\u00020\u00042\u0006\u0010\u000F\u001A\u00020\bJ\u000E\u0010\u0010\u001A\u00020\u00042\u0006\u0010\u000F\u001A\u00020\bJ\u000E\u0010\u0011\u001A\u00020\u00042\u0006\u0010\u000F\u001A\u00020\bJ\r\u0010\u0007\u001A\u00020\bH\u0007¢\u0006\u0002\b\u0012J\u000E\u0010\u0013\u001A\u00020\u00042\u0006\u0010\u000F\u001A\u00020\bJ\u001A\u0010\u0014\u001A\u00020\u000B2\b\u0010\u0015\u001A\u0004\u0018\u00010\u00162\u0006\u0010\u0017\u001A\u00020\u0018H\u0002J\u0010\u0010\u0019\u001A\u00020\u001A2\u0006\u0010\u0015\u001A\u00020\u0016H\u0016R\u0014\u0010\u0002\u001A\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001A\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0007\u001A\u00020\b8G¢\u0006\u0006\u001A\u0004\b\u0007\u0010\t¨\u0006\u001D"}, d2 = {"Lokhttp3/FormBody;", "Lokhttp3/RequestBody;", "encodedNames", "", "", "encodedValues", "(Ljava/util/List;Ljava/util/List;)V", "size", "", "()I", "contentLength", "", "contentType", "Lokhttp3/MediaType;", "encodedName", "index", "encodedValue", "name", "-deprecated_size", "value", "writeOrCountBytes", "sink", "Lokio/BufferedSink;", "countBytes", "", "writeTo", "", "Builder", "Companion", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
public final class FormBody extends RequestBody {
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0010\u000E\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0013\b\u0007\u0012\n\b\u0002\u0010\u0002\u001A\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\t\u001A\u00020\u00002\u0006\u0010\n\u001A\u00020\u00072\u0006\u0010\u000B\u001A\u00020\u0007J\u0016\u0010\f\u001A\u00020\u00002\u0006\u0010\n\u001A\u00020\u00072\u0006\u0010\u000B\u001A\u00020\u0007J\u0006\u0010\r\u001A\u00020\u000ER\u0010\u0010\u0002\u001A\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001A\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001A\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000F"}, d2 = {"Lokhttp3/FormBody$Builder;", "", "charset", "Ljava/nio/charset/Charset;", "(Ljava/nio/charset/Charset;)V", "names", "", "", "values", "add", "name", "value", "addEncoded", "build", "Lokhttp3/FormBody;", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    public static final class Builder {
        private final Charset charset;
        private final List names;
        private final List values;

        public Builder() {
            this(null, 1, null);
        }

        public Builder(Charset charset0) {
            this.charset = charset0;
            this.names = new ArrayList();
            this.values = new ArrayList();
        }

        public Builder(Charset charset0, int v, DefaultConstructorMarker defaultConstructorMarker0) {
            if((v & 1) != 0) {
                charset0 = null;
            }
            this(charset0);
        }

        public final Builder add(String s, String s1) {
            Intrinsics.checkNotNullParameter(s, "name");
            Intrinsics.checkNotNullParameter(s1, "value");
            String s2 = Companion.canonicalize$okhttp$default(HttpUrl.Companion, s, 0, 0, " !\"#$&\'()+,/:;<=>?@[\\]^`{|}~", false, false, false, false, this.charset, 91, null);
            this.names.add(s2);
            String s3 = Companion.canonicalize$okhttp$default(HttpUrl.Companion, s1, 0, 0, " !\"#$&\'()+,/:;<=>?@[\\]^`{|}~", false, false, false, false, this.charset, 91, null);
            this.values.add(s3);
            return this;
        }

        public final Builder addEncoded(String s, String s1) {
            Intrinsics.checkNotNullParameter(s, "name");
            Intrinsics.checkNotNullParameter(s1, "value");
            String s2 = Companion.canonicalize$okhttp$default(HttpUrl.Companion, s, 0, 0, " !\"#$&\'()+,/:;<=>?@[\\]^`{|}~", true, false, true, false, this.charset, 83, null);
            this.names.add(s2);
            String s3 = Companion.canonicalize$okhttp$default(HttpUrl.Companion, s1, 0, 0, " !\"#$&\'()+,/:;<=>?@[\\]^`{|}~", true, false, true, false, this.charset, 83, null);
            this.values.add(s3);
            return this;
        }

        public final FormBody build() {
            return new FormBody(this.names, this.values);
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000E\u0010\u0003\u001A\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lokhttp3/FormBody$Companion;", "", "()V", "CONTENT_TYPE", "Lokhttp3/MediaType;", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    public static final class okhttp3.FormBody.Companion {
        private okhttp3.FormBody.Companion() {
        }

        public okhttp3.FormBody.Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }
    }

    private static final MediaType CONTENT_TYPE;
    public static final okhttp3.FormBody.Companion Companion;
    private final List encodedNames;
    private final List encodedValues;

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "size", imports = {}))
    public final int -deprecated_size() {
        return this.size();
    }

    static {
        FormBody.Companion = new okhttp3.FormBody.Companion(null);
        FormBody.CONTENT_TYPE = MediaType.Companion.get("application/x-www-form-urlencoded");
    }

    public FormBody(List list0, List list1) {
        Intrinsics.checkNotNullParameter(list0, "encodedNames");
        Intrinsics.checkNotNullParameter(list1, "encodedValues");
        super();
        this.encodedNames = _UtilJvmKt.toImmutableList(list0);
        this.encodedValues = _UtilJvmKt.toImmutableList(list1);
    }

    @Override  // okhttp3.RequestBody
    public long contentLength() {
        return this.writeOrCountBytes(null, true);
    }

    @Override  // okhttp3.RequestBody
    public MediaType contentType() {
        return FormBody.CONTENT_TYPE;
    }

    public final String encodedName(int v) {
        return (String)this.encodedNames.get(v);
    }

    public final String encodedValue(int v) {
        return (String)this.encodedValues.get(v);
    }

    public final String name(int v) {
        String s = this.encodedName(v);
        return Companion.percentDecode$okhttp$default(HttpUrl.Companion, s, 0, 0, true, 3, null);
    }

    public final int size() {
        return this.encodedNames.size();
    }

    public final String value(int v) {
        String s = this.encodedValue(v);
        return Companion.percentDecode$okhttp$default(HttpUrl.Companion, s, 0, 0, true, 3, null);
    }

    private final long writeOrCountBytes(BufferedSink bufferedSink0, boolean z) {
        Buffer buffer0;
        if(z) {
            buffer0 = new Buffer();
        }
        else {
            Intrinsics.checkNotNull(bufferedSink0);
            buffer0 = bufferedSink0.getBuffer();
        }
        int v = this.encodedNames.size();
        for(int v1 = 0; v1 < v; ++v1) {
            if(v1 > 0) {
                buffer0.writeByte(38);
            }
            buffer0.writeUtf8(((String)this.encodedNames.get(v1)));
            buffer0.writeByte(61);
            buffer0.writeUtf8(((String)this.encodedValues.get(v1)));
        }
        if(z) {
            buffer0.clear();
            return buffer0.size();
        }
        return 0L;
    }

    @Override  // okhttp3.RequestBody
    public void writeTo(BufferedSink bufferedSink0) throws IOException {
        Intrinsics.checkNotNullParameter(bufferedSink0, "sink");
        this.writeOrCountBytes(bufferedSink0, false);
    }
}

