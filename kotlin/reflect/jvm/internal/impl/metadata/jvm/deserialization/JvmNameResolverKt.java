package kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization;

import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.StringTableTypes.Record;

public final class JvmNameResolverKt {
    public static final List toExpandedRecordsList(List list0) {
        Intrinsics.checkNotNullParameter(list0, "<this>");
        ArrayList arrayList0 = new ArrayList();
        arrayList0.ensureCapacity(list0.size());
        for(Object object0: list0) {
            Record jvmProtoBuf$StringTableTypes$Record0 = (Record)object0;
            int v = jvmProtoBuf$StringTableTypes$Record0.getRange();
            for(int v1 = 0; v1 < v; ++v1) {
                arrayList0.add(jvmProtoBuf$StringTableTypes$Record0);
            }
        }
        arrayList0.trimToSize();
        return arrayList0;
    }
}

