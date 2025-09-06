package okio.internal;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import jeb.synthetic.FIN;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref.BooleanRef;
import kotlin.jvm.internal.Ref.LongRef;
import kotlin.jvm.internal.Ref.ObjectRef;
import kotlin.text.StringsKt;
import okio.BufferedSource;
import okio.FileHandle;
import okio.FileMetadata;
import okio.FileSystem;
import okio.Okio;
import okio.Path.Companion;
import okio.Path;
import okio.ZipFileSystem;

@Metadata(d1 = {"\u0000j\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000E\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u001A\"\u0010\u0012\u001A\u000E\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00150\u00132\f\u0010\u0016\u001A\b\u0012\u0004\u0012\u00020\u00150\u0017H\u0002\u001A\u001F\u0010\u0018\u001A\u0004\u0018\u00010\u000B2\u0006\u0010\u0019\u001A\u00020\u00012\u0006\u0010\u001A\u001A\u00020\u0001H\u0002\u00A2\u0006\u0002\u0010\u001B\u001A.\u0010\u001C\u001A\u00020\u001D2\u0006\u0010\u001E\u001A\u00020\u00142\u0006\u0010\u001F\u001A\u00020 2\u0014\b\u0002\u0010!\u001A\u000E\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020#0\"H\u0000\u001A\f\u0010$\u001A\u00020\u0015*\u00020%H\u0000\u001A\f\u0010&\u001A\u00020\'*\u00020%H\u0002\u001A.\u0010(\u001A\u00020)*\u00020%2\u0006\u0010*\u001A\u00020\u00012\u0018\u0010+\u001A\u0014\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u000B\u0012\u0004\u0012\u00020)0,H\u0002\u001A\u0014\u0010-\u001A\u00020.*\u00020%2\u0006\u0010/\u001A\u00020.H\u0000\u001A\u0018\u00100\u001A\u0004\u0018\u00010.*\u00020%2\b\u0010/\u001A\u0004\u0018\u00010.H\u0002\u001A\u0014\u00101\u001A\u00020\'*\u00020%2\u0006\u00102\u001A\u00020\'H\u0002\u001A\f\u00103\u001A\u00020)*\u00020%H\u0000\"\u000E\u0010\u0000\u001A\u00020\u0001X\u0082T\u00A2\u0006\u0002\n\u0000\"\u000E\u0010\u0002\u001A\u00020\u0001X\u0082T\u00A2\u0006\u0002\n\u0000\"\u000E\u0010\u0003\u001A\u00020\u0001X\u0082T\u00A2\u0006\u0002\n\u0000\"\u000E\u0010\u0004\u001A\u00020\u0001X\u0080T\u00A2\u0006\u0002\n\u0000\"\u000E\u0010\u0005\u001A\u00020\u0001X\u0080T\u00A2\u0006\u0002\n\u0000\"\u000E\u0010\u0006\u001A\u00020\u0001X\u0082T\u00A2\u0006\u0002\n\u0000\"\u000E\u0010\u0007\u001A\u00020\u0001X\u0082T\u00A2\u0006\u0002\n\u0000\"\u000E\u0010\b\u001A\u00020\u0001X\u0082T\u00A2\u0006\u0002\n\u0000\"\u000E\u0010\t\u001A\u00020\u0001X\u0082T\u00A2\u0006\u0002\n\u0000\"\u000E\u0010\n\u001A\u00020\u000BX\u0082T\u00A2\u0006\u0002\n\u0000\"\u000E\u0010\f\u001A\u00020\u0001X\u0082T\u00A2\u0006\u0002\n\u0000\"\u000E\u0010\r\u001A\u00020\u0001X\u0082T\u00A2\u0006\u0002\n\u0000\"\u0018\u0010\u000E\u001A\u00020\u000F*\u00020\u00018BX\u0082\u0004\u00A2\u0006\u0006\u001A\u0004\b\u0010\u0010\u0011\u00A8\u00064"}, d2 = {"BIT_FLAG_ENCRYPTED", "", "BIT_FLAG_UNSUPPORTED_MASK", "CENTRAL_FILE_HEADER_SIGNATURE", "COMPRESSION_METHOD_DEFLATED", "COMPRESSION_METHOD_STORED", "END_OF_CENTRAL_DIRECTORY_SIGNATURE", "HEADER_ID_EXTENDED_TIMESTAMP", "HEADER_ID_ZIP64_EXTENDED_INFO", "LOCAL_FILE_HEADER_SIGNATURE", "MAX_ZIP_ENTRY_AND_ARCHIVE_SIZE", "", "ZIP64_EOCD_RECORD_SIGNATURE", "ZIP64_LOCATOR_SIGNATURE", "hex", "", "getHex", "(I)Ljava/lang/String;", "buildIndex", "", "Lokio/Path;", "Lokio/internal/ZipEntry;", "entries", "", "dosDateTimeToEpochMillis", "date", "time", "(II)Ljava/lang/Long;", "openZip", "Lokio/ZipFileSystem;", "zipPath", "fileSystem", "Lokio/FileSystem;", "predicate", "Lkotlin/Function1;", "", "readEntry", "Lokio/BufferedSource;", "readEocdRecord", "Lokio/internal/EocdRecord;", "readExtra", "", "extraSize", "block", "Lkotlin/Function2;", "readLocalHeader", "Lokio/FileMetadata;", "basicMetadata", "readOrSkipLocalHeader", "readZip64EocdRecord", "regularRecord", "skipLocalHeader", "okio"}, k = 2, mv = {1, 6, 0}, xi = 0x30)
public final class ZipKt {
    private static final int BIT_FLAG_ENCRYPTED = 1;
    private static final int BIT_FLAG_UNSUPPORTED_MASK = 1;
    private static final int CENTRAL_FILE_HEADER_SIGNATURE = 0x2014B50;
    public static final int COMPRESSION_METHOD_DEFLATED = 8;
    public static final int COMPRESSION_METHOD_STORED = 0;
    private static final int END_OF_CENTRAL_DIRECTORY_SIGNATURE = 101010256;
    private static final int HEADER_ID_EXTENDED_TIMESTAMP = 0x5455;
    private static final int HEADER_ID_ZIP64_EXTENDED_INFO = 1;
    private static final int LOCAL_FILE_HEADER_SIGNATURE = 0x4034B50;
    private static final long MAX_ZIP_ENTRY_AND_ARCHIVE_SIZE = 0xFFFFFFFFL;
    private static final int ZIP64_EOCD_RECORD_SIGNATURE = 0x6064B50;
    private static final int ZIP64_LOCATOR_SIGNATURE = 0x7064B50;

