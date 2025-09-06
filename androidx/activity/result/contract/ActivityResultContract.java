package androidx.activity.result.contract;

import android.content.Context;
import android.content.Intent;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\b&\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u00020\u0003:\u0001\u0013B\u0005¢\u0006\u0002\u0010\u0004J\u001D\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00028\u0000H&¢\u0006\u0002\u0010\nJ%\u0010\u000B\u001A\n\u0012\u0004\u0012\u00028\u0001\u0018\u00010\f2\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00028\u0000H\u0016¢\u0006\u0002\u0010\rJ\u001F\u0010\u000E\u001A\u00028\u00012\u0006\u0010\u000F\u001A\u00020\u00102\b\u0010\u0011\u001A\u0004\u0018\u00010\u0006H&¢\u0006\u0002\u0010\u0012¨\u0006\u0014"}, d2 = {"Landroidx/activity/result/contract/ActivityResultContract;", "I", "O", "", "()V", "createIntent", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "input", "(Landroid/content/Context;Ljava/lang/Object;)Landroid/content/Intent;", "getSynchronousResult", "Landroidx/activity/result/contract/ActivityResultContract$SynchronousResult;", "(Landroid/content/Context;Ljava/lang/Object;)Landroidx/activity/result/contract/ActivityResultContract$SynchronousResult;", "parseResult", "resultCode", "", "intent", "(ILandroid/content/Intent;)Ljava/lang/Object;", "SynchronousResult", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public abstract class ActivityResultContract {
    @Metadata(d1 = {"\u0000\u000E\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0006\u0018\u0000*\u0004\b\u0002\u0010\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001A\u00028\u0002¢\u0006\u0002\u0010\u0004R\u0013\u0010\u0003\u001A\u00028\u0002¢\u0006\n\n\u0002\u0010\u0007\u001A\u0004\b\u0005\u0010\u0006¨\u0006\b"}, d2 = {"Landroidx/activity/result/contract/ActivityResultContract$SynchronousResult;", "T", "", "value", "(Ljava/lang/Object;)V", "getValue", "()Ljava/lang/Object;", "Ljava/lang/Object;", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class SynchronousResult {
        private final Object value;

        public SynchronousResult(Object object0) {
            this.value = object0;
        }

        public final Object getValue() {
            return this.value;
        }
    }

    public abstract Intent createIntent(Context arg1, Object arg2);

    public SynchronousResult getSynchronousResult(Context context0, Object object0) {
        Intrinsics.checkNotNullParameter(context0, "context");
        return null;
    }

    public abstract Object parseResult(int arg1, Intent arg2);
}

