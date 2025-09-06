package kotlin.reflect.jvm.internal.impl.resolve.constants;

import java.util.Collection;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;

public final class PrimitiveTypeUtilKt {
    public static final Collection getAllSignedLiteralTypes(ModuleDescriptor moduleDescriptor0) {
        Intrinsics.checkNotNullParameter(moduleDescriptor0, "<this>");
        return CollectionsKt.listOf(new SimpleType[]{moduleDescriptor0.getBuiltIns().getIntType(), moduleDescriptor0.getBuiltIns().getLongType(), moduleDescriptor0.getBuiltIns().getByteType(), moduleDescriptor0.getBuiltIns().getShortType()});
    }
}

