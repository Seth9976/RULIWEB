package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import java.util.Map;
import kotlin.jvm.internal.Intrinsics;

public final class TypeEnhancementInfo {
    private final Map map;

    public TypeEnhancementInfo(Map map0) {
        Intrinsics.checkNotNullParameter(map0, "map");
        super();
        this.map = map0;
    }

    public final Map getMap() {
        return this.map;
    }
}

