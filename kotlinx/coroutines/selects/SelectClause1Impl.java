package kotlinx.coroutines.selects;

import kotlin.Metadata;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\b\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u00A6\u0002\u0012\u0006\u0010\u0003\u001A\u00020\u0004\u0012U\u0010\u0005\u001AQ\u0012\u0013\u0012\u00110\u0004\u00A2\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u0003\u0012\u0017\u0012\u0015\u0012\u0002\b\u00030\t\u00A2\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\n\u0012\u0015\u0012\u0013\u0018\u00010\u0004\u00A2\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u000B\u0012\u0004\u0012\u00020\f0\u0006j\u0002`\r\u0012U\u0010\u000E\u001AQ\u0012\u0013\u0012\u00110\u0004\u00A2\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u0003\u0012\u0015\u0012\u0013\u0018\u00010\u0004\u00A2\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u000B\u0012\u0015\u0012\u0013\u0018\u00010\u0004\u00A2\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u000F\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0006j\u0002`\u0010\u0012i\b\u0002\u0010\u0011\u001Ac\u0012\u0017\u0012\u0015\u0012\u0002\b\u00030\t\u00A2\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\n\u0012\u0015\u0012\u0013\u0018\u00010\u0004\u00A2\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u000B\u0012\u0015\u0012\u0013\u0018\u00010\u0004\u00A2\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u0012\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\f0\u0013\u0018\u00010\u0006j\u0004\u0018\u0001`\u0015\u00A2\u0006\u0002\u0010\u0016R\u0014\u0010\u0003\u001A\u00020\u0004X\u0096\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0017\u0010\u0018Ru\u0010\u0011\u001Ac\u0012\u0017\u0012\u0015\u0012\u0002\b\u00030\t\u00A2\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\n\u0012\u0015\u0012\u0013\u0018\u00010\u0004\u00A2\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u000B\u0012\u0015\u0012\u0013\u0018\u00010\u0004\u00A2\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u0012\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\f0\u0013\u0018\u00010\u0006j\u0004\u0018\u0001`\u0015X\u0096\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0019\u0010\u001ARc\u0010\u000E\u001AQ\u0012\u0013\u0012\u00110\u0004\u00A2\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u0003\u0012\u0015\u0012\u0013\u0018\u00010\u0004\u00A2\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u000B\u0012\u0015\u0012\u0013\u0018\u00010\u0004\u00A2\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u000F\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0006j\u0002`\u0010X\u0096\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b\u001B\u0010\u001ARc\u0010\u0005\u001AQ\u0012\u0013\u0012\u00110\u0004\u00A2\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u0003\u0012\u0017\u0012\u0015\u0012\u0002\b\u00030\t\u00A2\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\n\u0012\u0015\u0012\u0013\u0018\u00010\u0004\u00A2\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u000B\u0012\u0004\u0012\u00020\f0\u0006j\u0002`\rX\u0096\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b\u001C\u0010\u001A\u00A8\u0006\u001D"}, d2 = {"Lkotlinx/coroutines/selects/SelectClause1Impl;", "Q", "Lkotlinx/coroutines/selects/SelectClause1;", "clauseObject", "", "regFunc", "Lkotlin/Function3;", "Lkotlin/ParameterName;", "name", "Lkotlinx/coroutines/selects/SelectInstance;", "select", "param", "", "Lkotlinx/coroutines/selects/RegistrationFunction;", "processResFunc", "clauseResult", "Lkotlinx/coroutines/selects/ProcessResultFunction;", "onCancellationConstructor", "internalResult", "Lkotlin/Function1;", "", "Lkotlinx/coroutines/selects/OnCancellationConstructor;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function3;)V", "getClauseObject", "()Ljava/lang/Object;", "getOnCancellationConstructor", "()Lkotlin/jvm/functions/Function3;", "getProcessResFunc", "getRegFunc", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class SelectClause1Impl implements SelectClause1 {
    private final Object clauseObject;
    private final Function3 onCancellationConstructor;
    private final Function3 processResFunc;
    private final Function3 regFunc;

    public SelectClause1Impl(Object object0, Function3 function30, Function3 function31, Function3 function32) {
        this.clauseObject = object0;
        this.regFunc = function30;
        this.processResFunc = function31;
        this.onCancellationConstructor = function32;
    }

    public SelectClause1Impl(Object object0, Function3 function30, Function3 function31, Function3 function32, int v, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v & 8) != 0) {
            function32 = null;
        }
        this(object0, function30, function31, function32);
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

