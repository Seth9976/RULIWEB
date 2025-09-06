package androidx.work;

import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Landroidx/work/ExistingPeriodicWorkPolicy;", "", "(Ljava/lang/String;I)V", "REPLACE", "KEEP", "UPDATE", "CANCEL_AND_REENQUEUE", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public enum ExistingPeriodicWorkPolicy {
    @Deprecated(message = "Deprecated in favor of the UPDATE policy. UPDATE policy has very similar behavior: next run of the worker with the same unique name, going to have new specification. However, UPDATE has better defaults: unlike REPLACE policy UPDATE won\'t cancel the worker if it is currently running and new worker specification will be used only on the next run. Also it preserves original enqueue time, so unlike REPLACE period isn\'t reset. If you want to preserve previous behavior, CANCEL_AND_REENQUEUE should be used.", replaceWith = @ReplaceWith(expression = "UPDATE", imports = {}))
    REPLACE,
    KEEP,
    UPDATE,
    CANCEL_AND_REENQUEUE;

    private static final ExistingPeriodicWorkPolicy[] $values() [...] // Inlined contents
}

