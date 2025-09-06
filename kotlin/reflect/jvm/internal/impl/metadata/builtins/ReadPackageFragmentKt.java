package kotlin.reflect.jvm.internal.impl.metadata.builtins;

import java.io.InputStream;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.PackageFragment;
import kotlin.reflect.jvm.internal.impl.protobuf.ExtensionRegistryLite;

public final class ReadPackageFragmentKt {
    public static final Pair readBuiltinsPackageFragment(InputStream inputStream0) {
        Pair pair0;
        PackageFragment protoBuf$PackageFragment0;
        Intrinsics.checkNotNullParameter(inputStream0, "<this>");
        try {
            BuiltInsBinaryVersion builtInsBinaryVersion0 = BuiltInsBinaryVersion.Companion.readFrom(inputStream0);
            if(builtInsBinaryVersion0.isCompatibleWithCurrentCompilerVersion()) {
                ExtensionRegistryLite extensionRegistryLite0 = ExtensionRegistryLite.newInstance();
                BuiltInsProtoBuf.registerAllExtensions(extensionRegistryLite0);
                protoBuf$PackageFragment0 = PackageFragment.parseFrom(inputStream0, extensionRegistryLite0);
            }
            else {
                protoBuf$PackageFragment0 = null;
            }
            pair0 = TuplesKt.to(protoBuf$PackageFragment0, builtInsBinaryVersion0);
        }
        catch(Throwable throwable0) {
            CloseableKt.closeFinally(inputStream0, throwable0);
            throw throwable0;
        }
        CloseableKt.closeFinally(inputStream0, null);
        return pair0;
    }
}

