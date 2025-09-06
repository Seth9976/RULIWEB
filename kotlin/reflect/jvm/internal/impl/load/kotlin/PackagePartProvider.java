package kotlin.reflect.jvm.internal.impl.load.kotlin;

import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

public interface PackagePartProvider {
    public static final class Empty implements PackagePartProvider {
        public static final Empty INSTANCE;

        static {
            Empty.INSTANCE = new Empty();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.load.kotlin.PackagePartProvider
        public List findPackageParts(String s) {
            Intrinsics.checkNotNullParameter(s, "packageFqName");
            return CollectionsKt.emptyList();
        }
    }

    List findPackageParts(String arg1);
}

