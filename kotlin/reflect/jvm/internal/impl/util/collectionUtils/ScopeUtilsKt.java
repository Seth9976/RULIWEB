package kotlin.reflect.jvm.internal.impl.util.collectionUtils;

import java.util.Collection;
import java.util.LinkedHashSet;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope.Empty;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.utils.SmartList;

public final class ScopeUtilsKt {
    public static final Collection concat(Collection collection0, Collection collection1) {
        Intrinsics.checkNotNullParameter(collection1, "collection");
        if(collection1.isEmpty()) {
            return collection0;
        }
        if(collection0 == null) {
            return collection1;
        }
        if(collection0 instanceof LinkedHashSet) {
            ((LinkedHashSet)collection0).addAll(collection1);
            return collection0;
        }
        LinkedHashSet linkedHashSet0 = new LinkedHashSet(collection0);
        linkedHashSet0.addAll(collection1);
        return linkedHashSet0;
    }

    public static final SmartList listOfNonEmptyScopes(Iterable iterable0) {
        Intrinsics.checkNotNullParameter(iterable0, "scopes");
        Collection collection0 = new SmartList();
        for(Object object0: iterable0) {
            if(((MemberScope)object0) != null && ((MemberScope)object0) != Empty.INSTANCE) {
                collection0.add(object0);
            }
        }
        return (SmartList)collection0;
    }
}

