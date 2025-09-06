package okhttp3.internal;

import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import okhttp3.Headers;
import okhttp3.RequestBody.Companion;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ByteString;
import okio.FileSystem;
import okio.Options;
import okio.Path;
import okio.Sink;

@Metadata(d1 = {"\u0000\u00EA\u0001\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u001C\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\f\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001A \u0010\u0014\u001A\u00020\u00152\u0006\u0010\u0016\u001A\u00020\u00172\u0006\u0010\u0018\u001A\u00020\u00172\u0006\u0010\u0019\u001A\u00020\u0017H\u0000\u001A\u001A\u0010\u001A\u001A\u00020\u00152\f\u0010\u001B\u001A\b\u0012\u0004\u0012\u00020\u00150\u001CH\u0080\b\u00F8\u0001\u0000\u001A0\u0010\u001D\u001A\b\u0012\u0004\u0012\u0002H\u001F0\u001E\"\u0004\b\u0000\u0010\u001F2\f\u0010 \u001A\b\u0012\u0004\u0012\u0002H\u001F0!2\f\u0010\"\u001A\b\u0012\u0004\u0012\u0002H\u001F0!H\u0000\u001A\u0010\u0010#\u001A\u00020$2\u0006\u0010%\u001A\u00020\u0013H\u0000\u001A%\u0010&\u001A\u00020\u0015\"\u0004\b\u0000\u0010\'*\b\u0012\u0004\u0012\u0002H\'0(2\u0006\u0010)\u001A\u0002H\'H\u0000\u00A2\u0006\u0002\u0010*\u001A\u0015\u0010+\u001A\u00020,*\u00020-2\u0006\u0010.\u001A\u00020,H\u0080\u0004\u001A\u0015\u0010+\u001A\u00020\u0017*\u00020,2\u0006\u0010.\u001A\u00020\u0017H\u0080\u0004\u001A\u0015\u0010+\u001A\u00020,*\u00020/2\u0006\u0010.\u001A\u00020,H\u0080\u0004\u001A\u000E\u00100\u001A\u00020\u0015*\u000601j\u0002`2\u001A%\u00103\u001A\b\u0012\u0004\u0012\u00020\u001304*\b\u0012\u0004\u0012\u00020\u0013042\u0006\u00105\u001A\u00020\u0013H\u0000\u00A2\u0006\u0002\u00106\u001A\u0014\u00107\u001A\u00020\u0015*\u0002082\u0006\u00109\u001A\u00020:H\u0000\u001A\u0014\u0010;\u001A\u00020\u0015*\u0002082\u0006\u0010<\u001A\u00020:H\u0000\u001A&\u0010=\u001A\u00020,*\u00020\u00132\u0006\u0010>\u001A\u00020?2\b\b\u0002\u0010@\u001A\u00020,2\b\b\u0002\u0010A\u001A\u00020,\u001A&\u0010=\u001A\u00020,*\u00020\u00132\u0006\u0010B\u001A\u00020\u00132\b\b\u0002\u0010@\u001A\u00020,2\b\b\u0002\u0010A\u001A\u00020,\u001A;\u0010C\u001A\b\u0012\u0004\u0012\u0002H\u001F0\u001E\"\u0004\b\u0000\u0010\u001F*\b\u0012\u0004\u0012\u0002H\u001F0!2\u0017\u0010D\u001A\u0013\u0012\u0004\u0012\u0002H\u001F\u0012\u0004\u0012\u00020$0E\u00A2\u0006\u0002\bFH\u0080\b\u00F8\u0001\u0000\u001AC\u0010G\u001A\u00020$*\b\u0012\u0004\u0012\u00020\u0013042\u000E\u0010H\u001A\n\u0012\u0004\u0012\u00020\u0013\u0018\u0001042\u001A\u0010I\u001A\u0016\u0012\u0006\b\u0000\u0012\u00020\u00130Jj\n\u0012\u0006\b\u0000\u0012\u00020\u0013`KH\u0000\u00A2\u0006\u0002\u0010L\u001A7\u0010M\u001A\u00020,*\b\u0012\u0004\u0012\u00020\u0013042\u0006\u00105\u001A\u00020\u00132\u0016\u0010I\u001A\u0012\u0012\u0004\u0012\u00020\u00130Jj\b\u0012\u0004\u0012\u00020\u0013`KH\u0000\u00A2\u0006\u0002\u0010N\u001A\f\u0010O\u001A\u00020,*\u00020\u0013H\u0000\u001A \u0010P\u001A\u00020,*\u00020\u00132\b\b\u0002\u0010@\u001A\u00020,2\b\b\u0002\u0010A\u001A\u00020,H\u0000\u001A \u0010Q\u001A\u00020,*\u00020\u00132\b\b\u0002\u0010@\u001A\u00020,2\b\b\u0002\u0010A\u001A\u00020,H\u0000\u001A\u0016\u0010R\u001A\u00020,*\u00020\u00132\b\b\u0002\u0010@\u001A\u00020,H\u0000\u001AG\u0010S\u001A\b\u0012\u0004\u0012\u00020\u001304*\b\u0012\u0004\u0012\u00020\u0013042\f\u0010H\u001A\b\u0012\u0004\u0012\u00020\u0013042\u001A\u0010I\u001A\u0016\u0012\u0006\b\u0000\u0012\u00020\u00130Jj\n\u0012\u0006\b\u0000\u0012\u00020\u0013`KH\u0000\u00A2\u0006\u0002\u0010T\u001A\u0014\u0010U\u001A\u00020$*\u0002082\u0006\u0010V\u001A\u00020:H\u0000\u001A\u001E\u0010W\u001A\u0004\u0018\u00010X*\u00020Y2\u0006\u0010Z\u001A\u00020[2\u0006\u0010\\\u001A\u00020,H\u0000\u001A\f\u0010]\u001A\u00020,*\u00020?H\u0000\u001A\f\u0010^\u001A\u00020,*\u00020_H\u0000\u001A\u0014\u0010`\u001A\u00020,*\u00020a2\u0006\u0010\"\u001A\u00020-H\u0000\u001A\u0012\u0010b\u001A\u00020\u0017*\u00020\u00132\u0006\u0010c\u001A\u00020\u0017\u001A\u0016\u0010d\u001A\u00020,*\u0004\u0018\u00010\u00132\u0006\u0010c\u001A\u00020,H\u0000\u001A\u001E\u0010e\u001A\u00020\u0013*\u00020\u00132\b\b\u0002\u0010@\u001A\u00020,2\b\b\u0002\u0010A\u001A\u00020,\u001A\"\u0010f\u001A\u00020g*\u00060hj\u0002`i2\u0010\u0010j\u001A\f\u0012\b\u0012\u00060hj\u0002`i0\u001EH\u0000\u001A\u0014\u0010k\u001A\u00020\u0015*\u00020l2\u0006\u0010m\u001A\u00020,H\u0000\"\u0010\u0010\u0000\u001A\u00020\u00018\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000\"\u0014\u0010\u0002\u001A\u00020\u0003X\u0080\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0004\u0010\u0005\"\u0011\u0010\u0006\u001A\u00020\u0007\u00A2\u0006\b\n\u0000\u001A\u0004\b\b\u0010\t\"\u0011\u0010\n\u001A\u00020\u000B\u00A2\u0006\b\n\u0000\u001A\u0004\b\f\u0010\r\"\u0011\u0010\u000E\u001A\u00020\u000F\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0010\u0010\u0011\"\u000E\u0010\u0012\u001A\u00020\u0013X\u0080T\u00A2\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001\u00A8\u0006n"}, d2 = {"EMPTY_BYTE_ARRAY", "", "UNICODE_BOMS", "Lokio/Options;", "getUNICODE_BOMS", "()Lokio/Options;", "commonEmptyHeaders", "Lokhttp3/Headers;", "getCommonEmptyHeaders", "()Lokhttp3/Headers;", "commonEmptyRequestBody", "Lokhttp3/RequestBody;", "getCommonEmptyRequestBody", "()Lokhttp3/RequestBody;", "commonEmptyResponse", "Lokhttp3/ResponseBody;", "getCommonEmptyResponse", "()Lokhttp3/ResponseBody;", "userAgent", "", "checkOffsetAndCount", "", "arrayLength", "", "offset", "count", "ignoreIoExceptions", "block", "Lkotlin/Function0;", "interleave", "", "T", "a", "", "b", "isSensitiveHeader", "", "name", "addIfAbsent", "E", "", "element", "(Ljava/util/List;Ljava/lang/Object;)V", "and", "", "", "mask", "", "closeQuietly", "Ljava/io/Closeable;", "Lokio/Closeable;", "concat", "", "value", "([Ljava/lang/String;Ljava/lang/String;)[Ljava/lang/String;", "deleteContents", "Lokio/FileSystem;", "directory", "Lokio/Path;", "deleteIfExists", "path", "delimiterOffset", "delimiter", "", "startIndex", "endIndex", "delimiters", "filterList", "predicate", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "hasIntersection", "other", "comparator", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "([Ljava/lang/String;[Ljava/lang/String;Ljava/util/Comparator;)Z", "indexOf", "([Ljava/lang/String;Ljava/lang/String;Ljava/util/Comparator;)I", "indexOfControlOrNonAscii", "indexOfFirstNonAsciiWhitespace", "indexOfLastNonAsciiWhitespace", "indexOfNonWhitespace", "intersect", "([Ljava/lang/String;[Ljava/lang/String;Ljava/util/Comparator;)[Ljava/lang/String;", "isCivilized", "file", "matchAtPolyfill", "Lkotlin/text/MatchResult;", "Lkotlin/text/Regex;", "input", "", "index", "parseHexDigit", "readMedium", "Lokio/BufferedSource;", "skipAll", "Lokio/Buffer;", "toLongOrDefault", "defaultValue", "toNonNegativeInt", "trimSubstring", "withSuppressed", "", "Ljava/lang/Exception;", "Lkotlin/Exception;", "suppressed", "writeMedium", "Lokio/BufferedSink;", "medium", "okhttp"}, k = 2, mv = {1, 7, 1}, xi = 0x30)
public final class _UtilCommonKt {
    public static final byte[] EMPTY_BYTE_ARRAY = null;
    private static final Options UNICODE_BOMS = null;
    private static final Headers commonEmptyHeaders = null;
    private static final RequestBody commonEmptyRequestBody = null;
    private static final ResponseBody commonEmptyResponse = null;
    public static final String userAgent = "okhttp/5.0.0-alpha.11";

