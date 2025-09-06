package androidx.activity.result;

import androidx.activity.result.contract.ActivityResultContract;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J<\u0010\u0002\u001A\b\u0012\u0004\u0012\u0002H\u00040\u0003\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u00052\u0012\u0010\u0006\u001A\u000E\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u00072\f\u0010\b\u001A\b\u0012\u0004\u0012\u0002H\u00050\tH&JD\u0010\u0002\u001A\b\u0012\u0004\u0012\u0002H\u00040\u0003\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u00052\u0012\u0010\u0006\u001A\u000E\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u00072\u0006\u0010\n\u001A\u00020\u000B2\f\u0010\b\u001A\b\u0012\u0004\u0012\u0002H\u00050\tH&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\fÀ\u0006\u0001"}, d2 = {"Landroidx/activity/result/ActivityResultCaller;", "", "registerForActivityResult", "Landroidx/activity/result/ActivityResultLauncher;", "I", "O", "contract", "Landroidx/activity/result/contract/ActivityResultContract;", "callback", "Landroidx/activity/result/ActivityResultCallback;", "registry", "Landroidx/activity/result/ActivityResultRegistry;", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public interface ActivityResultCaller {
    ActivityResultLauncher registerForActivityResult(ActivityResultContract arg1, ActivityResultCallback arg2);

    ActivityResultLauncher registerForActivityResult(ActivityResultContract arg1, ActivityResultRegistry arg2, ActivityResultCallback arg3);
}

