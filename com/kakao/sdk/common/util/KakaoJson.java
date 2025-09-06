package com.kakao.sdk.common.util;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.kakao.sdk.common.json.KakaoTypeAdapterFactory;
import java.lang.reflect.Type;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000?\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0007\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007*\u0001\u000B\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J!\u0010\u000F\u001A\u0002H\u0010\"\u0004\b\u0000\u0010\u00102\u0006\u0010\u0011\u001A\u00020\u00122\u0006\u0010\u0013\u001A\u00020\u0014¢\u0006\u0002\u0010\u0015J(\u0010\u0016\u001A\b\u0012\u0004\u0012\u0002H\u00100\u0017\"\u0004\b\u0000\u0010\u00102\u0006\u0010\u0011\u001A\u00020\u00122\f\u0010\u0018\u001A\b\u0012\u0004\u0012\u0002H\u00100\u0019J)\u0010\u001A\u001A\u0002H\u0010\"\u0004\b\u0000\u0010\u00102\u0006\u0010\u0011\u001A\u00020\u00122\u0006\u0010\u0013\u001A\u00020\u00142\u0006\u0010\u001B\u001A\u00020\u0014¢\u0006\u0002\u0010\u001CJ\u0019\u0010\u001D\u001A\u00020\u0012\"\u0004\b\u0000\u0010\u00102\u0006\u0010\u001E\u001A\u0002H\u0010¢\u0006\u0002\u0010\u001FR\u0011\u0010\u0003\u001A\u00020\u0004¢\u0006\b\n\u0000\u001A\u0004\b\u0005\u0010\u0006R\u0016\u0010\u0007\u001A\n \t*\u0004\u0018\u00010\b0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001A\u00020\u000BX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\fR\u0011\u0010\r\u001A\u00020\u0004¢\u0006\b\n\u0000\u001A\u0004\b\u000E\u0010\u0006¨\u0006 "}, d2 = {"Lcom/kakao/sdk/common/util/KakaoJson;", "", "()V", "base", "Lcom/google/gson/Gson;", "getBase", "()Lcom/google/gson/Gson;", "internalBuilder", "Lcom/google/gson/GsonBuilder;", "kotlin.jvm.PlatformType", "kakaoExclusionStrategy", "com/kakao/sdk/common/util/KakaoJson$kakaoExclusionStrategy$1", "Lcom/kakao/sdk/common/util/KakaoJson$kakaoExclusionStrategy$1;", "pretty", "getPretty", "fromJson", "T", "string", "", "type1", "Ljava/lang/reflect/Type;", "(Ljava/lang/String;Ljava/lang/reflect/Type;)Ljava/lang/Object;", "listFromJson", "", "type", "Ljava/lang/Class;", "parameterizedFromJson", "type2", "(Ljava/lang/String;Ljava/lang/reflect/Type;Ljava/lang/reflect/Type;)Ljava/lang/Object;", "toJson", "model", "(Ljava/lang/Object;)Ljava/lang/String;", "common_release"}, k = 1, mv = {1, 5, 1}, xi = 0x30)
public final class KakaoJson {
    public static final KakaoJson INSTANCE;
    private static final Gson base;
    private static final GsonBuilder internalBuilder;
    private static final KakaoJson.kakaoExclusionStrategy.1 kakaoExclusionStrategy;
    private static final Gson pretty;

    static {
        KakaoJson.INSTANCE = new KakaoJson();
        KakaoJson.kakaoExclusionStrategy.1 kakaoJson$kakaoExclusionStrategy$10 = new KakaoJson.kakaoExclusionStrategy.1();
        KakaoJson.kakaoExclusionStrategy = kakaoJson$kakaoExclusionStrategy$10;
        GsonBuilder gsonBuilder0 = new GsonBuilder().registerTypeAdapterFactory(new KakaoTypeAdapterFactory()).setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).addSerializationExclusionStrategy(kakaoJson$kakaoExclusionStrategy$10).addDeserializationExclusionStrategy(kakaoJson$kakaoExclusionStrategy$10);
        KakaoJson.internalBuilder = gsonBuilder0;
        Gson gson0 = gsonBuilder0.create();
        Intrinsics.checkNotNullExpressionValue(gson0, "internalBuilder.create()");
        KakaoJson.base = gson0;
        Gson gson1 = gsonBuilder0.setPrettyPrinting().create();
        Intrinsics.checkNotNullExpressionValue(gson1, "internalBuilder.setPrettyPrinting().create()");
        KakaoJson.pretty = gson1;
    }

    public final Object fromJson(String s, Type type0) {
        Intrinsics.checkNotNullParameter(s, "string");
        Intrinsics.checkNotNullParameter(type0, "type1");
        return KakaoJson.base.fromJson(s, type0);
    }

    public final Gson getBase() {
        return KakaoJson.base;
    }

    public final Gson getPretty() {
        return KakaoJson.pretty;
    }

    public final List listFromJson(String s, Class class0) {
        Intrinsics.checkNotNullParameter(s, "string");
        Intrinsics.checkNotNullParameter(class0, "type");
        Type type0 = TypeToken.getParameterized(List.class, new Type[]{class0}).getType();
        Object object0 = KakaoJson.base.fromJson(s, type0);
        Intrinsics.checkNotNullExpressionValue(object0, "base.fromJson(string, TypeToken.getParameterized(List::class.java, type).type)");
        return (List)object0;
    }

    public final Object parameterizedFromJson(String s, Type type0, Type type1) {
        Intrinsics.checkNotNullParameter(s, "string");
        Intrinsics.checkNotNullParameter(type0, "type1");
        Intrinsics.checkNotNullParameter(type1, "type2");
        Type type2 = TypeToken.getParameterized(type0, new Type[]{type1}).getType();
        return KakaoJson.base.fromJson(s, type2);
    }

    public final String toJson(Object object0) {
        String s = KakaoJson.base.toJson(object0);
        Intrinsics.checkNotNullExpressionValue(s, "base.toJson(model)");
        return s;
    }
}

