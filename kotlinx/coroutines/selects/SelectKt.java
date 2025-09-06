package kotlinx.coroutines.selects;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.internal.Symbol;

@Metadata(d1 = {"\u0000d\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001A\u0010\u0010\u0016\u001A\u00020\u00172\u0006\u0010\u0018\u001A\u00020\u0012H\u0002\u001AE\u0010\u0019\u001A\u0002H\u001A\"\u0004\b\u0000\u0010\u001A2\u001F\b\u0004\u0010\u001B\u001A\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u001A0\u001D\u0012\u0004\u0012\u00020\u001E0\u001C\u00A2\u0006\u0002\b\u001FH\u0086H\u00F8\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\u00A2\u0006\u0002\u0010 \u001A7\u0010!\u001A\u00020\"*\b\u0012\u0004\u0012\u00020\u001E0#2#\u0010$\u001A\u001F\u0012\u0013\u0012\u00110%\u00A2\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(&\u0012\u0004\u0012\u00020\u001E\u0018\u00010\u001CH\u0002\"]\u0010\u0000\u001AQ\u0012\u0013\u0012\u00110\u0002\u00A2\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0005\u0012\u0015\u0012\u0013\u0018\u00010\u0002\u00A2\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0006\u0012\u0015\u0012\u0013\u0018\u00010\u0002\u00A2\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001j\u0002`\bX\u0082\u0004\u00A2\u0006\u0002\n\u0000\"\u000E\u0010\t\u001A\u00020\nX\u0082\u0004\u00A2\u0006\u0002\n\u0000\"\u0014\u0010\u000B\u001A\u00020\nX\u0080\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b\f\u0010\r\"\u000E\u0010\u000E\u001A\u00020\nX\u0082\u0004\u00A2\u0006\u0002\n\u0000\"\u000E\u0010\u000F\u001A\u00020\nX\u0082\u0004\u00A2\u0006\u0002\n\u0000\"\u000E\u0010\u0010\u001A\u00020\nX\u0082\u0004\u00A2\u0006\u0002\n\u0000\"\u000E\u0010\u0011\u001A\u00020\u0012X\u0082T\u00A2\u0006\u0002\n\u0000\"\u000E\u0010\u0013\u001A\u00020\u0012X\u0082T\u00A2\u0006\u0002\n\u0000\"\u000E\u0010\u0014\u001A\u00020\u0012X\u0082T\u00A2\u0006\u0002\n\u0000\"\u000E\u0010\u0015\u001A\u00020\u0012X\u0082T\u00A2\u0006\u0002\n\u0000*\u00C2\u0001\b\u0007\u0010\'\"[\u0012\u0017\u0012\u0015\u0012\u0002\b\u00030(\u00A2\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0019\u0012\u0015\u0012\u0013\u0018\u00010\u0002\u00A2\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0006\u0012\u0015\u0012\u0013\u0018\u00010\u0002\u00A2\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b()\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020\u001E0\u001C0\u00012[\u0012\u0017\u0012\u0015\u0012\u0002\b\u00030(\u00A2\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0019\u0012\u0015\u0012\u0013\u0018\u00010\u0002\u00A2\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0006\u0012\u0015\u0012\u0013\u0018\u00010\u0002\u00A2\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b()\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020\u001E0\u001C0\u0001B\u0002\b**\u00A6\u0001\b\u0007\u0010+\"M\u0012\u0013\u0012\u00110\u0002\u00A2\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0005\u0012\u0015\u0012\u0013\u0018\u00010\u0002\u00A2\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0006\u0012\u0015\u0012\u0013\u0018\u00010\u0002\u00A2\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00012M\u0012\u0013\u0012\u00110\u0002\u00A2\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0005\u0012\u0015\u0012\u0013\u0018\u00010\u0002\u00A2\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0006\u0012\u0015\u0012\u0013\u0018\u00010\u0002\u00A2\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001B\u0002\b**\u00A6\u0001\b\u0007\u0010,\"M\u0012\u0013\u0012\u00110\u0002\u00A2\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0005\u0012\u0017\u0012\u0015\u0012\u0002\b\u00030(\u00A2\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0019\u0012\u0015\u0012\u0013\u0018\u00010\u0002\u00A2\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\u001E0\u00012M\u0012\u0013\u0012\u00110\u0002\u00A2\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0005\u0012\u0017\u0012\u0015\u0012\u0002\b\u00030(\u00A2\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0019\u0012\u0015\u0012\u0013\u0018\u00010\u0002\u00A2\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\u001E0\u0001B\u0002\b*\u0082\u0002\u0004\n\u0002\b\u0019\u00A8\u0006-"}, d2 = {"DUMMY_PROCESS_RESULT_FUNCTION", "Lkotlin/Function3;", "", "Lkotlin/ParameterName;", "name", "clauseObject", "param", "clauseResult", "Lkotlinx/coroutines/selects/ProcessResultFunction;", "NO_RESULT", "Lkotlinx/coroutines/internal/Symbol;", "PARAM_CLAUSE_0", "getPARAM_CLAUSE_0", "()Lkotlinx/coroutines/internal/Symbol;", "STATE_CANCELLED", "STATE_COMPLETED", "STATE_REG", "TRY_SELECT_ALREADY_SELECTED", "", "TRY_SELECT_CANCELLED", "TRY_SELECT_REREGISTER", "TRY_SELECT_SUCCESSFUL", "TrySelectDetailedResult", "Lkotlinx/coroutines/selects/TrySelectDetailedResult;", "trySelectInternalResult", "select", "R", "builder", "Lkotlin/Function1;", "Lkotlinx/coroutines/selects/SelectBuilder;", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "tryResume", "", "Lkotlinx/coroutines/CancellableContinuation;", "onCancellation", "", "cause", "OnCancellationConstructor", "Lkotlinx/coroutines/selects/SelectInstance;", "internalResult", "Lkotlinx/coroutines/InternalCoroutinesApi;", "ProcessResultFunction", "RegistrationFunction", "kotlinx-coroutines-core"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class SelectKt {
    private static final Function3 DUMMY_PROCESS_RESULT_FUNCTION = null;
    private static final Symbol NO_RESULT = null;
    private static final Symbol PARAM_CLAUSE_0 = null;
    private static final Symbol STATE_CANCELLED = null;
    private static final Symbol STATE_COMPLETED = null;
    private static final Symbol STATE_REG = null;
    private static final int TRY_SELECT_ALREADY_SELECTED = 3;
    private static final int TRY_SELECT_CANCELLED = 2;
    private static final int TRY_SELECT_REREGISTER = 1;
    private static final int TRY_SELECT_SUCCESSFUL;

    static {
        SelectKt.DUMMY_PROCESS_RESULT_FUNCTION = SelectKt.DUMMY_PROCESS_RESULT_FUNCTION.1.INSTANCE;
        SelectKt.STATE_REG = new Symbol("STATE_REG");
        SelectKt.STATE_COMPLETED = new Symbol("STATE_COMPLETED");
        SelectKt.STATE_CANCELLED = new Symbol("STATE_CANCELLED");
        SelectKt.NO_RESULT = new Symbol("NO_RESULT");
        SelectKt.PARAM_CLAUSE_0 = new Symbol("PARAM_CLAUSE_0");
    }

    public static void OnCancellationConstructor$annotations() {
    }

    public static void ProcessResultFunction$annotations() {
    }

    public static void RegistrationFunction$annotations() {
    }

    private static final TrySelectDetailedResult TrySelectDetailedResult(int v) {
        switch(v) {
            case 0: {
                return TrySelectDetailedResult.SUCCESSFUL;
            }
            case 1: {
                return TrySelectDetailedResult.REREGISTER;
            }
            case 2: {
                return TrySelectDetailedResult.CANCELLED;
            }
            case 3: {
                return TrySelectDetailedResult.ALREADY_SELECTED;
            }
            default: {
                throw new IllegalStateException(("Unexpected internal result: " + v).toString());
            }
        }
    }

    public static final Function3 access$getDUMMY_PROCESS_RESULT_FUNCTION$p() {
        return SelectKt.DUMMY_PROCESS_RESULT_FUNCTION;
    }

    public static final Symbol getPARAM_CLAUSE_0() {
        return SelectKt.PARAM_CLAUSE_0;
    }

    public static final Object select(Function1 function10, Continuation continuation0) {
        SelectImplementation selectImplementation0 = new SelectImplementation(continuation0.getContext());
        function10.invoke(selectImplementation0);
        return selectImplementation0.doSelect(continuation0);
    }

    private static final Object select$$forInline(Function1 function10, Continuation continuation0) {
        throw new NullPointerException();
    }

    private static final boolean tryResume(CancellableContinuation cancellableContinuation0, Function1 function10) {
        Object object0 = cancellableContinuation0.tryResume(Unit.INSTANCE, null, function10);
        if(object0 == null) {
            return false;
        }
        cancellableContinuation0.completeResume(object0);
        return true;
    }
}

