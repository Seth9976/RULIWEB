package com.airbnb.lottie.parser;

import com.airbnb.lottie.model.DocumentData.Justification;
import com.airbnb.lottie.model.DocumentData;
import com.airbnb.lottie.parser.moshi.JsonReader.Options;
import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;

public class DocumentDataParser implements ValueParser {
    public static final DocumentDataParser INSTANCE;
    private static final Options NAMES;

    static {
        DocumentDataParser.INSTANCE = new DocumentDataParser();
        DocumentDataParser.NAMES = Options.of(new String[]{"t", "f", "s", "j", "tr", "lh", "ls", "fc", "sc", "sw", "of"});
    }

    public DocumentData parse(JsonReader jsonReader0, float f) throws IOException {
        jsonReader0.beginObject();
        Justification documentData$Justification0 = Justification.CENTER;
        String s = null;
        String s1 = null;
        double f1 = 0.0;
        double f2 = 0.0;
        double f3 = 0.0;
        double f4 = 0.0;
        int v = 0;
        int v1 = 0;
        int v2 = 0;
        boolean z = true;
        while(jsonReader0.hasNext()) {
            switch(jsonReader0.selectName(DocumentDataParser.NAMES)) {
                case 0: {
                    s = jsonReader0.nextString();
                    break;
                }
                case 1: {
                    s1 = jsonReader0.nextString();
                    break;
                }
                case 2: {
                    f1 = jsonReader0.nextDouble();
                    break;
                }
                case 3: {
                    int v3 = jsonReader0.nextInt();
                    if(v3 > Justification.CENTER.ordinal() || v3 < 0) {
                        documentData$Justification0 = Justification.CENTER;
                        break;
                    }
                    else {
                        documentData$Justification0 = Justification.values()[v3];
                        continue;
                    }
                    v = jsonReader0.nextInt();
                    break;
                }
                case 4: {
                    v = jsonReader0.nextInt();
                    break;
                }
                case 5: {
                    f2 = jsonReader0.nextDouble();
                    break;
                }
                case 6: {
                    f3 = jsonReader0.nextDouble();
                    break;
                }
                case 7: {
                    v1 = JsonUtils.jsonToColor(jsonReader0);
                    break;
                }
                case 8: {
                    v2 = JsonUtils.jsonToColor(jsonReader0);
                    break;
                }
                case 9: {
                    f4 = jsonReader0.nextDouble();
                    break;
                }
                case 10: {
                    z = jsonReader0.nextBoolean();
                    break;
                }
                default: {
                    jsonReader0.skipName();
                    jsonReader0.skipValue();
                }
            }
        }
        jsonReader0.endObject();
        return new DocumentData(s, s1, f1, documentData$Justification0, v, f2, f3, v1, v2, f4, z);
    }

    @Override  // com.airbnb.lottie.parser.ValueParser
    public Object parse(JsonReader jsonReader0, float f) throws IOException {
        return this.parse(jsonReader0, f);
    }
}

