package okhttp3.internal;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlin.time.Duration;
import kotlin.time.DurationKt;
import kotlin.time.DurationUnit;
import okhttp3.CacheControl.Builder;
import okhttp3.CacheControl.Companion;
import okhttp3.CacheControl;
import okhttp3.Headers;

@Metadata(d1 = {"\u00004\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0004\u001A\f\u0010\u0000\u001A\u00020\u0001*\u00020\u0002H\u0000\u001A\f\u0010\u0003\u001A\u00020\u0004*\u00020\u0005H\u0000\u001A\f\u0010\u0006\u001A\u00020\u0001*\u00020\u0007H\u0000\u001A\f\u0010\b\u001A\u00020\u0001*\u00020\u0007H\u0000\u001A\f\u0010\t\u001A\u00020\u0002*\u00020\u0002H\u0000\u001A\u001C\u0010\n\u001A\u00020\u0002*\u00020\u00022\u0006\u0010\u000B\u001A\u00020\u00042\u0006\u0010\f\u001A\u00020\rH\u0000\u001A\u001C\u0010\u000E\u001A\u00020\u0002*\u00020\u00022\u0006\u0010\u000F\u001A\u00020\u00042\u0006\u0010\f\u001A\u00020\rH\u0000\u001A\u001C\u0010\u0010\u001A\u00020\u0002*\u00020\u00022\u0006\u0010\u0011\u001A\u00020\u00042\u0006\u0010\f\u001A\u00020\rH\u0000\u001A\f\u0010\u0012\u001A\u00020\u0002*\u00020\u0002H\u0000\u001A\f\u0010\u0013\u001A\u00020\u0002*\u00020\u0002H\u0000\u001A\f\u0010\u0014\u001A\u00020\u0002*\u00020\u0002H\u0000\u001A\f\u0010\u0015\u001A\u00020\u0002*\u00020\u0002H\u0000\u001A\u0014\u0010\u0016\u001A\u00020\u0001*\u00020\u00072\u0006\u0010\u0017\u001A\u00020\u0018H\u0000\u001A\f\u0010\u0019\u001A\u00020\u001A*\u00020\u0001H\u0000\u001A\u001E\u0010\u001B\u001A\u00020\u0004*\u00020\u001A2\u0006\u0010\u001C\u001A\u00020\u001A2\b\b\u0002\u0010\u001D\u001A\u00020\u0004H\u0002¨\u0006\u001E"}, d2 = {"commonBuild", "Lokhttp3/CacheControl;", "Lokhttp3/CacheControl$Builder;", "commonClampToInt", "", "", "commonForceCache", "Lokhttp3/CacheControl$Companion;", "commonForceNetwork", "commonImmutable", "commonMaxAge", "maxAge", "timeUnit", "Lkotlin/time/DurationUnit;", "commonMaxStale", "maxStale", "commonMinFresh", "minFresh", "commonNoCache", "commonNoStore", "commonNoTransform", "commonOnlyIfCached", "commonParse", "headers", "Lokhttp3/Headers;", "commonToString", "", "indexOfElement", "characters", "startIndex", "okhttp"}, k = 2, mv = {1, 7, 1}, xi = 0x30)
public final class _CacheControlCommonKt {
    public static final CacheControl commonBuild(Builder cacheControl$Builder0) {
        Intrinsics.checkNotNullParameter(cacheControl$Builder0, "<this>");
        return new CacheControl(cacheControl$Builder0.getNoCache$okhttp(), cacheControl$Builder0.getNoStore$okhttp(), cacheControl$Builder0.getMaxAgeSeconds$okhttp(), -1, false, false, false, cacheControl$Builder0.getMaxStaleSeconds$okhttp(), cacheControl$Builder0.getMinFreshSeconds$okhttp(), cacheControl$Builder0.getOnlyIfCached$okhttp(), cacheControl$Builder0.getNoTransform$okhttp(), cacheControl$Builder0.getImmutable$okhttp(), null);
    }

