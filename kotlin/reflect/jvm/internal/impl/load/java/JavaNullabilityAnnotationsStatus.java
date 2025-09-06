package kotlin.reflect.jvm.internal.impl.load.java;

import kotlin.KotlinVersion;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class JavaNullabilityAnnotationsStatus {
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final JavaNullabilityAnnotationsStatus getDEFAULT() {
            return JavaNullabilityAnnotationsStatus.DEFAULT;
        }
    }

    public static final Companion Companion;
    private static final JavaNullabilityAnnotationsStatus DEFAULT;
    private final ReportLevel reportLevelAfter;
    private final ReportLevel reportLevelBefore;
    private final KotlinVersion sinceVersion;

    static {
        JavaNullabilityAnnotationsStatus.Companion = new Companion(null);
        JavaNullabilityAnnotationsStatus.DEFAULT = new JavaNullabilityAnnotationsStatus(ReportLevel.STRICT, null, null, 6, null);
    }

    public JavaNullabilityAnnotationsStatus(ReportLevel reportLevel0, KotlinVersion kotlinVersion0, ReportLevel reportLevel1) {
        Intrinsics.checkNotNullParameter(reportLevel0, "reportLevelBefore");
        Intrinsics.checkNotNullParameter(reportLevel1, "reportLevelAfter");
        super();
        this.reportLevelBefore = reportLevel0;
        this.sinceVersion = kotlinVersion0;
        this.reportLevelAfter = reportLevel1;
    }

    public JavaNullabilityAnnotationsStatus(ReportLevel reportLevel0, KotlinVersion kotlinVersion0, ReportLevel reportLevel1, int v, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v & 2) != 0) {
            kotlinVersion0 = new KotlinVersion(1, 0);
        }
        if((v & 4) != 0) {
            reportLevel1 = reportLevel0;
        }
        this(reportLevel0, kotlinVersion0, reportLevel1);
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(!(object0 instanceof JavaNullabilityAnnotationsStatus)) {
            return false;
        }
        if(this.reportLevelBefore != ((JavaNullabilityAnnotationsStatus)object0).reportLevelBefore) {
            return false;
        }
        return Intrinsics.areEqual(this.sinceVersion, ((JavaNullabilityAnnotationsStatus)object0).sinceVersion) ? this.reportLevelAfter == ((JavaNullabilityAnnotationsStatus)object0).reportLevelAfter : false;
    }

    public final ReportLevel getReportLevelAfter() {
        return this.reportLevelAfter;
    }

    public final ReportLevel getReportLevelBefore() {
        return this.reportLevelBefore;
    }

    public final KotlinVersion getSinceVersion() {
        return this.sinceVersion;
    }

    @Override
    public int hashCode() {
        int v = this.reportLevelBefore.hashCode();
        return this.sinceVersion == null ? v * 961 + this.reportLevelAfter.hashCode() : (v * 0x1F + this.sinceVersion.hashCode()) * 0x1F + this.reportLevelAfter.hashCode();
    }

    @Override
    public String toString() {
        return "JavaNullabilityAnnotationsStatus(reportLevelBefore=" + this.reportLevelBefore + ", sinceVersion=" + this.sinceVersion + ", reportLevelAfter=" + this.reportLevelAfter + ')';
    }
}

