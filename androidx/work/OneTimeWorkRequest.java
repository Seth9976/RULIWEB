package androidx.work;

import android.os.Build.VERSION;
import androidx.work.impl.model.WorkSpec;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00062\u00020\u0001:\u0002\u0005\u0006B\u000F\b\u0000\u0012\u0006\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0007"}, d2 = {"Landroidx/work/OneTimeWorkRequest;", "Landroidx/work/WorkRequest;", "builder", "Landroidx/work/OneTimeWorkRequest$Builder;", "(Landroidx/work/OneTimeWorkRequest$Builder;)V", "Builder", "Companion", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class OneTimeWorkRequest extends WorkRequest {
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u000E\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00020\u0001B\u0017\b\u0016\u0012\u000E\u0010\u0003\u001A\n\u0012\u0006\b\u0001\u0012\u00020\u00050\u0004¢\u0006\u0002\u0010\u0006B\u0015\u0012\u000E\u0010\u0003\u001A\n\u0012\u0006\b\u0001\u0012\u00020\u00050\u0007¢\u0006\u0002\u0010\bJ\r\u0010\f\u001A\u00020\u0002H\u0010¢\u0006\u0002\b\rJ\u0016\u0010\u000E\u001A\u00020\u00002\u000E\u0010\u000F\u001A\n\u0012\u0006\b\u0001\u0012\u00020\u00100\u0007R\u0014\u0010\t\u001A\u00020\u00008PX\u0090\u0004¢\u0006\u0006\u001A\u0004\b\n\u0010\u000B¨\u0006\u0011"}, d2 = {"Landroidx/work/OneTimeWorkRequest$Builder;", "Landroidx/work/WorkRequest$Builder;", "Landroidx/work/OneTimeWorkRequest;", "workerClass", "Lkotlin/reflect/KClass;", "Landroidx/work/ListenableWorker;", "(Lkotlin/reflect/KClass;)V", "Ljava/lang/Class;", "(Ljava/lang/Class;)V", "thisObject", "getThisObject$work_runtime_release", "()Landroidx/work/OneTimeWorkRequest$Builder;", "buildInternal", "buildInternal$work_runtime_release", "setInputMerger", "inputMerger", "Landroidx/work/InputMerger;", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class Builder extends androidx.work.WorkRequest.Builder {
        public Builder(Class class0) {
            Intrinsics.checkNotNullParameter(class0, "workerClass");
            super(class0);
        }

        public Builder(KClass kClass0) {
            Intrinsics.checkNotNullParameter(kClass0, "workerClass");
            this(JvmClassMappingKt.getJavaClass(kClass0));
        }

        public OneTimeWorkRequest buildInternal$work_runtime_release() {
            if(this.getBackoffCriteriaSet$work_runtime_release() && Build.VERSION.SDK_INT >= 23 && this.getWorkSpec$work_runtime_release().constraints.requiresDeviceIdle()) {
                throw new IllegalArgumentException("Cannot set backoff criteria on an idle mode job");
            }
            return new OneTimeWorkRequest(this);
        }

        @Override  // androidx.work.WorkRequest$Builder
        public WorkRequest buildInternal$work_runtime_release() {
            return this.buildInternal$work_runtime_release();
        }

        public Builder getThisObject$work_runtime_release() [...] // Inlined contents

        @Override  // androidx.work.WorkRequest$Builder
        public androidx.work.WorkRequest.Builder getThisObject$work_runtime_release() {
            return this;
        }

        public final Builder setInputMerger(Class class0) {
            Intrinsics.checkNotNullParameter(class0, "inputMerger");
            WorkSpec workSpec0 = this.getWorkSpec$work_runtime_release();
            String s = class0.getName();
            Intrinsics.checkNotNullExpressionValue(s, "inputMerger.name");
            workSpec0.inputMergerClassName = s;
            return this;
        }
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001A\u00020\u00042\u000E\u0010\u0005\u001A\n\u0012\u0006\b\u0001\u0012\u00020\u00070\u0006H\u0007J$\u0010\u0003\u001A\b\u0012\u0004\u0012\u00020\u00040\b2\u0014\u0010\t\u001A\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00070\u00060\bH\u0007¨\u0006\n"}, d2 = {"Landroidx/work/OneTimeWorkRequest$Companion;", "", "()V", "from", "Landroidx/work/OneTimeWorkRequest;", "workerClass", "Ljava/lang/Class;", "Landroidx/work/ListenableWorker;", "", "workerClasses", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        @JvmStatic
        public final OneTimeWorkRequest from(Class class0) {
            Intrinsics.checkNotNullParameter(class0, "workerClass");
            return (OneTimeWorkRequest)new Builder(class0).build();
        }

        @JvmStatic
        public final List from(List list0) {
            Intrinsics.checkNotNullParameter(list0, "workerClasses");
            ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list0, 10));
            for(Object object0: list0) {
                arrayList0.add(((OneTimeWorkRequest)new Builder(((Class)object0)).build()));
            }
            return arrayList0;
        }
    }

    public static final Companion Companion;

    static {
        OneTimeWorkRequest.Companion = new Companion(null);
    }

    public OneTimeWorkRequest(Builder oneTimeWorkRequest$Builder0) {
        Intrinsics.checkNotNullParameter(oneTimeWorkRequest$Builder0, "builder");
        super(oneTimeWorkRequest$Builder0.getId$work_runtime_release(), oneTimeWorkRequest$Builder0.getWorkSpec$work_runtime_release(), oneTimeWorkRequest$Builder0.getTags$work_runtime_release());
    }

    @JvmStatic
    public static final OneTimeWorkRequest from(Class class0) {
        return OneTimeWorkRequest.Companion.from(class0);
    }

    @JvmStatic
    public static final List from(List list0) {
        return OneTimeWorkRequest.Companion.from(list0);
    }
}

