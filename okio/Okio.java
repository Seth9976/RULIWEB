package okio;

import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.file.OpenOption;
import java.security.MessageDigest;
import javax.crypto.Cipher;
import javax.crypto.Mac;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

@Metadata(d1 = {"okio/Okio__JvmOkioKt", "okio/Okio__OkioKt"}, k = 4, mv = {1, 6, 0}, xi = 0x30)
public final class Okio {
    public static final Sink appendingSink(File file0) throws FileNotFoundException {
        return Okio__JvmOkioKt.appendingSink(file0);
    }

    public static final FileSystem asResourceFileSystem(ClassLoader classLoader0) {
        return Okio__JvmOkioKt.asResourceFileSystem(classLoader0);
    }

    public static final Sink blackhole() {
        return Okio__OkioKt.blackhole();
    }

    public static final BufferedSink buffer(Sink sink0) {
        return Okio__OkioKt.buffer(sink0);
    }

    public static final BufferedSource buffer(Source source0) {
        return Okio__OkioKt.buffer(source0);
    }

    public static final CipherSink cipherSink(Sink sink0, Cipher cipher0) {
        return Okio__JvmOkioKt.cipherSink(sink0, cipher0);
    }

    public static final CipherSource cipherSource(Source source0, Cipher cipher0) {
        return Okio__JvmOkioKt.cipherSource(source0, cipher0);
    }

    public static final HashingSink hashingSink(Sink sink0, MessageDigest messageDigest0) {
        return Okio__JvmOkioKt.hashingSink(sink0, messageDigest0);
    }

    public static final HashingSink hashingSink(Sink sink0, Mac mac0) {
        return Okio__JvmOkioKt.hashingSink(sink0, mac0);
    }

    public static final HashingSource hashingSource(Source source0, MessageDigest messageDigest0) {
        return Okio__JvmOkioKt.hashingSource(source0, messageDigest0);
    }

    public static final HashingSource hashingSource(Source source0, Mac mac0) {
        return Okio__JvmOkioKt.hashingSource(source0, mac0);
    }

    public static final boolean isAndroidGetsocknameError(AssertionError assertionError0) {
        return Okio__JvmOkioKt.isAndroidGetsocknameError(assertionError0);
    }

    public static final FileSystem openZip(FileSystem fileSystem0, Path path0) throws IOException {
        return Okio__JvmOkioKt.openZip(fileSystem0, path0);
    }

    public static final Sink sink(File file0) throws FileNotFoundException {
        return Okio__JvmOkioKt.sink(file0);
    }

    public static final Sink sink(File file0, boolean z) throws FileNotFoundException {
        return Okio__JvmOkioKt.sink(file0, z);
    }

    public static final Sink sink(OutputStream outputStream0) {
        return Okio__JvmOkioKt.sink(outputStream0);
    }

    public static final Sink sink(Socket socket0) throws IOException {
        return Okio__JvmOkioKt.sink(socket0);
    }

    public static final Sink sink(java.nio.file.Path path0, OpenOption[] arr_openOption) throws IOException {
        return Okio__JvmOkioKt.sink(path0, arr_openOption);
    }

    public static Sink sink$default(File file0, boolean z, int v, Object object0) throws FileNotFoundException {
        return Okio__JvmOkioKt.sink$default(file0, z, v, object0);
    }

    public static final Source source(File file0) throws FileNotFoundException {
        return Okio__JvmOkioKt.source(file0);
    }

    public static final Source source(InputStream inputStream0) {
        return Okio__JvmOkioKt.source(inputStream0);
    }

    public static final Source source(Socket socket0) throws IOException {
        return Okio__JvmOkioKt.source(socket0);
    }

    public static final Source source(java.nio.file.Path path0, OpenOption[] arr_openOption) throws IOException {
        return Okio__JvmOkioKt.source(path0, arr_openOption);
    }

    public static final Object use(Closeable closeable0, Function1 function10) {
        return Okio__OkioKt.use(closeable0, function10);
    }
}

