package kotlin.reflect.jvm.internal.impl.resolve.scopes;

import java.util.Collection;
import java.util.Set;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation;
import kotlin.reflect.jvm.internal.impl.name.Name;

public interface MemberScope extends ResolutionScope {
    public static final class Companion {
        static final Companion $$INSTANCE;
        private static final Function1 ALL_NAME_FILTER;

        static {
            Companion.$$INSTANCE = new Companion();
            Companion.ALL_NAME_FILTER = MemberScope.Companion.ALL_NAME_FILTER.1.INSTANCE;
        }

        public final Function1 getALL_NAME_FILTER() {
            return Companion.ALL_NAME_FILTER;
        }
    }

    public static final class DefaultImpls {
        public static void recordLookup(MemberScope memberScope0, Name name0, LookupLocation lookupLocation0) {
            Intrinsics.checkNotNullParameter(name0, "name");
            Intrinsics.checkNotNullParameter(lookupLocation0, "location");
            kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope.DefaultImpls.recordLookup(memberScope0, name0, lookupLocation0);
        }
    }

    public static final class Empty extends MemberScopeImpl {
        public static final Empty INSTANCE;

        static {
            Empty.INSTANCE = new Empty();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl
        public Set getClassifierNames() {
            return SetsKt.emptySet();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl
        public Set getFunctionNames() {
            return SetsKt.emptySet();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl
        public Set getVariableNames() {
            return SetsKt.emptySet();
        }
    }

    public static final Companion Companion;

    static {
        MemberScope.Companion = Companion.$$INSTANCE;
    }

    Set getClassifierNames();

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope
    Collection getContributedFunctions(Name arg1, LookupLocation arg2);

    Collection getContributedVariables(Name arg1, LookupLocation arg2);

    Set getFunctionNames();

    Set getVariableNames();
}

