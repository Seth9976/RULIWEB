package androidx.work.impl.model;

import android.net.NetworkRequest;
import android.net.Uri;
import android.os.Build.VERSION;
import androidx.work.BackoffPolicy;
import androidx.work.Constraints.ContentUriTrigger;
import androidx.work.NetworkType;
import androidx.work.OutOfQuotaPolicy;
import androidx.work.WorkInfo.State;
import androidx.work.impl.utils.NetworkRequest28;
import androidx.work.impl.utils.NetworkRequestCompat;
import androidx.work.impl.utils.NetworkRequestCompatKt;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.io.CloseableKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000F\bÆ\u0002\u0018\u00002\u00020\u0001:\u0004\"#$%B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u0006H\u0007J\u0016\u0010\u0007\u001A\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001A\u00020\u000BH\u0007J\u0015\u0010\f\u001A\u00020\u000B2\u0006\u0010\r\u001A\u00020\u000EH\u0001¢\u0006\u0002\b\u000FJ\u0010\u0010\u0010\u001A\u00020\u00062\u0006\u0010\u0011\u001A\u00020\u0004H\u0007J\u0010\u0010\u0012\u001A\u00020\u00132\u0006\u0010\u0011\u001A\u00020\u0004H\u0007J\u0010\u0010\u0014\u001A\u00020\u00152\u0006\u0010\u0011\u001A\u00020\u0004H\u0007J\u0010\u0010\u0016\u001A\u00020\u00172\u0006\u0010\u0011\u001A\u00020\u0004H\u0007J\u0010\u0010\u0018\u001A\u00020\u00042\u0006\u0010\u0019\u001A\u00020\u0013H\u0007J\u0010\u0010\u001A\u001A\u00020\u00042\u0006\u0010\u001B\u001A\u00020\u0015H\u0007J\u0016\u0010\u001C\u001A\u00020\u000B2\f\u0010\u001D\u001A\b\u0012\u0004\u0012\u00020\t0\bH\u0007J\u0010\u0010\u001E\u001A\u00020\u00042\u0006\u0010\u001F\u001A\u00020\u0017H\u0007J\u0015\u0010 \u001A\u00020\u000E2\u0006\u0010\n\u001A\u00020\u000BH\u0001¢\u0006\u0002\b!¨\u0006&"}, d2 = {"Landroidx/work/impl/model/WorkTypeConverters;", "", "()V", "backoffPolicyToInt", "", "backoffPolicy", "Landroidx/work/BackoffPolicy;", "byteArrayToSetOfTriggers", "", "Landroidx/work/Constraints$ContentUriTrigger;", "bytes", "", "fromNetworkRequest", "requestCompat", "Landroidx/work/impl/utils/NetworkRequestCompat;", "fromNetworkRequest$work_runtime_release", "intToBackoffPolicy", "value", "intToNetworkType", "Landroidx/work/NetworkType;", "intToOutOfQuotaPolicy", "Landroidx/work/OutOfQuotaPolicy;", "intToState", "Landroidx/work/WorkInfo$State;", "networkTypeToInt", "networkType", "outOfQuotaPolicyToInt", "policy", "setOfTriggersToByteArray", "triggers", "stateToInt", "state", "toNetworkRequest", "toNetworkRequest$work_runtime_release", "BackoffPolicyIds", "NetworkTypeIds", "OutOfPolicyIds", "StateIds", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class WorkTypeConverters {
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\bÂ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000E\u0010\u0003\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000E\u0010\u0005\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Landroidx/work/impl/model/WorkTypeConverters$BackoffPolicyIds;", "", "()V", "EXPONENTIAL", "", "LINEAR", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    static final class BackoffPolicyIds {
        public static final int EXPONENTIAL = 0;
        public static final BackoffPolicyIds INSTANCE = null;
        public static final int LINEAR = 1;

        static {
            BackoffPolicyIds.INSTANCE = new BackoffPolicyIds();
        }
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\bÂ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000E\u0010\u0003\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000E\u0010\u0005\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000E\u0010\u0006\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000E\u0010\u0007\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000E\u0010\b\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000E\u0010\t\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Landroidx/work/impl/model/WorkTypeConverters$NetworkTypeIds;", "", "()V", "CONNECTED", "", "METERED", "NOT_REQUIRED", "NOT_ROAMING", "TEMPORARILY_UNMETERED", "UNMETERED", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    static final class NetworkTypeIds {
        public static final int CONNECTED = 1;
        public static final NetworkTypeIds INSTANCE = null;
        public static final int METERED = 4;
        public static final int NOT_REQUIRED = 0;
        public static final int NOT_ROAMING = 3;
        public static final int TEMPORARILY_UNMETERED = 5;
        public static final int UNMETERED = 2;

        static {
            NetworkTypeIds.INSTANCE = new NetworkTypeIds();
        }
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\bÂ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000E\u0010\u0003\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000E\u0010\u0005\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Landroidx/work/impl/model/WorkTypeConverters$OutOfPolicyIds;", "", "()V", "DROP_WORK_REQUEST", "", "RUN_AS_NON_EXPEDITED_WORK_REQUEST", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    static final class OutOfPolicyIds {
        public static final int DROP_WORK_REQUEST = 1;
        public static final OutOfPolicyIds INSTANCE;
        public static final int RUN_AS_NON_EXPEDITED_WORK_REQUEST;

        static {
            OutOfPolicyIds.INSTANCE = new OutOfPolicyIds();
        }
    }

    @Metadata(d1 = {"\u0000\u001C\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000E\u0010\u0003\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000E\u0010\u0005\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000E\u0010\u0006\u001A\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000E\u0010\b\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000E\u0010\t\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000E\u0010\n\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000E\u0010\u000B\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Landroidx/work/impl/model/WorkTypeConverters$StateIds;", "", "()V", "BLOCKED", "", "CANCELLED", "COMPLETED_STATES", "", "ENQUEUED", "FAILED", "RUNNING", "SUCCEEDED", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class StateIds {
        public static final int BLOCKED = 4;
        public static final int CANCELLED = 5;
        public static final String COMPLETED_STATES = "(2, 3, 5)";
        public static final int ENQUEUED = 0;
        public static final int FAILED = 3;
        public static final StateIds INSTANCE = null;
        public static final int RUNNING = 1;
        public static final int SUCCEEDED = 2;

        static {
            StateIds.INSTANCE = new StateIds();
        }
    }

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 0x30)
    public final class WhenMappings {
        public static final int[] $EnumSwitchMapping$0;
        public static final int[] $EnumSwitchMapping$1;
        public static final int[] $EnumSwitchMapping$2;
        public static final int[] $EnumSwitchMapping$3;

        static {
            int[] arr_v = new int[State.values().length];
            try {
                arr_v[State.ENQUEUED.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[State.RUNNING.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[State.SUCCEEDED.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[State.FAILED.ordinal()] = 4;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[State.BLOCKED.ordinal()] = 5;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[State.CANCELLED.ordinal()] = 6;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            WhenMappings.$EnumSwitchMapping$0 = arr_v;
            int[] arr_v1 = new int[BackoffPolicy.values().length];
            try {
                arr_v1[BackoffPolicy.EXPONENTIAL.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v1[BackoffPolicy.LINEAR.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            WhenMappings.$EnumSwitchMapping$1 = arr_v1;
            int[] arr_v2 = new int[NetworkType.values().length];
            try {
                arr_v2[NetworkType.NOT_REQUIRED.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v2[NetworkType.CONNECTED.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v2[NetworkType.UNMETERED.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v2[NetworkType.NOT_ROAMING.ordinal()] = 4;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v2[NetworkType.METERED.ordinal()] = 5;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            WhenMappings.$EnumSwitchMapping$2 = arr_v2;
            int[] arr_v3 = new int[OutOfQuotaPolicy.values().length];
            try {
                arr_v3[OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v3[OutOfQuotaPolicy.DROP_WORK_REQUEST.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            WhenMappings.$EnumSwitchMapping$3 = arr_v3;
        }
    }

    public static final WorkTypeConverters INSTANCE;

    static {
        WorkTypeConverters.INSTANCE = new WorkTypeConverters();
    }

    @JvmStatic
    public static final int backoffPolicyToInt(BackoffPolicy backoffPolicy0) {
        Intrinsics.checkNotNullParameter(backoffPolicy0, "backoffPolicy");
        switch(WhenMappings.$EnumSwitchMapping$1[backoffPolicy0.ordinal()]) {
            case 1: {
                return 0;
            }
            case 2: {
                return 1;
            }
            default: {
                throw new NoWhenBranchMatchedException();
            }
        }
    }

    @JvmStatic
    public static final Set byteArrayToSetOfTriggers(byte[] arr_b) {
        Intrinsics.checkNotNullParameter(arr_b, "bytes");
        Set set0 = new LinkedHashSet();
        if(arr_b.length == 0) {
            return set0;
        }
        ByteArrayInputStream byteArrayInputStream0 = new ByteArrayInputStream(arr_b);
        try {
            try {
                Closeable closeable0 = new ObjectInputStream(byteArrayInputStream0);
                try {
                    int v = ((ObjectInputStream)closeable0).readInt();
                    for(int v1 = 0; v1 < v; ++v1) {
                        Uri uri0 = Uri.parse(((ObjectInputStream)closeable0).readUTF());
                        boolean z = ((ObjectInputStream)closeable0).readBoolean();
                        Intrinsics.checkNotNullExpressionValue(uri0, "uri");
                        set0.add(new ContentUriTrigger(uri0, z));
                    }
                }
                catch(Throwable throwable1) {
                    CloseableKt.closeFinally(closeable0, throwable1);
                    throw throwable1;
                }
                CloseableKt.closeFinally(closeable0, null);
            }
            catch(IOException iOException0) {
                iOException0.printStackTrace();
            }
        }
        catch(Throwable throwable0) {
            CloseableKt.closeFinally(byteArrayInputStream0, throwable0);
            throw throwable0;
        }
        CloseableKt.closeFinally(byteArrayInputStream0, null);
        return set0;
    }

    @JvmStatic
    public static final byte[] fromNetworkRequest$work_runtime_release(NetworkRequestCompat networkRequestCompat0) {
        Intrinsics.checkNotNullParameter(networkRequestCompat0, "requestCompat");
        if(Build.VERSION.SDK_INT < 28) {
            return new byte[0];
        }
        NetworkRequest networkRequest0 = networkRequestCompat0.getNetworkRequest();
        if(networkRequest0 == null) {
            return new byte[0];
        }
        ByteArrayOutputStream byteArrayOutputStream0 = new ByteArrayOutputStream();
        try {
            Closeable closeable0 = new ObjectOutputStream(byteArrayOutputStream0);
            try {
                int[] arr_v = NetworkRequestCompatKt.getTransportTypesCompat(networkRequest0);
                int[] arr_v1 = NetworkRequestCompatKt.getCapabilitiesCompat(networkRequest0);
                ((ObjectOutputStream)closeable0).writeInt(arr_v.length);
                for(int v1 = 0; v1 < arr_v.length; ++v1) {
                    ((ObjectOutputStream)closeable0).writeInt(arr_v[v1]);
                }
                ((ObjectOutputStream)closeable0).writeInt(arr_v1.length);
                for(int v = 0; v < arr_v1.length; ++v) {
                    ((ObjectOutputStream)closeable0).writeInt(arr_v1[v]);
                }
            }
            catch(Throwable throwable1) {
                CloseableKt.closeFinally(closeable0, throwable1);
                throw throwable1;
            }
            CloseableKt.closeFinally(closeable0, null);
        }
        catch(Throwable throwable0) {
            CloseableKt.closeFinally(byteArrayOutputStream0, throwable0);
            throw throwable0;
        }
        CloseableKt.closeFinally(byteArrayOutputStream0, null);
        byte[] arr_b = byteArrayOutputStream0.toByteArray();
        Intrinsics.checkNotNullExpressionValue(arr_b, "outputStream.toByteArray()");
        return arr_b;
    }

    @JvmStatic
    public static final BackoffPolicy intToBackoffPolicy(int v) {
        switch(v) {
            case 0: {
                return BackoffPolicy.EXPONENTIAL;
            }
            case 1: {
                return BackoffPolicy.LINEAR;
            }
            default: {
                throw new IllegalArgumentException("Could not convert " + v + " to BackoffPolicy");
            }
        }
    }

    @JvmStatic
    public static final NetworkType intToNetworkType(int v) {
        switch(v) {
            case 0: {
                return NetworkType.NOT_REQUIRED;
            }
            case 1: {
                return NetworkType.CONNECTED;
            }
            case 2: {
                return NetworkType.UNMETERED;
            }
            case 3: {
                return NetworkType.NOT_ROAMING;
            }
            case 4: {
                return NetworkType.METERED;
            }
            default: {
                if(Build.VERSION.SDK_INT < 30 || v != 5) {
                    throw new IllegalArgumentException("Could not convert " + v + " to NetworkType");
                }
                return NetworkType.TEMPORARILY_UNMETERED;
            }
        }
    }

    @JvmStatic
    public static final OutOfQuotaPolicy intToOutOfQuotaPolicy(int v) {
        switch(v) {
            case 0: {
                return OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST;
            }
            case 1: {
                return OutOfQuotaPolicy.DROP_WORK_REQUEST;
            }
            default: {
                throw new IllegalArgumentException("Could not convert " + v + " to OutOfQuotaPolicy");
            }
        }
    }

    @JvmStatic
    public static final State intToState(int v) {
        switch(v) {
            case 0: {
                return State.ENQUEUED;
            }
            case 1: {
                return State.RUNNING;
            }
            case 2: {
                return State.SUCCEEDED;
            }
            case 3: {
                return State.FAILED;
            }
            case 4: {
                return State.BLOCKED;
            }
            case 5: {
                return State.CANCELLED;
            }
            default: {
                throw new IllegalArgumentException("Could not convert " + v + " to State");
            }
        }
    }

    @JvmStatic
    public static final int networkTypeToInt(NetworkType networkType0) {
        Intrinsics.checkNotNullParameter(networkType0, "networkType");
        switch(WhenMappings.$EnumSwitchMapping$2[networkType0.ordinal()]) {
            case 1: {
                return 0;
            }
            case 2: {
                return 1;
            }
            case 3: {
                return 2;
            }
            case 4: {
                return 3;
            }
            case 5: {
                return 4;
            }
            default: {
                if(Build.VERSION.SDK_INT < 30 || networkType0 != NetworkType.TEMPORARILY_UNMETERED) {
                    throw new IllegalArgumentException("Could not convert " + networkType0 + " to int");
                }
                return 5;
            }
        }
    }

    @JvmStatic
    public static final int outOfQuotaPolicyToInt(OutOfQuotaPolicy outOfQuotaPolicy0) {
        Intrinsics.checkNotNullParameter(outOfQuotaPolicy0, "policy");
        switch(WhenMappings.$EnumSwitchMapping$3[outOfQuotaPolicy0.ordinal()]) {
            case 1: {
                return 0;
            }
            case 2: {
                return 1;
            }
            default: {
                throw new NoWhenBranchMatchedException();
            }
        }
    }

    @JvmStatic
    public static final byte[] setOfTriggersToByteArray(Set set0) {
        Intrinsics.checkNotNullParameter(set0, "triggers");
        if(set0.isEmpty()) {
            return new byte[0];
        }
        ByteArrayOutputStream byteArrayOutputStream0 = new ByteArrayOutputStream();
        try {
            Closeable closeable0 = new ObjectOutputStream(byteArrayOutputStream0);
            try {
                ((ObjectOutputStream)closeable0).writeInt(set0.size());
                for(Object object0: set0) {
                    ((ObjectOutputStream)closeable0).writeUTF(((ContentUriTrigger)object0).getUri().toString());
                    ((ObjectOutputStream)closeable0).writeBoolean(((ContentUriTrigger)object0).isTriggeredForDescendants());
                }
            }
            catch(Throwable throwable1) {
                CloseableKt.closeFinally(closeable0, throwable1);
                throw throwable1;
            }
            CloseableKt.closeFinally(closeable0, null);
        }
        catch(Throwable throwable0) {
            CloseableKt.closeFinally(byteArrayOutputStream0, throwable0);
            throw throwable0;
        }
        CloseableKt.closeFinally(byteArrayOutputStream0, null);
        byte[] arr_b = byteArrayOutputStream0.toByteArray();
        Intrinsics.checkNotNullExpressionValue(arr_b, "outputStream.toByteArray()");
        return arr_b;
    }

    @JvmStatic
    public static final int stateToInt(State workInfo$State0) {
        Intrinsics.checkNotNullParameter(workInfo$State0, "state");
        switch(WhenMappings.$EnumSwitchMapping$0[workInfo$State0.ordinal()]) {
            case 1: {
                return 0;
            }
            case 2: {
                return 1;
            }
            case 3: {
                return 2;
            }
            case 4: {
                return 3;
            }
            case 5: {
                return 4;
            }
            case 6: {
                return 5;
            }
            default: {
                throw new NoWhenBranchMatchedException();
            }
        }
    }

    @JvmStatic
    public static final NetworkRequestCompat toNetworkRequest$work_runtime_release(byte[] arr_b) {
        NetworkRequestCompat networkRequestCompat0;
        Intrinsics.checkNotNullParameter(arr_b, "bytes");
        if(Build.VERSION.SDK_INT >= 28 && arr_b.length != 0) {
            Closeable closeable0 = new ByteArrayInputStream(arr_b);
            try {
                Closeable closeable1 = new ObjectInputStream(((ByteArrayInputStream)closeable0));
                try {
                    int v = ((ObjectInputStream)closeable1).readInt();
                    int[] arr_v = new int[v];
                    for(int v2 = 0; v2 < v; ++v2) {
                        arr_v[v2] = ((ObjectInputStream)closeable1).readInt();
                    }
                    int v3 = ((ObjectInputStream)closeable1).readInt();
                    int[] arr_v1 = new int[v3];
                    for(int v1 = 0; v1 < v3; ++v1) {
                        arr_v1[v1] = ((ObjectInputStream)closeable1).readInt();
                    }
                    networkRequestCompat0 = NetworkRequest28.INSTANCE.createNetworkRequestCompat$work_runtime_release(arr_v1, arr_v);
                }
                catch(Throwable throwable1) {
                    CloseableKt.closeFinally(closeable1, throwable1);
                    throw throwable1;
                }
                CloseableKt.closeFinally(closeable1, null);
            }
            catch(Throwable throwable0) {
                CloseableKt.closeFinally(closeable0, throwable0);
                throw throwable0;
            }
            CloseableKt.closeFinally(closeable0, null);
            return networkRequestCompat0;
        }
        return new NetworkRequestCompat(null);
    }
}

