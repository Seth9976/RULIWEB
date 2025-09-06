package androidx.window.embedding;

import java.util.List;
import java.util.Set;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0002\ba\u0018\u00002\u00020\u0001:\u0001\nJ\u0010\u0010\u0002\u001A\u00020\u00032\u0006\u0010\u0004\u001A\u00020\u0005H&J\u0016\u0010\u0006\u001A\u00020\u00032\f\u0010\u0007\u001A\b\u0012\u0004\u0012\u00020\t0\bH&¨\u0006\u000B"}, d2 = {"Landroidx/window/embedding/EmbeddingInterfaceCompat;", "", "setEmbeddingCallback", "", "embeddingCallback", "Landroidx/window/embedding/EmbeddingInterfaceCompat$EmbeddingCallbackInterface;", "setSplitRules", "rules", "", "Landroidx/window/embedding/EmbeddingRule;", "EmbeddingCallbackInterface", "window_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public interface EmbeddingInterfaceCompat {
    @Metadata(d1 = {"\u0000\u001A\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001A\u00020\u00032\f\u0010\u0004\u001A\b\u0012\u0004\u0012\u00020\u00060\u0005H&¨\u0006\u0007"}, d2 = {"Landroidx/window/embedding/EmbeddingInterfaceCompat$EmbeddingCallbackInterface;", "", "onSplitInfoChanged", "", "splitInfo", "", "Landroidx/window/embedding/SplitInfo;", "window_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
    public interface EmbeddingCallbackInterface {
        void onSplitInfoChanged(List arg1);
    }

    void setEmbeddingCallback(EmbeddingCallbackInterface arg1);

    void setSplitRules(Set arg1);
}

