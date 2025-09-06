package androidx.window.embedding;

import android.app.Activity;
import android.util.Log;
import androidx.core.util.Consumer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.Executor;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\b\u0001\u0018\u0000 )2\u00020\u0001:\u0003)*+B\u0011\b\u0007\u0012\b\u0010\u0002\u001A\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u000E\u0010\u0014\u001A\b\u0012\u0004\u0012\u00020\u00130\u0015H\u0016J\b\u0010\u0016\u001A\u00020\u0017H\u0016J\u0010\u0010\u0018\u001A\u00020\u00192\u0006\u0010\u001A\u001A\u00020\u0013H\u0016J,\u0010\u001B\u001A\u00020\u00192\u0006\u0010\u001C\u001A\u00020\u001D2\u0006\u0010\u001E\u001A\u00020\u001F2\u0012\u0010 \u001A\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u00020#0\"0!H\u0016J\u0016\u0010$\u001A\u00020\u00192\f\u0010%\u001A\b\u0012\u0004\u0012\u00020\u00130\u0015H\u0016J\u0010\u0010&\u001A\u00020\u00192\u0006\u0010\u001A\u001A\u00020\u0013H\u0016J\u001C\u0010\'\u001A\u00020\u00192\u0012\u0010(\u001A\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u00020#0\"0!H\u0016R \u0010\u0002\u001A\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004R\"\u0010\b\u001A\b\u0012\u0004\u0012\u00020\n0\t8\u0006X\u0087\u0004¢\u0006\u000E\n\u0000\u0012\u0004\b\u000B\u0010\f\u001A\u0004\b\r\u0010\u000ER\u0012\u0010\u000F\u001A\u00060\u0010R\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001A\b\u0012\u0004\u0012\u00020\u00130\u0012X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006,"}, d2 = {"Landroidx/window/embedding/ExtensionEmbeddingBackend;", "Landroidx/window/embedding/EmbeddingBackend;", "embeddingExtension", "Landroidx/window/embedding/EmbeddingInterfaceCompat;", "(Landroidx/window/embedding/EmbeddingInterfaceCompat;)V", "getEmbeddingExtension", "()Landroidx/window/embedding/EmbeddingInterfaceCompat;", "setEmbeddingExtension", "splitChangeCallbacks", "Ljava/util/concurrent/CopyOnWriteArrayList;", "Landroidx/window/embedding/ExtensionEmbeddingBackend$SplitListenerWrapper;", "getSplitChangeCallbacks$annotations", "()V", "getSplitChangeCallbacks", "()Ljava/util/concurrent/CopyOnWriteArrayList;", "splitInfoEmbeddingCallback", "Landroidx/window/embedding/ExtensionEmbeddingBackend$EmbeddingCallbackImpl;", "splitRules", "Ljava/util/concurrent/CopyOnWriteArraySet;", "Landroidx/window/embedding/EmbeddingRule;", "getSplitRules", "", "isSplitSupported", "", "registerRule", "", "rule", "registerSplitListenerForActivity", "activity", "Landroid/app/Activity;", "executor", "Ljava/util/concurrent/Executor;", "callback", "Landroidx/core/util/Consumer;", "", "Landroidx/window/embedding/SplitInfo;", "setSplitRules", "rules", "unregisterRule", "unregisterSplitListenerForActivity", "consumer", "Companion", "EmbeddingCallbackImpl", "SplitListenerWrapper", "window_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public final class ExtensionEmbeddingBackend implements EmbeddingBackend {
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\t\u001A\u00020\u0006J\n\u0010\n\u001A\u0004\u0018\u00010\u000BH\u0002J\u0017\u0010\f\u001A\u00020\r2\b\u0010\u000E\u001A\u0004\u0018\u00010\u000FH\u0007¢\u0006\u0002\u0010\u0010R\u000E\u0010\u0003\u001A\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001A\u0004\u0018\u00010\u0006X\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\u0007\u001A\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Landroidx/window/embedding/ExtensionEmbeddingBackend$Companion;", "", "()V", "TAG", "", "globalInstance", "Landroidx/window/embedding/ExtensionEmbeddingBackend;", "globalLock", "Ljava/util/concurrent/locks/ReentrantLock;", "getInstance", "initAndVerifyEmbeddingExtension", "Landroidx/window/embedding/EmbeddingInterfaceCompat;", "isExtensionVersionSupported", "", "extensionVersion", "", "(Ljava/lang/Integer;)Z", "window_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final ExtensionEmbeddingBackend getInstance() {
            if(ExtensionEmbeddingBackend.globalInstance == null) {
                Lock lock0 = ExtensionEmbeddingBackend.globalLock;
                lock0.lock();
                try {
                    if(ExtensionEmbeddingBackend.globalInstance == null) {
                        ExtensionEmbeddingBackend.globalInstance = new ExtensionEmbeddingBackend(ExtensionEmbeddingBackend.Companion.initAndVerifyEmbeddingExtension());
                    }
                }
                finally {
                    lock0.unlock();
                }
            }
            ExtensionEmbeddingBackend extensionEmbeddingBackend0 = ExtensionEmbeddingBackend.globalInstance;
            Intrinsics.checkNotNull(extensionEmbeddingBackend0);
            return extensionEmbeddingBackend0;
        }

        private final EmbeddingInterfaceCompat initAndVerifyEmbeddingExtension() {
            EmbeddingInterfaceCompat embeddingInterfaceCompat0 = null;
            try {
                if(this.isExtensionVersionSupported(EmbeddingCompat.Companion.getExtensionApiLevel()) && EmbeddingCompat.Companion.isEmbeddingAvailable()) {
                    embeddingInterfaceCompat0 = new EmbeddingCompat();
                }
            }
            catch(Throwable throwable0) {
                Log.d("EmbeddingBackend", "Failed to load embedding extension: " + throwable0);
            }
            if(embeddingInterfaceCompat0 == null) {
                Log.d("EmbeddingBackend", "No supported embedding extension found");
            }
            return embeddingInterfaceCompat0;
        }

        public final boolean isExtensionVersionSupported(Integer integer0) {
            return integer0 == null ? false : ((int)integer0) >= 1;
        }
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0080\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0016\u0010\n\u001A\u00020\u000B2\f\u0010\f\u001A\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0016R\"\u0010\u0003\u001A\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004X\u0086\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\r"}, d2 = {"Landroidx/window/embedding/ExtensionEmbeddingBackend$EmbeddingCallbackImpl;", "Landroidx/window/embedding/EmbeddingInterfaceCompat$EmbeddingCallbackInterface;", "(Landroidx/window/embedding/ExtensionEmbeddingBackend;)V", "lastInfo", "", "Landroidx/window/embedding/SplitInfo;", "getLastInfo", "()Ljava/util/List;", "setLastInfo", "(Ljava/util/List;)V", "onSplitInfoChanged", "", "splitInfo", "window_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
    public final class EmbeddingCallbackImpl implements EmbeddingCallbackInterface {
        private List lastInfo;

        public EmbeddingCallbackImpl() {
            Intrinsics.checkNotNullParameter(extensionEmbeddingBackend0, "this$0");
            ExtensionEmbeddingBackend.this = extensionEmbeddingBackend0;
            super();
        }

        public final List getLastInfo() {
            return this.lastInfo;
        }

        @Override  // androidx.window.embedding.EmbeddingInterfaceCompat$EmbeddingCallbackInterface
        public void onSplitInfoChanged(List list0) {
            Intrinsics.checkNotNullParameter(list0, "splitInfo");
            this.lastInfo = list0;
            for(Object object0: ExtensionEmbeddingBackend.this.getSplitChangeCallbacks()) {
                ((SplitListenerWrapper)object0).accept(list0);
            }
        }

        public final void setLastInfo(List list0) {
            this.lastInfo = list0;
        }
    }

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0005\u0012\u0012\u0010\u0006\u001A\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u0007¢\u0006\u0002\u0010\nJ\u0014\u0010\u000E\u001A\u00020\u000F2\f\u0010\u0010\u001A\b\u0012\u0004\u0012\u00020\t0\bR\u000E\u0010\u0002\u001A\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001D\u0010\u0006\u001A\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u0007¢\u0006\b\n\u0000\u001A\u0004\b\u000B\u0010\fR\u000E\u0010\u0004\u001A\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\r\u001A\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bX\u0082\u000E¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Landroidx/window/embedding/ExtensionEmbeddingBackend$SplitListenerWrapper;", "", "activity", "Landroid/app/Activity;", "executor", "Ljava/util/concurrent/Executor;", "callback", "Landroidx/core/util/Consumer;", "", "Landroidx/window/embedding/SplitInfo;", "(Landroid/app/Activity;Ljava/util/concurrent/Executor;Landroidx/core/util/Consumer;)V", "getCallback", "()Landroidx/core/util/Consumer;", "lastValue", "accept", "", "splitInfoList", "window_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
    public static final class SplitListenerWrapper {
        private final Activity activity;
        private final Consumer callback;
        private final Executor executor;
        private List lastValue;

        // 检测为 Lambda 实现
        public static void $r8$lambda$GPkRTYmr_tXrdbWXg4atNFRvxrk(SplitListenerWrapper extensionEmbeddingBackend$SplitListenerWrapper0, List list0) [...]

        public SplitListenerWrapper(Activity activity0, Executor executor0, Consumer consumer0) {
            Intrinsics.checkNotNullParameter(activity0, "activity");
            Intrinsics.checkNotNullParameter(executor0, "executor");
            Intrinsics.checkNotNullParameter(consumer0, "callback");
            super();
            this.activity = activity0;
            this.executor = executor0;
            this.callback = consumer0;
        }

        public final void accept(List list0) {
            Intrinsics.checkNotNullParameter(list0, "splitInfoList");
            Collection collection0 = new ArrayList();
            for(Object object0: list0) {
                if(((SplitInfo)object0).contains(this.activity)) {
                    collection0.add(object0);
                }
            }
            if(Intrinsics.areEqual(((List)collection0), this.lastValue)) {
                return;
            }
            this.lastValue = (List)collection0;
            ExtensionEmbeddingBackend.SplitListenerWrapper..ExternalSyntheticLambda0 extensionEmbeddingBackend$SplitListenerWrapper$$ExternalSyntheticLambda00 = () -> SplitListenerWrapper.accept$lambda-1(this, ((List)collection0));
            this.executor.execute(extensionEmbeddingBackend$SplitListenerWrapper$$ExternalSyntheticLambda00);
        }

        private static final void accept$lambda-1(SplitListenerWrapper extensionEmbeddingBackend$SplitListenerWrapper0, List list0) {
            Intrinsics.checkNotNullParameter(extensionEmbeddingBackend$SplitListenerWrapper0, "this$0");
            Intrinsics.checkNotNullParameter(list0, "$splitsWithActivity");
            extensionEmbeddingBackend$SplitListenerWrapper0.callback.accept(list0);
        }

        public final Consumer getCallback() {
            return this.callback;
        }
    }

    public static final Companion Companion = null;
    private static final String TAG = "EmbeddingBackend";
    private EmbeddingInterfaceCompat embeddingExtension;
    private static volatile ExtensionEmbeddingBackend globalInstance;
    private static final ReentrantLock globalLock;
    private final CopyOnWriteArrayList splitChangeCallbacks;
    private final EmbeddingCallbackImpl splitInfoEmbeddingCallback;
    private final CopyOnWriteArraySet splitRules;

    static {
        ExtensionEmbeddingBackend.Companion = new Companion(null);
        ExtensionEmbeddingBackend.globalLock = new ReentrantLock();
    }

    public ExtensionEmbeddingBackend(EmbeddingInterfaceCompat embeddingInterfaceCompat0) {
        this.embeddingExtension = embeddingInterfaceCompat0;
        EmbeddingCallbackImpl extensionEmbeddingBackend$EmbeddingCallbackImpl0 = new EmbeddingCallbackImpl(this);
        this.splitInfoEmbeddingCallback = extensionEmbeddingBackend$EmbeddingCallbackImpl0;
        this.splitChangeCallbacks = new CopyOnWriteArrayList();
        EmbeddingInterfaceCompat embeddingInterfaceCompat1 = this.embeddingExtension;
        if(embeddingInterfaceCompat1 != null) {
            embeddingInterfaceCompat1.setEmbeddingCallback(extensionEmbeddingBackend$EmbeddingCallbackImpl0);
        }
        this.splitRules = new CopyOnWriteArraySet();
    }

    public final EmbeddingInterfaceCompat getEmbeddingExtension() {
        return this.embeddingExtension;
    }

    public final CopyOnWriteArrayList getSplitChangeCallbacks() {
        return this.splitChangeCallbacks;
    }

    public static void getSplitChangeCallbacks$annotations() {
    }

    @Override  // androidx.window.embedding.EmbeddingBackend
    public Set getSplitRules() {
        return this.splitRules;
    }

    @Override  // androidx.window.embedding.EmbeddingBackend
    public boolean isSplitSupported() {
        return this.embeddingExtension != null;
    }

    @Override  // androidx.window.embedding.EmbeddingBackend
    public void registerRule(EmbeddingRule embeddingRule0) {
        Intrinsics.checkNotNullParameter(embeddingRule0, "rule");
        if(!this.splitRules.contains(embeddingRule0)) {
            this.splitRules.add(embeddingRule0);
            EmbeddingInterfaceCompat embeddingInterfaceCompat0 = this.embeddingExtension;
            if(embeddingInterfaceCompat0 != null) {
                embeddingInterfaceCompat0.setSplitRules(this.splitRules);
            }
        }
    }

    @Override  // androidx.window.embedding.EmbeddingBackend
    public void registerSplitListenerForActivity(Activity activity0, Executor executor0, Consumer consumer0) {
        Intrinsics.checkNotNullParameter(activity0, "activity");
        Intrinsics.checkNotNullParameter(executor0, "executor");
        Intrinsics.checkNotNullParameter(consumer0, "callback");
        Lock lock0 = ExtensionEmbeddingBackend.globalLock;
        lock0.lock();
        try {
            if(this.getEmbeddingExtension() == null) {
                Log.v("EmbeddingBackend", "Extension not loaded, skipping callback registration.");
                consumer0.accept(CollectionsKt.emptyList());
                return;
            }
            SplitListenerWrapper extensionEmbeddingBackend$SplitListenerWrapper0 = new SplitListenerWrapper(activity0, executor0, consumer0);
            this.getSplitChangeCallbacks().add(extensionEmbeddingBackend$SplitListenerWrapper0);
            if(this.splitInfoEmbeddingCallback.getLastInfo() == null) {
                extensionEmbeddingBackend$SplitListenerWrapper0.accept(CollectionsKt.emptyList());
            }
            else {
                List list0 = this.splitInfoEmbeddingCallback.getLastInfo();
                Intrinsics.checkNotNull(list0);
                extensionEmbeddingBackend$SplitListenerWrapper0.accept(list0);
            }
        }
        finally {
            lock0.unlock();
        }
    }

    public final void setEmbeddingExtension(EmbeddingInterfaceCompat embeddingInterfaceCompat0) {
        this.embeddingExtension = embeddingInterfaceCompat0;
    }

    @Override  // androidx.window.embedding.EmbeddingBackend
    public void setSplitRules(Set set0) {
        Intrinsics.checkNotNullParameter(set0, "rules");
        this.splitRules.clear();
        this.splitRules.addAll(set0);
        EmbeddingInterfaceCompat embeddingInterfaceCompat0 = this.embeddingExtension;
        if(embeddingInterfaceCompat0 == null) {
            return;
        }
        embeddingInterfaceCompat0.setSplitRules(this.splitRules);
    }

    @Override  // androidx.window.embedding.EmbeddingBackend
    public void unregisterRule(EmbeddingRule embeddingRule0) {
        Intrinsics.checkNotNullParameter(embeddingRule0, "rule");
        if(this.splitRules.contains(embeddingRule0)) {
            this.splitRules.remove(embeddingRule0);
            EmbeddingInterfaceCompat embeddingInterfaceCompat0 = this.embeddingExtension;
            if(embeddingInterfaceCompat0 != null) {
                embeddingInterfaceCompat0.setSplitRules(this.splitRules);
            }
        }
    }

    @Override  // androidx.window.embedding.EmbeddingBackend
    public void unregisterSplitListenerForActivity(Consumer consumer0) {
        Intrinsics.checkNotNullParameter(consumer0, "consumer");
        Lock lock0 = ExtensionEmbeddingBackend.globalLock;
        lock0.lock();
        try {
            for(Object object0: this.getSplitChangeCallbacks()) {
                SplitListenerWrapper extensionEmbeddingBackend$SplitListenerWrapper0 = (SplitListenerWrapper)object0;
                if(Intrinsics.areEqual(extensionEmbeddingBackend$SplitListenerWrapper0.getCallback(), consumer0)) {
                    this.getSplitChangeCallbacks().remove(extensionEmbeddingBackend$SplitListenerWrapper0);
                    break;
                }
                if(false) {
                    break;
                }
            }
        }
        finally {
            lock0.unlock();
        }
    }
}

