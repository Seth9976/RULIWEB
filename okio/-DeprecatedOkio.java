package okio;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.util.Arrays;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;

@Deprecated(message = "changed in Okio 2.x")
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u0006H\u0007J\b\u0010\u0007\u001A\u00020\u0004H\u0007J\u0010\u0010\b\u001A\u00020\t2\u0006\u0010\n\u001A\u00020\u0004H\u0007J\u0010\u0010\b\u001A\u00020\u000B2\u0006\u0010\f\u001A\u00020\rH\u0007J\u0010\u0010\n\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u0006H\u0007J\u0010\u0010\n\u001A\u00020\u00042\u0006\u0010\u000E\u001A\u00020\u000FH\u0007J\u0010\u0010\n\u001A\u00020\u00042\u0006\u0010\u0010\u001A\u00020\u0011H\u0007J)\u0010\n\u001A\u00020\u00042\u0006\u0010\u0012\u001A\u00020\u00132\u0012\u0010\u0014\u001A\n\u0012\u0006\b\u0001\u0012\u00020\u00160\u0015\"\u00020\u0016H\u0007¢\u0006\u0002\u0010\u0017J\u0010\u0010\f\u001A\u00020\r2\u0006\u0010\u0005\u001A\u00020\u0006H\u0007J\u0010\u0010\f\u001A\u00020\r2\u0006\u0010\u0018\u001A\u00020\u0019H\u0007J\u0010\u0010\f\u001A\u00020\r2\u0006\u0010\u0010\u001A\u00020\u0011H\u0007J)\u0010\f\u001A\u00020\r2\u0006\u0010\u0012\u001A\u00020\u00132\u0012\u0010\u0014\u001A\n\u0012\u0006\b\u0001\u0012\u00020\u00160\u0015\"\u00020\u0016H\u0007¢\u0006\u0002\u0010\u001A¨\u0006\u001B"}, d2 = {"Lokio/-DeprecatedOkio;", "", "()V", "appendingSink", "Lokio/Sink;", "file", "Ljava/io/File;", "blackhole", "buffer", "Lokio/BufferedSink;", "sink", "Lokio/BufferedSource;", "source", "Lokio/Source;", "outputStream", "Ljava/io/OutputStream;", "socket", "Ljava/net/Socket;", "path", "Ljava/nio/file/Path;", "options", "", "Ljava/nio/file/OpenOption;", "(Ljava/nio/file/Path;[Ljava/nio/file/OpenOption;)Lokio/Sink;", "inputStream", "Ljava/io/InputStream;", "(Ljava/nio/file/Path;[Ljava/nio/file/OpenOption;)Lokio/Source;", "okio"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public final class -DeprecatedOkio {
    public static final -DeprecatedOkio INSTANCE;

    static {
        -DeprecatedOkio.INSTANCE = new -DeprecatedOkio();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to extension function", replaceWith = @ReplaceWith(expression = "file.appendingSink()", imports = {"okio.appendingSink"}))
    public final Sink appendingSink(File file0) {
        Intrinsics.checkNotNullParameter(file0, "file");
        return Okio.appendingSink(file0);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to extension function", replaceWith = @ReplaceWith(expression = "blackholeSink()", imports = {"okio.blackholeSink"}))
    public final Sink blackhole() {
        return Okio.blackhole();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to extension function", replaceWith = @ReplaceWith(expression = "sink.buffer()", imports = {"okio.buffer"}))
    public final BufferedSink buffer(Sink sink0) {
        Intrinsics.checkNotNullParameter(sink0, "sink");
        return Okio.buffer(sink0);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to extension function", replaceWith = @ReplaceWith(expression = "source.buffer()", imports = {"okio.buffer"}))
    public final BufferedSource buffer(Source source0) {
        Intrinsics.checkNotNullParameter(source0, "source");
        return Okio.buffer(source0);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to extension function", replaceWith = @ReplaceWith(expression = "file.sink()", imports = {"okio.sink"}))
    public final Sink sink(File file0) {
        Intrinsics.checkNotNullParameter(file0, "file");
        return Okio.sink$default(file0, false, 1, null);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to extension function", replaceWith = @ReplaceWith(expression = "outputStream.sink()", imports = {"okio.sink"}))
    public final Sink sink(OutputStream outputStream0) {
        Intrinsics.checkNotNullParameter(outputStream0, "outputStream");
        return Okio.sink(outputStream0);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to extension function", replaceWith = @ReplaceWith(expression = "socket.sink()", imports = {"okio.sink"}))
    public final Sink sink(Socket socket0) {
        Intrinsics.checkNotNullParameter(socket0, "socket");
        return Okio.sink(socket0);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to extension function", replaceWith = @ReplaceWith(expression = "path.sink(*options)", imports = {"okio.sink"}))
    public final Sink sink(Path path0, OpenOption[] arr_openOption) {
        Intrinsics.checkNotNullParameter(path0, "path");
        Intrinsics.checkNotNullParameter(arr_openOption, "options");
        return Okio.sink(path0, ((OpenOption[])Arrays.copyOf(arr_openOption, arr_openOption.length)));
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to extension function", replaceWith = @ReplaceWith(expression = "file.source()", imports = {"okio.source"}))
    public final Source source(File file0) {
        Intrinsics.checkNotNullParameter(file0, "file");
        return Okio.source(file0);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to extension function", replaceWith = @ReplaceWith(expression = "inputStream.source()", imports = {"okio.source"}))
    public final Source source(InputStream inputStream0) {
        Intrinsics.checkNotNullParameter(inputStream0, "inputStream");
        return Okio.source(inputStream0);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to extension function", replaceWith = @ReplaceWith(expression = "socket.source()", imports = {"okio.source"}))
    public final Source source(Socket socket0) {
        Intrinsics.checkNotNullParameter(socket0, "socket");
        return Okio.source(socket0);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to extension function", replaceWith = @ReplaceWith(expression = "path.source(*options)", imports = {"okio.source"}))
    public final Source source(Path path0, OpenOption[] arr_openOption) {
        Intrinsics.checkNotNullParameter(path0, "path");
        Intrinsics.checkNotNullParameter(arr_openOption, "options");
        return Okio.source(path0, ((OpenOption[])Arrays.copyOf(arr_openOption, arr_openOption.length)));
    }
}

