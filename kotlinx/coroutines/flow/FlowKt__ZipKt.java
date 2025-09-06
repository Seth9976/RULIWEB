package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.functions.Function6;
import kotlin.jvm.functions.Function7;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.flow.internal.CombineKt;

@Metadata(d1 = {"\u0000d\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u001C\n\u0002\b\u000B\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0006\u001Aq\u0010\u0000\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0006\b\u0000\u0010\u0003\u0018\u0001\"\u0004\b\u0001\u0010\u00022\u001E\u0010\u0004\u001A\u0010\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u0002H\u00030\u00010\u0005\"\b\u0012\u0004\u0012\u0002H\u00030\u00012*\b\u0004\u0010\u0006\u001A$\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0007H\u0086\b\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\n\u001Ae\u0010\u0000\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0006\b\u0000\u0010\u0003\u0018\u0001\"\u0004\b\u0001\u0010\u00022\u0012\u0010\u0004\u001A\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u00010\u000B2*\b\u0004\u0010\u0006\u001A$\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0007H\u0086\b\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\f\u001A\u00BA\u0001\u0010\u0000\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\r\"\u0004\b\u0001\u0010\u000E\"\u0004\b\u0002\u0010\u000F\"\u0004\b\u0003\u0010\u0010\"\u0004\b\u0004\u0010\u0011\"\u0004\b\u0005\u0010\u00022\f\u0010\u0012\u001A\b\u0012\u0004\u0012\u0002H\r0\u00012\f\u0010\u0013\u001A\b\u0012\u0004\u0012\u0002H\u000E0\u00012\f\u0010\u0014\u001A\b\u0012\u0004\u0012\u0002H\u000F0\u00012\f\u0010\u0015\u001A\b\u0012\u0004\u0012\u0002H\u00100\u00012\f\u0010\u0016\u001A\b\u0012\u0004\u0012\u0002H\u00110\u00012:\u0010\u0006\u001A6\b\u0001\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u0002H\u000E\u0012\u0004\u0012\u0002H\u000F\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H\u0011\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0017\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u0018\u001A\u00A0\u0001\u0010\u0000\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\r\"\u0004\b\u0001\u0010\u000E\"\u0004\b\u0002\u0010\u000F\"\u0004\b\u0003\u0010\u0010\"\u0004\b\u0004\u0010\u00022\f\u0010\u0012\u001A\b\u0012\u0004\u0012\u0002H\r0\u00012\f\u0010\u0013\u001A\b\u0012\u0004\u0012\u0002H\u000E0\u00012\f\u0010\u0014\u001A\b\u0012\u0004\u0012\u0002H\u000F0\u00012\f\u0010\u0015\u001A\b\u0012\u0004\u0012\u0002H\u00100\u000124\u0010\u0006\u001A0\b\u0001\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u0002H\u000E\u0012\u0004\u0012\u0002H\u000F\u0012\u0004\u0012\u0002H\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0019\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u001A\u001A\u0088\u0001\u0010\u0000\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\r\"\u0004\b\u0001\u0010\u000E\"\u0004\b\u0002\u0010\u000F\"\u0004\b\u0003\u0010\u00022\f\u0010\u0012\u001A\b\u0012\u0004\u0012\u0002H\r0\u00012\f\u0010\u0013\u001A\b\u0012\u0004\u0012\u0002H\u000E0\u00012\f\u0010\u0014\u001A\b\u0012\u0004\u0012\u0002H\u000F0\u000120\b\u0001\u0010\u0006\u001A*\b\u0001\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u0002H\u000E\u0012\u0004\u0012\u0002H\u000F\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u001B\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u001C\u001A\u008A\u0001\u0010\u0000\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\r\"\u0004\b\u0001\u0010\u000E\"\u0004\b\u0002\u0010\u00022\f\u0010\u0012\u001A\b\u0012\u0004\u0012\u0002H\r0\u00012\f\u0010\u0013\u001A\b\u0012\u0004\u0012\u0002H\u000E0\u00012F\u0010\u0006\u001AB\b\u0001\u0012\u0013\u0012\u0011H\r\u00A2\u0006\f\b\u001E\u0012\b\b\u001F\u0012\u0004\b\b( \u0012\u0013\u0012\u0011H\u000E\u00A2\u0006\f\b\u001E\u0012\b\b\u001F\u0012\u0004\b\b(!\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u001D\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\"\u001A\u0082\u0001\u0010#\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0006\b\u0000\u0010\u0003\u0018\u0001\"\u0004\b\u0001\u0010\u00022\u001E\u0010\u0004\u001A\u0010\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u0002H\u00030\u00010\u0005\"\b\u0012\u0004\u0012\u0002H\u00030\u00012;\b\u0005\u0010\u0006\u001A5\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020$\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020%0\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u001D\u00A2\u0006\u0002\b&H\u0086\b\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\'\u001Av\u0010#\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0006\b\u0000\u0010\u0003\u0018\u0001\"\u0004\b\u0001\u0010\u00022\u0012\u0010\u0004\u001A\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u00010\u000B2;\b\u0005\u0010\u0006\u001A5\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020$\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020%0\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u001D\u00A2\u0006\u0002\b&H\u0086\b\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010(\u001A\u00CD\u0001\u0010#\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\r\"\u0004\b\u0001\u0010\u000E\"\u0004\b\u0002\u0010\u000F\"\u0004\b\u0003\u0010\u0010\"\u0004\b\u0004\u0010\u0011\"\u0004\b\u0005\u0010\u00022\f\u0010\u0012\u001A\b\u0012\u0004\u0012\u0002H\r0\u00012\f\u0010\u0013\u001A\b\u0012\u0004\u0012\u0002H\u000E0\u00012\f\u0010\u0014\u001A\b\u0012\u0004\u0012\u0002H\u000F0\u00012\f\u0010\u0015\u001A\b\u0012\u0004\u0012\u0002H\u00100\u00012\f\u0010\u0016\u001A\b\u0012\u0004\u0012\u0002H\u00110\u00012M\b\u0001\u0010\u0006\u001AG\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020$\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u0002H\u000E\u0012\u0004\u0012\u0002H\u000F\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H\u0011\u0012\n\u0012\b\u0012\u0004\u0012\u00020%0\b\u0012\u0006\u0012\u0004\u0018\u00010\t0)\u00A2\u0006\u0002\b&\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010*\u001A\u00B3\u0001\u0010#\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\r\"\u0004\b\u0001\u0010\u000E\"\u0004\b\u0002\u0010\u000F\"\u0004\b\u0003\u0010\u0010\"\u0004\b\u0004\u0010\u00022\f\u0010\u0012\u001A\b\u0012\u0004\u0012\u0002H\r0\u00012\f\u0010\u0013\u001A\b\u0012\u0004\u0012\u0002H\u000E0\u00012\f\u0010\u0014\u001A\b\u0012\u0004\u0012\u0002H\u000F0\u00012\f\u0010\u0015\u001A\b\u0012\u0004\u0012\u0002H\u00100\u00012G\b\u0001\u0010\u0006\u001AA\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020$\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u0002H\u000E\u0012\u0004\u0012\u0002H\u000F\u0012\u0004\u0012\u0002H\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020%0\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0017\u00A2\u0006\u0002\b&\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010+\u001A\u0099\u0001\u0010#\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\r\"\u0004\b\u0001\u0010\u000E\"\u0004\b\u0002\u0010\u000F\"\u0004\b\u0003\u0010\u00022\f\u0010\u0012\u001A\b\u0012\u0004\u0012\u0002H\r0\u00012\f\u0010\u0013\u001A\b\u0012\u0004\u0012\u0002H\u000E0\u00012\f\u0010\u0014\u001A\b\u0012\u0004\u0012\u0002H\u000F0\u00012A\b\u0001\u0010\u0006\u001A;\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020$\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u0002H\u000E\u0012\u0004\u0012\u0002H\u000F\u0012\n\u0012\b\u0012\u0004\u0012\u00020%0\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0019\u00A2\u0006\u0002\b&\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010,\u001A\u009D\u0001\u0010#\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\r\"\u0004\b\u0001\u0010\u000E\"\u0004\b\u0002\u0010\u00022\f\u0010\u0012\u001A\b\u0012\u0004\u0012\u0002H\r0\u00012\f\u0010\u0013\u001A\b\u0012\u0004\u0012\u0002H\u000E0\u00012Y\b\u0001\u0010\u0006\u001AS\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020$\u0012\u0013\u0012\u0011H\r\u00A2\u0006\f\b\u001E\u0012\b\b\u001F\u0012\u0004\b\b( \u0012\u0013\u0012\u0011H\u000E\u00A2\u0006\f\b\u001E\u0012\b\b\u001F\u0012\u0004\b\b(!\u0012\n\u0012\b\u0012\u0004\u0012\u00020%0\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u001B\u00A2\u0006\u0002\b&\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010-\u001A\u0084\u0001\u0010.\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0006\b\u0000\u0010\u0003\u0018\u0001\"\u0004\b\u0001\u0010\u00022\u001E\u0010\u0004\u001A\u0010\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u0002H\u00030\u00010\u0005\"\b\u0012\u0004\u0012\u0002H\u00030\u00012;\b\u0005\u0010\u0006\u001A5\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020$\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020%0\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u001D\u00A2\u0006\u0002\b&H\u0082\b\u00F8\u0001\u0000\u00A2\u0006\u0004\b/\u0010\'\u001As\u00100\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0006\b\u0000\u0010\u0003\u0018\u0001\"\u0004\b\u0001\u0010\u00022\u001E\u0010\u0004\u001A\u0010\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u0002H\u00030\u00010\u0005\"\b\u0012\u0004\u0012\u0002H\u00030\u00012*\b\u0004\u0010\u0006\u001A$\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0007H\u0082\b\u00F8\u0001\u0000\u00A2\u0006\u0004\b1\u0010\n\u001A!\u00102\u001A\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u0002H\u0003\u0018\u00010\u000503\"\u0004\b\u0000\u0010\u0003H\u0002\u00A2\u0006\u0002\b4\u001A\u008A\u0001\u0010\u0000\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\r\"\u0004\b\u0001\u0010\u000E\"\u0004\b\u0002\u0010\u0002*\b\u0012\u0004\u0012\u0002H\r0\u00012\f\u0010\u0012\u001A\b\u0012\u0004\u0012\u0002H\u000E0\u00012F\u0010\u0006\u001AB\b\u0001\u0012\u0013\u0012\u0011H\r\u00A2\u0006\f\b\u001E\u0012\b\b\u001F\u0012\u0004\b\b( \u0012\u0013\u0012\u0011H\u000E\u00A2\u0006\f\b\u001E\u0012\b\b\u001F\u0012\u0004\b\b(!\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u001DH\u0007\u00F8\u0001\u0000\u00A2\u0006\u0004\b5\u0010\"\u001A\u009D\u0001\u0010#\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\r\"\u0004\b\u0001\u0010\u000E\"\u0004\b\u0002\u0010\u0002*\b\u0012\u0004\u0012\u0002H\r0\u00012\f\u0010\u0012\u001A\b\u0012\u0004\u0012\u0002H\u000E0\u00012Y\b\u0001\u0010\u0006\u001AS\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020$\u0012\u0013\u0012\u0011H\r\u00A2\u0006\f\b\u001E\u0012\b\b\u001F\u0012\u0004\b\b( \u0012\u0013\u0012\u0011H\u000E\u00A2\u0006\f\b\u001E\u0012\b\b\u001F\u0012\u0004\b\b(!\u0012\n\u0012\b\u0012\u0004\u0012\u00020%0\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u001B\u00A2\u0006\u0002\b&H\u0007\u00F8\u0001\u0000\u00A2\u0006\u0004\b6\u0010-\u001Ah\u00107\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\r\"\u0004\b\u0001\u0010\u000E\"\u0004\b\u0002\u0010\u0002*\b\u0012\u0004\u0012\u0002H\r0\u00012\f\u00108\u001A\b\u0012\u0004\u0012\u0002H\u000E0\u00012(\u0010\u0006\u001A$\b\u0001\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u0002H\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u001D\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\"\u0082\u0002\u0004\n\u0002\b\u0019\u00A8\u00069"}, d2 = {"combine", "Lkotlinx/coroutines/flow/Flow;", "R", "T", "flows", "", "transform", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "", "([Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/flow/Flow;", "", "(Ljava/lang/Iterable;Lkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/flow/Flow;", "T1", "T2", "T3", "T4", "T5", "flow", "flow2", "flow3", "flow4", "flow5", "Lkotlin/Function6;", "(Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function6;)Lkotlinx/coroutines/flow/Flow;", "Lkotlin/Function5;", "(Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function5;)Lkotlinx/coroutines/flow/Flow;", "Lkotlin/Function4;", "(Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function4;)Lkotlinx/coroutines/flow/Flow;", "Lkotlin/Function3;", "Lkotlin/ParameterName;", "name", "a", "b", "(Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function3;)Lkotlinx/coroutines/flow/Flow;", "combineTransform", "Lkotlinx/coroutines/flow/FlowCollector;", "", "Lkotlin/ExtensionFunctionType;", "([Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function3;)Lkotlinx/coroutines/flow/Flow;", "(Ljava/lang/Iterable;Lkotlin/jvm/functions/Function3;)Lkotlinx/coroutines/flow/Flow;", "Lkotlin/Function7;", "(Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function7;)Lkotlinx/coroutines/flow/Flow;", "(Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function6;)Lkotlinx/coroutines/flow/Flow;", "(Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function5;)Lkotlinx/coroutines/flow/Flow;", "(Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function4;)Lkotlinx/coroutines/flow/Flow;", "combineTransformUnsafe", "combineTransformUnsafe$FlowKt__ZipKt", "combineUnsafe", "combineUnsafe$FlowKt__ZipKt", "nullArrayFactory", "Lkotlin/Function0;", "nullArrayFactory$FlowKt__ZipKt", "flowCombine", "flowCombineTransform", "zip", "other", "kotlinx-coroutines-core"}, k = 5, mv = {1, 8, 0}, xi = 0x30, xs = "kotlinx/coroutines/flow/FlowKt")
final class FlowKt__ZipKt {
    public static final Flow combine(Iterable iterable0, Function2 function20) {
        Flow[] arr_flow = (Flow[])CollectionsKt.toList(iterable0).toArray(new Flow[0]);
        Intrinsics.needClassReification();
        return new Flow() {
            @Override  // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector flowCollector0, Continuation continuation0) {
                Intrinsics.needClassReification();
                kotlinx.coroutines.flow.FlowKt__ZipKt.combine.6.1 flowKt__ZipKt$combine$6$10 = new kotlinx.coroutines.flow.FlowKt__ZipKt.combine.6.1(arr_flow);
                Intrinsics.needClassReification();
                kotlinx.coroutines.flow.FlowKt__ZipKt.combine.6.2 flowKt__ZipKt$combine$6$20 = new kotlinx.coroutines.flow.FlowKt__ZipKt.combine.6.2(function20, null);
                Object object0 = CombineKt.combineInternal(flowCollector0, arr_flow, flowKt__ZipKt$combine$6$10, flowKt__ZipKt$combine$6$20, continuation0);
                return object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? object0 : Unit.INSTANCE;

                @Metadata(k = 3, mv = {1, 8, 0}, xi = 0xB0)
                public final class kotlinx.coroutines.flow.FlowKt__ZipKt.combine..inlined.unsafeFlow.3.1 extends ContinuationImpl {
                    int label;
                    Object result;

                    public kotlinx.coroutines.flow.FlowKt__ZipKt.combine..inlined.unsafeFlow.3.1(kotlinx.coroutines.flow.FlowKt__ZipKt.combine..inlined.unsafeFlow.3 flowKt__ZipKt$combine$$inlined$unsafeFlow$30, Continuation continuation0) {
                    }

                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object object0) {
                        this.result = object0;
                        this.label |= 0x80000000;
                        return kotlinx.coroutines.flow.FlowKt__ZipKt.combine..inlined.unsafeFlow.3.this.collect(null, this);
                    }
                }

            }