    static {
        byte[] arr_b = new byte[0];
        _UtilCommonKt.EMPTY_BYTE_ARRAY = arr_b;
        ByteString[] arr_byteString = {ByteString.Companion.decodeHex("efbbbf"), ByteString.Companion.decodeHex("feff"), ByteString.Companion.decodeHex("fffe"), ByteString.Companion.decodeHex("0000ffff"), ByteString.Companion.decodeHex("ffff0000")};
        _UtilCommonKt.UNICODE_BOMS = Options.Companion.of(arr_byteString);
        _UtilCommonKt.commonEmptyHeaders = Headers.Companion.of(new String[0]);
        _UtilCommonKt.commonEmptyRequestBody = Companion.create$default(RequestBody.Companion, arr_b, null, 0, 0, 7, null);
        _UtilCommonKt.commonEmptyResponse = okhttp3.ResponseBody.Companion.create$default(ResponseBody.Companion, arr_b, null, 1, null);
    }

    public static final void addIfAbsent(List list0, Object object0) {
        Intrinsics.checkNotNullParameter(list0, "<this>");
        if(!list0.contains(object0)) {
            list0.add(object0);
        }
    }

    public static final int and(byte b, int v) {
        return b & v;
    }

    public static final int and(short v, int v1) {
        return v & v1;
    }

