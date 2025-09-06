package okhttp3;

import java.io.IOException;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u001C\u0010\u0002\u001A\u00020\u00032\u0006\u0010\u0004\u001A\u00020\u00052\n\u0010\u0006\u001A\u00060\u0007j\u0002`\bH&J\u0018\u0010\t\u001A\u00020\u00032\u0006\u0010\u0004\u001A\u00020\u00052\u0006\u0010\n\u001A\u00020\u000BH&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\fÀ\u0006\u0001"}, d2 = {"Lokhttp3/Callback;", "", "onFailure", "", "call", "Lokhttp3/Call;", "e", "Ljava/io/IOException;", "Lokio/IOException;", "onResponse", "response", "Lokhttp3/Response;", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
public interface Callback {
    void onFailure(Call arg1, IOException arg2);

    void onResponse(Call arg1, Response arg2) throws IOException;
}

