package com.kakao.sdk.auth.model;

import com.google.gson.annotations.SerializedName;
import java.lang.annotation.Annotation;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0002\b\b\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001A\u00020\u00048F¢\u0006\u0006\u001A\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000B¨\u0006\f"}, d2 = {"Lcom/kakao/sdk/auth/model/Prompt;", "", "(Ljava/lang/String;I)V", "value", "", "getValue", "()Ljava/lang/String;", "LOGIN", "CREATE", "CERT", "UNIFY_DAUM", "QR_LOGIN", "auth_release"}, k = 1, mv = {1, 5, 1}, xi = 0x30)
public enum Prompt {
    @SerializedName("login")
    LOGIN,
    @SerializedName("create")
    CREATE,
    @SerializedName("cert")
    CERT,
    @SerializedName("unify_daum")
    UNIFY_DAUM,
    @SerializedName("qr_login")
    QR_LOGIN;

    private static final Prompt[] $values() [...] // Inlined contents

    public final String getValue() {
        Annotation annotation0 = this.getClass().getField(this.name()).getAnnotation(SerializedName.class);
        Intrinsics.checkNotNull(annotation0);
        return ((SerializedName)annotation0).value();
    }
}

