package androidx.work.impl.utils;

import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Process;
import androidx.work.Configuration;
import androidx.work.Logger;
import androidx.work.WorkManager;
import java.lang.reflect.Method;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001C\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0000\u001A\u0012\u0010\u0002\u001A\u0004\u0018\u00010\u00012\u0006\u0010\u0003\u001A\u00020\u0004H\u0003\u001A\u0016\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0007\u001A\u00020\b\"\u000E\u0010\u0000\u001A\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"TAG", "", "getProcessName", "context", "Landroid/content/Context;", "isDefaultProcess", "", "configuration", "Landroidx/work/Configuration;", "work-runtime_release"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class ProcessUtils {
    private static final String TAG;

    // 去混淆评级： 低(20)
    static {
        Intrinsics.checkNotNullExpressionValue("WM-ProcessUtils", "tagWithPrefix(\"ProcessUtils\")");
        ProcessUtils.TAG = "WM-ProcessUtils";
    }

    private static final String getProcessName(Context context0) {
        if(Build.VERSION.SDK_INT >= 28) {
            return Api28Impl.INSTANCE.getProcessName();
        }
        try {
            Method method0 = Class.forName("android.app.ActivityThread", false, WorkManager.class.getClassLoader()).getDeclaredMethod("currentProcessName", null);
            method0.setAccessible(true);
            Object object0 = method0.invoke(null, null);
            Intrinsics.checkNotNull(object0);
            if(object0 instanceof String) {
                return (String)object0;
            }
        }
        catch(Throwable throwable0) {
            Logger.get().debug("WM-ProcessUtils", "Unable to check ActivityThread for processName", throwable0);
        }
        int v = Process.myPid();
        Object object1 = context0.getSystemService("activity");
        Intrinsics.checkNotNull(object1, "null cannot be cast to non-null type android.app.ActivityManager");
        List list0 = ((ActivityManager)object1).getRunningAppProcesses();
        if(list0 != null) {
            for(Object object2: list0) {
                if(((ActivityManager.RunningAppProcessInfo)object2).pid == v) {
                    return ((ActivityManager.RunningAppProcessInfo)object2) == null ? null : ((ActivityManager.RunningAppProcessInfo)object2).processName;
                }
                if(false) {
                    break;
                }
            }
            return null;
        }
        return null;
    }

    public static final boolean isDefaultProcess(Context context0, Configuration configuration0) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(configuration0, "configuration");
        String s = ProcessUtils.getProcessName(context0);
        CharSequence charSequence0 = configuration0.getDefaultProcessName();
        return charSequence0 == null || charSequence0.length() == 0 ? Intrinsics.areEqual(s, context0.getApplicationInfo().processName) : Intrinsics.areEqual(s, configuration0.getDefaultProcessName());
    }
}

