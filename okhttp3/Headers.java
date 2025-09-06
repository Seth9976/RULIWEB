package okhttp3;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.text.StringsKt;
import okhttp3.internal._HeadersCommonKt;
import okhttp3.internal.http.DatesKt;
import retrofit2.Platform..ExternalSyntheticApiModelOutline0;

@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u001C\n\u0002\u0018\u0002\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010(\n\u0002\b\u0002\n\u0002\u0010\"\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010 \n\u0002\b\u0006\u0018\u0000 )2\u0014\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u00020\u0001:\u0002()B\u0015\b\u0000\u0012\f\u0010\u0004\u001A\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\u0002\u0010\u0006J\u0006\u0010\r\u001A\u00020\u000EJ\u0013\u0010\u000F\u001A\u00020\u00102\b\u0010\u0011\u001A\u0004\u0018\u00010\u0012H\u0096\u0002J\u0013\u0010\u0013\u001A\u0004\u0018\u00010\u00032\u0006\u0010\u0014\u001A\u00020\u0003H\u0086\u0002J\u0010\u0010\u0015\u001A\u0004\u0018\u00010\u00162\u0006\u0010\u0014\u001A\u00020\u0003J\u0010\u0010\u0017\u001A\u0004\u0018\u00010\u00182\u0006\u0010\u0014\u001A\u00020\u0003J\b\u0010\u0019\u001A\u00020\u000BH\u0016J\u001B\u0010\u001A\u001A\u0014\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u00020\u001BH\u0096\u0002J\u000E\u0010\u0014\u001A\u00020\u00032\u0006\u0010\u001C\u001A\u00020\u000BJ\f\u0010\u001D\u001A\b\u0012\u0004\u0012\u00020\u00030\u001EJ\u0006\u0010\u001F\u001A\u00020 J\r\u0010\n\u001A\u00020\u000BH\u0007¢\u0006\u0002\b!J\u0018\u0010\"\u001A\u0014\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030$0#J\b\u0010%\u001A\u00020\u0003H\u0016J\u000E\u0010&\u001A\u00020\u00032\u0006\u0010\u001C\u001A\u00020\u000BJ\u0014\u0010\'\u001A\b\u0012\u0004\u0012\u00020\u00030$2\u0006\u0010\u0014\u001A\u00020\u0003R\u001C\u0010\u0004\u001A\b\u0012\u0004\u0012\u00020\u00030\u0005X\u0080\u0004¢\u0006\n\n\u0002\u0010\t\u001A\u0004\b\u0007\u0010\bR\u0011\u0010\n\u001A\u00020\u000B8G¢\u0006\u0006\u001A\u0004\b\n\u0010\f¨\u0006*"}, d2 = {"Lokhttp3/Headers;", "", "Lkotlin/Pair;", "", "namesAndValues", "", "([Ljava/lang/String;)V", "getNamesAndValues$okhttp", "()[Ljava/lang/String;", "[Ljava/lang/String;", "size", "", "()I", "byteCount", "", "equals", "", "other", "", "get", "name", "getDate", "Ljava/util/Date;", "getInstant", "Ljava/time/Instant;", "hashCode", "iterator", "", "index", "names", "", "newBuilder", "Lokhttp3/Headers$Builder;", "-deprecated_size", "toMultimap", "", "", "toString", "value", "values", "Builder", "Companion", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
public final class Headers implements Iterable, KMappedMarker {
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0010\u000E\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000E\u0010\b\u001A\u00020\u00002\u0006\u0010\t\u001A\u00020\u0005J\u0016\u0010\b\u001A\u00020\u00002\u0006\u0010\n\u001A\u00020\u00052\u0006\u0010\u000B\u001A\u00020\fJ\u0016\u0010\b\u001A\u00020\u00002\u0006\u0010\n\u001A\u00020\u00052\u0006\u0010\u000B\u001A\u00020\rJ\u0016\u0010\b\u001A\u00020\u00002\u0006\u0010\n\u001A\u00020\u00052\u0006\u0010\u000B\u001A\u00020\u0005J\u000E\u0010\u000E\u001A\u00020\u00002\u0006\u0010\u000F\u001A\u00020\u0010J\u0015\u0010\u0011\u001A\u00020\u00002\u0006\u0010\t\u001A\u00020\u0005H\u0000¢\u0006\u0002\b\u0012J\u001D\u0010\u0011\u001A\u00020\u00002\u0006\u0010\n\u001A\u00020\u00052\u0006\u0010\u000B\u001A\u00020\u0005H\u0000¢\u0006\u0002\b\u0012J\u0016\u0010\u0013\u001A\u00020\u00002\u0006\u0010\n\u001A\u00020\u00052\u0006\u0010\u000B\u001A\u00020\u0005J\u0006\u0010\u0014\u001A\u00020\u0010J\u0013\u0010\u0015\u001A\u0004\u0018\u00010\u00052\u0006\u0010\n\u001A\u00020\u0005H\u0086\u0002J\u000E\u0010\u0016\u001A\u00020\u00002\u0006\u0010\n\u001A\u00020\u0005J\u0019\u0010\u0017\u001A\u00020\u00002\u0006\u0010\n\u001A\u00020\u00052\u0006\u0010\u000B\u001A\u00020\fH\u0086\u0002J\u0019\u0010\u0017\u001A\u00020\u00002\u0006\u0010\n\u001A\u00020\u00052\u0006\u0010\u000B\u001A\u00020\rH\u0086\u0002J\u0019\u0010\u0017\u001A\u00020\u00002\u0006\u0010\n\u001A\u00020\u00052\u0006\u0010\u000B\u001A\u00020\u0005H\u0086\u0002R\u001A\u0010\u0003\u001A\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0080\u0004¢\u0006\b\n\u0000\u001A\u0004\b\u0006\u0010\u0007¨\u0006\u0018"}, d2 = {"Lokhttp3/Headers$Builder;", "", "()V", "namesAndValues", "", "", "getNamesAndValues$okhttp", "()Ljava/util/List;", "add", "line", "name", "value", "Ljava/time/Instant;", "Ljava/util/Date;", "addAll", "headers", "Lokhttp3/Headers;", "addLenient", "addLenient$okhttp", "addUnsafeNonAscii", "build", "get", "removeAll", "set", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    public static final class Builder {
        private final List namesAndValues;

        public Builder() {
            this.namesAndValues = new ArrayList(20);
        }

        public final Builder add(String s) {
            Intrinsics.checkNotNullParameter(s, "line");
            int v = StringsKt.indexOf$default(s, ':', 0, false, 6, null);
            if(v == -1) {
                throw new IllegalArgumentException(("Unexpected header: " + s).toString());
            }
            String s1 = s.substring(0, v);
            Intrinsics.checkNotNullExpressionValue(s1, "this as java.lang.String…ing(startIndex, endIndex)");
            String s2 = StringsKt.trim(s1).toString();
            String s3 = s.substring(v + 1);
            Intrinsics.checkNotNullExpressionValue(s3, "this as java.lang.String).substring(startIndex)");
            this.add(s2, s3);
            return this;
        }

        public final Builder add(String s, String s1) {
            Intrinsics.checkNotNullParameter(s, "name");
            Intrinsics.checkNotNullParameter(s1, "value");
            return _HeadersCommonKt.commonAdd(this, s, s1);
        }

        public final Builder add(String s, Instant instant0) {
            Intrinsics.checkNotNullParameter(s, "name");
            Intrinsics.checkNotNullParameter(instant0, "value");
            Date date0 = Platform..ExternalSyntheticApiModelOutline0.m(instant0);
            Intrinsics.checkNotNullExpressionValue(date0, "from(value)");
            return this.add(s, date0);
        }

        public final Builder add(String s, Date date0) {
            Intrinsics.checkNotNullParameter(s, "name");
            Intrinsics.checkNotNullParameter(date0, "value");
            return this.add(s, DatesKt.toHttpDateString(date0));
        }

        public final Builder addAll(Headers headers0) {
            Intrinsics.checkNotNullParameter(headers0, "headers");
            return _HeadersCommonKt.commonAddAll(this, headers0);
        }

        public final Builder addLenient$okhttp(String s) {
            Intrinsics.checkNotNullParameter(s, "line");
            int v = StringsKt.indexOf$default(s, ':', 1, false, 4, null);
            if(v != -1) {
                String s1 = s.substring(0, v);
                Intrinsics.checkNotNullExpressionValue(s1, "this as java.lang.String…ing(startIndex, endIndex)");
                String s2 = s.substring(v + 1);
                Intrinsics.checkNotNullExpressionValue(s2, "this as java.lang.String).substring(startIndex)");
                this.addLenient$okhttp(s1, s2);
                return this;
            }
            if(s.charAt(0) == 58) {
                String s3 = s.substring(1);
                Intrinsics.checkNotNullExpressionValue(s3, "this as java.lang.String).substring(startIndex)");
                this.addLenient$okhttp("", s3);
                return this;
            }
            this.addLenient$okhttp("", s);
            return this;
        }

        public final Builder addLenient$okhttp(String s, String s1) {
            Intrinsics.checkNotNullParameter(s, "name");
            Intrinsics.checkNotNullParameter(s1, "value");
            return _HeadersCommonKt.commonAddLenient(this, s, s1);
        }

        public final Builder addUnsafeNonAscii(String s, String s1) {
            Intrinsics.checkNotNullParameter(s, "name");
            Intrinsics.checkNotNullParameter(s1, "value");
            _HeadersCommonKt.headersCheckName(s);
            this.addLenient$okhttp(s, s1);
            return this;
        }

        public final Headers build() {
            return _HeadersCommonKt.commonBuild(this);
        }

        public final String get(String s) {
            Intrinsics.checkNotNullParameter(s, "name");
            return _HeadersCommonKt.commonGet(this, s);
        }

        public final List getNamesAndValues$okhttp() {
            return this.namesAndValues;
        }

        public final Builder removeAll(String s) {
            Intrinsics.checkNotNullParameter(s, "name");
            return _HeadersCommonKt.commonRemoveAll(this, s);
        }

        public final Builder set(String s, String s1) {
            Intrinsics.checkNotNullParameter(s, "name");
            Intrinsics.checkNotNullParameter(s1, "value");
            return _HeadersCommonKt.commonSet(this, s, s1);
        }

        public final Builder set(String s, Instant instant0) {
            Intrinsics.checkNotNullParameter(s, "name");
            Intrinsics.checkNotNullParameter(instant0, "value");
            Date date0 = Platform..ExternalSyntheticApiModelOutline0.m(instant0);
            Intrinsics.checkNotNullExpressionValue(date0, "from(value)");
            return this.set(s, date0);
        }

        public final Builder set(String s, Date date0) {
            Intrinsics.checkNotNullParameter(s, "name");
            Intrinsics.checkNotNullParameter(date0, "value");
            return this.set(s, DatesKt.toHttpDateString(date0));
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000E\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J#\u0010\u0003\u001A\u00020\u00042\u0012\u0010\u0005\u001A\n\u0012\u0006\b\u0001\u0012\u00020\u00070\u0006\"\u00020\u0007H\u0007¢\u0006\u0004\b\b\u0010\tJ#\u0010\b\u001A\u00020\u00042\u0012\u0010\u0005\u001A\n\u0012\u0006\b\u0001\u0012\u00020\u00070\u0006\"\u00020\u0007H\u0007¢\u0006\u0004\b\n\u0010\tJ!\u0010\b\u001A\u00020\u00042\u0012\u0010\u000B\u001A\u000E\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\fH\u0007¢\u0006\u0002\b\nJ\u001D\u0010\r\u001A\u00020\u0004*\u000E\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\fH\u0007¢\u0006\u0002\b\b¨\u0006\u000E"}, d2 = {"Lokhttp3/Headers$Companion;", "", "()V", "headersOf", "Lokhttp3/Headers;", "namesAndValues", "", "", "of", "([Ljava/lang/String;)Lokhttp3/Headers;", "-deprecated_of", "headers", "", "toHeaders", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    public static final class Companion {
        @Deprecated(level = DeprecationLevel.ERROR, message = "function moved to extension", replaceWith = @ReplaceWith(expression = "headers.toHeaders()", imports = {}))
        public final Headers -deprecated_of(Map map0) {
            Intrinsics.checkNotNullParameter(map0, "headers");
            return this.of(map0);
        }

        @Deprecated(level = DeprecationLevel.ERROR, message = "function name changed", replaceWith = @ReplaceWith(expression = "headersOf(*namesAndValues)", imports = {}))
        public final Headers -deprecated_of(String[] arr_s) {
            Intrinsics.checkNotNullParameter(arr_s, "namesAndValues");
            return this.of(((String[])Arrays.copyOf(arr_s, arr_s.length)));
        }

        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        @JvmStatic
        public final Headers of(Map map0) {
            Intrinsics.checkNotNullParameter(map0, "<this>");
            return _HeadersCommonKt.commonToHeaders(map0);
        }

        @JvmStatic
        public final Headers of(String[] arr_s) {
            Intrinsics.checkNotNullParameter(arr_s, "namesAndValues");
            return _HeadersCommonKt.commonHeadersOf(((String[])Arrays.copyOf(arr_s, arr_s.length)));
        }
    }

    public static final Companion Companion;
    private final String[] namesAndValues;

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "size", imports = {}))
    public final int -deprecated_size() {
        return this.size();
    }

    static {
        Headers.Companion = new Companion(null);
    }

    public Headers(String[] arr_s) {
        Intrinsics.checkNotNullParameter(arr_s, "namesAndValues");
        super();
        this.namesAndValues = arr_s;
    }

    public final long byteCount() {
        long v = (long)(this.namesAndValues.length * 2);
        for(int v1 = 0; v1 < this.namesAndValues.length; ++v1) {
            v += (long)this.namesAndValues[v1].length();
        }
        return v;
    }

    @Override
    public boolean equals(Object object0) {
        return _HeadersCommonKt.commonEquals(this, object0);
    }

    public final String get(String s) {
        Intrinsics.checkNotNullParameter(s, "name");
        return _HeadersCommonKt.commonHeadersGet(this.namesAndValues, s);
    }

    public final Date getDate(String s) {
        Intrinsics.checkNotNullParameter(s, "name");
        String s1 = this.get(s);
        return s1 == null ? null : DatesKt.toHttpDateOrNull(s1);
    }

    public final Instant getInstant(String s) {
        Intrinsics.checkNotNullParameter(s, "name");
        Date date0 = this.getDate(s);
        return date0 == null ? null : date0.toInstant();
    }

    public final String[] getNamesAndValues$okhttp() {
        return this.namesAndValues;
    }

    @Override
    public int hashCode() {
        return _HeadersCommonKt.commonHashCode(this);
    }

    @Override
    public Iterator iterator() {
        return _HeadersCommonKt.commonIterator(this);
    }

    public final String name(int v) {
        return _HeadersCommonKt.commonName(this, v);
    }

    public final Set names() {
        TreeSet treeSet0 = new TreeSet(StringsKt.getCASE_INSENSITIVE_ORDER(StringCompanionObject.INSTANCE));
        int v = this.size();
        for(int v1 = 0; v1 < v; ++v1) {
            treeSet0.add(this.name(v1));
        }
        Set set0 = Collections.unmodifiableSet(treeSet0);
        Intrinsics.checkNotNullExpressionValue(set0, "unmodifiableSet(result)");
        return set0;
    }

    public final Builder newBuilder() {
        return _HeadersCommonKt.commonNewBuilder(this);
    }

    @JvmStatic
    public static final Headers of(Map map0) {
        return Headers.Companion.of(map0);
    }

    @JvmStatic
    public static final Headers of(String[] arr_s) {
        return Headers.Companion.of(arr_s);
    }

    public final int size() {
        return this.namesAndValues.length / 2;
    }

    public final Map toMultimap() {
        TreeMap treeMap0 = new TreeMap(StringsKt.getCASE_INSENSITIVE_ORDER(StringCompanionObject.INSTANCE));
        int v = this.size();
        for(int v1 = 0; v1 < v; ++v1) {
            String s = this.name(v1);
            Locale locale0 = Locale.US;
            Intrinsics.checkNotNullExpressionValue(locale0, "US");
            String s1 = s.toLowerCase(locale0);
            Intrinsics.checkNotNullExpressionValue(s1, "this as java.lang.String).toLowerCase(locale)");
            List list0 = (List)treeMap0.get(s1);
            if(list0 == null) {
                list0 = new ArrayList(2);
                treeMap0.put(s1, list0);
            }
            list0.add(this.value(v1));
        }
        return treeMap0;
    }

    @Override
    public String toString() {
        return _HeadersCommonKt.commonToString(this);
    }

    public final String value(int v) {
        return _HeadersCommonKt.commonValue(this, v);
    }

    public final List values(String s) {
        Intrinsics.checkNotNullParameter(s, "name");
        return _HeadersCommonKt.commonValues(this, s);
    }
}

