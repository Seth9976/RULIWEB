package kotlinx.coroutines.internal;

import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;
import kotlin.Metadata;
import kotlin.sequences.SequencesKt;
import kotlinx.coroutines.MainCoroutineDispatcher;

@Metadata(d1 = {"\u0000\u001A\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0007\u001A\u00020\u0006H\u0002R\u000E\u0010\u0003\u001A\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001A\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lkotlinx/coroutines/internal/MainDispatcherLoader;", "", "()V", "FAST_SERVICE_LOADER_ENABLED", "", "dispatcher", "Lkotlinx/coroutines/MainCoroutineDispatcher;", "loadMainDispatcher", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class MainDispatcherLoader {
    private static final boolean FAST_SERVICE_LOADER_ENABLED;
    public static final MainDispatcherLoader INSTANCE;
    public static final MainCoroutineDispatcher dispatcher;

    static {
        MainDispatcherLoader mainDispatcherLoader0 = new MainDispatcherLoader();
        MainDispatcherLoader.INSTANCE = mainDispatcherLoader0;
        MainDispatcherLoader.FAST_SERVICE_LOADER_ENABLED = SystemPropsKt.systemProp("kotlinx.coroutines.fast.service.loader", true);
        MainDispatcherLoader.dispatcher = mainDispatcherLoader0.loadMainDispatcher();
    }

    private final MainCoroutineDispatcher loadMainDispatcher() {
        Object object0;
        try {
            List list0 = MainDispatcherLoader.FAST_SERVICE_LOADER_ENABLED ? FastServiceLoader.INSTANCE.loadMainDispatcherFactory$kotlinx_coroutines_core() : SequencesKt.toList(SequencesKt.asSequence(ServiceLoader.load(MainDispatcherFactory.class, MainDispatcherFactory.class.getClassLoader()).iterator()));
            Iterator iterator0 = list0.iterator();
            if(iterator0.hasNext()) {
                object0 = iterator0.next();
                if(iterator0.hasNext()) {
                    int v = ((MainDispatcherFactory)object0).getLoadPriority();
                    while(true) {
                        Object object1 = iterator0.next();
                        int v1 = ((MainDispatcherFactory)object1).getLoadPriority();
                        if(v < v1) {
                            object0 = object1;
                            v = v1;
                        }
                        if(!iterator0.hasNext()) {
                            break;
                        }
                    }
                }
            }
            else {
                object0 = null;
            }
            if(((MainDispatcherFactory)object0) != null) {
                MainCoroutineDispatcher mainCoroutineDispatcher0 = MainDispatchersKt.tryCreateDispatcher(((MainDispatcherFactory)object0), list0);
                if(mainCoroutineDispatcher0 != null) {
                    return mainCoroutineDispatcher0;
                }
            }
            return MainDispatchersKt.createMissingDispatcher$default(null, null, 3, null);
        }
        catch(Throwable throwable0) {
            return MainDispatchersKt.createMissingDispatcher$default(throwable0, null, 2, null);
        }
    }
}

