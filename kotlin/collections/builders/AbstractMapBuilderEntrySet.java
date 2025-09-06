package kotlin.collections.builders;

import java.util.Map.Entry;
import kotlin.Metadata;
import kotlin.collections.AbstractMutableSet;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001E\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010&\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0002\b\u0004\b \u0018\u0000*\u0014\b\u0000\u0010\u0001*\u000E\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u0002H\u00040\u0002*\u0004\b\u0001\u0010\u0003*\u0004\b\u0002\u0010\u00042\b\u0012\u0004\u0012\u0002H\u00010\u0005B\u0005¢\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00028\u0000H\u0086\u0002¢\u0006\u0002\u0010\nJ\u001C\u0010\u000B\u001A\u00020\b2\u0012\u0010\t\u001A\u000E\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u0002H&¨\u0006\f"}, d2 = {"Lkotlin/collections/builders/AbstractMapBuilderEntrySet;", "E", "", "K", "V", "Lkotlin/collections/AbstractMutableSet;", "()V", "contains", "", "element", "(Ljava/util/Map$Entry;)Z", "containsEntry", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public abstract class AbstractMapBuilderEntrySet extends AbstractMutableSet {
    @Override
    public final boolean contains(Object object0) {
        return object0 instanceof Map.Entry ? this.contains(((Map.Entry)object0)) : false;
    }

    public final boolean contains(Map.Entry map$Entry0) {
        Intrinsics.checkNotNullParameter(map$Entry0, "element");
        return this.containsEntry(map$Entry0);
    }

    public abstract boolean containsEntry(Map.Entry arg1);

    @Override
    public final boolean remove(Object object0) {
        return object0 instanceof Map.Entry ? this.remove(((Map.Entry)object0)) : false;
    }

    public boolean remove(Map.Entry map$Entry0) {
        return super.remove(map$Entry0);
    }
}

