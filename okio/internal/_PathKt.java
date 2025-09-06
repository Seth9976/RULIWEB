package okio.internal;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import okio.Buffer;
import okio.ByteString;
import okio.Path.Companion;
import okio.Path;

@Metadata(d1 = {"\u0000H\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000B\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000B\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0010\u000E\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\f\n\u0002\b\u0006\n\u0002\u0010\u0005\n\u0000\u001A\u0015\u0010\u0014\u001A\u00020\r*\u00020\u000E2\u0006\u0010\u0015\u001A\u00020\u000EH\u0080\b\u001A\u0017\u0010\u0016\u001A\u00020\u0017*\u00020\u000E2\b\u0010\u0015\u001A\u0004\u0018\u00010\u0018H\u0080\b\u001A\r\u0010\u0019\u001A\u00020\r*\u00020\u000EH\u0080\b\u001A\r\u0010\u001A\u001A\u00020\u0017*\u00020\u000EH\u0080\b\u001A\r\u0010\u001B\u001A\u00020\u0017*\u00020\u000EH\u0080\b\u001A\r\u0010\u001C\u001A\u00020\u0017*\u00020\u000EH\u0080\b\u001A\r\u0010\u001D\u001A\u00020\u001E*\u00020\u000EH\u0080\b\u001A\r\u0010\u001F\u001A\u00020\u0001*\u00020\u000EH\u0080\b\u001A\r\u0010 \u001A\u00020\u000E*\u00020\u000EH\u0080\b\u001A\u000F\u0010!\u001A\u0004\u0018\u00010\u000E*\u00020\u000EH\u0080\b\u001A\u0015\u0010\"\u001A\u00020\u000E*\u00020\u000E2\u0006\u0010\u0015\u001A\u00020\u000EH\u0080\b\u001A\u001D\u0010#\u001A\u00020\u000E*\u00020\u000E2\u0006\u0010$\u001A\u00020\u001E2\u0006\u0010%\u001A\u00020\u0017H\u0080\b\u001A\u001D\u0010#\u001A\u00020\u000E*\u00020\u000E2\u0006\u0010$\u001A\u00020&2\u0006\u0010%\u001A\u00020\u0017H\u0080\b\u001A\u001D\u0010#\u001A\u00020\u000E*\u00020\u000E2\u0006\u0010$\u001A\u00020\u00012\u0006\u0010%\u001A\u00020\u0017H\u0080\b\u001A\u001C\u0010#\u001A\u00020\u000E*\u00020\u000E2\u0006\u0010$\u001A\u00020\u000E2\u0006\u0010%\u001A\u00020\u0017H\u0000\u001A\u000F\u0010\'\u001A\u0004\u0018\u00010\u000E*\u00020\u000EH\u0080\b\u001A\u0013\u0010(\u001A\b\u0012\u0004\u0012\u00020\u001E0)*\u00020\u000EH\u0080\b\u001A\u0013\u0010*\u001A\b\u0012\u0004\u0012\u00020\u00010)*\u00020\u000EH\u0080\b\u001A\u0014\u0010+\u001A\u00020\u000E*\u00020\u001E2\u0006\u0010%\u001A\u00020\u0017H\u0000\u001A\r\u0010,\u001A\u00020\u001E*\u00020\u000EH\u0080\b\u001A\u0014\u0010-\u001A\u0004\u0018\u00010.*\u00020\u000EH\u0080\b\u00A2\u0006\u0002\u0010/\u001A\f\u00100\u001A\u00020\u0017*\u00020\u000EH\u0002\u001A\f\u00101\u001A\u00020\r*\u00020\u000EH\u0002\u001A\u0014\u00102\u001A\u00020\u0017*\u00020&2\u0006\u0010\u0011\u001A\u00020\u0001H\u0002\u001A\u0014\u00103\u001A\u00020\u000E*\u00020&2\u0006\u0010%\u001A\u00020\u0017H\u0000\u001A\f\u00104\u001A\u00020\u0001*\u000205H\u0002\u001A\f\u00104\u001A\u00020\u0001*\u00020\u001EH\u0002\"\u0016\u0010\u0000\u001A\u00020\u00018\u0002X\u0083\u0004\u00A2\u0006\b\n\u0000\u0012\u0004\b\u0002\u0010\u0003\"\u0016\u0010\u0004\u001A\u00020\u00018\u0002X\u0083\u0004\u00A2\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0003\"\u0016\u0010\u0006\u001A\u00020\u00018\u0002X\u0083\u0004\u00A2\u0006\b\n\u0000\u0012\u0004\b\u0007\u0010\u0003\"\u0016\u0010\b\u001A\u00020\u00018\u0002X\u0083\u0004\u00A2\u0006\b\n\u0000\u0012\u0004\b\t\u0010\u0003\"\u0016\u0010\n\u001A\u00020\u00018\u0002X\u0083\u0004\u00A2\u0006\b\n\u0000\u0012\u0004\b\u000B\u0010\u0003\"\u0018\u0010\f\u001A\u00020\r*\u00020\u000E8BX\u0082\u0004\u00A2\u0006\u0006\u001A\u0004\b\u000F\u0010\u0010\"\u001A\u0010\u0011\u001A\u0004\u0018\u00010\u0001*\u00020\u000E8BX\u0082\u0004\u00A2\u0006\u0006\u001A\u0004\b\u0012\u0010\u0013\u00A8\u00066"}, d2 = {"ANY_SLASH", "Lokio/ByteString;", "getANY_SLASH$annotations", "()V", "BACKSLASH", "getBACKSLASH$annotations", "DOT", "getDOT$annotations", "DOT_DOT", "getDOT_DOT$annotations", "SLASH", "getSLASH$annotations", "indexOfLastSlash", "", "Lokio/Path;", "getIndexOfLastSlash", "(Lokio/Path;)I", "slash", "getSlash", "(Lokio/Path;)Lokio/ByteString;", "commonCompareTo", "other", "commonEquals", "", "", "commonHashCode", "commonIsAbsolute", "commonIsRelative", "commonIsRoot", "commonName", "", "commonNameBytes", "commonNormalized", "commonParent", "commonRelativeTo", "commonResolve", "child", "normalize", "Lokio/Buffer;", "commonRoot", "commonSegments", "", "commonSegmentsBytes", "commonToPath", "commonToString", "commonVolumeLetter", "", "(Lokio/Path;)Ljava/lang/Character;", "lastSegmentIsDotDot", "rootLength", "startsWithVolumeLetterAndColon", "toPath", "toSlash", "", "okio"}, k = 2, mv = {1, 6, 0}, xi = 0x30)
public final class _PathKt {
    private static final ByteString ANY_SLASH;
    private static final ByteString BACKSLASH;
    private static final ByteString DOT;
    private static final ByteString DOT_DOT;
    private static final ByteString SLASH;

