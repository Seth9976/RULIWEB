package okhttp3.internal.connection;

import java.io.IOException;
import kotlin.Metadata;
import kotlin.collections.ArrayDeque;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Address;
import okhttp3.HttpUrl;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001:\u0002\u0014\u0015J\u0014\u0010\u000B\u001A\u00020\f2\n\b\u0002\u0010\r\u001A\u0004\u0018\u00010\u000EH&J\b\u0010\u000F\u001A\u00020\fH&J\b\u0010\u0010\u001A\u00020\bH&J\u0010\u0010\u0011\u001A\u00020\f2\u0006\u0010\u0012\u001A\u00020\u0013H&R\u0012\u0010\u0002\u001A\u00020\u0003X¦\u0004¢\u0006\u0006\u001A\u0004\b\u0004\u0010\u0005R\u0018\u0010\u0006\u001A\b\u0012\u0004\u0012\u00020\b0\u0007X¦\u0004¢\u0006\u0006\u001A\u0004\b\t\u0010\nø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0016À\u0006\u0001"}, d2 = {"Lokhttp3/internal/connection/RoutePlanner;", "", "address", "Lokhttp3/Address;", "getAddress", "()Lokhttp3/Address;", "deferredPlans", "Lkotlin/collections/ArrayDeque;", "Lokhttp3/internal/connection/RoutePlanner$Plan;", "getDeferredPlans", "()Lkotlin/collections/ArrayDeque;", "hasNext", "", "failedConnection", "Lokhttp3/internal/connection/RealConnection;", "isCanceled", "plan", "sameHostAndPort", "url", "Lokhttp3/HttpUrl;", "ConnectResult", "Plan", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
public interface RoutePlanner {
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0002\b\r\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000E\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001A\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001A\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\u0010\u001A\u00020\u0003HÆ\u0003J\u000B\u0010\u0011\u001A\u0004\u0018\u00010\u0003HÆ\u0003J\u000B\u0010\u0012\u001A\u0004\u0018\u00010\u0006HÆ\u0003J+\u0010\u0013\u001A\u00020\u00002\b\b\u0002\u0010\u0002\u001A\u00020\u00032\n\b\u0002\u0010\u0004\u001A\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001A\u0004\u0018\u00010\u0006HÆ\u0001J\u0013\u0010\u0014\u001A\u00020\t2\b\u0010\u0015\u001A\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001A\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001A\u00020\u0019HÖ\u0001R\u0011\u0010\b\u001A\u00020\t8F¢\u0006\u0006\u001A\u0004\b\b\u0010\nR\u0013\u0010\u0004\u001A\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001A\u0004\b\u000B\u0010\fR\u0011\u0010\u0002\u001A\u00020\u0003¢\u0006\b\n\u0000\u001A\u0004\b\r\u0010\fR\u0013\u0010\u0005\u001A\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001A\u0004\b\u000E\u0010\u000F¨\u0006\u001A"}, d2 = {"Lokhttp3/internal/connection/RoutePlanner$ConnectResult;", "", "plan", "Lokhttp3/internal/connection/RoutePlanner$Plan;", "nextPlan", "throwable", "", "(Lokhttp3/internal/connection/RoutePlanner$Plan;Lokhttp3/internal/connection/RoutePlanner$Plan;Ljava/lang/Throwable;)V", "isSuccess", "", "()Z", "getNextPlan", "()Lokhttp3/internal/connection/RoutePlanner$Plan;", "getPlan", "getThrowable", "()Ljava/lang/Throwable;", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    public static final class ConnectResult {
        private final Plan nextPlan;
        private final Plan plan;
        private final Throwable throwable;

        public ConnectResult(Plan routePlanner$Plan0, Plan routePlanner$Plan1, Throwable throwable0) {
            Intrinsics.checkNotNullParameter(routePlanner$Plan0, "plan");
            super();
            this.plan = routePlanner$Plan0;
            this.nextPlan = routePlanner$Plan1;
            this.throwable = throwable0;
        }

