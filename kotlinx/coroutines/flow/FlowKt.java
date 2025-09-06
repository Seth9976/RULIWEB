package kotlinx.coroutines.flow;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.functions.Function6;
import kotlin.jvm.functions.Function7;
import kotlin.ranges.IntRange;
import kotlin.ranges.LongRange;
import kotlin.reflect.KClass;
import kotlin.sequences.Sequence;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.channels.BroadcastChannel;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.channels.ReceiveChannel;

@Metadata(d1 = {"kotlinx/coroutines/flow/FlowKt__BuildersKt", "kotlinx/coroutines/flow/FlowKt__ChannelsKt", "kotlinx/coroutines/flow/FlowKt__CollectKt", "kotlinx/coroutines/flow/FlowKt__CollectionKt", "kotlinx/coroutines/flow/FlowKt__ContextKt", "kotlinx/coroutines/flow/FlowKt__CountKt", "kotlinx/coroutines/flow/FlowKt__DelayKt", "kotlinx/coroutines/flow/FlowKt__DistinctKt", "kotlinx/coroutines/flow/FlowKt__EmittersKt", "kotlinx/coroutines/flow/FlowKt__ErrorsKt", "kotlinx/coroutines/flow/FlowKt__LimitKt", "kotlinx/coroutines/flow/FlowKt__MergeKt", "kotlinx/coroutines/flow/FlowKt__MigrationKt", "kotlinx/coroutines/flow/FlowKt__ReduceKt", "kotlinx/coroutines/flow/FlowKt__ShareKt", "kotlinx/coroutines/flow/FlowKt__TransformKt", "kotlinx/coroutines/flow/FlowKt__ZipKt"}, k = 4, mv = {1, 8, 0}, xi = 0x30)
public final class FlowKt {
    public static final String DEFAULT_CONCURRENCY_PROPERTY_NAME = "kotlinx.coroutines.flow.defaultConcurrency";

    public static final Flow asFlow(Iterable iterable0) {
        return FlowKt__BuildersKt.asFlow(iterable0);
    }

    public static final Flow asFlow(Iterator iterator0) {
        return FlowKt__BuildersKt.asFlow(iterator0);
    }

    public static final Flow asFlow(Function0 function00) {
        return FlowKt__BuildersKt.asFlow(function00);
    }

    public static final Flow asFlow(Function1 function10) {
        return FlowKt__BuildersKt.asFlow(function10);
    }

    public static final Flow asFlow(IntRange intRange0) {
        return FlowKt__BuildersKt.asFlow(intRange0);
    }

    public static final Flow asFlow(LongRange longRange0) {
        return FlowKt__BuildersKt.asFlow(longRange0);
    }

    public static final Flow asFlow(Sequence sequence0) {
        return FlowKt__BuildersKt.asFlow(sequence0);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "\'BroadcastChannel\' is obsolete and all corresponding operators are deprecated in the favour of StateFlow and SharedFlow")
    public static final Flow asFlow(BroadcastChannel broadcastChannel0) {
        return FlowKt__ChannelsKt.asFlow(broadcastChannel0);
    }

    public static final Flow asFlow(int[] arr_v) {
        return FlowKt__BuildersKt.asFlow(arr_v);
    }

    public static final Flow asFlow(long[] arr_v) {
        return FlowKt__BuildersKt.asFlow(arr_v);
    }

    public static final Flow asFlow(Object[] arr_object) {
        return FlowKt__BuildersKt.asFlow(arr_object);
    }

    public static final SharedFlow asSharedFlow(MutableSharedFlow mutableSharedFlow0) {
        return FlowKt__ShareKt.asSharedFlow(mutableSharedFlow0);
    }

    public static final StateFlow asStateFlow(MutableStateFlow mutableStateFlow0) {
        return FlowKt__ShareKt.asStateFlow(mutableStateFlow0);
    }

    public static final Flow buffer(Flow flow0, int v, BufferOverflow bufferOverflow0) {
        return FlowKt__ContextKt.buffer(flow0, v, bufferOverflow0);
    }

