package kotlin.io.path;

import java.nio.charset.Charset;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystem;
import java.nio.file.FileSystemException;
import java.nio.file.FileSystemLoopException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.SecureDirectoryStream;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.FileAttributeView;
import java.time.Duration;
import java.util.List;

public final class PathTreeWalk..ExternalSyntheticApiModelOutline0 {
    public static int m(Path path0) {
        return path0.getNameCount();
    }

    public static DirectoryStream m(Object object0) [...] // Inlined contents

    public static FileSystemException m(String s, String s1, String s2) {
        return new FileSystemException(s, s1, s2);
    }

    public static FileSystemLoopException m(String s) {
        return new FileSystemLoopException(s);
    }

    public static NoSuchFileException m(String s, String s1, String s2) {
        return new NoSuchFileException(s, s1, s2);
    }

    public static Path m(FileSystem fileSystem0, String s, String[] arr_s) {
        return fileSystem0.getPath(s, arr_s);
    }

    public static Path m(Path path0) {
        return path0.normalize();
    }

    public static Path m(Path path0, int v) {
        return path0.getName(v);
    }

    public static Path m(Path path0, Path path1) {
        return path0.relativize(path1);
    }

    public static SecureDirectoryStream m(Object object0) [...] // Inlined contents

    public static StandardOpenOption m() {
        return StandardOpenOption.APPEND;
    }

    public static BasicFileAttributeView m(Object object0) {
        return (BasicFileAttributeView)object0;
    }

    public static BasicFileAttributes m(Object object0) [...] // Inlined contents

    public static FileAttributeView m(Object object0) [...] // Inlined contents

    public static Duration m(long v, long v1) {
        return Duration.ofSeconds(v, v1);
    }

    public static List m(Path path0, Charset charset0) {
        return Files.readAllLines(path0, charset0);
    }

    public static void m() {
    }

    public static void m(Path path0) {
        Files.delete(path0);
    }

    public static boolean m(Object object0) {
        return object0 instanceof SecureDirectoryStream;
    }

    public static boolean m(Path path0, LinkOption[] arr_linkOption) {
        return Files.exists(path0, arr_linkOption);
    }

    public static byte[] m(Path path0) {
        return Files.readAllBytes(path0);
    }

    public static Class m$1() {
        return BasicFileAttributeView.class;
    }

    public static Path m$1(Path path0, Path path1) {
        return Files.createLink(path0, path1);
    }

    public static Path m$1(Path path0, FileAttribute[] arr_fileAttribute) {
        return Files.createDirectory(path0, arr_fileAttribute);
    }

    public static void m$1() {
    }

    public static void m$1(SecureDirectoryStream secureDirectoryStream0, Object object0) {
        secureDirectoryStream0.deleteFile(object0);
    }

    public static boolean m$1(Path path0, Path path1) {
        return path0.startsWith(path1);
    }

    public static Class m$2() {
        return FileAttributeView.class;
    }

    public static FileVisitResult m$2() {
        return FileVisitResult.CONTINUE;
    }

    public static Path m$2(Path path0) {
        return path0.toAbsolutePath();
    }

    public static Path m$2(Path path0, FileAttribute[] arr_fileAttribute) {
        return Files.createFile(path0, arr_fileAttribute);
    }

    public static boolean m$3(Path path0) {
        return Files.isWritable(path0);
    }

    public static boolean m$4(Path path0) {
        return Files.isReadable(path0);
    }

    public static boolean m$5(Path path0) {
        return Files.isExecutable(path0);
    }
}

