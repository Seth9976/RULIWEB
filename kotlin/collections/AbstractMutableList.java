package kotlin.collections;

import java.util.AbstractList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.markers.KMutableList;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\b\'\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u0007\b\u0004¢\u0006\u0002\u0010\u0004J\u001D\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00028\u0000H&¢\u0006\u0002\u0010\nJ\u0015\u0010\u000B\u001A\u00028\u00002\u0006\u0010\u0007\u001A\u00020\bH&¢\u0006\u0002\u0010\fJ\u001E\u0010\r\u001A\u00028\u00002\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00028\u0000H¦\u0002¢\u0006\u0002\u0010\u000E¨\u0006\u000F"}, d2 = {"Lkotlin/collections/AbstractMutableList;", "E", "", "Ljava/util/AbstractList;", "()V", "add", "", "index", "", "element", "(ILjava/lang/Object;)V", "removeAt", "(I)Ljava/lang/Object;", "set", "(ILjava/lang/Object;)Ljava/lang/Object;", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public abstract class AbstractMutableList extends AbstractList implements List, KMutableList {
    @Override
    public abstract void add(int arg1, Object arg2);

    public abstract int getSize();

    @Override
    public final Object remove(int v) {
        return this.removeAt(v);
    }

    public abstract Object removeAt(int arg1);

    @Override
    public abstract Object set(int arg1, Object arg2);

    @Override
    public final int size() {
        return this.getSize();
    }
}

