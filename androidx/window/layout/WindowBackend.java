package androidx.window.layout;

import android.app.Activity;
import androidx.core.util.Consumer;
import java.util.concurrent.Executor;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b`\u0018\u00002\u00020\u0001J&\u0010\u0002\u001A\u00020\u00032\u0006\u0010\u0004\u001A\u00020\u00052\u0006\u0010\u0006\u001A\u00020\u00072\f\u0010\b\u001A\b\u0012\u0004\u0012\u00020\n0\tH&J\u0016\u0010\u000B\u001A\u00020\u00032\f\u0010\b\u001A\b\u0012\u0004\u0012\u00020\n0\tH&¨\u0006\f"}, d2 = {"Landroidx/window/layout/WindowBackend;", "", "registerLayoutChangeCallback", "", "activity", "Landroid/app/Activity;", "executor", "Ljava/util/concurrent/Executor;", "callback", "Landroidx/core/util/Consumer;", "Landroidx/window/layout/WindowLayoutInfo;", "unregisterLayoutChangeCallback", "window_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public interface WindowBackend {
    void registerLayoutChangeCallback(Activity arg1, Executor arg2, Consumer arg3);

    void unregisterLayoutChangeCallback(Consumer arg1);
}

