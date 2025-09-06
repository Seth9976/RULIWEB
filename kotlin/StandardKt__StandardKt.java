package kotlin;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000:\n\u0000\n\u0002\u0010\u0001\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000B\n\u0002\b\u0002\u001A\t\u0010\u0000\u001A\u00020\u0001H\u0087\b\u001A\u0011\u0010\u0000\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u0003H\u0087\b\u001A3\u0010\u0004\u001A\u00020\u00052\u0006\u0010\u0006\u001A\u00020\u00072\u0012\u0010\b\u001A\u000E\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00050\tH\u0087\b\u00F8\u0001\u0000\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0002\u001A2\u0010\n\u001A\u0002H\u000B\"\u0004\b\u0000\u0010\u000B2\f\u0010\f\u001A\b\u0012\u0004\u0012\u0002H\u000B0\rH\u0087\b\u00F8\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\u00A2\u0006\u0002\u0010\u000E\u001AK\u0010\u000F\u001A\u0002H\u000B\"\u0004\b\u0000\u0010\u0010\"\u0004\b\u0001\u0010\u000B2\u0006\u0010\u0011\u001A\u0002H\u00102\u0017\u0010\f\u001A\u0013\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H\u000B0\t\u00A2\u0006\u0002\b\u0012H\u0087\b\u00F8\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001\u00A2\u0006\u0002\u0010\u0013\u001A<\u0010\u0014\u001A\u0002H\u0010\"\u0004\b\u0000\u0010\u0010*\u0002H\u00102\u0012\u0010\f\u001A\u000E\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\u00050\tH\u0087\b\u00F8\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\u00A2\u0006\u0002\u0010\u0013\u001AA\u0010\u0015\u001A\u0002H\u0010\"\u0004\b\u0000\u0010\u0010*\u0002H\u00102\u0017\u0010\f\u001A\u0013\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\u00050\t\u00A2\u0006\u0002\b\u0012H\u0087\b\u00F8\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\u00A2\u0006\u0002\u0010\u0013\u001AB\u0010\u0016\u001A\u0002H\u000B\"\u0004\b\u0000\u0010\u0010\"\u0004\b\u0001\u0010\u000B*\u0002H\u00102\u0012\u0010\f\u001A\u000E\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H\u000B0\tH\u0087\b\u00F8\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\u00A2\u0006\u0002\u0010\u0013\u001AG\u0010\n\u001A\u0002H\u000B\"\u0004\b\u0000\u0010\u0010\"\u0004\b\u0001\u0010\u000B*\u0002H\u00102\u0017\u0010\f\u001A\u0013\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H\u000B0\t\u00A2\u0006\u0002\b\u0012H\u0087\b\u00F8\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\u00A2\u0006\u0002\u0010\u0013\u001A>\u0010\u0017\u001A\u0004\u0018\u0001H\u0010\"\u0004\b\u0000\u0010\u0010*\u0002H\u00102\u0012\u0010\u0018\u001A\u000E\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\u00190\tH\u0087\b\u00F8\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\u00A2\u0006\u0002\u0010\u0013\u001A>\u0010\u001A\u001A\u0004\u0018\u0001H\u0010\"\u0004\b\u0000\u0010\u0010*\u0002H\u00102\u0012\u0010\u0018\u001A\u000E\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\u00190\tH\u0087\b\u00F8\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\u00A2\u0006\u0002\u0010\u0013\u0082\u0002\u0007\n\u0005\b\u009920\u0001\u00A8\u0006\u001B"}, d2 = {"TODO", "", "reason", "", "repeat", "", "times", "", "action", "Lkotlin/Function1;", "run", "R", "block", "Lkotlin/Function0;", "(Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "with", "T", "receiver", "Lkotlin/ExtensionFunctionType;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "also", "apply", "let", "takeIf", "predicate", "", "takeUnless", "kotlin-stdlib"}, k = 5, mv = {1, 9, 0}, xi = 49, xs = "kotlin/StandardKt")
class StandardKt__StandardKt {
    private static final Void TODO() {
        throw new NotImplementedError(null, 1, null);
    }

    private static final Void TODO(String s) {
        Intrinsics.checkNotNullParameter(s, "reason");
        throw new NotImplementedError("An operation is not implemented: " + s);
    }

    private static final Object also(Object object0, Function1 function10) {
        Intrinsics.checkNotNullParameter(function10, "block");
        function10.invoke(object0);
        return object0;
    }

    private static final Object apply(Object object0, Function1 function10) {
        Intrinsics.checkNotNullParameter(function10, "block");
        function10.invoke(object0);
        return object0;
    }

    private static final Object let(Object object0, Function1 function10) {
        Intrinsics.checkNotNullParameter(function10, "block");
        return function10.invoke(object0);
    }

    private static final void repeat(int v, Function1 function10) {
        Intrinsics.checkNotNullParameter(function10, "action");
        for(int v1 = 0; v1 < v; ++v1) {
            function10.invoke(v1);
        }
    }

    private static final Object run(Object object0, Function1 function10) {
        Intrinsics.checkNotNullParameter(function10, "block");
        return function10.invoke(object0);
    }

    private static final Object run(Function0 function00) {
        Intrinsics.checkNotNullParameter(function00, "block");
        return function00.invoke();
    }

    private static final Object takeIf(Object object0, Function1 function10) {
        Intrinsics.checkNotNullParameter(function10, "predicate");
        return ((Boolean)function10.invoke(object0)).booleanValue() ? object0 : null;
    }

    private static final Object takeUnless(Object object0, Function1 function10) {
        Intrinsics.checkNotNullParameter(function10, "predicate");
        return ((Boolean)function10.invoke(object0)).booleanValue() ? null : object0;
    }

    private static final Object with(Object object0, Function1 function10) {
        Intrinsics.checkNotNullParameter(function10, "block");
        return function10.invoke(object0);
    }
}

