package okhttp3.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.internal.ProgressionUtilKt;
import kotlin.jvm.internal.ArrayIteratorKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;
import kotlin.text.StringsKt;
import okhttp3.Headers.Builder;
import okhttp3.Headers;

@Metadata(d1 = {"\u0000\\\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\f\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010(\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\u001A%\u0010\u0000\u001A\u0004\u0018\u00010\u00012\f\u0010\u0002\u001A\b\u0012\u0004\u0012\u00020\u00010\u00032\u0006\u0010\u0004\u001A\u00020\u0001H\u0000\u00A2\u0006\u0002\u0010\u0005\u001A!\u0010\u0006\u001A\u00020\u00072\u0012\u0010\b\u001A\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u0003\"\u00020\u0001H\u0000\u00A2\u0006\u0002\u0010\t\u001A\u0010\u0010\n\u001A\u00020\u000B2\u0006\u0010\u0004\u001A\u00020\u0001H\u0000\u001A\u0018\u0010\f\u001A\u00020\u000B2\u0006\u0010\r\u001A\u00020\u00012\u0006\u0010\u0004\u001A\u00020\u0001H\u0000\u001A\f\u0010\u000E\u001A\u00020\u0001*\u00020\u000FH\u0002\u001A\u001C\u0010\u0010\u001A\u00020\u0011*\u00020\u00112\u0006\u0010\u0004\u001A\u00020\u00012\u0006\u0010\r\u001A\u00020\u0001H\u0000\u001A\u0014\u0010\u0012\u001A\u00020\u0011*\u00020\u00112\u0006\u0010\u0013\u001A\u00020\u0007H\u0000\u001A\u001C\u0010\u0014\u001A\u00020\u0011*\u00020\u00112\u0006\u0010\u0004\u001A\u00020\u00012\u0006\u0010\r\u001A\u00020\u0001H\u0000\u001A\f\u0010\u0015\u001A\u00020\u0007*\u00020\u0011H\u0000\u001A\u0016\u0010\u0016\u001A\u00020\u0017*\u00020\u00072\b\u0010\u0018\u001A\u0004\u0018\u00010\u0019H\u0000\u001A\u0016\u0010\u001A\u001A\u0004\u0018\u00010\u0001*\u00020\u00112\u0006\u0010\u0004\u001A\u00020\u0001H\u0000\u001A\f\u0010\u001B\u001A\u00020\u001C*\u00020\u0007H\u0000\u001A\u001E\u0010\u001D\u001A\u0014\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00010\u001F0\u001E*\u00020\u0007H\u0000\u001A\u0014\u0010 \u001A\u00020\u0001*\u00020\u00072\u0006\u0010!\u001A\u00020\u001CH\u0000\u001A\f\u0010\"\u001A\u00020\u0011*\u00020\u0007H\u0000\u001A\u0014\u0010#\u001A\u00020\u0011*\u00020\u00112\u0006\u0010\u0004\u001A\u00020\u0001H\u0000\u001A\u001C\u0010$\u001A\u00020\u0011*\u00020\u00112\u0006\u0010\u0004\u001A\u00020\u00012\u0006\u0010\r\u001A\u00020\u0001H\u0000\u001A\u0018\u0010%\u001A\u00020\u0007*\u000E\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00010&H\u0000\u001A\f\u0010\'\u001A\u00020\u0001*\u00020\u0007H\u0000\u001A\u0014\u0010(\u001A\u00020\u0001*\u00020\u00072\u0006\u0010!\u001A\u00020\u001CH\u0000\u001A\u001A\u0010)\u001A\b\u0012\u0004\u0012\u00020\u00010**\u00020\u00072\u0006\u0010\u0004\u001A\u00020\u0001H\u0000\u00A8\u0006+"}, d2 = {"commonHeadersGet", "", "namesAndValues", "", "name", "([Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", "commonHeadersOf", "Lokhttp3/Headers;", "inputNamesAndValues", "([Ljava/lang/String;)Lokhttp3/Headers;", "headersCheckName", "", "headersCheckValue", "value", "charCode", "", "commonAdd", "Lokhttp3/Headers$Builder;", "commonAddAll", "headers", "commonAddLenient", "commonBuild", "commonEquals", "", "other", "", "commonGet", "commonHashCode", "", "commonIterator", "", "Lkotlin/Pair;", "commonName", "index", "commonNewBuilder", "commonRemoveAll", "commonSet", "commonToHeaders", "", "commonToString", "commonValue", "commonValues", "", "okhttp"}, k = 2, mv = {1, 7, 1}, xi = 0x30)
public final class _HeadersCommonKt {
    private static final String charCode(char c) {
        String s = Integer.toString(c, CharsKt.checkRadix(16));
        Intrinsics.checkNotNullExpressionValue(s, "toString(this, checkRadix(radix))");
        return s.length() >= 2 ? s : "0" + s;
    }

