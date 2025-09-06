package retrofit2.converter.gson;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.ByteString;
import retrofit2.Converter;

final class GsonRequestBodyConverter implements Converter {
    private static final MediaType MEDIA_TYPE;
    private static final Charset UTF_8;
    private final TypeAdapter adapter;
    private final Gson gson;

    static {
        GsonRequestBodyConverter.MEDIA_TYPE = MediaType.get("application/json; charset=UTF-8");
        GsonRequestBodyConverter.UTF_8 = Charset.forName("UTF-8");
    }

    GsonRequestBodyConverter(Gson gson0, TypeAdapter typeAdapter0) {
        this.gson = gson0;
        this.adapter = typeAdapter0;
    }

    @Override  // retrofit2.Converter
    public Object convert(Object object0) throws IOException {
        return this.convert(object0);
    }

    public RequestBody convert(Object object0) throws IOException {
        Buffer buffer0 = new Buffer();
        OutputStreamWriter outputStreamWriter0 = new OutputStreamWriter(buffer0.outputStream(), GsonRequestBodyConverter.UTF_8);
        JsonWriter jsonWriter0 = this.gson.newJsonWriter(outputStreamWriter0);
        this.adapter.write(jsonWriter0, object0);
        jsonWriter0.close();
        ByteString byteString0 = buffer0.readByteString();
        return RequestBody.create(GsonRequestBodyConverter.MEDIA_TYPE, byteString0);
    }
}

