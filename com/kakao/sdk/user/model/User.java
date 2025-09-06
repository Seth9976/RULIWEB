package com.kakao.sdk.user.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.Parcelable;
import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0002\b\u001A\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001BW\u0012\b\u0010\u0002\u001A\u0004\u0018\u00010\u0003\u0012\u0014\u0010\u0004\u001A\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005\u0012\b\u0010\u0007\u001A\u0004\u0018\u00010\b\u0012\b\u0010\t\u001A\u0004\u0018\u00010\u0006\u0012\b\u0010\n\u001A\u0004\u0018\u00010\u000B\u0012\b\u0010\f\u001A\u0004\u0018\u00010\u000B\u0012\b\u0010\r\u001A\u0004\u0018\u00010\u000E\u00A2\u0006\u0002\u0010\u000FJ\u0010\u0010\u001F\u001A\u0004\u0018\u00010\u0003H\u00C6\u0003\u00A2\u0006\u0002\u0010\u0018J\u0017\u0010 \u001A\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005H\u00C6\u0003J\u000B\u0010!\u001A\u0004\u0018\u00010\bH\u00C6\u0003J\u000B\u0010\"\u001A\u0004\u0018\u00010\u0006H\u00C6\u0003J\u000B\u0010#\u001A\u0004\u0018\u00010\u000BH\u00C6\u0003J\u000B\u0010$\u001A\u0004\u0018\u00010\u000BH\u00C6\u0003J\u0010\u0010%\u001A\u0004\u0018\u00010\u000EH\u00C6\u0003\u00A2\u0006\u0002\u0010\u0015Jn\u0010&\u001A\u00020\u00002\n\b\u0002\u0010\u0002\u001A\u0004\u0018\u00010\u00032\u0016\b\u0002\u0010\u0004\u001A\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001A\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001A\u0004\u0018\u00010\u00062\n\b\u0002\u0010\n\u001A\u0004\u0018\u00010\u000B2\n\b\u0002\u0010\f\u001A\u0004\u0018\u00010\u000B2\n\b\u0002\u0010\r\u001A\u0004\u0018\u00010\u000EH\u00C6\u0001\u00A2\u0006\u0002\u0010\'J\t\u0010(\u001A\u00020)H\u00D6\u0001J\u0013\u0010*\u001A\u00020\u000E2\b\u0010+\u001A\u0004\u0018\u00010,H\u00D6\u0003J\t\u0010-\u001A\u00020)H\u00D6\u0001J\t\u0010.\u001A\u00020\u0006H\u00D6\u0001J\u0019\u0010/\u001A\u0002002\u0006\u00101\u001A\u0002022\u0006\u00103\u001A\u00020)H\u00D6\u0001R\u0013\u0010\n\u001A\u0004\u0018\u00010\u000B\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0010\u0010\u0011R\u0013\u0010\t\u001A\u0004\u0018\u00010\u0006\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0012\u0010\u0013R\u0015\u0010\r\u001A\u0004\u0018\u00010\u000E\u00A2\u0006\n\n\u0002\u0010\u0016\u001A\u0004\b\u0014\u0010\u0015R\u0015\u0010\u0002\u001A\u0004\u0018\u00010\u0003\u00A2\u0006\n\n\u0002\u0010\u0019\u001A\u0004\b\u0017\u0010\u0018R\u0013\u0010\u0007\u001A\u0004\u0018\u00010\b\u00A2\u0006\b\n\u0000\u001A\u0004\b\u001A\u0010\u001BR\u001F\u0010\u0004\u001A\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005\u00A2\u0006\b\n\u0000\u001A\u0004\b\u001C\u0010\u001DR\u0013\u0010\f\u001A\u0004\u0018\u00010\u000B\u00A2\u0006\b\n\u0000\u001A\u0004\b\u001E\u0010\u0011\u00A8\u00064"}, d2 = {"Lcom/kakao/sdk/user/model/User;", "Landroid/os/Parcelable;", "id", "", "properties", "", "", "kakaoAccount", "Lcom/kakao/sdk/user/model/Account;", "groupUserToken", "connectedAt", "Ljava/util/Date;", "synchedAt", "hasSignedUp", "", "(Ljava/lang/Long;Ljava/util/Map;Lcom/kakao/sdk/user/model/Account;Ljava/lang/String;Ljava/util/Date;Ljava/util/Date;Ljava/lang/Boolean;)V", "getConnectedAt", "()Ljava/util/Date;", "getGroupUserToken", "()Ljava/lang/String;", "getHasSignedUp", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getKakaoAccount", "()Lcom/kakao/sdk/user/model/Account;", "getProperties", "()Ljava/util/Map;", "getSynchedAt", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "(Ljava/lang/Long;Ljava/util/Map;Lcom/kakao/sdk/user/model/Account;Ljava/lang/String;Ljava/util/Date;Ljava/util/Date;Ljava/lang/Boolean;)Lcom/kakao/sdk/user/model/User;", "describeContents", "", "equals", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "user_release"}, k = 1, mv = {1, 5, 1}, xi = 0x30)
public final class User implements Parcelable {
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 0x30)
    public static final class Creator implements Parcelable.Creator {
        public final User createFromParcel(Parcel parcel0) {
            LinkedHashMap linkedHashMap0;
            Intrinsics.checkNotNullParameter(parcel0, "parcel");
            Long long0 = parcel0.readInt() == 0 ? null : parcel0.readLong();
            boolean z = false;
            if(parcel0.readInt() == 0) {
                linkedHashMap0 = null;
            }
            else {
                int v = parcel0.readInt();
                linkedHashMap0 = new LinkedHashMap(v);
                for(int v1 = 0; v1 != v; ++v1) {
                    linkedHashMap0.put(parcel0.readString(), parcel0.readString());
                }
            }
            Object object0 = parcel0.readInt() == 0 ? null : Account.CREATOR.createFromParcel(parcel0);
            String s = parcel0.readString();
            Serializable serializable0 = parcel0.readSerializable();
            Serializable serializable1 = parcel0.readSerializable();
            if(parcel0.readInt() == 0) {
                return new User(long0, linkedHashMap0, ((Account)object0), s, ((Date)serializable0), ((Date)serializable1), null);
            }
            if(parcel0.readInt() != 0) {
                z = true;
            }
            return new User(long0, linkedHashMap0, ((Account)object0), s, ((Date)serializable0), ((Date)serializable1), Boolean.valueOf(z));
        }

        @Override  // android.os.Parcelable$Creator
        public Object createFromParcel(Parcel parcel0) {
            return this.createFromParcel(parcel0);
        }

        public final User[] newArray(int v) {
            return new User[v];
        }

        @Override  // android.os.Parcelable$Creator
        public Object[] newArray(int v) {
            return this.newArray(v);
        }
    }

    public static final Parcelable.Creator CREATOR;
    private final Date connectedAt;
    private final String groupUserToken;
    private final Boolean hasSignedUp;
    private final Long id;
    private final Account kakaoAccount;
    private final Map properties;
    private final Date synchedAt;

    static {
        User.CREATOR = new Creator();
    }

    public User(Long long0, Map map0, Account account0, String s, Date date0, Date date1, Boolean boolean0) {
        this.id = long0;
        this.properties = map0;
        this.kakaoAccount = account0;
        this.groupUserToken = s;
        this.connectedAt = date0;
        this.synchedAt = date1;
        this.hasSignedUp = boolean0;
    }

    public final Long component1() {
        return this.id;
    }

    public final Map component2() {
        return this.properties;
    }

    public final Account component3() {
        return this.kakaoAccount;
    }

    public final String component4() {
        return this.groupUserToken;
    }

    public final Date component5() {
        return this.connectedAt;
    }

    public final Date component6() {
        return this.synchedAt;
    }

    public final Boolean component7() {
        return this.hasSignedUp;
    }

    public final User copy(Long long0, Map map0, Account account0, String s, Date date0, Date date1, Boolean boolean0) {
        return new User(long0, map0, account0, s, date0, date1, boolean0);
    }

    public static User copy$default(User user0, Long long0, Map map0, Account account0, String s, Date date0, Date date1, Boolean boolean0, int v, Object object0) {
        if((v & 1) != 0) {
            long0 = user0.id;
        }
        if((v & 2) != 0) {
            map0 = user0.properties;
        }
        if((v & 4) != 0) {
            account0 = user0.kakaoAccount;
        }
        if((v & 8) != 0) {
            s = user0.groupUserToken;
        }
        if((v & 16) != 0) {
            date0 = user0.connectedAt;
        }
        if((v & 0x20) != 0) {
            date1 = user0.synchedAt;
        }
        if((v & 0x40) != 0) {
            boolean0 = user0.hasSignedUp;
        }
        return user0.copy(long0, map0, account0, s, date0, date1, boolean0);
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
        if(!(object0 instanceof User)) {
            return false;
        }
        if(!Intrinsics.areEqual(this.id, ((User)object0).id)) {
            return false;
        }
        if(!Intrinsics.areEqual(this.properties, ((User)object0).properties)) {
            return false;
        }
        if(!Intrinsics.areEqual(this.kakaoAccount, ((User)object0).kakaoAccount)) {
            return false;
        }
        if(!Intrinsics.areEqual(this.groupUserToken, ((User)object0).groupUserToken)) {
            return false;
        }
        if(!Intrinsics.areEqual(this.connectedAt, ((User)object0).connectedAt)) {
            return false;
        }
        return Intrinsics.areEqual(this.synchedAt, ((User)object0).synchedAt) ? Intrinsics.areEqual(this.hasSignedUp, ((User)object0).hasSignedUp) : false;
    }

    public final Date getConnectedAt() {
        return this.connectedAt;
    }

    public final String getGroupUserToken() {
        return this.groupUserToken;
    }

    public final Boolean getHasSignedUp() {
        return this.hasSignedUp;
    }

    public final Long getId() {
        return this.id;
    }

    public final Account getKakaoAccount() {
        return this.kakaoAccount;
    }

    public final Map getProperties() {
        return this.properties;
    }

    public final Date getSynchedAt() {
        return this.synchedAt;
    }

    @Override
    public int hashCode() {
        int v = 0;
        int v1 = this.id == null ? 0 : this.id.hashCode();
        int v2 = this.properties == null ? 0 : this.properties.hashCode();
        int v3 = this.kakaoAccount == null ? 0 : this.kakaoAccount.hashCode();
        int v4 = this.groupUserToken == null ? 0 : this.groupUserToken.hashCode();
        int v5 = this.connectedAt == null ? 0 : this.connectedAt.hashCode();
        int v6 = this.synchedAt == null ? 0 : this.synchedAt.hashCode();
        Boolean boolean0 = this.hasSignedUp;
        if(boolean0 != null) {
            v = boolean0.hashCode();
        }
        return (((((v1 * 0x1F + v2) * 0x1F + v3) * 0x1F + v4) * 0x1F + v5) * 0x1F + v6) * 0x1F + v;
    }

    @Override
    public String toString() {
        return "User(id=" + this.id + ", properties=" + this.properties + ", kakaoAccount=" + this.kakaoAccount + ", groupUserToken=" + this.groupUserToken + ", connectedAt=" + this.connectedAt + ", synchedAt=" + this.synchedAt + ", hasSignedUp=" + this.hasSignedUp + ')';
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
        Map map0 = this.properties;
        if(map0 == null) {
            parcel0.writeInt(0);
        }
        else {
            parcel0.writeInt(1);
            parcel0.writeInt(map0.size());
            for(Object object0: map0.entrySet()) {
                parcel0.writeString(((String)((Map.Entry)object0).getKey()));
                parcel0.writeString(((String)((Map.Entry)object0).getValue()));
            }
        }
        Account account0 = this.kakaoAccount;
        if(account0 == null) {
            parcel0.writeInt(0);
        }
        else {
            parcel0.writeInt(1);
            account0.writeToParcel(parcel0, v);
        }
        parcel0.writeString(this.groupUserToken);
        parcel0.writeSerializable(this.connectedAt);
        parcel0.writeSerializable(this.synchedAt);
        Boolean boolean0 = this.hasSignedUp;
        if(boolean0 == null) {
            parcel0.writeInt(0);
            return;
        }
        parcel0.writeInt(1);
        parcel0.writeInt(((int)boolean0.booleanValue()));
    }
}

