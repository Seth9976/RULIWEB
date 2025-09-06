package com.airbnb.lottie.parser;

import android.graphics.Color;
import com.airbnb.lottie.model.content.GradientColor;
import com.airbnb.lottie.parser.moshi.JsonReader.Token;
import com.airbnb.lottie.parser.moshi.JsonReader;
import com.airbnb.lottie.utils.MiscUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GradientColorParser implements ValueParser {
    private int colorPoints;

    public GradientColorParser(int v) {
        this.colorPoints = v;
    }

    private void addOpacityStopsToGradientIfNeeded(GradientColor gradientColor0, List list0) {
        int v = this.colorPoints * 4;
        if(list0.size() > v) {
            int v1 = (list0.size() - v) / 2;
            double[] arr_f = new double[v1];
            double[] arr_f1 = new double[v1];
            int v3 = 0;
            while(v < list0.size()) {
                if(v % 2 == 0) {
                    arr_f[v3] = (double)(((float)(((Float)list0.get(v)))));
                }
                else {
                    arr_f1[v3] = (double)(((float)(((Float)list0.get(v)))));
                    ++v3;
                }
                ++v;
            }
            for(int v2 = 0; v2 < gradientColor0.getSize(); ++v2) {
                int v4 = gradientColor0.getColors()[v2];
                gradientColor0.getColors()[v2] = Color.argb(this.getOpacityAtPosition(((double)gradientColor0.getPositions()[v2]), arr_f, arr_f1), Color.red(v4), Color.green(v4), Color.blue(v4));
            }
        }
    }

    private int getOpacityAtPosition(double f, double[] arr_f, double[] arr_f1) {
        for(int v = 1; v < arr_f.length; ++v) {
            double f1 = arr_f[v - 1];
            double f2 = arr_f[v];
            if(f2 >= f) {
                return (int)(MiscUtils.lerp(arr_f1[v - 1], arr_f1[v], (f - f1) / (f2 - f1)) * 255.0);
            }
        }
        return (int)(arr_f1[arr_f1.length - 1] * 255.0);
    }

    public GradientColor parse(JsonReader jsonReader0, float f) throws IOException {
        ArrayList arrayList0 = new ArrayList();
        boolean z = jsonReader0.peek() == Token.BEGIN_ARRAY;
        if(z) {
            jsonReader0.beginArray();
        }
        while(jsonReader0.hasNext()) {
            arrayList0.add(((float)jsonReader0.nextDouble()));
        }
        if(z) {
            jsonReader0.endArray();
        }
        if(this.colorPoints == -1) {
            this.colorPoints = arrayList0.size() / 4;
        }
        int v1 = this.colorPoints;
        float[] arr_f = new float[v1];
        int[] arr_v = new int[v1];
        int v2 = 0;
        int v3 = 0;
        for(int v = 0; v < this.colorPoints * 4; ++v) {
            double f1 = (double)(((float)(((Float)arrayList0.get(v)))));
            switch(v % 4) {
                case 0: {
                    arr_f[v / 4] = (float)f1;
                    break;
                }
                case 1: {
                    v2 = (int)(f1 * 255.0);
                    break;
                }
                case 2: {
                    v3 = (int)(f1 * 255.0);
                    break;
                }
                case 3: {
                    arr_v[v / 4] = Color.argb(0xFF, v2, v3, ((int)(f1 * 255.0)));
                }
            }
        }
        GradientColor gradientColor0 = new GradientColor(arr_f, arr_v);
        this.addOpacityStopsToGradientIfNeeded(gradientColor0, arrayList0);
        return gradientColor0;
    }

    @Override  // com.airbnb.lottie.parser.ValueParser
    public Object parse(JsonReader jsonReader0, float f) throws IOException {
        return this.parse(jsonReader0, f);
    }
}

