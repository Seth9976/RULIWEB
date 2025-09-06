package kotlinx.coroutines.selects;

import androidx.concurrent.futures.AbstractResolvableFuture.SafeAtomicHelper..ExternalSyntheticBackportWithForwarding0;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.Volatile;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancelHandler;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.internal.Segment;

@Metadata(d1 = {"\u0000\u0092\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0011\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u00032\b\u0012\u0004\u0012\u0002H\u00010\u0004:\u0001HB\r\u0012\u0006\u0010\u0005\u001A\u00020\u0006\u00A2\u0006\u0002\u0010\u0007J\u0010\u0010\u001A\u001A\u00020\u001B2\u0006\u0010\u001C\u001A\u00020\u000EH\u0002J\u001A\u0010\u001D\u001A\u00020\u001B2\u0010\u0010\u001E\u001A\f0\nR\b\u0012\u0004\u0012\u00028\u00000\u0000H\u0002J\u0011\u0010\u001F\u001A\u00028\u0000H\u0082@\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010 J\u0010\u0010!\u001A\u00020\u001B2\u0006\u0010\"\u001A\u00020#H\u0016J\u0011\u0010$\u001A\u00028\u0000H\u0091@\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010 J\u0011\u0010%\u001A\u00028\u0000H\u0082@\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010 J\u001C\u0010&\u001A\u000E\u0018\u00010\nR\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010\u001C\u001A\u00020\u000EH\u0002J\u0013\u0010\'\u001A\u00020\u001B2\b\u0010(\u001A\u0004\u0018\u00010)H\u0096\u0002J\u001C\u0010*\u001A\u00020\u001B2\n\u0010+\u001A\u0006\u0012\u0002\b\u00030,2\u0006\u0010-\u001A\u00020\u0014H\u0016J-\u0010.\u001A\u00028\u00002\u0010\u0010/\u001A\f0\nR\b\u0012\u0004\u0012\u00028\u00000\u00002\b\u0010\u0015\u001A\u0004\u0018\u00010\u000EH\u0082@\u00F8\u0001\u0000\u00A2\u0006\u0002\u00100J\u0010\u00101\u001A\u00020\u001B2\u0006\u0010\u001C\u001A\u00020\u000EH\u0002J\u0012\u00102\u001A\u00020\u001B2\b\u0010\u0015\u001A\u0004\u0018\u00010\u000EH\u0016J\u001A\u00103\u001A\u00020\u00102\u0006\u0010\u001C\u001A\u00020\u000E2\b\u00104\u001A\u0004\u0018\u00010\u000EH\u0016J\u0018\u00105\u001A\u0002062\u0006\u0010\u001C\u001A\u00020\u000E2\b\u00104\u001A\u0004\u0018\u00010\u000EJ\u001A\u00107\u001A\u00020\u00142\u0006\u0010\u001C\u001A\u00020\u000E2\b\u0010\u0015\u001A\u0004\u0018\u00010\u000EH\u0002J\u0011\u00108\u001A\u00020\u001BH\u0082@\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010 J3\u0010\'\u001A\u00020\u001B*\u0002092\u001C\u0010:\u001A\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000<\u0012\u0006\u0012\u0004\u0018\u00010\u000E0;H\u0096\u0002\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010=JE\u0010\'\u001A\u00020\u001B\"\u0004\b\u0001\u0010>*\b\u0012\u0004\u0012\u0002H>0?2\"\u0010:\u001A\u001E\b\u0001\u0012\u0004\u0012\u0002H>\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000<\u0012\u0006\u0012\u0004\u0018\u00010\u000E0@H\u0096\u0002\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010AJY\u0010\'\u001A\u00020\u001B\"\u0004\b\u0001\u0010B\"\u0004\b\u0002\u0010>*\u000E\u0012\u0004\u0012\u0002HB\u0012\u0004\u0012\u0002H>0C2\u0006\u0010D\u001A\u0002HB2\"\u0010:\u001A\u001E\b\u0001\u0012\u0004\u0012\u0002H>\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000<\u0012\u0006\u0012\u0004\u0018\u00010\u000E0@H\u0096\u0002\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010EJ \u0010F\u001A\u00020\u001B*\f0\nR\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0002\u0010G\u001A\u00020\u0010H\u0001R \u0010\b\u001A\u0014\u0012\u000E\u0012\f0\nR\b\u0012\u0004\u0012\u00028\u00000\u0000\u0018\u00010\tX\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001A\u00020\u0006X\u0096\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b\u000B\u0010\fR\u0010\u0010\r\u001A\u0004\u0018\u00010\u000EX\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u0014\u0010\u000F\u001A\u00020\u00108BX\u0082\u0004\u00A2\u0006\u0006\u001A\u0004\b\u0011\u0010\u0012R\u000E\u0010\u0013\u001A\u00020\u0014X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001A\u0004\u0018\u00010\u000EX\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u0014\u0010\u0016\u001A\u00020\u00108BX\u0082\u0004\u00A2\u0006\u0006\u001A\u0004\b\u0016\u0010\u0012R\u0014\u0010\u0017\u001A\u00020\u00108BX\u0082\u0004\u00A2\u0006\u0006\u001A\u0004\b\u0017\u0010\u0012R\u000F\u0010\u0018\u001A\b\u0012\u0004\u0012\u00020\u000E0\u0019X\u0082\u0004\u0082\u0002\u0004\n\u0002\b\u0019\u00A8\u0006I"}, d2 = {"Lkotlinx/coroutines/selects/SelectImplementation;", "R", "Lkotlinx/coroutines/CancelHandler;", "Lkotlinx/coroutines/selects/SelectBuilder;", "Lkotlinx/coroutines/selects/SelectInstanceInternal;", "context", "Lkotlin/coroutines/CoroutineContext;", "(Lkotlin/coroutines/CoroutineContext;)V", "clauses", "", "Lkotlinx/coroutines/selects/SelectImplementation$ClauseData;", "getContext", "()Lkotlin/coroutines/CoroutineContext;", "disposableHandleOrSegment", "", "inRegistrationPhase", "", "getInRegistrationPhase", "()Z", "indexInSegment", "", "internalResult", "isCancelled", "isSelected", "state", "Lkotlinx/atomicfu/AtomicRef;", "checkClauseObject", "", "clauseObject", "cleanup", "selectedClause", "complete", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "disposeOnCompletion", "disposableHandle", "Lkotlinx/coroutines/DisposableHandle;", "doSelect", "doSelectSuspend", "findClause", "invoke", "cause", "", "invokeOnCancellation", "segment", "Lkotlinx/coroutines/internal/Segment;", "index", "processResultAndInvokeBlockRecoveringException", "clause", "(Lkotlinx/coroutines/selects/SelectImplementation$ClauseData;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "reregisterClause", "selectInRegistrationPhase", "trySelect", "result", "trySelectDetailed", "Lkotlinx/coroutines/selects/TrySelectDetailedResult;", "trySelectInternal", "waitUntilSelected", "Lkotlinx/coroutines/selects/SelectClause0;", "block", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "(Lkotlinx/coroutines/selects/SelectClause0;Lkotlin/jvm/functions/Function1;)V", "Q", "Lkotlinx/coroutines/selects/SelectClause1;", "Lkotlin/Function2;", "(Lkotlinx/coroutines/selects/SelectClause1;Lkotlin/jvm/functions/Function2;)V", "P", "Lkotlinx/coroutines/selects/SelectClause2;", "param", "(Lkotlinx/coroutines/selects/SelectClause2;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)V", "register", "reregister", "ClauseData", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public class SelectImplementation extends CancelHandler implements SelectBuilder, SelectInstanceInternal {
    @Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000B\n\u0002\u0018\u0002\n\u0000\b\u0080\u0004\u0018\u00002\u00020\u0001B\u00B6\u0002\u0012\u0006\u0010\u0002\u001A\u00020\u0001\u0012U\u0010\u0003\u001AQ\u0012\u0013\u0012\u00110\u0001\u00A2\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0002\u0012\u0017\u0012\u0015\u0012\u0002\b\u00030\u0007\u00A2\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\b\u0012\u0015\u0012\u0013\u0018\u00010\u0001\u00A2\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n0\u0004j\u0002`\u000B\u0012U\u0010\f\u001AQ\u0012\u0013\u0012\u00110\u0001\u00A2\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0002\u0012\u0015\u0012\u0013\u0018\u00010\u0001\u00A2\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\t\u0012\u0015\u0012\u0013\u0018\u00010\u0001\u00A2\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\r\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0004j\u0002`\u000E\u0012\b\u0010\t\u001A\u0004\u0018\u00010\u0001\u0012\u0006\u0010\u000F\u001A\u00020\u0001\u0012g\u0010\u0010\u001Ac\u0012\u0017\u0012\u0015\u0012\u0002\b\u00030\u0007\u00A2\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\b\u0012\u0015\u0012\u0013\u0018\u00010\u0001\u00A2\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\t\u0012\u0015\u0012\u0013\u0018\u00010\u0001\u00A2\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0011\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\n0\u0012\u0018\u00010\u0004j\u0004\u0018\u0001`\u0014\u00A2\u0006\u0002\u0010\u0015J*\u0010\u0019\u001A\u0010\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\n\u0018\u00010\u00122\n\u0010\b\u001A\u0006\u0012\u0002\b\u00030\u00072\b\u0010\u0011\u001A\u0004\u0018\u00010\u0001J\u0006\u0010\u001A\u001A\u00020\nJ\u001B\u0010\u001B\u001A\u00028\u00002\b\u0010\u001C\u001A\u0004\u0018\u00010\u0001H\u0086@\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u001DJ\u0012\u0010\u001E\u001A\u0004\u0018\u00010\u00012\b\u0010\u001F\u001A\u0004\u0018\u00010\u0001J\u0014\u0010 \u001A\u00020!2\f\u0010\b\u001A\b\u0012\u0004\u0012\u00028\u00000\"R\u000E\u0010\u000F\u001A\u00020\u0001X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010\u0002\u001A\u00020\u00018\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0014\u0010\u0016\u001A\u0004\u0018\u00010\u00018\u0006@\u0006X\u0087\u000E\u00A2\u0006\u0002\n\u0000R\u0012\u0010\u0017\u001A\u00020\u00188\u0006@\u0006X\u0087\u000E\u00A2\u0006\u0002\n\u0000Rq\u0010\u0010\u001Ac\u0012\u0017\u0012\u0015\u0012\u0002\b\u00030\u0007\u00A2\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\b\u0012\u0015\u0012\u0013\u0018\u00010\u0001\u00A2\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\t\u0012\u0015\u0012\u0013\u0018\u00010\u0001\u00A2\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0011\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\n0\u0012\u0018\u00010\u0004j\u0004\u0018\u0001`\u00148\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010\t\u001A\u0004\u0018\u00010\u0001X\u0082\u0004\u00A2\u0006\u0002\n\u0000R]\u0010\f\u001AQ\u0012\u0013\u0012\u00110\u0001\u00A2\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0002\u0012\u0015\u0012\u0013\u0018\u00010\u0001\u00A2\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\t\u0012\u0015\u0012\u0013\u0018\u00010\u0001\u00A2\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\r\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0004j\u0002`\u000EX\u0082\u0004\u00A2\u0006\u0002\n\u0000R]\u0010\u0003\u001AQ\u0012\u0013\u0012\u00110\u0001\u00A2\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0002\u0012\u0017\u0012\u0015\u0012\u0002\b\u00030\u0007\u00A2\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\b\u0012\u0015\u0012\u0013\u0018\u00010\u0001\u00A2\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n0\u0004j\u0002`\u000BX\u0082\u0004\u00A2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00A8\u0006#"}, d2 = {"Lkotlinx/coroutines/selects/SelectImplementation$ClauseData;", "", "clauseObject", "regFunc", "Lkotlin/Function3;", "Lkotlin/ParameterName;", "name", "Lkotlinx/coroutines/selects/SelectInstance;", "select", "param", "", "Lkotlinx/coroutines/selects/RegistrationFunction;", "processResFunc", "clauseResult", "Lkotlinx/coroutines/selects/ProcessResultFunction;", "block", "onCancellationConstructor", "internalResult", "Lkotlin/Function1;", "", "Lkotlinx/coroutines/selects/OnCancellationConstructor;", "(Lkotlinx/coroutines/selects/SelectImplementation;Ljava/lang/Object;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function3;Ljava/lang/Object;Ljava/lang/Object;Lkotlin/jvm/functions/Function3;)V", "disposableHandleOrSegment", "indexInSegment", "", "createOnCancellationAction", "dispose", "invokeBlock", "argument", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "processResult", "result", "tryRegisterAsWaiter", "", "Lkotlinx/coroutines/selects/SelectImplementation;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public final class ClauseData {
        private final Object block;
        public final Object clauseObject;
        public Object disposableHandleOrSegment;
        public int indexInSegment;
        public final Function3 onCancellationConstructor;
        private final Object param;
        private final Function3 processResFunc;
        private final Function3 regFunc;

        public ClauseData(Object object0, Function3 function30, Function3 function31, Object object1, Object object2, Function3 function32) {
            this.clauseObject = object0;
            this.regFunc = function30;
            this.processResFunc = function31;
            this.param = object1;
            this.block = object2;
            this.onCancellationConstructor = function32;
            this.indexInSegment = -1;
        }

        public final Function1 createOnCancellationAction(SelectInstance selectInstance0, Object object0) {
            return this.onCancellationConstructor == null ? null : ((Function1)this.onCancellationConstructor.invoke(selectInstance0, this.param, object0));
        }

        public final void dispose() {
            Object object0 = this.disposableHandleOrSegment;
            SelectImplementation selectImplementation0 = SelectImplementation.this;
            DisposableHandle disposableHandle0 = null;
            if(object0 instanceof Segment) {
                ((Segment)object0).onCancellation(this.indexInSegment, null, selectImplementation0.getContext());
                return;
            }
            if(object0 instanceof DisposableHandle) {
                disposableHandle0 = (DisposableHandle)object0;
            }
            if(disposableHandle0 != null) {
                disposableHandle0.dispose();
            }
        }

        public final Object invokeBlock(Object object0, Continuation continuation0) {
            Object object1 = this.block;
            if(this.param == SelectKt.getPARAM_CLAUSE_0()) {
                Intrinsics.checkNotNull(object1, "null cannot be cast to non-null type kotlin.coroutines.SuspendFunction0<R of kotlinx.coroutines.selects.SelectImplementation>");
                return ((Function1)object1).invoke(continuation0);
            }
            Intrinsics.checkNotNull(object1, "null cannot be cast to non-null type kotlin.coroutines.SuspendFunction1<kotlin.Any?, R of kotlinx.coroutines.selects.SelectImplementation>");
            return ((Function2)object1).invoke(object0, continuation0);
        }

        public final Object processResult(Object object0) {
            return this.processResFunc.invoke(this.clauseObject, this.param, object0);
        }

        // 去混淆评级： 低(40)
        public final boolean tryRegisterAsWaiter(SelectImplementation selectImplementation0) {
            this.regFunc.invoke(this.clauseObject, selectImplementation0, this.param);
            return selectImplementation0.internalResult == SelectKt.NO_RESULT;
        }
    }

    private List clauses;
    private final CoroutineContext context;
    private Object disposableHandleOrSegment;
    private int indexInSegment;
    private Object internalResult;
    @Volatile
    private volatile Object state;
    private static final AtomicReferenceFieldUpdater state$FU;

    static {
        SelectImplementation.state$FU = AtomicReferenceFieldUpdater.newUpdater(SelectImplementation.class, Object.class, "state");
    }

    public SelectImplementation(CoroutineContext coroutineContext0) {
        this.context = coroutineContext0;
        this.state = SelectKt.access$getSTATE_REG$p();
        this.clauses = new ArrayList(2);
        this.indexInSegment = -1;
        this.internalResult = SelectKt.access$getNO_RESULT$p();
    }

    public static final Object access$complete(SelectImplementation selectImplementation0, Continuation continuation0) {
        return selectImplementation0.complete(continuation0);
    }

    public static final boolean access$getInRegistrationPhase(SelectImplementation selectImplementation0) {
        return selectImplementation0.getInRegistrationPhase();
    }

    public static final boolean access$isCancelled(SelectImplementation selectImplementation0) {
        return selectImplementation0.isCancelled();
    }

    public static final Object access$waitUntilSelected(SelectImplementation selectImplementation0, Continuation continuation0) {
        return selectImplementation0.waitUntilSelected(continuation0);
    }

    private final void checkClauseObject(Object object0) {
        List list0 = this.clauses;
        Intrinsics.checkNotNull(list0);
        if(list0 instanceof Collection && list0.isEmpty()) {
            return;
        }
        for(Object object1: list0) {
            if(((ClauseData)object1).clauseObject == object0) {
                throw new IllegalStateException(("Cannot use select clauses on the same object: " + object0).toString());
            }
            if(false) {
                break;
            }
        }
    }

    private final void cleanup(ClauseData selectImplementation$ClauseData0) {
        List list0 = this.clauses;
        if(list0 == null) {
            return;
        }
        for(Object object0: list0) {
            ClauseData selectImplementation$ClauseData1 = (ClauseData)object0;
            if(selectImplementation$ClauseData1 != selectImplementation$ClauseData0) {
                selectImplementation$ClauseData1.dispose();
            }
        }
        SelectImplementation.state$FU.set(this, SelectKt.access$getSTATE_COMPLETED$p());
        this.internalResult = SelectKt.access$getNO_RESULT$p();
        this.clauses = null;
    }

    private final Object complete(Continuation continuation0) {
        Object object0 = SelectImplementation.state$FU.get(this);
        Intrinsics.checkNotNull(object0, "null cannot be cast to non-null type kotlinx.coroutines.selects.SelectImplementation.ClauseData<R of kotlinx.coroutines.selects.SelectImplementation>");
        Object object1 = this.internalResult;
        this.cleanup(((ClauseData)object0));
        return ((ClauseData)object0).invokeBlock(((ClauseData)object0).processResult(object1), continuation0);
    }

    @Override  // kotlinx.coroutines.selects.SelectInstance
    public void disposeOnCompletion(DisposableHandle disposableHandle0) {
        this.disposableHandleOrSegment = disposableHandle0;
    }

    public Object doSelect(Continuation continuation0) {
        return SelectImplementation.doSelect$suspendImpl(this, continuation0);
    }

    // 去混淆评级： 低(20)
    static Object doSelect$suspendImpl(SelectImplementation selectImplementation0, Continuation continuation0) {
        return selectImplementation0.isSelected() ? selectImplementation0.complete(continuation0) : selectImplementation0.doSelectSuspend(continuation0);
    }

    private final Object doSelectSuspend(Continuation continuation0) {
        SelectImplementation selectImplementation0;
        kotlinx.coroutines.selects.SelectImplementation.doSelectSuspend.1 selectImplementation$doSelectSuspend$10;
        if(continuation0 instanceof kotlinx.coroutines.selects.SelectImplementation.doSelectSuspend.1) {
            selectImplementation$doSelectSuspend$10 = (kotlinx.coroutines.selects.SelectImplementation.doSelectSuspend.1)continuation0;
            if((selectImplementation$doSelectSuspend$10.label & 0x80000000) == 0) {
                selectImplementation$doSelectSuspend$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
                    Object L$0;
                    int label;
                    Object result;

                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object object0) {
                        this.result = object0;
                        this.label |= 0x80000000;
                        return continuation0.doSelectSuspend(this);
                    }
                };
            }
            else {
                selectImplementation$doSelectSuspend$10.label ^= 0x80000000;
            }
        }
        else {
            selectImplementation$doSelectSuspend$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
                Object L$0;
                int label;
                Object result;

                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object object0) {
                    this.result = object0;
                    this.label |= 0x80000000;
                    return continuation0.doSelectSuspend(this);
                }
            };
        }
        Object object0 = selectImplementation$doSelectSuspend$10.result;
        Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch(selectImplementation$doSelectSuspend$10.label) {
            case 0: {
                ResultKt.throwOnFailure(object0);
                selectImplementation$doSelectSuspend$10.L$0 = this;
                selectImplementation$doSelectSuspend$10.label = 1;
                if(this.waitUntilSelected(selectImplementation$doSelectSuspend$10) != object1) {
                    selectImplementation0 = this;
                    break;
                }
                return object1;
            }
            case 1: {
                selectImplementation0 = (SelectImplementation)selectImplementation$doSelectSuspend$10.L$0;
                ResultKt.throwOnFailure(object0);
                break;
            }
            case 2: {
                ResultKt.throwOnFailure(object0);
                return object0;
            }
            default: {
                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
            }
        }
        selectImplementation$doSelectSuspend$10.L$0 = null;
        selectImplementation$doSelectSuspend$10.label = 2;
        Object object2 = selectImplementation0.complete(selectImplementation$doSelectSuspend$10);
        return object2 == object1 ? object1 : object2;
    }

    private final ClauseData findClause(Object object0) {
        List list0 = this.clauses;
        Object object1 = null;
        if(list0 == null) {
            return null;
        }
        for(Object object2: list0) {
            if(((ClauseData)object2).clauseObject == object0) {
                object1 = object2;
                break;
            }
            if(false) {
                break;
            }
        }
        if(((ClauseData)object1) == null) {
            throw new IllegalStateException(("Clause with object " + object0 + " is not found").toString());
        }
        return (ClauseData)object1;
    }

    @Override  // kotlinx.coroutines.selects.SelectInstance
    public CoroutineContext getContext() {
        return this.context;
    }

    private final boolean getInRegistrationPhase() {
        Object object0 = SelectImplementation.state$FU.get(this);
        return object0 == SelectKt.access$getSTATE_REG$p() || object0 instanceof List;
    }

    @Override  // kotlin.jvm.functions.Function1
    public Object invoke(Object object0) {
        this.invoke(((Throwable)object0));
        return Unit.INSTANCE;
    }

    @Override  // kotlinx.coroutines.CancelHandlerBase
    public void invoke(Throwable throwable0) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater0 = SelectImplementation.state$FU;
        Object object0;
        while((object0 = atomicReferenceFieldUpdater0.get(this)) != SelectKt.STATE_COMPLETED) {
            if(AbstractResolvableFuture.SafeAtomicHelper..ExternalSyntheticBackportWithForwarding0.m(atomicReferenceFieldUpdater0, this, object0, SelectKt.STATE_CANCELLED)) {
                List list0 = this.clauses;
                if(list0 == null) {
                    break;
                }
                for(Object object1: list0) {
                    ((ClauseData)object1).dispose();
                }
                this.internalResult = SelectKt.NO_RESULT;
                this.clauses = null;
                return;
            }
        }
    }

    @Override  // kotlinx.coroutines.selects.SelectBuilder
    public void invoke(SelectClause0 selectClause00, Function1 function10) {
        SelectImplementation.register$default(this, new ClauseData(this, selectClause00.getClauseObject(), selectClause00.getRegFunc(), selectClause00.getProcessResFunc(), SelectKt.getPARAM_CLAUSE_0(), function10, selectClause00.getOnCancellationConstructor()), false, 1, null);
    }

    @Override  // kotlinx.coroutines.selects.SelectBuilder
    public void invoke(SelectClause1 selectClause10, Function2 function20) {
        SelectImplementation.register$default(this, new ClauseData(this, selectClause10.getClauseObject(), selectClause10.getRegFunc(), selectClause10.getProcessResFunc(), null, function20, selectClause10.getOnCancellationConstructor()), false, 1, null);
    }

    @Override  // kotlinx.coroutines.selects.SelectBuilder
    public void invoke(SelectClause2 selectClause20, Object object0, Function2 function20) {
        SelectImplementation.register$default(this, new ClauseData(this, selectClause20.getClauseObject(), selectClause20.getRegFunc(), selectClause20.getProcessResFunc(), object0, function20, selectClause20.getOnCancellationConstructor()), false, 1, null);
    }

    @Override  // kotlinx.coroutines.selects.SelectBuilder
    public void invoke(SelectClause2 selectClause20, Function2 function20) {
        DefaultImpls.invoke(this, selectClause20, function20);
    }

    @Override  // kotlinx.coroutines.Waiter
    public void invokeOnCancellation(Segment segment0, int v) {
        this.disposableHandleOrSegment = segment0;
        this.indexInSegment = v;
    }

    private final boolean isCancelled() {
        return SelectImplementation.state$FU.get(this) == SelectKt.access$getSTATE_CANCELLED$p();
    }

    private final boolean isSelected() {
        return SelectImplementation.state$FU.get(this) instanceof ClauseData;
    }

    private final void loop$atomicfu(AtomicReferenceFieldUpdater atomicReferenceFieldUpdater0, Function1 function10, Object object0) {
        while(true) {
            function10.invoke(atomicReferenceFieldUpdater0.get(object0));
        }
    }

    @Override  // kotlinx.coroutines.selects.SelectBuilder
    @Deprecated(level = DeprecationLevel.ERROR, message = "Replaced with the same extension function", replaceWith = @ReplaceWith(expression = "onTimeout", imports = {"kotlinx.coroutines.selects.onTimeout"}))
    public void onTimeout(long v, Function1 function10) {
        DefaultImpls.onTimeout(this, v, function10);
    }

    private final Object processResultAndInvokeBlockRecoveringException(ClauseData selectImplementation$ClauseData0, Object object0, Continuation continuation0) {
        kotlinx.coroutines.selects.SelectImplementation.processResultAndInvokeBlockRecoveringException.1 selectImplementation$processResultAndInvokeBlockRecoveringException$10;
        if(continuation0 instanceof kotlinx.coroutines.selects.SelectImplementation.processResultAndInvokeBlockRecoveringException.1) {
            selectImplementation$processResultAndInvokeBlockRecoveringException$10 = (kotlinx.coroutines.selects.SelectImplementation.processResultAndInvokeBlockRecoveringException.1)continuation0;
            if((selectImplementation$processResultAndInvokeBlockRecoveringException$10.label & 0x80000000) == 0) {
                selectImplementation$processResultAndInvokeBlockRecoveringException$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
                    int label;
                    Object result;

                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object object0) {
                        this.result = object0;
                        this.label |= 0x80000000;
                        return continuation0.processResultAndInvokeBlockRecoveringException(null, null, this);
                    }
                };
            }
            else {
                selectImplementation$processResultAndInvokeBlockRecoveringException$10.label ^= 0x80000000;
            }
        }
        else {
            selectImplementation$processResultAndInvokeBlockRecoveringException$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
                int label;
                Object result;

                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object object0) {
                    this.result = object0;
                    this.label |= 0x80000000;
                    return continuation0.processResultAndInvokeBlockRecoveringException(null, null, this);
                }
            };
        }
        Object object1 = selectImplementation$processResultAndInvokeBlockRecoveringException$10.result;
        Object object2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch(selectImplementation$processResultAndInvokeBlockRecoveringException$10.label) {
            case 0: {
                ResultKt.throwOnFailure(object1);
                Object object3 = selectImplementation$ClauseData0.processResult(object0);
                selectImplementation$processResultAndInvokeBlockRecoveringException$10.label = 1;
                Object object4 = selectImplementation$ClauseData0.invokeBlock(object3, selectImplementation$processResultAndInvokeBlockRecoveringException$10);
                return object4 == object2 ? object2 : object4;
            }
            case 1: {
                ResultKt.throwOnFailure(object1);
                return object1;
            }
            default: {
                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
            }
        }
    }

    public final void register(ClauseData selectImplementation$ClauseData0, boolean z) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater0 = SelectImplementation.state$FU;
        if(atomicReferenceFieldUpdater0.get(this) instanceof ClauseData) {
            return;
        }
        if(!z) {
            this.checkClauseObject(selectImplementation$ClauseData0.clauseObject);
        }
        if(selectImplementation$ClauseData0.tryRegisterAsWaiter(this)) {
            if(!z) {
                List list0 = this.clauses;
                Intrinsics.checkNotNull(list0);
                list0.add(selectImplementation$ClauseData0);
            }
            selectImplementation$ClauseData0.disposableHandleOrSegment = this.disposableHandleOrSegment;
            selectImplementation$ClauseData0.indexInSegment = this.indexInSegment;
            this.disposableHandleOrSegment = null;
            this.indexInSegment = -1;
            return;
        }
        atomicReferenceFieldUpdater0.set(this, selectImplementation$ClauseData0);
    }

    public static void register$default(SelectImplementation selectImplementation0, ClauseData selectImplementation$ClauseData0, boolean z, int v, Object object0) {
        if(object0 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: register");
        }
        if((v & 1) != 0) {
            z = false;
        }
        selectImplementation0.register(selectImplementation$ClauseData0, z);
    }

    private final void reregisterClause(Object object0) {
        ClauseData selectImplementation$ClauseData0 = this.findClause(object0);
        Intrinsics.checkNotNull(selectImplementation$ClauseData0);
        selectImplementation$ClauseData0.disposableHandleOrSegment = null;
        selectImplementation$ClauseData0.indexInSegment = -1;
        this.register(selectImplementation$ClauseData0, true);
    }

    @Override  // kotlinx.coroutines.selects.SelectInstance
    public void selectInRegistrationPhase(Object object0) {
        this.internalResult = object0;
    }

    @Override  // kotlinx.coroutines.selects.SelectInstance
    public boolean trySelect(Object object0, Object object1) {
        return this.trySelectInternal(object0, object1) == 0;
    }

    public final TrySelectDetailedResult trySelectDetailed(Object object0, Object object1) {
        return SelectKt.TrySelectDetailedResult(this.trySelectInternal(object0, object1));
    }

    private final int trySelectInternal(Object object0, Object object1) {
        Object object2;
        while(true) {
            while(true) {
            alab1:
                while(true) {
                    do {
                        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater0 = SelectImplementation.state$FU;
                        object2 = atomicReferenceFieldUpdater0.get(this);
                        if(!(object2 instanceof CancellableContinuation)) {
                            break alab1;
                        }
                        ClauseData selectImplementation$ClauseData0 = this.findClause(object0);
                        if(selectImplementation$ClauseData0 == null) {
                            continue alab1;
                        }
                        Function1 function10 = selectImplementation$ClauseData0.createOnCancellationAction(this, object1);
                    }
                    while(!AbstractResolvableFuture.SafeAtomicHelper..ExternalSyntheticBackportWithForwarding0.m(atomicReferenceFieldUpdater0, this, object2, selectImplementation$ClauseData0));
                    this.internalResult = object1;
                    if(SelectKt.tryResume(((CancellableContinuation)object2), function10)) {
                        return 0;
                    }
                    this.internalResult = null;
                    return 2;
                }
                if((Intrinsics.areEqual(object2, SelectKt.STATE_COMPLETED) ? true : object2 instanceof ClauseData)) {
                    return 3;
                }
                if(Intrinsics.areEqual(object2, SelectKt.STATE_CANCELLED)) {
                    return 2;
                }
                if(!Intrinsics.areEqual(object2, SelectKt.STATE_REG)) {
                    break;
                }
                if(AbstractResolvableFuture.SafeAtomicHelper..ExternalSyntheticBackportWithForwarding0.m(atomicReferenceFieldUpdater0, this, object2, CollectionsKt.listOf(object0))) {
                    return 1;
                }
            }
            if(!(object2 instanceof List)) {
                break;
            }
            if(AbstractResolvableFuture.SafeAtomicHelper..ExternalSyntheticBackportWithForwarding0.m(atomicReferenceFieldUpdater0, this, object2, CollectionsKt.plus(((Collection)object2), object0))) {
                return 1;
            }
        }
        throw new IllegalStateException(("Unexpected state: " + object2).toString());
    }

    private final void update$atomicfu(AtomicReferenceFieldUpdater atomicReferenceFieldUpdater0, Function1 function10, Object object0) {
        do {
            Object object1 = atomicReferenceFieldUpdater0.get(object0);
        }
        while(!AbstractResolvableFuture.SafeAtomicHelper..ExternalSyntheticBackportWithForwarding0.m(atomicReferenceFieldUpdater0, object0, object1, function10.invoke(object1)));
    }

    private final Object waitUntilSelected(Continuation continuation0) {
        Iterator iterator0;
        Object object0;
        CancellableContinuationImpl cancellableContinuationImpl0 = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation0), 1);
        cancellableContinuationImpl0.initCancellability();
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater0 = SelectImplementation.state$FU;
        do {
            do {
                do {
                    object0 = atomicReferenceFieldUpdater0.get(this);
                    if(object0 != SelectKt.access$getSTATE_REG$p()) {
                        goto label_8;
                    }
                }
                while(!AbstractResolvableFuture.SafeAtomicHelper..ExternalSyntheticBackportWithForwarding0.m(SelectImplementation.state$FU, this, object0, cancellableContinuationImpl0));
                cancellableContinuationImpl0.invokeOnCancellation(this);
                goto label_19;
            label_8:
                if(!(object0 instanceof List)) {
                    goto label_16;
                }
            }
            while(!AbstractResolvableFuture.SafeAtomicHelper..ExternalSyntheticBackportWithForwarding0.m(SelectImplementation.state$FU, this, object0, SelectKt.access$getSTATE_REG$p()));
            List list0 = (List)object0;
            iterator0 = ((Iterable)object0).iterator();
        label_12:
        }
        while(!iterator0.hasNext());
        Object object1 = iterator0.next();
        this.reregisterClause(object1);
        goto label_12;
    label_16:
        if(!(object0 instanceof ClauseData)) {
            throw new IllegalStateException(("unexpected state: " + object0).toString());
        }
        Function1 function10 = ((ClauseData)object0).createOnCancellationAction(this, this.internalResult);
        cancellableContinuationImpl0.resume(Unit.INSTANCE, function10);
    label_19:
        Object object2 = cancellableContinuationImpl0.getResult();
        if(object2 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation0);
        }
        return object2 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? object2 : Unit.INSTANCE;
    }
}

