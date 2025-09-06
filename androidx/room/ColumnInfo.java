package androidx.room;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import kotlin.Metadata;
import kotlin.annotation.AnnotationRetention;
import kotlin.annotation.AnnotationTarget;

@Retention(RetentionPolicy.CLASS)
@Target({ElementType.FIELD, ElementType.METHOD})
@Metadata(d1 = {"\u0000\u001E\n\u0002\u0018\u0002\n\u0002\u0010\u001B\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000B\n\u0002\b\t\b\u0087\u0002\u0018\u0000 \u000E2\u00020\u0001:\u0003\r\u000E\u000FB2\u0012\b\b\u0002\u0010\u0002\u001A\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001A\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001A\u00020\u0007\u0012\b\b\u0002\u0010\b\u001A\u00020\u0005\u0012\b\b\u0002\u0010\t\u001A\u00020\u0003R\u0011\u0010\b\u001A\u00020\u00058\u0007¢\u0006\u0006\u001A\u0004\b\b\u0010\nR\u000F\u0010\t\u001A\u00020\u0003¢\u0006\u0006\u001A\u0004\b\t\u0010\u000BR\u000F\u0010\u0006\u001A\u00020\u0007¢\u0006\u0006\u001A\u0004\b\u0006\u0010\fR\u000F\u0010\u0002\u001A\u00020\u0003¢\u0006\u0006\u001A\u0004\b\u0002\u0010\u000BR\u0011\u0010\u0004\u001A\u00020\u00058\u0007¢\u0006\u0006\u001A\u0004\b\u0004\u0010\n¨\u0006\u0010"}, d2 = {"Landroidx/room/ColumnInfo;", "", "name", "", "typeAffinity", "", "index", "", "collate", "defaultValue", "()I", "()Ljava/lang/String;", "()Z", "Collate", "Companion", "SQLiteTypeAffinity", "room-common"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
@kotlin.annotation.Retention(AnnotationRetention.BINARY)
@kotlin.annotation.Target(allowedTargets = {AnnotationTarget.FIELD, AnnotationTarget.FUNCTION})
public @interface ColumnInfo {
    @Retention(RetentionPolicy.CLASS)
    @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u001B\n\u0000\b\u0087\u0002\u0018\u00002\u00020\u0001B\u0000¨\u0006\u0002"}, d2 = {"Landroidx/room/ColumnInfo$Collate;", "", "room-common"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    @kotlin.annotation.Retention(AnnotationRetention.BINARY)
    public @interface Collate {
    }

    @Metadata(d1 = {"\u0000\u001C\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0002\b\u000B\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000E\u0010\u0003\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000E\u0010\u0005\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000E\u0010\u0006\u001A\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000E\u0010\b\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001A\u00020\u00048\u0006X\u0087T¢\u0006\u0002\n\u0000R\u000E\u0010\n\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000E\u0010\u000B\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000E\u0010\f\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000E\u0010\r\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000E\u0010\u000E\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0010\u0010\u000F\u001A\u00020\u00048\u0006X\u0087T¢\u0006\u0002\n\u0000R\u000E\u0010\u0010\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000E\u0010\u0011\u001A\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Landroidx/room/ColumnInfo$Companion;", "", "()V", "BINARY", "", "BLOB", "INHERIT_FIELD_NAME", "", "INTEGER", "LOCALIZED", "NOCASE", "REAL", "RTRIM", "TEXT", "UNDEFINED", "UNICODE", "UNSPECIFIED", "VALUE_UNSPECIFIED", "room-common"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class Companion {
        static final Companion $$INSTANCE = null;
        public static final int BINARY = 2;
        public static final int BLOB = 5;
        public static final String INHERIT_FIELD_NAME = "[field-name]";
        public static final int INTEGER = 3;
        public static final int LOCALIZED = 5;
        public static final int NOCASE = 3;
        public static final int REAL = 4;
        public static final int RTRIM = 4;
        public static final int TEXT = 2;
        public static final int UNDEFINED = 1;
        public static final int UNICODE = 6;
        public static final int UNSPECIFIED = 1;
        public static final String VALUE_UNSPECIFIED = "[value-unspecified]";

        static {
            Companion.$$INSTANCE = new Companion();
        }
    }

    @Retention(RetentionPolicy.CLASS)
    @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u001B\n\u0000\b\u0087\u0002\u0018\u00002\u00020\u0001B\u0000¨\u0006\u0002"}, d2 = {"Landroidx/room/ColumnInfo$SQLiteTypeAffinity;", "", "room-common"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    @kotlin.annotation.Retention(AnnotationRetention.BINARY)
    public @interface SQLiteTypeAffinity {
    }

    public static final int BINARY = 2;
    public static final int BLOB = 5;
    public static final Companion Companion = null;
    public static final String INHERIT_FIELD_NAME = "[field-name]";
    public static final int INTEGER = 3;
    public static final int LOCALIZED = 5;
    public static final int NOCASE = 3;
    public static final int REAL = 4;
    public static final int RTRIM = 4;
    public static final int TEXT = 2;
    public static final int UNDEFINED = 1;
    public static final int UNICODE = 6;
    public static final int UNSPECIFIED = 1;
    public static final String VALUE_UNSPECIFIED = "[value-unspecified]";

    static {
        ColumnInfo.Companion = Companion.$$INSTANCE;
    }

    int collate() default 1;

    String defaultValue() default "[value-unspecified]";

    boolean index() default false;

    String name() default "[field-name]";

    int typeAffinity() default 1;
}