        public ConnectResult(Plan routePlanner$Plan0, Plan routePlanner$Plan1, Throwable throwable0, int v, DefaultConstructorMarker defaultConstructorMarker0) {
            if((v & 2) != 0) {
                routePlanner$Plan1 = null;
            }
            if((v & 4) != 0) {
                throwable0 = null;
            }
            this(routePlanner$Plan0, routePlanner$Plan1, throwable0);
        }

        public final Plan component1() {
            return this.plan;
        }

        public final Plan component2() {
            return this.nextPlan;
        }

        public final Throwable component3() {
            return this.throwable;
        }

        public final ConnectResult copy(Plan routePlanner$Plan0, Plan routePlanner$Plan1, Throwable throwable0) {
            Intrinsics.checkNotNullParameter(routePlanner$Plan0, "plan");
            return new ConnectResult(routePlanner$Plan0, routePlanner$Plan1, throwable0);
        }

        public static ConnectResult copy$default(ConnectResult routePlanner$ConnectResult0, Plan routePlanner$Plan0, Plan routePlanner$Plan1, Throwable throwable0, int v, Object object0) {
            if((v & 1) != 0) {
                routePlanner$Plan0 = routePlanner$ConnectResult0.plan;
            }
            if((v & 2) != 0) {
                routePlanner$Plan1 = routePlanner$ConnectResult0.nextPlan;
            }
            if((v & 4) != 0) {
                throwable0 = routePlanner$ConnectResult0.throwable;
            }
            return routePlanner$ConnectResult0.copy(routePlanner$Plan0, routePlanner$Plan1, throwable0);
        }

        @Override
        public boolean equals(Object object0) {
            if(this == object0) {
                return true;
            }
            if(!(object0 instanceof ConnectResult)) {
                return false;
            }
            if(!Intrinsics.areEqual(this.plan, ((ConnectResult)object0).plan)) {
                return false;
            }
            return Intrinsics.areEqual(this.nextPlan, ((ConnectResult)object0).nextPlan) ? Intrinsics.areEqual(this.throwable, ((ConnectResult)object0).throwable) : false;
        }

        public final Plan getNextPlan() {
            return this.nextPlan;
        }

        public final Plan getPlan() {
            return this.plan;
        }

        public final Throwable getThrowable() {
            return this.throwable;
        }

        @Override
        public int hashCode() {
            int v = this.plan.hashCode();
            int v1 = 0;
            int v2 = this.nextPlan == null ? 0 : this.nextPlan.hashCode();
            Throwable throwable0 = this.throwable;
            if(throwable0 != null) {
                v1 = throwable0.hashCode();
            }
            return (v * 0x1F + v2) * 0x1F + v1;
        }

        public final boolean isSuccess() {
            return this.nextPlan == null && this.throwable == null;
        }

        @Override
        public String toString() {
            return "ConnectResult(plan=" + this.plan + ", nextPlan=" + this.nextPlan + ", throwable=" + this.throwable + ')';
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010\u0005\u001A\u00020\u0006H&J\b\u0010\u0007\u001A\u00020\bH&J\b\u0010\t\u001A\u00020\bH&J\b\u0010\n\u001A\u00020\u000BH&J\n\u0010\f\u001A\u0004\u0018\u00010\u0000H&R\u0012\u0010\u0002\u001A\u00020\u0003X¦\u0004¢\u0006\u0006\u001A\u0004\b\u0002\u0010\u0004ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\rÀ\u0006\u0001"}, d2 = {"Lokhttp3/internal/connection/RoutePlanner$Plan;", "", "isReady", "", "()Z", "cancel", "", "connectTcp", "Lokhttp3/internal/connection/RoutePlanner$ConnectResult;", "connectTlsEtc", "handleSuccess", "Lokhttp3/internal/connection/RealConnection;", "retry", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    public interface Plan {
        void cancel();

        ConnectResult connectTcp();

        ConnectResult connectTlsEtc();

        RealConnection handleSuccess();

        boolean isReady();

        Plan retry();
    }

    Address getAddress();

    ArrayDeque getDeferredPlans();

    boolean hasNext(RealConnection arg1);

    boolean isCanceled();

    Plan plan() throws IOException;

    boolean sameHostAndPort(HttpUrl arg1);
}

