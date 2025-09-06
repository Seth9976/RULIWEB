package retrofit2.converter.gson;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import java.io.IOException;
import java.io.Reader;
import jeb.synthetic.TWR;
import okhttp3.ResponseBody;
import retrofit2.Converter;

final class GsonResponseBodyConverter implements Converter {
    private final TypeAdapter adapter;
    private final Gson gson;

    GsonResponseBodyConverter(Gson gson0, TypeAdapter typeAdapter0) {
        this.gson = gson0;
        this.adapter = typeAdapter0;
    }

    @Override  // retrofit2.Converter
    public Object convert(Object object0) throws IOException {
        return this.convert(((ResponseBody)object0));
    }

    public Object convert(ResponseBody responseBody0) throws IOException {
        Object object0;
        Reader reader0 = responseBody0.charStream();
        JsonReader jsonReader0 = this.gson.newJsonReader(reader0);
        try {
            object0 = this.adapter.read(jsonReader0);
            if(jsonReader0.peek() != JsonToken.END_DOCUMENT) {
                throw new JsonIOException("JSON document was not fully consumed.");
            }
        }
        catch(Throwable throwable0) {
            TWR.safeClose$NT(responseBody0, throwable0);
            throw throwable0;
        }
        responseBody0.close();
        return object0;
    }
}

