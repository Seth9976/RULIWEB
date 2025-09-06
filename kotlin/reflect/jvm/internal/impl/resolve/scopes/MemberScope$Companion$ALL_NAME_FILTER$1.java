package kotlin.reflect.jvm.internal.impl.resolve.scopes;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.name.Name;

final class MemberScope.Companion.ALL_NAME_FILTER.1 extends Lambda implements Function1 {
    public static final MemberScope.Companion.ALL_NAME_FILTER.1 INSTANCE;

    static {
        MemberScope.Companion.ALL_NAME_FILTER.1.INSTANCE = new MemberScope.Companion.ALL_NAME_FILTER.1();
    }

    MemberScope.Companion.ALL_NAME_FILTER.1() {
        super(1);
    }

    public final Boolean invoke(Name name0) {
        Intrinsics.checkNotNullParameter(name0, "it");
        return true;
    }

    @Override  // kotlin.jvm.functions.Function1
    public Object invoke(Object object0) {
        return this.invoke(((Name)object0));
    }
}