    public static final Builder commonAdd(Builder headers$Builder0, String s, String s1) {
        Intrinsics.checkNotNullParameter(headers$Builder0, "<this>");
        Intrinsics.checkNotNullParameter(s, "name");
        Intrinsics.checkNotNullParameter(s1, "value");
        _HeadersCommonKt.headersCheckName(s);
        _HeadersCommonKt.headersCheckValue(s1, s);
        _HeadersCommonKt.commonAddLenient(headers$Builder0, s, s1);
        return headers$Builder0;
    }

    public static final Builder commonAddAll(Builder headers$Builder0, Headers headers0) {
        Intrinsics.checkNotNullParameter(headers$Builder0, "<this>");
        Intrinsics.checkNotNullParameter(headers0, "headers");
        int v = headers0.size();
        for(int v1 = 0; v1 < v; ++v1) {
            _HeadersCommonKt.commonAddLenient(headers$Builder0, headers0.name(v1), headers0.value(v1));
        }
        return headers$Builder0;
    }

    public static final Builder commonAddLenient(Builder headers$Builder0, String s, String s1) {
        Intrinsics.checkNotNullParameter(headers$Builder0, "<this>");
        Intrinsics.checkNotNullParameter(s, "name");
        Intrinsics.checkNotNullParameter(s1, "value");
        headers$Builder0.getNamesAndValues$okhttp().add(s);
        headers$Builder0.getNamesAndValues$okhttp().add(StringsKt.trim(s1).toString());
        return headers$Builder0;
    }

