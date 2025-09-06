package com.kakao.sdk.user.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.Parcelable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u001A\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001BA\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0003\u0012\u0006\u0010\u0005\u001A\u00020\u0006\u0012\u0006\u0010\u0007\u001A\u00020\b\u0012\b\u0010\t\u001A\u0004\u0018\u00010\b\u0012\u0006\u0010\n\u001A\u00020\b\u0012\b\u0010\u000B\u001A\u0004\u0018\u00010\b\u00A2\u0006\u0002\u0010\fJ\t\u0010\u0019\u001A\u00020\u0003H\u00C6\u0003J\t\u0010\u001A\u001A\u00020\u0003H\u00C6\u0003J\t\u0010\u001B\u001A\u00020\u0006H\u00C6\u0003J\t\u0010\u001C\u001A\u00020\bH\u00C6\u0003J\u0010\u0010\u001D\u001A\u0004\u0018\u00010\bH\u00C6\u0003\u00A2\u0006\u0002\u0010\u0010J\t\u0010\u001E\u001A\u00020\bH\u00C6\u0003J\u0010\u0010\u001F\u001A\u0004\u0018\u00010\bH\u00C6\u0003\u00A2\u0006\u0002\u0010\u0010JX\u0010 \u001A\u00020\u00002\b\b\u0002\u0010\u0002\u001A\u00020\u00032\b\b\u0002\u0010\u0004\u001A\u00020\u00032\b\b\u0002\u0010\u0005\u001A\u00020\u00062\b\b\u0002\u0010\u0007\u001A\u00020\b2\n\b\u0002\u0010\t\u001A\u0004\u0018\u00010\b2\b\b\u0002\u0010\n\u001A\u00020\b2\n\b\u0002\u0010\u000B\u001A\u0004\u0018\u00010\bH\u00C6\u0001\u00A2\u0006\u0002\u0010!J\t\u0010\"\u001A\u00020#H\u00D6\u0001J\u0013\u0010$\u001A\u00020\b2\b\u0010%\u001A\u0004\u0018\u00010&H\u00D6\u0003J\t\u0010\'\u001A\u00020#H\u00D6\u0001J\t\u0010(\u001A\u00020\u0003H\u00D6\u0001J\u0019\u0010)\u001A\u00020*2\u0006\u0010+\u001A\u00020,2\u0006\u0010-\u001A\u00020#H\u00D6\u0001R\u0011\u0010\n\u001A\u00020\b\u00A2\u0006\b\n\u0000\u001A\u0004\b\r\u0010\u000ER\u0015\u0010\t\u001A\u0004\u0018\u00010\b\u00A2\u0006\n\n\u0002\u0010\u0011\u001A\u0004\b\u000F\u0010\u0010R\u0011\u0010\u0004\u001A\u00020\u0003\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0002\u001A\u00020\u0003\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0014\u0010\u0013R\u0015\u0010\u000B\u001A\u0004\u0018\u00010\b\u00A2\u0006\n\n\u0002\u0010\u0011\u001A\u0004\b\u0015\u0010\u0010R\u0011\u0010\u0005\u001A\u00020\u0006\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0007\u001A\u00020\b\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0018\u0010\u000E\u00A8\u0006."}, d2 = {"Lcom/kakao/sdk/user/model/Scope;", "Landroid/os/Parcelable;", "id", "", "displayName", "type", "Lcom/kakao/sdk/user/model/ScopeType;", "using", "", "delegated", "agreed", "revocable", "(Ljava/lang/String;Ljava/lang/String;Lcom/kakao/sdk/user/model/ScopeType;ZLjava/lang/Boolean;ZLjava/lang/Boolean;)V", "getAgreed", "()Z", "getDelegated", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getDisplayName", "()Ljava/lang/String;", "getId", "getRevocable", "getType", "()Lcom/kakao/sdk/user/model/ScopeType;", "getUsing", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "(Ljava/lang/String;Ljava/lang/String;Lcom/kakao/sdk/user/model/ScopeType;ZLjava/lang/Boolean;ZLjava/lang/Boolean;)Lcom/kakao/sdk/user/model/Scope;", "describeContents", "", "equals", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "user_release"}, k = 1, mv = {1, 5, 1}, xi = 0x30)
public final class Scope implements Parcelable {
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 0x30)
    public static final class Creator implements Parcelable.Creator {
        public final Scope createFromParcel(Parcel parcel0) {
            Intrinsics.checkNotNullParameter(parcel0, "parcel");
            String s = parcel0.readString();
            String s1 = parcel0.readString();
            ScopeType scopeType0 = ScopeType.valueOf(parcel0.readString());
            boolean z = parcel0.readInt() != 0;
            boolean z1 = true;
            Boolean boolean0 = null;
            Boolean boolean1 = parcel0.readInt() == 0 ? null : Boolean.valueOf(parcel0.readInt() != 0);
            boolean z2 = parcel0.readInt() != 0;
            if(parcel0.readInt() != 0) {
                if(parcel0.readInt() == 0) {
                    z1 = false;
                }
                boolean0 = Boolean.valueOf(z1);
            }
            return new Scope(s, s1, scopeType0, z, boolean1, z2, boolean0);
        }

        @Override  // android.os.Parcelable$Creator
        public Object createFromParcel(Parcel parcel0) {
            return this.createFromParcel(parcel0);
        }

        public final Scope[] newArray(int v) {
            return new Scope[v];
        }

        @Override  // android.os.Parcelable$Creator
        public Object[] newArray(int v) {
            return this.newArray(v);
        }
    }

    public static final Parcelable.Creator CREATOR;
    private final boolean agreed;
    private final Boolean delegated;
    private final String displayName;
    private final String id;
    private final Boolean revocable;
    private final ScopeType type;
    private final boolean using;

    static {
        Scope.CREATOR = new Creator();
    }

    public Scope(String s, String s1, ScopeType scopeType0, boolean z, Boolean boolean0, boolean z1, Boolean boolean1) {
        Intrinsics.checkNotNullParameter(s, "id");
        Intrinsics.checkNotNullParameter(s1, "displayName");
        Intrinsics.checkNotNullParameter(scopeType0, "type");
        super();
        this.id = s;
        this.displayName = s1;
        this.type = scopeType0;
        this.using = z;
        this.delegated = boolean0;
        this.agreed = z1;
        this.revocable = boolean1;
    }

    public final String component1() {
        return this.id;
    }

    public final String component2() {
        return this.displayName;
    }

    public final ScopeType component3() {
        return this.type;
    }

    public final boolean component4() {
        return this.using;
    }

    public final Boolean component5() {
        return this.delegated;
    }

    public final boolean component6() {
        return this.agreed;
    }

    public final Boolean component7() {
        return this.revocable;
    }

    public final Scope copy(String s, String s1, ScopeType scopeType0, boolean z, Boolean boolean0, boolean z1, Boolean boolean1) {
        Intrinsics.checkNotNullParameter(s, "id");
        Intrinsics.checkNotNullParameter(s1, "displayName");
        Intrinsics.checkNotNullParameter(scopeType0, "type");
        return new Scope(s, s1, scopeType0, z, boolean0, z1, boolean1);
    }

    public static Scope copy$default(Scope scope0, String s, String s1, ScopeType scopeType0, boolean z, Boolean boolean0, boolean z1, Boolean boolean1, int v, Object object0) {
        if((v & 1) != 0) {
            s = scope0.id;
        }
        if((v & 2) != 0) {
            s1 = scope0.displayName;
        }
        if((v & 4) != 0) {
            scopeType0 = scope0.type;
        }
        if((v & 8) != 0) {
            z = scope0.using;
        }
        if((v & 16) != 0) {
            boolean0 = scope0.delegated;
        }
        if((v & 0x20) != 0) {
            z1 = scope0.agreed;
        }
        if((v & 0x40) != 0) {
            boolean1 = scope0.revocable;
        }
        return scope0.copy(s, s1, scopeType0, z, boolean0, z1, boolean1);
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
        if(!(object0 instanceof Scope)) {
            return false;
        }
        if(!Intrinsics.areEqual(this.id, ((Scope)object0).id)) {
            return false;
        }
        if(!Intrinsics.areEqual(this.displayName, ((Scope)object0).displayName)) {
            return false;
        }
        if(this.type != ((Scope)object0).type) {
            return false;
        }
        if(this.using != ((Scope)object0).using) {
            return false;
        }
        if(!Intrinsics.areEqual(this.delegated, ((Scope)object0).delegated)) {
            return false;
        }
        return this.agreed == ((Scope)object0).agreed ? Intrinsics.areEqual(this.revocable, ((Scope)object0).revocable) : false;
    }

    public final boolean getAgreed() {
        return this.agreed;
    }

    public final Boolean getDelegated() {
        return this.delegated;
    }

    public final String getDisplayName() {
        return this.displayName;
    }

    public final String getId() {
        return this.id;
    }

    public final Boolean getRevocable() {
        return this.revocable;
    }

    public final ScopeType getType() {
        return this.type;
    }

    public final boolean getUsing() {
        return this.using;
    }

    @Override
    public int hashCode() {
        int v = this.id.hashCode();
        int v1 = this.displayName.hashCode();
        int v2 = this.type.hashCode();
        int v3 = this.using;
        int v4 = 1;
        if(v3) {
            v3 = 1;
        }
        int v5 = 0;
        int v6 = this.delegated == null ? 0 : this.delegated.hashCode();
        if(!this.agreed) {
            v4 = false;
        }
        Boolean boolean0 = this.revocable;
        if(boolean0 != null) {
            v5 = boolean0.hashCode();
        }
        return (((((v * 0x1F + v1) * 0x1F + v2) * 0x1F + v3) * 0x1F + v6) * 0x1F + v4) * 0x1F + v5;
    }

    @Override
    public String toString() {
        return "Scope(id=" + this.id + ", displayName=" + this.displayName + ", type=" + this.type + ", using=" + this.using + ", delegated=" + this.delegated + ", agreed=" + this.agreed + ", revocable=" + this.revocable + ')';
    }

    @Override  // android.os.Parcelable
    public void writeToParcel(Parcel parcel0, int v) {
        Intrinsics.checkNotNullParameter(parcel0, "out");
        parcel0.writeString(this.id);
        parcel0.writeString(this.displayName);
        parcel0.writeString(this.type.name());
        parcel0.writeInt(((int)this.using));
        Boolean boolean0 = this.delegated;
        if(boolean0 == null) {
            parcel0.writeInt(0);
        }
        else {
            parcel0.writeInt(1);
            parcel0.writeInt(((int)boolean0.booleanValue()));
        }
        parcel0.writeInt(((int)this.agreed));
        Boolean boolean1 = this.revocable;
        if(boolean1 == null) {
            parcel0.writeInt(0);
            return;
        }
        parcel0.writeInt(1);
        parcel0.writeInt(((int)boolean1.booleanValue()));
    }
}