    public static final int commonClampToInt(long v) {
        return v <= 0x7FFFFFFFL ? ((int)v) : 0x7FFFFFFF;
    }

    public static final CacheControl commonForceCache(Companion cacheControl$Companion0) {
        Intrinsics.checkNotNullParameter(cacheControl$Companion0, "<this>");
        return new Builder().onlyIfCached().maxStale(0x7FFFFFFF, DurationUnit.SECONDS).build();
    }

    public static final CacheControl commonForceNetwork(Companion cacheControl$Companion0) {
        Intrinsics.checkNotNullParameter(cacheControl$Companion0, "<this>");
        return new Builder().noCache().build();
    }

    public static final Builder commonImmutable(Builder cacheControl$Builder0) {
        Intrinsics.checkNotNullParameter(cacheControl$Builder0, "<this>");
        cacheControl$Builder0.setImmutable$okhttp(true);
        return cacheControl$Builder0;
    }

    public static final Builder commonMaxAge(Builder cacheControl$Builder0, int v, DurationUnit durationUnit0) {
        Intrinsics.checkNotNullParameter(cacheControl$Builder0, "<this>");
        Intrinsics.checkNotNullParameter(durationUnit0, "timeUnit");
        if(v < 0) {
            throw new IllegalArgumentException(("maxAge < 0: " + v).toString());
        }
        cacheControl$Builder0.setMaxAgeSeconds$okhttp(_CacheControlCommonKt.commonClampToInt(Duration.getInWholeSeconds-impl(DurationKt.toDuration(v, durationUnit0))));
        return cacheControl$Builder0;
    }

    public static final Builder commonMaxStale(Builder cacheControl$Builder0, int v, DurationUnit durationUnit0) {
        Intrinsics.checkNotNullParameter(cacheControl$Builder0, "<this>");
        Intrinsics.checkNotNullParameter(durationUnit0, "timeUnit");
        if(v < 0) {
            throw new IllegalArgumentException(("maxStale < 0: " + v).toString());
        }
        cacheControl$Builder0.setMaxStaleSeconds$okhttp(_CacheControlCommonKt.commonClampToInt(Duration.getInWholeSeconds-impl(DurationKt.toDuration(v, durationUnit0))));
        return cacheControl$Builder0;
    }

    public static final Builder commonMinFresh(Builder cacheControl$Builder0, int v, DurationUnit durationUnit0) {
        Intrinsics.checkNotNullParameter(cacheControl$Builder0, "<this>");
        Intrinsics.checkNotNullParameter(durationUnit0, "timeUnit");
        if(v < 0) {
            throw new IllegalArgumentException(("minFresh < 0: " + v).toString());
        }
        cacheControl$Builder0.setMinFreshSeconds$okhttp(_CacheControlCommonKt.commonClampToInt(Duration.getInWholeSeconds-impl(DurationKt.toDuration(v, durationUnit0))));
        return cacheControl$Builder0;
    }

    public static final Builder commonNoCache(Builder cacheControl$Builder0) {
        Intrinsics.checkNotNullParameter(cacheControl$Builder0, "<this>");
        cacheControl$Builder0.setNoCache$okhttp(true);
        return cacheControl$Builder0;
    }

    public static final Builder commonNoStore(Builder cacheControl$Builder0) {
        Intrinsics.checkNotNullParameter(cacheControl$Builder0, "<this>");
        cacheControl$Builder0.setNoStore$okhttp(true);
        return cacheControl$Builder0;
    }

    public static final Builder commonNoTransform(Builder cacheControl$Builder0) {
        Intrinsics.checkNotNullParameter(cacheControl$Builder0, "<this>");
        cacheControl$Builder0.setNoTransform$okhttp(true);
        return cacheControl$Builder0;
    }

    public static final Builder commonOnlyIfCached(Builder cacheControl$Builder0) {
        Intrinsics.checkNotNullParameter(cacheControl$Builder0, "<this>");
        cacheControl$Builder0.setOnlyIfCached$okhttp(true);
        return cacheControl$Builder0;
    }