    public static final long and(int v, long v1) {
        return v1 & ((long)v);
    }

    public static final void checkOffsetAndCount(long v, long v1, long v2) {
        if((v1 | v2) < 0L || v1 > v || v - v1 < v2) {
            throw new ArrayIndexOutOfBoundsException("length=" + v + ", offset=" + v1 + ", count=" + v1);
        }
    }

    public static final void closeQuietly(Closeable closeable0) {
        Intrinsics.checkNotNullParameter(closeable0, "<this>");
        try {
            closeable0.close();
        }
        catch(RuntimeException runtimeException0) {
            throw runtimeException0;
        }
        catch(Exception unused_ex) {
        }
    }

    public static final String[] concat(String[] arr_s, String s) {
        Intrinsics.checkNotNullParameter(arr_s, "<this>");
        Intrinsics.checkNotNullParameter(s, "value");
        Object[] arr_object = Arrays.copyOf(arr_s, arr_s.length + 1);
        Intrinsics.checkNotNullExpressionValue(arr_object, "copyOf(this, newSize)");
        ((String[])arr_object)[ArraysKt.getLastIndex(((String[])arr_object))] = s;
        Intrinsics.checkNotNull(((String[])arr_object), "null cannot be cast to non-null type kotlin.Array<kotlin.String>");
        return (String[])arr_object;
    }

