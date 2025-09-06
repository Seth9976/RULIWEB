package com.navercorp.nid.oauth;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u001C\b\u0086\u0001\u0018\u0000 \u001E2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u001EB\u0017\b\u0002\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0003¢\u0006\u0002\u0010\u0005R\u0011\u0010\u0002\u001A\u00020\u0003¢\u0006\b\n\u0000\u001A\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001A\u00020\u0003¢\u0006\b\n\u0000\u001A\u0004\b\b\u0010\u0007j\u0002\b\tj\u0002\b\nj\u0002\b\u000Bj\u0002\b\fj\u0002\b\rj\u0002\b\u000Ej\u0002\b\u000Fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001Aj\u0002\b\u001Bj\u0002\b\u001Cj\u0002\b\u001D¨\u0006\u001F"}, d2 = {"Lcom/navercorp/nid/oauth/NidOAuthErrorCode;", "", "code", "", "description", "(Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;)V", "getCode", "()Ljava/lang/String;", "getDescription", "NONE", "SERVER_ERROR_INVALID_REQUEST", "SERVER_ERROR_UNAUTHORIZED_CLIENT", "SERVER_ERROR_ACCESS_DENIED", "SERVER_ERROR_UNSUPPORTED_RESPONSE_TYPE", "SERVER_ERROR_INVALID_SCOPE", "SERVER_ERROR_SERVER_ERROR", "SERVER_ERROR_TEMPORARILY_UNAVAILABLE", "ERROR_NO_CATAGORIZED", "CLIENT_ERROR_PARSING_FAIL", "CLIENT_ERROR_NO_CLIENTID", "CLIENT_ERROR_NO_CLIENTSECRET", "CLIENT_ERROR_NO_CLIENTNAME", "CLIENT_ERROR_NO_CALLBACKURL", "CLIENT_ERROR_CONNECTION_ERROR", "CLIENT_ERROR_CERTIFICATION_ERROR", "CLIENT_USER_CANCEL", "ACTIVITY_IS_SINGLE_TASK", "WEB_VIEW_IS_DEPRECATED", "NO_APP_FOR_AUTHENTICATION", "SDK_IS_NOT_INITIALIZED", "INSTANCE", "Nid-OAuth_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public enum NidOAuthErrorCode {
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001A\u00020\u00042\b\u0010\u0005\u001A\u0004\u0018\u00010\u0006¨\u0006\u0007"}, d2 = {"Lcom/navercorp/nid/oauth/NidOAuthErrorCode$INSTANCE;", "", "()V", "fromString", "Lcom/navercorp/nid/oauth/NidOAuthErrorCode;", "code", "", "Nid-OAuth_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
    public static final class INSTANCE {
        private INSTANCE() {
        }

        public INSTANCE(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final NidOAuthErrorCode fromString(String s) {
            if(s != null && s.length() != 0) {
                NidOAuthErrorCode[] arr_nidOAuthErrorCode = NidOAuthErrorCode.values();
                for(int v = 0; v < arr_nidOAuthErrorCode.length; ++v) {
                    NidOAuthErrorCode nidOAuthErrorCode0 = arr_nidOAuthErrorCode[v];
                    if(Intrinsics.areEqual(s, nidOAuthErrorCode0.getCode()) || Intrinsics.areEqual(s, nidOAuthErrorCode0.name())) {
                        return nidOAuthErrorCode0;
                    }
                }
                return NidOAuthErrorCode.ERROR_NO_CATAGORIZED;
            }
            return NidOAuthErrorCode.NONE;
        }
    }

    NONE("", ""),
    SERVER_ERROR_INVALID_REQUEST("invalid_request", "invalid_request"),
    SERVER_ERROR_UNAUTHORIZED_CLIENT("unauthorized_client", "unauthorized_client"),
    SERVER_ERROR_ACCESS_DENIED("access_denied", "access_denied"),
    SERVER_ERROR_UNSUPPORTED_RESPONSE_TYPE("unsupported_response_type", "unsupported_response_type"),
    SERVER_ERROR_INVALID_SCOPE("invalid_scope", "invalid_scope"),
    SERVER_ERROR_SERVER_ERROR("server_error", "server_error"),
    SERVER_ERROR_TEMPORARILY_UNAVAILABLE("temporarily_unavailable", "temporarily_unavailable"),
    ERROR_NO_CATAGORIZED("no_catagorized_error", "no_catagorized_error"),
    CLIENT_ERROR_PARSING_FAIL("parsing_fail", "parsing_fail"),
    CLIENT_ERROR_NO_CLIENTID("invalid_request", "no_clientid"),
    CLIENT_ERROR_NO_CLIENTSECRET("invalid_request", "no_clientsecret"),
    CLIENT_ERROR_NO_CLIENTNAME("invalid_request", "no_clientname"),
    CLIENT_ERROR_NO_CALLBACKURL("invalid_request", "no_callbackurl"),
    CLIENT_ERROR_CONNECTION_ERROR("server_error", "connection_error"),
    CLIENT_ERROR_CERTIFICATION_ERROR("server_error", "certification_error"),
    CLIENT_USER_CANCEL("user_cancel", "user_cancel"),
    ACTIVITY_IS_SINGLE_TASK("activity_is_single_task", "activity_is_single_task"),
    WEB_VIEW_IS_DEPRECATED("web_view_is_deprecated", "web_view_is_deprecated"),
    NO_APP_FOR_AUTHENTICATION("no_app_for_authentication", "no_app_for_authentication"),
    SDK_IS_NOT_INITIALIZED("sdk_is_not_initialized", "sdk_is_not_initialized");

    public static final INSTANCE INSTANCE;
    private final String code;
    private final String description;

    private static final NidOAuthErrorCode[] $values() [...] // Inlined contents

    static {
        NidOAuthErrorCode.INSTANCE = new INSTANCE(null);
    }

    private NidOAuthErrorCode(String s1, String s2) {
        this.code = s1;
        this.description = s2;
    }

    public final String getCode() {
        return this.code;
    }

    public final String getDescription() {
        return this.description;
    }
}

