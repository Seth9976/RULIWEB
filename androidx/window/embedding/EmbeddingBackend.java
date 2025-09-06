package androidx.window.embedding;

import android.app.Activity;
import androidx.core.util.Consumer;
import java.util.Set;
import java.util.concurrent.Executor;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\ba\u0018\u00002\u00020\u0001J\u000E\u0010\u0002\u001A\b\u0012\u0004\u0012\u00020\u00040\u0003H&J\b\u0010\u0005\u001A\u00020\u0006H&J\u0010\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\u0004H&J,\u0010\n\u001A\u00020\b2\u0006\u0010\u000B\u001A\u00020\f2\u0006\u0010\r\u001A\u00020\u000E2\u0012\u0010\u000F\u001A\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00110\u0010H&J\u0016\u0010\u0013\u001A\u00020\b2\f\u0010\u0014\u001A\b\u0012\u0004\u0012\u00020\u00040\u0003H&J\u0010\u0010\u0015\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\u0004H&J\u001C\u0010\u0016\u001A\u00020\b2\u0012\u0010\u0017\u001A\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00110\u0010H&¨\u0006\u0018"}, d2 = {"Landroidx/window/embedding/EmbeddingBackend;", "", "getSplitRules", "", "Landroidx/window/embedding/EmbeddingRule;", "isSplitSupported", "", "registerRule", "", "rule", "registerSplitListenerForActivity", "activity", "Landroid/app/Activity;", "executor", "Ljava/util/concurrent/Executor;", "callback", "Landroidx/core/util/Consumer;", "", "Landroidx/window/embedding/SplitInfo;", "setSplitRules", "rules", "unregisterRule", "unregisterSplitListenerForActivity", "consumer", "window_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public interface EmbeddingBackend {
    Set getSplitRules();

    boolean isSplitSupported();

    void registerRule(EmbeddingRule arg1);

    void registerSplitListenerForActivity(Activity arg1, Executor arg2, Consumer arg3);

    void setSplitRules(Set arg1);

    void unregisterRule(EmbeddingRule arg1);

    void unregisterSplitListenerForActivity(Consumer arg1);
}

