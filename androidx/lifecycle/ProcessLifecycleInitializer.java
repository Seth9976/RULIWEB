package androidx.lifecycle;

import android.content.Context;
import androidx.startup.AppInitializer;
import androidx.startup.Initializer;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001A\u00020\u00022\u0006\u0010\u0005\u001A\u00020\u0006H\u0016J\u001A\u0010\u0007\u001A\u0014\u0012\u0010\u0012\u000E\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\u00010\t0\bH\u0016¨\u0006\n"}, d2 = {"Landroidx/lifecycle/ProcessLifecycleInitializer;", "Landroidx/startup/Initializer;", "Landroidx/lifecycle/LifecycleOwner;", "()V", "create", "context", "Landroid/content/Context;", "dependencies", "", "Ljava/lang/Class;", "lifecycle-process_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class ProcessLifecycleInitializer implements Initializer {
    public LifecycleOwner create(Context context0) {
        Intrinsics.checkNotNullParameter(context0, "context");
        AppInitializer appInitializer0 = AppInitializer.getInstance(context0);
        Intrinsics.checkNotNullExpressionValue(appInitializer0, "getInstance(context)");
        if(!appInitializer0.isEagerlyInitialized(this.getClass())) {
            throw new IllegalStateException("ProcessLifecycleInitializer cannot be initialized lazily.\n               Please ensure that you have:\n               <meta-data\n                   android:name=\'androidx.lifecycle.ProcessLifecycleInitializer\'\n                   android:value=\'androidx.startup\' />\n               under InitializationProvider in your AndroidManifest.xml");
        }
        LifecycleDispatcher.init(context0);
        ProcessLifecycleOwner.Companion.init$lifecycle_process_release(context0);
        return ProcessLifecycleOwner.Companion.get();
    }

    @Override  // androidx.startup.Initializer
    public Object create(Context context0) {
        return this.create(context0);
    }

    @Override  // androidx.startup.Initializer
    public List dependencies() {
        return CollectionsKt.emptyList();
    }
}

