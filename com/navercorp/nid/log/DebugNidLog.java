package com.navercorp.nid.log;

import android.util.Log;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001A\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\u00042\u0006\u0010\b\u001A\u00020\u0004H\u0016J\u0018\u0010\t\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\u00042\u0006\u0010\b\u001A\u00020\u0004H\u0016J\u0018\u0010\n\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\u00042\u0006\u0010\b\u001A\u00020\u0004H\u0016J\u0010\u0010\u000B\u001A\u00020\u00062\u0006\u0010\u0003\u001A\u00020\u0004H\u0016J\u0018\u0010\f\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\u00042\u0006\u0010\b\u001A\u00020\u0004H\u0016J\u0018\u0010\r\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\u00042\u0006\u0010\b\u001A\u00020\u0004H\u0016J\u0018\u0010\u000E\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\u00042\u0006\u0010\b\u001A\u00020\u0004H\u0016R\u000E\u0010\u0003\u001A\u00020\u0004X\u0082\u000E¢\u0006\u0002\n\u0000¨\u0006\u000F"}, d2 = {"Lcom/navercorp/nid/log/DebugNidLog;", "Lcom/navercorp/nid/log/INidLog;", "()V", "prefix", "", "d", "", "tag", "message", "e", "i", "setPrefix", "v", "w", "wtf", "Nid-OAuth_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public final class DebugNidLog implements INidLog {
    private String prefix;

    public DebugNidLog() {
        this.prefix = "";
    }

    @Override  // com.navercorp.nid.log.INidLog
    public void d(String s, String s1) {
        Intrinsics.checkNotNullParameter(s, "tag");
        Intrinsics.checkNotNullParameter(s1, "message");
        Log.d((this.prefix + s), s1);
    }

    @Override  // com.navercorp.nid.log.INidLog
    public void e(String s, String s1) {
        Intrinsics.checkNotNullParameter(s, "tag");
        Intrinsics.checkNotNullParameter(s1, "message");
        Log.e((this.prefix + s), s1);
    }

    @Override  // com.navercorp.nid.log.INidLog
    public void i(String s, String s1) {
        Intrinsics.checkNotNullParameter(s, "tag");
        Intrinsics.checkNotNullParameter(s1, "message");
        Log.i((this.prefix + s), s1);
    }

    @Override  // com.navercorp.nid.log.INidLog
    public void setPrefix(String s) {
        Intrinsics.checkNotNullParameter(s, "prefix");
        this.prefix = s;
    }

    @Override  // com.navercorp.nid.log.INidLog
    public void v(String s, String s1) {
        Intrinsics.checkNotNullParameter(s, "tag");
        Intrinsics.checkNotNullParameter(s1, "message");
        Log.v((this.prefix + s), s1);
    }

    @Override  // com.navercorp.nid.log.INidLog
    public void w(String s, String s1) {
        Intrinsics.checkNotNullParameter(s, "tag");
        Intrinsics.checkNotNullParameter(s1, "message");
        Log.w((this.prefix + s), s1);
    }

    @Override  // com.navercorp.nid.log.INidLog
    public void wtf(String s, String s1) {
        Intrinsics.checkNotNullParameter(s, "tag");
        Intrinsics.checkNotNullParameter(s1, "message");
        Log.wtf((this.prefix + s), s1);
    }
}

