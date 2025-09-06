package kotlin.reflect.jvm.internal.impl.metadata.deserialization;

import java.util.LinkedList;
import java.util.List;
import kotlin.Triple;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.QualifiedNameTable.QualifiedName.Kind;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.QualifiedNameTable.QualifiedName;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.QualifiedNameTable;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.StringTable;

public final class NameResolverImpl implements NameResolver {
    public final class WhenMappings {
        public static final int[] $EnumSwitchMapping$0;

        static {
            int[] arr_v = new int[Kind.values().length];
            try {
                arr_v[Kind.CLASS.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[Kind.PACKAGE.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[Kind.LOCAL.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            WhenMappings.$EnumSwitchMapping$0 = arr_v;
        }
    }

    private final QualifiedNameTable qualifiedNames;
    private final StringTable strings;

    public NameResolverImpl(StringTable protoBuf$StringTable0, QualifiedNameTable protoBuf$QualifiedNameTable0) {
        Intrinsics.checkNotNullParameter(protoBuf$StringTable0, "strings");
        Intrinsics.checkNotNullParameter(protoBuf$QualifiedNameTable0, "qualifiedNames");
        super();
        this.strings = protoBuf$StringTable0;
        this.qualifiedNames = protoBuf$QualifiedNameTable0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver
    public String getQualifiedClassName(int v) {
        Triple triple0 = this.traverseIds(v);
        List list0 = (List)triple0.component1();
        String s = CollectionsKt.joinToString$default(((List)triple0.component2()), ".", null, null, 0, null, null, 62, null);
        return list0.isEmpty() ? s : CollectionsKt.joinToString$default(list0, "/", null, null, 0, null, null, 62, null) + '/' + s;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver
    public String getString(int v) {
        String s = this.strings.getString(v);
        Intrinsics.checkNotNullExpressionValue(s, "strings.getString(index)");
        return s;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver
    public boolean isLocalClassName(int v) {
        return ((Boolean)this.traverseIds(v).getThird()).booleanValue();
    }

    private final Triple traverseIds(int v) {
        LinkedList linkedList0 = new LinkedList();
        LinkedList linkedList1 = new LinkedList();
        boolean z = false;
        while(v != -1) {
            QualifiedName protoBuf$QualifiedNameTable$QualifiedName0 = this.qualifiedNames.getQualifiedName(v);
            String s = this.strings.getString(protoBuf$QualifiedNameTable$QualifiedName0.getShortName());
            Kind protoBuf$QualifiedNameTable$QualifiedName$Kind0 = protoBuf$QualifiedNameTable$QualifiedName0.getKind();
            Intrinsics.checkNotNull(protoBuf$QualifiedNameTable$QualifiedName$Kind0);
            switch(WhenMappings.$EnumSwitchMapping$0[protoBuf$QualifiedNameTable$QualifiedName$Kind0.ordinal()]) {
                case 1: {
                    linkedList1.addFirst(s);
                    break;
                }
                case 2: {
                    linkedList0.addFirst(s);
                    break;
                }
                case 3: {
                    linkedList1.addFirst(s);
                    z = true;
                }
            }
            v = protoBuf$QualifiedNameTable$QualifiedName0.getParentQualifiedName();
        }
        return new Triple(linkedList0, linkedList1, Boolean.valueOf(z));
    }
}

