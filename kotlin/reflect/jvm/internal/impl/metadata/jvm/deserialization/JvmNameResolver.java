package kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization;

import java.util.List;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.StringTableTypes;

public final class JvmNameResolver extends JvmNameResolverBase {
    private final StringTableTypes types;

    public JvmNameResolver(StringTableTypes jvmProtoBuf$StringTableTypes0, String[] arr_s) {
        Intrinsics.checkNotNullParameter(jvmProtoBuf$StringTableTypes0, "types");
        Set set0;
        Intrinsics.checkNotNullParameter(arr_s, "strings");
        List list0 = jvmProtoBuf$StringTableTypes0.getLocalNameList();
        if(list0.isEmpty()) {
            set0 = SetsKt.emptySet();
        }
        else {
            Intrinsics.checkNotNullExpressionValue(list0, "_init_$lambda$0");
            set0 = CollectionsKt.toSet(list0);
        }
        List list1 = jvmProtoBuf$StringTableTypes0.getRecordList();
        Intrinsics.checkNotNullExpressionValue(list1, "types.recordList");
        super(arr_s, set0, JvmNameResolverKt.toExpandedRecordsList(list1));
        this.types = jvmProtoBuf$StringTableTypes0;
    }
}

