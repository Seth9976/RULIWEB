package androidx.window.embedding;

import android.util.Log;
import androidx.window.extensions.WindowExtensionsProvider;
import androidx.window.extensions.embedding.ActivityEmbeddingComponent;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0001\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B\u0015\u0012\u0006\u0010\u0003\u001A\u00020\u0004\u0012\u0006\u0010\u0005\u001A\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0010\u0010\b\u001A\u00020\t2\u0006\u0010\n\u001A\u00020\u000BH\u0016J\u0016\u0010\f\u001A\u00020\t2\f\u0010\r\u001A\b\u0012\u0004\u0012\u00020\u000F0\u000EH\u0016R\u000E\u0010\u0005\u001A\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0003\u001A\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Landroidx/window/embedding/EmbeddingCompat;", "Landroidx/window/embedding/EmbeddingInterfaceCompat;", "()V", "embeddingExtension", "Landroidx/window/extensions/embedding/ActivityEmbeddingComponent;", "adapter", "Landroidx/window/embedding/EmbeddingAdapter;", "(Landroidx/window/extensions/embedding/ActivityEmbeddingComponent;Landroidx/window/embedding/EmbeddingAdapter;)V", "setEmbeddingCallback", "", "embeddingCallback", "Landroidx/window/embedding/EmbeddingInterfaceCompat$EmbeddingCallbackInterface;", "setSplitRules", "rules", "", "Landroidx/window/embedding/EmbeddingRule;", "Companion", "window_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public final class EmbeddingCompat implements EmbeddingInterfaceCompat {
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0007\u001A\u00020\bJ\r\u0010\t\u001A\u0004\u0018\u00010\n¢\u0006\u0002\u0010\u000BJ\u0006\u0010\f\u001A\u00020\u0004R\u000E\u0010\u0003\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000E\u0010\u0005\u001A\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Landroidx/window/embedding/EmbeddingCompat$Companion;", "", "()V", "DEBUG", "", "TAG", "", "embeddingComponent", "Landroidx/window/extensions/embedding/ActivityEmbeddingComponent;", "getExtensionApiLevel", "", "()Ljava/lang/Integer;", "isEmbeddingAvailable", "window_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final ActivityEmbeddingComponent embeddingComponent() {
            if(this.isEmbeddingAvailable()) {
                ActivityEmbeddingComponent activityEmbeddingComponent0 = WindowExtensionsProvider.getWindowExtensions().getActivityEmbeddingComponent();
                return activityEmbeddingComponent0 == null ? new EmptyEmbeddingComponent() : activityEmbeddingComponent0;
            }
            return new EmptyEmbeddingComponent();
        }

        public final Integer getExtensionApiLevel() {
            try {
                return WindowExtensionsProvider.getWindowExtensions().getVendorApiLevel();
            }
            catch(NoClassDefFoundError unused_ex) {
                Log.d("EmbeddingCompat", "Embedding extension version not found");
                return null;
            }
            catch(UnsupportedOperationException unused_ex) {
                Log.d("EmbeddingCompat", "Stub Extension");
                return null;
            }
        }

        public final boolean isEmbeddingAvailable() {
            try {
                if(WindowExtensionsProvider.getWindowExtensions().getActivityEmbeddingComponent() != null) {
                    return true;
                }
            }
            catch(NoClassDefFoundError unused_ex) {
                Log.d("EmbeddingCompat", "Embedding extension version not found");
            }
            catch(UnsupportedOperationException unused_ex) {
                Log.d("EmbeddingCompat", "Stub Extension");
            }
            return false;
        }
    }

    public static final Companion Companion = null;
    public static final boolean DEBUG = true;
    private static final String TAG = "EmbeddingCompat";
    private final EmbeddingAdapter adapter;
    private final ActivityEmbeddingComponent embeddingExtension;

    static {
        EmbeddingCompat.Companion = new Companion(null);
    }

    public EmbeddingCompat() {
        this(EmbeddingCompat.Companion.embeddingComponent(), new EmbeddingAdapter());
    }

    public EmbeddingCompat(ActivityEmbeddingComponent activityEmbeddingComponent0, EmbeddingAdapter embeddingAdapter0) {
        Intrinsics.checkNotNullParameter(activityEmbeddingComponent0, "embeddingExtension");
        Intrinsics.checkNotNullParameter(embeddingAdapter0, "adapter");
        super();
        this.embeddingExtension = activityEmbeddingComponent0;
        this.adapter = embeddingAdapter0;
    }

    @Override  // androidx.window.embedding.EmbeddingInterfaceCompat
    public void setEmbeddingCallback(EmbeddingCallbackInterface embeddingInterfaceCompat$EmbeddingCallbackInterface0) {
        Intrinsics.checkNotNullParameter(embeddingInterfaceCompat$EmbeddingCallbackInterface0, "embeddingCallback");
        EmbeddingTranslatingCallback embeddingTranslatingCallback0 = new EmbeddingTranslatingCallback(embeddingInterfaceCompat$EmbeddingCallbackInterface0, this.adapter);
        this.embeddingExtension.setSplitInfoCallback(embeddingTranslatingCallback0);
    }

    @Override  // androidx.window.embedding.EmbeddingInterfaceCompat
    public void setSplitRules(Set set0) {
        Intrinsics.checkNotNullParameter(set0, "rules");
        Set set1 = this.adapter.translate(set0);
        this.embeddingExtension.setEmbeddingRules(set1);
    }
}