    public static final void deleteContents(FileSystem fileSystem0, Path path0) {
        Intrinsics.checkNotNullParameter(fileSystem0, "<this>");
        Intrinsics.checkNotNullParameter(path0, "directory");
        try {
            List list0 = fileSystem0.list(path0);
        }
        catch(FileNotFoundException unused_ex) {
            return;
        }
        Throwable throwable0 = null;
        for(Object object0: list0) {
            Path path1 = (Path)object0;
            try {
                if(fileSystem0.metadata(path1).isDirectory()) {
                    _UtilCommonKt.deleteContents(fileSystem0, path1);
                }
                fileSystem0.delete(path1);
            }
            catch(IOException iOException0) {
                if(throwable0 != null) {
                    continue;
                }
                throwable0 = iOException0;
            }
        }
        if(throwable0 != null) {
            throw throwable0;
        }
    }

    public static final void deleteIfExists(FileSystem fileSystem0, Path path0) {
        Intrinsics.checkNotNullParameter(fileSystem0, "<this>");
        Intrinsics.checkNotNullParameter(path0, "path");
        try {
            fileSystem0.delete(path0);
        }
        catch(FileNotFoundException unused_ex) {
        }
    }

    public static final int delimiterOffset(String s, char c, int v, int v1) {
        Intrinsics.checkNotNullParameter(s, "<this>");
        while(v < v1) {
            if(s.charAt(v) == c) {
                return v;
            }
            ++v;
        }
        return v1;
    }

    public static final int delimiterOffset(String s, String s1, int v, int v1) {
        Intrinsics.checkNotNullParameter(s, "<this>");
        Intrinsics.checkNotNullParameter(s1, "delimiters");
        while(v < v1) {
            if(StringsKt.contains$default(s1, s.charAt(v), false, 2, null)) {
                return v;
            }
            ++v;
        }
        return v1;
    }

    public static int delimiterOffset$default(String s, char c, int v, int v1, int v2, Object object0) {
        if((v2 & 2) != 0) {
            v = 0;
        }
        if((v2 & 4) != 0) {
            v1 = s.length();
        }
        return _UtilCommonKt.delimiterOffset(s, c, v, v1);
    }

    public static int delimiterOffset$default(String s, String s1, int v, int v1, int v2, Object object0) {
        if((v2 & 2) != 0) {
            v = 0;
        }
        if((v2 & 4) != 0) {
            v1 = s.length();
        }
        return _UtilCommonKt.delimiterOffset(s, s1, v, v1);
    }

