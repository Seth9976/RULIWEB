package kotlinx.coroutines;

import java.util.concurrent.CancellationException;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.coroutines.AbstractCoroutineContextElement;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import kotlinx.coroutines.selects.SelectClause0;

@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000B\n\u0002\b\u0007\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u00C6\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u0007\b\u0002\u00A2\u0006\u0002\u0010\u0003J\u0010\u0010\u001C\u001A\u00020\u001D2\u0006\u0010\u001E\u001A\u00020\u001FH\u0017J\u0012\u0010 \u001A\u00020\n2\b\u0010!\u001A\u0004\u0018\u00010\"H\u0017J\u0018\u0010 \u001A\u00020#2\u000E\u0010!\u001A\n\u0018\u00010$j\u0004\u0018\u0001`%H\u0017J\f\u0010&\u001A\u00060$j\u0002`%H\u0017JA\u0010\'\u001A\u00020(2\u0006\u0010)\u001A\u00020\n2\u0006\u0010*\u001A\u00020\n2\'\u0010+\u001A#\u0012\u0015\u0012\u0013\u0018\u00010\"\u00A2\u0006\f\b-\u0012\b\b.\u0012\u0004\b\b(!\u0012\u0004\u0012\u00020#0,j\u0002`/H\u0017J1\u0010\'\u001A\u00020(2\'\u0010+\u001A#\u0012\u0015\u0012\u0013\u0018\u00010\"\u00A2\u0006\f\b-\u0012\b\b.\u0012\u0004\b\b(!\u0012\u0004\u0012\u00020#0,j\u0002`/H\u0017J\u0011\u00100\u001A\u00020#H\u0097@\u00F8\u0001\u0000\u00A2\u0006\u0002\u00101J\b\u00102\u001A\u00020\nH\u0017J\b\u00103\u001A\u00020\u0012H\u0016R \u0010\u0004\u001A\b\u0012\u0004\u0012\u00020\u00020\u00058VX\u0097\u0004\u00A2\u0006\f\u0012\u0004\b\u0006\u0010\u0003\u001A\u0004\b\u0007\u0010\bR\u001A\u0010\t\u001A\u00020\n8VX\u0097\u0004\u00A2\u0006\f\u0012\u0004\b\u000B\u0010\u0003\u001A\u0004\b\t\u0010\fR\u001A\u0010\r\u001A\u00020\n8VX\u0097\u0004\u00A2\u0006\f\u0012\u0004\b\u000E\u0010\u0003\u001A\u0004\b\r\u0010\fR\u001A\u0010\u000F\u001A\u00020\n8VX\u0097\u0004\u00A2\u0006\f\u0012\u0004\b\u0010\u0010\u0003\u001A\u0004\b\u000F\u0010\fR\u000E\u0010\u0011\u001A\u00020\u0012X\u0082T\u00A2\u0006\u0002\n\u0000R\u001A\u0010\u0013\u001A\u00020\u00148VX\u0097\u0004\u00A2\u0006\f\u0012\u0004\b\u0015\u0010\u0003\u001A\u0004\b\u0016\u0010\u0017R\u001C\u0010\u0018\u001A\u0004\u0018\u00010\u00028VX\u0097\u0004\u00A2\u0006\f\u0012\u0004\b\u0019\u0010\u0003\u001A\u0004\b\u001A\u0010\u001B\u0082\u0002\u0004\n\u0002\b\u0019\u00A8\u00064"}, d2 = {"Lkotlinx/coroutines/NonCancellable;", "Lkotlin/coroutines/AbstractCoroutineContextElement;", "Lkotlinx/coroutines/Job;", "()V", "children", "Lkotlin/sequences/Sequence;", "getChildren$annotations", "getChildren", "()Lkotlin/sequences/Sequence;", "isActive", "", "isActive$annotations", "()Z", "isCancelled", "isCancelled$annotations", "isCompleted", "isCompleted$annotations", "message", "", "onJoin", "Lkotlinx/coroutines/selects/SelectClause0;", "getOnJoin$annotations", "getOnJoin", "()Lkotlinx/coroutines/selects/SelectClause0;", "parent", "getParent$annotations", "getParent", "()Lkotlinx/coroutines/Job;", "attachChild", "Lkotlinx/coroutines/ChildHandle;", "child", "Lkotlinx/coroutines/ChildJob;", "cancel", "cause", "", "", "Ljava/util/concurrent/CancellationException;", "Lkotlinx/coroutines/CancellationException;", "getCancellationException", "invokeOnCompletion", "Lkotlinx/coroutines/DisposableHandle;", "onCancelling", "invokeImmediately", "handler", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "Lkotlinx/coroutines/CompletionHandler;", "join", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "start", "toString", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class NonCancellable extends AbstractCoroutineContextElement implements Job {
    public static final NonCancellable INSTANCE = null;
    private static final String message = "NonCancellable can be used only as an argument for \'withContext\', direct usages of its API are prohibited";

    static {
        NonCancellable.INSTANCE = new NonCancellable();
    }

    private NonCancellable() {
        super(Job.Key);
    }

    @Override  // kotlinx.coroutines.Job
    @Deprecated(level = DeprecationLevel.WARNING, message = "NonCancellable can be used only as an argument for \'withContext\', direct usages of its API are prohibited")
    public ChildHandle attachChild(ChildJob childJob0) {
        return NonDisposableHandle.INSTANCE;
    }

    @Override  // kotlinx.coroutines.Job
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Since 1.2.0, binary compatibility with versions <= 1.1.x")
    public void cancel() {
        DefaultImpls.cancel(this);
    }

    @Override  // kotlinx.coroutines.Job
    @Deprecated(level = DeprecationLevel.WARNING, message = "NonCancellable can be used only as an argument for \'withContext\', direct usages of its API are prohibited")
    public void cancel(CancellationException cancellationException0) {
    }

    @Override  // kotlinx.coroutines.Job
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Since 1.2.0, binary compatibility with versions <= 1.1.x")
    public boolean cancel(Throwable throwable0) {
        return false;
    }

    @Override  // kotlinx.coroutines.Job
    @Deprecated(level = DeprecationLevel.WARNING, message = "NonCancellable can be used only as an argument for \'withContext\', direct usages of its API are prohibited")
    public CancellationException getCancellationException() {
        throw new IllegalStateException("This job is always active");
    }

    @Override  // kotlinx.coroutines.Job
    public Sequence getChildren() {
        return SequencesKt.emptySequence();
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "NonCancellable can be used only as an argument for \'withContext\', direct usages of its API are prohibited")
    public static void getChildren$annotations() {
    }

    @Override  // kotlinx.coroutines.Job
    public SelectClause0 getOnJoin() {
        throw new UnsupportedOperationException("This job is always active");
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "NonCancellable can be used only as an argument for \'withContext\', direct usages of its API are prohibited")
    public static void getOnJoin$annotations() {
    }

    @Override  // kotlinx.coroutines.Job
    public Job getParent() {
        return null;
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "NonCancellable can be used only as an argument for \'withContext\', direct usages of its API are prohibited")
    public static void getParent$annotations() {
    }

    @Override  // kotlinx.coroutines.Job
    @Deprecated(level = DeprecationLevel.WARNING, message = "NonCancellable can be used only as an argument for \'withContext\', direct usages of its API are prohibited")
    public DisposableHandle invokeOnCompletion(Function1 function10) {
        return NonDisposableHandle.INSTANCE;
    }

    @Override  // kotlinx.coroutines.Job
    @Deprecated(level = DeprecationLevel.WARNING, message = "NonCancellable can be used only as an argument for \'withContext\', direct usages of its API are prohibited")
    public DisposableHandle invokeOnCompletion(boolean z, boolean z1, Function1 function10) {
        return NonDisposableHandle.INSTANCE;
    }

    @Override  // kotlinx.coroutines.Job
    public boolean isActive() {
        return true;
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "NonCancellable can be used only as an argument for \'withContext\', direct usages of its API are prohibited")
    public static void isActive$annotations() {
    }

    @Override  // kotlinx.coroutines.Job
    public boolean isCancelled() {
        return false;
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "NonCancellable can be used only as an argument for \'withContext\', direct usages of its API are prohibited")
    public static void isCancelled$annotations() {
    }

    @Override  // kotlinx.coroutines.Job
    public boolean isCompleted() {
        return false;
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "NonCancellable can be used only as an argument for \'withContext\', direct usages of its API are prohibited")
    public static void isCompleted$annotations() {
    }

    @Override  // kotlinx.coroutines.Job
    @Deprecated(level = DeprecationLevel.WARNING, message = "NonCancellable can be used only as an argument for \'withContext\', direct usages of its API are prohibited")
    public Object join(Continuation continuation0) {
        throw new UnsupportedOperationException("This job is always active");
    }

    @Override  // kotlinx.coroutines.Job
    @Deprecated(level = DeprecationLevel.ERROR, message = "Operator \'+\' on two Job objects is meaningless. Job is a coroutine context element and `+` is a set-sum operator for coroutine contexts. The job to the right of `+` just replaces the job the left of `+`.")
    public Job plus(Job job0) {
        return job0;
    }

    @Override  // kotlinx.coroutines.Job
    @Deprecated(level = DeprecationLevel.WARNING, message = "NonCancellable can be used only as an argument for \'withContext\', direct usages of its API are prohibited")
    public boolean start() {
        return false;
    }

    @Override
    public String toString() {
        return "NonCancellable";
    }
}

