package androidx.room.util;

import android.os.Build.VERSION;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001A\u0018\u0010\u0000\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u00032\u0006\u0010\u0004\u001A\u00020\u0005H\u0007¨\u0006\u0006"}, d2 = {"copy", "", "input", "Ljava/nio/channels/ReadableByteChannel;", "output", "Ljava/nio/channels/FileChannel;", "room-runtime_release"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class FileUtil {
    public static final void copy(ReadableByteChannel readableByteChannel0, FileChannel fileChannel0) throws IOException {
        Intrinsics.checkNotNullParameter(readableByteChannel0, "input");
        Intrinsics.checkNotNullParameter(fileChannel0, "output");
        try {
            if(Build.VERSION.SDK_INT > 23) {
                fileChannel0.transferFrom(readableByteChannel0, 0L, 0x7FFFFFFFFFFFFFFFL);
            }
            else {
                InputStream inputStream0 = Channels.newInputStream(readableByteChannel0);
                OutputStream outputStream0 = Channels.newOutputStream(fileChannel0);
                byte[] arr_b = new byte[0x1000];
                int v1;
                while((v1 = inputStream0.read(arr_b)) > 0) {
                    outputStream0.write(arr_b, 0, v1);
                }
            }
            fileChannel0.force(false);
        }
        finally {
            readableByteChannel0.close();
            fileChannel0.close();
        }
    }
}

