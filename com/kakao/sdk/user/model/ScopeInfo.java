package com.kakao.sdk.user.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u001D\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u000E\u0010\u0004\u001A\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005¢\u0006\u0002\u0010\u0007J\t\u0010\f\u001A\u00020\u0003HÆ\u0003J\u0011\u0010\r\u001A\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005HÆ\u0003J%\u0010\u000E\u001A\u00020\u00002\b\b\u0002\u0010\u0002\u001A\u00020\u00032\u0010\b\u0002\u0010\u0004\u001A\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005HÆ\u0001J\t\u0010\u000F\u001A\u00020\u0010HÖ\u0001J\u0013\u0010\u0011\u001A\u00020\u00122\b\u0010\u0013\u001A\u0004\u0018\u00010\u0014HÖ\u0003J\t\u0010\u0015\u001A\u00020\u0010HÖ\u0001J\t\u0010\u0016\u001A\u00020\u0017HÖ\u0001J\u0019\u0010\u0018\u001A\u00020\u00192\u0006\u0010\u001A\u001A\u00020\u001B2\u0006\u0010\u001C\u001A\u00020\u0010HÖ\u0001R\u0011\u0010\u0002\u001A\u00020\u0003¢\u0006\b\n\u0000\u001A\u0004\b\b\u0010\tR\u0019\u0010\u0004\u001A\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005¢\u0006\b\n\u0000\u001A\u0004\b\n\u0010\u000B¨\u0006\u001D"}, d2 = {"Lcom/kakao/sdk/user/model/ScopeInfo;", "Landroid/os/Parcelable;", "id", "", "scopes", "", "Lcom/kakao/sdk/user/model/Scope;", "(JLjava/util/List;)V", "getId", "()J", "getScopes", "()Ljava/util/List;", "component1", "component2", "copy", "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "user_release"}, k = 1, mv = {1, 5, 1}, xi = 0x30)
public final class ScopeInfo implements Parcelable {
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 0x30)
    public static final class Creator implements Parcelable.Creator {
        public final ScopeInfo createFromParcel(Parcel parcel0) {
            Intrinsics.checkNotNullParameter(parcel0, "parcel");
            long v = parcel0.readLong();
            if(parcel0.readInt() == 0) {
                return new ScopeInfo(v, null);
            }
            int v1 = parcel0.readInt();
            ArrayList arrayList0 = new ArrayList(v1);
            for(int v2 = 0; v2 != v1; ++v2) {
                arrayList0.add(Scope.CREATOR.createFromParcel(parcel0));
            }
            return new ScopeInfo(v, arrayList0);
        }

        @Override  // android.os.Parcelable$Creator
        public Object createFromParcel(Parcel parcel0) {
            return this.createFromParcel(parcel0);
        }

        public final ScopeInfo[] newArray(int v) {
            return new ScopeInfo[v];
        }

        @Override  // android.os.Parcelable$Creator
        public Object[] newArray(int v) {
            return this.newArray(v);
        }
    }

    public static final Parcelable.Creator CREATOR;
    private final long id;
    private final List scopes;

    static {
        ScopeInfo.CREATOR = new Creator();
    }

    public ScopeInfo(long v, List list0) {
        this.id = v;
        this.scopes = list0;
    }

    public final long component1() {
        return this.id;
    }

    public final List component2() {
        return this.scopes;
    }

    public final ScopeInfo copy(long v, List list0) {
        return new ScopeInfo(v, list0);
    }

    public static ScopeInfo copy$default(ScopeInfo scopeInfo0, long v, List list0, int v1, Object object0) {
        if((v1 & 1) != 0) {
            v = scopeInfo0.id;
        }
        if((v1 & 2) != 0) {
            list0 = scopeInfo0.scopes;
        }
        return scopeInfo0.copy(v, list0);
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
        if(!(object0 instanceof ScopeInfo)) {
            return false;
        }
        return this.id == ((ScopeInfo)object0).id ? Intrinsics.areEqual(this.scopes, ((ScopeInfo)object0).scopes) : false;
    }

    public final long getId() {
        return this.id;
    }

    public final List getScopes() {
        return this.scopes;
    }

    @Override
    public int hashCode() {
        int v = (int)(this.id ^ this.id >>> 0x20);
        return this.scopes == null ? v * 0x1F : v * 0x1F + this.scopes.hashCode();
    }

    @Override
    public String toString() {
        return "ScopeInfo(id=" + this.id + ", scopes=" + this.scopes + ')';
    }

    @Override  // android.os.Parcelable
    public void writeToParcel(Parcel parcel0, int v) {
        Intrinsics.checkNotNullParameter(parcel0, "out");
        parcel0.writeLong(this.id);
        List list0 = this.scopes;
        if(list0 == null) {
            parcel0.writeInt(0);
            return;
        }
        parcel0.writeInt(1);
        parcel0.writeInt(list0.size());
        for(Object object0: list0) {
            ((Scope)object0).writeToParcel(parcel0, v);
        }
    }
}

