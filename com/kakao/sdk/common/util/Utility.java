package com.kakao.sdk.common.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.Signature;
import android.os.Build.VERSION;
import android.os.Build;
import android.provider.Settings.Secure;
import android.util.Base64;
import com.google.gson.JsonArray;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.kakao.sdk.common.KakaoSdk.Type;
import com.kakao.sdk.common.model.SdkIdentifier;
import java.io.File;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.io.FilesKt;
import kotlin.io.path.LinkFollowing..ExternalSyntheticApiModelOutline0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.Regex;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000B\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u0006H\u0007J\u001C\u0010\u0007\u001A\u00020\b2\u0014\u0010\t\u001A\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0018\u00010\nJ\u0016\u0010\u000B\u001A\u00020\f2\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010\r\u001A\u00020\u000EJ\u000E\u0010\u000F\u001A\u00020\b2\u0006\u0010\u0010\u001A\u00020\bJ\u000E\u0010\u0011\u001A\u00020\u00122\u0006\u0010\u0010\u001A\u00020\bJ\u000E\u0010\u0013\u001A\u00020\f2\u0006\u0010\u0010\u001A\u00020\bJ\"\u0010\u0014\u001A\u00020\b2\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010\r\u001A\u00020\u000E2\n\b\u0002\u0010\u0015\u001A\u0004\u0018\u00010\u0016J\u0010\u0010\u0017\u001A\u00020\b2\u0006\u0010\u0005\u001A\u00020\u0006H\u0007J\u0010\u0010\u0018\u001A\u00020\b2\u0006\u0010\u0005\u001A\u00020\u0006H\u0007J\u0018\u0010\u0019\u001A\u0004\u0018\u00010\b2\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u001A\u001A\u00020\bJ\u0016\u0010\u001B\u001A\u00020\u001C2\u0006\u0010\u001D\u001A\u00020\f2\u0006\u0010\u001A\u001A\u00020\bJ\u001C\u0010\u001E\u001A\u000E\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\n2\b\u0010\u001F\u001A\u0004\u0018\u00010\b¨\u0006 "}, d2 = {"Lcom/kakao/sdk/common/util/Utility;", "", "()V", "androidId", "", "context", "Landroid/content/Context;", "buildQuery", "", "params", "", "getExtras", "Lcom/google/gson/JsonObject;", "sdkType", "Lcom/kakao/sdk/common/KakaoSdk$Type;", "getJson", "path", "getJsonArray", "Lcom/google/gson/JsonArray;", "getJsonObject", "getKAHeader", "sdkIdentifier", "Lcom/kakao/sdk/common/model/SdkIdentifier;", "getKeyHash", "getKeyHashDeprecated", "getMetadata", "key", "hasAndNotNull", "", "jsonObject", "parseQuery", "queries", "common_release"}, k = 1, mv = {1, 5, 1}, xi = 0x30)
public final class Utility {
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 0x30)
    public final class WhenMappings {
        public static final int[] $EnumSwitchMapping$0;

        static {
            int[] arr_v = new int[Type.values().length];
            arr_v[Type.RX_KOTLIN.ordinal()] = 1;
            WhenMappings.$EnumSwitchMapping$0 = arr_v;
        }
    }

    public static final Utility INSTANCE;

    static {
        Utility.INSTANCE = new Utility();
    }

    public final byte[] androidId(Context context0) throws NoSuchAlgorithmException {
        Intrinsics.checkNotNullParameter(context0, "context");
        try {
            String s = Settings.Secure.getString(context0.getContentResolver(), "android_id");
            Intrinsics.checkNotNullExpressionValue(s, "androidId");
            String s1 = new Regex("[0\\s]").replace(s, "");
            MessageDigest messageDigest0 = MessageDigest.getInstance("SHA-256");
            messageDigest0.reset();
            Charset charset0 = Charsets.UTF_8;
            if("SDK-" + s1 != null) {
                byte[] arr_b = ("SDK-" + s1).getBytes(charset0);
                Intrinsics.checkNotNullExpressionValue(arr_b, "(this as java.lang.String).getBytes(charset)");
                messageDigest0.update(arr_b);
                byte[] arr_b1 = messageDigest0.digest();
                Intrinsics.checkNotNullExpressionValue(arr_b1, "{\n            val androidId =\n                Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)\n            val stripped = androidId.replace(\"[0\\\\s]\".toRegex(), \"\")\n            val md = MessageDigest.getInstance(\"SHA-256\")\n            md.reset()\n            md.update(\"SDK-$stripped\".toByteArray())\n            md.digest()\n        }");
                return arr_b1;
            }
        }
        catch(Exception unused_ex) {
        }
        String s2 = "xxxx" + Build.PRODUCT + "a23456789012345bcdefg";
        Charset charset1 = Charsets.UTF_8;
        if(s2 == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        byte[] arr_b2 = s2.getBytes(charset1);
        Intrinsics.checkNotNullExpressionValue(arr_b2, "(this as java.lang.String).getBytes(charset)");
        return arr_b2;
    }

    public final String buildQuery(Map map0) {
        if(map0 != null && !map0.isEmpty()) {
            ArrayList arrayList0 = new ArrayList(map0.size());
            for(Object object0: map0.entrySet()) {
                arrayList0.add(((String)((Map.Entry)object0).getKey()) + '=' + ((String)((Map.Entry)object0).getValue()));
            }
            Iterator iterator1 = arrayList0.iterator();
            if(!iterator1.hasNext()) {
                throw new UnsupportedOperationException("Empty collection can\'t be reduced.");
            }
            String s;
            for(s = iterator1.next(); iterator1.hasNext(); s = s + '&' + ((String)object1)) {
                Object object1 = iterator1.next();
            }
            return s;
        }
        return "";
    }

    public final JsonObject getExtras(Context context0, Type kakaoSdk$Type0) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(kakaoSdk$Type0, "sdkType");
        JsonObject jsonObject0 = new JsonObject();
        jsonObject0.addProperty("appPkg", "com.ruliweb.www.ruliapp");
        jsonObject0.addProperty("keyHash", this.getKeyHash(context0));
        jsonObject0.addProperty("KA", Utility.getKAHeader$default(this, context0, kakaoSdk$Type0, null, 4, null));
        return jsonObject0;
    }

    public final String getJson(String s) {
        Intrinsics.checkNotNullParameter(s, "path");
        ClassLoader classLoader0 = this.getClass().getClassLoader();
        classLoader0.getClass();
        return new String(FilesKt.readBytes(new File(classLoader0.getResource(s).getPath())), Charsets.UTF_8);
    }

    public final JsonArray getJsonArray(String s) {
        Intrinsics.checkNotNullParameter(s, "path");
        String s1 = this.getJson(s);
        return (JsonArray)KakaoJson.INSTANCE.fromJson(s1, JsonArray.class);
    }

    public final JsonObject getJsonObject(String s) {
        Intrinsics.checkNotNullParameter(s, "path");
        String s1 = this.getJson(s);
        return (JsonObject)KakaoJson.INSTANCE.fromJson(s1, JsonObject.class);
    }

    public final String getKAHeader(Context context0, Type kakaoSdk$Type0, SdkIdentifier sdkIdentifier0) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(kakaoSdk$Type0, "sdkType");
        String s = Build.VERSION.SDK_INT < 33 ? context0.getPackageManager().getPackageInfo("com.ruliweb.www.ruliapp", 0).versionName : LinkFollowing..ExternalSyntheticApiModelOutline0.m(context0.getPackageManager(), "com.ruliweb.www.ruliapp", LinkFollowing..ExternalSyntheticApiModelOutline0.m(0L)).versionName;
        String s1 = WhenMappings.$EnumSwitchMapping$0[kakaoSdk$Type0.ordinal()] == 1 ? "rx-kotlin" : "kotlin";
        Integer integer0 = Build.VERSION.SDK_INT;
        String s2 = Locale.getDefault().getLanguage();
        Intrinsics.checkNotNullExpressionValue(s2, "getDefault().language");
        String s3 = s2.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(s3, "(this as java.lang.Strin….toLowerCase(Locale.ROOT)");
        String s4 = Locale.getDefault().getCountry();
        Intrinsics.checkNotNullExpressionValue(s4, "getDefault().country");
        String s5 = s4.toUpperCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(s5, "(this as java.lang.Strin….toUpperCase(Locale.ROOT)");
        String s6 = this.getKeyHash(context0);
        String s7 = Build.MODEL;
        Intrinsics.checkNotNullExpressionValue(s7, "MODEL");
        Locale locale0 = Locale.US;
        Intrinsics.checkNotNullExpressionValue(locale0, "US");
        String s8 = s7.toUpperCase(locale0);
        Intrinsics.checkNotNullExpressionValue(s8, "(this as java.lang.String).toUpperCase(locale)");
        CharSequence charSequence0 = new Regex("[^\\p{ASCII}]").replace(s8, "*");
        String s9 = String.format("%s/%s %s/%s %s/android-%s %s/%s-%s %s/%s %s/%s %s/%s %s/%s", Arrays.copyOf(new Object[]{"sdk", "2.13.0", "sdk_type", s1, "os", integer0, "lang", s3, s5, "origin", s6, "device", new Regex("\\s").replace(charSequence0, "-"), "android_pkg", "com.ruliweb.www.ruliapp", "app_ver", s}, 17));
        Intrinsics.checkNotNullExpressionValue(s9, "java.lang.String.format(format, *args)");
        if(sdkIdentifier0 != null) {
            String s10 = sdkIdentifier0.getIdentifiers();
            if(s10 != null) {
                String s11 = s9 + s10;
                return s11 == null ? s9 : s11;
            }
        }
        return s9;
    }

    public static String getKAHeader$default(Utility utility0, Context context0, Type kakaoSdk$Type0, SdkIdentifier sdkIdentifier0, int v, Object object0) {
        if((v & 4) != 0) {
            sdkIdentifier0 = null;
        }
        return utility0.getKAHeader(context0, kakaoSdk$Type0, sdkIdentifier0);
    }

    public final String getKeyHash(Context context0) {
        Intrinsics.checkNotNullParameter(context0, "context");
        return this.getKeyHashDeprecated(context0);
    }

    public final String getKeyHashDeprecated(Context context0) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Signature[] arr_signature = context0.getPackageManager().getPackageInfo("com.ruliweb.www.ruliapp", 0x40).signatures;
        Intrinsics.checkNotNullExpressionValue(arr_signature, "packageInfo.signatures");
        if(arr_signature.length <= 0) {
            throw new IllegalStateException();
        }
        Signature signature0 = arr_signature[0];
        MessageDigest messageDigest0 = MessageDigest.getInstance("SHA");
        messageDigest0.update(signature0.toByteArray());
        String s = Base64.encodeToString(messageDigest0.digest(), 2);
        Intrinsics.checkNotNullExpressionValue(s, "encodeToString(md.digest(), Base64.NO_WRAP)");
        return s;
    }

    public final String getMetadata(Context context0, String s) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(s, "key");
        ApplicationInfo applicationInfo0 = Build.VERSION.SDK_INT < 33 ? context0.getPackageManager().getApplicationInfo("com.ruliweb.www.ruliapp", 0x80) : LinkFollowing..ExternalSyntheticApiModelOutline0.m(context0.getPackageManager(), "com.ruliweb.www.ruliapp", LinkFollowing..ExternalSyntheticApiModelOutline0.m(0x80L));
        Intrinsics.checkNotNullExpressionValue(applicationInfo0, "if (Build.VERSION.SDK_INT >= 33) {\n            context.packageManager.getApplicationInfo(\n                context.packageName,\n                PackageManager.ApplicationInfoFlags.of(PackageManager.GET_META_DATA.toLong())\n            )\n        } else {\n            @Suppress(\"DEPRECATION\")\n            context.packageManager.getApplicationInfo(\n                context.packageName,\n                PackageManager.GET_META_DATA\n            )\n        }");
        return applicationInfo0.metaData.getString(s);
    }

    public final boolean hasAndNotNull(JsonObject jsonObject0, String s) {
        Intrinsics.checkNotNullParameter(jsonObject0, "jsonObject");
        Intrinsics.checkNotNullParameter(s, "key");
        return jsonObject0.has(s) && !(jsonObject0.get(s) instanceof JsonNull);
    }

    public final Map parseQuery(String s) {
        if(s == null) {
            return MapsKt.emptyMap();
        }
        Iterable iterable0 = StringsKt.split$default(s, new String[]{"&"}, false, 0, 6, null);
        ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable0, 10));
        for(Object object0: iterable0) {
            arrayList0.add(StringsKt.split$default(((String)object0), new String[]{"="}, false, 0, 6, null));
        }
        Collection collection0 = new ArrayList();
        for(Object object1: arrayList0) {
            if(((List)object1).size() > 1) {
                collection0.add(object1);
            }
        }
        ArrayList arrayList1 = new ArrayList(CollectionsKt.collectionSizeOrDefault(((List)collection0), 10));
        for(Object object2: ((List)collection0)) {
            arrayList1.add(new Pair(((List)object2).get(0), ((List)object2).get(1)));
        }
        Map map0 = new LinkedHashMap();
        for(Object object3: arrayList1) {
            String s1 = URLDecoder.decode(((String)((Pair)object3).getSecond()), "UTF-8");
            Intrinsics.checkNotNullExpressionValue(s1, "decode(pair.second, \"UTF-8\")");
            map0.put(((Pair)object3).getFirst(), s1);
        }
        return map0;
    }
}

