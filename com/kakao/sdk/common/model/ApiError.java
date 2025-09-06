package com.kakao.sdk.common.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.Parcelable;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0087\b\u0018\u0000 !2\u00020\u00012\u00020\u0002:\u0001!B\u001D\u0012\u0006\u0010\u0003\u001A\u00020\u0004\u0012\u0006\u0010\u0005\u001A\u00020\u0006\u0012\u0006\u0010\u0007\u001A\u00020\b¢\u0006\u0002\u0010\tJ\t\u0010\u0010\u001A\u00020\u0004HÆ\u0003J\t\u0010\u0011\u001A\u00020\u0006HÆ\u0003J\t\u0010\u0012\u001A\u00020\bHÆ\u0003J\'\u0010\u0013\u001A\u00020\u00002\b\b\u0002\u0010\u0003\u001A\u00020\u00042\b\b\u0002\u0010\u0005\u001A\u00020\u00062\b\b\u0002\u0010\u0007\u001A\u00020\bHÆ\u0001J\t\u0010\u0014\u001A\u00020\u0004HÖ\u0001J\u0013\u0010\u0015\u001A\u00020\u00162\b\u0010\u0017\u001A\u0004\u0018\u00010\u0018HÖ\u0003J\t\u0010\u0019\u001A\u00020\u0004HÖ\u0001J\t\u0010\u001A\u001A\u00020\u001BHÖ\u0001J\u0019\u0010\u001C\u001A\u00020\u001D2\u0006\u0010\u001E\u001A\u00020\u001F2\u0006\u0010 \u001A\u00020\u0004HÖ\u0001R\u0011\u0010\u0005\u001A\u00020\u0006¢\u0006\b\n\u0000\u001A\u0004\b\n\u0010\u000BR\u0011\u0010\u0007\u001A\u00020\b¢\u0006\b\n\u0000\u001A\u0004\b\f\u0010\rR\u0011\u0010\u0003\u001A\u00020\u0004¢\u0006\b\n\u0000\u001A\u0004\b\u000E\u0010\u000F¨\u0006\""}, d2 = {"Lcom/kakao/sdk/common/model/ApiError;", "Lcom/kakao/sdk/common/model/KakaoSdkError;", "Landroid/os/Parcelable;", "statusCode", "", "reason", "Lcom/kakao/sdk/common/model/ApiErrorCause;", "response", "Lcom/kakao/sdk/common/model/ApiErrorResponse;", "(ILcom/kakao/sdk/common/model/ApiErrorCause;Lcom/kakao/sdk/common/model/ApiErrorResponse;)V", "getReason", "()Lcom/kakao/sdk/common/model/ApiErrorCause;", "getResponse", "()Lcom/kakao/sdk/common/model/ApiErrorResponse;", "getStatusCode", "()I", "component1", "component2", "component3", "copy", "describeContents", "equals", "", "other", "", "hashCode", "toString", "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "Companion", "common_release"}, k = 1, mv = {1, 5, 1}, xi = 0x30)
public final class ApiError extends KakaoSdkError implements Parcelable {
    @Metadata(d1 = {"\u0000\u001C\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000E\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\u0003\u001A\u00020\u00042\f\u0010\u0005\u001A\b\u0012\u0004\u0012\u00020\u00070\u0006¨\u0006\b"}, d2 = {"Lcom/kakao/sdk/common/model/ApiError$Companion;", "", "()V", "fromScopes", "Lcom/kakao/sdk/common/model/ApiError;", "scopes", "", "", "common_release"}, k = 1, mv = {1, 5, 1}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final ApiError fromScopes(List list0) {
            Intrinsics.checkNotNullParameter(list0, "scopes");
            ApiErrorResponse apiErrorResponse0 = new ApiErrorResponse(ApiErrorCause.InsufficientScope.getErrorCode(), "", null, list0, null, 20, null);
            return new ApiError(403, ApiErrorCause.InsufficientScope, apiErrorResponse0);
        }
    }

