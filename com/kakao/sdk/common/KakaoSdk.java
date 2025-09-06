package com.kakao.sdk.common;

import android.content.Context;
import com.kakao.sdk.common.model.ApplicationContextInfo;
import com.kakao.sdk.common.model.ApprovalType;
import com.kakao.sdk.common.model.SdkIdentifier;
import com.kakao.sdk.common.model.ServerHosts;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000B\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u00C6\u0002\u0018\u00002\u00020\u0001:\u00015B\u0007\b\u0002\u00A2\u0006\u0002\u0010\u0002JN\u0010-\u001A\u00020.2\u0006\u0010/\u001A\u0002002\u0006\u0010\u0003\u001A\u00020\u00042\u0006\u00101\u001A\u00020\u00042\u0006\u0010!\u001A\u00020\u001A2\u0006\u0010\u0013\u001A\u00020\u00142\u0006\u0010\r\u001A\u00020\u000E2\u0006\u0010\'\u001A\u00020(2\u0006\u00102\u001A\u0002032\u0006\u0010\u001B\u001A\u00020\u001AJY\u0010-\u001A\u00020.2\u0006\u0010/\u001A\u0002002\u0006\u0010\u0003\u001A\u00020\u00042\n\b\u0002\u00101\u001A\u0004\u0018\u00010\u00042\n\b\u0002\u0010!\u001A\u0004\u0018\u00010\u001A2\n\b\u0002\u0010\u0013\u001A\u0004\u0018\u00010\u00142\n\b\u0002\u0010\r\u001A\u0004\u0018\u00010\u000E2\n\b\u0002\u00102\u001A\u0004\u0018\u000103H\u0007\u00A2\u0006\u0002\u00104R\u0011\u0010\u0003\u001A\u00020\u00048F\u00A2\u0006\u0006\u001A\u0004\b\u0005\u0010\u0006R\u001A\u0010\u0007\u001A\u00020\bX\u0086.\u00A2\u0006\u000E\n\u0000\u001A\u0004\b\t\u0010\n\"\u0004\b\u000B\u0010\fR\u001A\u0010\r\u001A\u00020\u000EX\u0086.\u00A2\u0006\u000E\n\u0000\u001A\u0004\b\u000F\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001A\u0010\u0013\u001A\u00020\u0014X\u0086.\u00A2\u0006\u000E\n\u0000\u001A\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001E\u0010\u001B\u001A\u00020\u001A2\u0006\u0010\u0019\u001A\u00020\u001A@BX\u0086\u000E\u00A2\u0006\b\n\u0000\u001A\u0004\b\u001B\u0010\u001CR\u0011\u0010\u001D\u001A\u00020\u00048F\u00A2\u0006\u0006\u001A\u0004\b\u001E\u0010\u0006R\u0011\u0010\u001F\u001A\u00020\u00048F\u00A2\u0006\u0006\u001A\u0004\b \u0010\u0006R\u001A\u0010!\u001A\u00020\u001AX\u0086\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b\"\u0010\u001C\"\u0004\b#\u0010$R\u0011\u0010%\u001A\u00020\u00048F\u00A2\u0006\u0006\u001A\u0004\b&\u0010\u0006R\u001A\u0010\'\u001A\u00020(X\u0086.\u00A2\u0006\u000E\n\u0000\u001A\u0004\b)\u0010*\"\u0004\b+\u0010,\u00A8\u00066"}, d2 = {"Lcom/kakao/sdk/common/KakaoSdk;", "", "()V", "appKey", "", "getAppKey", "()Ljava/lang/String;", "applicationContextInfo", "Lcom/kakao/sdk/common/model/ApplicationContextInfo;", "getApplicationContextInfo", "()Lcom/kakao/sdk/common/model/ApplicationContextInfo;", "setApplicationContextInfo", "(Lcom/kakao/sdk/common/model/ApplicationContextInfo;)V", "approvalType", "Lcom/kakao/sdk/common/model/ApprovalType;", "getApprovalType", "()Lcom/kakao/sdk/common/model/ApprovalType;", "setApprovalType", "(Lcom/kakao/sdk/common/model/ApprovalType;)V", "hosts", "Lcom/kakao/sdk/common/model/ServerHosts;", "getHosts", "()Lcom/kakao/sdk/common/model/ServerHosts;", "setHosts", "(Lcom/kakao/sdk/common/model/ServerHosts;)V", "<set-?>", "", "isAutomotive", "()Z", "kaHeader", "getKaHeader", "keyHash", "getKeyHash", "loggingEnabled", "getLoggingEnabled", "setLoggingEnabled", "(Z)V", "redirectUri", "getRedirectUri", "type", "Lcom/kakao/sdk/common/KakaoSdk$Type;", "getType", "()Lcom/kakao/sdk/common/KakaoSdk$Type;", "setType", "(Lcom/kakao/sdk/common/KakaoSdk$Type;)V", "init", "", "context", "Landroid/content/Context;", "customScheme", "sdkIdentifier", "Lcom/kakao/sdk/common/model/SdkIdentifier;", "(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Lcom/kakao/sdk/common/model/ServerHosts;Lcom/kakao/sdk/common/model/ApprovalType;Lcom/kakao/sdk/common/model/SdkIdentifier;)V", "Type", "common_release"}, k = 1, mv = {1, 5, 1}, xi = 0x30)
public final class KakaoSdk {
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, d2 = {"Lcom/kakao/sdk/common/KakaoSdk$Type;", "", "(Ljava/lang/String;I)V", "KOTLIN", "RX_KOTLIN", "common_release"}, k = 1, mv = {1, 5, 1}, xi = 0x30)
    public static enum Type {
        KOTLIN,
        RX_KOTLIN;

        private static final Type[] $values() [...] // Inlined contents
    }

    public static final KakaoSdk INSTANCE;
    public static ApplicationContextInfo applicationContextInfo;
    public static ApprovalType approvalType;
    public static ServerHosts hosts;
    private static boolean isAutomotive;
    private static boolean loggingEnabled;
    public static Type type;

    static {
        KakaoSdk.INSTANCE = new KakaoSdk();
    }

    public final String getAppKey() {
        return this.getApplicationContextInfo().getAppKey();
    }

    public final ApplicationContextInfo getApplicationContextInfo() {
        ApplicationContextInfo applicationContextInfo0 = KakaoSdk.applicationContextInfo;
        if(applicationContextInfo0 != null) {
            return applicationContextInfo0;
        }
        Intrinsics.throwUninitializedPropertyAccessException("applicationContextInfo");
        throw null;
    }

    public final ApprovalType getApprovalType() {
        ApprovalType approvalType0 = KakaoSdk.approvalType;
        if(approvalType0 != null) {
            return approvalType0;
        }
        Intrinsics.throwUninitializedPropertyAccessException("approvalType");
        throw null;
    }

    public final ServerHosts getHosts() {
        ServerHosts serverHosts0 = KakaoSdk.hosts;
        if(serverHosts0 != null) {
            return serverHosts0;
        }
        Intrinsics.throwUninitializedPropertyAccessException("hosts");
        throw null;
    }

    public final String getKaHeader() {
        return this.getApplicationContextInfo().getKaHeader();
    }

    public final String getKeyHash() {
        return this.getApplicationContextInfo().getSigningKeyHash();
    }

    public final boolean getLoggingEnabled() [...] // 潜在的解密器

    public final String getRedirectUri() {
        return this.getApplicationContextInfo().getRedirectUri();
    }

    public final Type getType() {
        Type kakaoSdk$Type0 = KakaoSdk.type;
        if(kakaoSdk$Type0 != null) {
            return kakaoSdk$Type0;
        }
        Intrinsics.throwUninitializedPropertyAccessException("type");
        throw null;
    }

    @JvmStatic
    public static final void init(Context context0, String s) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(s, "appKey");
        KakaoSdk.init$default(context0, s, null, null, null, null, null, 0x7C, null);
    }

    @JvmStatic
    public static final void init(Context context0, String s, String s1) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(s, "appKey");
        KakaoSdk.init$default(context0, s, s1, null, null, null, null, 120, null);
    }

    @JvmStatic
    public static final void init(Context context0, String s, String s1, Boolean boolean0) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(s, "appKey");
        KakaoSdk.init$default(context0, s, s1, boolean0, null, null, null, 0x70, null);
    }

    @JvmStatic
    public static final void init(Context context0, String s, String s1, Boolean boolean0, ServerHosts serverHosts0) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(s, "appKey");
        KakaoSdk.init$default(context0, s, s1, boolean0, serverHosts0, null, null, 0x60, null);
    }

    @JvmStatic
    public static final void init(Context context0, String s, String s1, Boolean boolean0, ServerHosts serverHosts0, ApprovalType approvalType0) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(s, "appKey");
        KakaoSdk.init$default(context0, s, s1, boolean0, serverHosts0, approvalType0, null, 0x40, null);
    }

    @JvmStatic
    public static final void init(Context context0, String s, String s1, Boolean boolean0, ServerHosts serverHosts0, ApprovalType approvalType0, SdkIdentifier sdkIdentifier0) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(s, "appKey");
        KakaoSdk kakaoSdk0 = KakaoSdk.INSTANCE;
        if(s1 == null) {
            s1 = "kakao" + s;
        }
        boolean z = boolean0 == null ? false : boolean0.booleanValue();
        if(serverHosts0 == null) {
            serverHosts0 = new ServerHosts();
        }
        ApprovalType approvalType1 = approvalType0 == null ? new ApprovalType() : approvalType0;
        SdkIdentifier sdkIdentifier1 = sdkIdentifier0 == null ? new SdkIdentifier(null, 1, null) : sdkIdentifier0;
        kakaoSdk0.init(context0, s, s1, z, serverHosts0, approvalType1, Type.KOTLIN, sdkIdentifier1, false);
    }

    public final void init(Context context0, String s, String s1, boolean z, ServerHosts serverHosts0, ApprovalType approvalType0, Type kakaoSdk$Type0, SdkIdentifier sdkIdentifier0, boolean z1) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(s, "appKey");
        Intrinsics.checkNotNullParameter(s1, "customScheme");
        Intrinsics.checkNotNullParameter(serverHosts0, "hosts");
        Intrinsics.checkNotNullParameter(approvalType0, "approvalType");
        Intrinsics.checkNotNullParameter(kakaoSdk$Type0, "type");
        Intrinsics.checkNotNullParameter(sdkIdentifier0, "sdkIdentifier");
        this.setHosts(serverHosts0);
        KakaoSdk.loggingEnabled = z;
        this.setType(kakaoSdk$Type0);
        this.setApprovalType(approvalType0);
        this.setApplicationContextInfo(new ApplicationContextInfo(context0, s, s1, kakaoSdk$Type0, sdkIdentifier0));
        KakaoSdk.isAutomotive = z1;
    }

    public static void init$default(Context context0, String s, String s1, Boolean boolean0, ServerHosts serverHosts0, ApprovalType approvalType0, SdkIdentifier sdkIdentifier0, int v, Object object0) {
        if((v & 4) != 0) {
            s1 = null;
        }
        if((v & 8) != 0) {
            boolean0 = null;
        }
        if((v & 16) != 0) {
            serverHosts0 = null;
        }
        if((v & 0x20) != 0) {
            approvalType0 = null;
        }
        if((v & 0x40) != 0) {
            sdkIdentifier0 = null;
        }
        KakaoSdk.init(context0, s, s1, boolean0, serverHosts0, approvalType0, sdkIdentifier0);
    }

    public final boolean isAutomotive() [...] // 潜在的解密器

    public final void setApplicationContextInfo(ApplicationContextInfo applicationContextInfo0) {
        Intrinsics.checkNotNullParameter(applicationContextInfo0, "<set-?>");
        KakaoSdk.applicationContextInfo = applicationContextInfo0;
    }

    public final void setApprovalType(ApprovalType approvalType0) {
        Intrinsics.checkNotNullParameter(approvalType0, "<set-?>");
        KakaoSdk.approvalType = approvalType0;
    }

    public final void setHosts(ServerHosts serverHosts0) {
        Intrinsics.checkNotNullParameter(serverHosts0, "<set-?>");
        KakaoSdk.hosts = serverHosts0;
    }

    public final void setLoggingEnabled(boolean z) {
        KakaoSdk.loggingEnabled = z;
    }

    public final void setType(Type kakaoSdk$Type0) {
        Intrinsics.checkNotNullParameter(kakaoSdk$Type0, "<set-?>");
        KakaoSdk.type = kakaoSdk$Type0;
    }
}