    private static final Map buildIndex(List list0) {
        ZipEntry zipEntry1;
        Path path0 = Companion.get$default(Path.Companion, "/", false, 1, null);
        Map map0 = MapsKt.mutableMapOf(new Pair[]{TuplesKt.to(path0, new ZipEntry(path0, true, null, 0L, 0L, 0L, 0, null, 0L, 508, null))});
        Iterator iterator0 = CollectionsKt.sortedWith(list0, new Comparator() {
            @Override
            public final int compare(Object object0, Object object1) {
                return ComparisonsKt.compareValues(((ZipEntry)object0).getCanonicalPath(), ((ZipEntry)object1).getCanonicalPath());
            }
        }).iterator();
    label_3:
        while(iterator0.hasNext()) {
            Object object0 = iterator0.next();
            ZipEntry zipEntry0 = (ZipEntry)object0;
            if(((ZipEntry)map0.put(zipEntry0.getCanonicalPath(), zipEntry0)) == null) {
                while(true) {
                    Path path1 = zipEntry0.getCanonicalPath().parent();
                    if(path1 == null) {
                        continue label_3;
                    }
                    zipEntry1 = (ZipEntry)map0.get(path1);
                    if(zipEntry1 != null) {
                        break;
                    }
                    ZipEntry zipEntry2 = new ZipEntry(path1, true, null, 0L, 0L, 0L, 0, null, 0L, 508, null);
                    map0.put(path1, zipEntry2);
                    zipEntry2.getChildren().add(zipEntry0.getCanonicalPath());
                    zipEntry0 = zipEntry2;
                }
                zipEntry1.getChildren().add(zipEntry0.getCanonicalPath());
            }
        }
        return map0;
    }

