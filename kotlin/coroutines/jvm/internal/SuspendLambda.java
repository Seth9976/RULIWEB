package kotlin.coroutines.jvm.internal;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.FunctionBase;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000E\n\u0000\b!\u0018\u00002\u00020\u00012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u00022\u00020\u0004B\u000F\b\u0016\u0012\u0006\u0010\u0005\u001A\u00020\u0006¢\u0006\u0002\u0010\u0007B\u001F\u0012\u0006\u0010\u0005\u001A\u00020\u0006\u0012\u0010\u0010\b\u001A\f\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\t¢\u0006\u0002\u0010\nJ\b\u0010\r\u001A\u00020\u000EH\u0016R\u0014\u0010\u0005\u001A\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001A\u0004\b\u000B\u0010\f¨\u0006\u000F"}, d2 = {"Lkotlin/coroutines/jvm/internal/SuspendLambda;", "Lkotlin/coroutines/jvm/internal/ContinuationImpl;", "Lkotlin/jvm/internal/FunctionBase;", "", "Lkotlin/coroutines/jvm/internal/SuspendFunction;", "arity", "", "(I)V", "completion", "Lkotlin/coroutines/Continuation;", "(ILkotlin/coroutines/Continuation;)V", "getArity", "()I", "toString", "", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public abstract class SuspendLambda extends ContinuationImpl implements SuspendFunction, FunctionBase {
    private final int arity;

    public SuspendLambda(int v) {
        this(v, null);
    }

    public SuspendLambda(int v, Continuation continuation0) {
        super(continuation0);
        this.arity = v;
    }

    @Override  // kotlin.jvm.internal.FunctionBase
    public int getArity() {
        return this.arity;
    }

    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public String toString() {
        if(this.getCompletion() == null) {
            String s = Reflection.renderLambdaToString(this);
            Intrinsics.checkNotNullExpressionValue(s, "renderLambdaToString(this)");
            return s;
        }
        return super.toString();
    }
}

