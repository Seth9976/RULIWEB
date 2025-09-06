package com.kakao.sdk.common.util;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.kakao.sdk.common.json.Exclude;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001F\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001A\u00020\u00032\f\u0010\u0004\u001A\b\u0012\u0002\b\u0003\u0018\u00010\u0005H\u0016J\u0012\u0010\u0006\u001A\u00020\u00032\b\u0010\u0007\u001A\u0004\u0018\u00010\bH\u0016¨\u0006\t"}, d2 = {"com/kakao/sdk/common/util/KakaoJson$kakaoExclusionStrategy$1", "Lcom/google/gson/ExclusionStrategy;", "shouldSkipClass", "", "clazz", "Ljava/lang/Class;", "shouldSkipField", "f", "Lcom/google/gson/FieldAttributes;", "common_release"}, k = 1, mv = {1, 5, 1}, xi = 0x30)
public final class KakaoJson.kakaoExclusionStrategy.1 implements ExclusionStrategy {
    @Override  // com.google.gson.ExclusionStrategy
    public boolean shouldSkipClass(Class class0) {
        return false;
    }

    @Override  // com.google.gson.ExclusionStrategy
    public boolean shouldSkipField(FieldAttributes fieldAttributes0) {
        Intrinsics.checkNotNull(fieldAttributes0);
        return ((Exclude)fieldAttributes0.getAnnotation(Exclude.class)) != null;
    }
}