    public static final CacheControl commonParse(Companion cacheControl$Companion0, Headers headers0) {
        int v10;
        String s5;
        int v6;
        Intrinsics.checkNotNullParameter(cacheControl$Companion0, "<this>");
        Intrinsics.checkNotNullParameter(headers0, "headers");
        int v = headers0.size();
        int v1 = 0;
        boolean z = true;
        String s = null;
        boolean z1 = false;
        boolean z2 = false;
        int v2 = -1;
        int v3 = -1;
        boolean z3 = false;
        boolean z4 = false;
        boolean z5 = false;
        int v4 = -1;
        int v5 = -1;
        boolean z6 = false;
        boolean z7 = false;
        boolean z8 = false;
        while(v1 < v) {
            String s1 = headers0.name(v1);
            String s2 = headers0.value(v1);
            if(StringsKt.equals(s1, "Cache-Control", true)) {
                if(s == null) {
                    s = s2;
                }
                else {
                    z = false;
                }
                v6 = 0;
                goto label_31;
            }
            else if(StringsKt.equals(s1, "Pragma", true)) {
                z = false;
                v6 = 0;
            label_31:
                while(v6 < s2.length()) {
                    int v7 = _CacheControlCommonKt.indexOfElement(s2, "=,;", v6);
                    String s3 = s2.substring(v6, v7);
                    Intrinsics.checkNotNullExpressionValue(s3, "this as java.lang.String…ing(startIndex, endIndex)");
                    String s4 = StringsKt.trim(s3).toString();
                    if(v7 == s2.length() || s2.charAt(v7) == 44 || s2.charAt(v7) == 59) {
                        v10 = v7 + 1;
                        s5 = null;
                    }
                    else {
                        int v8 = _UtilCommonKt.indexOfNonWhitespace(s2, v7 + 1);
                        if(v8 >= s2.length() || s2.charAt(v8) != 34) {
                            int v11 = _CacheControlCommonKt.indexOfElement(s2, ",;", v8);
                            String s6 = s2.substring(v8, v11);
                            Intrinsics.checkNotNullExpressionValue(s6, "this as java.lang.String…ing(startIndex, endIndex)");
                            s5 = StringsKt.trim(s6).toString();
                            v10 = v11;
                        }
                        else {
                            int v9 = StringsKt.indexOf$default(s2, '\"', v8 + 1, false, 4, null);
                            s5 = s2.substring(v8 + 1, v9);
                            Intrinsics.checkNotNullExpressionValue(s5, "this as java.lang.String…ing(startIndex, endIndex)");
                            v10 = v9 + 1;
                        }
                    }
                    if(StringsKt.equals("no-cache", s4, true)) {
                        v6 = v10;
                        z1 = true;
                    }
                    else if(StringsKt.equals("no-store", s4, true)) {
                        v6 = v10;
                        z2 = true;
                    }
                    else {
                        if(StringsKt.equals("max-age", s4, true)) {
                            v2 = _UtilCommonKt.toNonNegativeInt(s5, -1);
                        }
                        else if(StringsKt.equals("s-maxage", s4, true)) {
                            v3 = _UtilCommonKt.toNonNegativeInt(s5, -1);
                        }
                        else if(StringsKt.equals("private", s4, true)) {
                            v6 = v10;
                            z3 = true;
                            continue;
                        }
                        else if(StringsKt.equals("public", s4, true)) {
                            v6 = v10;
                            z4 = true;
                            continue;
                        }
                        else if(StringsKt.equals("must-revalidate", s4, true)) {
                            v6 = v10;
                            z5 = true;
                            continue;
                        }
                        else if(StringsKt.equals("max-stale", s4, true)) {
                            v4 = _UtilCommonKt.toNonNegativeInt(s5, 0x7FFFFFFF);
                        }
                        else if(StringsKt.equals("min-fresh", s4, true)) {
                            v5 = _UtilCommonKt.toNonNegativeInt(s5, -1);
                        }
                        else {
                            goto label_85;
                        }
                        v6 = v10;
                        continue;
                    label_85:
                        if(StringsKt.equals("only-if-cached", s4, true)) {
                            v6 = v10;
                            z6 = true;
                        }
                        else if(StringsKt.equals("no-transform", s4, true)) {
                            v6 = v10;
                            z7 = true;
                        }
                        else {
                            v6 = v10;
                            if(StringsKt.equals("immutable", s4, true)) {
                                z8 = true;
                            }
                        }
                    }
                }
            }
            ++v1;
        }
        return z ? new CacheControl(z1, z2, v2, v3, z3, z4, z5, v4, v5, z6, z7, z8, s) : new CacheControl(z1, z2, v2, v3, z3, z4, z5, v4, v5, z6, z7, z8, null);
    }

