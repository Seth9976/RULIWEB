package kotlinx.coroutines.internal;

import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.JvmInline;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\u000B\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000E\n\u0002\b\u0003\b\u0081@\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0016\u0012\n\b\u0002\u0010\u0003\u001A\u0004\u0018\u00010\u0002ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005J\u001A\u0010\u0006\u001A\u00020\u00072\b\u0010\b\u001A\u0004\u0018\u00010\u0002HÖ\u0003¢\u0006\u0004\b\t\u0010\nJ$\u0010\u000B\u001A\u00020\f2\u0012\u0010\r\u001A\u000E\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\f0\u000EH\u0086\b¢\u0006\u0004\b\u000F\u0010\u0010J\u0010\u0010\u0011\u001A\u00020\u0012HÖ\u0001¢\u0006\u0004\b\u0013\u0010\u0014J\'\u0010\u0015\u001A\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010\u0016\u001A\u00028\u0000H\u0086\u0002ø\u0001\u0001ø\u0001\u0002ø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018J\u0010\u0010\u0019\u001A\u00020\u001AHÖ\u0001¢\u0006\u0004\b\u001B\u0010\u001CR\u0010\u0010\u0003\u001A\u0004\u0018\u00010\u0002X\u0082\u0004¢\u0006\u0002\n\u0000\u0088\u0001\u0003\u0092\u0001\u0004\u0018\u00010\u0002ø\u0001\u0000\u0082\u0002\u000F\n\u0002\b\u0019\n\u0002\b!\n\u0005\b¡\u001E0\u0001¨\u0006\u001D"}, d2 = {"Lkotlinx/coroutines/internal/InlineList;", "E", "", "holder", "constructor-impl", "(Ljava/lang/Object;)Ljava/lang/Object;", "equals", "", "other", "equals-impl", "(Ljava/lang/Object;Ljava/lang/Object;)Z", "forEachReversed", "", "action", "Lkotlin/Function1;", "forEachReversed-impl", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)V", "hashCode", "", "hashCode-impl", "(Ljava/lang/Object;)I", "plus", "element", "plus-FjFbRPM", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "toString", "", "toString-impl", "(Ljava/lang/Object;)Ljava/lang/String;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
@JvmInline
public final class InlineList {
    private final Object holder;

    private InlineList(Object object0) {
        this.holder = object0;
    }

    public static final InlineList box-impl(Object object0) {
        return new InlineList(object0);
    }

    public static Object constructor-impl(Object object0) [...] // Inlined contents

    public static Object constructor-impl$default(Object object0, int v, DefaultConstructorMarker defaultConstructorMarker0) {
        return (v & 1) == 0 ? object0 : null;
    }

    @Override
    public boolean equals(Object object0) {
        return InlineList.equals-impl(this.holder, object0);
    }

    // 去混淆评级： 低(20)
    public static boolean equals-impl(Object object0, Object object1) {
        return object1 instanceof InlineList ? Intrinsics.areEqual(object0, ((InlineList)object1).unbox-impl()) : false;
    }

    public static final boolean equals-impl0(Object object0, Object object1) {
        return Intrinsics.areEqual(object0, object1);
    }

    public static final void forEachReversed-impl(Object object0, Function1 function10) {
        if(object0 != null) {
            if(!(object0 instanceof ArrayList)) {
                function10.invoke(object0);
                return;
            }
            Intrinsics.checkNotNull(object0, "null cannot be cast to non-null type java.util.ArrayList<E of kotlinx.coroutines.internal.InlineList>{ kotlin.collections.TypeAliasesKt.ArrayList<E of kotlinx.coroutines.internal.InlineList> }");
            for(int v = ((ArrayList)object0).size() - 1; -1 < v; --v) {
                function10.invoke(((ArrayList)object0).get(v));
            }
        }
    }

    @Override
    public int hashCode() {
        return InlineList.hashCode-impl(this.holder);
    }

    public static int hashCode-impl(Object object0) {
        return object0 == null ? 0 : object0.hashCode();
    }

    public static final Object plus-FjFbRPM(Object object0, Object object1) {
        if(object0 == null) {
            return object1;
        }
        if(object0 instanceof ArrayList) {
            Intrinsics.checkNotNull(object0, "null cannot be cast to non-null type java.util.ArrayList<E of kotlinx.coroutines.internal.InlineList>{ kotlin.collections.TypeAliasesKt.ArrayList<E of kotlinx.coroutines.internal.InlineList> }");
            ((ArrayList)object0).add(object1);
            return object0;
        }
        ArrayList arrayList0 = new ArrayList(4);
        arrayList0.add(object0);
        arrayList0.add(object1);
        return arrayList0;
    }

    @Override
    public String toString() {
        return "InlineList(holder=" + this.holder + ')';
    }

    public static String toString-impl(Object object0) [...] // Inlined contents

    public final Object unbox-impl() {
        return this.holder;
    }
}

