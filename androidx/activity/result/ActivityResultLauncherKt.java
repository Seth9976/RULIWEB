package androidx.activity.result;

import androidx.core.app.ActivityOptionsCompat;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001A\u001E\u0010\u0000\u001A\u00020\u0001*\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u00022\n\b\u0002\u0010\u0004\u001A\u0004\u0018\u00010\u0005\u001A#\u0010\u0000\u001A\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00010\u00022\n\b\u0002\u0010\u0004\u001A\u0004\u0018\u00010\u0005H\u0007¢\u0006\u0002\b\u0006¨\u0006\u0007"}, d2 = {"launch", "", "Landroidx/activity/result/ActivityResultLauncher;", "Ljava/lang/Void;", "options", "Landroidx/core/app/ActivityOptionsCompat;", "launchUnit", "activity_release"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class ActivityResultLauncherKt {
    public static final void launch(ActivityResultLauncher activityResultLauncher0, ActivityOptionsCompat activityOptionsCompat0) {
        Intrinsics.checkNotNullParameter(activityResultLauncher0, "<this>");
        activityResultLauncher0.launch(null, activityOptionsCompat0);
    }

    public static void launch$default(ActivityResultLauncher activityResultLauncher0, ActivityOptionsCompat activityOptionsCompat0, int v, Object object0) {
        if((v & 1) != 0) {
            activityOptionsCompat0 = null;
        }
        ActivityResultLauncherKt.launch(activityResultLauncher0, activityOptionsCompat0);
    }

    public static final void launchUnit(ActivityResultLauncher activityResultLauncher0, ActivityOptionsCompat activityOptionsCompat0) {
        Intrinsics.checkNotNullParameter(activityResultLauncher0, "<this>");
        activityResultLauncher0.launch(Unit.INSTANCE, activityOptionsCompat0);
    }

    public static void launchUnit$default(ActivityResultLauncher activityResultLauncher0, ActivityOptionsCompat activityOptionsCompat0, int v, Object object0) {
        if((v & 1) != 0) {
            activityOptionsCompat0 = null;
        }
        ActivityResultLauncherKt.launchUnit(activityResultLauncher0, activityOptionsCompat0);
    }
}

