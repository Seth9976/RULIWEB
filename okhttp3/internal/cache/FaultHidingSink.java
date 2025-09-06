package okhttp3.internal.cache;

import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import okio.Buffer;
import okio.ForwardingSink;
import okio.Sink;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\b\u0010\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0012\u0010\u0004\u001A\u000E\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\u0002\u0010\bJ\b\u0010\r\u001A\u00020\u0007H\u0016J\b\u0010\u000E\u001A\u00020\u0007H\u0016J\u0018\u0010\u000F\u001A\u00020\u00072\u0006\u0010\u0010\u001A\u00020\u00112\u0006\u0010\u0012\u001A\u00020\u0013H\u0016R\u000E\u0010\t\u001A\u00020\nX\u0082\u000E¢\u0006\u0002\n\u0000R\u001D\u0010\u0004\u001A\u000E\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\b\n\u0000\u001A\u0004\b\u000B\u0010\f¨\u0006\u0014"}, d2 = {"Lokhttp3/internal/cache/FaultHidingSink;", "Lokio/ForwardingSink;", "delegate", "Lokio/Sink;", "onException", "Lkotlin/Function1;", "Ljava/io/IOException;", "", "(Lokio/Sink;Lkotlin/jvm/functions/Function1;)V", "hasErrors", "", "getOnException", "()Lkotlin/jvm/functions/Function1;", "close", "flush", "write", "source", "Lokio/Buffer;", "byteCount", "", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
public class FaultHidingSink extends ForwardingSink {
    private boolean hasErrors;
    private final Function1 onException;

    public FaultHidingSink(Sink sink0, Function1 function10) {
        Intrinsics.checkNotNullParameter(sink0, "delegate");
        Intrinsics.checkNotNullParameter(function10, "onException");
        super(sink0);
        this.onException = function10;
    }

    @Override  // okio.ForwardingSink
    public void close() {
        if(!this.hasErrors) {
            try {
                super.close();
            }
            catch(IOException iOException0) {
                this.hasErrors = true;
                this.onException.invoke(iOException0);
            }
        }
    }

    @Override  // okio.ForwardingSink
    public void flush() {
        if(!this.hasErrors) {
            try {
                super.flush();
            }
            catch(IOException iOException0) {
                this.hasErrors = true;
                this.onException.invoke(iOException0);
            }
        }
    }

    public final Function1 getOnException() {
        return this.onException;
    }

    @Override  // okio.ForwardingSink
    public void write(Buffer buffer0, long v) {
        Intrinsics.checkNotNullParameter(buffer0, "source");
        if(this.hasErrors) {
            buffer0.skip(v);
            return;
        }
        try {
            super.write(buffer0, v);
        }
        catch(IOException iOException0) {
            this.hasErrors = true;
            this.onException.invoke(iOException0);
        }
    }
}

