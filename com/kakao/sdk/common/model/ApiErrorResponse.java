package com.kakao.sdk.common.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.Parcelable;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0012\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001BC\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001A\u0004\u0018\u00010\u0005\u0012\u000E\u0010\u0007\u001A\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\b\u0012\u0010\b\u0002\u0010\t\u001A\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\b¢\u0006\u0002\u0010\nJ\t\u0010\u0013\u001A\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001A\u00020\u0005HÆ\u0003J\u000B\u0010\u0015\u001A\u0004\u0018\u00010\u0005HÆ\u0003J\u0011\u0010\u0016\u001A\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\bHÆ\u0003J\u0011\u0010\u0017\u001A\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\bHÆ\u0003JM\u0010\u0018\u001A\u00020\u00002\b\b\u0002\u0010\u0002\u001A\u00020\u00032\b\b\u0002\u0010\u0004\u001A\u00020\u00052\n\b\u0002\u0010\u0006\u001A\u0004\u0018\u00010\u00052\u0010\b\u0002\u0010\u0007\u001A\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\b2\u0010\b\u0002\u0010\t\u001A\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\bHÆ\u0001J\t\u0010\u0019\u001A\u00020\u0003HÖ\u0001J\u0013\u0010\u001A\u001A\u00020\u001B2\b\u0010\u001C\u001A\u0004\u0018\u00010\u001DHÖ\u0003J\t\u0010\u001E\u001A\u00020\u0003HÖ\u0001J\t\u0010\u001F\u001A\u00020\u0005HÖ\u0001J\u0019\u0010 \u001A\u00020!2\u0006\u0010\"\u001A\u00020#2\u0006\u0010$\u001A\u00020\u0003HÖ\u0001R\u0019\u0010\t\u001A\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\b¢\u0006\b\n\u0000\u001A\u0004\b\u000B\u0010\fR\u0013\u0010\u0006\u001A\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001A\u0004\b\r\u0010\u000ER\u0011\u0010\u0002\u001A\u00020\u0003¢\u0006\b\n\u0000\u001A\u0004\b\u000F\u0010\u0010R\u0011\u0010\u0004\u001A\u00020\u0005¢\u0006\b\n\u0000\u001A\u0004\b\u0011\u0010\u000ER\u0019\u0010\u0007\u001A\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\b¢\u0006\b\n\u0000\u001A\u0004\b\u0012\u0010\f¨\u0006%"}, d2 = {"Lcom/kakao/sdk/common/model/ApiErrorResponse;", "Landroid/os/Parcelable;", "code", "", "msg", "", "apiType", "requiredScopes", "", "allowedScopes", "(ILjava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/util/List;)V", "getAllowedScopes", "()Ljava/util/List;", "getApiType", "()Ljava/lang/String;", "getCode", "()I", "getMsg", "getRequiredScopes", "component1", "component2", "component3", "component4", "component5", "copy", "describeContents", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "common_release"}, k = 1, mv = {1, 5, 1}, xi = 0x30)
public final class ApiErrorResponse implements Parcelable {
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 0x30)
    public static final class Creator implements Parcelable.Creator {
        public final ApiErrorResponse createFromParcel(Parcel parcel0) {
            Intrinsics.checkNotNullParameter(parcel0, "parcel");
            return new ApiErrorResponse(parcel0.readInt(), parcel0.readString(), parcel0.readString(), parcel0.createStringArrayList(), parcel0.createStringArrayList());
        }

        @Override  // android.os.Parcelable$Creator
        public Object createFromParcel(Parcel parcel0) {
            return this.createFromParcel(parcel0);
        }

        public final ApiErrorResponse[] newArray(int v) {
            return new ApiErrorResponse[v];
        }

        @Override  // android.os.Parcelable$Creator
        public Object[] newArray(int v) {
            return this.newArray(v);
        }
    }

    public static final Parcelable.Creator CREATOR;
    private final List allowedScopes;
    private final String apiType;
    private final int code;
    private final String msg;
    private final List requiredScopes;

    static {
        ApiErrorResponse.CREATOR = new Creator();
    }

    public ApiErrorResponse(int v, String s, String s1, List list0, List list1) {
        Intrinsics.checkNotNullParameter(s, "msg");
        super();
        this.code = v;
        this.msg = s;
        this.apiType = s1;
        this.requiredScopes = list0;
        this.allowedScopes = list1;
    }

    public ApiErrorResponse(int v, String s, String s1, List list0, List list1, int v1, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v1 & 4) != 0) {
            s1 = null;
        }
        this(v, s, s1, list0, ((v1 & 16) == 0 ? list1 : null));
    }

    public final int component1() {
        return this.code;
    }

    public final String component2() {
        return this.msg;
    }

    public final String component3() {
        return this.apiType;
    }

    public final List component4() {
        return this.requiredScopes;
    }

    public final List component5() {
        return this.allowedScopes;
    }

    public final ApiErrorResponse copy(int v, String s, String s1, List list0, List list1) {
        Intrinsics.checkNotNullParameter(s, "msg");
        return new ApiErrorResponse(v, s, s1, list0, list1);
    }

    public static ApiErrorResponse copy$default(ApiErrorResponse apiErrorResponse0, int v, String s, String s1, List list0, List list1, int v1, Object object0) {
        if((v1 & 1) != 0) {
            v = apiErrorResponse0.code;
        }
        if((v1 & 2) != 0) {
            s = apiErrorResponse0.msg;
        }
        if((v1 & 4) != 0) {
            s1 = apiErrorResponse0.apiType;
        }
        if((v1 & 8) != 0) {
            list0 = apiErrorResponse0.requiredScopes;
        }
        if((v1 & 16) != 0) {
            list1 = apiErrorResponse0.allowedScopes;
        }
        return apiErrorResponse0.copy(v, s, s1, list0, list1);
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
        if(!(object0 instanceof ApiErrorResponse)) {
            return false;
        }
        if(this.code != ((ApiErrorResponse)object0).code) {
            return false;
        }
        if(!Intrinsics.areEqual(this.msg, ((ApiErrorResponse)object0).msg)) {
            return false;
        }
        if(!Intrinsics.areEqual(this.apiType, ((ApiErrorResponse)object0).apiType)) {
            return false;
        }
        return Intrinsics.areEqual(this.requiredScopes, ((ApiErrorResponse)object0).requiredScopes) ? Intrinsics.areEqual(this.allowedScopes, ((ApiErrorResponse)object0).allowedScopes) : false;
    }

    public final List getAllowedScopes() {
        return this.allowedScopes;
    }

    public final String getApiType() {
        return this.apiType;
    }

    public final int getCode() {
        return this.code;
    }

    public final String getMsg() {
        return this.msg;
    }

    public final List getRequiredScopes() {
        return this.requiredScopes;
    }

    @Override
    public int hashCode() {
        int v = (this.code * 0x1F + this.msg.hashCode()) * 0x1F;
        int v1 = 0;
        int v2 = this.apiType == null ? 0 : this.apiType.hashCode();
        int v3 = this.requiredScopes == null ? 0 : this.requiredScopes.hashCode();
        List list0 = this.allowedScopes;
        if(list0 != null) {
            v1 = list0.hashCode();
        }
        return ((v + v2) * 0x1F + v3) * 0x1F + v1;
    }

    @Override
    public String toString() {
        return "ApiErrorResponse(code=" + this.code + ", msg=" + this.msg + ", apiType=" + this.apiType + ", requiredScopes=" + this.requiredScopes + ", allowedScopes=" + this.allowedScopes + ')';
    }

    @Override  // android.os.Parcelable
    public void writeToParcel(Parcel parcel0, int v) {
        Intrinsics.checkNotNullParameter(parcel0, "out");
        parcel0.writeInt(this.code);
        parcel0.writeString(this.msg);
        parcel0.writeString(this.apiType);
        parcel0.writeStringList(this.requiredScopes);
        parcel0.writeStringList(this.allowedScopes);
    }
}

