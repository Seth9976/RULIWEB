package kotlin.io;

import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001A$\u0010\u0000\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u00032\b\u0010\u0004\u001A\u0004\u0018\u00010\u00032\b\u0010\u0005\u001A\u0004\u0018\u00010\u0001H\u0002¨\u0006\u0006"}, d2 = {"constructMessage", "", "file", "Ljava/io/File;", "other", "reason", "kotlin-stdlib"}, k = 2, mv = {1, 9, 0}, xi = 0x30)
public final class ExceptionsKt {
    public static final String access$constructMessage(File file0, File file1, String s) {
        return ExceptionsKt.constructMessage(file0, file1, s);
    }

    private static final String constructMessage(File file0, File file1, String s) {
        StringBuilder stringBuilder0 = new StringBuilder(file0.toString());
        if(file1 != null) {
            stringBuilder0.append(" -> " + file1);
        }
        if(s != null) {
            stringBuilder0.append(": " + s);
        }
        String s1 = stringBuilder0.toString();
        Intrinsics.checkNotNullExpressionValue(s1, "sb.toString()");
        return s1;
    }
}

