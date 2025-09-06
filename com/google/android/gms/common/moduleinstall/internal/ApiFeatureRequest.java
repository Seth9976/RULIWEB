package com.google.android.gms.common.moduleinstall.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.OptionalModuleApi;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.moduleinstall.ModuleInstallRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

public class ApiFeatureRequest extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR;
    private static final Comparator zaa;
    private final List zab;
    private final boolean zac;
    private final String zad;
    private final String zae;

    static {
        ApiFeatureRequest.CREATOR = new zac();
        ApiFeatureRequest.zaa = (Object object0, Object object1) -> (((Feature)object0).getName().equals(((Feature)object1).getName()) ? Long.compare(((Feature)object0).getVersion(), ((Feature)object1).getVersion()) : ((Feature)object0).getName().compareTo(((Feature)object1).getName()));
    }

    public ApiFeatureRequest(List list0, boolean z, String s, String s1) {
        Preconditions.checkNotNull(list0);
        this.zab = list0;
        this.zac = z;
        this.zad = s;
        this.zae = s1;
    }

    // 去混淆评级： 低(40)
    @Override
    public final boolean equals(Object object0) {
        return object0 instanceof ApiFeatureRequest ? this.zac == ((ApiFeatureRequest)object0).zac && Objects.equal(this.zab, ((ApiFeatureRequest)object0).zab) && Objects.equal(this.zad, ((ApiFeatureRequest)object0).zad) && Objects.equal(this.zae, ((ApiFeatureRequest)object0).zae) : false;
    }

    public static ApiFeatureRequest fromModuleInstallRequest(ModuleInstallRequest moduleInstallRequest0) {
        return ApiFeatureRequest.zaa(moduleInstallRequest0.getApis(), true);
    }

    public List getApiFeatures() {
        return this.zab;
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(new Object[]{Boolean.valueOf(this.zac), this.zab, this.zad, this.zae});
    }

    @Override  // android.os.Parcelable
    public final void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeTypedList(parcel0, 1, this.getApiFeatures(), false);
        SafeParcelWriter.writeBoolean(parcel0, 2, this.zac);
        SafeParcelWriter.writeString(parcel0, 3, this.zad, false);
        SafeParcelWriter.writeString(parcel0, 4, this.zae, false);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }

    static ApiFeatureRequest zaa(List list0, boolean z) {
        TreeSet treeSet0 = new TreeSet(ApiFeatureRequest.zaa);
        for(Object object0: list0) {
            Collections.addAll(treeSet0, ((OptionalModuleApi)object0).getOptionalFeatures());
        }
        return new ApiFeatureRequest(new ArrayList(treeSet0), z, null, null);
    }
}

