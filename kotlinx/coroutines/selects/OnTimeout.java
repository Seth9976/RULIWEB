package kotlinx.coroutines.selects;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlinx.coroutines.DelayKt;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001E\u0010\u000B\u001A\u00020\f2\n\u0010\r\u001A\u0006\u0012\u0002\b\u00030\u000E2\b\u0010\u000F\u001A\u0004\u0018\u00010\u0001H\u0002R\u0017\u0010\u0005\u001A\u00020\u00068F¢\u0006\f\u0012\u0004\b\u0007\u0010\b\u001A\u0004\b\t\u0010\nR\u000E\u0010\u0002\u001A\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lkotlinx/coroutines/selects/OnTimeout;", "", "timeMillis", "", "(J)V", "selectClause", "Lkotlinx/coroutines/selects/SelectClause0;", "getSelectClause$annotations", "()V", "getSelectClause", "()Lkotlinx/coroutines/selects/SelectClause0;", "register", "", "select", "Lkotlinx/coroutines/selects/SelectInstance;", "ignoredParam", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
final class OnTimeout {
    private final long timeMillis;

    public OnTimeout(long v) {
        this.timeMillis = v;
    }

    public final SelectClause0 getSelectClause() {
        Intrinsics.checkNotNull(kotlinx.coroutines.selects.OnTimeout.selectClause.1.INSTANCE, "null cannot be cast to non-null type kotlin.Function3<@[ParameterName(name = \'clauseObject\')] kotlin.Any, @[ParameterName(name = \'select\')] kotlinx.coroutines.selects.SelectInstance<*>, @[ParameterName(name = \'param\')] kotlin.Any?, kotlin.Unit>{ kotlinx.coroutines.selects.SelectKt.RegistrationFunction }");
        return new SelectClause0Impl(this, ((Function3)TypeIntrinsics.beforeCheckcastToFunctionOfArity(kotlinx.coroutines.selects.OnTimeout.selectClause.1.INSTANCE, 3)), null, 4, null);

        @Metadata(k = 3, mv = {1, 8, 0}, xi = 0x30)
        final class kotlinx.coroutines.selects.OnTimeout.selectClause.1 extends FunctionReferenceImpl implements Function3 {
            public static final kotlinx.coroutines.selects.OnTimeout.selectClause.1 INSTANCE;

            static {
                kotlinx.coroutines.selects.OnTimeout.selectClause.1.INSTANCE = new kotlinx.coroutines.selects.OnTimeout.selectClause.1();
            }

            kotlinx.coroutines.selects.OnTimeout.selectClause.1() {
                super(3, OnTimeout.class, "register", "register(Lkotlinx/coroutines/selects/SelectInstance;Ljava/lang/Object;)V", 0);
            }

            @Override  // kotlin.jvm.functions.Function3
            public Object invoke(Object object0, Object object1, Object object2) {
                this.invoke(((OnTimeout)object0), ((SelectInstance)object1), object2);
                return Unit.INSTANCE;
            }

            public final void invoke(OnTimeout onTimeout0, SelectInstance selectInstance0, Object object0) {
                onTimeout0.register(selectInstance0, object0);
            }
        }

    }

    public static void getSelectClause$annotations() {
    }

    private final void register(SelectInstance selectInstance0, Object object0) {
        if(this.timeMillis <= 0L) {
            selectInstance0.selectInRegistrationPhase(Unit.INSTANCE);
            return;
        }
        Runnable runnable0 = new Runnable() {
            @Override
            public final void run() {
                this.trySelect(OnTimeout.this, Unit.INSTANCE);
            }
        };
        Intrinsics.checkNotNull(selectInstance0, "null cannot be cast to non-null type kotlinx.coroutines.selects.SelectImplementation<*>");
        SelectImplementation selectImplementation0 = (SelectImplementation)selectInstance0;
        CoroutineContext coroutineContext0 = selectInstance0.getContext();
        selectInstance0.disposeOnCompletion(DelayKt.getDelay(coroutineContext0).invokeOnTimeout(this.timeMillis, runnable0, coroutineContext0));
    }
}

