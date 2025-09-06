package kotlin.reflect.jvm.internal.impl.util;

import java.util.Iterator;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.reflect.KClass;

public abstract class AbstractArrayMapOwner implements Iterable, KMappedMarker {
    public static abstract class AbstractArrayMapAccessor {
        private final int id;
        private final KClass key;

        public AbstractArrayMapAccessor(KClass kClass0, int v) {
            Intrinsics.checkNotNullParameter(kClass0, "key");
            super();
            this.key = kClass0;
            this.id = v;
        }

        protected final Object extractValue(AbstractArrayMapOwner abstractArrayMapOwner0) {
            Intrinsics.checkNotNullParameter(abstractArrayMapOwner0, "thisRef");
            return abstractArrayMapOwner0.getArrayMap().get(this.id);
        }
    }

    protected abstract ArrayMap getArrayMap();

    protected abstract TypeRegistry getTypeRegistry();

    public final boolean isEmpty() {
        return this.getArrayMap().getSize() == 0;
    }

    @Override
    public final Iterator iterator() {
        return this.getArrayMap().iterator();
    }

    protected abstract void registerComponent(KClass arg1, Object arg2);
}

