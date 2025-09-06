package kotlinx.coroutines;

import androidx.concurrent.futures.AbstractResolvableFuture.SafeAtomicHelper..ExternalSyntheticBackportWithForwarding0;
import java.util.ArrayList;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext.Element;
import kotlin.coroutines.CoroutineContext.Key;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.Volatile;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref.ObjectRef;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequenceScope;
import kotlin.sequences.SequencesKt;
import kotlinx.coroutines.internal.LockFreeLinkedListHead;
import kotlinx.coroutines.internal.LockFreeLinkedListKt;
import kotlinx.coroutines.internal.LockFreeLinkedListNode.CondAddOp;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import kotlinx.coroutines.internal.OpDescriptor;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.selects.SelectClause0;
import kotlinx.coroutines.selects.SelectClause0Impl;
import kotlinx.coroutines.selects.SelectClause1;
import kotlinx.coroutines.selects.SelectClause1Impl;
import kotlinx.coroutines.selects.SelectInstance;

@Deprecated(level = DeprecationLevel.ERROR, message = "This is internal API and may be removed in the future releases")
@Metadata(d1 = {"\u0000\u00E2\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000E\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0001\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\b\b\u0017\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003:\n\u00B2\u0001\u00B3\u0001\u00B4\u0001\u00B5\u0001\u00B6\u0001B\r\u0012\u0006\u0010\u0004\u001A\u00020\u0005\u00A2\u0006\u0002\u0010\u0006J \u0010A\u001A\u00020\u00052\u0006\u0010B\u001A\u00020\u000B2\u0006\u0010C\u001A\u00020D2\u0006\u0010E\u001A\u00020FH\u0002J\u001E\u0010G\u001A\u00020H2\u0006\u0010I\u001A\u00020\u00112\f\u0010J\u001A\b\u0012\u0004\u0012\u00020\u00110KH\u0002J\u0012\u0010L\u001A\u00020H2\b\u00108\u001A\u0004\u0018\u00010\u000BH\u0014J\u000E\u0010M\u001A\u00020\t2\u0006\u0010N\u001A\u00020\u0002J\u0013\u0010O\u001A\u0004\u0018\u00010\u000BH\u0084@\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010PJ\u0013\u0010Q\u001A\u0004\u0018\u00010\u000BH\u0082@\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010PJ\u0012\u0010R\u001A\u00020\u00052\b\u0010S\u001A\u0004\u0018\u00010\u0011H\u0017J\u0018\u0010R\u001A\u00020H2\u000E\u0010S\u001A\n\u0018\u00010Tj\u0004\u0018\u0001`UH\u0016J\u0010\u0010V\u001A\u00020\u00052\b\u0010S\u001A\u0004\u0018\u00010\u0011J\u0017\u0010W\u001A\u00020\u00052\b\u0010S\u001A\u0004\u0018\u00010\u000BH\u0000\u00A2\u0006\u0002\bXJ\u0010\u0010Y\u001A\u00020H2\u0006\u0010S\u001A\u00020\u0011H\u0016J\u0014\u0010Z\u001A\u0004\u0018\u00010\u000B2\b\u0010S\u001A\u0004\u0018\u00010\u000BH\u0002J\u0010\u0010[\u001A\u00020\u00052\u0006\u0010S\u001A\u00020\u0011H\u0002J\b\u0010\\\u001A\u00020]H\u0014J\u0010\u0010^\u001A\u00020\u00052\u0006\u0010S\u001A\u00020\u0011H\u0016J\u001A\u0010_\u001A\u00020H2\u0006\u00108\u001A\u00020?2\b\u0010`\u001A\u0004\u0018\u00010\u000BH\u0002J\"\u0010a\u001A\u00020H2\u0006\u00108\u001A\u00020b2\u0006\u0010c\u001A\u00020d2\b\u0010e\u001A\u0004\u0018\u00010\u000BH\u0002J\u0012\u0010f\u001A\u00020\u00112\b\u0010S\u001A\u0004\u0018\u00010\u000BH\u0002J&\u0010g\u001A\u00020h2\n\b\u0002\u0010i\u001A\u0004\u0018\u00010]2\n\b\u0002\u0010S\u001A\u0004\u0018\u00010\u0011H\u0080\b\u00A2\u0006\u0002\bjJ\u001C\u0010k\u001A\u0004\u0018\u00010\u000B2\u0006\u00108\u001A\u00020b2\b\u0010e\u001A\u0004\u0018\u00010\u000BH\u0002J\u0012\u0010l\u001A\u0004\u0018\u00010d2\u0006\u00108\u001A\u00020?H\u0002J\n\u0010m\u001A\u00060Tj\u0002`UJ\f\u0010n\u001A\u00060Tj\u0002`UH\u0016J\u000F\u0010o\u001A\u0004\u0018\u00010\u000BH\u0000\u00A2\u0006\u0002\bpJ\b\u0010q\u001A\u0004\u0018\u00010\u0011J \u0010r\u001A\u0004\u0018\u00010\u00112\u0006\u00108\u001A\u00020b2\f\u0010J\u001A\b\u0012\u0004\u0012\u00020\u00110KH\u0002J\u0012\u0010s\u001A\u0004\u0018\u00010D2\u0006\u00108\u001A\u00020?H\u0002J\u0010\u0010t\u001A\u00020\u00052\u0006\u0010u\u001A\u00020\u0011H\u0014J\u0015\u0010v\u001A\u00020H2\u0006\u0010u\u001A\u00020\u0011H\u0010\u00A2\u0006\u0002\bwJ\u0012\u0010x\u001A\u00020H2\b\u0010/\u001A\u0004\u0018\u00010\u0001H\u0004JA\u0010y\u001A\u00020z2\u0006\u0010{\u001A\u00020\u00052\u0006\u0010|\u001A\u00020\u00052)\u0010}\u001A%\u0012\u0016\u0012\u0014\u0018\u00010\u0011\u00A2\u0006\r\b\u007F\u0012\t\b\u0080\u0001\u0012\u0004\b\b(S\u0012\u0004\u0012\u00020H0~j\u0003`\u0081\u0001J1\u0010y\u001A\u00020z2)\u0010}\u001A%\u0012\u0016\u0012\u0014\u0018\u00010\u0011\u00A2\u0006\r\b\u007F\u0012\t\b\u0080\u0001\u0012\u0004\b\b(S\u0012\u0004\u0012\u00020H0~j\u0003`\u0081\u0001J\u0012\u0010\u0082\u0001\u001A\u00020HH\u0086@\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010PJ\t\u0010\u0083\u0001\u001A\u00020\u0005H\u0002J\u0012\u0010\u0084\u0001\u001A\u00020HH\u0082@\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010PJ\"\u0010\u0085\u0001\u001A\u00030\u0086\u00012\u0015\u0010\u0087\u0001\u001A\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u000B\u0012\u0004\u0012\u00020H0~H\u0082\bJ\u0015\u0010\u0088\u0001\u001A\u0004\u0018\u00010\u000B2\b\u0010S\u001A\u0004\u0018\u00010\u000BH\u0002J\u0019\u0010\u0089\u0001\u001A\u00020\u00052\b\u0010e\u001A\u0004\u0018\u00010\u000BH\u0000\u00A2\u0006\u0003\b\u008A\u0001J\u001B\u0010\u008B\u0001\u001A\u0004\u0018\u00010\u000B2\b\u0010e\u001A\u0004\u0018\u00010\u000BH\u0000\u00A2\u0006\u0003\b\u008C\u0001J<\u0010\u008D\u0001\u001A\u00020F2)\u0010}\u001A%\u0012\u0016\u0012\u0014\u0018\u00010\u0011\u00A2\u0006\r\b\u007F\u0012\t\b\u0080\u0001\u0012\u0004\b\b(S\u0012\u0004\u0012\u00020H0~j\u0003`\u0081\u00012\u0006\u0010{\u001A\u00020\u0005H\u0002J\u000F\u0010\u008E\u0001\u001A\u00020]H\u0010\u00A2\u0006\u0003\b\u008F\u0001J\u0019\u0010\u0090\u0001\u001A\u00020H2\u0006\u0010C\u001A\u00020D2\u0006\u0010S\u001A\u00020\u0011H\u0002J)\u0010\u0091\u0001\u001A\u00020H\"\u000B\b\u0000\u0010\u0092\u0001\u0018\u0001*\u00020F2\u0006\u0010C\u001A\u00020D2\b\u0010S\u001A\u0004\u0018\u00010\u0011H\u0082\bJ!\u0010\u0093\u0001\u001A\u0004\u0018\u00010\u000B2\t\u0010\u0094\u0001\u001A\u0004\u0018\u00010\u000B2\t\u0010\u0095\u0001\u001A\u0004\u0018\u00010\u000BH\u0002J\"\u0010\u0096\u0001\u001A\u00020H2\f\u0010\u0097\u0001\u001A\u0007\u0012\u0002\b\u00030\u0098\u00012\t\u0010\u0094\u0001\u001A\u0004\u0018\u00010\u000BH\u0002J\u0012\u0010{\u001A\u00020H2\b\u0010S\u001A\u0004\u0018\u00010\u0011H\u0014J\u0013\u0010\u0099\u0001\u001A\u00020H2\b\u00108\u001A\u0004\u0018\u00010\u000BH\u0014J\t\u0010\u009A\u0001\u001A\u00020HH\u0014J\u0010\u0010\u009B\u0001\u001A\u00020H2\u0007\u0010\u009C\u0001\u001A\u00020\u0003J\u0012\u0010\u009D\u0001\u001A\u00020H2\u0007\u00108\u001A\u00030\u009E\u0001H\u0002J\u0011\u0010\u009F\u0001\u001A\u00020H2\u0006\u00108\u001A\u00020FH\u0002J\"\u0010\u00A0\u0001\u001A\u00020H2\f\u0010\u0097\u0001\u001A\u0007\u0012\u0002\b\u00030\u0098\u00012\t\u0010\u0094\u0001\u001A\u0004\u0018\u00010\u000BH\u0002J\u0017\u0010\u00A1\u0001\u001A\u00020H2\u0006\u0010E\u001A\u00020FH\u0000\u00A2\u0006\u0003\b\u00A2\u0001J\u0007\u0010\u00A3\u0001\u001A\u00020\u0005J\u0014\u0010\u00A4\u0001\u001A\u00030\u00A5\u00012\b\u00108\u001A\u0004\u0018\u00010\u000BH\u0002J\u0013\u0010\u00A6\u0001\u001A\u00020]2\b\u00108\u001A\u0004\u0018\u00010\u000BH\u0002J\t\u0010\u00A7\u0001\u001A\u00020]H\u0007J\t\u0010\u00A8\u0001\u001A\u00020]H\u0016J\u001B\u0010\u00A9\u0001\u001A\u00020\u00052\u0006\u00108\u001A\u00020?2\b\u0010`\u001A\u0004\u0018\u00010\u000BH\u0002J\u0019\u0010\u00AA\u0001\u001A\u00020\u00052\u0006\u00108\u001A\u00020?2\u0006\u0010I\u001A\u00020\u0011H\u0002J\u001F\u0010\u00AB\u0001\u001A\u0004\u0018\u00010\u000B2\b\u00108\u001A\u0004\u0018\u00010\u000B2\b\u0010e\u001A\u0004\u0018\u00010\u000BH\u0002J\u001D\u0010\u00AC\u0001\u001A\u0004\u0018\u00010\u000B2\u0006\u00108\u001A\u00020?2\b\u0010e\u001A\u0004\u0018\u00010\u000BH\u0002J$\u0010\u00AD\u0001\u001A\u00020\u00052\u0006\u00108\u001A\u00020b2\u0006\u0010N\u001A\u00020d2\b\u0010e\u001A\u0004\u0018\u00010\u000BH\u0082\u0010J\u0010\u0010\u00AE\u0001\u001A\u0004\u0018\u00010d*\u00030\u00AF\u0001H\u0002J\u0017\u0010\u00B0\u0001\u001A\u00020H*\u00020D2\b\u0010S\u001A\u0004\u0018\u00010\u0011H\u0002J\u001D\u0010\u00B1\u0001\u001A\u00060Tj\u0002`U*\u00020\u00112\n\b\u0002\u0010i\u001A\u0004\u0018\u00010]H\u0004R\u0011\u0010\u0007\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\bX\u0082\u0004R\u0011\u0010\n\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010\u000B0\bX\u0082\u0004R\u0017\u0010\f\u001A\b\u0012\u0004\u0012\u00020\u00010\r8F\u00A2\u0006\u0006\u001A\u0004\b\u000E\u0010\u000FR\u0016\u0010\u0010\u001A\u0004\u0018\u00010\u00118DX\u0084\u0004\u00A2\u0006\u0006\u001A\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0014\u001A\u00020\u00058DX\u0084\u0004\u00A2\u0006\u0006\u001A\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0017\u001A\u00020\u00058PX\u0090\u0004\u00A2\u0006\u0006\u001A\u0004\b\u0018\u0010\u0016R\u0014\u0010\u0019\u001A\u00020\u00058VX\u0096\u0004\u00A2\u0006\u0006\u001A\u0004\b\u0019\u0010\u0016R\u0011\u0010\u001A\u001A\u00020\u00058F\u00A2\u0006\u0006\u001A\u0004\b\u001A\u0010\u0016R\u0011\u0010\u001B\u001A\u00020\u00058F\u00A2\u0006\u0006\u001A\u0004\b\u001B\u0010\u0016R\u0011\u0010\u001C\u001A\u00020\u00058F\u00A2\u0006\u0006\u001A\u0004\b\u001C\u0010\u0016R\u0014\u0010\u001D\u001A\u00020\u00058TX\u0094\u0004\u00A2\u0006\u0006\u001A\u0004\b\u001D\u0010\u0016R\u0015\u0010\u001E\u001A\u0006\u0012\u0002\b\u00030\u001F8F\u00A2\u0006\u0006\u001A\u0004\b \u0010!R\u001E\u0010\"\u001A\u0006\u0012\u0002\b\u00030#8DX\u0084\u0004\u00A2\u0006\f\u0012\u0004\b$\u0010%\u001A\u0004\b&\u0010\'R\u0014\u0010(\u001A\u00020\u00058PX\u0090\u0004\u00A2\u0006\u0006\u001A\u0004\b)\u0010\u0016R\u0017\u0010*\u001A\u00020+8F\u00A2\u0006\f\u0012\u0004\b,\u0010%\u001A\u0004\b-\u0010.R\u0016\u0010/\u001A\u0004\u0018\u00010\u00018VX\u0096\u0004\u00A2\u0006\u0006\u001A\u0004\b0\u00101R(\u00103\u001A\u0004\u0018\u00010\t2\b\u00102\u001A\u0004\u0018\u00010\t8@@@X\u0080\u000E\u00A2\u0006\f\u001A\u0004\b4\u00105\"\u0004\b6\u00107R\u0016\u00108\u001A\u0004\u0018\u00010\u000B8@X\u0080\u0004\u00A2\u0006\u0006\u001A\u0004\b9\u0010:R\u001C\u0010;\u001A\u0004\u0018\u00010\u0011*\u0004\u0018\u00010\u000B8BX\u0082\u0004\u00A2\u0006\u0006\u001A\u0004\b<\u0010=R\u0018\u0010>\u001A\u00020\u0005*\u00020?8BX\u0082\u0004\u00A2\u0006\u0006\u001A\u0004\b>\u0010@\u0082\u0002\u0004\n\u0002\b\u0019\u00A8\u0006\u00B7\u0001"}, d2 = {"Lkotlinx/coroutines/JobSupport;", "Lkotlinx/coroutines/Job;", "Lkotlinx/coroutines/ChildJob;", "Lkotlinx/coroutines/ParentJob;", "active", "", "(Z)V", "_parentHandle", "Lkotlinx/atomicfu/AtomicRef;", "Lkotlinx/coroutines/ChildHandle;", "_state", "", "children", "Lkotlin/sequences/Sequence;", "getChildren", "()Lkotlin/sequences/Sequence;", "completionCause", "", "getCompletionCause", "()Ljava/lang/Throwable;", "completionCauseHandled", "getCompletionCauseHandled", "()Z", "handlesException", "getHandlesException$kotlinx_coroutines_core", "isActive", "isCancelled", "isCompleted", "isCompletedExceptionally", "isScopedCoroutine", "key", "Lkotlin/coroutines/CoroutineContext$Key;", "getKey", "()Lkotlin/coroutines/CoroutineContext$Key;", "onAwaitInternal", "Lkotlinx/coroutines/selects/SelectClause1;", "getOnAwaitInternal$annotations", "()V", "getOnAwaitInternal", "()Lkotlinx/coroutines/selects/SelectClause1;", "onCancelComplete", "getOnCancelComplete$kotlinx_coroutines_core", "onJoin", "Lkotlinx/coroutines/selects/SelectClause0;", "getOnJoin$annotations", "getOnJoin", "()Lkotlinx/coroutines/selects/SelectClause0;", "parent", "getParent", "()Lkotlinx/coroutines/Job;", "value", "parentHandle", "getParentHandle$kotlinx_coroutines_core", "()Lkotlinx/coroutines/ChildHandle;", "setParentHandle$kotlinx_coroutines_core", "(Lkotlinx/coroutines/ChildHandle;)V", "state", "getState$kotlinx_coroutines_core", "()Ljava/lang/Object;", "exceptionOrNull", "getExceptionOrNull", "(Ljava/lang/Object;)Ljava/lang/Throwable;", "isCancelling", "Lkotlinx/coroutines/Incomplete;", "(Lkotlinx/coroutines/Incomplete;)Z", "addLastAtomic", "expect", "list", "Lkotlinx/coroutines/NodeList;", "node", "Lkotlinx/coroutines/JobNode;", "addSuppressedExceptions", "", "rootCause", "exceptions", "", "afterCompletion", "attachChild", "child", "awaitInternal", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "awaitSuspend", "cancel", "cause", "Ljava/util/concurrent/CancellationException;", "Lkotlinx/coroutines/CancellationException;", "cancelCoroutine", "cancelImpl", "cancelImpl$kotlinx_coroutines_core", "cancelInternal", "cancelMakeCompleting", "cancelParent", "cancellationExceptionMessage", "", "childCancelled", "completeStateFinalization", "update", "continueCompleting", "Lkotlinx/coroutines/JobSupport$Finishing;", "lastChild", "Lkotlinx/coroutines/ChildHandleNode;", "proposedUpdate", "createCauseException", "defaultCancellationException", "Lkotlinx/coroutines/JobCancellationException;", "message", "defaultCancellationException$kotlinx_coroutines_core", "finalizeFinishingState", "firstChild", "getCancellationException", "getChildJobCancellationCause", "getCompletedInternal", "getCompletedInternal$kotlinx_coroutines_core", "getCompletionExceptionOrNull", "getFinalRootCause", "getOrPromoteCancellingList", "handleJobException", "exception", "handleOnCompletionException", "handleOnCompletionException$kotlinx_coroutines_core", "initParentJob", "invokeOnCompletion", "Lkotlinx/coroutines/DisposableHandle;", "onCancelling", "invokeImmediately", "handler", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "Lkotlinx/coroutines/CompletionHandler;", "join", "joinInternal", "joinSuspend", "loopOnState", "", "block", "makeCancelling", "makeCompleting", "makeCompleting$kotlinx_coroutines_core", "makeCompletingOnce", "makeCompletingOnce$kotlinx_coroutines_core", "makeNode", "nameString", "nameString$kotlinx_coroutines_core", "notifyCancelling", "notifyHandlers", "T", "onAwaitInternalProcessResFunc", "ignoredParam", "result", "onAwaitInternalRegFunc", "select", "Lkotlinx/coroutines/selects/SelectInstance;", "onCompletionInternal", "onStart", "parentCancelled", "parentJob", "promoteEmptyToNodeList", "Lkotlinx/coroutines/Empty;", "promoteSingleToNodeList", "registerSelectForOnJoin", "removeNode", "removeNode$kotlinx_coroutines_core", "start", "startInternal", "", "stateString", "toDebugString", "toString", "tryFinalizeSimpleState", "tryMakeCancelling", "tryMakeCompleting", "tryMakeCompletingSlowPath", "tryWaitForChild", "nextChild", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "notifyCompletion", "toCancellationException", "AwaitContinuation", "ChildCompletion", "Finishing", "SelectOnAwaitCompletionHandler", "SelectOnJoinCompletionHandler", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public class JobSupport implements ChildJob, Job, ParentJob {
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0000\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u001B\u0012\f\u0010\u0003\u001A\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\u0006\u0010\u0005\u001A\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0010\u0010\b\u001A\u00020\t2\u0006\u0010\n\u001A\u00020\u000BH\u0016J\b\u0010\f\u001A\u00020\rH\u0014R\u000E\u0010\u0005\u001A\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000E"}, d2 = {"Lkotlinx/coroutines/JobSupport$AwaitContinuation;", "T", "Lkotlinx/coroutines/CancellableContinuationImpl;", "delegate", "Lkotlin/coroutines/Continuation;", "job", "Lkotlinx/coroutines/JobSupport;", "(Lkotlin/coroutines/Continuation;Lkotlinx/coroutines/JobSupport;)V", "getContinuationCancellationCause", "", "parent", "Lkotlinx/coroutines/Job;", "nameString", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    static final class AwaitContinuation extends CancellableContinuationImpl {
        private final JobSupport job;

        public AwaitContinuation(Continuation continuation0, JobSupport jobSupport0) {
            super(continuation0, 1);
            this.job = jobSupport0;
        }

        @Override  // kotlinx.coroutines.CancellableContinuationImpl
        public Throwable getContinuationCancellationCause(Job job0) {
            Object object0 = this.job.getState$kotlinx_coroutines_core();
            if(object0 instanceof Finishing) {
                Throwable throwable0 = ((Finishing)object0).getRootCause();
                if(throwable0 != null) {
                    return throwable0;
                }
            }
            return object0 instanceof CompletedExceptionally ? ((CompletedExceptionally)object0).cause : job0.getCancellationException();
        }

        @Override  // kotlinx.coroutines.CancellableContinuationImpl
        protected String nameString() {
            return "AwaitContinuation";
        }
    }

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\'\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0005\u0012\u0006\u0010\u0006\u001A\u00020\u0007\u0012\b\u0010\b\u001A\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nJ\u0013\u0010\u000B\u001A\u00020\f2\b\u0010\r\u001A\u0004\u0018\u00010\u000EH\u0096\u0002R\u000E\u0010\u0006\u001A\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0002\u001A\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001A\u0004\u0018\u00010\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0004\u001A\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000F"}, d2 = {"Lkotlinx/coroutines/JobSupport$ChildCompletion;", "Lkotlinx/coroutines/JobNode;", "parent", "Lkotlinx/coroutines/JobSupport;", "state", "Lkotlinx/coroutines/JobSupport$Finishing;", "child", "Lkotlinx/coroutines/ChildHandleNode;", "proposedUpdate", "", "(Lkotlinx/coroutines/JobSupport;Lkotlinx/coroutines/JobSupport$Finishing;Lkotlinx/coroutines/ChildHandleNode;Ljava/lang/Object;)V", "invoke", "", "cause", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    static final class ChildCompletion extends JobNode {
        private final ChildHandleNode child;
        private final JobSupport parent;
        private final Object proposedUpdate;
        private final Finishing state;

        public ChildCompletion(JobSupport jobSupport0, Finishing jobSupport$Finishing0, ChildHandleNode childHandleNode0, Object object0) {
            this.parent = jobSupport0;
            this.state = jobSupport$Finishing0;
            this.child = childHandleNode0;
            this.proposedUpdate = object0;
        }

        @Override  // kotlin.jvm.functions.Function1
        public Object invoke(Object object0) {
            this.invoke(((Throwable)object0));
            return Unit.INSTANCE;
        }

        @Override  // kotlinx.coroutines.CompletionHandlerBase
        public void invoke(Throwable throwable0) {
            this.parent.continueCompleting(this.state, this.child, this.proposedUpdate);
        }
    }

    @Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0000\b\u0002\u0018\u00002\u00060\u0001j\u0002`\u00022\u00020\u0003B\u001F\u0012\u0006\u0010\u0004\u001A\u00020\u0005\u0012\u0006\u0010\u0006\u001A\u00020\u0007\u0012\b\u0010\b\u001A\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nJ\u000E\u0010\"\u001A\u00020#2\u0006\u0010$\u001A\u00020\tJ\u0018\u0010%\u001A\u0012\u0012\u0004\u0012\u00020\t0&j\b\u0012\u0004\u0012\u00020\t`\'H\u0002J\u0016\u0010(\u001A\b\u0012\u0004\u0012\u00020\t0)2\b\u0010*\u001A\u0004\u0018\u00010\tJ\b\u0010+\u001A\u00020,H\u0016R\u0011\u0010\u000B\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\fX\u0082\u0004R\t\u0010\r\u001A\u00020\u000EX\u0082\u0004R\u0011\u0010\u000F\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\fX\u0082\u0004R(\u0010\u0011\u001A\u0004\u0018\u00010\u00012\b\u0010\u0010\u001A\u0004\u0018\u00010\u00018B@BX\u0082\u000E¢\u0006\f\u001A\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0016\u001A\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0018\u001A\u00020\u00078F¢\u0006\u0006\u001A\u0004\b\u0018\u0010\u0017R$\u0010\u0006\u001A\u00020\u00072\u0006\u0010\u0010\u001A\u00020\u00078F@FX\u0086\u000E¢\u0006\f\u001A\u0004\b\u0006\u0010\u0017\"\u0004\b\u0019\u0010\u001AR\u0011\u0010\u001B\u001A\u00020\u00078F¢\u0006\u0006\u001A\u0004\b\u001B\u0010\u0017R\u0014\u0010\u0004\u001A\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001A\u0004\b\u001C\u0010\u001DR(\u0010\b\u001A\u0004\u0018\u00010\t2\b\u0010\u0010\u001A\u0004\u0018\u00010\t8F@FX\u0086\u000E¢\u0006\f\u001A\u0004\b\u001E\u0010\u001F\"\u0004\b \u0010!¨\u0006-"}, d2 = {"Lkotlinx/coroutines/JobSupport$Finishing;", "", "Lkotlinx/coroutines/internal/SynchronizedObject;", "Lkotlinx/coroutines/Incomplete;", "list", "Lkotlinx/coroutines/NodeList;", "isCompleting", "", "rootCause", "", "(Lkotlinx/coroutines/NodeList;ZLjava/lang/Throwable;)V", "_exceptionsHolder", "Lkotlinx/atomicfu/AtomicRef;", "_isCompleting", "Lkotlinx/atomicfu/AtomicBoolean;", "_rootCause", "value", "exceptionsHolder", "getExceptionsHolder", "()Ljava/lang/Object;", "setExceptionsHolder", "(Ljava/lang/Object;)V", "isActive", "()Z", "isCancelling", "setCompleting", "(Z)V", "isSealed", "getList", "()Lkotlinx/coroutines/NodeList;", "getRootCause", "()Ljava/lang/Throwable;", "setRootCause", "(Ljava/lang/Throwable;)V", "addExceptionLocked", "", "exception", "allocateList", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "sealLocked", "", "proposedException", "toString", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    static final class Finishing implements Incomplete {
        @Volatile
        private volatile Object _exceptionsHolder;
        private static final AtomicReferenceFieldUpdater _exceptionsHolder$FU;
        @Volatile
        private volatile int _isCompleting;
        private static final AtomicIntegerFieldUpdater _isCompleting$FU;
        @Volatile
        private volatile Object _rootCause;
        private static final AtomicReferenceFieldUpdater _rootCause$FU;
        private final NodeList list;

        static {
            Finishing._isCompleting$FU = AtomicIntegerFieldUpdater.newUpdater(Finishing.class, "_isCompleting");
            Finishing._rootCause$FU = AtomicReferenceFieldUpdater.newUpdater(Finishing.class, Object.class, "_rootCause");
            Finishing._exceptionsHolder$FU = AtomicReferenceFieldUpdater.newUpdater(Finishing.class, Object.class, "_exceptionsHolder");
        }

        public Finishing(NodeList nodeList0, boolean z, Throwable throwable0) {
            this.list = nodeList0;
            this._isCompleting = z;
            this._rootCause = throwable0;
        }

        public final void addExceptionLocked(Throwable throwable0) {
            Throwable throwable1 = this.getRootCause();
            if(throwable1 == null) {
                this.setRootCause(throwable0);
                return;
            }
            if(throwable0 != throwable1) {
                Object object0 = this.getExceptionsHolder();
                if(object0 == null) {
                    this.setExceptionsHolder(throwable0);
                    return;
                }
                if(!(object0 instanceof Throwable)) {
                    if(!(object0 instanceof ArrayList)) {
                        throw new IllegalStateException(("State is " + object0).toString());
                    }
                    ((ArrayList)object0).add(throwable0);
                }
                else if(throwable0 != object0) {
                    ArrayList arrayList0 = this.allocateList();
                    arrayList0.add(object0);
                    arrayList0.add(throwable0);
                    this.setExceptionsHolder(arrayList0);
                }
            }
        }

        private final ArrayList allocateList() {
            return new ArrayList(4);
        }

        private final Object getExceptionsHolder() {
            return Finishing._exceptionsHolder$FU.get(this);
        }

        @Override  // kotlinx.coroutines.Incomplete
        public NodeList getList() {
            return this.list;
        }

        public final Throwable getRootCause() {
            return (Throwable)Finishing._rootCause$FU.get(this);
        }

        @Override  // kotlinx.coroutines.Incomplete
        public boolean isActive() {
            return this.getRootCause() == null;
        }

        public final boolean isCancelling() {
            return this.getRootCause() != null;
        }

        public final boolean isCompleting() {
            return Finishing._isCompleting$FU.get(this) != 0;
        }

        public final boolean isSealed() {
            return this.getExceptionsHolder() == JobSupportKt.access$getSEALED$p();
        }

        public final List sealLocked(Throwable throwable0) {
            ArrayList arrayList0;
            Object object0 = this.getExceptionsHolder();
            if(object0 == null) {
                arrayList0 = this.allocateList();
            }
            else if(object0 instanceof Throwable) {
                ArrayList arrayList1 = this.allocateList();
                arrayList1.add(object0);
                arrayList0 = arrayList1;
            }
            else if(object0 instanceof ArrayList) {
                arrayList0 = (ArrayList)object0;
            }
            else {
                throw new IllegalStateException(("State is " + object0).toString());
            }
            Throwable throwable1 = this.getRootCause();
            if(throwable1 != null) {
                arrayList0.add(0, throwable1);
            }
            if(throwable0 != null && !Intrinsics.areEqual(throwable0, throwable1)) {
                arrayList0.add(throwable0);
            }
            this.setExceptionsHolder(JobSupportKt.access$getSEALED$p());
            return arrayList0;
        }

        public final void setCompleting(boolean z) {
            Finishing._isCompleting$FU.set(this, ((int)z));
        }

        private final void setExceptionsHolder(Object object0) {
            Finishing._exceptionsHolder$FU.set(this, object0);
        }

        public final void setRootCause(Throwable throwable0) {
            Finishing._rootCause$FU.set(this, throwable0);
        }

        @Override
        public String toString() {
            return "Finishing[cancelling=" + this.isCancelling() + ", completing=" + this.isCompleting() + ", rootCause=" + this.getRootCause() + ", exceptions=" + this.getExceptionsHolder() + ", list=" + this.getList() + ']';
        }
    }

    @Metadata(d1 = {"\u0000\u001E\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0011\u0012\n\u0010\u0002\u001A\u0006\u0012\u0002\b\u00030\u0003¢\u0006\u0002\u0010\u0004J\u0013\u0010\u0005\u001A\u00020\u00062\b\u0010\u0007\u001A\u0004\u0018\u00010\bH\u0096\u0002R\u0012\u0010\u0002\u001A\u0006\u0012\u0002\b\u00030\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lkotlinx/coroutines/JobSupport$SelectOnAwaitCompletionHandler;", "Lkotlinx/coroutines/JobNode;", "select", "Lkotlinx/coroutines/selects/SelectInstance;", "(Lkotlinx/coroutines/JobSupport;Lkotlinx/coroutines/selects/SelectInstance;)V", "invoke", "", "cause", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    final class SelectOnAwaitCompletionHandler extends JobNode {
        private final SelectInstance select;

        public SelectOnAwaitCompletionHandler(SelectInstance selectInstance0) {
            this.select = selectInstance0;
        }

        @Override  // kotlin.jvm.functions.Function1
        public Object invoke(Object object0) {
            this.invoke(((Throwable)object0));
            return Unit.INSTANCE;
        }

        @Override  // kotlinx.coroutines.CompletionHandlerBase
        public void invoke(Throwable throwable0) {
            Object object0 = JobSupport.this.getState$kotlinx_coroutines_core();
            if(!(object0 instanceof CompletedExceptionally)) {
                object0 = JobSupportKt.unboxState(object0);
            }
            this.select.trySelect(JobSupport.this, object0);
        }
    }

    @Metadata(d1 = {"\u0000\u001E\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0011\u0012\n\u0010\u0002\u001A\u0006\u0012\u0002\b\u00030\u0003¢\u0006\u0002\u0010\u0004J\u0013\u0010\u0005\u001A\u00020\u00062\b\u0010\u0007\u001A\u0004\u0018\u00010\bH\u0096\u0002R\u0012\u0010\u0002\u001A\u0006\u0012\u0002\b\u00030\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lkotlinx/coroutines/JobSupport$SelectOnJoinCompletionHandler;", "Lkotlinx/coroutines/JobNode;", "select", "Lkotlinx/coroutines/selects/SelectInstance;", "(Lkotlinx/coroutines/JobSupport;Lkotlinx/coroutines/selects/SelectInstance;)V", "invoke", "", "cause", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    final class SelectOnJoinCompletionHandler extends JobNode {
        private final SelectInstance select;

        public SelectOnJoinCompletionHandler(SelectInstance selectInstance0) {
            this.select = selectInstance0;
        }

        @Override  // kotlin.jvm.functions.Function1
        public Object invoke(Object object0) {
            this.invoke(((Throwable)object0));
            return Unit.INSTANCE;
        }

        @Override  // kotlinx.coroutines.CompletionHandlerBase
        public void invoke(Throwable throwable0) {
            this.select.trySelect(JobSupport.this, Unit.INSTANCE);
        }
    }

    @Volatile
    private volatile Object _parentHandle;
    private static final AtomicReferenceFieldUpdater _parentHandle$FU;
    @Volatile
    private volatile Object _state;
    private static final AtomicReferenceFieldUpdater _state$FU;

    static {
        JobSupport._state$FU = AtomicReferenceFieldUpdater.newUpdater(JobSupport.class, Object.class, "_state");
        JobSupport._parentHandle$FU = AtomicReferenceFieldUpdater.newUpdater(JobSupport.class, Object.class, "_parentHandle");
    }

    public JobSupport(boolean z) {
        this._state = z ? JobSupportKt.access$getEMPTY_ACTIVE$p() : JobSupportKt.access$getEMPTY_NEW$p();
    }

    public static final Object access$awaitSuspend(JobSupport jobSupport0, Continuation continuation0) {
        return jobSupport0.awaitSuspend(continuation0);
    }

    // 去混淆评级： 低(20)
    public static final String access$cancellationExceptionMessage(JobSupport jobSupport0) {
        return "Job was cancelled";
    }

    public static final Object access$joinSuspend(JobSupport jobSupport0, Continuation continuation0) {
        return jobSupport0.joinSuspend(continuation0);
    }

    private final boolean addLastAtomic(Object object0, NodeList nodeList0, JobNode jobNode0) {
        kotlinx.coroutines.JobSupport.addLastAtomic..inlined.addLastIf.1 jobSupport$addLastAtomic$$inlined$addLastIf$10 = new CondAddOp(this) {
            @Override  // kotlinx.coroutines.internal.AtomicOp
            public Object prepare(Object object0) {
                return this.prepare(((LockFreeLinkedListNode)object0));
            }

            public Object prepare(LockFreeLinkedListNode lockFreeLinkedListNode0) {
                return object0.getState$kotlinx_coroutines_core() == this.$expect$inlined ? null : LockFreeLinkedListKt.getCONDITION_FALSE();
            }
        };
    alab1:
        while(true) {
            switch(nodeList0.getPrevNode().tryCondAddNext(jobNode0, nodeList0, jobSupport$addLastAtomic$$inlined$addLastIf$10)) {
                case 1: {
                    return true;
                }
                case 2: {
                    break alab1;
                }
            }
        }
        return false;
    }

    private final void addSuppressedExceptions(Throwable throwable0, List list0) {
        if(list0.size() > 1) {
            Set set0 = Collections.newSetFromMap(new IdentityHashMap(list0.size()));
            for(Object object0: list0) {
                Throwable throwable1 = (Throwable)object0;
                if(throwable1 != throwable0 && !(throwable1 instanceof CancellationException) && set0.add(throwable1)) {
                    ExceptionsKt.addSuppressed(throwable0, throwable1);
                }
            }
        }
    }

    protected void afterCompletion(Object object0) {
    }

    @Override  // kotlinx.coroutines.Job
    public final ChildHandle attachChild(ChildJob childJob0) {
        DisposableHandle disposableHandle0 = DefaultImpls.invokeOnCompletion$default(this, true, false, new ChildHandleNode(childJob0), 2, null);
        Intrinsics.checkNotNull(disposableHandle0, "null cannot be cast to non-null type kotlinx.coroutines.ChildHandle");
        return (ChildHandle)disposableHandle0;
    }

    protected final Object awaitInternal(Continuation continuation0) {
        do {
            Object object0 = this.getState$kotlinx_coroutines_core();
            if(!(object0 instanceof Incomplete)) {
                if(object0 instanceof CompletedExceptionally) {
                    throw ((CompletedExceptionally)object0).cause;
                }
                return JobSupportKt.unboxState(object0);
            }
        }
        while(this.startInternal(object0) < 0);
        return this.awaitSuspend(continuation0);
    }

    private final Object awaitSuspend(Continuation continuation0) {
        AwaitContinuation jobSupport$AwaitContinuation0 = new AwaitContinuation(IntrinsicsKt.intercepted(continuation0), this);
        jobSupport$AwaitContinuation0.initCancellability();
        CancellableContinuationKt.disposeOnCancellation(jobSupport$AwaitContinuation0, this.invokeOnCompletion(new ResumeAwaitOnCompletion(jobSupport$AwaitContinuation0)));
        Object object0 = jobSupport$AwaitContinuation0.getResult();
        if(object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation0);
        }
        return object0;
    }

    @Override  // kotlinx.coroutines.Job
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Since 1.2.0, binary compatibility with versions <= 1.1.x")
    public void cancel() {
        DefaultImpls.cancel(this);
    }

    @Override  // kotlinx.coroutines.Job
    public void cancel(CancellationException cancellationException0) {
        if(cancellationException0 == null) {
            cancellationException0 = new JobCancellationException(JobSupport.access$cancellationExceptionMessage(this), null, this);
        }
        this.cancelInternal(cancellationException0);
    }

    @Override  // kotlinx.coroutines.Job
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Added since 1.2.0 for binary compatibility with versions <= 1.1.x")
    public boolean cancel(Throwable throwable0) {
        CancellationException cancellationException0;
        if(throwable0 == null) {
            cancellationException0 = new JobCancellationException(JobSupport.access$cancellationExceptionMessage(this), null, this);
        }
        else {
            cancellationException0 = JobSupport.toCancellationException$default(this, throwable0, null, 1, null);
            if(cancellationException0 == null) {
                cancellationException0 = new JobCancellationException(JobSupport.access$cancellationExceptionMessage(this), null, this);
            }
        }
        this.cancelInternal(cancellationException0);
        return true;
    }

    public final boolean cancelCoroutine(Throwable throwable0) {
        return this.cancelImpl$kotlinx_coroutines_core(throwable0);
    }

    public final boolean cancelImpl$kotlinx_coroutines_core(Object object0) {
        Symbol symbol0 = JobSupportKt.access$getCOMPLETING_ALREADY$p();
        if(this.getOnCancelComplete$kotlinx_coroutines_core()) {
            symbol0 = this.cancelMakeCompleting(object0);
            if(symbol0 == JobSupportKt.COMPLETING_WAITING_CHILDREN) {
                return true;
            }
        }
        if(symbol0 == JobSupportKt.access$getCOMPLETING_ALREADY$p()) {
            symbol0 = this.makeCancelling(object0);
        }
        if(symbol0 == JobSupportKt.access$getCOMPLETING_ALREADY$p()) {
            return true;
        }
        if(symbol0 == JobSupportKt.COMPLETING_WAITING_CHILDREN) {
            return true;
        }
        if(symbol0 == JobSupportKt.access$getTOO_LATE_TO_CANCEL$p()) {
            return false;
        }
        this.afterCompletion(symbol0);
        return true;
    }

    public void cancelInternal(Throwable throwable0) {
        this.cancelImpl$kotlinx_coroutines_core(throwable0);
    }

    private final Object cancelMakeCompleting(Object object0) {
        while(true) {
            Object object1 = this.getState$kotlinx_coroutines_core();
            if(!(object1 instanceof Incomplete) || object1 instanceof Finishing && ((Finishing)object1).isCompleting()) {
                break;
            }
            Object object2 = this.tryMakeCompleting(object1, new CompletedExceptionally(this.createCauseException(object0), false, 2, null));
            if(object2 != JobSupportKt.access$getCOMPLETING_RETRY$p()) {
                return object2;
            }
        }
        return JobSupportKt.access$getCOMPLETING_ALREADY$p();
    }

    private final boolean cancelParent(Throwable throwable0) {
        if(this.isScopedCoroutine()) {
            return true;
        }
        ChildHandle childHandle0 = this.getParentHandle$kotlinx_coroutines_core();
        return childHandle0 == null || childHandle0 == NonDisposableHandle.INSTANCE ? throwable0 instanceof CancellationException : childHandle0.childCancelled(throwable0) || throwable0 instanceof CancellationException;
    }

    protected String cancellationExceptionMessage() [...] // 潜在的解密器

    // 去混淆评级： 低(40)
    public boolean childCancelled(Throwable throwable0) {
        return throwable0 instanceof CancellationException ? true : this.cancelImpl$kotlinx_coroutines_core(throwable0) && this.getHandlesException$kotlinx_coroutines_core();
    }

    private final void completeStateFinalization(Incomplete incomplete0, Object object0) {
        ChildHandle childHandle0 = this.getParentHandle$kotlinx_coroutines_core();
        if(childHandle0 != null) {
            childHandle0.dispose();
            this.setParentHandle$kotlinx_coroutines_core(NonDisposableHandle.INSTANCE);
        }
        Throwable throwable0 = null;
        CompletedExceptionally completedExceptionally0 = object0 instanceof CompletedExceptionally ? ((CompletedExceptionally)object0) : null;
        if(completedExceptionally0 != null) {
            throwable0 = completedExceptionally0.cause;
        }
        if(incomplete0 instanceof JobNode) {
            try {
                ((JobNode)incomplete0).invoke(throwable0);
            }
            catch(Throwable throwable1) {
                this.handleOnCompletionException$kotlinx_coroutines_core(new CompletionHandlerException("Exception in completion handler " + incomplete0 + " for " + this, throwable1));
            }
            return;
        }
        NodeList nodeList0 = incomplete0.getList();
        if(nodeList0 != null) {
            this.notifyCompletion(nodeList0, throwable0);
        }
    }

    private final void continueCompleting(Finishing jobSupport$Finishing0, ChildHandleNode childHandleNode0, Object object0) {
        ChildHandleNode childHandleNode1 = this.nextChild(childHandleNode0);
        if(childHandleNode1 != null && this.tryWaitForChild(jobSupport$Finishing0, childHandleNode1, object0)) {
            return;
        }
        this.afterCompletion(this.finalizeFinishingState(jobSupport$Finishing0, object0));
    }

    private final Throwable createCauseException(Object object0) {
        if((object0 == null ? true : object0 instanceof Throwable)) {
            return ((Throwable)object0) == null ? new JobCancellationException(JobSupport.access$cancellationExceptionMessage(this), null, this) : ((Throwable)object0);
        }
        Intrinsics.checkNotNull(object0, "null cannot be cast to non-null type kotlinx.coroutines.ParentJob");
        return ((ParentJob)object0).getChildJobCancellationCause();
    }

    public final JobCancellationException defaultCancellationException$kotlinx_coroutines_core(String s, Throwable throwable0) {
        if(s == null) {
            s = JobSupport.access$cancellationExceptionMessage(this);
        }
        return new JobCancellationException(s, throwable0, this);
    }

    public static JobCancellationException defaultCancellationException$kotlinx_coroutines_core$default(JobSupport jobSupport0, String s, Throwable throwable0, int v, Object object0) {
        if(object0 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: defaultCancellationException");
        }
        if((v & 1) != 0) {
            s = null;
        }
        if((v & 2) != 0) {
            throwable0 = null;
        }
        if(s == null) {
            s = JobSupport.access$cancellationExceptionMessage(jobSupport0);
        }
        return new JobCancellationException(s, throwable0, jobSupport0);
    }

    private final Object finalizeFinishingState(Finishing jobSupport$Finishing0, Object object0) {
        Throwable throwable1;
        CompletedExceptionally completedExceptionally0 = object0 instanceof CompletedExceptionally ? ((CompletedExceptionally)object0) : null;
        Throwable throwable0 = completedExceptionally0 == null ? null : completedExceptionally0.cause;
        synchronized(jobSupport$Finishing0) {
            boolean z = jobSupport$Finishing0.isCancelling();
            List list0 = jobSupport$Finishing0.sealLocked(throwable0);
            throwable1 = this.getFinalRootCause(jobSupport$Finishing0, list0);
            if(throwable1 != null) {
                this.addSuppressedExceptions(throwable1, list0);
            }
        }
        if(throwable1 != null && throwable1 != throwable0) {
            object0 = new CompletedExceptionally(throwable1, false, 2, null);
        }
        if(throwable1 != null && (this.cancelParent(throwable1) || this.handleJobException(throwable1))) {
            Intrinsics.checkNotNull(object0, "null cannot be cast to non-null type kotlinx.coroutines.CompletedExceptionally");
            ((CompletedExceptionally)object0).makeHandled();
        }
        if(!z) {
            this.onCancelling(throwable1);
        }
        this.onCompletionInternal(object0);
        JobSupportKt.boxIncomplete(object0);
        this.completeStateFinalization(jobSupport$Finishing0, object0);
        return object0;
    }

    private final ChildHandleNode firstChild(Incomplete incomplete0) {
        ChildHandleNode childHandleNode0 = incomplete0 instanceof ChildHandleNode ? ((ChildHandleNode)incomplete0) : null;
        if(childHandleNode0 == null) {
            NodeList nodeList0 = incomplete0.getList();
            return nodeList0 == null ? null : this.nextChild(nodeList0);
        }
        return childHandleNode0;
    }

    @Override  // kotlin.coroutines.CoroutineContext$Element
    public Object fold(Object object0, Function2 function20) {
        return DefaultImpls.fold(this, object0, function20);
    }

    @Override  // kotlin.coroutines.CoroutineContext$Element
    public Element get(Key coroutineContext$Key0) {
        return DefaultImpls.get(this, coroutineContext$Key0);
    }

    @Override  // kotlinx.coroutines.Job
    public final CancellationException getCancellationException() {
        Object object0 = this.getState$kotlinx_coroutines_core();
        if(object0 instanceof Finishing) {
            Throwable throwable0 = ((Finishing)object0).getRootCause();
            if(throwable0 != null) {
                CancellationException cancellationException0 = this.toCancellationException(throwable0, DebugStringsKt.getClassSimpleName(this) + " is cancelling");
                if(cancellationException0 != null) {
                    return cancellationException0;
                }
            }
            throw new IllegalStateException(("Job is still new or active: " + this).toString());
        }
        if(object0 instanceof Incomplete) {
            throw new IllegalStateException(("Job is still new or active: " + this).toString());
        }
        return object0 instanceof CompletedExceptionally ? JobSupport.toCancellationException$default(this, ((CompletedExceptionally)object0).cause, null, 1, null) : new JobCancellationException(DebugStringsKt.getClassSimpleName(this) + " has completed normally", null, this);
    }

    @Override  // kotlinx.coroutines.ParentJob
    public CancellationException getChildJobCancellationCause() {
        Throwable throwable0;
        Object object0 = this.getState$kotlinx_coroutines_core();
        CancellationException cancellationException0 = null;
        if(object0 instanceof Finishing) {
            throwable0 = ((Finishing)object0).getRootCause();
        }
        else if(object0 instanceof CompletedExceptionally) {
            throwable0 = ((CompletedExceptionally)object0).cause;
        }
        else if(!(object0 instanceof Incomplete)) {
            throwable0 = null;
        }
        else {
            throw new IllegalStateException(("Cannot be cancelling child in this state: " + object0).toString());
        }
        if(throwable0 instanceof CancellationException) {
            cancellationException0 = (CancellationException)throwable0;
        }
        return cancellationException0 == null ? new JobCancellationException("Parent job is " + this.stateString(object0), throwable0, this) : cancellationException0;
    }

    @Override  // kotlinx.coroutines.Job
    public final Sequence getChildren() {
        return SequencesKt.sequence(new Function2(null) {
            private Object L$0;
            Object L$1;
            Object L$2;
            int label;

            {
                JobSupport.this = jobSupport0;
                super(2, continuation0);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object object0, Continuation continuation0) {
                kotlinx.coroutines.JobSupport.children.1 jobSupport$children$10 = new kotlinx.coroutines.JobSupport.children.1(JobSupport.this, continuation0);
                jobSupport$children$10.L$0 = object0;
                return jobSupport$children$10;
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(((SequenceScope)object0), ((Continuation)object1));
            }

            public final Object invoke(SequenceScope sequenceScope0, Continuation continuation0) {
                return ((kotlinx.coroutines.JobSupport.children.1)this.create(sequenceScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object object0) {
                SequenceScope sequenceScope1;
                LockFreeLinkedListNode lockFreeLinkedListNode0;
                LockFreeLinkedListHead lockFreeLinkedListHead0;
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            alab1:
                switch(this.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        SequenceScope sequenceScope0 = (SequenceScope)this.L$0;
                        Object object2 = JobSupport.this.getState$kotlinx_coroutines_core();
                        if(object2 instanceof ChildHandleNode) {
                            this.label = 1;
                            if(sequenceScope0.yield(((ChildHandleNode)object2).childJob, this) == object1) {
                                return object1;
                            }
                        }
                        else if(object2 instanceof Incomplete) {
                            NodeList nodeList0 = ((Incomplete)object2).getList();
                            if(nodeList0 != null) {
                                Object object3 = nodeList0.getNext();
                                Intrinsics.checkNotNull(object3, "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode{ kotlinx.coroutines.internal.LockFreeLinkedListKt.Node }");
                                lockFreeLinkedListHead0 = nodeList0;
                                lockFreeLinkedListNode0 = (LockFreeLinkedListNode)object3;
                                sequenceScope1 = sequenceScope0;
                                goto label_26;
                            }
                        }
                        break;
                    }
                    case 1: {
                        ResultKt.throwOnFailure(object0);
                        return Unit.INSTANCE;
                    }
                    case 2: {
                        lockFreeLinkedListNode0 = (LockFreeLinkedListNode)this.L$2;
                        lockFreeLinkedListHead0 = (LockFreeLinkedListHead)this.L$1;
                        sequenceScope1 = (SequenceScope)this.L$0;
                        ResultKt.throwOnFailure(object0);
                        while(true) {
                            while(true) {
                                lockFreeLinkedListNode0 = lockFreeLinkedListNode0.getNextNode();
                            label_26:
                                if(Intrinsics.areEqual(lockFreeLinkedListNode0, lockFreeLinkedListHead0)) {
                                    break alab1;
                                }
                                if(!(lockFreeLinkedListNode0 instanceof ChildHandleNode)) {
                                    break;
                                }
                                this.L$0 = sequenceScope1;
                                this.L$1 = lockFreeLinkedListHead0;
                                this.L$2 = lockFreeLinkedListNode0;
                                this.label = 2;
                                if(sequenceScope1.yield(((ChildHandleNode)lockFreeLinkedListNode0).childJob, this) != object1) {
                                    break;
                                }
                                return object1;
                            }
                        }
                    }
                    default: {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    }
                }
                return Unit.INSTANCE;
            }
        });
    }

    public final Object getCompletedInternal$kotlinx_coroutines_core() {
        Object object0 = this.getState$kotlinx_coroutines_core();
        if(object0 instanceof Incomplete) {
            throw new IllegalStateException("This job has not completed yet");
        }
        if(object0 instanceof CompletedExceptionally) {
            throw ((CompletedExceptionally)object0).cause;
        }
        return JobSupportKt.unboxState(object0);
    }

    protected final Throwable getCompletionCause() {
        Object object0 = this.getState$kotlinx_coroutines_core();
        if(object0 instanceof Finishing) {
            Throwable throwable0 = ((Finishing)object0).getRootCause();
            if(throwable0 == null) {
                throw new IllegalStateException(("Job is still new or active: " + this).toString());
            }
            return throwable0;
        }
        if(object0 instanceof Incomplete) {
            throw new IllegalStateException(("Job is still new or active: " + this).toString());
        }
        return object0 instanceof CompletedExceptionally ? ((CompletedExceptionally)object0).cause : null;
    }

    protected final boolean getCompletionCauseHandled() {
        Object object0 = this.getState$kotlinx_coroutines_core();
        return object0 instanceof CompletedExceptionally && ((CompletedExceptionally)object0).getHandled();
    }

    public final Throwable getCompletionExceptionOrNull() {
        Object object0 = this.getState$kotlinx_coroutines_core();
        if(object0 instanceof Incomplete) {
            throw new IllegalStateException("This job has not completed yet");
        }
        return this.getExceptionOrNull(object0);
    }

    private final Throwable getExceptionOrNull(Object object0) {
        CompletedExceptionally completedExceptionally0 = object0 instanceof CompletedExceptionally ? ((CompletedExceptionally)object0) : null;
        return completedExceptionally0 == null ? null : completedExceptionally0.cause;
    }

    private final Throwable getFinalRootCause(Finishing jobSupport$Finishing0, List list0) {
        if(list0.isEmpty()) {
            return jobSupport$Finishing0.isCancelling() ? new JobCancellationException(JobSupport.access$cancellationExceptionMessage(this), null, this) : null;
        }
        Object object0 = null;
        for(Object object1: list0) {
            if(!(((Throwable)object1) instanceof CancellationException)) {
                object0 = object1;
                break;
            }
        }
        if(((Throwable)object0) != null) {
            return (Throwable)object0;
        }
        Throwable throwable0 = (Throwable)list0.get(0);
        if(throwable0 instanceof TimeoutCancellationException) {
            for(Object object2: list0) {
                if(((Throwable)object2) != throwable0 && ((Throwable)object2) instanceof TimeoutCancellationException) {
                    return ((Throwable)object2) == null ? throwable0 : ((Throwable)object2);
                }
                if(false) {
                    break;
                }
            }
            return throwable0;
        }
        return throwable0;
    }

    public boolean getHandlesException$kotlinx_coroutines_core() {
        return true;
    }

    @Override  // kotlin.coroutines.CoroutineContext$Element
    public final Key getKey() {
        return Job.Key;
    }

    protected final SelectClause1 getOnAwaitInternal() {
        Intrinsics.checkNotNull(kotlinx.coroutines.JobSupport.onAwaitInternal.1.INSTANCE, "null cannot be cast to non-null type kotlin.Function3<@[ParameterName(name = \'clauseObject\')] kotlin.Any, @[ParameterName(name = \'select\')] kotlinx.coroutines.selects.SelectInstance<*>, @[ParameterName(name = \'param\')] kotlin.Any?, kotlin.Unit>{ kotlinx.coroutines.selects.SelectKt.RegistrationFunction }");
        Function3 function30 = (Function3)TypeIntrinsics.beforeCheckcastToFunctionOfArity(kotlinx.coroutines.JobSupport.onAwaitInternal.1.INSTANCE, 3);
        Intrinsics.checkNotNull(kotlinx.coroutines.JobSupport.onAwaitInternal.2.INSTANCE, "null cannot be cast to non-null type kotlin.Function3<@[ParameterName(name = \'clauseObject\')] kotlin.Any, @[ParameterName(name = \'param\')] kotlin.Any?, @[ParameterName(name = \'clauseResult\')] kotlin.Any?, kotlin.Any?>{ kotlinx.coroutines.selects.SelectKt.ProcessResultFunction }");
        return new SelectClause1Impl(this, function30, ((Function3)TypeIntrinsics.beforeCheckcastToFunctionOfArity(kotlinx.coroutines.JobSupport.onAwaitInternal.2.INSTANCE, 3)), null, 8, null);

        @Metadata(k = 3, mv = {1, 8, 0}, xi = 0x30)
        final class kotlinx.coroutines.JobSupport.onAwaitInternal.1 extends FunctionReferenceImpl implements Function3 {
            public static final kotlinx.coroutines.JobSupport.onAwaitInternal.1 INSTANCE;

            static {
                kotlinx.coroutines.JobSupport.onAwaitInternal.1.INSTANCE = new kotlinx.coroutines.JobSupport.onAwaitInternal.1();
            }

            kotlinx.coroutines.JobSupport.onAwaitInternal.1() {
                super(3, JobSupport.class, "onAwaitInternalRegFunc", "onAwaitInternalRegFunc(Lkotlinx/coroutines/selects/SelectInstance;Ljava/lang/Object;)V", 0);
            }

            @Override  // kotlin.jvm.functions.Function3
            public Object invoke(Object object0, Object object1, Object object2) {
                this.invoke(((JobSupport)object0), ((SelectInstance)object1), object2);
                return Unit.INSTANCE;
            }

            public final void invoke(JobSupport jobSupport0, SelectInstance selectInstance0, Object object0) {
                jobSupport0.onAwaitInternalRegFunc(selectInstance0, object0);
            }
        }


        @Metadata(k = 3, mv = {1, 8, 0}, xi = 0x30)
        final class kotlinx.coroutines.JobSupport.onAwaitInternal.2 extends FunctionReferenceImpl implements Function3 {
            public static final kotlinx.coroutines.JobSupport.onAwaitInternal.2 INSTANCE;

            static {
                kotlinx.coroutines.JobSupport.onAwaitInternal.2.INSTANCE = new kotlinx.coroutines.JobSupport.onAwaitInternal.2();
            }

            kotlinx.coroutines.JobSupport.onAwaitInternal.2() {
                super(3, JobSupport.class, "onAwaitInternalProcessResFunc", "onAwaitInternalProcessResFunc(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", 0);
            }

            @Override  // kotlin.jvm.functions.Function3
            public Object invoke(Object object0, Object object1, Object object2) {
                return this.invoke(((JobSupport)object0), object1, object2);
            }

            public final Object invoke(JobSupport jobSupport0, Object object0, Object object1) {
                return jobSupport0.onAwaitInternalProcessResFunc(object0, object1);
            }
        }

    }

    protected static void getOnAwaitInternal$annotations() {
    }

    public boolean getOnCancelComplete$kotlinx_coroutines_core() {
        return false;
    }

    @Override  // kotlinx.coroutines.Job
    public final SelectClause0 getOnJoin() {
        Intrinsics.checkNotNull(kotlinx.coroutines.JobSupport.onJoin.1.INSTANCE, "null cannot be cast to non-null type kotlin.Function3<@[ParameterName(name = \'clauseObject\')] kotlin.Any, @[ParameterName(name = \'select\')] kotlinx.coroutines.selects.SelectInstance<*>, @[ParameterName(name = \'param\')] kotlin.Any?, kotlin.Unit>{ kotlinx.coroutines.selects.SelectKt.RegistrationFunction }");
        return new SelectClause0Impl(this, ((Function3)TypeIntrinsics.beforeCheckcastToFunctionOfArity(kotlinx.coroutines.JobSupport.onJoin.1.INSTANCE, 3)), null, 4, null);

        @Metadata(k = 3, mv = {1, 8, 0}, xi = 0x30)
        final class kotlinx.coroutines.JobSupport.onJoin.1 extends FunctionReferenceImpl implements Function3 {
            public static final kotlinx.coroutines.JobSupport.onJoin.1 INSTANCE;

            static {
                kotlinx.coroutines.JobSupport.onJoin.1.INSTANCE = new kotlinx.coroutines.JobSupport.onJoin.1();
            }

            kotlinx.coroutines.JobSupport.onJoin.1() {
                super(3, JobSupport.class, "registerSelectForOnJoin", "registerSelectForOnJoin(Lkotlinx/coroutines/selects/SelectInstance;Ljava/lang/Object;)V", 0);
            }

            @Override  // kotlin.jvm.functions.Function3
            public Object invoke(Object object0, Object object1, Object object2) {
                this.invoke(((JobSupport)object0), ((SelectInstance)object1), object2);
                return Unit.INSTANCE;
            }

            public final void invoke(JobSupport jobSupport0, SelectInstance selectInstance0, Object object0) {
                jobSupport0.registerSelectForOnJoin(selectInstance0, object0);
            }
        }

    }

    public static void getOnJoin$annotations() {
    }

    private final NodeList getOrPromoteCancellingList(Incomplete incomplete0) {
        NodeList nodeList0 = incomplete0.getList();
        if(nodeList0 == null) {
            if(incomplete0 instanceof Empty) {
                return new NodeList();
            }
            if(!(incomplete0 instanceof JobNode)) {
                throw new IllegalStateException(("State should have list: " + incomplete0).toString());
            }
            this.promoteSingleToNodeList(((JobNode)incomplete0));
            return null;
        }
        return nodeList0;
    }

    @Override  // kotlinx.coroutines.Job
    public Job getParent() {
        ChildHandle childHandle0 = this.getParentHandle$kotlinx_coroutines_core();
        return childHandle0 == null ? null : childHandle0.getParent();
    }

    public final ChildHandle getParentHandle$kotlinx_coroutines_core() {
        return (ChildHandle)JobSupport._parentHandle$FU.get(this);
    }

    public final Object getState$kotlinx_coroutines_core() {
        Object object0;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater0 = JobSupport._state$FU;
        while(true) {
            object0 = atomicReferenceFieldUpdater0.get(this);
            if(!(object0 instanceof OpDescriptor)) {
                break;
            }
            ((OpDescriptor)object0).perform(this);
        }
        return object0;
    }

    protected boolean handleJobException(Throwable throwable0) {
        return false;
    }

    public void handleOnCompletionException$kotlinx_coroutines_core(Throwable throwable0) {
        throw throwable0;
    }

    protected final void initParentJob(Job job0) {
        if(job0 == null) {
            this.setParentHandle$kotlinx_coroutines_core(NonDisposableHandle.INSTANCE);
            return;
        }
        job0.start();
        ChildHandle childHandle0 = job0.attachChild(this);
        this.setParentHandle$kotlinx_coroutines_core(childHandle0);
        if(this.isCompleted()) {
            childHandle0.dispose();
            this.setParentHandle$kotlinx_coroutines_core(NonDisposableHandle.INSTANCE);
        }
    }

    @Override  // kotlinx.coroutines.Job
    public final DisposableHandle invokeOnCompletion(Function1 function10) {
        return this.invokeOnCompletion(false, true, function10);
    }

    @Override  // kotlinx.coroutines.Job
    public final DisposableHandle invokeOnCompletion(boolean z, boolean z1, Function1 function10) {
        Object object0;
        Throwable throwable0;
        JobNode jobNode0 = this.makeNode(function10, z);
        while(true) {
            do {
            label_1:
                throwable0 = null;
                object0 = this.getState$kotlinx_coroutines_core();
                if(!(object0 instanceof Empty)) {
                    goto label_9;
                }
                if(!((Empty)object0).isActive()) {
                    this.promoteEmptyToNodeList(((Empty)object0));
                    goto label_1;
                }
            }
            while(!AbstractResolvableFuture.SafeAtomicHelper..ExternalSyntheticBackportWithForwarding0.m(JobSupport._state$FU, this, object0, jobNode0));
            return jobNode0;
        label_9:
            if(!(object0 instanceof Incomplete)) {
                break;
            }
            NodeList nodeList0 = ((Incomplete)object0).getList();
            if(nodeList0 == null) {
                Intrinsics.checkNotNull(object0, "null cannot be cast to non-null type kotlinx.coroutines.JobNode");
                this.promoteSingleToNodeList(((JobNode)object0));
                goto label_1;
            }
            NonDisposableHandle nonDisposableHandle0 = NonDisposableHandle.INSTANCE;
            if(z && object0 instanceof Finishing) {
                synchronized(object0) {
                    throwable0 = ((Finishing)object0).getRootCause();
                    if(throwable0 == null || function10 instanceof ChildHandleNode && !((Finishing)object0).isCompleting()) {
                        if(this.addLastAtomic(object0, nodeList0, jobNode0)) {
                            if(throwable0 == null) {
                                return jobNode0;
                            }
                            nonDisposableHandle0 = jobNode0;
                        }
                        else {
                            goto label_1;
                        }
                    }
                }
            }
            if(throwable0 != null) {
                if(z1) {
                    function10.invoke(throwable0);
                }
                return nonDisposableHandle0;
            }
            if(this.addLastAtomic(object0, nodeList0, jobNode0)) {
                return jobNode0;
            }
        }
        if(z1) {
            CompletedExceptionally completedExceptionally0 = object0 instanceof CompletedExceptionally ? ((CompletedExceptionally)object0) : null;
            if(completedExceptionally0 != null) {
                throwable0 = completedExceptionally0.cause;
            }
            function10.invoke(throwable0);
        }
        return NonDisposableHandle.INSTANCE;
    }

    @Override  // kotlinx.coroutines.Job
    public boolean isActive() {
        Object object0 = this.getState$kotlinx_coroutines_core();
        return object0 instanceof Incomplete && ((Incomplete)object0).isActive();
    }

    @Override  // kotlinx.coroutines.Job
    public final boolean isCancelled() {
        Object object0 = this.getState$kotlinx_coroutines_core();
        return object0 instanceof CompletedExceptionally || object0 instanceof Finishing && ((Finishing)object0).isCancelling();
    }

    // 去混淆评级： 低(20)
    private final boolean isCancelling(Incomplete incomplete0) {
        return incomplete0 instanceof Finishing && ((Finishing)incomplete0).isCancelling();
    }

    @Override  // kotlinx.coroutines.Job
    public final boolean isCompleted() {
        return !(this.getState$kotlinx_coroutines_core() instanceof Incomplete);
    }

    public final boolean isCompletedExceptionally() {
        return this.getState$kotlinx_coroutines_core() instanceof CompletedExceptionally;
    }

    protected boolean isScopedCoroutine() {
        return false;
    }

    @Override  // kotlinx.coroutines.Job
    public final Object join(Continuation continuation0) {
        if(!this.joinInternal()) {
            JobKt.ensureActive(continuation0.getContext());
            return Unit.INSTANCE;
        }
        Object object0 = this.joinSuspend(continuation0);
        return object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? object0 : Unit.INSTANCE;
    }

    private final boolean joinInternal() {
        do {
            Object object0 = this.getState$kotlinx_coroutines_core();
            if(!(object0 instanceof Incomplete)) {
                return false;
            }
        }
        while(this.startInternal(object0) < 0);
        return true;
    }

    private final Object joinSuspend(Continuation continuation0) {
        CancellableContinuationImpl cancellableContinuationImpl0 = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation0), 1);
        cancellableContinuationImpl0.initCancellability();
        CancellableContinuationKt.disposeOnCancellation(cancellableContinuationImpl0, this.invokeOnCompletion(new ResumeOnCompletion(cancellableContinuationImpl0)));
        Object object0 = cancellableContinuationImpl0.getResult();
        if(object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation0);
        }
        return object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? object0 : Unit.INSTANCE;
    }

    private final void loop$atomicfu(AtomicReferenceFieldUpdater atomicReferenceFieldUpdater0, Function1 function10, Object object0) {
        while(true) {
            function10.invoke(atomicReferenceFieldUpdater0.get(object0));
        }
    }

    private final Void loopOnState(Function1 function10) {
        while(true) {
            function10.invoke(this.getState$kotlinx_coroutines_core());
        }
    }

    private final Object makeCancelling(Object object0) {
        Object object2;
        Object object1;
        Throwable throwable0 = null;
        Throwable throwable1 = null;
        do {
            do {
                object1 = this.getState$kotlinx_coroutines_core();
                if(object1 instanceof Finishing) {
                    synchronized(object1) {
                        if(((Finishing)object1).isSealed()) {
                            return JobSupportKt.access$getTOO_LATE_TO_CANCEL$p();
                        }
                        boolean z = ((Finishing)object1).isCancelling();
                        if(object0 != null || !z) {
                            if(throwable1 == null) {
                                throwable1 = this.createCauseException(object0);
                            }
                            ((Finishing)object1).addExceptionLocked(throwable1);
                        }
                        Throwable throwable2 = ((Finishing)object1).getRootCause();
                        if(!z) {
                            throwable0 = throwable2;
                        }
                    }
                    if(throwable0 != null) {
                        this.notifyCancelling(((Finishing)object1).getList(), throwable0);
                    }
                    return JobSupportKt.access$getCOMPLETING_ALREADY$p();
                }
                if(!(object1 instanceof Incomplete)) {
                    return JobSupportKt.access$getTOO_LATE_TO_CANCEL$p();
                }
                if(throwable1 == null) {
                    throwable1 = this.createCauseException(object0);
                }
                if(!((Incomplete)object1).isActive()) {
                    goto label_29;
                }
            }
            while(!this.tryMakeCancelling(((Incomplete)object1), throwable1));
            return JobSupportKt.access$getCOMPLETING_ALREADY$p();
        label_29:
            object2 = this.tryMakeCompleting(object1, new CompletedExceptionally(throwable1, false, 2, null));
            if(object2 == JobSupportKt.access$getCOMPLETING_ALREADY$p()) {
                throw new IllegalStateException(("Cannot happen in " + object1).toString());
            }
        }
        while(object2 == JobSupportKt.access$getCOMPLETING_RETRY$p());
        return object2;
    }

    public final boolean makeCompleting$kotlinx_coroutines_core(Object object0) {
        Object object1;
        do {
            object1 = this.tryMakeCompleting(this.getState$kotlinx_coroutines_core(), object0);
            if(object1 == JobSupportKt.access$getCOMPLETING_ALREADY$p()) {
                return false;
            }
            if(object1 == JobSupportKt.COMPLETING_WAITING_CHILDREN) {
                return true;
            }
        }
        while(object1 == JobSupportKt.access$getCOMPLETING_RETRY$p());
        this.afterCompletion(object1);
        return true;
    }

    public final Object makeCompletingOnce$kotlinx_coroutines_core(Object object0) {
        Object object1;
        while((object1 = this.tryMakeCompleting(this.getState$kotlinx_coroutines_core(), object0)) != JobSupportKt.access$getCOMPLETING_ALREADY$p()) {
            if(object1 != JobSupportKt.access$getCOMPLETING_RETRY$p()) {
                return object1;
            }
        }
        throw new IllegalStateException("Job " + this + " is already complete or completing, but is being completed with " + object0, this.getExceptionOrNull(object0));
    }

    private final JobNode makeNode(Function1 function10, boolean z) {
        JobNode jobNode0 = null;
        if(z) {
            if(function10 instanceof JobCancellingNode) {
                jobNode0 = (JobCancellingNode)function10;
            }
            if(jobNode0 == null) {
                jobNode0 = new InvokeOnCancelling(function10);
            }
        }
        else {
            if(function10 instanceof JobNode) {
                jobNode0 = (JobNode)function10;
            }
            if(jobNode0 == null) {
                jobNode0 = new InvokeOnCompletion(function10);
            }
        }
        jobNode0.setJob(this);
        return jobNode0;
    }

    @Override  // kotlin.coroutines.CoroutineContext$Element
    public CoroutineContext minusKey(Key coroutineContext$Key0) {
        return DefaultImpls.minusKey(this, coroutineContext$Key0);
    }

    public String nameString$kotlinx_coroutines_core() {
        return DebugStringsKt.getClassSimpleName(this);
    }

    private final ChildHandleNode nextChild(LockFreeLinkedListNode lockFreeLinkedListNode0) {
        while(lockFreeLinkedListNode0.isRemoved()) {
            lockFreeLinkedListNode0 = lockFreeLinkedListNode0.getPrevNode();
        }
        do {
            do {
                lockFreeLinkedListNode0 = lockFreeLinkedListNode0.getNextNode();
            }
            while(lockFreeLinkedListNode0.isRemoved());
            if(lockFreeLinkedListNode0 instanceof ChildHandleNode) {
                return (ChildHandleNode)lockFreeLinkedListNode0;
            }
        }
        while(!(lockFreeLinkedListNode0 instanceof NodeList));
        return null;
    }

    private final void notifyCancelling(NodeList nodeList0, Throwable throwable0) {
        this.onCancelling(throwable0);
        Object object0 = nodeList0.getNext();
        Intrinsics.checkNotNull(object0, "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode{ kotlinx.coroutines.internal.LockFreeLinkedListKt.Node }");
        LockFreeLinkedListNode lockFreeLinkedListNode0 = (LockFreeLinkedListNode)object0;
        CompletionHandlerException completionHandlerException0 = null;
        while(!Intrinsics.areEqual(lockFreeLinkedListNode0, nodeList0)) {
            if(lockFreeLinkedListNode0 instanceof JobCancellingNode) {
                JobNode jobNode0 = (JobNode)lockFreeLinkedListNode0;
                try {
                    jobNode0.invoke(throwable0);
                }
                catch(Throwable throwable1) {
                    if(completionHandlerException0 == null) {
                        completionHandlerException0 = new CompletionHandlerException("Exception in completion handler " + jobNode0 + " for " + this, throwable1);
                    }
                    else {
                        ExceptionsKt.addSuppressed(completionHandlerException0, throwable1);
                        if(completionHandlerException0 == null) {
                            completionHandlerException0 = new CompletionHandlerException("Exception in completion handler " + jobNode0 + " for " + this, throwable1);
                        }
                    }
                }
            }
            lockFreeLinkedListNode0 = lockFreeLinkedListNode0.getNextNode();
        }
        if(completionHandlerException0 != null) {
            this.handleOnCompletionException$kotlinx_coroutines_core(completionHandlerException0);
        }
        this.cancelParent(throwable0);
    }

    private final void notifyCompletion(NodeList nodeList0, Throwable throwable0) {
        Object object0 = nodeList0.getNext();
        Intrinsics.checkNotNull(object0, "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode{ kotlinx.coroutines.internal.LockFreeLinkedListKt.Node }");
        LockFreeLinkedListNode lockFreeLinkedListNode0 = (LockFreeLinkedListNode)object0;
        CompletionHandlerException completionHandlerException0 = null;
        while(!Intrinsics.areEqual(lockFreeLinkedListNode0, nodeList0)) {
            if(lockFreeLinkedListNode0 instanceof JobNode) {
                JobNode jobNode0 = (JobNode)lockFreeLinkedListNode0;
                try {
                    jobNode0.invoke(throwable0);
                }
                catch(Throwable throwable1) {
                    if(completionHandlerException0 == null) {
                        completionHandlerException0 = new CompletionHandlerException("Exception in completion handler " + jobNode0 + " for " + this, throwable1);
                    }
                    else {
                        ExceptionsKt.addSuppressed(completionHandlerException0, throwable1);
                        if(completionHandlerException0 == null) {
                            completionHandlerException0 = new CompletionHandlerException("Exception in completion handler " + jobNode0 + " for " + this, throwable1);
                        }
                    }
                }
            }
            lockFreeLinkedListNode0 = lockFreeLinkedListNode0.getNextNode();
        }
        if(completionHandlerException0 != null) {
            this.handleOnCompletionException$kotlinx_coroutines_core(completionHandlerException0);
        }
    }

    private final void notifyHandlers(NodeList nodeList0, Throwable throwable0) {
        Object object0 = nodeList0.getNext();
        Intrinsics.checkNotNull(object0, "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode{ kotlinx.coroutines.internal.LockFreeLinkedListKt.Node }");
        LockFreeLinkedListNode lockFreeLinkedListNode0 = (LockFreeLinkedListNode)object0;
        CompletionHandlerException completionHandlerException0 = null;
        while(!Intrinsics.areEqual(lockFreeLinkedListNode0, nodeList0)) {
            Intrinsics.reifiedOperationMarker(3, "T");
            if(lockFreeLinkedListNode0 instanceof LockFreeLinkedListNode) {
                JobNode jobNode0 = (JobNode)lockFreeLinkedListNode0;
                try {
                    jobNode0.invoke(throwable0);
                }
                catch(Throwable throwable1) {
                    if(completionHandlerException0 == null) {
                        completionHandlerException0 = new CompletionHandlerException("Exception in completion handler " + jobNode0 + " for " + this, throwable1);
                    }
                    else {
                        ExceptionsKt.addSuppressed(completionHandlerException0, throwable1);
                        if(completionHandlerException0 == null) {
                            completionHandlerException0 = new CompletionHandlerException("Exception in completion handler " + jobNode0 + " for " + this, throwable1);
                        }
                    }
                }
            }
            lockFreeLinkedListNode0 = lockFreeLinkedListNode0.getNextNode();
        }
        if(completionHandlerException0 != null) {
            this.handleOnCompletionException$kotlinx_coroutines_core(completionHandlerException0);
        }
    }

    private final Object onAwaitInternalProcessResFunc(Object object0, Object object1) {
        if(object1 instanceof CompletedExceptionally) {
            throw ((CompletedExceptionally)object1).cause;
        }
        return object1;
    }

    private final void onAwaitInternalRegFunc(SelectInstance selectInstance0, Object object0) {
        do {
            Object object1 = this.getState$kotlinx_coroutines_core();
            if(!(object1 instanceof Incomplete)) {
                if(!(object1 instanceof CompletedExceptionally)) {
                    object1 = JobSupportKt.unboxState(object1);
                }
                selectInstance0.selectInRegistrationPhase(object1);
                return;
            }
        }
        while(this.startInternal(object1) < 0);
        selectInstance0.disposeOnCompletion(this.invokeOnCompletion(new SelectOnAwaitCompletionHandler(this, selectInstance0)));
    }

    protected void onCancelling(Throwable throwable0) {
    }

    protected void onCompletionInternal(Object object0) {
    }

    protected void onStart() {
    }

    @Override  // kotlinx.coroutines.ChildJob
    public final void parentCancelled(ParentJob parentJob0) {
        this.cancelImpl$kotlinx_coroutines_core(parentJob0);
    }

    @Override  // kotlin.coroutines.CoroutineContext
    public CoroutineContext plus(CoroutineContext coroutineContext0) {
        return DefaultImpls.plus(this, coroutineContext0);
    }

    @Override  // kotlinx.coroutines.Job
    @Deprecated(level = DeprecationLevel.ERROR, message = "Operator \'+\' on two Job objects is meaningless. Job is a coroutine context element and `+` is a set-sum operator for coroutine contexts. The job to the right of `+` just replaces the job the left of `+`.")
    public Job plus(Job job0) {
        return job0;
    }

    private final void promoteEmptyToNodeList(Empty empty0) {
        NodeList nodeList0 = new NodeList();
        Incomplete incomplete0 = empty0.isActive() ? nodeList0 : new InactiveNodeList(nodeList0);
        AbstractResolvableFuture.SafeAtomicHelper..ExternalSyntheticBackportWithForwarding0.m(JobSupport._state$FU, this, empty0, incomplete0);
    }

    private final void promoteSingleToNodeList(JobNode jobNode0) {
        jobNode0.addOneIfEmpty(new NodeList());
        LockFreeLinkedListNode lockFreeLinkedListNode0 = jobNode0.getNextNode();
        AbstractResolvableFuture.SafeAtomicHelper..ExternalSyntheticBackportWithForwarding0.m(JobSupport._state$FU, this, jobNode0, lockFreeLinkedListNode0);
    }

    private final void registerSelectForOnJoin(SelectInstance selectInstance0, Object object0) {
        if(!this.joinInternal()) {
            selectInstance0.selectInRegistrationPhase(Unit.INSTANCE);
            return;
        }
        selectInstance0.disposeOnCompletion(this.invokeOnCompletion(new SelectOnJoinCompletionHandler(this, selectInstance0)));
    }

    public final void removeNode$kotlinx_coroutines_core(JobNode jobNode0) {
        while(true) {
            Object object0 = this.getState$kotlinx_coroutines_core();
            if(!(object0 instanceof JobNode)) {
                break;
            }
            if(object0 != jobNode0 || AbstractResolvableFuture.SafeAtomicHelper..ExternalSyntheticBackportWithForwarding0.m(JobSupport._state$FU, this, object0, JobSupportKt.access$getEMPTY_ACTIVE$p())) {
                return;
            }
        }
        if(object0 instanceof Incomplete && ((Incomplete)object0).getList() != null) {
            jobNode0.remove();
        }
    }

    public final void setParentHandle$kotlinx_coroutines_core(ChildHandle childHandle0) {
        JobSupport._parentHandle$FU.set(this, childHandle0);
    }

    @Override  // kotlinx.coroutines.Job
    public final boolean start() {
    alab1:
        while(true) {
            switch(this.startInternal(this.getState$kotlinx_coroutines_core())) {
                case 0: {
                    return false;
                }
                case 1: {
                    break alab1;
                }
            }
        }
        return true;
    }

    private final int startInternal(Object object0) {
        if(object0 instanceof Empty) {
            if(((Empty)object0).isActive()) {
                return 0;
            }
            if(!AbstractResolvableFuture.SafeAtomicHelper..ExternalSyntheticBackportWithForwarding0.m(JobSupport._state$FU, this, object0, JobSupportKt.access$getEMPTY_ACTIVE$p())) {
                return -1;
            }
            this.onStart();
            return 1;
        }
        if(object0 instanceof InactiveNodeList) {
            NodeList nodeList0 = ((InactiveNodeList)object0).getList();
            if(!AbstractResolvableFuture.SafeAtomicHelper..ExternalSyntheticBackportWithForwarding0.m(JobSupport._state$FU, this, object0, nodeList0)) {
                return -1;
            }
            this.onStart();
            return 1;
        }
        return 0;
    }

    private final String stateString(Object object0) {
        if(object0 instanceof Finishing) {
            if(((Finishing)object0).isCancelling()) {
                return "Cancelling";
            }
            return ((Finishing)object0).isCompleting() ? "Completing" : "Active";
        }
        if(object0 instanceof Incomplete) {
            return ((Incomplete)object0).isActive() ? "Active" : "New";
        }
        return object0 instanceof CompletedExceptionally ? "Cancelled" : "Completed";
    }

    protected final CancellationException toCancellationException(Throwable throwable0, String s) {
        CancellationException cancellationException0 = throwable0 instanceof CancellationException ? ((CancellationException)throwable0) : null;
        if(cancellationException0 == null) {
            if(s == null) {
                s = JobSupport.access$cancellationExceptionMessage(this);
            }
            return new JobCancellationException(s, throwable0, this);
        }
        return cancellationException0;
    }

    public static CancellationException toCancellationException$default(JobSupport jobSupport0, Throwable throwable0, String s, int v, Object object0) {
        if(object0 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: toCancellationException");
        }
        if((v & 1) != 0) {
            s = null;
        }
        return jobSupport0.toCancellationException(throwable0, s);
    }

    public final String toDebugString() {
        return this.nameString$kotlinx_coroutines_core() + '{' + this.stateString(this.getState$kotlinx_coroutines_core()) + '}';
    }

    @Override
    public String toString() {
        return this.toDebugString() + '@' + DebugStringsKt.getHexAddress(this);
    }

    private final boolean tryFinalizeSimpleState(Incomplete incomplete0, Object object0) {
        Object object1 = JobSupportKt.boxIncomplete(object0);
        if(!AbstractResolvableFuture.SafeAtomicHelper..ExternalSyntheticBackportWithForwarding0.m(JobSupport._state$FU, this, incomplete0, object1)) {
            return false;
        }
        this.onCancelling(null);
        this.onCompletionInternal(object0);
        this.completeStateFinalization(incomplete0, object0);
        return true;
    }

    private final boolean tryMakeCancelling(Incomplete incomplete0, Throwable throwable0) {
        NodeList nodeList0 = this.getOrPromoteCancellingList(incomplete0);
        if(nodeList0 == null) {
            return false;
        }
        Finishing jobSupport$Finishing0 = new Finishing(nodeList0, false, throwable0);
        if(!AbstractResolvableFuture.SafeAtomicHelper..ExternalSyntheticBackportWithForwarding0.m(JobSupport._state$FU, this, incomplete0, jobSupport$Finishing0)) {
            return false;
        }
        this.notifyCancelling(nodeList0, throwable0);
        return true;
    }

    private final Object tryMakeCompleting(Object object0, Object object1) {
        if(!(object0 instanceof Incomplete)) {
            return JobSupportKt.access$getCOMPLETING_ALREADY$p();
        }
        if((object0 instanceof Empty || object0 instanceof JobNode) && !(object0 instanceof ChildHandleNode) && !(object1 instanceof CompletedExceptionally)) {
            return this.tryFinalizeSimpleState(((Incomplete)object0), object1) ? object1 : JobSupportKt.access$getCOMPLETING_RETRY$p();
        }
        return this.tryMakeCompletingSlowPath(((Incomplete)object0), object1);
    }

    private final Object tryMakeCompletingSlowPath(Incomplete incomplete0, Object object0) {
        Throwable throwable0 = null;
        NodeList nodeList0 = this.getOrPromoteCancellingList(incomplete0);
        if(nodeList0 == null) {
            return JobSupportKt.access$getCOMPLETING_RETRY$p();
        }
        Finishing jobSupport$Finishing0 = incomplete0 instanceof Finishing ? ((Finishing)incomplete0) : null;
        if(jobSupport$Finishing0 == null) {
            jobSupport$Finishing0 = new Finishing(nodeList0, false, null);
        }
        ObjectRef ref$ObjectRef0 = new ObjectRef();
        synchronized(jobSupport$Finishing0) {
            if(jobSupport$Finishing0.isCompleting()) {
                return JobSupportKt.access$getCOMPLETING_ALREADY$p();
            }
            jobSupport$Finishing0.setCompleting(true);
            if(jobSupport$Finishing0 != incomplete0 && !AbstractResolvableFuture.SafeAtomicHelper..ExternalSyntheticBackportWithForwarding0.m(JobSupport._state$FU, this, incomplete0, jobSupport$Finishing0)) {
                return JobSupportKt.access$getCOMPLETING_RETRY$p();
            }
            boolean z = jobSupport$Finishing0.isCancelling();
            CompletedExceptionally completedExceptionally0 = object0 instanceof CompletedExceptionally ? ((CompletedExceptionally)object0) : null;
            if(completedExceptionally0 != null) {
                jobSupport$Finishing0.addExceptionLocked(completedExceptionally0.cause);
            }
            Throwable throwable1 = jobSupport$Finishing0.getRootCause();
            Boolean.valueOf(((boolean)(true ^ z))).getClass();
            if(!z) {
                throwable0 = throwable1;
            }
            ref$ObjectRef0.element = throwable0;
        }
        Throwable throwable2 = (Throwable)ref$ObjectRef0.element;
        if(throwable2 != null) {
            this.notifyCancelling(nodeList0, throwable2);
        }
        ChildHandleNode childHandleNode0 = this.firstChild(incomplete0);
        return childHandleNode0 != null && this.tryWaitForChild(jobSupport$Finishing0, childHandleNode0, object0) ? JobSupportKt.COMPLETING_WAITING_CHILDREN : this.finalizeFinishingState(jobSupport$Finishing0, object0);
    }

    private final boolean tryWaitForChild(Finishing jobSupport$Finishing0, ChildHandleNode childHandleNode0, Object object0) {
        do {
            Function1 function10 = new ChildCompletion(this, jobSupport$Finishing0, childHandleNode0, object0);
            if(DefaultImpls.invokeOnCompletion$default(childHandleNode0.childJob, false, false, function10, 1, null) != NonDisposableHandle.INSTANCE) {
                return true;
            }
            childHandleNode0 = this.nextChild(childHandleNode0);
        }
        while(childHandleNode0 != null);
        return false;
    }
}

