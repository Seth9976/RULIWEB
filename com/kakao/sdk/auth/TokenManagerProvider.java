package com.kakao.sdk.auth;

import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \b2\u00020\u0001:\u0001\bB\u000F\u0012\b\b\u0002\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001A\u0010\u0002\u001A\u00020\u0003X\u0086\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004¨\u0006\t"}, d2 = {"Lcom/kakao/sdk/auth/TokenManagerProvider;", "", "manager", "Lcom/kakao/sdk/auth/TokenManageable;", "(Lcom/kakao/sdk/auth/TokenManageable;)V", "getManager", "()Lcom/kakao/sdk/auth/TokenManageable;", "setManager", "Companion", "auth_release"}, k = 1, mv = {1, 5, 1}, xi = 0x30)
public final class TokenManagerProvider {
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R!\u0010\u0003\u001A\u00020\u00048FX\u0087\u0084\u0002¢\u0006\u0012\n\u0004\b\b\u0010\t\u0012\u0004\b\u0005\u0010\u0002\u001A\u0004\b\u0006\u0010\u0007¨\u0006\n"}, d2 = {"Lcom/kakao/sdk/auth/TokenManagerProvider$Companion;", "", "()V", "instance", "Lcom/kakao/sdk/auth/TokenManagerProvider;", "getInstance$annotations", "getInstance", "()Lcom/kakao/sdk/auth/TokenManagerProvider;", "instance$delegate", "Lkotlin/Lazy;", "auth_release"}, k = 1, mv = {1, 5, 1}, xi = 0x30)
    public static final class Companion {
        static final KProperty[] $$delegatedProperties;

        static {
            Companion.$$delegatedProperties = new KProperty[]{Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(Companion.class), "instance", "getInstance()Lcom/kakao/sdk/auth/TokenManagerProvider;"))};
        }

        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final TokenManagerProvider getInstance() {
            return (TokenManagerProvider)TokenManagerProvider.instance$delegate.getValue();
        }

        @JvmStatic
        public static void getInstance$annotations() {
        }
    }

    public static final Companion Companion;
    private static final Lazy instance$delegate;
    private TokenManageable manager;

    static {
        TokenManagerProvider.Companion = new Companion(null);
        TokenManagerProvider.instance$delegate = LazyKt.lazy(TokenManagerProvider.Companion.instance.2.INSTANCE);
    }

    public TokenManagerProvider() {
        this(null, 1, null);
    }

    public TokenManagerProvider(TokenManageable tokenManageable0) {
        Intrinsics.checkNotNullParameter(tokenManageable0, "manager");
        super();
        this.manager = tokenManageable0;
    }

    public TokenManagerProvider(TokenManageable tokenManageable0, int v, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v & 1) != 0) {
            tokenManageable0 = TokenManager.Companion.getInstance();
        }
        this(tokenManageable0);
    }

    public static final TokenManagerProvider getInstance() {
        return TokenManagerProvider.Companion.getInstance();
    }

    public final TokenManageable getManager() {
        return this.manager;
    }

    public final void setManager(TokenManageable tokenManageable0) {
        Intrinsics.checkNotNullParameter(tokenManageable0, "<set-?>");
        this.manager = tokenManageable0;
    }
}

