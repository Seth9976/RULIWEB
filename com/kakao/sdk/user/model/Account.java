package com.kakao.sdk.user.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.Parcelable;
import java.util.Date;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\bR\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u00C5\u0002\u0012\b\u0010\u0002\u001A\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001A\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001A\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001A\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001A\u0004\u0018\u00010\u0003\u0012\b\u0010\t\u001A\u0004\u0018\u00010\n\u0012\b\u0010\u000B\u001A\u0004\u0018\u00010\u0003\u0012\b\u0010\f\u001A\u0004\u0018\u00010\u0003\u0012\b\u0010\r\u001A\u0004\u0018\u00010\u0003\u0012\b\u0010\u000E\u001A\u0004\u0018\u00010\n\u0012\b\u0010\u000F\u001A\u0004\u0018\u00010\u0003\u0012\b\u0010\u0010\u001A\u0004\u0018\u00010\u0011\u0012\b\u0010\u0012\u001A\u0004\u0018\u00010\u0003\u0012\b\u0010\u0013\u001A\u0004\u0018\u00010\n\u0012\b\u0010\u0014\u001A\u0004\u0018\u00010\u0003\u0012\b\u0010\u0015\u001A\u0004\u0018\u00010\n\u0012\b\u0010\u0016\u001A\u0004\u0018\u00010\u0017\u0012\b\u0010\u0018\u001A\u0004\u0018\u00010\u0003\u0012\b\u0010\u0019\u001A\u0004\u0018\u00010\u001A\u0012\b\u0010\u001B\u001A\u0004\u0018\u00010\u0003\u0012\b\u0010\u001C\u001A\u0004\u0018\u00010\n\u0012\b\u0010\u001D\u001A\u0004\u0018\u00010\u001E\u0012\b\u0010\u001F\u001A\u0004\u0018\u00010\u0003\u0012\b\u0010 \u001A\u0004\u0018\u00010\n\u0012\b\u0010!\u001A\u0004\u0018\u00010\u0003\u0012\b\u0010\"\u001A\u0004\u0018\u00010\n\u0012\b\u0010#\u001A\u0004\u0018\u00010\u0003\u0012\b\u0010$\u001A\u0004\u0018\u00010\u001A\u0012\b\u0010%\u001A\u0004\u0018\u00010\u0003\u0012\b\u0010&\u001A\u0004\u0018\u00010\n\u0012\b\u0010\'\u001A\u0004\u0018\u00010\u0003\u0012\b\u0010(\u001A\u0004\u0018\u00010\u0003\u00A2\u0006\u0002\u0010)J\u0010\u0010N\u001A\u0004\u0018\u00010\u0003H\u00C6\u0003\u00A2\u0006\u0002\u0010-J\u000B\u0010O\u001A\u0004\u0018\u00010\nH\u00C6\u0003J\u0010\u0010P\u001A\u0004\u0018\u00010\u0003H\u00C6\u0003\u00A2\u0006\u0002\u0010-J\u000B\u0010Q\u001A\u0004\u0018\u00010\u0011H\u00C6\u0003J\u0010\u0010R\u001A\u0004\u0018\u00010\u0003H\u00C6\u0003\u00A2\u0006\u0002\u0010-J\u000B\u0010S\u001A\u0004\u0018\u00010\nH\u00C6\u0003J\u0010\u0010T\u001A\u0004\u0018\u00010\u0003H\u00C6\u0003\u00A2\u0006\u0002\u0010-J\u000B\u0010U\u001A\u0004\u0018\u00010\nH\u00C6\u0003J\u000B\u0010V\u001A\u0004\u0018\u00010\u0017H\u00C6\u0003J\u0010\u0010W\u001A\u0004\u0018\u00010\u0003H\u00C6\u0003\u00A2\u0006\u0002\u0010-J\u000B\u0010X\u001A\u0004\u0018\u00010\u001AH\u00C6\u0003J\u0010\u0010Y\u001A\u0004\u0018\u00010\u0003H\u00C6\u0003\u00A2\u0006\u0002\u0010-J\u0010\u0010Z\u001A\u0004\u0018\u00010\u0003H\u00C6\u0003\u00A2\u0006\u0002\u0010-J\u000B\u0010[\u001A\u0004\u0018\u00010\nH\u00C6\u0003J\u000B\u0010\\\u001A\u0004\u0018\u00010\u001EH\u00C6\u0003J\u0010\u0010]\u001A\u0004\u0018\u00010\u0003H\u00C6\u0003\u00A2\u0006\u0002\u0010-J\u000B\u0010^\u001A\u0004\u0018\u00010\nH\u00C6\u0003J\u0010\u0010_\u001A\u0004\u0018\u00010\u0003H\u00C6\u0003\u00A2\u0006\u0002\u0010-J\u000B\u0010`\u001A\u0004\u0018\u00010\nH\u00C6\u0003J\u0010\u0010a\u001A\u0004\u0018\u00010\u0003H\u00C6\u0003\u00A2\u0006\u0002\u0010-J\u000B\u0010b\u001A\u0004\u0018\u00010\u001AH\u00C6\u0003J\u0010\u0010c\u001A\u0004\u0018\u00010\u0003H\u00C6\u0003\u00A2\u0006\u0002\u0010-J\u0010\u0010d\u001A\u0004\u0018\u00010\u0003H\u00C6\u0003\u00A2\u0006\u0002\u0010-J\u000B\u0010e\u001A\u0004\u0018\u00010\nH\u00C6\u0003J\u0010\u0010f\u001A\u0004\u0018\u00010\u0003H\u00C6\u0003\u00A2\u0006\u0002\u0010-J\u0010\u0010g\u001A\u0004\u0018\u00010\u0003H\u00C6\u0003\u00A2\u0006\u0002\u0010-J\u000B\u0010h\u001A\u0004\u0018\u00010\u0007H\u00C6\u0003J\u0010\u0010i\u001A\u0004\u0018\u00010\u0003H\u00C6\u0003\u00A2\u0006\u0002\u0010-J\u000B\u0010j\u001A\u0004\u0018\u00010\nH\u00C6\u0003J\u0010\u0010k\u001A\u0004\u0018\u00010\u0003H\u00C6\u0003\u00A2\u0006\u0002\u0010-J\u0010\u0010l\u001A\u0004\u0018\u00010\u0003H\u00C6\u0003\u00A2\u0006\u0002\u0010-J\u0010\u0010m\u001A\u0004\u0018\u00010\u0003H\u00C6\u0003\u00A2\u0006\u0002\u0010-J\u008E\u0003\u0010n\u001A\u00020\u00002\n\b\u0002\u0010\u0002\u001A\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001A\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001A\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001A\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001A\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001A\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000B\u001A\u0004\u0018\u00010\u00032\n\b\u0002\u0010\f\u001A\u0004\u0018\u00010\u00032\n\b\u0002\u0010\r\u001A\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000E\u001A\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000F\u001A\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0010\u001A\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0012\u001A\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0013\u001A\u0004\u0018\u00010\n2\n\b\u0002\u0010\u0014\u001A\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0015\u001A\u0004\u0018\u00010\n2\n\b\u0002\u0010\u0016\u001A\u0004\u0018\u00010\u00172\n\b\u0002\u0010\u0018\u001A\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0019\u001A\u0004\u0018\u00010\u001A2\n\b\u0002\u0010\u001B\u001A\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u001C\u001A\u0004\u0018\u00010\n2\n\b\u0002\u0010\u001D\u001A\u0004\u0018\u00010\u001E2\n\b\u0002\u0010\u001F\u001A\u0004\u0018\u00010\u00032\n\b\u0002\u0010 \u001A\u0004\u0018\u00010\n2\n\b\u0002\u0010!\u001A\u0004\u0018\u00010\u00032\n\b\u0002\u0010\"\u001A\u0004\u0018\u00010\n2\n\b\u0002\u0010#\u001A\u0004\u0018\u00010\u00032\n\b\u0002\u0010$\u001A\u0004\u0018\u00010\u001A2\n\b\u0002\u0010%\u001A\u0004\u0018\u00010\u00032\n\b\u0002\u0010&\u001A\u0004\u0018\u00010\n2\n\b\u0002\u0010\'\u001A\u0004\u0018\u00010\u00032\n\b\u0002\u0010(\u001A\u0004\u0018\u00010\u0003H\u00C6\u0001\u00A2\u0006\u0002\u0010oJ\t\u0010p\u001A\u00020qH\u00D6\u0001J\u0013\u0010r\u001A\u00020\u00032\b\u0010s\u001A\u0004\u0018\u00010tH\u00D6\u0003J\t\u0010u\u001A\u00020qH\u00D6\u0001J\t\u0010v\u001A\u00020\nH\u00D6\u0001J\u0019\u0010w\u001A\u00020x2\u0006\u0010y\u001A\u00020z2\u0006\u0010{\u001A\u00020qH\u00D6\u0001R\u0013\u0010\u0010\u001A\u0004\u0018\u00010\u0011\u00A2\u0006\b\n\u0000\u001A\u0004\b*\u0010+R\u0015\u0010\u000F\u001A\u0004\u0018\u00010\u0003\u00A2\u0006\n\n\u0002\u0010.\u001A\u0004\b,\u0010-R\u0013\u0010\u0015\u001A\u0004\u0018\u00010\n\u00A2\u0006\b\n\u0000\u001A\u0004\b/\u00100R\u0015\u0010\u0014\u001A\u0004\u0018\u00010\u0003\u00A2\u0006\n\n\u0002\u0010.\u001A\u0004\b1\u0010-R\u0013\u0010\u0016\u001A\u0004\u0018\u00010\u0017\u00A2\u0006\b\n\u0000\u001A\u0004\b2\u00103R\u0013\u0010\u0013\u001A\u0004\u0018\u00010\n\u00A2\u0006\b\n\u0000\u001A\u0004\b4\u00100R\u0015\u0010\u0012\u001A\u0004\u0018\u00010\u0003\u00A2\u0006\n\n\u0002\u0010.\u001A\u0004\b5\u0010-R\u0013\u0010\u001C\u001A\u0004\u0018\u00010\n\u00A2\u0006\b\n\u0000\u001A\u0004\b6\u00100R\u0013\u0010\u001D\u001A\u0004\u0018\u00010\u001E\u00A2\u0006\b\n\u0000\u001A\u0004\b7\u00108R\u0015\u0010\u001B\u001A\u0004\u0018\u00010\u0003\u00A2\u0006\n\n\u0002\u0010.\u001A\u0004\b9\u0010-R\u0013\u0010\u000E\u001A\u0004\u0018\u00010\n\u00A2\u0006\b\n\u0000\u001A\u0004\b:\u00100R\u0015\u0010\u000B\u001A\u0004\u0018\u00010\u0003\u00A2\u0006\n\n\u0002\u0010.\u001A\u0004\b;\u0010-R\u0013\u0010\u0019\u001A\u0004\u0018\u00010\u001A\u00A2\u0006\b\n\u0000\u001A\u0004\b<\u0010=R\u0015\u0010\u0018\u001A\u0004\u0018\u00010\u0003\u00A2\u0006\n\n\u0002\u0010.\u001A\u0004\b>\u0010-R\u0015\u0010\f\u001A\u0004\u0018\u00010\u0003\u00A2\u0006\n\n\u0002\u0010.\u001A\u0004\b\f\u0010-R\u0015\u0010\r\u001A\u0004\u0018\u00010\u0003\u00A2\u0006\n\n\u0002\u0010.\u001A\u0004\b\r\u0010-R\u0015\u0010(\u001A\u0004\u0018\u00010\u0003\u00A2\u0006\n\n\u0002\u0010.\u001A\u0004\b(\u0010-R\u0015\u0010\'\u001A\u0004\u0018\u00010\u0003\u00A2\u0006\n\n\u0002\u0010.\u001A\u0004\b\'\u0010-R\u0013\u0010\"\u001A\u0004\u0018\u00010\n\u00A2\u0006\b\n\u0000\u001A\u0004\b?\u00100R\u0015\u0010!\u001A\u0004\u0018\u00010\u0003\u00A2\u0006\n\n\u0002\u0010.\u001A\u0004\b@\u0010-R\u0013\u0010$\u001A\u0004\u0018\u00010\u001A\u00A2\u0006\b\n\u0000\u001A\u0004\bA\u0010=R\u0015\u0010#\u001A\u0004\u0018\u00010\u0003\u00A2\u0006\n\n\u0002\u0010.\u001A\u0004\bB\u0010-R\u0013\u0010 \u001A\u0004\u0018\u00010\n\u00A2\u0006\b\n\u0000\u001A\u0004\bC\u00100R\u0015\u0010\u001F\u001A\u0004\u0018\u00010\u0003\u00A2\u0006\n\n\u0002\u0010.\u001A\u0004\bD\u0010-R\u0013\u0010\t\u001A\u0004\u0018\u00010\n\u00A2\u0006\b\n\u0000\u001A\u0004\bE\u00100R\u0015\u0010\b\u001A\u0004\u0018\u00010\u0003\u00A2\u0006\n\n\u0002\u0010.\u001A\u0004\bF\u0010-R\u0013\u0010&\u001A\u0004\u0018\u00010\n\u00A2\u0006\b\n\u0000\u001A\u0004\bG\u00100R\u0015\u0010%\u001A\u0004\u0018\u00010\u0003\u00A2\u0006\n\n\u0002\u0010.\u001A\u0004\bH\u0010-R\u0013\u0010\u0006\u001A\u0004\u0018\u00010\u0007\u00A2\u0006\b\n\u0000\u001A\u0004\bI\u0010JR\u0015\u0010\u0005\u001A\u0004\u0018\u00010\u0003\u00A2\u0006\n\n\u0002\u0010.\u001A\u0004\bK\u0010-R\u0015\u0010\u0002\u001A\u0004\u0018\u00010\u0003\u00A2\u0006\n\n\u0002\u0010.\u001A\u0004\bL\u0010-R\u0015\u0010\u0004\u001A\u0004\u0018\u00010\u0003\u00A2\u0006\n\n\u0002\u0010.\u001A\u0004\bM\u0010-\u00A8\u0006|"}, d2 = {"Lcom/kakao/sdk/user/model/Account;", "Landroid/os/Parcelable;", "profileNeedsAgreement", "", "profileNicknameNeedsAgreement", "profileImageNeedsAgreement", "profile", "Lcom/kakao/sdk/user/model/Profile;", "nameNeedsAgreement", "name", "", "emailNeedsAgreement", "isEmailValid", "isEmailVerified", "email", "ageRangeNeedsAgreement", "ageRange", "Lcom/kakao/sdk/user/model/AgeRange;", "birthyearNeedsAgreement", "birthyear", "birthdayNeedsAgreement", "birthday", "birthdayType", "Lcom/kakao/sdk/user/model/BirthdayType;", "genderNeedsAgreement", "gender", "Lcom/kakao/sdk/user/model/Gender;", "ciNeedsAgreement", "ci", "ciAuthenticatedAt", "Ljava/util/Date;", "legalNameNeedsAgreement", "legalName", "legalBirthDateNeedsAgreement", "legalBirthDate", "legalGenderNeedsAgreement", "legalGender", "phoneNumberNeedsAgreement", "phoneNumber", "isKoreanNeedsAgreement", "isKorean", "(Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Lcom/kakao/sdk/user/model/Profile;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/Boolean;Lcom/kakao/sdk/user/model/AgeRange;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;Lcom/kakao/sdk/user/model/BirthdayType;Ljava/lang/Boolean;Lcom/kakao/sdk/user/model/Gender;Ljava/lang/Boolean;Ljava/lang/String;Ljava/util/Date;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/Boolean;Lcom/kakao/sdk/user/model/Gender;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;)V", "getAgeRange", "()Lcom/kakao/sdk/user/model/AgeRange;", "getAgeRangeNeedsAgreement", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getBirthday", "()Ljava/lang/String;", "getBirthdayNeedsAgreement", "getBirthdayType", "()Lcom/kakao/sdk/user/model/BirthdayType;", "getBirthyear", "getBirthyearNeedsAgreement", "getCi", "getCiAuthenticatedAt", "()Ljava/util/Date;", "getCiNeedsAgreement", "getEmail", "getEmailNeedsAgreement", "getGender", "()Lcom/kakao/sdk/user/model/Gender;", "getGenderNeedsAgreement", "getLegalBirthDate", "getLegalBirthDateNeedsAgreement", "getLegalGender", "getLegalGenderNeedsAgreement", "getLegalName", "getLegalNameNeedsAgreement", "getName", "getNameNeedsAgreement", "getPhoneNumber", "getPhoneNumberNeedsAgreement", "getProfile", "()Lcom/kakao/sdk/user/model/Profile;", "getProfileImageNeedsAgreement", "getProfileNeedsAgreement", "getProfileNicknameNeedsAgreement", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component21", "component22", "component23", "component24", "component25", "component26", "component27", "component28", "component29", "component3", "component30", "component31", "component32", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Lcom/kakao/sdk/user/model/Profile;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/Boolean;Lcom/kakao/sdk/user/model/AgeRange;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;Lcom/kakao/sdk/user/model/BirthdayType;Ljava/lang/Boolean;Lcom/kakao/sdk/user/model/Gender;Ljava/lang/Boolean;Ljava/lang/String;Ljava/util/Date;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/Boolean;Lcom/kakao/sdk/user/model/Gender;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;)Lcom/kakao/sdk/user/model/Account;", "describeContents", "", "equals", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "user_release"}, k = 1, mv = {1, 5, 1}, xi = 0x30)
public final class Account implements Parcelable {
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 0x30)
    public static final class Creator implements Parcelable.Creator {
        public final Account createFromParcel(Parcel parcel0) {
            Intrinsics.checkNotNullParameter(parcel0, "parcel");
            boolean z = true;
            Boolean boolean0 = parcel0.readInt() == 0 ? null : Boolean.valueOf(parcel0.readInt() != 0);
            Boolean boolean1 = parcel0.readInt() == 0 ? null : Boolean.valueOf(parcel0.readInt() != 0);
            Boolean boolean2 = parcel0.readInt() == 0 ? null : Boolean.valueOf(parcel0.readInt() != 0);
            Object object0 = parcel0.readInt() == 0 ? null : Profile.CREATOR.createFromParcel(parcel0);
            Boolean boolean3 = parcel0.readInt() == 0 ? null : Boolean.valueOf(parcel0.readInt() != 0);
            String s = parcel0.readString();
            Boolean boolean4 = parcel0.readInt() == 0 ? null : Boolean.valueOf(parcel0.readInt() != 0);
            Boolean boolean5 = parcel0.readInt() == 0 ? null : Boolean.valueOf(parcel0.readInt() != 0);
            Boolean boolean6 = parcel0.readInt() == 0 ? null : Boolean.valueOf(parcel0.readInt() != 0);
            String s1 = parcel0.readString();
            Boolean boolean7 = parcel0.readInt() == 0 ? null : Boolean.valueOf(parcel0.readInt() != 0);
            AgeRange ageRange0 = parcel0.readInt() == 0 ? null : AgeRange.valueOf(parcel0.readString());
            Boolean boolean8 = parcel0.readInt() == 0 ? null : Boolean.valueOf(parcel0.readInt() != 0);
            String s2 = parcel0.readString();
            Boolean boolean9 = parcel0.readInt() == 0 ? null : Boolean.valueOf(parcel0.readInt() != 0);
            String s3 = parcel0.readString();
            BirthdayType birthdayType0 = parcel0.readInt() == 0 ? null : BirthdayType.valueOf(parcel0.readString());
            Boolean boolean10 = parcel0.readInt() == 0 ? null : Boolean.valueOf(parcel0.readInt() != 0);
            Gender gender0 = parcel0.readInt() == 0 ? null : Gender.valueOf(parcel0.readString());
            Boolean boolean11 = parcel0.readInt() == 0 ? null : Boolean.valueOf(parcel0.readInt() != 0);
            String s4 = parcel0.readString();
            Date date0 = (Date)parcel0.readSerializable();
            Boolean boolean12 = parcel0.readInt() == 0 ? null : Boolean.valueOf(parcel0.readInt() != 0);
            String s5 = parcel0.readString();
            Boolean boolean13 = parcel0.readInt() == 0 ? null : Boolean.valueOf(parcel0.readInt() != 0);
            String s6 = parcel0.readString();
            Boolean boolean14 = parcel0.readInt() == 0 ? null : Boolean.valueOf(parcel0.readInt() != 0);
            Gender gender1 = parcel0.readInt() == 0 ? null : Gender.valueOf(parcel0.readString());
            Boolean boolean15 = parcel0.readInt() == 0 ? null : Boolean.valueOf(parcel0.readInt() != 0);
            String s7 = parcel0.readString();
            Boolean boolean16 = parcel0.readInt() == 0 ? null : Boolean.valueOf(parcel0.readInt() != 0);
            if(parcel0.readInt() == 0) {
                return new Account(boolean0, boolean1, boolean2, ((Profile)object0), boolean3, s, boolean4, boolean5, boolean6, s1, boolean7, ageRange0, boolean8, s2, boolean9, s3, birthdayType0, boolean10, gender0, boolean11, s4, date0, boolean12, s5, boolean13, s6, boolean14, gender1, boolean15, s7, boolean16, null);
            }
            if(parcel0.readInt() == 0) {
                z = false;
            }
            return new Account(boolean0, boolean1, boolean2, ((Profile)object0), boolean3, s, boolean4, boolean5, boolean6, s1, boolean7, ageRange0, boolean8, s2, boolean9, s3, birthdayType0, boolean10, gender0, boolean11, s4, date0, boolean12, s5, boolean13, s6, boolean14, gender1, boolean15, s7, boolean16, Boolean.valueOf(z));
        }

        @Override  // android.os.Parcelable$Creator
        public Object createFromParcel(Parcel parcel0) {
            return this.createFromParcel(parcel0);
        }

        public final Account[] newArray(int v) {
            return new Account[v];
        }

        @Override  // android.os.Parcelable$Creator
        public Object[] newArray(int v) {
            return this.newArray(v);
        }
    }

    public static final Parcelable.Creator CREATOR;
    private final AgeRange ageRange;
    private final Boolean ageRangeNeedsAgreement;
    private final String birthday;
    private final Boolean birthdayNeedsAgreement;
    private final BirthdayType birthdayType;
    private final String birthyear;
    private final Boolean birthyearNeedsAgreement;
    private final String ci;
    private final Date ciAuthenticatedAt;
    private final Boolean ciNeedsAgreement;
    private final String email;
    private final Boolean emailNeedsAgreement;
    private final Gender gender;
    private final Boolean genderNeedsAgreement;
    private final Boolean isEmailValid;
    private final Boolean isEmailVerified;
    private final Boolean isKorean;
    private final Boolean isKoreanNeedsAgreement;
    private final String legalBirthDate;
    private final Boolean legalBirthDateNeedsAgreement;
    private final Gender legalGender;
    private final Boolean legalGenderNeedsAgreement;
    private final String legalName;
    private final Boolean legalNameNeedsAgreement;
    private final String name;
    private final Boolean nameNeedsAgreement;
    private final String phoneNumber;
    private final Boolean phoneNumberNeedsAgreement;
    private final Profile profile;
    private final Boolean profileImageNeedsAgreement;
    private final Boolean profileNeedsAgreement;
    private final Boolean profileNicknameNeedsAgreement;

    static {
        Account.CREATOR = new Creator();
    }

    public Account(Boolean boolean0, Boolean boolean1, Boolean boolean2, Profile profile0, Boolean boolean3, String s, Boolean boolean4, Boolean boolean5, Boolean boolean6, String s1, Boolean boolean7, AgeRange ageRange0, Boolean boolean8, String s2, Boolean boolean9, String s3, BirthdayType birthdayType0, Boolean boolean10, Gender gender0, Boolean boolean11, String s4, Date date0, Boolean boolean12, String s5, Boolean boolean13, String s6, Boolean boolean14, Gender gender1, Boolean boolean15, String s7, Boolean boolean16, Boolean boolean17) {
        this.profileNeedsAgreement = boolean0;
        this.profileNicknameNeedsAgreement = boolean1;
        this.profileImageNeedsAgreement = boolean2;
        this.profile = profile0;
        this.nameNeedsAgreement = boolean3;
        this.name = s;
        this.emailNeedsAgreement = boolean4;
        this.isEmailValid = boolean5;
        this.isEmailVerified = boolean6;
        this.email = s1;
        this.ageRangeNeedsAgreement = boolean7;
        this.ageRange = ageRange0;
        this.birthyearNeedsAgreement = boolean8;
        this.birthyear = s2;
        this.birthdayNeedsAgreement = boolean9;
        this.birthday = s3;
        this.birthdayType = birthdayType0;
        this.genderNeedsAgreement = boolean10;
        this.gender = gender0;
        this.ciNeedsAgreement = boolean11;
        this.ci = s4;
        this.ciAuthenticatedAt = date0;
        this.legalNameNeedsAgreement = boolean12;
        this.legalName = s5;
        this.legalBirthDateNeedsAgreement = boolean13;
        this.legalBirthDate = s6;
        this.legalGenderNeedsAgreement = boolean14;
        this.legalGender = gender1;
        this.phoneNumberNeedsAgreement = boolean15;
        this.phoneNumber = s7;
        this.isKoreanNeedsAgreement = boolean16;
        this.isKorean = boolean17;
    }

    public final Boolean component1() {
        return this.profileNeedsAgreement;
    }

    public final String component10() {
        return this.email;
    }

    public final Boolean component11() {
        return this.ageRangeNeedsAgreement;
    }

    public final AgeRange component12() {
        return this.ageRange;
    }

    public final Boolean component13() {
        return this.birthyearNeedsAgreement;
    }

    public final String component14() {
        return this.birthyear;
    }

    public final Boolean component15() {
        return this.birthdayNeedsAgreement;
    }

    public final String component16() {
        return this.birthday;
    }

    public final BirthdayType component17() {
        return this.birthdayType;
    }

    public final Boolean component18() {
        return this.genderNeedsAgreement;
    }

    public final Gender component19() {
        return this.gender;
    }

    public final Boolean component2() {
        return this.profileNicknameNeedsAgreement;
    }

    public final Boolean component20() {
        return this.ciNeedsAgreement;
    }

    public final String component21() {
        return this.ci;
    }

    public final Date component22() {
        return this.ciAuthenticatedAt;
    }

    public final Boolean component23() {
        return this.legalNameNeedsAgreement;
    }

    public final String component24() {
        return this.legalName;
    }

    public final Boolean component25() {
        return this.legalBirthDateNeedsAgreement;
    }

    public final String component26() {
        return this.legalBirthDate;
    }

    public final Boolean component27() {
        return this.legalGenderNeedsAgreement;
    }

    public final Gender component28() {
        return this.legalGender;
    }

    public final Boolean component29() {
        return this.phoneNumberNeedsAgreement;
    }

    public final Boolean component3() {
        return this.profileImageNeedsAgreement;
    }

    public final String component30() {
        return this.phoneNumber;
    }

    public final Boolean component31() {
        return this.isKoreanNeedsAgreement;
    }

    public final Boolean component32() {
        return this.isKorean;
    }

    public final Profile component4() {
        return this.profile;
    }

    public final Boolean component5() {
        return this.nameNeedsAgreement;
    }

    public final String component6() {
        return this.name;
    }

    public final Boolean component7() {
        return this.emailNeedsAgreement;
    }

    public final Boolean component8() {
        return this.isEmailValid;
    }

    public final Boolean component9() {
        return this.isEmailVerified;
    }

    public final Account copy(Boolean boolean0, Boolean boolean1, Boolean boolean2, Profile profile0, Boolean boolean3, String s, Boolean boolean4, Boolean boolean5, Boolean boolean6, String s1, Boolean boolean7, AgeRange ageRange0, Boolean boolean8, String s2, Boolean boolean9, String s3, BirthdayType birthdayType0, Boolean boolean10, Gender gender0, Boolean boolean11, String s4, Date date0, Boolean boolean12, String s5, Boolean boolean13, String s6, Boolean boolean14, Gender gender1, Boolean boolean15, String s7, Boolean boolean16, Boolean boolean17) {
        return new Account(boolean0, boolean1, boolean2, profile0, boolean3, s, boolean4, boolean5, boolean6, s1, boolean7, ageRange0, boolean8, s2, boolean9, s3, birthdayType0, boolean10, gender0, boolean11, s4, date0, boolean12, s5, boolean13, s6, boolean14, gender1, boolean15, s7, boolean16, boolean17);
    }

    public static Account copy$default(Account account0, Boolean boolean0, Boolean boolean1, Boolean boolean2, Profile profile0, Boolean boolean3, String s, Boolean boolean4, Boolean boolean5, Boolean boolean6, String s1, Boolean boolean7, AgeRange ageRange0, Boolean boolean8, String s2, Boolean boolean9, String s3, BirthdayType birthdayType0, Boolean boolean10, Gender gender0, Boolean boolean11, String s4, Date date0, Boolean boolean12, String s5, Boolean boolean13, String s6, Boolean boolean14, Gender gender1, Boolean boolean15, String s7, Boolean boolean16, Boolean boolean17, int v, Object object0) {
        Boolean boolean18 = (v & 1) == 0 ? boolean0 : account0.profileNeedsAgreement;
        Boolean boolean19 = (v & 2) == 0 ? boolean1 : account0.profileNicknameNeedsAgreement;
        Boolean boolean20 = (v & 4) == 0 ? boolean2 : account0.profileImageNeedsAgreement;
        Profile profile1 = (v & 8) == 0 ? profile0 : account0.profile;
        Boolean boolean21 = (v & 16) == 0 ? boolean3 : account0.nameNeedsAgreement;
        String s8 = (v & 0x20) == 0 ? s : account0.name;
        Boolean boolean22 = (v & 0x40) == 0 ? boolean4 : account0.emailNeedsAgreement;
        Boolean boolean23 = (v & 0x80) == 0 ? boolean5 : account0.isEmailValid;
        Boolean boolean24 = (v & 0x100) == 0 ? boolean6 : account0.isEmailVerified;
        String s9 = (v & 0x200) == 0 ? s1 : account0.email;
        Boolean boolean25 = (v & 0x400) == 0 ? boolean7 : account0.ageRangeNeedsAgreement;
        AgeRange ageRange1 = (v & 0x800) == 0 ? ageRange0 : account0.ageRange;
        Boolean boolean26 = (v & 0x1000) == 0 ? boolean8 : account0.birthyearNeedsAgreement;
        String s10 = (v & 0x2000) == 0 ? s2 : account0.birthyear;
        Boolean boolean27 = (v & 0x4000) == 0 ? boolean9 : account0.birthdayNeedsAgreement;
        String s11 = (v & 0x8000) == 0 ? s3 : account0.birthday;
        BirthdayType birthdayType1 = (v & 0x10000) == 0 ? birthdayType0 : account0.birthdayType;
        Boolean boolean28 = (v & 0x20000) == 0 ? boolean10 : account0.genderNeedsAgreement;
        Gender gender2 = (v & 0x40000) == 0 ? gender0 : account0.gender;
        Boolean boolean29 = (v & 0x80000) == 0 ? boolean11 : account0.ciNeedsAgreement;
        String s12 = (v & 0x100000) == 0 ? s4 : account0.ci;
        Date date1 = (v & 0x200000) == 0 ? date0 : account0.ciAuthenticatedAt;
        Boolean boolean30 = (v & 0x400000) == 0 ? boolean12 : account0.legalNameNeedsAgreement;
        String s13 = (v & 0x800000) == 0 ? s5 : account0.legalName;
        Boolean boolean31 = (v & 0x1000000) == 0 ? boolean13 : account0.legalBirthDateNeedsAgreement;
        String s14 = (v & 0x2000000) == 0 ? s6 : account0.legalBirthDate;
        Boolean boolean32 = (v & 0x4000000) == 0 ? boolean14 : account0.legalGenderNeedsAgreement;
        Gender gender3 = (v & 0x8000000) == 0 ? gender1 : account0.legalGender;
        Boolean boolean33 = (v & 0x10000000) == 0 ? boolean15 : account0.phoneNumberNeedsAgreement;
        String s15 = (v & 0x20000000) == 0 ? s7 : account0.phoneNumber;
        Boolean boolean34 = (v & 0x40000000) == 0 ? boolean16 : account0.isKoreanNeedsAgreement;
        return (v & 0x80000000) == 0 ? account0.copy(boolean18, boolean19, boolean20, profile1, boolean21, s8, boolean22, boolean23, boolean24, s9, boolean25, ageRange1, boolean26, s10, boolean27, s11, birthdayType1, boolean28, gender2, boolean29, s12, date1, boolean30, s13, boolean31, s14, boolean32, gender3, boolean33, s15, boolean34, boolean17) : account0.copy(boolean18, boolean19, boolean20, profile1, boolean21, s8, boolean22, boolean23, boolean24, s9, boolean25, ageRange1, boolean26, s10, boolean27, s11, birthdayType1, boolean28, gender2, boolean29, s12, date1, boolean30, s13, boolean31, s14, boolean32, gender3, boolean33, s15, boolean34, account0.isKorean);
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
        if(!(object0 instanceof Account)) {
            return false;
        }
        Account account0 = (Account)object0;
        if(!Intrinsics.areEqual(this.profileNeedsAgreement, account0.profileNeedsAgreement)) {
            return false;
        }
        if(!Intrinsics.areEqual(this.profileNicknameNeedsAgreement, account0.profileNicknameNeedsAgreement)) {
            return false;
        }
        if(!Intrinsics.areEqual(this.profileImageNeedsAgreement, account0.profileImageNeedsAgreement)) {
            return false;
        }
        if(!Intrinsics.areEqual(this.profile, account0.profile)) {
            return false;
        }
        if(!Intrinsics.areEqual(this.nameNeedsAgreement, account0.nameNeedsAgreement)) {
            return false;
        }
        if(!Intrinsics.areEqual(this.name, account0.name)) {
            return false;
        }
        if(!Intrinsics.areEqual(this.emailNeedsAgreement, account0.emailNeedsAgreement)) {
            return false;
        }
        if(!Intrinsics.areEqual(this.isEmailValid, account0.isEmailValid)) {
            return false;
        }
        if(!Intrinsics.areEqual(this.isEmailVerified, account0.isEmailVerified)) {
            return false;
        }
        if(!Intrinsics.areEqual(this.email, account0.email)) {
            return false;
        }
        if(!Intrinsics.areEqual(this.ageRangeNeedsAgreement, account0.ageRangeNeedsAgreement)) {
            return false;
        }
        if(this.ageRange != account0.ageRange) {
            return false;
        }
        if(!Intrinsics.areEqual(this.birthyearNeedsAgreement, account0.birthyearNeedsAgreement)) {
            return false;
        }
        if(!Intrinsics.areEqual(this.birthyear, account0.birthyear)) {
            return false;
        }
        if(!Intrinsics.areEqual(this.birthdayNeedsAgreement, account0.birthdayNeedsAgreement)) {
            return false;
        }
        if(!Intrinsics.areEqual(this.birthday, account0.birthday)) {
            return false;
        }
        if(this.birthdayType != account0.birthdayType) {
            return false;
        }
        if(!Intrinsics.areEqual(this.genderNeedsAgreement, account0.genderNeedsAgreement)) {
            return false;
        }
        if(this.gender != account0.gender) {
            return false;
        }
        if(!Intrinsics.areEqual(this.ciNeedsAgreement, account0.ciNeedsAgreement)) {
            return false;
        }
        if(!Intrinsics.areEqual(this.ci, account0.ci)) {
            return false;
        }
        if(!Intrinsics.areEqual(this.ciAuthenticatedAt, account0.ciAuthenticatedAt)) {
            return false;
        }
        if(!Intrinsics.areEqual(this.legalNameNeedsAgreement, account0.legalNameNeedsAgreement)) {
            return false;
        }
        if(!Intrinsics.areEqual(this.legalName, account0.legalName)) {
            return false;
        }
        if(!Intrinsics.areEqual(this.legalBirthDateNeedsAgreement, account0.legalBirthDateNeedsAgreement)) {
            return false;
        }
        if(!Intrinsics.areEqual(this.legalBirthDate, account0.legalBirthDate)) {
            return false;
        }
        if(!Intrinsics.areEqual(this.legalGenderNeedsAgreement, account0.legalGenderNeedsAgreement)) {
            return false;
        }
        if(this.legalGender != account0.legalGender) {
            return false;
        }
        if(!Intrinsics.areEqual(this.phoneNumberNeedsAgreement, account0.phoneNumberNeedsAgreement)) {
            return false;
        }
        if(!Intrinsics.areEqual(this.phoneNumber, account0.phoneNumber)) {
            return false;
        }
        return Intrinsics.areEqual(this.isKoreanNeedsAgreement, account0.isKoreanNeedsAgreement) ? Intrinsics.areEqual(this.isKorean, account0.isKorean) : false;
    }

    public final AgeRange getAgeRange() {
        return this.ageRange;
    }

    public final Boolean getAgeRangeNeedsAgreement() {
        return this.ageRangeNeedsAgreement;
    }

    public final String getBirthday() {
        return this.birthday;
    }

    public final Boolean getBirthdayNeedsAgreement() {
        return this.birthdayNeedsAgreement;
    }

    public final BirthdayType getBirthdayType() {
        return this.birthdayType;
    }

    public final String getBirthyear() {
        return this.birthyear;
    }

    public final Boolean getBirthyearNeedsAgreement() {
        return this.birthyearNeedsAgreement;
    }

    public final String getCi() {
        return this.ci;
    }

    public final Date getCiAuthenticatedAt() {
        return this.ciAuthenticatedAt;
    }

    public final Boolean getCiNeedsAgreement() {
        return this.ciNeedsAgreement;
    }

    public final String getEmail() {
        return this.email;
    }

    public final Boolean getEmailNeedsAgreement() {
        return this.emailNeedsAgreement;
    }

    public final Gender getGender() {
        return this.gender;
    }

    public final Boolean getGenderNeedsAgreement() {
        return this.genderNeedsAgreement;
    }

    public final String getLegalBirthDate() {
        return this.legalBirthDate;
    }

    public final Boolean getLegalBirthDateNeedsAgreement() {
        return this.legalBirthDateNeedsAgreement;
    }

    public final Gender getLegalGender() {
        return this.legalGender;
    }

    public final Boolean getLegalGenderNeedsAgreement() {
        return this.legalGenderNeedsAgreement;
    }

    public final String getLegalName() {
        return this.legalName;
    }

    public final Boolean getLegalNameNeedsAgreement() {
        return this.legalNameNeedsAgreement;
    }

    public final String getName() {
        return this.name;
    }

    public final Boolean getNameNeedsAgreement() {
        return this.nameNeedsAgreement;
    }

    public final String getPhoneNumber() {
        return this.phoneNumber;
    }

    public final Boolean getPhoneNumberNeedsAgreement() {
        return this.phoneNumberNeedsAgreement;
    }

    public final Profile getProfile() {
        return this.profile;
    }

    public final Boolean getProfileImageNeedsAgreement() {
        return this.profileImageNeedsAgreement;
    }

    public final Boolean getProfileNeedsAgreement() {
        return this.profileNeedsAgreement;
    }

    public final Boolean getProfileNicknameNeedsAgreement() {
        return this.profileNicknameNeedsAgreement;
    }

    @Override
    public int hashCode() {
        int v = 0;
        int v1 = this.profileNeedsAgreement == null ? 0 : this.profileNeedsAgreement.hashCode();
        int v2 = this.profileNicknameNeedsAgreement == null ? 0 : this.profileNicknameNeedsAgreement.hashCode();
        int v3 = this.profileImageNeedsAgreement == null ? 0 : this.profileImageNeedsAgreement.hashCode();
        int v4 = this.profile == null ? 0 : this.profile.hashCode();
        int v5 = this.nameNeedsAgreement == null ? 0 : this.nameNeedsAgreement.hashCode();
        int v6 = this.name == null ? 0 : this.name.hashCode();
        int v7 = this.emailNeedsAgreement == null ? 0 : this.emailNeedsAgreement.hashCode();
        int v8 = this.isEmailValid == null ? 0 : this.isEmailValid.hashCode();
        int v9 = this.isEmailVerified == null ? 0 : this.isEmailVerified.hashCode();
        int v10 = this.email == null ? 0 : this.email.hashCode();
        int v11 = this.ageRangeNeedsAgreement == null ? 0 : this.ageRangeNeedsAgreement.hashCode();
        int v12 = this.ageRange == null ? 0 : this.ageRange.hashCode();
        int v13 = this.birthyearNeedsAgreement == null ? 0 : this.birthyearNeedsAgreement.hashCode();
        int v14 = this.birthyear == null ? 0 : this.birthyear.hashCode();
        int v15 = this.birthdayNeedsAgreement == null ? 0 : this.birthdayNeedsAgreement.hashCode();
        int v16 = this.birthday == null ? 0 : this.birthday.hashCode();
        int v17 = this.birthdayType == null ? 0 : this.birthdayType.hashCode();
        int v18 = this.genderNeedsAgreement == null ? 0 : this.genderNeedsAgreement.hashCode();
        int v19 = this.gender == null ? 0 : this.gender.hashCode();
        int v20 = this.ciNeedsAgreement == null ? 0 : this.ciNeedsAgreement.hashCode();
        int v21 = this.ci == null ? 0 : this.ci.hashCode();
        int v22 = this.ciAuthenticatedAt == null ? 0 : this.ciAuthenticatedAt.hashCode();
        int v23 = this.legalNameNeedsAgreement == null ? 0 : this.legalNameNeedsAgreement.hashCode();
        int v24 = this.legalName == null ? 0 : this.legalName.hashCode();
        int v25 = this.legalBirthDateNeedsAgreement == null ? 0 : this.legalBirthDateNeedsAgreement.hashCode();
        int v26 = this.legalBirthDate == null ? 0 : this.legalBirthDate.hashCode();
        int v27 = this.legalGenderNeedsAgreement == null ? 0 : this.legalGenderNeedsAgreement.hashCode();
        int v28 = this.legalGender == null ? 0 : this.legalGender.hashCode();
        int v29 = this.phoneNumberNeedsAgreement == null ? 0 : this.phoneNumberNeedsAgreement.hashCode();
        int v30 = this.phoneNumber == null ? 0 : this.phoneNumber.hashCode();
        int v31 = this.isKoreanNeedsAgreement == null ? 0 : this.isKoreanNeedsAgreement.hashCode();
        Boolean boolean0 = this.isKorean;
        if(boolean0 != null) {
            v = boolean0.hashCode();
        }
        return ((((((((((((((((((((((((((((((v1 * 0x1F + v2) * 0x1F + v3) * 0x1F + v4) * 0x1F + v5) * 0x1F + v6) * 0x1F + v7) * 0x1F + v8) * 0x1F + v9) * 0x1F + v10) * 0x1F + v11) * 0x1F + v12) * 0x1F + v13) * 0x1F + v14) * 0x1F + v15) * 0x1F + v16) * 0x1F + v17) * 0x1F + v18) * 0x1F + v19) * 0x1F + v20) * 0x1F + v21) * 0x1F + v22) * 0x1F + v23) * 0x1F + v24) * 0x1F + v25) * 0x1F + v26) * 0x1F + v27) * 0x1F + v28) * 0x1F + v29) * 0x1F + v30) * 0x1F + v31) * 0x1F + v;
    }

    public final Boolean isEmailValid() {
        return this.isEmailValid;
    }

    public final Boolean isEmailVerified() {
        return this.isEmailVerified;
    }

    public final Boolean isKorean() {
        return this.isKorean;
    }

    public final Boolean isKoreanNeedsAgreement() {
        return this.isKoreanNeedsAgreement;
    }

    @Override
    public String toString() {
        return "Account(profileNeedsAgreement=" + this.profileNeedsAgreement + ", profileNicknameNeedsAgreement=" + this.profileNicknameNeedsAgreement + ", profileImageNeedsAgreement=" + this.profileImageNeedsAgreement + ", profile=" + this.profile + ", nameNeedsAgreement=" + this.nameNeedsAgreement + ", name=" + this.name + ", emailNeedsAgreement=" + this.emailNeedsAgreement + ", isEmailValid=" + this.isEmailValid + ", isEmailVerified=" + this.isEmailVerified + ", email=" + this.email + ", ageRangeNeedsAgreement=" + this.ageRangeNeedsAgreement + ", ageRange=" + this.ageRange + ", birthyearNeedsAgreement=" + this.birthyearNeedsAgreement + ", birthyear=" + this.birthyear + ", birthdayNeedsAgreement=" + this.birthdayNeedsAgreement + ", birthday=" + this.birthday + ", birthdayType=" + this.birthdayType + ", genderNeedsAgreement=" + this.genderNeedsAgreement + ", gender=" + this.gender + ", ciNeedsAgreement=" + this.ciNeedsAgreement + ", ci=" + this.ci + ", ciAuthenticatedAt=" + this.ciAuthenticatedAt + ", legalNameNeedsAgreement=" + this.legalNameNeedsAgreement + ", legalName=" + this.legalName + ", legalBirthDateNeedsAgreement=" + this.legalBirthDateNeedsAgreement + ", legalBirthDate=" + this.legalBirthDate + ", legalGenderNeedsAgreement=" + this.legalGenderNeedsAgreement + ", legalGender=" + this.legalGender + ", phoneNumberNeedsAgreement=" + this.phoneNumberNeedsAgreement + ", phoneNumber=" + this.phoneNumber + ", isKoreanNeedsAgreement=" + this.isKoreanNeedsAgreement + ", isKorean=" + this.isKorean + ')';
    }

    @Override  // android.os.Parcelable
    public void writeToParcel(Parcel parcel0, int v) {
        Intrinsics.checkNotNullParameter(parcel0, "out");
        Boolean boolean0 = this.profileNeedsAgreement;
        if(boolean0 == null) {
            parcel0.writeInt(0);
        }
        else {
            parcel0.writeInt(1);
            parcel0.writeInt(((int)boolean0.booleanValue()));
        }
        Boolean boolean1 = this.profileNicknameNeedsAgreement;
        if(boolean1 == null) {
            parcel0.writeInt(0);
        }
        else {
            parcel0.writeInt(1);
            parcel0.writeInt(((int)boolean1.booleanValue()));
        }
        Boolean boolean2 = this.profileImageNeedsAgreement;
        if(boolean2 == null) {
            parcel0.writeInt(0);
        }
        else {
            parcel0.writeInt(1);
            parcel0.writeInt(((int)boolean2.booleanValue()));
        }
        Profile profile0 = this.profile;
        if(profile0 == null) {
            parcel0.writeInt(0);
        }
        else {
            parcel0.writeInt(1);
            profile0.writeToParcel(parcel0, v);
        }
        Boolean boolean3 = this.nameNeedsAgreement;
        if(boolean3 == null) {
            parcel0.writeInt(0);
        }
        else {
            parcel0.writeInt(1);
            parcel0.writeInt(((int)boolean3.booleanValue()));
        }
        parcel0.writeString(this.name);
        Boolean boolean4 = this.emailNeedsAgreement;
        if(boolean4 == null) {
            parcel0.writeInt(0);
        }
        else {
            parcel0.writeInt(1);
            parcel0.writeInt(((int)boolean4.booleanValue()));
        }
        Boolean boolean5 = this.isEmailValid;
        if(boolean5 == null) {
            parcel0.writeInt(0);
        }
        else {
            parcel0.writeInt(1);
            parcel0.writeInt(((int)boolean5.booleanValue()));
        }
        Boolean boolean6 = this.isEmailVerified;
        if(boolean6 == null) {
            parcel0.writeInt(0);
        }
        else {
            parcel0.writeInt(1);
            parcel0.writeInt(((int)boolean6.booleanValue()));
        }
        parcel0.writeString(this.email);
        Boolean boolean7 = this.ageRangeNeedsAgreement;
        if(boolean7 == null) {
            parcel0.writeInt(0);
        }
        else {
            parcel0.writeInt(1);
            parcel0.writeInt(((int)boolean7.booleanValue()));
        }
        AgeRange ageRange0 = this.ageRange;
        if(ageRange0 == null) {
            parcel0.writeInt(0);
        }
        else {
            parcel0.writeInt(1);
            parcel0.writeString(ageRange0.name());
        }
        Boolean boolean8 = this.birthyearNeedsAgreement;
        if(boolean8 == null) {
            parcel0.writeInt(0);
        }
        else {
            parcel0.writeInt(1);
            parcel0.writeInt(((int)boolean8.booleanValue()));
        }
        parcel0.writeString(this.birthyear);
        Boolean boolean9 = this.birthdayNeedsAgreement;
        if(boolean9 == null) {
            parcel0.writeInt(0);
        }
        else {
            parcel0.writeInt(1);
            parcel0.writeInt(((int)boolean9.booleanValue()));
        }
        parcel0.writeString(this.birthday);
        BirthdayType birthdayType0 = this.birthdayType;
        if(birthdayType0 == null) {
            parcel0.writeInt(0);
        }
        else {
            parcel0.writeInt(1);
            parcel0.writeString(birthdayType0.name());
        }
        Boolean boolean10 = this.genderNeedsAgreement;
        if(boolean10 == null) {
            parcel0.writeInt(0);
        }
        else {
            parcel0.writeInt(1);
            parcel0.writeInt(((int)boolean10.booleanValue()));
        }
        Gender gender0 = this.gender;
        if(gender0 == null) {
            parcel0.writeInt(0);
        }
        else {
            parcel0.writeInt(1);
            parcel0.writeString(gender0.name());
        }
        Boolean boolean11 = this.ciNeedsAgreement;
        if(boolean11 == null) {
            parcel0.writeInt(0);
        }
        else {
            parcel0.writeInt(1);
            parcel0.writeInt(((int)boolean11.booleanValue()));
        }
        parcel0.writeString(this.ci);
        parcel0.writeSerializable(this.ciAuthenticatedAt);
        Boolean boolean12 = this.legalNameNeedsAgreement;
        if(boolean12 == null) {
            parcel0.writeInt(0);
        }
        else {
            parcel0.writeInt(1);
            parcel0.writeInt(((int)boolean12.booleanValue()));
        }
        parcel0.writeString(this.legalName);
        Boolean boolean13 = this.legalBirthDateNeedsAgreement;
        if(boolean13 == null) {
            parcel0.writeInt(0);
        }
        else {
            parcel0.writeInt(1);
            parcel0.writeInt(((int)boolean13.booleanValue()));
        }
        parcel0.writeString(this.legalBirthDate);
        Boolean boolean14 = this.legalGenderNeedsAgreement;
        if(boolean14 == null) {
            parcel0.writeInt(0);
        }
        else {
            parcel0.writeInt(1);
            parcel0.writeInt(((int)boolean14.booleanValue()));
        }
        Gender gender1 = this.legalGender;
        if(gender1 == null) {
            parcel0.writeInt(0);
        }
        else {
            parcel0.writeInt(1);
            parcel0.writeString(gender1.name());
        }
        Boolean boolean15 = this.phoneNumberNeedsAgreement;
        if(boolean15 == null) {
            parcel0.writeInt(0);
        }
        else {
            parcel0.writeInt(1);
            parcel0.writeInt(((int)boolean15.booleanValue()));
        }
        parcel0.writeString(this.phoneNumber);
        Boolean boolean16 = this.isKoreanNeedsAgreement;
        if(boolean16 == null) {
            parcel0.writeInt(0);
        }
        else {
            parcel0.writeInt(1);
            parcel0.writeInt(((int)boolean16.booleanValue()));
        }
        Boolean boolean17 = this.isKorean;
        if(boolean17 == null) {
            parcel0.writeInt(0);
            return;
        }
        parcel0.writeInt(1);
        parcel0.writeInt(((int)boolean17.booleanValue()));
    }
}

