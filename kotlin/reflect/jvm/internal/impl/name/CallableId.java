package kotlin.reflect.jvm.internal.impl.name;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

public final class CallableId {
    static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }
    }

    private static final Companion Companion;
    private static final Name LOCAL_NAME;
    private static final FqName PACKAGE_FQ_NAME_FOR_LOCAL;
    private final Name callableName;
    private final FqName className;
    private final FqName packageName;
    private final FqName pathToLocal;

    static {
        CallableId.Companion = new Companion(null);
        CallableId.LOCAL_NAME = SpecialNames.LOCAL;
        FqName fqName0 = FqName.topLevel(SpecialNames.LOCAL);
        Intrinsics.checkNotNullExpressionValue(fqName0, "topLevel(LOCAL_NAME)");
        CallableId.PACKAGE_FQ_NAME_FOR_LOCAL = fqName0;
    }

    public CallableId(FqName fqName0, FqName fqName1, Name name0, FqName fqName2) {
        Intrinsics.checkNotNullParameter(fqName0, "packageName");
        Intrinsics.checkNotNullParameter(name0, "callableName");
        super();
        this.packageName = fqName0;
        this.className = fqName1;
        this.callableName = name0;
        this.pathToLocal = fqName2;
    }

    public CallableId(FqName fqName0, FqName fqName1, Name name0, FqName fqName2, int v, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v & 8) != 0) {
            fqName2 = null;
        }
        this(fqName0, fqName1, name0, fqName2);
    }

    public CallableId(FqName fqName0, Name name0) {
        Intrinsics.checkNotNullParameter(fqName0, "packageName");
        Intrinsics.checkNotNullParameter(name0, "callableName");
        this(fqName0, null, name0, null, 8, null);
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(!(object0 instanceof CallableId)) {
            return false;
        }
        if(!Intrinsics.areEqual(this.packageName, ((CallableId)object0).packageName)) {
            return false;
        }
        if(!Intrinsics.areEqual(this.className, ((CallableId)object0).className)) {
            return false;
        }
        return Intrinsics.areEqual(this.callableName, ((CallableId)object0).callableName) ? Intrinsics.areEqual(this.pathToLocal, ((CallableId)object0).pathToLocal) : false;
    }

    @Override
    public int hashCode() {
        int v = this.packageName.hashCode();
        int v1 = 0;
        int v2 = this.className == null ? 0 : this.className.hashCode();
        int v3 = this.callableName.hashCode();
        FqName fqName0 = this.pathToLocal;
        if(fqName0 != null) {
            v1 = fqName0.hashCode();
        }
        return ((v * 0x1F + v2) * 0x1F + v3) * 0x1F + v1;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder0 = new StringBuilder();
        String s = this.packageName.asString();
        Intrinsics.checkNotNullExpressionValue(s, "packageName.asString()");
        stringBuilder0.append(StringsKt.replace$default(s, '.', '/', false, 4, null));
        stringBuilder0.append("/");
        FqName fqName0 = this.className;
        if(fqName0 != null) {
            stringBuilder0.append(fqName0);
            stringBuilder0.append(".");
        }
        stringBuilder0.append(this.callableName);
        String s1 = stringBuilder0.toString();
        Intrinsics.checkNotNullExpressionValue(s1, "StringBuilder().apply(builderAction).toString()");
        return s1;
    }
}

