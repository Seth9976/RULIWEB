package com.kakao.sdk.common.model;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0005\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007B\u0011\u0012\n\b\u0002\u0010\u0002\u001A\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004R\u0013\u0010\u0002\u001A\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001A\u0004\b\u0005\u0010\u0006¨\u0006\b"}, d2 = {"Lcom/kakao/sdk/common/model/SdkIdentifier;", "", "identifiers", "", "(Ljava/lang/String;)V", "getIdentifiers", "()Ljava/lang/String;", "Companion", "common_release"}, k = 1, mv = {1, 5, 1}, xi = 0x30)
public final class SdkIdentifier {
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/kakao/sdk/common/model/SdkIdentifier$Companion;", "", "()V", "common_release"}, k = 1, mv = {1, 5, 1}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }
    }

    public static final Companion Companion;
    private final String identifiers;

    static {
        SdkIdentifier.Companion = new Companion(null);
    }

    public SdkIdentifier() {
        this(null, 1, null);
    }

    public SdkIdentifier(String s) {
        this.identifiers = s;
    }

    public SdkIdentifier(String s, int v, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v & 1) != 0) {
            s = null;
        }
        this(s);
    }

    public final String getIdentifiers() {
        return this.identifiers;
    }
}

