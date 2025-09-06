package com.kakao.sdk.user.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0014\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B)\u0012\b\u0010\u0002\u001A\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0003\u0012\u0006\u0010\u0005\u001A\u00020\u0006\u0012\b\u0010\u0007\u001A\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\bJ\u0010\u0010\u0013\u001A\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0010J\t\u0010\u0014\u001A\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001A\u00020\u0006HÆ\u0003J\u0010\u0010\u0016\u001A\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0010J:\u0010\u0017\u001A\u00020\u00002\n\b\u0002\u0010\u0002\u001A\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001A\u00020\u00032\b\b\u0002\u0010\u0005\u001A\u00020\u00062\n\b\u0002\u0010\u0007\u001A\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010\u0018J\t\u0010\u0019\u001A\u00020\u0006HÖ\u0001J\u0013\u0010\u001A\u001A\u00020\u001B2\b\u0010\u001C\u001A\u0004\u0018\u00010\u001DHÖ\u0003J\t\u0010\u001E\u001A\u00020\u0006HÖ\u0001J\t\u0010\u001F\u001A\u00020 HÖ\u0001J\u0019\u0010!\u001A\u00020\"2\u0006\u0010#\u001A\u00020$2\u0006\u0010%\u001A\u00020\u0006HÖ\u0001R\u0011\u0010\u0005\u001A\u00020\u0006¢\u0006\b\n\u0000\u001A\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001A\u00020\u0003¢\u0006\b\n\u0000\u001A\u0004\b\u000B\u0010\fR \u0010\u0007\u001A\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u0011\u0012\u0004\b\r\u0010\u000E\u001A\u0004\b\u000F\u0010\u0010R\u0015\u0010\u0002\u001A\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u0011\u001A\u0004\b\u0012\u0010\u0010¨\u0006&"}, d2 = {"Lcom/kakao/sdk/user/model/AccessTokenInfo;", "Landroid/os/Parcelable;", "id", "", "expiresIn", "appId", "", "expiresInMillis", "(Ljava/lang/Long;JILjava/lang/Long;)V", "getAppId", "()I", "getExpiresIn", "()J", "getExpiresInMillis$annotations", "()V", "getExpiresInMillis", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getId", "component1", "component2", "component3", "component4", "copy", "(Ljava/lang/Long;JILjava/lang/Long;)Lcom/kakao/sdk/user/model/AccessTokenInfo;", "describeContents", "equals", "", "other", "", "hashCode", "toString", "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "user_release"}, k = 1, mv = {1, 5, 1}, xi = 0x30)
public final class AccessTokenInfo implements Parcelable {
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 0x30)
    public static final class Creator implements Parcelable.Creator {
        public final AccessTokenInfo createFromParcel(Parcel parcel0) {
            Intrinsics.checkNotNullParameter(parcel0, "parcel");
            Long long0 = null;
            Long long1 = parcel0.readInt() == 0 ? null : parcel0.readLong();
            long v = parcel0.readLong();
            int v1 = parcel0.readInt();
            if(parcel0.readInt() != 0) {
                long0 = parcel0.readLong();
            }
            return new AccessTokenInfo(long1, v, v1, long0);
        }

        @Override  // android.os.Parcelable$Creator
        public Object createFromParcel(Parcel parcel0) {
            return this.createFromParcel(parcel0);
        }

        public final AccessTokenInfo[] newArray(int v) {
            return new AccessTokenInfo[v];
        }

        @Override  // android.os.Parcelable$Creator
        public Object[] newArray(int v) {
            return this.newArray(v);
        }
    }

    public static final Parcelable.Creator CREATOR;
    private final int appId;
    private final long expiresIn;
    @SerializedName("expiresInMillis")
    private final Long expiresInMillis;
    private final Long id;

    static {
        AccessTokenInfo.CREATOR = new Creator();
    }

    public AccessTokenInfo(Long long0, long v, int v1, Long long1) {
        this.id = long0;
        this.expiresIn = v;
        this.appId = v1;
        this.expiresInMillis = long1;
    }

    public final Long component1() {
        return this.id;
    }

    public final long component2() {
        return this.expiresIn;
    }

    public final int component3() {
        return this.appId;
    }

    public final Long component4() {
        return this.expiresInMillis;
    }

    public final AccessTokenInfo copy(Long long0, long v, int v1, Long long1) {
        return new AccessTokenInfo(long0, v, v1, long1);
    }

    public static AccessTokenInfo copy$default(AccessTokenInfo accessTokenInfo0, Long long0, long v, int v1, Long long1, int v2, Object object0) {
        if((v2 & 1) != 0) {
            long0 = accessTokenInfo0.id;
        }
        if((v2 & 2) != 0) {
            v = accessTokenInfo0.expiresIn;
        }
        if((v2 & 4) != 0) {
            v1 = accessTokenInfo0.appId;
        }
        if((v2 & 8) != 0) {
            long1 = accessTokenInfo0.expiresInMillis;
        }
        return accessTokenInfo0.copy(long0, v, v1, long1);
    }

    @Override  // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(!(object0 instanceof AccessTokenInfo)) {
            return false;
        }
        if(!Intrinsics.areEqual(this.id, ((AccessTokenInfo)object0).id)) {
            return false;
        }
        if(this.expiresIn != ((AccessTokenInfo)object0).expiresIn) {
            return false;
        }
        return this.appId == ((AccessTokenInfo)object0).appId ? Intrinsics.areEqual(this.expiresInMillis, ((AccessTokenInfo)object0).expiresInMillis) : false;
    }

    public final int getAppId() {
        return this.appId;
    }

    public final long getExpiresIn() {
        return this.expiresIn;
    }

    public final Long getExpiresInMillis() {
        return this.expiresInMillis;
    }

    @Deprecated(message = "\'초\' 단위를 사용하는 \'expiresIn\' 속성으로 대체되었습니다.")
    public static void getExpiresInMillis$annotations() {
    }

    public final Long getId() {
        return this.id;
    }

    @Override
    public int hashCode() {
        int v = 0;
        int v1 = (((this.id == null ? 0 : this.id.hashCode()) * 0x1F + ((int)(this.expiresIn ^ this.expiresIn >>> 0x20))) * 0x1F + this.appId) * 0x1F;
        Long long0 = this.expiresInMillis;
        if(long0 != null) {
            v = long0.hashCode();
        }
        return v1 + v;
    }

    @Override
    public String toString() {
        return "AccessTokenInfo(id=" + this.id + ", expiresIn=" + this.expiresIn + ", appId=" + this.appId + ", expiresInMillis=" + this.expiresInMillis + ')';
    }

    @Override  // android.os.Parcelable
    public void writeToParcel(Parcel parcel0, int v) {
        Intrinsics.checkNotNullParameter(parcel0, "out");
        Long long0 = this.id;
        if(long0 == null) {
            parcel0.writeInt(0);
        }
        else {
            parcel0.writeInt(1);
            parcel0.writeLong(((long)long0));
        }
        parcel0.writeLong(this.expiresIn);
        parcel0.writeInt(this.appId);
        Long long1 = this.expiresInMillis;
        if(long1 == null) {
            parcel0.writeInt(0);
            return;
        }
        parcel0.writeInt(1);
        parcel0.writeLong(((long)long1));
    }
}