    @Metadata(k = 3, mv = {1, 5, 1}, xi = 0x30)
    public static final class Creator implements Parcelable.Creator {
        public final ApiError createFromParcel(Parcel parcel0) {
            Intrinsics.checkNotNullParameter(parcel0, "parcel");
            return new ApiError(parcel0.readInt(), ApiErrorCause.valueOf(parcel0.readString()), ((ApiErrorResponse)ApiErrorResponse.CREATOR.createFromParcel(parcel0)));
        }

        @Override  // android.os.Parcelable$Creator
        public Object createFromParcel(Parcel parcel0) {
            return this.createFromParcel(parcel0);
        }

        public final ApiError[] newArray(int v) {
            return new ApiError[v];
        }

        @Override  // android.os.Parcelable$Creator
        public Object[] newArray(int v) {
            return this.newArray(v);
        }
    }

    public static final Parcelable.Creator CREATOR;
    public static final Companion Companion;
    private final ApiErrorCause reason;
    private final ApiErrorResponse response;
    private final int statusCode;

    static {
        ApiError.Companion = new Companion(null);
        ApiError.CREATOR = new Creator();
    }

    public ApiError(int v, ApiErrorCause apiErrorCause0, ApiErrorResponse apiErrorResponse0) {
        Intrinsics.checkNotNullParameter(apiErrorCause0, "reason");
        Intrinsics.checkNotNullParameter(apiErrorResponse0, "response");
        super(apiErrorResponse0.getMsg(), null);
        this.statusCode = v;
        this.reason = apiErrorCause0;
        this.response = apiErrorResponse0;
    }

    public final int component1() {
        return this.statusCode;
    }

    public final ApiErrorCause component2() {
        return this.reason;
    }

    public final ApiErrorResponse component3() {
        return this.response;
    }

    public final ApiError copy(int v, ApiErrorCause apiErrorCause0, ApiErrorResponse apiErrorResponse0) {
        Intrinsics.checkNotNullParameter(apiErrorCause0, "reason");
        Intrinsics.checkNotNullParameter(apiErrorResponse0, "response");
        return new ApiError(v, apiErrorCause0, apiErrorResponse0);
    }

    public static ApiError copy$default(ApiError apiError0, int v, ApiErrorCause apiErrorCause0, ApiErrorResponse apiErrorResponse0, int v1, Object object0) {
        if((v1 & 1) != 0) {
            v = apiError0.statusCode;
        }
        if((v1 & 2) != 0) {
            apiErrorCause0 = apiError0.reason;
        }
        if((v1 & 4) != 0) {
            apiErrorResponse0 = apiError0.response;
        }
        return apiError0.copy(v, apiErrorCause0, apiErrorResponse0);
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
        if(!(object0 instanceof ApiError)) {
            return false;
        }
        if(this.statusCode != ((ApiError)object0).statusCode) {
            return false;
        }
        return this.reason == ((ApiError)object0).reason ? Intrinsics.areEqual(this.response, ((ApiError)object0).response) : false;
    }

    public final ApiErrorCause getReason() {
        return this.reason;
    }

    public final ApiErrorResponse getResponse() {
        return this.response;
    }

    public final int getStatusCode() {
        return this.statusCode;
    }

    @Override
    public int hashCode() {
        return (this.statusCode * 0x1F + this.reason.hashCode()) * 0x1F + this.response.hashCode();
    }

    @Override
    public String toString() {
        return "ApiError(statusCode=" + this.statusCode + ", reason=" + this.reason + ", response=" + this.response + ')';
    }

    @Override  // android.os.Parcelable
    public void writeToParcel(Parcel parcel0, int v) {
        Intrinsics.checkNotNullParameter(parcel0, "out");
        parcel0.writeInt(this.statusCode);
        parcel0.writeString(this.reason.name());
        this.response.writeToParcel(parcel0, v);
    }
}

