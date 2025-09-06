package kotlin.reflect.jvm.internal.impl.resolve.scopes;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

public final class MemberScopeKt {
    public static final Set flatMapClassifierNamesOrNull(Iterable iterable0) {
        Intrinsics.checkNotNullParameter(iterable0, "<this>");
        Collection collection0 = new HashSet();
        for(Object object0: iterable0) {
            Iterable iterable1 = ((MemberScope)object0).getClassifierNames();
            if(iterable1 == null) {
                return null;
            }
            CollectionsKt.addAll(collection0, iterable1);
        }
        return (Set)collection0;
    }
}

