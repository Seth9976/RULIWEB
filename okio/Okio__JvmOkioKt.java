package okio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.Mac;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okio.internal.ResourceFileSystem;
import okio.internal.ZipKt;

@Metadata(d1 = {"\u0000\u0088\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001A\n\u0010\b\u001A\u00020\t*\u00020\n\u001A\n\u0010\u000B\u001A\u00020\f*\u00020\r\u001A\u0012\u0010\u000E\u001A\u00020\u000F*\u00020\t2\u0006\u0010\u0010\u001A\u00020\u0011\u001A\u0012\u0010\u0012\u001A\u00020\u0013*\u00020\u00142\u0006\u0010\u0010\u001A\u00020\u0011\u001A\u0012\u0010\u0015\u001A\u00020\u0016*\u00020\t2\u0006\u0010\u0017\u001A\u00020\u0018\u001A\u0012\u0010\u0015\u001A\u00020\u0016*\u00020\t2\u0006\u0010\u0019\u001A\u00020\u001A\u001A\u0012\u0010\u001B\u001A\u00020\u001C*\u00020\u00142\u0006\u0010\u0017\u001A\u00020\u0018\u001A\u0012\u0010\u001B\u001A\u00020\u001C*\u00020\u00142\u0006\u0010\u0019\u001A\u00020\u001A\u001A\u0012\u0010\u001D\u001A\u00020\f*\u00020\f2\u0006\u0010\u001E\u001A\u00020\u001F\u001A\u0016\u0010 \u001A\u00020\t*\u00020\n2\b\b\u0002\u0010!\u001A\u00020\u0004H\u0007\u001A\n\u0010 \u001A\u00020\t*\u00020\"\u001A\n\u0010 \u001A\u00020\t*\u00020#\u001A%\u0010 \u001A\u00020\t*\u00020$2\u0012\u0010%\u001A\n\u0012\u0006\b\u0001\u0012\u00020\'0&\"\u00020\'H\u0007\u00A2\u0006\u0002\u0010(\u001A\n\u0010)\u001A\u00020\u0014*\u00020\n\u001A\n\u0010)\u001A\u00020\u0014*\u00020*\u001A\n\u0010)\u001A\u00020\u0014*\u00020#\u001A%\u0010)\u001A\u00020\u0014*\u00020$2\u0012\u0010%\u001A\n\u0012\u0006\b\u0001\u0012\u00020\'0&\"\u00020\'H\u0007\u00A2\u0006\u0002\u0010+\"\u0016\u0010\u0000\u001A\n \u0002*\u0004\u0018\u00010\u00010\u0001X\u0082\u0004\u00A2\u0006\u0002\n\u0000\"\u001C\u0010\u0003\u001A\u00020\u0004*\u00060\u0005j\u0002`\u00068@X\u0080\u0004\u00A2\u0006\u0006\u001A\u0004\b\u0003\u0010\u0007\u00A8\u0006,"}, d2 = {"logger", "Ljava/util/logging/Logger;", "kotlin.jvm.PlatformType", "isAndroidGetsocknameError", "", "Ljava/lang/AssertionError;", "Lkotlin/AssertionError;", "(Ljava/lang/AssertionError;)Z", "appendingSink", "Lokio/Sink;", "Ljava/io/File;", "asResourceFileSystem", "Lokio/FileSystem;", "Ljava/lang/ClassLoader;", "cipherSink", "Lokio/CipherSink;", "cipher", "Ljavax/crypto/Cipher;", "cipherSource", "Lokio/CipherSource;", "Lokio/Source;", "hashingSink", "Lokio/HashingSink;", "digest", "Ljava/security/MessageDigest;", "mac", "Ljavax/crypto/Mac;", "hashingSource", "Lokio/HashingSource;", "openZip", "zipPath", "Lokio/Path;", "sink", "append", "Ljava/io/OutputStream;", "Ljava/net/Socket;", "Ljava/nio/file/Path;", "options", "", "Ljava/nio/file/OpenOption;", "(Ljava/nio/file/Path;[Ljava/nio/file/OpenOption;)Lokio/Sink;", "source", "Ljava/io/InputStream;", "(Ljava/nio/file/Path;[Ljava/nio/file/OpenOption;)Lokio/Source;", "okio"}, k = 5, mv = {1, 6, 0}, xi = 0x30, xs = "okio/Okio")
final class Okio__JvmOkioKt {
    private static final Logger logger;

    static {
        Okio__JvmOkioKt.logger = Logger.getLogger("okio.Okio");
    }

    public static final Sink appendingSink(File file0) throws FileNotFoundException {
        Intrinsics.checkNotNullParameter(file0, "<this>");
        return Okio.sink(new FileOutputStream(file0, true));
    }

    public static final FileSystem asResourceFileSystem(ClassLoader classLoader0) {
        Intrinsics.checkNotNullParameter(classLoader0, "<this>");
        return new ResourceFileSystem(classLoader0, true);
    }

    public static final CipherSink cipherSink(Sink sink0, Cipher cipher0) {
        Intrinsics.checkNotNullParameter(sink0, "<this>");
        Intrinsics.checkNotNullParameter(cipher0, "cipher");
        return new CipherSink(Okio.buffer(sink0), cipher0);
    }

