package com.kakao.sdk.user.model;

import java.util.Date;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001D\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0005\u0012\u0006\u0010\u0006\u001A\u00020\u0005¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001A\u00020\u0003HÆ\u0003J\t\u0010\u000E\u001A\u00020\u0005HÆ\u0003J\t\u0010\u000F\u001A\u00020\u0005HÆ\u0003J\'\u0010\u0010\u001A\u00020\u00002\b\b\u0002\u0010\u0002\u001A\u00020\u00032\b\b\u0002\u0010\u0004\u001A\u00020\u00052\b\b\u0002\u0010\u0006\u001A\u00020\u0005HÆ\u0001J\u0013\u0010\u0011\u001A\u00020\u00122\b\u0010\u0013\u001A\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001A\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001A\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001A\u00020\u0005¢\u0006\b\n\u0000\u001A\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001A\u00020\u0003¢\u0006\b\n\u0000\u001A\u0004\b\n\u0010\u000BR\u0011\u0010\u0006\u001A\u00020\u0005¢\u0006\b\n\u0000\u001A\u0004\b\f\u0010\t¨\u0006\u0017"}, d2 = {"Lcom/kakao/sdk/user/model/AppServiceTerms;", "", "tag", "", "createdAt", "Ljava/util/Date;", "updatedAt", "(Ljava/lang/String;Ljava/util/Date;Ljava/util/Date;)V", "getCreatedAt", "()Ljava/util/Date;", "getTag", "()Ljava/lang/String;", "getUpdatedAt", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "user_release"}, k = 1, mv = {1, 5, 1}, xi = 0x30)
public final class AppServiceTerms {
    private final Date createdAt;
    private final String tag;
    private final Date updatedAt;

    public AppServiceTerms(String s, Date date0, Date date1) {
        Intrinsics.checkNotNullParameter(s, "tag");
        Intrinsics.checkNotNullParameter(date0, "createdAt");
        Intrinsics.checkNotNullParameter(date1, "updatedAt");
        super();
        this.tag = s;
        this.createdAt = date0;
        this.updatedAt = date1;
    }

    public final String component1() {
        return this.tag;
    }

    public final Date component2() {
        return this.createdAt;
    }

    public final Date component3() {
        return this.updatedAt;
    }

    public final AppServiceTerms copy(String s, Date date0, Date date1) {
        Intrinsics.checkNotNullParameter(s, "tag");
        Intrinsics.checkNotNullParameter(date0, "createdAt");
        Intrinsics.checkNotNullParameter(date1, "updatedAt");
        return new AppServiceTerms(s, date0, date1);
    }

    public static AppServiceTerms copy$default(AppServiceTerms appServiceTerms0, String s, Date date0, Date date1, int v, Object object0) {
        if((v & 1) != 0) {
            s = appServiceTerms0.tag;
        }
        if((v & 2) != 0) {
            date0 = appServiceTerms0.createdAt;
        }
        if((v & 4) != 0) {
            date1 = appServiceTerms0.updatedAt;
        }
        return appServiceTerms0.copy(s, date0, date1);
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(!(object0 instanceof AppServiceTerms)) {
            return false;
        }
        if(!Intrinsics.areEqual(this.tag, ((AppServiceTerms)object0).tag)) {
            return false;
        }
        return Intrinsics.areEqual(this.createdAt, ((AppServiceTerms)object0).createdAt) ? Intrinsics.areEqual(this.updatedAt, ((AppServiceTerms)object0).updatedAt) : false;
    }

    public final Date getCreatedAt() {
        return this.createdAt;
    }

    public final String getTag() {
        return this.tag;
    }

    public final Date getUpdatedAt() {
        return this.updatedAt;
    }

    @Override
    public int hashCode() {
        return (this.tag.hashCode() * 0x1F + this.createdAt.hashCode()) * 0x1F + this.updatedAt.hashCode();
    }

    @Override
    public String toString() {
        return "AppServiceTerms(tag=" + this.tag + ", createdAt=" + this.createdAt + ", updatedAt=" + this.updatedAt + ')';
    }
}

