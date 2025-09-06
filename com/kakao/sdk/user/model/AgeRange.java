package com.kakao.sdk.user.model;

import com.google.gson.annotations.SerializedName;
import com.kakao.sdk.common.json.UnknownValue;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u000E\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000Bj\u0002\b\fj\u0002\b\rj\u0002\b\u000E¨\u0006\u000F"}, d2 = {"Lcom/kakao/sdk/user/model/AgeRange;", "", "(Ljava/lang/String;I)V", "AGE_0_9", "AGE_10_14", "AGE_15_19", "AGE_20_29", "AGE_30_39", "AGE_40_49", "AGE_50_59", "AGE_60_69", "AGE_70_79", "AGE_80_89", "AGE_90_ABOVE", "UNKNOWN", "user_release"}, k = 1, mv = {1, 5, 1}, xi = 0x30)
public enum AgeRange {
    @SerializedName("0~9")
    AGE_0_9,
    @SerializedName("10~14")
    AGE_10_14,
    @SerializedName("15~19")
    AGE_15_19,
    @SerializedName("20~29")
    AGE_20_29,
    @SerializedName("30~39")
    AGE_30_39,
    @SerializedName("40~49")
    AGE_40_49,
    @SerializedName("50~59")
    AGE_50_59,
    @SerializedName("60~69")
    AGE_60_69,
    @SerializedName("70~79")
    AGE_70_79,
    @SerializedName("80~89")
    AGE_80_89,
    @SerializedName("90~")
    AGE_90_ABOVE,
    @UnknownValue
    UNKNOWN;

    private static final AgeRange[] $values() [...] // Inlined contents
}

