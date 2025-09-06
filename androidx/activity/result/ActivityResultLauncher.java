package androidx.activity.result;

import androidx.activity.result.contract.ActivityResultContract;
import androidx.core.app.ActivityOptionsCompat;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0015\u0010\b\u001A\u00020\t2\u0006\u0010\n\u001A\u00028\u0000H\u0016¢\u0006\u0002\u0010\u000BJ\u001F\u0010\b\u001A\u00020\t2\u0006\u0010\n\u001A\u00028\u00002\b\u0010\f\u001A\u0004\u0018\u00010\rH&¢\u0006\u0002\u0010\u000EJ\b\u0010\u000F\u001A\u00020\tH\'R\u001C\u0010\u0004\u001A\f\u0012\u0004\u0012\u00028\u0000\u0012\u0002\b\u00030\u0005X¦\u0004¢\u0006\u0006\u001A\u0004\b\u0006\u0010\u0007¨\u0006\u0010"}, d2 = {"Landroidx/activity/result/ActivityResultLauncher;", "I", "", "()V", "contract", "Landroidx/activity/result/contract/ActivityResultContract;", "getContract", "()Landroidx/activity/result/contract/ActivityResultContract;", "launch", "", "input", "(Ljava/lang/Object;)V", "options", "Landroidx/core/app/ActivityOptionsCompat;", "(Ljava/lang/Object;Landroidx/core/app/ActivityOptionsCompat;)V", "unregister", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public abstract class ActivityResultLauncher {
    public abstract ActivityResultContract getContract();

    public void launch(Object object0) {
        this.launch(object0, null);
    }

    public abstract void launch(Object arg1, ActivityOptionsCompat arg2);

    public abstract void unregister();
}