    public static final List filterList(Iterable iterable0, Function1 function10) {
        Intrinsics.checkNotNullParameter(iterable0, "<this>");
        Intrinsics.checkNotNullParameter(function10, "predicate");
        List list0 = CollectionsKt.emptyList();
        for(Object object0: iterable0) {
            if(((Boolean)function10.invoke(object0)).booleanValue()) {
                if(list0.isEmpty()) {
                    list0 = new ArrayList();
                }
                Intrinsics.checkNotNull(list0, "null cannot be cast to non-null type kotlin.collections.MutableList<T of okhttp3.internal._UtilCommonKt.filterList>");
                TypeIntrinsics.asMutableList(list0).add(object0);
            }
        }
        return list0;
    }

    public static final Headers getCommonEmptyHeaders() {
        return _UtilCommonKt.commonEmptyHeaders;
    }

    public static final RequestBody getCommonEmptyRequestBody() {
        return _UtilCommonKt.commonEmptyRequestBody;
    }

    public static final ResponseBody getCommonEmptyResponse() {
        return _UtilCommonKt.commonEmptyResponse;
    }

    public static final Options getUNICODE_BOMS() {
        return _UtilCommonKt.UNICODE_BOMS;
    }

    public static final boolean hasIntersection(String[] arr_s, String[] arr_s1, Comparator comparator0) {
        Intrinsics.checkNotNullParameter(arr_s, "<this>");
        Intrinsics.checkNotNullParameter(comparator0, "comparator");
        if(arr_s.length != 0 && arr_s1 != null && arr_s1.length != 0) {
            for(int v = 0; v < arr_s.length; ++v) {
                String s = arr_s[v];
                for(Object object0: arr_s1) {
                    if(comparator0.compare(s, ((String)object0)) == 0) {
                        return true;
                    }
                    if(false) {
                        break;
                    }
                }
            }
        }
        return false;
    }

    public static final void ignoreIoExceptions(Function0 function00) {
        Intrinsics.checkNotNullParameter(function00, "block");
        try {
            function00.invoke();
        }
        catch(IOException unused_ex) {
        }
    }

    public static final int indexOf(String[] arr_s, String s, Comparator comparator0) {
        Intrinsics.checkNotNullParameter(arr_s, "<this>");
        Intrinsics.checkNotNullParameter(s, "value");
        Intrinsics.checkNotNullParameter(comparator0, "comparator");
        for(int v = 0; v < arr_s.length; ++v) {
            if(comparator0.compare(arr_s[v], s) == 0) {
                return v;
            }
        }
        return -1;
    }

    public static final int indexOfControlOrNonAscii(String s) {
        Intrinsics.checkNotNullParameter(s, "<this>");
        int v = s.length();
        int v1 = 0;
        while(v1 < v) {
            int v2 = s.charAt(v1);
            if(Intrinsics.compare(v2, 0x1F) > 0 && Intrinsics.compare(v2, 0x7F) < 0) {
                ++v1;
                continue;
            }
            return v1;
        }
        return -1;
    }

    public static final int indexOfFirstNonAsciiWhitespace(String s, int v, int v1) {
        Intrinsics.checkNotNullParameter(s, "<this>");
        while(v < v1) {
            switch(s.charAt(v)) {
                case 9: 
                case 10: 
                case 12: 
                case 13: 
                case 0x20: {
                    ++v;
                    continue;
                }
                default: {
                    return v;
                }
            }
        }
        return v1;
    }

    public static int indexOfFirstNonAsciiWhitespace$default(String s, int v, int v1, int v2, Object object0) {
        if((v2 & 1) != 0) {
            v = 0;
        }
        if((v2 & 2) != 0) {
            v1 = s.length();
        }
        return _UtilCommonKt.indexOfFirstNonAsciiWhitespace(s, v, v1);
    }

