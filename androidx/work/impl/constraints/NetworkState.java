package androidx.work.impl.constraints;

import kotlin.Metadata;
import kotlin.UByte..ExternalSyntheticBackport0;

@Metadata(d1 = {"\u0000\u001E\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000B\n\u0002\b\r\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000E\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0003\u0012\u0006\u0010\u0005\u001A\u00020\u0003\u0012\u0006\u0010\u0006\u001A\u00020\u0003¢\u0006\u0002\u0010\u0007J\t\u0010\t\u001A\u00020\u0003HÆ\u0003J\t\u0010\n\u001A\u00020\u0003HÆ\u0003J\t\u0010\u000B\u001A\u00020\u0003HÆ\u0003J\t\u0010\f\u001A\u00020\u0003HÆ\u0003J1\u0010\r\u001A\u00020\u00002\b\b\u0002\u0010\u0002\u001A\u00020\u00032\b\b\u0002\u0010\u0004\u001A\u00020\u00032\b\b\u0002\u0010\u0005\u001A\u00020\u00032\b\b\u0002\u0010\u0006\u001A\u00020\u0003HÆ\u0001J\u0013\u0010\u000E\u001A\u00020\u00032\b\u0010\u000F\u001A\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0010\u001A\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001A\u00020\u0013HÖ\u0001R\u0011\u0010\u0002\u001A\u00020\u0003¢\u0006\b\n\u0000\u001A\u0004\b\u0002\u0010\bR\u0011\u0010\u0005\u001A\u00020\u0003¢\u0006\b\n\u0000\u001A\u0004\b\u0005\u0010\bR\u0011\u0010\u0006\u001A\u00020\u0003¢\u0006\b\n\u0000\u001A\u0004\b\u0006\u0010\bR\u0011\u0010\u0004\u001A\u00020\u0003¢\u0006\b\n\u0000\u001A\u0004\b\u0004\u0010\b¨\u0006\u0014"}, d2 = {"Landroidx/work/impl/constraints/NetworkState;", "", "isConnected", "", "isValidated", "isMetered", "isNotRoaming", "(ZZZZ)V", "()Z", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "", "toString", "", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class NetworkState {
    private final boolean isConnected;
    private final boolean isMetered;
    private final boolean isNotRoaming;
    private final boolean isValidated;

    public NetworkState(boolean z, boolean z1, boolean z2, boolean z3) {
        this.isConnected = z;
        this.isValidated = z1;
        this.isMetered = z2;
        this.isNotRoaming = z3;
    }

    public final boolean component1() {
        return this.isConnected;
    }

    public final boolean component2() {
        return this.isValidated;
    }

    public final boolean component3() {
        return this.isMetered;
    }

    public final boolean component4() {
        return this.isNotRoaming;
    }

    public final NetworkState copy(boolean z, boolean z1, boolean z2, boolean z3) {
        return new NetworkState(z, z1, z2, z3);
    }

    public static NetworkState copy$default(NetworkState networkState0, boolean z, boolean z1, boolean z2, boolean z3, int v, Object object0) {
        if((v & 1) != 0) {
            z = networkState0.isConnected;
        }
        if((v & 2) != 0) {
            z1 = networkState0.isValidated;
        }
        if((v & 4) != 0) {
            z2 = networkState0.isMetered;
        }
        if((v & 8) != 0) {
            z3 = networkState0.isNotRoaming;
        }
        return networkState0.copy(z, z1, z2, z3);
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(!(object0 instanceof NetworkState)) {
            return false;
        }
        if(this.isConnected != ((NetworkState)object0).isConnected) {
            return false;
        }
        if(this.isValidated != ((NetworkState)object0).isValidated) {
            return false;
        }
        return this.isMetered == ((NetworkState)object0).isMetered ? this.isNotRoaming == ((NetworkState)object0).isNotRoaming : false;
    }

    @Override
    public int hashCode() {
        return ((UByte..ExternalSyntheticBackport0.m(this.isConnected) * 0x1F + UByte..ExternalSyntheticBackport0.m(this.isValidated)) * 0x1F + UByte..ExternalSyntheticBackport0.m(this.isMetered)) * 0x1F + UByte..ExternalSyntheticBackport0.m(this.isNotRoaming);
    }

    public final boolean isConnected() {
        return this.isConnected;
    }

    public final boolean isMetered() {
        return this.isMetered;
    }

    public final boolean isNotRoaming() {
        return this.isNotRoaming;
    }

    public final boolean isValidated() {
        return this.isValidated;
    }

    @Override
    public String toString() {
        return "NetworkState(isConnected=" + this.isConnected + ", isValidated=" + this.isValidated + ", isMetered=" + this.isMetered + ", isNotRoaming=" + this.isNotRoaming + ')';
    }
}

