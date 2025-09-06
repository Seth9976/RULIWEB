package androidx.work.impl.constraints;

import android.net.ConnectivityManager.NetworkCallback;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import androidx.work.Logger;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref.BooleanRef;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u0000 \u000E2\u00020\u0001:\u0001\u000EB\u001F\b\u0002\u0012\u0016\u0010\u0002\u001A\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003j\u0002`\u0006¢\u0006\u0002\u0010\u0007J\u0018\u0010\b\u001A\u00020\u00052\u0006\u0010\t\u001A\u00020\n2\u0006\u0010\u000B\u001A\u00020\fH\u0016J\u0010\u0010\r\u001A\u00020\u00052\u0006\u0010\t\u001A\u00020\nH\u0016R\u001E\u0010\u0002\u001A\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003j\u0002`\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000F"}, d2 = {"Landroidx/work/impl/constraints/IndividualNetworkCallback;", "Landroid/net/ConnectivityManager$NetworkCallback;", "onConstraintState", "Lkotlin/Function1;", "Landroidx/work/impl/constraints/ConstraintsState;", "", "Landroidx/work/impl/constraints/OnConstraintState;", "(Lkotlin/jvm/functions/Function1;)V", "onCapabilitiesChanged", "network", "Landroid/net/Network;", "networkCapabilities", "Landroid/net/NetworkCapabilities;", "onLost", "Companion", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
final class IndividualNetworkCallback extends ConnectivityManager.NetworkCallback {
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J4\u0010\u0003\u001A\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001A\u00020\u00072\u0006\u0010\b\u001A\u00020\t2\u0016\u0010\n\u001A\u0012\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00050\u000Bj\u0002`\r¨\u0006\u000E"}, d2 = {"Landroidx/work/impl/constraints/IndividualNetworkCallback$Companion;", "", "()V", "addCallback", "Lkotlin/Function0;", "", "connManager", "Landroid/net/ConnectivityManager;", "networkRequest", "Landroid/net/NetworkRequest;", "onConstraintState", "Lkotlin/Function1;", "Landroidx/work/impl/constraints/ConstraintsState;", "Landroidx/work/impl/constraints/OnConstraintState;", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final Function0 addCallback(ConnectivityManager connectivityManager0, NetworkRequest networkRequest0, Function1 function10) {
            Intrinsics.checkNotNullParameter(connectivityManager0, "connManager");
            Intrinsics.checkNotNullParameter(networkRequest0, "networkRequest");
            Intrinsics.checkNotNullParameter(function10, "onConstraintState");
            IndividualNetworkCallback individualNetworkCallback0 = new IndividualNetworkCallback(function10, null);
            BooleanRef ref$BooleanRef0 = new BooleanRef();
            try {
                Logger.get().debug("WM-WorkConstraintsTrack", "NetworkRequestConstraintController register callback");
                connectivityManager0.registerNetworkCallback(networkRequest0, individualNetworkCallback0);
                ref$BooleanRef0.element = true;
                return new Function0(connectivityManager0, individualNetworkCallback0) {
                    final BooleanRef $callbackRegistered;
                    final ConnectivityManager $connManager;
                    final IndividualNetworkCallback $networkCallback;

                    {
                        this.$callbackRegistered = ref$BooleanRef0;
                        this.$connManager = connectivityManager0;
                        this.$networkCallback = individualNetworkCallback0;
                        super(0);
                    }

                    @Override  // kotlin.jvm.functions.Function0
                    public Object invoke() {
                        this.invoke();
                        return Unit.INSTANCE;
                    }

                    public final void invoke() {
                        if(this.$callbackRegistered.element) {
                            Logger.get().debug("WM-WorkConstraintsTrack", "NetworkRequestConstraintController unregister callback");
                            this.$connManager.unregisterNetworkCallback(this.$networkCallback);
                        }
                    }
                };
            }
            catch(RuntimeException runtimeException0) {
                String s = runtimeException0.getClass().getName();
                Intrinsics.checkNotNullExpressionValue(s, "ex.javaClass.name");
                if(!StringsKt.endsWith$default(s, "TooManyRequestsException", false, 2, null)) {
                    throw runtimeException0;
                }
                Logger.get().debug("WM-WorkConstraintsTrack", "NetworkRequestConstraintController couldn\'t register callback", runtimeException0);
                function10.invoke(new ConstraintsNotMet(7));
                return new Function0(connectivityManager0, individualNetworkCallback0) {
                    final BooleanRef $callbackRegistered;
                    final ConnectivityManager $connManager;
                    final IndividualNetworkCallback $networkCallback;

                    {
                        this.$callbackRegistered = ref$BooleanRef0;
                        this.$connManager = connectivityManager0;
                        this.$networkCallback = individualNetworkCallback0;
                        super(0);
                    }

                    @Override  // kotlin.jvm.functions.Function0
                    public Object invoke() {
                        this.invoke();
                        return Unit.INSTANCE;
                    }

                    public final void invoke() {
                        if(this.$callbackRegistered.element) {
                            Logger.get().debug("WM-WorkConstraintsTrack", "NetworkRequestConstraintController unregister callback");
                            this.$connManager.unregisterNetworkCallback(this.$networkCallback);
                        }
                    }
                };
            }
        }
    }

    public static final Companion Companion;
    private final Function1 onConstraintState;

    static {
        IndividualNetworkCallback.Companion = new Companion(null);
    }

    private IndividualNetworkCallback(Function1 function10) {
        this.onConstraintState = function10;
    }

    public IndividualNetworkCallback(Function1 function10, DefaultConstructorMarker defaultConstructorMarker0) {
        this(function10);
    }

    @Override  // android.net.ConnectivityManager$NetworkCallback
    public void onCapabilitiesChanged(Network network0, NetworkCapabilities networkCapabilities0) {
        Intrinsics.checkNotNullParameter(network0, "network");
        Intrinsics.checkNotNullParameter(networkCapabilities0, "networkCapabilities");
        Logger.get().debug("WM-WorkConstraintsTrack", "NetworkRequestConstraintController onCapabilitiesChanged callback");
        this.onConstraintState.invoke(ConstraintsMet.INSTANCE);
    }

    @Override  // android.net.ConnectivityManager$NetworkCallback
    public void onLost(Network network0) {
        Intrinsics.checkNotNullParameter(network0, "network");
        Logger.get().debug("WM-WorkConstraintsTrack", "NetworkRequestConstraintController onLost callback");
        ConstraintsNotMet constraintsState$ConstraintsNotMet0 = new ConstraintsNotMet(7);
        this.onConstraintState.invoke(constraintsState$ConstraintsNotMet0);
    }
}

