package retrofit2;

import android.net.ssl.SSLSockets;
import android.security.NetworkSecurityPolicy;
import android.util.CloseGuard;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.reflect.Method;
import java.nio.file.CopyOption;
import java.nio.file.NoSuchFileException;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.CompletionStage;
import java.util.function.BiFunction;
import java.util.stream.Stream;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocket;
import kotlinx.coroutines.internal.ClassValueCtorCache.cache.1;

public final class Platform..ExternalSyntheticApiModelOutline0 {
    public static long m(BasicFileAttributes basicFileAttributes0) {
        return basicFileAttributes0.size();
    }

    public static long m(FileTime fileTime0) {
        return fileTime0.toMillis();
    }

    public static long m(Duration duration0) {
        return duration0.toMillis();
    }

    public static NetworkSecurityPolicy m() {
        return NetworkSecurityPolicy.getInstance();
    }

    public static CloseGuard m() {
        return new CloseGuard();
    }

    public static CloseGuard m(Object object0) [...] // Inlined contents

    public static Class m() {
        return CompletableFuture.class;
    }

    public static Object m(CompletableFuture completableFuture0) {
        return completableFuture0.get();
    }

    public static Object m(ClassValueCtorCache.cache.1 classValueCtorCache$cache$10, Class class0) {
        return classValueCtorCache$cache$10.get(class0);
    }

    public static String m(NoSuchFileException noSuchFileException0) {
        return noSuchFileException0.getMessage();
    }

    public static String m(SSLSocket sSLSocket0) {
        return sSLSocket0.getApplicationProtocol();
    }

    public static Throwable m(CompletionException completionException0) {
        return completionException0.getCause();
    }

    public static MethodHandles.Lookup m(Object object0) {
        return (MethodHandles.Lookup)object0;
    }

    public static CopyOption m(Object object0) [...] // Inlined contents

    public static StandardCopyOption m() {
        return StandardCopyOption.ATOMIC_MOVE;
    }

    public static FileTime m(BasicFileAttributes basicFileAttributes0) {
        return basicFileAttributes0.creationTime();
    }

    public static Duration m(ChronoUnit chronoUnit0) {
        return chronoUnit0.getDuration();
    }

    public static ChronoUnit m() {
        return ChronoUnit.MILLIS;
    }

    public static Date m(Instant instant0) {
        return Date.from(instant0);
    }

    public static Optional m(Object object0) {
        return Optional.ofNullable(object0);
    }

    public static CompletableFuture m() {
        return new CompletableFuture();
    }

    public static CompletableFuture m(CompletableFuture completableFuture0, BiFunction biFunction0) {
        return completableFuture0.handle(biFunction0);
    }

    public static CompletableFuture m(CompletionStage completionStage0) {
        return completionStage0.toCompletableFuture();
    }

    public static CompletionException m(Object object0) [...] // Inlined contents

    public static CompletionStage m(CompletionStage completionStage0, BiFunction biFunction0) {
        return completionStage0.handle(biFunction0);
    }

    public static BiFunction m(Object object0) [...] // Inlined contents

    public static void m(CloseGuard closeGuard0) {
        closeGuard0.warnIfOpen();
    }

    public static void m(CloseGuard closeGuard0, String s) {
        closeGuard0.open(s);
    }

    public static void m(Stream stream0) {
        stream0.close();
    }

    public static void m(SSLParameters sSLParameters0, String[] arr_s) {
        sSLParameters0.setApplicationProtocols(arr_s);
    }

    public static void m(SSLSocket sSLSocket0, boolean z) {
        SSLSockets.setUseSessionTickets(sSLSocket0, z);
    }

    public static boolean m(NetworkSecurityPolicy networkSecurityPolicy0) {
        return networkSecurityPolicy0.isCleartextTrafficPermitted();
    }

    public static boolean m(NetworkSecurityPolicy networkSecurityPolicy0, String s) {
        return networkSecurityPolicy0.isCleartextTrafficPermitted(s);
    }

    public static boolean m(Object object0) {
        return object0 instanceof CompletionException;
    }

    public static boolean m(Method method0) {
        return method0.isDefault();
    }

    public static boolean m(BasicFileAttributes basicFileAttributes0) {
        return basicFileAttributes0.isSymbolicLink();
    }

    public static boolean m(CompletableFuture completableFuture0) {
        return completableFuture0.isDone();
    }

    public static boolean m(CompletableFuture completableFuture0, boolean z) {
        return completableFuture0.cancel(z);
    }

    public static boolean m(SSLSocket sSLSocket0) {
        return SSLSockets.isSupportedSocket(sSLSocket0);
    }

    public static Class m$1() {
        return Optional.class;
    }

    public static FileTime m$1(BasicFileAttributes basicFileAttributes0) {
        return basicFileAttributes0.lastModifiedTime();
    }

    public static boolean m$1(BasicFileAttributes basicFileAttributes0) {
        return basicFileAttributes0.isRegularFile();
    }

    public static Class m$2() {
        return MethodHandles.Lookup.class;
    }

    public static FileTime m$2(BasicFileAttributes basicFileAttributes0) {
        return basicFileAttributes0.lastAccessTime();
    }
}

