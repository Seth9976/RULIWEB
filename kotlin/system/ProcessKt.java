package kotlin.system;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u000E\n\u0000\n\u0002\u0010\u0001\n\u0000\n\u0002\u0010\b\n\u0000\u001A\u0011\u0010\u0000\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u0003H\u0087\b¨\u0006\u0004"}, d2 = {"exitProcess", "", "status", "", "kotlin-stdlib"}, k = 2, mv = {1, 9, 0}, xi = 0x30)
public final class ProcessKt {
    private static final Void exitProcess(int v) {
        System.exit(v);
        throw new RuntimeException("System.exit returned normally, while it was supposed to halt JVM.");
    }
}

