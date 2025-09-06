package com.navercorp.nid.profile.data;

import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000E\n\u0002\b\'\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B}\u0012\b\u0010\u0002\u001A\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001A\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001A\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001A\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001A\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001A\u0004\u0018\u00010\u0003\u0012\b\u0010\t\u001A\u0004\u0018\u00010\u0003\u0012\b\u0010\n\u001A\u0004\u0018\u00010\u0003\u0012\b\u0010\u000B\u001A\u0004\u0018\u00010\u0003\u0012\b\u0010\f\u001A\u0004\u0018\u00010\u0003\u0012\b\u0010\r\u001A\u0004\u0018\u00010\u0003\u0012\b\u0010\u000E\u001A\u0004\u0018\u00010\u0003\u00A2\u0006\u0002\u0010\u000FJ\u000B\u0010\u001D\u001A\u0004\u0018\u00010\u0003H\u00C6\u0003J\u000B\u0010\u001E\u001A\u0004\u0018\u00010\u0003H\u00C6\u0003J\u000B\u0010\u001F\u001A\u0004\u0018\u00010\u0003H\u00C6\u0003J\u000B\u0010 \u001A\u0004\u0018\u00010\u0003H\u00C6\u0003J\u000B\u0010!\u001A\u0004\u0018\u00010\u0003H\u00C6\u0003J\u000B\u0010\"\u001A\u0004\u0018\u00010\u0003H\u00C6\u0003J\u000B\u0010#\u001A\u0004\u0018\u00010\u0003H\u00C6\u0003J\u000B\u0010$\u001A\u0004\u0018\u00010\u0003H\u00C6\u0003J\u000B\u0010%\u001A\u0004\u0018\u00010\u0003H\u00C6\u0003J\u000B\u0010&\u001A\u0004\u0018\u00010\u0003H\u00C6\u0003J\u000B\u0010\'\u001A\u0004\u0018\u00010\u0003H\u00C6\u0003J\u000B\u0010(\u001A\u0004\u0018\u00010\u0003H\u00C6\u0003J\u0099\u0001\u0010)\u001A\u00020\u00002\n\b\u0002\u0010\u0002\u001A\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001A\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001A\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001A\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001A\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001A\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001A\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001A\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000B\u001A\u0004\u0018\u00010\u00032\n\b\u0002\u0010\f\u001A\u0004\u0018\u00010\u00032\n\b\u0002\u0010\r\u001A\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000E\u001A\u0004\u0018\u00010\u0003H\u00C6\u0001J\u0013\u0010*\u001A\u00020+2\b\u0010,\u001A\u0004\u0018\u00010\u0001H\u00D6\u0003J\t\u0010-\u001A\u00020.H\u00D6\u0001J\t\u0010/\u001A\u00020\u0003H\u00D6\u0001R\u0018\u0010\b\u001A\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0010\u0010\u0011R\u0018\u0010\u000B\u001A\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0012\u0010\u0011R\u0018\u0010\t\u001A\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0013\u0010\u0011R\u0018\u0010\r\u001A\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0014\u0010\u0011R\u0018\u0010\u0006\u001A\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0015\u0010\u0011R\u0018\u0010\u000E\u001A\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0016\u0010\u0011R\u0018\u0010\u0007\u001A\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0017\u0010\u0011R\u0018\u0010\u0002\u001A\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0018\u0010\u0011R\u0018\u0010\f\u001A\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0019\u0010\u0011R\u0018\u0010\u0005\u001A\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b\u001A\u0010\u0011R\u0018\u0010\u0004\u001A\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b\u001B\u0010\u0011R\u0018\u0010\n\u001A\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b\u001C\u0010\u0011\u00A8\u00060"}, d2 = {"Lcom/navercorp/nid/profile/data/NidProfile;", "", "id", "", "nickname", "name", "email", "gender", "age", "birthday", "profileImage", "birthYear", "mobile", "ci", "encId", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAge", "()Ljava/lang/String;", "getBirthYear", "getBirthday", "getCi", "getEmail", "getEncId", "getGender", "getId", "getMobile", "getName", "getNickname", "getProfileImage", "component1", "component10", "component11", "component12", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "", "toString", "Nid-OAuth_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public final class NidProfile {
    @SerializedName("age")
    private final String age;
    @SerializedName("birthyear")
    private final String birthYear;
    @SerializedName("birthday")
    private final String birthday;
    @SerializedName("ci")
    private final String ci;
    @SerializedName("email")
    private final String email;
    @SerializedName("enc_id")
    private final String encId;
    @SerializedName("gender")
    private final String gender;
    @SerializedName("id")
    private final String id;
    @SerializedName("mobile")
    private final String mobile;
    @SerializedName("name")
    private final String name;
    @SerializedName("nickname")
    private final String nickname;
    @SerializedName("profile_image")
    private final String profileImage;

    public NidProfile(String s, String s1, String s2, String s3, String s4, String s5, String s6, String s7, String s8, String s9, String s10, String s11) {
        this.id = s;
        this.nickname = s1;
        this.name = s2;
        this.email = s3;
        this.gender = s4;
        this.age = s5;
        this.birthday = s6;
        this.profileImage = s7;
        this.birthYear = s8;
        this.mobile = s9;
        this.ci = s10;
        this.encId = s11;
    }

    public final String component1() {
        return this.id;
    }

    public final String component10() {
        return this.mobile;
    }

    public final String component11() {
        return this.ci;
    }

    public final String component12() {
        return this.encId;
    }

    public final String component2() {
        return this.nickname;
    }

    public final String component3() {
        return this.name;
    }

    public final String component4() {
        return this.email;
    }

    public final String component5() {
        return this.gender;
    }

    public final String component6() {
        return this.age;
    }

    public final String component7() {
        return this.birthday;
    }

    public final String component8() {
        return this.profileImage;
    }

    public final String component9() {
        return this.birthYear;
    }

    public final NidProfile copy(String s, String s1, String s2, String s3, String s4, String s5, String s6, String s7, String s8, String s9, String s10, String s11) {
        return new NidProfile(s, s1, s2, s3, s4, s5, s6, s7, s8, s9, s10, s11);
    }

    public static NidProfile copy$default(NidProfile nidProfile0, String s, String s1, String s2, String s3, String s4, String s5, String s6, String s7, String s8, String s9, String s10, String s11, int v, Object object0) {
        if((v & 1) != 0) {
            s = nidProfile0.id;
        }
        if((v & 2) != 0) {
            s1 = nidProfile0.nickname;
        }
        if((v & 4) != 0) {
            s2 = nidProfile0.name;
        }
        if((v & 8) != 0) {
            s3 = nidProfile0.email;
        }
        if((v & 16) != 0) {
            s4 = nidProfile0.gender;
        }
        if((v & 0x20) != 0) {
            s5 = nidProfile0.age;
        }
        if((v & 0x40) != 0) {
            s6 = nidProfile0.birthday;
        }
        if((v & 0x80) != 0) {
            s7 = nidProfile0.profileImage;
        }
        if((v & 0x100) != 0) {
            s8 = nidProfile0.birthYear;
        }
        if((v & 0x200) != 0) {
            s9 = nidProfile0.mobile;
        }
        if((v & 0x400) != 0) {
            s10 = nidProfile0.ci;
        }
        if((v & 0x800) != 0) {
            s11 = nidProfile0.encId;
        }
        return nidProfile0.copy(s, s1, s2, s3, s4, s5, s6, s7, s8, s9, s10, s11);
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(!(object0 instanceof NidProfile)) {
            return false;
        }
        if(!Intrinsics.areEqual(this.id, ((NidProfile)object0).id)) {
            return false;
        }
        if(!Intrinsics.areEqual(this.nickname, ((NidProfile)object0).nickname)) {
            return false;
        }
        if(!Intrinsics.areEqual(this.name, ((NidProfile)object0).name)) {
            return false;
        }
        if(!Intrinsics.areEqual(this.email, ((NidProfile)object0).email)) {
            return false;
        }
        if(!Intrinsics.areEqual(this.gender, ((NidProfile)object0).gender)) {
            return false;
        }
        if(!Intrinsics.areEqual(this.age, ((NidProfile)object0).age)) {
            return false;
        }
        if(!Intrinsics.areEqual(this.birthday, ((NidProfile)object0).birthday)) {
            return false;
        }
        if(!Intrinsics.areEqual(this.profileImage, ((NidProfile)object0).profileImage)) {
            return false;
        }
        if(!Intrinsics.areEqual(this.birthYear, ((NidProfile)object0).birthYear)) {
            return false;
        }
        if(!Intrinsics.areEqual(this.mobile, ((NidProfile)object0).mobile)) {
            return false;
        }
        return Intrinsics.areEqual(this.ci, ((NidProfile)object0).ci) ? Intrinsics.areEqual(this.encId, ((NidProfile)object0).encId) : false;
    }

    public final String getAge() {
        return this.age;
    }

    public final String getBirthYear() {
        return this.birthYear;
    }

    public final String getBirthday() {
        return this.birthday;
    }

    public final String getCi() {
        return this.ci;
    }

    public final String getEmail() {
        return this.email;
    }

    public final String getEncId() {
        return this.encId;
    }

    public final String getGender() {
        return this.gender;
    }

    public final String getId() {
        return this.id;
    }

    public final String getMobile() {
        return this.mobile;
    }

    public final String getName() {
        return this.name;
    }

    public final String getNickname() {
        return this.nickname;
    }

    public final String getProfileImage() {
        return this.profileImage;
    }

    @Override
    public int hashCode() {
        int v = 0;
        int v1 = this.id == null ? 0 : this.id.hashCode();
        int v2 = this.nickname == null ? 0 : this.nickname.hashCode();
        int v3 = this.name == null ? 0 : this.name.hashCode();
        int v4 = this.email == null ? 0 : this.email.hashCode();
        int v5 = this.gender == null ? 0 : this.gender.hashCode();
        int v6 = this.age == null ? 0 : this.age.hashCode();
        int v7 = this.birthday == null ? 0 : this.birthday.hashCode();
        int v8 = this.profileImage == null ? 0 : this.profileImage.hashCode();
        int v9 = this.birthYear == null ? 0 : this.birthYear.hashCode();
        int v10 = this.mobile == null ? 0 : this.mobile.hashCode();
        int v11 = this.ci == null ? 0 : this.ci.hashCode();
        String s = this.encId;
        if(s != null) {
            v = s.hashCode();
        }
        return ((((((((((v1 * 0x1F + v2) * 0x1F + v3) * 0x1F + v4) * 0x1F + v5) * 0x1F + v6) * 0x1F + v7) * 0x1F + v8) * 0x1F + v9) * 0x1F + v10) * 0x1F + v11) * 0x1F + v;
    }

    @Override
    public String toString() {
        return "NidProfile(id=" + this.id + ", nickname=" + this.nickname + ", name=" + this.name + ", email=" + this.email + ", gender=" + this.gender + ", age=" + this.age + ", birthday=" + this.birthday + ", profileImage=" + this.profileImage + ", birthYear=" + this.birthYear + ", mobile=" + this.mobile + ", ci=" + this.ci + ", encId=" + this.encId + ")";
    }
}

