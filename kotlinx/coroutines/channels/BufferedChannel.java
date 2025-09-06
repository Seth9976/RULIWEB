package kotlinx.coroutines.channels;

import androidx.concurrent.futures.AbstractResolvableFuture.SafeAtomicHelper..ExternalSyntheticBackportWithForwarding0;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.Volatile;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CancellableContinuationKt;
import kotlinx.coroutines.DebugStringsKt;
import kotlinx.coroutines.Waiter;
import kotlinx.coroutines.internal.ConcurrentLinkedListKt;
import kotlinx.coroutines.internal.ConcurrentLinkedListNode;
import kotlinx.coroutines.internal.InlineList;
import kotlinx.coroutines.internal.OnUndeliveredElementKt;
import kotlinx.coroutines.internal.Segment;
import kotlinx.coroutines.internal.SegmentOrClosed;
import kotlinx.coroutines.internal.StackTraceRecoveryKt;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.internal.UndeliveredElementException;
import kotlinx.coroutines.selects.SelectClause1;
import kotlinx.coroutines.selects.SelectClause1Impl;
import kotlinx.coroutines.selects.SelectClause2;
import kotlinx.coroutines.selects.SelectClause2Impl;
import kotlinx.coroutines.selects.SelectImplementation;
import kotlinx.coroutines.selects.SelectInstance;
import kotlinx.coroutines.selects.TrySelectDetailedResult;

