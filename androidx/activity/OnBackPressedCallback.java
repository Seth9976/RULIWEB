package androidx.activity;

import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u000E\n\u0002\u0018\u0002\n\u0002\b\u0004\b&\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0013\u001A\u00020\n2\u0006\u0010\u0014\u001A\u00020\u0007H\u0001J\b\u0010\u0015\u001A\u00020\nH\u0017J\b\u0010\u0016\u001A\u00020\nH\'J\u0010\u0010\u0017\u001A\u00020\n2\u0006\u0010\u0018\u001A\u00020\u0019H\u0017J\u0010\u0010\u001A\u001A\u00020\n2\u0006\u0010\u0018\u001A\u00020\u0019H\u0017J\b\u0010\u001B\u001A\u00020\nH\u0007J\u0010\u0010\u001C\u001A\u00020\n2\u0006\u0010\u0014\u001A\u00020\u0007H\u0001R\u0014\u0010\u0005\u001A\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\b\u001A\n\u0012\u0004\u0012\u00020\n\u0018\u00010\tX\u0080\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b\u000B\u0010\f\"\u0004\b\r\u0010\u000ER&\u0010\u0010\u001A\u00020\u00032\u0006\u0010\u000F\u001A\u00020\u00038G@GX\u0086\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0004¨\u0006\u001D"}, d2 = {"Landroidx/activity/OnBackPressedCallback;", "", "enabled", "", "(Z)V", "cancellables", "Ljava/util/concurrent/CopyOnWriteArrayList;", "Landroidx/activity/Cancellable;", "enabledChangedCallback", "Lkotlin/Function0;", "", "getEnabledChangedCallback$activity_release", "()Lkotlin/jvm/functions/Function0;", "setEnabledChangedCallback$activity_release", "(Lkotlin/jvm/functions/Function0;)V", "value", "isEnabled", "()Z", "setEnabled", "addCancellable", "cancellable", "handleOnBackCancelled", "handleOnBackPressed", "handleOnBackProgressed", "backEvent", "Landroidx/activity/BackEventCompat;", "handleOnBackStarted", "remove", "removeCancellable", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public abstract class OnBackPressedCallback {
    private final CopyOnWriteArrayList cancellables;
    private Function0 enabledChangedCallback;
    private boolean isEnabled;

    public OnBackPressedCallback(boolean z) {
        this.isEnabled = z;
        this.cancellables = new CopyOnWriteArrayList();
    }

    public final void addCancellable(Cancellable cancellable0) {
        Intrinsics.checkNotNullParameter(cancellable0, "cancellable");
        this.cancellables.add(cancellable0);
    }

    public final Function0 getEnabledChangedCallback$activity_release() {
        return this.enabledChangedCallback;
    }

    public void handleOnBackCancelled() {
    }

    public abstract void handleOnBackPressed();

    public void handleOnBackProgressed(BackEventCompat backEventCompat0) {
        Intrinsics.checkNotNullParameter(backEventCompat0, "backEvent");
    }

    public void handleOnBackStarted(BackEventCompat backEventCompat0) {
        Intrinsics.checkNotNullParameter(backEventCompat0, "backEvent");
    }

    public final boolean isEnabled() {
        return this.isEnabled;
    }

    public final void remove() {
        for(Object object0: this.cancellables) {
            ((Cancellable)object0).cancel();
        }
    }

    public final void removeCancellable(Cancellable cancellable0) {
        Intrinsics.checkNotNullParameter(cancellable0, "cancellable");
        this.cancellables.remove(cancellable0);
    }

    public final void setEnabled(boolean z) {
        this.isEnabled = z;
        Function0 function00 = this.enabledChangedCallback;
        if(function00 != null) {
            function00.invoke();
        }
    }

    public final void setEnabledChangedCallback$activity_release(Function0 function00) {
        this.enabledChangedCallback = function00;
    }
}