    static {
        _PathKt.SLASH = ByteString.Companion.encodeUtf8("/");
        _PathKt.BACKSLASH = ByteString.Companion.encodeUtf8("\\");
        _PathKt.ANY_SLASH = ByteString.Companion.encodeUtf8("/\\");
        _PathKt.DOT = ByteString.Companion.encodeUtf8(".");
        _PathKt.DOT_DOT = ByteString.Companion.encodeUtf8("..");
    }

    public static final int commonCompareTo(Path path0, Path path1) {
        Intrinsics.checkNotNullParameter(path0, "<this>");
        Intrinsics.checkNotNullParameter(path1, "other");
        return path0.getBytes$okio().compareTo(path1.getBytes$okio());
    }

    public static final boolean commonEquals(Path path0, Object object0) {
        Intrinsics.checkNotNullParameter(path0, "<this>");
        return object0 instanceof Path && Intrinsics.areEqual(((Path)object0).getBytes$okio(), path0.getBytes$okio());
    }

    public static final int commonHashCode(Path path0) {
        Intrinsics.checkNotNullParameter(path0, "<this>");
        return path0.getBytes$okio().hashCode();
    }

    public static final boolean commonIsAbsolute(Path path0) {
        Intrinsics.checkNotNullParameter(path0, "<this>");
        return _PathKt.rootLength(path0) != -1;
    }

