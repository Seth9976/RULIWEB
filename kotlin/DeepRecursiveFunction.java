package kotlin;

import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u00020\u0003BC\u00129\u0010\u0004\u001A5\b\u0001\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0006\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0005¢\u0006\u0002\b\bø\u0001\u0000¢\u0006\u0002\u0010\tRL\u0010\u0004\u001A5\b\u0001\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0006\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0005¢\u0006\u0002\b\bX\u0080\u0004ø\u0001\u0000¢\u0006\n\n\u0002\u0010\f\u001A\u0004\b\n\u0010\u000B\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\r"}, d2 = {"Lkotlin/DeepRecursiveFunction;", "T", "R", "", "block", "Lkotlin/Function3;", "Lkotlin/DeepRecursiveScope;", "Lkotlin/coroutines/Continuation;", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function3;)V", "getBlock$kotlin_stdlib", "()Lkotlin/jvm/functions/Function3;", "Lkotlin/jvm/functions/Function3;", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public final class DeepRecursiveFunction {
    private final Function3 block;

    public DeepRecursiveFunction(Function3 function30) {
        Intrinsics.checkNotNullParameter(function30, "block");
        super();
        this.block = function30;
    }

    public final Function3 getBlock$kotlin_stdlib() {
        return this.block;
    }
}

