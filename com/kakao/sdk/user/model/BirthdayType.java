package com.kakao.sdk.user.model;

import com.google.gson.annotations.SerializedName;
import com.kakao.sdk.common.json.UnknownValue;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/kakao/sdk/user/model/BirthdayType;", "", "(Ljava/lang/String;I)V", "SOLAR", "LUNAR", "UNKNOWN", "user_release"}, k = 1, mv = {1, 5, 1}, xi = 0x30)
public enum BirthdayType {
    @SerializedName("SOLAR")
    SOLAR,
    @SerializedName("LUNAR")
    LUNAR,
    @UnknownValue
    UNKNOWN;

    private static final BirthdayType[] $values() [...] // Inlined contents
}

