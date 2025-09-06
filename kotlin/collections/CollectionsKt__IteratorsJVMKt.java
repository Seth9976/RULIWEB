package kotlin.collections;

import java.util.Enumeration;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u000E\n\u0000\n\u0002\u0010(\n\u0000\n\u0002\u0018\u0002\n\u0000\u001A\u001F\u0010\u0000\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u0086\u0002¨\u0006\u0004"}, d2 = {"iterator", "", "T", "Ljava/util/Enumeration;", "kotlin-stdlib"}, k = 5, mv = {1, 9, 0}, xi = 49, xs = "kotlin/collections/CollectionsKt")
class CollectionsKt__IteratorsJVMKt extends CollectionsKt__IterablesKt {
    public static final Iterator iterator(Enumeration enumeration0) {
        Intrinsics.checkNotNullParameter(enumeration0, "<this>");
        return new Object() {
            @Override
            public boolean hasNext() {
                return enumeration0.hasMoreElements();
            }

            @Override
            public Object next() {
                return enumeration0.nextElement();
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
            }
        };
    }
}

