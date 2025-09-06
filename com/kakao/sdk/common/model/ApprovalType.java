package com.kakao.sdk.common.model;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0002\b\u0004\b\u0016\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007B\u0005¢\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001A\u0004\u0018\u00010\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001A\u0004\b\u0005\u0010\u0006¨\u0006\b"}, d2 = {"Lcom/kakao/sdk/common/model/ApprovalType;", "", "()V", "value", "", "getValue", "()Ljava/lang/String;", "Companion", "common_release"}, k = 1, mv = {1, 5, 1}, xi = 0x30)
public class ApprovalType {
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/kakao/sdk/common/model/ApprovalType$Companion;", "", "()V", "common_release"}, k = 1, mv = {1, 5, 1}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }
    }

    public static final Companion Companion;
    private final String value;

    static {
        ApprovalType.Companion = new Companion(null);
    }

    public String getValue() {
        return this.value;
    }
}