    public static final boolean commonIsRelative(Path path0) {
        Intrinsics.checkNotNullParameter(path0, "<this>");
        return _PathKt.rootLength(path0) == -1;
    }

    public static final boolean commonIsRoot(Path path0) {
        Intrinsics.checkNotNullParameter(path0, "<this>");
        return _PathKt.rootLength(path0) == path0.getBytes$okio().size();
    }

    public static final String commonName(Path path0) {
        Intrinsics.checkNotNullParameter(path0, "<this>");
        return path0.nameBytes().utf8();
    }

    public static final ByteString commonNameBytes(Path path0) {
        Intrinsics.checkNotNullParameter(path0, "<this>");
        int v = _PathKt.getIndexOfLastSlash(path0);
        if(v != -1) {
            return ByteString.substring$default(path0.getBytes$okio(), v + 1, 0, 2, null);
        }
        return path0.volumeLetter() == null || path0.getBytes$okio().size() != 2 ? path0.getBytes$okio() : ByteString.EMPTY;
    }

    public static final Path commonNormalized(Path path0) {
        Intrinsics.checkNotNullParameter(path0, "<this>");
        return Path.Companion.get(path0.toString(), true);
    }

    public static final Path commonParent(Path path0) {
        Intrinsics.checkNotNullParameter(path0, "<this>");
        if(!Intrinsics.areEqual(path0.getBytes$okio(), _PathKt.DOT) && !Intrinsics.areEqual(path0.getBytes$okio(), _PathKt.SLASH) && !Intrinsics.areEqual(path0.getBytes$okio(), _PathKt.BACKSLASH) && !_PathKt.lastSegmentIsDotDot(path0)) {
            int v = _PathKt.getIndexOfLastSlash(path0);
            if(v == 2 && path0.volumeLetter() != null) {
                return path0.getBytes$okio().size() == 3 ? null : new Path(ByteString.substring$default(path0.getBytes$okio(), 0, 3, 1, null));
            }
            if(v == 1 && path0.getBytes$okio().startsWith(_PathKt.BACKSLASH)) {
                return null;
            }
            if(v == -1 && path0.volumeLetter() != null) {
                return path0.getBytes$okio().size() == 2 ? null : new Path(ByteString.substring$default(path0.getBytes$okio(), 0, 2, 1, null));
            }
            if(v == -1) {
                return new Path(_PathKt.DOT);
            }
            return v == 0 ? new Path(ByteString.substring$default(path0.getBytes$okio(), 0, 1, 1, null)) : new Path(ByteString.substring$default(path0.getBytes$okio(), 0, v, 1, null));
        }
        return null;
    }

    public static final Path commonRelativeTo(Path path0, Path path1) {
        Intrinsics.checkNotNullParameter(path0, "<this>");
        Intrinsics.checkNotNullParameter(path1, "other");
        if(!Intrinsics.areEqual(path0.getRoot(), path1.getRoot())) {
            throw new IllegalArgumentException(("Paths of different roots cannot be relative to each other: " + path0 + " and " + path1).toString());
        }
        List list0 = path0.getSegmentsBytes();
        List list1 = path1.getSegmentsBytes();
        int v = Math.min(list0.size(), list1.size());
        int v1;
        for(v1 = 0; v1 < v && Intrinsics.areEqual(list0.get(v1), list1.get(v1)); ++v1) {
        }
        if(v1 == v && path0.getBytes$okio().size() == path1.getBytes$okio().size()) {
            return Companion.get$default(Path.Companion, ".", false, 1, null);
        }
        if(list1.subList(v1, list1.size()).indexOf(_PathKt.DOT_DOT) != -1) {
            throw new IllegalArgumentException(("Impossible relative path to resolve: " + path0 + " and " + path1).toString());
        }
        Buffer buffer0 = new Buffer();
        ByteString byteString0 = _PathKt.getSlash(path1);
        if(byteString0 == null) {
            byteString0 = _PathKt.getSlash(path0);
            if(byteString0 == null) {
                byteString0 = _PathKt.toSlash("/");
            }
        }
        int v2 = list1.size();
        for(int v3 = v1; v3 < v2; ++v3) {
            buffer0.write(_PathKt.DOT_DOT);
            buffer0.write(byteString0);
        }
        int v4 = list0.size();
        while(v1 < v4) {
            buffer0.write(((ByteString)list0.get(v1)));
            buffer0.write(byteString0);
            ++v1;
        }
        return _PathKt.toPath(buffer0, false);
    }

