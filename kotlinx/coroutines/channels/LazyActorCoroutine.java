package kotlinx.coroutines.channels;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlinx.coroutines.intrinsics.CancellableKt;
import kotlinx.coroutines.selects.SelectClause2;
import kotlinx.coroutines.selects.SelectClause2Impl;
import kotlinx.coroutines.selects.SelectInstance;

@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002BM\u0012\u0006\u0010\u0003\u001A\u00020\u0004\u0012\f\u0010\u0005\u001A\b\u0012\u0004\u0012\u00028\u00000\u0006\u0012-\u0010\u0007\u001A)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000B0\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\b¢\u0006\u0002\b\rø\u0001\u0000¢\u0006\u0002\u0010\u000EJ\u0012\u0010\u0017\u001A\u00020\u00182\b\u0010\u0019\u001A\u0004\u0018\u00010\u001AH\u0016J\u0015\u0010\u001B\u001A\u00020\u00182\u0006\u0010\u001C\u001A\u00028\u0000H\u0017¢\u0006\u0002\u0010\u001DJ\u001E\u0010\u001E\u001A\u00020\u000B2\n\u0010\u001F\u001A\u0006\u0012\u0002\b\u00030 2\b\u0010\u001C\u001A\u0004\u0018\u00010\fH\u0002J\b\u0010!\u001A\u00020\u000BH\u0014J\u0019\u0010\"\u001A\u00020\u000B2\u0006\u0010\u001C\u001A\u00028\u0000H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010#J&\u0010$\u001A\b\u0012\u0004\u0012\u00020\u000B0%2\u0006\u0010\u001C\u001A\u00028\u0000H\u0016ø\u0001\u0001ø\u0001\u0002ø\u0001\u0000¢\u0006\u0004\b&\u0010\'R\u0014\u0010\u000F\u001A\b\u0012\u0004\u0012\u00020\u000B0\nX\u0082\u000E¢\u0006\u0002\n\u0000R,\u0010\u0010\u001A\u0014\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00120\u00118VX\u0096\u0004¢\u0006\f\u0012\u0004\b\u0013\u0010\u0014\u001A\u0004\b\u0015\u0010\u0016\u0082\u0002\u000F\n\u0002\b\u0019\n\u0002\b!\n\u0005\b¡\u001E0\u0001¨\u0006("}, d2 = {"Lkotlinx/coroutines/channels/LazyActorCoroutine;", "E", "Lkotlinx/coroutines/channels/ActorCoroutine;", "parentContext", "Lkotlin/coroutines/CoroutineContext;", "channel", "Lkotlinx/coroutines/channels/Channel;", "block", "Lkotlin/Function2;", "Lkotlinx/coroutines/channels/ActorScope;", "Lkotlin/coroutines/Continuation;", "", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/channels/Channel;Lkotlin/jvm/functions/Function2;)V", "continuation", "onSend", "Lkotlinx/coroutines/selects/SelectClause2;", "Lkotlinx/coroutines/channels/SendChannel;", "getOnSend$annotations", "()V", "getOnSend", "()Lkotlinx/coroutines/selects/SelectClause2;", "close", "", "cause", "", "offer", "element", "(Ljava/lang/Object;)Z", "onSendRegFunction", "select", "Lkotlinx/coroutines/selects/SelectInstance;", "onStart", "send", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "trySend", "Lkotlinx/coroutines/channels/ChannelResult;", "trySend-JP2dKIU", "(Ljava/lang/Object;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
final class LazyActorCoroutine extends ActorCoroutine {
    private Continuation continuation;

    public LazyActorCoroutine(CoroutineContext coroutineContext0, Channel channel0, Function2 function20) {
        super(coroutineContext0, channel0, false);
        this.continuation = IntrinsicsKt.createCoroutineUnintercepted(function20, this, this);
    }

    @Override  // kotlinx.coroutines.channels.ChannelCoroutine
    public boolean close(Throwable throwable0) {
        boolean z = super.close(throwable0);
        this.start();
        return z;
    }

    @Override  // kotlinx.coroutines.channels.ChannelCoroutine
    public SelectClause2 getOnSend() {
        Intrinsics.checkNotNull(kotlinx.coroutines.channels.LazyActorCoroutine.onSend.1.INSTANCE, "null cannot be cast to non-null type kotlin.Function3<@[ParameterName(name = \'clauseObject\')] kotlin.Any, @[ParameterName(name = \'select\')] kotlinx.coroutines.selects.SelectInstance<*>, @[ParameterName(name = \'param\')] kotlin.Any?, kotlin.Unit>{ kotlinx.coroutines.selects.SelectKt.RegistrationFunction }");
        return new SelectClause2Impl(this, ((Function3)TypeIntrinsics.beforeCheckcastToFunctionOfArity(kotlinx.coroutines.channels.LazyActorCoroutine.onSend.1.INSTANCE, 3)), super.getOnSend().getProcessResFunc(), null, 8, null);

        @Metadata(k = 3, mv = {1, 8, 0}, xi = 0x30)
        final class kotlinx.coroutines.channels.LazyActorCoroutine.onSend.1 extends FunctionReferenceImpl implements Function3 {
            public static final kotlinx.coroutines.channels.LazyActorCoroutine.onSend.1 INSTANCE;

            static {
                kotlinx.coroutines.channels.LazyActorCoroutine.onSend.1.INSTANCE = new kotlinx.coroutines.channels.LazyActorCoroutine.onSend.1();
            }

            kotlinx.coroutines.channels.LazyActorCoroutine.onSend.1() {
                super(3, LazyActorCoroutine.class, "onSendRegFunction", "onSendRegFunction(Lkotlinx/coroutines/selects/SelectInstance;Ljava/lang/Object;)V", 0);
            }

            @Override  // kotlin.jvm.functions.Function3
            public Object invoke(Object object0, Object object1, Object object2) {
                this.invoke(((LazyActorCoroutine)object0), ((SelectInstance)object1), object2);
                return Unit.INSTANCE;
            }

            public final void invoke(LazyActorCoroutine lazyActorCoroutine0, SelectInstance selectInstance0, Object object0) {
                lazyActorCoroutine0.onSendRegFunction(selectInstance0, object0);
            }
        }

    }

    public static void getOnSend$annotations() {
    }

    @Override  // kotlinx.coroutines.channels.ChannelCoroutine
    @Deprecated(level = DeprecationLevel.ERROR, message = "Deprecated in the favour of \'trySend\' method", replaceWith = @ReplaceWith(expression = "trySend(element).isSuccess", imports = {}))
    public boolean offer(Object object0) {
        this.start();
        return super.offer(object0);
    }

    private final void onSendRegFunction(SelectInstance selectInstance0, Object object0) {
        this.onStart();
        super.getOnSend().getRegFunc().invoke(this, selectInstance0, object0);
    }

    @Override  // kotlinx.coroutines.JobSupport
    protected void onStart() {
        CancellableKt.startCoroutineCancellable(this.continuation, this);
    }

    @Override  // kotlinx.coroutines.channels.ChannelCoroutine
    public Object send(Object object0, Continuation continuation0) {
        this.start();
        Object object1 = super.send(object0, continuation0);
        return object1 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? object1 : Unit.INSTANCE;
    }

    @Override  // kotlinx.coroutines.channels.ChannelCoroutine
    public Object trySend-JP2dKIU(Object object0) {
        this.start();
        return super.trySend-JP2dKIU(object0);
    }
}

