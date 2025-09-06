package com.kakao.sdk.common.model;

import com.google.gson.annotations.SerializedName;
import com.kakao.sdk.common.json.UnknownValue;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b#\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000F\b\u0002\u0012\u0006\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001A\u00020\u0003¢\u0006\b\n\u0000\u001A\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000Bj\u0002\b\fj\u0002\b\rj\u0002\b\u000Ej\u0002\b\u000Fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001Aj\u0002\b\u001Bj\u0002\b\u001Cj\u0002\b\u001Dj\u0002\b\u001Ej\u0002\b\u001Fj\u0002\b j\u0002\b!j\u0002\b\"j\u0002\b#j\u0002\b$j\u0002\b%¨\u0006&"}, d2 = {"Lcom/kakao/sdk/common/model/ApiErrorCause;", "", "errorCode", "", "(Ljava/lang/String;II)V", "getErrorCode", "()I", "InternalError", "IllegalParams", "UnsupportedApi", "BlockedAction", "PermissionDenied", "DeprecatedApi", "ApiLimitExceeded", "NotRegisteredUser", "AlreadyRegisteredUser", "AccountDoesNotExist", "PropertyKeyDoesNotExist", "AppDoesNotExist", "InvalidToken", "InsufficientScope", "RequiredAgeVerification", "UnderAgeLimit", "NotTalkUser", "NotFriend", "UserDeviceUnsupported", "TalkMessageDisabled", "TalkSendMessageMonthlyLimitExceed", "TalkSendMessageDailyLimitExceed", "NotStoryUser", "StoryImageUploadSizeExceeded", "TimeOut", "StoryInvalidScrapUrl", "StoryInvalidPostId", "StoryMaxUploadCountExceed", "DeveloperDoesNotExist", "UnderMaintenance", "Unknown", "common_release"}, k = 1, mv = {1, 5, 1}, xi = 0x30)
public enum ApiErrorCause {
    @SerializedName("-1")
    InternalError(-1),
    @SerializedName("-2")
    IllegalParams(-2),
    @SerializedName("-3")
    UnsupportedApi(-3),
    @SerializedName("-4")
    BlockedAction(-4),
    @SerializedName("-5")
    PermissionDenied(-5),
    @SerializedName("-9")
    DeprecatedApi(-9),
    @SerializedName("-10")
    ApiLimitExceeded(-10),
    @SerializedName("-101")
    NotRegisteredUser(-101),
    @SerializedName("-102")
    AlreadyRegisteredUser(-102),
    @SerializedName("-103")
    AccountDoesNotExist(-103),
    @SerializedName("-201")
    PropertyKeyDoesNotExist(-201),
    @SerializedName("-301")
    AppDoesNotExist(-301),
    @SerializedName("-401")
    InvalidToken(-401),
    @SerializedName("-402")
    InsufficientScope(-402),
    @SerializedName("-405")
    RequiredAgeVerification(-405),
    @SerializedName("-406")
    UnderAgeLimit(-406),
    @SerializedName("-501")
    NotTalkUser(-501),
    @SerializedName("-502")
    NotFriend(-502),
    @SerializedName("-504")
    UserDeviceUnsupported(-504),
    @SerializedName("-530")
    TalkMessageDisabled(-530),
    @SerializedName("-531")
    TalkSendMessageMonthlyLimitExceed(-531),
    @SerializedName("-532")
    TalkSendMessageDailyLimitExceed(-532),
    @SerializedName("-601")
    NotStoryUser(-601),
    @SerializedName("-602")
    StoryImageUploadSizeExceeded(-602),
    @SerializedName("-603")
    TimeOut(-603),
    @SerializedName("-604")
    StoryInvalidScrapUrl(-604),
    @SerializedName("-605")
    StoryInvalidPostId(-605),
    @SerializedName("-606")
    StoryMaxUploadCountExceed(-606),
    @SerializedName("-903")
    DeveloperDoesNotExist(-903),
    @SerializedName("-9798")
    UnderMaintenance(-9798),
    @UnknownValue
    Unknown(0x7FFFFFFF);

    private final int errorCode;

    private static final ApiErrorCause[] $values() [...] // Inlined contents

    private ApiErrorCause(int v1) {
        this.errorCode = v1;
    }

    public final int getErrorCode() {
        return this.errorCode;
    }
}

