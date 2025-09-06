package kotlinx.coroutines.flow;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.functions.Function6;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.DelayKt;

@Metadata(d1 = {"\u0000x\n\u0000\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0003\n\u0002\u0010\u000B\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0013\u001A\b\u0010\u0000\u001A\u00020\u0001H\u0000\u001A\u001E\u0010\u0002\u001A\b\u0012\u0004\u0012\u0002H\u00040\u0003\"\u0004\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u0003H\u0007\u001A\u00B8\u0001\u0010\u0005\u001A\b\u0012\u0004\u0012\u0002H\u00060\u0003\"\u0004\b\u0000\u0010\u0007\"\u0004\b\u0001\u0010\b\"\u0004\b\u0002\u0010\t\"\u0004\b\u0003\u0010\n\"\u0004\b\u0004\u0010\u000B\"\u0004\b\u0005\u0010\u0006*\b\u0012\u0004\u0012\u0002H\u00070\u00032\f\u0010\f\u001A\b\u0012\u0004\u0012\u0002H\b0\u00032\f\u0010\r\u001A\b\u0012\u0004\u0012\u0002H\t0\u00032\f\u0010\u000E\u001A\b\u0012\u0004\u0012\u0002H\n0\u00032\f\u0010\u000F\u001A\b\u0012\u0004\u0012\u0002H\u000B0\u00032:\u0010\u0010\u001A6\b\u0001\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u0002H\b\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u0002H\u000B\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00060\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u00130\u0011H\u0007\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u0014\u001A\u009E\u0001\u0010\u0005\u001A\b\u0012\u0004\u0012\u0002H\u00060\u0003\"\u0004\b\u0000\u0010\u0007\"\u0004\b\u0001\u0010\b\"\u0004\b\u0002\u0010\t\"\u0004\b\u0003\u0010\n\"\u0004\b\u0004\u0010\u0006*\b\u0012\u0004\u0012\u0002H\u00070\u00032\f\u0010\f\u001A\b\u0012\u0004\u0012\u0002H\b0\u00032\f\u0010\r\u001A\b\u0012\u0004\u0012\u0002H\t0\u00032\f\u0010\u000E\u001A\b\u0012\u0004\u0012\u0002H\n0\u000324\u0010\u0010\u001A0\b\u0001\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u0002H\b\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u0002H\n\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00060\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u00130\u0015H\u0007\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u0016\u001A\u0084\u0001\u0010\u0005\u001A\b\u0012\u0004\u0012\u0002H\u00060\u0003\"\u0004\b\u0000\u0010\u0007\"\u0004\b\u0001\u0010\b\"\u0004\b\u0002\u0010\t\"\u0004\b\u0003\u0010\u0006*\b\u0012\u0004\u0012\u0002H\u00070\u00032\f\u0010\f\u001A\b\u0012\u0004\u0012\u0002H\b0\u00032\f\u0010\r\u001A\b\u0012\u0004\u0012\u0002H\t0\u00032.\u0010\u0010\u001A*\b\u0001\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u0002H\b\u0012\u0004\u0012\u0002H\t\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00060\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u00130\u0017H\u0007\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u0018\u001Aj\u0010\u0005\u001A\b\u0012\u0004\u0012\u0002H\u00060\u0003\"\u0004\b\u0000\u0010\u0007\"\u0004\b\u0001\u0010\b\"\u0004\b\u0002\u0010\u0006*\b\u0012\u0004\u0012\u0002H\u00070\u00032\f\u0010\f\u001A\b\u0012\u0004\u0012\u0002H\b0\u00032(\u0010\u0010\u001A$\b\u0001\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u0002H\b\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00060\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u00130\u0019H\u0007\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u001A\u001AI\u0010\u001B\u001A\b\u0012\u0004\u0012\u0002H\u00060\u0003\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0006*\b\u0012\u0004\u0012\u0002H\u00040\u00032#\u0010\u001C\u001A\u001F\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00040\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00060\u00030\u001D\u00A2\u0006\u0002\b\u001EH\u0007\u001A>\u0010\u001F\u001A\b\u0012\u0004\u0012\u0002H\u00060\u0003\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0006*\b\u0012\u0004\u0012\u0002H\u00040\u00032\u0018\u0010 \u001A\u0014\u0012\u0004\u0012\u0002H\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00060\u00030\u001DH\u0007\u001A+\u0010!\u001A\b\u0012\u0004\u0012\u0002H\u00040\u0003\"\u0004\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u00032\u0006\u0010\"\u001A\u0002H\u0004H\u0007\u00A2\u0006\u0002\u0010#\u001A,\u0010!\u001A\b\u0012\u0004\u0012\u0002H\u00040\u0003\"\u0004\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u00032\f\u0010\f\u001A\b\u0012\u0004\u0012\u0002H\u00040\u0003H\u0007\u001A&\u0010$\u001A\b\u0012\u0004\u0012\u0002H\u00040\u0003\"\u0004\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u00032\u0006\u0010%\u001A\u00020&H\u0007\u001A&\u0010\'\u001A\b\u0012\u0004\u0012\u0002H\u00040\u0003\"\u0004\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u00032\u0006\u0010%\u001A\u00020&H\u0007\u001AV\u0010(\u001A\b\u0012\u0004\u0012\u0002H\u00060\u0003\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0006*\b\u0012\u0004\u0012\u0002H\u00040\u00032(\u0010 \u001A$\b\u0001\u0012\u0004\u0012\u0002H\u0004\u0012\u0010\u0012\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00060\u00030\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u00130)H\u0007\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010*\u001A$\u0010+\u001A\b\u0012\u0004\u0012\u0002H\u00040\u0003\"\u0004\b\u0000\u0010\u0004*\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00040\u00030\u0003H\u0007\u001AS\u0010,\u001A\u00020-\"\u0004\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u000321\u0010.\u001A-\b\u0001\u0012\u0013\u0012\u0011H\u0004\u00A2\u0006\f\b/\u0012\b\b0\u0012\u0004\b\b(\"\u0012\n\u0012\b\u0012\u0004\u0012\u00020-0\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u00130)H\u0007\u00F8\u0001\u0000\u00A2\u0006\u0002\u00101\u001A$\u00102\u001A\b\u0012\u0004\u0012\u0002H\u00040\u0003\"\u0004\b\u0000\u0010\u0004*\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00040\u00030\u0003H\u0007\u001A&\u00103\u001A\b\u0012\u0004\u0012\u0002H\u00040\u0003\"\u0004\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u00032\u0006\u00104\u001A\u000205H\u0007\u001A,\u00106\u001A\b\u0012\u0004\u0012\u0002H\u00040\u0003\"\u0004\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u00032\f\u00107\u001A\b\u0012\u0004\u0012\u0002H\u00040\u0003H\u0007\u001A,\u00108\u001A\b\u0012\u0004\u0012\u0002H\u00040\u0003\"\u0004\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u00032\f\u00107\u001A\b\u0012\u0004\u0012\u0002H\u00040\u0003H\u0007\u001A+\u00109\u001A\b\u0012\u0004\u0012\u0002H\u00040\u0003\"\u0004\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u00032\u0006\u00107\u001A\u0002H\u0004H\u0007\u00A2\u0006\u0002\u0010#\u001AA\u00109\u001A\b\u0012\u0004\u0012\u0002H\u00040\u0003\"\u0004\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u00032\u0006\u00107\u001A\u0002H\u00042\u0014\b\u0002\u0010:\u001A\u000E\u0012\u0004\u0012\u00020;\u0012\u0004\u0012\u00020<0\u001DH\u0007\u00A2\u0006\u0002\u0010=\u001A\u001E\u0010>\u001A\b\u0012\u0004\u0012\u0002H\u00040\u0003\"\u0004\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u0003H\u0007\u001A&\u0010>\u001A\b\u0012\u0004\u0012\u0002H\u00040\u0003\"\u0004\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u00032\u0006\u0010?\u001A\u00020@H\u0007\u001A&\u0010A\u001A\b\u0012\u0004\u0012\u0002H\u00040\u0003\"\u0004\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u00032\u0006\u00104\u001A\u000205H\u0007\u001A\u001E\u0010B\u001A\b\u0012\u0004\u0012\u0002H\u00040\u0003\"\u0004\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u0003H\u0007\u001A&\u0010B\u001A\b\u0012\u0004\u0012\u0002H\u00040\u0003\"\u0004\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u00032\u0006\u0010?\u001A\u00020@H\u0007\u001A~\u0010C\u001A\b\u0012\u0004\u0012\u0002H\u00060\u0003\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0006*\b\u0012\u0004\u0012\u0002H\u00040\u00032\u0006\u0010D\u001A\u0002H\u00062H\b\u0001\u0010E\u001AB\b\u0001\u0012\u0013\u0012\u0011H\u0006\u00A2\u0006\f\b/\u0012\b\b0\u0012\u0004\b\b(F\u0012\u0013\u0012\u0011H\u0004\u00A2\u0006\f\b/\u0012\b\b0\u0012\u0004\b\b(\"\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00060\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u00130\u0019H\u0007\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010G\u001An\u0010H\u001A\b\u0012\u0004\u0012\u0002H\u00040\u0003\"\u0004\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u00032F\u0010E\u001AB\b\u0001\u0012\u0013\u0012\u0011H\u0004\u00A2\u0006\f\b/\u0012\b\b0\u0012\u0004\b\b(F\u0012\u0013\u0012\u0011H\u0004\u00A2\u0006\f\b/\u0012\b\b0\u0012\u0004\b\b(\"\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00040\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u00130\u0019H\u0007\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010I\u001A&\u0010J\u001A\b\u0012\u0004\u0012\u0002H\u00040\u0003\"\u0004\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u00032\u0006\u0010K\u001A\u00020@H\u0007\u001A+\u0010L\u001A\b\u0012\u0004\u0012\u0002H\u00040\u0003\"\u0004\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u00032\u0006\u0010\"\u001A\u0002H\u0004H\u0007\u00A2\u0006\u0002\u0010#\u001A,\u0010L\u001A\b\u0012\u0004\u0012\u0002H\u00040\u0003\"\u0004\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u00032\f\u0010\f\u001A\b\u0012\u0004\u0012\u0002H\u00040\u0003H\u0007\u001A\u0018\u0010M\u001A\u00020-\"\u0004\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u0003H\u0007\u001AD\u0010M\u001A\u00020-\"\u0004\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u00032\"\u0010N\u001A\u001E\b\u0001\u0012\u0004\u0012\u0002H\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020-0\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u00130)H\u0007\u00F8\u0001\u0000\u00A2\u0006\u0002\u00101\u001Ah\u0010M\u001A\u00020-\"\u0004\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u00032\"\u0010N\u001A\u001E\b\u0001\u0012\u0004\u0012\u0002H\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020-0\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u00130)2\"\u0010O\u001A\u001E\b\u0001\u0012\u0004\u0012\u00020;\u0012\n\u0012\b\u0012\u0004\u0012\u00020-0\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u00130)H\u0007\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010P\u001A&\u0010Q\u001A\b\u0012\u0004\u0012\u0002H\u00040\u0003\"\u0004\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u00032\u0006\u00104\u001A\u000205H\u0007\u001Ae\u0010R\u001A\b\u0012\u0004\u0012\u0002H\u00060\u0003\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0006*\b\u0012\u0004\u0012\u0002H\u00040\u000327\u0010\u0010\u001A3\b\u0001\u0012\u0013\u0012\u0011H\u0004\u00A2\u0006\f\b/\u0012\b\b0\u0012\u0004\b\b(\"\u0012\u0010\u0012\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00060\u00030\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u00130)H\u0007\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010*\u0082\u0002\u0004\n\u0002\b\u0019\u00A8\u0006S"}, d2 = {"noImpl", "", "cache", "Lkotlinx/coroutines/flow/Flow;", "T", "combineLatest", "R", "T1", "T2", "T3", "T4", "T5", "other", "other2", "other3", "other4", "transform", "Lkotlin/Function6;", "Lkotlin/coroutines/Continuation;", "", "(Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function6;)Lkotlinx/coroutines/flow/Flow;", "Lkotlin/Function5;", "(Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function5;)Lkotlinx/coroutines/flow/Flow;", "Lkotlin/Function4;", "(Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function4;)Lkotlinx/coroutines/flow/Flow;", "Lkotlin/Function3;", "(Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function3;)Lkotlinx/coroutines/flow/Flow;", "compose", "transformer", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "concatMap", "mapper", "concatWith", "value", "(Lkotlinx/coroutines/flow/Flow;Ljava/lang/Object;)Lkotlinx/coroutines/flow/Flow;", "delayEach", "timeMillis", "", "delayFlow", "flatMap", "Lkotlin/Function2;", "(Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/flow/Flow;", "flatten", "forEach", "", "action", "Lkotlin/ParameterName;", "name", "(Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function2;)V", "merge", "observeOn", "context", "Lkotlin/coroutines/CoroutineContext;", "onErrorResume", "fallback", "onErrorResumeNext", "onErrorReturn", "predicate", "", "", "(Lkotlinx/coroutines/flow/Flow;Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)Lkotlinx/coroutines/flow/Flow;", "publish", "bufferSize", "", "publishOn", "replay", "scanFold", "initial", "operation", "accumulator", "(Lkotlinx/coroutines/flow/Flow;Ljava/lang/Object;Lkotlin/jvm/functions/Function3;)Lkotlinx/coroutines/flow/Flow;", "scanReduce", "(Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function3;)Lkotlinx/coroutines/flow/Flow;", "skip", "count", "startWith", "subscribe", "onEach", "onError", "(Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;)V", "subscribeOn", "switchMap", "kotlinx-coroutines-core"}, k = 5, mv = {1, 8, 0}, xi = 0x30, xs = "kotlinx/coroutines/flow/FlowKt")
final class FlowKt__MigrationKt {
    @Deprecated(level = DeprecationLevel.ERROR, message = "Flow analogue of \'cache()\' is \'shareIn\' with unlimited replay and \'started = SharingStared.Lazily\' argument\'", replaceWith = @ReplaceWith(expression = "this.shareIn(scope, Int.MAX_VALUE, started = SharingStared.Lazily)", imports = {}))
    public static final Flow cache(Flow flow0) {
        FlowKt.noImpl();
        throw new KotlinNothingValueException();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Flow analogue of \'combineLatest\' is \'combine\'", replaceWith = @ReplaceWith(expression = "this.combine(other, transform)", imports = {}))
    public static final Flow combineLatest(Flow flow0, Flow flow1, Function3 function30) {
        return FlowKt.combine(flow0, flow1, function30);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Flow analogue of \'combineLatest\' is \'combine\'", replaceWith = @ReplaceWith(expression = "combine(this, other, other2, transform)", imports = {}))
    public static final Flow combineLatest(Flow flow0, Flow flow1, Flow flow2, Function4 function40) {
        return FlowKt.combine(flow0, flow1, flow2, function40);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Flow analogue of \'combineLatest\' is \'combine\'", replaceWith = @ReplaceWith(expression = "combine(this, other, other2, other3, transform)", imports = {}))
    public static final Flow combineLatest(Flow flow0, Flow flow1, Flow flow2, Flow flow3, Function5 function50) {
        return FlowKt.combine(flow0, flow1, flow2, flow3, function50);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Flow analogue of \'combineLatest\' is \'combine\'", replaceWith = @ReplaceWith(expression = "combine(this, other, other2, other3, transform)", imports = {}))
    public static final Flow combineLatest(Flow flow0, Flow flow1, Flow flow2, Flow flow3, Flow flow4, Function6 function60) {
        return FlowKt.combine(flow0, flow1, flow2, flow3, flow4, function60);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Flow analogue of \'compose\' is \'let\'", replaceWith = @ReplaceWith(expression = "let(transformer)", imports = {}))
    public static final Flow compose(Flow flow0, Function1 function10) {
        FlowKt.noImpl();
        throw new KotlinNothingValueException();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Flow analogue of \'concatMap\' is \'flatMapConcat\'", replaceWith = @ReplaceWith(expression = "flatMapConcat(mapper)", imports = {}))
    public static final Flow concatMap(Flow flow0, Function1 function10) {
        FlowKt.noImpl();
        throw new KotlinNothingValueException();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Flow analogue of \'concatWith\' is \'onCompletion\'. Use \'onCompletion { emit(value) }\'", replaceWith = @ReplaceWith(expression = "onCompletion { emit(value) }", imports = {}))
    public static final Flow concatWith(Flow flow0, Object object0) {
        FlowKt.noImpl();
        throw new KotlinNothingValueException();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Flow analogue of \'concatWith\' is \'onCompletion\'. Use \'onCompletion { if (it == null) emitAll(other) }\'", replaceWith = @ReplaceWith(expression = "onCompletion { if (it == null) emitAll(other) }", imports = {}))
    public static final Flow concatWith(Flow flow0, Flow flow1) {
        FlowKt.noImpl();
        throw new KotlinNothingValueException();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use \'onEach { delay(timeMillis) }\'", replaceWith = @ReplaceWith(expression = "onEach { delay(timeMillis) }", imports = {}))
    public static final Flow delayEach(Flow flow0, long v) {
        return FlowKt.onEach(flow0, new Function2(v, null) {
            final long $timeMillis;
            int label;

            {
                this.$timeMillis = v;
                super(2, continuation0);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object object0, Continuation continuation0) {
                return new kotlinx.coroutines.flow.FlowKt__MigrationKt.delayEach.1(this.$timeMillis, continuation0);
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(object0, ((Continuation)object1));
            }

            public final Object invoke(Object object0, Continuation continuation0) {
                return ((kotlinx.coroutines.flow.FlowKt__MigrationKt.delayEach.1)this.create(object0, continuation0)).invokeSuspend(Unit.INSTANCE);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object object0) {
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(this.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        this.label = 1;
                        return DelayKt.delay(this.$timeMillis, this) == object1 ? object1 : Unit.INSTANCE;
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

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use \'onStart { delay(timeMillis) }\'", replaceWith = @ReplaceWith(expression = "onStart { delay(timeMillis) }", imports = {}))
    public static final Flow delayFlow(Flow flow0, long v) {
        return FlowKt.onStart(flow0, new Function2(v, null) {
            final long $timeMillis;
            int label;

            {
                this.$timeMillis = v;
                super(2, continuation0);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object object0, Continuation continuation0) {
                return new kotlinx.coroutines.flow.FlowKt__MigrationKt.delayFlow.1(this.$timeMillis, continuation0);
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(((FlowCollector)object0), ((Continuation)object1));
            }

            public final Object invoke(FlowCollector flowCollector0, Continuation continuation0) {
                return ((kotlinx.coroutines.flow.FlowKt__MigrationKt.delayFlow.1)this.create(flowCollector0, continuation0)).invokeSuspend(Unit.INSTANCE);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object object0) {
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(this.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        this.label = 1;
                        return DelayKt.delay(this.$timeMillis, this) == object1 ? object1 : Unit.INSTANCE;
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

    @Deprecated(level = DeprecationLevel.ERROR, message = "Flow analogue is \'flatMapConcat\'", replaceWith = @ReplaceWith(expression = "flatMapConcat(mapper)", imports = {}))
    public static final Flow flatMap(Flow flow0, Function2 function20) {
        FlowKt.noImpl();
        throw new KotlinNothingValueException();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Flow analogue of \'flatten\' is \'flattenConcat\'", replaceWith = @ReplaceWith(expression = "flattenConcat()", imports = {}))
    public static final Flow flatten(Flow flow0) {
        FlowKt.noImpl();
        throw new KotlinNothingValueException();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Flow analogue of \'forEach\' is \'collect\'", replaceWith = @ReplaceWith(expression = "collect(action)", imports = {}))
    public static final void forEach(Flow flow0, Function2 function20) {
        FlowKt.noImpl();
        throw new KotlinNothingValueException();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Flow analogue of \'merge\' is \'flattenConcat\'", replaceWith = @ReplaceWith(expression = "flattenConcat()", imports = {}))
    public static final Flow merge(Flow flow0) {
        FlowKt.noImpl();
        throw new KotlinNothingValueException();
    }

    public static final Void noImpl() {
        throw new UnsupportedOperationException("Not implemented, should not be called");
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Collect flow in the desired context instead")
    public static final Flow observeOn(Flow flow0, CoroutineContext coroutineContext0) {
        FlowKt.noImpl();
        throw new KotlinNothingValueException();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Flow analogue of \'onErrorXxx\' is \'catch\'. Use \'catch { emitAll(fallback) }\'", replaceWith = @ReplaceWith(expression = "catch { emitAll(fallback) }", imports = {}))
    public static final Flow onErrorResume(Flow flow0, Flow flow1) {
        FlowKt.noImpl();
        throw new KotlinNothingValueException();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Flow analogue of \'onErrorXxx\' is \'catch\'. Use \'catch { emitAll(fallback) }\'", replaceWith = @ReplaceWith(expression = "catch { emitAll(fallback) }", imports = {}))
    public static final Flow onErrorResumeNext(Flow flow0, Flow flow1) {
        FlowKt.noImpl();
        throw new KotlinNothingValueException();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Flow analogue of \'onErrorXxx\' is \'catch\'. Use \'catch { emit(fallback) }\'", replaceWith = @ReplaceWith(expression = "catch { emit(fallback) }", imports = {}))
    public static final Flow onErrorReturn(Flow flow0, Object object0) {
        FlowKt.noImpl();
        throw new KotlinNothingValueException();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Flow analogue of \'onErrorXxx\' is \'catch\'. Use \'catch { e -> if (predicate(e)) emit(fallback) else throw e }\'", replaceWith = @ReplaceWith(expression = "catch { e -> if (predicate(e)) emit(fallback) else throw e }", imports = {}))
    public static final Flow onErrorReturn(Flow flow0, Object object0, Function1 function10) {
        return FlowKt.catch(flow0, new Function3(function10, object0, null) {
            final Object $fallback;
            final Function1 $predicate;
            private Object L$0;
            Object L$1;
            int label;

            {
                this.$predicate = function10;
                this.$fallback = object0;
                super(3, continuation0);
            }

            @Override  // kotlin.jvm.functions.Function3
            public Object invoke(Object object0, Object object1, Object object2) {
                return this.invoke(((FlowCollector)object0), ((Throwable)object1), ((Continuation)object2));
            }

            public final Object invoke(FlowCollector flowCollector0, Throwable throwable0, Continuation continuation0) {
                kotlinx.coroutines.flow.FlowKt__MigrationKt.onErrorReturn.2 flowKt__MigrationKt$onErrorReturn$20 = new kotlinx.coroutines.flow.FlowKt__MigrationKt.onErrorReturn.2(this.$predicate, this.$fallback, continuation0);
                flowKt__MigrationKt$onErrorReturn$20.L$0 = flowCollector0;
                flowKt__MigrationKt$onErrorReturn$20.L$1 = throwable0;
                return flowKt__MigrationKt$onErrorReturn$20.invokeSuspend(Unit.INSTANCE);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object object0) {
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(this.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        FlowCollector flowCollector0 = (FlowCollector)this.L$0;
                        Throwable throwable0 = (Throwable)this.L$1;
                        if(!((Boolean)this.$predicate.invoke(throwable0)).booleanValue()) {
                            throw throwable0;
                        }
                        this.L$0 = null;
                        this.label = 1;
                        return flowCollector0.emit(this.$fallback, this) == object1 ? object1 : Unit.INSTANCE;
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

    public static Flow onErrorReturn$default(Flow flow0, Object object0, Function1 function10, int v, Object object1) {
        if((v & 2) != 0) {
            function10 = kotlinx.coroutines.flow.FlowKt__MigrationKt.onErrorReturn.1.INSTANCE;
        }
        return FlowKt.onErrorReturn(flow0, object0, function10);

        @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0002\u0010\u0000\u001A\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001A\u00020\u0004H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "T", "it", "", "invoke", "(Ljava/lang/Throwable;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 0x30)
        final class kotlinx.coroutines.flow.FlowKt__MigrationKt.onErrorReturn.1 extends Lambda implements Function1 {
            public static final kotlinx.coroutines.flow.FlowKt__MigrationKt.onErrorReturn.1 INSTANCE;

            static {
                kotlinx.coroutines.flow.FlowKt__MigrationKt.onErrorReturn.1.INSTANCE = new kotlinx.coroutines.flow.FlowKt__MigrationKt.onErrorReturn.1();
            }

            kotlinx.coroutines.flow.FlowKt__MigrationKt.onErrorReturn.1() {
                super(1);
            }

            public final Boolean invoke(Throwable throwable0) {
                return true;
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((Throwable)object0));
            }
        }

    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Flow analogue of \'publish()\' is \'shareIn\'. \npublish().connect() is the default strategy (no extra call is needed), \npublish().autoConnect() translates to \'started = SharingStared.Lazily\' argument, \npublish().refCount() translates to \'started = SharingStared.WhileSubscribed()\' argument.", replaceWith = @ReplaceWith(expression = "this.shareIn(scope, 0)", imports = {}))
    public static final Flow publish(Flow flow0) {
        FlowKt.noImpl();
        throw new KotlinNothingValueException();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Flow analogue of \'publish(bufferSize)\' is \'buffer\' followed by \'shareIn\'. \npublish().connect() is the default strategy (no extra call is needed), \npublish().autoConnect() translates to \'started = SharingStared.Lazily\' argument, \npublish().refCount() translates to \'started = SharingStared.WhileSubscribed()\' argument.", replaceWith = @ReplaceWith(expression = "this.buffer(bufferSize).shareIn(scope, 0)", imports = {}))
    public static final Flow publish(Flow flow0, int v) {
        FlowKt.noImpl();
        throw new KotlinNothingValueException();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Collect flow in the desired context instead")
    public static final Flow publishOn(Flow flow0, CoroutineContext coroutineContext0) {
        FlowKt.noImpl();
        throw new KotlinNothingValueException();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Flow analogue of \'replay()\' is \'shareIn\' with unlimited replay. \nreplay().connect() is the default strategy (no extra call is needed), \nreplay().autoConnect() translates to \'started = SharingStared.Lazily\' argument, \nreplay().refCount() translates to \'started = SharingStared.WhileSubscribed()\' argument.", replaceWith = @ReplaceWith(expression = "this.shareIn(scope, Int.MAX_VALUE)", imports = {}))
    public static final Flow replay(Flow flow0) {
        FlowKt.noImpl();
        throw new KotlinNothingValueException();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Flow analogue of \'replay(bufferSize)\' is \'shareIn\' with the specified replay parameter. \nreplay().connect() is the default strategy (no extra call is needed), \nreplay().autoConnect() translates to \'started = SharingStared.Lazily\' argument, \nreplay().refCount() translates to \'started = SharingStared.WhileSubscribed()\' argument.", replaceWith = @ReplaceWith(expression = "this.shareIn(scope, bufferSize)", imports = {}))
    public static final Flow replay(Flow flow0, int v) {
        FlowKt.noImpl();
        throw new KotlinNothingValueException();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Flow has less verbose \'scan\' shortcut", replaceWith = @ReplaceWith(expression = "scan(initial, operation)", imports = {}))
    public static final Flow scanFold(Flow flow0, Object object0, Function3 function30) {
        FlowKt.noImpl();
        throw new KotlinNothingValueException();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "\'scanReduce\' was renamed to \'runningReduce\' to be consistent with Kotlin standard library", replaceWith = @ReplaceWith(expression = "runningReduce(operation)", imports = {}))
    public static final Flow scanReduce(Flow flow0, Function3 function30) {
        return FlowKt.runningReduce(flow0, function30);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Flow analogue of \'skip\' is \'drop\'", replaceWith = @ReplaceWith(expression = "drop(count)", imports = {}))
    public static final Flow skip(Flow flow0, int v) {
        FlowKt.noImpl();
        throw new KotlinNothingValueException();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Flow analogue of \'startWith\' is \'onStart\'. Use \'onStart { emit(value) }\'", replaceWith = @ReplaceWith(expression = "onStart { emit(value) }", imports = {}))
    public static final Flow startWith(Flow flow0, Object object0) {
        FlowKt.noImpl();
        throw new KotlinNothingValueException();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Flow analogue of \'startWith\' is \'onStart\'. Use \'onStart { emitAll(other) }\'", replaceWith = @ReplaceWith(expression = "onStart { emitAll(other) }", imports = {}))
    public static final Flow startWith(Flow flow0, Flow flow1) {
        FlowKt.noImpl();
        throw new KotlinNothingValueException();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use \'launchIn\' with \'onEach\', \'onCompletion\' and \'catch\' instead")
    public static final void subscribe(Flow flow0) {
        FlowKt.noImpl();
        throw new KotlinNothingValueException();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use \'launchIn\' with \'onEach\', \'onCompletion\' and \'catch\' instead")
    public static final void subscribe(Flow flow0, Function2 function20) {
        FlowKt.noImpl();
        throw new KotlinNothingValueException();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use \'launchIn\' with \'onEach\', \'onCompletion\' and \'catch\' instead")
    public static final void subscribe(Flow flow0, Function2 function20, Function2 function21) {
        FlowKt.noImpl();
        throw new KotlinNothingValueException();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use \'flowOn\' instead")
    public static final Flow subscribeOn(Flow flow0, CoroutineContext coroutineContext0) {
        FlowKt.noImpl();
        throw new KotlinNothingValueException();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Flow analogues of \'switchMap\' are \'transformLatest\', \'flatMapLatest\' and \'mapLatest\'", replaceWith = @ReplaceWith(expression = "this.flatMapLatest(transform)", imports = {}))
    public static final Flow switchMap(Flow flow0, Function2 function20) {
        return FlowKt.transformLatest(flow0, new Function3(function20, null) {
            final Function2 $transform;
            private Object L$0;
            Object L$1;
            int label;

            {
                this.$transform = function20;
                super(3, continuation0);
            }

            @Override  // kotlin.jvm.functions.Function3
            public Object invoke(Object object0, Object object1, Object object2) {
                return this.invoke(((FlowCollector)object0), object1, ((Continuation)object2));
            }

            public final Object invoke(FlowCollector flowCollector0, Object object0, Continuation continuation0) {
                kotlinx.coroutines.flow.FlowKt__MigrationKt.switchMap..inlined.flatMapLatest.1 flowKt__MigrationKt$switchMap$$inlined$flatMapLatest$10 = new kotlinx.coroutines.flow.FlowKt__MigrationKt.switchMap..inlined.flatMapLatest.1(this.$transform, continuation0);
                flowKt__MigrationKt$switchMap$$inlined$flatMapLatest$10.L$0 = flowCollector0;
                flowKt__MigrationKt$switchMap$$inlined$flatMapLatest$10.L$1 = object0;
                return flowKt__MigrationKt$switchMap$$inlined$flatMapLatest$10.invokeSuspend(Unit.INSTANCE);
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
                        object0 = this.$transform.invoke(this.L$1, this);
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
                return FlowKt.emitAll(flowCollector0, ((Flow)object0), this) == object1 ? object1 : Unit.INSTANCE;
            }
        });
    }
}

