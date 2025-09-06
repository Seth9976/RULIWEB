package com.navercorp.nid.log;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000B\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001C\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\b2\n\u0010\t\u001A\u00060\nj\u0002`\u000BH\u0007J\u0018\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010\f\u001A\u00020\bH\u0007J\u001C\u0010\r\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\b2\n\u0010\t\u001A\u00060\nj\u0002`\u000BH\u0007J\u0018\u0010\r\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010\f\u001A\u00020\bH\u0007J\u0018\u0010\u000E\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010\f\u001A\u00020\bH\u0007J\u0006\u0010\u000F\u001A\u00020\u0006J\u0010\u0010\u0010\u001A\u00020\u00062\u0006\u0010\u0011\u001A\u00020\bH\u0007J\u0010\u0010\u0012\u001A\u00020\u00062\u0006\u0010\u0013\u001A\u00020\u0014H\u0007J\u0018\u0010\u0015\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010\f\u001A\u00020\bH\u0007J\u0018\u0010\u0016\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010\f\u001A\u00020\bH\u0007J\u0018\u0010\u0017\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010\f\u001A\u00020\bH\u0007R\u000E\u0010\u0003\u001A\u00020\u0004X\u0082\u000E¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/navercorp/nid/log/NidLog;", "", "()V", "instance", "Lcom/navercorp/nid/log/INidLog;", "d", "", "tag", "", "exception", "Ljava/lang/Exception;", "Lkotlin/Exception;", "message", "e", "i", "init", "setPrefix", "prefix", "showLog", "isShow", "", "v", "w", "wtf", "Nid-OAuth_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public final class NidLog {
    public static final NidLog INSTANCE;
    private static INidLog instance;

    static {
        NidLog.INSTANCE = new NidLog();
        NidLog.instance = new ReleaseNidLog();
    }

    @JvmStatic
    public static final void d(String s, Exception exception0) {
        Intrinsics.checkNotNullParameter(s, "tag");
        Intrinsics.checkNotNullParameter(exception0, "exception");
        NidLog.instance.d(s, NidLogKt.toMessage(exception0));
    }

    @JvmStatic
    public static final void d(String s, String s1) {
        Intrinsics.checkNotNullParameter(s, "tag");
        Intrinsics.checkNotNullParameter(s1, "message");
        NidLog.instance.d(s, s1);
    }

    @JvmStatic
    public static final void e(String s, Exception exception0) {
        Intrinsics.checkNotNullParameter(s, "tag");
        Intrinsics.checkNotNullParameter(exception0, "exception");
        NidLog.instance.e(s, NidLogKt.toMessage(exception0));
    }

    @JvmStatic
    public static final void e(String s, String s1) {
        Intrinsics.checkNotNullParameter(s, "tag");
        Intrinsics.checkNotNullParameter(s1, "message");
        NidLog.instance.e(s, s1);
    }

    @JvmStatic
    public static final void i(String s, String s1) {
        Intrinsics.checkNotNullParameter(s, "tag");
        Intrinsics.checkNotNullParameter(s1, "message");
        NidLog.instance.i(s, s1);
    }

    public final void init() {
        INidLog iNidLog0 = StringsKt.equals("release", "debug", true) ? new DebugNidLog() : new ReleaseNidLog();
        NidLog.instance = iNidLog0;
    }

    @JvmStatic
    public static final void setPrefix(String s) {
        Intrinsics.checkNotNullParameter(s, "prefix");
        NidLog.instance.setPrefix(s);
    }

    @JvmStatic
    public static final void showLog(boolean z) {
        if(z) {
            NidLog.instance = new DebugNidLog();
        }
    }

    @JvmStatic
    public static final void v(String s, String s1) {
        Intrinsics.checkNotNullParameter(s, "tag");
        Intrinsics.checkNotNullParameter(s1, "message");
        NidLog.instance.v(s, s1);
    }

    @JvmStatic
    public static final void w(String s, String s1) {
        Intrinsics.checkNotNullParameter(s, "tag");
        Intrinsics.checkNotNullParameter(s1, "message");
        NidLog.instance.w(s, s1);
    }

    @JvmStatic
    public static final void wtf(String s, String s1) {
        Intrinsics.checkNotNullParameter(s, "tag");
        Intrinsics.checkNotNullParameter(s1, "message");
        NidLog.instance.wtf(s, s1);
    }
}

