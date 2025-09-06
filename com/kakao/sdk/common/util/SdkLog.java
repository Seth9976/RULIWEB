package com.kakao.sdk.common.util;

import com.kakao.sdk.common.KakaoSdk;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000E\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u000F\u0012\b\b\u0002\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001A\u0010\u0011\u001A\u00020\u00122\b\u0010\u0013\u001A\u0004\u0018\u00010\u00012\u0006\u0010\u0014\u001A\u00020\u0015H\u0002R\u001B\u0010\u0005\u001A\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001A\u0004\b\u0007\u0010\bR\u000E\u0010\u0002\u001A\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R!\u0010\u000B\u001A\b\u0012\u0004\u0012\u00020\r0\f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0010\u0010\n\u001A\u0004\b\u000E\u0010\u000F¨\u0006\u0017"}, d2 = {"Lcom/kakao/sdk/common/util/SdkLog;", "", "enabled", "", "(Z)V", "dateFormat", "Ljava/text/SimpleDateFormat;", "getDateFormat", "()Ljava/text/SimpleDateFormat;", "dateFormat$delegate", "Lkotlin/Lazy;", "logs", "Ljava/util/LinkedList;", "", "getLogs", "()Ljava/util/LinkedList;", "logs$delegate", "log", "", "logged", "logLevel", "Lcom/kakao/sdk/common/util/SdkLogLevel;", "Companion", "common_release"}, k = 1, mv = {1, 5, 1}, xi = 0x30)
public final class SdkLog {
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000E\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\f\u001A\u00020\r2\b\u0010\u000E\u001A\u0004\u0018\u00010\u0001J\u0010\u0010\u000F\u001A\u00020\r2\b\u0010\u000E\u001A\u0004\u0018\u00010\u0001J\u0010\u0010\u0010\u001A\u00020\r2\b\u0010\u000E\u001A\u0004\u0018\u00010\u0001J\b\u0010\u0011\u001A\u00020\u0012H\u0007J\u0010\u0010\u0013\u001A\u00020\r2\b\u0010\u000E\u001A\u0004\u0018\u00010\u0001J\u0010\u0010\u0014\u001A\u00020\r2\b\u0010\u000E\u001A\u0004\u0018\u00010\u0001R\u000E\u0010\u0003\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R!\u0010\u0005\u001A\u00020\u00068FX\u0087\u0084\u0002¢\u0006\u0012\n\u0004\b\n\u0010\u000B\u0012\u0004\b\u0007\u0010\u0002\u001A\u0004\b\b\u0010\t¨\u0006\u0015"}, d2 = {"Lcom/kakao/sdk/common/util/SdkLog$Companion;", "", "()V", "MAX_SIZE", "", "instance", "Lcom/kakao/sdk/common/util/SdkLog;", "getInstance$annotations", "getInstance", "()Lcom/kakao/sdk/common/util/SdkLog;", "instance$delegate", "Lkotlin/Lazy;", "d", "", "logged", "e", "i", "log", "", "v", "w", "common_release"}, k = 1, mv = {1, 5, 1}, xi = 0x30)
    public static final class Companion {
        static final KProperty[] $$delegatedProperties;

