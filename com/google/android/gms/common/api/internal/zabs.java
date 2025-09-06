package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.Feature;
import com.google.android.gms.common.internal.Objects;

final class zabs {
    private final ApiKey zaa;
    private final Feature zab;

    zabs(ApiKey apiKey0, Feature feature0, zabr zabr0) {
        this.zaa = apiKey0;
        this.zab = feature0;
    }

    // 去混淆评级： 低(30)
    @Override
    public final boolean equals(Object object0) {
        return object0 != null && object0 instanceof zabs && Objects.equal(this.zaa, ((zabs)object0).zaa) && Objects.equal(this.zab, ((zabs)object0).zab);
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(new Object[]{this.zaa, this.zab});
    }

    @Override
    public final String toString() {
        return Objects.toStringHelper(this).add("key", this.zaa).add("feature", this.zab).toString();
    }

    static ApiKey zab(zabs zabs0) {
        return zabs0.zaa;
    }
}