    public static final Path commonResolve(Path path0, String s, boolean z) {
        Intrinsics.checkNotNullParameter(path0, "<this>");
        Intrinsics.checkNotNullParameter(s, "child");
        return _PathKt.commonResolve(path0, _PathKt.toPath(new Buffer().writeUtf8(s), false), z);
    }

    public static final Path commonResolve(Path path0, Buffer buffer0, boolean z) {
        Intrinsics.checkNotNullParameter(path0, "<this>");
        Intrinsics.checkNotNullParameter(buffer0, "child");
        return _PathKt.commonResolve(path0, _PathKt.toPath(buffer0, false), z);
    }

    public static final Path commonResolve(Path path0, ByteString byteString0, boolean z) {
        Intrinsics.checkNotNullParameter(path0, "<this>");
        Intrinsics.checkNotNullParameter(byteString0, "child");
        return _PathKt.commonResolve(path0, _PathKt.toPath(new Buffer().write(byteString0), false), z);
    }

    public static final Path commonResolve(Path path0, Path path1, boolean z) {
        Intrinsics.checkNotNullParameter(path0, "<this>");
        Intrinsics.checkNotNullParameter(path1, "child");
        if(!path1.isAbsolute() && path1.volumeLetter() == null) {
            ByteString byteString0 = _PathKt.getSlash(path0);
            if(byteString0 == null) {
                byteString0 = _PathKt.getSlash(path1);
                if(byteString0 == null) {
                    byteString0 = _PathKt.toSlash("/");
                }
            }
            Buffer buffer0 = new Buffer();
            buffer0.write(path0.getBytes$okio());
            if(buffer0.size() > 0L) {
                buffer0.write(byteString0);
            }
            buffer0.write(path1.getBytes$okio());
            return _PathKt.toPath(buffer0, z);
        }
        return path1;
    }

    public static final Path commonRoot(Path path0) {
        Intrinsics.checkNotNullParameter(path0, "<this>");
        int v = _PathKt.rootLength(path0);
        return v == -1 ? null : new Path(path0.getBytes$okio().substring(0, v));
    }

