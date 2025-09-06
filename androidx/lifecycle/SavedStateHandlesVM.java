package androidx.lifecycle;

import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u001C\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000E\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001D\u0010\u0003\u001A\u000E\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\b\n\u0000\u001A\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Landroidx/lifecycle/SavedStateHandlesVM;", "Landroidx/lifecycle/ViewModel;", "()V", "handles", "", "", "Landroidx/lifecycle/SavedStateHandle;", "getHandles", "()Ljava/util/Map;", "lifecycle-viewmodel-savedstate_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class SavedStateHandlesVM extends ViewModel {
    private final Map handles;

    public SavedStateHandlesVM() {
        this.handles = new LinkedHashMap();
    }

    public final Map getHandles() {
        return this.handles;
    }
}

