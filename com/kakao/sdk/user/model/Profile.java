package com.kakao.sdk.user.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.Parcelable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0003\n\u0002\u0010\u000B\n\u0002\b\u000E\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B-\u0012\b\u0010\u0002\u001A\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001A\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001A\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001A\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\u000B\u0010\u000F\u001A\u0004\u0018\u00010\u0003HÆ\u0003J\u000B\u0010\u0010\u001A\u0004\u0018\u00010\u0003HÆ\u0003J\u000B\u0010\u0011\u001A\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\u0012\u001A\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\tJ>\u0010\u0013\u001A\u00020\u00002\n\b\u0002\u0010\u0002\u001A\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001A\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001A\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001A\u0004\u0018\u00010\u0007HÆ\u0001¢\u0006\u0002\u0010\u0014J\t\u0010\u0015\u001A\u00020\u0016HÖ\u0001J\u0013\u0010\u0017\u001A\u00020\u00072\b\u0010\u0018\u001A\u0004\u0018\u00010\u0019HÖ\u0003J\t\u0010\u001A\u001A\u00020\u0016HÖ\u0001J\t\u0010\u001B\u001A\u00020\u0003HÖ\u0001J\u0019\u0010\u001C\u001A\u00020\u001D2\u0006\u0010\u001E\u001A\u00020\u001F2\u0006\u0010 \u001A\u00020\u0016HÖ\u0001R\u0015\u0010\u0006\u001A\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010\n\u001A\u0004\b\u0006\u0010\tR\u0013\u0010\u0002\u001A\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001A\u0004\b\u000B\u0010\fR\u0013\u0010\u0004\u001A\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001A\u0004\b\r\u0010\fR\u0013\u0010\u0005\u001A\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001A\u0004\b\u000E\u0010\f¨\u0006!"}, d2 = {"Lcom/kakao/sdk/user/model/Profile;", "Landroid/os/Parcelable;", "nickname", "", "profileImageUrl", "thumbnailImageUrl", "isDefaultImage", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;)V", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getNickname", "()Ljava/lang/String;", "getProfileImageUrl", "getThumbnailImageUrl", "component1", "component2", "component3", "component4", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;)Lcom/kakao/sdk/user/model/Profile;", "describeContents", "", "equals", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "user_release"}, k = 1, mv = {1, 5, 1}, xi = 0x30)
public final class Profile implements Parcelable {
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 0x30)
    public static final class Creator implements Parcelable.Creator {
        public final Profile createFromParcel(Parcel parcel0) {
            Intrinsics.checkNotNullParameter(parcel0, "parcel");
            String s = parcel0.readString();
            String s1 = parcel0.readString();
            String s2 = parcel0.readString();
            if(parcel0.readInt() == 0) {
                return new Profile(s, s1, s2, null);
            }
            return parcel0.readInt() == 0 ? new Profile(s, s1, s2, Boolean.FALSE) : new Profile(s, s1, s2, Boolean.TRUE);
        }

        @Override  // android.os.Parcelable$Creator
        public Object createFromParcel(Parcel parcel0) {
            return this.createFromParcel(parcel0);
        }

        public final Profile[] newArray(int v) {
            return new Profile[v];
        }

        @Override  // android.os.Parcelable$Creator
        public Object[] newArray(int v) {
            return this.newArray(v);
        }
    }

    public static final Parcelable.Creator CREATOR;
    private final Boolean isDefaultImage;
    private final String nickname;
    private final String profileImageUrl;
    private final String thumbnailImageUrl;

    static {
        Profile.CREATOR = new Creator();
    }

    public Profile(String s, String s1, String s2, Boolean boolean0) {
        this.nickname = s;
        this.profileImageUrl = s1;
        this.thumbnailImageUrl = s2;
        this.isDefaultImage = boolean0;
    }

    public final String component1() {
        return this.nickname;
    }

    public final String component2() {
        return this.profileImageUrl;
    }

    public final String component3() {
        return this.thumbnailImageUrl;
    }

    public final Boolean component4() {
        return this.isDefaultImage;
    }

    public final Profile copy(String s, String s1, String s2, Boolean boolean0) {
        return new Profile(s, s1, s2, boolean0);
    }

    public static Profile copy$default(Profile profile0, String s, String s1, String s2, Boolean boolean0, int v, Object object0) {
        if((v & 1) != 0) {
            s = profile0.nickname;
        }
        if((v & 2) != 0) {
            s1 = profile0.profileImageUrl;
        }
        if((v & 4) != 0) {
            s2 = profile0.thumbnailImageUrl;
        }
        if((v & 8) != 0) {
            boolean0 = profile0.isDefaultImage;
        }
        return profile0.copy(s, s1, s2, boolean0);
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
        if(!(object0 instanceof Profile)) {
            return false;
        }
        if(!Intrinsics.areEqual(this.nickname, ((Profile)object0).nickname)) {
            return false;
        }
        if(!Intrinsics.areEqual(this.profileImageUrl, ((Profile)object0).profileImageUrl)) {
            return false;
        }
        return Intrinsics.areEqual(this.thumbnailImageUrl, ((Profile)object0).thumbnailImageUrl) ? Intrinsics.areEqual(this.isDefaultImage, ((Profile)object0).isDefaultImage) : false;
    }

    public final String getNickname() {
        return this.nickname;
    }

    public final String getProfileImageUrl() {
        return this.profileImageUrl;
    }

    public final String getThumbnailImageUrl() {
        return this.thumbnailImageUrl;
    }

    @Override
    public int hashCode() {
        int v = 0;
        int v1 = this.nickname == null ? 0 : this.nickname.hashCode();
        int v2 = this.profileImageUrl == null ? 0 : this.profileImageUrl.hashCode();
        int v3 = this.thumbnailImageUrl == null ? 0 : this.thumbnailImageUrl.hashCode();
        Boolean boolean0 = this.isDefaultImage;
        if(boolean0 != null) {
            v = boolean0.hashCode();
        }
        return ((v1 * 0x1F + v2) * 0x1F + v3) * 0x1F + v;
    }

    public final Boolean isDefaultImage() {
        return this.isDefaultImage;
    }

    @Override
    public String toString() {
        return "Profile(nickname=" + this.nickname + ", profileImageUrl=" + this.profileImageUrl + ", thumbnailImageUrl=" + this.thumbnailImageUrl + ", isDefaultImage=" + this.isDefaultImage + ')';
    }

    @Override  // android.os.Parcelable
    public void writeToParcel(Parcel parcel0, int v) {
        Intrinsics.checkNotNullParameter(parcel0, "out");
        parcel0.writeString(this.nickname);
        parcel0.writeString(this.profileImageUrl);
        parcel0.writeString(this.thumbnailImageUrl);
        Boolean boolean0 = this.isDefaultImage;
        if(boolean0 == null) {
            parcel0.writeInt(0);
            return;
        }
        parcel0.writeInt(1);
        parcel0.writeInt(((int)boolean0.booleanValue()));
    }
}

