package androidx.window.embedding;

import java.util.List;
import java.util.function.Consumer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0001\u0018\u00002\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001B\u0015\u0012\u0006\u0010\u0004\u001A\u00020\u0005\u0012\u0006\u0010\u0006\u001A\u00020\u0007¢\u0006\u0002\u0010\bJ\u0016\u0010\t\u001A\u00020\n2\f\u0010\u000B\u001A\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0016R\u000E\u0010\u0006\u001A\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0004\u001A\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Landroidx/window/embedding/EmbeddingTranslatingCallback;", "Ljava/util/function/Consumer;", "", "Landroidx/window/extensions/embedding/SplitInfo;", "callback", "Landroidx/window/embedding/EmbeddingInterfaceCompat$EmbeddingCallbackInterface;", "adapter", "Landroidx/window/embedding/EmbeddingAdapter;", "(Landroidx/window/embedding/EmbeddingInterfaceCompat$EmbeddingCallbackInterface;Landroidx/window/embedding/EmbeddingAdapter;)V", "accept", "", "splitInfoList", "window_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public final class EmbeddingTranslatingCallback implements Consumer {
    private final EmbeddingAdapter adapter;
    private final EmbeddingCallbackInterface callback;

    public EmbeddingTranslatingCallback(EmbeddingCallbackInterface embeddingInterfaceCompat$EmbeddingCallbackInterface0, EmbeddingAdapter embeddingAdapter0) {
        Intrinsics.checkNotNullParameter(embeddingInterfaceCompat$EmbeddingCallbackInterface0, "callback");
        Intrinsics.checkNotNullParameter(embeddingAdapter0, "adapter");
        super();
        this.callback = embeddingInterfaceCompat$EmbeddingCallbackInterface0;
        this.adapter = embeddingAdapter0;
    }

    @Override
    public void accept(Object object0) {
        this.accept(((List)object0));
    }

    public void accept(List list0) {
        Intrinsics.checkNotNullParameter(list0, "splitInfoList");
        List list1 = this.adapter.translate(list0);
        this.callback.onSplitInfoChanged(list1);
    }
}

