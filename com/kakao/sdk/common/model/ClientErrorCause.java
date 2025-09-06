package com.kakao.sdk.common.model;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\b\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, d2 = {"Lcom/kakao/sdk/common/model/ClientErrorCause;", "", "(Ljava/lang/String;I)V", "Unknown", "Cancelled", "TokenNotFound", "NotSupported", "BadParameter", "IllegalState", "common_release"}, k = 1, mv = {1, 5, 1}, xi = 0x30)
public enum ClientErrorCause {
    @Description("unknown error.")
    Unknown,
    @Description("user cancelled.")
    Cancelled,
    @Description("authentication tokens don\'t exist.")
    TokenNotFound,
    @Description("user is unauthenticated.")
    NotSupported,
    @Description("bad parameters.")
    BadParameter,
    @Description("illegal state.")
    IllegalState;

    private static final ClientErrorCause[] $values() [...] // Inlined contents
}

