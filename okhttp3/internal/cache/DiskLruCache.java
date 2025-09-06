package okhttp3.internal.cache;

import java.io.Closeable;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.Flushable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.ExceptionsKt;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import okhttp3.internal._UtilCommonKt;
import okhttp3.internal._UtilJvmKt;
import okhttp3.internal.concurrent.Task;
import okhttp3.internal.concurrent.TaskQueue;
import okhttp3.internal.concurrent.TaskRunner;
import okhttp3.internal.platform.Platform;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.FileSystem;
import okio.ForwardingFileSystem;
import okio.ForwardingSource;
import okio.Okio;
import okio.Path;
import okio.Sink;
import okio.Source;

@Metadata(d1 = {"\u0000}\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000E\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000F\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010)\n\u0002\b\u0007*\u0001\u0014\u0018\u0000 \\2\u00020\u00012\u00020\u0002:\u0004\\]^_B5\u0012\u0006\u0010\u0003\u001A\u00020\u0004\u0012\u0006\u0010\u0005\u001A\u00020\u0006\u0012\u0006\u0010\u0007\u001A\u00020\b\u0012\u0006\u0010\t\u001A\u00020\b\u0012\u0006\u0010\n\u001A\u00020\u000B\u0012\u0006\u0010\f\u001A\u00020\r\u00A2\u0006\u0002\u0010\u000EJ\b\u00109\u001A\u00020:H\u0002J\b\u0010;\u001A\u00020:H\u0016J!\u0010<\u001A\u00020:2\n\u0010=\u001A\u00060>R\u00020\u00002\u0006\u0010?\u001A\u00020\u0010H\u0000\u00A2\u0006\u0002\b@J\u0006\u0010A\u001A\u00020:J \u0010B\u001A\b\u0018\u00010>R\u00020\u00002\u0006\u0010C\u001A\u00020(2\b\b\u0002\u0010D\u001A\u00020\u000BH\u0007J\u0006\u0010E\u001A\u00020:J\b\u0010F\u001A\u00020:H\u0016J\u0017\u0010G\u001A\b\u0018\u00010HR\u00020\u00002\u0006\u0010C\u001A\u00020(H\u0086\u0002J\u0006\u0010I\u001A\u00020:J\u0006\u0010J\u001A\u00020\u0010J\b\u0010K\u001A\u00020\u0010H\u0002J\b\u0010L\u001A\u00020%H\u0002J\b\u0010M\u001A\u00020:H\u0002J\b\u0010N\u001A\u00020:H\u0002J\u0010\u0010O\u001A\u00020:2\u0006\u0010P\u001A\u00020(H\u0002J\r\u0010Q\u001A\u00020:H\u0000\u00A2\u0006\u0002\bRJ\u000E\u0010S\u001A\u00020\u00102\u0006\u0010C\u001A\u00020(J\u0019\u0010T\u001A\u00020\u00102\n\u0010U\u001A\u00060)R\u00020\u0000H\u0000\u00A2\u0006\u0002\bVJ\b\u0010W\u001A\u00020\u0010H\u0002J\u0006\u00106\u001A\u00020\u000BJ\u0010\u0010X\u001A\f\u0012\b\u0012\u00060HR\u00020\u00000YJ\u0006\u0010Z\u001A\u00020:J\u0010\u0010[\u001A\u00020:2\u0006\u0010C\u001A\u00020(H\u0002R\u000E\u0010\u0007\u001A\u00020\bX\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u000F\u001A\u00020\u0010X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u0011\u001A\u00020\u0012X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001A\u00020\u0014X\u0082\u0004\u00A2\u0006\u0004\n\u0002\u0010\u0015R\u001A\u0010\u0016\u001A\u00020\u0010X\u0080\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001AR\u0011\u0010\u0005\u001A\u00020\u0006\u00A2\u0006\b\n\u0000\u001A\u0004\b\u001B\u0010\u001CR\u0014\u0010\u0003\u001A\u00020\u0004X\u0080\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b\u001D\u0010\u001ER\u000E\u0010\u001F\u001A\u00020\u0010X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u000E\u0010 \u001A\u00020\u0010X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u000E\u0010!\u001A\u00020\u0006X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u000E\u0010\"\u001A\u00020\u0006X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u000E\u0010#\u001A\u00020\u0006X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010$\u001A\u0004\u0018\u00010%X\u0082\u000E\u00A2\u0006\u0002\n\u0000R8\u0010&\u001A&\u0012\u0004\u0012\u00020(\u0012\b\u0012\u00060)R\u00020\u00000\'j\u0012\u0012\u0004\u0012\u00020(\u0012\b\u0012\u00060)R\u00020\u0000`*X\u0080\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b+\u0010,R&\u0010\n\u001A\u00020\u000B2\u0006\u0010-\u001A\u00020\u000B8F@FX\u0086\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b.\u0010/\"\u0004\b0\u00101R\u000E\u00102\u001A\u00020\u0010X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u000E\u00103\u001A\u00020\u0010X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u000E\u00104\u001A\u00020\u000BX\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u000E\u00105\u001A\u00020\bX\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u000E\u00106\u001A\u00020\u000BX\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u0014\u0010\t\u001A\u00020\bX\u0080\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b7\u00108\u00A8\u0006`"}, d2 = {"Lokhttp3/internal/cache/DiskLruCache;", "Ljava/io/Closeable;", "Ljava/io/Flushable;", "fileSystem", "Lokio/FileSystem;", "directory", "Lokio/Path;", "appVersion", "", "valueCount", "maxSize", "", "taskRunner", "Lokhttp3/internal/concurrent/TaskRunner;", "(Lokio/FileSystem;Lokio/Path;IIJLokhttp3/internal/concurrent/TaskRunner;)V", "civilizedFileSystem", "", "cleanupQueue", "Lokhttp3/internal/concurrent/TaskQueue;", "cleanupTask", "okhttp3/internal/cache/DiskLruCache$cleanupTask$1", "Lokhttp3/internal/cache/DiskLruCache$cleanupTask$1;", "closed", "getClosed$okhttp", "()Z", "setClosed$okhttp", "(Z)V", "getDirectory", "()Lokio/Path;", "getFileSystem$okhttp", "()Lokio/FileSystem;", "hasJournalErrors", "initialized", "journalFile", "journalFileBackup", "journalFileTmp", "journalWriter", "Lokio/BufferedSink;", "lruEntries", "Ljava/util/LinkedHashMap;", "", "Lokhttp3/internal/cache/DiskLruCache$Entry;", "Lkotlin/collections/LinkedHashMap;", "getLruEntries$okhttp", "()Ljava/util/LinkedHashMap;", "value", "getMaxSize", "()J", "setMaxSize", "(J)V", "mostRecentRebuildFailed", "mostRecentTrimFailed", "nextSequenceNumber", "redundantOpCount", "size", "getValueCount$okhttp", "()I", "checkNotClosed", "", "close", "completeEdit", "editor", "Lokhttp3/internal/cache/DiskLruCache$Editor;", "success", "completeEdit$okhttp", "delete", "edit", "key", "expectedSequenceNumber", "evictAll", "flush", "get", "Lokhttp3/internal/cache/DiskLruCache$Snapshot;", "initialize", "isClosed", "journalRebuildRequired", "newJournalWriter", "processJournal", "readJournal", "readJournalLine", "line", "rebuildJournal", "rebuildJournal$okhttp", "remove", "removeEntry", "entry", "removeEntry$okhttp", "removeOldestEntry", "snapshots", "", "trimToSize", "validateKey", "Companion", "Editor", "Entry", "Snapshot", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
public final class DiskLruCache implements Closeable, Flushable, AutoCloseable {
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0010\u0010\u0003\u001A\u00020\u00048\u0006X\u0087D¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001A\u00020\u00068\u0006X\u0087D¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001A\u00020\u00068\u0006X\u0087D¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001A\u00020\u00068\u0006X\u0087D¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001A\u00020\u00068\u0006X\u0087D¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001A\u00020\u00068\u0006X\u0087D¢\u0006\u0002\n\u0000R\u0010\u0010\u000B\u001A\u00020\f8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001A\u00020\u00068\u0006X\u0087D¢\u0006\u0002\n\u0000R\u0010\u0010\u000E\u001A\u00020\u00068\u0006X\u0087D¢\u0006\u0002\n\u0000R\u0010\u0010\u000F\u001A\u00020\u00068\u0006X\u0087D¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001A\u00020\u00068\u0006X\u0087D¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lokhttp3/internal/cache/DiskLruCache$Companion;", "", "()V", "ANY_SEQUENCE_NUMBER", "", "CLEAN", "", "DIRTY", "JOURNAL_FILE", "JOURNAL_FILE_BACKUP", "JOURNAL_FILE_TEMP", "LEGAL_KEY_PATTERN", "Lkotlin/text/Regex;", "MAGIC", "READ", "REMOVE", "VERSION_1", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }
    }

    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0002\b\u0003\n\u0002\u0010\u0018\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0013\b\u0000\u0012\n\u0010\u0002\u001A\u00060\u0003R\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0006\u0010\u000E\u001A\u00020\u000FJ\u0006\u0010\u0010\u001A\u00020\u000FJ\r\u0010\u0011\u001A\u00020\u000FH\u0000¢\u0006\u0002\b\u0012J\u000E\u0010\u0013\u001A\u00020\u00142\u0006\u0010\u0015\u001A\u00020\u0016J\u0010\u0010\u0017\u001A\u0004\u0018\u00010\u00182\u0006\u0010\u0015\u001A\u00020\u0016R\u000E\u0010\u0006\u001A\u00020\u0007X\u0082\u000E¢\u0006\u0002\n\u0000R\u0018\u0010\u0002\u001A\u00060\u0003R\u00020\u0004X\u0080\u0004¢\u0006\b\n\u0000\u001A\u0004\b\b\u0010\tR\u0016\u0010\n\u001A\u0004\u0018\u00010\u000BX\u0080\u0004¢\u0006\b\n\u0000\u001A\u0004\b\f\u0010\r¨\u0006\u0019"}, d2 = {"Lokhttp3/internal/cache/DiskLruCache$Editor;", "", "entry", "Lokhttp3/internal/cache/DiskLruCache$Entry;", "Lokhttp3/internal/cache/DiskLruCache;", "(Lokhttp3/internal/cache/DiskLruCache;Lokhttp3/internal/cache/DiskLruCache$Entry;)V", "done", "", "getEntry$okhttp", "()Lokhttp3/internal/cache/DiskLruCache$Entry;", "written", "", "getWritten$okhttp", "()[Z", "abort", "", "commit", "detach", "detach$okhttp", "newSink", "Lokio/Sink;", "index", "", "newSource", "Lokio/Source;", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    public final class Editor {
        private boolean done;
        private final Entry entry;
        private final boolean[] written;

        public Editor(Entry diskLruCache$Entry0) {
            Intrinsics.checkNotNullParameter(diskLruCache$Entry0, "entry");
            DiskLruCache.this = diskLruCache0;
            super();
            this.entry = diskLruCache$Entry0;
            this.written = diskLruCache$Entry0.getReadable$okhttp() ? null : new boolean[diskLruCache0.getValueCount$okhttp()];
        }

        public final void abort() throws IOException {
            DiskLruCache diskLruCache0 = DiskLruCache.this;
            synchronized(diskLruCache0) {
                if(!this.done) {
                    if(Intrinsics.areEqual(this.entry.getCurrentEditor$okhttp(), this)) {
                        diskLruCache0.completeEdit$okhttp(this, false);
                    }
                    this.done = true;
                    return;
                }
            }
            throw new IllegalStateException("Check failed.");
        }

        public final void commit() throws IOException {
            DiskLruCache diskLruCache0 = DiskLruCache.this;
            synchronized(diskLruCache0) {
                if(!this.done) {
                    if(Intrinsics.areEqual(this.entry.getCurrentEditor$okhttp(), this)) {
                        diskLruCache0.completeEdit$okhttp(this, true);
                    }
                    this.done = true;
                    return;
                }
            }
            throw new IllegalStateException("Check failed.");
        }

        public final void detach$okhttp() {
            if(Intrinsics.areEqual(this.entry.getCurrentEditor$okhttp(), this)) {
                if(DiskLruCache.this.civilizedFileSystem) {
                    DiskLruCache.this.completeEdit$okhttp(this, false);
                    return;
                }
                this.entry.setZombie$okhttp(true);
            }
        }

        public final Entry getEntry$okhttp() {
            return this.entry;
        }

        public final boolean[] getWritten$okhttp() {
            return this.written;
        }

        public final Sink newSink(int v) {
            Sink sink1;
            DiskLruCache diskLruCache0 = DiskLruCache.this;
            synchronized(diskLruCache0) {
                if(!this.done) {
                    if(!Intrinsics.areEqual(this.entry.getCurrentEditor$okhttp(), this)) {
                        return Okio.blackhole();
                    }
                    if(!this.entry.getReadable$okhttp()) {
                        Intrinsics.checkNotNull(this.written);
                        this.written[v] = true;
                    }
                    Path path0 = (Path)this.entry.getDirtyFiles$okhttp().get(v);
                    try {
                        sink1 = diskLruCache0.getFileSystem$okhttp().sink(path0);
                    }
                    catch(FileNotFoundException unused_ex) {
                        return Okio.blackhole();
                    }
                    return new FaultHidingSink(sink1, new Function1(this) {
                        {
                            DiskLruCache.this = diskLruCache0;
                            Editor.this = diskLruCache$Editor0;
                            super(1);
                        }

                        @Override  // kotlin.jvm.functions.Function1
                        public Object invoke(Object object0) {
                            this.invoke(((IOException)object0));
                            return Unit.INSTANCE;
                        }

                        public final void invoke(IOException iOException0) {
                            Intrinsics.checkNotNullParameter(iOException0, "it");
                            synchronized(DiskLruCache.this) {
                                Editor.this.detach$okhttp();
                            }
                        }
                    });
                }
            }
            throw new IllegalStateException("Check failed.");
        }

        public final Source newSource(int v) {
            Source source0 = null;
            DiskLruCache diskLruCache0 = DiskLruCache.this;
            synchronized(diskLruCache0) {
                if(!this.done) {
                    if(this.entry.getReadable$okhttp() && Intrinsics.areEqual(this.entry.getCurrentEditor$okhttp(), this) && !this.entry.getZombie$okhttp()) {
                        try {
                            source0 = diskLruCache0.getFileSystem$okhttp().source(((Path)this.entry.getCleanFiles$okhttp().get(v)));
                        }
                        catch(FileNotFoundException unused_ex) {
                        }
                        return source0;
                    }
                    return null;
                }
            }
            throw new IllegalStateException("Check failed.");
        }
    }

    @Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0016\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000B\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0010\u0001\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0080\u0004\u0018\u00002\u00020\u0001B\u000F\b\u0000\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u00A2\u0006\u0002\u0010\u0004J\u0016\u0010.\u001A\u00020/2\f\u00100\u001A\b\u0012\u0004\u0012\u00020\u000301H\u0002J\u0010\u00102\u001A\u0002032\u0006\u00104\u001A\u00020\u001AH\u0002J\u001B\u00105\u001A\u0002062\f\u00100\u001A\b\u0012\u0004\u0012\u00020\u000301H\u0000\u00A2\u0006\u0002\b7J\u0013\u00108\u001A\b\u0018\u000109R\u00020\fH\u0000\u00A2\u0006\u0002\b:J\u0015\u0010;\u001A\u0002062\u0006\u0010<\u001A\u00020=H\u0000\u00A2\u0006\u0002\b>R\u001A\u0010\u0005\u001A\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0080\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b\b\u0010\tR \u0010\n\u001A\b\u0018\u00010\u000BR\u00020\fX\u0080\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b\r\u0010\u000E\"\u0004\b\u000F\u0010\u0010R\u001A\u0010\u0011\u001A\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0080\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0012\u0010\tR\u0014\u0010\u0002\u001A\u00020\u0003X\u0080\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0015\u001A\u00020\u0016X\u0080\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0017\u0010\u0018R\u001A\u0010\u0019\u001A\u00020\u001AX\u0080\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b\u001B\u0010\u001C\"\u0004\b\u001D\u0010\u001ER\u001A\u0010\u001F\u001A\u00020 X\u0080\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u001A\u0010%\u001A\u00020&X\u0080\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b\'\u0010(\"\u0004\b)\u0010*R\u001A\u0010+\u001A\u00020 X\u0080\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b,\u0010\"\"\u0004\b-\u0010$\u00A8\u0006?"}, d2 = {"Lokhttp3/internal/cache/DiskLruCache$Entry;", "", "key", "", "(Lokhttp3/internal/cache/DiskLruCache;Ljava/lang/String;)V", "cleanFiles", "", "Lokio/Path;", "getCleanFiles$okhttp", "()Ljava/util/List;", "currentEditor", "Lokhttp3/internal/cache/DiskLruCache$Editor;", "Lokhttp3/internal/cache/DiskLruCache;", "getCurrentEditor$okhttp", "()Lokhttp3/internal/cache/DiskLruCache$Editor;", "setCurrentEditor$okhttp", "(Lokhttp3/internal/cache/DiskLruCache$Editor;)V", "dirtyFiles", "getDirtyFiles$okhttp", "getKey$okhttp", "()Ljava/lang/String;", "lengths", "", "getLengths$okhttp", "()[J", "lockingSourceCount", "", "getLockingSourceCount$okhttp", "()I", "setLockingSourceCount$okhttp", "(I)V", "readable", "", "getReadable$okhttp", "()Z", "setReadable$okhttp", "(Z)V", "sequenceNumber", "", "getSequenceNumber$okhttp", "()J", "setSequenceNumber$okhttp", "(J)V", "zombie", "getZombie$okhttp", "setZombie$okhttp", "invalidLengths", "", "strings", "", "newSource", "Lokio/Source;", "index", "setLengths", "", "setLengths$okhttp", "snapshot", "Lokhttp3/internal/cache/DiskLruCache$Snapshot;", "snapshot$okhttp", "writeLengths", "writer", "Lokio/BufferedSink;", "writeLengths$okhttp", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    public final class Entry {
        private final List cleanFiles;
        private Editor currentEditor;
        private final List dirtyFiles;
        private final String key;
        private final long[] lengths;
        private int lockingSourceCount;
        private boolean readable;
        private long sequenceNumber;
        private boolean zombie;

        public Entry(String s) {
            Intrinsics.checkNotNullParameter(s, "key");
            DiskLruCache.this = diskLruCache0;
            super();
            this.key = s;
            this.lengths = new long[diskLruCache0.getValueCount$okhttp()];
            this.cleanFiles = new ArrayList();
            this.dirtyFiles = new ArrayList();
            StringBuilder stringBuilder0 = new StringBuilder(s);
            stringBuilder0.append('.');
            int v = stringBuilder0.length();
            int v1 = diskLruCache0.getValueCount$okhttp();
            for(int v2 = 0; v2 < v1; ++v2) {
                stringBuilder0.append(v2);
                String s1 = stringBuilder0.toString();
                Intrinsics.checkNotNullExpressionValue(s1, "fileBuilder.toString()");
                Path path0 = DiskLruCache.this.getDirectory().resolve(s1);
                this.cleanFiles.add(path0);
                stringBuilder0.append(".tmp");
                String s2 = stringBuilder0.toString();
                Intrinsics.checkNotNullExpressionValue(s2, "fileBuilder.toString()");
                Path path1 = DiskLruCache.this.getDirectory().resolve(s2);
                this.dirtyFiles.add(path1);
                stringBuilder0.setLength(v);
            }
        }

        public final List getCleanFiles$okhttp() {
            return this.cleanFiles;
        }

        public final Editor getCurrentEditor$okhttp() {
            return this.currentEditor;
        }

        public final List getDirtyFiles$okhttp() {
            return this.dirtyFiles;
        }

        public final String getKey$okhttp() {
            return this.key;
        }

        public final long[] getLengths$okhttp() {
            return this.lengths;
        }

        public final int getLockingSourceCount$okhttp() {
            return this.lockingSourceCount;
        }

        public final boolean getReadable$okhttp() {
            return this.readable;
        }

        public final long getSequenceNumber$okhttp() {
            return this.sequenceNumber;
        }

        public final boolean getZombie$okhttp() {
            return this.zombie;
        }

        private final Void invalidLengths(List list0) throws IOException {
            throw new IOException("unexpected journal line: " + list0);
        }

        private final Source newSource(int v) {
            Source source0 = DiskLruCache.this.getFileSystem$okhttp().source(((Path)this.cleanFiles.get(v)));
            if(DiskLruCache.this.civilizedFileSystem) {
                return source0;
            }
            ++this.lockingSourceCount;
            return new ForwardingSource(DiskLruCache.this) {
                private boolean closed;

                @Override  // okio.ForwardingSource
                public void close() {
                    super.close();
                    if(!this.closed) {
                        this.closed = true;
                        DiskLruCache diskLruCache0 = this;
                        Entry diskLruCache$Entry0 = Entry.this;
                        synchronized(diskLruCache0) {
                            diskLruCache$Entry0.setLockingSourceCount$okhttp(diskLruCache$Entry0.getLockingSourceCount$okhttp() - 1);
                            if(diskLruCache$Entry0.getLockingSourceCount$okhttp() == 0 && diskLruCache$Entry0.getZombie$okhttp()) {
                                diskLruCache0.removeEntry$okhttp(diskLruCache$Entry0);
                            }
                        }
                    }
                }
            };
        }

        public final void setCurrentEditor$okhttp(Editor diskLruCache$Editor0) {
            this.currentEditor = diskLruCache$Editor0;
        }

        public final void setLengths$okhttp(List list0) throws IOException {
            Intrinsics.checkNotNullParameter(list0, "strings");
            if(list0.size() == DiskLruCache.this.getValueCount$okhttp()) {
                try {
                    int v = list0.size();
                    for(int v1 = 0; v1 < v; ++v1) {
                        this.lengths[v1] = Long.parseLong(((String)list0.get(v1)));
                    }
                    return;
                }
                catch(NumberFormatException unused_ex) {
                    this.invalidLengths(list0);
                    throw new KotlinNothingValueException();
                }
            }
            this.invalidLengths(list0);
            throw new KotlinNothingValueException();
        }

        public final void setLockingSourceCount$okhttp(int v) {
            this.lockingSourceCount = v;
        }

        public final void setReadable$okhttp(boolean z) {
            this.readable = z;
        }

        public final void setSequenceNumber$okhttp(long v) {
            this.sequenceNumber = v;
        }

        public final void setZombie$okhttp(boolean z) {
            this.zombie = z;
        }

        public final Snapshot snapshot$okhttp() {
            DiskLruCache diskLruCache0 = DiskLruCache.this;
            if(_UtilJvmKt.assertionsEnabled && !Thread.holdsLock(diskLruCache0)) {
                throw new AssertionError("Thread jeb-dexdec-sb-st-13485 MUST hold lock on " + diskLruCache0);
            }
            if(!this.readable) {
                return null;
            }
            if(!DiskLruCache.this.civilizedFileSystem && (this.currentEditor != null || this.zombie)) {
                return null;
            }
            List list0 = new ArrayList();
            long[] arr_v = (long[])this.lengths.clone();
            try {
                int v = DiskLruCache.this.getValueCount$okhttp();
                for(int v1 = 0; v1 < v; ++v1) {
                    list0.add(this.newSource(v1));
                }
                return new Snapshot(DiskLruCache.this, this.key, this.sequenceNumber, list0, arr_v);
            }
            catch(FileNotFoundException unused_ex) {
                for(Object object0: list0) {
                    _UtilCommonKt.closeQuietly(((Source)object0));
                }
                try {
                    DiskLruCache.this.removeEntry$okhttp(this);
                }
                catch(IOException unused_ex) {
                }
                return null;
            }
        }

        public final void writeLengths$okhttp(BufferedSink bufferedSink0) throws IOException {
            Intrinsics.checkNotNullParameter(bufferedSink0, "writer");
            long[] arr_v = this.lengths;
            for(int v = 0; v < arr_v.length; ++v) {
                long v1 = arr_v[v];
                bufferedSink0.writeByte(0x20).writeDecimalLong(v1);
            }
        }
    }

    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0016\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0004\u0018\u00002\u00020\u0001B-\b\u0000\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0005\u0012\f\u0010\u0006\u001A\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0010\t\u001A\u00020\n¢\u0006\u0002\u0010\u000BJ\b\u0010\f\u001A\u00020\rH\u0016J\f\u0010\u000E\u001A\b\u0018\u00010\u000FR\u00020\u0010J\u000E\u0010\u0011\u001A\u00020\u00052\u0006\u0010\u0012\u001A\u00020\u0013J\u000E\u0010\u0014\u001A\u00020\b2\u0006\u0010\u0012\u001A\u00020\u0013J\u0006\u0010\u0002\u001A\u00020\u0003R\u000E\u0010\u0002\u001A\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\t\u001A\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0004\u001A\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001A\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lokhttp3/internal/cache/DiskLruCache$Snapshot;", "Ljava/io/Closeable;", "key", "", "sequenceNumber", "", "sources", "", "Lokio/Source;", "lengths", "", "(Lokhttp3/internal/cache/DiskLruCache;Ljava/lang/String;JLjava/util/List;[J)V", "close", "", "edit", "Lokhttp3/internal/cache/DiskLruCache$Editor;", "Lokhttp3/internal/cache/DiskLruCache;", "getLength", "index", "", "getSource", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    public final class Snapshot implements Closeable, AutoCloseable {
        private final String key;
        private final long[] lengths;
        private final long sequenceNumber;
        private final List sources;

        public Snapshot(String s, long v, List list0, long[] arr_v) {
            Intrinsics.checkNotNullParameter(s, "key");
            Intrinsics.checkNotNullParameter(list0, "sources");
            Intrinsics.checkNotNullParameter(arr_v, "lengths");
            DiskLruCache.this = diskLruCache0;
            super();
            this.key = s;
            this.sequenceNumber = v;
            this.sources = list0;
            this.lengths = arr_v;
        }

        @Override
        public void close() {
            for(Object object0: this.sources) {
                _UtilCommonKt.closeQuietly(((Source)object0));
            }
        }

        public final Editor edit() throws IOException {
            return DiskLruCache.this.edit(this.key, this.sequenceNumber);
        }

        public final long getLength(int v) {
            return this.lengths[v];
        }

        public final Source getSource(int v) {
            return (Source)this.sources.get(v);
        }

        public final String key() {
            return this.key;
        }
    }

    public static final long ANY_SEQUENCE_NUMBER;
    public static final String CLEAN;
    public static final Companion Companion;
    public static final String DIRTY;
    public static final String JOURNAL_FILE;
    public static final String JOURNAL_FILE_BACKUP;
    public static final String JOURNAL_FILE_TEMP;
    public static final Regex LEGAL_KEY_PATTERN;
    public static final String MAGIC;
    public static final String READ;
    public static final String REMOVE;
    public static final String VERSION_1;
    private final int appVersion;
    private boolean civilizedFileSystem;
    private final TaskQueue cleanupQueue;
    private final okhttp3.internal.cache.DiskLruCache.cleanupTask.1 cleanupTask;
    private boolean closed;
    private final Path directory;
    private final FileSystem fileSystem;
    private boolean hasJournalErrors;
    private boolean initialized;
    private final Path journalFile;
    private final Path journalFileBackup;
    private final Path journalFileTmp;
    private BufferedSink journalWriter;
    private final LinkedHashMap lruEntries;
    private long maxSize;
    private boolean mostRecentRebuildFailed;
    private boolean mostRecentTrimFailed;
    private long nextSequenceNumber;
    private int redundantOpCount;
    private long size;
    private final int valueCount;

    static {
        DiskLruCache.Companion = new Companion(null);
        DiskLruCache.JOURNAL_FILE = "journal";
        DiskLruCache.JOURNAL_FILE_TEMP = "journal.tmp";
        DiskLruCache.JOURNAL_FILE_BACKUP = "journal.bkp";
        DiskLruCache.MAGIC = "libcore.io.DiskLruCache";
        DiskLruCache.VERSION_1 = "1";
        DiskLruCache.ANY_SEQUENCE_NUMBER = -1L;
        DiskLruCache.LEGAL_KEY_PATTERN = new Regex("[a-z0-9_-]{1,120}");
        DiskLruCache.CLEAN = "CLEAN";
        DiskLruCache.DIRTY = "DIRTY";
        DiskLruCache.REMOVE = "REMOVE";
        DiskLruCache.READ = "READ";
    }

    public DiskLruCache(FileSystem fileSystem0, Path path0, int v, int v1, long v2, TaskRunner taskRunner0) {
        Intrinsics.checkNotNullParameter(fileSystem0, "fileSystem");
        Intrinsics.checkNotNullParameter(path0, "directory");
        Intrinsics.checkNotNullParameter(taskRunner0, "taskRunner");
        super();
        this.directory = path0;
        this.appVersion = v;
        this.valueCount = v1;
        this.fileSystem = new ForwardingFileSystem(/*ERROR_MISSING_ARG_0/*) {
            @Override  // okio.ForwardingFileSystem
            public Sink sink(Path path0, boolean z) {
                Intrinsics.checkNotNullParameter(path0, "file");
                Path path1 = path0.parent();
                if(path1 != null) {
                    this.createDirectories(path1);
                }
                return super.sink(path0, z);
            }
        };
        this.maxSize = v2;
        this.lruEntries = new LinkedHashMap(0, 0.75f, true);
        this.cleanupQueue = taskRunner0.newQueue();
        this.cleanupTask = new Task(_UtilJvmKt.okHttpName + " Cache") {
            {
                DiskLruCache.this = diskLruCache0;
                super(s, false, 2, null);
            }

            @Override  // okhttp3.internal.concurrent.Task
            public long runOnce() {
                DiskLruCache diskLruCache0 = DiskLruCache.this;
                synchronized(diskLruCache0) {
                    if(DiskLruCache.access$getInitialized$p(diskLruCache0) && !diskLruCache0.getClosed$okhttp()) {
                        try {
                            diskLruCache0.trimToSize();
                        }
                        catch(IOException unused_ex) {
                            DiskLruCache.access$setMostRecentTrimFailed$p(diskLruCache0, true);
                        }
                        try {
                            if(DiskLruCache.access$journalRebuildRequired(diskLruCache0)) {
                                diskLruCache0.rebuildJournal$okhttp();
                                DiskLruCache.access$setRedundantOpCount$p(diskLruCache0, 0);
                            }
                        }
                        catch(IOException unused_ex) {
                            DiskLruCache.access$setMostRecentRebuildFailed$p(diskLruCache0, true);
                            DiskLruCache.access$setJournalWriter$p(diskLruCache0, Okio.buffer(Okio.blackhole()));
                        }
                        return -1L;
                    }
                    return -1L;
                }
            }
        };
        if(v2 <= 0L) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        if(v1 <= 0) {
            throw new IllegalArgumentException("valueCount <= 0");
        }
        this.journalFile = path0.resolve("journal");
        this.journalFileTmp = path0.resolve("journal.tmp");
        this.journalFileBackup = path0.resolve("journal.bkp");
    }

    public static final boolean access$getInitialized$p(DiskLruCache diskLruCache0) {
        return diskLruCache0.initialized;
    }

    public static final boolean access$journalRebuildRequired(DiskLruCache diskLruCache0) {
        return diskLruCache0.journalRebuildRequired();
    }

    public static final void access$setJournalWriter$p(DiskLruCache diskLruCache0, BufferedSink bufferedSink0) {
        diskLruCache0.journalWriter = bufferedSink0;
    }

    public static final void access$setMostRecentRebuildFailed$p(DiskLruCache diskLruCache0, boolean z) {
        diskLruCache0.mostRecentRebuildFailed = z;
    }

    public static final void access$setMostRecentTrimFailed$p(DiskLruCache diskLruCache0, boolean z) {
        diskLruCache0.mostRecentTrimFailed = z;
    }

    public static final void access$setRedundantOpCount$p(DiskLruCache diskLruCache0, int v) {
        diskLruCache0.redundantOpCount = v;
    }

    private final void checkNotClosed() {
        synchronized(this) {
            if(!this.closed) {
                return;
            }
        }
        throw new IllegalStateException("cache is closed");
    }

    @Override
    public void close() throws IOException {
        synchronized(this) {
            if(this.initialized && !this.closed) {
                Collection collection0 = this.lruEntries.values();
                Intrinsics.checkNotNullExpressionValue(collection0, "lruEntries.values");
                Object[] arr_object = collection0.toArray(new Entry[0]);
                Intrinsics.checkNotNull(arr_object, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
                for(int v1 = 0; v1 < ((Entry[])arr_object).length; ++v1) {
                    Entry diskLruCache$Entry0 = ((Entry[])arr_object)[v1];
                    if(diskLruCache$Entry0.getCurrentEditor$okhttp() != null) {
                        Editor diskLruCache$Editor0 = diskLruCache$Entry0.getCurrentEditor$okhttp();
                        if(diskLruCache$Editor0 != null) {
                            diskLruCache$Editor0.detach$okhttp();
                        }
                    }
                }
                this.trimToSize();
                BufferedSink bufferedSink0 = this.journalWriter;
                Intrinsics.checkNotNull(bufferedSink0);
                bufferedSink0.close();
                this.journalWriter = null;
                this.closed = true;
                return;
            }
            this.closed = true;
        }
    }

    public final void completeEdit$okhttp(Editor diskLruCache$Editor0, boolean z) throws IOException {
        synchronized(this) {
            Intrinsics.checkNotNullParameter(diskLruCache$Editor0, "editor");
            Entry diskLruCache$Entry0 = diskLruCache$Editor0.getEntry$okhttp();
            if(Intrinsics.areEqual(diskLruCache$Entry0.getCurrentEditor$okhttp(), diskLruCache$Editor0)) {
                if(z && !diskLruCache$Entry0.getReadable$okhttp()) {
                    int v2 = this.valueCount;
                    int v3 = 0;
                    while(v3 < v2) {
                        boolean[] arr_z = diskLruCache$Editor0.getWritten$okhttp();
                        Intrinsics.checkNotNull(arr_z);
                        if(arr_z[v3]) {
                            Path path0 = (Path)diskLruCache$Entry0.getDirtyFiles$okhttp().get(v3);
                            if(!this.fileSystem.exists(path0)) {
                                diskLruCache$Editor0.abort();
                                return;
                            }
                            ++v3;
                            continue;
                        }
                        diskLruCache$Editor0.abort();
                        throw new IllegalStateException("Newly created entry didn\'t create value for index " + v3);
                    }
                }
                int v4 = this.valueCount;
                for(int v1 = 0; v1 < v4; ++v1) {
                    Path path1 = (Path)diskLruCache$Entry0.getDirtyFiles$okhttp().get(v1);
                    if(!z || diskLruCache$Entry0.getZombie$okhttp()) {
                        _UtilCommonKt.deleteIfExists(this.fileSystem, path1);
                    }
                    else if(this.fileSystem.exists(path1)) {
                        Path path2 = (Path)diskLruCache$Entry0.getCleanFiles$okhttp().get(v1);
                        this.fileSystem.atomicMove(path1, path2);
                        long v5 = diskLruCache$Entry0.getLengths$okhttp()[v1];
                        Long long0 = this.fileSystem.metadata(path2).getSize();
                        long v6 = long0 == null ? 0L : ((long)long0);
                        diskLruCache$Entry0.getLengths$okhttp()[v1] = v6;
                        this.size = this.size - v5 + v6;
                    }
                }
                diskLruCache$Entry0.setCurrentEditor$okhttp(null);
                if(diskLruCache$Entry0.getZombie$okhttp()) {
                    this.removeEntry$okhttp(diskLruCache$Entry0);
                    return;
                }
                ++this.redundantOpCount;
                BufferedSink bufferedSink0 = this.journalWriter;
                Intrinsics.checkNotNull(bufferedSink0);
                if(diskLruCache$Entry0.getReadable$okhttp() || z) {
                    diskLruCache$Entry0.setReadable$okhttp(true);
                    bufferedSink0.writeUtf8("CLEAN").writeByte(0x20);
                    bufferedSink0.writeUtf8(diskLruCache$Entry0.getKey$okhttp());
                    diskLruCache$Entry0.writeLengths$okhttp(bufferedSink0);
                    bufferedSink0.writeByte(10);
                    if(z) {
                        long v7 = this.nextSequenceNumber;
                        this.nextSequenceNumber = v7 + 1L;
                        diskLruCache$Entry0.setSequenceNumber$okhttp(v7);
                    }
                }
                else {
                    this.lruEntries.remove(diskLruCache$Entry0.getKey$okhttp());
                    bufferedSink0.writeUtf8("REMOVE").writeByte(0x20);
                    bufferedSink0.writeUtf8(diskLruCache$Entry0.getKey$okhttp());
                    bufferedSink0.writeByte(10);
                }
                bufferedSink0.flush();
                if(this.size > this.maxSize || this.journalRebuildRequired()) {
                    TaskQueue.schedule$default(this.cleanupQueue, this.cleanupTask, 0L, 2, null);
                }
                return;
            }
        }
        throw new IllegalStateException("Check failed.");
    }

    public final void delete() throws IOException {
        this.close();
        _UtilCommonKt.deleteContents(this.fileSystem, this.directory);
    }

    public final Editor edit(String s) throws IOException {
        Intrinsics.checkNotNullParameter(s, "key");
        return DiskLruCache.edit$default(this, s, 0L, 2, null);
    }

    public final Editor edit(String s, long v) throws IOException {
        synchronized(this) {
            Intrinsics.checkNotNullParameter(s, "key");
            this.initialize();
            this.checkNotClosed();
            this.validateKey(s);
            Entry diskLruCache$Entry0 = (Entry)this.lruEntries.get(s);
            if(v != DiskLruCache.ANY_SEQUENCE_NUMBER && (diskLruCache$Entry0 == null || diskLruCache$Entry0.getSequenceNumber$okhttp() != v)) {
                return null;
            }
            if((diskLruCache$Entry0 == null ? null : diskLruCache$Entry0.getCurrentEditor$okhttp()) != null) {
                return null;
            }
            if(diskLruCache$Entry0 != null && diskLruCache$Entry0.getLockingSourceCount$okhttp() != 0) {
                return null;
            }
            if(!this.mostRecentTrimFailed && !this.mostRecentRebuildFailed) {
                BufferedSink bufferedSink0 = this.journalWriter;
                Intrinsics.checkNotNull(bufferedSink0);
                bufferedSink0.writeUtf8("DIRTY").writeByte(0x20).writeUtf8(s).writeByte(10);
                bufferedSink0.flush();
                if(this.hasJournalErrors) {
                    return null;
                }
                if(diskLruCache$Entry0 == null) {
                    diskLruCache$Entry0 = new Entry(this, s);
                    this.lruEntries.put(s, diskLruCache$Entry0);
                }
                Editor diskLruCache$Editor0 = new Editor(this, diskLruCache$Entry0);
                diskLruCache$Entry0.setCurrentEditor$okhttp(diskLruCache$Editor0);
                return diskLruCache$Editor0;
            }
            TaskQueue.schedule$default(this.cleanupQueue, this.cleanupTask, 0L, 2, null);
            return null;
        }
    }

    public static Editor edit$default(DiskLruCache diskLruCache0, String s, long v, int v1, Object object0) throws IOException {
        if((v1 & 2) != 0) {
            v = DiskLruCache.ANY_SEQUENCE_NUMBER;
        }
        return diskLruCache0.edit(s, v);
    }

    public final void evictAll() throws IOException {
        synchronized(this) {
            this.initialize();
            Collection collection0 = this.lruEntries.values();
            Intrinsics.checkNotNullExpressionValue(collection0, "lruEntries.values");
            Object[] arr_object = collection0.toArray(new Entry[0]);
            Intrinsics.checkNotNull(arr_object, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
            for(int v1 = 0; v1 < ((Entry[])arr_object).length; ++v1) {
                Entry diskLruCache$Entry0 = ((Entry[])arr_object)[v1];
                Intrinsics.checkNotNullExpressionValue(diskLruCache$Entry0, "entry");
                this.removeEntry$okhttp(diskLruCache$Entry0);
            }
            this.mostRecentTrimFailed = false;
        }
    }

    @Override
    public void flush() throws IOException {
        synchronized(this) {
            if(!this.initialized) {
                return;
            }
            this.checkNotClosed();
            this.trimToSize();
            BufferedSink bufferedSink0 = this.journalWriter;
            Intrinsics.checkNotNull(bufferedSink0);
            bufferedSink0.flush();
        }
    }

    public final Snapshot get(String s) throws IOException {
        synchronized(this) {
            Intrinsics.checkNotNullParameter(s, "key");
            this.initialize();
            this.checkNotClosed();
            this.validateKey(s);
            Entry diskLruCache$Entry0 = (Entry)this.lruEntries.get(s);
            if(diskLruCache$Entry0 == null) {
                return null;
            }
            Snapshot diskLruCache$Snapshot0 = diskLruCache$Entry0.snapshot$okhttp();
            if(diskLruCache$Snapshot0 == null) {
                return null;
            }
            ++this.redundantOpCount;
            BufferedSink bufferedSink0 = this.journalWriter;
            Intrinsics.checkNotNull(bufferedSink0);
            bufferedSink0.writeUtf8("READ").writeByte(0x20).writeUtf8(s).writeByte(10);
            if(this.journalRebuildRequired()) {
                TaskQueue.schedule$default(this.cleanupQueue, this.cleanupTask, 0L, 2, null);
            }
            return diskLruCache$Snapshot0;
        }
    }

    public final boolean getClosed$okhttp() {
        return this.closed;
    }

    public final Path getDirectory() {
        return this.directory;
    }

    public final FileSystem getFileSystem$okhttp() {
        return this.fileSystem;
    }

    public final LinkedHashMap getLruEntries$okhttp() {
        return this.lruEntries;
    }

    public final long getMaxSize() {
        synchronized(this) {
        }
        return this.maxSize;
    }

    public final int getValueCount$okhttp() {
        return this.valueCount;
    }

    public final void initialize() throws IOException {
        synchronized(this) {
            if(_UtilJvmKt.assertionsEnabled && !Thread.holdsLock(this)) {
                throw new AssertionError("Thread jeb-dexdec-sb-st-13485 MUST hold lock on " + this);
            }
            if(this.initialized) {
                return;
            }
            if(this.fileSystem.exists(this.journalFileBackup)) {
                if(this.fileSystem.exists(this.journalFile)) {
                    this.fileSystem.delete(this.journalFileBackup);
                }
                else {
                    this.fileSystem.atomicMove(this.journalFileBackup, this.journalFile);
                }
            }
            this.civilizedFileSystem = _UtilCommonKt.isCivilized(this.fileSystem, this.journalFileBackup);
            if(this.fileSystem.exists(this.journalFile)) {
                try {
                    this.readJournal();
                    this.processJournal();
                    this.initialized = true;
                    return;
                }
                catch(IOException iOException0) {
                    Platform.Companion.get().log("DiskLruCache " + this.directory + " is corrupt: " + iOException0.getMessage() + ", removing", 5, iOException0);
                    try {
                        this.delete();
                    }
                    finally {
                        this.closed = false;
                    }
                }
            }
            this.rebuildJournal$okhttp();
            this.initialized = true;
        }
    }

    public final boolean isClosed() {
        synchronized(this) {
        }
        return this.closed;
    }

    private final boolean journalRebuildRequired() {
        return this.redundantOpCount >= 2000 && this.redundantOpCount >= this.lruEntries.size();
    }

    private final BufferedSink newJournalWriter() throws FileNotFoundException {
        return Okio.buffer(new FaultHidingSink(this.fileSystem.appendingSink(this.journalFile), new Function1() {
            {
                DiskLruCache.this = diskLruCache0;
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                this.invoke(((IOException)object0));
                return Unit.INSTANCE;
            }

            public final void invoke(IOException iOException0) {
                Intrinsics.checkNotNullParameter(iOException0, "it");
                DiskLruCache diskLruCache0 = DiskLruCache.this;
                if(_UtilJvmKt.assertionsEnabled && !Thread.holdsLock(diskLruCache0)) {
                    throw new AssertionError("Thread jeb-dexdec-sb-st-13503 MUST hold lock on " + diskLruCache0);
                }
                DiskLruCache.this.hasJournalErrors = true;
            }
        }));
    }

    private final void processJournal() throws IOException {
        _UtilCommonKt.deleteIfExists(this.fileSystem, this.journalFileTmp);
        Iterator iterator0 = this.lruEntries.values().iterator();
        while(iterator0.hasNext()) {
            Object object0 = iterator0.next();
            Intrinsics.checkNotNullExpressionValue(object0, "i.next()");
            Entry diskLruCache$Entry0 = (Entry)object0;
            int v = 0;
            if(diskLruCache$Entry0.getCurrentEditor$okhttp() == null) {
                int v1 = this.valueCount;
                while(v < v1) {
                    this.size += diskLruCache$Entry0.getLengths$okhttp()[v];
                    ++v;
                }
            }
            else {
                diskLruCache$Entry0.setCurrentEditor$okhttp(null);
                int v2 = this.valueCount;
                while(v < v2) {
                    Path path0 = (Path)diskLruCache$Entry0.getCleanFiles$okhttp().get(v);
                    _UtilCommonKt.deleteIfExists(this.fileSystem, path0);
                    Path path1 = (Path)diskLruCache$Entry0.getDirtyFiles$okhttp().get(v);
                    _UtilCommonKt.deleteIfExists(this.fileSystem, path1);
                    ++v;
                }
                iterator0.remove();
            }
        }
    }

    private final void readJournal() throws IOException {
        Unit unit0;
        Closeable closeable0 = Okio.buffer(this.fileSystem.source(this.journalFile));
        Throwable throwable0 = null;
        try {
            BufferedSource bufferedSource0 = (BufferedSource)closeable0;
            String s = bufferedSource0.readUtf8LineStrict();
            String s1 = bufferedSource0.readUtf8LineStrict();
            String s2 = bufferedSource0.readUtf8LineStrict();
            String s3 = bufferedSource0.readUtf8LineStrict();
            String s4 = bufferedSource0.readUtf8LineStrict();
            if(!Intrinsics.areEqual("libcore.io.DiskLruCache", s) || !Intrinsics.areEqual("1", s1) || !Intrinsics.areEqual(String.valueOf(this.appVersion), s2) || !Intrinsics.areEqual(String.valueOf(this.valueCount), s3) || s4.length() > 0) {
                throw new IOException("unexpected journal header: [" + s + ", " + s1 + ", " + s3 + ", " + s4 + ']');
            }
            int v = 0;
            try {
                while(true) {
                    this.readJournalLine(bufferedSource0.readUtf8LineStrict());
                    ++v;
                }
            }
            catch(EOFException unused_ex) {
                this.redundantOpCount = v - this.lruEntries.size();
                if(bufferedSource0.exhausted()) {
                    this.journalWriter = this.newJournalWriter();
                }
                else {
                    this.rebuildJournal$okhttp();
                }
                unit0 = Unit.INSTANCE;
            }
        }
        catch(Throwable throwable1) {
            throwable0 = throwable1;
            unit0 = null;
        }
        if(closeable0 != null) {
            try {
                closeable0.close();
            }
            catch(Throwable throwable2) {
                if(throwable0 == null) {
                    throwable0 = throwable2;
                }
                else {
                    ExceptionsKt.addSuppressed(throwable0, throwable2);
                }
            }
        }
        if(throwable0 != null) {
            throw throwable0;
        }
        Intrinsics.checkNotNull(unit0);
    }

    private final void readJournalLine(String s) throws IOException {
        String s1;
        int v = StringsKt.indexOf$default(s, ' ', 0, false, 6, null);
        if(v == -1) {
            throw new IOException("unexpected journal line: " + s);
        }
        int v1 = StringsKt.indexOf$default(s, ' ', v + 1, false, 4, null);
        if(v1 == -1) {
            s1 = s.substring(v + 1);
            Intrinsics.checkNotNullExpressionValue(s1, "this as java.lang.String).substring(startIndex)");
            if(v == 6 && StringsKt.startsWith$default(s, "REMOVE", false, 2, null)) {
                this.lruEntries.remove(s1);
                return;
            }
        }
        else {
            s1 = s.substring(v + 1, v1);
            Intrinsics.checkNotNullExpressionValue(s1, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        Entry diskLruCache$Entry0 = (Entry)this.lruEntries.get(s1);
        if(diskLruCache$Entry0 == null) {
            diskLruCache$Entry0 = new Entry(this, s1);
            this.lruEntries.put(s1, diskLruCache$Entry0);
        }
        if(v1 != -1 && (v == 5 && StringsKt.startsWith$default(s, "CLEAN", false, 2, null))) {
            String s2 = s.substring(v1 + 1);
            Intrinsics.checkNotNullExpressionValue(s2, "this as java.lang.String).substring(startIndex)");
            List list0 = StringsKt.split$default(s2, new char[]{' '}, false, 0, 6, null);
            diskLruCache$Entry0.setReadable$okhttp(true);
            diskLruCache$Entry0.setCurrentEditor$okhttp(null);
            diskLruCache$Entry0.setLengths$okhttp(list0);
            return;
        }
        if(v1 == -1 && (v == 5 && StringsKt.startsWith$default(s, "DIRTY", false, 2, null))) {
            diskLruCache$Entry0.setCurrentEditor$okhttp(new Editor(this, diskLruCache$Entry0));
            return;
        }
        if(v1 != -1 || (v != 4 || !StringsKt.startsWith$default(s, "READ", false, 2, null))) {
            throw new IOException("unexpected journal line: " + s);
        }
    }

    public final void rebuildJournal$okhttp() throws IOException {
        Throwable throwable0;
        Unit unit0;
        synchronized(this) {
            BufferedSink bufferedSink0 = this.journalWriter;
            if(bufferedSink0 != null) {
                bufferedSink0.close();
            }
            Closeable closeable0 = Okio.buffer(this.fileSystem.sink(this.journalFileTmp, false));
            throwable0 = null;
            try {
                ((BufferedSink)closeable0).writeUtf8("libcore.io.DiskLruCache").writeByte(10);
                ((BufferedSink)closeable0).writeUtf8("1").writeByte(10);
                ((BufferedSink)closeable0).writeDecimalLong(((long)this.appVersion)).writeByte(10);
                ((BufferedSink)closeable0).writeDecimalLong(((long)this.valueCount)).writeByte(10);
                ((BufferedSink)closeable0).writeByte(10);
                for(Object object0: this.lruEntries.values()) {
                    Entry diskLruCache$Entry0 = (Entry)object0;
                    if(diskLruCache$Entry0.getCurrentEditor$okhttp() == null) {
                        ((BufferedSink)closeable0).writeUtf8("CLEAN").writeByte(0x20);
                        ((BufferedSink)closeable0).writeUtf8(diskLruCache$Entry0.getKey$okhttp());
                        diskLruCache$Entry0.writeLengths$okhttp(((BufferedSink)closeable0));
                    }
                    else {
                        ((BufferedSink)closeable0).writeUtf8("DIRTY").writeByte(0x20);
                        ((BufferedSink)closeable0).writeUtf8(diskLruCache$Entry0.getKey$okhttp());
                    }
                    ((BufferedSink)closeable0).writeByte(10);
                }
                unit0 = Unit.INSTANCE;
            }
            catch(Throwable throwable1) {
                unit0 = null;
                throwable0 = throwable1;
            }
            if(closeable0 != null) {
                try {
                    closeable0.close();
                }
                catch(Throwable throwable2) {
                    if(throwable0 == null) {
                        throwable0 = throwable2;
                    }
                    else {
                        ExceptionsKt.addSuppressed(throwable0, throwable2);
                    }
                }
            }
            if(throwable0 == null) {
                Intrinsics.checkNotNull(unit0);
                if(this.fileSystem.exists(this.journalFile)) {
                    this.fileSystem.atomicMove(this.journalFile, this.journalFileBackup);
                    this.fileSystem.atomicMove(this.journalFileTmp, this.journalFile);
                    _UtilCommonKt.deleteIfExists(this.fileSystem, this.journalFileBackup);
                }
                else {
                    this.fileSystem.atomicMove(this.journalFileTmp, this.journalFile);
                }
                this.journalWriter = this.newJournalWriter();
                this.hasJournalErrors = false;
                this.mostRecentRebuildFailed = false;
                return;
            }
        }
        throw throwable0;
    }

    public final boolean remove(String s) throws IOException {
        synchronized(this) {
            Intrinsics.checkNotNullParameter(s, "key");
            this.initialize();
            this.checkNotClosed();
            this.validateKey(s);
            Entry diskLruCache$Entry0 = (Entry)this.lruEntries.get(s);
            if(diskLruCache$Entry0 == null) {
                return false;
            }
            boolean z = this.removeEntry$okhttp(diskLruCache$Entry0);
            if(z && this.size <= this.maxSize) {
                this.mostRecentTrimFailed = false;
            }
            return z;
        }
    }

    public final boolean removeEntry$okhttp(Entry diskLruCache$Entry0) throws IOException {
        Intrinsics.checkNotNullParameter(diskLruCache$Entry0, "entry");
        if(!this.civilizedFileSystem) {
            if(diskLruCache$Entry0.getLockingSourceCount$okhttp() > 0) {
                BufferedSink bufferedSink0 = this.journalWriter;
                if(bufferedSink0 != null) {
                    bufferedSink0.writeUtf8("DIRTY");
                    bufferedSink0.writeByte(0x20);
                    bufferedSink0.writeUtf8(diskLruCache$Entry0.getKey$okhttp());
                    bufferedSink0.writeByte(10);
                    bufferedSink0.flush();
                }
            }
            if(diskLruCache$Entry0.getLockingSourceCount$okhttp() > 0 || diskLruCache$Entry0.getCurrentEditor$okhttp() != null) {
                diskLruCache$Entry0.setZombie$okhttp(true);
                return true;
            }
        }
        Editor diskLruCache$Editor0 = diskLruCache$Entry0.getCurrentEditor$okhttp();
        if(diskLruCache$Editor0 != null) {
            diskLruCache$Editor0.detach$okhttp();
        }
        int v = this.valueCount;
        for(int v1 = 0; v1 < v; ++v1) {
            Path path0 = (Path)diskLruCache$Entry0.getCleanFiles$okhttp().get(v1);
            _UtilCommonKt.deleteIfExists(this.fileSystem, path0);
            this.size -= diskLruCache$Entry0.getLengths$okhttp()[v1];
            diskLruCache$Entry0.getLengths$okhttp()[v1] = 0L;
        }
        ++this.redundantOpCount;
        BufferedSink bufferedSink1 = this.journalWriter;
        if(bufferedSink1 != null) {
            bufferedSink1.writeUtf8("REMOVE");
            bufferedSink1.writeByte(0x20);
            bufferedSink1.writeUtf8(diskLruCache$Entry0.getKey$okhttp());
            bufferedSink1.writeByte(10);
        }
        this.lruEntries.remove(diskLruCache$Entry0.getKey$okhttp());
        if(this.journalRebuildRequired()) {
            TaskQueue.schedule$default(this.cleanupQueue, this.cleanupTask, 0L, 2, null);
        }
        return true;
    }

    private final boolean removeOldestEntry() {
        for(Object object0: this.lruEntries.values()) {
            Entry diskLruCache$Entry0 = (Entry)object0;
            if(!diskLruCache$Entry0.getZombie$okhttp()) {
                Intrinsics.checkNotNullExpressionValue(diskLruCache$Entry0, "toEvict");
                this.removeEntry$okhttp(diskLruCache$Entry0);
                return true;
            }
            if(false) {
                break;
            }
        }
        return false;
    }

    public final void setClosed$okhttp(boolean z) {
        this.closed = z;
    }

    public final void setMaxSize(long v) {
        synchronized(this) {
            this.maxSize = v;
            if(this.initialized) {
                TaskQueue.schedule$default(this.cleanupQueue, this.cleanupTask, 0L, 2, null);
            }
        }
    }

    public final long size() throws IOException {
        synchronized(this) {
            this.initialize();
            return this.size;
        }
    }

    public final Iterator snapshots() throws IOException {
        synchronized(this) {
            this.initialize();
            return new Object() {
                private final Iterator delegate;
                private Snapshot nextSnapshot;
                private Snapshot removeSnapshot;

                {
                    Iterator iterator0 = new ArrayList(diskLruCache0.getLruEntries$okhttp().values()).iterator();
                    Intrinsics.checkNotNullExpressionValue(iterator0, "ArrayList(lruEntries.values).iterator()");
                    this.delegate = iterator0;
                }

                @Override
                public boolean hasNext() {
                    if(this.nextSnapshot != null) {
                        return true;
                    }
                    DiskLruCache diskLruCache0 = DiskLruCache.this;
                    synchronized(diskLruCache0) {
                        if(diskLruCache0.getClosed$okhttp()) {
                            return false;
                        }
                        while(this.delegate.hasNext()) {
                            Object object0 = this.delegate.next();
                            Entry diskLruCache$Entry0 = (Entry)object0;
                            if(diskLruCache$Entry0 != null) {
                                Snapshot diskLruCache$Snapshot0 = diskLruCache$Entry0.snapshot$okhttp();
                                if(diskLruCache$Snapshot0 != null) {
                                    this.nextSnapshot = diskLruCache$Snapshot0;
                                    return true;
                                }
                                if(false) {
                                    break;
                                }
                            }
                        }
                        return false;
                    }
                }

                @Override
                public Object next() {
                    return this.next();
                }

                public Snapshot next() {
                    if(!this.hasNext()) {
                        throw new NoSuchElementException();
                    }
                    Snapshot diskLruCache$Snapshot0 = this.nextSnapshot;
                    this.removeSnapshot = diskLruCache$Snapshot0;
                    this.nextSnapshot = null;
                    Intrinsics.checkNotNull(diskLruCache$Snapshot0);
                    return diskLruCache$Snapshot0;
                }

                @Override
                public void remove() {
                    Snapshot diskLruCache$Snapshot0 = this.removeSnapshot;
                    if(diskLruCache$Snapshot0 != null) {
                        try {
                            DiskLruCache.this.remove(diskLruCache$Snapshot0.key());
                            this.removeSnapshot = null;
                            return;
                        }
                        catch(IOException unused_ex) {
                            this.removeSnapshot = null;
                            return;
                        }
                        catch(Throwable throwable0) {
                            this.removeSnapshot = null;
                            throw throwable0;
                        }
                    }
                    throw new IllegalStateException("remove() before next()");
                }
            };
        }
    }

    public final void trimToSize() throws IOException {
        while(this.size > this.maxSize) {
            if(!this.removeOldestEntry()) {
                return;
            }
            if(false) {
                break;
            }
        }
        this.mostRecentTrimFailed = false;
    }

    private final void validateKey(String s) {
        if(!DiskLruCache.LEGAL_KEY_PATTERN.matches(s)) {
            throw new IllegalArgumentException(("keys must match regex [a-z0-9_-]{1,120}: \"" + s + '\"').toString());
        }
    }
}

