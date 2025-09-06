package kotlinx.coroutines.selects;

import kotlin.Metadata;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u00CF\u0001\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012U\u0010\u0004\u001AQ\u0012\u0013\u0012\u00110\u0003\u00A2\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\u0002\u0012\u0017\u0012\u0015\u0012\u0002\b\u00030\b\u00A2\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\t\u0012\u0015\u0012\u0013\u0018\u00010\u0003\u00A2\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000B0\u0005j\u0002`\f\u0012i\b\u0002\u0010\r\u001Ac\u0012\u0017\u0012\u0015\u0012\u0002\b\u00030\b\u00A2\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\t\u0012\u0015\u0012\u0013\u0018\u00010\u0003\u00A2\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\n\u0012\u0015\u0012\u0013\u0018\u00010\u0003\u00A2\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\u000E\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u000B0\u000F\u0018\u00010\u0005j\u0004\u0018\u0001`\u0011\u00A2\u0006\u0002\u0010\u0012R\u0014\u0010\u0002\u001A\u00020\u0003X\u0096\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0013\u0010\u0014Ru\u0010\r\u001Ac\u0012\u0017\u0012\u0015\u0012\u0002\b\u00030\b\u00A2\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\t\u0012\u0015\u0012\u0013\u0018\u00010\u0003\u00A2\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\n\u0012\u0015\u0012\u0013\u0018\u00010\u0003\u00A2\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\u000E\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u000B0\u000F\u0018\u00010\u0005j\u0004\u0018\u0001`\u0011X\u0096\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0015\u0010\u0016Rc\u0010\u0017\u001AQ\u0012\u0013\u0012\u00110\u0003\u00A2\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\u0002\u0012\u0015\u0012\u0013\u0018\u00010\u0003\u00A2\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\n\u0012\u0015\u0012\u0013\u0018\u00010\u0003\u00A2\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\u0018\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0005j\u0002`\u0019X\u0096\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b\u001A\u0010\u0016Rc\u0010\u0004\u001AQ\u0012\u0013\u0012\u00110\u0003\u00A2\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\u0002\u0012\u0017\u0012\u0015\u0012\u0002\b\u00030\b\u00A2\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\t\u0012\u0015\u0012\u0013\u0018\u00010\u0003\u00A2\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000B0\u0005j\u0002`\fX\u0096\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b\u001B\u0010\u0016\u00A8\u0006\u001C"}, d2 = {"Lkotlinx/coroutines/selects/SelectClause0Impl;", "Lkotlinx/coroutines/selects/SelectClause0;", "clauseObject", "", "regFunc", "Lkotlin/Function3;", "Lkotlin/ParameterName;", "name", "Lkotlinx/coroutines/selects/SelectInstance;", "select", "param", "", "Lkotlinx/coroutines/selects/RegistrationFunction;", "onCancellationConstructor", "internalResult", "Lkotlin/Function1;", "", "Lkotlinx/coroutines/selects/OnCancellationConstructor;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function3;)V", "getClauseObject", "()Ljava/lang/Object;", "getOnCancellationConstructor", "()Lkotlin/jvm/functions/Function3;", "processResFunc", "clauseResult", "Lkotlinx/coroutines/selects/ProcessResultFunction;", "getProcessResFunc", "getRegFunc", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class SelectClause0Impl implements SelectClause0 {
    private final Object clauseObject;
    private final Function3 onCancellationConstructor;
    private final Function3 processResFunc;
    private final Function3 regFunc;

    public SelectClause0Impl(Object object0, Function3 function30, Function3 function31) {
        this.clauseObject = object0;
        this.regFunc = function30;
        this.onCancellationConstructor = function31;
        this.processResFunc = SelectKt.access$getDUMMY_PROCESS_RESULT_FUNCTION$p();
    }

    public SelectClause0Impl(Object object0, Function3 function30, Function3 function31, int v, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v & 4) != 0) {
            function31 = null;
        }
        this(object0, function30, function31);
    }

    @Override  // kotlinx.coroutines.selects.SelectClause
    public Object getClauseObject() {
        return this.clauseObject;
    }

    @Override  // kotlinx.coroutines.selects.SelectClause
    public Function3 getOnCancellationConstructor() {
        return this.onCancellationConstructor;
    }

    @Override  // kotlinx.coroutines.selects.SelectClause
    public Function3 getProcessResFunc() {
        return this.processResFunc;
    }

    @Override  // kotlinx.coroutines.selects.SelectClause
    public Function3 getRegFunc() {
        return this.regFunc;
    }
}

