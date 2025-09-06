package androidx.window.embedding;

import androidx.window.extensions.embedding.ActivityEmbeddingComponent;
import java.util.Set;
import java.util.function.Consumer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001A\u00020\u00042\f\u0010\u0005\u001A\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0016J\u001C\u0010\b\u001A\u00020\u00042\u0012\u0010\t\u001A\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000B0\nH\u0016¨\u0006\r"}, d2 = {"Landroidx/window/embedding/EmptyEmbeddingComponent;", "Landroidx/window/extensions/embedding/ActivityEmbeddingComponent;", "()V", "setEmbeddingRules", "", "splitRules", "", "Landroidx/window/extensions/embedding/EmbeddingRule;", "setSplitInfoCallback", "consumer", "Ljava/util/function/Consumer;", "", "Landroidx/window/extensions/embedding/SplitInfo;", "window_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
final class EmptyEmbeddingComponent implements ActivityEmbeddingComponent {
    public void setEmbeddingRules(Set set0) {
        Intrinsics.checkNotNullParameter(set0, "splitRules");
    }

    public void setSplitInfoCallback(Consumer consumer0) {
        Intrinsics.checkNotNullParameter(consumer0, "consumer");
    }
}