    public static Flow buffer$default(Flow flow0, int v, int v1, Object object0) {
        return FlowKt__ContextKt.buffer$default(flow0, v, v1, object0);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Flow analogue of \'cache()\' is \'shareIn\' with unlimited replay and \'started = SharingStared.Lazily\' argument\'", replaceWith = @ReplaceWith(expression = "this.shareIn(scope, Int.MAX_VALUE, started = SharingStared.Lazily)", imports = {}))
    public static final Flow cache(Flow flow0) {
        return FlowKt__MigrationKt.cache(flow0);
    }

    public static final Flow callbackFlow(Function2 function20) {
        return FlowKt__BuildersKt.callbackFlow(function20);
    }

    public static final Flow cancellable(Flow flow0) {
        return FlowKt__ContextKt.cancellable(flow0);
    }

    public static final Flow catch(Flow flow0, Function3 function30) {
        return FlowKt__ErrorsKt.catch(flow0, function30);
    }

    public static final Object catchImpl(Flow flow0, FlowCollector flowCollector0, Continuation continuation0) {
        return FlowKt__ErrorsKt.catchImpl(flow0, flowCollector0, continuation0);
    }

    public static final Flow channelFlow(Function2 function20) {
        return FlowKt__BuildersKt.channelFlow(function20);
    }

    public static final Object collect(Flow flow0, Continuation continuation0) {
        return FlowKt__CollectKt.collect(flow0, continuation0);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Backwards compatibility with JS and K/N")
    public static final Object collect(Flow flow0, Function2 function20, Continuation continuation0) {
        return FlowKt__CollectKt.collect(flow0, function20, continuation0);
    }

    public static final Object collectIndexed(Flow flow0, Function3 function30, Continuation continuation0) {
        return FlowKt__CollectKt.collectIndexed(flow0, function30, continuation0);
    }

    public static final Object collectLatest(Flow flow0, Function2 function20, Continuation continuation0) {
        return FlowKt__CollectKt.collectLatest(flow0, function20, continuation0);
    }

    public static final Object collectWhile(Flow flow0, Function2 function20, Continuation continuation0) {
        return FlowKt__LimitKt.collectWhile(flow0, function20, continuation0);
    }

    public static final Flow combine(Iterable iterable0, Function2 function20) {
        return FlowKt__ZipKt.combine(iterable0, function20);
    }

    public static final Flow combine(Flow flow0, Flow flow1, Function3 function30) {
        return FlowKt__ZipKt.combine(flow0, flow1, function30);
    }

    public static final Flow combine(Flow flow0, Flow flow1, Flow flow2, Function4 function40) {
        return FlowKt__ZipKt.combine(flow0, flow1, flow2, function40);
    }

    public static final Flow combine(Flow flow0, Flow flow1, Flow flow2, Flow flow3, Function5 function50) {
        return FlowKt__ZipKt.combine(flow0, flow1, flow2, flow3, function50);
    }

    public static final Flow combine(Flow flow0, Flow flow1, Flow flow2, Flow flow3, Flow flow4, Function6 function60) {
        return FlowKt__ZipKt.combine(flow0, flow1, flow2, flow3, flow4, function60);
    }

    public static final Flow combine(Flow[] arr_flow, Function2 function20) {
        return FlowKt__ZipKt.combine(arr_flow, function20);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Flow analogue of \'combineLatest\' is \'combine\'", replaceWith = @ReplaceWith(expression = "this.combine(other, transform)", imports = {}))
    public static final Flow combineLatest(Flow flow0, Flow flow1, Function3 function30) {
        return FlowKt__MigrationKt.combineLatest(flow0, flow1, function30);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Flow analogue of \'combineLatest\' is \'combine\'", replaceWith = @ReplaceWith(expression = "combine(this, other, other2, transform)", imports = {}))
    public static final Flow combineLatest(Flow flow0, Flow flow1, Flow flow2, Function4 function40) {
        return FlowKt__MigrationKt.combineLatest(flow0, flow1, flow2, function40);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Flow analogue of \'combineLatest\' is \'combine\'", replaceWith = @ReplaceWith(expression = "combine(this, other, other2, other3, transform)", imports = {}))
    public static final Flow combineLatest(Flow flow0, Flow flow1, Flow flow2, Flow flow3, Function5 function50) {
        return FlowKt__MigrationKt.combineLatest(flow0, flow1, flow2, flow3, function50);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Flow analogue of \'combineLatest\' is \'combine\'", replaceWith = @ReplaceWith(expression = "combine(this, other, other2, other3, transform)", imports = {}))
    public static final Flow combineLatest(Flow flow0, Flow flow1, Flow flow2, Flow flow3, Flow flow4, Function6 function60) {
        return FlowKt__MigrationKt.combineLatest(flow0, flow1, flow2, flow3, flow4, function60);
    }

    public static final Flow combineTransform(Iterable iterable0, Function3 function30) {
        return FlowKt__ZipKt.combineTransform(iterable0, function30);
    }

    public static final Flow combineTransform(Flow flow0, Flow flow1, Function4 function40) {
        return FlowKt__ZipKt.combineTransform(flow0, flow1, function40);
    }

    public static final Flow combineTransform(Flow flow0, Flow flow1, Flow flow2, Function5 function50) {
        return FlowKt__ZipKt.combineTransform(flow0, flow1, flow2, function50);
    }

    public static final Flow combineTransform(Flow flow0, Flow flow1, Flow flow2, Flow flow3, Function6 function60) {
        return FlowKt__ZipKt.combineTransform(flow0, flow1, flow2, flow3, function60);
    }

    public static final Flow combineTransform(Flow flow0, Flow flow1, Flow flow2, Flow flow3, Flow flow4, Function7 function70) {
        return FlowKt__ZipKt.combineTransform(flow0, flow1, flow2, flow3, flow4, function70);
    }

    public static final Flow combineTransform(Flow[] arr_flow, Function3 function30) {
        return FlowKt__ZipKt.combineTransform(arr_flow, function30);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Flow analogue of \'compose\' is \'let\'", replaceWith = @ReplaceWith(expression = "let(transformer)", imports = {}))
    public static final Flow compose(Flow flow0, Function1 function10) {
        return FlowKt__MigrationKt.compose(flow0, function10);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Flow analogue of \'concatMap\' is \'flatMapConcat\'", replaceWith = @ReplaceWith(expression = "flatMapConcat(mapper)", imports = {}))
    public static final Flow concatMap(Flow flow0, Function1 function10) {
        return FlowKt__MigrationKt.concatMap(flow0, function10);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Flow analogue of \'concatWith\' is \'onCompletion\'. Use \'onCompletion { emit(value) }\'", replaceWith = @ReplaceWith(expression = "onCompletion { emit(value) }", imports = {}))
    public static final Flow concatWith(Flow flow0, Object object0) {
        return FlowKt__MigrationKt.concatWith(flow0, object0);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Flow analogue of \'concatWith\' is \'onCompletion\'. Use \'onCompletion { if (it == null) emitAll(other) }\'", replaceWith = @ReplaceWith(expression = "onCompletion { if (it == null) emitAll(other) }", imports = {}))
    public static final Flow concatWith(Flow flow0, Flow flow1) {
        return FlowKt__MigrationKt.concatWith(flow0, flow1);
    }

    public static final Flow conflate(Flow flow0) {
        return FlowKt__ContextKt.conflate(flow0);
    }

    public static final Flow consumeAsFlow(ReceiveChannel receiveChannel0) {
        return FlowKt__ChannelsKt.consumeAsFlow(receiveChannel0);
    }

    public static final Object count(Flow flow0, Continuation continuation0) {
        return FlowKt__CountKt.count(flow0, continuation0);
    }

    public static final Object count(Flow flow0, Function2 function20, Continuation continuation0) {
        return FlowKt__CountKt.count(flow0, function20, continuation0);
    }

    public static final Flow debounce(Flow flow0, long v) {
        return FlowKt__DelayKt.debounce(flow0, v);
    }

    public static final Flow debounce(Flow flow0, Function1 function10) {
        return FlowKt__DelayKt.debounce(flow0, function10);
    }

    public static final Flow debounce-HG0u8IE(Flow flow0, long v) {
        return FlowKt__DelayKt.debounce-HG0u8IE(flow0, v);
    }

    public static final Flow debounceDuration(Flow flow0, Function1 function10) {
        return FlowKt__DelayKt.debounceDuration(flow0, function10);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use \'onEach { delay(timeMillis) }\'", replaceWith = @ReplaceWith(expression = "onEach { delay(timeMillis) }", imports = {}))
    public static final Flow delayEach(Flow flow0, long v) {
        return FlowKt__MigrationKt.delayEach(flow0, v);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use \'onStart { delay(timeMillis) }\'", replaceWith = @ReplaceWith(expression = "onStart { delay(timeMillis) }", imports = {}))
    public static final Flow delayFlow(Flow flow0, long v) {
        return FlowKt__MigrationKt.delayFlow(flow0, v);
    }

    public static final Flow distinctUntilChanged(Flow flow0) {
        return FlowKt__DistinctKt.distinctUntilChanged(flow0);
    }

    public static final Flow distinctUntilChanged(Flow flow0, Function2 function20) {
        return FlowKt__DistinctKt.distinctUntilChanged(flow0, function20);
    }

    public static final Flow distinctUntilChangedBy(Flow flow0, Function1 function10) {
        return FlowKt__DistinctKt.distinctUntilChangedBy(flow0, function10);
    }

    public static final Flow drop(Flow flow0, int v) {
        return FlowKt__LimitKt.drop(flow0, v);
    }

    public static final Flow dropWhile(Flow flow0, Function2 function20) {
        return FlowKt__LimitKt.dropWhile(flow0, function20);
    }

    public static final Object emitAll(FlowCollector flowCollector0, ReceiveChannel receiveChannel0, Continuation continuation0) {
        return FlowKt__ChannelsKt.emitAll(flowCollector0, receiveChannel0, continuation0);
    }

    public static final Object emitAll(FlowCollector flowCollector0, Flow flow0, Continuation continuation0) {
        return FlowKt__CollectKt.emitAll(flowCollector0, flow0, continuation0);
    }

    public static final Flow emptyFlow() {
        return FlowKt__BuildersKt.emptyFlow();
    }

    public static final void ensureActive(FlowCollector flowCollector0) {
        FlowKt__EmittersKt.ensureActive(flowCollector0);
    }

    public static final Flow filter(Flow flow0, Function2 function20) {
        return FlowKt__TransformKt.filter(flow0, function20);
    }

    public static final Flow filterIsInstance(Flow flow0) {
        return FlowKt__TransformKt.filterIsInstance(flow0);
    }

    public static final Flow filterIsInstance(Flow flow0, KClass kClass0) {
        return FlowKt__TransformKt.filterIsInstance(flow0, kClass0);
    }

    public static final Flow filterNot(Flow flow0, Function2 function20) {
        return FlowKt__TransformKt.filterNot(flow0, function20);
    }

    public static final Flow filterNotNull(Flow flow0) {
        return FlowKt__TransformKt.filterNotNull(flow0);
    }

    public static final Object first(Flow flow0, Continuation continuation0) {
        return FlowKt__ReduceKt.first(flow0, continuation0);
    }

    public static final Object first(Flow flow0, Function2 function20, Continuation continuation0) {
        return FlowKt__ReduceKt.first(flow0, function20, continuation0);
    }

    public static final Object firstOrNull(Flow flow0, Continuation continuation0) {
        return FlowKt__ReduceKt.firstOrNull(flow0, continuation0);
    }

    public static final Object firstOrNull(Flow flow0, Function2 function20, Continuation continuation0) {
        return FlowKt__ReduceKt.firstOrNull(flow0, function20, continuation0);
    }

    public static final ReceiveChannel fixedPeriodTicker(CoroutineScope coroutineScope0, long v, long v1) {
        return FlowKt__DelayKt.fixedPeriodTicker(coroutineScope0, v, v1);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Flow analogue is \'flatMapConcat\'", replaceWith = @ReplaceWith(expression = "flatMapConcat(mapper)", imports = {}))
    public static final Flow flatMap(Flow flow0, Function2 function20) {
        return FlowKt__MigrationKt.flatMap(flow0, function20);
    }

    public static final Flow flatMapConcat(Flow flow0, Function2 function20) {
        return FlowKt__MergeKt.flatMapConcat(flow0, function20);
    }

    public static final Flow flatMapLatest(Flow flow0, Function2 function20) {
        return FlowKt__MergeKt.flatMapLatest(flow0, function20);
    }

    public static final Flow flatMapMerge(Flow flow0, int v, Function2 function20) {
        return FlowKt__MergeKt.flatMapMerge(flow0, v, function20);
    }

    public static Flow flatMapMerge$default(Flow flow0, int v, Function2 function20, int v1, Object object0) {
        return FlowKt__MergeKt.flatMapMerge$default(flow0, v, function20, v1, object0);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Flow analogue of \'flatten\' is \'flattenConcat\'", replaceWith = @ReplaceWith(expression = "flattenConcat()", imports = {}))
    public static final Flow flatten(Flow flow0) {
        return FlowKt__MigrationKt.flatten(flow0);
    }

    public static final Flow flattenConcat(Flow flow0) {
        return FlowKt__MergeKt.flattenConcat(flow0);
    }

    public static final Flow flattenMerge(Flow flow0, int v) {
        return FlowKt__MergeKt.flattenMerge(flow0, v);
    }

    public static Flow flattenMerge$default(Flow flow0, int v, int v1, Object object0) {
        return FlowKt__MergeKt.flattenMerge$default(flow0, v, v1, object0);
    }

    public static final Flow flow(Function2 function20) {
        return FlowKt__BuildersKt.flow(function20);
    }

    public static final Flow flowCombine(Flow flow0, Flow flow1, Function3 function30) {
        return FlowKt__ZipKt.flowCombine(flow0, flow1, function30);
    }

    public static final Flow flowCombineTransform(Flow flow0, Flow flow1, Function4 function40) {
        return FlowKt__ZipKt.flowCombineTransform(flow0, flow1, function40);
    }

    public static final Flow flowOf(Object object0) {
        return FlowKt__BuildersKt.flowOf(object0);
    }

    public static final Flow flowOf(Object[] arr_object) {
        return FlowKt__BuildersKt.flowOf(arr_object);
    }

    public static final Flow flowOn(Flow flow0, CoroutineContext coroutineContext0) {
        return FlowKt__ContextKt.flowOn(flow0, coroutineContext0);
    }

    public static final Object fold(Flow flow0, Object object0, Function3 function30, Continuation continuation0) {
        return FlowKt__ReduceKt.fold(flow0, object0, function30, continuation0);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Flow analogue of \'forEach\' is \'collect\'", replaceWith = @ReplaceWith(expression = "collect(action)", imports = {}))
    public static final void forEach(Flow flow0, Function2 function20) {
        FlowKt__MigrationKt.forEach(flow0, function20);
    }

    // 去混淆评级： 低(20)
    public static final int getDEFAULT_CONCURRENCY() {
        return 16;
    }

    public static void getDEFAULT_CONCURRENCY_PROPERTY_NAME$annotations() {
    }

    public static final Object last(Flow flow0, Continuation continuation0) {
        return FlowKt__ReduceKt.last(flow0, continuation0);
    }

    public static final Object lastOrNull(Flow flow0, Continuation continuation0) {
        return FlowKt__ReduceKt.lastOrNull(flow0, continuation0);
    }

    public static final Job launchIn(Flow flow0, CoroutineScope coroutineScope0) {
        return FlowKt__CollectKt.launchIn(flow0, coroutineScope0);
    }

    public static final Flow map(Flow flow0, Function2 function20) {
        return FlowKt__TransformKt.map(flow0, function20);
    }

    public static final Flow mapLatest(Flow flow0, Function2 function20) {
        return FlowKt__MergeKt.mapLatest(flow0, function20);
    }

    public static final Flow mapNotNull(Flow flow0, Function2 function20) {
        return FlowKt__TransformKt.mapNotNull(flow0, function20);
    }

    public static final Flow merge(Iterable iterable0) {
        return FlowKt__MergeKt.merge(iterable0);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Flow analogue of \'merge\' is \'flattenConcat\'", replaceWith = @ReplaceWith(expression = "flattenConcat()", imports = {}))
    public static final Flow merge(Flow flow0) {
        return FlowKt__MigrationKt.merge(flow0);
    }

    public static final Flow merge(Flow[] arr_flow) {
        return FlowKt__MergeKt.merge(arr_flow);
    }

    public static final Void noImpl() {
        return FlowKt__MigrationKt.noImpl();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Collect flow in the desired context instead")
    public static final Flow observeOn(Flow flow0, CoroutineContext coroutineContext0) {
        return FlowKt__MigrationKt.observeOn(flow0, coroutineContext0);
    }

    public static final Flow onCompletion(Flow flow0, Function3 function30) {
        return FlowKt__EmittersKt.onCompletion(flow0, function30);
    }

    public static final Flow onEach(Flow flow0, Function2 function20) {
        return FlowKt__TransformKt.onEach(flow0, function20);
    }

    public static final Flow onEmpty(Flow flow0, Function2 function20) {
        return FlowKt__EmittersKt.onEmpty(flow0, function20);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Flow analogue of \'onErrorXxx\' is \'catch\'. Use \'catch { emitAll(fallback) }\'", replaceWith = @ReplaceWith(expression = "catch { emitAll(fallback) }", imports = {}))
    public static final Flow onErrorResume(Flow flow0, Flow flow1) {
        return FlowKt__MigrationKt.onErrorResume(flow0, flow1);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Flow analogue of \'onErrorXxx\' is \'catch\'. Use \'catch { emitAll(fallback) }\'", replaceWith = @ReplaceWith(expression = "catch { emitAll(fallback) }", imports = {}))
    public static final Flow onErrorResumeNext(Flow flow0, Flow flow1) {
        return FlowKt__MigrationKt.onErrorResumeNext(flow0, flow1);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Flow analogue of \'onErrorXxx\' is \'catch\'. Use \'catch { emit(fallback) }\'", replaceWith = @ReplaceWith(expression = "catch { emit(fallback) }", imports = {}))
    public static final Flow onErrorReturn(Flow flow0, Object object0) {
        return FlowKt__MigrationKt.onErrorReturn(flow0, object0);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Flow analogue of \'onErrorXxx\' is \'catch\'. Use \'catch { e -> if (predicate(e)) emit(fallback) else throw e }\'", replaceWith = @ReplaceWith(expression = "catch { e -> if (predicate(e)) emit(fallback) else throw e }", imports = {}))
    public static final Flow onErrorReturn(Flow flow0, Object object0, Function1 function10) {
        return FlowKt__MigrationKt.onErrorReturn(flow0, object0, function10);
    }

    public static Flow onErrorReturn$default(Flow flow0, Object object0, Function1 function10, int v, Object object1) {
        return FlowKt__MigrationKt.onErrorReturn$default(flow0, object0, function10, v, object1);
    }

    public static final Flow onStart(Flow flow0, Function2 function20) {
        return FlowKt__EmittersKt.onStart(flow0, function20);
    }

    public static final SharedFlow onSubscription(SharedFlow sharedFlow0, Function2 function20) {
        return FlowKt__ShareKt.onSubscription(sharedFlow0, function20);
    }

    public static final ReceiveChannel produceIn(Flow flow0, CoroutineScope coroutineScope0) {
        return FlowKt__ChannelsKt.produceIn(flow0, coroutineScope0);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Flow analogue of \'publish()\' is \'shareIn\'. \npublish().connect() is the default strategy (no extra call is needed), \npublish().autoConnect() translates to \'started = SharingStared.Lazily\' argument, \npublish().refCount() translates to \'started = SharingStared.WhileSubscribed()\' argument.", replaceWith = @ReplaceWith(expression = "this.shareIn(scope, 0)", imports = {}))
    public static final Flow publish(Flow flow0) {
        return FlowKt__MigrationKt.publish(flow0);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Flow analogue of \'publish(bufferSize)\' is \'buffer\' followed by \'shareIn\'. \npublish().connect() is the default strategy (no extra call is needed), \npublish().autoConnect() translates to \'started = SharingStared.Lazily\' argument, \npublish().refCount() translates to \'started = SharingStared.WhileSubscribed()\' argument.", replaceWith = @ReplaceWith(expression = "this.buffer(bufferSize).shareIn(scope, 0)", imports = {}))
    public static final Flow publish(Flow flow0, int v) {
        return FlowKt__MigrationKt.publish(flow0, v);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Collect flow in the desired context instead")
    public static final Flow publishOn(Flow flow0, CoroutineContext coroutineContext0) {
        return FlowKt__MigrationKt.publishOn(flow0, coroutineContext0);
    }

    public static final Flow receiveAsFlow(ReceiveChannel receiveChannel0) {
        return FlowKt__ChannelsKt.receiveAsFlow(receiveChannel0);
    }

    public static final Object reduce(Flow flow0, Function3 function30, Continuation continuation0) {
        return FlowKt__ReduceKt.reduce(flow0, function30, continuation0);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Flow analogue of \'replay()\' is \'shareIn\' with unlimited replay. \nreplay().connect() is the default strategy (no extra call is needed), \nreplay().autoConnect() translates to \'started = SharingStared.Lazily\' argument, \nreplay().refCount() translates to \'started = SharingStared.WhileSubscribed()\' argument.", replaceWith = @ReplaceWith(expression = "this.shareIn(scope, Int.MAX_VALUE)", imports = {}))
    public static final Flow replay(Flow flow0) {
        return FlowKt__MigrationKt.replay(flow0);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Flow analogue of \'replay(bufferSize)\' is \'shareIn\' with the specified replay parameter. \nreplay().connect() is the default strategy (no extra call is needed), \nreplay().autoConnect() translates to \'started = SharingStared.Lazily\' argument, \nreplay().refCount() translates to \'started = SharingStared.WhileSubscribed()\' argument.", replaceWith = @ReplaceWith(expression = "this.shareIn(scope, bufferSize)", imports = {}))
    public static final Flow replay(Flow flow0, int v) {
        return FlowKt__MigrationKt.replay(flow0, v);
    }

    public static final Flow retry(Flow flow0, long v, Function2 function20) {
        return FlowKt__ErrorsKt.retry(flow0, v, function20);
    }

    public static Flow retry$default(Flow flow0, long v, Function2 function20, int v1, Object object0) {
        return FlowKt__ErrorsKt.retry$default(flow0, v, function20, v1, object0);
    }

    public static final Flow retryWhen(Flow flow0, Function4 function40) {
        return FlowKt__ErrorsKt.retryWhen(flow0, function40);
    }

    public static final Flow runningFold(Flow flow0, Object object0, Function3 function30) {
        return FlowKt__TransformKt.runningFold(flow0, object0, function30);
    }

    public static final Flow runningReduce(Flow flow0, Function3 function30) {
        return FlowKt__TransformKt.runningReduce(flow0, function30);
    }

    public static final Flow sample(Flow flow0, long v) {
        return FlowKt__DelayKt.sample(flow0, v);
    }

    public static final Flow sample-HG0u8IE(Flow flow0, long v) {
        return FlowKt__DelayKt.sample-HG0u8IE(flow0, v);
    }

    public static final Flow scan(Flow flow0, Object object0, Function3 function30) {
        return FlowKt__TransformKt.scan(flow0, object0, function30);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Flow has less verbose \'scan\' shortcut", replaceWith = @ReplaceWith(expression = "scan(initial, operation)", imports = {}))
    public static final Flow scanFold(Flow flow0, Object object0, Function3 function30) {
        return FlowKt__MigrationKt.scanFold(flow0, object0, function30);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "\'scanReduce\' was renamed to \'runningReduce\' to be consistent with Kotlin standard library", replaceWith = @ReplaceWith(expression = "runningReduce(operation)", imports = {}))
    public static final Flow scanReduce(Flow flow0, Function3 function30) {
        return FlowKt__MigrationKt.scanReduce(flow0, function30);
    }

    public static final SharedFlow shareIn(Flow flow0, CoroutineScope coroutineScope0, SharingStarted sharingStarted0, int v) {
        return FlowKt__ShareKt.shareIn(flow0, coroutineScope0, sharingStarted0, v);
    }

    public static SharedFlow shareIn$default(Flow flow0, CoroutineScope coroutineScope0, SharingStarted sharingStarted0, int v, int v1, Object object0) {
        return FlowKt__ShareKt.shareIn$default(flow0, coroutineScope0, sharingStarted0, v, v1, object0);
    }

    public static final Object single(Flow flow0, Continuation continuation0) {
        return FlowKt__ReduceKt.single(flow0, continuation0);
    }

    public static final Object singleOrNull(Flow flow0, Continuation continuation0) {
        return FlowKt__ReduceKt.singleOrNull(flow0, continuation0);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Flow analogue of \'skip\' is \'drop\'", replaceWith = @ReplaceWith(expression = "drop(count)", imports = {}))
    public static final Flow skip(Flow flow0, int v) {
        return FlowKt__MigrationKt.skip(flow0, v);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Flow analogue of \'startWith\' is \'onStart\'. Use \'onStart { emit(value) }\'", replaceWith = @ReplaceWith(expression = "onStart { emit(value) }", imports = {}))
    public static final Flow startWith(Flow flow0, Object object0) {
        return FlowKt__MigrationKt.startWith(flow0, object0);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Flow analogue of \'startWith\' is \'onStart\'. Use \'onStart { emitAll(other) }\'", replaceWith = @ReplaceWith(expression = "onStart { emitAll(other) }", imports = {}))
    public static final Flow startWith(Flow flow0, Flow flow1) {
        return FlowKt__MigrationKt.startWith(flow0, flow1);
    }

    public static final Object stateIn(Flow flow0, CoroutineScope coroutineScope0, Continuation continuation0) {
        return FlowKt__ShareKt.stateIn(flow0, coroutineScope0, continuation0);
    }

    public static final StateFlow stateIn(Flow flow0, CoroutineScope coroutineScope0, SharingStarted sharingStarted0, Object object0) {
        return FlowKt__ShareKt.stateIn(flow0, coroutineScope0, sharingStarted0, object0);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use \'launchIn\' with \'onEach\', \'onCompletion\' and \'catch\' instead")
    public static final void subscribe(Flow flow0) {
        FlowKt__MigrationKt.subscribe(flow0);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use \'launchIn\' with \'onEach\', \'onCompletion\' and \'catch\' instead")
    public static final void subscribe(Flow flow0, Function2 function20) {
        FlowKt__MigrationKt.subscribe(flow0, function20);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use \'launchIn\' with \'onEach\', \'onCompletion\' and \'catch\' instead")
    public static final void subscribe(Flow flow0, Function2 function20, Function2 function21) {
        FlowKt__MigrationKt.subscribe(flow0, function20, function21);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use \'flowOn\' instead")
    public static final Flow subscribeOn(Flow flow0, CoroutineContext coroutineContext0) {
        return FlowKt__MigrationKt.subscribeOn(flow0, coroutineContext0);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Flow analogues of \'switchMap\' are \'transformLatest\', \'flatMapLatest\' and \'mapLatest\'", replaceWith = @ReplaceWith(expression = "this.flatMapLatest(transform)", imports = {}))
    public static final Flow switchMap(Flow flow0, Function2 function20) {
        return FlowKt__MigrationKt.switchMap(flow0, function20);
    }

    public static final Flow take(Flow flow0, int v) {
        return FlowKt__LimitKt.take(flow0, v);
    }

    public static final Flow takeWhile(Flow flow0, Function2 function20) {
        return FlowKt__LimitKt.takeWhile(flow0, function20);
    }

    public static final Flow timeout-HG0u8IE(Flow flow0, long v) {
        return FlowKt__DelayKt.timeout-HG0u8IE(flow0, v);
    }

    public static final Object toCollection(Flow flow0, Collection collection0, Continuation continuation0) {
        return FlowKt__CollectionKt.toCollection(flow0, collection0, continuation0);
    }

    public static final Object toList(Flow flow0, List list0, Continuation continuation0) {
        return FlowKt__CollectionKt.toList(flow0, list0, continuation0);
    }

    public static final Object toSet(Flow flow0, Set set0, Continuation continuation0) {
        return FlowKt__CollectionKt.toSet(flow0, set0, continuation0);
    }

    public static final Flow transform(Flow flow0, Function3 function30) {
        return FlowKt__EmittersKt.transform(flow0, function30);
    }

    public static final Flow transformLatest(Flow flow0, Function3 function30) {
        return FlowKt__MergeKt.transformLatest(flow0, function30);
    }

    public static final Flow transformWhile(Flow flow0, Function3 function30) {
        return FlowKt__LimitKt.transformWhile(flow0, function30);
    }

    public static final Flow unsafeTransform(Flow flow0, Function3 function30) {
        return FlowKt__EmittersKt.unsafeTransform(flow0, function30);
    }

    public static final Flow withIndex(Flow flow0) {
        return FlowKt__TransformKt.withIndex(flow0);
    }

    public static final Flow zip(Flow flow0, Flow flow1, Function3 function30) {
        return FlowKt__ZipKt.zip(flow0, flow1, function30);
    }
}

