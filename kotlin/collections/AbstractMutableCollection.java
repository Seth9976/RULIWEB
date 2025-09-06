package kotlin.collections;

import java.util.AbstractCollection;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.jvm.internal.markers.KMutableCollection;

@Metadata(d1 = {"\u0000\u001A\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001F\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0002\b\u0003\b\'\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u0007\b\u0004¢\u0006\u0002\u0010\u0004J\u0015\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00028\u0000H&¢\u0006\u0002\u0010\b¨\u0006\t"}, d2 = {"Lkotlin/collections/AbstractMutableCollection;", "E", "", "Ljava/util/AbstractCollection;", "()V", "add", "", "element", "(Ljava/lang/Object;)Z", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public abstract class AbstractMutableCollection extends AbstractCollection implements Collection, KMutableCollection {
    @Override
    public abstract boolean add(Object arg1);

    public abstract int getSize();

    @Override
    public final int size() {
        return this.getSize();
    }
}