    public static final CipherSource cipherSource(Source source0, Cipher cipher0) {
        Intrinsics.checkNotNullParameter(source0, "<this>");
        Intrinsics.checkNotNullParameter(cipher0, "cipher");
        return new CipherSource(Okio.buffer(source0), cipher0);
    }

    public static final HashingSink hashingSink(Sink sink0, MessageDigest messageDigest0) {
        Intrinsics.checkNotNullParameter(sink0, "<this>");
        Intrinsics.checkNotNullParameter(messageDigest0, "digest");
        return new HashingSink(sink0, messageDigest0);
    }

    public static final HashingSink hashingSink(Sink sink0, Mac mac0) {
        Intrinsics.checkNotNullParameter(sink0, "<this>");
        Intrinsics.checkNotNullParameter(mac0, "mac");
        return new HashingSink(sink0, mac0);
    }

    public static final HashingSource hashingSource(Source source0, MessageDigest messageDigest0) {
        Intrinsics.checkNotNullParameter(source0, "<this>");
        Intrinsics.checkNotNullParameter(messageDigest0, "digest");
        return new HashingSource(source0, messageDigest0);
    }

    public static final HashingSource hashingSource(Source source0, Mac mac0) {
        Intrinsics.checkNotNullParameter(source0, "<this>");
        Intrinsics.checkNotNullParameter(mac0, "mac");
        return new HashingSource(source0, mac0);
    }

    public static final boolean isAndroidGetsocknameError(AssertionError assertionError0) {
        Intrinsics.checkNotNullParameter(assertionError0, "<this>");
        if(assertionError0.getCause() != null) {
            String s = assertionError0.getMessage();
            return s == null ? false : StringsKt.contains$default(s, "getsockname failed", false, 2, null);
        }
        return false;
    }

    public static final FileSystem openZip(FileSystem fileSystem0, Path path0) throws IOException {
        Intrinsics.checkNotNullParameter(fileSystem0, "<this>");
        Intrinsics.checkNotNullParameter(path0, "zipPath");
        return ZipKt.openZip$default(path0, fileSystem0, null, 4, null);
    }

    public static final Sink sink(File file0) throws FileNotFoundException {
        Intrinsics.checkNotNullParameter(file0, "<this>");
        return Okio.sink$default(file0, false, 1, null);
    }

    public static final Sink sink(File file0, boolean z) throws FileNotFoundException {
        Intrinsics.checkNotNullParameter(file0, "<this>");
        return Okio.sink(new FileOutputStream(file0, z));
    }

    public static final Sink sink(OutputStream outputStream0) {
        Intrinsics.checkNotNullParameter(outputStream0, "<this>");
        return new OutputStreamSink(outputStream0, new Timeout());
    }

    public static final Sink sink(Socket socket0) throws IOException {
        Intrinsics.checkNotNullParameter(socket0, "<this>");
        SocketAsyncTimeout socketAsyncTimeout0 = new SocketAsyncTimeout(socket0);
        OutputStream outputStream0 = socket0.getOutputStream();
        Intrinsics.checkNotNullExpressionValue(outputStream0, "getOutputStream()");
        return socketAsyncTimeout0.sink(new OutputStreamSink(outputStream0, socketAsyncTimeout0));
    }

    public static final Sink sink(java.nio.file.Path path0, OpenOption[] arr_openOption) throws IOException {
        Intrinsics.checkNotNullParameter(path0, "<this>");
        Intrinsics.checkNotNullParameter(arr_openOption, "options");
        OutputStream outputStream0 = Files.newOutputStream(path0, ((OpenOption[])Arrays.copyOf(arr_openOption, arr_openOption.length)));
        Intrinsics.checkNotNullExpressionValue(outputStream0, "newOutputStream(this, *options)");
        return Okio.sink(outputStream0);
    }

    public static Sink sink$default(File file0, boolean z, int v, Object object0) throws FileNotFoundException {
        if((v & 1) != 0) {
            z = false;
        }
        return Okio.sink(file0, z);
    }

    public static final Source source(File file0) throws FileNotFoundException {
        Intrinsics.checkNotNullParameter(file0, "<this>");
        return new InputStreamSource(new FileInputStream(file0), Timeout.NONE);
    }

    public static final Source source(InputStream inputStream0) {
        Intrinsics.checkNotNullParameter(inputStream0, "<this>");
        return new InputStreamSource(inputStream0, new Timeout());
    }

    public static final Source source(Socket socket0) throws IOException {
        Intrinsics.checkNotNullParameter(socket0, "<this>");
        SocketAsyncTimeout socketAsyncTimeout0 = new SocketAsyncTimeout(socket0);
        InputStream inputStream0 = socket0.getInputStream();
        Intrinsics.checkNotNullExpressionValue(inputStream0, "getInputStream()");
        return socketAsyncTimeout0.source(new InputStreamSource(inputStream0, socketAsyncTimeout0));
    }

    public static final Source source(java.nio.file.Path path0, OpenOption[] arr_openOption) throws IOException {
        Intrinsics.checkNotNullParameter(path0, "<this>");
        Intrinsics.checkNotNullParameter(arr_openOption, "options");
        InputStream inputStream0 = Files.newInputStream(path0, ((OpenOption[])Arrays.copyOf(arr_openOption, arr_openOption.length)));
        Intrinsics.checkNotNullExpressionValue(inputStream0, "newInputStream(this, *options)");
        return Okio.source(inputStream0);
    }
}