@Metadata(d1 = {"\u0000\u00C0\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0005\n\u0002\u0010\u000B\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b#\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000E\n\u0002\b\"\b\u0010\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002:\u0004\u00DE\u0001\u00DF\u0001B1\u0012\u0006\u0010\u0003\u001A\u00020\u0004\u0012\"\b\u0002\u0010\u0005\u001A\u001C\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006j\n\u0012\u0004\u0012\u00028\u0000\u0018\u0001`\b\u00A2\u0006\u0002\u0010\tJ\u0010\u0010P\u001A\u00020\u001C2\u0006\u0010Q\u001A\u00020\u0010H\u0002J\u0006\u0010R\u001A\u00020\u0007J\u0010\u0010R\u001A\u00020\u001C2\b\u0010S\u001A\u0004\u0018\u00010\u0016J\u0016\u0010R\u001A\u00020\u00072\u000E\u0010S\u001A\n\u0018\u00010Tj\u0004\u0018\u0001`UJ\u0017\u0010V\u001A\u00020\u001C2\b\u0010S\u001A\u0004\u0018\u00010\u0016H\u0010\u00A2\u0006\u0002\bWJ\u001E\u0010X\u001A\u00020\u00072\f\u0010Y\u001A\b\u0012\u0004\u0012\u00028\u00000\u00142\u0006\u0010K\u001A\u00020\u0010H\u0002J\u0006\u0010Z\u001A\u00020\u0007J\u0012\u0010[\u001A\u00020\u001C2\b\u0010S\u001A\u0004\u0018\u00010\u0016H\u0016J\u000E\u0010\\\u001A\b\u0012\u0004\u0012\u00028\u00000\u0014H\u0002J\u001A\u0010]\u001A\u00020\u001C2\b\u0010S\u001A\u0004\u0018\u00010\u00162\u0006\u0010R\u001A\u00020\u001CH\u0014J\u0010\u0010^\u001A\u00020\u00072\u0006\u0010_\u001A\u00020\u0010H\u0002J\u0016\u0010`\u001A\b\u0012\u0004\u0012\u00028\u00000\u00142\u0006\u0010_\u001A\u00020\u0010H\u0002J\b\u0010a\u001A\u00020\u0007H\u0002J\u0010\u0010b\u001A\u00020\u00072\u0006\u0010c\u001A\u00020\u0010H\u0004J\b\u0010d\u001A\u00020\u0007H\u0002J.\u0010e\u001A\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u00142\u0006\u0010f\u001A\u00020\u00102\f\u0010g\u001A\b\u0012\u0004\u0012\u00028\u00000\u00142\u0006\u0010h\u001A\u00020\u0010H\u0002J&\u0010i\u001A\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u00142\u0006\u0010f\u001A\u00020\u00102\f\u0010g\u001A\b\u0012\u0004\u0012\u00028\u00000\u0014H\u0002J&\u0010j\u001A\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u00142\u0006\u0010f\u001A\u00020\u00102\f\u0010g\u001A\b\u0012\u0004\u0012\u00028\u00000\u0014H\u0002J\r\u0010k\u001A\u00020\u001CH\u0000\u00A2\u0006\u0002\blJ\u0012\u0010m\u001A\u00020\u00072\b\b\u0002\u0010n\u001A\u00020\u0010H\u0002J\b\u0010o\u001A\u00020\u0007H\u0002J-\u0010p\u001A\u00020\u00072#\u0010q\u001A\u001F\u0012\u0015\u0012\u0013\u0018\u00010\u0016\u00A2\u0006\f\b:\u0012\b\b;\u0012\u0004\b\b(S\u0012\u0004\u0012\u00020\u00070\u0006H\u0016J&\u0010r\u001A\u00020\u001C2\f\u0010s\u001A\b\u0012\u0004\u0012\u00028\u00000\u00142\u0006\u0010t\u001A\u00020\u00042\u0006\u0010u\u001A\u00020\u0010H\u0002J\u0018\u0010v\u001A\u00020\u001C2\u0006\u0010w\u001A\u00020\u00102\u0006\u0010\u001B\u001A\u00020\u001CH\u0002J\u000F\u0010x\u001A\b\u0012\u0004\u0012\u00028\u00000yH\u0096\u0002J\u0016\u0010z\u001A\u00020\u00102\f\u0010Y\u001A\b\u0012\u0004\u0012\u00028\u00000\u0014H\u0002J\b\u0010{\u001A\u00020\u0007H\u0002J\b\u0010|\u001A\u00020\u0007H\u0002J\b\u0010}\u001A\u00020\u0007H\u0002J\u001E\u0010~\u001A\u00020\u00072\u0006\u0010f\u001A\u00020\u00102\f\u0010g\u001A\b\u0012\u0004\u0012\u00028\u00000\u0014H\u0002J\b\u0010\u007F\u001A\u00020\u0007H\u0014J\"\u0010\u0080\u0001\u001A\u00020\u00072\u0014\u0010\u0081\u0001\u001A\u000F\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000,0\u0082\u0001H\u0002\u00F8\u0001\u0000J\u0019\u0010\u0083\u0001\u001A\u00020\u00072\u000E\u0010\u0081\u0001\u001A\t\u0012\u0004\u0012\u00028\u00000\u0082\u0001H\u0002J\u0015\u0010\u0084\u0001\u001A\u00020\u00072\n\u0010<\u001A\u0006\u0012\u0002\b\u000309H\u0002J$\u0010\u0085\u0001\u001A\u00020\u00072\u0007\u0010\u0086\u0001\u001A\u00028\u00002\n\u0010<\u001A\u0006\u0012\u0002\b\u000309H\u0002\u00A2\u0006\u0003\u0010\u0087\u0001J\u001C\u0010\u0088\u0001\u001A\u00020\u00072\u0007\u0010\u0086\u0001\u001A\u00028\u0000H\u0082@\u00F8\u0001\u0000\u00A2\u0006\u0003\u0010\u0089\u0001J(\u0010\u008A\u0001\u001A\u00020\u00072\u0007\u0010\u0086\u0001\u001A\u00028\u00002\u000E\u0010\u0081\u0001\u001A\t\u0012\u0004\u0012\u00020\u00070\u0082\u0001H\u0002\u00A2\u0006\u0003\u0010\u008B\u0001J\t\u0010\u008C\u0001\u001A\u00020\u0007H\u0014J\t\u0010\u008D\u0001\u001A\u00020\u0007H\u0014J!\u0010\u008E\u0001\u001A\u0004\u0018\u00010\f2\t\u0010\u008F\u0001\u001A\u0004\u0018\u00010\f2\t\u0010\u0090\u0001\u001A\u0004\u0018\u00010\fH\u0002J!\u0010\u0091\u0001\u001A\u0004\u0018\u00010\f2\t\u0010\u008F\u0001\u001A\u0004\u0018\u00010\f2\t\u0010\u0090\u0001\u001A\u0004\u0018\u00010\fH\u0002J!\u0010\u0092\u0001\u001A\u0004\u0018\u00010\f2\t\u0010\u008F\u0001\u001A\u0004\u0018\u00010\f2\t\u0010\u0090\u0001\u001A\u0004\u0018\u00010\fH\u0002J!\u0010\u0093\u0001\u001A\u0004\u0018\u00010\f2\t\u0010\u008F\u0001\u001A\u0004\u0018\u00010\f2\t\u0010\u0090\u0001\u001A\u0004\u0018\u00010\fH\u0002J\u0013\u0010\u0094\u0001\u001A\u00028\u0000H\u0096@\u00F8\u0001\u0000\u00A2\u0006\u0003\u0010\u0095\u0001J%\u0010\u0096\u0001\u001A\b\u0012\u0004\u0012\u00028\u00000,H\u0096@\u00F8\u0001\u0001\u00F8\u0001\u0002\u00F8\u0001\u0000\u00F8\u0001\u0000\u00A2\u0006\u0006\b\u0097\u0001\u0010\u0095\u0001JD\u0010\u0098\u0001\u001A\b\u0012\u0004\u0012\u00028\u00000,2\f\u0010s\u001A\b\u0012\u0004\u0012\u00028\u00000\u00142\u0006\u0010t\u001A\u00020\u00042\u0007\u0010\u0099\u0001\u001A\u00020\u0010H\u0082@\u00F8\u0001\u0001\u00F8\u0001\u0002\u00F8\u0001\u0000\u00F8\u0001\u0000\u00A2\u0006\u0006\b\u009A\u0001\u0010\u009B\u0001J\u008C\u0002\u0010\u009C\u0001\u001A\u0003H\u009D\u0001\"\u0005\b\u0001\u0010\u009D\u00012\t\u0010\u009E\u0001\u001A\u0004\u0018\u00010\f2$\u0010\u009F\u0001\u001A\u001F\u0012\u0014\u0012\u00128\u0000\u00A2\u0006\r\b:\u0012\t\b;\u0012\u0005\b\b(\u0086\u0001\u0012\u0005\u0012\u0003H\u009D\u00010\u00062V\u0010\u00A0\u0001\u001AQ\u0012\u001A\u0012\u0018\u0012\u0004\u0012\u00028\u00000\u0014\u00A2\u0006\r\b:\u0012\t\b;\u0012\u0005\b\b(\u00A1\u0001\u0012\u0014\u0012\u00120\u0004\u00A2\u0006\r\b:\u0012\t\b;\u0012\u0005\b\b(\u00A2\u0001\u0012\u0014\u0012\u00120\u0010\u00A2\u0006\r\b:\u0012\t\b;\u0012\u0005\b\b(\u0099\u0001\u0012\u0005\u0012\u0003H\u009D\u0001082\u000F\u0010\u00A3\u0001\u001A\n\u0012\u0005\u0012\u0003H\u009D\u00010\u00A4\u00012X\b\u0002\u0010\u00A5\u0001\u001AQ\u0012\u001A\u0012\u0018\u0012\u0004\u0012\u00028\u00000\u0014\u00A2\u0006\r\b:\u0012\t\b;\u0012\u0005\b\b(\u00A1\u0001\u0012\u0014\u0012\u00120\u0004\u00A2\u0006\r\b:\u0012\t\b;\u0012\u0005\b\b(\u00A2\u0001\u0012\u0014\u0012\u00120\u0010\u00A2\u0006\r\b:\u0012\t\b;\u0012\u0005\b\b(\u0099\u0001\u0012\u0005\u0012\u0003H\u009D\u000108H\u0082\b\u00A2\u0006\u0003\u0010\u00A6\u0001Jh\u0010\u00A7\u0001\u001A\u00020\u00072\f\u0010s\u001A\b\u0012\u0004\u0012\u00028\u00000\u00142\u0006\u0010t\u001A\u00020\u00042\u0007\u0010\u0099\u0001\u001A\u00020\u00102\b\u0010\u009E\u0001\u001A\u00030\u00A8\u00012#\u0010\u009F\u0001\u001A\u001E\u0012\u0014\u0012\u00128\u0000\u00A2\u0006\r\b:\u0012\t\b;\u0012\u0005\b\b(\u0086\u0001\u0012\u0004\u0012\u00020\u00070\u00062\u000E\u0010\u00A3\u0001\u001A\t\u0012\u0004\u0012\u00020\u00070\u00A4\u0001H\u0082\bJ2\u0010\u00A9\u0001\u001A\u00028\u00002\f\u0010s\u001A\b\u0012\u0004\u0012\u00028\u00000\u00142\u0006\u0010t\u001A\u00020\u00042\u0007\u0010\u0099\u0001\u001A\u00020\u0010H\u0082@\u00F8\u0001\u0000\u00A2\u0006\u0003\u0010\u009B\u0001J \u0010\u00AA\u0001\u001A\u00020\u00072\n\u0010<\u001A\u0006\u0012\u0002\b\u0003092\t\u0010\u008F\u0001\u001A\u0004\u0018\u00010\fH\u0002J \u0010\u00AB\u0001\u001A\u00020\u00072\n\u0010<\u001A\u0006\u0012\u0002\b\u0003092\t\u0010\u0086\u0001\u001A\u0004\u0018\u00010\fH\u0014J\u0017\u0010\u00AC\u0001\u001A\u00020\u00072\f\u0010Y\u001A\b\u0012\u0004\u0012\u00028\u00000\u0014H\u0002J\u001C\u0010\u00AD\u0001\u001A\u00020\u00072\u0007\u0010\u0086\u0001\u001A\u00028\u0000H\u0096@\u00F8\u0001\u0000\u00A2\u0006\u0003\u0010\u0089\u0001J\u001F\u0010\u00AE\u0001\u001A\u00020\u001C2\u0007\u0010\u0086\u0001\u001A\u00028\u0000H\u0090@\u00F8\u0001\u0000\u00A2\u0006\u0006\b\u00AF\u0001\u0010\u0089\u0001J\u0082\u0002\u0010\u00B0\u0001\u001A\u0003H\u009D\u0001\"\u0005\b\u0001\u0010\u009D\u00012\u0007\u0010\u0086\u0001\u001A\u00028\u00002\t\u0010\u009E\u0001\u001A\u0004\u0018\u00010\f2\u000F\u0010\u00B1\u0001\u001A\n\u0012\u0005\u0012\u0003H\u009D\u00010\u00A4\u00012A\u0010\u00A0\u0001\u001A<\u0012\u001A\u0012\u0018\u0012\u0004\u0012\u00028\u00000\u0014\u00A2\u0006\r\b:\u0012\t\b;\u0012\u0005\b\b(\u00A1\u0001\u0012\u0014\u0012\u00120\u0004\u00A2\u0006\r\b:\u0012\t\b;\u0012\u0005\b\b(\u00A2\u0001\u0012\u0005\u0012\u0003H\u009D\u00010\u00B2\u00012\u000F\u0010\u00A3\u0001\u001A\n\u0012\u0005\u0012\u0003H\u009D\u00010\u00A4\u00012o\b\u0002\u0010\u00A5\u0001\u001Ah\u0012\u001A\u0012\u0018\u0012\u0004\u0012\u00028\u00000\u0014\u00A2\u0006\r\b:\u0012\t\b;\u0012\u0005\b\b(\u00A1\u0001\u0012\u0014\u0012\u00120\u0004\u00A2\u0006\r\b:\u0012\t\b;\u0012\u0005\b\b(\u00A2\u0001\u0012\u0014\u0012\u00128\u0000\u00A2\u0006\r\b:\u0012\t\b;\u0012\u0005\b\b(\u0086\u0001\u0012\u0014\u0012\u00120\u0010\u00A2\u0006\r\b:\u0012\t\b;\u0012\u0005\b\b(\u00B4\u0001\u0012\u0005\u0012\u0003H\u009D\u00010\u00B3\u0001H\u0084\b\u00A2\u0006\u0003\u0010\u00B5\u0001Jb\u0010\u00B6\u0001\u001A\u00020\u00072\f\u0010s\u001A\b\u0012\u0004\u0012\u00028\u00000\u00142\u0006\u0010t\u001A\u00020\u00042\u0007\u0010\u0086\u0001\u001A\u00028\u00002\u0007\u0010\u00B4\u0001\u001A\u00020\u00102\b\u0010\u009E\u0001\u001A\u00030\u00A8\u00012\u000E\u0010\u00B1\u0001\u001A\t\u0012\u0004\u0012\u00020\u00070\u00A4\u00012\u000E\u0010\u00A3\u0001\u001A\t\u0012\u0004\u0012\u00020\u00070\u00A4\u0001H\u0082\b\u00A2\u0006\u0003\u0010\u00B7\u0001J;\u0010\u00B8\u0001\u001A\u00020\u00072\f\u0010s\u001A\b\u0012\u0004\u0012\u00028\u00000\u00142\u0006\u0010t\u001A\u00020\u00042\u0007\u0010\u0086\u0001\u001A\u00028\u00002\u0007\u0010\u00B4\u0001\u001A\u00020\u0010H\u0082@\u00F8\u0001\u0000\u00A2\u0006\u0003\u0010\u00B9\u0001J\u000F\u0010\u00BA\u0001\u001A\u00020\u001CH\u0010\u00A2\u0006\u0003\b\u00BB\u0001J\u0012\u0010\u00BA\u0001\u001A\u00020\u001C2\u0007\u0010\u00BC\u0001\u001A\u00020\u0010H\u0003J\n\u0010\u00BD\u0001\u001A\u00030\u00BE\u0001H\u0016J\u0010\u0010\u00BF\u0001\u001A\u00030\u00BE\u0001H\u0000\u00A2\u0006\u0003\b\u00C0\u0001J!\u0010\u00C1\u0001\u001A\b\u0012\u0004\u0012\u00028\u00000,H\u0016\u00F8\u0001\u0001\u00F8\u0001\u0002\u00F8\u0001\u0000\u00A2\u0006\u0006\b\u00C2\u0001\u0010\u00C3\u0001J*\u0010\u00C4\u0001\u001A\b\u0012\u0004\u0012\u00020\u00070,2\u0007\u0010\u0086\u0001\u001A\u00028\u0000H\u0016\u00F8\u0001\u0001\u00F8\u0001\u0002\u00F8\u0001\u0000\u00A2\u0006\u0006\b\u00C5\u0001\u0010\u00C6\u0001J(\u0010\u00C7\u0001\u001A\u00020\u001C2\f\u0010s\u001A\b\u0012\u0004\u0012\u00028\u00000\u00142\u0006\u0010t\u001A\u00020\u00042\u0007\u0010\u00C8\u0001\u001A\u00020\u0010H\u0002J(\u0010\u00C9\u0001\u001A\u00020\u001C2\f\u0010s\u001A\b\u0012\u0004\u0012\u00028\u00000\u00142\u0006\u0010t\u001A\u00020\u00042\u0007\u0010\u00C8\u0001\u001A\u00020\u0010H\u0002J5\u0010\u00CA\u0001\u001A\u0004\u0018\u00010\f2\f\u0010s\u001A\b\u0012\u0004\u0012\u00028\u00000\u00142\u0006\u0010t\u001A\u00020\u00042\u0007\u0010\u0099\u0001\u001A\u00020\u00102\t\u0010\u009E\u0001\u001A\u0004\u0018\u00010\fH\u0002J5\u0010\u00CB\u0001\u001A\u0004\u0018\u00010\f2\f\u0010s\u001A\b\u0012\u0004\u0012\u00028\u00000\u00142\u0006\u0010t\u001A\u00020\u00042\u0007\u0010\u0099\u0001\u001A\u00020\u00102\t\u0010\u009E\u0001\u001A\u0004\u0018\u00010\fH\u0002JK\u0010\u00CC\u0001\u001A\u00020\u00042\f\u0010s\u001A\b\u0012\u0004\u0012\u00028\u00000\u00142\u0006\u0010t\u001A\u00020\u00042\u0007\u0010\u0086\u0001\u001A\u00028\u00002\u0007\u0010\u00B4\u0001\u001A\u00020\u00102\t\u0010\u009E\u0001\u001A\u0004\u0018\u00010\f2\u0007\u0010\u00CD\u0001\u001A\u00020\u001CH\u0002\u00A2\u0006\u0003\u0010\u00CE\u0001JK\u0010\u00CF\u0001\u001A\u00020\u00042\f\u0010s\u001A\b\u0012\u0004\u0012\u00028\u00000\u00142\u0006\u0010t\u001A\u00020\u00042\u0007\u0010\u0086\u0001\u001A\u00028\u00002\u0007\u0010\u00B4\u0001\u001A\u00020\u00102\t\u0010\u009E\u0001\u001A\u0004\u0018\u00010\f2\u0007\u0010\u00CD\u0001\u001A\u00020\u001CH\u0002\u00A2\u0006\u0003\u0010\u00CE\u0001J\u0012\u0010\u00D0\u0001\u001A\u00020\u00072\u0007\u0010\u00D1\u0001\u001A\u00020\u0010H\u0002J\u0012\u0010\u00D2\u0001\u001A\u00020\u00072\u0007\u0010\u00D1\u0001\u001A\u00020\u0010H\u0002J\u0017\u0010\u00D3\u0001\u001A\u00020\u00072\u0006\u0010u\u001A\u00020\u0010H\u0000\u00A2\u0006\u0003\b\u00D4\u0001J$\u0010\u00D5\u0001\u001A\u00020\u0007*\u00030\u00A8\u00012\f\u0010s\u001A\b\u0012\u0004\u0012\u00028\u00000\u00142\u0006\u0010t\u001A\u00020\u0004H\u0002J$\u0010\u00D6\u0001\u001A\u00020\u0007*\u00030\u00A8\u00012\f\u0010s\u001A\b\u0012\u0004\u0012\u00028\u00000\u00142\u0006\u0010t\u001A\u00020\u0004H\u0002J\u000E\u0010\u00D7\u0001\u001A\u00020\u0007*\u00030\u00A8\u0001H\u0002J\u000E\u0010\u00D8\u0001\u001A\u00020\u0007*\u00030\u00A8\u0001H\u0002J\u0017\u0010\u00D9\u0001\u001A\u00020\u0007*\u00030\u00A8\u00012\u0007\u0010\u00DA\u0001\u001A\u00020\u001CH\u0002J\u001C\u0010\u00DB\u0001\u001A\u00020\u001C*\u00020\f2\u0007\u0010\u0086\u0001\u001A\u00028\u0000H\u0002\u00A2\u0006\u0003\u0010\u00DC\u0001J#\u0010\u00DD\u0001\u001A\u00020\u001C*\u00020\f2\f\u0010s\u001A\b\u0012\u0004\u0012\u00028\u00000\u00142\u0006\u0010t\u001A\u00020\u0004H\u0002R\u0011\u0010\n\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000BX\u0082\u0004R\t\u0010\r\u001A\u00020\u000EX\u0082\u0004R\u0014\u0010\u000F\u001A\u00020\u00108BX\u0082\u0004\u00A2\u0006\u0006\u001A\u0004\b\u0011\u0010\u0012R\u0015\u0010\u0013\u001A\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00140\u000BX\u0082\u0004R\u000E\u0010\u0003\u001A\u00020\u0004X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u0016\u0010\u0015\u001A\u0004\u0018\u00010\u00168DX\u0084\u0004\u00A2\u0006\u0006\u001A\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0019\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000BX\u0082\u0004R\t\u0010\u001A\u001A\u00020\u000EX\u0082\u0004R\u001A\u0010\u001B\u001A\u00020\u001C8VX\u0097\u0004\u00A2\u0006\f\u0012\u0004\b\u001D\u0010\u001E\u001A\u0004\b\u001B\u0010\u001FR\u001A\u0010 \u001A\u00020\u001C8VX\u0097\u0004\u00A2\u0006\f\u0012\u0004\b!\u0010\u001E\u001A\u0004\b \u0010\u001FR\u0014\u0010\"\u001A\u00020\u001C8TX\u0094\u0004\u00A2\u0006\u0006\u001A\u0004\b\"\u0010\u001FR\u001A\u0010#\u001A\u00020\u001C8VX\u0097\u0004\u00A2\u0006\f\u0012\u0004\b$\u0010\u001E\u001A\u0004\b#\u0010\u001FR\u0014\u0010%\u001A\u00020\u001C8BX\u0082\u0004\u00A2\u0006\u0006\u001A\u0004\b%\u0010\u001FR \u0010&\u001A\b\u0012\u0004\u0012\u00028\u00000\'8VX\u0096\u0004\u00A2\u0006\f\u0012\u0004\b(\u0010\u001E\u001A\u0004\b)\u0010*R)\u0010+\u001A\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000,0\'8VX\u0096\u0004\u00F8\u0001\u0000\u00A2\u0006\f\u0012\u0004\b-\u0010\u001E\u001A\u0004\b.\u0010*R\"\u0010/\u001A\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000\'8VX\u0096\u0004\u00A2\u0006\f\u0012\u0004\b0\u0010\u001E\u001A\u0004\b1\u0010*R,\u00102\u001A\u0014\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0000038VX\u0096\u0004\u00A2\u0006\f\u0012\u0004\b4\u0010\u001E\u001A\u0004\b5\u00106R*\u0010\u0005\u001A\u001C\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006j\n\u0012\u0004\u0012\u00028\u0000\u0018\u0001`\b8\u0000X\u0081\u0004\u00A2\u0006\u0002\n\u0000Ru\u00107\u001Ac\u0012\u0017\u0012\u0015\u0012\u0002\b\u000309\u00A2\u0006\f\b:\u0012\b\b;\u0012\u0004\b\b(<\u0012\u0015\u0012\u0013\u0018\u00010\f\u00A2\u0006\f\b:\u0012\b\b;\u0012\u0004\b\b(=\u0012\u0015\u0012\u0013\u0018\u00010\f\u00A2\u0006\f\b:\u0012\b\b;\u0012\u0004\b\b(>\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00070\u0006\u0018\u000108j\u0004\u0018\u0001`?X\u0082\u0004\u00A2\u0006\b\n\u0000\u0012\u0004\b@\u0010\u001ER\u0014\u0010A\u001A\u00020\u00168BX\u0082\u0004\u00A2\u0006\u0006\u001A\u0004\bB\u0010\u0018R\u0015\u0010C\u001A\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00140\u000BX\u0082\u0004R\t\u0010D\u001A\u00020\u000EX\u0082\u0004R\u0014\u0010E\u001A\u00020\u00108@X\u0080\u0004\u00A2\u0006\u0006\u001A\u0004\bF\u0010\u0012R\u0014\u0010G\u001A\u00020\u00168DX\u0084\u0004\u00A2\u0006\u0006\u001A\u0004\bH\u0010\u0018R\u0015\u0010I\u001A\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00140\u000BX\u0082\u0004R\t\u0010J\u001A\u00020\u000EX\u0082\u0004R\u0014\u0010K\u001A\u00020\u00108@X\u0080\u0004\u00A2\u0006\u0006\u001A\u0004\bL\u0010\u0012R\u0018\u0010M\u001A\u00020\u001C*\u00020\u00108BX\u0082\u0004\u00A2\u0006\u0006\u001A\u0004\bM\u0010NR\u0018\u0010O\u001A\u00020\u001C*\u00020\u00108BX\u0082\u0004\u00A2\u0006\u0006\u001A\u0004\bO\u0010N\u0082\u0002\u000F\n\u0002\b\u0019\n\u0002\b!\n\u0005\b\u00A1\u001E0\u0001\u00A8\u0006\u00E0\u0001"}, d2 = {"Lkotlinx/coroutines/channels/BufferedChannel;", "E", "Lkotlinx/coroutines/channels/Channel;", "capacity", "", "onUndeliveredElement", "Lkotlin/Function1;", "", "Lkotlinx/coroutines/internal/OnUndeliveredElement;", "(ILkotlin/jvm/functions/Function1;)V", "_closeCause", "Lkotlinx/atomicfu/AtomicRef;", "", "bufferEnd", "Lkotlinx/atomicfu/AtomicLong;", "bufferEndCounter", "", "getBufferEndCounter", "()J", "bufferEndSegment", "Lkotlinx/coroutines/channels/ChannelSegment;", "closeCause", "", "getCloseCause", "()Ljava/lang/Throwable;", "closeHandler", "completedExpandBuffersAndPauseFlag", "isClosedForReceive", "", "isClosedForReceive$annotations", "()V", "()Z", "isClosedForSend", "isClosedForSend$annotations", "isConflatedDropOldest", "isEmpty", "isEmpty$annotations", "isRendezvousOrUnlimited", "onReceive", "Lkotlinx/coroutines/selects/SelectClause1;", "getOnReceive$annotations", "getOnReceive", "()Lkotlinx/coroutines/selects/SelectClause1;", "onReceiveCatching", "Lkotlinx/coroutines/channels/ChannelResult;", "getOnReceiveCatching$annotations", "getOnReceiveCatching", "onReceiveOrNull", "getOnReceiveOrNull$annotations", "getOnReceiveOrNull", "onSend", "Lkotlinx/coroutines/selects/SelectClause2;", "getOnSend$annotations", "getOnSend", "()Lkotlinx/coroutines/selects/SelectClause2;", "onUndeliveredElementReceiveCancellationConstructor", "Lkotlin/Function3;", "Lkotlinx/coroutines/selects/SelectInstance;", "Lkotlin/ParameterName;", "name", "select", "param", "internalResult", "Lkotlinx/coroutines/selects/OnCancellationConstructor;", "getOnUndeliveredElementReceiveCancellationConstructor$annotations", "receiveException", "getReceiveException", "receiveSegment", "receivers", "receiversCounter", "getReceiversCounter$kotlinx_coroutines_core", "sendException", "getSendException", "sendSegment", "sendersAndCloseStatus", "sendersCounter", "getSendersCounter$kotlinx_coroutines_core", "isClosedForReceive0", "(J)Z", "isClosedForSend0", "bufferOrRendezvousSend", "curSenders", "cancel", "cause", "Ljava/util/concurrent/CancellationException;", "Lkotlinx/coroutines/CancellationException;", "cancelImpl", "cancelImpl$kotlinx_coroutines_core", "cancelSuspendedReceiveRequests", "lastSegment", "checkSegmentStructureInvariants", "close", "closeLinkedList", "closeOrCancelImpl", "completeCancel", "sendersCur", "completeClose", "completeCloseOrCancel", "dropFirstElementUntilTheSpecifiedCellIsInTheBuffer", "globalCellIndex", "expandBuffer", "findSegmentBufferEnd", "id", "startFrom", "currentBufferEndCounter", "findSegmentReceive", "findSegmentSend", "hasElements", "hasElements$kotlinx_coroutines_core", "incCompletedExpandBufferAttempts", "nAttempts", "invokeCloseHandler", "invokeOnClose", "handler", "isCellNonEmpty", "segment", "index", "globalIndex", "isClosed", "sendersAndCloseStatusCur", "iterator", "Lkotlinx/coroutines/channels/ChannelIterator;", "markAllEmptyCellsAsClosed", "markCancellationStarted", "markCancelled", "markClosed", "moveSegmentBufferEndToSpecifiedOrLast", "onClosedIdempotent", "onClosedReceiveCatchingOnNoWaiterSuspend", "cont", "Lkotlinx/coroutines/CancellableContinuation;", "onClosedReceiveOnNoWaiterSuspend", "onClosedSelectOnReceive", "onClosedSelectOnSend", "element", "(Ljava/lang/Object;Lkotlinx/coroutines/selects/SelectInstance;)V", "onClosedSend", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onClosedSendOnNoWaiterSuspend", "(Ljava/lang/Object;Lkotlinx/coroutines/CancellableContinuation;)V", "onReceiveDequeued", "onReceiveEnqueued", "processResultSelectReceive", "ignoredParam", "selectResult", "processResultSelectReceiveCatching", "processResultSelectReceiveOrNull", "processResultSelectSend", "receive", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "receiveCatching", "receiveCatching-JP2dKIU", "receiveCatchingOnNoWaiterSuspend", "r", "receiveCatchingOnNoWaiterSuspend-GKJJFZk", "(Lkotlinx/coroutines/channels/ChannelSegment;IJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "receiveImpl", "R", "waiter", "onElementRetrieved", "onSuspend", "segm", "i", "onClosed", "Lkotlin/Function0;", "onNoWaiterSuspend", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function3;)Ljava/lang/Object;", "receiveImplOnNoWaiter", "Lkotlinx/coroutines/Waiter;", "receiveOnNoWaiterSuspend", "registerSelectForReceive", "registerSelectForSend", "removeUnprocessedElements", "send", "sendBroadcast", "sendBroadcast$kotlinx_coroutines_core", "sendImpl", "onRendezvousOrBuffered", "Lkotlin/Function2;", "Lkotlin/Function4;", "s", "(Ljava/lang/Object;Ljava/lang/Object;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function4;)Ljava/lang/Object;", "sendImplOnNoWaiter", "(Lkotlinx/coroutines/channels/ChannelSegment;ILjava/lang/Object;JLkotlinx/coroutines/Waiter;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;)V", "sendOnNoWaiterSuspend", "(Lkotlinx/coroutines/channels/ChannelSegment;ILjava/lang/Object;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "shouldSendSuspend", "shouldSendSuspend$kotlinx_coroutines_core", "curSendersAndCloseStatus", "toString", "", "toStringDebug", "toStringDebug$kotlinx_coroutines_core", "tryReceive", "tryReceive-PtdJZtk", "()Ljava/lang/Object;", "trySend", "trySend-JP2dKIU", "(Ljava/lang/Object;)Ljava/lang/Object;", "updateCellExpandBuffer", "b", "updateCellExpandBufferSlow", "updateCellReceive", "updateCellReceiveSlow", "updateCellSend", "closed", "(Lkotlinx/coroutines/channels/ChannelSegment;ILjava/lang/Object;JLjava/lang/Object;Z)I", "updateCellSendSlow", "updateReceiversCounterIfLower", "value", "updateSendersCounterIfLower", "waitExpandBufferCompletion", "waitExpandBufferCompletion$kotlinx_coroutines_core", "prepareReceiverForSuspension", "prepareSenderForSuspension", "resumeReceiverOnClosedChannel", "resumeSenderOnCancelledChannel", "resumeWaiterOnClosedChannel", "receiver", "tryResumeReceiver", "(Ljava/lang/Object;Ljava/lang/Object;)Z", "tryResumeSender", "BufferedChannelIterator", "SendBroadcast", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public class BufferedChannel implements Channel {
    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\t\b\u0082\u0004\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0011\u0010\t\u001A\u00020\u0006H\u0096Bø\u0001\u0000¢\u0006\u0002\u0010\nJ/\u0010\u000B\u001A\u00020\u00062\f\u0010\f\u001A\b\u0012\u0004\u0012\u00028\u00000\r2\u0006\u0010\u000E\u001A\u00020\u000F2\u0006\u0010\u0010\u001A\u00020\u0011H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\u0012J\u001C\u0010\u0013\u001A\u00020\u00142\n\u0010\f\u001A\u0006\u0012\u0002\b\u00030\u00152\u0006\u0010\u000E\u001A\u00020\u000FH\u0016J\u000E\u0010\u0016\u001A\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\u0017J\b\u0010\u0018\u001A\u00020\u0006H\u0002J\b\u0010\u0019\u001A\u00020\u0014H\u0002J\u0013\u0010\u001A\u001A\u00020\u00062\u0006\u0010\u001B\u001A\u00028\u0000¢\u0006\u0002\u0010\u001CJ\u0006\u0010\u001D\u001A\u00020\u0014R\u0016\u0010\u0004\u001A\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005X\u0082\u000E¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001A\u0004\u0018\u00010\bX\u0082\u000E¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001E"}, d2 = {"Lkotlinx/coroutines/channels/BufferedChannel$BufferedChannelIterator;", "Lkotlinx/coroutines/channels/ChannelIterator;", "Lkotlinx/coroutines/Waiter;", "(Lkotlinx/coroutines/channels/BufferedChannel;)V", "continuation", "Lkotlinx/coroutines/CancellableContinuationImpl;", "", "receiveResult", "", "hasNext", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "hasNextOnNoWaiterSuspend", "segment", "Lkotlinx/coroutines/channels/ChannelSegment;", "index", "", "r", "", "(Lkotlinx/coroutines/channels/ChannelSegment;IJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "invokeOnCancellation", "", "Lkotlinx/coroutines/internal/Segment;", "next", "()Ljava/lang/Object;", "onClosedHasNext", "onClosedHasNextNoWaiterSuspend", "tryResumeHasNext", "element", "(Ljava/lang/Object;)Z", "tryResumeHasNextOnClosedChannel", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    final class BufferedChannelIterator implements Waiter, ChannelIterator {
        private CancellableContinuationImpl continuation;
        private Object receiveResult;

        public BufferedChannelIterator() {
            this.receiveResult = BufferedChannelKt.NO_RECEIVE_RESULT;
        }

        public static final Object access$hasNextOnNoWaiterSuspend(BufferedChannelIterator bufferedChannel$BufferedChannelIterator0, ChannelSegment channelSegment0, int v, long v1, Continuation continuation0) {
            return bufferedChannel$BufferedChannelIterator0.hasNextOnNoWaiterSuspend(channelSegment0, v, v1, continuation0);
        }

        @Override  // kotlinx.coroutines.channels.ChannelIterator
        public Object hasNext(Continuation continuation0) {
            BufferedChannel bufferedChannel0 = BufferedChannel.this;
            ChannelSegment channelSegment0 = (ChannelSegment)BufferedChannel.receiveSegment$FU.get(bufferedChannel0);
            while(true) {
                if(bufferedChannel0.isClosedForReceive()) {
                    return Boxing.boxBoolean(this.onClosedHasNext());
                }
                long v = BufferedChannel.receivers$FU.getAndIncrement(bufferedChannel0);
                long v1 = v / ((long)BufferedChannelKt.SEGMENT_SIZE);
                int v2 = (int)(v % ((long)BufferedChannelKt.SEGMENT_SIZE));
                if(channelSegment0.id != v1) {
                    ChannelSegment channelSegment1 = bufferedChannel0.findSegmentReceive(v1, channelSegment0);
                    if(channelSegment1 == null) {
                        continue;
                    }
                    channelSegment0 = channelSegment1;
                }
                Object object0 = bufferedChannel0.updateCellReceive(channelSegment0, v2, v, null);
                if(object0 == BufferedChannelKt.SUSPEND) {
                    break;
                }
                if(object0 == BufferedChannelKt.FAILED) {
                    if(v >= bufferedChannel0.getSendersCounter$kotlinx_coroutines_core()) {
                        continue;
                    }
                    channelSegment0.cleanPrev();
                    continue;
                }
                if(object0 == BufferedChannelKt.SUSPEND_NO_WAITER) {
                    return this.hasNextOnNoWaiterSuspend(channelSegment0, v2, v, continuation0);
                }
                channelSegment0.cleanPrev();
                this.receiveResult = object0;
                return Boxing.boxBoolean(true);
            }
            throw new IllegalStateException("unreachable");
        }

        private final Object hasNextOnNoWaiterSuspend(ChannelSegment channelSegment0, int v, long v1, Continuation continuation0) {
            Boolean boolean0;
            ChannelSegment channelSegment2;
            BufferedChannel bufferedChannel0 = BufferedChannel.this;
            CancellableContinuationImpl cancellableContinuationImpl0 = CancellableContinuationKt.getOrCreateCancellableContinuation(IntrinsicsKt.intercepted(continuation0));
            try {
                Function1 function10 = null;
                this.continuation = cancellableContinuationImpl0;
                Object object0 = bufferedChannel0.updateCellReceive(channelSegment0, v, v1, this);
                if(object0 == BufferedChannelKt.SUSPEND) {
                    bufferedChannel0.prepareReceiverForSuspension(this, channelSegment0, v);
                }
                else if(object0 == BufferedChannelKt.FAILED) {
                    if(v1 < bufferedChannel0.getSendersCounter$kotlinx_coroutines_core()) {
                        channelSegment0.cleanPrev();
                    }
                    ChannelSegment channelSegment1 = (ChannelSegment)BufferedChannel.receiveSegment$FU.get(bufferedChannel0);
                    while(!bufferedChannel0.isClosedForReceive()) {
                        long v2 = BufferedChannel.receivers$FU.getAndIncrement(bufferedChannel0);
                        long v3 = v2 / ((long)BufferedChannelKt.SEGMENT_SIZE);
                        int v4 = (int)(v2 % ((long)BufferedChannelKt.SEGMENT_SIZE));
                        if(channelSegment1.id == v3) {
                            channelSegment2 = channelSegment1;
                        }
                        else {
                            ChannelSegment channelSegment3 = bufferedChannel0.findSegmentReceive(v3, channelSegment1);
                            if(channelSegment3 == null) {
                                continue;
                            }
                            channelSegment2 = channelSegment3;
                        }
                        Object object1 = bufferedChannel0.updateCellReceive(channelSegment2, v4, v2, this);
                        if(object1 == BufferedChannelKt.SUSPEND) {
                            goto label_39;
                        }
                        if(object1 == BufferedChannelKt.FAILED) {
                            if(v2 < bufferedChannel0.getSendersCounter$kotlinx_coroutines_core()) {
                                channelSegment2.cleanPrev();
                            }
                            channelSegment1 = channelSegment2;
                            continue;
                        }
                        if(object1 == BufferedChannelKt.SUSPEND_NO_WAITER) {
                            throw new IllegalStateException("unexpected");
                        }
                        channelSegment2.cleanPrev();
                        this.receiveResult = object1;
                        this.continuation = null;
                        boolean0 = Boxing.boxBoolean(true);
                        Function1 function11 = bufferedChannel0.onUndeliveredElement;
                        if(function11 == null) {
                            cancellableContinuationImpl0.resume(boolean0, null);
                            goto label_60;
                        label_39:
                            if(this instanceof Waiter) {
                                function10 = this;
                            }
                            if(function10 != null) {
                                bufferedChannel0.prepareReceiverForSuspension(((Waiter)function10), channelSegment2, v4);
                            }
                        }
                        else {
                            cancellableContinuationImpl0.resume(boolean0, OnUndeliveredElementKt.bindCancellationFun(function11, object1, cancellableContinuationImpl0.getContext()));
                        }
                        goto label_60;
                    }
                    this.onClosedHasNextNoWaiterSuspend();
                }
                else {
                    channelSegment0.cleanPrev();
                    this.receiveResult = object0;
                    this.continuation = null;
                    boolean0 = Boxing.boxBoolean(true);
                    Function1 function12 = bufferedChannel0.onUndeliveredElement;
                    if(function12 != null) {
                        function10 = OnUndeliveredElementKt.bindCancellationFun(function12, object0, cancellableContinuationImpl0.getContext());
                    }
                    cancellableContinuationImpl0.resume(boolean0, function10);
                }
            }
            catch(Throwable throwable0) {
                cancellableContinuationImpl0.releaseClaimedReusableContinuation$kotlinx_coroutines_core();
                throw throwable0;
            }
        label_60:
            Object object2 = cancellableContinuationImpl0.getResult();
            if(object2 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                DebugProbesKt.probeCoroutineSuspended(continuation0);
            }
            return object2;
        }

        @Override  // kotlinx.coroutines.Waiter
        public void invokeOnCancellation(Segment segment0, int v) {
            CancellableContinuationImpl cancellableContinuationImpl0 = this.continuation;
            if(cancellableContinuationImpl0 != null) {
                cancellableContinuationImpl0.invokeOnCancellation(segment0, v);
            }
        }

        @Override  // kotlinx.coroutines.channels.ChannelIterator
        public Object next() {
            Object object0 = this.receiveResult;
            if(object0 == BufferedChannelKt.NO_RECEIVE_RESULT) {
                throw new IllegalStateException("`hasNext()` has not been invoked");
            }
            this.receiveResult = BufferedChannelKt.NO_RECEIVE_RESULT;
            if(object0 == BufferedChannelKt.getCHANNEL_CLOSED()) {
                throw StackTraceRecoveryKt.recoverStackTrace(BufferedChannel.this.getReceiveException());
            }
            return object0;
        }

        @Override  // kotlinx.coroutines.channels.ChannelIterator
        @Deprecated(level = DeprecationLevel.HIDDEN, message = "Since 1.3.0, binary compatibility with versions <= 1.2.x")
        public Object next(Continuation continuation0) {
            return DefaultImpls.next(this, continuation0);
        }

        private final boolean onClosedHasNext() {
            this.receiveResult = BufferedChannelKt.getCHANNEL_CLOSED();
            Throwable throwable0 = BufferedChannel.this.getCloseCause();
            if(throwable0 != null) {
                throw StackTraceRecoveryKt.recoverStackTrace(throwable0);
            }
            return false;
        }

        private final void onClosedHasNextNoWaiterSuspend() {
            CancellableContinuationImpl cancellableContinuationImpl0 = this.continuation;
            Intrinsics.checkNotNull(cancellableContinuationImpl0);
            this.continuation = null;
            this.receiveResult = BufferedChannelKt.getCHANNEL_CLOSED();
            Throwable throwable0 = BufferedChannel.this.getCloseCause();
            if(throwable0 == null) {
                cancellableContinuationImpl0.resumeWith(Boolean.FALSE);
                return;
            }
            cancellableContinuationImpl0.resumeWith(Result.constructor-impl(ResultKt.createFailure(throwable0)));
        }

        public final boolean tryResumeHasNext(Object object0) {
            CancellableContinuationImpl cancellableContinuationImpl0 = this.continuation;
            Intrinsics.checkNotNull(cancellableContinuationImpl0);
            Function1 function10 = null;
            this.continuation = null;
            this.receiveResult = object0;
            Function1 function11 = BufferedChannel.this.onUndeliveredElement;
            if(function11 != null) {
                function10 = OnUndeliveredElementKt.bindCancellationFun(function11, object0, cancellableContinuationImpl0.getContext());
            }
            return BufferedChannelKt.tryResume0(cancellableContinuationImpl0, Boolean.TRUE, function10);
        }

        public final void tryResumeHasNextOnClosedChannel() {
            CancellableContinuationImpl cancellableContinuationImpl0 = this.continuation;
            Intrinsics.checkNotNull(cancellableContinuationImpl0);
            this.continuation = null;
            this.receiveResult = BufferedChannelKt.getCHANNEL_CLOSED();
            Throwable throwable0 = BufferedChannel.this.getCloseCause();
            if(throwable0 == null) {
                cancellableContinuationImpl0.resumeWith(Boolean.FALSE);
                return;
            }
            cancellableContinuationImpl0.resumeWith(Result.constructor-impl(ResultKt.createFailure(throwable0)));
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000B\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001A\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u001D\u0010\b\u001A\u00020\t2\n\u0010\n\u001A\u0006\u0012\u0002\b\u00030\u000B2\u0006\u0010\f\u001A\u00020\rH\u0096\u0001R\u0017\u0010\u0002\u001A\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001A\u0004\b\u0006\u0010\u0007¨\u0006\u000E"}, d2 = {"Lkotlinx/coroutines/channels/BufferedChannel$SendBroadcast;", "Lkotlinx/coroutines/Waiter;", "cont", "Lkotlinx/coroutines/CancellableContinuation;", "", "(Lkotlinx/coroutines/CancellableContinuation;)V", "getCont", "()Lkotlinx/coroutines/CancellableContinuation;", "invokeOnCancellation", "", "segment", "Lkotlinx/coroutines/internal/Segment;", "index", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    static final class SendBroadcast implements Waiter {
        private final CancellableContinuationImpl $$delegate_0;
        private final CancellableContinuation cont;

        public SendBroadcast(CancellableContinuation cancellableContinuation0) {
            this.cont = cancellableContinuation0;
            Intrinsics.checkNotNull(cancellableContinuation0, "null cannot be cast to non-null type kotlinx.coroutines.CancellableContinuationImpl<kotlin.Boolean>");
            this.$$delegate_0 = (CancellableContinuationImpl)cancellableContinuation0;
        }

        public final CancellableContinuation getCont() {
            return this.cont;
        }

        @Override  // kotlinx.coroutines.Waiter
        public void invokeOnCancellation(Segment segment0, int v) {
            this.$$delegate_0.invokeOnCancellation(segment0, v);
        }
    }

    @Volatile
    private volatile Object _closeCause;
    private static final AtomicReferenceFieldUpdater _closeCause$FU;
    @Volatile
    private volatile long bufferEnd;
    private static final AtomicLongFieldUpdater bufferEnd$FU;
    @Volatile
    private volatile Object bufferEndSegment;
    private static final AtomicReferenceFieldUpdater bufferEndSegment$FU;
    private final int capacity;
    @Volatile
    private volatile Object closeHandler;
    private static final AtomicReferenceFieldUpdater closeHandler$FU;
    @Volatile
    private volatile long completedExpandBuffersAndPauseFlag;
    private static final AtomicLongFieldUpdater completedExpandBuffersAndPauseFlag$FU;
    public final Function1 onUndeliveredElement;
    private final Function3 onUndeliveredElementReceiveCancellationConstructor;
    @Volatile
    private volatile Object receiveSegment;
    private static final AtomicReferenceFieldUpdater receiveSegment$FU;
    @Volatile
    private volatile long receivers;
    private static final AtomicLongFieldUpdater receivers$FU;
    @Volatile
    private volatile Object sendSegment;
    private static final AtomicReferenceFieldUpdater sendSegment$FU;
    @Volatile
    private volatile long sendersAndCloseStatus;
    private static final AtomicLongFieldUpdater sendersAndCloseStatus$FU;

    static {
        BufferedChannel.sendersAndCloseStatus$FU = AtomicLongFieldUpdater.newUpdater(BufferedChannel.class, "sendersAndCloseStatus");
        BufferedChannel.receivers$FU = AtomicLongFieldUpdater.newUpdater(BufferedChannel.class, "receivers");
        BufferedChannel.bufferEnd$FU = AtomicLongFieldUpdater.newUpdater(BufferedChannel.class, "bufferEnd");
        BufferedChannel.completedExpandBuffersAndPauseFlag$FU = AtomicLongFieldUpdater.newUpdater(BufferedChannel.class, "completedExpandBuffersAndPauseFlag");
        BufferedChannel.sendSegment$FU = AtomicReferenceFieldUpdater.newUpdater(BufferedChannel.class, Object.class, "sendSegment");
        BufferedChannel.receiveSegment$FU = AtomicReferenceFieldUpdater.newUpdater(BufferedChannel.class, Object.class, "receiveSegment");
        BufferedChannel.bufferEndSegment$FU = AtomicReferenceFieldUpdater.newUpdater(BufferedChannel.class, Object.class, "bufferEndSegment");
        BufferedChannel._closeCause$FU = AtomicReferenceFieldUpdater.newUpdater(BufferedChannel.class, Object.class, "_closeCause");
        BufferedChannel.closeHandler$FU = AtomicReferenceFieldUpdater.newUpdater(BufferedChannel.class, Object.class, "closeHandler");
    }

    public BufferedChannel(int v, Function1 function10) {
        this.capacity = v;
        this.onUndeliveredElement = function10;
        if(v < 0) {
            throw new IllegalArgumentException(("Invalid channel capacity: " + v + ", should be >=0").toString());
        }
        this.bufferEnd = BufferedChannelKt.access$initialBufferEnd(v);
        this.completedExpandBuffersAndPauseFlag = this.getBufferEndCounter();
        ChannelSegment channelSegment0 = new ChannelSegment(0L, null, this, 3);
        this.sendSegment = channelSegment0;
        this.receiveSegment = channelSegment0;
        if(this.isRendezvousOrUnlimited()) {
            channelSegment0 = BufferedChannelKt.access$getNULL_SEGMENT$p();
            Intrinsics.checkNotNull(channelSegment0, "null cannot be cast to non-null type kotlinx.coroutines.channels.ChannelSegment<E of kotlinx.coroutines.channels.BufferedChannel>");
        }
        this.bufferEndSegment = channelSegment0;
        Function3 function30 = function10 == null ? null : new Function3() {
            {
                BufferedChannel.this = bufferedChannel0;
                super(3);
            }

            @Override  // kotlin.jvm.functions.Function3
            public Object invoke(Object object0, Object object1, Object object2) {
                return this.invoke(((SelectInstance)object0), object1, object2);
            }

            public final Function1 invoke(SelectInstance selectInstance0, Object object0, Object object1) {
                return new Function1(BufferedChannel.this, selectInstance0) {
                    final Object $element;
                    final SelectInstance $select;

                    {
                        this.$element = object0;
                        BufferedChannel.this = bufferedChannel0;
                        this.$select = selectInstance0;
                        super(1);
                    }

                    @Override  // kotlin.jvm.functions.Function1
                    public Object invoke(Object object0) {
                        this.invoke(((Throwable)object0));
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Throwable throwable0) {
                        Symbol symbol0 = BufferedChannelKt.getCHANNEL_CLOSED();
                        if(this.$element != symbol0) {
                            CoroutineContext coroutineContext0 = this.$select.getContext();
                            OnUndeliveredElementKt.callUndeliveredElement(BufferedChannel.this.onUndeliveredElement, this.$element, coroutineContext0);
                        }
                    }
                };
            }
        };
        this.onUndeliveredElementReceiveCancellationConstructor = function30;
        this._closeCause = BufferedChannelKt.access$getNO_CLOSE_CAUSE$p();
    }

    public BufferedChannel(int v, Function1 function10, int v1, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v1 & 2) != 0) {
            function10 = null;
        }
        this(v, function10);
    }

    public static final Object access$onClosedSend(BufferedChannel bufferedChannel0, Object object0, Continuation continuation0) {
        return bufferedChannel0.onClosedSend(object0, continuation0);
    }

    public static final Object access$receiveOnNoWaiterSuspend(BufferedChannel bufferedChannel0, ChannelSegment channelSegment0, int v, long v1, Continuation continuation0) {
        return bufferedChannel0.receiveOnNoWaiterSuspend(channelSegment0, v, v1, continuation0);
    }

    public static final Object access$sendOnNoWaiterSuspend(BufferedChannel bufferedChannel0, ChannelSegment channelSegment0, int v, Object object0, long v1, Continuation continuation0) {
        return bufferedChannel0.sendOnNoWaiterSuspend(channelSegment0, v, object0, v1, continuation0);
    }

    private final boolean bufferOrRendezvousSend(long v) {
        return v < this.getBufferEndCounter() || v < this.getReceiversCounter$kotlinx_coroutines_core() + ((long)this.capacity);
    }

    @Override  // kotlinx.coroutines.channels.ReceiveChannel
    public final void cancel() {
        this.cancelImpl$kotlinx_coroutines_core(null);
    }

    @Override  // kotlinx.coroutines.channels.ReceiveChannel
    public final void cancel(CancellationException cancellationException0) {
        this.cancelImpl$kotlinx_coroutines_core(cancellationException0);
    }

    @Override  // kotlinx.coroutines.channels.ReceiveChannel
    public final boolean cancel(Throwable throwable0) {
        return this.cancelImpl$kotlinx_coroutines_core(throwable0);
    }

    public boolean cancelImpl$kotlinx_coroutines_core(Throwable throwable0) {
        if(throwable0 == null) {
            throwable0 = new CancellationException("Channel was cancelled");
        }
        return this.closeOrCancelImpl(throwable0, true);
    }

    private final void cancelSuspendedReceiveRequests(ChannelSegment channelSegment0, long v) {
        Object object1;
        Object object0 = InlineList.constructor-impl$default(null, 1, null);
    alab1:
        while(channelSegment0 != null) {
            int v1 = BufferedChannelKt.SEGMENT_SIZE - 1;
            while(-1 < v1) {
                if(channelSegment0.id * ((long)BufferedChannelKt.SEGMENT_SIZE) + ((long)v1) < v) {
                    break alab1;
                }
            alab2:
                while(true) {
                    do {
                        do {
                            object1 = channelSegment0.getState$kotlinx_coroutines_core(v1);
                            if(object1 == null || object1 == BufferedChannelKt.access$getIN_BUFFER$p()) {
                                goto label_17;
                            }
                            if(!(object1 instanceof WaiterEB)) {
                                goto label_12;
                            }
                        }
                        while(!channelSegment0.casState$kotlinx_coroutines_core(v1, object1, BufferedChannelKt.getCHANNEL_CLOSED()));
                        object0 = InlineList.plus-FjFbRPM(object0, ((WaiterEB)object1).waiter);
                        channelSegment0.onCancelledRequest(v1, true);
                        break alab2;
                    label_12:
                        if(!(object1 instanceof Waiter)) {
                            break alab2;
                        }
                    }
                    while(!channelSegment0.casState$kotlinx_coroutines_core(v1, object1, BufferedChannelKt.getCHANNEL_CLOSED()));
                    object0 = InlineList.plus-FjFbRPM(object0, object1);
                    channelSegment0.onCancelledRequest(v1, true);
                    break;
                label_17:
                    if(channelSegment0.casState$kotlinx_coroutines_core(v1, object1, BufferedChannelKt.getCHANNEL_CLOSED())) {
                        channelSegment0.onSlotCleaned();
                        break;
                    }
                }
                --v1;
            }
            channelSegment0 = (ChannelSegment)channelSegment0.getPrev();
        }
        if(object0 != null) {
            if(!(object0 instanceof ArrayList)) {
                this.resumeReceiverOnClosedChannel(((Waiter)object0));
                return;
            }
            Intrinsics.checkNotNull(object0, "null cannot be cast to non-null type java.util.ArrayList<E of kotlinx.coroutines.internal.InlineList>{ kotlin.collections.TypeAliasesKt.ArrayList<E of kotlinx.coroutines.internal.InlineList> }");
            for(int v2 = ((ArrayList)object0).size() - 1; -1 < v2; --v2) {
                this.resumeReceiverOnClosedChannel(((Waiter)((ArrayList)object0).get(v2)));
            }
        }
    }

    public final void checkSegmentStructureInvariants() {
        if(this.isRendezvousOrUnlimited()) {
            if(BufferedChannel.bufferEndSegment$FU.get(this) != BufferedChannelKt.NULL_SEGMENT) {
                throw new IllegalStateException(("bufferEndSegment must be NULL_SEGMENT for rendezvous and unlimited channels; they do not manipulate it.\nChannel state: " + this).toString());
            }
        }
        else if(((ChannelSegment)BufferedChannel.receiveSegment$FU.get(this)).id > ((ChannelSegment)BufferedChannel.bufferEndSegment$FU.get(this)).id) {
            throw new IllegalStateException(("bufferEndSegment should not have lower id than receiveSegment.\nChannel state: " + this).toString());
        }
        Iterable iterable0 = CollectionsKt.listOf(new ChannelSegment[]{BufferedChannel.receiveSegment$FU.get(this), BufferedChannel.sendSegment$FU.get(this), BufferedChannel.bufferEndSegment$FU.get(this)});
        Collection collection0 = new ArrayList();
        for(Object object0: iterable0) {
            if(((ChannelSegment)object0) != BufferedChannelKt.NULL_SEGMENT) {
                collection0.add(object0);
            }
        }
        Iterator iterator1 = ((List)collection0).iterator();
        if(!iterator1.hasNext()) {
            throw new NoSuchElementException();
        }
        Object object1 = iterator1.next();
        if(iterator1.hasNext()) {
            long v = ((ChannelSegment)object1).id;
            while(true) {
                Object object2 = iterator1.next();
                long v1 = ((ChannelSegment)object2).id;
                if(v > v1) {
                    object1 = object2;
                    v = v1;
                }
                if(!iterator1.hasNext()) {
                    break;
                }
            }
        }
        ChannelSegment channelSegment0 = (ChannelSegment)object1;
        if(channelSegment0.getPrev() != null) {
            throw new IllegalStateException(("All processed segments should be unreachable from the data structure, but the `prev` link of the leftmost segment is non-null.\nChannel state: " + this).toString());
        }
        while(channelSegment0.getNext() != null) {
            ConcurrentLinkedListNode concurrentLinkedListNode0 = channelSegment0.getNext();
            Intrinsics.checkNotNull(concurrentLinkedListNode0);
            if(((ChannelSegment)concurrentLinkedListNode0).getPrev() != null) {
                ConcurrentLinkedListNode concurrentLinkedListNode1 = channelSegment0.getNext();
                Intrinsics.checkNotNull(concurrentLinkedListNode1);
                if(((ChannelSegment)concurrentLinkedListNode1).getPrev() != channelSegment0) {
                    throw new IllegalStateException(("The `segment.next.prev === segment` invariant is violated.\nChannel state: " + this).toString());
                }
            }
            int v2 = BufferedChannelKt.SEGMENT_SIZE;
            int v4 = 0;
            for(int v3 = 0; v3 < v2; ++v3) {
                Object object3 = channelSegment0.getState$kotlinx_coroutines_core(v3);
                if(!Intrinsics.areEqual(object3, BufferedChannelKt.BUFFERED) && !(object3 instanceof Waiter)) {
                    if(((Intrinsics.areEqual(object3, BufferedChannelKt.INTERRUPTED_RCV) ? true : Intrinsics.areEqual(object3, BufferedChannelKt.INTERRUPTED_SEND)) ? true : Intrinsics.areEqual(object3, BufferedChannelKt.getCHANNEL_CLOSED()))) {
                        if(channelSegment0.getElement$kotlinx_coroutines_core(v3) != null) {
                            throw new IllegalStateException("Check failed.");
                        }
                        ++v4;
                        continue;
                    }
                    else {
                        if(!(Intrinsics.areEqual(object3, BufferedChannelKt.POISONED) ? true : Intrinsics.areEqual(object3, BufferedChannelKt.DONE_RCV))) {
                            throw new IllegalStateException(("Unexpected segment cell state: " + object3 + ".\nChannel state: " + this).toString());
                        }
                        if(channelSegment0.getElement$kotlinx_coroutines_core(v3) != null) {
                            throw new IllegalStateException("Check failed.");
                        }
                    }
                }
            }
            if(v4 == BufferedChannelKt.SEGMENT_SIZE && channelSegment0 != BufferedChannel.receiveSegment$FU.get(this) && channelSegment0 != BufferedChannel.sendSegment$FU.get(this) && channelSegment0 != BufferedChannel.bufferEndSegment$FU.get(this)) {
                throw new IllegalStateException(("Logically removed segment is reachable.\nChannel state: " + this).toString());
            }
            ConcurrentLinkedListNode concurrentLinkedListNode2 = channelSegment0.getNext();
            Intrinsics.checkNotNull(concurrentLinkedListNode2);
            channelSegment0 = (ChannelSegment)concurrentLinkedListNode2;
        }
    }

    @Override  // kotlinx.coroutines.channels.SendChannel
    public boolean close(Throwable throwable0) {
        return this.closeOrCancelImpl(throwable0, false);
    }

    private final ChannelSegment closeLinkedList() {
        ChannelSegment channelSegment0 = BufferedChannel.bufferEndSegment$FU.get(this);
        ChannelSegment channelSegment1 = (ChannelSegment)BufferedChannel.sendSegment$FU.get(this);
        if(channelSegment1.id > channelSegment0.id) {
            channelSegment0 = channelSegment1;
        }
        ChannelSegment channelSegment2 = (ChannelSegment)BufferedChannel.receiveSegment$FU.get(this);
        if(channelSegment2.id > channelSegment0.id) {
            channelSegment0 = channelSegment2;
        }
        return (ChannelSegment)ConcurrentLinkedListKt.close(channelSegment0);
    }

    protected boolean closeOrCancelImpl(Throwable throwable0, boolean z) {
        if(z) {
            this.markCancellationStarted();
        }
        boolean z1 = AbstractResolvableFuture.SafeAtomicHelper..ExternalSyntheticBackportWithForwarding0.m(BufferedChannel._closeCause$FU, this, BufferedChannelKt.NO_CLOSE_CAUSE, throwable0);
        if(z) {
            this.markCancelled();
        }
        else {
            this.markClosed();
        }
        this.completeCloseOrCancel();
        if(z1) {
            this.invokeCloseHandler();
        }
        return z1;
    }

    private final void completeCancel(long v) {
        this.removeUnprocessedElements(this.completeClose(v));
    }

    private final ChannelSegment completeClose(long v) {
        ChannelSegment channelSegment0 = this.closeLinkedList();
        if(this.isConflatedDropOldest()) {
            long v1 = this.markAllEmptyCellsAsClosed(channelSegment0);
            if(v1 != -1L) {
                this.dropFirstElementUntilTheSpecifiedCellIsInTheBuffer(v1);
            }
        }
        this.cancelSuspendedReceiveRequests(channelSegment0, v);
        return channelSegment0;
    }

    private final void completeCloseOrCancel() {
        this.isClosedForSend();
    }

    protected final void dropFirstElementUntilTheSpecifiedCellIsInTheBuffer(long v) {
        UndeliveredElementException undeliveredElementException0;
        Function1 function10;
        Object object0;
        int v4;
        long v1;
        ChannelSegment channelSegment0 = (ChannelSegment)BufferedChannel.receiveSegment$FU.get(this);
        while(true) {
            while(true) {
                while(true) {
                    while(true) {
                        do {
                        label_1:
                            AtomicLongFieldUpdater atomicLongFieldUpdater0 = BufferedChannel.receivers$FU;
                            v1 = atomicLongFieldUpdater0.get(this);
                            long v2 = this.getBufferEndCounter();
                            if(v < Math.max(((long)this.capacity) + v1, v2)) {
                                return;
                            }
                        }
                        while(!atomicLongFieldUpdater0.compareAndSet(this, v1, v1 + 1L));
                        long v3 = v1 / ((long)BufferedChannelKt.SEGMENT_SIZE);
                        v4 = (int)(v1 % ((long)BufferedChannelKt.SEGMENT_SIZE));
                        if(channelSegment0.id == v3) {
                            break;
                        }
                        ChannelSegment channelSegment1 = this.findSegmentReceive(v3, channelSegment0);
                        if(channelSegment1 != null) {
                            channelSegment0 = channelSegment1;
                            break;
                        }
                    }
                    object0 = this.updateCellReceive(channelSegment0, v4, v1, null);
                    if(object0 != BufferedChannelKt.FAILED) {
                        break;
                    }
                    if(v1 < this.getSendersCounter$kotlinx_coroutines_core()) {
                        channelSegment0.cleanPrev();
                    }
                }
                channelSegment0.cleanPrev();
                function10 = this.onUndeliveredElement;
                if(function10 == null) {
                    goto label_1;
                }
                break;
            }
            undeliveredElementException0 = OnUndeliveredElementKt.callUndeliveredElementCatchingException$default(function10, object0, null, 2, null);
            if(undeliveredElementException0 == null) {
                goto label_1;
            }
            break;
        }
        throw undeliveredElementException0;
    }

    private final void expandBuffer() {
        if(this.isRendezvousOrUnlimited()) {
            return;
        }
        ChannelSegment channelSegment0 = (ChannelSegment)BufferedChannel.bufferEndSegment$FU.get(this);
        while(true) {
            long v = BufferedChannel.bufferEnd$FU.getAndIncrement(this);
            long v1 = v / ((long)BufferedChannelKt.SEGMENT_SIZE);
            if(this.getSendersCounter$kotlinx_coroutines_core() <= v) {
                if(channelSegment0.id < v1 && channelSegment0.getNext() != null) {
                    this.moveSegmentBufferEndToSpecifiedOrLast(v1, channelSegment0);
                }
                BufferedChannel.incCompletedExpandBufferAttempts$default(this, 0L, 1, null);
                return;
            }
            if(Long.compare(channelSegment0.id, v1) != 0) {
                ChannelSegment channelSegment1 = this.findSegmentBufferEnd(v1, channelSegment0, v);
                if(channelSegment1 == null) {
                    continue;
                }
                channelSegment0 = channelSegment1;
            }
            if(this.updateCellExpandBuffer(channelSegment0, ((int)(v % ((long)BufferedChannelKt.SEGMENT_SIZE))), v)) {
                BufferedChannel.incCompletedExpandBufferAttempts$default(this, 0L, 1, null);
                return;
            }
            BufferedChannel.incCompletedExpandBufferAttempts$default(this, 0L, 1, null);
        }
    }

    private final ChannelSegment findSegmentBufferEnd(long v, ChannelSegment channelSegment0, long v1) {
        Object object0;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater0 = BufferedChannel.bufferEndSegment$FU;
        Function2 function20 = (Function2)BufferedChannelKt.createSegmentFunction();
    alab1:
        while(true) {
            object0 = ConcurrentLinkedListKt.findSegmentInternal(channelSegment0, v, function20);
            if(SegmentOrClosed.isClosed-impl(object0)) {
                break;
            }
            Segment segment0 = SegmentOrClosed.getSegment-impl(object0);
            while(true) {
                Segment segment1 = (Segment)atomicReferenceFieldUpdater0.get(this);
                if(segment1.id >= segment0.id) {
                    break alab1;
                }
                if(!segment0.tryIncPointers$kotlinx_coroutines_core()) {
                    break;
                }
                if(AbstractResolvableFuture.SafeAtomicHelper..ExternalSyntheticBackportWithForwarding0.m(atomicReferenceFieldUpdater0, this, segment1, segment0)) {
                    if(!segment1.decPointers$kotlinx_coroutines_core()) {
                        break alab1;
                    }
                    segment1.remove();
                    break alab1;
                }
                if(segment0.decPointers$kotlinx_coroutines_core()) {
                    segment0.remove();
                }
            }
        }
        if(SegmentOrClosed.isClosed-impl(object0)) {
            this.completeCloseOrCancel();
            this.moveSegmentBufferEndToSpecifiedOrLast(v, channelSegment0);
            BufferedChannel.incCompletedExpandBufferAttempts$default(this, 0L, 1, null);
            return null;
        }
        ChannelSegment channelSegment1 = (ChannelSegment)SegmentOrClosed.getSegment-impl(object0);
        if(channelSegment1.id > v) {
            if(BufferedChannel.bufferEnd$FU.compareAndSet(this, v1 + 1L, ((long)BufferedChannelKt.SEGMENT_SIZE) * channelSegment1.id)) {
                this.incCompletedExpandBufferAttempts(channelSegment1.id * ((long)BufferedChannelKt.SEGMENT_SIZE) - v1);
                return null;
            }
            BufferedChannel.incCompletedExpandBufferAttempts$default(this, 0L, 1, null);
            return null;
        }
        return channelSegment1;
    }

    private final ChannelSegment findSegmentReceive(long v, ChannelSegment channelSegment0) {
        Object object0;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater0 = BufferedChannel.receiveSegment$FU;
        Function2 function20 = (Function2)BufferedChannelKt.createSegmentFunction();
    alab1:
        while(true) {
            object0 = ConcurrentLinkedListKt.findSegmentInternal(channelSegment0, v, function20);
            if(SegmentOrClosed.isClosed-impl(object0)) {
                break;
            }
            Segment segment0 = SegmentOrClosed.getSegment-impl(object0);
            while(true) {
                Segment segment1 = (Segment)atomicReferenceFieldUpdater0.get(this);
                if(segment1.id >= segment0.id) {
                    break alab1;
                }
                if(!segment0.tryIncPointers$kotlinx_coroutines_core()) {
                    break;
                }
                if(AbstractResolvableFuture.SafeAtomicHelper..ExternalSyntheticBackportWithForwarding0.m(atomicReferenceFieldUpdater0, this, segment1, segment0)) {
                    if(!segment1.decPointers$kotlinx_coroutines_core()) {
                        break alab1;
                    }
                    segment1.remove();
                    break alab1;
                }
                if(segment0.decPointers$kotlinx_coroutines_core()) {
                    segment0.remove();
                }
            }
        }
        if(SegmentOrClosed.isClosed-impl(object0)) {
            this.completeCloseOrCancel();
            if(channelSegment0.id * ((long)BufferedChannelKt.SEGMENT_SIZE) < this.getSendersCounter$kotlinx_coroutines_core()) {
                channelSegment0.cleanPrev();
            }
            return null;
        }
        ChannelSegment channelSegment1 = (ChannelSegment)SegmentOrClosed.getSegment-impl(object0);
        if(!this.isRendezvousOrUnlimited() && v <= this.getBufferEndCounter() / ((long)BufferedChannelKt.SEGMENT_SIZE)) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater1 = BufferedChannel.bufferEndSegment$FU;
            while(true) {
                Segment segment2 = (Segment)atomicReferenceFieldUpdater1.get(this);
                if(segment2.id >= channelSegment1.id || !channelSegment1.tryIncPointers$kotlinx_coroutines_core()) {
                    break;
                }
                if(AbstractResolvableFuture.SafeAtomicHelper..ExternalSyntheticBackportWithForwarding0.m(atomicReferenceFieldUpdater1, this, segment2, channelSegment1)) {
                    if(!segment2.decPointers$kotlinx_coroutines_core()) {
                        break;
                    }
                    segment2.remove();
                    break;
                }
                if(channelSegment1.decPointers$kotlinx_coroutines_core()) {
                    channelSegment1.remove();
                }
            }
        }
        if(channelSegment1.id > v) {
            this.updateReceiversCounterIfLower(channelSegment1.id * ((long)BufferedChannelKt.SEGMENT_SIZE));
            if(channelSegment1.id * ((long)BufferedChannelKt.SEGMENT_SIZE) < this.getSendersCounter$kotlinx_coroutines_core()) {
                channelSegment1.cleanPrev();
            }
            return null;
        }
        return channelSegment1;
    }

    private final ChannelSegment findSegmentSend(long v, ChannelSegment channelSegment0) {
        Object object0;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater0 = BufferedChannel.sendSegment$FU;
        Function2 function20 = (Function2)BufferedChannelKt.createSegmentFunction();
    alab1:
        while(true) {
            object0 = ConcurrentLinkedListKt.findSegmentInternal(channelSegment0, v, function20);
            if(SegmentOrClosed.isClosed-impl(object0)) {
                break;
            }
            Segment segment0 = SegmentOrClosed.getSegment-impl(object0);
            while(true) {
                Segment segment1 = (Segment)atomicReferenceFieldUpdater0.get(this);
                if(segment1.id >= segment0.id) {
                    break alab1;
                }
                if(!segment0.tryIncPointers$kotlinx_coroutines_core()) {
                    break;
                }
                if(AbstractResolvableFuture.SafeAtomicHelper..ExternalSyntheticBackportWithForwarding0.m(atomicReferenceFieldUpdater0, this, segment1, segment0)) {
                    if(!segment1.decPointers$kotlinx_coroutines_core()) {
                        break alab1;
                    }
                    segment1.remove();
                    break alab1;
                }
                if(segment0.decPointers$kotlinx_coroutines_core()) {
                    segment0.remove();
                }
            }
        }
        if(SegmentOrClosed.isClosed-impl(object0)) {
            this.completeCloseOrCancel();
            if(channelSegment0.id * ((long)BufferedChannelKt.SEGMENT_SIZE) < this.getReceiversCounter$kotlinx_coroutines_core()) {
                channelSegment0.cleanPrev();
            }
            return null;
        }
        ChannelSegment channelSegment1 = (ChannelSegment)SegmentOrClosed.getSegment-impl(object0);
        if(channelSegment1.id > v) {
            this.updateSendersCounterIfLower(channelSegment1.id * ((long)BufferedChannelKt.SEGMENT_SIZE));
            if(channelSegment1.id * ((long)BufferedChannelKt.SEGMENT_SIZE) < this.getReceiversCounter$kotlinx_coroutines_core()) {
                channelSegment1.cleanPrev();
            }
            return null;
        }
        return channelSegment1;
    }

    private final Object getAndUpdate$atomicfu(AtomicReferenceFieldUpdater atomicReferenceFieldUpdater0, Function1 function10, Object object0) {
        Object object1;
        do {
            object1 = atomicReferenceFieldUpdater0.get(object0);
        }
        while(!AbstractResolvableFuture.SafeAtomicHelper..ExternalSyntheticBackportWithForwarding0.m(atomicReferenceFieldUpdater0, object0, object1, function10.invoke(object1)));
        return object1;
    }

    private final long getBufferEndCounter() {
        return BufferedChannel.bufferEnd$FU.get(this);
    }

    protected final Throwable getCloseCause() {
        return (Throwable)BufferedChannel._closeCause$FU.get(this);
    }

    @Override  // kotlinx.coroutines.channels.ReceiveChannel
    public SelectClause1 getOnReceive() {
        Intrinsics.checkNotNull(kotlinx.coroutines.channels.BufferedChannel.onReceive.1.INSTANCE, "null cannot be cast to non-null type kotlin.Function3<@[ParameterName(name = \'clauseObject\')] kotlin.Any, @[ParameterName(name = \'select\')] kotlinx.coroutines.selects.SelectInstance<*>, @[ParameterName(name = \'param\')] kotlin.Any?, kotlin.Unit>{ kotlinx.coroutines.selects.SelectKt.RegistrationFunction }");
        Function3 function30 = (Function3)TypeIntrinsics.beforeCheckcastToFunctionOfArity(kotlinx.coroutines.channels.BufferedChannel.onReceive.1.INSTANCE, 3);
        Intrinsics.checkNotNull(kotlinx.coroutines.channels.BufferedChannel.onReceive.2.INSTANCE, "null cannot be cast to non-null type kotlin.Function3<@[ParameterName(name = \'clauseObject\')] kotlin.Any, @[ParameterName(name = \'param\')] kotlin.Any?, @[ParameterName(name = \'clauseResult\')] kotlin.Any?, kotlin.Any?>{ kotlinx.coroutines.selects.SelectKt.ProcessResultFunction }");
        return new SelectClause1Impl(this, function30, ((Function3)TypeIntrinsics.beforeCheckcastToFunctionOfArity(kotlinx.coroutines.channels.BufferedChannel.onReceive.2.INSTANCE, 3)), this.onUndeliveredElementReceiveCancellationConstructor);

        @Metadata(k = 3, mv = {1, 8, 0}, xi = 0x30)
        final class kotlinx.coroutines.channels.BufferedChannel.onReceive.1 extends FunctionReferenceImpl implements Function3 {
            public static final kotlinx.coroutines.channels.BufferedChannel.onReceive.1 INSTANCE;

            static {
                kotlinx.coroutines.channels.BufferedChannel.onReceive.1.INSTANCE = new kotlinx.coroutines.channels.BufferedChannel.onReceive.1();
            }

            kotlinx.coroutines.channels.BufferedChannel.onReceive.1() {
                super(3, BufferedChannel.class, "registerSelectForReceive", "registerSelectForReceive(Lkotlinx/coroutines/selects/SelectInstance;Ljava/lang/Object;)V", 0);
            }

            @Override  // kotlin.jvm.functions.Function3
            public Object invoke(Object object0, Object object1, Object object2) {
                this.invoke(((BufferedChannel)object0), ((SelectInstance)object1), object2);
                return Unit.INSTANCE;
            }

            public final void invoke(BufferedChannel bufferedChannel0, SelectInstance selectInstance0, Object object0) {
                bufferedChannel0.registerSelectForReceive(selectInstance0, object0);
            }
        }


        @Metadata(k = 3, mv = {1, 8, 0}, xi = 0x30)
        final class kotlinx.coroutines.channels.BufferedChannel.onReceive.2 extends FunctionReferenceImpl implements Function3 {
            public static final kotlinx.coroutines.channels.BufferedChannel.onReceive.2 INSTANCE;

            static {
                kotlinx.coroutines.channels.BufferedChannel.onReceive.2.INSTANCE = new kotlinx.coroutines.channels.BufferedChannel.onReceive.2();
            }

            kotlinx.coroutines.channels.BufferedChannel.onReceive.2() {
                super(3, BufferedChannel.class, "processResultSelectReceive", "processResultSelectReceive(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", 0);
            }

            @Override  // kotlin.jvm.functions.Function3
            public Object invoke(Object object0, Object object1, Object object2) {
                return this.invoke(((BufferedChannel)object0), object1, object2);
            }

            public final Object invoke(BufferedChannel bufferedChannel0, Object object0, Object object1) {
                return bufferedChannel0.processResultSelectReceive(object0, object1);
            }
        }

    }

    public static void getOnReceive$annotations() {
    }

    @Override  // kotlinx.coroutines.channels.ReceiveChannel
    public SelectClause1 getOnReceiveCatching() {
        Intrinsics.checkNotNull(kotlinx.coroutines.channels.BufferedChannel.onReceiveCatching.1.INSTANCE, "null cannot be cast to non-null type kotlin.Function3<@[ParameterName(name = \'clauseObject\')] kotlin.Any, @[ParameterName(name = \'select\')] kotlinx.coroutines.selects.SelectInstance<*>, @[ParameterName(name = \'param\')] kotlin.Any?, kotlin.Unit>{ kotlinx.coroutines.selects.SelectKt.RegistrationFunction }");
        Function3 function30 = (Function3)TypeIntrinsics.beforeCheckcastToFunctionOfArity(kotlinx.coroutines.channels.BufferedChannel.onReceiveCatching.1.INSTANCE, 3);
        Intrinsics.checkNotNull(kotlinx.coroutines.channels.BufferedChannel.onReceiveCatching.2.INSTANCE, "null cannot be cast to non-null type kotlin.Function3<@[ParameterName(name = \'clauseObject\')] kotlin.Any, @[ParameterName(name = \'param\')] kotlin.Any?, @[ParameterName(name = \'clauseResult\')] kotlin.Any?, kotlin.Any?>{ kotlinx.coroutines.selects.SelectKt.ProcessResultFunction }");
        return new SelectClause1Impl(this, function30, ((Function3)TypeIntrinsics.beforeCheckcastToFunctionOfArity(kotlinx.coroutines.channels.BufferedChannel.onReceiveCatching.2.INSTANCE, 3)), this.onUndeliveredElementReceiveCancellationConstructor);

        @Metadata(k = 3, mv = {1, 8, 0}, xi = 0x30)
        final class kotlinx.coroutines.channels.BufferedChannel.onReceiveCatching.1 extends FunctionReferenceImpl implements Function3 {
            public static final kotlinx.coroutines.channels.BufferedChannel.onReceiveCatching.1 INSTANCE;

            static {
                kotlinx.coroutines.channels.BufferedChannel.onReceiveCatching.1.INSTANCE = new kotlinx.coroutines.channels.BufferedChannel.onReceiveCatching.1();
            }

            kotlinx.coroutines.channels.BufferedChannel.onReceiveCatching.1() {
                super(3, BufferedChannel.class, "registerSelectForReceive", "registerSelectForReceive(Lkotlinx/coroutines/selects/SelectInstance;Ljava/lang/Object;)V", 0);
            }

            @Override  // kotlin.jvm.functions.Function3
            public Object invoke(Object object0, Object object1, Object object2) {
                this.invoke(((BufferedChannel)object0), ((SelectInstance)object1), object2);
                return Unit.INSTANCE;
            }

            public final void invoke(BufferedChannel bufferedChannel0, SelectInstance selectInstance0, Object object0) {
                bufferedChannel0.registerSelectForReceive(selectInstance0, object0);
            }
        }


        @Metadata(k = 3, mv = {1, 8, 0}, xi = 0x30)
        final class kotlinx.coroutines.channels.BufferedChannel.onReceiveCatching.2 extends FunctionReferenceImpl implements Function3 {
            public static final kotlinx.coroutines.channels.BufferedChannel.onReceiveCatching.2 INSTANCE;

            static {
                kotlinx.coroutines.channels.BufferedChannel.onReceiveCatching.2.INSTANCE = new kotlinx.coroutines.channels.BufferedChannel.onReceiveCatching.2();
            }

            kotlinx.coroutines.channels.BufferedChannel.onReceiveCatching.2() {
                super(3, BufferedChannel.class, "processResultSelectReceiveCatching", "processResultSelectReceiveCatching(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", 0);
            }

            @Override  // kotlin.jvm.functions.Function3
            public Object invoke(Object object0, Object object1, Object object2) {
                return this.invoke(((BufferedChannel)object0), object1, object2);
            }

            public final Object invoke(BufferedChannel bufferedChannel0, Object object0, Object object1) {
                return bufferedChannel0.processResultSelectReceiveCatching(object0, object1);
            }
        }

    }

    public static void getOnReceiveCatching$annotations() {
    }

    @Override  // kotlinx.coroutines.channels.ReceiveChannel
    public SelectClause1 getOnReceiveOrNull() {
        Intrinsics.checkNotNull(kotlinx.coroutines.channels.BufferedChannel.onReceiveOrNull.1.INSTANCE, "null cannot be cast to non-null type kotlin.Function3<@[ParameterName(name = \'clauseObject\')] kotlin.Any, @[ParameterName(name = \'select\')] kotlinx.coroutines.selects.SelectInstance<*>, @[ParameterName(name = \'param\')] kotlin.Any?, kotlin.Unit>{ kotlinx.coroutines.selects.SelectKt.RegistrationFunction }");
        Function3 function30 = (Function3)TypeIntrinsics.beforeCheckcastToFunctionOfArity(kotlinx.coroutines.channels.BufferedChannel.onReceiveOrNull.1.INSTANCE, 3);
        Intrinsics.checkNotNull(kotlinx.coroutines.channels.BufferedChannel.onReceiveOrNull.2.INSTANCE, "null cannot be cast to non-null type kotlin.Function3<@[ParameterName(name = \'clauseObject\')] kotlin.Any, @[ParameterName(name = \'param\')] kotlin.Any?, @[ParameterName(name = \'clauseResult\')] kotlin.Any?, kotlin.Any?>{ kotlinx.coroutines.selects.SelectKt.ProcessResultFunction }");
        return new SelectClause1Impl(this, function30, ((Function3)TypeIntrinsics.beforeCheckcastToFunctionOfArity(kotlinx.coroutines.channels.BufferedChannel.onReceiveOrNull.2.INSTANCE, 3)), this.onUndeliveredElementReceiveCancellationConstructor);

        @Metadata(k = 3, mv = {1, 8, 0}, xi = 0x30)
        final class kotlinx.coroutines.channels.BufferedChannel.onReceiveOrNull.1 extends FunctionReferenceImpl implements Function3 {
            public static final kotlinx.coroutines.channels.BufferedChannel.onReceiveOrNull.1 INSTANCE;

            static {
                kotlinx.coroutines.channels.BufferedChannel.onReceiveOrNull.1.INSTANCE = new kotlinx.coroutines.channels.BufferedChannel.onReceiveOrNull.1();
            }

            kotlinx.coroutines.channels.BufferedChannel.onReceiveOrNull.1() {
                super(3, BufferedChannel.class, "registerSelectForReceive", "registerSelectForReceive(Lkotlinx/coroutines/selects/SelectInstance;Ljava/lang/Object;)V", 0);
            }

            @Override  // kotlin.jvm.functions.Function3
            public Object invoke(Object object0, Object object1, Object object2) {
                this.invoke(((BufferedChannel)object0), ((SelectInstance)object1), object2);
                return Unit.INSTANCE;
            }

            public final void invoke(BufferedChannel bufferedChannel0, SelectInstance selectInstance0, Object object0) {
                bufferedChannel0.registerSelectForReceive(selectInstance0, object0);
            }
        }


        @Metadata(k = 3, mv = {1, 8, 0}, xi = 0x30)
        final class kotlinx.coroutines.channels.BufferedChannel.onReceiveOrNull.2 extends FunctionReferenceImpl implements Function3 {
            public static final kotlinx.coroutines.channels.BufferedChannel.onReceiveOrNull.2 INSTANCE;

            static {
                kotlinx.coroutines.channels.BufferedChannel.onReceiveOrNull.2.INSTANCE = new kotlinx.coroutines.channels.BufferedChannel.onReceiveOrNull.2();
            }

            kotlinx.coroutines.channels.BufferedChannel.onReceiveOrNull.2() {
                super(3, BufferedChannel.class, "processResultSelectReceiveOrNull", "processResultSelectReceiveOrNull(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", 0);
            }

            @Override  // kotlin.jvm.functions.Function3
            public Object invoke(Object object0, Object object1, Object object2) {
                return this.invoke(((BufferedChannel)object0), object1, object2);
            }

            public final Object invoke(BufferedChannel bufferedChannel0, Object object0, Object object1) {
                return bufferedChannel0.processResultSelectReceiveOrNull(object0, object1);
            }
        }

    }

    public static void getOnReceiveOrNull$annotations() {
    }

    @Override  // kotlinx.coroutines.channels.SendChannel
    public SelectClause2 getOnSend() {
        Intrinsics.checkNotNull(kotlinx.coroutines.channels.BufferedChannel.onSend.1.INSTANCE, "null cannot be cast to non-null type kotlin.Function3<@[ParameterName(name = \'clauseObject\')] kotlin.Any, @[ParameterName(name = \'select\')] kotlinx.coroutines.selects.SelectInstance<*>, @[ParameterName(name = \'param\')] kotlin.Any?, kotlin.Unit>{ kotlinx.coroutines.selects.SelectKt.RegistrationFunction }");
        Function3 function30 = (Function3)TypeIntrinsics.beforeCheckcastToFunctionOfArity(kotlinx.coroutines.channels.BufferedChannel.onSend.1.INSTANCE, 3);
        Intrinsics.checkNotNull(kotlinx.coroutines.channels.BufferedChannel.onSend.2.INSTANCE, "null cannot be cast to non-null type kotlin.Function3<@[ParameterName(name = \'clauseObject\')] kotlin.Any, @[ParameterName(name = \'param\')] kotlin.Any?, @[ParameterName(name = \'clauseResult\')] kotlin.Any?, kotlin.Any?>{ kotlinx.coroutines.selects.SelectKt.ProcessResultFunction }");
        return new SelectClause2Impl(this, function30, ((Function3)TypeIntrinsics.beforeCheckcastToFunctionOfArity(kotlinx.coroutines.channels.BufferedChannel.onSend.2.INSTANCE, 3)), null, 8, null);

        @Metadata(k = 3, mv = {1, 8, 0}, xi = 0x30)
        final class kotlinx.coroutines.channels.BufferedChannel.onSend.1 extends FunctionReferenceImpl implements Function3 {
            public static final kotlinx.coroutines.channels.BufferedChannel.onSend.1 INSTANCE;

            static {
                kotlinx.coroutines.channels.BufferedChannel.onSend.1.INSTANCE = new kotlinx.coroutines.channels.BufferedChannel.onSend.1();
            }

            kotlinx.coroutines.channels.BufferedChannel.onSend.1() {
                super(3, BufferedChannel.class, "registerSelectForSend", "registerSelectForSend(Lkotlinx/coroutines/selects/SelectInstance;Ljava/lang/Object;)V", 0);
            }

            @Override  // kotlin.jvm.functions.Function3
            public Object invoke(Object object0, Object object1, Object object2) {
                this.invoke(((BufferedChannel)object0), ((SelectInstance)object1), object2);
                return Unit.INSTANCE;
            }

            public final void invoke(BufferedChannel bufferedChannel0, SelectInstance selectInstance0, Object object0) {
                bufferedChannel0.registerSelectForSend(selectInstance0, object0);
            }
        }


        @Metadata(k = 3, mv = {1, 8, 0}, xi = 0x30)
        final class kotlinx.coroutines.channels.BufferedChannel.onSend.2 extends FunctionReferenceImpl implements Function3 {
            public static final kotlinx.coroutines.channels.BufferedChannel.onSend.2 INSTANCE;

            static {
                kotlinx.coroutines.channels.BufferedChannel.onSend.2.INSTANCE = new kotlinx.coroutines.channels.BufferedChannel.onSend.2();
            }

            kotlinx.coroutines.channels.BufferedChannel.onSend.2() {
                super(3, BufferedChannel.class, "processResultSelectSend", "processResultSelectSend(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", 0);
            }

            @Override  // kotlin.jvm.functions.Function3
            public Object invoke(Object object0, Object object1, Object object2) {
                return this.invoke(((BufferedChannel)object0), object1, object2);
            }

            public final Object invoke(BufferedChannel bufferedChannel0, Object object0, Object object1) {
                return bufferedChannel0.processResultSelectSend(object0, object1);
            }
        }

    }

    public static void getOnSend$annotations() {
    }

    private static void getOnUndeliveredElementReceiveCancellationConstructor$annotations() {
    }

    private final Throwable getReceiveException() {
        Throwable throwable0 = this.getCloseCause();
        return throwable0 == null ? new ClosedReceiveChannelException("Channel was closed") : throwable0;
    }

    public final long getReceiversCounter$kotlinx_coroutines_core() {
        return BufferedChannel.receivers$FU.get(this);
    }

    protected final Throwable getSendException() {
        Throwable throwable0 = this.getCloseCause();
        return throwable0 == null ? new ClosedSendChannelException("Channel was closed") : throwable0;
    }

    public final long getSendersCounter$kotlinx_coroutines_core() {
        return BufferedChannel.sendersAndCloseStatus$FU.get(this) & 0xFFFFFFFFFFFFFFFL;
    }

    public final boolean hasElements$kotlinx_coroutines_core() {
        while(true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater0 = BufferedChannel.receiveSegment$FU;
            ChannelSegment channelSegment0 = (ChannelSegment)atomicReferenceFieldUpdater0.get(this);
            long v = this.getReceiversCounter$kotlinx_coroutines_core();
            if(this.getSendersCounter$kotlinx_coroutines_core() <= v) {
                return false;
            }
            long v1 = v / ((long)BufferedChannelKt.SEGMENT_SIZE);
            if(channelSegment0.id != v1) {
                ChannelSegment channelSegment1 = this.findSegmentReceive(v1, channelSegment0);
                if(channelSegment1 == null) {
                    if(((ChannelSegment)atomicReferenceFieldUpdater0.get(this)).id >= v1) {
                        continue;
                    }
                    return false;
                }
                else {
                    channelSegment0 = channelSegment1;
                }
            }
            channelSegment0.cleanPrev();
            if(this.isCellNonEmpty(channelSegment0, ((int)(v % ((long)BufferedChannelKt.SEGMENT_SIZE))), v)) {
                return true;
            }
            BufferedChannel.receivers$FU.compareAndSet(this, v, v + 1L);
        }
    }

    private final void incCompletedExpandBufferAttempts(long v) {
        if((BufferedChannel.completedExpandBuffersAndPauseFlag$FU.addAndGet(this, v) & 0x4000000000000000L) != 0L) {
            while((BufferedChannel.completedExpandBuffersAndPauseFlag$FU.get(this) & 0x4000000000000000L) != 0L) {
            }
        }
    }

    static void incCompletedExpandBufferAttempts$default(BufferedChannel bufferedChannel0, long v, int v1, Object object0) {
        if(object0 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: incCompletedExpandBufferAttempts");
        }
        if((v1 & 1) != 0) {
            v = 1L;
        }
        bufferedChannel0.incCompletedExpandBufferAttempts(v);
    }

    private final void invokeCloseHandler() {
        Object object0;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater0 = BufferedChannel.closeHandler$FU;
        do {
            object0 = atomicReferenceFieldUpdater0.get(this);
        }
        while(!AbstractResolvableFuture.SafeAtomicHelper..ExternalSyntheticBackportWithForwarding0.m(atomicReferenceFieldUpdater0, this, object0, (object0 == null ? BufferedChannelKt.access$getCLOSE_HANDLER_CLOSED$p() : BufferedChannelKt.access$getCLOSE_HANDLER_INVOKED$p())));
        if(object0 == null) {
            return;
        }
        Function1 function10 = (Function1)TypeIntrinsics.beforeCheckcastToFunctionOfArity(object0, 1);
        ((Function1)object0).invoke(this.getCloseCause());
    }

    @Override  // kotlinx.coroutines.channels.SendChannel
    public void invokeOnClose(Function1 function10) {
        Object object0;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater0 = BufferedChannel.closeHandler$FU;
        if(AbstractResolvableFuture.SafeAtomicHelper..ExternalSyntheticBackportWithForwarding0.m(atomicReferenceFieldUpdater0, this, null, function10)) {
            return;
        }
        while(true) {
            object0 = atomicReferenceFieldUpdater0.get(this);
            if(object0 != BufferedChannelKt.CLOSE_HANDLER_CLOSED) {
                break;
            }
            if(AbstractResolvableFuture.SafeAtomicHelper..ExternalSyntheticBackportWithForwarding0.m(BufferedChannel.closeHandler$FU, this, BufferedChannelKt.CLOSE_HANDLER_CLOSED, BufferedChannelKt.CLOSE_HANDLER_INVOKED)) {
                function10.invoke(this.getCloseCause());
                return;
            }
        }
        throw object0 == BufferedChannelKt.CLOSE_HANDLER_INVOKED ? new IllegalStateException("Another handler was already registered and successfully invoked") : new IllegalStateException(("Another handler is already registered: " + object0).toString());
    }

    private final boolean isCellNonEmpty(ChannelSegment channelSegment0, int v, long v1) {
        do {
            Object object0 = channelSegment0.getState$kotlinx_coroutines_core(v);
            if(object0 != null && object0 != BufferedChannelKt.access$getIN_BUFFER$p()) {
                if(object0 == BufferedChannelKt.BUFFERED) {
                    return true;
                }
                if(object0 == BufferedChannelKt.access$getINTERRUPTED_SEND$p()) {
                    return false;
                }
                if(object0 == BufferedChannelKt.getCHANNEL_CLOSED()) {
                    return false;
                }
                if(object0 == BufferedChannelKt.access$getDONE_RCV$p()) {
                    return false;
                }
                if(object0 == BufferedChannelKt.access$getPOISONED$p()) {
                    return false;
                }
                if(object0 == BufferedChannelKt.access$getRESUMING_BY_EB$p()) {
                    return true;
                }
                return object0 == BufferedChannelKt.access$getRESUMING_BY_RCV$p() ? false : v1 == this.getReceiversCounter$kotlinx_coroutines_core();
            }
        }
        while(!channelSegment0.casState$kotlinx_coroutines_core(v, object0, BufferedChannelKt.access$getPOISONED$p()));
        this.expandBuffer();
        return false;
    }

    private final boolean isClosed(long v, boolean z) {
        switch(((int)(v >> 60))) {
            case 0: 
            case 1: {
                return false;
            }
            case 2: {
                this.completeClose(v & 0xFFFFFFFFFFFFFFFL);
                return !z || !this.hasElements$kotlinx_coroutines_core();
            }
            case 3: {
                this.completeCancel(v & 0xFFFFFFFFFFFFFFFL);
                return true;
            }
            default: {
                throw new IllegalStateException(("unexpected close status: " + ((int)(v >> 60))).toString());
            }
        }
    }

    @Override  // kotlinx.coroutines.channels.ReceiveChannel
    public boolean isClosedForReceive() {
        return this.isClosedForReceive0(BufferedChannel.sendersAndCloseStatus$FU.get(this));
    }

    public static void isClosedForReceive$annotations() {
    }

    private final boolean isClosedForReceive0(long v) {
        return this.isClosed(v, true);
    }

    @Override  // kotlinx.coroutines.channels.SendChannel
    public boolean isClosedForSend() {
        return this.isClosedForSend0(BufferedChannel.sendersAndCloseStatus$FU.get(this));
    }

    public static void isClosedForSend$annotations() {
    }

    private final boolean isClosedForSend0(long v) {
        return this.isClosed(v, false);
    }

    protected boolean isConflatedDropOldest() {
        return false;
    }

    @Override  // kotlinx.coroutines.channels.ReceiveChannel
    public boolean isEmpty() {
        if(this.isClosedForReceive()) {
            return false;
        }
        return this.hasElements$kotlinx_coroutines_core() ? false : !this.isClosedForReceive();
    }

    public static void isEmpty$annotations() {
    }

    private final boolean isRendezvousOrUnlimited() {
        long v = this.getBufferEndCounter();
        return v == 0L || v == 0x7FFFFFFFFFFFFFFFL;
    }

    @Override  // kotlinx.coroutines.channels.ReceiveChannel
    public ChannelIterator iterator() {
        return new BufferedChannelIterator(this);
    }

    private final void loop$atomicfu(AtomicLongFieldUpdater atomicLongFieldUpdater0, Function1 function10, Object object0) {
        while(true) {
            function10.invoke(atomicLongFieldUpdater0.get(object0));
        }
    }

    private final void loop$atomicfu(AtomicReferenceFieldUpdater atomicReferenceFieldUpdater0, Function1 function10, Object object0) {
        while(true) {
            function10.invoke(atomicReferenceFieldUpdater0.get(object0));
        }
    }

    private final long markAllEmptyCellsAsClosed(ChannelSegment channelSegment0) {
        do {
            for(int v = BufferedChannelKt.SEGMENT_SIZE - 1; -1 < v; --v) {
                long v1 = channelSegment0.id * ((long)BufferedChannelKt.SEGMENT_SIZE) + ((long)v);
                if(v1 < this.getReceiversCounter$kotlinx_coroutines_core()) {
                    return -1L;
                }
                while(true) {
                    Object object0 = channelSegment0.getState$kotlinx_coroutines_core(v);
                    if(object0 != null && object0 != BufferedChannelKt.IN_BUFFER) {
                        if(object0 != BufferedChannelKt.BUFFERED) {
                            break;
                        }
                        return v1;
                    }
                    if(channelSegment0.casState$kotlinx_coroutines_core(v, object0, BufferedChannelKt.getCHANNEL_CLOSED())) {
                        channelSegment0.onSlotCleaned();
                        break;
                    }
                }
            }
            channelSegment0 = (ChannelSegment)channelSegment0.getPrev();
        }
        while(channelSegment0 != null);
        return -1L;
    }

    private final void markCancellationStarted() {
        AtomicLongFieldUpdater atomicLongFieldUpdater0 = BufferedChannel.sendersAndCloseStatus$FU;
        do {
            long v = atomicLongFieldUpdater0.get(this);
        }
        while(((int)(v >> 60)) == 0 && !atomicLongFieldUpdater0.compareAndSet(this, v, BufferedChannelKt.access$constructSendersAndCloseStatus(0xFFFFFFFFFFFFFFFL & v, 1)));
    }

    private final void markCancelled() {
        AtomicLongFieldUpdater atomicLongFieldUpdater0 = BufferedChannel.sendersAndCloseStatus$FU;
        do {
            long v = atomicLongFieldUpdater0.get(this);
        }
        while(!atomicLongFieldUpdater0.compareAndSet(this, v, BufferedChannelKt.access$constructSendersAndCloseStatus(0xFFFFFFFFFFFFFFFL & v, 3)));
    }

    private final void markClosed() {
        long v1;
        AtomicLongFieldUpdater atomicLongFieldUpdater0 = BufferedChannel.sendersAndCloseStatus$FU;
        do {
            long v = atomicLongFieldUpdater0.get(this);
            switch(((int)(v >> 60))) {
                case 0: {
                    v1 = BufferedChannelKt.access$constructSendersAndCloseStatus(0xFFFFFFFFFFFFFFFL & v, 2);
                    break;
                }
                case 1: {
                    v1 = BufferedChannelKt.access$constructSendersAndCloseStatus(0xFFFFFFFFFFFFFFFL & v, 3);
                    break;
                }
                default: {
                    return;
                }
            }
        }
        while(!atomicLongFieldUpdater0.compareAndSet(this, v, v1));
    }

    private final void moveSegmentBufferEndToSpecifiedOrLast(long v, ChannelSegment channelSegment0) {
        Segment segment0;
        while(channelSegment0.id < v) {
            ChannelSegment channelSegment1 = (ChannelSegment)channelSegment0.getNext();
            if(channelSegment1 == null) {
                break;
            }
            channelSegment0 = channelSegment1;
        }
        while(true) {
            if(channelSegment0.isRemoved()) {
                ChannelSegment channelSegment2 = (ChannelSegment)channelSegment0.getNext();
                if(channelSegment2 != null) {
                    channelSegment0 = channelSegment2;
                    continue;
                }
            }
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater0 = BufferedChannel.bufferEndSegment$FU;
        label_11:
            segment0 = (Segment)atomicReferenceFieldUpdater0.get(this);
            if(segment0.id >= channelSegment0.id) {
                return;
            }
            if(channelSegment0.tryIncPointers$kotlinx_coroutines_core()) {
                break;
            }
        }
        if(AbstractResolvableFuture.SafeAtomicHelper..ExternalSyntheticBackportWithForwarding0.m(atomicReferenceFieldUpdater0, this, segment0, channelSegment0)) {
            if(segment0.decPointers$kotlinx_coroutines_core()) {
                segment0.remove();
            }
            return;
        }
        if(!channelSegment0.decPointers$kotlinx_coroutines_core()) {
            goto label_11;
        }
        channelSegment0.remove();
        goto label_11;
    }

    @Override  // kotlinx.coroutines.channels.SendChannel
    @Deprecated(level = DeprecationLevel.ERROR, message = "Deprecated in the favour of \'trySend\' method", replaceWith = @ReplaceWith(expression = "trySend(element).isSuccess", imports = {}))
    public boolean offer(Object object0) {
        return kotlinx.coroutines.channels.Channel.DefaultImpls.offer(this, object0);
    }

    protected void onClosedIdempotent() {
    }

    private final void onClosedReceiveCatchingOnNoWaiterSuspend(CancellableContinuation cancellableContinuation0) {
        Throwable throwable0 = this.getCloseCause();
        cancellableContinuation0.resumeWith(Result.constructor-impl(ChannelResult.box-impl(ChannelResult.Companion.closed-JP2dKIU(throwable0))));
    }

    private final void onClosedReceiveOnNoWaiterSuspend(CancellableContinuation cancellableContinuation0) {
        cancellableContinuation0.resumeWith(Result.constructor-impl(ResultKt.createFailure(this.getReceiveException())));
    }

    private final void onClosedSelectOnReceive(SelectInstance selectInstance0) {
        selectInstance0.selectInRegistrationPhase(BufferedChannelKt.getCHANNEL_CLOSED());
    }

    private final void onClosedSelectOnSend(Object object0, SelectInstance selectInstance0) {
        Function1 function10 = this.onUndeliveredElement;
        if(function10 != null) {
            OnUndeliveredElementKt.callUndeliveredElement(function10, object0, selectInstance0.getContext());
        }
        selectInstance0.selectInRegistrationPhase(BufferedChannelKt.getCHANNEL_CLOSED());
    }

    private final Object onClosedSend(Object object0, Continuation continuation0) {
        CancellableContinuationImpl cancellableContinuationImpl0 = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation0), 1);
        cancellableContinuationImpl0.initCancellability();
        Function1 function10 = this.onUndeliveredElement;
        if(function10 == null) {
            cancellableContinuationImpl0.resumeWith(Result.constructor-impl(ResultKt.createFailure(this.getSendException())));
        }
        else {
            UndeliveredElementException undeliveredElementException0 = OnUndeliveredElementKt.callUndeliveredElementCatchingException$default(function10, object0, null, 2, null);
            if(undeliveredElementException0 == null) {
                cancellableContinuationImpl0.resumeWith(Result.constructor-impl(ResultKt.createFailure(this.getSendException())));
            }
            else {
                ExceptionsKt.addSuppressed(undeliveredElementException0, this.getSendException());
                cancellableContinuationImpl0.resumeWith(Result.constructor-impl(ResultKt.createFailure(undeliveredElementException0)));
            }
        }
        Object object1 = cancellableContinuationImpl0.getResult();
        if(object1 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation0);
        }
        return object1 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? object1 : Unit.INSTANCE;
    }

    private final void onClosedSendOnNoWaiterSuspend(Object object0, CancellableContinuation cancellableContinuation0) {
        Function1 function10 = this.onUndeliveredElement;
        if(function10 != null) {
            OnUndeliveredElementKt.callUndeliveredElement(function10, object0, cancellableContinuation0.getContext());
        }
        cancellableContinuation0.resumeWith(Result.constructor-impl(ResultKt.createFailure(this.getSendException())));
    }

    protected void onReceiveDequeued() {
    }

    protected void onReceiveEnqueued() {
    }

    @Override  // kotlinx.coroutines.channels.ReceiveChannel
    @Deprecated(level = DeprecationLevel.ERROR, message = "Deprecated in the favour of \'tryReceive\'. Please note that the provided replacement does not rethrow channel\'s close cause as \'poll\' did, for the precise replacement please refer to the \'poll\' documentation", replaceWith = @ReplaceWith(expression = "tryReceive().getOrNull()", imports = {}))
    public Object poll() {
        return kotlinx.coroutines.channels.Channel.DefaultImpls.poll(this);
    }

    private final void prepareReceiverForSuspension(Waiter waiter0, ChannelSegment channelSegment0, int v) {
        waiter0.invokeOnCancellation(channelSegment0, v);
    }

    private final void prepareSenderForSuspension(Waiter waiter0, ChannelSegment channelSegment0, int v) {
        waiter0.invokeOnCancellation(channelSegment0, v + BufferedChannelKt.SEGMENT_SIZE);
    }

    private final Object processResultSelectReceive(Object object0, Object object1) {
        if(object1 == BufferedChannelKt.getCHANNEL_CLOSED()) {
            throw this.getReceiveException();
        }
        return object1;
    }

    private final Object processResultSelectReceiveCatching(Object object0, Object object1) {
        if(object1 == BufferedChannelKt.getCHANNEL_CLOSED()) {
            Throwable throwable0 = this.getCloseCause();
            return ChannelResult.box-impl(ChannelResult.Companion.closed-JP2dKIU(throwable0));
        }
        return ChannelResult.box-impl(ChannelResult.Companion.success-JP2dKIU(object1));
    }

    private final Object processResultSelectReceiveOrNull(Object object0, Object object1) {
        if(object1 == BufferedChannelKt.getCHANNEL_CLOSED()) {
            if(this.getCloseCause() != null) {
                throw this.getReceiveException();
            }
            return null;
        }
        return object1;
    }

    private final Object processResultSelectSend(Object object0, Object object1) {
        if(object1 == BufferedChannelKt.getCHANNEL_CLOSED()) {
            throw this.getSendException();
        }
        return this;
    }

    @Override  // kotlinx.coroutines.channels.ReceiveChannel
    public Object receive(Continuation continuation0) {
        return BufferedChannel.receive$suspendImpl(this, continuation0);
    }

    static Object receive$suspendImpl(BufferedChannel bufferedChannel0, Continuation continuation0) {
        ChannelSegment channelSegment2;
        ChannelSegment channelSegment0 = (ChannelSegment)BufferedChannel.receiveSegment$FU.get(bufferedChannel0);
        while(!bufferedChannel0.isClosedForReceive()) {
            long v = BufferedChannel.receivers$FU.getAndIncrement(bufferedChannel0);
            long v1 = v / ((long)BufferedChannelKt.SEGMENT_SIZE);
            int v2 = (int)(v % ((long)BufferedChannelKt.SEGMENT_SIZE));
            if(channelSegment0.id == v1) {
                channelSegment2 = channelSegment0;
            }
            else {
                ChannelSegment channelSegment1 = bufferedChannel0.findSegmentReceive(v1, channelSegment0);
                if(channelSegment1 == null) {
                    continue;
                }
                channelSegment2 = channelSegment1;
            }
            Object object0 = bufferedChannel0.updateCellReceive(channelSegment2, v2, v, null);
            if(object0 == BufferedChannelKt.SUSPEND) {
                throw new IllegalStateException("unexpected");
            }
            if(object0 == BufferedChannelKt.FAILED) {
                if(v < bufferedChannel0.getSendersCounter$kotlinx_coroutines_core()) {
                    channelSegment2.cleanPrev();
                }
                channelSegment0 = channelSegment2;
                continue;
            }
            if(object0 == BufferedChannelKt.SUSPEND_NO_WAITER) {
                return bufferedChannel0.receiveOnNoWaiterSuspend(channelSegment2, v2, v, continuation0);
            }
            channelSegment2.cleanPrev();
            return object0;
        }
        throw StackTraceRecoveryKt.recoverStackTrace(bufferedChannel0.getReceiveException());
    }

    @Override  // kotlinx.coroutines.channels.ReceiveChannel
    public Object receiveCatching-JP2dKIU(Continuation continuation0) {
        return BufferedChannel.receiveCatching-JP2dKIU$suspendImpl(this, continuation0);
    }

    static Object receiveCatching-JP2dKIU$suspendImpl(BufferedChannel bufferedChannel0, Continuation continuation0) {
        ChannelSegment channelSegment2;
        kotlinx.coroutines.channels.BufferedChannel.receiveCatching.1 bufferedChannel$receiveCatching$10;
        if(continuation0 instanceof kotlinx.coroutines.channels.BufferedChannel.receiveCatching.1) {
            bufferedChannel$receiveCatching$10 = (kotlinx.coroutines.channels.BufferedChannel.receiveCatching.1)continuation0;
            if((bufferedChannel$receiveCatching$10.label & 0x80000000) == 0) {
                bufferedChannel$receiveCatching$10 = new ContinuationImpl(continuation0) {
                    int label;
                    Object result;

                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object object0) {
                        this.result = object0;
                        this.label |= 0x80000000;
                        Object object1 = BufferedChannel.receiveCatching-JP2dKIU$suspendImpl(bufferedChannel0, this);
                        return object1 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? object1 : ChannelResult.box-impl(object1);
                    }
                };
            }
            else {
                bufferedChannel$receiveCatching$10.label ^= 0x80000000;
            }
        }
        else {
            bufferedChannel$receiveCatching$10 = new ContinuationImpl(continuation0) {
                int label;
                Object result;

                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object object0) {
                    this.result = object0;
                    this.label |= 0x80000000;
                    Object object1 = BufferedChannel.receiveCatching-JP2dKIU$suspendImpl(bufferedChannel0, this);
                    return object1 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? object1 : ChannelResult.box-impl(object1);
                }
            };
        }
        Object object0 = bufferedChannel$receiveCatching$10.result;
        Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch(bufferedChannel$receiveCatching$10.label) {
            case 0: {
                ResultKt.throwOnFailure(object0);
                ChannelSegment channelSegment0 = (ChannelSegment)BufferedChannel.receiveSegment$FU.get(bufferedChannel0);
                while(true) {
                    if(bufferedChannel0.isClosedForReceive()) {
                        Throwable throwable0 = bufferedChannel0.getCloseCause();
                        return ChannelResult.Companion.closed-JP2dKIU(throwable0);
                    }
                    long v = BufferedChannel.receivers$FU.getAndIncrement(bufferedChannel0);
                    long v1 = v / ((long)BufferedChannelKt.SEGMENT_SIZE);
                    int v2 = (int)(v % ((long)BufferedChannelKt.SEGMENT_SIZE));
                    if(channelSegment0.id == v1) {
                        channelSegment2 = channelSegment0;
                    }
                    else {
                        ChannelSegment channelSegment1 = bufferedChannel0.findSegmentReceive(v1, channelSegment0);
                        if(channelSegment1 == null) {
                            continue;
                        }
                        channelSegment2 = channelSegment1;
                    }
                    Object object2 = bufferedChannel0.updateCellReceive(channelSegment2, v2, v, null);
                    if(object2 == BufferedChannelKt.SUSPEND) {
                        break;
                    }
                    if(object2 == BufferedChannelKt.FAILED) {
                        if(v < bufferedChannel0.getSendersCounter$kotlinx_coroutines_core()) {
                            channelSegment2.cleanPrev();
                        }
                        channelSegment0 = channelSegment2;
                        continue;
                    }
                    if(object2 == BufferedChannelKt.SUSPEND_NO_WAITER) {
                        bufferedChannel$receiveCatching$10.label = 1;
                        Object object3 = bufferedChannel0.receiveCatchingOnNoWaiterSuspend-GKJJFZk(channelSegment2, v2, v, bufferedChannel$receiveCatching$10);
                        return object3 == object1 ? object1 : object3;
                    }
                    channelSegment2.cleanPrev();
                    return ChannelResult.Companion.success-JP2dKIU(object2);
                }
                throw new IllegalStateException("unexpected");
            }
            case 1: {
                ResultKt.throwOnFailure(object0);
                return ((ChannelResult)object0).unbox-impl();
            }
            default: {
                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
            }
        }
    }

    private final Object receiveCatchingOnNoWaiterSuspend-GKJJFZk(ChannelSegment channelSegment0, int v, long v1, Continuation continuation0) {
        ChannelResult channelResult0;
        ChannelSegment channelSegment2;
        kotlinx.coroutines.channels.BufferedChannel.receiveCatchingOnNoWaiterSuspend.1 bufferedChannel$receiveCatchingOnNoWaiterSuspend$10;
        if(continuation0 instanceof kotlinx.coroutines.channels.BufferedChannel.receiveCatchingOnNoWaiterSuspend.1) {
            bufferedChannel$receiveCatchingOnNoWaiterSuspend$10 = (kotlinx.coroutines.channels.BufferedChannel.receiveCatchingOnNoWaiterSuspend.1)continuation0;
            if((bufferedChannel$receiveCatchingOnNoWaiterSuspend$10.label & 0x80000000) == 0) {
                bufferedChannel$receiveCatchingOnNoWaiterSuspend$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
                    int I$0;
                    long J$0;
                    Object L$0;
                    Object L$1;
                    int label;
                    Object result;

                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object object0) {
                        this.result = object0;
                        this.label |= 0x80000000;
                        Object object1 = continuation0.receiveCatchingOnNoWaiterSuspend-GKJJFZk(null, 0, 0L, this);
                        return object1 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? object1 : ChannelResult.box-impl(object1);
                    }
                };
            }
            else {
                bufferedChannel$receiveCatchingOnNoWaiterSuspend$10.label ^= 0x80000000;
            }
        }
        else {
            bufferedChannel$receiveCatchingOnNoWaiterSuspend$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
                int I$0;
                long J$0;
                Object L$0;
                Object L$1;
                int label;
                Object result;

                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object object0) {
                    this.result = object0;
                    this.label |= 0x80000000;
                    Object object1 = continuation0.receiveCatchingOnNoWaiterSuspend-GKJJFZk(null, 0, 0L, this);
                    return object1 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? object1 : ChannelResult.box-impl(object1);
                }
            };
        }
        Object object0 = bufferedChannel$receiveCatchingOnNoWaiterSuspend$10.result;
        Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch(bufferedChannel$receiveCatchingOnNoWaiterSuspend$10.label) {
            case 0: {
                ResultKt.throwOnFailure(object0);
                bufferedChannel$receiveCatchingOnNoWaiterSuspend$10.L$0 = this;
                bufferedChannel$receiveCatchingOnNoWaiterSuspend$10.L$1 = channelSegment0;
                bufferedChannel$receiveCatchingOnNoWaiterSuspend$10.I$0 = v;
                bufferedChannel$receiveCatchingOnNoWaiterSuspend$10.J$0 = v1;
                bufferedChannel$receiveCatchingOnNoWaiterSuspend$10.label = 1;
                CancellableContinuationImpl cancellableContinuationImpl0 = CancellableContinuationKt.getOrCreateCancellableContinuation(IntrinsicsKt.intercepted(bufferedChannel$receiveCatchingOnNoWaiterSuspend$10));
                try {
                    Intrinsics.checkNotNull(cancellableContinuationImpl0, "null cannot be cast to non-null type kotlinx.coroutines.CancellableContinuationImpl<kotlinx.coroutines.channels.ChannelResult<E of kotlinx.coroutines.channels.BufferedChannel.receiveCatchingOnNoWaiterSuspend_GKJJFZk$lambda$35>>");
                    ReceiveCatching receiveCatching0 = new ReceiveCatching(cancellableContinuationImpl0);
                    Function1 function10 = null;
                    Object object2 = this.updateCellReceive(channelSegment0, v, v1, receiveCatching0);
                    if(object2 == BufferedChannelKt.SUSPEND) {
                        this.prepareReceiverForSuspension(receiveCatching0, channelSegment0, v);
                    }
                    else if(object2 == BufferedChannelKt.FAILED) {
                        if(v1 < this.getSendersCounter$kotlinx_coroutines_core()) {
                            channelSegment0.cleanPrev();
                        }
                        ChannelSegment channelSegment1 = (ChannelSegment)BufferedChannel.receiveSegment$FU.get(this);
                        while(!this.isClosedForReceive()) {
                            long v2 = BufferedChannel.receivers$FU.getAndIncrement(this);
                            long v3 = v2 / ((long)BufferedChannelKt.SEGMENT_SIZE);
                            int v4 = (int)(v2 % ((long)BufferedChannelKt.SEGMENT_SIZE));
                            if(channelSegment1.id == v3) {
                                channelSegment2 = channelSegment1;
                            }
                            else {
                                ChannelSegment channelSegment3 = this.findSegmentReceive(v3, channelSegment1);
                                if(channelSegment3 == null) {
                                    continue;
                                }
                                channelSegment2 = channelSegment3;
                            }
                            Object object3 = this.updateCellReceive(channelSegment2, v4, v2, receiveCatching0);
                            if(object3 == BufferedChannelKt.SUSPEND) {
                                goto label_55;
                            }
                            if(object3 == BufferedChannelKt.FAILED) {
                                if(v2 < this.getSendersCounter$kotlinx_coroutines_core()) {
                                    channelSegment2.cleanPrev();
                                }
                                channelSegment1 = channelSegment2;
                                continue;
                            }
                            if(object3 == BufferedChannelKt.SUSPEND_NO_WAITER) {
                                throw new IllegalStateException("unexpected");
                            }
                            channelSegment2.cleanPrev();
                            channelResult0 = ChannelResult.box-impl(ChannelResult.Companion.success-JP2dKIU(object3));
                            Function1 function11 = this.onUndeliveredElement;
                            if(function11 == null) {
                                cancellableContinuationImpl0.resume(channelResult0, null);
                                goto label_74;
                            label_55:
                                if(receiveCatching0 instanceof Waiter) {
                                    function10 = receiveCatching0;
                                }
                                if(function10 != null) {
                                    this.prepareReceiverForSuspension(((Waiter)function10), channelSegment2, v4);
                                }
                            }
                            else {
                                cancellableContinuationImpl0.resume(channelResult0, OnUndeliveredElementKt.bindCancellationFun(function11, object3, cancellableContinuationImpl0.getContext()));
                            }
                            goto label_74;
                        }
                        this.onClosedReceiveCatchingOnNoWaiterSuspend(cancellableContinuationImpl0);
                    }
                    else {
                        channelSegment0.cleanPrev();
                        channelResult0 = ChannelResult.box-impl(ChannelResult.Companion.success-JP2dKIU(object2));
                        Function1 function12 = this.onUndeliveredElement;
                        if(function12 != null) {
                            function10 = OnUndeliveredElementKt.bindCancellationFun(function12, object2, cancellableContinuationImpl0.getContext());
                        }
                        cancellableContinuationImpl0.resume(channelResult0, function10);
                    }
                }
                catch(Throwable throwable0) {
                    cancellableContinuationImpl0.releaseClaimedReusableContinuation$kotlinx_coroutines_core();
                    throw throwable0;
                }
            label_74:
                object0 = cancellableContinuationImpl0.getResult();
                if(object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                    DebugProbesKt.probeCoroutineSuspended(bufferedChannel$receiveCatchingOnNoWaiterSuspend$10);
                }
                return object0 == object1 ? object1 : ((ChannelResult)object0).unbox-impl();
            }
            case 1: {
                ChannelSegment channelSegment4 = (ChannelSegment)bufferedChannel$receiveCatchingOnNoWaiterSuspend$10.L$1;
                BufferedChannel bufferedChannel0 = (BufferedChannel)bufferedChannel$receiveCatchingOnNoWaiterSuspend$10.L$0;
                ResultKt.throwOnFailure(object0);
                return ((ChannelResult)object0).unbox-impl();
            }
            default: {
                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
            }
        }
    }

    private final Object receiveImpl(Object object0, Function1 function10, Function3 function30, Function0 function00, Function3 function31) {
        Object object1;
        ChannelSegment channelSegment2;
        int v2;
        long v;
        ChannelSegment channelSegment0 = (ChannelSegment)BufferedChannel.receiveSegment$FU.get(this);
        while(true) {
            if(this.isClosedForReceive()) {
                return function00.invoke();
            }
            v = BufferedChannel.receivers$FU.getAndIncrement(this);
            long v1 = v / ((long)BufferedChannelKt.SEGMENT_SIZE);
            v2 = (int)(v % ((long)BufferedChannelKt.SEGMENT_SIZE));
            if(channelSegment0.id == v1) {
                channelSegment2 = channelSegment0;
            }
            else {
                ChannelSegment channelSegment1 = this.findSegmentReceive(v1, channelSegment0);
                if(channelSegment1 == null) {
                    continue;
                }
                channelSegment2 = channelSegment1;
            }
            object1 = this.updateCellReceive(channelSegment2, v2, v, object0);
            channelSegment0 = channelSegment2;
            if(object1 == BufferedChannelKt.SUSPEND) {
                Waiter waiter0 = object0 instanceof Waiter ? ((Waiter)object0) : null;
                if(waiter0 != null) {
                    this.prepareReceiverForSuspension(waiter0, channelSegment0, v2);
                }
                return function30.invoke(channelSegment0, v2, v);
            }
            if(object1 != BufferedChannelKt.FAILED) {
                break;
            }
            if(v < this.getSendersCounter$kotlinx_coroutines_core()) {
                channelSegment0.cleanPrev();
            }
        }
        if(object1 == BufferedChannelKt.SUSPEND_NO_WAITER) {
            return function31.invoke(channelSegment0, v2, v);
        }
        channelSegment0.cleanPrev();
        return function10.invoke(object1);
    }

    static Object receiveImpl$default(BufferedChannel bufferedChannel0, Object object0, Function1 function10, Function3 function30, Function0 function00, Function3 function31, int v, Object object1) {
        Object object2;
        ChannelSegment channelSegment2;
        int v3;
        long v1;
        if(object1 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: receiveImpl");
        }
        if((v & 16) != 0) {
            function31 = kotlinx.coroutines.channels.BufferedChannel.receiveImpl.1.INSTANCE;
        }
        ChannelSegment channelSegment0 = (ChannelSegment)BufferedChannel.receiveSegment$FU.get(bufferedChannel0);
        while(true) {
            if(bufferedChannel0.isClosedForReceive()) {
                return function00.invoke();
            }
            v1 = BufferedChannel.receivers$FU.getAndIncrement(bufferedChannel0);
            long v2 = v1 / ((long)BufferedChannelKt.SEGMENT_SIZE);
            v3 = (int)(v1 % ((long)BufferedChannelKt.SEGMENT_SIZE));
            if(channelSegment0.id == v2) {
                channelSegment2 = channelSegment0;
            }
            else {
                ChannelSegment channelSegment1 = bufferedChannel0.findSegmentReceive(v2, channelSegment0);
                if(channelSegment1 == null) {
                    continue;
                }
                channelSegment2 = channelSegment1;
            }
            object2 = bufferedChannel0.updateCellReceive(channelSegment2, v3, v1, object0);
            channelSegment0 = channelSegment2;
            if(object2 == BufferedChannelKt.SUSPEND) {
                Waiter waiter0 = object0 instanceof Waiter ? ((Waiter)object0) : null;
                if(waiter0 != null) {
                    bufferedChannel0.prepareReceiverForSuspension(waiter0, channelSegment0, v3);
                }
                return function30.invoke(channelSegment0, v3, v1);
            }
            if(object2 != BufferedChannelKt.FAILED) {
                break;
            }
            if(v1 < bufferedChannel0.getSendersCounter$kotlinx_coroutines_core()) {
                channelSegment0.cleanPrev();
            }
        }
        if(object2 == BufferedChannelKt.SUSPEND_NO_WAITER) {
            return function31.invoke(channelSegment0, v3, v1);
        }
        channelSegment0.cleanPrev();
        return function10.invoke(object2);

        @Metadata(d1 = {"\u0000\u001C\n\u0000\n\u0002\u0010\u0001\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\u0010\u0000\u001A\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032\f\u0010\u0004\u001A\b\u0012\u0004\u0012\u0002H\u00030\u00052\u0006\u0010\u0006\u001A\u00020\u00072\u0006\u0010\b\u001A\u00020\tH\n¢\u0006\u0002\b\n"}, d2 = {"<anonymous>", "", "R", "E", "<anonymous parameter 0>", "Lkotlinx/coroutines/channels/ChannelSegment;", "<anonymous parameter 1>", "", "<anonymous parameter 2>", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 0x30)
        public final class kotlinx.coroutines.channels.BufferedChannel.receiveImpl.1 extends Lambda implements Function3 {
            public static final kotlinx.coroutines.channels.BufferedChannel.receiveImpl.1 INSTANCE;

            static {
                kotlinx.coroutines.channels.BufferedChannel.receiveImpl.1.INSTANCE = new kotlinx.coroutines.channels.BufferedChannel.receiveImpl.1();
            }

            public kotlinx.coroutines.channels.BufferedChannel.receiveImpl.1() {
                super(3);
            }

            @Override  // kotlin.jvm.functions.Function3
            public Object invoke(Object object0, Object object1, Object object2) {
                return this.invoke(((ChannelSegment)object0), ((Number)object1).intValue(), ((Number)object2).longValue());
            }

            // 去混淆评级： 低(20)
            public final Void invoke(ChannelSegment channelSegment0, int v, long v1) {
                throw new IllegalStateException("unexpected");
            }
        }

    }

    private final void receiveImplOnNoWaiter(ChannelSegment channelSegment0, int v, long v1, Waiter waiter0, Function1 function10, Function0 function00) {
        Object object1;
        ChannelSegment channelSegment3;
        Object object0 = this.updateCellReceive(channelSegment0, v, v1, waiter0);
        if(object0 == BufferedChannelKt.SUSPEND) {
            this.prepareReceiverForSuspension(waiter0, channelSegment0, v);
            return;
        }
        if(object0 == BufferedChannelKt.FAILED) {
            if(v1 < this.getSendersCounter$kotlinx_coroutines_core()) {
                channelSegment0.cleanPrev();
            }
            ChannelSegment channelSegment1 = (ChannelSegment)BufferedChannel.receiveSegment$FU.get(this);
            while(true) {
                if(this.isClosedForReceive()) {
                    function00.invoke();
                    return;
                }
                long v2 = BufferedChannel.receivers$FU.getAndIncrement(this);
                long v3 = v2 / ((long)BufferedChannelKt.SEGMENT_SIZE);
                int v4 = (int)(v2 % ((long)BufferedChannelKt.SEGMENT_SIZE));
                if(channelSegment1.id == v3) {
                    channelSegment3 = channelSegment1;
                }
                else {
                    ChannelSegment channelSegment2 = this.findSegmentReceive(v3, channelSegment1);
                    if(channelSegment2 == null) {
                        continue;
                    }
                    channelSegment3 = channelSegment2;
                }
                object1 = this.updateCellReceive(channelSegment3, v4, v2, waiter0);
                channelSegment1 = channelSegment3;
                if(object1 == BufferedChannelKt.SUSPEND) {
                    Waiter waiter1 = waiter0 instanceof Waiter ? waiter0 : null;
                    if(waiter1 != null) {
                        this.prepareReceiverForSuspension(waiter1, channelSegment1, v4);
                    }
                    return;
                }
                if(object1 != BufferedChannelKt.FAILED) {
                    break;
                }
                if(v2 < this.getSendersCounter$kotlinx_coroutines_core()) {
                    channelSegment1.cleanPrev();
                }
            }
            if(object1 == BufferedChannelKt.SUSPEND_NO_WAITER) {
                throw new IllegalStateException("unexpected");
            }
            channelSegment1.cleanPrev();
            function10.invoke(object1);
            return;
        }
        channelSegment0.cleanPrev();
        function10.invoke(object0);
    }

    private final Object receiveOnNoWaiterSuspend(ChannelSegment channelSegment0, int v, long v1, Continuation continuation0) {
        ChannelSegment channelSegment2;
        CancellableContinuationImpl cancellableContinuationImpl0 = CancellableContinuationKt.getOrCreateCancellableContinuation(IntrinsicsKt.intercepted(continuation0));
        try {
            Function1 function10 = null;
            Object object0 = this.updateCellReceive(channelSegment0, v, v1, cancellableContinuationImpl0);
            if(object0 == BufferedChannelKt.SUSPEND) {
                this.prepareReceiverForSuspension(cancellableContinuationImpl0, channelSegment0, v);
            }
            else if(object0 == BufferedChannelKt.FAILED) {
                if(v1 < this.getSendersCounter$kotlinx_coroutines_core()) {
                    channelSegment0.cleanPrev();
                }
                ChannelSegment channelSegment1 = (ChannelSegment)BufferedChannel.receiveSegment$FU.get(this);
                while(!this.isClosedForReceive()) {
                    long v2 = BufferedChannel.receivers$FU.getAndIncrement(this);
                    long v3 = v2 / ((long)BufferedChannelKt.SEGMENT_SIZE);
                    int v4 = (int)(v2 % ((long)BufferedChannelKt.SEGMENT_SIZE));
                    if(channelSegment1.id == v3) {
                        channelSegment2 = channelSegment1;
                    }
                    else {
                        ChannelSegment channelSegment3 = this.findSegmentReceive(v3, channelSegment1);
                        if(channelSegment3 == null) {
                            continue;
                        }
                        channelSegment2 = channelSegment3;
                    }
                    object0 = this.updateCellReceive(channelSegment2, v4, v2, cancellableContinuationImpl0);
                    if(object0 == BufferedChannelKt.SUSPEND) {
                        goto label_34;
                    }
                    if(object0 == BufferedChannelKt.FAILED) {
                        if(v2 < this.getSendersCounter$kotlinx_coroutines_core()) {
                            channelSegment2.cleanPrev();
                        }
                        channelSegment1 = channelSegment2;
                        continue;
                    }
                    if(object0 == BufferedChannelKt.SUSPEND_NO_WAITER) {
                        throw new IllegalStateException("unexpected");
                    }
                    channelSegment2.cleanPrev();
                    Function1 function11 = this.onUndeliveredElement;
                    if(function11 == null) {
                        cancellableContinuationImpl0.resume(object0, null);
                        goto label_52;
                    label_34:
                        if(cancellableContinuationImpl0 instanceof Waiter) {
                            function10 = cancellableContinuationImpl0;
                        }
                        if(function10 != null) {
                            this.prepareReceiverForSuspension(((Waiter)function10), channelSegment2, v4);
                        }
                    }
                    else {
                        cancellableContinuationImpl0.resume(object0, OnUndeliveredElementKt.bindCancellationFun(function11, object0, cancellableContinuationImpl0.getContext()));
                    }
                    goto label_52;
                }
                this.onClosedReceiveOnNoWaiterSuspend(cancellableContinuationImpl0);
            }
            else {
                channelSegment0.cleanPrev();
                Function1 function12 = this.onUndeliveredElement;
                if(function12 != null) {
                    function10 = OnUndeliveredElementKt.bindCancellationFun(function12, object0, cancellableContinuationImpl0.getContext());
                }
                cancellableContinuationImpl0.resume(object0, function10);
            }
        }
        catch(Throwable throwable0) {
            cancellableContinuationImpl0.releaseClaimedReusableContinuation$kotlinx_coroutines_core();
            throw throwable0;
        }
    label_52:
        Object object1 = cancellableContinuationImpl0.getResult();
        if(object1 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation0);
        }
        return object1;
    }

    @Override  // kotlinx.coroutines.channels.ReceiveChannel
    @Deprecated(level = DeprecationLevel.ERROR, message = "Deprecated in favor of \'receiveCatching\'. Please note that the provided replacement does not rethrow channel\'s close cause as \'receiveOrNull\' did, for the detailed replacement please refer to the \'receiveOrNull\' documentation", replaceWith = @ReplaceWith(expression = "receiveCatching().getOrNull()", imports = {}))
    public Object receiveOrNull(Continuation continuation0) {
        return kotlinx.coroutines.channels.Channel.DefaultImpls.receiveOrNull(this, continuation0);
    }

    private final void registerSelectForReceive(SelectInstance selectInstance0, Object object0) {
        Object object1;
        ChannelSegment channelSegment2;
        ChannelSegment channelSegment0 = (ChannelSegment)BufferedChannel.receiveSegment$FU.get(this);
        while(true) {
            if(this.isClosedForReceive()) {
                this.onClosedSelectOnReceive(selectInstance0);
                return;
            }
            long v = BufferedChannel.receivers$FU.getAndIncrement(this);
            long v1 = v / ((long)BufferedChannelKt.SEGMENT_SIZE);
            int v2 = (int)(v % ((long)BufferedChannelKt.SEGMENT_SIZE));
            if(channelSegment0.id == v1) {
                channelSegment2 = channelSegment0;
            }
            else {
                ChannelSegment channelSegment1 = this.findSegmentReceive(v1, channelSegment0);
                if(channelSegment1 == null) {
                    continue;
                }
                channelSegment2 = channelSegment1;
            }
            object1 = this.updateCellReceive(channelSegment2, v2, v, selectInstance0);
            channelSegment0 = channelSegment2;
            if(object1 == BufferedChannelKt.SUSPEND) {
                Waiter waiter0 = selectInstance0 instanceof Waiter ? ((Waiter)selectInstance0) : null;
                if(waiter0 != null) {
                    this.prepareReceiverForSuspension(waiter0, channelSegment0, v2);
                }
                return;
            }
            if(object1 != BufferedChannelKt.FAILED) {
                break;
            }
            if(v < this.getSendersCounter$kotlinx_coroutines_core()) {
                channelSegment0.cleanPrev();
            }
        }
        if(object1 == BufferedChannelKt.SUSPEND_NO_WAITER) {
            throw new IllegalStateException("unexpected");
        }
        channelSegment0.cleanPrev();
        selectInstance0.selectInRegistrationPhase(object1);
    }

    protected void registerSelectForSend(SelectInstance selectInstance0, Object object0) {
        ChannelSegment channelSegment2;
        ChannelSegment channelSegment0 = (ChannelSegment)BufferedChannel.sendSegment$FU.get(this);
        while(true) {
            long v = BufferedChannel.sendersAndCloseStatus$FU.getAndIncrement(this);
            long v1 = v & 0xFFFFFFFFFFFFFFFL;
            boolean z = this.isClosedForSend0(v);
            long v2 = v1 / ((long)BufferedChannelKt.SEGMENT_SIZE);
            int v3 = (int)(v1 % ((long)BufferedChannelKt.SEGMENT_SIZE));
            if(channelSegment0.id == v2) {
            label_14:
                channelSegment2 = channelSegment0;
            }
            else {
                ChannelSegment channelSegment1 = this.findSegmentSend(v2, channelSegment0);
                if(channelSegment1 == null) {
                    if(!z) {
                        continue;
                    }
                    this.onClosedSelectOnSend(object0, selectInstance0);
                    return;
                }
                else {
                    channelSegment2 = channelSegment1;
                    goto label_15;
                }
                goto label_14;
            }
        label_15:
            channelSegment0 = channelSegment2;
            switch(this.updateCellSend(channelSegment2, v3, object0, v1, selectInstance0, z)) {
                case 0: {
                    channelSegment0.cleanPrev();
                    selectInstance0.selectInRegistrationPhase(Unit.INSTANCE);
                    return;
                }
                case 1: {
                    selectInstance0.selectInRegistrationPhase(Unit.INSTANCE);
                    return;
                }
                case 2: {
                    if(z) {
                        channelSegment0.onSlotCleaned();
                        this.onClosedSelectOnSend(object0, selectInstance0);
                        return;
                    }
                    Waiter waiter0 = selectInstance0 instanceof Waiter ? ((Waiter)selectInstance0) : null;
                    if(waiter0 != null) {
                        this.prepareSenderForSuspension(waiter0, channelSegment0, v3);
                    }
                    return;
                }
                case 3: {
                    throw new IllegalStateException("unexpected");
                }
                case 4: {
                    if(v1 < this.getReceiversCounter$kotlinx_coroutines_core()) {
                        channelSegment0.cleanPrev();
                    }
                    this.onClosedSelectOnSend(object0, selectInstance0);
                    return;
                }
                case 5: {
                    channelSegment0.cleanPrev();
                }
            }
        }
    }

    private final void removeUnprocessedElements(ChannelSegment channelSegment0) {
        Waiter waiter0;
        Object object1;
        Function1 function10 = this.onUndeliveredElement;
        Throwable throwable0 = null;
        Object object0 = InlineList.constructor-impl$default(null, 1, null);
    alab1:
        do {
            int v = BufferedChannelKt.SEGMENT_SIZE - 1;
            while(-1 < v) {
                long v1 = channelSegment0.id * ((long)BufferedChannelKt.SEGMENT_SIZE) + ((long)v);
            alab2:
                do {
                    do {
                        do {
                            do {
                                object1 = channelSegment0.getState$kotlinx_coroutines_core(v);
                                if(object1 == BufferedChannelKt.DONE_RCV) {
                                    break alab1;
                                }
                                if(object1 != BufferedChannelKt.BUFFERED) {
                                    goto label_16;
                                }
                                if(v1 < this.getReceiversCounter$kotlinx_coroutines_core()) {
                                    break alab1;
                                }
                            }
                            while(!channelSegment0.casState$kotlinx_coroutines_core(v, object1, BufferedChannelKt.getCHANNEL_CLOSED()));
                            if(function10 != null) {
                                throwable0 = OnUndeliveredElementKt.callUndeliveredElementCatchingException(function10, channelSegment0.getElement$kotlinx_coroutines_core(v), ((UndeliveredElementException)throwable0));
                            }
                            channelSegment0.cleanElement$kotlinx_coroutines_core(v);
                            channelSegment0.onSlotCleaned();
                            break;
                        label_16:
                            if(object1 == BufferedChannelKt.IN_BUFFER || object1 == null) {
                                continue alab2;
                            }
                            if(object1 instanceof Waiter || object1 instanceof WaiterEB) {
                                goto label_21;
                            }
                            if(object1 == BufferedChannelKt.RESUMING_BY_EB || object1 == BufferedChannelKt.RESUMING_BY_RCV) {
                                break alab1;
                            }
                        }
                        while(object1 == BufferedChannelKt.RESUMING_BY_EB);
                        goto label_32;
                    label_21:
                        if(v1 < this.getReceiversCounter$kotlinx_coroutines_core()) {
                            break alab1;
                        }
                        waiter0 = object1 instanceof WaiterEB ? ((WaiterEB)object1).waiter : ((Waiter)object1);
                    }
                    while(!channelSegment0.casState$kotlinx_coroutines_core(v, object1, BufferedChannelKt.getCHANNEL_CLOSED()));
                    if(function10 != null) {
                        throwable0 = OnUndeliveredElementKt.callUndeliveredElementCatchingException(function10, channelSegment0.getElement$kotlinx_coroutines_core(v), ((UndeliveredElementException)throwable0));
                    }
                    object0 = InlineList.plus-FjFbRPM(object0, waiter0);
                    channelSegment0.cleanElement$kotlinx_coroutines_core(v);
                    channelSegment0.onSlotCleaned();
                    goto label_32;
                }
                while(!channelSegment0.casState$kotlinx_coroutines_core(v, object1, BufferedChannelKt.getCHANNEL_CLOSED()));
                channelSegment0.onSlotCleaned();
            label_32:
                --v;
            }
            channelSegment0 = (ChannelSegment)channelSegment0.getPrev();
        }
        while(channelSegment0 != null);
        if(object0 != null) {
            if(object0 instanceof ArrayList) {
                Intrinsics.checkNotNull(object0, "null cannot be cast to non-null type java.util.ArrayList<E of kotlinx.coroutines.internal.InlineList>{ kotlin.collections.TypeAliasesKt.ArrayList<E of kotlinx.coroutines.internal.InlineList> }");
                for(int v2 = ((ArrayList)object0).size() - 1; -1 < v2; --v2) {
                    this.resumeSenderOnCancelledChannel(((Waiter)((ArrayList)object0).get(v2)));
                }
            }
            else {
                this.resumeSenderOnCancelledChannel(((Waiter)object0));
            }
        }
        if(throwable0 != null) {
            throw throwable0;
        }
    }

    private final void resumeReceiverOnClosedChannel(Waiter waiter0) {
        this.resumeWaiterOnClosedChannel(waiter0, true);
    }

    private final void resumeSenderOnCancelledChannel(Waiter waiter0) {
        this.resumeWaiterOnClosedChannel(waiter0, false);
    }

    private final void resumeWaiterOnClosedChannel(Waiter waiter0, boolean z) {
        if(waiter0 instanceof SendBroadcast) {
            ((SendBroadcast)waiter0).getCont().resumeWith(Boolean.FALSE);
            return;
        }
        if(waiter0 instanceof CancellableContinuation) {
            ((Continuation)waiter0).resumeWith(Result.constructor-impl(ResultKt.createFailure((z ? this.getReceiveException() : this.getSendException()))));
            return;
        }
        if(waiter0 instanceof ReceiveCatching) {
            Throwable throwable0 = this.getCloseCause();
            Object object0 = Result.constructor-impl(ChannelResult.box-impl(ChannelResult.Companion.closed-JP2dKIU(throwable0)));
            ((ReceiveCatching)waiter0).cont.resumeWith(object0);
            return;
        }
        if(waiter0 instanceof BufferedChannelIterator) {
            ((BufferedChannelIterator)waiter0).tryResumeHasNextOnClosedChannel();
            return;
        }
        if(!(waiter0 instanceof SelectInstance)) {
            throw new IllegalStateException(("Unexpected waiter: " + waiter0).toString());
        }
        ((SelectInstance)waiter0).trySelect(this, BufferedChannelKt.getCHANNEL_CLOSED());
    }

    @Override  // kotlinx.coroutines.channels.SendChannel
    public Object send(Object object0, Continuation continuation0) {
        return BufferedChannel.send$suspendImpl(this, object0, continuation0);
    }

    static Object send$suspendImpl(BufferedChannel bufferedChannel0, Object object0, Continuation continuation0) {
        ChannelSegment channelSegment2;
        int v3;
        long v1;
        ChannelSegment channelSegment0 = (ChannelSegment)BufferedChannel.sendSegment$FU.get(bufferedChannel0);
    alab1:
        while(true) {
            long v = BufferedChannel.sendersAndCloseStatus$FU.getAndIncrement(bufferedChannel0);
            v1 = v & 0xFFFFFFFFFFFFFFFL;
            boolean z = bufferedChannel0.isClosedForSend0(v);
            long v2 = v1 / ((long)BufferedChannelKt.SEGMENT_SIZE);
            v3 = (int)(v1 % ((long)BufferedChannelKt.SEGMENT_SIZE));
            if(channelSegment0.id == v2) {
            label_15:
                channelSegment2 = channelSegment0;
            }
            else {
                ChannelSegment channelSegment1 = bufferedChannel0.findSegmentSend(v2, channelSegment0);
                if(channelSegment1 == null) {
                    if(!z) {
                        continue;
                    }
                    Object object1 = bufferedChannel0.onClosedSend(object0, continuation0);
                    return object1 != IntrinsicsKt.getCOROUTINE_SUSPENDED() ? Unit.INSTANCE : object1;
                }
                else {
                    channelSegment2 = channelSegment1;
                    goto label_16;
                }
                goto label_15;
            }
        label_16:
            switch(bufferedChannel0.updateCellSend(channelSegment2, v3, object0, v1, null, z)) {
                case 0: {
                    channelSegment2.cleanPrev();
                    return Unit.INSTANCE;
                }
                case 1: {
                    return Unit.INSTANCE;
                }
                case 2: {
                    goto label_29;
                }
                case 3: {
                    goto label_26;
                }
                case 4: {
                    break alab1;
                }
                case 5: {
                    channelSegment2.cleanPrev();
                    channelSegment0 = channelSegment2;
                    break;
                }
                default: {
                    channelSegment0 = channelSegment2;
                    break;
                }
            }
        }
        if(v1 < bufferedChannel0.getReceiversCounter$kotlinx_coroutines_core()) {
            channelSegment2.cleanPrev();
        }
        Object object2 = bufferedChannel0.onClosedSend(object0, continuation0);
        if(object2 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            return object2;
        label_26:
            Object object3 = bufferedChannel0.sendOnNoWaiterSuspend(channelSegment2, v3, object0, v1, continuation0);
            if(object3 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                return object3;
            label_29:
                if(z) {
                    channelSegment2.onSlotCleaned();
                    Object object4 = bufferedChannel0.onClosedSend(object0, continuation0);
                    if(object4 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                        return object4;
                    }
                }
            }
        }
        return Unit.INSTANCE;
    }

    public Object sendBroadcast$kotlinx_coroutines_core(Object object0, Continuation continuation0) {
        return BufferedChannel.sendBroadcast$suspendImpl(this, object0, continuation0);
    }

    static Object sendBroadcast$suspendImpl(BufferedChannel bufferedChannel0, Object object0, Continuation continuation0) {
        int v3;
        CancellableContinuationImpl cancellableContinuationImpl0 = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation0), 1);
        cancellableContinuationImpl0.initCancellability();
        if(bufferedChannel0.onUndeliveredElement != null) {
            throw new IllegalStateException("the `onUndeliveredElement` feature is unsupported for `sendBroadcast(e)`");
        }
        SendBroadcast bufferedChannel$SendBroadcast0 = new SendBroadcast(cancellableContinuationImpl0);
        ChannelSegment channelSegment0 = (ChannelSegment)BufferedChannel.sendSegment$FU.get(bufferedChannel0);
    alab1:
        while(true) {
            long v = BufferedChannel.sendersAndCloseStatus$FU.getAndIncrement(bufferedChannel0);
            long v1 = 0xFFFFFFFFFFFFFFFL & v;
            boolean z = bufferedChannel0.isClosedForSend0(v);
            long v2 = v1 / ((long)BufferedChannelKt.SEGMENT_SIZE);
            v3 = (int)(v1 % ((long)BufferedChannelKt.SEGMENT_SIZE));
            if(channelSegment0.id != v2) {
                ChannelSegment channelSegment1 = bufferedChannel0.findSegmentSend(v2, channelSegment0);
                if(channelSegment1 == null) {
                    if(!z) {
                        continue;
                    }
                    cancellableContinuationImpl0.resumeWith(Boxing.boxBoolean(false));
                    goto label_40;
                }
                else {
                    channelSegment0 = channelSegment1;
                }
            }
            switch(bufferedChannel0.updateCellSend(channelSegment0, v3, object0, v1, bufferedChannel$SendBroadcast0, z)) {
                case 0: {
                    goto label_38;
                }
                case 1: {
                    goto label_39;
                }
                case 2: {
                    goto label_27;
                }
                case 3: {
                    throw new IllegalStateException("unexpected");
                }
                case 4: {
                    break alab1;
                }
                case 5: {
                    channelSegment0.cleanPrev();
                }
            }
        }
        if(v1 < bufferedChannel0.getReceiversCounter$kotlinx_coroutines_core()) {
            channelSegment0.cleanPrev();
        }
        cancellableContinuationImpl0.resumeWith(Boxing.boxBoolean(false));
        goto label_40;
    label_27:
        if(z) {
            channelSegment0.onSlotCleaned();
            cancellableContinuationImpl0.resumeWith(Boxing.boxBoolean(false));
        }
        else {
            Waiter waiter0 = bufferedChannel$SendBroadcast0 instanceof Waiter ? bufferedChannel$SendBroadcast0 : null;
            if(waiter0 != null) {
                bufferedChannel0.prepareSenderForSuspension(waiter0, channelSegment0, v3);
                goto label_40;
            label_38:
                channelSegment0.cleanPrev();
            label_39:
                cancellableContinuationImpl0.resumeWith(Boxing.boxBoolean(true));
            }
        }
    label_40:
        Object object1 = cancellableContinuationImpl0.getResult();
        if(object1 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation0);
        }
        return object1;
    }

    protected final Object sendImpl(Object object0, Object object1, Function0 function00, Function2 function20, Function0 function01, Function4 function40) {
        ChannelSegment channelSegment0 = (ChannelSegment)BufferedChannel.sendSegment$FU.get(this);
        while(true) {
            long v = BufferedChannel.sendersAndCloseStatus$FU.getAndIncrement(this);
            long v1 = 0xFFFFFFFFFFFFFFFL & v;
            boolean z = this.isClosedForSend0(v);
            long v2 = v1 / ((long)BufferedChannelKt.SEGMENT_SIZE);
            int v3 = (int)(v1 % ((long)BufferedChannelKt.SEGMENT_SIZE));
            if(channelSegment0.id != v2) {
                ChannelSegment channelSegment1 = this.findSegmentSend(v2, channelSegment0);
                if(channelSegment1 == null) {
                    if(!z) {
                        continue;
                    }
                    return function01.invoke();
                }
                else {
                    channelSegment0 = channelSegment1;
                }
            }
            switch(this.updateCellSend(channelSegment0, v3, object0, v1, object1, z)) {
                case 0: {
                    channelSegment0.cleanPrev();
                    return function00.invoke();
                }
                case 1: {
                    return function00.invoke();
                }
                case 2: {
                    if(z) {
                        channelSegment0.onSlotCleaned();
                        return function01.invoke();
                    }
                    Waiter waiter0 = object1 instanceof Waiter ? ((Waiter)object1) : null;
                    if(waiter0 != null) {
                        this.prepareSenderForSuspension(waiter0, channelSegment0, v3);
                    }
                    return function20.invoke(channelSegment0, v3);
                }
                case 3: {
                    return function40.invoke(channelSegment0, v3, object0, v1);
                }
                case 4: {
                    if(v1 < this.getReceiversCounter$kotlinx_coroutines_core()) {
                        channelSegment0.cleanPrev();
                    }
                    return function01.invoke();
                }
                case 5: {
                    channelSegment0.cleanPrev();
                }
            }
        }
    }

    public static Object sendImpl$default(BufferedChannel bufferedChannel0, Object object0, Object object1, Function0 function00, Function2 function20, Function0 function01, Function4 function40, int v, Object object2) {
        if(object2 == null) {
            Function4 function41 = (v & 0x20) == 0 ? function40 : kotlinx.coroutines.channels.BufferedChannel.sendImpl.1.INSTANCE;
            ChannelSegment channelSegment0 = (ChannelSegment)BufferedChannel.sendSegment$FU.get(bufferedChannel0);
            while(true) {
                long v1 = BufferedChannel.sendersAndCloseStatus$FU.getAndIncrement(bufferedChannel0);
                long v2 = 0xFFFFFFFFFFFFFFFL & v1;
                boolean z = bufferedChannel0.isClosedForSend0(v1);
                long v3 = v2 / ((long)BufferedChannelKt.SEGMENT_SIZE);
                int v4 = (int)(v2 % ((long)BufferedChannelKt.SEGMENT_SIZE));
                if(channelSegment0.id != v3) {
                    ChannelSegment channelSegment1 = bufferedChannel0.findSegmentSend(v3, channelSegment0);
                    if(channelSegment1 == null) {
                        if(!z) {
                            continue;
                        }
                        return function01.invoke();
                    }
                    else {
                        channelSegment0 = channelSegment1;
                    }
                }
                switch(bufferedChannel0.updateCellSend(channelSegment0, v4, object0, v2, object1, z)) {
                    case 0: {
                        channelSegment0.cleanPrev();
                        return function00.invoke();
                    }
                    case 1: {
                        return function00.invoke();
                    }
                    case 2: {
                        if(z) {
                            channelSegment0.onSlotCleaned();
                            return function01.invoke();
                        }
                        Waiter waiter0 = object1 instanceof Waiter ? ((Waiter)object1) : null;
                        if(waiter0 != null) {
                            bufferedChannel0.prepareSenderForSuspension(waiter0, channelSegment0, v4);
                        }
                        return function20.invoke(channelSegment0, v4);
                    }
                    case 3: {
                        return function41.invoke(channelSegment0, v4, object0, v2);
                    }
                    case 4: {
                        if(v2 < bufferedChannel0.getReceiversCounter$kotlinx_coroutines_core()) {
                            channelSegment0.cleanPrev();
                        }
                        return function01.invoke();
                    }
                    case 5: {
                        channelSegment0.cleanPrev();
                    }
                }
            }
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: sendImpl");

        @Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0010\u0001\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\u0010\u0000\u001A\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032\f\u0010\u0004\u001A\b\u0012\u0004\u0012\u0002H\u00030\u00052\u0006\u0010\u0006\u001A\u00020\u00072\u0006\u0010\b\u001A\u0002H\u00032\u0006\u0010\t\u001A\u00020\nH\n¢\u0006\u0004\b\u000B\u0010\f"}, d2 = {"<anonymous>", "", "R", "E", "<anonymous parameter 0>", "Lkotlinx/coroutines/channels/ChannelSegment;", "<anonymous parameter 1>", "", "<anonymous parameter 2>", "<anonymous parameter 3>", "", "invoke", "(Lkotlinx/coroutines/channels/ChannelSegment;ILjava/lang/Object;J)Ljava/lang/Void;"}, k = 3, mv = {1, 8, 0}, xi = 0xB0)
        public final class kotlinx.coroutines.channels.BufferedChannel.sendImpl.1 extends Lambda implements Function4 {
            public static final kotlinx.coroutines.channels.BufferedChannel.sendImpl.1 INSTANCE;

            static {
                kotlinx.coroutines.channels.BufferedChannel.sendImpl.1.INSTANCE = new kotlinx.coroutines.channels.BufferedChannel.sendImpl.1();
            }

            public kotlinx.coroutines.channels.BufferedChannel.sendImpl.1() {
                super(4);
            }

            @Override  // kotlin.jvm.functions.Function4
            public Object invoke(Object object0, Object object1, Object object2, Object object3) {
                return this.invoke(((ChannelSegment)object0), ((Number)object1).intValue(), object2, ((Number)object3).longValue());
            }

            // 去混淆评级： 低(20)
            public final Void invoke(ChannelSegment channelSegment0, int v, Object object0, long v1) {
                throw new IllegalStateException("unexpected");
            }
        }

    }

    private final void sendImplOnNoWaiter(ChannelSegment channelSegment0, int v, Object object0, long v1, Waiter waiter0, Function0 function00, Function0 function01) {
        switch(this.updateCellSend(channelSegment0, v, object0, v1, waiter0, false)) {
            case 0: {
                channelSegment0.cleanPrev();
                function00.invoke();
                return;
            }
            case 1: {
                function00.invoke();
                return;
            }
            case 2: {
                this.prepareSenderForSuspension(waiter0, channelSegment0, v);
                return;
            }
            case 4: {
                if(v1 < this.getReceiversCounter$kotlinx_coroutines_core()) {
                    channelSegment0.cleanPrev();
                }
                function01.invoke();
                return;
            }
            case 5: {
                channelSegment0.cleanPrev();
                ChannelSegment channelSegment1 = (ChannelSegment)BufferedChannel.sendSegment$FU.get(this);
                while(true) {
                    long v2 = BufferedChannel.sendersAndCloseStatus$FU.getAndIncrement(this);
                    long v3 = 0xFFFFFFFFFFFFFFFL & v2;
                    boolean z = this.isClosedForSend0(v2);
                    long v4 = v3 / ((long)BufferedChannelKt.SEGMENT_SIZE);
                    int v5 = (int)(v3 % ((long)BufferedChannelKt.SEGMENT_SIZE));
                    if(channelSegment1.id != v4) {
                        ChannelSegment channelSegment2 = this.findSegmentSend(v4, channelSegment1);
                        if(channelSegment2 == null) {
                            if(!z) {
                                continue;
                            }
                            function01.invoke();
                            return;
                        }
                        else {
                            channelSegment1 = channelSegment2;
                        }
                    }
                    switch(this.updateCellSend(channelSegment1, v5, object0, v3, waiter0, z)) {
                        case 0: {
                            channelSegment1.cleanPrev();
                            function00.invoke();
                            return;
                        }
                        case 1: {
                            function00.invoke();
                            return;
                        }
                        case 2: {
                            if(z) {
                                channelSegment1.onSlotCleaned();
                                function01.invoke();
                                return;
                            }
                            Waiter waiter1 = waiter0 instanceof Waiter ? waiter0 : null;
                            if(waiter1 != null) {
                                this.prepareSenderForSuspension(waiter1, channelSegment1, v5);
                            }
                            return;
                        }
                        case 3: {
                            throw new IllegalStateException("unexpected");
                        }
                        case 4: {
                            if(v3 < this.getReceiversCounter$kotlinx_coroutines_core()) {
                                channelSegment1.cleanPrev();
                            }
                            function01.invoke();
                            return;
                        }
                        case 5: {
                            channelSegment1.cleanPrev();
                        }
                    }
                }
            }
            default: {
                throw new IllegalStateException("unexpected");
            }
        }
    }

    private final Object sendOnNoWaiterSuspend(ChannelSegment channelSegment0, int v, Object object0, long v1, Continuation continuation0) {
        Unit unit0;
        ChannelSegment channelSegment3;
        int v5;
        CancellableContinuationImpl cancellableContinuationImpl0 = CancellableContinuationKt.getOrCreateCancellableContinuation(IntrinsicsKt.intercepted(continuation0));
        try {
            switch(this.updateCellSend(channelSegment0, v, object0, v1, cancellableContinuationImpl0, false)) {
                case 0: {
                    channelSegment0.cleanPrev();
                    unit0 = Unit.INSTANCE;
                    cancellableContinuationImpl0.resumeWith(unit0);
                    break;
                }
                case 1: {
                    unit0 = Unit.INSTANCE;
                    cancellableContinuationImpl0.resumeWith(unit0);
                    break;
                }
                case 2: {
                    this.prepareSenderForSuspension(cancellableContinuationImpl0, channelSegment0, v);
                    break;
                }
                case 4: {
                    if(v1 < this.getReceiversCounter$kotlinx_coroutines_core()) {
                        channelSegment0.cleanPrev();
                    }
                    this.onClosedSendOnNoWaiterSuspend(object0, cancellableContinuationImpl0);
                    break;
                }
                case 5: {
                    channelSegment0.cleanPrev();
                    ChannelSegment channelSegment1 = (ChannelSegment)BufferedChannel.sendSegment$FU.get(this);
                alab1:
                    while(true) {
                        long v2 = BufferedChannel.sendersAndCloseStatus$FU.getAndIncrement(this);
                        long v3 = 0xFFFFFFFFFFFFFFFL & v2;
                        boolean z = this.isClosedForSend0(v2);
                        long v4 = v3 / ((long)BufferedChannelKt.SEGMENT_SIZE);
                        v5 = (int)(v3 % ((long)BufferedChannelKt.SEGMENT_SIZE));
                        if(channelSegment1.id == v4) {
                        label_17:
                            channelSegment3 = channelSegment1;
                        }
                        else {
                            ChannelSegment channelSegment2 = this.findSegmentSend(v4, channelSegment1);
                            if(channelSegment2 == null) {
                                if(!z) {
                                    continue;
                                }
                                goto label_45;
                            }
                            else {
                                channelSegment3 = channelSegment2;
                                goto label_18;
                            }
                            goto label_17;
                        }
                    label_18:
                        int v6 = this.updateCellSend(channelSegment3, v5, object0, v3, cancellableContinuationImpl0, z);
                        switch(v6) {
                            case 0: {
                                break alab1;
                            }
                            case 1: {
                                goto label_29;
                            }
                            case 2: {
                                goto label_31;
                            }
                            case 3: {
                                throw new IllegalStateException("unexpected");
                            label_22:
                                if(v6 == 5) {
                                    channelSegment3.cleanPrev();
                                }
                                channelSegment1 = channelSegment3;
                                break;
                            }
                            case 4: {
                                goto label_41;
                            }
                            default: {
                                goto label_22;
                            }
                        }
                    }
                    channelSegment3.cleanPrev();
                    unit0 = Unit.INSTANCE;
                    cancellableContinuationImpl0.resumeWith(unit0);
                    break;
                label_29:
                    unit0 = Unit.INSTANCE;
                    cancellableContinuationImpl0.resumeWith(unit0);
                    break;
                label_31:
                    if(z) {
                        channelSegment3.onSlotCleaned();
                        goto label_45;
                    label_41:
                        if(v3 < this.getReceiversCounter$kotlinx_coroutines_core()) {
                            channelSegment3.cleanPrev();
                        }
                    label_45:
                        this.onClosedSendOnNoWaiterSuspend(object0, cancellableContinuationImpl0);
                    }
                    else {
                        Waiter waiter0 = cancellableContinuationImpl0 instanceof Waiter ? cancellableContinuationImpl0 : null;
                        if(waiter0 != null) {
                            this.prepareSenderForSuspension(waiter0, channelSegment3, v5);
                        }
                    }
                    break;
                }
                default: {
                    throw new IllegalStateException("unexpected");
                }
            }
        }
        catch(Throwable throwable0) {
            cancellableContinuationImpl0.releaseClaimedReusableContinuation$kotlinx_coroutines_core();
            throw throwable0;
        }
        Object object1 = cancellableContinuationImpl0.getResult();
        if(object1 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation0);
        }
        return object1 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? object1 : Unit.INSTANCE;
    }

    // 去混淆评级： 低(20)
    private final boolean shouldSendSuspend(long v) {
        return this.isClosedForSend0(v) ? false : !this.bufferOrRendezvousSend(v & 0xFFFFFFFFFFFFFFFL);
    }

    public boolean shouldSendSuspend$kotlinx_coroutines_core() {
        return this.shouldSendSuspend(BufferedChannel.sendersAndCloseStatus$FU.get(this));
    }

    @Override
    public String toString() {
        String s;
        StringBuilder stringBuilder0 = new StringBuilder();
        switch(((int)(BufferedChannel.sendersAndCloseStatus$FU.get(this) >> 60))) {
            case 2: {
                stringBuilder0.append("closed,");
                break;
            }
            case 3: {
                stringBuilder0.append("cancelled,");
            }
        }
        stringBuilder0.append("capacity=" + this.capacity + ',');
        stringBuilder0.append("data=[");
        Iterable iterable0 = CollectionsKt.listOf(new ChannelSegment[]{BufferedChannel.receiveSegment$FU.get(this), BufferedChannel.sendSegment$FU.get(this), BufferedChannel.bufferEndSegment$FU.get(this)});
        Collection collection0 = new ArrayList();
        for(Object object0: iterable0) {
            if(((ChannelSegment)object0) != BufferedChannelKt.NULL_SEGMENT) {
                collection0.add(object0);
            }
        }
        Iterator iterator1 = ((List)collection0).iterator();
        if(!iterator1.hasNext()) {
            throw new NoSuchElementException();
        }
        Object object1 = iterator1.next();
        if(iterator1.hasNext()) {
            long v = ((ChannelSegment)object1).id;
            while(true) {
                Object object2 = iterator1.next();
                long v1 = ((ChannelSegment)object2).id;
                if(v > v1) {
                    object1 = object2;
                    v = v1;
                }
                if(!iterator1.hasNext()) {
                    break;
                }
            }
        }
        ChannelSegment channelSegment0 = (ChannelSegment)object1;
        long v2 = this.getReceiversCounter$kotlinx_coroutines_core();
        long v3 = this.getSendersCounter$kotlinx_coroutines_core();
    alab1:
        do {
            int v4 = BufferedChannelKt.SEGMENT_SIZE;
            int v5 = 0;
            while(v5 < v4) {
                long v6 = channelSegment0.id * ((long)BufferedChannelKt.SEGMENT_SIZE) + ((long)v5);
                int v7 = Long.compare(v6, v3);
                if(v7 >= 0 && v6 >= v2) {
                    break alab1;
                }
                Object object3 = channelSegment0.getState$kotlinx_coroutines_core(v5);
                Object object4 = channelSegment0.getElement$kotlinx_coroutines_core(v5);
                if(object3 instanceof CancellableContinuation) {
                    int v8 = Long.compare(v6, v2);
                    if(v8 < 0 && v7 >= 0) {
                        s = "receive";
                    }
                    else if(v7 >= 0 || v8 < 0) {
                        s = "cont";
                    }
                    else {
                        s = "send";
                    }
                    goto label_72;
                }
                else if(object3 instanceof SelectInstance) {
                    int v9 = Long.compare(v6, v2);
                    if(v9 < 0 && v7 >= 0) {
                        s = "onReceive";
                    }
                    else if(v7 >= 0 || v9 < 0) {
                        s = "select";
                    }
                    else {
                        s = "onSend";
                    }
                    goto label_72;
                }
                else if(object3 instanceof ReceiveCatching) {
                    s = "receiveCatching";
                    goto label_72;
                }
                else if(object3 instanceof SendBroadcast) {
                    s = "sendBroadcast";
                    goto label_72;
                }
                else if(object3 instanceof WaiterEB) {
                    s = "EB(" + object3 + ')';
                    goto label_72;
                }
                else if((Intrinsics.areEqual(object3, BufferedChannelKt.RESUMING_BY_RCV) ? true : Intrinsics.areEqual(object3, BufferedChannelKt.RESUMING_BY_EB))) {
                    s = "resuming_sender";
                    goto label_72;
                }
                else if(!((((((object3 == null ? true : Intrinsics.areEqual(object3, BufferedChannelKt.IN_BUFFER)) ? true : Intrinsics.areEqual(object3, BufferedChannelKt.DONE_RCV)) ? true : Intrinsics.areEqual(object3, BufferedChannelKt.POISONED)) ? true : Intrinsics.areEqual(object3, BufferedChannelKt.INTERRUPTED_RCV)) ? true : Intrinsics.areEqual(object3, BufferedChannelKt.INTERRUPTED_SEND)) ? true : Intrinsics.areEqual(object3, BufferedChannelKt.getCHANNEL_CLOSED()))) {
                    s = object3.toString();
                label_72:
                    if(object4 == null) {
                        stringBuilder0.append(s + ',');
                    }
                    else {
                        stringBuilder0.append("(" + s + ',' + object4 + "),");
                    }
                }
                ++v5;
            }
            channelSegment0 = (ChannelSegment)channelSegment0.getNext();
        }
        while(channelSegment0 != null);
        if(StringsKt.last(stringBuilder0) == 44) {
            Intrinsics.checkNotNullExpressionValue(stringBuilder0.deleteCharAt(stringBuilder0.length() - 1), "this.deleteCharAt(index)");
        }
        stringBuilder0.append("]");
        return stringBuilder0.toString();
    }

    public final String toStringDebug$kotlinx_coroutines_core() {
        String s1;
        StringBuilder stringBuilder0 = new StringBuilder();
        stringBuilder0.append("S=" + this.getSendersCounter$kotlinx_coroutines_core() + ",R=" + this.getReceiversCounter$kotlinx_coroutines_core() + ",B=" + this.getBufferEndCounter() + ",B\'=" + BufferedChannel.completedExpandBuffersAndPauseFlag$FU.get(this) + ",C=" + ((int)(BufferedChannel.sendersAndCloseStatus$FU.get(this) >> 60)) + ',');
        switch(((int)(BufferedChannel.sendersAndCloseStatus$FU.get(this) >> 60))) {
            case 1: {
                stringBuilder0.append("CANCELLATION_STARTED,");
                break;
            }
            case 2: {
                stringBuilder0.append("CLOSED,");
                break;
            }
            case 3: {
                stringBuilder0.append("CANCELLED,");
            }
        }
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater0 = BufferedChannel.sendSegment$FU;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater1 = BufferedChannel.receiveSegment$FU;
        stringBuilder0.append("SEND_SEGM=" + DebugStringsKt.getHexAddress(atomicReferenceFieldUpdater0.get(this)) + ",RCV_SEGM=" + DebugStringsKt.getHexAddress(atomicReferenceFieldUpdater1.get(this)));
        if(!this.isRendezvousOrUnlimited()) {
            stringBuilder0.append(",EB_SEGM=" + DebugStringsKt.getHexAddress(BufferedChannel.bufferEndSegment$FU.get(this)));
        }
        stringBuilder0.append("  ");
        Iterable iterable0 = CollectionsKt.listOf(new ChannelSegment[]{atomicReferenceFieldUpdater1.get(this), atomicReferenceFieldUpdater0.get(this), BufferedChannel.bufferEndSegment$FU.get(this)});
        Collection collection0 = new ArrayList();
        for(Object object0: iterable0) {
            if(((ChannelSegment)object0) != BufferedChannelKt.NULL_SEGMENT) {
                collection0.add(object0);
            }
        }
        Iterator iterator1 = ((List)collection0).iterator();
        if(!iterator1.hasNext()) {
            throw new NoSuchElementException();
        }
        Object object1 = iterator1.next();
        if(iterator1.hasNext()) {
            long v = ((ChannelSegment)object1).id;
            while(true) {
                Object object2 = iterator1.next();
                long v1 = ((ChannelSegment)object2).id;
                if(v > v1) {
                    object1 = object2;
                    v = v1;
                }
                if(!iterator1.hasNext()) {
                    break;
                }
            }
        }
        ChannelSegment channelSegment0 = (ChannelSegment)object1;
        do {
            ChannelSegment channelSegment1 = (ChannelSegment)channelSegment0.getPrev();
            String s = null;
            stringBuilder0.append(DebugStringsKt.getHexAddress(channelSegment0) + "=[" + (channelSegment0.isRemoved() ? "*" : "") + channelSegment0.id + ",prev=" + (channelSegment1 == null ? null : DebugStringsKt.getHexAddress(channelSegment1)) + ',');
            int v2 = BufferedChannelKt.SEGMENT_SIZE;
            for(int v3 = 0; v3 < v2; ++v3) {
                Object object3 = channelSegment0.getState$kotlinx_coroutines_core(v3);
                Object object4 = channelSegment0.getElement$kotlinx_coroutines_core(v3);
                if(object3 instanceof CancellableContinuation) {
                    s1 = "cont";
                }
                else if(object3 instanceof SelectInstance) {
                    s1 = "select";
                }
                else if(object3 instanceof ReceiveCatching) {
                    s1 = "receiveCatching";
                }
                else if(object3 instanceof SendBroadcast) {
                    s1 = "send(broadcast)";
                }
                else {
                    s1 = object3 instanceof WaiterEB ? "EB(" + object3 + ')' : String.valueOf(object3);
                }
                stringBuilder0.append("[" + v3 + "]=(" + s1 + ',' + object4 + "),");
            }
            StringBuilder stringBuilder1 = new StringBuilder("next=");
            ChannelSegment channelSegment2 = (ChannelSegment)channelSegment0.getNext();
            if(channelSegment2 != null) {
                s = DebugStringsKt.getHexAddress(channelSegment2);
            }
            stringBuilder1.append(s);
            stringBuilder1.append("]  ");
            stringBuilder0.append(stringBuilder1.toString());
            channelSegment0 = (ChannelSegment)channelSegment0.getNext();
        }
        while(channelSegment0 != null);
        return stringBuilder0.toString();
    }

    @Override  // kotlinx.coroutines.channels.ReceiveChannel
    public Object tryReceive-PtdJZtk() {
        Object object0;
        ChannelSegment channelSegment2;
        long v = BufferedChannel.receivers$FU.get(this);
        long v1 = BufferedChannel.sendersAndCloseStatus$FU.get(this);
        if(this.isClosedForReceive0(v1)) {
            Throwable throwable0 = this.getCloseCause();
            return ChannelResult.Companion.closed-JP2dKIU(throwable0);
        }
        if(v >= (v1 & 0xFFFFFFFFFFFFFFFL)) {
            return ChannelResult.Companion.failure-PtdJZtk();
        }
        Symbol symbol0 = BufferedChannelKt.INTERRUPTED_RCV;
        ChannelSegment channelSegment0 = (ChannelSegment)BufferedChannel.receiveSegment$FU.get(this);
        while(true) {
            if(this.isClosedForReceive()) {
                Throwable throwable1 = this.getCloseCause();
                return ChannelResult.Companion.closed-JP2dKIU(throwable1);
            }
            long v2 = BufferedChannel.receivers$FU.getAndIncrement(this);
            long v3 = v2 / ((long)BufferedChannelKt.SEGMENT_SIZE);
            int v4 = (int)(v2 % ((long)BufferedChannelKt.SEGMENT_SIZE));
            if(channelSegment0.id == v3) {
                channelSegment2 = channelSegment0;
            }
            else {
                ChannelSegment channelSegment1 = this.findSegmentReceive(v3, channelSegment0);
                if(channelSegment1 == null) {
                    continue;
                }
                channelSegment2 = channelSegment1;
            }
            object0 = this.updateCellReceive(channelSegment2, v4, v2, symbol0);
            if(object0 == BufferedChannelKt.SUSPEND) {
                Waiter waiter0 = symbol0 instanceof Waiter ? ((Waiter)symbol0) : null;
                if(waiter0 != null) {
                    this.prepareReceiverForSuspension(waiter0, channelSegment2, v4);
                }
                this.waitExpandBufferCompletion$kotlinx_coroutines_core(v2);
                channelSegment2.onSlotCleaned();
                return ChannelResult.Companion.failure-PtdJZtk();
            }
            if(object0 != BufferedChannelKt.FAILED) {
                break;
            }
            if(v2 < this.getSendersCounter$kotlinx_coroutines_core()) {
                channelSegment2.cleanPrev();
            }
            channelSegment0 = channelSegment2;
        }
        if(object0 == BufferedChannelKt.SUSPEND_NO_WAITER) {
            throw new IllegalStateException("unexpected");
        }
        channelSegment2.cleanPrev();
        return ChannelResult.Companion.success-JP2dKIU(object0);
    }

    private final boolean tryResumeReceiver(Object object0, Object object1) {
        Function1 function10 = null;
        if(object0 instanceof SelectInstance) {
            return ((SelectInstance)object0).trySelect(this, object1);
        }
        if(object0 instanceof ReceiveCatching) {
            Intrinsics.checkNotNull(object0, "null cannot be cast to non-null type kotlinx.coroutines.channels.ReceiveCatching<E of kotlinx.coroutines.channels.BufferedChannel>");
            CancellableContinuation cancellableContinuation0 = ((ReceiveCatching)object0).cont;
            ChannelResult channelResult0 = ChannelResult.box-impl(ChannelResult.Companion.success-JP2dKIU(object1));
            Function1 function11 = this.onUndeliveredElement;
            if(function11 != null) {
                function10 = OnUndeliveredElementKt.bindCancellationFun(function11, object1, ((ReceiveCatching)object0).cont.getContext());
            }
            return BufferedChannelKt.tryResume0(cancellableContinuation0, channelResult0, function10);
        }
        if(object0 instanceof BufferedChannelIterator) {
            Intrinsics.checkNotNull(object0, "null cannot be cast to non-null type kotlinx.coroutines.channels.BufferedChannel.BufferedChannelIterator<E of kotlinx.coroutines.channels.BufferedChannel>");
            return ((BufferedChannelIterator)object0).tryResumeHasNext(object1);
        }
        if(!(object0 instanceof CancellableContinuation)) {
            throw new IllegalStateException(("Unexpected receiver type: " + object0).toString());
        }
        Intrinsics.checkNotNull(object0, "null cannot be cast to non-null type kotlinx.coroutines.CancellableContinuation<E of kotlinx.coroutines.channels.BufferedChannel>");
        Function1 function12 = this.onUndeliveredElement;
        if(function12 != null) {
            function10 = OnUndeliveredElementKt.bindCancellationFun(function12, object1, ((CancellableContinuation)object0).getContext());
        }
        return BufferedChannelKt.tryResume0(((CancellableContinuation)object0), object1, function10);
    }

    private final boolean tryResumeSender(Object object0, ChannelSegment channelSegment0, int v) {
        if(object0 instanceof CancellableContinuation) {
            Intrinsics.checkNotNull(object0, "null cannot be cast to non-null type kotlinx.coroutines.CancellableContinuation<kotlin.Unit>");
            return BufferedChannelKt.tryResume0$default(((CancellableContinuation)object0), Unit.INSTANCE, null, 2, null);
        }
        if(object0 instanceof SelectInstance) {
            Intrinsics.checkNotNull(object0, "null cannot be cast to non-null type kotlinx.coroutines.selects.SelectImplementation<*>");
            TrySelectDetailedResult trySelectDetailedResult0 = ((SelectImplementation)object0).trySelectDetailed(this, Unit.INSTANCE);
            if(trySelectDetailedResult0 == TrySelectDetailedResult.REREGISTER) {
                channelSegment0.cleanElement$kotlinx_coroutines_core(v);
            }
            return trySelectDetailedResult0 == TrySelectDetailedResult.SUCCESSFUL;
        }
        if(!(object0 instanceof SendBroadcast)) {
            throw new IllegalStateException(("Unexpected waiter: " + object0).toString());
        }
        return BufferedChannelKt.tryResume0$default(((SendBroadcast)object0).getCont(), Boolean.TRUE, null, 2, null);
    }

    @Override  // kotlinx.coroutines.channels.SendChannel
    public Object trySend-JP2dKIU(Object object0) {
        int v4;
        ChannelSegment channelSegment1;
        if(this.shouldSendSuspend(BufferedChannel.sendersAndCloseStatus$FU.get(this))) {
            return ChannelResult.Companion.failure-PtdJZtk();
        }
        Symbol symbol0 = BufferedChannelKt.INTERRUPTED_SEND;
        ChannelSegment channelSegment0 = (ChannelSegment)BufferedChannel.sendSegment$FU.get(this);
        while(true) {
            long v = BufferedChannel.sendersAndCloseStatus$FU.getAndIncrement(this);
            long v1 = 0xFFFFFFFFFFFFFFFL & v;
            boolean z = this.isClosedForSend0(v);
            long v2 = v1 / ((long)BufferedChannelKt.SEGMENT_SIZE);
            int v3 = (int)(v1 % ((long)BufferedChannelKt.SEGMENT_SIZE));
            if(channelSegment0.id == v2) {
            label_17:
                channelSegment1 = channelSegment0;
                v4 = v3;
            }
            else {
                channelSegment1 = this.findSegmentSend(v2, channelSegment0);
                if(channelSegment1 == null) {
                    if(!z) {
                        continue;
                    }
                    Throwable throwable0 = this.getSendException();
                    return ChannelResult.Companion.closed-JP2dKIU(throwable0);
                }
                else {
                    v4 = v3;
                    goto label_19;
                }
                goto label_17;
            }
        label_19:
            channelSegment0 = channelSegment1;
            switch(this.updateCellSend(channelSegment1, v4, object0, v1, symbol0, z)) {
                case 0: {
                    channelSegment0.cleanPrev();
                    return ChannelResult.Companion.success-JP2dKIU(Unit.INSTANCE);
                }
                case 1: {
                    return ChannelResult.Companion.success-JP2dKIU(Unit.INSTANCE);
                }
                case 2: {
                    if(z) {
                        channelSegment0.onSlotCleaned();
                        Throwable throwable2 = this.getSendException();
                        return ChannelResult.Companion.closed-JP2dKIU(throwable2);
                    }
                    Waiter waiter0 = symbol0 instanceof Waiter ? ((Waiter)symbol0) : null;
                    if(waiter0 != null) {
                        this.prepareSenderForSuspension(waiter0, channelSegment0, v4);
                    }
                    channelSegment0.onSlotCleaned();
                    return ChannelResult.Companion.failure-PtdJZtk();
                }
                case 3: {
                    throw new IllegalStateException("unexpected");
                }
                case 4: {
                    if(v1 < this.getReceiversCounter$kotlinx_coroutines_core()) {
                        channelSegment0.cleanPrev();
                    }
                    Throwable throwable1 = this.getSendException();
                    return ChannelResult.Companion.closed-JP2dKIU(throwable1);
                }
                case 5: {
                    channelSegment0.cleanPrev();
                }
            }
        }
    }

    private final void update$atomicfu(AtomicLongFieldUpdater atomicLongFieldUpdater0, Function1 function10, Object object0) {
        do {
            long v = atomicLongFieldUpdater0.get(object0);
        }
        while(!atomicLongFieldUpdater0.compareAndSet(object0, v, ((Number)function10.invoke(v)).longValue()));
    }

    private final boolean updateCellExpandBuffer(ChannelSegment channelSegment0, int v, long v1) {
        Object object0 = channelSegment0.getState$kotlinx_coroutines_core(v);
        if(object0 instanceof Waiter && v1 >= BufferedChannel.receivers$FU.get(this) && channelSegment0.casState$kotlinx_coroutines_core(v, object0, BufferedChannelKt.RESUMING_BY_EB)) {
            if(this.tryResumeSender(object0, channelSegment0, v)) {
                channelSegment0.setState$kotlinx_coroutines_core(v, BufferedChannelKt.BUFFERED);
                return true;
            }
            channelSegment0.setState$kotlinx_coroutines_core(v, BufferedChannelKt.INTERRUPTED_SEND);
            channelSegment0.onCancelledRequest(v, false);
            return false;
        }
        return this.updateCellExpandBufferSlow(channelSegment0, v, v1);
    }

    private final boolean updateCellExpandBufferSlow(ChannelSegment channelSegment0, int v, long v1) {
        Object object0;
        do {
            while(true) {
            alab1:
                while(true) {
                    do {
                        object0 = channelSegment0.getState$kotlinx_coroutines_core(v);
                        if(!(object0 instanceof Waiter)) {
                            break alab1;
                        }
                        if(v1 >= BufferedChannel.receivers$FU.get(this)) {
                            goto label_5;
                        }
                    }
                    while(!channelSegment0.casState$kotlinx_coroutines_core(v, object0, new WaiterEB(((Waiter)object0))));
                    return true;
                label_5:
                    if(channelSegment0.casState$kotlinx_coroutines_core(v, object0, BufferedChannelKt.RESUMING_BY_EB)) {
                        if(this.tryResumeSender(object0, channelSegment0, v)) {
                            channelSegment0.setState$kotlinx_coroutines_core(v, BufferedChannelKt.BUFFERED);
                            return true;
                        }
                        channelSegment0.setState$kotlinx_coroutines_core(v, BufferedChannelKt.INTERRUPTED_SEND);
                        channelSegment0.onCancelledRequest(v, false);
                        return false;
                    }
                }
                if(object0 == BufferedChannelKt.INTERRUPTED_SEND) {
                    return false;
                }
                if(object0 != null) {
                    break;
                }
                if(channelSegment0.casState$kotlinx_coroutines_core(v, null, BufferedChannelKt.IN_BUFFER)) {
                    return true;
                }
            }
            if(object0 == BufferedChannelKt.BUFFERED) {
                return true;
            }
            if(object0 == BufferedChannelKt.POISONED || object0 == BufferedChannelKt.DONE_RCV || object0 == BufferedChannelKt.INTERRUPTED_RCV || object0 == BufferedChannelKt.getCHANNEL_CLOSED()) {
                return true;
            }
        }
        while(object0 == BufferedChannelKt.RESUMING_BY_RCV);
        throw new IllegalStateException(("Unexpected cell state: " + object0).toString());
    }

    private final Object updateCellReceive(ChannelSegment channelSegment0, int v, long v1, Object object0) {
        Object object1 = channelSegment0.getState$kotlinx_coroutines_core(v);
        if(object1 == null) {
            if(v1 >= (BufferedChannel.sendersAndCloseStatus$FU.get(this) & 0xFFFFFFFFFFFFFFFL)) {
                if(object0 == null) {
                    return BufferedChannelKt.SUSPEND_NO_WAITER;
                }
                if(channelSegment0.casState$kotlinx_coroutines_core(v, null, object0)) {
                    this.expandBuffer();
                    return BufferedChannelKt.SUSPEND;
                }
            }
        }
        else if(object1 == BufferedChannelKt.BUFFERED && channelSegment0.casState$kotlinx_coroutines_core(v, object1, BufferedChannelKt.DONE_RCV)) {
            this.expandBuffer();
            return channelSegment0.retrieveElement$kotlinx_coroutines_core(v);
        }
        return this.updateCellReceiveSlow(channelSegment0, v, v1, object0);
    }

    private final Object updateCellReceiveSlow(ChannelSegment channelSegment0, int v, long v1, Object object0) {
        Waiter waiter0;
        do {
            while(true) {
            alab1:
                while(true) {
                    do {
                        waiter0 = channelSegment0.getState$kotlinx_coroutines_core(v);
                        if(waiter0 == null || waiter0 == BufferedChannelKt.IN_BUFFER) {
                            break alab1;
                        }
                        if(waiter0 != BufferedChannelKt.BUFFERED) {
                            goto label_6;
                        }
                    }
                    while(!channelSegment0.casState$kotlinx_coroutines_core(v, waiter0, BufferedChannelKt.DONE_RCV));
                    this.expandBuffer();
                    return channelSegment0.retrieveElement$kotlinx_coroutines_core(v);
                label_6:
                    if(waiter0 == BufferedChannelKt.INTERRUPTED_SEND) {
                        return BufferedChannelKt.FAILED;
                    }
                    if(waiter0 == BufferedChannelKt.POISONED) {
                        return BufferedChannelKt.FAILED;
                    }
                    if(waiter0 == BufferedChannelKt.getCHANNEL_CLOSED()) {
                        this.expandBuffer();
                        return BufferedChannelKt.FAILED;
                    }
                    if(waiter0 != BufferedChannelKt.RESUMING_BY_EB && channelSegment0.casState$kotlinx_coroutines_core(v, waiter0, BufferedChannelKt.RESUMING_BY_RCV)) {
                        boolean z = waiter0 instanceof WaiterEB;
                        if(z) {
                            waiter0 = ((WaiterEB)waiter0).waiter;
                        }
                        if(this.tryResumeSender(waiter0, channelSegment0, v)) {
                            channelSegment0.setState$kotlinx_coroutines_core(v, BufferedChannelKt.DONE_RCV);
                            this.expandBuffer();
                            return channelSegment0.retrieveElement$kotlinx_coroutines_core(v);
                        }
                        channelSegment0.setState$kotlinx_coroutines_core(v, BufferedChannelKt.INTERRUPTED_SEND);
                        channelSegment0.onCancelledRequest(v, false);
                        if(z) {
                            this.expandBuffer();
                        }
                        return BufferedChannelKt.FAILED;
                    }
                }
                if(v1 >= (BufferedChannel.sendersAndCloseStatus$FU.get(this) & 0xFFFFFFFFFFFFFFFL)) {
                    break;
                }
                if(channelSegment0.casState$kotlinx_coroutines_core(v, waiter0, BufferedChannelKt.POISONED)) {
                    this.expandBuffer();
                    return BufferedChannelKt.FAILED;
                }
            }
            if(object0 == null) {
                return BufferedChannelKt.SUSPEND_NO_WAITER;
            }
        }
        while(!channelSegment0.casState$kotlinx_coroutines_core(v, waiter0, object0));
        this.expandBuffer();
        return BufferedChannelKt.SUSPEND;
    }

    private final int updateCellSend(ChannelSegment channelSegment0, int v, Object object0, long v1, Object object1, boolean z) {
        channelSegment0.storeElement$kotlinx_coroutines_core(v, object0);
        if(z) {
            return this.updateCellSendSlow(channelSegment0, v, object0, v1, object1, true);
        }
        Object object2 = channelSegment0.getState$kotlinx_coroutines_core(v);
        if(object2 == null) {
            if(this.bufferOrRendezvousSend(v1)) {
                return channelSegment0.casState$kotlinx_coroutines_core(v, null, BufferedChannelKt.BUFFERED) ? 1 : this.updateCellSendSlow(channelSegment0, v, object0, v1, object1, false);
            }
            if(object1 == null) {
                return 3;
            }
            return channelSegment0.casState$kotlinx_coroutines_core(v, null, object1) ? 2 : this.updateCellSendSlow(channelSegment0, v, object0, v1, object1, false);
        }
        if(object2 instanceof Waiter) {
            channelSegment0.cleanElement$kotlinx_coroutines_core(v);
            if(this.tryResumeReceiver(object2, object0)) {
                channelSegment0.setState$kotlinx_coroutines_core(v, BufferedChannelKt.DONE_RCV);
                return 0;
            }
            if(channelSegment0.getAndSetState$kotlinx_coroutines_core(v, BufferedChannelKt.INTERRUPTED_RCV) != BufferedChannelKt.INTERRUPTED_RCV) {
                channelSegment0.onCancelledRequest(v, true);
            }
            return 5;
        }
        return this.updateCellSendSlow(channelSegment0, v, object0, v1, object1, false);
    }

    private final int updateCellSendSlow(ChannelSegment channelSegment0, int v, Object object0, long v1, Object object1, boolean z) {
        Waiter waiter0;
        while(true) {
        alab1:
            while(true) {
                do {
                    do {
                        waiter0 = channelSegment0.getState$kotlinx_coroutines_core(v);
                        if(waiter0 != null) {
                            break alab1;
                        }
                        if(!this.bufferOrRendezvousSend(v1) || z) {
                            goto label_5;
                        }
                    }
                    while(!channelSegment0.casState$kotlinx_coroutines_core(v, null, BufferedChannelKt.BUFFERED));
                    return 1;
                label_5:
                    if(!z) {
                        goto label_9;
                    }
                }
                while(!channelSegment0.casState$kotlinx_coroutines_core(v, null, BufferedChannelKt.INTERRUPTED_SEND));
                channelSegment0.onCancelledRequest(v, false);
                return 4;
            label_9:
                if(object1 == null) {
                    return 3;
                }
                if(channelSegment0.casState$kotlinx_coroutines_core(v, null, object1)) {
                    return 2;
                }
            }
            if(waiter0 != BufferedChannelKt.IN_BUFFER) {
                break;
            }
            if(channelSegment0.casState$kotlinx_coroutines_core(v, waiter0, BufferedChannelKt.BUFFERED)) {
                return 1;
            }
        }
        if(waiter0 == BufferedChannelKt.INTERRUPTED_RCV) {
            channelSegment0.cleanElement$kotlinx_coroutines_core(v);
            return 5;
        }
        if(waiter0 == BufferedChannelKt.POISONED) {
            channelSegment0.cleanElement$kotlinx_coroutines_core(v);
            return 5;
        }
        if(waiter0 == BufferedChannelKt.getCHANNEL_CLOSED()) {
            channelSegment0.cleanElement$kotlinx_coroutines_core(v);
            this.completeCloseOrCancel();
            return 4;
        }
        channelSegment0.cleanElement$kotlinx_coroutines_core(v);
        if(waiter0 instanceof WaiterEB) {
            waiter0 = ((WaiterEB)waiter0).waiter;
        }
        if(this.tryResumeReceiver(waiter0, object0)) {
            channelSegment0.setState$kotlinx_coroutines_core(v, BufferedChannelKt.DONE_RCV);
            return 0;
        }
        if(channelSegment0.getAndSetState$kotlinx_coroutines_core(v, BufferedChannelKt.INTERRUPTED_RCV) != BufferedChannelKt.INTERRUPTED_RCV) {
            channelSegment0.onCancelledRequest(v, true);
        }
        return 5;
    }

    private final void updateReceiversCounterIfLower(long v) {
        AtomicLongFieldUpdater atomicLongFieldUpdater0 = BufferedChannel.receivers$FU;
        do {
            long v1 = atomicLongFieldUpdater0.get(this);
        }
        while(v1 < v && !BufferedChannel.receivers$FU.compareAndSet(this, v1, v));
    }

    private final void updateSendersCounterIfLower(long v) {
        AtomicLongFieldUpdater atomicLongFieldUpdater0 = BufferedChannel.sendersAndCloseStatus$FU;
        do {
            long v1 = atomicLongFieldUpdater0.get(this);
        }
        while((0xFFFFFFFFFFFFFFFL & v1) < v && !BufferedChannel.sendersAndCloseStatus$FU.compareAndSet(this, v1, BufferedChannelKt.access$constructSendersAndCloseStatus(0xFFFFFFFFFFFFFFFL & v1, ((int)(v1 >> 60)))));
    }

    public final void waitExpandBufferCompletion$kotlinx_coroutines_core(long v) {
        AtomicLongFieldUpdater atomicLongFieldUpdater1;
        if(!this.isRendezvousOrUnlimited()) {
            while(this.getBufferEndCounter() <= v) {
            }
            AtomicLongFieldUpdater atomicLongFieldUpdater0 = BufferedChannel.completedExpandBuffersAndPauseFlag$FU;
            do {
                long v1 = atomicLongFieldUpdater0.get(this);
            }
            while(!atomicLongFieldUpdater0.compareAndSet(this, v1, BufferedChannelKt.constructEBCompletedAndPauseFlag(v1 & 0x3FFFFFFFFFFFFFFFL, true)));
            while(true) {
                long v2 = this.getBufferEndCounter();
                atomicLongFieldUpdater1 = BufferedChannel.completedExpandBuffersAndPauseFlag$FU;
                long v3 = atomicLongFieldUpdater1.get(this);
                long v4 = v3 & 0x3FFFFFFFFFFFFFFFL;
                if(v2 == v4 && v2 == this.getBufferEndCounter()) {
                    break;
                }
                if((0x4000000000000000L & v3) == 0L) {
                    atomicLongFieldUpdater1.compareAndSet(this, v3, BufferedChannelKt.constructEBCompletedAndPauseFlag(v4, true));
                }
            }
            while(true) {
                long v5 = atomicLongFieldUpdater1.get(this);
                if(atomicLongFieldUpdater1.compareAndSet(this, v5, BufferedChannelKt.constructEBCompletedAndPauseFlag(v5 & 0x3FFFFFFFFFFFFFFFL, false))) {
                    break;
                }
            }
        }
    }
}

