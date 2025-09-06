package androidx.room;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.annotation.AnnotationRetention;

@Retention(RetentionPolicy.CLASS)
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u001B\n\u0002\b\u0002\b\u0087\u0002\u0018\u0000 \u00022\u00020\u0001:\u0001\u0002B\u0000¨\u0006\u0003"}, d2 = {"Landroidx/room/OnConflictStrategy;", "", "Companion", "room-common"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
@kotlin.annotation.Retention(AnnotationRetention.BINARY)
public @interface OnConflictStrategy {
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000E\u0010\u0003\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001A\u00020\u00048\u0006X\u0087T¢\u0006\b\n\u0000\u0012\u0004\b\u0006\u0010\u0002R\u000E\u0010\u0007\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000E\u0010\b\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000E\u0010\t\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0016\u0010\n\u001A\u00020\u00048\u0006X\u0087T¢\u0006\b\n\u0000\u0012\u0004\b\u000B\u0010\u0002¨\u0006\f"}, d2 = {"Landroidx/room/OnConflictStrategy$Companion;", "", "()V", "ABORT", "", "FAIL", "getFAIL$annotations", "IGNORE", "NONE", "REPLACE", "ROLLBACK", "getROLLBACK$annotations", "room-common"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class Companion {
        static final Companion $$INSTANCE = null;
        public static final int ABORT = 3;
        public static final int FAIL = 4;
        public static final int IGNORE = 5;
        public static final int NONE = 0;
        public static final int REPLACE = 1;
        public static final int ROLLBACK = 2;

        static {
            Companion.$$INSTANCE = new Companion();
        }

        @Deprecated(message = "Use ABORT instead.")
        public static void getFAIL$annotations() {
        }

        @Deprecated(message = "Use ABORT instead.")
        public static void getROLLBACK$annotations() {
        }
    }

    public static final int ABORT = 3;
    public static final Companion Companion = null;
    public static final int FAIL = 4;
    public static final int IGNORE = 5;
    public static final int NONE = 0;
    public static final int REPLACE = 1;
    public static final int ROLLBACK = 2;

    static {
        OnConflictStrategy.Companion = Companion.$$INSTANCE;
    }
}

