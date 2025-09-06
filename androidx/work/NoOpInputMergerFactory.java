package androidx.work;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0001\n\u0000\n\u0002\u0010\u000E\n\u0000\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001A\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001A\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"Landroidx/work/NoOpInputMergerFactory;", "Landroidx/work/InputMergerFactory;", "()V", "createInputMerger", "", "className", "", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class NoOpInputMergerFactory extends InputMergerFactory {
    public static final NoOpInputMergerFactory INSTANCE;

    static {
        NoOpInputMergerFactory.INSTANCE = new NoOpInputMergerFactory();
    }

    @Override  // androidx.work.InputMergerFactory
    public InputMerger createInputMerger(String s) {
        return (InputMerger)this.createInputMerger(s);
    }

    public Void createInputMerger(String s) {
        Intrinsics.checkNotNullParameter(s, "className");
        return null;
    }
}