    public static final Headers commonBuild(Builder headers$Builder0) {
        Intrinsics.checkNotNullParameter(headers$Builder0, "<this>");
        Object[] arr_object = headers$Builder0.getNamesAndValues$okhttp().toArray(new String[0]);
        Intrinsics.checkNotNull(arr_object, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
        return new Headers(((String[])arr_object));
    }

    public static final boolean commonEquals(Headers headers0, Object object0) {
        Intrinsics.checkNotNullParameter(headers0, "<this>");
        return object0 instanceof Headers && Arrays.equals(headers0.getNamesAndValues$okhttp(), ((Headers)object0).getNamesAndValues$okhttp());
    }

    public static final String commonGet(Builder headers$Builder0, String s) {
        Intrinsics.checkNotNullParameter(headers$Builder0, "<this>");
        Intrinsics.checkNotNullParameter(s, "name");
        int v = headers$Builder0.getNamesAndValues$okhttp().size() - 2;
        int v1 = ProgressionUtilKt.getProgressionLastElement(v, 0, -2);
        if(v1 <= v) {
            while(true) {
                if(StringsKt.equals(s, ((String)headers$Builder0.getNamesAndValues$okhttp().get(v)), true)) {
                    return (String)headers$Builder0.getNamesAndValues$okhttp().get(v + 1);
                }
                if(v == v1) {
                    break;
                }
                v -= 2;
            }
        }
        return null;
    }

    public static final int commonHashCode(Headers headers0) {
        Intrinsics.checkNotNullParameter(headers0, "<this>");
        return Arrays.hashCode(headers0.getNamesAndValues$okhttp());
    }

    public static final String commonHeadersGet(String[] arr_s, String s) {
        Intrinsics.checkNotNullParameter(arr_s, "namesAndValues");
        Intrinsics.checkNotNullParameter(s, "name");
        int v = arr_s.length - 2;
        int v1 = ProgressionUtilKt.getProgressionLastElement(v, 0, -2);
        if(v1 <= v) {
            while(true) {
                if(StringsKt.equals(s, arr_s[v], true)) {
                    return arr_s[v + 1];
                }
                if(v == v1) {
                    break;
                }
                v -= 2;
            }
        }
        return null;
    }

    public static final Headers commonHeadersOf(String[] arr_s) {
        Intrinsics.checkNotNullParameter(arr_s, "inputNamesAndValues");
        if(arr_s.length % 2 != 0) {
            throw new IllegalArgumentException("Expected alternating header names and values");
        }
        String[] arr_s1 = (String[])Arrays.copyOf(arr_s, arr_s.length);
        for(int v1 = 0; v1 < arr_s1.length; ++v1) {
            if(arr_s1[v1] == null) {
                throw new IllegalArgumentException("Headers cannot be null");
            }
            arr_s1[v1] = StringsKt.trim(arr_s[v1]).toString();
        }
        int v2 = ProgressionUtilKt.getProgressionLastElement(0, arr_s1.length - 1, 2);
        if(v2 >= 0) {
            for(int v = 0; true; v += 2) {
                String s = arr_s1[v];
                String s1 = arr_s1[v + 1];
                _HeadersCommonKt.headersCheckName(s);
                _HeadersCommonKt.headersCheckValue(s1, s);
                if(v == v2) {
                    break;
                }
            }
        }
        return new Headers(arr_s1);
    }

    public static final Iterator commonIterator(Headers headers0) {
        Intrinsics.checkNotNullParameter(headers0, "<this>");
        int v = headers0.size();
        Pair[] arr_pair = new Pair[v];
        for(int v1 = 0; v1 < v; ++v1) {
            arr_pair[v1] = TuplesKt.to(headers0.name(v1), headers0.value(v1));
        }
        return ArrayIteratorKt.iterator(arr_pair);
    }

    public static final String commonName(Headers headers0, int v) {
        Intrinsics.checkNotNullParameter(headers0, "<this>");
        String s = (String)ArraysKt.getOrNull(headers0.getNamesAndValues$okhttp(), v * 2);
        if(s == null) {
            throw new IndexOutOfBoundsException("name[" + v + ']');
        }
        return s;
    }

    public static final Builder commonNewBuilder(Headers headers0) {
        Intrinsics.checkNotNullParameter(headers0, "<this>");
        Builder headers$Builder0 = new Builder();
        CollectionsKt.addAll(headers$Builder0.getNamesAndValues$okhttp(), headers0.getNamesAndValues$okhttp());
        return headers$Builder0;
    }

    public static final Builder commonRemoveAll(Builder headers$Builder0, String s) {
        Intrinsics.checkNotNullParameter(headers$Builder0, "<this>");
        Intrinsics.checkNotNullParameter(s, "name");
        for(int v = 0; v < headers$Builder0.getNamesAndValues$okhttp().size(); v += 2) {
            if(StringsKt.equals(s, ((String)headers$Builder0.getNamesAndValues$okhttp().get(v)), true)) {
                headers$Builder0.getNamesAndValues$okhttp().remove(v);
                headers$Builder0.getNamesAndValues$okhttp().remove(v);
                v -= 2;
            }
        }
        return headers$Builder0;
    }

    public static final Builder commonSet(Builder headers$Builder0, String s, String s1) {
        Intrinsics.checkNotNullParameter(headers$Builder0, "<this>");
        Intrinsics.checkNotNullParameter(s, "name");
        Intrinsics.checkNotNullParameter(s1, "value");
        _HeadersCommonKt.headersCheckName(s);
        _HeadersCommonKt.headersCheckValue(s1, s);
        headers$Builder0.removeAll(s);
        _HeadersCommonKt.commonAddLenient(headers$Builder0, s, s1);
        return headers$Builder0;
    }

    public static final Headers commonToHeaders(Map map0) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        String[] arr_s = new String[map0.size() * 2];
        int v = 0;
        for(Object object0: map0.entrySet()) {
            String s = (String)((Map.Entry)object0).getKey();
            String s1 = (String)((Map.Entry)object0).getValue();
            String s2 = StringsKt.trim(s).toString();
            String s3 = StringsKt.trim(s1).toString();
            _HeadersCommonKt.headersCheckName(s2);
            _HeadersCommonKt.headersCheckValue(s3, s2);
            arr_s[v] = s2;
            arr_s[v + 1] = s3;
            v += 2;
        }
        return new Headers(arr_s);
    }

