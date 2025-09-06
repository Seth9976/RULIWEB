package kotlinx.coroutines.selects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0011\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\r\u0012\u0006\u0010\u0003\u001A\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0011\u0010\t\u001A\u00028\u0000H\u0091@ø\u0001\u0000¢\u0006\u0002\u0010\nJ\b\u0010\u000B\u001A\u00020\fH\u0002J3\u0010\r\u001A\u00020\f*\u00020\u000E2\u001C\u0010\u000F\u001A\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0011\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u0010H\u0096\u0002ø\u0001\u0000¢\u0006\u0002\u0010\u0013JE\u0010\r\u001A\u00020\f\"\u0004\b\u0001\u0010\u0014*\b\u0012\u0004\u0012\u0002H\u00140\u00152\"\u0010\u000F\u001A\u001E\b\u0001\u0012\u0004\u0012\u0002H\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0011\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u0016H\u0096\u0002ø\u0001\u0000¢\u0006\u0002\u0010\u0017JY\u0010\r\u001A\u00020\f\"\u0004\b\u0001\u0010\u0018\"\u0004\b\u0002\u0010\u0014*\u000E\u0012\u0004\u0012\u0002H\u0018\u0012\u0004\u0012\u0002H\u00140\u00192\u0006\u0010\u001A\u001A\u0002H\u00182\"\u0010\u000F\u001A\u001E\b\u0001\u0012\u0004\u0012\u0002H\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0011\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u0016H\u0096\u0002ø\u0001\u0000¢\u0006\u0002\u0010\u001BR\u001E\u0010\u0006\u001A\u0012\u0012\u000E\u0012\f0\bR\b\u0012\u0004\u0012\u00028\u00000\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001C"}, d2 = {"Lkotlinx/coroutines/selects/UnbiasedSelectImplementation;", "R", "Lkotlinx/coroutines/selects/SelectImplementation;", "context", "Lkotlin/coroutines/CoroutineContext;", "(Lkotlin/coroutines/CoroutineContext;)V", "clausesToRegister", "", "Lkotlinx/coroutines/selects/SelectImplementation$ClauseData;", "doSelect", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "shuffleAndRegisterClauses", "", "invoke", "Lkotlinx/coroutines/selects/SelectClause0;", "block", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "", "(Lkotlinx/coroutines/selects/SelectClause0;Lkotlin/jvm/functions/Function1;)V", "Q", "Lkotlinx/coroutines/selects/SelectClause1;", "Lkotlin/Function2;", "(Lkotlinx/coroutines/selects/SelectClause1;Lkotlin/jvm/functions/Function2;)V", "P", "Lkotlinx/coroutines/selects/SelectClause2;", "param", "(Lkotlinx/coroutines/selects/SelectClause2;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)V", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public class UnbiasedSelectImplementation extends SelectImplementation {
    private final List clausesToRegister;

    public UnbiasedSelectImplementation(CoroutineContext coroutineContext0) {
        super(coroutineContext0);
        this.clausesToRegister = new ArrayList();
    }

    @Override  // kotlinx.coroutines.selects.SelectImplementation
    public Object doSelect(Continuation continuation0) {
        return UnbiasedSelectImplementation.doSelect$suspendImpl(this, continuation0);
    }

    static Object doSelect$suspendImpl(UnbiasedSelectImplementation unbiasedSelectImplementation0, Continuation continuation0) {
        unbiasedSelectImplementation0.shuffleAndRegisterClauses();
        return unbiasedSelectImplementation0.super.doSelect(continuation0);
    }

    @Override  // kotlinx.coroutines.selects.SelectImplementation
    public void invoke(SelectClause0 selectClause00, Function1 function10) {
        ClauseData selectImplementation$ClauseData0 = new ClauseData(this, selectClause00.getClauseObject(), selectClause00.getRegFunc(), selectClause00.getProcessResFunc(), SelectKt.getPARAM_CLAUSE_0(), function10, selectClause00.getOnCancellationConstructor());
        this.clausesToRegister.add(selectImplementation$ClauseData0);
    }

    @Override  // kotlinx.coroutines.selects.SelectImplementation
    public void invoke(SelectClause1 selectClause10, Function2 function20) {
        ClauseData selectImplementation$ClauseData0 = new ClauseData(this, selectClause10.getClauseObject(), selectClause10.getRegFunc(), selectClause10.getProcessResFunc(), null, function20, selectClause10.getOnCancellationConstructor());
        this.clausesToRegister.add(selectImplementation$ClauseData0);
    }

    @Override  // kotlinx.coroutines.selects.SelectImplementation
    public void invoke(SelectClause2 selectClause20, Object object0, Function2 function20) {
        ClauseData selectImplementation$ClauseData0 = new ClauseData(this, selectClause20.getClauseObject(), selectClause20.getRegFunc(), selectClause20.getProcessResFunc(), object0, function20, selectClause20.getOnCancellationConstructor());
        this.clausesToRegister.add(selectImplementation$ClauseData0);
    }

    private final void shuffleAndRegisterClauses() {
        try {
            Collections.shuffle(this.clausesToRegister);
            for(Object object0: this.clausesToRegister) {
                SelectImplementation.register$default(this, ((ClauseData)object0), false, 1, null);
            }
        }
        finally {
            this.clausesToRegister.clear();
        }
    }
}