            public Object collect$$forInline(FlowCollector flowCollector0, Continuation continuation0) {
                new kotlinx.coroutines.flow.FlowKt__ZipKt.combine..inlined.unsafeFlow.3.1(this, continuation0);
                Intrinsics.needClassReification();
                kotlinx.coroutines.flow.FlowKt__ZipKt.combine.6.1 flowKt__ZipKt$combine$6$10 = new kotlinx.coroutines.flow.FlowKt__ZipKt.combine.6.1(arr_flow);
                Intrinsics.needClassReification();
                kotlinx.coroutines.flow.FlowKt__ZipKt.combine.6.2 flowKt__ZipKt$combine$6$20 = new kotlinx.coroutines.flow.FlowKt__ZipKt.combine.6.2(function20, null);
                CombineKt.combineInternal(flowCollector0, arr_flow, flowKt__ZipKt$combine$6$10, flowKt__ZipKt$combine$6$20, continuation0);
                return Unit.INSTANCE;
            }
        };

        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0004\u0010\u0000\u001A\f\u0012\u0006\u0012\u0004\u0018\u0001H\u0002\u0018\u00010\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0004\b\u0001\u0010\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "T", "R", "invoke", "()[Ljava/lang/Object;"}, k = 3, mv = {1, 8, 0}, xi = 0xB0)
        public final class kotlinx.coroutines.flow.FlowKt__ZipKt.combine.6.1 extends Lambda implements Function0 {
            final Flow[] $flowArray;

            public kotlinx.coroutines.flow.FlowKt__ZipKt.combine.6.1(Flow[] arr_flow) {
                this.$flowArray = arr_flow;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final Object[] invoke() {
                Intrinsics.reifiedOperationMarker(0, "T?");
                return new Object[this.$flowArray.length];
            }
        }


        @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\u0010\u0000\u001A\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0004\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u00042\f\u0010\u0005\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0006H\u008A@"}, d2 = {"<anonymous>", "", "T", "R", "Lkotlinx/coroutines/flow/FlowCollector;", "it", ""}, k = 3, mv = {1, 8, 0}, xi = 0xB0)
        @DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__ZipKt$combine$6$2", f = "Zip.kt", i = {}, l = {292, 292}, m = "invokeSuspend", n = {}, s = {})
        public final class kotlinx.coroutines.flow.FlowKt__ZipKt.combine.6.2 extends SuspendLambda implements Function3 {
            final Function2 $transform;
            private Object L$0;
            Object L$1;
            int label;

            public kotlinx.coroutines.flow.FlowKt__ZipKt.combine.6.2(Function2 function20, Continuation continuation0) {
                this.$transform = function20;
                super(3, continuation0);
            }

            @Override  // kotlin.jvm.functions.Function3
            public Object invoke(Object object0, Object object1, Object object2) {
                return this.invoke(((FlowCollector)object0), ((Object[])object1), ((Continuation)object2));
            }

            public final Object invoke(FlowCollector flowCollector0, Object[] arr_object, Continuation continuation0) {
                Intrinsics.needClassReification();
                kotlinx.coroutines.flow.FlowKt__ZipKt.combine.6.2 flowKt__ZipKt$combine$6$20 = new kotlinx.coroutines.flow.FlowKt__ZipKt.combine.6.2(this.$transform, continuation0);
                flowKt__ZipKt$combine$6$20.L$0 = flowCollector0;
                flowKt__ZipKt$combine$6$20.L$1 = arr_object;
                return flowKt__ZipKt$combine$6$20.invokeSuspend(Unit.INSTANCE);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object object0) {
                FlowCollector flowCollector0;
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(this.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        flowCollector0 = (FlowCollector)this.L$0;
                        this.L$0 = flowCollector0;
                        this.label = 1;
                        object0 = this.$transform.invoke(((Object[])this.L$1), this);
                        if(object0 == object1) {
                            return object1;
                        }
                        break;
                    }
                    case 1: {
                        flowCollector0 = (FlowCollector)this.L$0;
                        ResultKt.throwOnFailure(object0);
                        break;
                    }
                    case 2: {
                        ResultKt.throwOnFailure(object0);
                        return Unit.INSTANCE;
                    }
                    default: {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    }
                }
                this.L$0 = null;
                this.label = 2;
                return flowCollector0.emit(object0, this) == object1 ? object1 : Unit.INSTANCE;
            }

            public final Object invokeSuspend$$forInline(Object object0) {
                ((FlowCollector)this.L$0).emit(this.$transform.invoke(((Object[])this.L$1), this), this);
                return Unit.INSTANCE;
            }
        }

    }

    public static final Flow combine(Flow flow0, Flow flow1, Function3 function30) {
        return FlowKt.flowCombine(flow0, flow1, function30);
    }

    public static final Flow combine(Flow flow0, Flow flow1, Flow flow2, Function4 function40) {
        return new Flow() {
            @Override  // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector flowCollector0, Continuation continuation0) {
                kotlinx.coroutines.flow.FlowKt__ZipKt.combine..inlined.combineUnsafe.FlowKt__ZipKt.1.2 flowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1$20 = new Function3(function40) {
                    final Function4 $transform$inlined;
                    private Object L$0;
                    Object L$1;
                    int label;

                    {
                        this.$transform$inlined = function40;
                        super(3, continuation0);
                    }

                    @Override  // kotlin.jvm.functions.Function3
                    public Object invoke(Object object0, Object object1, Object object2) {
                        return this.invoke(((FlowCollector)object0), ((Object[])object1), ((Continuation)object2));
                    }

                    public final Object invoke(FlowCollector flowCollector0, Object[] arr_object, Continuation continuation0) {
                        kotlinx.coroutines.flow.FlowKt__ZipKt.combine..inlined.combineUnsafe.FlowKt__ZipKt.1.2 flowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1$20 = new kotlinx.coroutines.flow.FlowKt__ZipKt.combine..inlined.combineUnsafe.FlowKt__ZipKt.1.2(continuation0, this.$transform$inlined);
                        flowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1$20.L$0 = flowCollector0;
                        flowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1$20.L$1 = arr_object;
                        return flowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1$20.invokeSuspend(Unit.INSTANCE);
                    }

                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object object0) {
                        FlowCollector flowCollector0;
                        Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        switch(this.label) {
                            case 0: {
                                ResultKt.throwOnFailure(object0);
                                flowCollector0 = (FlowCollector)this.L$0;
                                Object[] arr_object = (Object[])this.L$1;
                                this.L$0 = flowCollector0;
                                this.label = 1;
                                object0 = this.$transform$inlined.invoke(arr_object[0], arr_object[1], arr_object[2], this);
                                if(object0 == object1) {
                                    return object1;
                                }
                                break;
                            }
                            case 1: {
                                flowCollector0 = (FlowCollector)this.L$0;
                                ResultKt.throwOnFailure(object0);
                                break;
                            }
                            case 2: {
                                ResultKt.throwOnFailure(object0);
                                return Unit.INSTANCE;
                            }
                            default: {
                                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                            }
                        }
                        this.L$0 = null;
                        this.label = 2;
                        return flowCollector0.emit(object0, this) == object1 ? object1 : Unit.INSTANCE;
                    }
                };
                Object object0 = CombineKt.combineInternal(flowCollector0, new Flow[]{flow0, flow1, flow2}, FlowKt__ZipKt.nullArrayFactory$FlowKt__ZipKt(), flowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1$20, continuation0);
                return object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? object0 : Unit.INSTANCE;
            }
        };
    }

    public static final Flow combine(Flow flow0, Flow flow1, Flow flow2, Flow flow3, Function5 function50) {
        return new Flow() {
            @Override  // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector flowCollector0, Continuation continuation0) {
                kotlinx.coroutines.flow.FlowKt__ZipKt.combine..inlined.combineUnsafe.FlowKt__ZipKt.2.2 flowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$2$20 = new Function3(function50) {
                    final Function5 $transform$inlined;
                    private Object L$0;
                    Object L$1;
                    int label;

                    {
                        this.$transform$inlined = function50;
                        super(3, continuation0);
                    }

                    @Override  // kotlin.jvm.functions.Function3
                    public Object invoke(Object object0, Object object1, Object object2) {
                        return this.invoke(((FlowCollector)object0), ((Object[])object1), ((Continuation)object2));
                    }

                    public final Object invoke(FlowCollector flowCollector0, Object[] arr_object, Continuation continuation0) {
                        kotlinx.coroutines.flow.FlowKt__ZipKt.combine..inlined.combineUnsafe.FlowKt__ZipKt.2.2 flowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$2$20 = new kotlinx.coroutines.flow.FlowKt__ZipKt.combine..inlined.combineUnsafe.FlowKt__ZipKt.2.2(continuation0, this.$transform$inlined);
                        flowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$2$20.L$0 = flowCollector0;
                        flowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$2$20.L$1 = arr_object;
                        return flowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$2$20.invokeSuspend(Unit.INSTANCE);
                    }

                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object object0) {
                        FlowCollector flowCollector0;
                        Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        switch(this.label) {
                            case 0: {
                                ResultKt.throwOnFailure(object0);
                                flowCollector0 = (FlowCollector)this.L$0;
                                Object[] arr_object = (Object[])this.L$1;
                                this.L$0 = flowCollector0;
                                this.label = 1;
                                object0 = this.$transform$inlined.invoke(arr_object[0], arr_object[1], arr_object[2], arr_object[3], this);
                                if(object0 == object1) {
                                    return object1;
                                }
                                break;
                            }
                            case 1: {
                                flowCollector0 = (FlowCollector)this.L$0;
                                ResultKt.throwOnFailure(object0);
                                break;
                            }
                            case 2: {
                                ResultKt.throwOnFailure(object0);
                                return Unit.INSTANCE;
                            }
                            default: {
                                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                            }
                        }
                        this.L$0 = null;
                        this.label = 2;
                        return flowCollector0.emit(object0, this) == object1 ? object1 : Unit.INSTANCE;
                    }
                };
                Object object0 = CombineKt.combineInternal(flowCollector0, new Flow[]{flow0, flow1, flow2, flow3}, FlowKt__ZipKt.nullArrayFactory$FlowKt__ZipKt(), flowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$2$20, continuation0);
                return object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? object0 : Unit.INSTANCE;
            }
        };
    }

    public static final Flow combine(Flow flow0, Flow flow1, Flow flow2, Flow flow3, Flow flow4, Function6 function60) {
        return new Flow() {
            @Override  // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector flowCollector0, Continuation continuation0) {
                kotlinx.coroutines.flow.FlowKt__ZipKt.combine..inlined.combineUnsafe.FlowKt__ZipKt.3.2 flowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$3$20 = new Function3(function60) {
                    final Function6 $transform$inlined;
                    private Object L$0;
                    Object L$1;
                    int label;

                    {
                        this.$transform$inlined = function60;
                        super(3, continuation0);
                    }

                    @Override  // kotlin.jvm.functions.Function3
                    public Object invoke(Object object0, Object object1, Object object2) {
                        return this.invoke(((FlowCollector)object0), ((Object[])object1), ((Continuation)object2));
                    }

                    public final Object invoke(FlowCollector flowCollector0, Object[] arr_object, Continuation continuation0) {
                        kotlinx.coroutines.flow.FlowKt__ZipKt.combine..inlined.combineUnsafe.FlowKt__ZipKt.3.2 flowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$3$20 = new kotlinx.coroutines.flow.FlowKt__ZipKt.combine..inlined.combineUnsafe.FlowKt__ZipKt.3.2(continuation0, this.$transform$inlined);
                        flowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$3$20.L$0 = flowCollector0;
                        flowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$3$20.L$1 = arr_object;
                        return flowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$3$20.invokeSuspend(Unit.INSTANCE);
                    }

                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object object0) {
                        FlowCollector flowCollector0;
                        Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        switch(this.label) {
                            case 0: {
                                ResultKt.throwOnFailure(object0);
                                flowCollector0 = (FlowCollector)this.L$0;
                                Object[] arr_object = (Object[])this.L$1;
                                this.L$0 = flowCollector0;
                                this.label = 1;
                                object0 = this.$transform$inlined.invoke(arr_object[0], arr_object[1], arr_object[2], arr_object[3], arr_object[4], this);
                                if(object0 == object1) {
                                    return object1;
                                }
                                break;
                            }
                            case 1: {
                                flowCollector0 = (FlowCollector)this.L$0;
                                ResultKt.throwOnFailure(object0);
                                break;
                            }
                            case 2: {
                                ResultKt.throwOnFailure(object0);
                                return Unit.INSTANCE;
                            }
                            default: {
                                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                            }
                        }
                        this.L$0 = null;
                        this.label = 2;
                        return flowCollector0.emit(object0, this) == object1 ? object1 : Unit.INSTANCE;
                    }
                };
                Object object0 = CombineKt.combineInternal(flowCollector0, new Flow[]{flow0, flow1, flow2, flow3, flow4}, FlowKt__ZipKt.nullArrayFactory$FlowKt__ZipKt(), flowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$3$20, continuation0);
                return object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? object0 : Unit.INSTANCE;
            }
        };
    }

    public static final Flow combine(Flow[] arr_flow, Function2 function20) {
        Intrinsics.needClassReification();
        return new Flow() {
            @Override  // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector flowCollector0, Continuation continuation0) {
                Intrinsics.needClassReification();
                kotlinx.coroutines.flow.FlowKt__ZipKt.combine.5.1 flowKt__ZipKt$combine$5$10 = new kotlinx.coroutines.flow.FlowKt__ZipKt.combine.5.1(arr_flow);
                Intrinsics.needClassReification();
                kotlinx.coroutines.flow.FlowKt__ZipKt.combine.5.2 flowKt__ZipKt$combine$5$20 = new kotlinx.coroutines.flow.FlowKt__ZipKt.combine.5.2(function20, null);
                Object object0 = CombineKt.combineInternal(flowCollector0, arr_flow, flowKt__ZipKt$combine$5$10, flowKt__ZipKt$combine$5$20, continuation0);
                return object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? object0 : Unit.INSTANCE;

                @Metadata(k = 3, mv = {1, 8, 0}, xi = 0xB0)
                public final class kotlinx.coroutines.flow.FlowKt__ZipKt.combine..inlined.unsafeFlow.2.1 extends ContinuationImpl {
                    int label;
                    Object result;

                    public kotlinx.coroutines.flow.FlowKt__ZipKt.combine..inlined.unsafeFlow.2.1(kotlinx.coroutines.flow.FlowKt__ZipKt.combine..inlined.unsafeFlow.2 flowKt__ZipKt$combine$$inlined$unsafeFlow$20, Continuation continuation0) {
                    }

                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object object0) {
                        this.result = object0;
                        this.label |= 0x80000000;
                        return kotlinx.coroutines.flow.FlowKt__ZipKt.combine..inlined.unsafeFlow.2.this.collect(null, this);
                    }
                }

            }

            public Object collect$$forInline(FlowCollector flowCollector0, Continuation continuation0) {
                new kotlinx.coroutines.flow.FlowKt__ZipKt.combine..inlined.unsafeFlow.2.1(this, continuation0);
                Intrinsics.needClassReification();
                kotlinx.coroutines.flow.FlowKt__ZipKt.combine.5.1 flowKt__ZipKt$combine$5$10 = new kotlinx.coroutines.flow.FlowKt__ZipKt.combine.5.1(arr_flow);
                Intrinsics.needClassReification();
                kotlinx.coroutines.flow.FlowKt__ZipKt.combine.5.2 flowKt__ZipKt$combine$5$20 = new kotlinx.coroutines.flow.FlowKt__ZipKt.combine.5.2(function20, null);
                CombineKt.combineInternal(flowCollector0, arr_flow, flowKt__ZipKt$combine$5$10, flowKt__ZipKt$combine$5$20, continuation0);
                return Unit.INSTANCE;
            }
        };

        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0004\u0010\u0000\u001A\f\u0012\u0006\u0012\u0004\u0018\u0001H\u0002\u0018\u00010\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0004\b\u0001\u0010\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "T", "R", "invoke", "()[Ljava/lang/Object;"}, k = 3, mv = {1, 8, 0}, xi = 0xB0)
        public final class kotlinx.coroutines.flow.FlowKt__ZipKt.combine.5.1 extends Lambda implements Function0 {
            final Flow[] $flows;

            public kotlinx.coroutines.flow.FlowKt__ZipKt.combine.5.1(Flow[] arr_flow) {
                this.$flows = arr_flow;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final Object[] invoke() {
                Intrinsics.reifiedOperationMarker(0, "T?");
                return new Object[this.$flows.length];
            }
        }


        @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\u0010\u0000\u001A\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0004\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u00042\f\u0010\u0005\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0006H\u008A@"}, d2 = {"<anonymous>", "", "T", "R", "Lkotlinx/coroutines/flow/FlowCollector;", "it", ""}, k = 3, mv = {1, 8, 0}, xi = 0xB0)
        @DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__ZipKt$combine$5$2", f = "Zip.kt", i = {}, l = {0xEE, 0xEE}, m = "invokeSuspend", n = {}, s = {})
        public final class kotlinx.coroutines.flow.FlowKt__ZipKt.combine.5.2 extends SuspendLambda implements Function3 {
            final Function2 $transform;
            private Object L$0;
            Object L$1;
            int label;

            public kotlinx.coroutines.flow.FlowKt__ZipKt.combine.5.2(Function2 function20, Continuation continuation0) {
                this.$transform = function20;
                super(3, continuation0);
            }

            @Override  // kotlin.jvm.functions.Function3
            public Object invoke(Object object0, Object object1, Object object2) {
                return this.invoke(((FlowCollector)object0), ((Object[])object1), ((Continuation)object2));
            }

            public final Object invoke(FlowCollector flowCollector0, Object[] arr_object, Continuation continuation0) {
                Intrinsics.needClassReification();
                kotlinx.coroutines.flow.FlowKt__ZipKt.combine.5.2 flowKt__ZipKt$combine$5$20 = new kotlinx.coroutines.flow.FlowKt__ZipKt.combine.5.2(this.$transform, continuation0);
                flowKt__ZipKt$combine$5$20.L$0 = flowCollector0;
                flowKt__ZipKt$combine$5$20.L$1 = arr_object;
                return flowKt__ZipKt$combine$5$20.invokeSuspend(Unit.INSTANCE);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object object0) {
                FlowCollector flowCollector0;
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(this.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        flowCollector0 = (FlowCollector)this.L$0;
                        this.L$0 = flowCollector0;
                        this.label = 1;
                        object0 = this.$transform.invoke(((Object[])this.L$1), this);
                        if(object0 == object1) {
                            return object1;
                        }
                        break;
                    }
                    case 1: {
                        flowCollector0 = (FlowCollector)this.L$0;
                        ResultKt.throwOnFailure(object0);
                        break;
                    }
                    case 2: {
                        ResultKt.throwOnFailure(object0);
                        return Unit.INSTANCE;
                    }
                    default: {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    }
                }
                this.L$0 = null;
                this.label = 2;
                return flowCollector0.emit(object0, this) == object1 ? object1 : Unit.INSTANCE;
            }

            public final Object invokeSuspend$$forInline(Object object0) {
                ((FlowCollector)this.L$0).emit(this.$transform.invoke(((Object[])this.L$1), this), this);
                return Unit.INSTANCE;
            }
        }

    }

    public static final Flow combineTransform(Iterable iterable0, Function3 function30) {
        Flow[] arr_flow = (Flow[])CollectionsKt.toList(iterable0).toArray(new Flow[0]);
        Intrinsics.needClassReification();
        return FlowKt.flow(new Function2(arr_flow, function30, null) {
            final Flow[] $flowArray;
            final Function3 $transform;
            private Object L$0;
            int label;

            {
                this.$flowArray = arr_flow;
                this.$transform = function30;
                super(2, continuation0);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object object0, Continuation continuation0) {
                kotlinx.coroutines.flow.FlowKt__ZipKt.combineTransform.7 flowKt__ZipKt$combineTransform$70 = new kotlinx.coroutines.flow.FlowKt__ZipKt.combineTransform.7(this.$flowArray, this.$transform, continuation0);
                flowKt__ZipKt$combineTransform$70.L$0 = object0;
                return flowKt__ZipKt$combineTransform$70;
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(((FlowCollector)object0), ((Continuation)object1));
            }

            public final Object invoke(FlowCollector flowCollector0, Continuation continuation0) {
                return ((kotlinx.coroutines.flow.FlowKt__ZipKt.combineTransform.7)this.create(flowCollector0, continuation0)).invokeSuspend(Unit.INSTANCE);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object object0) {
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(this.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        FlowCollector flowCollector0 = (FlowCollector)this.L$0;
                        Intrinsics.needClassReification();
                        kotlinx.coroutines.flow.FlowKt__ZipKt.combineTransform.7.1 flowKt__ZipKt$combineTransform$7$10 = new Function0() {
                            final Flow[] $flowArray;

                            {
                                this.$flowArray = arr_flow;
                                super(0);
                            }

                            @Override  // kotlin.jvm.functions.Function0
                            public Object invoke() {
                                return this.invoke();
                            }

                            public final Object[] invoke() {
                                Intrinsics.reifiedOperationMarker(0, "T?");
                                return new Object[this.$flowArray.length];
                            }
                        };
                        Intrinsics.needClassReification();
                        kotlinx.coroutines.flow.FlowKt__ZipKt.combineTransform.7.2 flowKt__ZipKt$combineTransform$7$20 = new Function3(null) {
                            final Function3 $transform;
                            private Object L$0;
                            Object L$1;
                            int label;

                            {
                                this.$transform = function30;
                                super(3, continuation0);
                            }

                            @Override  // kotlin.jvm.functions.Function3
                            public Object invoke(Object object0, Object object1, Object object2) {
                                return this.invoke(((FlowCollector)object0), ((Object[])object1), ((Continuation)object2));
                            }

                            public final Object invoke(FlowCollector flowCollector0, Object[] arr_object, Continuation continuation0) {
                                Intrinsics.needClassReification();
                                kotlinx.coroutines.flow.FlowKt__ZipKt.combineTransform.7.2 flowKt__ZipKt$combineTransform$7$20 = new kotlinx.coroutines.flow.FlowKt__ZipKt.combineTransform.7.2(this.$transform, continuation0);
                                flowKt__ZipKt$combineTransform$7$20.L$0 = flowCollector0;
                                flowKt__ZipKt$combineTransform$7$20.L$1 = arr_object;
                                return flowKt__ZipKt$combineTransform$7$20.invokeSuspend(Unit.INSTANCE);
                            }

                            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            public final Object invokeSuspend(Object object0) {
                                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                switch(this.label) {
                                    case 0: {
                                        ResultKt.throwOnFailure(object0);
                                        FlowCollector flowCollector0 = (FlowCollector)this.L$0;
                                        this.L$0 = null;
                                        this.label = 1;
                                        return this.$transform.invoke(flowCollector0, ((Object[])this.L$1), this) == object1 ? object1 : Unit.INSTANCE;
                                    }
                                    case 1: {
                                        ResultKt.throwOnFailure(object0);
                                        return Unit.INSTANCE;
                                    }
                                    default: {
                                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                                    }
                                }
                            }

                            public final Object invokeSuspend$$forInline(Object object0) {
                                this.$transform.invoke(((FlowCollector)this.L$0), ((Object[])this.L$1), this);
                                return Unit.INSTANCE;
                            }
                        };
                        this.label = 1;
                        return CombineKt.combineInternal(flowCollector0, this.$flowArray, flowKt__ZipKt$combineTransform$7$10, flowKt__ZipKt$combineTransform$7$20, this) == object1 ? object1 : Unit.INSTANCE;
                    }
                    case 1: {
                        ResultKt.throwOnFailure(object0);
                        return Unit.INSTANCE;
                    }
                    default: {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    }
                }
            }

            public final Object invokeSuspend$$forInline(Object object0) {
                FlowCollector flowCollector0 = (FlowCollector)this.L$0;
                Intrinsics.needClassReification();
                kotlinx.coroutines.flow.FlowKt__ZipKt.combineTransform.7.1 flowKt__ZipKt$combineTransform$7$10 = new kotlinx.coroutines.flow.FlowKt__ZipKt.combineTransform.7.1(this.$flowArray);
                Intrinsics.needClassReification();
                kotlinx.coroutines.flow.FlowKt__ZipKt.combineTransform.7.2 flowKt__ZipKt$combineTransform$7$20 = new kotlinx.coroutines.flow.FlowKt__ZipKt.combineTransform.7.2(this.$transform, null);
                CombineKt.combineInternal(flowCollector0, this.$flowArray, flowKt__ZipKt$combineTransform$7$10, flowKt__ZipKt$combineTransform$7$20, this);
                return Unit.INSTANCE;
            }
        });
    }

    public static final Flow combineTransform(Flow flow0, Flow flow1, Function4 function40) {
        return FlowKt.flow(new Function2(new Flow[]{flow0, flow1}, null, function40) {
            final Flow[] $flows;
            final Function4 $transform$inlined;
            private Object L$0;
            int label;

            {
                this.$flows = arr_flow;
                this.$transform$inlined = function40;
                super(2, continuation0);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object object0, Continuation continuation0) {
                kotlinx.coroutines.flow.FlowKt__ZipKt.combineTransform..inlined.combineTransformUnsafe.FlowKt__ZipKt.2 flowKt__ZipKt$combineTransform$$inlined$combineTransformUnsafe$FlowKt__ZipKt$20 = new kotlinx.coroutines.flow.FlowKt__ZipKt.combineTransform..inlined.combineTransformUnsafe.FlowKt__ZipKt.2(this.$flows, continuation0, this.$transform$inlined);
                flowKt__ZipKt$combineTransform$$inlined$combineTransformUnsafe$FlowKt__ZipKt$20.L$0 = object0;
                return flowKt__ZipKt$combineTransform$$inlined$combineTransformUnsafe$FlowKt__ZipKt$20;
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(((FlowCollector)object0), ((Continuation)object1));
            }

            public final Object invoke(FlowCollector flowCollector0, Continuation continuation0) {
                return ((kotlinx.coroutines.flow.FlowKt__ZipKt.combineTransform..inlined.combineTransformUnsafe.FlowKt__ZipKt.2)this.create(flowCollector0, continuation0)).invokeSuspend(Unit.INSTANCE);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object object0) {
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(this.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        FlowCollector flowCollector0 = (FlowCollector)this.L$0;
                        kotlinx.coroutines.flow.FlowKt__ZipKt.combineTransform..inlined.combineTransformUnsafe.FlowKt__ZipKt.2.1 flowKt__ZipKt$combineTransform$$inlined$combineTransformUnsafe$FlowKt__ZipKt$2$10 = new Function3(this.$transform$inlined) {
                            final Function4 $transform$inlined;
                            private Object L$0;
                            Object L$1;
                            int label;

                            {
                                this.$transform$inlined = function40;
                                super(3, continuation0);
                            }

                            @Override  // kotlin.jvm.functions.Function3
                            public Object invoke(Object object0, Object object1, Object object2) {
                                return this.invoke(((FlowCollector)object0), ((Object[])object1), ((Continuation)object2));
                            }

                            public final Object invoke(FlowCollector flowCollector0, Object[] arr_object, Continuation continuation0) {
                                kotlinx.coroutines.flow.FlowKt__ZipKt.combineTransform..inlined.combineTransformUnsafe.FlowKt__ZipKt.2.1 flowKt__ZipKt$combineTransform$$inlined$combineTransformUnsafe$FlowKt__ZipKt$2$10 = new kotlinx.coroutines.flow.FlowKt__ZipKt.combineTransform..inlined.combineTransformUnsafe.FlowKt__ZipKt.2.1(continuation0, this.$transform$inlined);
                                flowKt__ZipKt$combineTransform$$inlined$combineTransformUnsafe$FlowKt__ZipKt$2$10.L$0 = flowCollector0;
                                flowKt__ZipKt$combineTransform$$inlined$combineTransformUnsafe$FlowKt__ZipKt$2$10.L$1 = arr_object;
                                return flowKt__ZipKt$combineTransform$$inlined$combineTransformUnsafe$FlowKt__ZipKt$2$10.invokeSuspend(Unit.INSTANCE);
                            }

                            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            public final Object invokeSuspend(Object object0) {
                                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                switch(this.label) {
                                    case 0: {
                                        ResultKt.throwOnFailure(object0);
                                        FlowCollector flowCollector0 = (FlowCollector)this.L$0;
                                        Object[] arr_object = (Object[])this.L$1;
                                        this.label = 1;
                                        return this.$transform$inlined.invoke(flowCollector0, arr_object[0], arr_object[1], this) == object1 ? object1 : Unit.INSTANCE;
                                    }
                                    case 1: {
                                        ResultKt.throwOnFailure(object0);
                                        return Unit.INSTANCE;
                                    }
                                    default: {
                                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                                    }
                                }
                            }
                        };
                        this.label = 1;
                        return CombineKt.combineInternal(flowCollector0, this.$flows, FlowKt__ZipKt.nullArrayFactory$FlowKt__ZipKt(), flowKt__ZipKt$combineTransform$$inlined$combineTransformUnsafe$FlowKt__ZipKt$2$10, this) == object1 ? object1 : Unit.INSTANCE;
                    }
                    case 1: {
                        ResultKt.throwOnFailure(object0);
                        return Unit.INSTANCE;
                    }
                    default: {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    }
                }
            }
        });
    }

    public static final Flow combineTransform(Flow flow0, Flow flow1, Flow flow2, Function5 function50) {
        return FlowKt.flow(new Function2(new Flow[]{flow0, flow1, flow2}, null, function50) {
            final Flow[] $flows;
            final Function5 $transform$inlined;
            private Object L$0;
            int label;

            {
                this.$flows = arr_flow;
                this.$transform$inlined = function50;
                super(2, continuation0);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object object0, Continuation continuation0) {
                kotlinx.coroutines.flow.FlowKt__ZipKt.combineTransform..inlined.combineTransformUnsafe.FlowKt__ZipKt.3 flowKt__ZipKt$combineTransform$$inlined$combineTransformUnsafe$FlowKt__ZipKt$30 = new kotlinx.coroutines.flow.FlowKt__ZipKt.combineTransform..inlined.combineTransformUnsafe.FlowKt__ZipKt.3(this.$flows, continuation0, this.$transform$inlined);
                flowKt__ZipKt$combineTransform$$inlined$combineTransformUnsafe$FlowKt__ZipKt$30.L$0 = object0;
                return flowKt__ZipKt$combineTransform$$inlined$combineTransformUnsafe$FlowKt__ZipKt$30;
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(((FlowCollector)object0), ((Continuation)object1));
            }

            public final Object invoke(FlowCollector flowCollector0, Continuation continuation0) {
                return ((kotlinx.coroutines.flow.FlowKt__ZipKt.combineTransform..inlined.combineTransformUnsafe.FlowKt__ZipKt.3)this.create(flowCollector0, continuation0)).invokeSuspend(Unit.INSTANCE);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object object0) {
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(this.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        FlowCollector flowCollector0 = (FlowCollector)this.L$0;
                        kotlinx.coroutines.flow.FlowKt__ZipKt.combineTransform..inlined.combineTransformUnsafe.FlowKt__ZipKt.3.1 flowKt__ZipKt$combineTransform$$inlined$combineTransformUnsafe$FlowKt__ZipKt$3$10 = new Function3(this.$transform$inlined) {
                            final Function5 $transform$inlined;
                            private Object L$0;
                            Object L$1;
                            int label;

                            {
                                this.$transform$inlined = function50;
                                super(3, continuation0);
                            }

                            @Override  // kotlin.jvm.functions.Function3
                            public Object invoke(Object object0, Object object1, Object object2) {
                                return this.invoke(((FlowCollector)object0), ((Object[])object1), ((Continuation)object2));
                            }

                            public final Object invoke(FlowCollector flowCollector0, Object[] arr_object, Continuation continuation0) {
                                kotlinx.coroutines.flow.FlowKt__ZipKt.combineTransform..inlined.combineTransformUnsafe.FlowKt__ZipKt.3.1 flowKt__ZipKt$combineTransform$$inlined$combineTransformUnsafe$FlowKt__ZipKt$3$10 = new kotlinx.coroutines.flow.FlowKt__ZipKt.combineTransform..inlined.combineTransformUnsafe.FlowKt__ZipKt.3.1(continuation0, this.$transform$inlined);
                                flowKt__ZipKt$combineTransform$$inlined$combineTransformUnsafe$FlowKt__ZipKt$3$10.L$0 = flowCollector0;
                                flowKt__ZipKt$combineTransform$$inlined$combineTransformUnsafe$FlowKt__ZipKt$3$10.L$1 = arr_object;
                                return flowKt__ZipKt$combineTransform$$inlined$combineTransformUnsafe$FlowKt__ZipKt$3$10.invokeSuspend(Unit.INSTANCE);
                            }

                            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            public final Object invokeSuspend(Object object0) {
                                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                switch(this.label) {
                                    case 0: {
                                        ResultKt.throwOnFailure(object0);
                                        FlowCollector flowCollector0 = (FlowCollector)this.L$0;
                                        Object[] arr_object = (Object[])this.L$1;
                                        this.label = 1;
                                        return this.$transform$inlined.invoke(flowCollector0, arr_object[0], arr_object[1], arr_object[2], this) == object1 ? object1 : Unit.INSTANCE;
                                    }
                                    case 1: {
                                        ResultKt.throwOnFailure(object0);
                                        return Unit.INSTANCE;
                                    }
                                    default: {
                                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                                    }
                                }
                            }
                        };
                        this.label = 1;
                        return CombineKt.combineInternal(flowCollector0, this.$flows, FlowKt__ZipKt.nullArrayFactory$FlowKt__ZipKt(), flowKt__ZipKt$combineTransform$$inlined$combineTransformUnsafe$FlowKt__ZipKt$3$10, this) == object1 ? object1 : Unit.INSTANCE;
                    }
                    case 1: {
                        ResultKt.throwOnFailure(object0);
                        return Unit.INSTANCE;
                    }
                    default: {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    }
                }
            }
        });
    }

    public static final Flow combineTransform(Flow flow0, Flow flow1, Flow flow2, Flow flow3, Function6 function60) {
        return FlowKt.flow(new Function2(new Flow[]{flow0, flow1, flow2, flow3}, null, function60) {
            final Flow[] $flows;
            final Function6 $transform$inlined;
            private Object L$0;
            int label;

            {
                this.$flows = arr_flow;
                this.$transform$inlined = function60;
                super(2, continuation0);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object object0, Continuation continuation0) {
                kotlinx.coroutines.flow.FlowKt__ZipKt.combineTransform..inlined.combineTransformUnsafe.FlowKt__ZipKt.4 flowKt__ZipKt$combineTransform$$inlined$combineTransformUnsafe$FlowKt__ZipKt$40 = new kotlinx.coroutines.flow.FlowKt__ZipKt.combineTransform..inlined.combineTransformUnsafe.FlowKt__ZipKt.4(this.$flows, continuation0, this.$transform$inlined);
                flowKt__ZipKt$combineTransform$$inlined$combineTransformUnsafe$FlowKt__ZipKt$40.L$0 = object0;
                return flowKt__ZipKt$combineTransform$$inlined$combineTransformUnsafe$FlowKt__ZipKt$40;
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(((FlowCollector)object0), ((Continuation)object1));
            }

            public final Object invoke(FlowCollector flowCollector0, Continuation continuation0) {
                return ((kotlinx.coroutines.flow.FlowKt__ZipKt.combineTransform..inlined.combineTransformUnsafe.FlowKt__ZipKt.4)this.create(flowCollector0, continuation0)).invokeSuspend(Unit.INSTANCE);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object object0) {
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(this.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        FlowCollector flowCollector0 = (FlowCollector)this.L$0;
                        kotlinx.coroutines.flow.FlowKt__ZipKt.combineTransform..inlined.combineTransformUnsafe.FlowKt__ZipKt.4.1 flowKt__ZipKt$combineTransform$$inlined$combineTransformUnsafe$FlowKt__ZipKt$4$10 = new Function3(this.$transform$inlined) {
                            final Function6 $transform$inlined;
                            private Object L$0;
                            Object L$1;
                            int label;

                            {
                                this.$transform$inlined = function60;
                                super(3, continuation0);
                            }

                            @Override  // kotlin.jvm.functions.Function3
                            public Object invoke(Object object0, Object object1, Object object2) {
                                return this.invoke(((FlowCollector)object0), ((Object[])object1), ((Continuation)object2));
                            }

                            public final Object invoke(FlowCollector flowCollector0, Object[] arr_object, Continuation continuation0) {
                                kotlinx.coroutines.flow.FlowKt__ZipKt.combineTransform..inlined.combineTransformUnsafe.FlowKt__ZipKt.4.1 flowKt__ZipKt$combineTransform$$inlined$combineTransformUnsafe$FlowKt__ZipKt$4$10 = new kotlinx.coroutines.flow.FlowKt__ZipKt.combineTransform..inlined.combineTransformUnsafe.FlowKt__ZipKt.4.1(continuation0, this.$transform$inlined);
                                flowKt__ZipKt$combineTransform$$inlined$combineTransformUnsafe$FlowKt__ZipKt$4$10.L$0 = flowCollector0;
                                flowKt__ZipKt$combineTransform$$inlined$combineTransformUnsafe$FlowKt__ZipKt$4$10.L$1 = arr_object;
                                return flowKt__ZipKt$combineTransform$$inlined$combineTransformUnsafe$FlowKt__ZipKt$4$10.invokeSuspend(Unit.INSTANCE);
                            }

                            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            public final Object invokeSuspend(Object object0) {
                                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                switch(this.label) {
                                    case 0: {
                                        ResultKt.throwOnFailure(object0);
                                        FlowCollector flowCollector0 = (FlowCollector)this.L$0;
                                        Object[] arr_object = (Object[])this.L$1;
                                        this.label = 1;
                                        return this.$transform$inlined.invoke(flowCollector0, arr_object[0], arr_object[1], arr_object[2], arr_object[3], this) == object1 ? object1 : Unit.INSTANCE;
                                    }
                                    case 1: {
                                        ResultKt.throwOnFailure(object0);
                                        return Unit.INSTANCE;
                                    }
                                    default: {
                                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                                    }
                                }
                            }
                        };
                        this.label = 1;
                        return CombineKt.combineInternal(flowCollector0, this.$flows, FlowKt__ZipKt.nullArrayFactory$FlowKt__ZipKt(), flowKt__ZipKt$combineTransform$$inlined$combineTransformUnsafe$FlowKt__ZipKt$4$10, this) == object1 ? object1 : Unit.INSTANCE;
                    }
                    case 1: {
                        ResultKt.throwOnFailure(object0);
                        return Unit.INSTANCE;
                    }
                    default: {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    }
                }
            }
        });
    }

    public static final Flow combineTransform(Flow flow0, Flow flow1, Flow flow2, Flow flow3, Flow flow4, Function7 function70) {
        return FlowKt.flow(new Function2(new Flow[]{flow0, flow1, flow2, flow3, flow4}, null, function70) {
            final Flow[] $flows;
            final Function7 $transform$inlined;
            private Object L$0;
            int label;

            {
                this.$flows = arr_flow;
                this.$transform$inlined = function70;
                super(2, continuation0);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object object0, Continuation continuation0) {
                kotlinx.coroutines.flow.FlowKt__ZipKt.combineTransform..inlined.combineTransformUnsafe.FlowKt__ZipKt.5 flowKt__ZipKt$combineTransform$$inlined$combineTransformUnsafe$FlowKt__ZipKt$50 = new kotlinx.coroutines.flow.FlowKt__ZipKt.combineTransform..inlined.combineTransformUnsafe.FlowKt__ZipKt.5(this.$flows, continuation0, this.$transform$inlined);
                flowKt__ZipKt$combineTransform$$inlined$combineTransformUnsafe$FlowKt__ZipKt$50.L$0 = object0;
                return flowKt__ZipKt$combineTransform$$inlined$combineTransformUnsafe$FlowKt__ZipKt$50;
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(((FlowCollector)object0), ((Continuation)object1));
            }

            public final Object invoke(FlowCollector flowCollector0, Continuation continuation0) {
                return ((kotlinx.coroutines.flow.FlowKt__ZipKt.combineTransform..inlined.combineTransformUnsafe.FlowKt__ZipKt.5)this.create(flowCollector0, continuation0)).invokeSuspend(Unit.INSTANCE);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object object0) {
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(this.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        FlowCollector flowCollector0 = (FlowCollector)this.L$0;
                        kotlinx.coroutines.flow.FlowKt__ZipKt.combineTransform..inlined.combineTransformUnsafe.FlowKt__ZipKt.5.1 flowKt__ZipKt$combineTransform$$inlined$combineTransformUnsafe$FlowKt__ZipKt$5$10 = new Function3(this.$transform$inlined) {
                            final Function7 $transform$inlined;
                            private Object L$0;
                            Object L$1;
                            int label;

                            {
                                this.$transform$inlined = function70;
                                super(3, continuation0);
                            }

                            @Override  // kotlin.jvm.functions.Function3
                            public Object invoke(Object object0, Object object1, Object object2) {
                                return this.invoke(((FlowCollector)object0), ((Object[])object1), ((Continuation)object2));
                            }

                            public final Object invoke(FlowCollector flowCollector0, Object[] arr_object, Continuation continuation0) {
                                kotlinx.coroutines.flow.FlowKt__ZipKt.combineTransform..inlined.combineTransformUnsafe.FlowKt__ZipKt.5.1 flowKt__ZipKt$combineTransform$$inlined$combineTransformUnsafe$FlowKt__ZipKt$5$10 = new kotlinx.coroutines.flow.FlowKt__ZipKt.combineTransform..inlined.combineTransformUnsafe.FlowKt__ZipKt.5.1(continuation0, this.$transform$inlined);
                                flowKt__ZipKt$combineTransform$$inlined$combineTransformUnsafe$FlowKt__ZipKt$5$10.L$0 = flowCollector0;
                                flowKt__ZipKt$combineTransform$$inlined$combineTransformUnsafe$FlowKt__ZipKt$5$10.L$1 = arr_object;
                                return flowKt__ZipKt$combineTransform$$inlined$combineTransformUnsafe$FlowKt__ZipKt$5$10.invokeSuspend(Unit.INSTANCE);
                            }

                            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            public final Object invokeSuspend(Object object0) {
                                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                switch(this.label) {
                                    case 0: {
                                        ResultKt.throwOnFailure(object0);
                                        FlowCollector flowCollector0 = (FlowCollector)this.L$0;
                                        Object[] arr_object = (Object[])this.L$1;
                                        this.label = 1;
                                        return this.$transform$inlined.invoke(flowCollector0, arr_object[0], arr_object[1], arr_object[2], arr_object[3], arr_object[4], this) == object1 ? object1 : Unit.INSTANCE;
                                    }
                                    case 1: {
                                        ResultKt.throwOnFailure(object0);
                                        return Unit.INSTANCE;
                                    }
                                    default: {
                                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                                    }
                                }
                            }
                        };
                        this.label = 1;
                        return CombineKt.combineInternal(flowCollector0, this.$flows, FlowKt__ZipKt.nullArrayFactory$FlowKt__ZipKt(), flowKt__ZipKt$combineTransform$$inlined$combineTransformUnsafe$FlowKt__ZipKt$5$10, this) == object1 ? object1 : Unit.INSTANCE;
                    }
                    case 1: {
                        ResultKt.throwOnFailure(object0);
                        return Unit.INSTANCE;
                    }
                    default: {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    }
                }
            }
        });
    }

    public static final Flow combineTransform(Flow[] arr_flow, Function3 function30) {
        Intrinsics.needClassReification();
        return FlowKt.flow(new Function2(arr_flow, function30, null) {
            final Flow[] $flows;
            final Function3 $transform;
            private Object L$0;
            int label;

            {
                this.$flows = arr_flow;
                this.$transform = function30;
                super(2, continuation0);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object object0, Continuation continuation0) {
                kotlinx.coroutines.flow.FlowKt__ZipKt.combineTransform.6 flowKt__ZipKt$combineTransform$60 = new kotlinx.coroutines.flow.FlowKt__ZipKt.combineTransform.6(this.$flows, this.$transform, continuation0);
                flowKt__ZipKt$combineTransform$60.L$0 = object0;
                return flowKt__ZipKt$combineTransform$60;
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(((FlowCollector)object0), ((Continuation)object1));
            }

            public final Object invoke(FlowCollector flowCollector0, Continuation continuation0) {
                return ((kotlinx.coroutines.flow.FlowKt__ZipKt.combineTransform.6)this.create(flowCollector0, continuation0)).invokeSuspend(Unit.INSTANCE);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object object0) {
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(this.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        FlowCollector flowCollector0 = (FlowCollector)this.L$0;
                        Intrinsics.needClassReification();
                        kotlinx.coroutines.flow.FlowKt__ZipKt.combineTransform.6.1 flowKt__ZipKt$combineTransform$6$10 = new Function0() {
                            final Flow[] $flows;

                            {
                                this.$flows = arr_flow;
                                super(0);
                            }

                            @Override  // kotlin.jvm.functions.Function0
                            public Object invoke() {
                                return this.invoke();
                            }

                            public final Object[] invoke() {
                                Intrinsics.reifiedOperationMarker(0, "T?");
                                return new Object[this.$flows.length];
                            }
                        };
                        Intrinsics.needClassReification();
                        kotlinx.coroutines.flow.FlowKt__ZipKt.combineTransform.6.2 flowKt__ZipKt$combineTransform$6$20 = new Function3(null) {
                            final Function3 $transform;
                            private Object L$0;
                            Object L$1;
                            int label;

                            {
                                this.$transform = function30;
                                super(3, continuation0);
                            }

                            @Override  // kotlin.jvm.functions.Function3
                            public Object invoke(Object object0, Object object1, Object object2) {
                                return this.invoke(((FlowCollector)object0), ((Object[])object1), ((Continuation)object2));
                            }

                            public final Object invoke(FlowCollector flowCollector0, Object[] arr_object, Continuation continuation0) {
                                Intrinsics.needClassReification();
                                kotlinx.coroutines.flow.FlowKt__ZipKt.combineTransform.6.2 flowKt__ZipKt$combineTransform$6$20 = new kotlinx.coroutines.flow.FlowKt__ZipKt.combineTransform.6.2(this.$transform, continuation0);
                                flowKt__ZipKt$combineTransform$6$20.L$0 = flowCollector0;
                                flowKt__ZipKt$combineTransform$6$20.L$1 = arr_object;
                                return flowKt__ZipKt$combineTransform$6$20.invokeSuspend(Unit.INSTANCE);
                            }

                            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            public final Object invokeSuspend(Object object0) {
                                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                switch(this.label) {
                                    case 0: {
                                        ResultKt.throwOnFailure(object0);
                                        FlowCollector flowCollector0 = (FlowCollector)this.L$0;
                                        this.L$0 = null;
                                        this.label = 1;
                                        return this.$transform.invoke(flowCollector0, ((Object[])this.L$1), this) == object1 ? object1 : Unit.INSTANCE;
                                    }
                                    case 1: {
                                        ResultKt.throwOnFailure(object0);
                                        return Unit.INSTANCE;
                                    }
                                    default: {
                                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                                    }
                                }
                            }

                            public final Object invokeSuspend$$forInline(Object object0) {
                                this.$transform.invoke(((FlowCollector)this.L$0), ((Object[])this.L$1), this);
                                return Unit.INSTANCE;
                            }
                        };
                        this.label = 1;
                        return CombineKt.combineInternal(flowCollector0, this.$flows, flowKt__ZipKt$combineTransform$6$10, flowKt__ZipKt$combineTransform$6$20, this) == object1 ? object1 : Unit.INSTANCE;
                    }
                    case 1: {
                        ResultKt.throwOnFailure(object0);
                        return Unit.INSTANCE;
                    }
                    default: {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    }
                }
            }

            public final Object invokeSuspend$$forInline(Object object0) {
                FlowCollector flowCollector0 = (FlowCollector)this.L$0;
                Intrinsics.needClassReification();
                kotlinx.coroutines.flow.FlowKt__ZipKt.combineTransform.6.1 flowKt__ZipKt$combineTransform$6$10 = new kotlinx.coroutines.flow.FlowKt__ZipKt.combineTransform.6.1(this.$flows);
                Intrinsics.needClassReification();
                kotlinx.coroutines.flow.FlowKt__ZipKt.combineTransform.6.2 flowKt__ZipKt$combineTransform$6$20 = new kotlinx.coroutines.flow.FlowKt__ZipKt.combineTransform.6.2(this.$transform, null);
                CombineKt.combineInternal(flowCollector0, this.$flows, flowKt__ZipKt$combineTransform$6$10, flowKt__ZipKt$combineTransform$6$20, this);
                return Unit.INSTANCE;
            }
        });
    }

    private static final Flow combineTransformUnsafe$FlowKt__ZipKt(Flow[] arr_flow, Function3 function30) {
        Intrinsics.needClassReification();
        return FlowKt.flow(new Function2(arr_flow, function30, null) {
            final Flow[] $flows;
            final Function3 $transform;
            private Object L$0;
            int label;

            {
                this.$flows = arr_flow;
                this.$transform = function30;
                super(2, continuation0);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object object0, Continuation continuation0) {
                kotlinx.coroutines.flow.FlowKt__ZipKt.combineTransformUnsafe.1 flowKt__ZipKt$combineTransformUnsafe$10 = new kotlinx.coroutines.flow.FlowKt__ZipKt.combineTransformUnsafe.1(this.$flows, this.$transform, continuation0);
                flowKt__ZipKt$combineTransformUnsafe$10.L$0 = object0;
                return flowKt__ZipKt$combineTransformUnsafe$10;
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(((FlowCollector)object0), ((Continuation)object1));
            }

            public final Object invoke(FlowCollector flowCollector0, Continuation continuation0) {
                return ((kotlinx.coroutines.flow.FlowKt__ZipKt.combineTransformUnsafe.1)this.create(flowCollector0, continuation0)).invokeSuspend(Unit.INSTANCE);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object object0) {
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(this.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        FlowCollector flowCollector0 = (FlowCollector)this.L$0;
                        Intrinsics.needClassReification();
                        kotlinx.coroutines.flow.FlowKt__ZipKt.combineTransformUnsafe.1.1 flowKt__ZipKt$combineTransformUnsafe$1$10 = new Function3(null) {
                            final Function3 $transform;
                            private Object L$0;
                            Object L$1;
                            int label;

                            {
                                this.$transform = function30;
                                super(3, continuation0);
                            }

                            @Override  // kotlin.jvm.functions.Function3
                            public Object invoke(Object object0, Object object1, Object object2) {
                                return this.invoke(((FlowCollector)object0), ((Object[])object1), ((Continuation)object2));
                            }

                            public final Object invoke(FlowCollector flowCollector0, Object[] arr_object, Continuation continuation0) {
                                Intrinsics.needClassReification();
                                kotlinx.coroutines.flow.FlowKt__ZipKt.combineTransformUnsafe.1.1 flowKt__ZipKt$combineTransformUnsafe$1$10 = new kotlinx.coroutines.flow.FlowKt__ZipKt.combineTransformUnsafe.1.1(this.$transform, continuation0);
                                flowKt__ZipKt$combineTransformUnsafe$1$10.L$0 = flowCollector0;
                                flowKt__ZipKt$combineTransformUnsafe$1$10.L$1 = arr_object;
                                return flowKt__ZipKt$combineTransformUnsafe$1$10.invokeSuspend(Unit.INSTANCE);
                            }

                            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            public final Object invokeSuspend(Object object0) {
                                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                switch(this.label) {
                                    case 0: {
                                        ResultKt.throwOnFailure(object0);
                                        FlowCollector flowCollector0 = (FlowCollector)this.L$0;
                                        this.L$0 = null;
                                        this.label = 1;
                                        return this.$transform.invoke(flowCollector0, ((Object[])this.L$1), this) == object1 ? object1 : Unit.INSTANCE;
                                    }
                                    case 1: {
                                        ResultKt.throwOnFailure(object0);
                                        return Unit.INSTANCE;
                                    }
                                    default: {
                                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                                    }
                                }
                            }

                            public final Object invokeSuspend$$forInline(Object object0) {
                                this.$transform.invoke(((FlowCollector)this.L$0), ((Object[])this.L$1), this);
                                return Unit.INSTANCE;
                            }
                        };
                        this.label = 1;
                        return CombineKt.combineInternal(flowCollector0, this.$flows, FlowKt__ZipKt.nullArrayFactory$FlowKt__ZipKt(), flowKt__ZipKt$combineTransformUnsafe$1$10, this) == object1 ? object1 : Unit.INSTANCE;
                    }
                    case 1: {
                        ResultKt.throwOnFailure(object0);
                        return Unit.INSTANCE;
                    }
                    default: {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    }
                }
            }

            public final Object invokeSuspend$$forInline(Object object0) {
                FlowCollector flowCollector0 = (FlowCollector)this.L$0;
                Intrinsics.needClassReification();
                kotlinx.coroutines.flow.FlowKt__ZipKt.combineTransformUnsafe.1.1 flowKt__ZipKt$combineTransformUnsafe$1$10 = new kotlinx.coroutines.flow.FlowKt__ZipKt.combineTransformUnsafe.1.1(this.$transform, null);
                CombineKt.combineInternal(flowCollector0, this.$flows, FlowKt__ZipKt.nullArrayFactory$FlowKt__ZipKt(), flowKt__ZipKt$combineTransformUnsafe$1$10, this);
                return Unit.INSTANCE;
            }
        });
    }

    private static final Flow combineUnsafe$FlowKt__ZipKt(Flow[] arr_flow, Function2 function20) {
        Intrinsics.needClassReification();
        return new Flow() {
            @Override  // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector flowCollector0, Continuation continuation0) {
                Intrinsics.needClassReification();
                kotlinx.coroutines.flow.FlowKt__ZipKt.combineUnsafe.1.1 flowKt__ZipKt$combineUnsafe$1$10 = new kotlinx.coroutines.flow.FlowKt__ZipKt.combineUnsafe.1.1(function20, null);
                Object object0 = CombineKt.combineInternal(flowCollector0, arr_flow, FlowKt__ZipKt.nullArrayFactory$FlowKt__ZipKt(), flowKt__ZipKt$combineUnsafe$1$10, continuation0);
                return object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? object0 : Unit.INSTANCE;

                @Metadata(k = 3, mv = {1, 8, 0}, xi = 0x30)
                public final class kotlinx.coroutines.flow.FlowKt__ZipKt.combineUnsafe..inlined.unsafeFlow.1.1 extends ContinuationImpl {
                    int label;
                    Object result;

                    public kotlinx.coroutines.flow.FlowKt__ZipKt.combineUnsafe..inlined.unsafeFlow.1.1(kotlinx.coroutines.flow.FlowKt__ZipKt.combineUnsafe..inlined.unsafeFlow.1 flowKt__ZipKt$combineUnsafe$$inlined$unsafeFlow$10, Continuation continuation0) {
                    }

                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object object0) {
                        this.result = object0;
                        this.label |= 0x80000000;
                        return kotlinx.coroutines.flow.FlowKt__ZipKt.combineUnsafe..inlined.unsafeFlow.1.this.collect(null, this);
                    }
                }

            }

            public Object collect$$forInline(FlowCollector flowCollector0, Continuation continuation0) {
                new kotlinx.coroutines.flow.FlowKt__ZipKt.combineUnsafe..inlined.unsafeFlow.1.1(this, continuation0);
                Intrinsics.needClassReification();
                kotlinx.coroutines.flow.FlowKt__ZipKt.combineUnsafe.1.1 flowKt__ZipKt$combineUnsafe$1$10 = new kotlinx.coroutines.flow.FlowKt__ZipKt.combineUnsafe.1.1(function20, null);
                CombineKt.combineInternal(flowCollector0, arr_flow, FlowKt__ZipKt.nullArrayFactory$FlowKt__ZipKt(), flowKt__ZipKt$combineUnsafe$1$10, continuation0);
                return Unit.INSTANCE;
            }
        };

        @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\u0010\u0000\u001A\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0004\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u00042\f\u0010\u0005\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0006H\u008A@"}, d2 = {"<anonymous>", "", "T", "R", "Lkotlinx/coroutines/flow/FlowCollector;", "it", ""}, k = 3, mv = {1, 8, 0}, xi = 0x30)
        @DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__ZipKt$combineUnsafe$1$1", f = "Zip.kt", i = {}, l = {0x106, 0x106}, m = "invokeSuspend", n = {}, s = {})
        public final class kotlinx.coroutines.flow.FlowKt__ZipKt.combineUnsafe.1.1 extends SuspendLambda implements Function3 {
            final Function2 $transform;
            private Object L$0;
            Object L$1;
            int label;

            public kotlinx.coroutines.flow.FlowKt__ZipKt.combineUnsafe.1.1(Function2 function20, Continuation continuation0) {
                this.$transform = function20;
                super(3, continuation0);
            }

            @Override  // kotlin.jvm.functions.Function3
            public Object invoke(Object object0, Object object1, Object object2) {
                return this.invoke(((FlowCollector)object0), ((Object[])object1), ((Continuation)object2));
            }

            public final Object invoke(FlowCollector flowCollector0, Object[] arr_object, Continuation continuation0) {
                Intrinsics.needClassReification();
                kotlinx.coroutines.flow.FlowKt__ZipKt.combineUnsafe.1.1 flowKt__ZipKt$combineUnsafe$1$10 = new kotlinx.coroutines.flow.FlowKt__ZipKt.combineUnsafe.1.1(this.$transform, continuation0);
                flowKt__ZipKt$combineUnsafe$1$10.L$0 = flowCollector0;
                flowKt__ZipKt$combineUnsafe$1$10.L$1 = arr_object;
                return flowKt__ZipKt$combineUnsafe$1$10.invokeSuspend(Unit.INSTANCE);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object object0) {
                FlowCollector flowCollector0;
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(this.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        flowCollector0 = (FlowCollector)this.L$0;
                        this.L$0 = flowCollector0;
                        this.label = 1;
                        object0 = this.$transform.invoke(((Object[])this.L$1), this);
                        if(object0 == object1) {
                            return object1;
                        }
                        break;
                    }
                    case 1: {
                        flowCollector0 = (FlowCollector)this.L$0;
                        ResultKt.throwOnFailure(object0);
                        break;
                    }
                    case 2: {
                        ResultKt.throwOnFailure(object0);
                        return Unit.INSTANCE;
                    }
                    default: {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    }
                }
                this.L$0 = null;
                this.label = 2;
                return flowCollector0.emit(object0, this) == object1 ? object1 : Unit.INSTANCE;
            }

            public final Object invokeSuspend$$forInline(Object object0) {
                ((FlowCollector)this.L$0).emit(this.$transform.invoke(((Object[])this.L$1), this), this);
                return Unit.INSTANCE;
            }
        }

    }

    public static final Flow flowCombine(Flow flow0, Flow flow1, Function3 function30) {
        return new Flow() {
            @Override  // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector flowCollector0, Continuation continuation0) {
                Object object0 = CombineKt.combineInternal(flowCollector0, new Flow[]{flow0, flow1}, FlowKt__ZipKt.nullArrayFactory$FlowKt__ZipKt(), new kotlinx.coroutines.flow.FlowKt__ZipKt.combine.1.1(function30, null), continuation0);
                return object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? object0 : Unit.INSTANCE;
            }
        };

        @Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u0000\u0010\u0000\u001A\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u00052\u000E\u0010\u0006\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007H\u008A@"}, d2 = {"<anonymous>", "", "T1", "T2", "R", "Lkotlinx/coroutines/flow/FlowCollector;", "it", "", ""}, k = 3, mv = {1, 8, 0}, xi = 0x30)
        @DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__ZipKt$combine$1$1", f = "Zip.kt", i = {}, l = {33, 33}, m = "invokeSuspend", n = {}, s = {})
        final class kotlinx.coroutines.flow.FlowKt__ZipKt.combine.1.1 extends SuspendLambda implements Function3 {
            final Function3 $transform;
            private Object L$0;
            Object L$1;
            int label;

            kotlinx.coroutines.flow.FlowKt__ZipKt.combine.1.1(Function3 function30, Continuation continuation0) {
                this.$transform = function30;
                super(3, continuation0);
            }

            @Override  // kotlin.jvm.functions.Function3
            public Object invoke(Object object0, Object object1, Object object2) {
                return this.invoke(((FlowCollector)object0), ((Object[])object1), ((Continuation)object2));
            }

            public final Object invoke(FlowCollector flowCollector0, Object[] arr_object, Continuation continuation0) {
                kotlinx.coroutines.flow.FlowKt__ZipKt.combine.1.1 flowKt__ZipKt$combine$1$10 = new kotlinx.coroutines.flow.FlowKt__ZipKt.combine.1.1(this.$transform, continuation0);
                flowKt__ZipKt$combine$1$10.L$0 = flowCollector0;
                flowKt__ZipKt$combine$1$10.L$1 = arr_object;
                return flowKt__ZipKt$combine$1$10.invokeSuspend(Unit.INSTANCE);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object object0) {
                FlowCollector flowCollector0;
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(this.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        flowCollector0 = (FlowCollector)this.L$0;
                        Object[] arr_object = (Object[])this.L$1;
                        this.L$0 = flowCollector0;
                        this.label = 1;
                        object0 = this.$transform.invoke(arr_object[0], arr_object[1], this);
                        if(object0 == object1) {
                            return object1;
                        }
                        break;
                    }
                    case 1: {
                        flowCollector0 = (FlowCollector)this.L$0;
                        ResultKt.throwOnFailure(object0);
                        break;
                    }
                    case 2: {
                        ResultKt.throwOnFailure(object0);
                        return Unit.INSTANCE;
                    }
                    default: {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    }
                }
                this.L$0 = null;
                this.label = 2;
                return flowCollector0.emit(object0, this) == object1 ? object1 : Unit.INSTANCE;
            }
        }

    }

    public static final Flow flowCombineTransform(Flow flow0, Flow flow1, Function4 function40) {
        return FlowKt.flow(new Function2(new Flow[]{flow0, flow1}, null, function40) {
            final Flow[] $flows;
            final Function4 $transform$inlined;
            private Object L$0;
            int label;

            {
                this.$flows = arr_flow;
                this.$transform$inlined = function40;
                super(2, continuation0);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object object0, Continuation continuation0) {
                kotlinx.coroutines.flow.FlowKt__ZipKt.combineTransform..inlined.combineTransformUnsafe.FlowKt__ZipKt.1 flowKt__ZipKt$combineTransform$$inlined$combineTransformUnsafe$FlowKt__ZipKt$10 = new kotlinx.coroutines.flow.FlowKt__ZipKt.combineTransform..inlined.combineTransformUnsafe.FlowKt__ZipKt.1(this.$flows, continuation0, this.$transform$inlined);
                flowKt__ZipKt$combineTransform$$inlined$combineTransformUnsafe$FlowKt__ZipKt$10.L$0 = object0;
                return flowKt__ZipKt$combineTransform$$inlined$combineTransformUnsafe$FlowKt__ZipKt$10;
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(((FlowCollector)object0), ((Continuation)object1));
            }

            public final Object invoke(FlowCollector flowCollector0, Continuation continuation0) {
                return ((kotlinx.coroutines.flow.FlowKt__ZipKt.combineTransform..inlined.combineTransformUnsafe.FlowKt__ZipKt.1)this.create(flowCollector0, continuation0)).invokeSuspend(Unit.INSTANCE);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object object0) {
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(this.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        FlowCollector flowCollector0 = (FlowCollector)this.L$0;
                        kotlinx.coroutines.flow.FlowKt__ZipKt.combineTransform..inlined.combineTransformUnsafe.FlowKt__ZipKt.1.1 flowKt__ZipKt$combineTransform$$inlined$combineTransformUnsafe$FlowKt__ZipKt$1$10 = new Function3(this.$transform$inlined) {
                            final Function4 $transform$inlined;
                            private Object L$0;
                            Object L$1;
                            int label;

                            {
                                this.$transform$inlined = function40;
                                super(3, continuation0);
                            }

                            @Override  // kotlin.jvm.functions.Function3
                            public Object invoke(Object object0, Object object1, Object object2) {
                                return this.invoke(((FlowCollector)object0), ((Object[])object1), ((Continuation)object2));
                            }

                            public final Object invoke(FlowCollector flowCollector0, Object[] arr_object, Continuation continuation0) {
                                kotlinx.coroutines.flow.FlowKt__ZipKt.combineTransform..inlined.combineTransformUnsafe.FlowKt__ZipKt.1.1 flowKt__ZipKt$combineTransform$$inlined$combineTransformUnsafe$FlowKt__ZipKt$1$10 = new kotlinx.coroutines.flow.FlowKt__ZipKt.combineTransform..inlined.combineTransformUnsafe.FlowKt__ZipKt.1.1(continuation0, this.$transform$inlined);
                                flowKt__ZipKt$combineTransform$$inlined$combineTransformUnsafe$FlowKt__ZipKt$1$10.L$0 = flowCollector0;
                                flowKt__ZipKt$combineTransform$$inlined$combineTransformUnsafe$FlowKt__ZipKt$1$10.L$1 = arr_object;
                                return flowKt__ZipKt$combineTransform$$inlined$combineTransformUnsafe$FlowKt__ZipKt$1$10.invokeSuspend(Unit.INSTANCE);
                            }

                            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            public final Object invokeSuspend(Object object0) {
                                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                switch(this.label) {
                                    case 0: {
                                        ResultKt.throwOnFailure(object0);
                                        FlowCollector flowCollector0 = (FlowCollector)this.L$0;
                                        Object[] arr_object = (Object[])this.L$1;
                                        this.label = 1;
                                        return this.$transform$inlined.invoke(flowCollector0, arr_object[0], arr_object[1], this) == object1 ? object1 : Unit.INSTANCE;
                                    }
                                    case 1: {
                                        ResultKt.throwOnFailure(object0);
                                        return Unit.INSTANCE;
                                    }
                                    default: {
                                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                                    }
                                }
                            }
                        };
                        this.label = 1;
                        return CombineKt.combineInternal(flowCollector0, this.$flows, FlowKt__ZipKt.nullArrayFactory$FlowKt__ZipKt(), flowKt__ZipKt$combineTransform$$inlined$combineTransformUnsafe$FlowKt__ZipKt$1$10, this) == object1 ? object1 : Unit.INSTANCE;
                    }
                    case 1: {
                        ResultKt.throwOnFailure(object0);
                        return Unit.INSTANCE;
                    }
                    default: {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    }
                }
            }
        });
    }

    private static final Function0 nullArrayFactory$FlowKt__ZipKt() {
        return kotlinx.coroutines.flow.FlowKt__ZipKt.nullArrayFactory.1.INSTANCE;

        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0001\n\u0002\b\u0002\u0010\u0000\u001A\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "T", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 0x30)
        final class kotlinx.coroutines.flow.FlowKt__ZipKt.nullArrayFactory.1 extends Lambda implements Function0 {
            public static final kotlinx.coroutines.flow.FlowKt__ZipKt.nullArrayFactory.1 INSTANCE;

            static {
                kotlinx.coroutines.flow.FlowKt__ZipKt.nullArrayFactory.1.INSTANCE = new kotlinx.coroutines.flow.FlowKt__ZipKt.nullArrayFactory.1();
            }

            kotlinx.coroutines.flow.FlowKt__ZipKt.nullArrayFactory.1() {
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return null;
            }

            public final Void invoke() [...] // Inlined contents
        }

    }

    public static final Flow zip(Flow flow0, Flow flow1, Function3 function30) {
        return CombineKt.zipImpl(flow0, flow1, function30);
    }
}