    public static final String commonToString(Headers headers0) {
        Intrinsics.checkNotNullParameter(headers0, "<this>");
        StringBuilder stringBuilder0 = new StringBuilder();
        int v = headers0.size();
        for(int v1 = 0; v1 < v; ++v1) {
            String s = headers0.name(v1);
            String s1 = headers0.value(v1);
            stringBuilder0.append(s);
            stringBuilder0.append(": ");
            if(_UtilCommonKt.isSensitiveHeader(s)) {
                s1 = "██";
            }
            stringBuilder0.append(s1);
            stringBuilder0.append("\n");
        }
        String s2 = stringBuilder0.toString();
        Intrinsics.checkNotNullExpressionValue(s2, "StringBuilder().apply(builderAction).toString()");
        return s2;
    }

    public static final String commonValue(Headers headers0, int v) {
        Intrinsics.checkNotNullParameter(headers0, "<this>");
        String s = (String)ArraysKt.getOrNull(headers0.getNamesAndValues$okhttp(), v * 2 + 1);
        if(s == null) {
            throw new IndexOutOfBoundsException("value[" + v + ']');
        }
        return s;
    }

    public static final List commonValues(Headers headers0, String s) {
        Intrinsics.checkNotNullParameter(headers0, "<this>");
        Intrinsics.checkNotNullParameter(s, "name");
        int v = headers0.size();
        List list0 = null;
        List list1 = null;
        for(int v1 = 0; v1 < v; ++v1) {
            if(StringsKt.equals(s, headers0.name(v1), true)) {
                if(list1 == null) {
                    list1 = new ArrayList(2);
                }
                list1.add(headers0.value(v1));
            }
        }
        if(list1 != null) {
            list0 = CollectionsKt.toList(list1);
        }
        return list0 == null ? CollectionsKt.emptyList() : list0;
    }

    public static final void headersCheckName(String s) {
        Intrinsics.checkNotNullParameter(s, "name");
        if(s.length() <= 0) {
            throw new IllegalArgumentException("name is empty");
        }
        int v = s.length();
        for(int v1 = 0; v1 < v; ++v1) {
            int v2 = s.charAt(v1);
            if(33 > v2 || v2 >= 0x7F) {
                throw new IllegalArgumentException(("Unexpected char 0x" + _HeadersCommonKt.charCode(((char)v2)) + " at " + v1 + " in header name: " + s).toString());
            }
        }
    }

    public static final void headersCheckValue(String s, String s1) {
        Intrinsics.checkNotNullParameter(s, "value");
        Intrinsics.checkNotNullParameter(s1, "name");
        int v = s.length();
        for(int v1 = 0; v1 < v; ++v1) {
            int v2 = s.charAt(v1);
            if(v2 != 9 && (0x20 > v2 || v2 >= 0x7F)) {
                throw new IllegalArgumentException(("Unexpected char 0x" + _HeadersCommonKt.charCode(((char)v2)) + " at " + v1 + " in " + s1 + " value" + (_UtilCommonKt.isSensitiveHeader(s1) ? "" : ": " + s)).toString());
            }
        }
    }
}