    private static final Long dosDateTimeToEpochMillis(int v, int v1) {
        if(v1 == -1) {
            return null;
        }
        GregorianCalendar gregorianCalendar0 = new GregorianCalendar();
        gregorianCalendar0.set(14, 0);
        gregorianCalendar0.set((v >> 9 & 0x7F) + 1980, (v >> 5 & 15) - 1, v & 0x1F, v1 >> 11 & 0x1F, v1 >> 5 & 0x3F, (v1 & 0x1F) << 1);
        return gregorianCalendar0.getTime().getTime();
    }

    private static final String getHex(int v) [...] // 潜在的解密器

    public static final ZipFileSystem openZip(Path path0, FileSystem fileSystem0, Function1 function10) throws IOException {
        ZipFileSystem zipFileSystem0;
        int v3;
        BufferedSource bufferedSource0;
        Intrinsics.checkNotNullParameter(path0, "zipPath");
        Intrinsics.checkNotNullParameter(fileSystem0, "fileSystem");
        Intrinsics.checkNotNullParameter(function10, "predicate");
        Closeable closeable0 = fileSystem0.openReadOnly(path0);
        try {
            FileHandle fileHandle0 = (FileHandle)closeable0;
            long v = fileHandle0.size() - 22L;
            if(v < 0L) {
                throw new IOException("not a zip: size=" + fileHandle0.size());
            }
            long v2 = Math.max(v - 0x10000L, 0L);
            while(true) {
                bufferedSource0 = Okio.buffer(fileHandle0.source(v));
                v3 = FIN.finallyOpen$NT();
                if(bufferedSource0.readIntLe() == 101010256) {
                    break;
                }
                FIN.finallyCodeBegin$NT(v3);
                bufferedSource0.close();
                FIN.finallyCodeEnd$NT(v3);
                --v;
                if(v < v2) {
                    throw new IOException("not a zip: end of central directory signature not found");
                }
            }
            EocdRecord eocdRecord0 = ZipKt.readEocdRecord(bufferedSource0);
            String s = bufferedSource0.readUtf8(((long)eocdRecord0.getCommentByteCount()));
            FIN.finallyExec$NT(v3);
            if(v - 20L > 0L) {
                Closeable closeable1 = Okio.buffer(fileHandle0.source(v - 20L));
                try {
                    if(((BufferedSource)closeable1).readIntLe() == 0x7064B50) {
                        int v4 = ((BufferedSource)closeable1).readIntLe();
                        long v5 = ((BufferedSource)closeable1).readLongLe();
                        if(((BufferedSource)closeable1).readIntLe() != 1 || v4 != 0) {
                            throw new IOException("unsupported zip: spanned");
                        }
                        Closeable closeable2 = Okio.buffer(fileHandle0.source(v5));
                        try {
                            int v6 = ((BufferedSource)closeable2).readIntLe();
                            if(v6 != 0x6064B50) {
                                throw new IOException("bad zip: expected 0x6064b50 but was " + ZipKt.getHex(v6));
                            }
                            eocdRecord0 = ZipKt.readZip64EocdRecord(((BufferedSource)closeable2), eocdRecord0);
                        }
                        catch(Throwable throwable2) {
                            CloseableKt.closeFinally(closeable2, throwable2);
                            throw throwable2;
                        }
                        CloseableKt.closeFinally(closeable2, null);
                    }
                }
                catch(Throwable throwable1) {
                    CloseableKt.closeFinally(closeable1, throwable1);
                    throw throwable1;
                }
                CloseableKt.closeFinally(closeable1, null);
            }
            List list0 = new ArrayList();
            Closeable closeable3 = Okio.buffer(fileHandle0.source(eocdRecord0.getCentralDirectoryOffset()));
            try {
                long v7 = eocdRecord0.getEntryCount();
                for(long v1 = 0L; v1 < v7; ++v1) {
                    ZipEntry zipEntry0 = ZipKt.readEntry(((BufferedSource)closeable3));
                    if(zipEntry0.getOffset() >= eocdRecord0.getCentralDirectoryOffset()) {
                        throw new IOException("bad zip: local file header offset >= central directory offset");
                    }
                    if(((Boolean)function10.invoke(zipEntry0)).booleanValue()) {
                        list0.add(zipEntry0);
                    }
                }
            }
            catch(Throwable throwable3) {
                CloseableKt.closeFinally(closeable3, throwable3);
                throw throwable3;
            }
            CloseableKt.closeFinally(closeable3, null);
            zipFileSystem0 = new ZipFileSystem(path0, fileSystem0, ZipKt.buildIndex(list0), s);
        }
        catch(Throwable throwable0) {
            CloseableKt.closeFinally(closeable0, throwable0);
            throw throwable0;
        }
        CloseableKt.closeFinally(closeable0, null);
        return zipFileSystem0;
    }

