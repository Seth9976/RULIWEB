package kotlinx.coroutines.channels;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.IndexedValue;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref.IntRef;
import kotlinx.coroutines.Dispatchers;

@Metadata(d1 = {"\u0000\u00A0\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u000B\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u001F\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000B\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\b\u0002\n\u0002\u0010!\n\u0000\n\u0002\u0010#\n\u0000\n\u0002\u0010\"\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001AJ\u0010\u0000\u001A#\u0012\u0015\u0012\u0013\u0018\u00010\u0002\u00A2\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0005\u0012\u0004\u0012\u00020\u00060\u0001j\u0002`\u00072\u001A\u0010\b\u001A\u000E\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\n0\t\"\u0006\u0012\u0002\b\u00030\nH\u0001\u00A2\u0006\u0002\u0010\u000B\u001A!\u0010\f\u001A\u00020\r\"\u0004\b\u0000\u0010\u000E*\b\u0012\u0004\u0012\u0002H\u000E0\nH\u0087@\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u000F\u001A1\u0010\u0010\u001A#\u0012\u0015\u0012\u0013\u0018\u00010\u0002\u00A2\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0005\u0012\u0004\u0012\u00020\u00060\u0001j\u0002`\u0007*\u0006\u0012\u0002\b\u00030\nH\u0001\u001A!\u0010\u0011\u001A\u00020\u0012\"\u0004\b\u0000\u0010\u000E*\b\u0012\u0004\u0012\u0002H\u000E0\nH\u0087@\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u000F\u001A\u001E\u0010\u0013\u001A\b\u0012\u0004\u0012\u0002H\u000E0\n\"\u0004\b\u0000\u0010\u000E*\b\u0012\u0004\u0012\u0002H\u000E0\nH\u0007\u001AZ\u0010\u0014\u001A\b\u0012\u0004\u0012\u0002H\u000E0\n\"\u0004\b\u0000\u0010\u000E\"\u0004\b\u0001\u0010\u0015*\b\u0012\u0004\u0012\u0002H\u000E0\n2\b\b\u0002\u0010\u0016\u001A\u00020\u00172\"\u0010\u0018\u001A\u001E\b\u0001\u0012\u0004\u0012\u0002H\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00150\u001A\u0012\u0006\u0012\u0004\u0018\u00010\u001B0\u0019H\u0001\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u001C\u001A0\u0010\u001D\u001A\b\u0012\u0004\u0012\u0002H\u000E0\n\"\u0004\b\u0000\u0010\u000E*\b\u0012\u0004\u0012\u0002H\u000E0\n2\u0006\u0010\u001E\u001A\u00020\u00122\b\b\u0002\u0010\u0016\u001A\u00020\u0017H\u0007\u001AT\u0010\u001F\u001A\b\u0012\u0004\u0012\u0002H\u000E0\n\"\u0004\b\u0000\u0010\u000E*\b\u0012\u0004\u0012\u0002H\u000E0\n2\b\b\u0002\u0010\u0016\u001A\u00020\u00172\"\u0010 \u001A\u001E\b\u0001\u0012\u0004\u0012\u0002H\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u001A\u0012\u0006\u0012\u0004\u0018\u00010\u001B0\u0019H\u0007\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u001C\u001A)\u0010!\u001A\u0002H\u000E\"\u0004\b\u0000\u0010\u000E*\b\u0012\u0004\u0012\u0002H\u000E0\n2\u0006\u0010\"\u001A\u00020\u0012H\u0087@\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010#\u001A+\u0010$\u001A\u0004\u0018\u0001H\u000E\"\u0004\b\u0000\u0010\u000E*\b\u0012\u0004\u0012\u0002H\u000E0\n2\u0006\u0010\"\u001A\u00020\u0012H\u0087@\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010#\u001AT\u0010%\u001A\b\u0012\u0004\u0012\u0002H\u000E0\n\"\u0004\b\u0000\u0010\u000E*\b\u0012\u0004\u0012\u0002H\u000E0\n2\b\b\u0002\u0010\u0016\u001A\u00020\u00172\"\u0010 \u001A\u001E\b\u0001\u0012\u0004\u0012\u0002H\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u001A\u0012\u0006\u0012\u0004\u0018\u00010\u001B0\u0019H\u0001\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u001C\u001Ai\u0010&\u001A\b\u0012\u0004\u0012\u0002H\u000E0\n\"\u0004\b\u0000\u0010\u000E*\b\u0012\u0004\u0012\u0002H\u000E0\n2\b\b\u0002\u0010\u0016\u001A\u00020\u001727\u0010 \u001A3\b\u0001\u0012\u0013\u0012\u00110\u0012\u00A2\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\"\u0012\u0004\u0012\u0002H\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u001A\u0012\u0006\u0012\u0004\u0018\u00010\u001B0\'H\u0007\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010(\u001AT\u0010)\u001A\b\u0012\u0004\u0012\u0002H\u000E0\n\"\u0004\b\u0000\u0010\u000E*\b\u0012\u0004\u0012\u0002H\u000E0\n2\b\b\u0002\u0010\u0016\u001A\u00020\u00172\"\u0010 \u001A\u001E\b\u0001\u0012\u0004\u0012\u0002H\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u001A\u0012\u0006\u0012\u0004\u0018\u00010\u001B0\u0019H\u0007\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u001C\u001A$\u0010*\u001A\b\u0012\u0004\u0012\u0002H\u000E0\n\"\b\b\u0000\u0010\u000E*\u00020\u001B*\n\u0012\u0006\u0012\u0004\u0018\u0001H\u000E0\nH\u0001\u001AA\u0010+\u001A\u0002H,\"\b\b\u0000\u0010\u000E*\u00020\u001B\"\u0010\b\u0001\u0010,*\n\u0012\u0006\b\u0000\u0012\u0002H\u000E0-*\n\u0012\u0006\u0012\u0004\u0018\u0001H\u000E0\n2\u0006\u0010.\u001A\u0002H,H\u0087@\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010/\u001A?\u0010+\u001A\u0002H,\"\b\b\u0000\u0010\u000E*\u00020\u001B\"\u000E\b\u0001\u0010,*\b\u0012\u0004\u0012\u0002H\u000E00*\n\u0012\u0006\u0012\u0004\u0018\u0001H\u000E0\n2\u0006\u0010.\u001A\u0002H,H\u0087@\u00F8\u0001\u0000\u00A2\u0006\u0002\u00101\u001A!\u00102\u001A\u0002H\u000E\"\u0004\b\u0000\u0010\u000E*\b\u0012\u0004\u0012\u0002H\u000E0\nH\u0087@\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u000F\u001A#\u00103\u001A\u0004\u0018\u0001H\u000E\"\u0004\b\u0000\u0010\u000E*\b\u0012\u0004\u0012\u0002H\u000E0\nH\u0087@\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u000F\u001A`\u00104\u001A\b\u0012\u0004\u0012\u0002H50\n\"\u0004\b\u0000\u0010\u000E\"\u0004\b\u0001\u00105*\b\u0012\u0004\u0012\u0002H\u000E0\n2\b\b\u0002\u0010\u0016\u001A\u00020\u00172(\u00106\u001A$\b\u0001\u0012\u0004\u0012\u0002H\u000E\u0012\u0010\u0012\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u0002H50\n0\u001A\u0012\u0006\u0012\u0004\u0018\u00010\u001B0\u0019H\u0007\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u001C\u001A)\u00107\u001A\u00020\u0012\"\u0004\b\u0000\u0010\u000E*\b\u0012\u0004\u0012\u0002H\u000E0\n2\u0006\u00108\u001A\u0002H\u000EH\u0087@\u00F8\u0001\u0000\u00A2\u0006\u0002\u00109\u001A!\u0010:\u001A\u0002H\u000E\"\u0004\b\u0000\u0010\u000E*\b\u0012\u0004\u0012\u0002H\u000E0\nH\u0087@\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u000F\u001A)\u0010;\u001A\u00020\u0012\"\u0004\b\u0000\u0010\u000E*\b\u0012\u0004\u0012\u0002H\u000E0\n2\u0006\u00108\u001A\u0002H\u000EH\u0087@\u00F8\u0001\u0000\u00A2\u0006\u0002\u00109\u001A#\u0010<\u001A\u0004\u0018\u0001H\u000E\"\u0004\b\u0000\u0010\u000E*\b\u0012\u0004\u0012\u0002H\u000E0\nH\u0087@\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u000F\u001AZ\u0010=\u001A\b\u0012\u0004\u0012\u0002H50\n\"\u0004\b\u0000\u0010\u000E\"\u0004\b\u0001\u00105*\b\u0012\u0004\u0012\u0002H\u000E0\n2\b\b\u0002\u0010\u0016\u001A\u00020\u00172\"\u00106\u001A\u001E\b\u0001\u0012\u0004\u0012\u0002H\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u0002H50\u001A\u0012\u0006\u0012\u0004\u0018\u00010\u001B0\u0019H\u0001\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u001C\u001Ao\u0010>\u001A\b\u0012\u0004\u0012\u0002H50\n\"\u0004\b\u0000\u0010\u000E\"\u0004\b\u0001\u00105*\b\u0012\u0004\u0012\u0002H\u000E0\n2\b\b\u0002\u0010\u0016\u001A\u00020\u001727\u00106\u001A3\b\u0001\u0012\u0013\u0012\u00110\u0012\u00A2\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\"\u0012\u0004\u0012\u0002H\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u0002H50\u001A\u0012\u0006\u0012\u0004\u0018\u00010\u001B0\'H\u0001\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010(\u001Au\u0010?\u001A\b\u0012\u0004\u0012\u0002H50\n\"\u0004\b\u0000\u0010\u000E\"\b\b\u0001\u00105*\u00020\u001B*\b\u0012\u0004\u0012\u0002H\u000E0\n2\b\b\u0002\u0010\u0016\u001A\u00020\u001729\u00106\u001A5\b\u0001\u0012\u0013\u0012\u00110\u0012\u00A2\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\"\u0012\u0004\u0012\u0002H\u000E\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u0001H50\u001A\u0012\u0006\u0012\u0004\u0018\u00010\u001B0\'H\u0007\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010(\u001A`\u0010@\u001A\b\u0012\u0004\u0012\u0002H50\n\"\u0004\b\u0000\u0010\u000E\"\b\b\u0001\u00105*\u00020\u001B*\b\u0012\u0004\u0012\u0002H\u000E0\n2\b\b\u0002\u0010\u0016\u001A\u00020\u00172$\u00106\u001A \b\u0001\u0012\u0004\u0012\u0002H\u000E\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u0001H50\u001A\u0012\u0006\u0012\u0004\u0018\u00010\u001B0\u0019H\u0007\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u001C\u001A?\u0010A\u001A\u0004\u0018\u0001H\u000E\"\u0004\b\u0000\u0010\u000E*\b\u0012\u0004\u0012\u0002H\u000E0\n2\u001A\u0010B\u001A\u0016\u0012\u0006\b\u0000\u0012\u0002H\u000E0Cj\n\u0012\u0006\b\u0000\u0012\u0002H\u000E`DH\u0087@\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010E\u001A?\u0010F\u001A\u0004\u0018\u0001H\u000E\"\u0004\b\u0000\u0010\u000E*\b\u0012\u0004\u0012\u0002H\u000E0\n2\u001A\u0010B\u001A\u0016\u0012\u0006\b\u0000\u0012\u0002H\u000E0Cj\n\u0012\u0006\b\u0000\u0012\u0002H\u000E`DH\u0087@\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010E\u001A!\u0010G\u001A\u00020\r\"\u0004\b\u0000\u0010\u000E*\b\u0012\u0004\u0012\u0002H\u000E0\nH\u0087@\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u000F\u001A$\u0010H\u001A\b\u0012\u0004\u0012\u0002H\u000E0\n\"\b\b\u0000\u0010\u000E*\u00020\u001B*\n\u0012\u0006\u0012\u0004\u0018\u0001H\u000E0\nH\u0007\u001A!\u0010I\u001A\u0002H\u000E\"\u0004\b\u0000\u0010\u000E*\b\u0012\u0004\u0012\u0002H\u000E0\nH\u0087@\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u000F\u001A#\u0010J\u001A\u0004\u0018\u0001H\u000E\"\u0004\b\u0000\u0010\u000E*\b\u0012\u0004\u0012\u0002H\u000E0\nH\u0087@\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u000F\u001A0\u0010K\u001A\b\u0012\u0004\u0012\u0002H\u000E0\n\"\u0004\b\u0000\u0010\u000E*\b\u0012\u0004\u0012\u0002H\u000E0\n2\u0006\u0010\u001E\u001A\u00020\u00122\b\b\u0002\u0010\u0016\u001A\u00020\u0017H\u0007\u001AT\u0010L\u001A\b\u0012\u0004\u0012\u0002H\u000E0\n\"\u0004\b\u0000\u0010\u000E*\b\u0012\u0004\u0012\u0002H\u000E0\n2\b\b\u0002\u0010\u0016\u001A\u00020\u00172\"\u0010 \u001A\u001E\b\u0001\u0012\u0004\u0012\u0002H\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u001A\u0012\u0006\u0012\u0004\u0018\u00010\u001B0\u0019H\u0007\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u001C\u001A9\u0010M\u001A\u0002H,\"\u0004\b\u0000\u0010\u000E\"\u000E\b\u0001\u0010,*\b\u0012\u0004\u0012\u0002H\u000E00*\b\u0012\u0004\u0012\u0002H\u000E0\n2\u0006\u0010.\u001A\u0002H,H\u0081@\u00F8\u0001\u0000\u00A2\u0006\u0002\u00101\u001A;\u0010N\u001A\u0002H,\"\u0004\b\u0000\u0010\u000E\"\u0010\b\u0001\u0010,*\n\u0012\u0006\b\u0000\u0012\u0002H\u000E0-*\b\u0012\u0004\u0012\u0002H\u000E0\n2\u0006\u0010.\u001A\u0002H,H\u0081@\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010/\u001A?\u0010O\u001A\u000E\u0012\u0004\u0012\u0002H\u0015\u0012\u0004\u0012\u0002HQ0P\"\u0004\b\u0000\u0010\u0015\"\u0004\b\u0001\u0010Q*\u0014\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u0002H\u0015\u0012\u0004\u0012\u0002HQ0R0\nH\u0087@\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u000F\u001AU\u0010O\u001A\u0002HS\"\u0004\b\u0000\u0010\u0015\"\u0004\b\u0001\u0010Q\"\u0018\b\u0002\u0010S*\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0015\u0012\u0006\b\u0000\u0012\u0002HQ0T*\u0014\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u0002H\u0015\u0012\u0004\u0012\u0002HQ0R0\n2\u0006\u0010.\u001A\u0002HSH\u0081@\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010U\u001A\'\u0010V\u001A\b\u0012\u0004\u0012\u0002H\u000E0W\"\u0004\b\u0000\u0010\u000E*\b\u0012\u0004\u0012\u0002H\u000E0\nH\u0087@\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u000F\u001A\'\u0010X\u001A\b\u0012\u0004\u0012\u0002H\u000E0Y\"\u0004\b\u0000\u0010\u000E*\b\u0012\u0004\u0012\u0002H\u000E0\nH\u0081@\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u000F\u001A\'\u0010Z\u001A\b\u0012\u0004\u0012\u0002H\u000E0[\"\u0004\b\u0000\u0010\u000E*\b\u0012\u0004\u0012\u0002H\u000E0\nH\u0087@\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u000F\u001A.\u0010\\\u001A\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000E0]0\n\"\u0004\b\u0000\u0010\u000E*\b\u0012\u0004\u0012\u0002H\u000E0\n2\b\b\u0002\u0010\u0016\u001A\u00020\u0017H\u0007\u001A?\u0010^\u001A\u0014\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u0002H\u000E\u0012\u0004\u0012\u0002H50R0\n\"\u0004\b\u0000\u0010\u000E\"\u0004\b\u0001\u00105*\b\u0012\u0004\u0012\u0002H\u000E0\n2\f\u0010_\u001A\b\u0012\u0004\u0012\u0002H50\nH\u0087\u0004\u001Az\u0010^\u001A\b\u0012\u0004\u0012\u0002HQ0\n\"\u0004\b\u0000\u0010\u000E\"\u0004\b\u0001\u00105\"\u0004\b\u0002\u0010Q*\b\u0012\u0004\u0012\u0002H\u000E0\n2\f\u0010_\u001A\b\u0012\u0004\u0012\u0002H50\n2\b\b\u0002\u0010\u0016\u001A\u00020\u001726\u00106\u001A2\u0012\u0013\u0012\u0011H\u000E\u00A2\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(`\u0012\u0013\u0012\u0011H5\u00A2\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(a\u0012\u0004\u0012\u0002HQ0\u0019H\u0001\u0082\u0002\u0004\n\u0002\b\u0019\u00A8\u0006b"}, d2 = {"consumesAll", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "cause", "", "Lkotlinx/coroutines/CompletionHandler;", "channels", "", "Lkotlinx/coroutines/channels/ReceiveChannel;", "([Lkotlinx/coroutines/channels/ReceiveChannel;)Lkotlin/jvm/functions/Function1;", "any", "", "E", "(Lkotlinx/coroutines/channels/ReceiveChannel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "consumes", "count", "", "distinct", "distinctBy", "K", "context", "Lkotlin/coroutines/CoroutineContext;", "selector", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "", "(Lkotlinx/coroutines/channels/ReceiveChannel;Lkotlin/coroutines/CoroutineContext;Lkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/channels/ReceiveChannel;", "drop", "n", "dropWhile", "predicate", "elementAt", "index", "(Lkotlinx/coroutines/channels/ReceiveChannel;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "elementAtOrNull", "filter", "filterIndexed", "Lkotlin/Function3;", "(Lkotlinx/coroutines/channels/ReceiveChannel;Lkotlin/coroutines/CoroutineContext;Lkotlin/jvm/functions/Function3;)Lkotlinx/coroutines/channels/ReceiveChannel;", "filterNot", "filterNotNull", "filterNotNullTo", "C", "", "destination", "(Lkotlinx/coroutines/channels/ReceiveChannel;Ljava/util/Collection;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lkotlinx/coroutines/channels/SendChannel;", "(Lkotlinx/coroutines/channels/ReceiveChannel;Lkotlinx/coroutines/channels/SendChannel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "first", "firstOrNull", "flatMap", "R", "transform", "indexOf", "element", "(Lkotlinx/coroutines/channels/ReceiveChannel;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "last", "lastIndexOf", "lastOrNull", "map", "mapIndexed", "mapIndexedNotNull", "mapNotNull", "maxWith", "comparator", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "(Lkotlinx/coroutines/channels/ReceiveChannel;Ljava/util/Comparator;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "minWith", "none", "requireNoNulls", "single", "singleOrNull", "take", "takeWhile", "toChannel", "toCollection", "toMap", "", "V", "Lkotlin/Pair;", "M", "", "(Lkotlinx/coroutines/channels/ReceiveChannel;Ljava/util/Map;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "toMutableList", "", "toMutableSet", "", "toSet", "", "withIndex", "Lkotlin/collections/IndexedValue;", "zip", "other", "a", "b", "kotlinx-coroutines-core"}, k = 5, mv = {1, 8, 0}, xi = 0x30, xs = "kotlinx/coroutines/channels/ChannelsKt")
final class ChannelsKt__DeprecatedKt {
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final Object any(ReceiveChannel receiveChannel0, Continuation continuation0) {
        kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.any.1 channelsKt__DeprecatedKt$any$10;
        if(continuation0 instanceof kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.any.1) {
            channelsKt__DeprecatedKt$any$10 = (kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.any.1)continuation0;
            if((channelsKt__DeprecatedKt$any$10.label & 0x80000000) == 0) {
                channelsKt__DeprecatedKt$any$10 = new ContinuationImpl(continuation0) {
                    Object L$0;
                    int label;
                    Object result;

                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object object0) {
                        this.result = object0;
                        this.label |= 0x80000000;
                        return ChannelsKt__DeprecatedKt.any(null, this);
                    }
                };
            }
            else {
                channelsKt__DeprecatedKt$any$10.label ^= 0x80000000;
            }
        }
        else {
            channelsKt__DeprecatedKt$any$10 = new ContinuationImpl(continuation0) {
                Object L$0;
                int label;
                Object result;

                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object object0) {
                    this.result = object0;
                    this.label |= 0x80000000;
                    return ChannelsKt__DeprecatedKt.any(null, this);
                }
            };
        }
        Object object0 = channelsKt__DeprecatedKt$any$10.result;
        Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch(channelsKt__DeprecatedKt$any$10.label) {
            case 0: {
                ResultKt.throwOnFailure(object0);
                try {
                    ChannelIterator channelIterator0 = receiveChannel0.iterator();
                    channelsKt__DeprecatedKt$any$10.L$0 = receiveChannel0;
                    channelsKt__DeprecatedKt$any$10.label = 1;
                    object0 = channelIterator0.hasNext(channelsKt__DeprecatedKt$any$10);
                    if(object0 == object1) {
                        return object1;
                    }
                    break;
                }
                catch(Throwable throwable0) {
                    ChannelsKt.cancelConsumed(receiveChannel0, throwable0);
                    throw throwable0;
                }
            }
            case 1: {
                receiveChannel0 = (ReceiveChannel)channelsKt__DeprecatedKt$any$10.L$0;
                try {
                    ResultKt.throwOnFailure(object0);
                    break;
                }
                catch(Throwable throwable0) {
                    ChannelsKt.cancelConsumed(receiveChannel0, throwable0);
                    throw throwable0;
                }
            }
            default: {
                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
            }
        }
        ChannelsKt.cancelConsumed(receiveChannel0, null);
        return object0;
    }

    public static final Function1 consumes(ReceiveChannel receiveChannel0) {
        return new Function1(receiveChannel0) {
            final ReceiveChannel $this_consumes;

            {
                this.$this_consumes = receiveChannel0;
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                this.invoke(((Throwable)object0));
                return Unit.INSTANCE;
            }

            public final void invoke(Throwable throwable0) {
                ChannelsKt.cancelConsumed(this.$this_consumes, throwable0);
            }
        };
    }

    public static final Function1 consumesAll(ReceiveChannel[] arr_receiveChannel) {
        return new Function1(arr_receiveChannel) {
            final ReceiveChannel[] $channels;

            {
                this.$channels = arr_receiveChannel;
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                this.invoke(((Throwable)object0));
                return Unit.INSTANCE;
            }

            public final void invoke(Throwable throwable0) {
                ReceiveChannel[] arr_receiveChannel = this.$channels;
                Throwable throwable1 = null;
                for(int v = 0; v < arr_receiveChannel.length; ++v) {
                    ReceiveChannel receiveChannel0 = arr_receiveChannel[v];
                    try {
                        ChannelsKt.cancelConsumed(receiveChannel0, throwable0);
                    }
                    catch(Throwable throwable2) {
                        if(throwable1 == null) {
                            throwable1 = throwable2;
                        }
                        else {
                            ExceptionsKt.addSuppressed(throwable1, throwable2);
                        }
                    }
                }
                if(throwable1 != null) {
                    throw throwable1;
                }
            }
        };
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final Object count(ReceiveChannel receiveChannel0, Continuation continuation0) {
        Object object2;
        Throwable throwable1;
        ReceiveChannel receiveChannel2;
        ChannelIterator channelIterator0;
        ReceiveChannel receiveChannel1;
        IntRef ref$IntRef1;
        kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.count.1 channelsKt__DeprecatedKt$count$10;
        if(continuation0 instanceof kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.count.1) {
            channelsKt__DeprecatedKt$count$10 = (kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.count.1)continuation0;
            if((channelsKt__DeprecatedKt$count$10.label & 0x80000000) == 0) {
                channelsKt__DeprecatedKt$count$10 = new ContinuationImpl(continuation0) {
                    Object L$0;
                    Object L$1;
                    Object L$2;
                    int label;
                    Object result;

                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object object0) {
                        this.result = object0;
                        this.label |= 0x80000000;
                        return ChannelsKt__DeprecatedKt.count(null, this);
                    }
                };
            }
            else {
                channelsKt__DeprecatedKt$count$10.label ^= 0x80000000;
            }
        }
        else {
            channelsKt__DeprecatedKt$count$10 = new ContinuationImpl(continuation0) {
                Object L$0;
                Object L$1;
                Object L$2;
                int label;
                Object result;

                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object object0) {
                    this.result = object0;
                    this.label |= 0x80000000;
                    return ChannelsKt__DeprecatedKt.count(null, this);
                }
            };
        }
        Object object0 = channelsKt__DeprecatedKt$count$10.result;
        Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
    alab1:
        switch(channelsKt__DeprecatedKt$count$10.label) {
            case 0: {
                ResultKt.throwOnFailure(object0);
                IntRef ref$IntRef0 = new IntRef();
                try {
                    ref$IntRef1 = ref$IntRef0;
                    receiveChannel1 = receiveChannel0;
                    channelIterator0 = receiveChannel0.iterator();
                    goto label_30;
                }
                catch(Throwable throwable0) {
                    receiveChannel2 = receiveChannel0;
                    throwable1 = throwable0;
                    break;
                }
            }
            case 1: {
                channelIterator0 = (ChannelIterator)channelsKt__DeprecatedKt$count$10.L$2;
                receiveChannel2 = (ReceiveChannel)channelsKt__DeprecatedKt$count$10.L$1;
                ref$IntRef1 = (IntRef)channelsKt__DeprecatedKt$count$10.L$0;
                try {
                    ResultKt.throwOnFailure(object0);
                    while(true) {
                        if(!((Boolean)object0).booleanValue()) {
                            goto label_49;
                        }
                        channelIterator0.next();
                        ++ref$IntRef1.element;
                        receiveChannel1 = receiveChannel2;
                        try {
                        label_30:
                            channelsKt__DeprecatedKt$count$10.L$0 = ref$IntRef1;
                            channelsKt__DeprecatedKt$count$10.L$1 = receiveChannel1;
                            channelsKt__DeprecatedKt$count$10.L$2 = channelIterator0;
                            channelsKt__DeprecatedKt$count$10.label = 1;
                            object2 = channelIterator0.hasNext(channelsKt__DeprecatedKt$count$10);
                        }
                        catch(Throwable throwable1) {
                            receiveChannel2 = receiveChannel1;
                            break alab1;
                        }
                        if(object2 == object1) {
                            return object1;
                        }
                        receiveChannel2 = receiveChannel1;
                        object0 = object2;
                    }
                }
                catch(Throwable throwable1) {
                    break;
                }
            }
            default: {
                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
            }
        }
        try {
            throw throwable1;
        }
        catch(Throwable throwable2) {
            ChannelsKt.cancelConsumed(receiveChannel2, throwable1);
            throw throwable2;
        }
    label_49:
        ChannelsKt.cancelConsumed(receiveChannel2, null);
        return Boxing.boxInt(ref$IntRef1.element);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final ReceiveChannel distinct(ReceiveChannel receiveChannel0) {
        return ChannelsKt__DeprecatedKt.distinctBy$default(receiveChannel0, null, new Function2(null) {
            Object L$0;
            int label;

            {
                super(2, continuation0);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object object0, Continuation continuation0) {
                kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.distinct.1 channelsKt__DeprecatedKt$distinct$10 = new kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.distinct.1(continuation0);
                channelsKt__DeprecatedKt$distinct$10.L$0 = object0;
                return channelsKt__DeprecatedKt$distinct$10;
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(object0, ((Continuation)object1));
            }

            public final Object invoke(Object object0, Continuation continuation0) {
                return ((kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.distinct.1)this.create(object0, continuation0)).invokeSuspend(Unit.INSTANCE);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object object0) {
                if(this.label != 0) {
                    throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                }
                ResultKt.throwOnFailure(object0);
                return this.L$0;
            }
        }, 1, null);
    }

    public static final ReceiveChannel distinctBy(ReceiveChannel receiveChannel0, CoroutineContext coroutineContext0, Function2 function20) {
        Function1 function10 = ChannelsKt.consumes(receiveChannel0);
        kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.distinctBy.1 channelsKt__DeprecatedKt$distinctBy$10 = new Function2(receiveChannel0, function20, null) {
            final Function2 $selector;
            final ReceiveChannel $this_distinctBy;
            private Object L$0;
            Object L$1;
            Object L$2;
            Object L$3;
            int label;

            {
                this.$this_distinctBy = receiveChannel0;
                this.$selector = function20;
                super(2, continuation0);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object object0, Continuation continuation0) {
                kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.distinctBy.1 channelsKt__DeprecatedKt$distinctBy$10 = new kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.distinctBy.1(this.$this_distinctBy, this.$selector, continuation0);
                channelsKt__DeprecatedKt$distinctBy$10.L$0 = object0;
                return channelsKt__DeprecatedKt$distinctBy$10;
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(((ProducerScope)object0), ((Continuation)object1));
            }

            public final Object invoke(ProducerScope producerScope0, Continuation continuation0) {
                return ((kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.distinctBy.1)this.create(producerScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object object0) {
                ChannelIterator channelIterator2;
                Object object4;
                Object object3;
                ProducerScope producerScope2;
                HashSet hashSet1;
                ProducerScope producerScope1;
                ChannelIterator channelIterator0;
                HashSet hashSet0;
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(this.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        ProducerScope producerScope0 = (ProducerScope)this.L$0;
                        hashSet0 = new HashSet();
                        channelIterator0 = this.$this_distinctBy.iterator();
                        producerScope1 = producerScope0;
                        goto label_31;
                    }
                    case 1: {
                        channelIterator0 = (ChannelIterator)this.L$2;
                        hashSet0 = (HashSet)this.L$1;
                        producerScope1 = (ProducerScope)this.L$0;
                        ResultKt.throwOnFailure(object0);
                        goto label_38;
                    }
                    case 2: {
                        Object object2 = this.L$3;
                        ChannelIterator channelIterator1 = (ChannelIterator)this.L$2;
                        hashSet1 = (HashSet)this.L$1;
                        producerScope2 = (ProducerScope)this.L$0;
                        ResultKt.throwOnFailure(object0);
                        object3 = object2;
                        channelIterator0 = channelIterator1;
                        goto label_51;
                    }
                    case 3: {
                        object4 = this.L$3;
                        channelIterator2 = (ChannelIterator)this.L$2;
                        hashSet1 = (HashSet)this.L$1;
                        producerScope2 = (ProducerScope)this.L$0;
                        ResultKt.throwOnFailure(object0);
                        break;
                    }
                    default: {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    }
                }
            alab1:
                while(true) {
                    hashSet1.add(object4);
                    channelIterator0 = channelIterator2;
                    do {
                        hashSet0 = hashSet1;
                        producerScope1 = producerScope2;
                    label_31:
                        this.L$0 = producerScope1;
                        this.L$1 = hashSet0;
                        this.L$2 = channelIterator0;
                        this.L$3 = null;
                        this.label = 1;
                        object0 = channelIterator0.hasNext(this);
                        if(object0 == object1) {
                            break alab1;
                        }
                    label_38:
                        if(!((Boolean)object0).booleanValue()) {
                            return Unit.INSTANCE;
                        }
                        Object object5 = channelIterator0.next();
                        this.L$0 = producerScope1;
                        this.L$1 = hashSet0;
                        this.L$2 = channelIterator0;
                        this.L$3 = object5;
                        this.label = 2;
                        Object object6 = this.$selector.invoke(object5, this);
                        if(object6 == object1) {
                            break alab1;
                        }
                        object3 = object5;
                        object0 = object6;
                        producerScope2 = producerScope1;
                        hashSet1 = hashSet0;
                    label_51:
                    }
                    while(hashSet1.contains(object0));
                    this.L$0 = producerScope2;
                    this.L$1 = hashSet1;
                    this.L$2 = channelIterator0;
                    this.L$3 = object0;
                    this.label = 3;
                    if(producerScope2.send(object3, this) == object1) {
                        break;
                    }
                    channelIterator2 = channelIterator0;
                    object4 = object0;
                }
                return object1;
            }
        };
        return ProduceKt.produce$default(() -> EmptyCoroutineContext.INSTANCE, coroutineContext0, 0, null, function10, channelsKt__DeprecatedKt$distinctBy$10, 6, null);
    }

    public static ReceiveChannel distinctBy$default(ReceiveChannel receiveChannel0, CoroutineContext coroutineContext0, Function2 function20, int v, Object object0) {
        if((v & 1) != 0) {
            coroutineContext0 = Dispatchers.getUnconfined();
        }
        return ChannelsKt.distinctBy(receiveChannel0, coroutineContext0, function20);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final ReceiveChannel drop(ReceiveChannel receiveChannel0, int v, CoroutineContext coroutineContext0) {
        Function1 function10 = ChannelsKt.consumes(receiveChannel0);
        kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.drop.1 channelsKt__DeprecatedKt$drop$10 = new Function2(v, receiveChannel0, null) {
            final int $n;
            final ReceiveChannel $this_drop;
            int I$0;
            private Object L$0;
            Object L$1;
            int label;

            {
                this.$n = v;
                this.$this_drop = receiveChannel0;
                super(2, continuation0);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object object0, Continuation continuation0) {
                kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.drop.1 channelsKt__DeprecatedKt$drop$10 = new kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.drop.1(this.$n, this.$this_drop, continuation0);
                channelsKt__DeprecatedKt$drop$10.L$0 = object0;
                return channelsKt__DeprecatedKt$drop$10;
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(((ProducerScope)object0), ((Continuation)object1));
            }

            public final Object invoke(ProducerScope producerScope0, Continuation continuation0) {
                return ((kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.drop.1)this.create(producerScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object object0) {
                ProducerScope producerScope2;
                ChannelIterator channelIterator1;
                ProducerScope producerScope1;
                ChannelIterator channelIterator0;
                int v;
                ProducerScope producerScope0;
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(this.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        producerScope0 = (ProducerScope)this.L$0;
                        v = this.$n;
                        if(v < 0) {
                            throw new IllegalArgumentException(("Requested element count " + v + " is less than zero.").toString());
                        }
                        if(v > 0) {
                            channelIterator0 = this.$this_drop.iterator();
                            producerScope1 = producerScope0;
                            goto label_20;
                        }
                        channelIterator1 = this.$this_drop.iterator();
                        goto label_38;
                    }
                    case 1: {
                        v = this.I$0;
                        channelIterator0 = (ChannelIterator)this.L$1;
                        producerScope1 = (ProducerScope)this.L$0;
                        ResultKt.throwOnFailure(object0);
                        while(((Boolean)object0).booleanValue()) {
                            channelIterator0.next();
                            --v;
                            if(v == 0) {
                                break;
                            }
                        label_20:
                            this.L$0 = producerScope1;
                            this.L$1 = channelIterator0;
                            this.I$0 = v;
                            this.label = 1;
                            object0 = channelIterator0.hasNext(this);
                            if(object0 == object1) {
                                return object1;
                            }
                        }
                        producerScope0 = producerScope1;
                        channelIterator1 = this.$this_drop.iterator();
                        goto label_38;
                    }
                    case 2: {
                        channelIterator1 = (ChannelIterator)this.L$1;
                        producerScope2 = (ProducerScope)this.L$0;
                        ResultKt.throwOnFailure(object0);
                        goto label_45;
                    }
                    case 3: {
                        channelIterator1 = (ChannelIterator)this.L$1;
                        producerScope2 = (ProducerScope)this.L$0;
                        ResultKt.throwOnFailure(object0);
                        break;
                    }
                    default: {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    }
                }
                do {
                    producerScope0 = producerScope2;
                label_38:
                    this.L$0 = producerScope0;
                    this.L$1 = channelIterator1;
                    this.label = 2;
                    Object object2 = channelIterator1.hasNext(this);
                    if(object2 == object1) {
                        return object1;
                    }
                    producerScope2 = producerScope0;
                    object0 = object2;
                label_45:
                    if(!((Boolean)object0).booleanValue()) {
                        return Unit.INSTANCE;
                    }
                    Object object3 = channelIterator1.next();
                    this.L$0 = producerScope2;
                    this.L$1 = channelIterator1;
                    this.label = 3;
                }
                while(producerScope2.send(object3, this) != object1);
                return object1;
            }
        };
        return ProduceKt.produce$default(() -> EmptyCoroutineContext.INSTANCE, coroutineContext0, 0, null, function10, channelsKt__DeprecatedKt$drop$10, 6, null);
    }

    public static ReceiveChannel drop$default(ReceiveChannel receiveChannel0, int v, CoroutineContext coroutineContext0, int v1, Object object0) {
        if((v1 & 2) != 0) {
            coroutineContext0 = Dispatchers.getUnconfined();
        }
        return ChannelsKt__DeprecatedKt.drop(receiveChannel0, v, coroutineContext0);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final ReceiveChannel dropWhile(ReceiveChannel receiveChannel0, CoroutineContext coroutineContext0, Function2 function20) {
        Function1 function10 = ChannelsKt.consumes(receiveChannel0);
        kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.dropWhile.1 channelsKt__DeprecatedKt$dropWhile$10 = new Function2(receiveChannel0, function20, null) {
            final Function2 $predicate;
            final ReceiveChannel $this_dropWhile;
            private Object L$0;
            Object L$1;
            Object L$2;
            int label;

            {
                this.$this_dropWhile = receiveChannel0;
                this.$predicate = function20;
                super(2, continuation0);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object object0, Continuation continuation0) {
                kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.dropWhile.1 channelsKt__DeprecatedKt$dropWhile$10 = new kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.dropWhile.1(this.$this_dropWhile, this.$predicate, continuation0);
                channelsKt__DeprecatedKt$dropWhile$10.L$0 = object0;
                return channelsKt__DeprecatedKt$dropWhile$10;
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(((ProducerScope)object0), ((Continuation)object1));
            }

            public final Object invoke(ProducerScope producerScope0, Continuation continuation0) {
                return ((kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.dropWhile.1)this.create(producerScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object object0) {
                ProducerScope producerScope0;
                ChannelIterator channelIterator0;
                ProducerScope producerScope1;
                Object object3;
                ChannelIterator channelIterator2;
                ProducerScope producerScope2;
                ProducerScope producerScope3;
                ChannelIterator channelIterator3;
                ChannelIterator channelIterator4;
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(this.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        ProducerScope producerScope4 = (ProducerScope)this.L$0;
                        channelIterator3 = this.$this_dropWhile.iterator();
                        for(producerScope3 = producerScope4; true; producerScope3 = producerScope2) {
                            this.L$0 = producerScope3;
                            this.L$1 = channelIterator3;
                            this.L$2 = null;
                            this.label = 1;
                            object0 = channelIterator3.hasNext(this);
                            if(object0 == object1) {
                                return object1;
                            }
                            producerScope1 = producerScope3;
                        label_38:
                            if(!((Boolean)object0).booleanValue()) {
                                break;
                            }
                            Object object4 = channelIterator3.next();
                            this.L$0 = producerScope1;
                            this.L$1 = channelIterator3;
                            this.L$2 = object4;
                            this.label = 2;
                            Object object5 = this.$predicate.invoke(object4, this);
                            if(object5 == object1) {
                                return object1;
                            }
                            channelIterator2 = channelIterator3;
                            object3 = object4;
                            object0 = object5;
                            producerScope2 = producerScope1;
                        label_50:
                            channelIterator3 = channelIterator2;
                            if(!((Boolean)object0).booleanValue()) {
                                this.L$0 = producerScope2;
                                this.L$1 = null;
                                this.L$2 = null;
                                this.label = 3;
                                if(producerScope2.send(object3, this) == object1) {
                                    return object1;
                                }
                                producerScope1 = producerScope2;
                                break;
                            }
                        }
                        channelIterator4 = this.$this_dropWhile.iterator();
                        break;
                    }
                    case 1: {
                        channelIterator3 = (ChannelIterator)this.L$1;
                        producerScope3 = (ProducerScope)this.L$0;
                        ResultKt.throwOnFailure(object0);
                        producerScope1 = producerScope3;
                        goto label_38;
                    }
                    case 2: {
                        Object object2 = this.L$2;
                        ChannelIterator channelIterator1 = (ChannelIterator)this.L$1;
                        producerScope2 = (ProducerScope)this.L$0;
                        ResultKt.throwOnFailure(object0);
                        channelIterator2 = channelIterator1;
                        object3 = object2;
                        goto label_50;
                    }
                    case 3: {
                        producerScope1 = (ProducerScope)this.L$0;
                        ResultKt.throwOnFailure(object0);
                        channelIterator4 = this.$this_dropWhile.iterator();
                        break;
                    }
                    case 4: {
                        channelIterator0 = (ChannelIterator)this.L$1;
                        producerScope0 = (ProducerScope)this.L$0;
                        ResultKt.throwOnFailure(object0);
                        goto label_70;
                    }
                    case 5: {
                        channelIterator0 = (ChannelIterator)this.L$1;
                        producerScope0 = (ProducerScope)this.L$0;
                        ResultKt.throwOnFailure(object0);
                        goto label_76;
                    }
                    default: {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    }
                }
                while(true) {
                    this.L$0 = producerScope1;
                    this.L$1 = channelIterator4;
                    this.label = 4;
                    Object object6 = channelIterator4.hasNext(this);
                    if(object6 == object1) {
                        break;
                    }
                    channelIterator0 = channelIterator4;
                    object0 = object6;
                    producerScope0 = producerScope1;
                label_70:
                    if(((Boolean)object0).booleanValue()) {
                        Object object7 = channelIterator0.next();
                        this.L$0 = producerScope0;
                        this.L$1 = channelIterator0;
                        this.label = 5;
                        if(producerScope0.send(object7, this) == object1) {
                            break;
                        }
                    label_76:
                        channelIterator4 = channelIterator0;
                        producerScope1 = producerScope0;
                        continue;
                    }
                    return Unit.INSTANCE;
                }
                return object1;
            }
        };
        return ProduceKt.produce$default(() -> EmptyCoroutineContext.INSTANCE, coroutineContext0, 0, null, function10, channelsKt__DeprecatedKt$dropWhile$10, 6, null);
    }

    public static ReceiveChannel dropWhile$default(ReceiveChannel receiveChannel0, CoroutineContext coroutineContext0, Function2 function20, int v, Object object0) {
        if((v & 1) != 0) {
            coroutineContext0 = Dispatchers.getUnconfined();
        }
        return ChannelsKt__DeprecatedKt.dropWhile(receiveChannel0, coroutineContext0, function20);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final Object elementAt(ReceiveChannel receiveChannel0, int v, Continuation continuation0) {
        Object object3;
        Object object2;
        ReceiveChannel receiveChannel1;
        ChannelIterator channelIterator1;
        int v2;
        int v1;
        ChannelIterator channelIterator0;
        kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.elementAt.1 channelsKt__DeprecatedKt$elementAt$10;
        if(continuation0 instanceof kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.elementAt.1) {
            channelsKt__DeprecatedKt$elementAt$10 = (kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.elementAt.1)continuation0;
            if((channelsKt__DeprecatedKt$elementAt$10.label & 0x80000000) == 0) {
                channelsKt__DeprecatedKt$elementAt$10 = new ContinuationImpl(continuation0) {
                    int I$0;
                    int I$1;
                    Object L$0;
                    Object L$1;
                    int label;
                    Object result;

                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object object0) {
                        this.result = object0;
                        this.label |= 0x80000000;
                        return ChannelsKt__DeprecatedKt.elementAt(null, 0, this);
                    }
                };
            }
            else {
                channelsKt__DeprecatedKt$elementAt$10.label ^= 0x80000000;
            }
        }
        else {
            channelsKt__DeprecatedKt$elementAt$10 = new ContinuationImpl(continuation0) {
                int I$0;
                int I$1;
                Object L$0;
                Object L$1;
                int label;
                Object result;

                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object object0) {
                    this.result = object0;
                    this.label |= 0x80000000;
                    return ChannelsKt__DeprecatedKt.elementAt(null, 0, this);
                }
            };
        }
        Object object0 = channelsKt__DeprecatedKt$elementAt$10.result;
        Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
    alab1:
        switch(channelsKt__DeprecatedKt$elementAt$10.label) {
            case 0: {
                ResultKt.throwOnFailure(object0);
                try {
                    if(v < 0) {
                        throw new IndexOutOfBoundsException("ReceiveChannel doesn\'t contain element at index " + v + '.');
                    }
                    channelIterator0 = receiveChannel0.iterator();
                    v1 = 0;
                    goto label_32;
                }
                catch(Throwable throwable0) {
                    receiveChannel1 = receiveChannel0;
                    throwable1 = throwable0;
                    throw throwable1;
                }
            }
            case 1: {
                v2 = channelsKt__DeprecatedKt$elementAt$10.I$1;
                v = channelsKt__DeprecatedKt$elementAt$10.I$0;
                channelIterator1 = (ChannelIterator)channelsKt__DeprecatedKt$elementAt$10.L$1;
                receiveChannel1 = (ReceiveChannel)channelsKt__DeprecatedKt$elementAt$10.L$0;
                ResultKt.throwOnFailure(object0);
                while(true) {
                label_23:
                    if(!((Boolean)object0).booleanValue()) {
                        throw new IndexOutOfBoundsException("ReceiveChannel doesn\'t contain element at index " + v + '.');
                    }
                    object2 = channelIterator1.next();
                    if(v == v2) {
                        break alab1;
                    }
                    goto label_29;
                }
            }
            default: {
                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
            }
        }
        ChannelsKt.cancelConsumed(receiveChannel1, null);
        return object2;
    label_29:
        channelIterator0 = channelIterator1;
        receiveChannel0 = receiveChannel1;
        v1 = v2 + 1;
        try {
        label_32:
            channelsKt__DeprecatedKt$elementAt$10.L$0 = receiveChannel0;
            channelsKt__DeprecatedKt$elementAt$10.L$1 = channelIterator0;
            channelsKt__DeprecatedKt$elementAt$10.I$0 = v;
            channelsKt__DeprecatedKt$elementAt$10.I$1 = v1;
            channelsKt__DeprecatedKt$elementAt$10.label = 1;
            object3 = channelIterator0.hasNext(channelsKt__DeprecatedKt$elementAt$10);
        }
        catch(Throwable throwable0) {
            receiveChannel1 = receiveChannel0;
            throwable1 = throwable0;
            throw throwable1;
        }
        if(object3 == object1) {
            return object1;
        }
        try {
            receiveChannel1 = receiveChannel0;
            v2 = v1;
            channelIterator1 = channelIterator0;
            object0 = object3;
            goto label_23;
        }
        catch(Throwable throwable1) {
        }
        try {
            throw throwable1;
        }
        catch(Throwable throwable2) {
            ChannelsKt.cancelConsumed(receiveChannel1, throwable1);
            throw throwable2;
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final Object elementAtOrNull(ReceiveChannel receiveChannel0, int v, Continuation continuation0) {
        Object object2;
        Throwable throwable3;
        ChannelIterator channelIterator2;
        kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.elementAtOrNull.1 channelsKt__DeprecatedKt$elementAtOrNull$11;
        ReceiveChannel receiveChannel1;
        Throwable throwable1;
        int v1;
        ChannelIterator channelIterator0;
        kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.elementAtOrNull.1 channelsKt__DeprecatedKt$elementAtOrNull$10;
        if(continuation0 instanceof kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.elementAtOrNull.1) {
            channelsKt__DeprecatedKt$elementAtOrNull$10 = (kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.elementAtOrNull.1)continuation0;
            if((channelsKt__DeprecatedKt$elementAtOrNull$10.label & 0x80000000) == 0) {
                channelsKt__DeprecatedKt$elementAtOrNull$10 = new ContinuationImpl(continuation0) {
                    int I$0;
                    int I$1;
                    Object L$0;
                    Object L$1;
                    int label;
                    Object result;

                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object object0) {
                        this.result = object0;
                        this.label |= 0x80000000;
                        return ChannelsKt__DeprecatedKt.elementAtOrNull(null, 0, this);
                    }
                };
            }
            else {
                channelsKt__DeprecatedKt$elementAtOrNull$10.label ^= 0x80000000;
            }
        }
        else {
            channelsKt__DeprecatedKt$elementAtOrNull$10 = new ContinuationImpl(continuation0) {
                int I$0;
                int I$1;
                Object L$0;
                Object L$1;
                int label;
                Object result;

                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object object0) {
                    this.result = object0;
                    this.label |= 0x80000000;
                    return ChannelsKt__DeprecatedKt.elementAtOrNull(null, 0, this);
                }
            };
        }
        Object object0 = channelsKt__DeprecatedKt$elementAtOrNull$10.result;
        Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch(channelsKt__DeprecatedKt$elementAtOrNull$10.label) {
            case 0: {
                ResultKt.throwOnFailure(object0);
                if(v < 0) {
                    ChannelsKt.cancelConsumed(receiveChannel0, null);
                    return null;
                }
                try {
                    channelIterator0 = receiveChannel0.iterator();
                    v1 = 0;
                    throwable1 = null;
                    goto label_42;
                }
                catch(Throwable throwable0) {
                    goto label_56;
                }
            }
            case 1: {
                int v2 = channelsKt__DeprecatedKt$elementAtOrNull$10.I$1;
                v = channelsKt__DeprecatedKt$elementAtOrNull$10.I$0;
                ChannelIterator channelIterator1 = (ChannelIterator)channelsKt__DeprecatedKt$elementAtOrNull$10.L$1;
                receiveChannel1 = (ReceiveChannel)channelsKt__DeprecatedKt$elementAtOrNull$10.L$0;
                ResultKt.throwOnFailure(object0);
                v1 = v2;
                receiveChannel0 = receiveChannel1;
                channelsKt__DeprecatedKt$elementAtOrNull$11 = channelsKt__DeprecatedKt$elementAtOrNull$10;
                channelIterator2 = channelIterator1;
                throwable3 = null;
                break;
            }
            default: {
                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
            }
        }
        try {
            while(true) {
            label_33:
                if(!((Boolean)object0).booleanValue()) {
                    goto label_62;
                }
                object2 = channelIterator2.next();
                if(v == v1) {
                    break;
                }
                goto label_38;
            }
        }
        catch(Throwable throwable0) {
            goto label_56;
        }
        ChannelsKt.cancelConsumed(receiveChannel0, throwable3);
        return object2;
    label_38:
        channelIterator0 = channelIterator2;
        channelsKt__DeprecatedKt$elementAtOrNull$10 = channelsKt__DeprecatedKt$elementAtOrNull$11;
        throwable1 = throwable3;
        ++v1;
        try {
        label_42:
            channelsKt__DeprecatedKt$elementAtOrNull$10.L$0 = receiveChannel0;
            channelsKt__DeprecatedKt$elementAtOrNull$10.L$1 = channelIterator0;
            channelsKt__DeprecatedKt$elementAtOrNull$10.I$0 = v;
            channelsKt__DeprecatedKt$elementAtOrNull$10.I$1 = v1;
            channelsKt__DeprecatedKt$elementAtOrNull$10.label = 1;
            Object object3 = channelIterator0.hasNext(channelsKt__DeprecatedKt$elementAtOrNull$10);
            if(object3 == object1) {
                return object1;
            }
            channelIterator2 = channelIterator0;
            object0 = object3;
            throwable3 = throwable1;
            channelsKt__DeprecatedKt$elementAtOrNull$11 = channelsKt__DeprecatedKt$elementAtOrNull$10;
            goto label_33;
        }
        catch(Throwable throwable0) {
        label_56:
            receiveChannel1 = receiveChannel0;
            throwable2 = throwable0;
        }
        try {
            throw throwable2;
        }
        catch(Throwable throwable4) {
            ChannelsKt.cancelConsumed(receiveChannel1, throwable2);
            throw throwable4;
        }
    label_62:
        ChannelsKt.cancelConsumed(receiveChannel0, throwable3);
        return null;
    }

    public static final ReceiveChannel filter(ReceiveChannel receiveChannel0, CoroutineContext coroutineContext0, Function2 function20) {
        Function1 function10 = ChannelsKt.consumes(receiveChannel0);
        kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.filter.1 channelsKt__DeprecatedKt$filter$10 = new Function2(receiveChannel0, function20, null) {
            final Function2 $predicate;
            final ReceiveChannel $this_filter;
            private Object L$0;
            Object L$1;
            Object L$2;
            int label;

            {
                this.$this_filter = receiveChannel0;
                this.$predicate = function20;
                super(2, continuation0);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object object0, Continuation continuation0) {
                kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.filter.1 channelsKt__DeprecatedKt$filter$10 = new kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.filter.1(this.$this_filter, this.$predicate, continuation0);
                channelsKt__DeprecatedKt$filter$10.L$0 = object0;
                return channelsKt__DeprecatedKt$filter$10;
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(((ProducerScope)object0), ((Continuation)object1));
            }

            public final Object invoke(ProducerScope producerScope0, Continuation continuation0) {
                return ((kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.filter.1)this.create(producerScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object object0) {
                Object object3;
                ProducerScope producerScope2;
                ProducerScope producerScope1;
                ChannelIterator channelIterator0;
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(this.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        ProducerScope producerScope0 = (ProducerScope)this.L$0;
                        channelIterator0 = this.$this_filter.iterator();
                        producerScope1 = producerScope0;
                        break;
                    }
                    case 1: {
                        channelIterator0 = (ChannelIterator)this.L$1;
                        producerScope1 = (ProducerScope)this.L$0;
                        ResultKt.throwOnFailure(object0);
                        goto label_28;
                    }
                    case 2: {
                        Object object2 = this.L$2;
                        ChannelIterator channelIterator1 = (ChannelIterator)this.L$1;
                        producerScope2 = (ProducerScope)this.L$0;
                        ResultKt.throwOnFailure(object0);
                        object3 = object2;
                        channelIterator0 = channelIterator1;
                        goto label_39;
                    }
                    case 3: {
                        channelIterator0 = (ChannelIterator)this.L$1;
                        producerScope1 = (ProducerScope)this.L$0;
                        ResultKt.throwOnFailure(object0);
                        break;
                    }
                    default: {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    }
                }
                while(true) {
                    this.L$0 = producerScope1;
                    this.L$1 = channelIterator0;
                    this.L$2 = null;
                    this.label = 1;
                    object0 = channelIterator0.hasNext(this);
                    if(object0 == object1) {
                        break;
                    }
                label_28:
                    if(((Boolean)object0).booleanValue()) {
                        Object object4 = channelIterator0.next();
                        this.L$0 = producerScope1;
                        this.L$1 = channelIterator0;
                        this.L$2 = object4;
                        this.label = 2;
                        Object object5 = this.$predicate.invoke(object4, this);
                        if(object5 == object1) {
                            break;
                        }
                        object3 = object4;
                        object0 = object5;
                        producerScope2 = producerScope1;
                    label_39:
                        if(((Boolean)object0).booleanValue()) {
                            this.L$0 = producerScope2;
                            this.L$1 = channelIterator0;
                            this.L$2 = null;
                            this.label = 3;
                            if(producerScope2.send(object3, this) != object1) {
                                producerScope1 = producerScope2;
                                continue;
                            }
                            break;
                        }
                        producerScope1 = producerScope2;
                        continue;
                    }
                    return Unit.INSTANCE;
                }
                return object1;
            }
        };
        return ProduceKt.produce$default(() -> EmptyCoroutineContext.INSTANCE, coroutineContext0, 0, null, function10, channelsKt__DeprecatedKt$filter$10, 6, null);
    }

    public static ReceiveChannel filter$default(ReceiveChannel receiveChannel0, CoroutineContext coroutineContext0, Function2 function20, int v, Object object0) {
        if((v & 1) != 0) {
            coroutineContext0 = Dispatchers.getUnconfined();
        }
        return ChannelsKt.filter(receiveChannel0, coroutineContext0, function20);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final ReceiveChannel filterIndexed(ReceiveChannel receiveChannel0, CoroutineContext coroutineContext0, Function3 function30) {
        Function1 function10 = ChannelsKt.consumes(receiveChannel0);
        kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.filterIndexed.1 channelsKt__DeprecatedKt$filterIndexed$10 = new Function2(receiveChannel0, function30, null) {
            final Function3 $predicate;
            final ReceiveChannel $this_filterIndexed;
            int I$0;
            private Object L$0;
            Object L$1;
            Object L$2;
            int label;

            {
                this.$this_filterIndexed = receiveChannel0;
                this.$predicate = function30;
                super(2, continuation0);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object object0, Continuation continuation0) {
                kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.filterIndexed.1 channelsKt__DeprecatedKt$filterIndexed$10 = new kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.filterIndexed.1(this.$this_filterIndexed, this.$predicate, continuation0);
                channelsKt__DeprecatedKt$filterIndexed$10.L$0 = object0;
                return channelsKt__DeprecatedKt$filterIndexed$10;
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(((ProducerScope)object0), ((Continuation)object1));
            }

            public final Object invoke(ProducerScope producerScope0, Continuation continuation0) {
                return ((kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.filterIndexed.1)this.create(producerScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object object0) {
                Object object3;
                ProducerScope producerScope1;
                int v;
                ChannelIterator channelIterator0;
                ProducerScope producerScope0;
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(this.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        producerScope0 = (ProducerScope)this.L$0;
                        channelIterator0 = this.$this_filterIndexed.iterator();
                        v = 0;
                        break;
                    }
                    case 1: {
                        v = this.I$0;
                        channelIterator0 = (ChannelIterator)this.L$1;
                        producerScope0 = (ProducerScope)this.L$0;
                        ResultKt.throwOnFailure(object0);
                        goto label_32;
                    }
                    case 2: {
                        v = this.I$0;
                        Object object2 = this.L$2;
                        ChannelIterator channelIterator1 = (ChannelIterator)this.L$1;
                        producerScope1 = (ProducerScope)this.L$0;
                        ResultKt.throwOnFailure(object0);
                        object3 = object2;
                        channelIterator0 = channelIterator1;
                        goto label_45;
                    }
                    case 3: {
                        v = this.I$0;
                        channelIterator0 = (ChannelIterator)this.L$1;
                        producerScope0 = (ProducerScope)this.L$0;
                        ResultKt.throwOnFailure(object0);
                        break;
                    }
                    default: {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    }
                }
                while(true) {
                    this.L$0 = producerScope0;
                    this.L$1 = channelIterator0;
                    this.L$2 = null;
                    this.I$0 = v;
                    this.label = 1;
                    object0 = channelIterator0.hasNext(this);
                    if(object0 == object1) {
                        break;
                    }
                label_32:
                    if(((Boolean)object0).booleanValue()) {
                        Object object4 = channelIterator0.next();
                        this.L$0 = producerScope0;
                        this.L$1 = channelIterator0;
                        this.L$2 = object4;
                        this.I$0 = v + 1;
                        this.label = 2;
                        Object object5 = this.$predicate.invoke(Boxing.boxInt(v), object4, this);
                        if(object5 == object1) {
                            break;
                        }
                        producerScope1 = producerScope0;
                        object3 = object4;
                        object0 = object5;
                        ++v;
                    label_45:
                        if(((Boolean)object0).booleanValue()) {
                            this.L$0 = producerScope1;
                            this.L$1 = channelIterator0;
                            this.L$2 = null;
                            this.I$0 = v;
                            this.label = 3;
                            if(producerScope1.send(object3, this) != object1) {
                                producerScope0 = producerScope1;
                                continue;
                            }
                            break;
                        }
                        producerScope0 = producerScope1;
                        continue;
                    }
                    return Unit.INSTANCE;
                }
                return object1;
            }
        };
        return ProduceKt.produce$default(() -> EmptyCoroutineContext.INSTANCE, coroutineContext0, 0, null, function10, channelsKt__DeprecatedKt$filterIndexed$10, 6, null);
    }

    public static ReceiveChannel filterIndexed$default(ReceiveChannel receiveChannel0, CoroutineContext coroutineContext0, Function3 function30, int v, Object object0) {
        if((v & 1) != 0) {
            coroutineContext0 = Dispatchers.getUnconfined();
        }
        return ChannelsKt__DeprecatedKt.filterIndexed(receiveChannel0, coroutineContext0, function30);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final ReceiveChannel filterNot(ReceiveChannel receiveChannel0, CoroutineContext coroutineContext0, Function2 function20) {
        return ChannelsKt.filter(receiveChannel0, coroutineContext0, new Function2(function20, null) {
            final Function2 $predicate;
            Object L$0;
            int label;

            {
                this.$predicate = function20;
                super(2, continuation0);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object object0, Continuation continuation0) {
                kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.filterNot.1 channelsKt__DeprecatedKt$filterNot$10 = new kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.filterNot.1(this.$predicate, continuation0);
                channelsKt__DeprecatedKt$filterNot$10.L$0 = object0;
                return channelsKt__DeprecatedKt$filterNot$10;
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(object0, ((Continuation)object1));
            }

            public final Object invoke(Object object0, Continuation continuation0) {
                return ((kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.filterNot.1)this.create(object0, continuation0)).invokeSuspend(Unit.INSTANCE);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object object0) {
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(this.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        this.label = 1;
                        object0 = this.$predicate.invoke(this.L$0, this);
                        return object0 == object1 ? object1 : Boxing.boxBoolean(!((Boolean)object0).booleanValue());
                    }
                    case 1: {
                        ResultKt.throwOnFailure(object0);
                        return Boxing.boxBoolean(!((Boolean)object0).booleanValue());
                    }
                    default: {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    }
                }
            }
        });
    }

    public static ReceiveChannel filterNot$default(ReceiveChannel receiveChannel0, CoroutineContext coroutineContext0, Function2 function20, int v, Object object0) {
        if((v & 1) != 0) {
            coroutineContext0 = Dispatchers.getUnconfined();
        }
        return ChannelsKt__DeprecatedKt.filterNot(receiveChannel0, coroutineContext0, function20);
    }

    public static final ReceiveChannel filterNotNull(ReceiveChannel receiveChannel0) {
        ReceiveChannel receiveChannel1 = ChannelsKt__DeprecatedKt.filter$default(receiveChannel0, null, new Function2(null) {
            Object L$0;
            int label;

            {
                super(2, continuation0);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object object0, Continuation continuation0) {
                kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.filterNotNull.1 channelsKt__DeprecatedKt$filterNotNull$10 = new kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.filterNotNull.1(continuation0);
                channelsKt__DeprecatedKt$filterNotNull$10.L$0 = object0;
                return channelsKt__DeprecatedKt$filterNotNull$10;
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(object0, ((Continuation)object1));
            }

            public final Object invoke(Object object0, Continuation continuation0) {
                return ((kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.filterNotNull.1)this.create(object0, continuation0)).invokeSuspend(Unit.INSTANCE);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object object0) {
                if(this.label != 0) {
                    throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                }
                ResultKt.throwOnFailure(object0);
                return this.L$0 == null ? Boxing.boxBoolean(false) : Boxing.boxBoolean(true);
            }
        }, 1, null);
        Intrinsics.checkNotNull(receiveChannel1, "null cannot be cast to non-null type kotlinx.coroutines.channels.ReceiveChannel<E of kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.filterNotNull>");
        return receiveChannel1;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final Object filterNotNullTo(ReceiveChannel receiveChannel0, Collection collection0, Continuation continuation0) {
        Collection collection2;
        Throwable throwable1;
        Collection collection1;
        ChannelIterator channelIterator0;
        ReceiveChannel receiveChannel1;
        kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.filterNotNullTo.1 channelsKt__DeprecatedKt$filterNotNullTo$10;
        if(continuation0 instanceof kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.filterNotNullTo.1) {
            channelsKt__DeprecatedKt$filterNotNullTo$10 = (kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.filterNotNullTo.1)continuation0;
            if((channelsKt__DeprecatedKt$filterNotNullTo$10.label & 0x80000000) == 0) {
                channelsKt__DeprecatedKt$filterNotNullTo$10 = new ContinuationImpl(continuation0) {
                    Object L$0;
                    Object L$1;
                    Object L$2;
                    int label;
                    Object result;

                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object object0) {
                        this.result = object0;
                        this.label |= 0x80000000;
                        return ChannelsKt__DeprecatedKt.filterNotNullTo(null, null, this);
                    }
                };
            }
            else {
                channelsKt__DeprecatedKt$filterNotNullTo$10.label ^= 0x80000000;
            }
        }
        else {
            channelsKt__DeprecatedKt$filterNotNullTo$10 = new ContinuationImpl(continuation0) {
                Object L$0;
                Object L$1;
                Object L$2;
                int label;
                Object result;

                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object object0) {
                    this.result = object0;
                    this.label |= 0x80000000;
                    return ChannelsKt__DeprecatedKt.filterNotNullTo(null, null, this);
                }
            };
        }
        Object object0 = channelsKt__DeprecatedKt$filterNotNullTo$10.result;
        Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch(channelsKt__DeprecatedKt$filterNotNullTo$10.label) {
            case 0: {
                ResultKt.throwOnFailure(object0);
                try {
                    receiveChannel1 = receiveChannel0;
                    channelIterator0 = receiveChannel0.iterator();
                    collection1 = collection0;
                    goto label_29;
                }
                catch(Throwable throwable0) {
                    throwable1 = throwable0;
                    break;
                }
            }
            case 1: {
                channelIterator0 = (ChannelIterator)channelsKt__DeprecatedKt$filterNotNullTo$10.L$2;
                receiveChannel1 = (ReceiveChannel)channelsKt__DeprecatedKt$filterNotNullTo$10.L$1;
                collection2 = (Collection)channelsKt__DeprecatedKt$filterNotNullTo$10.L$0;
                try {
                    ResultKt.throwOnFailure(object0);
                    while(true) {
                        if(!((Boolean)object0).booleanValue()) {
                            goto label_44;
                        }
                        Object object2 = channelIterator0.next();
                        if(object2 != null) {
                            collection2.add(object2);
                        }
                        collection1 = collection2;
                    label_29:
                        channelsKt__DeprecatedKt$filterNotNullTo$10.L$0 = collection1;
                        channelsKt__DeprecatedKt$filterNotNullTo$10.L$1 = receiveChannel1;
                        channelsKt__DeprecatedKt$filterNotNullTo$10.L$2 = channelIterator0;
                        channelsKt__DeprecatedKt$filterNotNullTo$10.label = 1;
                        Object object3 = channelIterator0.hasNext(channelsKt__DeprecatedKt$filterNotNullTo$10);
                        if(object3 == object1) {
                            return object1;
                        }
                        collection2 = collection1;
                        object0 = object3;
                    }
                }
                catch(Throwable throwable1) {
                    break;
                }
            }
            default: {
                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
            }
        }
        try {
            throw throwable1;
        }
        catch(Throwable throwable2) {
            ChannelsKt.cancelConsumed(receiveChannel1, throwable1);
            throw throwable2;
        }
    label_44:
        ChannelsKt.cancelConsumed(receiveChannel1, null);
        return collection2;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final Object filterNotNullTo(ReceiveChannel receiveChannel0, SendChannel sendChannel0, Continuation continuation0) {
        Object object2;
        SendChannel sendChannel1;
        ReceiveChannel receiveChannel1;
        ChannelIterator channelIterator1;
        ChannelIterator channelIterator0;
        kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.filterNotNullTo.3 channelsKt__DeprecatedKt$filterNotNullTo$30;
        if(continuation0 instanceof kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.filterNotNullTo.3) {
            channelsKt__DeprecatedKt$filterNotNullTo$30 = (kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.filterNotNullTo.3)continuation0;
            if((channelsKt__DeprecatedKt$filterNotNullTo$30.label & 0x80000000) == 0) {
                channelsKt__DeprecatedKt$filterNotNullTo$30 = new ContinuationImpl(continuation0) {
                    Object L$0;
                    Object L$1;
                    Object L$2;
                    int label;
                    Object result;

                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object object0) {
                        this.result = object0;
                        this.label |= 0x80000000;
                        return ChannelsKt__DeprecatedKt.filterNotNullTo(null, null, this);
                    }
                };
            }
            else {
                channelsKt__DeprecatedKt$filterNotNullTo$30.label ^= 0x80000000;
            }
        }
        else {
            channelsKt__DeprecatedKt$filterNotNullTo$30 = new ContinuationImpl(continuation0) {
                Object L$0;
                Object L$1;
                Object L$2;
                int label;
                Object result;

                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object object0) {
                    this.result = object0;
                    this.label |= 0x80000000;
                    return ChannelsKt__DeprecatedKt.filterNotNullTo(null, null, this);
                }
            };
        }
        Object object0 = channelsKt__DeprecatedKt$filterNotNullTo$30.result;
        Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
    alab1:
        switch(channelsKt__DeprecatedKt$filterNotNullTo$30.label) {
            case 0: {
                ResultKt.throwOnFailure(object0);
                try {
                    channelIterator0 = receiveChannel0.iterator();
                    break;
                }
                catch(Throwable throwable0) {
                    receiveChannel1 = receiveChannel0;
                    throwable1 = throwable0;
                    throw throwable1;
                }
            }
            case 1: {
                channelIterator1 = (ChannelIterator)channelsKt__DeprecatedKt$filterNotNullTo$30.L$2;
                receiveChannel1 = (ReceiveChannel)channelsKt__DeprecatedKt$filterNotNullTo$30.L$1;
                sendChannel1 = (SendChannel)channelsKt__DeprecatedKt$filterNotNullTo$30.L$0;
                ResultKt.throwOnFailure(object0);
                goto label_42;
            }
            case 2: {
                channelIterator1 = (ChannelIterator)channelsKt__DeprecatedKt$filterNotNullTo$30.L$2;
                receiveChannel1 = (ReceiveChannel)channelsKt__DeprecatedKt$filterNotNullTo$30.L$1;
                sendChannel1 = (SendChannel)channelsKt__DeprecatedKt$filterNotNullTo$30.L$0;
                ResultKt.throwOnFailure(object0);
                while(true) {
                    while(true) {
                        channelIterator0 = channelIterator1;
                        receiveChannel0 = receiveChannel1;
                        sendChannel0 = sendChannel1;
                        break alab1;
                    }
                }
            }
            default: {
                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
            }
        }
        try {
            channelsKt__DeprecatedKt$filterNotNullTo$30.L$0 = sendChannel0;
            channelsKt__DeprecatedKt$filterNotNullTo$30.L$1 = receiveChannel0;
            channelsKt__DeprecatedKt$filterNotNullTo$30.L$2 = channelIterator0;
            channelsKt__DeprecatedKt$filterNotNullTo$30.label = 1;
            object2 = channelIterator0.hasNext(channelsKt__DeprecatedKt$filterNotNullTo$30);
        }
        catch(Throwable throwable0) {
            receiveChannel1 = receiveChannel0;
            throwable1 = throwable0;
            throw throwable1;
        }
        if(object2 == object1) {
            return object1;
        }
        receiveChannel1 = receiveChannel0;
        channelIterator1 = channelIterator0;
        object0 = object2;
        sendChannel1 = sendChannel0;
    label_42:
        if(((Boolean)object0).booleanValue()) {
            Object object3 = channelIterator1.next();
            if(object3 == null) {
                channelIterator0 = channelIterator1;
                receiveChannel0 = receiveChannel1;
                sendChannel0 = sendChannel1;
                break alab1;
            }
            channelsKt__DeprecatedKt$filterNotNullTo$30.L$0 = sendChannel1;
            channelsKt__DeprecatedKt$filterNotNullTo$30.L$1 = receiveChannel1;
            channelsKt__DeprecatedKt$filterNotNullTo$30.L$2 = channelIterator1;
            channelsKt__DeprecatedKt$filterNotNullTo$30.label = 2;
            if(sendChannel1.send(object3, channelsKt__DeprecatedKt$filterNotNullTo$30) != object1) {
                channelIterator0 = channelIterator1;
                receiveChannel0 = receiveChannel1;
                sendChannel0 = sendChannel1;
                break alab1;
            }
            return object1;
        }
        ChannelsKt.cancelConsumed(receiveChannel1, null);
        return sendChannel1;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final Object first(ReceiveChannel receiveChannel0, Continuation continuation0) {
        Object object3;
        ChannelIterator channelIterator1;
        Throwable throwable1;
        ReceiveChannel receiveChannel1;
        Object object2;
        ChannelIterator channelIterator0;
        kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.first.1 channelsKt__DeprecatedKt$first$10;
        if(continuation0 instanceof kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.first.1) {
            channelsKt__DeprecatedKt$first$10 = (kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.first.1)continuation0;
            if((channelsKt__DeprecatedKt$first$10.label & 0x80000000) == 0) {
                channelsKt__DeprecatedKt$first$10 = new ContinuationImpl(continuation0) {
                    Object L$0;
                    Object L$1;
                    int label;
                    Object result;

                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object object0) {
                        this.result = object0;
                        this.label |= 0x80000000;
                        return ChannelsKt__DeprecatedKt.first(null, this);
                    }
                };
            }
            else {
                channelsKt__DeprecatedKt$first$10.label ^= 0x80000000;
            }
        }
        else {
            channelsKt__DeprecatedKt$first$10 = new ContinuationImpl(continuation0) {
                Object L$0;
                Object L$1;
                int label;
                Object result;

                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object object0) {
                    this.result = object0;
                    this.label |= 0x80000000;
                    return ChannelsKt__DeprecatedKt.first(null, this);
                }
            };
        }
        Object object0 = channelsKt__DeprecatedKt$first$10.result;
        Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch(channelsKt__DeprecatedKt$first$10.label) {
            case 0: {
                ResultKt.throwOnFailure(object0);
                try {
                    channelIterator0 = receiveChannel0.iterator();
                    channelsKt__DeprecatedKt$first$10.L$0 = receiveChannel0;
                    channelsKt__DeprecatedKt$first$10.L$1 = channelIterator0;
                    channelsKt__DeprecatedKt$first$10.label = 1;
                    object2 = channelIterator0.hasNext(channelsKt__DeprecatedKt$first$10);
                }
                catch(Throwable throwable0) {
                    receiveChannel1 = receiveChannel0;
                    throwable1 = throwable0;
                    break;
                }
                if(object2 == object1) {
                    return object1;
                }
                receiveChannel1 = receiveChannel0;
                channelIterator1 = channelIterator0;
                object0 = object2;
                goto label_32;
            }
            case 1: {
                channelIterator1 = (ChannelIterator)channelsKt__DeprecatedKt$first$10.L$1;
                receiveChannel1 = (ReceiveChannel)channelsKt__DeprecatedKt$first$10.L$0;
                try {
                    ResultKt.throwOnFailure(object0);
                label_32:
                    if(!((Boolean)object0).booleanValue()) {
                        throw new NoSuchElementException("ReceiveChannel is empty.");
                    }
                    object3 = channelIterator1.next();
                    goto label_41;
                }
                catch(Throwable throwable1) {
                    break;
                }
            }
            default: {
                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
            }
        }
        try {
            throw throwable1;
        }
        catch(Throwable throwable2) {
            ChannelsKt.cancelConsumed(receiveChannel1, throwable1);
            throw throwable2;
        }
    label_41:
        ChannelsKt.cancelConsumed(receiveChannel1, null);
        return object3;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final Object firstOrNull(ReceiveChannel receiveChannel0, Continuation continuation0) {
        Object object3;
        ChannelIterator channelIterator1;
        Throwable throwable1;
        ReceiveChannel receiveChannel1;
        Object object2;
        ChannelIterator channelIterator0;
        kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.firstOrNull.1 channelsKt__DeprecatedKt$firstOrNull$10;
        if(continuation0 instanceof kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.firstOrNull.1) {
            channelsKt__DeprecatedKt$firstOrNull$10 = (kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.firstOrNull.1)continuation0;
            if((channelsKt__DeprecatedKt$firstOrNull$10.label & 0x80000000) == 0) {
                channelsKt__DeprecatedKt$firstOrNull$10 = new ContinuationImpl(continuation0) {
                    Object L$0;
                    Object L$1;
                    int label;
                    Object result;

                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object object0) {
                        this.result = object0;
                        this.label |= 0x80000000;
                        return ChannelsKt__DeprecatedKt.firstOrNull(null, this);
                    }
                };
            }
            else {
                channelsKt__DeprecatedKt$firstOrNull$10.label ^= 0x80000000;
            }
        }
        else {
            channelsKt__DeprecatedKt$firstOrNull$10 = new ContinuationImpl(continuation0) {
                Object L$0;
                Object L$1;
                int label;
                Object result;

                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object object0) {
                    this.result = object0;
                    this.label |= 0x80000000;
                    return ChannelsKt__DeprecatedKt.firstOrNull(null, this);
                }
            };
        }
        Object object0 = channelsKt__DeprecatedKt$firstOrNull$10.result;
        Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch(channelsKt__DeprecatedKt$firstOrNull$10.label) {
            case 0: {
                ResultKt.throwOnFailure(object0);
                try {
                    channelIterator0 = receiveChannel0.iterator();
                    channelsKt__DeprecatedKt$firstOrNull$10.L$0 = receiveChannel0;
                    channelsKt__DeprecatedKt$firstOrNull$10.L$1 = channelIterator0;
                    channelsKt__DeprecatedKt$firstOrNull$10.label = 1;
                    object2 = channelIterator0.hasNext(channelsKt__DeprecatedKt$firstOrNull$10);
                }
                catch(Throwable throwable0) {
                    receiveChannel1 = receiveChannel0;
                    throwable1 = throwable0;
                    break;
                }
                if(object2 == object1) {
                    return object1;
                }
                receiveChannel1 = receiveChannel0;
                channelIterator1 = channelIterator0;
                object0 = object2;
                goto label_32;
            }
            case 1: {
                channelIterator1 = (ChannelIterator)channelsKt__DeprecatedKt$firstOrNull$10.L$1;
                receiveChannel1 = (ReceiveChannel)channelsKt__DeprecatedKt$firstOrNull$10.L$0;
                try {
                    ResultKt.throwOnFailure(object0);
                label_32:
                    if(((Boolean)object0).booleanValue()) {
                        object3 = channelIterator1.next();
                        goto label_40;
                    }
                    goto label_42;
                }
                catch(Throwable throwable1) {
                    break;
                }
            }
            default: {
                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
            }
        }
        try {
            throw throwable1;
        }
        catch(Throwable throwable2) {
            ChannelsKt.cancelConsumed(receiveChannel1, throwable1);
            throw throwable2;
        }
    label_40:
        ChannelsKt.cancelConsumed(receiveChannel1, null);
        return object3;
    label_42:
        ChannelsKt.cancelConsumed(receiveChannel1, null);
        return null;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final ReceiveChannel flatMap(ReceiveChannel receiveChannel0, CoroutineContext coroutineContext0, Function2 function20) {
        Function1 function10 = ChannelsKt.consumes(receiveChannel0);
        kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.flatMap.1 channelsKt__DeprecatedKt$flatMap$10 = new Function2(receiveChannel0, function20, null) {
            final ReceiveChannel $this_flatMap;
            final Function2 $transform;
            private Object L$0;
            Object L$1;
            int label;

            {
                this.$this_flatMap = receiveChannel0;
                this.$transform = function20;
                super(2, continuation0);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object object0, Continuation continuation0) {
                kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.flatMap.1 channelsKt__DeprecatedKt$flatMap$10 = new kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.flatMap.1(this.$this_flatMap, this.$transform, continuation0);
                channelsKt__DeprecatedKt$flatMap$10.L$0 = object0;
                return channelsKt__DeprecatedKt$flatMap$10;
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(((ProducerScope)object0), ((Continuation)object1));
            }

            public final Object invoke(ProducerScope producerScope0, Continuation continuation0) {
                return ((kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.flatMap.1)this.create(producerScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object object0) {
                ProducerScope producerScope1;
                ChannelIterator channelIterator0;
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(this.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        ProducerScope producerScope0 = (ProducerScope)this.L$0;
                        channelIterator0 = this.$this_flatMap.iterator();
                        producerScope1 = producerScope0;
                        break;
                    }
                    case 1: {
                        channelIterator0 = (ChannelIterator)this.L$1;
                        producerScope1 = (ProducerScope)this.L$0;
                        ResultKt.throwOnFailure(object0);
                        goto label_24;
                    }
                    case 2: {
                        channelIterator0 = (ChannelIterator)this.L$1;
                        producerScope1 = (ProducerScope)this.L$0;
                        ResultKt.throwOnFailure(object0);
                        goto label_31;
                    }
                    case 3: {
                        channelIterator0 = (ChannelIterator)this.L$1;
                        producerScope1 = (ProducerScope)this.L$0;
                        ResultKt.throwOnFailure(object0);
                        break;
                    }
                    default: {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    }
                }
                do {
                    this.L$0 = producerScope1;
                    this.L$1 = channelIterator0;
                    this.label = 1;
                    object0 = channelIterator0.hasNext(this);
                    if(object0 == object1) {
                        return object1;
                    }
                label_24:
                    if(!((Boolean)object0).booleanValue()) {
                        return Unit.INSTANCE;
                    }
                    Object object2 = channelIterator0.next();
                    this.L$0 = producerScope1;
                    this.L$1 = channelIterator0;
                    this.label = 2;
                    object0 = this.$transform.invoke(object2, this);
                    if(object0 == object1) {
                        return object1;
                    }
                label_31:
                    this.L$0 = producerScope1;
                    this.L$1 = channelIterator0;
                    this.label = 3;
                }
                while(ChannelsKt.toChannel(((ReceiveChannel)object0), producerScope1, this) != object1);
                return object1;
            }
        };
        return ProduceKt.produce$default(() -> EmptyCoroutineContext.INSTANCE, coroutineContext0, 0, null, function10, channelsKt__DeprecatedKt$flatMap$10, 6, null);
    }

    public static ReceiveChannel flatMap$default(ReceiveChannel receiveChannel0, CoroutineContext coroutineContext0, Function2 function20, int v, Object object0) {
        if((v & 1) != 0) {
            coroutineContext0 = Dispatchers.getUnconfined();
        }
        return ChannelsKt__DeprecatedKt.flatMap(receiveChannel0, coroutineContext0, function20);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final Object indexOf(ReceiveChannel receiveChannel0, Object object0, Continuation continuation0) {
        Integer integer0;
        Throwable throwable1;
        Object object3;
        IntRef ref$IntRef1;
        ChannelIterator channelIterator0;
        ReceiveChannel receiveChannel1;
        kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.indexOf.1 channelsKt__DeprecatedKt$indexOf$10;
        if(continuation0 instanceof kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.indexOf.1) {
            channelsKt__DeprecatedKt$indexOf$10 = (kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.indexOf.1)continuation0;
            if((channelsKt__DeprecatedKt$indexOf$10.label & 0x80000000) == 0) {
                channelsKt__DeprecatedKt$indexOf$10 = new ContinuationImpl(continuation0) {
                    Object L$0;
                    Object L$1;
                    Object L$2;
                    Object L$3;
                    int label;
                    Object result;

                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object object0) {
                        this.result = object0;
                        this.label |= 0x80000000;
                        return ChannelsKt__DeprecatedKt.indexOf(null, null, this);
                    }
                };
            }
            else {
                channelsKt__DeprecatedKt$indexOf$10.label ^= 0x80000000;
            }
        }
        else {
            channelsKt__DeprecatedKt$indexOf$10 = new ContinuationImpl(continuation0) {
                Object L$0;
                Object L$1;
                Object L$2;
                Object L$3;
                int label;
                Object result;

                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object object0) {
                    this.result = object0;
                    this.label |= 0x80000000;
                    return ChannelsKt__DeprecatedKt.indexOf(null, null, this);
                }
            };
        }
        Object object1 = channelsKt__DeprecatedKt$indexOf$10.result;
        Object object2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch(channelsKt__DeprecatedKt$indexOf$10.label) {
            case 0: {
                ResultKt.throwOnFailure(object1);
                IntRef ref$IntRef0 = new IntRef();
                try {
                    receiveChannel1 = receiveChannel0;
                    channelIterator0 = receiveChannel0.iterator();
                    ref$IntRef1 = ref$IntRef0;
                    object3 = object0;
                    goto label_31;
                }
                catch(Throwable throwable0) {
                    throwable1 = throwable0;
                    break;
                }
            }
            case 1: {
                channelIterator0 = (ChannelIterator)channelsKt__DeprecatedKt$indexOf$10.L$3;
                receiveChannel1 = (ReceiveChannel)channelsKt__DeprecatedKt$indexOf$10.L$2;
                ref$IntRef1 = (IntRef)channelsKt__DeprecatedKt$indexOf$10.L$1;
                Object object4 = channelsKt__DeprecatedKt$indexOf$10.L$0;
                try {
                    ResultKt.throwOnFailure(object1);
                    while(true) {
                        if(!((Boolean)object1).booleanValue()) {
                            goto label_51;
                        }
                        if(!Intrinsics.areEqual(object4, channelIterator0.next())) {
                            ++ref$IntRef1.element;
                            object3 = object4;
                        label_31:
                            channelsKt__DeprecatedKt$indexOf$10.L$0 = object3;
                            channelsKt__DeprecatedKt$indexOf$10.L$1 = ref$IntRef1;
                            channelsKt__DeprecatedKt$indexOf$10.L$2 = receiveChannel1;
                            channelsKt__DeprecatedKt$indexOf$10.L$3 = channelIterator0;
                            channelsKt__DeprecatedKt$indexOf$10.label = 1;
                            Object object5 = channelIterator0.hasNext(channelsKt__DeprecatedKt$indexOf$10);
                            if(object5 == object2) {
                                return object2;
                            }
                            object4 = object3;
                            object1 = object5;
                            continue;
                        }
                        integer0 = Boxing.boxInt(ref$IntRef1.element);
                        goto label_49;
                    }
                }
                catch(Throwable throwable1) {
                    break;
                }
            }
            default: {
                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
            }
        }
        try {
            throw throwable1;
        }
        catch(Throwable throwable2) {
            ChannelsKt.cancelConsumed(receiveChannel1, throwable1);
            throw throwable2;
        }
    label_49:
        ChannelsKt.cancelConsumed(receiveChannel1, null);
        return integer0;
    label_51:
        ChannelsKt.cancelConsumed(receiveChannel1, null);
        return Boxing.boxInt(-1);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final Object last(ReceiveChannel receiveChannel0, Continuation continuation0) {
        Object object5;
        ReceiveChannel receiveChannel2;
        Object object4;
        ChannelIterator channelIterator2;
        Object object3;
        ChannelIterator channelIterator1;
        ReceiveChannel receiveChannel1;
        Object object2;
        ChannelIterator channelIterator0;
        kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.last.1 channelsKt__DeprecatedKt$last$10;
        if(continuation0 instanceof kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.last.1) {
            channelsKt__DeprecatedKt$last$10 = (kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.last.1)continuation0;
            if((channelsKt__DeprecatedKt$last$10.label & 0x80000000) == 0) {
                channelsKt__DeprecatedKt$last$10 = new ContinuationImpl(continuation0) {
                    Object L$0;
                    Object L$1;
                    Object L$2;
                    int label;
                    Object result;

                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object object0) {
                        this.result = object0;
                        this.label |= 0x80000000;
                        return ChannelsKt__DeprecatedKt.last(null, this);
                    }
                };
            }
            else {
                channelsKt__DeprecatedKt$last$10.label ^= 0x80000000;
            }
        }
        else {
            channelsKt__DeprecatedKt$last$10 = new ContinuationImpl(continuation0) {
                Object L$0;
                Object L$1;
                Object L$2;
                int label;
                Object result;

                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object object0) {
                    this.result = object0;
                    this.label |= 0x80000000;
                    return ChannelsKt__DeprecatedKt.last(null, this);
                }
            };
        }
        Object object0 = channelsKt__DeprecatedKt$last$10.result;
        Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
    alab1:
        switch(channelsKt__DeprecatedKt$last$10.label) {
            case 0: {
                ResultKt.throwOnFailure(object0);
                try {
                    channelIterator0 = receiveChannel0.iterator();
                    channelsKt__DeprecatedKt$last$10.L$0 = receiveChannel0;
                    channelsKt__DeprecatedKt$last$10.L$1 = channelIterator0;
                    channelsKt__DeprecatedKt$last$10.label = 1;
                    object2 = channelIterator0.hasNext(channelsKt__DeprecatedKt$last$10);
                    if(object2 != object1) {
                        receiveChannel1 = receiveChannel0;
                        channelIterator1 = channelIterator0;
                        object0 = object2;
                        goto label_26;
                    }
                    return object1;
                }
                catch(Throwable throwable0) {
                    receiveChannel1 = receiveChannel0;
                    throwable1 = throwable0;
                    throw throwable1;
                }
                receiveChannel1 = receiveChannel0;
                channelIterator1 = channelIterator0;
                object0 = object2;
                goto label_26;
            }
            case 1: {
                channelIterator1 = (ChannelIterator)channelsKt__DeprecatedKt$last$10.L$1;
                receiveChannel1 = (ReceiveChannel)channelsKt__DeprecatedKt$last$10.L$0;
                ResultKt.throwOnFailure(object0);
            label_26:
                if(!((Boolean)object0).booleanValue()) {
                    throw new NoSuchElementException("ReceiveChannel is empty.");
                }
                object3 = channelIterator1.next();
                channelIterator2 = channelIterator1;
                receiveChannel0 = receiveChannel1;
                break;
            }
            case 2: {
                object4 = channelsKt__DeprecatedKt$last$10.L$2;
                channelIterator2 = (ChannelIterator)channelsKt__DeprecatedKt$last$10.L$1;
                receiveChannel2 = (ReceiveChannel)channelsKt__DeprecatedKt$last$10.L$0;
                try {
                    ResultKt.throwOnFailure(object0);
                    while(true) {
                    label_38:
                        if(!((Boolean)object0).booleanValue()) {
                            goto label_63;
                        }
                        object3 = channelIterator2.next();
                        receiveChannel0 = receiveChannel2;
                        break alab1;
                    }
                }
                catch(Throwable throwable1) {
                    goto label_58;
                }
            }
            default: {
                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
            }
        }
        try {
            channelsKt__DeprecatedKt$last$10.L$0 = receiveChannel0;
            channelsKt__DeprecatedKt$last$10.L$1 = channelIterator2;
            channelsKt__DeprecatedKt$last$10.L$2 = object3;
            channelsKt__DeprecatedKt$last$10.label = 2;
            object5 = channelIterator2.hasNext(channelsKt__DeprecatedKt$last$10);
        }
        catch(Throwable throwable0) {
            receiveChannel1 = receiveChannel0;
            throwable1 = throwable0;
            throw throwable1;
        }
        if(object5 == object1) {
            return object1;
        }
        try {
            receiveChannel2 = receiveChannel0;
            object4 = object3;
            object0 = object5;
            goto label_38;
        }
        catch(Throwable throwable1) {
        label_58:
            receiveChannel1 = receiveChannel2;
        }
        try {
            throw throwable1;
        }
        catch(Throwable throwable2) {
            ChannelsKt.cancelConsumed(receiveChannel1, throwable1);
            throw throwable2;
        }
    label_63:
        ChannelsKt.cancelConsumed(receiveChannel2, null);
        return object4;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final Object lastIndexOf(ReceiveChannel receiveChannel0, Object object0, Continuation continuation0) {
        Throwable throwable1;
        Object object3;
        IntRef ref$IntRef2;
        ChannelIterator channelIterator0;
        ReceiveChannel receiveChannel1;
        IntRef ref$IntRef1;
        kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.lastIndexOf.1 channelsKt__DeprecatedKt$lastIndexOf$10;
        if(continuation0 instanceof kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.lastIndexOf.1) {
            channelsKt__DeprecatedKt$lastIndexOf$10 = (kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.lastIndexOf.1)continuation0;
            if((channelsKt__DeprecatedKt$lastIndexOf$10.label & 0x80000000) == 0) {
                channelsKt__DeprecatedKt$lastIndexOf$10 = new ContinuationImpl(continuation0) {
                    Object L$0;
                    Object L$1;
                    Object L$2;
                    Object L$3;
                    Object L$4;
                    int label;
                    Object result;

                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object object0) {
                        this.result = object0;
                        this.label |= 0x80000000;
                        return ChannelsKt__DeprecatedKt.lastIndexOf(null, null, this);
                    }
                };
            }
            else {
                channelsKt__DeprecatedKt$lastIndexOf$10.label ^= 0x80000000;
            }
        }
        else {
            channelsKt__DeprecatedKt$lastIndexOf$10 = new ContinuationImpl(continuation0) {
                Object L$0;
                Object L$1;
                Object L$2;
                Object L$3;
                Object L$4;
                int label;
                Object result;

                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object object0) {
                    this.result = object0;
                    this.label |= 0x80000000;
                    return ChannelsKt__DeprecatedKt.lastIndexOf(null, null, this);
                }
            };
        }
        Object object1 = channelsKt__DeprecatedKt$lastIndexOf$10.result;
        Object object2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch(channelsKt__DeprecatedKt$lastIndexOf$10.label) {
            case 0: {
                ResultKt.throwOnFailure(object1);
                IntRef ref$IntRef0 = new IntRef();
                ref$IntRef0.element = -1;
                ref$IntRef1 = new IntRef();
                try {
                    receiveChannel1 = receiveChannel0;
                    channelIterator0 = receiveChannel0.iterator();
                    ref$IntRef2 = ref$IntRef0;
                    object3 = object0;
                    goto label_35;
                }
                catch(Throwable throwable0) {
                    throwable1 = throwable0;
                    break;
                }
            }
            case 1: {
                channelIterator0 = (ChannelIterator)channelsKt__DeprecatedKt$lastIndexOf$10.L$4;
                receiveChannel1 = (ReceiveChannel)channelsKt__DeprecatedKt$lastIndexOf$10.L$3;
                ref$IntRef1 = (IntRef)channelsKt__DeprecatedKt$lastIndexOf$10.L$2;
                ref$IntRef2 = (IntRef)channelsKt__DeprecatedKt$lastIndexOf$10.L$1;
                Object object4 = channelsKt__DeprecatedKt$lastIndexOf$10.L$0;
                try {
                    ResultKt.throwOnFailure(object1);
                    while(true) {
                        if(!((Boolean)object1).booleanValue()) {
                            goto label_52;
                        }
                        if(Intrinsics.areEqual(object4, channelIterator0.next())) {
                            ref$IntRef2.element = ref$IntRef1.element;
                        }
                        ++ref$IntRef1.element;
                        object3 = object4;
                    label_35:
                        channelsKt__DeprecatedKt$lastIndexOf$10.L$0 = object3;
                        channelsKt__DeprecatedKt$lastIndexOf$10.L$1 = ref$IntRef2;
                        channelsKt__DeprecatedKt$lastIndexOf$10.L$2 = ref$IntRef1;
                        channelsKt__DeprecatedKt$lastIndexOf$10.L$3 = receiveChannel1;
                        channelsKt__DeprecatedKt$lastIndexOf$10.L$4 = channelIterator0;
                        channelsKt__DeprecatedKt$lastIndexOf$10.label = 1;
                        Object object5 = channelIterator0.hasNext(channelsKt__DeprecatedKt$lastIndexOf$10);
                        if(object5 == object2) {
                            return object2;
                        }
                        object4 = object3;
                        object1 = object5;
                    }
                }
                catch(Throwable throwable1) {
                    break;
                }
            }
            default: {
                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
            }
        }
        try {
            throw throwable1;
        }
        catch(Throwable throwable2) {
            ChannelsKt.cancelConsumed(receiveChannel1, throwable1);
            throw throwable2;
        }
    label_52:
        ChannelsKt.cancelConsumed(receiveChannel1, null);
        return Boxing.boxInt(ref$IntRef2.element);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final Object lastOrNull(ReceiveChannel receiveChannel0, Continuation continuation0) {
        Object object5;
        ReceiveChannel receiveChannel2;
        Object object4;
        ChannelIterator channelIterator2;
        Object object3;
        ChannelIterator channelIterator1;
        ReceiveChannel receiveChannel1;
        Object object2;
        ChannelIterator channelIterator0;
        kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.lastOrNull.1 channelsKt__DeprecatedKt$lastOrNull$10;
        if(continuation0 instanceof kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.lastOrNull.1) {
            channelsKt__DeprecatedKt$lastOrNull$10 = (kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.lastOrNull.1)continuation0;
            if((channelsKt__DeprecatedKt$lastOrNull$10.label & 0x80000000) == 0) {
                channelsKt__DeprecatedKt$lastOrNull$10 = new ContinuationImpl(continuation0) {
                    Object L$0;
                    Object L$1;
                    Object L$2;
                    int label;
                    Object result;

                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object object0) {
                        this.result = object0;
                        this.label |= 0x80000000;
                        return ChannelsKt__DeprecatedKt.lastOrNull(null, this);
                    }
                };
            }
            else {
                channelsKt__DeprecatedKt$lastOrNull$10.label ^= 0x80000000;
            }
        }
        else {
            channelsKt__DeprecatedKt$lastOrNull$10 = new ContinuationImpl(continuation0) {
                Object L$0;
                Object L$1;
                Object L$2;
                int label;
                Object result;

                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object object0) {
                    this.result = object0;
                    this.label |= 0x80000000;
                    return ChannelsKt__DeprecatedKt.lastOrNull(null, this);
                }
            };
        }
        Object object0 = channelsKt__DeprecatedKt$lastOrNull$10.result;
        Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
    alab1:
        switch(channelsKt__DeprecatedKt$lastOrNull$10.label) {
            case 0: {
                ResultKt.throwOnFailure(object0);
                try {
                    channelIterator0 = receiveChannel0.iterator();
                    channelsKt__DeprecatedKt$lastOrNull$10.L$0 = receiveChannel0;
                    channelsKt__DeprecatedKt$lastOrNull$10.L$1 = channelIterator0;
                    channelsKt__DeprecatedKt$lastOrNull$10.label = 1;
                    object2 = channelIterator0.hasNext(channelsKt__DeprecatedKt$lastOrNull$10);
                    if(object2 != object1) {
                        receiveChannel1 = receiveChannel0;
                        channelIterator1 = channelIterator0;
                        object0 = object2;
                        goto label_26;
                    }
                    return object1;
                }
                catch(Throwable throwable0) {
                    receiveChannel1 = receiveChannel0;
                    throwable1 = throwable0;
                    throw throwable1;
                }
                receiveChannel1 = receiveChannel0;
                channelIterator1 = channelIterator0;
                object0 = object2;
                goto label_26;
            }
            case 1: {
                channelIterator1 = (ChannelIterator)channelsKt__DeprecatedKt$lastOrNull$10.L$1;
                receiveChannel1 = (ReceiveChannel)channelsKt__DeprecatedKt$lastOrNull$10.L$0;
                ResultKt.throwOnFailure(object0);
            label_26:
                if(((Boolean)object0).booleanValue()) {
                    object3 = channelIterator1.next();
                    channelIterator2 = channelIterator1;
                    receiveChannel0 = receiveChannel1;
                    break;
                }
                ChannelsKt.cancelConsumed(receiveChannel1, null);
                return null;
            }
            case 2: {
                object4 = channelsKt__DeprecatedKt$lastOrNull$10.L$2;
                channelIterator2 = (ChannelIterator)channelsKt__DeprecatedKt$lastOrNull$10.L$1;
                receiveChannel2 = (ReceiveChannel)channelsKt__DeprecatedKt$lastOrNull$10.L$0;
                try {
                    ResultKt.throwOnFailure(object0);
                    while(true) {
                    label_39:
                        if(!((Boolean)object0).booleanValue()) {
                            goto label_64;
                        }
                        object3 = channelIterator2.next();
                        receiveChannel0 = receiveChannel2;
                        break alab1;
                    }
                }
                catch(Throwable throwable1) {
                    goto label_59;
                }
            }
            default: {
                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
            }
        }
        try {
            channelsKt__DeprecatedKt$lastOrNull$10.L$0 = receiveChannel0;
            channelsKt__DeprecatedKt$lastOrNull$10.L$1 = channelIterator2;
            channelsKt__DeprecatedKt$lastOrNull$10.L$2 = object3;
            channelsKt__DeprecatedKt$lastOrNull$10.label = 2;
            object5 = channelIterator2.hasNext(channelsKt__DeprecatedKt$lastOrNull$10);
        }
        catch(Throwable throwable0) {
            receiveChannel1 = receiveChannel0;
            throwable1 = throwable0;
            throw throwable1;
        }
        if(object5 == object1) {
            return object1;
        }
        try {
            receiveChannel2 = receiveChannel0;
            object4 = object3;
            object0 = object5;
            goto label_39;
        }
        catch(Throwable throwable1) {
        label_59:
            receiveChannel1 = receiveChannel2;
        }
        try {
            throw throwable1;
        }
        catch(Throwable throwable2) {
            ChannelsKt.cancelConsumed(receiveChannel1, throwable1);
            throw throwable2;
        }
    label_64:
        ChannelsKt.cancelConsumed(receiveChannel2, null);
        return object4;
    }

    public static final ReceiveChannel map(ReceiveChannel receiveChannel0, CoroutineContext coroutineContext0, Function2 function20) {
        Function1 function10 = ChannelsKt.consumes(receiveChannel0);
        kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.map.1 channelsKt__DeprecatedKt$map$10 = new Function2(receiveChannel0, function20, null) {
            final ReceiveChannel $this_map;
            final Function2 $transform;
            private Object L$0;
            Object L$1;
            Object L$2;
            Object L$3;
            Object L$4;
            int label;

            {
                this.$this_map = receiveChannel0;
                this.$transform = function20;
                super(2, continuation0);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object object0, Continuation continuation0) {
                kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.map.1 channelsKt__DeprecatedKt$map$10 = new kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.map.1(this.$this_map, this.$transform, continuation0);
                channelsKt__DeprecatedKt$map$10.L$0 = object0;
                return channelsKt__DeprecatedKt$map$10;
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(((ProducerScope)object0), ((Continuation)object1));
            }

            public final Object invoke(ProducerScope producerScope0, Continuation continuation0) {
                return ((kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.map.1)this.create(producerScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object object0) {
                Throwable throwable3;
                Throwable throwable2;
                ProducerScope producerScope3;
                Function2 function21;
                ReceiveChannel receiveChannel1;
                ChannelIterator channelIterator2;
                ProducerScope producerScope2;
                Throwable throwable1;
                ProducerScope producerScope1;
                ChannelIterator channelIterator1;
                Function2 function20;
                ReceiveChannel receiveChannel0;
                ProducerScope producerScope0;
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            alab1:
                switch(this.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        producerScope0 = (ProducerScope)this.L$0;
                        receiveChannel0 = this.$this_map;
                        ChannelIterator channelIterator0 = receiveChannel0.iterator();
                        function20 = this.$transform;
                        channelIterator1 = channelIterator0;
                        goto label_31;
                    }
                    case 1: {
                        channelIterator1 = (ChannelIterator)this.L$3;
                        receiveChannel0 = (ReceiveChannel)this.L$2;
                        function20 = (Function2)this.L$1;
                        producerScope1 = (ProducerScope)this.L$0;
                        ResultKt.throwOnFailure(object0);
                        throwable1 = null;
                        goto label_42;
                    }
                    case 2: {
                        producerScope2 = (ProducerScope)this.L$4;
                        channelIterator2 = (ChannelIterator)this.L$3;
                        receiveChannel1 = (ReceiveChannel)this.L$2;
                        function21 = (Function2)this.L$1;
                        producerScope3 = (ProducerScope)this.L$0;
                        try {
                            ResultKt.throwOnFailure(object0);
                            throwable2 = null;
                            goto label_59;
                        }
                        catch(Throwable throwable0) {
                            receiveChannel0 = receiveChannel1;
                            throw throwable0;
                        }
                    }
                    case 3: {
                        channelIterator1 = (ChannelIterator)this.L$3;
                        receiveChannel0 = (ReceiveChannel)this.L$2;
                        function20 = (Function2)this.L$1;
                        ProducerScope producerScope4 = (ProducerScope)this.L$0;
                        ResultKt.throwOnFailure(object0);
                        producerScope0 = producerScope4;
                    label_31:
                        throwable3 = null;
                        while(true) {
                        label_32:
                            this.L$0 = producerScope0;
                            this.L$1 = function20;
                            this.L$2 = receiveChannel0;
                            this.L$3 = channelIterator1;
                            this.label = 1;
                            Object object2 = channelIterator1.hasNext(this);
                            if(object2 != object1) {
                                producerScope1 = producerScope0;
                                object0 = object2;
                                throwable1 = throwable3;
                            label_42:
                                if(!((Boolean)object0).booleanValue()) {
                                    ChannelsKt.cancelConsumed(receiveChannel0, throwable1);
                                    return Unit.INSTANCE;
                                }
                                Object object3 = channelIterator1.next();
                                this.L$0 = producerScope1;
                                this.L$1 = function20;
                                this.L$2 = receiveChannel0;
                                this.L$3 = channelIterator1;
                                this.L$4 = producerScope1;
                                this.label = 2;
                                object0 = function20.invoke(object3, this);
                                if(object0 == object1) {
                                    return object1;
                                }
                                break alab1;
                            }
                            return object1;
                        }
                    }
                    default: {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    }
                }
                throwable2 = throwable1;
                producerScope3 = producerScope1;
                function21 = function20;
                receiveChannel1 = receiveChannel0;
                channelIterator2 = channelIterator1;
                producerScope2 = producerScope3;
                try {
                label_59:
                    this.L$0 = producerScope3;
                    this.L$1 = function21;
                    this.L$2 = receiveChannel1;
                    this.L$3 = channelIterator2;
                    this.L$4 = null;
                    this.label = 3;
                    if(producerScope2.send(object0, this) == object1) {
                        return object1;
                    }
                }
                catch(Throwable throwable0) {
                    receiveChannel0 = receiveChannel1;
                    throw throwable0;
                }
                try {
                    channelIterator1 = channelIterator2;
                    receiveChannel0 = receiveChannel1;
                    function20 = function21;
                    producerScope0 = producerScope3;
                    throwable3 = throwable2;
                    goto label_32;
                }
                catch(Throwable throwable0) {
                }
                try {
                    throw throwable0;
                }
                catch(Throwable throwable4) {
                    ChannelsKt.cancelConsumed(receiveChannel0, throwable0);
                    throw throwable4;
                }
                ChannelsKt.cancelConsumed(receiveChannel0, throwable1);
                return Unit.INSTANCE;
            }
        };
        return ProduceKt.produce$default(() -> EmptyCoroutineContext.INSTANCE, coroutineContext0, 0, null, function10, channelsKt__DeprecatedKt$map$10, 6, null);
    }

    public static ReceiveChannel map$default(ReceiveChannel receiveChannel0, CoroutineContext coroutineContext0, Function2 function20, int v, Object object0) {
        if((v & 1) != 0) {
            coroutineContext0 = Dispatchers.getUnconfined();
        }
        return ChannelsKt.map(receiveChannel0, coroutineContext0, function20);
    }

    public static final ReceiveChannel mapIndexed(ReceiveChannel receiveChannel0, CoroutineContext coroutineContext0, Function3 function30) {
        Function1 function10 = ChannelsKt.consumes(receiveChannel0);
        kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.mapIndexed.1 channelsKt__DeprecatedKt$mapIndexed$10 = new Function2(receiveChannel0, function30, null) {
            final ReceiveChannel $this_mapIndexed;
            final Function3 $transform;
            int I$0;
            private Object L$0;
            Object L$1;
            Object L$2;
            int label;

            {
                this.$this_mapIndexed = receiveChannel0;
                this.$transform = function30;
                super(2, continuation0);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object object0, Continuation continuation0) {
                kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.mapIndexed.1 channelsKt__DeprecatedKt$mapIndexed$10 = new kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.mapIndexed.1(this.$this_mapIndexed, this.$transform, continuation0);
                channelsKt__DeprecatedKt$mapIndexed$10.L$0 = object0;
                return channelsKt__DeprecatedKt$mapIndexed$10;
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(((ProducerScope)object0), ((Continuation)object1));
            }

            public final Object invoke(ProducerScope producerScope0, Continuation continuation0) {
                return ((kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.mapIndexed.1)this.create(producerScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object object0) {
                ProducerScope producerScope3;
                ChannelIterator channelIterator1;
                ProducerScope producerScope2;
                ProducerScope producerScope1;
                int v;
                ChannelIterator channelIterator0;
                ProducerScope producerScope0;
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(this.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        producerScope0 = (ProducerScope)this.L$0;
                        channelIterator0 = this.$this_mapIndexed.iterator();
                        v = 0;
                        break;
                    }
                    case 1: {
                        v = this.I$0;
                        channelIterator0 = (ChannelIterator)this.L$1;
                        producerScope1 = (ProducerScope)this.L$0;
                        ResultKt.throwOnFailure(object0);
                        goto label_32;
                    }
                    case 2: {
                        v = this.I$0;
                        producerScope2 = (ProducerScope)this.L$2;
                        channelIterator1 = (ChannelIterator)this.L$1;
                        producerScope3 = (ProducerScope)this.L$0;
                        ResultKt.throwOnFailure(object0);
                        goto label_45;
                    }
                    case 3: {
                        v = this.I$0;
                        channelIterator0 = (ChannelIterator)this.L$1;
                        ProducerScope producerScope4 = (ProducerScope)this.L$0;
                        ResultKt.throwOnFailure(object0);
                        producerScope0 = producerScope4;
                        break;
                    }
                    default: {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    }
                }
                while(true) {
                    this.L$0 = producerScope0;
                    this.L$1 = channelIterator0;
                    this.I$0 = v;
                    this.label = 1;
                    Object object2 = channelIterator0.hasNext(this);
                    if(object2 == object1) {
                        break;
                    }
                    producerScope1 = producerScope0;
                    object0 = object2;
                label_32:
                    if(((Boolean)object0).booleanValue()) {
                        Object object3 = channelIterator0.next();
                        this.L$0 = producerScope1;
                        this.L$1 = channelIterator0;
                        this.L$2 = producerScope1;
                        this.I$0 = v + 1;
                        this.label = 2;
                        object0 = this.$transform.invoke(Boxing.boxInt(v), object3, this);
                        if(object0 == object1) {
                            break;
                        }
                        producerScope3 = producerScope1;
                        ++v;
                        channelIterator1 = channelIterator0;
                        producerScope2 = producerScope3;
                    label_45:
                        this.L$0 = producerScope3;
                        this.L$1 = channelIterator1;
                        this.L$2 = null;
                        this.I$0 = v;
                        this.label = 3;
                        if(producerScope2.send(object0, this) == object1) {
                            break;
                        }
                        channelIterator0 = channelIterator1;
                        producerScope0 = producerScope3;
                        continue;
                    }
                    return Unit.INSTANCE;
                }
                return object1;
            }
        };
        return ProduceKt.produce$default(() -> EmptyCoroutineContext.INSTANCE, coroutineContext0, 0, null, function10, channelsKt__DeprecatedKt$mapIndexed$10, 6, null);
    }

    public static ReceiveChannel mapIndexed$default(ReceiveChannel receiveChannel0, CoroutineContext coroutineContext0, Function3 function30, int v, Object object0) {
        if((v & 1) != 0) {
            coroutineContext0 = Dispatchers.getUnconfined();
        }
        return ChannelsKt.mapIndexed(receiveChannel0, coroutineContext0, function30);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final ReceiveChannel mapIndexedNotNull(ReceiveChannel receiveChannel0, CoroutineContext coroutineContext0, Function3 function30) {
        return ChannelsKt.filterNotNull(ChannelsKt.mapIndexed(receiveChannel0, coroutineContext0, function30));
    }

    public static ReceiveChannel mapIndexedNotNull$default(ReceiveChannel receiveChannel0, CoroutineContext coroutineContext0, Function3 function30, int v, Object object0) {
        if((v & 1) != 0) {
            coroutineContext0 = Dispatchers.getUnconfined();
        }
        return ChannelsKt__DeprecatedKt.mapIndexedNotNull(receiveChannel0, coroutineContext0, function30);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final ReceiveChannel mapNotNull(ReceiveChannel receiveChannel0, CoroutineContext coroutineContext0, Function2 function20) {
        return ChannelsKt.filterNotNull(ChannelsKt.map(receiveChannel0, coroutineContext0, function20));
    }

    public static ReceiveChannel mapNotNull$default(ReceiveChannel receiveChannel0, CoroutineContext coroutineContext0, Function2 function20, int v, Object object0) {
        if((v & 1) != 0) {
            coroutineContext0 = Dispatchers.getUnconfined();
        }
        return ChannelsKt__DeprecatedKt.mapNotNull(receiveChannel0, coroutineContext0, function20);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final Object maxWith(ReceiveChannel receiveChannel0, Comparator comparator0, Continuation continuation0) {
        Object object5;
        kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.maxWith.1 channelsKt__DeprecatedKt$maxWith$11;
        Comparator comparator2;
        ChannelIterator channelIterator2;
        Object object3;
        Comparator comparator1;
        ChannelIterator channelIterator1;
        ReceiveChannel receiveChannel1;
        kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.maxWith.1 channelsKt__DeprecatedKt$maxWith$10;
        if(continuation0 instanceof kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.maxWith.1) {
            channelsKt__DeprecatedKt$maxWith$10 = (kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.maxWith.1)continuation0;
            if((channelsKt__DeprecatedKt$maxWith$10.label & 0x80000000) == 0) {
                channelsKt__DeprecatedKt$maxWith$10 = new ContinuationImpl(continuation0) {
                    Object L$0;
                    Object L$1;
                    Object L$2;
                    Object L$3;
                    int label;
                    Object result;

                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object object0) {
                        this.result = object0;
                        this.label |= 0x80000000;
                        return ChannelsKt__DeprecatedKt.maxWith(null, null, this);
                    }
                };
            }
            else {
                channelsKt__DeprecatedKt$maxWith$10.label ^= 0x80000000;
            }
        }
        else {
            channelsKt__DeprecatedKt$maxWith$10 = new ContinuationImpl(continuation0) {
                Object L$0;
                Object L$1;
                Object L$2;
                Object L$3;
                int label;
                Object result;

                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object object0) {
                    this.result = object0;
                    this.label |= 0x80000000;
                    return ChannelsKt__DeprecatedKt.maxWith(null, null, this);
                }
            };
        }
        Object object0 = channelsKt__DeprecatedKt$maxWith$10.result;
        Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch(channelsKt__DeprecatedKt$maxWith$10.label) {
            case 0: {
                ResultKt.throwOnFailure(object0);
                try {
                    ChannelIterator channelIterator0 = receiveChannel0.iterator();
                    channelsKt__DeprecatedKt$maxWith$10.L$0 = comparator0;
                    channelsKt__DeprecatedKt$maxWith$10.L$1 = receiveChannel0;
                    channelsKt__DeprecatedKt$maxWith$10.L$2 = channelIterator0;
                    channelsKt__DeprecatedKt$maxWith$10.label = 1;
                    Object object2 = channelIterator0.hasNext(channelsKt__DeprecatedKt$maxWith$10);
                    if(object2 == object1) {
                        return object1;
                    }
                    receiveChannel1 = receiveChannel0;
                    channelIterator1 = channelIterator0;
                    object0 = object2;
                    comparator1 = comparator0;
                    goto label_29;
                }
                catch(Throwable throwable0) {
                    goto label_69;
                }
            }
            case 1: {
                channelIterator1 = (ChannelIterator)channelsKt__DeprecatedKt$maxWith$10.L$2;
                receiveChannel1 = (ReceiveChannel)channelsKt__DeprecatedKt$maxWith$10.L$1;
                comparator1 = (Comparator)channelsKt__DeprecatedKt$maxWith$10.L$0;
                ResultKt.throwOnFailure(object0);
            label_29:
                if(((Boolean)object0).booleanValue()) {
                    object3 = channelIterator1.next();
                    channelIterator2 = channelIterator1;
                    receiveChannel0 = receiveChannel1;
                    comparator2 = comparator1;
                    goto label_56;
                }
                ChannelsKt.cancelConsumed(receiveChannel1, null);
                return null;
            }
            case 2: {
                Object object4 = channelsKt__DeprecatedKt$maxWith$10.L$3;
                channelIterator2 = (ChannelIterator)channelsKt__DeprecatedKt$maxWith$10.L$2;
                ReceiveChannel receiveChannel2 = (ReceiveChannel)channelsKt__DeprecatedKt$maxWith$10.L$1;
                comparator2 = (Comparator)channelsKt__DeprecatedKt$maxWith$10.L$0;
                try {
                    ResultKt.throwOnFailure(object0);
                    channelsKt__DeprecatedKt$maxWith$11 = channelsKt__DeprecatedKt$maxWith$10;
                    object5 = object4;
                    receiveChannel0 = receiveChannel2;
                    break;
                }
                catch(Throwable throwable1) {
                    receiveChannel1 = receiveChannel2;
                    throw throwable1;
                }
            }
            default: {
                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
            }
        }
        try {
            while(true) {
                if(!((Boolean)object0).booleanValue()) {
                    goto label_75;
                }
                object3 = channelIterator2.next();
                if(comparator2.compare(object5, object3) >= 0) {
                    object3 = object5;
                }
                channelsKt__DeprecatedKt$maxWith$10 = channelsKt__DeprecatedKt$maxWith$11;
            label_56:
                channelsKt__DeprecatedKt$maxWith$10.L$0 = comparator2;
                channelsKt__DeprecatedKt$maxWith$10.L$1 = receiveChannel0;
                channelsKt__DeprecatedKt$maxWith$10.L$2 = channelIterator2;
                channelsKt__DeprecatedKt$maxWith$10.L$3 = object3;
                channelsKt__DeprecatedKt$maxWith$10.label = 2;
                Object object6 = channelIterator2.hasNext(channelsKt__DeprecatedKt$maxWith$10);
                if(object6 == object1) {
                    return object1;
                }
                channelsKt__DeprecatedKt$maxWith$11 = channelsKt__DeprecatedKt$maxWith$10;
                object5 = object3;
                object0 = object6;
            }
        }
        catch(Throwable throwable0) {
        label_69:
            receiveChannel1 = receiveChannel0;
            throwable1 = throwable0;
        }
        try {
            throw throwable1;
        }
        catch(Throwable throwable2) {
            ChannelsKt.cancelConsumed(receiveChannel1, throwable1);
            throw throwable2;
        }
    label_75:
        ChannelsKt.cancelConsumed(receiveChannel0, null);
        return object5;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final Object minWith(ReceiveChannel receiveChannel0, Comparator comparator0, Continuation continuation0) {
        Object object5;
        kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.minWith.1 channelsKt__DeprecatedKt$minWith$11;
        Comparator comparator2;
        ChannelIterator channelIterator2;
        Object object3;
        Comparator comparator1;
        ChannelIterator channelIterator1;
        ReceiveChannel receiveChannel1;
        kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.minWith.1 channelsKt__DeprecatedKt$minWith$10;
        if(continuation0 instanceof kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.minWith.1) {
            channelsKt__DeprecatedKt$minWith$10 = (kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.minWith.1)continuation0;
            if((channelsKt__DeprecatedKt$minWith$10.label & 0x80000000) == 0) {
                channelsKt__DeprecatedKt$minWith$10 = new ContinuationImpl(continuation0) {
                    Object L$0;
                    Object L$1;
                    Object L$2;
                    Object L$3;
                    int label;
                    Object result;

                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object object0) {
                        this.result = object0;
                        this.label |= 0x80000000;
                        return ChannelsKt__DeprecatedKt.minWith(null, null, this);
                    }
                };
            }
            else {
                channelsKt__DeprecatedKt$minWith$10.label ^= 0x80000000;
            }
        }
        else {
            channelsKt__DeprecatedKt$minWith$10 = new ContinuationImpl(continuation0) {
                Object L$0;
                Object L$1;
                Object L$2;
                Object L$3;
                int label;
                Object result;

                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object object0) {
                    this.result = object0;
                    this.label |= 0x80000000;
                    return ChannelsKt__DeprecatedKt.minWith(null, null, this);
                }
            };
        }
        Object object0 = channelsKt__DeprecatedKt$minWith$10.result;
        Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch(channelsKt__DeprecatedKt$minWith$10.label) {
            case 0: {
                ResultKt.throwOnFailure(object0);
                try {
                    ChannelIterator channelIterator0 = receiveChannel0.iterator();
                    channelsKt__DeprecatedKt$minWith$10.L$0 = comparator0;
                    channelsKt__DeprecatedKt$minWith$10.L$1 = receiveChannel0;
                    channelsKt__DeprecatedKt$minWith$10.L$2 = channelIterator0;
                    channelsKt__DeprecatedKt$minWith$10.label = 1;
                    Object object2 = channelIterator0.hasNext(channelsKt__DeprecatedKt$minWith$10);
                    if(object2 == object1) {
                        return object1;
                    }
                    receiveChannel1 = receiveChannel0;
                    channelIterator1 = channelIterator0;
                    object0 = object2;
                    comparator1 = comparator0;
                    goto label_29;
                }
                catch(Throwable throwable0) {
                    goto label_69;
                }
            }
            case 1: {
                channelIterator1 = (ChannelIterator)channelsKt__DeprecatedKt$minWith$10.L$2;
                receiveChannel1 = (ReceiveChannel)channelsKt__DeprecatedKt$minWith$10.L$1;
                comparator1 = (Comparator)channelsKt__DeprecatedKt$minWith$10.L$0;
                ResultKt.throwOnFailure(object0);
            label_29:
                if(((Boolean)object0).booleanValue()) {
                    object3 = channelIterator1.next();
                    channelIterator2 = channelIterator1;
                    receiveChannel0 = receiveChannel1;
                    comparator2 = comparator1;
                    goto label_56;
                }
                ChannelsKt.cancelConsumed(receiveChannel1, null);
                return null;
            }
            case 2: {
                Object object4 = channelsKt__DeprecatedKt$minWith$10.L$3;
                channelIterator2 = (ChannelIterator)channelsKt__DeprecatedKt$minWith$10.L$2;
                ReceiveChannel receiveChannel2 = (ReceiveChannel)channelsKt__DeprecatedKt$minWith$10.L$1;
                comparator2 = (Comparator)channelsKt__DeprecatedKt$minWith$10.L$0;
                try {
                    ResultKt.throwOnFailure(object0);
                    channelsKt__DeprecatedKt$minWith$11 = channelsKt__DeprecatedKt$minWith$10;
                    object5 = object4;
                    receiveChannel0 = receiveChannel2;
                    break;
                }
                catch(Throwable throwable1) {
                    receiveChannel1 = receiveChannel2;
                    throw throwable1;
                }
            }
            default: {
                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
            }
        }
        try {
            while(true) {
                if(!((Boolean)object0).booleanValue()) {
                    goto label_75;
                }
                object3 = channelIterator2.next();
                if(comparator2.compare(object5, object3) <= 0) {
                    object3 = object5;
                }
                channelsKt__DeprecatedKt$minWith$10 = channelsKt__DeprecatedKt$minWith$11;
            label_56:
                channelsKt__DeprecatedKt$minWith$10.L$0 = comparator2;
                channelsKt__DeprecatedKt$minWith$10.L$1 = receiveChannel0;
                channelsKt__DeprecatedKt$minWith$10.L$2 = channelIterator2;
                channelsKt__DeprecatedKt$minWith$10.L$3 = object3;
                channelsKt__DeprecatedKt$minWith$10.label = 2;
                Object object6 = channelIterator2.hasNext(channelsKt__DeprecatedKt$minWith$10);
                if(object6 == object1) {
                    return object1;
                }
                channelsKt__DeprecatedKt$minWith$11 = channelsKt__DeprecatedKt$minWith$10;
                object5 = object3;
                object0 = object6;
            }
        }
        catch(Throwable throwable0) {
        label_69:
            receiveChannel1 = receiveChannel0;
            throwable1 = throwable0;
        }
        try {
            throw throwable1;
        }
        catch(Throwable throwable2) {
            ChannelsKt.cancelConsumed(receiveChannel1, throwable1);
            throw throwable2;
        }
    label_75:
        ChannelsKt.cancelConsumed(receiveChannel0, null);
        return object5;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final Object none(ReceiveChannel receiveChannel0, Continuation continuation0) {
        Boolean boolean0;
        kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.none.1 channelsKt__DeprecatedKt$none$10;
        if(continuation0 instanceof kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.none.1) {
            channelsKt__DeprecatedKt$none$10 = (kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.none.1)continuation0;
            if((channelsKt__DeprecatedKt$none$10.label & 0x80000000) == 0) {
                channelsKt__DeprecatedKt$none$10 = new ContinuationImpl(continuation0) {
                    Object L$0;
                    int label;
                    Object result;

                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object object0) {
                        this.result = object0;
                        this.label |= 0x80000000;
                        return ChannelsKt__DeprecatedKt.none(null, this);
                    }
                };
            }
            else {
                channelsKt__DeprecatedKt$none$10.label ^= 0x80000000;
            }
        }
        else {
            channelsKt__DeprecatedKt$none$10 = new ContinuationImpl(continuation0) {
                Object L$0;
                int label;
                Object result;

                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object object0) {
                    this.result = object0;
                    this.label |= 0x80000000;
                    return ChannelsKt__DeprecatedKt.none(null, this);
                }
            };
        }
        Object object0 = channelsKt__DeprecatedKt$none$10.result;
        Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch(channelsKt__DeprecatedKt$none$10.label) {
            case 0: {
                ResultKt.throwOnFailure(object0);
                try {
                    ChannelIterator channelIterator0 = receiveChannel0.iterator();
                    channelsKt__DeprecatedKt$none$10.L$0 = receiveChannel0;
                    channelsKt__DeprecatedKt$none$10.label = 1;
                    object0 = channelIterator0.hasNext(channelsKt__DeprecatedKt$none$10);
                    if(object0 == object1) {
                        return object1;
                    }
                    boolean0 = Boxing.boxBoolean(!((Boolean)object0).booleanValue());
                    goto label_26;
                }
                catch(Throwable throwable0) {
                    break;
                }
            }
            case 1: {
                receiveChannel0 = (ReceiveChannel)channelsKt__DeprecatedKt$none$10.L$0;
                try {
                    ResultKt.throwOnFailure(object0);
                    boolean0 = Boxing.boxBoolean(!((Boolean)object0).booleanValue());
                    goto label_26;
                }
                catch(Throwable throwable0) {
                }
                break;
            }
            default: {
                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
            }
        }
        ChannelsKt.cancelConsumed(receiveChannel0, throwable0);
        throw throwable0;
    label_26:
        ChannelsKt.cancelConsumed(receiveChannel0, null);
        return boolean0;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Left for binary compatibility")
    public static final ReceiveChannel requireNoNulls(ReceiveChannel receiveChannel0) {
        return ChannelsKt__DeprecatedKt.map$default(receiveChannel0, null, new Function2(receiveChannel0, null) {
            final ReceiveChannel $this_requireNoNulls;
            Object L$0;
            int label;

            {
                this.$this_requireNoNulls = receiveChannel0;
                super(2, continuation0);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object object0, Continuation continuation0) {
                kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.requireNoNulls.1 channelsKt__DeprecatedKt$requireNoNulls$10 = new kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.requireNoNulls.1(this.$this_requireNoNulls, continuation0);
                channelsKt__DeprecatedKt$requireNoNulls$10.L$0 = object0;
                return channelsKt__DeprecatedKt$requireNoNulls$10;
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(object0, ((Continuation)object1));
            }

            public final Object invoke(Object object0, Continuation continuation0) {
                return ((kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.requireNoNulls.1)this.create(object0, continuation0)).invokeSuspend(Unit.INSTANCE);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object object0) {
                if(this.label != 0) {
                    throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                }
                ResultKt.throwOnFailure(object0);
                Object object1 = this.L$0;
                if(object1 == null) {
                    throw new IllegalArgumentException("null element found in " + this.$this_requireNoNulls + '.');
                }
                return object1;
            }
        }, 1, null);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final Object single(ReceiveChannel receiveChannel0, Continuation continuation0) {
        ReceiveChannel receiveChannel2;
        Object object5;
        Object object4;
        Object object3;
        ChannelIterator channelIterator1;
        Throwable throwable1;
        ReceiveChannel receiveChannel1;
        Object object2;
        ChannelIterator channelIterator0;
        kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.single.1 channelsKt__DeprecatedKt$single$10;
        if(continuation0 instanceof kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.single.1) {
            channelsKt__DeprecatedKt$single$10 = (kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.single.1)continuation0;
            if((channelsKt__DeprecatedKt$single$10.label & 0x80000000) == 0) {
                channelsKt__DeprecatedKt$single$10 = new ContinuationImpl(continuation0) {
                    Object L$0;
                    Object L$1;
                    int label;
                    Object result;

                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object object0) {
                        this.result = object0;
                        this.label |= 0x80000000;
                        return ChannelsKt__DeprecatedKt.single(null, this);
                    }
                };
            }
            else {
                channelsKt__DeprecatedKt$single$10.label ^= 0x80000000;
            }
        }
        else {
            channelsKt__DeprecatedKt$single$10 = new ContinuationImpl(continuation0) {
                Object L$0;
                Object L$1;
                int label;
                Object result;

                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object object0) {
                    this.result = object0;
                    this.label |= 0x80000000;
                    return ChannelsKt__DeprecatedKt.single(null, this);
                }
            };
        }
        Object object0 = channelsKt__DeprecatedKt$single$10.result;
        Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch(channelsKt__DeprecatedKt$single$10.label) {
            case 0: {
                ResultKt.throwOnFailure(object0);
                try {
                    channelIterator0 = receiveChannel0.iterator();
                    channelsKt__DeprecatedKt$single$10.L$0 = receiveChannel0;
                    channelsKt__DeprecatedKt$single$10.L$1 = channelIterator0;
                    channelsKt__DeprecatedKt$single$10.label = 1;
                    object2 = channelIterator0.hasNext(channelsKt__DeprecatedKt$single$10);
                }
                catch(Throwable throwable0) {
                    receiveChannel1 = receiveChannel0;
                    throwable1 = throwable0;
                    throw throwable1;
                }
                if(object2 == object1) {
                    return object1;
                }
                receiveChannel1 = receiveChannel0;
                channelIterator1 = channelIterator0;
                object0 = object2;
                goto label_31;
            }
            case 1: {
                channelIterator1 = (ChannelIterator)channelsKt__DeprecatedKt$single$10.L$1;
                receiveChannel1 = (ReceiveChannel)channelsKt__DeprecatedKt$single$10.L$0;
                ResultKt.throwOnFailure(object0);
            label_31:
                if(!((Boolean)object0).booleanValue()) {
                    throw new NoSuchElementException("ReceiveChannel is empty.");
                }
                object3 = channelIterator1.next();
                channelsKt__DeprecatedKt$single$10.L$0 = receiveChannel1;
                channelsKt__DeprecatedKt$single$10.L$1 = object3;
                channelsKt__DeprecatedKt$single$10.label = 2;
                object4 = channelIterator1.hasNext(channelsKt__DeprecatedKt$single$10);
                if(object4 == object1) {
                    return object1;
                }
                object0 = object4;
                object5 = object3;
                receiveChannel2 = receiveChannel1;
                goto label_50;
            }
            case 2: {
                object5 = channelsKt__DeprecatedKt$single$10.L$1;
                receiveChannel2 = (ReceiveChannel)channelsKt__DeprecatedKt$single$10.L$0;
                try {
                    ResultKt.throwOnFailure(object0);
                label_50:
                    if(((Boolean)object0).booleanValue()) {
                        throw new IllegalArgumentException("ReceiveChannel has more than one element.");
                    }
                    goto label_58;
                }
                catch(Throwable throwable1) {
                    break;
                }
            }
            default: {
                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
            }
        }
        receiveChannel1 = receiveChannel2;
        try {
            throw throwable1;
        }
        catch(Throwable throwable2) {
            ChannelsKt.cancelConsumed(receiveChannel1, throwable1);
            throw throwable2;
        }
    label_58:
        ChannelsKt.cancelConsumed(receiveChannel2, null);
        return object5;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final Object singleOrNull(ReceiveChannel receiveChannel0, Continuation continuation0) {
        ReceiveChannel receiveChannel2;
        Object object5;
        Object object4;
        Object object3;
        ChannelIterator channelIterator1;
        Throwable throwable1;
        ReceiveChannel receiveChannel1;
        Object object2;
        ChannelIterator channelIterator0;
        kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.singleOrNull.1 channelsKt__DeprecatedKt$singleOrNull$10;
        if(continuation0 instanceof kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.singleOrNull.1) {
            channelsKt__DeprecatedKt$singleOrNull$10 = (kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.singleOrNull.1)continuation0;
            if((channelsKt__DeprecatedKt$singleOrNull$10.label & 0x80000000) == 0) {
                channelsKt__DeprecatedKt$singleOrNull$10 = new ContinuationImpl(continuation0) {
                    Object L$0;
                    Object L$1;
                    int label;
                    Object result;

                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object object0) {
                        this.result = object0;
                        this.label |= 0x80000000;
                        return ChannelsKt__DeprecatedKt.singleOrNull(null, this);
                    }
                };
            }
            else {
                channelsKt__DeprecatedKt$singleOrNull$10.label ^= 0x80000000;
            }
        }
        else {
            channelsKt__DeprecatedKt$singleOrNull$10 = new ContinuationImpl(continuation0) {
                Object L$0;
                Object L$1;
                int label;
                Object result;

                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object object0) {
                    this.result = object0;
                    this.label |= 0x80000000;
                    return ChannelsKt__DeprecatedKt.singleOrNull(null, this);
                }
            };
        }
        Object object0 = channelsKt__DeprecatedKt$singleOrNull$10.result;
        Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch(channelsKt__DeprecatedKt$singleOrNull$10.label) {
            case 0: {
                ResultKt.throwOnFailure(object0);
                try {
                    channelIterator0 = receiveChannel0.iterator();
                    channelsKt__DeprecatedKt$singleOrNull$10.L$0 = receiveChannel0;
                    channelsKt__DeprecatedKt$singleOrNull$10.L$1 = channelIterator0;
                    channelsKt__DeprecatedKt$singleOrNull$10.label = 1;
                    object2 = channelIterator0.hasNext(channelsKt__DeprecatedKt$singleOrNull$10);
                }
                catch(Throwable throwable0) {
                    receiveChannel1 = receiveChannel0;
                    throwable1 = throwable0;
                    throw throwable1;
                }
                if(object2 == object1) {
                    return object1;
                }
                receiveChannel1 = receiveChannel0;
                channelIterator1 = channelIterator0;
                object0 = object2;
                goto label_31;
            }
            case 1: {
                channelIterator1 = (ChannelIterator)channelsKt__DeprecatedKt$singleOrNull$10.L$1;
                receiveChannel1 = (ReceiveChannel)channelsKt__DeprecatedKt$singleOrNull$10.L$0;
                ResultKt.throwOnFailure(object0);
            label_31:
                if(((Boolean)object0).booleanValue()) {
                    object3 = channelIterator1.next();
                    channelsKt__DeprecatedKt$singleOrNull$10.L$0 = receiveChannel1;
                    channelsKt__DeprecatedKt$singleOrNull$10.L$1 = object3;
                    channelsKt__DeprecatedKt$singleOrNull$10.label = 2;
                    object4 = channelIterator1.hasNext(channelsKt__DeprecatedKt$singleOrNull$10);
                }
                else {
                    ChannelsKt.cancelConsumed(receiveChannel1, null);
                    return null;
                }
                if(object4 == object1) {
                    return object1;
                }
                object0 = object4;
                object5 = object3;
                receiveChannel2 = receiveChannel1;
                goto label_51;
            }
            case 2: {
                object5 = channelsKt__DeprecatedKt$singleOrNull$10.L$1;
                receiveChannel2 = (ReceiveChannel)channelsKt__DeprecatedKt$singleOrNull$10.L$0;
                try {
                    ResultKt.throwOnFailure(object0);
                label_51:
                    if(((Boolean)object0).booleanValue()) {
                        goto label_59;
                    }
                    goto label_61;
                }
                catch(Throwable throwable1) {
                    break;
                }
            }
            default: {
                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
            }
        }
        receiveChannel1 = receiveChannel2;
        try {
            throw throwable1;
        }
        catch(Throwable throwable2) {
            ChannelsKt.cancelConsumed(receiveChannel1, throwable1);
            throw throwable2;
        }
    label_59:
        ChannelsKt.cancelConsumed(receiveChannel2, null);
        return null;
    label_61:
        ChannelsKt.cancelConsumed(receiveChannel2, null);
        return object5;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final ReceiveChannel take(ReceiveChannel receiveChannel0, int v, CoroutineContext coroutineContext0) {
        Function1 function10 = ChannelsKt.consumes(receiveChannel0);
        kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.take.1 channelsKt__DeprecatedKt$take$10 = new Function2(v, receiveChannel0, null) {
            final int $n;
            final ReceiveChannel $this_take;
            int I$0;
            private Object L$0;
            Object L$1;
            int label;

            {
                this.$n = v;
                this.$this_take = receiveChannel0;
                super(2, continuation0);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object object0, Continuation continuation0) {
                kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.take.1 channelsKt__DeprecatedKt$take$10 = new kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.take.1(this.$n, this.$this_take, continuation0);
                channelsKt__DeprecatedKt$take$10.L$0 = object0;
                return channelsKt__DeprecatedKt$take$10;
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(((ProducerScope)object0), ((Continuation)object1));
            }

            public final Object invoke(ProducerScope producerScope0, Continuation continuation0) {
                return ((kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.take.1)this.create(producerScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object object0) {
                ProducerScope producerScope1;
                ChannelIterator channelIterator0;
                int v;
                ProducerScope producerScope0;
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(this.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        producerScope0 = (ProducerScope)this.L$0;
                        v = this.$n;
                        if(v == 0) {
                            return Unit.INSTANCE;
                        }
                        if(v < 0) {
                            throw new IllegalArgumentException(("Requested element count " + v + " is less than zero.").toString());
                        }
                        channelIterator0 = this.$this_take.iterator();
                        goto label_25;
                    }
                    case 1: {
                        v = this.I$0;
                        channelIterator0 = (ChannelIterator)this.L$1;
                        producerScope1 = (ProducerScope)this.L$0;
                        ResultKt.throwOnFailure(object0);
                        goto label_33;
                    }
                    case 2: {
                        v = this.I$0;
                        channelIterator0 = (ChannelIterator)this.L$1;
                        producerScope1 = (ProducerScope)this.L$0;
                        ResultKt.throwOnFailure(object0);
                        break;
                    }
                    default: {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    }
                }
                do {
                    producerScope0 = producerScope1;
                    --v;
                    if(v == 0) {
                        return Unit.INSTANCE;
                    }
                label_25:
                    this.L$0 = producerScope0;
                    this.L$1 = channelIterator0;
                    this.I$0 = v;
                    this.label = 1;
                    Object object2 = channelIterator0.hasNext(this);
                    if(object2 == object1) {
                        return object1;
                    }
                    producerScope1 = producerScope0;
                    object0 = object2;
                label_33:
                    if(!((Boolean)object0).booleanValue()) {
                        return Unit.INSTANCE;
                    }
                    Object object3 = channelIterator0.next();
                    this.L$0 = producerScope1;
                    this.L$1 = channelIterator0;
                    this.I$0 = v;
                    this.label = 2;
                }
                while(producerScope1.send(object3, this) != object1);
                return object1;
            }
        };
        return ProduceKt.produce$default(() -> EmptyCoroutineContext.INSTANCE, coroutineContext0, 0, null, function10, channelsKt__DeprecatedKt$take$10, 6, null);
    }

    public static ReceiveChannel take$default(ReceiveChannel receiveChannel0, int v, CoroutineContext coroutineContext0, int v1, Object object0) {
        if((v1 & 2) != 0) {
            coroutineContext0 = Dispatchers.getUnconfined();
        }
        return ChannelsKt__DeprecatedKt.take(receiveChannel0, v, coroutineContext0);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final ReceiveChannel takeWhile(ReceiveChannel receiveChannel0, CoroutineContext coroutineContext0, Function2 function20) {
        Function1 function10 = ChannelsKt.consumes(receiveChannel0);
        kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.takeWhile.1 channelsKt__DeprecatedKt$takeWhile$10 = new Function2(receiveChannel0, function20, null) {
            final Function2 $predicate;
            final ReceiveChannel $this_takeWhile;
            private Object L$0;
            Object L$1;
            Object L$2;
            int label;

            {
                this.$this_takeWhile = receiveChannel0;
                this.$predicate = function20;
                super(2, continuation0);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object object0, Continuation continuation0) {
                kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.takeWhile.1 channelsKt__DeprecatedKt$takeWhile$10 = new kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.takeWhile.1(this.$this_takeWhile, this.$predicate, continuation0);
                channelsKt__DeprecatedKt$takeWhile$10.L$0 = object0;
                return channelsKt__DeprecatedKt$takeWhile$10;
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(((ProducerScope)object0), ((Continuation)object1));
            }

            public final Object invoke(ProducerScope producerScope0, Continuation continuation0) {
                return ((kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.takeWhile.1)this.create(producerScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object object0) {
                ProducerScope producerScope2;
                ChannelIterator channelIterator1;
                Object object2;
                ProducerScope producerScope1;
                ChannelIterator channelIterator0;
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(this.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        ProducerScope producerScope0 = (ProducerScope)this.L$0;
                        channelIterator0 = this.$this_takeWhile.iterator();
                        producerScope1 = producerScope0;
                        break;
                    }
                    case 1: {
                        channelIterator0 = (ChannelIterator)this.L$1;
                        producerScope1 = (ProducerScope)this.L$0;
                        ResultKt.throwOnFailure(object0);
                        goto label_25;
                    }
                    case 2: {
                        object2 = this.L$2;
                        channelIterator1 = (ChannelIterator)this.L$1;
                        producerScope2 = (ProducerScope)this.L$0;
                        ResultKt.throwOnFailure(object0);
                        goto label_37;
                    }
                    case 3: {
                        channelIterator0 = (ChannelIterator)this.L$1;
                        producerScope1 = (ProducerScope)this.L$0;
                        ResultKt.throwOnFailure(object0);
                        break;
                    }
                    default: {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    }
                }
                while(true) {
                    this.L$0 = producerScope1;
                    this.L$1 = channelIterator0;
                    this.label = 1;
                    object0 = channelIterator0.hasNext(this);
                    if(object0 == object1) {
                        break;
                    }
                label_25:
                    if(((Boolean)object0).booleanValue()) {
                        Object object3 = channelIterator0.next();
                        this.L$0 = producerScope1;
                        this.L$1 = channelIterator0;
                        this.L$2 = object3;
                        this.label = 2;
                        Object object4 = this.$predicate.invoke(object3, this);
                        if(object4 == object1) {
                            break;
                        }
                        object2 = object3;
                        object0 = object4;
                        producerScope2 = producerScope1;
                        channelIterator1 = channelIterator0;
                    label_37:
                        if(!((Boolean)object0).booleanValue()) {
                            return Unit.INSTANCE;
                        }
                        this.L$0 = producerScope2;
                        this.L$1 = channelIterator1;
                        this.L$2 = null;
                        this.label = 3;
                        if(producerScope2.send(object2, this) == object1) {
                            break;
                        }
                        channelIterator0 = channelIterator1;
                        producerScope1 = producerScope2;
                        continue;
                    }
                    return Unit.INSTANCE;
                }
                return object1;
            }
        };
        return ProduceKt.produce$default(() -> EmptyCoroutineContext.INSTANCE, coroutineContext0, 0, null, function10, channelsKt__DeprecatedKt$takeWhile$10, 6, null);
    }

    public static ReceiveChannel takeWhile$default(ReceiveChannel receiveChannel0, CoroutineContext coroutineContext0, Function2 function20, int v, Object object0) {
        if((v & 1) != 0) {
            coroutineContext0 = Dispatchers.getUnconfined();
        }
        return ChannelsKt__DeprecatedKt.takeWhile(receiveChannel0, coroutineContext0, function20);
    }

    public static final Object toChannel(ReceiveChannel receiveChannel0, SendChannel sendChannel0, Continuation continuation0) {
        Object object2;
        SendChannel sendChannel1;
        ReceiveChannel receiveChannel1;
        ChannelIterator channelIterator1;
        ChannelIterator channelIterator0;
        kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.toChannel.1 channelsKt__DeprecatedKt$toChannel$10;
        if(continuation0 instanceof kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.toChannel.1) {
            channelsKt__DeprecatedKt$toChannel$10 = (kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.toChannel.1)continuation0;
            if((channelsKt__DeprecatedKt$toChannel$10.label & 0x80000000) == 0) {
                channelsKt__DeprecatedKt$toChannel$10 = new ContinuationImpl(continuation0) {
                    Object L$0;
                    Object L$1;
                    Object L$2;
                    int label;
                    Object result;

                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object object0) {
                        this.result = object0;
                        this.label |= 0x80000000;
                        return ChannelsKt.toChannel(null, null, this);
                    }
                };
            }
            else {
                channelsKt__DeprecatedKt$toChannel$10.label ^= 0x80000000;
            }
        }
        else {
            channelsKt__DeprecatedKt$toChannel$10 = new ContinuationImpl(continuation0) {
                Object L$0;
                Object L$1;
                Object L$2;
                int label;
                Object result;

                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object object0) {
                    this.result = object0;
                    this.label |= 0x80000000;
                    return ChannelsKt.toChannel(null, null, this);
                }
            };
        }
        Object object0 = channelsKt__DeprecatedKt$toChannel$10.result;
        Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch(channelsKt__DeprecatedKt$toChannel$10.label) {
            case 0: {
                ResultKt.throwOnFailure(object0);
                try {
                    channelIterator0 = receiveChannel0.iterator();
                    goto label_27;
                }
                catch(Throwable throwable0) {
                    receiveChannel1 = receiveChannel0;
                    throwable1 = throwable0;
                    throw throwable1;
                }
            }
            case 1: {
                channelIterator1 = (ChannelIterator)channelsKt__DeprecatedKt$toChannel$10.L$2;
                receiveChannel1 = (ReceiveChannel)channelsKt__DeprecatedKt$toChannel$10.L$1;
                sendChannel1 = (SendChannel)channelsKt__DeprecatedKt$toChannel$10.L$0;
                ResultKt.throwOnFailure(object0);
                goto label_42;
            }
            case 2: {
                channelIterator1 = (ChannelIterator)channelsKt__DeprecatedKt$toChannel$10.L$2;
                receiveChannel1 = (ReceiveChannel)channelsKt__DeprecatedKt$toChannel$10.L$1;
                sendChannel1 = (SendChannel)channelsKt__DeprecatedKt$toChannel$10.L$0;
                ResultKt.throwOnFailure(object0);
                break;
            }
            default: {
                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
            }
        }
        while(true) {
            channelIterator0 = channelIterator1;
            receiveChannel0 = receiveChannel1;
            sendChannel0 = sendChannel1;
            try {
            label_27:
                channelsKt__DeprecatedKt$toChannel$10.L$0 = sendChannel0;
                channelsKt__DeprecatedKt$toChannel$10.L$1 = receiveChannel0;
                channelsKt__DeprecatedKt$toChannel$10.L$2 = channelIterator0;
                channelsKt__DeprecatedKt$toChannel$10.label = 1;
                object2 = channelIterator0.hasNext(channelsKt__DeprecatedKt$toChannel$10);
            }
            catch(Throwable throwable0) {
                receiveChannel1 = receiveChannel0;
                throwable1 = throwable0;
                throw throwable1;
            }
            if(object2 == object1) {
                return object1;
            }
            receiveChannel1 = receiveChannel0;
            channelIterator1 = channelIterator0;
            object0 = object2;
            sendChannel1 = sendChannel0;
        label_42:
            if(!((Boolean)object0).booleanValue()) {
                ChannelsKt.cancelConsumed(receiveChannel1, null);
                return sendChannel1;
            }
            Object object3 = channelIterator1.next();
            channelsKt__DeprecatedKt$toChannel$10.L$0 = sendChannel1;
            channelsKt__DeprecatedKt$toChannel$10.L$1 = receiveChannel1;
            channelsKt__DeprecatedKt$toChannel$10.L$2 = channelIterator1;
            channelsKt__DeprecatedKt$toChannel$10.label = 2;
            if(sendChannel1.send(object3, channelsKt__DeprecatedKt$toChannel$10) == object1) {
                break;
            }
        }
        return object1;
    }

    public static final Object toCollection(ReceiveChannel receiveChannel0, Collection collection0, Continuation continuation0) {
        Collection collection2;
        Throwable throwable1;
        Collection collection1;
        ChannelIterator channelIterator0;
        ReceiveChannel receiveChannel1;
        kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.toCollection.1 channelsKt__DeprecatedKt$toCollection$10;
        if(continuation0 instanceof kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.toCollection.1) {
            channelsKt__DeprecatedKt$toCollection$10 = (kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.toCollection.1)continuation0;
            if((channelsKt__DeprecatedKt$toCollection$10.label & 0x80000000) == 0) {
                channelsKt__DeprecatedKt$toCollection$10 = new ContinuationImpl(continuation0) {
                    Object L$0;
                    Object L$1;
                    Object L$2;
                    int label;
                    Object result;

                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object object0) {
                        this.result = object0;
                        this.label |= 0x80000000;
                        return ChannelsKt.toCollection(null, null, this);
                    }
                };
            }
            else {
                channelsKt__DeprecatedKt$toCollection$10.label ^= 0x80000000;
            }
        }
        else {
            channelsKt__DeprecatedKt$toCollection$10 = new ContinuationImpl(continuation0) {
                Object L$0;
                Object L$1;
                Object L$2;
                int label;
                Object result;

                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object object0) {
                    this.result = object0;
                    this.label |= 0x80000000;
                    return ChannelsKt.toCollection(null, null, this);
                }
            };
        }
        Object object0 = channelsKt__DeprecatedKt$toCollection$10.result;
        Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch(channelsKt__DeprecatedKt$toCollection$10.label) {
            case 0: {
                ResultKt.throwOnFailure(object0);
                try {
                    receiveChannel1 = receiveChannel0;
                    channelIterator0 = receiveChannel0.iterator();
                    collection1 = collection0;
                    goto label_27;
                }
                catch(Throwable throwable0) {
                    throwable1 = throwable0;
                    break;
                }
            }
            case 1: {
                channelIterator0 = (ChannelIterator)channelsKt__DeprecatedKt$toCollection$10.L$2;
                receiveChannel1 = (ReceiveChannel)channelsKt__DeprecatedKt$toCollection$10.L$1;
                collection2 = (Collection)channelsKt__DeprecatedKt$toCollection$10.L$0;
                try {
                    ResultKt.throwOnFailure(object0);
                    while(true) {
                        if(!((Boolean)object0).booleanValue()) {
                            goto label_42;
                        }
                        collection2.add(channelIterator0.next());
                        collection1 = collection2;
                    label_27:
                        channelsKt__DeprecatedKt$toCollection$10.L$0 = collection1;
                        channelsKt__DeprecatedKt$toCollection$10.L$1 = receiveChannel1;
                        channelsKt__DeprecatedKt$toCollection$10.L$2 = channelIterator0;
                        channelsKt__DeprecatedKt$toCollection$10.label = 1;
                        Object object2 = channelIterator0.hasNext(channelsKt__DeprecatedKt$toCollection$10);
                        if(object2 == object1) {
                            return object1;
                        }
                        collection2 = collection1;
                        object0 = object2;
                    }
                }
                catch(Throwable throwable1) {
                    break;
                }
            }
            default: {
                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
            }
        }
        try {
            throw throwable1;
        }
        catch(Throwable throwable2) {
            ChannelsKt.cancelConsumed(receiveChannel1, throwable1);
            throw throwable2;
        }
    label_42:
        ChannelsKt.cancelConsumed(receiveChannel1, null);
        return collection2;
    }

    public static final Object toMap(ReceiveChannel receiveChannel0, Map map0, Continuation continuation0) {
        Map map2;
        Throwable throwable1;
        Map map1;
        ChannelIterator channelIterator0;
        ReceiveChannel receiveChannel1;
        kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.toMap.2 channelsKt__DeprecatedKt$toMap$20;
        if(continuation0 instanceof kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.toMap.2) {
            channelsKt__DeprecatedKt$toMap$20 = (kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.toMap.2)continuation0;
            if((channelsKt__DeprecatedKt$toMap$20.label & 0x80000000) == 0) {
                channelsKt__DeprecatedKt$toMap$20 = new ContinuationImpl(continuation0) {
                    Object L$0;
                    Object L$1;
                    Object L$2;
                    int label;
                    Object result;

                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object object0) {
                        this.result = object0;
                        this.label |= 0x80000000;
                        return ChannelsKt.toMap(null, null, this);
                    }
                };
            }
            else {
                channelsKt__DeprecatedKt$toMap$20.label ^= 0x80000000;
            }
        }
        else {
            channelsKt__DeprecatedKt$toMap$20 = new ContinuationImpl(continuation0) {
                Object L$0;
                Object L$1;
                Object L$2;
                int label;
                Object result;

                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object object0) {
                    this.result = object0;
                    this.label |= 0x80000000;
                    return ChannelsKt.toMap(null, null, this);
                }
            };
        }
        Object object0 = channelsKt__DeprecatedKt$toMap$20.result;
        Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch(channelsKt__DeprecatedKt$toMap$20.label) {
            case 0: {
                ResultKt.throwOnFailure(object0);
                try {
                    receiveChannel1 = receiveChannel0;
                    channelIterator0 = receiveChannel0.iterator();
                    map1 = map0;
                    goto label_28;
                }
                catch(Throwable throwable0) {
                    throwable1 = throwable0;
                    break;
                }
            }
            case 1: {
                channelIterator0 = (ChannelIterator)channelsKt__DeprecatedKt$toMap$20.L$2;
                receiveChannel1 = (ReceiveChannel)channelsKt__DeprecatedKt$toMap$20.L$1;
                map2 = (Map)channelsKt__DeprecatedKt$toMap$20.L$0;
                try {
                    ResultKt.throwOnFailure(object0);
                    while(true) {
                        if(!((Boolean)object0).booleanValue()) {
                            goto label_43;
                        }
                        Pair pair0 = (Pair)channelIterator0.next();
                        map2.put(pair0.getFirst(), pair0.getSecond());
                        map1 = map2;
                    label_28:
                        channelsKt__DeprecatedKt$toMap$20.L$0 = map1;
                        channelsKt__DeprecatedKt$toMap$20.L$1 = receiveChannel1;
                        channelsKt__DeprecatedKt$toMap$20.L$2 = channelIterator0;
                        channelsKt__DeprecatedKt$toMap$20.label = 1;
                        Object object2 = channelIterator0.hasNext(channelsKt__DeprecatedKt$toMap$20);
                        if(object2 == object1) {
                            return object1;
                        }
                        map2 = map1;
                        object0 = object2;
                    }
                }
                catch(Throwable throwable1) {
                    break;
                }
            }
            default: {
                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
            }
        }
        try {
            throw throwable1;
        }
        catch(Throwable throwable2) {
            ChannelsKt.cancelConsumed(receiveChannel1, throwable1);
            throw throwable2;
        }
    label_43:
        ChannelsKt.cancelConsumed(receiveChannel1, null);
        return map2;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final Object toMap(ReceiveChannel receiveChannel0, Continuation continuation0) {
        return ChannelsKt.toMap(receiveChannel0, new LinkedHashMap(), continuation0);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final Object toMutableList(ReceiveChannel receiveChannel0, Continuation continuation0) {
        return ChannelsKt.toCollection(receiveChannel0, new ArrayList(), continuation0);
    }

    public static final Object toMutableSet(ReceiveChannel receiveChannel0, Continuation continuation0) {
        return ChannelsKt.toCollection(receiveChannel0, new LinkedHashSet(), continuation0);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final Object toSet(ReceiveChannel receiveChannel0, Continuation continuation0) {
        return ChannelsKt.toMutableSet(receiveChannel0, continuation0);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final ReceiveChannel withIndex(ReceiveChannel receiveChannel0, CoroutineContext coroutineContext0) {
        Function1 function10 = ChannelsKt.consumes(receiveChannel0);
        kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.withIndex.1 channelsKt__DeprecatedKt$withIndex$10 = new Function2(receiveChannel0, null) {
            final ReceiveChannel $this_withIndex;
            int I$0;
            private Object L$0;
            Object L$1;
            int label;

            {
                this.$this_withIndex = receiveChannel0;
                super(2, continuation0);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object object0, Continuation continuation0) {
                kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.withIndex.1 channelsKt__DeprecatedKt$withIndex$10 = new kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.withIndex.1(this.$this_withIndex, continuation0);
                channelsKt__DeprecatedKt$withIndex$10.L$0 = object0;
                return channelsKt__DeprecatedKt$withIndex$10;
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(((ProducerScope)object0), ((Continuation)object1));
            }

            public final Object invoke(ProducerScope producerScope0, Continuation continuation0) {
                return ((kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.withIndex.1)this.create(producerScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object object0) {
                ProducerScope producerScope1;
                ChannelIterator channelIterator1;
                int v1;
                int v;
                ChannelIterator channelIterator0;
                ProducerScope producerScope0;
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(this.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        producerScope0 = (ProducerScope)this.L$0;
                        channelIterator0 = this.$this_withIndex.iterator();
                        v = 0;
                        goto label_20;
                    }
                    case 1: {
                        v1 = this.I$0;
                        channelIterator1 = (ChannelIterator)this.L$1;
                        producerScope1 = (ProducerScope)this.L$0;
                        ResultKt.throwOnFailure(object0);
                        goto label_30;
                    }
                    case 2: {
                        int v2 = this.I$0;
                        ChannelIterator channelIterator2 = (ChannelIterator)this.L$1;
                        producerScope1 = (ProducerScope)this.L$0;
                        ResultKt.throwOnFailure(object0);
                        v = v2;
                        channelIterator0 = channelIterator2;
                        break;
                    }
                    default: {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    }
                }
                while(true) {
                    producerScope0 = producerScope1;
                label_20:
                    this.L$0 = producerScope0;
                    this.L$1 = channelIterator0;
                    this.I$0 = v;
                    this.label = 1;
                    Object object2 = channelIterator0.hasNext(this);
                    if(object2 == object1) {
                        break;
                    }
                    producerScope1 = producerScope0;
                    object0 = object2;
                    channelIterator1 = channelIterator0;
                    v1 = v;
                label_30:
                    if(((Boolean)object0).booleanValue()) {
                        IndexedValue indexedValue0 = new IndexedValue(v1, channelIterator1.next());
                        this.L$0 = producerScope1;
                        this.L$1 = channelIterator1;
                        this.I$0 = v1 + 1;
                        this.label = 2;
                        if(producerScope1.send(indexedValue0, this) == object1) {
                            break;
                        }
                        channelIterator0 = channelIterator1;
                        v = v1 + 1;
                        continue;
                    }
                    return Unit.INSTANCE;
                }
                return object1;
            }
        };
        return ProduceKt.produce$default(() -> EmptyCoroutineContext.INSTANCE, coroutineContext0, 0, null, function10, channelsKt__DeprecatedKt$withIndex$10, 6, null);
    }

    public static ReceiveChannel withIndex$default(ReceiveChannel receiveChannel0, CoroutineContext coroutineContext0, int v, Object object0) {
        if((v & 1) != 0) {
            coroutineContext0 = Dispatchers.getUnconfined();
        }
        return ChannelsKt__DeprecatedKt.withIndex(receiveChannel0, coroutineContext0);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final ReceiveChannel zip(ReceiveChannel receiveChannel0, ReceiveChannel receiveChannel1) {
        return ChannelsKt__DeprecatedKt.zip$default(receiveChannel0, receiveChannel1, null, kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.zip.1.INSTANCE, 2, null);

        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0010\u0000\u001A\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032\u0006\u0010\u0004\u001A\u0002H\u00022\u0006\u0010\u0005\u001A\u0002H\u0003H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"<anonymous>", "Lkotlin/Pair;", "E", "R", "t1", "t2", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Lkotlin/Pair;"}, k = 3, mv = {1, 8, 0}, xi = 0x30)
        final class kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.zip.1 extends Lambda implements Function2 {
            public static final kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.zip.1 INSTANCE;

            static {
                kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.zip.1.INSTANCE = new kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.zip.1();
            }

            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.zip.1() {
                super(2);
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(object0, object1);
            }

            public final Pair invoke(Object object0, Object object1) {
                return TuplesKt.to(object0, object1);
            }
        }

    }

    public static final ReceiveChannel zip(ReceiveChannel receiveChannel0, ReceiveChannel receiveChannel1, CoroutineContext coroutineContext0, Function2 function20) {
        Function1 function10 = ChannelsKt.consumesAll(new ReceiveChannel[]{receiveChannel0, receiveChannel1});
        kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.zip.2 channelsKt__DeprecatedKt$zip$20 = new Function2(receiveChannel1, receiveChannel0, function20, null) {
            final ReceiveChannel $other;
            final ReceiveChannel $this_zip;
            final Function2 $transform;
            private Object L$0;
            Object L$1;
            Object L$2;
            Object L$3;
            Object L$4;
            Object L$5;
            int label;

            {
                this.$other = receiveChannel0;
                this.$this_zip = receiveChannel1;
                this.$transform = function20;
                super(2, continuation0);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object object0, Continuation continuation0) {
                kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.zip.2 channelsKt__DeprecatedKt$zip$20 = new kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.zip.2(this.$other, this.$this_zip, this.$transform, continuation0);
                channelsKt__DeprecatedKt$zip$20.L$0 = object0;
                return channelsKt__DeprecatedKt$zip$20;
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(((ProducerScope)object0), ((Continuation)object1));
            }

            public final Object invoke(ProducerScope producerScope0, Continuation continuation0) {
                return ((kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.zip.2)this.create(producerScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object object0) {
                Object object6;
                Object object5;
                Object object4;
                Throwable throwable3;
                Throwable throwable2;
                ReceiveChannel receiveChannel2;
                Function2 function24;
                ChannelIterator channelIterator9;
                ProducerScope producerScope5;
                Object object3;
                Throwable throwable1;
                ChannelIterator channelIterator6;
                ReceiveChannel receiveChannel1;
                Function2 function22;
                ChannelIterator channelIterator5;
                ProducerScope producerScope3;
                ProducerScope producerScope1;
                ChannelIterator channelIterator2;
                ChannelIterator channelIterator1;
                Function2 function20;
                ReceiveChannel receiveChannel0;
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(this.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        ProducerScope producerScope0 = (ProducerScope)this.L$0;
                        ChannelIterator channelIterator0 = this.$other.iterator();
                        receiveChannel0 = this.$this_zip;
                        function20 = this.$transform;
                        try {
                            channelIterator1 = channelIterator0;
                            channelIterator2 = receiveChannel0.iterator();
                            producerScope1 = producerScope0;
                            goto label_46;
                        }
                        catch(Throwable throwable0) {
                            break;
                        }
                    }
                    case 1: {
                        ChannelIterator channelIterator3 = (ChannelIterator)this.L$4;
                        receiveChannel0 = (ReceiveChannel)this.L$3;
                        Function2 function21 = (Function2)this.L$2;
                        ChannelIterator channelIterator4 = (ChannelIterator)this.L$1;
                        ProducerScope producerScope2 = (ProducerScope)this.L$0;
                        try {
                            ResultKt.throwOnFailure(object0);
                            producerScope3 = producerScope2;
                            channelIterator5 = channelIterator4;
                            function22 = function21;
                            receiveChannel1 = receiveChannel0;
                            channelIterator6 = channelIterator3;
                            throwable1 = null;
                            goto label_66;
                        }
                        catch(Throwable throwable0) {
                            break;
                        }
                    }
                    case 2: {
                        Object object2 = this.L$5;
                        ChannelIterator channelIterator7 = (ChannelIterator)this.L$4;
                        receiveChannel1 = (ReceiveChannel)this.L$3;
                        Function2 function23 = (Function2)this.L$2;
                        ChannelIterator channelIterator8 = (ChannelIterator)this.L$1;
                        ProducerScope producerScope4 = (ProducerScope)this.L$0;
                        try {
                            ResultKt.throwOnFailure(object0);
                            object3 = object2;
                            channelIterator2 = channelIterator7;
                            producerScope5 = producerScope4;
                            channelIterator9 = channelIterator8;
                            function24 = function23;
                            receiveChannel2 = receiveChannel1;
                            throwable2 = null;
                            goto label_89;
                        }
                        catch(Throwable throwable0) {
                            receiveChannel0 = receiveChannel1;
                            throw throwable0;
                        }
                    }
                    case 3: {
                        channelIterator2 = (ChannelIterator)this.L$4;
                        receiveChannel0 = (ReceiveChannel)this.L$3;
                        function20 = (Function2)this.L$2;
                        channelIterator1 = (ChannelIterator)this.L$1;
                        producerScope1 = (ProducerScope)this.L$0;
                        try {
                            ResultKt.throwOnFailure(object0);
                        label_46:
                            throwable3 = null;
                            while(true) {
                            label_47:
                                this.L$0 = producerScope1;
                                this.L$1 = channelIterator1;
                                this.L$2 = function20;
                                this.L$3 = receiveChannel0;
                                this.L$4 = channelIterator2;
                                this.L$5 = null;
                                this.label = 1;
                                object4 = channelIterator2.hasNext(this);
                                goto label_58;
                            }
                        }
                        catch(Throwable throwable0) {
                            break;
                        }
                    }
                    default: {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    }
                }
                throw throwable0;
            label_58:
                if(object4 != object1) {
                    throwable1 = throwable3;
                    object0 = object4;
                    producerScope3 = producerScope1;
                    channelIterator5 = channelIterator1;
                    function22 = function20;
                    receiveChannel1 = receiveChannel0;
                    channelIterator6 = channelIterator2;
                    try {
                    label_66:
                        if(!((Boolean)object0).booleanValue()) {
                            goto label_111;
                        }
                        object5 = channelIterator6.next();
                        this.L$0 = producerScope3;
                        this.L$1 = channelIterator5;
                        this.L$2 = function22;
                        this.L$3 = receiveChannel1;
                        this.L$4 = channelIterator6;
                        this.L$5 = object5;
                        this.label = 2;
                        object6 = channelIterator5.hasNext(this);
                    }
                    catch(Throwable throwable0) {
                        receiveChannel0 = receiveChannel1;
                        throw throwable0;
                    }
                    if(object6 == object1) {
                        return object1;
                    }
                    object3 = object5;
                    object0 = object6;
                    producerScope5 = producerScope3;
                    channelIterator9 = channelIterator5;
                    function24 = function22;
                    receiveChannel2 = receiveChannel1;
                    throwable2 = throwable1;
                    channelIterator2 = channelIterator6;
                    try {
                    label_89:
                        if(((Boolean)object0).booleanValue()) {
                            Object object7 = function24.invoke(object3, channelIterator9.next());
                            this.L$0 = producerScope5;
                            this.L$1 = channelIterator9;
                            this.L$2 = function24;
                            this.L$3 = receiveChannel2;
                            this.L$4 = channelIterator2;
                            this.L$5 = null;
                            this.label = 3;
                            if(producerScope5.send(object7, this) != object1) {
                                goto label_99;
                            }
                            return object1;
                        }
                    label_99:
                        throwable3 = throwable2;
                        receiveChannel0 = receiveChannel2;
                        function20 = function24;
                        channelIterator1 = channelIterator9;
                        producerScope1 = producerScope5;
                        goto label_47;
                    }
                    catch(Throwable throwable0) {
                        receiveChannel0 = receiveChannel2;
                    }
                    try {
                        throw throwable0;
                    }
                    catch(Throwable throwable4) {
                        ChannelsKt.cancelConsumed(receiveChannel0, throwable0);
                        throw throwable4;
                    }
                label_111:
                    ChannelsKt.cancelConsumed(receiveChannel1, throwable1);
                    return Unit.INSTANCE;
                }
                return object1;
            }
        };
        return ProduceKt.produce$default(() -> EmptyCoroutineContext.INSTANCE, coroutineContext0, 0, null, function10, channelsKt__DeprecatedKt$zip$20, 6, null);
    }

    public static ReceiveChannel zip$default(ReceiveChannel receiveChannel0, ReceiveChannel receiveChannel1, CoroutineContext coroutineContext0, Function2 function20, int v, Object object0) {
        if((v & 2) != 0) {
            coroutineContext0 = Dispatchers.getUnconfined();
        }
        return ChannelsKt.zip(receiveChannel0, receiveChannel1, coroutineContext0, function20);
    }
}

