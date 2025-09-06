package kotlin.reflect.jvm.internal;

import java.lang.ref.WeakReference;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import jeb.synthetic.FIN;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.components.RuntimeModuleData;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectClassUtilKt;

@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001A\b\u0010\u0005\u001A\u00020\u0006H\u0000\u001A\u0010\u0010\u0007\u001A\u00020\u0004*\u0006\u0012\u0002\b\u00030\bH\u0000\" \u0010\u0000\u001A\u0014\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"moduleByClassLoader", "Ljava/util/concurrent/ConcurrentMap;", "Lkotlin/reflect/jvm/internal/WeakClassLoaderBox;", "Ljava/lang/ref/WeakReference;", "Lkotlin/reflect/jvm/internal/impl/descriptors/runtime/components/RuntimeModuleData;", "clearModuleByClassLoaderCache", "", "getOrCreateModule", "Ljava/lang/Class;", "kotlin-reflection"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class ModuleByClassLoaderKt {
    private static final ConcurrentMap moduleByClassLoader;

    static {
        ModuleByClassLoaderKt.moduleByClassLoader = new ConcurrentHashMap();
    }

    public static final void clearModuleByClassLoaderCache() {
        ModuleByClassLoaderKt.moduleByClassLoader.clear();
    }

    public static final RuntimeModuleData getOrCreateModule(Class class0) {
        Intrinsics.checkNotNullParameter(class0, "<this>");
        ClassLoader classLoader0 = ReflectClassUtilKt.getSafeClassLoader(class0);
        WeakClassLoaderBox weakClassLoaderBox0 = new WeakClassLoaderBox(classLoader0);
        ConcurrentMap concurrentMap0 = ModuleByClassLoaderKt.moduleByClassLoader;
        WeakReference weakReference0 = (WeakReference)concurrentMap0.get(weakClassLoaderBox0);
        if(weakReference0 != null) {
            RuntimeModuleData runtimeModuleData0 = (RuntimeModuleData)weakReference0.get();
            if(runtimeModuleData0 != null) {
                return runtimeModuleData0;
            }
            concurrentMap0.remove(weakClassLoaderBox0, weakReference0);
        }
        RuntimeModuleData runtimeModuleData1 = RuntimeModuleData.Companion.create(classLoader0);
        int v = FIN.finallyOpen$NT();
        while(true) {
            ConcurrentMap concurrentMap1 = ModuleByClassLoaderKt.moduleByClassLoader;
            WeakReference weakReference1 = (WeakReference)concurrentMap1.putIfAbsent(weakClassLoaderBox0, new WeakReference(runtimeModuleData1));
            if(weakReference1 == null) {
                FIN.finallyCodeBegin$NT(v);
                weakClassLoaderBox0.setTemporaryStrongRef(null);
                FIN.finallyCodeEnd$NT(v);
                return runtimeModuleData1;
            }
            RuntimeModuleData runtimeModuleData2 = (RuntimeModuleData)weakReference1.get();
            if(runtimeModuleData2 != null) {
                FIN.finallyExec$NT(v);
                return runtimeModuleData2;
            }
            concurrentMap1.remove(weakClassLoaderBox0, weakReference1);
        }
    }
}

