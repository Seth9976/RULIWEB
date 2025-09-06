package com.kakao.sdk.user.model;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000E\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B/\u0012\b\u0010\u0002\u001A\u0004\u0018\u00010\u0003\u0012\u000E\u0010\u0004\u001A\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005\u0012\u000E\u0010\u0007\u001A\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0005¢\u0006\u0002\u0010\tJ\u0010\u0010\u0010\u001A\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u000EJ\u0011\u0010\u0011\u001A\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005HÆ\u0003J\u0011\u0010\u0012\u001A\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0005HÆ\u0003J>\u0010\u0013\u001A\u00020\u00002\n\b\u0002\u0010\u0002\u001A\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u0004\u001A\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00052\u0010\b\u0002\u0010\u0007\u001A\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0005HÆ\u0001¢\u0006\u0002\u0010\u0014J\u0013\u0010\u0015\u001A\u00020\u00162\b\u0010\u0017\u001A\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0018\u001A\u00020\u0019HÖ\u0001J\t\u0010\u001A\u001A\u00020\u001BHÖ\u0001R\u0019\u0010\u0004\u001A\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005¢\u0006\b\n\u0000\u001A\u0004\b\n\u0010\u000BR\u0019\u0010\u0007\u001A\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0005¢\u0006\b\n\u0000\u001A\u0004\b\f\u0010\u000BR\u0015\u0010\u0002\u001A\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u000F\u001A\u0004\b\r\u0010\u000E¨\u0006\u001C"}, d2 = {"Lcom/kakao/sdk/user/model/UserServiceTerms;", "", "userId", "", "allowedServiceTerms", "", "Lcom/kakao/sdk/user/model/ServiceTerms;", "appServiceTerms", "Lcom/kakao/sdk/user/model/AppServiceTerms;", "(Ljava/lang/Long;Ljava/util/List;Ljava/util/List;)V", "getAllowedServiceTerms", "()Ljava/util/List;", "getAppServiceTerms", "getUserId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "component1", "component2", "component3", "copy", "(Ljava/lang/Long;Ljava/util/List;Ljava/util/List;)Lcom/kakao/sdk/user/model/UserServiceTerms;", "equals", "", "other", "hashCode", "", "toString", "", "user_release"}, k = 1, mv = {1, 5, 1}, xi = 0x30)
public final class UserServiceTerms {
    private final List allowedServiceTerms;
    private final List appServiceTerms;
    private final Long userId;

    public UserServiceTerms(Long long0, List list0, List list1) {
        this.userId = long0;
        this.allowedServiceTerms = list0;
        this.appServiceTerms = list1;
    }

    public final Long component1() {
        return this.userId;
    }

    public final List component2() {
        return this.allowedServiceTerms;
    }

    public final List component3() {
        return this.appServiceTerms;
    }

    public final UserServiceTerms copy(Long long0, List list0, List list1) {
        return new UserServiceTerms(long0, list0, list1);
    }

    public static UserServiceTerms copy$default(UserServiceTerms userServiceTerms0, Long long0, List list0, List list1, int v, Object object0) {
        if((v & 1) != 0) {
            long0 = userServiceTerms0.userId;
        }
        if((v & 2) != 0) {
            list0 = userServiceTerms0.allowedServiceTerms;
        }
        if((v & 4) != 0) {
            list1 = userServiceTerms0.appServiceTerms;
        }
        return userServiceTerms0.copy(long0, list0, list1);
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(!(object0 instanceof UserServiceTerms)) {
            return false;
        }
        if(!Intrinsics.areEqual(this.userId, ((UserServiceTerms)object0).userId)) {
            return false;
        }
        return Intrinsics.areEqual(this.allowedServiceTerms, ((UserServiceTerms)object0).allowedServiceTerms) ? Intrinsics.areEqual(this.appServiceTerms, ((UserServiceTerms)object0).appServiceTerms) : false;
    }

    public final List getAllowedServiceTerms() {
        return this.allowedServiceTerms;
    }

    public final List getAppServiceTerms() {
        return this.appServiceTerms;
    }

    public final Long getUserId() {
        return this.userId;
    }

    @Override
    public int hashCode() {
        int v = 0;
        int v1 = this.userId == null ? 0 : this.userId.hashCode();
        int v2 = this.allowedServiceTerms == null ? 0 : this.allowedServiceTerms.hashCode();
        List list0 = this.appServiceTerms;
        if(list0 != null) {
            v = list0.hashCode();
        }
        return (v1 * 0x1F + v2) * 0x1F + v;
    }

    @Override
    public String toString() {
        return "UserServiceTerms(userId=" + this.userId + ", allowedServiceTerms=" + this.allowedServiceTerms + ", appServiceTerms=" + this.appServiceTerms + ')';
    }
}