    public static final String commonToString(CacheControl cacheControl0) {
        Intrinsics.checkNotNullParameter(cacheControl0, "<this>");
        String s = cacheControl0.getHeaderValue$okhttp();
        if(s == null) {
            StringBuilder stringBuilder0 = new StringBuilder();
            if(cacheControl0.noCache()) {
                stringBuilder0.append("no-cache, ");
            }
            if(cacheControl0.noStore()) {
                stringBuilder0.append("no-store, ");
            }
            if(cacheControl0.maxAgeSeconds() != -1) {
                stringBuilder0.append("max-age=");
                stringBuilder0.append(cacheControl0.maxAgeSeconds());
                stringBuilder0.append(", ");
            }
            if(cacheControl0.sMaxAgeSeconds() != -1) {
                stringBuilder0.append("s-maxage=");
                stringBuilder0.append(cacheControl0.sMaxAgeSeconds());
                stringBuilder0.append(", ");
            }
            if(cacheControl0.isPrivate()) {
                stringBuilder0.append("private, ");
            }
            if(cacheControl0.isPublic()) {
                stringBuilder0.append("public, ");
            }
            if(cacheControl0.mustRevalidate()) {
                stringBuilder0.append("must-revalidate, ");
            }
            if(cacheControl0.maxStaleSeconds() != -1) {
                stringBuilder0.append("max-stale=");
                stringBuilder0.append(cacheControl0.maxStaleSeconds());
                stringBuilder0.append(", ");
            }
            if(cacheControl0.minFreshSeconds() != -1) {
                stringBuilder0.append("min-fresh=");
                stringBuilder0.append(cacheControl0.minFreshSeconds());
                stringBuilder0.append(", ");
            }
            if(cacheControl0.onlyIfCached()) {
                stringBuilder0.append("only-if-cached, ");
            }
            if(cacheControl0.noTransform()) {
                stringBuilder0.append("no-transform, ");
            }
            if(cacheControl0.immutable()) {
                stringBuilder0.append("immutable, ");
            }
            if(stringBuilder0.length() == 0) {
                return "";
            }
            Intrinsics.checkNotNullExpressionValue(stringBuilder0.delete(stringBuilder0.length() - 2, stringBuilder0.length()), "this.delete(startIndex, endIndex)");
            s = stringBuilder0.toString();
            Intrinsics.checkNotNullExpressionValue(s, "StringBuilder().apply(builderAction).toString()");
            cacheControl0.setHeaderValue$okhttp(s);
        }
        return s;
    }

    private static final int indexOfElement(String s, String s1, int v) {
        int v1 = s.length();
        while(v < v1) {
            if(StringsKt.contains$default(s1, s.charAt(v), false, 2, null)) {
                return v;
            }
            ++v;
        }
        return s.length();
    }

    static int indexOfElement$default(String s, String s1, int v, int v1, Object object0) {
        if((v1 & 2) != 0) {
            v = 0;
        }
        return _CacheControlCommonKt.indexOfElement(s, s1, v);
    }
}