    public static ZipFileSystem openZip$default(Path path0, FileSystem fileSystem0, Function1 function10, int v, Object object0) throws IOException {
        if((v & 4) != 0) {
            function10 = okio.internal.ZipKt.openZip.1.INSTANCE;
        }
        return ZipKt.openZip(path0, fileSystem0, function10);

        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "it", "Lokio/internal/ZipEntry;", "invoke", "(Lokio/internal/ZipEntry;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 6, 0}, xi = 0x30)
        final class okio.internal.ZipKt.openZip.1 extends Lambda implements Function1 {
            public static final okio.internal.ZipKt.openZip.1 INSTANCE;

            static {
                okio.internal.ZipKt.openZip.1.INSTANCE = new okio.internal.ZipKt.openZip.1();
            }

            okio.internal.ZipKt.openZip.1() {
                super(1);
            }

            public final Boolean invoke(ZipEntry zipEntry0) {
                Intrinsics.checkNotNullParameter(zipEntry0, "it");
                return true;
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((ZipEntry)object0));
            }
        }

    }

    public static final ZipEntry readEntry(BufferedSource bufferedSource0) throws IOException {
        Intrinsics.checkNotNullParameter(bufferedSource0, "<this>");
        int v = bufferedSource0.readIntLe();
        if(v != 0x2014B50) {
            throw new IOException("bad zip: expected 0x2014b50 but was " + ZipKt.getHex(v));
        }
        bufferedSource0.skip(4L);
        int v1 = bufferedSource0.readShortLe();
        if((v1 & 1) != 0) {
            throw new IOException("unsupported zip: general purpose bit flag=" + ZipKt.getHex(v1 & 0xFFFF));
        }
        int v2 = bufferedSource0.readShortLe();
        int v3 = bufferedSource0.readShortLe();
        Long long0 = ZipKt.dosDateTimeToEpochMillis(bufferedSource0.readShortLe() & 0xFFFF, v3 & 0xFFFF);
        long v4 = ((long)bufferedSource0.readIntLe()) & 0xFFFFFFFFL;
        LongRef ref$LongRef0 = new LongRef();
        ref$LongRef0.element = ((long)bufferedSource0.readIntLe()) & 0xFFFFFFFFL;
        LongRef ref$LongRef1 = new LongRef();
        ref$LongRef1.element = ((long)bufferedSource0.readIntLe()) & 0xFFFFFFFFL;
        int v5 = bufferedSource0.readShortLe();
        int v6 = bufferedSource0.readShortLe();
        int v7 = bufferedSource0.readShortLe();
        bufferedSource0.skip(8L);
        LongRef ref$LongRef2 = new LongRef();
        ref$LongRef2.element = ((long)bufferedSource0.readIntLe()) & 0xFFFFFFFFL;
        String s = bufferedSource0.readUtf8(((long)(v5 & 0xFFFF)));
        if(StringsKt.contains$default(s, '\u0000', false, 2, null)) {
            throw new IOException("bad zip: filename contains 0x00");
        }
        long v8 = ref$LongRef1.element == 0xFFFFFFFFL ? 8L : 0L;
        long v9 = ref$LongRef0.element == 0xFFFFFFFFL ? v8 + 8L : v8;
        if(ref$LongRef2.element == 0xFFFFFFFFL) {
            v9 += 8L;
        }
        BooleanRef ref$BooleanRef0 = new BooleanRef();
        ZipKt.readExtra(bufferedSource0, v6 & 0xFFFF, new Function2(ref$BooleanRef0, v9, ref$LongRef1, bufferedSource0, ref$LongRef0, ref$LongRef2) {
            final LongRef $compressedSize;
            final BooleanRef $hasZip64Extra;
            final LongRef $offset;
            final long $requiredZip64ExtraSize;
            final LongRef $size;
            final BufferedSource $this_readEntry;

            {
                this.$hasZip64Extra = ref$BooleanRef0;
                this.$requiredZip64ExtraSize = v;
                this.$size = ref$LongRef0;
                this.$this_readEntry = bufferedSource0;
                this.$compressedSize = ref$LongRef1;
                this.$offset = ref$LongRef2;
                super(2);
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                this.invoke(((Number)object0).intValue(), ((Number)object1).longValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int v, long v1) {
                if(v == 1) {
                    if(this.$hasZip64Extra.element) {
                        throw new IOException("bad zip: zip64 extra repeated");
                    }
                    this.$hasZip64Extra.element = true;
                    if(v1 < this.$requiredZip64ExtraSize) {
                        throw new IOException("bad zip: zip64 extra too short");
                    }
                    this.$size.element = this.$size.element == 0xFFFFFFFFL ? this.$this_readEntry.readLongLe() : this.$size.element;
                    long v2 = 0L;
                    this.$compressedSize.element = this.$compressedSize.element == 0xFFFFFFFFL ? this.$this_readEntry.readLongLe() : 0L;
                    LongRef ref$LongRef0 = this.$offset;
                    if(ref$LongRef0.element == 0xFFFFFFFFL) {
                        v2 = this.$this_readEntry.readLongLe();
                    }
                    ref$LongRef0.element = v2;
                }
            }
        });
        if(v9 > 0L && !ref$BooleanRef0.element) {
            throw new IOException("bad zip: zip64 extra required but absent");
        }
        String s1 = bufferedSource0.readUtf8(((long)(v7 & 0xFFFF)));
        return new ZipEntry(Companion.get$default(Path.Companion, "/", false, 1, null).resolve(s), StringsKt.endsWith$default(s, "/", false, 2, null), s1, v4, ref$LongRef0.element, ref$LongRef1.element, v2 & 0xFFFF, long0, ref$LongRef2.element);
    }

    private static final EocdRecord readEocdRecord(BufferedSource bufferedSource0) throws IOException {
        int v = bufferedSource0.readShortLe();
        int v1 = bufferedSource0.readShortLe();
        int v2 = bufferedSource0.readShortLe();
        if(((long)(v2 & 0xFFFF)) != ((long)(bufferedSource0.readShortLe() & 0xFFFF)) || (v & 0xFFFF) != 0 || (v1 & 0xFFFF) != 0) {
            throw new IOException("unsupported zip: spanned");
        }
        bufferedSource0.skip(4L);
        return new EocdRecord(((long)(v2 & 0xFFFF)), 0xFFFFFFFFL & ((long)bufferedSource0.readIntLe()), bufferedSource0.readShortLe() & 0xFFFF);
    }

    private static final void readExtra(BufferedSource bufferedSource0, int v, Function2 function20) {
        for(long v1 = (long)v; v1 != 0L; v1 = v1 - 4L - v3) {
            if(v1 < 4L) {
                throw new IOException("bad zip: truncated header in extra field");
            }
            int v2 = bufferedSource0.readShortLe() & 0xFFFF;
            long v3 = ((long)bufferedSource0.readShortLe()) & 0xFFFFL;
            if(v1 - 4L < v3) {
                throw new IOException("bad zip: truncated value in extra field");
            }
            bufferedSource0.require(v3);
            long v4 = bufferedSource0.getBuffer().size();
            function20.invoke(v2, v3);
            long v5 = bufferedSource0.getBuffer().size() + v3 - v4;
            int v6 = Long.compare(v5, 0L);
            if(v6 < 0) {
                throw new IOException("unsupported zip: too many bytes processed for " + v2);
            }
            if(v6 > 0) {
                bufferedSource0.getBuffer().skip(v5);
            }
        }
    }

    public static final FileMetadata readLocalHeader(BufferedSource bufferedSource0, FileMetadata fileMetadata0) {
        Intrinsics.checkNotNullParameter(bufferedSource0, "<this>");
        Intrinsics.checkNotNullParameter(fileMetadata0, "basicMetadata");
        FileMetadata fileMetadata1 = ZipKt.readOrSkipLocalHeader(bufferedSource0, fileMetadata0);
        Intrinsics.checkNotNull(fileMetadata1);
        return fileMetadata1;
    }

    private static final FileMetadata readOrSkipLocalHeader(BufferedSource bufferedSource0, FileMetadata fileMetadata0) {
        ObjectRef ref$ObjectRef0 = new ObjectRef();
        ref$ObjectRef0.element = fileMetadata0 == null ? null : fileMetadata0.getLastModifiedAtMillis();
        ObjectRef ref$ObjectRef1 = new ObjectRef();
        ObjectRef ref$ObjectRef2 = new ObjectRef();
        int v = bufferedSource0.readIntLe();
        if(v != 0x4034B50) {
            throw new IOException("bad zip: expected 0x4034b50 but was " + ZipKt.getHex(v));
        }
        bufferedSource0.skip(2L);
        int v1 = bufferedSource0.readShortLe();
        if((v1 & 1) != 0) {
            throw new IOException("unsupported zip: general purpose bit flag=" + ZipKt.getHex(v1 & 0xFFFF));
        }
        bufferedSource0.skip(18L);
        long v2 = ((long)bufferedSource0.readShortLe()) & 0xFFFFL;
        int v3 = bufferedSource0.readShortLe();
        bufferedSource0.skip(v2);
        if(fileMetadata0 == null) {
            bufferedSource0.skip(((long)(v3 & 0xFFFF)));
            return null;
        }
        ZipKt.readExtra(bufferedSource0, v3 & 0xFFFF, new Function2(bufferedSource0, ref$ObjectRef0, ref$ObjectRef1, ref$ObjectRef2) {
            final ObjectRef $createdAtMillis;
            final ObjectRef $lastAccessedAtMillis;
            final ObjectRef $lastModifiedAtMillis;
            final BufferedSource $this_readOrSkipLocalHeader;

            {
                this.$this_readOrSkipLocalHeader = bufferedSource0;
                this.$lastModifiedAtMillis = ref$ObjectRef0;
                this.$lastAccessedAtMillis = ref$ObjectRef1;
                this.$createdAtMillis = ref$ObjectRef2;
                super(2);
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                this.invoke(((Number)object0).intValue(), ((Number)object1).longValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int v, long v1) {
                long v2 = 1L;
                if(v == 0x5455) {
                    if(v1 < 1L) {
                        throw new IOException("bad zip: extended timestamp extra too short");
                    }
                    int v3 = this.$this_readOrSkipLocalHeader.readByte();
                    boolean z = false;
                    boolean z1 = (v3 & 1) == 1;
                    boolean z2 = (v3 & 2) == 2;
                    if((v3 & 4) == 4) {
                        z = true;
                    }
                    BufferedSource bufferedSource0 = this.$this_readOrSkipLocalHeader;
                    if(z1) {
                        v2 = 5L;
                    }
                    if(z2) {
                        v2 += 4L;
                    }
                    if(z) {
                        v2 += 4L;
                    }
                    if(v1 < v2) {
                        throw new IOException("bad zip: extended timestamp extra too short");
                    }
                    if(z1) {
                        this.$lastModifiedAtMillis.element = (long)(((long)bufferedSource0.readIntLe()) * 1000L);
                    }
                    if(z2) {
                        this.$lastAccessedAtMillis.element = (long)(((long)this.$this_readOrSkipLocalHeader.readIntLe()) * 1000L);
                    }
                    if(z) {
                        this.$createdAtMillis.element = (long)(((long)this.$this_readOrSkipLocalHeader.readIntLe()) * 1000L);
                    }
                }
            }
        });
        return new FileMetadata(fileMetadata0.isRegularFile(), fileMetadata0.isDirectory(), null, fileMetadata0.getSize(), ((Long)ref$ObjectRef2.element), ((Long)ref$ObjectRef0.element), ((Long)ref$ObjectRef1.element), null, 0x80, null);
    }

    private static final EocdRecord readZip64EocdRecord(BufferedSource bufferedSource0, EocdRecord eocdRecord0) throws IOException {
        bufferedSource0.skip(12L);
        int v = bufferedSource0.readIntLe();
        int v1 = bufferedSource0.readIntLe();
        long v2 = bufferedSource0.readLongLe();
        if(v2 != bufferedSource0.readLongLe() || v != 0 || v1 != 0) {
            throw new IOException("unsupported zip: spanned");
        }
        bufferedSource0.skip(8L);
        return new EocdRecord(v2, bufferedSource0.readLongLe(), eocdRecord0.getCommentByteCount());
    }

    public static final void skipLocalHeader(BufferedSource bufferedSource0) {
        Intrinsics.checkNotNullParameter(bufferedSource0, "<this>");
        ZipKt.readOrSkipLocalHeader(bufferedSource0, null);
    }
}

