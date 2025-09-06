package kotlin.reflect.jvm.internal.impl.metadata.deserialization;

import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type;

public final class TypeTable {
    private final List types;

    public TypeTable(kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeTable protoBuf$TypeTable0) {
        Intrinsics.checkNotNullParameter(protoBuf$TypeTable0, "typeTable");
        super();
        List list0 = protoBuf$TypeTable0.getTypeList();
        if(protoBuf$TypeTable0.hasFirstNullable()) {
            int v = protoBuf$TypeTable0.getFirstNullable();
            List list1 = protoBuf$TypeTable0.getTypeList();
            Intrinsics.checkNotNullExpressionValue(list1, "typeTable.typeList");
            ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list1, 10));
            int v1 = 0;
            for(Object object0: list1) {
                if(v1 < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                Type protoBuf$Type0 = (Type)object0;
                if(v1 >= v) {
                    protoBuf$Type0 = protoBuf$Type0.toBuilder().setNullable(true).build();
                }
                arrayList0.add(protoBuf$Type0);
                ++v1;
            }
            list0 = arrayList0;
        }
        Intrinsics.checkNotNullExpressionValue(list0, "run {\n        val origin… else originalTypes\n    }");
        this.types = list0;
    }

    public final Type get(int v) {
        return (Type)this.types.get(v);
    }
}

