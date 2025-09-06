package kotlinx.coroutines.selects;

import kotlin.Metadata;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\b\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u000E\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0003B\u00A6\u0002\u0012\u0006\u0010\u0004\u001A\u00020\u0005\u0012U\u0010\u0006\u001AQ\u0012\u0013\u0012\u00110\u0005\u00A2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u0004\u0012\u0017\u0012\u0015\u0012\u0002\b\u00030\n\u00A2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000B\u0012\u0015\u0012\u0013\u0018\u00010\u0005\u00A2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\r0\u0007j\u0002`\u000E\u0012U\u0010\u000F\u001AQ\u0012\u0013\u0012\u00110\u0005\u00A2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u0004\u0012\u0015\u0012\u0013\u0018\u00010\u0005\u00A2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0015\u0012\u0013\u0018\u00010\u0005\u00A2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0007j\u0002`\u0011\u0012i\b\u0002\u0010\u0012\u001Ac\u0012\u0017\u0012\u0015\u0012\u0002\b\u00030\n\u00A2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000B\u0012\u0015\u0012\u0013\u0018\u00010\u0005\u00A2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0015\u0012\u0013\u0018\u00010\u0005\u00A2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u0013\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\r0\u0014\u0018\u00010\u0007j\u0004\u0018\u0001`\u0016\u00A2\u0006\u0002\u0010\u0017R\u0014\u0010\u0004\u001A\u00020\u0005X\u0096\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0018\u0010\u0019Ru\u0010\u0012\u001Ac\u0012\u0017\u0012\u0015\u0012\u0002\b\u00030\n\u00A2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000B\u0012\u0015\u0012\u0013\u0018\u00010\u0005\u00A2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0015\u0012\u0013\u0018\u00010\u0005\u00A2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u0013\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\r0\u0014\u0018\u00010\u0007j\u0004\u0018\u0001`\u0016X\u0096\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b\u001A\u0010\u001BRc\u0010\u000F\u001AQ\u0012\u0013\u0012\u00110\u0005\u00A2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u0004\u0012\u0015\u0012\u0013\u0018\u00010\u0005\u00A2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0015\u0012\u0013\u0018\u00010\u0005\u00A2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0007j\u0002`\u0011X\u0096\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b\u001C\u0010\u001BRc\u0010\u0006\u001AQ\u0012\u0013\u0012\u00110\u0005\u00A2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u0004\u0012\u0017\u0012\u0015\u0012\u0002\b\u00030\n\u00A2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000B\u0012\u0015\u0012\u0013\u0018\u00010\u0005\u00A2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\r0\u0007j\u0002`\u000EX\u0096\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b\u001D\u0010\u001B\u00A8\u0006\u001E"}, d2 = {"Lkotlinx/coroutines/selects/SelectClause2Impl;", "P", "Q", "Lkotlinx/coroutines/selects/SelectClause2;", "clauseObject", "", "regFunc", "Lkotlin/Function3;", "Lkotlin/ParameterName;", "name", "Lkotlinx/coroutines/selects/SelectInstance;", "select", "param", "", "Lkotlinx/coroutines/selects/RegistrationFunction;", "processResFunc", "clauseResult", "Lkotlinx/coroutines/selects/ProcessResultFunction;", "onCancellationConstructor", "internalResult", "Lkotlin/Function1;", "", "Lkotlinx/coroutines/selects/OnCancellationConstructor;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function3;)V", "getClauseObject", "()Ljava/lang/Object;", "getOnCancellationConstructor", "()Lkotlin/jvm/functions/Function3;", "getProcessResFunc", "getRegFunc", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class SelectClause2Impl implements SelectClause2 {
    private final Object clauseObject;
    private final Function3 onCancellationConstructor;
    private final Function3 processResFunc;
    private final Function3 regFunc;

    public SelectClause2Impl(Object object0, Function3 function30, Function3 function31, Function3 function32) {
        this.clauseObject = object0;
        this.regFunc = function30;
        this.processResFunc = function31;
        this.onCancellationConstructor = function32;
    }

    public SelectClause2Impl(Object object0, Function3 function30, Function3 function31, Function3 function32, int v, DefaultConstructorMarker defaultConstructorMarker0) {
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

