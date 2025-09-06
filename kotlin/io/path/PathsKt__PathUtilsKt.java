package kotlin.io.path;

import java.io.Closeable;
import java.io.IOException;
import java.net.URI;
import java.nio.file.CopyOption;
import java.nio.file.DirectoryStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.FileStore;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.FileAttributeView;
import java.nio.file.attribute.FileTime;
import java.nio.file.attribute.UserPrincipal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000\u00CC\u0001\n\u0000\n\u0002\u0010\u000E\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010\u0011\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001A\u0011\u0010\u0016\u001A\u00020\u00022\u0006\u0010\u0017\u001A\u00020\u0001H\u0087\b\u001A*\u0010\u0016\u001A\u00020\u00022\u0006\u0010\u0018\u001A\u00020\u00012\u0012\u0010\u0019\u001A\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u001A\"\u00020\u0001H\u0087\b\u00A2\u0006\u0002\u0010\u001B\u001A?\u0010\u001C\u001A\u00020\u00022\b\u0010\u001D\u001A\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u001E\u001A\u0004\u0018\u00010\u00012\u001A\u0010\u001F\u001A\u000E\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030 0\u001A\"\u0006\u0012\u0002\b\u00030 H\u0007\u00A2\u0006\u0002\u0010!\u001A6\u0010\u001C\u001A\u00020\u00022\n\b\u0002\u0010\u001E\u001A\u0004\u0018\u00010\u00012\u001A\u0010\u001F\u001A\u000E\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030 0\u001A\"\u0006\u0012\u0002\b\u00030 H\u0087\b\u00A2\u0006\u0002\u0010\"\u001AK\u0010#\u001A\u00020\u00022\b\u0010\u001D\u001A\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u001E\u001A\u0004\u0018\u00010\u00012\n\b\u0002\u0010$\u001A\u0004\u0018\u00010\u00012\u001A\u0010\u001F\u001A\u000E\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030 0\u001A\"\u0006\u0012\u0002\b\u00030 H\u0007\u00A2\u0006\u0002\u0010%\u001AB\u0010#\u001A\u00020\u00022\n\b\u0002\u0010\u001E\u001A\u0004\u0018\u00010\u00012\n\b\u0002\u0010$\u001A\u0004\u0018\u00010\u00012\u001A\u0010\u001F\u001A\u000E\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030 0\u001A\"\u0006\u0012\u0002\b\u00030 H\u0087\b\u00A2\u0006\u0002\u0010&\u001A\u001C\u0010\'\u001A\u00020(2\u0006\u0010\u0017\u001A\u00020\u00022\n\u0010)\u001A\u0006\u0012\u0002\b\u00030*H\u0001\u001A4\u0010+\u001A\b\u0012\u0004\u0012\u00020\u00020,2\u0017\u0010-\u001A\u0013\u0012\u0004\u0012\u00020/\u0012\u0004\u0012\u0002000.\u00A2\u0006\u0002\b1H\u0007\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\u001A\r\u00102\u001A\u00020\u0002*\u00020\u0002H\u0087\b\u001A\r\u00103\u001A\u00020\u0001*\u00020\u0002H\u0087\b\u001A.\u00104\u001A\u00020\u0002*\u00020\u00022\u0006\u00105\u001A\u00020\u00022\u0012\u00106\u001A\n\u0012\u0006\b\u0001\u0012\u0002070\u001A\"\u000207H\u0087\b\u00A2\u0006\u0002\u00108\u001A\u001F\u00104\u001A\u00020\u0002*\u00020\u00022\u0006\u00105\u001A\u00020\u00022\b\b\u0002\u00109\u001A\u00020:H\u0087\b\u001A.\u0010;\u001A\u00020\u0002*\u00020\u00022\u001A\u0010\u001F\u001A\u000E\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030 0\u001A\"\u0006\u0012\u0002\b\u00030 H\u0087\b\u00A2\u0006\u0002\u0010<\u001A.\u0010=\u001A\u00020\u0002*\u00020\u00022\u001A\u0010\u001F\u001A\u000E\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030 0\u001A\"\u0006\u0012\u0002\b\u00030 H\u0087\b\u00A2\u0006\u0002\u0010<\u001A.\u0010>\u001A\u00020\u0002*\u00020\u00022\u001A\u0010\u001F\u001A\u000E\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030 0\u001A\"\u0006\u0012\u0002\b\u00030 H\u0087\b\u00A2\u0006\u0002\u0010<\u001A\u0015\u0010?\u001A\u00020\u0002*\u00020\u00022\u0006\u00105\u001A\u00020\u0002H\u0087\b\u001A-\u0010@\u001A\u00020\u0002*\u00020\u00022\u001A\u0010\u001F\u001A\u000E\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030 0\u001A\"\u0006\u0012\u0002\b\u00030 H\u0007\u00A2\u0006\u0002\u0010<\u001A6\u0010A\u001A\u00020\u0002*\u00020\u00022\u0006\u00105\u001A\u00020\u00022\u001A\u0010\u001F\u001A\u000E\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030 0\u001A\"\u0006\u0012\u0002\b\u00030 H\u0087\b\u00A2\u0006\u0002\u0010B\u001A\r\u0010C\u001A\u000200*\u00020\u0002H\u0087\b\u001A\r\u0010D\u001A\u00020:*\u00020\u0002H\u0087\b\u001A\u0015\u0010E\u001A\u00020\u0002*\u00020\u00022\u0006\u0010F\u001A\u00020\u0002H\u0087\n\u001A\u0015\u0010E\u001A\u00020\u0002*\u00020\u00022\u0006\u0010F\u001A\u00020\u0001H\u0087\n\u001A&\u0010G\u001A\u00020:*\u00020\u00022\u0012\u00106\u001A\n\u0012\u0006\b\u0001\u0012\u00020H0\u001A\"\u00020HH\u0087\b\u00A2\u0006\u0002\u0010I\u001A2\u0010J\u001A\u0002HK\"\n\b\u0000\u0010K\u0018\u0001*\u00020L*\u00020\u00022\u0012\u00106\u001A\n\u0012\u0006\b\u0001\u0012\u00020H0\u001A\"\u00020HH\u0087\b\u00A2\u0006\u0002\u0010M\u001A4\u0010N\u001A\u0004\u0018\u0001HK\"\n\b\u0000\u0010K\u0018\u0001*\u00020L*\u00020\u00022\u0012\u00106\u001A\n\u0012\u0006\b\u0001\u0012\u00020H0\u001A\"\u00020HH\u0087\b\u00A2\u0006\u0002\u0010M\u001A\r\u0010O\u001A\u00020P*\u00020\u0002H\u0087\b\u001A\r\u0010Q\u001A\u00020R*\u00020\u0002H\u0087\b\u001A.\u0010S\u001A\u000200*\u00020\u00022\b\b\u0002\u0010T\u001A\u00020\u00012\u0012\u0010U\u001A\u000E\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u0002000.H\u0087\b\u00F8\u0001\u0000\u001A0\u0010V\u001A\u0004\u0018\u00010W*\u00020\u00022\u0006\u0010X\u001A\u00020\u00012\u0012\u00106\u001A\n\u0012\u0006\b\u0001\u0012\u00020H0\u001A\"\u00020HH\u0087\b\u00A2\u0006\u0002\u0010Y\u001A&\u0010Z\u001A\u00020[*\u00020\u00022\u0012\u00106\u001A\n\u0012\u0006\b\u0001\u0012\u00020H0\u001A\"\u00020HH\u0087\b\u00A2\u0006\u0002\u0010\\\u001A(\u0010]\u001A\u0004\u0018\u00010^*\u00020\u00022\u0012\u00106\u001A\n\u0012\u0006\b\u0001\u0012\u00020H0\u001A\"\u00020HH\u0087\b\u00A2\u0006\u0002\u0010_\u001A,\u0010`\u001A\b\u0012\u0004\u0012\u00020b0a*\u00020\u00022\u0012\u00106\u001A\n\u0012\u0006\b\u0001\u0012\u00020H0\u001A\"\u00020HH\u0087\b\u00A2\u0006\u0002\u0010c\u001A&\u0010d\u001A\u00020:*\u00020\u00022\u0012\u00106\u001A\n\u0012\u0006\b\u0001\u0012\u00020H0\u001A\"\u00020HH\u0087\b\u00A2\u0006\u0002\u0010I\u001A\r\u0010e\u001A\u00020:*\u00020\u0002H\u0087\b\u001A\r\u0010f\u001A\u00020:*\u00020\u0002H\u0087\b\u001A\r\u0010g\u001A\u00020:*\u00020\u0002H\u0087\b\u001A&\u0010h\u001A\u00020:*\u00020\u00022\u0012\u00106\u001A\n\u0012\u0006\b\u0001\u0012\u00020H0\u001A\"\u00020HH\u0087\b\u00A2\u0006\u0002\u0010I\u001A\u0015\u0010i\u001A\u00020:*\u00020\u00022\u0006\u0010F\u001A\u00020\u0002H\u0087\b\u001A\r\u0010j\u001A\u00020:*\u00020\u0002H\u0087\b\u001A\r\u0010k\u001A\u00020:*\u00020\u0002H\u0087\b\u001A\u001C\u0010l\u001A\b\u0012\u0004\u0012\u00020\u00020m*\u00020\u00022\b\b\u0002\u0010T\u001A\u00020\u0001H\u0007\u001A.\u0010n\u001A\u00020\u0002*\u00020\u00022\u0006\u00105\u001A\u00020\u00022\u0012\u00106\u001A\n\u0012\u0006\b\u0001\u0012\u0002070\u001A\"\u000207H\u0087\b\u00A2\u0006\u0002\u00108\u001A\u001F\u0010n\u001A\u00020\u0002*\u00020\u00022\u0006\u00105\u001A\u00020\u00022\b\b\u0002\u00109\u001A\u00020:H\u0087\b\u001A&\u0010o\u001A\u00020:*\u00020\u00022\u0012\u00106\u001A\n\u0012\u0006\b\u0001\u0012\u00020H0\u001A\"\u00020HH\u0087\b\u00A2\u0006\u0002\u0010I\u001A2\u0010p\u001A\u0002Hq\"\n\b\u0000\u0010q\u0018\u0001*\u00020r*\u00020\u00022\u0012\u00106\u001A\n\u0012\u0006\b\u0001\u0012\u00020H0\u001A\"\u00020HH\u0087\b\u00A2\u0006\u0002\u0010s\u001A<\u0010p\u001A\u0010\u0012\u0004\u0012\u00020\u0001\u0012\u0006\u0012\u0004\u0018\u00010W0t*\u00020\u00022\u0006\u0010\u001F\u001A\u00020\u00012\u0012\u00106\u001A\n\u0012\u0006\b\u0001\u0012\u00020H0\u001A\"\u00020HH\u0087\b\u00A2\u0006\u0002\u0010u\u001A\r\u0010v\u001A\u00020\u0002*\u00020\u0002H\u0087\b\u001A\u0014\u0010w\u001A\u00020\u0002*\u00020\u00022\u0006\u0010\u0018\u001A\u00020\u0002H\u0007\u001A\u0016\u0010x\u001A\u0004\u0018\u00010\u0002*\u00020\u00022\u0006\u0010\u0018\u001A\u00020\u0002H\u0007\u001A\u0014\u0010y\u001A\u00020\u0002*\u00020\u00022\u0006\u0010\u0018\u001A\u00020\u0002H\u0007\u001A8\u0010z\u001A\u00020\u0002*\u00020\u00022\u0006\u0010X\u001A\u00020\u00012\b\u0010{\u001A\u0004\u0018\u00010W2\u0012\u00106\u001A\n\u0012\u0006\b\u0001\u0012\u00020H0\u001A\"\u00020HH\u0087\b\u00A2\u0006\u0002\u0010|\u001A\u0015\u0010}\u001A\u00020\u0002*\u00020\u00022\u0006\u0010{\u001A\u00020[H\u0087\b\u001A\u0015\u0010~\u001A\u00020\u0002*\u00020\u00022\u0006\u0010{\u001A\u00020^H\u0087\b\u001A\u001B\u0010\u007F\u001A\u00020\u0002*\u00020\u00022\f\u0010{\u001A\b\u0012\u0004\u0012\u00020b0aH\u0087\b\u001A\u000F\u0010\u0080\u0001\u001A\u00020\u0002*\u00030\u0081\u0001H\u0087\b\u001AF\u0010\u0082\u0001\u001A\u0003H\u0083\u0001\"\u0005\b\u0000\u0010\u0083\u0001*\u00020\u00022\b\b\u0002\u0010T\u001A\u00020\u00012\u001B\u0010\u0084\u0001\u001A\u0016\u0012\u000B\u0012\t\u0012\u0004\u0012\u00020\u00020\u0085\u0001\u0012\u0005\u0012\u0003H\u0083\u00010.H\u0087\b\u00F8\u0001\u0000\u00A2\u0006\u0003\u0010\u0086\u0001\u001A3\u0010\u0087\u0001\u001A\u000200*\u00020\u00022\r\u0010\u0088\u0001\u001A\b\u0012\u0004\u0012\u00020\u00020,2\n\b\u0002\u0010\u0089\u0001\u001A\u00030\u008A\u00012\t\b\u0002\u0010\u008B\u0001\u001A\u00020:H\u0007\u001AJ\u0010\u0087\u0001\u001A\u000200*\u00020\u00022\n\b\u0002\u0010\u0089\u0001\u001A\u00030\u008A\u00012\t\b\u0002\u0010\u008B\u0001\u001A\u00020:2\u0017\u0010-\u001A\u0013\u0012\u0004\u0012\u00020/\u0012\u0004\u0012\u0002000.\u00A2\u0006\u0002\b1H\u0007\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0003 \u0001\u001A0\u0010\u008C\u0001\u001A\t\u0012\u0004\u0012\u00020\u00020\u0085\u0001*\u00020\u00022\u0014\u00106\u001A\u000B\u0012\u0007\b\u0001\u0012\u00030\u008D\u00010\u001A\"\u00030\u008D\u0001H\u0007\u00A2\u0006\u0003\u0010\u008E\u0001\"\u001E\u0010\u0000\u001A\u00020\u0001*\u00020\u00028FX\u0087\u0004\u00A2\u0006\f\u0012\u0004\b\u0003\u0010\u0004\u001A\u0004\b\u0005\u0010\u0006\"\u001F\u0010\u0007\u001A\u00020\u0001*\u00020\u00028\u00C6\u0002X\u0087\u0004\u00A2\u0006\f\u0012\u0004\b\b\u0010\u0004\u001A\u0004\b\t\u0010\u0006\"\u001E\u0010\n\u001A\u00020\u0001*\u00020\u00028FX\u0087\u0004\u00A2\u0006\f\u0012\u0004\b\u000B\u0010\u0004\u001A\u0004\b\f\u0010\u0006\"\u001E\u0010\r\u001A\u00020\u0001*\u00020\u00028FX\u0087\u0004\u00A2\u0006\f\u0012\u0004\b\u000E\u0010\u0004\u001A\u0004\b\u000F\u0010\u0006\"\u001E\u0010\u0010\u001A\u00020\u0001*\u00020\u00028FX\u0087\u0004\u00A2\u0006\f\u0012\u0004\b\u0011\u0010\u0004\u001A\u0004\b\u0012\u0010\u0006\"\u001F\u0010\u0013\u001A\u00020\u0001*\u00020\u00028\u00C6\u0002X\u0087\u0004\u00A2\u0006\f\u0012\u0004\b\u0014\u0010\u0004\u001A\u0004\b\u0015\u0010\u0006\u0082\u0002\u0007\n\u0005\b\u009920\u0001\u00A8\u0006\u008F\u0001"}, d2 = {"extension", "", "Ljava/nio/file/Path;", "getExtension$annotations", "(Ljava/nio/file/Path;)V", "getExtension", "(Ljava/nio/file/Path;)Ljava/lang/String;", "invariantSeparatorsPath", "getInvariantSeparatorsPath$annotations", "getInvariantSeparatorsPath", "invariantSeparatorsPathString", "getInvariantSeparatorsPathString$annotations", "getInvariantSeparatorsPathString", "name", "getName$annotations", "getName", "nameWithoutExtension", "getNameWithoutExtension$annotations", "getNameWithoutExtension", "pathString", "getPathString$annotations", "getPathString", "Path", "path", "base", "subpaths", "", "(Ljava/lang/String;[Ljava/lang/String;)Ljava/nio/file/Path;", "createTempDirectory", "directory", "prefix", "attributes", "Ljava/nio/file/attribute/FileAttribute;", "(Ljava/nio/file/Path;Ljava/lang/String;[Ljava/nio/file/attribute/FileAttribute;)Ljava/nio/file/Path;", "(Ljava/lang/String;[Ljava/nio/file/attribute/FileAttribute;)Ljava/nio/file/Path;", "createTempFile", "suffix", "(Ljava/nio/file/Path;Ljava/lang/String;Ljava/lang/String;[Ljava/nio/file/attribute/FileAttribute;)Ljava/nio/file/Path;", "(Ljava/lang/String;Ljava/lang/String;[Ljava/nio/file/attribute/FileAttribute;)Ljava/nio/file/Path;", "fileAttributeViewNotAvailable", "", "attributeViewClass", "Ljava/lang/Class;", "fileVisitor", "Ljava/nio/file/FileVisitor;", "builderAction", "Lkotlin/Function1;", "Lkotlin/io/path/FileVisitorBuilder;", "", "Lkotlin/ExtensionFunctionType;", "absolute", "absolutePathString", "copyTo", "target", "options", "Ljava/nio/file/CopyOption;", "(Ljava/nio/file/Path;Ljava/nio/file/Path;[Ljava/nio/file/CopyOption;)Ljava/nio/file/Path;", "overwrite", "", "createDirectories", "(Ljava/nio/file/Path;[Ljava/nio/file/attribute/FileAttribute;)Ljava/nio/file/Path;", "createDirectory", "createFile", "createLinkPointingTo", "createParentDirectories", "createSymbolicLinkPointingTo", "(Ljava/nio/file/Path;Ljava/nio/file/Path;[Ljava/nio/file/attribute/FileAttribute;)Ljava/nio/file/Path;", "deleteExisting", "deleteIfExists", "div", "other", "exists", "Ljava/nio/file/LinkOption;", "(Ljava/nio/file/Path;[Ljava/nio/file/LinkOption;)Z", "fileAttributesView", "V", "Ljava/nio/file/attribute/FileAttributeView;", "(Ljava/nio/file/Path;[Ljava/nio/file/LinkOption;)Ljava/nio/file/attribute/FileAttributeView;", "fileAttributesViewOrNull", "fileSize", "", "fileStore", "Ljava/nio/file/FileStore;", "forEachDirectoryEntry", "glob", "action", "getAttribute", "", "attribute", "(Ljava/nio/file/Path;Ljava/lang/String;[Ljava/nio/file/LinkOption;)Ljava/lang/Object;", "getLastModifiedTime", "Ljava/nio/file/attribute/FileTime;", "(Ljava/nio/file/Path;[Ljava/nio/file/LinkOption;)Ljava/nio/file/attribute/FileTime;", "getOwner", "Ljava/nio/file/attribute/UserPrincipal;", "(Ljava/nio/file/Path;[Ljava/nio/file/LinkOption;)Ljava/nio/file/attribute/UserPrincipal;", "getPosixFilePermissions", "", "Ljava/nio/file/attribute/PosixFilePermission;", "(Ljava/nio/file/Path;[Ljava/nio/file/LinkOption;)Ljava/util/Set;", "isDirectory", "isExecutable", "isHidden", "isReadable", "isRegularFile", "isSameFileAs", "isSymbolicLink", "isWritable", "listDirectoryEntries", "", "moveTo", "notExists", "readAttributes", "A", "Ljava/nio/file/attribute/BasicFileAttributes;", "(Ljava/nio/file/Path;[Ljava/nio/file/LinkOption;)Ljava/nio/file/attribute/BasicFileAttributes;", "", "(Ljava/nio/file/Path;Ljava/lang/String;[Ljava/nio/file/LinkOption;)Ljava/util/Map;", "readSymbolicLink", "relativeTo", "relativeToOrNull", "relativeToOrSelf", "setAttribute", "value", "(Ljava/nio/file/Path;Ljava/lang/String;Ljava/lang/Object;[Ljava/nio/file/LinkOption;)Ljava/nio/file/Path;", "setLastModifiedTime", "setOwner", "setPosixFilePermissions", "toPath", "Ljava/net/URI;", "useDirectoryEntries", "T", "block", "Lkotlin/sequences/Sequence;", "(Ljava/nio/file/Path;Ljava/lang/String;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "visitFileTree", "visitor", "maxDepth", "", "followLinks", "walk", "Lkotlin/io/path/PathWalkOption;", "(Ljava/nio/file/Path;[Lkotlin/io/path/PathWalkOption;)Lkotlin/sequences/Sequence;", "kotlin-stdlib-jdk7"}, k = 5, mv = {1, 9, 0}, xi = 49, xs = "kotlin/io/path/PathsKt")
class PathsKt__PathUtilsKt extends PathsKt__PathRecursiveFunctionsKt {
    private static final Path Path(String s) {
        Intrinsics.checkNotNullParameter(s, "path");
        Path path0 = Paths.get(s, new String[0]);
        Intrinsics.checkNotNullExpressionValue(path0, "get(path)");
        return path0;
    }

    private static final Path Path(String s, String[] arr_s) {
        Intrinsics.checkNotNullParameter(s, "base");
        Intrinsics.checkNotNullParameter(arr_s, "subpaths");
        Path path0 = Paths.get(s, ((String[])Arrays.copyOf(arr_s, arr_s.length)));
        Intrinsics.checkNotNullExpressionValue(path0, "get(base, *subpaths)");
        return path0;
    }

    private static final Path absolute(Path path0) {
        Intrinsics.checkNotNullParameter(path0, "<this>");
        Path path1 = PathTreeWalk..ExternalSyntheticApiModelOutline0.m$2(path0);
        Intrinsics.checkNotNullExpressionValue(path1, "toAbsolutePath()");
        return path1;
    }

    private static final String absolutePathString(Path path0) {
        Intrinsics.checkNotNullParameter(path0, "<this>");
        return PathTreeWalk..ExternalSyntheticApiModelOutline0.m$2(path0).toString();
    }

    private static final Path copyTo(Path path0, Path path1, boolean z) throws IOException {
        Intrinsics.checkNotNullParameter(path0, "<this>");
        Intrinsics.checkNotNullParameter(path1, "target");
        CopyOption[] arr_copyOption = z ? new CopyOption[]{StandardCopyOption.REPLACE_EXISTING} : new CopyOption[0];
        Path path2 = Files.copy(path0, path1, ((CopyOption[])Arrays.copyOf(arr_copyOption, arr_copyOption.length)));
        Intrinsics.checkNotNullExpressionValue(path2, "copy(this, target, *options)");
        return path2;
    }

    private static final Path copyTo(Path path0, Path path1, CopyOption[] arr_copyOption) throws IOException {
        Intrinsics.checkNotNullParameter(path0, "<this>");
        Intrinsics.checkNotNullParameter(path1, "target");
        Intrinsics.checkNotNullParameter(arr_copyOption, "options");
        Path path2 = Files.copy(path0, path1, ((CopyOption[])Arrays.copyOf(arr_copyOption, arr_copyOption.length)));
        Intrinsics.checkNotNullExpressionValue(path2, "copy(this, target, *options)");
        return path2;
    }

    static Path copyTo$default(Path path0, Path path1, boolean z, int v, Object object0) throws IOException {
        if((v & 2) != 0) {
            z = false;
        }
        Intrinsics.checkNotNullParameter(path0, "<this>");
        Intrinsics.checkNotNullParameter(path1, "target");
        CopyOption[] arr_copyOption = z ? new CopyOption[]{StandardCopyOption.REPLACE_EXISTING} : new CopyOption[0];
        Path path2 = Files.copy(path0, path1, ((CopyOption[])Arrays.copyOf(arr_copyOption, arr_copyOption.length)));
        Intrinsics.checkNotNullExpressionValue(path2, "copy(this, target, *options)");
        return path2;
    }

    private static final Path createDirectories(Path path0, FileAttribute[] arr_fileAttribute) throws IOException {
        Intrinsics.checkNotNullParameter(path0, "<this>");
        Intrinsics.checkNotNullParameter(arr_fileAttribute, "attributes");
        Path path1 = Files.createDirectories(path0, ((FileAttribute[])Arrays.copyOf(arr_fileAttribute, arr_fileAttribute.length)));
        Intrinsics.checkNotNullExpressionValue(path1, "createDirectories(this, *attributes)");
        return path1;
    }

    private static final Path createDirectory(Path path0, FileAttribute[] arr_fileAttribute) throws IOException {
        Intrinsics.checkNotNullParameter(path0, "<this>");
        Intrinsics.checkNotNullParameter(arr_fileAttribute, "attributes");
        Path path1 = PathTreeWalk..ExternalSyntheticApiModelOutline0.m$1(path0, ((FileAttribute[])Arrays.copyOf(arr_fileAttribute, arr_fileAttribute.length)));
        Intrinsics.checkNotNullExpressionValue(path1, "createDirectory(this, *attributes)");
        return path1;
    }

    private static final Path createFile(Path path0, FileAttribute[] arr_fileAttribute) throws IOException {
        Intrinsics.checkNotNullParameter(path0, "<this>");
        Intrinsics.checkNotNullParameter(arr_fileAttribute, "attributes");
        Path path1 = PathTreeWalk..ExternalSyntheticApiModelOutline0.m$2(path0, ((FileAttribute[])Arrays.copyOf(arr_fileAttribute, arr_fileAttribute.length)));
        Intrinsics.checkNotNullExpressionValue(path1, "createFile(this, *attributes)");
        return path1;
    }

    private static final Path createLinkPointingTo(Path path0, Path path1) throws IOException {
        Intrinsics.checkNotNullParameter(path0, "<this>");
        Intrinsics.checkNotNullParameter(path1, "target");
        Path path2 = PathTreeWalk..ExternalSyntheticApiModelOutline0.m$1(path0, path1);
        Intrinsics.checkNotNullExpressionValue(path2, "createLink(this, target)");
        return path2;
    }

    public static final Path createParentDirectories(Path path0, FileAttribute[] arr_fileAttribute) throws IOException {
        Intrinsics.checkNotNullParameter(path0, "<this>");
        Intrinsics.checkNotNullParameter(arr_fileAttribute, "attributes");
        Path path1 = LinkFollowing..ExternalSyntheticApiModelOutline0.m$1(path0);
        if(path1 != null && !LinkFollowing..ExternalSyntheticApiModelOutline0.m(path1, ((LinkOption[])Arrays.copyOf(new LinkOption[0], 0)))) {
            try {
                FileAttribute[] arr_fileAttribute1 = (FileAttribute[])Arrays.copyOf(arr_fileAttribute, arr_fileAttribute.length);
                Intrinsics.checkNotNullExpressionValue(Files.createDirectories(path1, ((FileAttribute[])Arrays.copyOf(arr_fileAttribute1, arr_fileAttribute1.length))), "createDirectories(this, *attributes)");
                return path0;
            }
            catch(FileAlreadyExistsException fileAlreadyExistsException0) {
                if(!LinkFollowing..ExternalSyntheticApiModelOutline0.m(path1, ((LinkOption[])Arrays.copyOf(new LinkOption[0], 0)))) {
                    throw fileAlreadyExistsException0;
                }
            }
        }
        return path0;
    }

    private static final Path createSymbolicLinkPointingTo(Path path0, Path path1, FileAttribute[] arr_fileAttribute) throws IOException {
        Intrinsics.checkNotNullParameter(path0, "<this>");
        Intrinsics.checkNotNullParameter(path1, "target");
        Intrinsics.checkNotNullParameter(arr_fileAttribute, "attributes");
        Path path2 = Files.createSymbolicLink(path0, path1, ((FileAttribute[])Arrays.copyOf(arr_fileAttribute, arr_fileAttribute.length)));
        Intrinsics.checkNotNullExpressionValue(path2, "createSymbolicLink(this, target, *attributes)");
        return path2;
    }

    private static final Path createTempDirectory(String s, FileAttribute[] arr_fileAttribute) throws IOException {
        Intrinsics.checkNotNullParameter(arr_fileAttribute, "attributes");
        Path path0 = Files.createTempDirectory(s, ((FileAttribute[])Arrays.copyOf(arr_fileAttribute, arr_fileAttribute.length)));
        Intrinsics.checkNotNullExpressionValue(path0, "createTempDirectory(prefix, *attributes)");
        return path0;
    }

    public static final Path createTempDirectory(Path path0, String s, FileAttribute[] arr_fileAttribute) throws IOException {
        Intrinsics.checkNotNullParameter(arr_fileAttribute, "attributes");
        if(path0 != null) {
            Path path1 = Files.createTempDirectory(path0, s, ((FileAttribute[])Arrays.copyOf(arr_fileAttribute, arr_fileAttribute.length)));
            Intrinsics.checkNotNullExpressionValue(path1, "createTempDirectory(dire…ory, prefix, *attributes)");
            return path1;
        }
        Path path2 = Files.createTempDirectory(s, ((FileAttribute[])Arrays.copyOf(arr_fileAttribute, arr_fileAttribute.length)));
        Intrinsics.checkNotNullExpressionValue(path2, "createTempDirectory(prefix, *attributes)");
        return path2;
    }

    static Path createTempDirectory$default(String s, FileAttribute[] arr_fileAttribute, int v, Object object0) throws IOException {
        if((v & 1) != 0) {
            s = null;
        }
        Intrinsics.checkNotNullParameter(arr_fileAttribute, "attributes");
        Path path0 = Files.createTempDirectory(s, ((FileAttribute[])Arrays.copyOf(arr_fileAttribute, arr_fileAttribute.length)));
        Intrinsics.checkNotNullExpressionValue(path0, "createTempDirectory(prefix, *attributes)");
        return path0;
    }

    public static Path createTempDirectory$default(Path path0, String s, FileAttribute[] arr_fileAttribute, int v, Object object0) throws IOException {
        if((v & 2) != 0) {
            s = null;
        }
        return PathsKt.createTempDirectory(path0, s, arr_fileAttribute);
    }

    private static final Path createTempFile(String s, String s1, FileAttribute[] arr_fileAttribute) throws IOException {
        Intrinsics.checkNotNullParameter(arr_fileAttribute, "attributes");
        Path path0 = Files.createTempFile(s, s1, ((FileAttribute[])Arrays.copyOf(arr_fileAttribute, arr_fileAttribute.length)));
        Intrinsics.checkNotNullExpressionValue(path0, "createTempFile(prefix, suffix, *attributes)");
        return path0;
    }

    public static final Path createTempFile(Path path0, String s, String s1, FileAttribute[] arr_fileAttribute) throws IOException {
        Intrinsics.checkNotNullParameter(arr_fileAttribute, "attributes");
        if(path0 != null) {
            Path path1 = Files.createTempFile(path0, s, s1, ((FileAttribute[])Arrays.copyOf(arr_fileAttribute, arr_fileAttribute.length)));
            Intrinsics.checkNotNullExpressionValue(path1, "createTempFile(directory…fix, suffix, *attributes)");
            return path1;
        }
        Path path2 = Files.createTempFile(s, s1, ((FileAttribute[])Arrays.copyOf(arr_fileAttribute, arr_fileAttribute.length)));
        Intrinsics.checkNotNullExpressionValue(path2, "createTempFile(prefix, suffix, *attributes)");
        return path2;
    }

    static Path createTempFile$default(String s, String s1, FileAttribute[] arr_fileAttribute, int v, Object object0) throws IOException {
        if((v & 1) != 0) {
            s = null;
        }
        if((v & 2) != 0) {
            s1 = null;
        }
        Intrinsics.checkNotNullParameter(arr_fileAttribute, "attributes");
        Path path0 = Files.createTempFile(s, s1, ((FileAttribute[])Arrays.copyOf(arr_fileAttribute, arr_fileAttribute.length)));
        Intrinsics.checkNotNullExpressionValue(path0, "createTempFile(prefix, suffix, *attributes)");
        return path0;
    }

    public static Path createTempFile$default(Path path0, String s, String s1, FileAttribute[] arr_fileAttribute, int v, Object object0) throws IOException {
        if((v & 2) != 0) {
            s = null;
        }
        if((v & 4) != 0) {
            s1 = null;
        }
        return PathsKt.createTempFile(path0, s, s1, arr_fileAttribute);
    }

    private static final void deleteExisting(Path path0) throws IOException {
        Intrinsics.checkNotNullParameter(path0, "<this>");
        PathTreeWalk..ExternalSyntheticApiModelOutline0.m(path0);
    }

    private static final boolean deleteIfExists(Path path0) throws IOException {
        Intrinsics.checkNotNullParameter(path0, "<this>");
        return PathTreeWalk..ExternalSyntheticApiModelOutline0.m(path0);
    }

    private static final Path div(Path path0, String s) {
        Intrinsics.checkNotNullParameter(path0, "<this>");
        Intrinsics.checkNotNullParameter(s, "other");
        Path path1 = path0.resolve(s);
        Intrinsics.checkNotNullExpressionValue(path1, "this.resolve(other)");
        return path1;
    }

    private static final Path div(Path path0, Path path1) {
        Intrinsics.checkNotNullParameter(path0, "<this>");
        Intrinsics.checkNotNullParameter(path1, "other");
        Path path2 = path0.resolve(path1);
        Intrinsics.checkNotNullExpressionValue(path2, "this.resolve(other)");
        return path2;
    }

    private static final boolean exists(Path path0, LinkOption[] arr_linkOption) {
        Intrinsics.checkNotNullParameter(path0, "<this>");
        Intrinsics.checkNotNullParameter(arr_linkOption, "options");
        return PathTreeWalk..ExternalSyntheticApiModelOutline0.m(path0, ((LinkOption[])Arrays.copyOf(arr_linkOption, arr_linkOption.length)));
    }

    public static final Void fileAttributeViewNotAvailable(Path path0, Class class0) {
        Intrinsics.checkNotNullParameter(path0, "path");
        Intrinsics.checkNotNullParameter(class0, "attributeViewClass");
        throw new UnsupportedOperationException("The desired attribute view type " + class0 + " is not available for the file " + path0 + '.');
    }

    private static final FileAttributeView fileAttributesView(Path path0, LinkOption[] arr_linkOption) {
        Intrinsics.checkNotNullParameter(path0, "<this>");
        Intrinsics.checkNotNullParameter(arr_linkOption, "options");
        Intrinsics.reifiedOperationMarker(4, "V");
        FileAttributeView fileAttributeView0 = Files.getFileAttributeView(path0, PathTreeWalk..ExternalSyntheticApiModelOutline0.m$2(), ((LinkOption[])Arrays.copyOf(arr_linkOption, arr_linkOption.length)));
        if(fileAttributeView0 != null) {
            return fileAttributeView0;
        }
        Intrinsics.reifiedOperationMarker(4, "V");
        PathsKt.fileAttributeViewNotAvailable(path0, PathTreeWalk..ExternalSyntheticApiModelOutline0.m$2());
        throw new KotlinNothingValueException();
    }

    private static final FileAttributeView fileAttributesViewOrNull(Path path0, LinkOption[] arr_linkOption) {
        Intrinsics.checkNotNullParameter(path0, "<this>");
        Intrinsics.checkNotNullParameter(arr_linkOption, "options");
        Intrinsics.reifiedOperationMarker(4, "V");
        return Files.getFileAttributeView(path0, PathTreeWalk..ExternalSyntheticApiModelOutline0.m$2(), ((LinkOption[])Arrays.copyOf(arr_linkOption, arr_linkOption.length)));
    }

    private static final long fileSize(Path path0) throws IOException {
        Intrinsics.checkNotNullParameter(path0, "<this>");
        return Files.size(path0);
    }

    private static final FileStore fileStore(Path path0) throws IOException {
        Intrinsics.checkNotNullParameter(path0, "<this>");
        FileStore fileStore0 = Files.getFileStore(path0);
        Intrinsics.checkNotNullExpressionValue(fileStore0, "getFileStore(this)");
        return fileStore0;
    }

    public static final FileVisitor fileVisitor(Function1 function10) {
        Intrinsics.checkNotNullParameter(function10, "builderAction");
        FileVisitorBuilderImpl fileVisitorBuilderImpl0 = new FileVisitorBuilderImpl();
        function10.invoke(fileVisitorBuilderImpl0);
        return fileVisitorBuilderImpl0.build();
    }

    private static final void forEachDirectoryEntry(Path path0, String s, Function1 function10) throws IOException {
        Intrinsics.checkNotNullParameter(path0, "<this>");
        Intrinsics.checkNotNullParameter(s, "glob");
        Intrinsics.checkNotNullParameter(function10, "action");
        Closeable closeable0 = Files.newDirectoryStream(path0, s);
        try {
            Intrinsics.checkNotNullExpressionValue(((DirectoryStream)closeable0), "it");
            for(Object object0: ((DirectoryStream)closeable0)) {
                function10.invoke(object0);
            }
        }
        catch(Throwable throwable0) {
            CloseableKt.closeFinally(closeable0, throwable0);
            throw throwable0;
        }
        CloseableKt.closeFinally(closeable0, null);
    }

    static void forEachDirectoryEntry$default(Path path0, String s, Function1 function10, int v, Object object0) throws IOException {
        if((v & 1) != 0) {
            s = "*";
        }
        Intrinsics.checkNotNullParameter(path0, "<this>");
        Intrinsics.checkNotNullParameter(s, "glob");
        Intrinsics.checkNotNullParameter(function10, "action");
        Closeable closeable0 = Files.newDirectoryStream(path0, s);
        try {
            Intrinsics.checkNotNullExpressionValue(((DirectoryStream)closeable0), "it");
            for(Object object1: ((DirectoryStream)closeable0)) {
                function10.invoke(object1);
            }
        }
        catch(Throwable throwable0) {
            CloseableKt.closeFinally(closeable0, throwable0);
            throw throwable0;
        }
        CloseableKt.closeFinally(closeable0, null);
    }

    private static final Object getAttribute(Path path0, String s, LinkOption[] arr_linkOption) throws IOException {
        Intrinsics.checkNotNullParameter(path0, "<this>");
        Intrinsics.checkNotNullParameter(s, "attribute");
        Intrinsics.checkNotNullParameter(arr_linkOption, "options");
        return Files.getAttribute(path0, s, ((LinkOption[])Arrays.copyOf(arr_linkOption, arr_linkOption.length)));
    }

    public static final String getExtension(Path path0) {
        Intrinsics.checkNotNullParameter(path0, "<this>");
        Path path1 = path0.getFileName();
        if(path1 != null) {
            String s = path1.toString();
            if(s != null) {
                String s1 = StringsKt.substringAfterLast(s, '.', "");
                return s1 == null ? "" : s1;
            }
        }
        return "";
    }

    public static void getExtension$annotations(Path path0) {
    }

    private static final String getInvariantSeparatorsPath(Path path0) {
        Intrinsics.checkNotNullParameter(path0, "<this>");
        return PathsKt.getInvariantSeparatorsPathString(path0);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use invariantSeparatorsPathString property instead.", replaceWith = @ReplaceWith(expression = "invariantSeparatorsPathString", imports = {}))
    public static void getInvariantSeparatorsPath$annotations(Path path0) {
    }

    public static final String getInvariantSeparatorsPathString(Path path0) {
        Intrinsics.checkNotNullParameter(path0, "<this>");
        String s = path0.getFileSystem().getSeparator();
        if(!Intrinsics.areEqual(s, "/")) {
            Intrinsics.checkNotNullExpressionValue(s, "separator");
            return StringsKt.replace$default(path0.toString(), s, "/", false, 4, null);
        }
        return path0.toString();
    }

    public static void getInvariantSeparatorsPathString$annotations(Path path0) {
    }

    private static final FileTime getLastModifiedTime(Path path0, LinkOption[] arr_linkOption) throws IOException {
        Intrinsics.checkNotNullParameter(path0, "<this>");
        Intrinsics.checkNotNullParameter(arr_linkOption, "options");
        FileTime fileTime0 = Files.getLastModifiedTime(path0, ((LinkOption[])Arrays.copyOf(arr_linkOption, arr_linkOption.length)));
        Intrinsics.checkNotNullExpressionValue(fileTime0, "getLastModifiedTime(this, *options)");
        return fileTime0;
    }

    public static final String getName(Path path0) {
        Intrinsics.checkNotNullParameter(path0, "<this>");
        Path path1 = path0.getFileName();
        String s = path1 == null ? null : path1.toString();
        return s == null ? "" : s;
    }

    public static void getName$annotations(Path path0) {
    }

    public static final String getNameWithoutExtension(Path path0) {
        Intrinsics.checkNotNullParameter(path0, "<this>");
        Path path1 = path0.getFileName();
        if(path1 != null) {
            String s = path1.toString();
            if(s != null) {
                String s1 = StringsKt.substringBeforeLast$default(s, ".", null, 2, null);
                return s1 == null ? "" : s1;
            }
        }
        return "";
    }

    public static void getNameWithoutExtension$annotations(Path path0) {
    }

    private static final UserPrincipal getOwner(Path path0, LinkOption[] arr_linkOption) throws IOException {
        Intrinsics.checkNotNullParameter(path0, "<this>");
        Intrinsics.checkNotNullParameter(arr_linkOption, "options");
        return Files.getOwner(path0, ((LinkOption[])Arrays.copyOf(arr_linkOption, arr_linkOption.length)));
    }

    private static final String getPathString(Path path0) {
        Intrinsics.checkNotNullParameter(path0, "<this>");
        return path0.toString();
    }

    public static void getPathString$annotations(Path path0) {
    }

    private static final Set getPosixFilePermissions(Path path0, LinkOption[] arr_linkOption) throws IOException {
        Intrinsics.checkNotNullParameter(path0, "<this>");
        Intrinsics.checkNotNullParameter(arr_linkOption, "options");
        Set set0 = Files.getPosixFilePermissions(path0, ((LinkOption[])Arrays.copyOf(arr_linkOption, arr_linkOption.length)));
        Intrinsics.checkNotNullExpressionValue(set0, "getPosixFilePermissions(this, *options)");
        return set0;
    }

    private static final boolean isDirectory(Path path0, LinkOption[] arr_linkOption) {
        Intrinsics.checkNotNullParameter(path0, "<this>");
        Intrinsics.checkNotNullParameter(arr_linkOption, "options");
        return Files.isDirectory(path0, ((LinkOption[])Arrays.copyOf(arr_linkOption, arr_linkOption.length)));
    }

    private static final boolean isExecutable(Path path0) {
        Intrinsics.checkNotNullParameter(path0, "<this>");
        return PathTreeWalk..ExternalSyntheticApiModelOutline0.m$5(path0);
    }

    private static final boolean isHidden(Path path0) throws IOException {
        Intrinsics.checkNotNullParameter(path0, "<this>");
        return Files.isHidden(path0);
    }

    private static final boolean isReadable(Path path0) {
        Intrinsics.checkNotNullParameter(path0, "<this>");
        return PathTreeWalk..ExternalSyntheticApiModelOutline0.m$4(path0);
    }

    private static final boolean isRegularFile(Path path0, LinkOption[] arr_linkOption) {
        Intrinsics.checkNotNullParameter(path0, "<this>");
        Intrinsics.checkNotNullParameter(arr_linkOption, "options");
        return Files.isRegularFile(path0, ((LinkOption[])Arrays.copyOf(arr_linkOption, arr_linkOption.length)));
    }

    private static final boolean isSameFileAs(Path path0, Path path1) throws IOException {
        Intrinsics.checkNotNullParameter(path0, "<this>");
        Intrinsics.checkNotNullParameter(path1, "other");
        return Files.isSameFile(path0, path1);
    }

    private static final boolean isSymbolicLink(Path path0) {
        Intrinsics.checkNotNullParameter(path0, "<this>");
        return Files.isSymbolicLink(path0);
    }

    private static final boolean isWritable(Path path0) {
        Intrinsics.checkNotNullParameter(path0, "<this>");
        return PathTreeWalk..ExternalSyntheticApiModelOutline0.m$3(path0);
    }

    public static final List listDirectoryEntries(Path path0, String s) throws IOException {
        List list0;
        Intrinsics.checkNotNullParameter(path0, "<this>");
        Intrinsics.checkNotNullParameter(s, "glob");
        Closeable closeable0 = Files.newDirectoryStream(path0, s);
        try {
            Intrinsics.checkNotNullExpressionValue(((DirectoryStream)closeable0), "it");
            list0 = CollectionsKt.toList(((DirectoryStream)closeable0));
        }
        catch(Throwable throwable0) {
            CloseableKt.closeFinally(closeable0, throwable0);
            throw throwable0;
        }
        CloseableKt.closeFinally(closeable0, null);
        return list0;
    }

    public static List listDirectoryEntries$default(Path path0, String s, int v, Object object0) throws IOException {
        if((v & 1) != 0) {
            s = "*";
        }
        return PathsKt.listDirectoryEntries(path0, s);
    }

    private static final Path moveTo(Path path0, Path path1, boolean z) throws IOException {
        Intrinsics.checkNotNullParameter(path0, "<this>");
        Intrinsics.checkNotNullParameter(path1, "target");
        CopyOption[] arr_copyOption = z ? new CopyOption[]{StandardCopyOption.REPLACE_EXISTING} : new CopyOption[0];
        Path path2 = Files.move(path0, path1, ((CopyOption[])Arrays.copyOf(arr_copyOption, arr_copyOption.length)));
        Intrinsics.checkNotNullExpressionValue(path2, "move(this, target, *options)");
        return path2;
    }

    private static final Path moveTo(Path path0, Path path1, CopyOption[] arr_copyOption) throws IOException {
        Intrinsics.checkNotNullParameter(path0, "<this>");
        Intrinsics.checkNotNullParameter(path1, "target");
        Intrinsics.checkNotNullParameter(arr_copyOption, "options");
        Path path2 = Files.move(path0, path1, ((CopyOption[])Arrays.copyOf(arr_copyOption, arr_copyOption.length)));
        Intrinsics.checkNotNullExpressionValue(path2, "move(this, target, *options)");
        return path2;
    }

    static Path moveTo$default(Path path0, Path path1, boolean z, int v, Object object0) throws IOException {
        if((v & 2) != 0) {
            z = false;
        }
        Intrinsics.checkNotNullParameter(path0, "<this>");
        Intrinsics.checkNotNullParameter(path1, "target");
        CopyOption[] arr_copyOption = z ? new CopyOption[]{StandardCopyOption.REPLACE_EXISTING} : new CopyOption[0];
        Path path2 = Files.move(path0, path1, ((CopyOption[])Arrays.copyOf(arr_copyOption, arr_copyOption.length)));
        Intrinsics.checkNotNullExpressionValue(path2, "move(this, target, *options)");
        return path2;
    }

    private static final boolean notExists(Path path0, LinkOption[] arr_linkOption) {
        Intrinsics.checkNotNullParameter(path0, "<this>");
        Intrinsics.checkNotNullParameter(arr_linkOption, "options");
        return Files.notExists(path0, ((LinkOption[])Arrays.copyOf(arr_linkOption, arr_linkOption.length)));
    }

    private static final BasicFileAttributes readAttributes(Path path0, LinkOption[] arr_linkOption) throws IOException {
        Intrinsics.checkNotNullParameter(path0, "<this>");
        Intrinsics.checkNotNullParameter(arr_linkOption, "options");
        Intrinsics.reifiedOperationMarker(4, "A");
        BasicFileAttributes basicFileAttributes0 = Files.readAttributes(path0, BasicFileAttributes.class, ((LinkOption[])Arrays.copyOf(arr_linkOption, arr_linkOption.length)));
        Intrinsics.checkNotNullExpressionValue(basicFileAttributes0, "readAttributes(this, A::class.java, *options)");
        return basicFileAttributes0;
    }

    private static final Map readAttributes(Path path0, String s, LinkOption[] arr_linkOption) throws IOException {
        Intrinsics.checkNotNullParameter(path0, "<this>");
        Intrinsics.checkNotNullParameter(s, "attributes");
        Intrinsics.checkNotNullParameter(arr_linkOption, "options");
        Map map0 = Files.readAttributes(path0, s, ((LinkOption[])Arrays.copyOf(arr_linkOption, arr_linkOption.length)));
        Intrinsics.checkNotNullExpressionValue(map0, "readAttributes(this, attributes, *options)");
        return map0;
    }

    private static final Path readSymbolicLink(Path path0) throws IOException {
        Intrinsics.checkNotNullParameter(path0, "<this>");
        Path path1 = Files.readSymbolicLink(path0);
        Intrinsics.checkNotNullExpressionValue(path1, "readSymbolicLink(this)");
        return path1;
    }

    public static final Path relativeTo(Path path0, Path path1) {
        Intrinsics.checkNotNullParameter(path0, "<this>");
        Intrinsics.checkNotNullParameter(path1, "base");
        try {
            return PathRelativizer.INSTANCE.tryRelativeTo(path0, path1);
        }
        catch(IllegalArgumentException illegalArgumentException0) {
            throw new IllegalArgumentException(illegalArgumentException0.getMessage() + "\nthis path: " + path0 + "\nbase path: " + path1, illegalArgumentException0);
        }
    }

    public static final Path relativeToOrNull(Path path0, Path path1) {
        Intrinsics.checkNotNullParameter(path0, "<this>");
        Intrinsics.checkNotNullParameter(path1, "base");
        try {
            return PathRelativizer.INSTANCE.tryRelativeTo(path0, path1);
        }
        catch(IllegalArgumentException unused_ex) {
            return null;
        }
    }

    public static final Path relativeToOrSelf(Path path0, Path path1) {
        Intrinsics.checkNotNullParameter(path0, "<this>");
        Intrinsics.checkNotNullParameter(path1, "base");
        Path path2 = PathsKt.relativeToOrNull(path0, path1);
        return path2 == null ? path0 : path2;
    }

    private static final Path setAttribute(Path path0, String s, Object object0, LinkOption[] arr_linkOption) throws IOException {
        Intrinsics.checkNotNullParameter(path0, "<this>");
        Intrinsics.checkNotNullParameter(s, "attribute");
        Intrinsics.checkNotNullParameter(arr_linkOption, "options");
        Path path1 = Files.setAttribute(path0, s, object0, ((LinkOption[])Arrays.copyOf(arr_linkOption, arr_linkOption.length)));
        Intrinsics.checkNotNullExpressionValue(path1, "setAttribute(this, attribute, value, *options)");
        return path1;
    }

    private static final Path setLastModifiedTime(Path path0, FileTime fileTime0) throws IOException {
        Intrinsics.checkNotNullParameter(path0, "<this>");
        Intrinsics.checkNotNullParameter(fileTime0, "value");
        Path path1 = Files.setLastModifiedTime(path0, fileTime0);
        Intrinsics.checkNotNullExpressionValue(path1, "setLastModifiedTime(this, value)");
        return path1;
    }

    private static final Path setOwner(Path path0, UserPrincipal userPrincipal0) throws IOException {
        Intrinsics.checkNotNullParameter(path0, "<this>");
        Intrinsics.checkNotNullParameter(userPrincipal0, "value");
        Path path1 = Files.setOwner(path0, userPrincipal0);
        Intrinsics.checkNotNullExpressionValue(path1, "setOwner(this, value)");
        return path1;
    }

    private static final Path setPosixFilePermissions(Path path0, Set set0) throws IOException {
        Intrinsics.checkNotNullParameter(path0, "<this>");
        Intrinsics.checkNotNullParameter(set0, "value");
        Path path1 = Files.setPosixFilePermissions(path0, set0);
        Intrinsics.checkNotNullExpressionValue(path1, "setPosixFilePermissions(this, value)");
        return path1;
    }

    private static final Path toPath(URI uRI0) {
        Intrinsics.checkNotNullParameter(uRI0, "<this>");
        Path path0 = Paths.get(uRI0);
        Intrinsics.checkNotNullExpressionValue(path0, "get(this)");
        return path0;
    }

    private static final Object useDirectoryEntries(Path path0, String s, Function1 function10) throws IOException {
        Object object0;
        Intrinsics.checkNotNullParameter(path0, "<this>");
        Intrinsics.checkNotNullParameter(s, "glob");
        Intrinsics.checkNotNullParameter(function10, "block");
        Closeable closeable0 = Files.newDirectoryStream(path0, s);
        try {
            Intrinsics.checkNotNullExpressionValue(((DirectoryStream)closeable0), "it");
            object0 = function10.invoke(CollectionsKt.asSequence(((DirectoryStream)closeable0)));
        }
        catch(Throwable throwable0) {
            CloseableKt.closeFinally(closeable0, throwable0);
            throw throwable0;
        }
        CloseableKt.closeFinally(closeable0, null);
        return object0;
    }

    static Object useDirectoryEntries$default(Path path0, String s, Function1 function10, int v, Object object0) throws IOException {
        Object object1;
        if((v & 1) != 0) {
            s = "*";
        }
        Intrinsics.checkNotNullParameter(path0, "<this>");
        Intrinsics.checkNotNullParameter(s, "glob");
        Intrinsics.checkNotNullParameter(function10, "block");
        Closeable closeable0 = Files.newDirectoryStream(path0, s);
        try {
            Intrinsics.checkNotNullExpressionValue(((DirectoryStream)closeable0), "it");
            object1 = function10.invoke(CollectionsKt.asSequence(((DirectoryStream)closeable0)));
        }
        catch(Throwable throwable0) {
            CloseableKt.closeFinally(closeable0, throwable0);
            throw throwable0;
        }
        CloseableKt.closeFinally(closeable0, null);
        return object1;
    }

    public static final void visitFileTree(Path path0, int v, boolean z, Function1 function10) {
        Intrinsics.checkNotNullParameter(path0, "<this>");
        Intrinsics.checkNotNullParameter(function10, "builderAction");
        PathsKt.visitFileTree(path0, PathsKt.fileVisitor(function10), v, z);
    }

    public static final void visitFileTree(Path path0, FileVisitor fileVisitor0, int v, boolean z) {
        Intrinsics.checkNotNullParameter(path0, "<this>");
        Intrinsics.checkNotNullParameter(fileVisitor0, "visitor");
        Files.walkFileTree(path0, (z ? SetsKt.setOf(FileVisitOption.FOLLOW_LINKS) : SetsKt.emptySet()), v, fileVisitor0);
    }

    public static void visitFileTree$default(Path path0, int v, boolean z, Function1 function10, int v1, Object object0) {
        if((v1 & 1) != 0) {
            v = 0x7FFFFFFF;
        }
        if((v1 & 2) != 0) {
            z = false;
        }
        PathsKt.visitFileTree(path0, v, z, function10);
    }

    public static void visitFileTree$default(Path path0, FileVisitor fileVisitor0, int v, boolean z, int v1, Object object0) {
        if((v1 & 2) != 0) {
            v = 0x7FFFFFFF;
        }
        if((v1 & 4) != 0) {
            z = false;
        }
        PathsKt.visitFileTree(path0, fileVisitor0, v, z);
    }

    public static final Sequence walk(Path path0, PathWalkOption[] arr_pathWalkOption) {
        Intrinsics.checkNotNullParameter(path0, "<this>");
        Intrinsics.checkNotNullParameter(arr_pathWalkOption, "options");
        return new PathTreeWalk(path0, arr_pathWalkOption);
    }
}