    public static final int indexOfLastNonAsciiWhitespace(String s, int v, int v1) {
        Intrinsics.checkNotNullParameter(s, "<this>");
        int v2 = v1 - 1;
        if(v <= v2) {
        alab1:
            while(true) {
                switch(s.charAt(v2)) {
                    case 9: 
                    case 10: 
                    case 12: 
                    case 13: 
                    case 0x20: {
                        if(v2 != v) {
                            break;
                        }
                        break alab1;
                    }
                    default: {
                        return v2 + 1;
                    }
                }
                --v2;
            }
        }
        return v;
    }

    public static int indexOfLastNonAsciiWhitespace$default(String s, int v, int v1, int v2, Object object0) {
        if((v2 & 1) != 0) {
            v = 0;
        }
        if((v2 & 2) != 0) {
            v1 = s.length();
        }
        return _UtilCommonKt.indexOfLastNonAsciiWhitespace(s, v, v1);
    }

    public static final int indexOfNonWhitespace(String s, int v) {
        Intrinsics.checkNotNullParameter(s, "<this>");
        int v1 = s.length();
        while(v < v1) {
            if(s.charAt(v) != 9 && s.charAt(v) != 0x20) {
                return v;
            }
            ++v;
        }
        return s.length();
    }

    public static int indexOfNonWhitespace$default(String s, int v, int v1, Object object0) {
        if((v1 & 1) != 0) {
            v = 0;
        }
        return _UtilCommonKt.indexOfNonWhitespace(s, v);
    }

    public static final List interleave(Iterable iterable0, Iterable iterable1) {
        Intrinsics.checkNotNullParameter(iterable0, "a");
        Intrinsics.checkNotNullParameter(iterable1, "b");
        Iterator iterator0 = iterable0.iterator();
        Iterator iterator1 = iterable1.iterator();
        List list0 = CollectionsKt.createListBuilder();
        while(iterator0.hasNext() || iterator1.hasNext()) {
            if(iterator0.hasNext()) {
                Object object0 = iterator0.next();
                list0.add(object0);
            }
            if(iterator1.hasNext()) {
                Object object1 = iterator1.next();
                list0.add(object1);
            }
        }
        return CollectionsKt.build(list0);
    }