        static {
            Companion.$$delegatedProperties = new KProperty[]{Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(Companion.class), "instance", "getInstance()Lcom/kakao/sdk/common/util/SdkLog;"))};
        }

        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final void d(Object object0) {
            this.getInstance().log(object0, SdkLogLevel.D);
        }

        public final void e(Object object0) {
            this.getInstance().log(object0, SdkLogLevel.E);
        }

        public final SdkLog getInstance() {
            return (SdkLog)SdkLog.instance$delegate.getValue();
        }

        @JvmStatic
        public static void getInstance$annotations() {
        }

        public final void i(Object object0) {
            this.getInstance().log(object0, SdkLogLevel.I);
        }

        @JvmStatic
        public final String log() {
            return Intrinsics.stringPlus(StringsKt.trimIndent(("\n                ==== sdk version: 2.13.0\n                ==== app version: " + KakaoSdk.INSTANCE.getApplicationContextInfo().getAppVer() + "\n            ")), CollectionsKt.joinToString$default(this.getInstance().getLogs(), "\n", "\n", null, 0, null, null, 60, null));
        }

        public final void v(Object object0) {
            this.getInstance().log(object0, SdkLogLevel.V);
        }

        public final void w(Object object0) {
            this.getInstance().log(object0, SdkLogLevel.W);
        }
    }

    public static final Companion Companion = null;
    public static final int MAX_SIZE = 100;
    private final Lazy dateFormat$delegate;
    private final boolean enabled;
    private static final Lazy instance$delegate;
    private final Lazy logs$delegate;

    static {
        SdkLog.Companion = new Companion(null);
        SdkLog.instance$delegate = LazyKt.lazy(SdkLog.Companion.instance.2.INSTANCE);
    }

    public SdkLog() {
        this(false, 1, null);
    }

    public SdkLog(boolean z) {
        this.enabled = z;
        this.logs$delegate = LazyKt.lazy(com.kakao.sdk.common.util.SdkLog.logs.2.INSTANCE);
        this.dateFormat$delegate = LazyKt.lazy(com.kakao.sdk.common.util.SdkLog.dateFormat.2.INSTANCE);

        @Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001A\u00020\u0001H\n"}, d2 = {"<anonymous>", "Ljava/text/SimpleDateFormat;"}, k = 3, mv = {1, 5, 1}, xi = 0x30)
        final class com.kakao.sdk.common.util.SdkLog.dateFormat.2 extends Lambda implements Function0 {
            public static final com.kakao.sdk.common.util.SdkLog.dateFormat.2 INSTANCE;

            static {
                com.kakao.sdk.common.util.SdkLog.dateFormat.2.INSTANCE = new com.kakao.sdk.common.util.SdkLog.dateFormat.2();
            }

            com.kakao.sdk.common.util.SdkLog.dateFormat.2() {
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final SimpleDateFormat invoke() {
                return new SimpleDateFormat("MM-dd HH:mm:ss.SSS");
            }
        }


        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000E\u0010\u0000\u001A\b\u0012\u0004\u0012\u00020\u00020\u0001H\n"}, d2 = {"<anonymous>", "Ljava/util/LinkedList;", ""}, k = 3, mv = {1, 5, 1}, xi = 0x30)
        final class com.kakao.sdk.common.util.SdkLog.logs.2 extends Lambda implements Function0 {
            public static final com.kakao.sdk.common.util.SdkLog.logs.2 INSTANCE;

            static {
                com.kakao.sdk.common.util.SdkLog.logs.2.INSTANCE = new com.kakao.sdk.common.util.SdkLog.logs.2();
            }

            com.kakao.sdk.common.util.SdkLog.logs.2() {
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final LinkedList invoke() {
                return new LinkedList();
            }
        }

    }

    public SdkLog(boolean z, int v, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v & 1) != 0) {
            z = false;
        }
        this(z);
    }

    private final SimpleDateFormat getDateFormat() {
        return (SimpleDateFormat)this.dateFormat$delegate.getValue();
    }

    public static final SdkLog getInstance() {
        return SdkLog.Companion.getInstance();
    }

    private final LinkedList getLogs() {
        return (LinkedList)this.logs$delegate.getValue();
    }

    @JvmStatic
    public static final String log() {
        return SdkLog.Companion.log();
    }

    private final void log(Object object0, SdkLogLevel sdkLogLevel0) {
        String s = sdkLogLevel0.getSymbol() + ' ' + object0;
        if(this.enabled && sdkLogLevel0.compareTo(SdkLogLevel.I) >= 0) {
            this.getLogs().add(this.getDateFormat().format(new Date()) + ' ' + s);
            if(this.getLogs().size() > 100) {
                this.getLogs().poll();
            }
        }
    }
}

