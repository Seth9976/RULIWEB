package kotlin.coroutines.jvm.internal;

import java.lang.reflect.Method;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0002\bÂ\u0002\u0018\u00002\u00020\u0001:\u0001\u000BB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0006\u001A\u00020\u00042\u0006\u0010\u0007\u001A\u00020\bH\u0002J\u0010\u0010\t\u001A\u0004\u0018\u00010\n2\u0006\u0010\u0007\u001A\u00020\bR\u0010\u0010\u0003\u001A\u0004\u0018\u00010\u0004X\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\u0005\u001A\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lkotlin/coroutines/jvm/internal/ModuleNameRetriever;", "", "()V", "cache", "Lkotlin/coroutines/jvm/internal/ModuleNameRetriever$Cache;", "notOnJava9", "buildCache", "continuation", "Lkotlin/coroutines/jvm/internal/BaseContinuationImpl;", "getModuleName", "", "Cache", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
final class ModuleNameRetriever {
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u0001B#\u0012\b\u0010\u0002\u001A\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001A\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001A\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0006R\u0012\u0010\u0004\u001A\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0002\u001A\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0005\u001A\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lkotlin/coroutines/jvm/internal/ModuleNameRetriever$Cache;", "", "getModuleMethod", "Ljava/lang/reflect/Method;", "getDescriptorMethod", "nameMethod", "(Ljava/lang/reflect/Method;Ljava/lang/reflect/Method;Ljava/lang/reflect/Method;)V", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
    static final class Cache {
        public final Method getDescriptorMethod;
        public final Method getModuleMethod;
        public final Method nameMethod;

        public Cache(Method method0, Method method1, Method method2) {
            this.getModuleMethod = method0;
            this.getDescriptorMethod = method1;
            this.nameMethod = method2;
        }
    }

    public static final ModuleNameRetriever INSTANCE;
    private static Cache cache;
    private static final Cache notOnJava9;

    static {
        ModuleNameRetriever.INSTANCE = new ModuleNameRetriever();
        ModuleNameRetriever.notOnJava9 = new Cache(null, null, null);
    }

    private final Cache buildCache(BaseContinuationImpl baseContinuationImpl0) {
        try {
            Cache moduleNameRetriever$Cache0 = new Cache(Class.class.getDeclaredMethod("getModule", null), baseContinuationImpl0.getClass().getClassLoader().loadClass("java.lang.Module").getDeclaredMethod("getDescriptor", null), baseContinuationImpl0.getClass().getClassLoader().loadClass("java.lang.module.ModuleDescriptor").getDeclaredMethod("name", null));
            ModuleNameRetriever.cache = moduleNameRetriever$Cache0;
            return moduleNameRetriever$Cache0;
        }
        catch(Exception unused_ex) {
            ModuleNameRetriever.cache = ModuleNameRetriever.notOnJava9;
            return ModuleNameRetriever.notOnJava9;
        }
    }

    public final String getModuleName(BaseContinuationImpl baseContinuationImpl0) {
        Intrinsics.checkNotNullParameter(baseContinuationImpl0, "continuation");
        Cache moduleNameRetriever$Cache0 = ModuleNameRetriever.cache == null ? this.buildCache(baseContinuationImpl0) : ModuleNameRetriever.cache;
        if(moduleNameRetriever$Cache0 == ModuleNameRetriever.notOnJava9) {
            return null;
        }
        Object object0 = moduleNameRetriever$Cache0.getModuleMethod == null ? null : moduleNameRetriever$Cache0.getModuleMethod.invoke(baseContinuationImpl0.getClass(), null);
        if(object0 == null) {
            return null;
        }
        Object object1 = moduleNameRetriever$Cache0.getDescriptorMethod == null ? null : moduleNameRetriever$Cache0.getDescriptorMethod.invoke(object0, null);
        if(object1 == null) {
            return null;
        }
        Object object2 = moduleNameRetriever$Cache0.nameMethod == null ? null : moduleNameRetriever$Cache0.nameMethod.invoke(object1, null);
        return object2 instanceof String ? ((String)object2) : null;
    }
}

