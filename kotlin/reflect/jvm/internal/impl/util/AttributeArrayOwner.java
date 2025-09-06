package kotlin.reflect.jvm.internal.impl.util;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;

public abstract class AttributeArrayOwner extends AbstractArrayMapOwner {
    private ArrayMap arrayMap;

    public AttributeArrayOwner() {
        Intrinsics.checkNotNull(EmptyArrayMap.INSTANCE, "null cannot be cast to non-null type org.jetbrains.kotlin.util.ArrayMap<T of org.jetbrains.kotlin.util.AttributeArrayOwner>");
        this(EmptyArrayMap.INSTANCE);
    }

    protected AttributeArrayOwner(ArrayMap arrayMap0) {
        Intrinsics.checkNotNullParameter(arrayMap0, "arrayMap");
        super();
        this.arrayMap = arrayMap0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.util.AbstractArrayMapOwner
    protected final ArrayMap getArrayMap() {
        return this.arrayMap;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.util.AbstractArrayMapOwner
    protected final void registerComponent(KClass kClass0, Object object0) {
        Intrinsics.checkNotNullParameter(kClass0, "tClass");
        Intrinsics.checkNotNullParameter(object0, "value");
        int v = this.getTypeRegistry().getId(kClass0);
        switch(this.arrayMap.getSize()) {
            case 0: {
                this.arrayMap = new OneElementArrayMap(object0, v);
                return;
            }
            case 1: {
                ArrayMap arrayMap0 = this.arrayMap;
                Intrinsics.checkNotNull(arrayMap0, "null cannot be cast to non-null type org.jetbrains.kotlin.util.OneElementArrayMap<T of org.jetbrains.kotlin.util.AttributeArrayOwner>");
                if(((OneElementArrayMap)arrayMap0).getIndex() == v) {
                    this.arrayMap = new OneElementArrayMap(object0, v);
                    return;
                }
                ArrayMap arrayMap1 = new ArrayMapImpl();
                this.arrayMap = arrayMap1;
                arrayMap1.set(((OneElementArrayMap)arrayMap0).getIndex(), ((OneElementArrayMap)arrayMap0).getValue());
            }
        }
        this.arrayMap.set(v, object0);
    }
}

