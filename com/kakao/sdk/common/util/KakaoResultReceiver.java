package com.kakao.sdk.common.util;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ResultReceiver;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b&\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001A\u00020\u0004¢\u0006\u0002\u0010\u0005J\u001A\u0010\f\u001A\u00020\r2\u0006\u0010\u000E\u001A\u00020\u000F2\b\u0010\u0010\u001A\u0004\u0018\u00010\u0011H\u0014J\b\u0010\u0012\u001A\u00020\rH&J\u0012\u0010\u0013\u001A\u00020\r2\b\u0010\u0010\u001A\u0004\u0018\u00010\u0011H&J\u0012\u0010\u0014\u001A\u00020\r2\b\u0010\u0010\u001A\u0004\u0018\u00010\u0011H&R\u001E\u0010\u0006\u001A\u0004\u0018\u00018\u0000X\u0086\u000E¢\u0006\u0010\n\u0002\u0010\u000B\u001A\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u000E\u0010\u0003\u001A\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/kakao/sdk/common/util/KakaoResultReceiver;", "T", "Landroid/os/ResultReceiver;", "message", "", "(Ljava/lang/String;)V", "emitter", "getEmitter", "()Ljava/lang/Object;", "setEmitter", "(Ljava/lang/Object;)V", "Ljava/lang/Object;", "onReceiveResult", "", "resultCode", "", "resultData", "Landroid/os/Bundle;", "processError", "receiveCanceled", "receiveOk", "common_release"}, k = 1, mv = {1, 5, 1}, xi = 0x30)
public abstract class KakaoResultReceiver extends ResultReceiver {
    private Object emitter;
    private final String message;

    public KakaoResultReceiver(String s) {
        Intrinsics.checkNotNullParameter(s, "message");
        super(new Handler(Looper.getMainLooper()));
        this.message = s;
    }

    public final Object getEmitter() {
        return this.emitter;
    }

    @Override  // android.os.ResultReceiver
    protected void onReceiveResult(int v, Bundle bundle0) {
        SdkLog.Companion.d("***** " + this.message + " Status: " + bundle0);
        switch(v) {
            case -1: {
                this.receiveOk(bundle0);
                break;
            }
            case 0: {
                this.receiveCanceled(bundle0);
                break;
            }
            default: {
                this.processError();
            }
        }
        this.emitter = null;
    }

    public abstract void processError();

    public abstract void receiveCanceled(Bundle arg1);

    public abstract void receiveOk(Bundle arg1);

    public final void setEmitter(Object object0) {
        this.emitter = object0;
    }
}