    public static final String[] intersect(String[] arr_s, String[] arr_s1, Comparator comparator0) {
        Intrinsics.checkNotNullParameter(arr_s, "<this>");
        Intrinsics.checkNotNullParameter(arr_s1, "other");
        Intrinsics.checkNotNullParameter(comparator0, "comparator");
        List list0 = new ArrayList();
        for(int v = 0; v < arr_s.length; ++v) {
            String s = arr_s[v];
            for(int v1 = 0; v1 < arr_s1.length; ++v1) {
                if(comparator0.compare(s, arr_s1[v1]) == 0) {
                    list0.add(s);
                    break;
                }
            }
        }
        Object[] arr_object = list0.toArray(new String[0]);
        Intrinsics.checkNotNull(arr_object, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
        return (String[])arr_object;
    }

    public static final boolean isCivilized(FileSystem fileSystem0, Path path0) {
        Unit unit0;
        Intrinsics.checkNotNullParameter(fileSystem0, "<this>");
        Intrinsics.checkNotNullParameter(path0, "file");
        Closeable closeable0 = fileSystem0.sink(path0);
        Throwable throwable0 = null;
        try {
            Sink sink0 = (Sink)closeable0;
            try {
                fileSystem0.delete(path0);
                return true;
            }
            catch(IOException unused_ex) {
                unit0 = Unit.INSTANCE;
            }
        }
        catch(Throwable throwable1) {
            unit0 = null;
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
        Intrinsics.checkNotNull(unit0);
        fileSystem0.delete(path0);
        return false;
    }

    // 去混淆评级： 低(20)
    public static final boolean isSensitiveHeader(String s) {
        Intrinsics.checkNotNullParameter(s, "name");
        return StringsKt.equals(s, "Authorization", true) || StringsKt.equals(s, "Cookie", true) || StringsKt.equals(s, "Proxy-Authorization", true) || StringsKt.equals(s, "Set-Cookie", true);
    }

    public static final MatchResult matchAtPolyfill(Regex regex0, CharSequence charSequence0, int v) {
        Intrinsics.checkNotNullParameter(regex0, "<this>");
        Intrinsics.checkNotNullParameter(charSequence0, "input");
        MatchResult matchResult0 = regex0.find(charSequence0, v);
        if(matchResult0 == null) {
            return null;
        }
        return matchResult0.getRange().getFirst() == v ? matchResult0 : null;
    }

    public static final int parseHexDigit(char c) {
        if(0x30 <= c && c < 58) {
            return c - 0x30;
        }
        if(97 <= c && c < 103) {
            return c - 87;
        }
        return 65 > c || c >= 71 ? -1 : c - 55;
    }

    public static final int readMedium(BufferedSource bufferedSource0) throws IOException {
        Intrinsics.checkNotNullParameter(bufferedSource0, "<this>");
        int v = _UtilCommonKt.and(bufferedSource0.readByte(), ((byte)0xFF));
        int v1 = _UtilCommonKt.and(bufferedSource0.readByte(), ((byte)0xFF));
        return _UtilCommonKt.and(bufferedSource0.readByte(), ((byte)0xFF)) | (v << 16 | v1 << 8);
    }

    public static final int skipAll(Buffer buffer0, byte b) {
        Intrinsics.checkNotNullParameter(buffer0, "<this>");
        int v = 0;
        while(!buffer0.exhausted() && buffer0.getByte(0L) == b) {
            ++v;
            buffer0.readByte();
        }
        return v;
    }

    public static final long toLongOrDefault(String s, long v) {
        Intrinsics.checkNotNullParameter(s, "<this>");
        try {
            return Long.parseLong(s);
        }
        catch(NumberFormatException unused_ex) {
            return v;
        }
    }

    public static final int toNonNegativeInt(String s, int v) {
        if(s != null) {
            try {
                long v1 = Long.parseLong(s);
                if(v1 > 0x7FFFFFFFL) {
                    return 0x7FFFFFFF;
                }
                return v1 >= 0L ? ((int)v1) : 0;
            }
            catch(NumberFormatException unused_ex) {
            }
        }
        return v;
    }

    public static final String trimSubstring(String s, int v, int v1) {
        Intrinsics.checkNotNullParameter(s, "<this>");
        int v2 = _UtilCommonKt.indexOfFirstNonAsciiWhitespace(s, v, v1);
        String s1 = s.substring(v2, _UtilCommonKt.indexOfLastNonAsciiWhitespace(s, v2, v1));
        Intrinsics.checkNotNullExpressionValue(s1, "this as java.lang.String…ing(startIndex, endIndex)");
        return s1;
    }

    public static String trimSubstring$default(String s, int v, int v1, int v2, Object object0) {
        if((v2 & 1) != 0) {
            v = 0;
        }
        if((v2 & 2) != 0) {
            v1 = s.length();
        }
        return _UtilCommonKt.trimSubstring(s, v, v1);
    }

    public static final Throwable withSuppressed(Exception exception0, List list0) {
        Intrinsics.checkNotNullParameter(exception0, "<this>");
        Intrinsics.checkNotNullParameter(list0, "suppressed");
        for(Object object0: list0) {
            ExceptionsKt.addSuppressed(exception0, ((Exception)object0));
        }
        return exception0;
    }

    public static final void writeMedium(BufferedSink bufferedSink0, int v) throws IOException {
        Intrinsics.checkNotNullParameter(bufferedSink0, "<this>");
        bufferedSink0.writeByte(v >>> 16 & 0xFF);
        bufferedSink0.writeByte(v >>> 8 & 0xFF);
        bufferedSink0.writeByte(v & 0xFF);
    }
}

