package androidx.window.embedding;

import android.app.Activity;
import android.content.Context;
import androidx.core.util.Consumer;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000B\n\u0002\b\b\b\u0007\u0018\u0000 \u001C2\u00020\u0001:\u0001\u001CB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J*\u0010\b\u001A\u00020\t2\u0006\u0010\n\u001A\u00020\u000B2\u0006\u0010\f\u001A\u00020\r2\u0012\u0010\u000E\u001A\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\u000FJ\u0006\u0010\u0012\u001A\u00020\tJ\f\u0010\u0013\u001A\b\u0012\u0004\u0012\u00020\u00070\u0006J\u0006\u0010\u0014\u001A\u00020\u0015J\u000E\u0010\u0016\u001A\u00020\t2\u0006\u0010\u0017\u001A\u00020\u0007J\u001A\u0010\u0018\u001A\u00020\t2\u0012\u0010\u000E\u001A\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\u000FJ\u0016\u0010\u0019\u001A\u00020\t2\f\u0010\u001A\u001A\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0002J\u000E\u0010\u001B\u001A\u00020\t2\u0006\u0010\u0017\u001A\u00020\u0007R\u000E\u0010\u0003\u001A\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001A\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u000E¢\u0006\u0002\n\u0000¨\u0006\u001D"}, d2 = {"Landroidx/window/embedding/SplitController;", "", "()V", "embeddingBackend", "Landroidx/window/embedding/EmbeddingBackend;", "staticSplitRules", "", "Landroidx/window/embedding/EmbeddingRule;", "addSplitListener", "", "activity", "Landroid/app/Activity;", "executor", "Ljava/util/concurrent/Executor;", "consumer", "Landroidx/core/util/Consumer;", "", "Landroidx/window/embedding/SplitInfo;", "clearRegisteredRules", "getSplitRules", "isSplitSupported", "", "registerRule", "rule", "removeSplitListener", "setStaticSplitRules", "staticRules", "unregisterRule", "Companion", "window_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public final class SplitController {
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\t\u001A\u00020\u0004H\u0007J\u0018\u0010\n\u001A\u00020\u000B2\u0006\u0010\f\u001A\u00020\r2\u0006\u0010\u000E\u001A\u00020\u000FH\u0007R\u0010\u0010\u0003\u001A\u0004\u0018\u00010\u0004X\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\u0005\u001A\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0007\u001A\u00020\bX\u0080T¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Landroidx/window/embedding/SplitController$Companion;", "", "()V", "globalInstance", "Landroidx/window/embedding/SplitController;", "globalLock", "Ljava/util/concurrent/locks/ReentrantLock;", "sDebug", "", "getInstance", "initialize", "", "context", "Landroid/content/Context;", "staticRuleResourceId", "", "window_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        @JvmStatic
        public final SplitController getInstance() {
            if(SplitController.globalInstance == null) {
                Lock lock0 = SplitController.globalLock;
                lock0.lock();
                try {
                    if(SplitController.globalInstance == null) {
                        SplitController.globalInstance = new SplitController(null);
                    }
                }
                finally {
                    lock0.unlock();
                }
            }
            SplitController splitController0 = SplitController.globalInstance;
            Intrinsics.checkNotNull(splitController0);
            return splitController0;
        }

        @JvmStatic
        public final void initialize(Context context0, int v) {
            Intrinsics.checkNotNullParameter(context0, "context");
            Set set0 = new SplitRuleParser().parseSplitRules$window_release(context0, v);
            SplitController splitController0 = this.getInstance();
            if(set0 == null) {
                set0 = SetsKt.emptySet();
            }
            splitController0.setStaticSplitRules(set0);
        }
    }

    public static final Companion Companion = null;
    private final EmbeddingBackend embeddingBackend;
    private static volatile SplitController globalInstance = null;
    private static final ReentrantLock globalLock = null;
    public static final boolean sDebug = false;
    private Set staticSplitRules;

    static {
        SplitController.Companion = new Companion(null);
        SplitController.globalLock = new ReentrantLock();
    }

    private SplitController() {
        this.embeddingBackend = ExtensionEmbeddingBackend.Companion.getInstance();
        this.staticSplitRules = SetsKt.emptySet();
    }

    public SplitController(DefaultConstructorMarker defaultConstructorMarker0) {
    }

    public final void addSplitListener(Activity activity0, Executor executor0, Consumer consumer0) {
        Intrinsics.checkNotNullParameter(activity0, "activity");
        Intrinsics.checkNotNullParameter(executor0, "executor");
        Intrinsics.checkNotNullParameter(consumer0, "consumer");
        this.embeddingBackend.registerSplitListenerForActivity(activity0, executor0, consumer0);
    }

    public final void clearRegisteredRules() {
        this.embeddingBackend.setSplitRules(this.staticSplitRules);
    }

    @JvmStatic
    public static final SplitController getInstance() {
        return SplitController.Companion.getInstance();
    }

    public final Set getSplitRules() {
        return CollectionsKt.toSet(this.embeddingBackend.getSplitRules());
    }

    @JvmStatic
    public static final void initialize(Context context0, int v) {
        SplitController.Companion.initialize(context0, v);
    }

    public final boolean isSplitSupported() {
        return this.embeddingBackend.isSplitSupported();
    }

    public final void registerRule(EmbeddingRule embeddingRule0) {
        Intrinsics.checkNotNullParameter(embeddingRule0, "rule");
        this.embeddingBackend.registerRule(embeddingRule0);
    }

    public final void removeSplitListener(Consumer consumer0) {
        Intrinsics.checkNotNullParameter(consumer0, "consumer");
        this.embeddingBackend.unregisterSplitListenerForActivity(consumer0);
    }

    private final void setStaticSplitRules(Set set0) {
        this.staticSplitRules = set0;
        this.embeddingBackend.setSplitRules(set0);
    }

    public final void unregisterRule(EmbeddingRule embeddingRule0) {
        Intrinsics.checkNotNullParameter(embeddingRule0, "rule");
        this.embeddingBackend.unregisterRule(embeddingRule0);
    }
}

