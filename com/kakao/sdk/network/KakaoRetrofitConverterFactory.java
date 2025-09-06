package com.kakao.sdk.network;

import com.kakao.sdk.common.json.IntDate;
import com.kakao.sdk.common.json.MapToQuery;
import com.kakao.sdk.common.util.KakaoJson;
import com.kakao.sdk.common.util.Utility;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import retrofit2.Converter.Factory;
import retrofit2.Converter;
import retrofit2.Retrofit;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u001B\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J9\u0010\u0003\u001A\u000E\u0012\u0002\b\u0003\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00042\u0006\u0010\u0006\u001A\u00020\u00072\u000E\u0010\b\u001A\n\u0012\u0006\b\u0001\u0012\u00020\n0\t2\u0006\u0010\u000B\u001A\u00020\fH\u0016¢\u0006\u0002\u0010\r¨\u0006\u000E"}, d2 = {"Lcom/kakao/sdk/network/KakaoRetrofitConverterFactory;", "Lretrofit2/Converter$Factory;", "()V", "stringConverter", "Lretrofit2/Converter;", "", "type", "Ljava/lang/reflect/Type;", "annotations", "", "", "retrofit", "Lretrofit2/Retrofit;", "(Ljava/lang/reflect/Type;[Ljava/lang/annotation/Annotation;Lretrofit2/Retrofit;)Lretrofit2/Converter;", "network_release"}, k = 1, mv = {1, 5, 1}, xi = 0x30)
public final class KakaoRetrofitConverterFactory extends Factory {
    // 检测为 Lambda 实现
    public static String $r8$lambda$4mknwR-MHVwK0XOs-t2uuO_SNGU(Map map0) [...]

    // 检测为 Lambda 实现
    public static String $r8$lambda$EHRnix255yKZnxwpDNfDDNBPmMo(Enum enum0) [...]

    // 检测为 Lambda 实现
    public static String $r8$lambda$JLPxKfeiweRkDTrbFgnO-I3B9WE(Object object0) [...]

    // 检测为 Lambda 实现
    public static String $r8$lambda$bO_o1JcoQYobGD0NV7tU_U_6tQg(Date date0) [...]

    @Override  // retrofit2.Converter$Factory
    public Converter stringConverter(Type type0, Annotation[] arr_annotation, Retrofit retrofit0) {
        Intrinsics.checkNotNullParameter(type0, "type");
        Intrinsics.checkNotNullParameter(arr_annotation, "annotations");
        Intrinsics.checkNotNullParameter(retrofit0, "retrofit");
        if(Intrinsics.areEqual(type0, String.class)) {
            return null;
        }
        if(type0 instanceof Class && ((Class)type0).isEnum()) {
            return (Enum enum0) -> KakaoRetrofitConverterFactory.stringConverter$lambda-0(enum0);
        }
        if(Intrinsics.areEqual(type0, Date.class)) {
            Collection collection0 = new ArrayList();
            for(int v1 = 0; v1 < arr_annotation.length; ++v1) {
                Annotation annotation0 = arr_annotation[v1];
                if(annotation0 instanceof IntDate) {
                    collection0.add(annotation0);
                }
            }
            if(((IntDate)CollectionsKt.firstOrNull(((List)collection0))) != null) {
                return (Date date0) -> KakaoRetrofitConverterFactory.stringConverter$lambda-2$lambda-1(date0);
            }
        }
        if(type0 instanceof ParameterizedType && Intrinsics.areEqual(((ParameterizedType)type0).getRawType(), Map.class)) {
            Collection collection1 = new ArrayList();
            for(int v = 0; v < arr_annotation.length; ++v) {
                Annotation annotation1 = arr_annotation[v];
                if(annotation1 instanceof MapToQuery) {
                    collection1.add(annotation1);
                }
            }
            if(((MapToQuery)CollectionsKt.firstOrNull(((List)collection1))) != null) {
                return (Map map0) -> KakaoRetrofitConverterFactory.stringConverter$lambda-4$lambda-3(map0);
            }
        }
        return (Object object0) -> KakaoRetrofitConverterFactory.stringConverter$lambda-5(object0);
    }

    private static final String stringConverter$lambda-0(Enum enum0) {
        Intrinsics.checkNotNullParameter(enum0, "enum");
        String s = KakaoJson.INSTANCE.toJson(enum0);
        String s1 = s.substring(1, s.length() - 1);
        Intrinsics.checkNotNullExpressionValue(s1, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return s1;
    }

    private static final String stringConverter$lambda-2$lambda-1(Date date0) {
        Intrinsics.checkNotNullParameter(date0, "value");
        return String.valueOf(date0.getTime() / 1000L);
    }

    private static final String stringConverter$lambda-4$lambda-3(Map map0) {
        Intrinsics.checkNotNullParameter(map0, "map");
        return Utility.INSTANCE.buildQuery(map0);
    }

    private static final String stringConverter$lambda-5(Object object0) {
        Intrinsics.checkNotNullParameter(object0, "value");
        return KakaoJson.INSTANCE.toJson(object0);
    }
}