    public static final List commonSegments(Path path0) {
        Intrinsics.checkNotNullParameter(path0, "<this>");
        List list0 = new ArrayList();
        int v = _PathKt.rootLength(path0);
        if(v == -1) {
            v = 0;
        }
        else if(v < path0.getBytes$okio().size() && path0.getBytes$okio().getByte(v) == 92) {
            ++v;
        }
        int v1 = path0.getBytes$okio().size();
        int v2 = v;
        while(v < v1) {
            switch(path0.getBytes$okio().getByte(v)) {
                case 0x2F: 
                case 92: {
                    list0.add(path0.getBytes$okio().substring(v2, v));
                    v2 = v + 1;
                }
            }
            ++v;
        }
        if(v2 < path0.getBytes$okio().size()) {
            list0.add(path0.getBytes$okio().substring(v2, path0.getBytes$okio().size()));
        }
        ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list0, 10));
        for(Object object0: list0) {
            arrayList0.add(((ByteString)object0).utf8());
        }
        return arrayList0;
    }

    public static final List commonSegmentsBytes(Path path0) {
        Intrinsics.checkNotNullParameter(path0, "<this>");
        List list0 = new ArrayList();
        int v = _PathKt.rootLength(path0);
        if(v == -1) {
            v = 0;
        }
        else if(v < path0.getBytes$okio().size() && path0.getBytes$okio().getByte(v) == 92) {
            ++v;
        }
        int v1 = path0.getBytes$okio().size();
        int v2 = v;
        while(v < v1) {
            switch(path0.getBytes$okio().getByte(v)) {
                case 0x2F: 
                case 92: {
                    list0.add(path0.getBytes$okio().substring(v2, v));
                    v2 = v + 1;
                }
            }
            ++v;
        }
        if(v2 < path0.getBytes$okio().size()) {
            list0.add(path0.getBytes$okio().substring(v2, path0.getBytes$okio().size()));
        }
        return list0;
    }

    public static final Path commonToPath(String s, boolean z) {
        Intrinsics.checkNotNullParameter(s, "<this>");
        return _PathKt.toPath(new Buffer().writeUtf8(s), z);
    }

    public static final String commonToString(Path path0) {
        Intrinsics.checkNotNullParameter(path0, "<this>");
        return path0.getBytes$okio().utf8();
    }

    public static final Character commonVolumeLetter(Path path0) {
        Intrinsics.checkNotNullParameter(path0, "<this>");
        if(ByteString.indexOf$default(path0.getBytes$okio(), _PathKt.SLASH, 0, 2, null) != -1) {
            return null;
        }
        if(path0.getBytes$okio().size() < 2) {
            return null;
        }
        if(path0.getBytes$okio().getByte(1) != 58) {
            return null;
        }
        int v = (char)path0.getBytes$okio().getByte(0);
        return (97 > v || v >= 0x7B) && (65 > v || v >= 91) ? null : Character.valueOf(((char)v));
    }

    private static void getANY_SLASH$annotations() {
    }

    private static void getBACKSLASH$annotations() {
    }

    private static void getDOT$annotations() {
    }

    private static void getDOT_DOT$annotations() {
    }

    private static final int getIndexOfLastSlash(Path path0) {
        int v = ByteString.lastIndexOf$default(path0.getBytes$okio(), _PathKt.SLASH, 0, 2, null);
        return v == -1 ? ByteString.lastIndexOf$default(path0.getBytes$okio(), _PathKt.BACKSLASH, 0, 2, null) : v;
    }

    private static void getSLASH$annotations() {
    }

    private static final ByteString getSlash(Path path0) {
        ByteString byteString0 = _PathKt.SLASH;
        if(ByteString.indexOf$default(path0.getBytes$okio(), byteString0, 0, 2, null) != -1) {
            return byteString0;
        }
        return ByteString.indexOf$default(path0.getBytes$okio(), _PathKt.BACKSLASH, 0, 2, null) == -1 ? null : _PathKt.BACKSLASH;
    }

    private static final boolean lastSegmentIsDotDot(Path path0) {
        if(path0.getBytes$okio().endsWith(_PathKt.DOT_DOT)) {
            if(path0.getBytes$okio().size() == 2) {
                return true;
            }
            return path0.getBytes$okio().rangeEquals(path0.getBytes$okio().size() - 3, _PathKt.SLASH, 0, 1) ? true : path0.getBytes$okio().rangeEquals(path0.getBytes$okio().size() - 3, _PathKt.BACKSLASH, 0, 1);
        }
        return false;
    }

    private static final int rootLength(Path path0) {
        if(path0.getBytes$okio().size() == 0) {
            return -1;
        }
        switch(path0.getBytes$okio().getByte(0)) {
            case 0x2F: {
                return 1;
            }
            case 92: {
                if(path0.getBytes$okio().size() > 2 && path0.getBytes$okio().getByte(1) == 92) {
                    int v1 = path0.getBytes$okio().indexOf(_PathKt.BACKSLASH, 2);
                    return v1 == -1 ? path0.getBytes$okio().size() : v1;
                }
                return 1;
            }
            default: {
                if(path0.getBytes$okio().size() > 2 && path0.getBytes$okio().getByte(1) == 58 && path0.getBytes$okio().getByte(2) == 92) {
                    int v = (char)path0.getBytes$okio().getByte(0);
                    return (97 > v || v >= 0x7B) && (65 > v || v >= 91) ? -1 : 3;
                }
                return -1;
            }
        }
    }

    private static final boolean startsWithVolumeLetterAndColon(Buffer buffer0, ByteString byteString0) {
        if(!Intrinsics.areEqual(byteString0, _PathKt.BACKSLASH)) {
            return false;
        }
        if(buffer0.size() < 2L) {
            return false;
        }
        if(buffer0.getByte(1L) != 58) {
            return false;
        }
        int v = (char)buffer0.getByte(0L);
        return 97 <= v && v < 0x7B || 65 <= v && v < 91;
    }

    public static final Path toPath(Buffer buffer0, boolean z) {
        ByteString byteString2;
        ByteString byteString1;
        Intrinsics.checkNotNullParameter(buffer0, "<this>");
        Buffer buffer1 = new Buffer();
        ByteString byteString0 = null;
        int v1 = 0;
        while(true) {
            if(!buffer0.rangeEquals(0L, _PathKt.SLASH)) {
                byteString1 = _PathKt.BACKSLASH;
                if(buffer0.rangeEquals(0L, byteString1)) {
                    goto label_8;
                }
                break;
            }
        label_8:
            int v2 = buffer0.readByte();
            if(byteString0 == null) {
                byteString0 = _PathKt.toSlash(((byte)v2));
            }
            ++v1;
        }
        boolean z1 = v1 >= 2 && Intrinsics.areEqual(byteString0, byteString1);
        if(z1) {
            Intrinsics.checkNotNull(byteString0);
            buffer1.write(byteString0);
            buffer1.write(byteString0);
        }
        else if(v1 > 0) {
            Intrinsics.checkNotNull(byteString0);
            buffer1.write(byteString0);
        }
        else {
            long v3 = buffer0.indexOfElement(_PathKt.ANY_SLASH);
            if(byteString0 == null) {
                byteString0 = v3 == -1L ? _PathKt.toSlash("/") : _PathKt.toSlash(buffer0.getByte(v3));
            }
            if(_PathKt.startsWithVolumeLetterAndColon(buffer0, byteString0)) {
                if(v3 == 2L) {
                    buffer1.write(buffer0, 3L);
                }
                else {
                    buffer1.write(buffer0, 2L);
                }
            }
        }
        boolean z2 = buffer1.size() > 0L;
        List list0 = new ArrayList();
        while(!buffer0.exhausted()) {
            long v4 = buffer0.indexOfElement(_PathKt.ANY_SLASH);
            if(v4 == -1L) {
                byteString2 = buffer0.readByteString();
            }
            else {
                byteString2 = buffer0.readByteString(v4);
                buffer0.readByte();
            }
            ByteString byteString3 = _PathKt.DOT_DOT;
            if(Intrinsics.areEqual(byteString2, byteString3)) {
                if(z2 && list0.isEmpty()) {
                    continue;
                }
                if(!z || !z2 && (list0.isEmpty() || Intrinsics.areEqual(CollectionsKt.last(list0), byteString3))) {
                    list0.add(byteString2);
                }
                else {
                    if(z1 && list0.size() == 1) {
                        continue;
                    }
                    CollectionsKt.removeLastOrNull(list0);
                }
            }
            else if(!Intrinsics.areEqual(byteString2, _PathKt.DOT) && !Intrinsics.areEqual(byteString2, ByteString.EMPTY)) {
                list0.add(byteString2);
            }
        }
        int v5 = list0.size();
        for(int v = 0; v < v5; ++v) {
            if(v > 0) {
                buffer1.write(byteString0);
            }
            buffer1.write(((ByteString)list0.get(v)));
        }
        if(buffer1.size() == 0L) {
            buffer1.write(_PathKt.DOT);
        }
        return new Path(buffer1.readByteString());
    }

    private static final ByteString toSlash(byte b) {
        switch(b) {
            case 0x2F: {
                return _PathKt.SLASH;
            }
            case 92: {
                return _PathKt.BACKSLASH;
            }
            default: {
                throw new IllegalArgumentException("not a directory separator: " + ((int)b));
            }
        }
    }

    private static final ByteString toSlash(String s) {
        if(Intrinsics.areEqual(s, "/")) {
            return _PathKt.SLASH;
        }
        if(!Intrinsics.areEqual(s, "\\")) {
            throw new IllegalArgumentException("not a directory separator: " + s);
        }
        return _PathKt.BACKSLASH;
    }
}

