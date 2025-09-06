package kotlinx.coroutines.debug.internal;

import _COROUTINE.ArtificialStackFrames;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.concurrent.ThreadsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.jvm.Volatile;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.ranges.RangesKt;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineId;
import kotlinx.coroutines.CoroutineName;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;
import kotlinx.coroutines.JobSupport;
import kotlinx.coroutines.internal.ScopeCoroutine;

@Metadata(d1 = {"\u0000\u00C6\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0017\n\u0002\u0010\u0003\n\u0002\b\n\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u00C1\u0002\u0018\u00002\u00020\u0001:\u0001}B\u0007\b\u0002\u00A2\u0006\u0002\u0010\u0002J,\u00101\u001A\b\u0012\u0004\u0012\u0002H302\"\u0004\b\u0000\u001032\f\u00104\u001A\b\u0012\u0004\u0012\u0002H3022\b\u00105\u001A\u0004\u0018\u000106H\u0002J\u0010\u00107\u001A\u00020\u00142\u0006\u00108\u001A\u000209H\u0001J\f\u0010:\u001A\b\u0012\u0004\u0012\u00020<0;J\u0011\u0010=\u001A\b\u0012\u0004\u0012\u00020\u00010>\u00A2\u0006\u0002\u0010?J9\u0010@\u001A\b\u0012\u0004\u0012\u0002HA0;\"\b\b\u0000\u0010A*\u00020\u00012\u001E\b\u0004\u0010B\u001A\u0018\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000B\u0012\u0004\u0012\u00020D\u0012\u0004\u0012\u0002HA0CH\u0082\bJ\u0010\u0010E\u001A\u00020\u00142\u0006\u00108\u001A\u000209H\u0002J\f\u0010F\u001A\b\u0012\u0004\u0012\u00020G0;J\"\u0010H\u001A\b\u0012\u0004\u0012\u00020\u00040;2\u0006\u0010I\u001A\u00020<2\f\u0010J\u001A\b\u0012\u0004\u0012\u00020\u00040;J\u000E\u0010K\u001A\u00020)2\u0006\u0010I\u001A\u00020<J.\u0010L\u001A\b\u0012\u0004\u0012\u00020\u00040;2\u0006\u0010M\u001A\u00020)2\b\u0010N\u001A\u0004\u0018\u00010\'2\f\u0010J\u001A\b\u0012\u0004\u0012\u00020\u00040;H\u0002J=\u0010O\u001A\u000E\u0012\u0004\u0012\u00020Q\u0012\u0004\u0012\u00020Q0P2\u0006\u0010R\u001A\u00020Q2\f\u0010S\u001A\b\u0012\u0004\u0012\u00020\u00040>2\f\u0010J\u001A\b\u0012\u0004\u0012\u00020\u00040;H\u0002\u00A2\u0006\u0002\u0010TJ1\u0010U\u001A\u00020Q2\u0006\u0010V\u001A\u00020Q2\f\u0010S\u001A\b\u0012\u0004\u0012\u00020\u00040>2\f\u0010J\u001A\b\u0012\u0004\u0012\u00020\u00040;H\u0002\u00A2\u0006\u0002\u0010WJ\u0016\u0010X\u001A\u0010\u0012\u0004\u0012\u00020\u000F\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0013H\u0002J\u0015\u0010Y\u001A\u00020)2\u0006\u0010Z\u001A\u00020*H\u0000\u00A2\u0006\u0002\b[J\r\u0010\\\u001A\u00020\u0014H\u0000\u00A2\u0006\u0002\b]J\u001E\u0010^\u001A\u00020\u00142\u0006\u00108\u001A\u0002092\f\u0010_\u001A\b\u0012\u0004\u0012\u00020\u00040;H\u0002J\u0014\u0010`\u001A\u00020\u00142\n\u0010a\u001A\u0006\u0012\u0002\b\u00030\u000BH\u0002J\'\u0010b\u001A\b\u0012\u0004\u0012\u0002H302\"\u0004\b\u0000\u001032\f\u00104\u001A\b\u0012\u0004\u0012\u0002H302H\u0000\u00A2\u0006\u0002\bcJ\u0019\u0010d\u001A\u00020\u00142\n\u00105\u001A\u0006\u0012\u0002\b\u000302H\u0000\u00A2\u0006\u0002\beJ\u0019\u0010f\u001A\u00020\u00142\n\u00105\u001A\u0006\u0012\u0002\b\u000302H\u0000\u00A2\u0006\u0002\bgJ%\u0010h\u001A\b\u0012\u0004\u0012\u00020\u00040;\"\b\b\u0000\u00103*\u00020i2\u0006\u0010j\u001A\u0002H3H\u0002\u00A2\u0006\u0002\u0010kJ\b\u0010l\u001A\u00020\u0014H\u0002J\b\u0010m\u001A\u00020\u0014H\u0002J\r\u0010n\u001A\u00020\u0014H\u0000\u00A2\u0006\u0002\boJ\u0018\u0010p\u001A\u00020\u00142\u0006\u00105\u001A\u00020\u00072\u0006\u0010M\u001A\u00020)H\u0002J\u001C\u0010q\u001A\u00020\u00142\n\u00105\u001A\u0006\u0012\u0002\b\u0003022\u0006\u0010M\u001A\u00020)H\u0002J(\u0010q\u001A\u00020\u00142\n\u0010a\u001A\u0006\u0012\u0002\b\u00030\u000B2\n\u00105\u001A\u0006\u0012\u0002\b\u0003022\u0006\u0010M\u001A\u00020)H\u0002J4\u0010r\u001A\u00020\u0014*\u00020*2\u0012\u0010s\u001A\u000E\u0012\u0004\u0012\u00020*\u0012\u0004\u0012\u00020\b0t2\n\u0010u\u001A\u00060vj\u0002`w2\u0006\u0010x\u001A\u00020)H\u0002J\u0010\u0010y\u001A\u00020\u000F*\u0006\u0012\u0002\b\u00030\u000BH\u0002J\u0016\u0010a\u001A\b\u0012\u0002\b\u0003\u0018\u00010\u000B*\u0006\u0012\u0002\b\u000302H\u0002J\u0013\u0010a\u001A\b\u0012\u0002\b\u0003\u0018\u00010\u000B*\u00020\u0007H\u0082\u0010J\u000F\u0010z\u001A\u0004\u0018\u00010\u0007*\u00020\u0007H\u0082\u0010J\u0012\u0010{\u001A\u000206*\b\u0012\u0004\u0012\u00020\u00040;H\u0002J\f\u0010|\u001A\u00020)*\u00020\u0001H\u0002R\u000E\u0010\u0003\u001A\u00020\u0004X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u001A\u0010\u0005\u001A\u000E\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u001E\u0010\t\u001A\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000B0\n8BX\u0082\u0004\u00A2\u0006\u0006\u001A\u0004\b\f\u0010\rR\u001E\u0010\u000E\u001A\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000B\u0012\u0004\u0012\u00020\u000F0\u0006X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u0010\u001A\u00020\u0011X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u001C\u0010\u0012\u001A\u0010\u0012\u0004\u0012\u00020\u000F\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0013X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u001A\u0010\u0015\u001A\u00020\u000FX\u0080\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001A\u0010\u001A\u001A\u00020\u000FX\u0086\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b\u001B\u0010\u0017\"\u0004\b\u001C\u0010\u0019R\t\u0010\u001D\u001A\u00020\u001EX\u0082\u0004R\u0011\u0010\u001F\u001A\u00020\u000F8G\u00A2\u0006\u0006\u001A\u0004\b \u0010\u0017R\u001A\u0010!\u001A\u00020\u000FX\u0080\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b\"\u0010\u0017\"\u0004\b#\u0010\u0019R\t\u0010$\u001A\u00020%X\u0082\u0004R\u0010\u0010&\u001A\u0004\u0018\u00010\'X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u001E\u0010(\u001A\u00020)*\u00020*8BX\u0082\u0004\u00A2\u0006\f\u0012\u0004\b+\u0010,\u001A\u0004\b-\u0010.R\u0018\u0010/\u001A\u00020\u000F*\u00020\u00048BX\u0082\u0004\u00A2\u0006\u0006\u001A\u0004\b/\u00100\u00A8\u0006~"}, d2 = {"Lkotlinx/coroutines/debug/internal/DebugProbesImpl;", "", "()V", "ARTIFICIAL_FRAME", "Ljava/lang/StackTraceElement;", "callerInfoCache", "Lkotlinx/coroutines/debug/internal/ConcurrentWeakMap;", "Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "Lkotlinx/coroutines/debug/internal/DebugCoroutineInfoImpl;", "capturedCoroutines", "", "Lkotlinx/coroutines/debug/internal/DebugProbesImpl$CoroutineOwner;", "getCapturedCoroutines", "()Ljava/util/Set;", "capturedCoroutinesMap", "", "dateFormat", "Ljava/text/SimpleDateFormat;", "dynamicAttach", "Lkotlin/Function1;", "", "enableCreationStackTraces", "getEnableCreationStackTraces$kotlinx_coroutines_core", "()Z", "setEnableCreationStackTraces$kotlinx_coroutines_core", "(Z)V", "ignoreCoroutinesWithEmptyContext", "getIgnoreCoroutinesWithEmptyContext", "setIgnoreCoroutinesWithEmptyContext", "installations", "Lkotlinx/atomicfu/AtomicInt;", "isInstalled", "isInstalled$kotlinx_coroutines_debug", "sanitizeStackTraces", "getSanitizeStackTraces$kotlinx_coroutines_core", "setSanitizeStackTraces$kotlinx_coroutines_core", "sequenceNumber", "Lkotlinx/atomicfu/AtomicLong;", "weakRefCleanerThread", "Ljava/lang/Thread;", "debugString", "", "Lkotlinx/coroutines/Job;", "getDebugString$annotations", "(Lkotlinx/coroutines/Job;)V", "getDebugString", "(Lkotlinx/coroutines/Job;)Ljava/lang/String;", "isInternalMethod", "(Ljava/lang/StackTraceElement;)Z", "createOwner", "Lkotlin/coroutines/Continuation;", "T", "completion", "frame", "Lkotlinx/coroutines/debug/internal/StackTraceFrame;", "dumpCoroutines", "out", "Ljava/io/PrintStream;", "dumpCoroutinesInfo", "", "Lkotlinx/coroutines/debug/internal/DebugCoroutineInfo;", "dumpCoroutinesInfoAsJsonAndReferences", "", "()[Ljava/lang/Object;", "dumpCoroutinesInfoImpl", "R", "create", "Lkotlin/Function2;", "Lkotlin/coroutines/CoroutineContext;", "dumpCoroutinesSynchronized", "dumpDebuggerInfo", "Lkotlinx/coroutines/debug/internal/DebuggerInfo;", "enhanceStackTraceWithThreadDump", "info", "coroutineTrace", "enhanceStackTraceWithThreadDumpAsJson", "enhanceStackTraceWithThreadDumpImpl", "state", "thread", "findContinuationStartIndex", "Lkotlin/Pair;", "", "indexOfResumeWith", "actualTrace", "(I[Ljava/lang/StackTraceElement;Ljava/util/List;)Lkotlin/Pair;", "findIndexOfFrame", "frameIndex", "(I[Ljava/lang/StackTraceElement;Ljava/util/List;)I", "getDynamicAttach", "hierarchyToString", "job", "hierarchyToString$kotlinx_coroutines_core", "install", "install$kotlinx_coroutines_core", "printStackTrace", "frames", "probeCoroutineCompleted", "owner", "probeCoroutineCreated", "probeCoroutineCreated$kotlinx_coroutines_core", "probeCoroutineResumed", "probeCoroutineResumed$kotlinx_coroutines_core", "probeCoroutineSuspended", "probeCoroutineSuspended$kotlinx_coroutines_core", "sanitizeStackTrace", "", "throwable", "(Ljava/lang/Throwable;)Ljava/util/List;", "startWeakRefCleanerThread", "stopWeakRefCleanerThread", "uninstall", "uninstall$kotlinx_coroutines_core", "updateRunningState", "updateState", "build", "map", "", "builder", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "indent", "isFinished", "realCaller", "toStackTraceFrame", "toStringRepr", "CoroutineOwner", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class DebugProbesImpl {
    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\u00020\u0003B\u001D\b\u0000\u0012\f\u0010\u0004\u001A\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\u0006\u0010\u0005\u001A\u00020\u0006¢\u0006\u0002\u0010\u0007J\n\u0010\u0013\u001A\u0004\u0018\u00010\u0014H\u0016J\u001E\u0010\u0015\u001A\u00020\u00162\f\u0010\u0017\u001A\b\u0012\u0004\u0012\u00028\u00000\u0018H\u0016ø\u0001\u0000¢\u0006\u0002\u0010\u0019J\b\u0010\u001A\u001A\u00020\u001BH\u0016R\u0016\u0010\b\u001A\u0004\u0018\u00010\u00038VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\t\u0010\nR\u0012\u0010\u000B\u001A\u00020\fX\u0096\u0005¢\u0006\u0006\u001A\u0004\b\r\u0010\u000ER\u0016\u0010\u0004\u001A\b\u0012\u0004\u0012\u00028\u00000\u00028\u0000X\u0081\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000F\u001A\u0004\u0018\u00010\u00108BX\u0082\u0004¢\u0006\u0006\u001A\u0004\b\u0011\u0010\u0012R\u0010\u0010\u0005\u001A\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001C"}, d2 = {"Lkotlinx/coroutines/debug/internal/DebugProbesImpl$CoroutineOwner;", "T", "Lkotlin/coroutines/Continuation;", "Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "delegate", "info", "Lkotlinx/coroutines/debug/internal/DebugCoroutineInfoImpl;", "(Lkotlin/coroutines/Continuation;Lkotlinx/coroutines/debug/internal/DebugCoroutineInfoImpl;)V", "callerFrame", "getCallerFrame", "()Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "context", "Lkotlin/coroutines/CoroutineContext;", "getContext", "()Lkotlin/coroutines/CoroutineContext;", "frame", "Lkotlinx/coroutines/debug/internal/StackTraceFrame;", "getFrame", "()Lkotlinx/coroutines/debug/internal/StackTraceFrame;", "getStackTraceElement", "Ljava/lang/StackTraceElement;", "resumeWith", "", "result", "Lkotlin/Result;", "(Ljava/lang/Object;)V", "toString", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class CoroutineOwner implements Continuation, CoroutineStackFrame {
        public final Continuation delegate;
        public final DebugCoroutineInfoImpl info;

        public CoroutineOwner(Continuation continuation0, DebugCoroutineInfoImpl debugCoroutineInfoImpl0) {
            this.delegate = continuation0;
            this.info = debugCoroutineInfoImpl0;
        }

        @Override  // kotlin.coroutines.jvm.internal.CoroutineStackFrame
        public CoroutineStackFrame getCallerFrame() {
            StackTraceFrame stackTraceFrame0 = this.getFrame();
            return stackTraceFrame0 == null ? null : stackTraceFrame0.getCallerFrame();
        }

        @Override  // kotlin.coroutines.Continuation
        public CoroutineContext getContext() {
            return this.delegate.getContext();
        }

        private final StackTraceFrame getFrame() {
            return this.info.getCreationStackBottom$kotlinx_coroutines_core();
        }

        @Override  // kotlin.coroutines.jvm.internal.CoroutineStackFrame
        public StackTraceElement getStackTraceElement() {
            StackTraceFrame stackTraceFrame0 = this.getFrame();
            return stackTraceFrame0 == null ? null : stackTraceFrame0.getStackTraceElement();
        }

        @Override  // kotlin.coroutines.Continuation
        public void resumeWith(Object object0) {
            DebugProbesImpl.INSTANCE.probeCoroutineCompleted(this);
            this.delegate.resumeWith(object0);
        }

        @Override
        public String toString() {
            return this.delegate.toString();
        }
    }

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 0x30)
    static final class Installations.kotlinx.VolatileWrapper {
        @Volatile
        private volatile int installations;
        private static final AtomicIntegerFieldUpdater installations$FU;

        static {
            Installations.kotlinx.VolatileWrapper.installations$FU = AtomicIntegerFieldUpdater.newUpdater(Installations.kotlinx.VolatileWrapper.class, "installations");
        }

        private Installations.kotlinx.VolatileWrapper() {
        }

        public Installations.kotlinx.VolatileWrapper(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public static final AtomicIntegerFieldUpdater access$getInstallations$FU$p() {
            return Installations.kotlinx.VolatileWrapper.installations$FU;
        }
    }

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 0x30)
    static final class SequenceNumber.kotlinx.VolatileWrapper {
        @Volatile
        private volatile long sequenceNumber;
        private static final AtomicLongFieldUpdater sequenceNumber$FU;

        static {
            SequenceNumber.kotlinx.VolatileWrapper.sequenceNumber$FU = AtomicLongFieldUpdater.newUpdater(SequenceNumber.kotlinx.VolatileWrapper.class, "sequenceNumber");
        }

        private SequenceNumber.kotlinx.VolatileWrapper() {
        }

        public SequenceNumber.kotlinx.VolatileWrapper(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public static final AtomicLongFieldUpdater access$getSequenceNumber$FU$p() {
            return SequenceNumber.kotlinx.VolatileWrapper.sequenceNumber$FU;
        }
    }

    private static final StackTraceElement ARTIFICIAL_FRAME;
    public static final DebugProbesImpl INSTANCE;
    private static final ConcurrentWeakMap callerInfoCache;
    private static final ConcurrentWeakMap capturedCoroutinesMap;
    private static final SimpleDateFormat dateFormat;
    private static final Function1 dynamicAttach;
    private static boolean enableCreationStackTraces;
    private static boolean ignoreCoroutinesWithEmptyContext;
    private static final Installations.kotlinx.VolatileWrapper installations$kotlinx$VolatileWrapper;
    private static boolean sanitizeStackTraces;
    private static final SequenceNumber.kotlinx.VolatileWrapper sequenceNumber$kotlinx$VolatileWrapper;
    private static Thread weakRefCleanerThread;

    static {
        DebugProbesImpl debugProbesImpl0 = new DebugProbesImpl();
        DebugProbesImpl.INSTANCE = debugProbesImpl0;
        DebugProbesImpl.ARTIFICIAL_FRAME = new ArtificialStackFrames().coroutineCreation();
        DebugProbesImpl.dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        DebugProbesImpl.capturedCoroutinesMap = new ConcurrentWeakMap(false, 1, null);
        DebugProbesImpl.sanitizeStackTraces = true;
        DebugProbesImpl.enableCreationStackTraces = true;
        DebugProbesImpl.ignoreCoroutinesWithEmptyContext = true;
        DebugProbesImpl.dynamicAttach = debugProbesImpl0.getDynamicAttach();
        DebugProbesImpl.callerInfoCache = new ConcurrentWeakMap(true);
        DebugProbesImpl.installations$kotlinx$VolatileWrapper = new Installations.kotlinx.VolatileWrapper(null);
        DebugProbesImpl.sequenceNumber$kotlinx$VolatileWrapper = new SequenceNumber.kotlinx.VolatileWrapper(null);
    }

    private final void build(Job job0, Map map0, StringBuilder stringBuilder0, String s) {
        DebugCoroutineInfoImpl debugCoroutineInfoImpl0 = (DebugCoroutineInfoImpl)map0.get(job0);
        if(debugCoroutineInfoImpl0 != null) {
            StackTraceElement stackTraceElement0 = (StackTraceElement)CollectionsKt.firstOrNull(debugCoroutineInfoImpl0.lastObservedStackTrace$kotlinx_coroutines_core());
            stringBuilder0.append(s + this.getDebugString(job0) + ", continuation is " + debugCoroutineInfoImpl0.getState$kotlinx_coroutines_core() + " at line " + stackTraceElement0 + '\n');
            s = s + '\t';
        }
        else if(!(job0 instanceof ScopeCoroutine)) {
            stringBuilder0.append(s + this.getDebugString(job0) + '\n');
            s = s + '\t';
        }
        for(Object object0: job0.getChildren()) {
            this.build(((Job)object0), map0, stringBuilder0, s);
        }
    }

    private final Continuation createOwner(Continuation continuation0, StackTraceFrame stackTraceFrame0) {
        if(!this.isInstalled$kotlinx_coroutines_debug()) {
            return continuation0;
        }
        CoroutineOwner debugProbesImpl$CoroutineOwner0 = new CoroutineOwner(continuation0, new DebugCoroutineInfoImpl(continuation0.getContext(), stackTraceFrame0, SequenceNumber.kotlinx.VolatileWrapper.access$getSequenceNumber$FU$p().incrementAndGet(DebugProbesImpl.sequenceNumber$kotlinx$VolatileWrapper)));
        ConcurrentWeakMap concurrentWeakMap0 = DebugProbesImpl.capturedCoroutinesMap;
        concurrentWeakMap0.put(debugProbesImpl$CoroutineOwner0, Boolean.TRUE);
        if(!this.isInstalled$kotlinx_coroutines_debug()) {
            concurrentWeakMap0.clear();
        }
        return debugProbesImpl$CoroutineOwner0;
    }

    public final void dumpCoroutines(PrintStream printStream0) {
        synchronized(printStream0) {
            DebugProbesImpl.INSTANCE.dumpCoroutinesSynchronized(printStream0);
        }
    }

    public final List dumpCoroutinesInfo() {
        if(!this.isInstalled$kotlinx_coroutines_debug()) {
            throw new IllegalStateException("Debug probes are not installed");
        }
        return SequencesKt.toList(SequencesKt.mapNotNull(SequencesKt.sortedWith(CollectionsKt.asSequence(this.getCapturedCoroutines()), new kotlinx.coroutines.debug.internal.DebugProbesImpl.dumpCoroutinesInfoImpl..inlined.sortedBy.1()), new kotlinx.coroutines.debug.internal.DebugProbesImpl.dumpCoroutinesInfo..inlined.dumpCoroutinesInfoImpl.1()));

        @Metadata(d1 = {"\u0000\u0012\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001A\u0004\u0018\u0001H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u00022\n\u0010\u0003\u001A\u0006\u0012\u0002\b\u00030\u0004H\n¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"<anonymous>", "R", "", "owner", "Lkotlinx/coroutines/debug/internal/DebugProbesImpl$CoroutineOwner;", "invoke", "(Lkotlinx/coroutines/debug/internal/DebugProbesImpl$CoroutineOwner;)Ljava/lang/Object;", "kotlinx/coroutines/debug/internal/DebugProbesImpl$dumpCoroutinesInfoImpl$3"}, k = 3, mv = {1, 8, 0}, xi = 0x30)
        public final class kotlinx.coroutines.debug.internal.DebugProbesImpl.dumpCoroutinesInfo..inlined.dumpCoroutinesInfoImpl.1 extends Lambda implements Function1 {
            public kotlinx.coroutines.debug.internal.DebugProbesImpl.dumpCoroutinesInfo..inlined.dumpCoroutinesInfoImpl.1() {
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((CoroutineOwner)object0));
            }

            public final Object invoke(CoroutineOwner debugProbesImpl$CoroutineOwner0) {
                if(DebugProbesImpl.INSTANCE.isFinished(debugProbesImpl$CoroutineOwner0)) {
                    return null;
                }
                CoroutineContext coroutineContext0 = debugProbesImpl$CoroutineOwner0.info.getContext();
                return coroutineContext0 == null ? null : new DebugCoroutineInfo(debugProbesImpl$CoroutineOwner0.info, coroutineContext0);
            }
        }

    }

    public final Object[] dumpCoroutinesInfoAsJsonAndReferences() {
        String s1;
        List list0 = this.dumpCoroutinesInfo();
        int v = list0.size();
        ArrayList arrayList0 = new ArrayList(v);
        ArrayList arrayList1 = new ArrayList(v);
        ArrayList arrayList2 = new ArrayList(v);
        for(Object object0: list0) {
            CoroutineContext coroutineContext0 = ((DebugCoroutineInfo)object0).getContext();
            CoroutineName coroutineName0 = (CoroutineName)coroutineContext0.get(CoroutineName.Key);
            Long long0 = null;
            if(coroutineName0 == null) {
                s1 = null;
            }
            else {
                String s = coroutineName0.getName();
                if(s != null) {
                    s1 = this.toStringRepr(s);
                }
            }
            CoroutineDispatcher coroutineDispatcher0 = (CoroutineDispatcher)coroutineContext0.get(CoroutineDispatcher.Key);
            String s2 = coroutineDispatcher0 == null ? null : this.toStringRepr(coroutineDispatcher0);
            StringBuilder stringBuilder0 = new StringBuilder("\n                {\n                    \"name\": ");
            stringBuilder0.append(s1);
            stringBuilder0.append(",\n                    \"id\": ");
            CoroutineId coroutineId0 = (CoroutineId)coroutineContext0.get(CoroutineId.Key);
            if(coroutineId0 != null) {
                long0 = coroutineId0.getId();
            }
            stringBuilder0.append(long0);
            stringBuilder0.append(",\n                    \"dispatcher\": ");
            stringBuilder0.append(s2);
            stringBuilder0.append(",\n                    \"sequenceNumber\": ");
            stringBuilder0.append(((DebugCoroutineInfo)object0).getSequenceNumber());
            stringBuilder0.append(",\n                    \"state\": \"");
            stringBuilder0.append(((DebugCoroutineInfo)object0).getState());
            stringBuilder0.append("\"\n                } \n                ");
            arrayList2.add(StringsKt.trimIndent(stringBuilder0.toString()));
            arrayList1.add(((DebugCoroutineInfo)object0).getLastObservedFrame());
            arrayList0.add(((DebugCoroutineInfo)object0).getLastObservedThread());
        }
        return new Object[]{"[" + CollectionsKt.joinToString$default(arrayList2, null, null, null, 0, null, null, 0x3F, null) + ']', arrayList0.toArray(new Thread[0]), arrayList1.toArray(new CoroutineStackFrame[0]), list0.toArray(new DebugCoroutineInfo[0])};
    }

    private final List dumpCoroutinesInfoImpl(Function2 function20) {
        if(!this.isInstalled$kotlinx_coroutines_debug()) {
            throw new IllegalStateException("Debug probes are not installed");
        }
        return SequencesKt.toList(SequencesKt.mapNotNull(SequencesKt.sortedWith(CollectionsKt.asSequence(this.getCapturedCoroutines()), new kotlinx.coroutines.debug.internal.DebugProbesImpl.dumpCoroutinesInfoImpl..inlined.sortedBy.1()), new Function1() {
            final Function2 $create;

            {
                this.$create = function20;
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((CoroutineOwner)object0));
            }

            public final Object invoke(CoroutineOwner debugProbesImpl$CoroutineOwner0) {
                if(DebugProbesImpl.INSTANCE.isFinished(debugProbesImpl$CoroutineOwner0)) {
                    return null;
                }
                CoroutineContext coroutineContext0 = debugProbesImpl$CoroutineOwner0.info.getContext();
                return coroutineContext0 == null ? null : this.$create.invoke(debugProbesImpl$CoroutineOwner0, coroutineContext0);
            }
        }));

        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\u0010\u0000\u001A\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u000E\u0010\u0003\u001A\n \u0004*\u0004\u0018\u0001H\u0002H\u00022\u000E\u0010\u0005\u001A\n \u0004*\u0004\u0018\u0001H\u0002H\u0002H\n¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"<anonymous>", "", "T", "a", "kotlin.jvm.PlatformType", "b", "compare", "(Ljava/lang/Object;Ljava/lang/Object;)I", "kotlin/comparisons/ComparisonsKt__ComparisonsKt$compareBy$2"}, k = 3, mv = {1, 8, 0}, xi = 0x30)
        public final class kotlinx.coroutines.debug.internal.DebugProbesImpl.dumpCoroutinesInfoImpl..inlined.sortedBy.1 implements Comparator {
            @Override
            public final int compare(Object object0, Object object1) {
                return ComparisonsKt.compareValues(((CoroutineOwner)object0).info.sequenceNumber, ((CoroutineOwner)object1).info.sequenceNumber);
            }
        }

    }

    private final void dumpCoroutinesSynchronized(PrintStream printStream0) {
        if(!this.isInstalled$kotlinx_coroutines_debug()) {
            throw new IllegalStateException("Debug probes are not installed");
        }
        printStream0.print("Coroutines dump 2025/09/06 04:46:37");
        for(Object object0: SequencesKt.sortedWith(SequencesKt.filter(CollectionsKt.asSequence(this.getCapturedCoroutines()), kotlinx.coroutines.debug.internal.DebugProbesImpl.dumpCoroutinesSynchronized.2.INSTANCE), new kotlinx.coroutines.debug.internal.DebugProbesImpl.dumpCoroutinesSynchronized..inlined.sortedBy.1())) {
            DebugCoroutineInfoImpl debugCoroutineInfoImpl0 = ((CoroutineOwner)object0).info;
            List list0 = debugCoroutineInfoImpl0.lastObservedStackTrace$kotlinx_coroutines_core();
            DebugProbesImpl debugProbesImpl0 = DebugProbesImpl.INSTANCE;
            List list1 = debugProbesImpl0.enhanceStackTraceWithThreadDumpImpl(debugCoroutineInfoImpl0.getState$kotlinx_coroutines_core(), debugCoroutineInfoImpl0.lastObservedThread, list0);
            String s = !Intrinsics.areEqual(debugCoroutineInfoImpl0.getState$kotlinx_coroutines_core(), "RUNNING") || list1 != list0 ? debugCoroutineInfoImpl0.getState$kotlinx_coroutines_core() : debugCoroutineInfoImpl0.getState$kotlinx_coroutines_core() + " (Last suspension stacktrace, not an actual stacktrace)";
            printStream0.print("\n\nCoroutine " + ((CoroutineOwner)object0).delegate + ", state: " + s);
            if(list0.isEmpty()) {
                printStream0.print("\n\tat " + DebugProbesImpl.ARTIFICIAL_FRAME);
                debugProbesImpl0.printStackTrace(printStream0, debugCoroutineInfoImpl0.getCreationStackTrace());
            }
            else {
                debugProbesImpl0.printStackTrace(printStream0, list1);
            }
        }

        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\u0010\u0000\u001A\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u000E\u0010\u0003\u001A\n \u0004*\u0004\u0018\u0001H\u0002H\u00022\u000E\u0010\u0005\u001A\n \u0004*\u0004\u0018\u0001H\u0002H\u0002H\n¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"<anonymous>", "", "T", "a", "kotlin.jvm.PlatformType", "b", "compare", "(Ljava/lang/Object;Ljava/lang/Object;)I", "kotlin/comparisons/ComparisonsKt__ComparisonsKt$compareBy$2"}, k = 3, mv = {1, 8, 0}, xi = 0x30)
        public final class kotlinx.coroutines.debug.internal.DebugProbesImpl.dumpCoroutinesSynchronized..inlined.sortedBy.1 implements Comparator {
            @Override
            public final int compare(Object object0, Object object1) {
                return ComparisonsKt.compareValues(((CoroutineOwner)object0).info.sequenceNumber, ((CoroutineOwner)object1).info.sequenceNumber);
            }
        }


        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001A\u00020\u00012\n\u0010\u0002\u001A\u0006\u0012\u0002\b\u00030\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "it", "Lkotlinx/coroutines/debug/internal/DebugProbesImpl$CoroutineOwner;", "invoke", "(Lkotlinx/coroutines/debug/internal/DebugProbesImpl$CoroutineOwner;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 0x30)
        final class kotlinx.coroutines.debug.internal.DebugProbesImpl.dumpCoroutinesSynchronized.2 extends Lambda implements Function1 {
            public static final kotlinx.coroutines.debug.internal.DebugProbesImpl.dumpCoroutinesSynchronized.2 INSTANCE;

            static {
                kotlinx.coroutines.debug.internal.DebugProbesImpl.dumpCoroutinesSynchronized.2.INSTANCE = new kotlinx.coroutines.debug.internal.DebugProbesImpl.dumpCoroutinesSynchronized.2();
            }

            kotlinx.coroutines.debug.internal.DebugProbesImpl.dumpCoroutinesSynchronized.2() {
                super(1);
            }

            public final Boolean invoke(CoroutineOwner debugProbesImpl$CoroutineOwner0) {
                return Boolean.valueOf(!DebugProbesImpl.INSTANCE.isFinished(debugProbesImpl$CoroutineOwner0));
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((CoroutineOwner)object0));
            }
        }

    }

    public final List dumpDebuggerInfo() {
        if(!this.isInstalled$kotlinx_coroutines_debug()) {
            throw new IllegalStateException("Debug probes are not installed");
        }
        return SequencesKt.toList(SequencesKt.mapNotNull(SequencesKt.sortedWith(CollectionsKt.asSequence(this.getCapturedCoroutines()), new kotlinx.coroutines.debug.internal.DebugProbesImpl.dumpCoroutinesInfoImpl..inlined.sortedBy.1()), new kotlinx.coroutines.debug.internal.DebugProbesImpl.dumpDebuggerInfo..inlined.dumpCoroutinesInfoImpl.1()));

        @Metadata(d1 = {"\u0000\u0012\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001A\u0004\u0018\u0001H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u00022\n\u0010\u0003\u001A\u0006\u0012\u0002\b\u00030\u0004H\n¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"<anonymous>", "R", "", "owner", "Lkotlinx/coroutines/debug/internal/DebugProbesImpl$CoroutineOwner;", "invoke", "(Lkotlinx/coroutines/debug/internal/DebugProbesImpl$CoroutineOwner;)Ljava/lang/Object;", "kotlinx/coroutines/debug/internal/DebugProbesImpl$dumpCoroutinesInfoImpl$3"}, k = 3, mv = {1, 8, 0}, xi = 0x30)
        public final class kotlinx.coroutines.debug.internal.DebugProbesImpl.dumpDebuggerInfo..inlined.dumpCoroutinesInfoImpl.1 extends Lambda implements Function1 {
            public kotlinx.coroutines.debug.internal.DebugProbesImpl.dumpDebuggerInfo..inlined.dumpCoroutinesInfoImpl.1() {
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((CoroutineOwner)object0));
            }

            public final Object invoke(CoroutineOwner debugProbesImpl$CoroutineOwner0) {
                if(DebugProbesImpl.INSTANCE.isFinished(debugProbesImpl$CoroutineOwner0)) {
                    return null;
                }
                CoroutineContext coroutineContext0 = debugProbesImpl$CoroutineOwner0.info.getContext();
                return coroutineContext0 == null ? null : new DebuggerInfo(debugProbesImpl$CoroutineOwner0.info, coroutineContext0);
            }
        }

    }

    public final List enhanceStackTraceWithThreadDump(DebugCoroutineInfo debugCoroutineInfo0, List list0) {
        return this.enhanceStackTraceWithThreadDumpImpl(debugCoroutineInfo0.getState(), debugCoroutineInfo0.getLastObservedThread(), list0);
    }

    public final String enhanceStackTraceWithThreadDumpAsJson(DebugCoroutineInfo debugCoroutineInfo0) {
        List list0 = this.enhanceStackTraceWithThreadDump(debugCoroutineInfo0, debugCoroutineInfo0.lastObservedStackTrace());
        List list1 = new ArrayList();
        for(Object object0: list0) {
            String s = ((StackTraceElement)object0).getFileName();
            list1.add(StringsKt.trimIndent(("\n                {\n                    \"declaringClass\": \"" + ((StackTraceElement)object0).getClassName() + "\",\n                    \"methodName\": \"" + ((StackTraceElement)object0).getMethodName() + "\",\n                    \"fileName\": " + (s == null ? null : this.toStringRepr(s)) + ",\n                    \"lineNumber\": " + ((StackTraceElement)object0).getLineNumber() + "\n                }\n                ")));
        }
        return "[" + CollectionsKt.joinToString$default(list1, null, null, null, 0, null, null, 0x3F, null) + ']';
    }

    private final List enhanceStackTraceWithThreadDumpImpl(String s, Thread thread0, List list0) {
        Object object0;
        if(Intrinsics.areEqual(s, "RUNNING") && thread0 != null) {
            try {
                object0 = Result.constructor-impl(thread0.getStackTrace());
            }
            catch(Throwable throwable0) {
                object0 = Result.constructor-impl(ResultKt.createFailure(throwable0));
            }
            if(Result.isFailure-impl(object0)) {
                object0 = null;
            }
            if(((StackTraceElement[])object0) != null) {
                int v1;
                for(v1 = 0; true; ++v1) {
                    if(v1 >= ((StackTraceElement[])object0).length) {
                        v1 = -1;
                        break;
                    }
                    StackTraceElement stackTraceElement0 = ((StackTraceElement[])object0)[v1];
                    if(Intrinsics.areEqual(stackTraceElement0.getClassName(), "kotlin.coroutines.jvm.internal.BaseContinuationImpl") && Intrinsics.areEqual(stackTraceElement0.getMethodName(), "resumeWith") && Intrinsics.areEqual(stackTraceElement0.getFileName(), "ContinuationImpl.kt")) {
                        break;
                    }
                }
                Pair pair0 = this.findContinuationStartIndex(v1, ((StackTraceElement[])object0), list0);
                int v2 = ((Number)pair0.component1()).intValue();
                int v3 = ((Number)pair0.component2()).intValue();
                if(v2 != -1) {
                    ArrayList arrayList0 = new ArrayList(list0.size() + v1 - v2 - 1 - v3);
                    for(int v = 0; v < v1 - v3; ++v) {
                        arrayList0.add(((StackTraceElement[])object0)[v]);
                    }
                    int v4 = v2 + 1;
                    int v5 = list0.size();
                    while(v4 < v5) {
                        arrayList0.add(list0.get(v4));
                        ++v4;
                    }
                    return arrayList0;
                }
            }
        }
        return list0;
    }

    private final Pair findContinuationStartIndex(int v, StackTraceElement[] arr_stackTraceElement, List list0) {
        for(int v1 = 0; v1 < 3; ++v1) {
            int v2 = DebugProbesImpl.INSTANCE.findIndexOfFrame(v - 1 - v1, arr_stackTraceElement, list0);
            if(v2 != -1) {
                return TuplesKt.to(v2, v1);
            }
        }
        return TuplesKt.to(-1, 0);
    }

    private final int findIndexOfFrame(int v, StackTraceElement[] arr_stackTraceElement, List list0) {
        StackTraceElement stackTraceElement0 = (StackTraceElement)ArraysKt.getOrNull(arr_stackTraceElement, v);
        if(stackTraceElement0 == null) {
            return -1;
        }
        int v1 = 0;
        for(Object object0: list0) {
            StackTraceElement stackTraceElement1 = (StackTraceElement)object0;
            if(Intrinsics.areEqual(stackTraceElement1.getFileName(), stackTraceElement0.getFileName()) && Intrinsics.areEqual(stackTraceElement1.getClassName(), stackTraceElement0.getClassName()) && Intrinsics.areEqual(stackTraceElement1.getMethodName(), stackTraceElement0.getMethodName())) {
                return v1;
            }
            ++v1;
        }
        return -1;
    }

    private final Set getCapturedCoroutines() {
        return DebugProbesImpl.capturedCoroutinesMap.keySet();
    }

    // 去混淆评级： 低(20)
    private final String getDebugString(Job job0) {
        return job0 instanceof JobSupport ? ((JobSupport)job0).toDebugString() : job0.toString();
    }

    private static void getDebugString$annotations(Job job0) {
    }

    private final Function1 getDynamicAttach() {
        Object object2;
        Object object0 = null;
        try {
            Object object1 = Class.forName("kotlinx.coroutines.debug.internal.ByteBuddyDynamicAttach").getConstructors()[0].newInstance(null);
            Intrinsics.checkNotNull(object1, "null cannot be cast to non-null type kotlin.Function1<kotlin.Boolean, kotlin.Unit>");
            object2 = Result.constructor-impl(((Function1)TypeIntrinsics.beforeCheckcastToFunctionOfArity(object1, 1)));
        }
        catch(Throwable throwable0) {
            object2 = Result.constructor-impl(ResultKt.createFailure(throwable0));
        }
        if(!Result.isFailure-impl(object2)) {
            object0 = object2;
        }
        return (Function1)object0;
    }

    public final boolean getEnableCreationStackTraces$kotlinx_coroutines_core() [...] // 潜在的解密器

    public final boolean getIgnoreCoroutinesWithEmptyContext() {
        return DebugProbesImpl.ignoreCoroutinesWithEmptyContext;
    }

    public final boolean getSanitizeStackTraces$kotlinx_coroutines_core() {
        return DebugProbesImpl.sanitizeStackTraces;
    }

    public final String hierarchyToString$kotlinx_coroutines_core(Job job0) {
        if(!this.isInstalled$kotlinx_coroutines_debug()) {
            throw new IllegalStateException("Debug probes are not installed");
        }
        Iterable iterable0 = this.getCapturedCoroutines();
        Collection collection0 = new ArrayList();
        for(Object object0: iterable0) {
            if(((CoroutineOwner)object0).delegate.getContext().get(Job.Key) != null) {
                collection0.add(object0);
            }
        }
        Map map0 = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(((List)collection0), 10)), 16));
        for(Object object1: ((List)collection0)) {
            map0.put(JobKt.getJob(((CoroutineOwner)object1).delegate.getContext()), ((CoroutineOwner)object1).info);
        }
        StringBuilder stringBuilder0 = new StringBuilder();
        DebugProbesImpl.INSTANCE.build(job0, map0, stringBuilder0, "");
        String s = stringBuilder0.toString();
        Intrinsics.checkNotNullExpressionValue(s, "StringBuilder().apply(builderAction).toString()");
        return s;
    }

    public final void install$kotlinx_coroutines_core() {
        if(Installations.kotlinx.VolatileWrapper.access$getInstallations$FU$p().incrementAndGet(DebugProbesImpl.installations$kotlinx$VolatileWrapper) <= 1) {
            this.startWeakRefCleanerThread();
            Function1 function10 = DebugProbesImpl.dynamicAttach;
            if(function10 != null) {
                function10.invoke(Boolean.TRUE);
            }
        }
    }

    private final boolean isFinished(CoroutineOwner debugProbesImpl$CoroutineOwner0) {
        CoroutineContext coroutineContext0 = debugProbesImpl$CoroutineOwner0.info.getContext();
        if(coroutineContext0 != null) {
            Job job0 = (Job)coroutineContext0.get(Job.Key);
            if(job0 == null || !job0.isCompleted()) {
                return false;
            }
            DebugProbesImpl.capturedCoroutinesMap.remove(debugProbesImpl$CoroutineOwner0);
            return true;
        }
        return false;
    }

    public final boolean isInstalled$kotlinx_coroutines_debug() {
        return Installations.kotlinx.VolatileWrapper.access$getInstallations$FU$p().get(DebugProbesImpl.installations$kotlinx$VolatileWrapper) > 0;
    }

    private final boolean isInternalMethod(StackTraceElement stackTraceElement0) {
        return StringsKt.startsWith$default(stackTraceElement0.getClassName(), "kotlinx.coroutines", false, 2, null);
    }

    private final CoroutineOwner owner(Continuation continuation0) {
        CoroutineStackFrame coroutineStackFrame0 = continuation0 instanceof CoroutineStackFrame ? ((CoroutineStackFrame)continuation0) : null;
        return coroutineStackFrame0 == null ? null : this.owner(coroutineStackFrame0);
    }

    private final CoroutineOwner owner(CoroutineStackFrame coroutineStackFrame0) {
        do {
            if(coroutineStackFrame0 instanceof CoroutineOwner) {
                return (CoroutineOwner)coroutineStackFrame0;
            }
            coroutineStackFrame0 = coroutineStackFrame0.getCallerFrame();
        }
        while(coroutineStackFrame0 != null);
        return null;
    }

    private final void printStackTrace(PrintStream printStream0, List list0) {
        for(Object object0: list0) {
            printStream0.print("\n\tat " + ((StackTraceElement)object0));
        }
    }

    private final void probeCoroutineCompleted(CoroutineOwner debugProbesImpl$CoroutineOwner0) {
        DebugProbesImpl.capturedCoroutinesMap.remove(debugProbesImpl$CoroutineOwner0);
        CoroutineStackFrame coroutineStackFrame0 = debugProbesImpl$CoroutineOwner0.info.getLastObservedFrame$kotlinx_coroutines_core();
        if(coroutineStackFrame0 != null) {
            CoroutineStackFrame coroutineStackFrame1 = this.realCaller(coroutineStackFrame0);
            if(coroutineStackFrame1 != null) {
                DebugProbesImpl.callerInfoCache.remove(coroutineStackFrame1);
            }
        }
    }

    public final Continuation probeCoroutineCreated$kotlinx_coroutines_core(Continuation continuation0) {
        if(!this.isInstalled$kotlinx_coroutines_debug() || DebugProbesImpl.ignoreCoroutinesWithEmptyContext && continuation0.getContext() == EmptyCoroutineContext.INSTANCE || this.owner(continuation0) != null) {
            return continuation0;
        }
        return DebugProbesImpl.enableCreationStackTraces ? this.createOwner(continuation0, this.toStackTraceFrame(this.sanitizeStackTrace(new Exception()))) : this.createOwner(continuation0, null);
    }

    public final void probeCoroutineResumed$kotlinx_coroutines_core(Continuation continuation0) {
        this.updateState(continuation0, "RUNNING");
    }

    public final void probeCoroutineSuspended$kotlinx_coroutines_core(Continuation continuation0) {
        this.updateState(continuation0, "SUSPENDED");
    }

    private final CoroutineStackFrame realCaller(CoroutineStackFrame coroutineStackFrame0) {
        do {
            coroutineStackFrame0 = coroutineStackFrame0.getCallerFrame();
            if(coroutineStackFrame0 == null) {
                return null;
            }
        }
        while(coroutineStackFrame0.getStackTraceElement() == null);
        return coroutineStackFrame0;
    }

    private final List sanitizeStackTrace(Throwable throwable0) {
        StackTraceElement[] arr_stackTraceElement = throwable0.getStackTrace();
        int v = -1;
        int v1 = arr_stackTraceElement.length - 1;
        if(v1 >= 0) {
            while(true) {
                if(Intrinsics.areEqual(arr_stackTraceElement[v1].getClassName(), "kotlin.coroutines.jvm.internal.DebugProbesKt")) {
                    v = v1;
                    break;
                }
                if(v1 - 1 < 0) {
                    break;
                }
                --v1;
            }
        }
        int v2 = v + 1;
        if(!DebugProbesImpl.sanitizeStackTraces) {
            int v3 = arr_stackTraceElement.length - v2;
            ArrayList arrayList0 = new ArrayList(v3);
            for(int v4 = 0; v4 < v3; ++v4) {
                arrayList0.add(arr_stackTraceElement[v4 + v2]);
            }
            return arrayList0;
        }
        ArrayList arrayList1 = new ArrayList(arr_stackTraceElement.length - v2 + 1);
        while(v2 < arr_stackTraceElement.length) {
            if(this.isInternalMethod(arr_stackTraceElement[v2])) {
                arrayList1.add(arr_stackTraceElement[v2]);
                int v5;
                for(v5 = v2 + 1; v5 < arr_stackTraceElement.length && this.isInternalMethod(arr_stackTraceElement[v5]); ++v5) {
                }
                int v6;
                for(v6 = v5 - 1; v6 > v2 && arr_stackTraceElement[v6].getFileName() == null; --v6) {
                }
                if(v6 > v2 && v6 < v5 - 1) {
                    arrayList1.add(arr_stackTraceElement[v6]);
                }
                arrayList1.add(arr_stackTraceElement[v5 - 1]);
                v2 = v5;
            }
            else {
                arrayList1.add(arr_stackTraceElement[v2]);
                ++v2;
            }
        }
        return arrayList1;
    }

    public final void setEnableCreationStackTraces$kotlinx_coroutines_core(boolean z) {
        DebugProbesImpl.enableCreationStackTraces = z;
    }

    public final void setIgnoreCoroutinesWithEmptyContext(boolean z) {
        DebugProbesImpl.ignoreCoroutinesWithEmptyContext = z;
    }

    public final void setSanitizeStackTraces$kotlinx_coroutines_core(boolean z) {
        DebugProbesImpl.sanitizeStackTraces = z;
    }

    private final void startWeakRefCleanerThread() {
        DebugProbesImpl.weakRefCleanerThread = ThreadsKt.thread$default(false, true, null, "Coroutines Debugger Cleaner", 0, kotlinx.coroutines.debug.internal.DebugProbesImpl.startWeakRefCleanerThread.1.INSTANCE, 21, null);

        @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001A\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 0x30)
        final class kotlinx.coroutines.debug.internal.DebugProbesImpl.startWeakRefCleanerThread.1 extends Lambda implements Function0 {
            public static final kotlinx.coroutines.debug.internal.DebugProbesImpl.startWeakRefCleanerThread.1 INSTANCE;

            static {
                kotlinx.coroutines.debug.internal.DebugProbesImpl.startWeakRefCleanerThread.1.INSTANCE = new kotlinx.coroutines.debug.internal.DebugProbesImpl.startWeakRefCleanerThread.1();
            }

            kotlinx.coroutines.debug.internal.DebugProbesImpl.startWeakRefCleanerThread.1() {
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                this.invoke();
                return Unit.INSTANCE;
            }

            public final void invoke() {
                DebugProbesImpl.callerInfoCache.runWeakRefQueueCleaningLoopUntilInterrupted();
            }
        }

    }

    private final void stopWeakRefCleanerThread() {
        Thread thread0 = DebugProbesImpl.weakRefCleanerThread;
        if(thread0 == null) {
            return;
        }
        DebugProbesImpl.weakRefCleanerThread = null;
        thread0.interrupt();
        thread0.join();
    }

    private final StackTraceFrame toStackTraceFrame(List list0) {
        StackTraceFrame stackTraceFrame0 = null;
        if(!list0.isEmpty()) {
            ListIterator listIterator0 = list0.listIterator(list0.size());
            while(listIterator0.hasPrevious()) {
                stackTraceFrame0 = new StackTraceFrame(stackTraceFrame0, ((StackTraceElement)listIterator0.previous()));
            }
        }
        return new StackTraceFrame(stackTraceFrame0, DebugProbesImpl.ARTIFICIAL_FRAME);
    }

    private final String toStringRepr(Object object0) {
        return DebugProbesImplKt.repr(object0.toString());
    }

    public final void uninstall$kotlinx_coroutines_core() {
        if(!this.isInstalled$kotlinx_coroutines_debug()) {
            throw new IllegalStateException("Agent was not installed");
        }
        if(Installations.kotlinx.VolatileWrapper.access$getInstallations$FU$p().decrementAndGet(DebugProbesImpl.installations$kotlinx$VolatileWrapper) == 0) {
            this.stopWeakRefCleanerThread();
            DebugProbesImpl.capturedCoroutinesMap.clear();
            DebugProbesImpl.callerInfoCache.clear();
            Function1 function10 = DebugProbesImpl.dynamicAttach;
            if(function10 != null) {
                function10.invoke(Boolean.FALSE);
            }
        }
    }

    private final void updateRunningState(CoroutineStackFrame coroutineStackFrame0, String s) {
        boolean z;
        if(this.isInstalled$kotlinx_coroutines_debug()) {
            ConcurrentWeakMap concurrentWeakMap0 = DebugProbesImpl.callerInfoCache;
            DebugCoroutineInfoImpl debugCoroutineInfoImpl0 = (DebugCoroutineInfoImpl)concurrentWeakMap0.remove(coroutineStackFrame0);
            if(debugCoroutineInfoImpl0 == null) {
                CoroutineOwner debugProbesImpl$CoroutineOwner0 = this.owner(coroutineStackFrame0);
                if(debugProbesImpl$CoroutineOwner0 != null) {
                    debugCoroutineInfoImpl0 = debugProbesImpl$CoroutineOwner0.info;
                    if(debugCoroutineInfoImpl0 != null) {
                        CoroutineStackFrame coroutineStackFrame1 = debugCoroutineInfoImpl0.getLastObservedFrame$kotlinx_coroutines_core();
                        CoroutineStackFrame coroutineStackFrame2 = coroutineStackFrame1 == null ? null : this.realCaller(coroutineStackFrame1);
                        if(coroutineStackFrame2 != null) {
                            concurrentWeakMap0.remove(coroutineStackFrame2);
                        }
                        z = true;
                    label_15:
                        Intrinsics.checkNotNull(coroutineStackFrame0, "null cannot be cast to non-null type kotlin.coroutines.Continuation<*>");
                        debugCoroutineInfoImpl0.updateState$kotlinx_coroutines_core(s, ((Continuation)coroutineStackFrame0), z);
                        CoroutineStackFrame coroutineStackFrame3 = this.realCaller(coroutineStackFrame0);
                        if(coroutineStackFrame3 != null) {
                            concurrentWeakMap0.put(coroutineStackFrame3, debugCoroutineInfoImpl0);
                        }
                    }
                }
            }
            else {
                z = false;
                goto label_15;
            }
        }
    }

    private final void updateState(Continuation continuation0, String s) {
        if(this.isInstalled$kotlinx_coroutines_debug() && (!DebugProbesImpl.ignoreCoroutinesWithEmptyContext || continuation0.getContext() != EmptyCoroutineContext.INSTANCE)) {
            if(Intrinsics.areEqual(s, "RUNNING")) {
                CoroutineStackFrame coroutineStackFrame0 = continuation0 instanceof CoroutineStackFrame ? ((CoroutineStackFrame)continuation0) : null;
                if(coroutineStackFrame0 != null) {
                    this.updateRunningState(coroutineStackFrame0, s);
                }
            }
            else {
                CoroutineOwner debugProbesImpl$CoroutineOwner0 = this.owner(continuation0);
                if(debugProbesImpl$CoroutineOwner0 != null) {
                    this.updateState(debugProbesImpl$CoroutineOwner0, continuation0, s);
                }
            }
        }
    }

    private final void updateState(CoroutineOwner debugProbesImpl$CoroutineOwner0, Continuation continuation0, String s) {
        if(!this.isInstalled$kotlinx_coroutines_debug()) {
            return;
        }
        debugProbesImpl$CoroutineOwner0.info.updateState$kotlinx_coroutines_core(s, continuation0, true);
    }
}

