package com.navercorp.nid.profile.data;

import com.google.gson.annotations.SerializedName;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u000B\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B/\u0012\b\u0010\u0002\u001A\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001A\u0004\u0018\u00010\u0003\u0012\u0014\u0010\u0005\u001A\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J\u000B\u0010\r\u001A\u0004\u0018\u00010\u0003HÆ\u0003J\u000B\u0010\u000E\u001A\u0004\u0018\u00010\u0003HÆ\u0003J\u0017\u0010\u000F\u001A\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0006HÆ\u0003J9\u0010\u0010\u001A\u00020\u00002\n\b\u0002\u0010\u0002\u001A\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001A\u0004\u0018\u00010\u00032\u0016\b\u0002\u0010\u0005\u001A\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0006HÆ\u0001J\u0013\u0010\u0011\u001A\u00020\u00122\b\u0010\u0013\u001A\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001A\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001A\u00020\u0003HÖ\u0001R\u0018\u0010\u0004\u001A\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001A\u0004\b\b\u0010\tR$\u0010\u0005\u001A\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001A\u0004\b\n\u0010\u000BR\u0018\u0010\u0002\u001A\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001A\u0004\b\f\u0010\t¨\u0006\u0017"}, d2 = {"Lcom/navercorp/nid/profile/data/NidProfileMap;", "", "resultCode", "", "message", "profile", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)V", "getMessage", "()Ljava/lang/String;", "getProfile", "()Ljava/util/Map;", "getResultCode", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "Nid-OAuth_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public final class NidProfileMap {
    @SerializedName("message")
    private final String message;
    @SerializedName("response")
    private final Map profile;
    @SerializedName("resultcode")
    private final String resultCode;

    public NidProfileMap(String s, String s1, Map map0) {
        this.resultCode = s;
        this.message = s1;
        this.profile = map0;
    }

    public final String component1() {
        return this.resultCode;
    }

    public final String component2() {
        return this.message;
    }

    public final Map component3() {
        return this.profile;
    }

    public final NidProfileMap copy(String s, String s1, Map map0) {
        return new NidProfileMap(s, s1, map0);
    }

    public static NidProfileMap copy$default(NidProfileMap nidProfileMap0, String s, String s1, Map map0, int v, Object object0) {
        if((v & 1) != 0) {
            s = nidProfileMap0.resultCode;
        }
        if((v & 2) != 0) {
            s1 = nidProfileMap0.message;
        }
        if((v & 4) != 0) {
            map0 = nidProfileMap0.profile;
        }
        return nidProfileMap0.copy(s, s1, map0);
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(!(object0 instanceof NidProfileMap)) {
            return false;
        }
        if(!Intrinsics.areEqual(this.resultCode, ((NidProfileMap)object0).resultCode)) {
            return false;
        }
        return Intrinsics.areEqual(this.message, ((NidProfileMap)object0).message) ? Intrinsics.areEqual(this.profile, ((NidProfileMap)object0).profile) : false;
    }

    public final String getMessage() {
        return this.message;
    }

    public final Map getProfile() {
        return this.profile;
    }

    public final String getResultCode() {
        return this.resultCode;
    }

    @Override
    public int hashCode() {
        int v = 0;
        int v1 = this.resultCode == null ? 0 : this.resultCode.hashCode();
        int v2 = this.message == null ? 0 : this.message.hashCode();
        Map map0 = this.profile;
        if(map0 != null) {
            v = map0.hashCode();
        }
        return (v1 * 0x1F + v2) * 0x1F + v;
    }

    @Override
    public String toString() {
        return "NidProfileMap(resultCode=" + this.resultCode + ", message=" + this.message + ", profile=" + this.profile + ")";
    }
}

