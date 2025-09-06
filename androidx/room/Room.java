package androidx.room;

import android.content.Context;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J8\u0010\u0007\u001A\b\u0012\u0004\u0012\u0002H\t0\b\"\b\b\u0000\u0010\t*\u00020\n2\u0006\u0010\u000B\u001A\u00020\f2\f\u0010\r\u001A\b\u0012\u0004\u0012\u0002H\t0\u000E2\b\u0010\u000F\u001A\u0004\u0018\u00010\u0004H\u0007J/\u0010\u0010\u001A\u0002H\t\"\u0004\b\u0000\u0010\t\"\u0004\b\u0001\u0010\u00112\f\u0010\r\u001A\b\u0012\u0004\u0012\u0002H\u00110\u000E2\u0006\u0010\u0012\u001A\u00020\u0004H\u0007¢\u0006\u0002\u0010\u0013J.\u0010\u0014\u001A\b\u0012\u0004\u0012\u0002H\t0\b\"\b\b\u0000\u0010\t*\u00020\n2\u0006\u0010\u000B\u001A\u00020\f2\f\u0010\r\u001A\b\u0012\u0004\u0012\u0002H\t0\u000EH\u0007R\u000E\u0010\u0003\u001A\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000E\u0010\u0005\u001A\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000E\u0010\u0006\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Landroidx/room/Room;", "", "()V", "CURSOR_CONV_SUFFIX", "", "LOG_TAG", "MASTER_TABLE_NAME", "databaseBuilder", "Landroidx/room/RoomDatabase$Builder;", "T", "Landroidx/room/RoomDatabase;", "context", "Landroid/content/Context;", "klass", "Ljava/lang/Class;", "name", "getGeneratedImplementation", "C", "suffix", "(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Object;", "inMemoryDatabaseBuilder", "room-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class Room {
    private static final String CURSOR_CONV_SUFFIX = "_CursorConverter";
    public static final Room INSTANCE = null;
    public static final String LOG_TAG = "ROOM";
    public static final String MASTER_TABLE_NAME = "room_master_table";

    static {
        Room.INSTANCE = new Room();
    }

    @JvmStatic
    public static final Builder databaseBuilder(Context context0, Class class0, String s) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(class0, "klass");
        if(s == null || StringsKt.isBlank(s)) {
            throw new IllegalArgumentException("Cannot build a database with null or empty name. If you are trying to create an in memory database, use Room.inMemoryDatabaseBuilder");
        }
        return new Builder(context0, class0, s);
    }

    @JvmStatic
    public static final Object getGeneratedImplementation(Class class0, String s) {
        Intrinsics.checkNotNullParameter(class0, "klass");
        Intrinsics.checkNotNullParameter(s, "suffix");
        Package package0 = class0.getPackage();
        Intrinsics.checkNotNull(package0);
        String s1 = package0.getName();
        String s2 = class0.getCanonicalName();
        Intrinsics.checkNotNull(s2);
        Intrinsics.checkNotNullExpressionValue(s1, "fullPackage");
        if(s1.length() != 0) {
            s2 = s2.substring(s1.length() + 1);
            Intrinsics.checkNotNullExpressionValue(s2, "this as java.lang.String).substring(startIndex)");
        }
        String s3 = StringsKt.replace$default(s2, '.', '_', false, 4, null) + s;
        try {
            Class class1 = Class.forName((s1.length() == 0 ? s3 : s1 + '.' + s3), true, class0.getClassLoader());
            Intrinsics.checkNotNull(class1, "null cannot be cast to non-null type java.lang.Class<T of androidx.room.Room.getGeneratedImplementation>");
            return class1.getDeclaredConstructor(null).newInstance(null);
        }
        catch(ClassNotFoundException unused_ex) {
            throw new RuntimeException("Cannot find implementation for " + class0.getCanonicalName() + ". " + s3 + " does not exist");
        }
        catch(IllegalAccessException unused_ex) {
            throw new RuntimeException("Cannot access the constructor " + class0.getCanonicalName());
        }
        catch(InstantiationException unused_ex) {
            throw new RuntimeException("Failed to create an instance of " + class0.getCanonicalName());
        }
    }

    @JvmStatic
    public static final Builder inMemoryDatabaseBuilder(Context context0, Class class0) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(class0, "klass");
        return new Builder(context0, class0, null);
    }
}

