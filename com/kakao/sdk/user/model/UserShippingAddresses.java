package com.kakao.sdk.user.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000E\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B)\u0012\b\u0010\u0002\u001A\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001A\u0004\u0018\u00010\u0005\u0012\u000E\u0010\u0006\u001A\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007¢\u0006\u0002\u0010\tJ\u0010\u0010\u0012\u001A\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0010J\u0010\u0010\u0013\u001A\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u000BJ\u0011\u0010\u0014\u001A\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007HÆ\u0003J8\u0010\u0015\u001A\u00020\u00002\n\b\u0002\u0010\u0002\u001A\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001A\u0004\u0018\u00010\u00052\u0010\b\u0002\u0010\u0006\u001A\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007HÆ\u0001¢\u0006\u0002\u0010\u0016J\u0013\u0010\u0017\u001A\u00020\u00052\b\u0010\u0018\u001A\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001A\u00020\u001AHÖ\u0001J\t\u0010\u001B\u001A\u00020\u001CHÖ\u0001R\u001A\u0010\u0004\u001A\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\f\u001A\u0004\b\n\u0010\u000BR\u0019\u0010\u0006\u001A\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007¢\u0006\b\n\u0000\u001A\u0004\b\r\u0010\u000ER\u0015\u0010\u0002\u001A\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u0011\u001A\u0004\b\u000F\u0010\u0010¨\u0006\u001D"}, d2 = {"Lcom/kakao/sdk/user/model/UserShippingAddresses;", "", "userId", "", "needsAgreement", "", "shippingAddresses", "", "Lcom/kakao/sdk/user/model/ShippingAddress;", "(Ljava/lang/Long;Ljava/lang/Boolean;Ljava/util/List;)V", "getNeedsAgreement", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getShippingAddresses", "()Ljava/util/List;", "getUserId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "component1", "component2", "component3", "copy", "(Ljava/lang/Long;Ljava/lang/Boolean;Ljava/util/List;)Lcom/kakao/sdk/user/model/UserShippingAddresses;", "equals", "other", "hashCode", "", "toString", "", "user_release"}, k = 1, mv = {1, 5, 1}, xi = 0x30)
public final class UserShippingAddresses {
    @SerializedName("shipping_addresses_needs_agreement")
    private final Boolean needsAgreement;
    private final List shippingAddresses;
    private final Long userId;

    public UserShippingAddresses(Long long0, Boolean boolean0, List list0) {
        this.userId = long0;
        this.needsAgreement = boolean0;
        this.shippingAddresses = list0;
    }

    public final Long component1() {
        return this.userId;
    }

    public final Boolean component2() {
        return this.needsAgreement;
    }

    public final List component3() {
        return this.shippingAddresses;
    }

    public final UserShippingAddresses copy(Long long0, Boolean boolean0, List list0) {
        return new UserShippingAddresses(long0, boolean0, list0);
    }

    public static UserShippingAddresses copy$default(UserShippingAddresses userShippingAddresses0, Long long0, Boolean boolean0, List list0, int v, Object object0) {
        if((v & 1) != 0) {
            long0 = userShippingAddresses0.userId;
        }
        if((v & 2) != 0) {
            boolean0 = userShippingAddresses0.needsAgreement;
        }
        if((v & 4) != 0) {
            list0 = userShippingAddresses0.shippingAddresses;
        }
        return userShippingAddresses0.copy(long0, boolean0, list0);
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(!(object0 instanceof UserShippingAddresses)) {
            return false;
        }
        if(!Intrinsics.areEqual(this.userId, ((UserShippingAddresses)object0).userId)) {
            return false;
        }
        return Intrinsics.areEqual(this.needsAgreement, ((UserShippingAddresses)object0).needsAgreement) ? Intrinsics.areEqual(this.shippingAddresses, ((UserShippingAddresses)object0).shippingAddresses) : false;
    }

    public final Boolean getNeedsAgreement() {
        return this.needsAgreement;
    }

    public final List getShippingAddresses() {
        return this.shippingAddresses;
    }

    public final Long getUserId() {
        return this.userId;
    }

    @Override
    public int hashCode() {
        int v = 0;
        int v1 = this.userId == null ? 0 : this.userId.hashCode();
        int v2 = this.needsAgreement == null ? 0 : this.needsAgreement.hashCode();
        List list0 = this.shippingAddresses;
        if(list0 != null) {
            v = list0.hashCode();
        }
        return (v1 * 0x1F + v2) * 0x1F + v;
    }

    @Override
    public String toString() {
        return "UserShippingAddresses(userId=" + this.userId + ", needsAgreement=" + this.needsAgreement + ", shippingAddresses=" + this.shippingAddresses + ')';
    }
}

