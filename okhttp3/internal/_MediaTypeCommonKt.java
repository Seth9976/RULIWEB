package okhttp3.internal;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.internal.ProgressionUtilKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.MatchGroup;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import okhttp3.MediaType;

@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0003\n\u0002\u0010\u000B\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\u001A\u0016\u0010\u0006\u001A\u00020\u0007*\u00020\b2\b\u0010\t\u001A\u0004\u0018\u00010\nH\u0000\u001A\f\u0010\u000B\u001A\u00020\f*\u00020\bH\u0000\u001A\u0016\u0010\r\u001A\u0004\u0018\u00010\u0003*\u00020\b2\u0006\u0010\u000E\u001A\u00020\u0003H\u0000\u001A\f\u0010\u000F\u001A\u00020\b*\u00020\u0003H\u0000\u001A\f\u0010\u0010\u001A\u0004\u0018\u00010\b*\u00020\u0003\u001A\f\u0010\u0011\u001A\u00020\u0003*\u00020\bH\u0000\"\u000E\u0010\u0000\u001A\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000E\u0010\u0002\u001A\u00020\u0003X\u0082T¢\u0006\u0002\n\u0000\"\u000E\u0010\u0004\u001A\u00020\u0003X\u0082T¢\u0006\u0002\n\u0000\"\u000E\u0010\u0005\u001A\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"PARAMETER", "Lkotlin/text/Regex;", "QUOTED", "", "TOKEN", "TYPE_SUBTYPE", "commonEquals", "", "Lokhttp3/MediaType;", "other", "", "commonHashCode", "", "commonParameter", "name", "commonToMediaType", "commonToMediaTypeOrNull", "commonToString", "okhttp"}, k = 2, mv = {1, 7, 1}, xi = 0x30)
public final class _MediaTypeCommonKt {
    private static final Regex PARAMETER = null;
    private static final String QUOTED = "\"([^\"]*)\"";
    private static final String TOKEN = "([a-zA-Z0-9-!#$%&\'*+.^_`{|}~]+)";
    private static final Regex TYPE_SUBTYPE;

    static {
        _MediaTypeCommonKt.TYPE_SUBTYPE = new Regex("([a-zA-Z0-9-!#$%&\'*+.^_`{|}~]+)/([a-zA-Z0-9-!#$%&\'*+.^_`{|}~]+)");
        _MediaTypeCommonKt.PARAMETER = new Regex(";\\s*(?:([a-zA-Z0-9-!#$%&\'*+.^_`{|}~]+)=(?:([a-zA-Z0-9-!#$%&\'*+.^_`{|}~]+)|\"([^\"]*)\"))?");
    }

    public static final boolean commonEquals(MediaType mediaType0, Object object0) {
        Intrinsics.checkNotNullParameter(mediaType0, "<this>");
        return object0 instanceof MediaType && Intrinsics.areEqual(((MediaType)object0).getMediaType$okhttp(), mediaType0.getMediaType$okhttp());
    }

    public static final int commonHashCode(MediaType mediaType0) {
        Intrinsics.checkNotNullParameter(mediaType0, "<this>");
        return mediaType0.getMediaType$okhttp().hashCode();
    }

    public static final String commonParameter(MediaType mediaType0, String s) {
        Intrinsics.checkNotNullParameter(mediaType0, "<this>");
        Intrinsics.checkNotNullParameter(s, "name");
        int v1 = ProgressionUtilKt.getProgressionLastElement(0, mediaType0.getParameterNamesAndValues$okhttp().length - 1, 2);
        if(v1 >= 0) {
            for(int v = 0; true; v += 2) {
                if(StringsKt.equals(mediaType0.getParameterNamesAndValues$okhttp()[v], s, true)) {
                    return mediaType0.getParameterNamesAndValues$okhttp()[v + 1];
                }
                if(v == v1) {
                    break;
                }
            }
        }
        return null;
    }

    public static final MediaType commonToMediaType(String s) {
        Intrinsics.checkNotNullParameter(s, "<this>");
        MatchResult matchResult0 = _UtilCommonKt.matchAtPolyfill(_MediaTypeCommonKt.TYPE_SUBTYPE, s, 0);
        if(matchResult0 == null) {
            throw new IllegalArgumentException("No subtype found for: \"" + s + '\"');
        }
        String s1 = ((String)matchResult0.getGroupValues().get(1)).toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(s1, "this as java.lang.String).toLowerCase(Locale.ROOT)");
        String s2 = ((String)matchResult0.getGroupValues().get(2)).toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(s2, "this as java.lang.String).toLowerCase(Locale.ROOT)");
        List list0 = new ArrayList();
        int v = matchResult0.getRange().getLast();
        while(v + 1 < s.length()) {
            MatchResult matchResult1 = _UtilCommonKt.matchAtPolyfill(_MediaTypeCommonKt.PARAMETER, s, v + 1);
            if(matchResult1 != null) {
                MatchGroup matchGroup0 = matchResult1.getGroups().get(1);
                String s3 = matchGroup0 == null ? null : matchGroup0.getValue();
                if(s3 == null) {
                    v = matchResult1.getRange().getLast();
                    continue;
                }
                MatchGroup matchGroup1 = matchResult1.getGroups().get(2);
                String s4 = matchGroup1 == null ? null : matchGroup1.getValue();
                if(s4 == null) {
                    MatchGroup matchGroup2 = matchResult1.getGroups().get(3);
                    Intrinsics.checkNotNull(matchGroup2);
                    s4 = matchGroup2.getValue();
                }
                else if(StringsKt.startsWith$default(s4, "\'", false, 2, null) && StringsKt.endsWith$default(s4, "\'", false, 2, null) && s4.length() > 2) {
                    s4 = s4.substring(1, s4.length() - 1);
                    Intrinsics.checkNotNullExpressionValue(s4, "this as java.lang.String…ing(startIndex, endIndex)");
                }
                list0.add(s3);
                list0.add(s4);
                v = matchResult1.getRange().getLast();
                continue;
            }
            String s5 = s.substring(v + 1);
            Intrinsics.checkNotNullExpressionValue(s5, "this as java.lang.String).substring(startIndex)");
            throw new IllegalArgumentException(("Parameter is not formatted correctly: \"" + s5 + "\" for: \"" + s + '\"').toString());
        }
        Object[] arr_object = list0.toArray(new String[0]);
        Intrinsics.checkNotNull(arr_object, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
        return new MediaType(s, s1, s2, ((String[])arr_object));
    }

    public static final MediaType commonToMediaTypeOrNull(String s) {
        Intrinsics.checkNotNullParameter(s, "<this>");
        try {
            return _MediaTypeCommonKt.commonToMediaType(s);
        }
        catch(IllegalArgumentException unused_ex) {
            return null;
        }
    }

    public static final String commonToString(MediaType mediaType0) {
        Intrinsics.checkNotNullParameter(mediaType0, "<this>");
        return mediaType0.getMediaType$okhttp();
    }
}

