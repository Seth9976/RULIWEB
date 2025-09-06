package kotlinx.coroutines.internal;

import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext.Element;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.ThreadContextElement;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001A\u0004\u0018\u00010\u00012\b\u0010\u0002\u001A\u0004\u0018\u00010\u00012\u0006\u0010\u0003\u001A\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<no name provided>", "", "countOrElement", "element", "Lkotlin/coroutines/CoroutineContext$Element;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 0x30)
final class ThreadContextKt.countAll.1 extends Lambda implements Function2 {
    public static final ThreadContextKt.countAll.1 INSTANCE;

    static {
        ThreadContextKt.countAll.1.INSTANCE = new ThreadContextKt.countAll.1();
    }

    ThreadContextKt.countAll.1() {
        super(2);
    }

    @Override  // kotlin.jvm.functions.Function2
    public Object invoke(Object object0, Object object1) {
        return this.invoke(object0, ((Element)object1));
    }

    public final Object invoke(Object object0, Element coroutineContext$Element0) {
        if(coroutineContext$Element0 instanceof ThreadContextElement) {
            Integer integer0 = object0 instanceof Integer ? ((Integer)object0) : null;
            int v = integer0 == null ? 1 : ((int)integer0);
            return v == 0 ? coroutineContext$Element0 : ((int)(v + 1));
        }
        return object0;
    }
}

