package kotlin.reflect.jvm.internal.impl.load.java;

import java.util.List;
import java.util.Map.Entry;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class Jsr305Settings {
    private final Lazy description$delegate;
    private final ReportLevel globalLevel;
    private final boolean isDisabled;
    private final ReportLevel migrationLevel;
    private final Map userDefinedLevelForSpecificAnnotation;

    public Jsr305Settings(ReportLevel reportLevel0, ReportLevel reportLevel1, Map map0) {
        Intrinsics.checkNotNullParameter(reportLevel0, "globalLevel");
        Intrinsics.checkNotNullParameter(map0, "userDefinedLevelForSpecificAnnotation");
        super();
        this.globalLevel = reportLevel0;
        this.migrationLevel = reportLevel1;
        this.userDefinedLevelForSpecificAnnotation = map0;
        this.description$delegate = LazyKt.lazy(new Function0() {
            {
                Jsr305Settings.this = jsr305Settings0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final String[] invoke() {
                Jsr305Settings jsr305Settings0 = Jsr305Settings.this;
                List list0 = CollectionsKt.createListBuilder();
                list0.add(jsr305Settings0.getGlobalLevel().getDescription());
                ReportLevel reportLevel0 = jsr305Settings0.getMigrationLevel();
                if(reportLevel0 != null) {
                    list0.add("under-migration:" + reportLevel0.getDescription());
                }
                for(Object object0: jsr305Settings0.getUserDefinedLevelForSpecificAnnotation().entrySet()) {
                    list0.add("@" + ((Map.Entry)object0).getKey() + ':' + ((ReportLevel)((Map.Entry)object0).getValue()).getDescription());
                }
                return (String[])CollectionsKt.build(list0).toArray(new String[0]);
            }
        });
        this.isDisabled = reportLevel0 == ReportLevel.IGNORE && reportLevel1 == ReportLevel.IGNORE && map0.isEmpty();
    }

    public Jsr305Settings(ReportLevel reportLevel0, ReportLevel reportLevel1, Map map0, int v, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v & 2) != 0) {
            reportLevel1 = null;
        }
        if((v & 4) != 0) {
            map0 = MapsKt.emptyMap();
        }
        this(reportLevel0, reportLevel1, map0);
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(!(object0 instanceof Jsr305Settings)) {
            return false;
        }
        if(this.globalLevel != ((Jsr305Settings)object0).globalLevel) {
            return false;
        }
        return this.migrationLevel == ((Jsr305Settings)object0).migrationLevel ? Intrinsics.areEqual(this.userDefinedLevelForSpecificAnnotation, ((Jsr305Settings)object0).userDefinedLevelForSpecificAnnotation) : false;
    }

    public final ReportLevel getGlobalLevel() {
        return this.globalLevel;
    }

    public final ReportLevel getMigrationLevel() {
        return this.migrationLevel;
    }

    public final Map getUserDefinedLevelForSpecificAnnotation() {
        return this.userDefinedLevelForSpecificAnnotation;
    }

    @Override
    public int hashCode() {
        int v = this.globalLevel.hashCode();
        return this.migrationLevel == null ? v * 961 + this.userDefinedLevelForSpecificAnnotation.hashCode() : (v * 0x1F + this.migrationLevel.hashCode()) * 0x1F + this.userDefinedLevelForSpecificAnnotation.hashCode();
    }

    public final boolean isDisabled() {
        return this.isDisabled;
    }

    @Override
    public String toString() {
        return "Jsr305Settings(globalLevel=" + this.globalLevel + ", migrationLevel=" + this.migrationLevel + ", userDefinedLevelForSpecificAnnotation=" + this.userDefinedLevelForSpecificAnnotation + ')';
    }
}

