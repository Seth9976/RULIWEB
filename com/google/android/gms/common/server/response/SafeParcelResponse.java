package com.google.android.gms.common.server.response;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.util.SparseArray;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.ArrayUtils;
import com.google.android.gms.common.util.Base64Utils;
import com.google.android.gms.common.util.JsonUtils;
import com.google.android.gms.common.util.MapUtils;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Map;
import java.util.Set;

public class SafeParcelResponse extends FastSafeParcelableJsonResponse {
    public static final Parcelable.Creator CREATOR;
    private final int zaa;
    private final Parcel zab;
    private final int zac;
    private final zan zad;
    private final String zae;
    private int zaf;
    private int zag;

    static {
        SafeParcelResponse.CREATOR = new zaq();
    }

    SafeParcelResponse(int v, Parcel parcel0, zan zan0) {
        this.zaa = v;
        this.zab = (Parcel)Preconditions.checkNotNull(parcel0);
        this.zac = 2;
        this.zad = zan0;
        this.zae = zan0 == null ? null : zan0.zaa();
        this.zaf = 2;
    }

    private SafeParcelResponse(SafeParcelable safeParcelable0, zan zan0, String s) {
        this.zaa = 1;
        Parcel parcel0 = Parcel.obtain();
        this.zab = parcel0;
        safeParcelable0.writeToParcel(parcel0, 0);
        this.zac = 1;
        this.zad = (zan)Preconditions.checkNotNull(zan0);
        this.zae = (String)Preconditions.checkNotNull(s);
        this.zaf = 2;
    }

    public SafeParcelResponse(zan zan0, String s) {
        this.zaa = 1;
        this.zab = Parcel.obtain();
        this.zac = 0;
        this.zad = (zan)Preconditions.checkNotNull(zan0);
        this.zae = (String)Preconditions.checkNotNull(s);
        this.zaf = 0;
    }

    @Override  // com.google.android.gms.common.server.response.FastJsonResponse
    public final void addConcreteTypeArrayInternal(Field fastJsonResponse$Field0, String s, ArrayList arrayList0) {
        this.zaG(fastJsonResponse$Field0);
        ArrayList arrayList1 = new ArrayList();
        ((ArrayList)Preconditions.checkNotNull(arrayList0)).size();
        int v = arrayList0.size();
        for(int v1 = 0; v1 < v; ++v1) {
            arrayList1.add(((SafeParcelResponse)(((FastJsonResponse)arrayList0.get(v1)))).zaE());
        }
        SafeParcelWriter.writeParcelList(this.zab, fastJsonResponse$Field0.getSafeParcelableFieldId(), arrayList1, true);
    }

    @Override  // com.google.android.gms.common.server.response.FastJsonResponse
    public final void addConcreteTypeInternal(Field fastJsonResponse$Field0, String s, FastJsonResponse fastJsonResponse0) {
        this.zaG(fastJsonResponse$Field0);
        Parcel parcel0 = ((SafeParcelResponse)fastJsonResponse0).zaE();
        SafeParcelWriter.writeParcel(this.zab, fastJsonResponse$Field0.getSafeParcelableFieldId(), parcel0, true);
    }

    public static SafeParcelResponse from(FastJsonResponse fastJsonResponse0) {
        String s = (String)Preconditions.checkNotNull(fastJsonResponse0.getClass().getCanonicalName());
        zan zan0 = new zan(fastJsonResponse0.getClass());
        SafeParcelResponse.zaF(zan0, fastJsonResponse0);
        zan0.zac();
        zan0.zad();
        return new SafeParcelResponse(((SafeParcelable)fastJsonResponse0), zan0, s);
    }

    @Override  // com.google.android.gms.common.server.response.FastJsonResponse
    public final Map getFieldMappings() {
        return this.zad == null ? null : this.zad.zab(((String)Preconditions.checkNotNull(this.zae)));
    }

    @Override  // com.google.android.gms.common.server.response.FastSafeParcelableJsonResponse
    public final Object getValueObject(String s) {
        throw new UnsupportedOperationException("Converting to JSON does not require this method.");
    }

    @Override  // com.google.android.gms.common.server.response.FastSafeParcelableJsonResponse
    public final boolean isPrimitiveFieldSet(String s) {
        throw new UnsupportedOperationException("Converting to JSON does not require this method.");
    }

    @Override  // com.google.android.gms.common.server.response.FastJsonResponse
    protected final void setBooleanInternal(Field fastJsonResponse$Field0, String s, boolean z) {
        this.zaG(fastJsonResponse$Field0);
        SafeParcelWriter.writeBoolean(this.zab, fastJsonResponse$Field0.getSafeParcelableFieldId(), z);
    }

    @Override  // com.google.android.gms.common.server.response.FastJsonResponse
    protected final void setDecodedBytesInternal(Field fastJsonResponse$Field0, String s, byte[] arr_b) {
        this.zaG(fastJsonResponse$Field0);
        SafeParcelWriter.writeByteArray(this.zab, fastJsonResponse$Field0.getSafeParcelableFieldId(), arr_b, true);
    }

    @Override  // com.google.android.gms.common.server.response.FastJsonResponse
    protected final void setIntegerInternal(Field fastJsonResponse$Field0, String s, int v) {
        this.zaG(fastJsonResponse$Field0);
        SafeParcelWriter.writeInt(this.zab, fastJsonResponse$Field0.getSafeParcelableFieldId(), v);
    }

    @Override  // com.google.android.gms.common.server.response.FastJsonResponse
    protected final void setLongInternal(Field fastJsonResponse$Field0, String s, long v) {
        this.zaG(fastJsonResponse$Field0);
        SafeParcelWriter.writeLong(this.zab, fastJsonResponse$Field0.getSafeParcelableFieldId(), v);
    }

    @Override  // com.google.android.gms.common.server.response.FastJsonResponse
    protected final void setStringInternal(Field fastJsonResponse$Field0, String s, String s1) {
        this.zaG(fastJsonResponse$Field0);
        SafeParcelWriter.writeString(this.zab, fastJsonResponse$Field0.getSafeParcelableFieldId(), s1, true);
    }

    @Override  // com.google.android.gms.common.server.response.FastJsonResponse
    protected final void setStringMapInternal(Field fastJsonResponse$Field0, String s, Map map0) {
        this.zaG(fastJsonResponse$Field0);
        Bundle bundle0 = new Bundle();
        for(Object object0: ((Map)Preconditions.checkNotNull(map0)).keySet()) {
            bundle0.putString(((String)object0), ((String)map0.get(((String)object0))));
        }
        SafeParcelWriter.writeBundle(this.zab, fastJsonResponse$Field0.getSafeParcelableFieldId(), bundle0, true);
    }

    @Override  // com.google.android.gms.common.server.response.FastJsonResponse
    protected final void setStringsInternal(Field fastJsonResponse$Field0, String s, ArrayList arrayList0) {
        this.zaG(fastJsonResponse$Field0);
        int v = ((ArrayList)Preconditions.checkNotNull(arrayList0)).size();
        String[] arr_s = new String[v];
        for(int v1 = 0; v1 < v; ++v1) {
            arr_s[v1] = (String)arrayList0.get(v1);
        }
        SafeParcelWriter.writeStringArray(this.zab, fastJsonResponse$Field0.getSafeParcelableFieldId(), arr_s, true);
    }

    @Override  // com.google.android.gms.common.server.response.FastJsonResponse
    public final String toString() {
        Preconditions.checkNotNull(this.zad, "Cannot convert to JSON on client side.");
        Parcel parcel0 = this.zaE();
        parcel0.setDataPosition(0);
        StringBuilder stringBuilder0 = new StringBuilder(100);
        String s = (String)Preconditions.checkNotNull(this.zae);
        this.zaH(stringBuilder0, ((Map)Preconditions.checkNotNull(this.zad.zab(s))), parcel0);
        return stringBuilder0.toString();
    }

    @Override  // android.os.Parcelable
    public final void writeToParcel(Parcel parcel0, int v) {
        zan zan0;
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeInt(parcel0, 1, this.zaa);
        SafeParcelWriter.writeParcel(parcel0, 2, this.zaE(), false);
        switch(this.zac) {
            case 0: {
                zan0 = null;
                break;
            }
            case 1: {
                zan0 = this.zad;
                break;
            }
            default: {
                zan0 = this.zad;
            }
        }
        SafeParcelWriter.writeParcelable(parcel0, 3, zan0, v, false);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }

    public final Parcel zaE() {
        switch(this.zaf) {
            case 0: {
                int v = SafeParcelWriter.beginObjectHeader(this.zab);
                this.zag = v;
                SafeParcelWriter.finishObjectHeader(this.zab, v);
                this.zaf = 2;
                return this.zab;
            }
            case 1: {
                SafeParcelWriter.finishObjectHeader(this.zab, this.zag);
                this.zaf = 2;
                return this.zab;
            }
            default: {
                return this.zab;
            }
        }
    }

    private static void zaF(zan zan0, FastJsonResponse fastJsonResponse0) {
        Class class0 = fastJsonResponse0.getClass();
        if(!zan0.zaf(class0)) {
            Map map0 = fastJsonResponse0.getFieldMappings();
            zan0.zae(class0, map0);
            for(Object object0: map0.keySet()) {
                Field fastJsonResponse$Field0 = (Field)map0.get(((String)object0));
                Class class1 = fastJsonResponse$Field0.zag;
                if(class1 != null) {
                    try {
                        SafeParcelResponse.zaF(zan0, ((FastJsonResponse)class1.newInstance()));
                    }
                    catch(InstantiationException instantiationException0) {
                        throw new IllegalStateException("Could not instantiate an object of type " + ((Class)Preconditions.checkNotNull(fastJsonResponse$Field0.zag)).getCanonicalName(), instantiationException0);
                    }
                    catch(IllegalAccessException illegalAccessException0) {
                        throw new IllegalStateException("Could not access object of type " + ((Class)Preconditions.checkNotNull(fastJsonResponse$Field0.zag)).getCanonicalName(), illegalAccessException0);
                    }
                }
            }
        }
    }

    private final void zaG(Field fastJsonResponse$Field0) {
        if(fastJsonResponse$Field0.zaf == -1) {
            throw new IllegalStateException("Field does not have a valid safe parcelable field id.");
        }
        Parcel parcel0 = this.zab;
        if(parcel0 != null) {
            switch(this.zaf) {
                case 0: {
                    this.zag = SafeParcelWriter.beginObjectHeader(parcel0);
                    this.zaf = 1;
                    return;
                }
                case 1: {
                    return;
                }
                default: {
                    throw new IllegalStateException("Attempted to parse JSON with a SafeParcelResponse object that is already filled with data.");
                }
            }
        }
        throw new IllegalStateException("Internal Parcel object is null.");
    }

    private final void zaH(StringBuilder stringBuilder0, Map map0, Parcel parcel0) {
        SparseArray sparseArray0 = new SparseArray();
        for(Object object0: map0.entrySet()) {
            sparseArray0.put(((Field)((Map.Entry)object0).getValue()).getSafeParcelableFieldId(), ((Map.Entry)object0));
        }
        stringBuilder0.append('{');
        int v = SafeParcelReader.validateObjectHeader(parcel0);
        boolean z = false;
        while(parcel0.dataPosition() < v) {
            int v1 = SafeParcelReader.readHeader(parcel0);
            Map.Entry map$Entry0 = (Map.Entry)sparseArray0.get(((char)v1));
            if(map$Entry0 != null) {
                if(z) {
                    stringBuilder0.append(",");
                }
                String s = (String)map$Entry0.getKey();
                Field fastJsonResponse$Field0 = (Field)map$Entry0.getValue();
                stringBuilder0.append("\"");
                stringBuilder0.append(s);
                stringBuilder0.append("\":");
                if(fastJsonResponse$Field0.zaj()) {
                    int v2 = fastJsonResponse$Field0.zac;
                    switch(v2) {
                        case 0: {
                            SafeParcelResponse.zaJ(stringBuilder0, fastJsonResponse$Field0, SafeParcelResponse.zaD(fastJsonResponse$Field0, SafeParcelReader.readInt(parcel0, v1)));
                            break;
                        }
                        case 1: {
                            SafeParcelResponse.zaJ(stringBuilder0, fastJsonResponse$Field0, SafeParcelResponse.zaD(fastJsonResponse$Field0, SafeParcelReader.createBigInteger(parcel0, v1)));
                            break;
                        }
                        case 2: {
                            SafeParcelResponse.zaJ(stringBuilder0, fastJsonResponse$Field0, SafeParcelResponse.zaD(fastJsonResponse$Field0, SafeParcelReader.readLong(parcel0, v1)));
                            break;
                        }
                        case 3: {
                            SafeParcelResponse.zaJ(stringBuilder0, fastJsonResponse$Field0, SafeParcelResponse.zaD(fastJsonResponse$Field0, SafeParcelReader.readFloat(parcel0, v1)));
                            break;
                        }
                        case 4: {
                            SafeParcelResponse.zaJ(stringBuilder0, fastJsonResponse$Field0, SafeParcelResponse.zaD(fastJsonResponse$Field0, SafeParcelReader.readDouble(parcel0, v1)));
                            break;
                        }
                        case 5: {
                            SafeParcelResponse.zaJ(stringBuilder0, fastJsonResponse$Field0, SafeParcelResponse.zaD(fastJsonResponse$Field0, SafeParcelReader.createBigDecimal(parcel0, v1)));
                            break;
                        }
                        case 6: {
                            SafeParcelResponse.zaJ(stringBuilder0, fastJsonResponse$Field0, SafeParcelResponse.zaD(fastJsonResponse$Field0, Boolean.valueOf(SafeParcelReader.readBoolean(parcel0, v1))));
                            break;
                        }
                        case 7: {
                            SafeParcelResponse.zaJ(stringBuilder0, fastJsonResponse$Field0, SafeParcelResponse.zaD(fastJsonResponse$Field0, SafeParcelReader.createString(parcel0, v1)));
                            break;
                        }
                        case 8: 
                        case 9: {
                            SafeParcelResponse.zaJ(stringBuilder0, fastJsonResponse$Field0, SafeParcelResponse.zaD(fastJsonResponse$Field0, SafeParcelReader.createByteArray(parcel0, v1)));
                            break;
                        }
                        case 10: {
                            Bundle bundle0 = SafeParcelReader.createBundle(parcel0, v1);
                            HashMap hashMap0 = new HashMap();
                            for(Object object1: bundle0.keySet()) {
                                hashMap0.put(((String)object1), ((String)Preconditions.checkNotNull(bundle0.getString(((String)object1)))));
                            }
                            SafeParcelResponse.zaJ(stringBuilder0, fastJsonResponse$Field0, SafeParcelResponse.zaD(fastJsonResponse$Field0, hashMap0));
                            break;
                        }
                        case 11: {
                            throw new IllegalArgumentException("Method does not accept concrete type.");
                        }
                        default: {
                            throw new IllegalArgumentException("Unknown field out type = " + v2);
                        }
                    }
                }
                else if(fastJsonResponse$Field0.zad) {
                    stringBuilder0.append("[");
                alab1:
                    switch(fastJsonResponse$Field0.zac) {
                        case 0: {
                            ArrayUtils.writeArray(stringBuilder0, SafeParcelReader.createIntArray(parcel0, v1));
                            break;
                        }
                        case 1: {
                            ArrayUtils.writeArray(stringBuilder0, SafeParcelReader.createBigIntegerArray(parcel0, v1));
                            break;
                        }
                        case 2: {
                            ArrayUtils.writeArray(stringBuilder0, SafeParcelReader.createLongArray(parcel0, v1));
                            break;
                        }
                        case 3: {
                            ArrayUtils.writeArray(stringBuilder0, SafeParcelReader.createFloatArray(parcel0, v1));
                            break;
                        }
                        case 4: {
                            ArrayUtils.writeArray(stringBuilder0, SafeParcelReader.createDoubleArray(parcel0, v1));
                            break;
                        }
                        case 5: {
                            ArrayUtils.writeArray(stringBuilder0, SafeParcelReader.createBigDecimalArray(parcel0, v1));
                            break;
                        }
                        case 6: {
                            ArrayUtils.writeArray(stringBuilder0, SafeParcelReader.createBooleanArray(parcel0, v1));
                            break;
                        }
                        case 7: {
                            ArrayUtils.writeStringArray(stringBuilder0, SafeParcelReader.createStringArray(parcel0, v1));
                            break;
                        }
                        case 8: 
                        case 9: 
                        case 10: {
                            throw new UnsupportedOperationException("List of type BASE64, BASE64_URL_SAFE, or STRING_MAP is not supported");
                        }
                        case 11: {
                            Parcel[] arr_parcel = SafeParcelReader.createParcelArray(parcel0, v1);
                            for(int v3 = 0; true; ++v3) {
                                if(v3 >= arr_parcel.length) {
                                    break alab1;
                                }
                                if(v3 > 0) {
                                    stringBuilder0.append(",");
                                }
                                arr_parcel[v3].setDataPosition(0);
                                this.zaH(stringBuilder0, fastJsonResponse$Field0.zah(), arr_parcel[v3]);
                            }
                        }
                        default: {
                            throw new IllegalStateException("Unknown field type out.");
                        }
                    }
                    stringBuilder0.append("]");
                }
                else {
                    switch(fastJsonResponse$Field0.zac) {
                        case 0: {
                            stringBuilder0.append(SafeParcelReader.readInt(parcel0, v1));
                            break;
                        }
                        case 1: {
                            stringBuilder0.append(SafeParcelReader.createBigInteger(parcel0, v1));
                            break;
                        }
                        case 2: {
                            stringBuilder0.append(SafeParcelReader.readLong(parcel0, v1));
                            break;
                        }
                        case 3: {
                            stringBuilder0.append(SafeParcelReader.readFloat(parcel0, v1));
                            break;
                        }
                        case 4: {
                            stringBuilder0.append(SafeParcelReader.readDouble(parcel0, v1));
                            break;
                        }
                        case 5: {
                            stringBuilder0.append(SafeParcelReader.createBigDecimal(parcel0, v1));
                            break;
                        }
                        case 6: {
                            stringBuilder0.append(SafeParcelReader.readBoolean(parcel0, v1));
                            break;
                        }
                        case 7: {
                            String s1 = SafeParcelReader.createString(parcel0, v1);
                            stringBuilder0.append("\"");
                            stringBuilder0.append(JsonUtils.escapeString(s1));
                            stringBuilder0.append("\"");
                            break;
                        }
                        case 8: {
                            byte[] arr_b = SafeParcelReader.createByteArray(parcel0, v1);
                            stringBuilder0.append("\"");
                            stringBuilder0.append(Base64Utils.encode(arr_b));
                            stringBuilder0.append("\"");
                            break;
                        }
                        case 9: {
                            byte[] arr_b1 = SafeParcelReader.createByteArray(parcel0, v1);
                            stringBuilder0.append("\"");
                            stringBuilder0.append(Base64Utils.encodeUrlSafe(arr_b1));
                            stringBuilder0.append("\"");
                            break;
                        }
                        case 10: {
                            Bundle bundle1 = SafeParcelReader.createBundle(parcel0, v1);
                            Set set0 = bundle1.keySet();
                            stringBuilder0.append("{");
                            boolean z1 = true;
                            for(Object object2: set0) {
                                if(!z1) {
                                    stringBuilder0.append(",");
                                }
                                stringBuilder0.append("\"");
                                stringBuilder0.append(((String)object2));
                                stringBuilder0.append("\":\"");
                                stringBuilder0.append(JsonUtils.escapeString(bundle1.getString(((String)object2))));
                                stringBuilder0.append("\"");
                                z1 = false;
                            }
                            stringBuilder0.append("}");
                            break;
                        }
                        case 11: {
                            Parcel parcel1 = SafeParcelReader.createParcel(parcel0, v1);
                            parcel1.setDataPosition(0);
                            this.zaH(stringBuilder0, fastJsonResponse$Field0.zah(), parcel1);
                            break;
                        }
                        default: {
                            throw new IllegalStateException("Unknown field type out");
                        }
                    }
                }
                z = true;
            }
        }
        if(parcel0.dataPosition() != v) {
            throw new ParseException("Overread allowed size end=" + v, parcel0);
        }
        stringBuilder0.append('}');
    }

    private static final void zaI(StringBuilder stringBuilder0, int v, Object object0) {
        switch(v) {
            case 0: 
            case 1: 
            case 2: 
            case 3: 
            case 4: 
            case 5: 
            case 6: {
                stringBuilder0.append(object0);
                return;
            }
            case 7: {
                stringBuilder0.append("\"");
                stringBuilder0.append(JsonUtils.escapeString(Preconditions.checkNotNull(object0).toString()));
                stringBuilder0.append("\"");
                return;
            }
            case 8: {
                stringBuilder0.append("\"");
                stringBuilder0.append(Base64Utils.encode(((byte[])object0)));
                stringBuilder0.append("\"");
                return;
            }
            case 9: {
                stringBuilder0.append("\"");
                stringBuilder0.append(Base64Utils.encodeUrlSafe(((byte[])object0)));
                stringBuilder0.append("\"");
                return;
            }
            case 10: {
                MapUtils.writeStringMapToJson(stringBuilder0, ((HashMap)Preconditions.checkNotNull(object0)));
                return;
            }
            case 11: {
                throw new IllegalArgumentException("Method does not accept concrete type.");
            }
            default: {
                throw new IllegalArgumentException("Unknown type = " + v);
            }
        }
    }

    private static final void zaJ(StringBuilder stringBuilder0, Field fastJsonResponse$Field0, Object object0) {
        if(fastJsonResponse$Field0.zab) {
            stringBuilder0.append("[");
            int v = ((ArrayList)object0).size();
            for(int v1 = 0; v1 < v; ++v1) {
                if(v1 != 0) {
                    stringBuilder0.append(",");
                }
                Object object1 = ((ArrayList)object0).get(v1);
                SafeParcelResponse.zaI(stringBuilder0, fastJsonResponse$Field0.zaa, object1);
            }
            stringBuilder0.append("]");
            return;
        }
        SafeParcelResponse.zaI(stringBuilder0, fastJsonResponse$Field0.zaa, object0);
    }

    @Override  // com.google.android.gms.common.server.response.FastJsonResponse
    protected final void zab(Field fastJsonResponse$Field0, String s, BigDecimal bigDecimal0) {
        this.zaG(fastJsonResponse$Field0);
        SafeParcelWriter.writeBigDecimal(this.zab, fastJsonResponse$Field0.getSafeParcelableFieldId(), bigDecimal0, true);
    }

    @Override  // com.google.android.gms.common.server.response.FastJsonResponse
    protected final void zad(Field fastJsonResponse$Field0, String s, ArrayList arrayList0) {
        this.zaG(fastJsonResponse$Field0);
        int v = ((ArrayList)Preconditions.checkNotNull(arrayList0)).size();
        BigDecimal[] arr_bigDecimal = new BigDecimal[v];
        for(int v1 = 0; v1 < v; ++v1) {
            arr_bigDecimal[v1] = (BigDecimal)arrayList0.get(v1);
        }
        SafeParcelWriter.writeBigDecimalArray(this.zab, fastJsonResponse$Field0.getSafeParcelableFieldId(), arr_bigDecimal, true);
    }

    @Override  // com.google.android.gms.common.server.response.FastJsonResponse
    protected final void zaf(Field fastJsonResponse$Field0, String s, BigInteger bigInteger0) {
        this.zaG(fastJsonResponse$Field0);
        SafeParcelWriter.writeBigInteger(this.zab, fastJsonResponse$Field0.getSafeParcelableFieldId(), bigInteger0, true);
    }

    @Override  // com.google.android.gms.common.server.response.FastJsonResponse
    protected final void zah(Field fastJsonResponse$Field0, String s, ArrayList arrayList0) {
        this.zaG(fastJsonResponse$Field0);
        int v = ((ArrayList)Preconditions.checkNotNull(arrayList0)).size();
        BigInteger[] arr_bigInteger = new BigInteger[v];
        for(int v1 = 0; v1 < v; ++v1) {
            arr_bigInteger[v1] = (BigInteger)arrayList0.get(v1);
        }
        SafeParcelWriter.writeBigIntegerArray(this.zab, fastJsonResponse$Field0.getSafeParcelableFieldId(), arr_bigInteger, true);
    }

    @Override  // com.google.android.gms.common.server.response.FastJsonResponse
    protected final void zak(Field fastJsonResponse$Field0, String s, ArrayList arrayList0) {
        this.zaG(fastJsonResponse$Field0);
        int v = ((ArrayList)Preconditions.checkNotNull(arrayList0)).size();
        boolean[] arr_z = new boolean[v];
        for(int v1 = 0; v1 < v; ++v1) {
            arr_z[v1] = ((Boolean)arrayList0.get(v1)).booleanValue();
        }
        SafeParcelWriter.writeBooleanArray(this.zab, fastJsonResponse$Field0.getSafeParcelableFieldId(), arr_z, true);
    }

    @Override  // com.google.android.gms.common.server.response.FastJsonResponse
    protected final void zan(Field fastJsonResponse$Field0, String s, double f) {
        this.zaG(fastJsonResponse$Field0);
        SafeParcelWriter.writeDouble(this.zab, fastJsonResponse$Field0.getSafeParcelableFieldId(), f);
    }

    @Override  // com.google.android.gms.common.server.response.FastJsonResponse
    protected final void zap(Field fastJsonResponse$Field0, String s, ArrayList arrayList0) {
        this.zaG(fastJsonResponse$Field0);
        int v = ((ArrayList)Preconditions.checkNotNull(arrayList0)).size();
        double[] arr_f = new double[v];
        for(int v1 = 0; v1 < v; ++v1) {
            arr_f[v1] = (double)(((Double)arrayList0.get(v1)));
        }
        SafeParcelWriter.writeDoubleArray(this.zab, fastJsonResponse$Field0.getSafeParcelableFieldId(), arr_f, true);
    }

    @Override  // com.google.android.gms.common.server.response.FastJsonResponse
    protected final void zar(Field fastJsonResponse$Field0, String s, float f) {
        this.zaG(fastJsonResponse$Field0);
        SafeParcelWriter.writeFloat(this.zab, fastJsonResponse$Field0.getSafeParcelableFieldId(), f);
    }

    @Override  // com.google.android.gms.common.server.response.FastJsonResponse
    protected final void zat(Field fastJsonResponse$Field0, String s, ArrayList arrayList0) {
        this.zaG(fastJsonResponse$Field0);
        int v = ((ArrayList)Preconditions.checkNotNull(arrayList0)).size();
        float[] arr_f = new float[v];
        for(int v1 = 0; v1 < v; ++v1) {
            arr_f[v1] = (float)(((Float)arrayList0.get(v1)));
        }
        SafeParcelWriter.writeFloatArray(this.zab, fastJsonResponse$Field0.getSafeParcelableFieldId(), arr_f, true);
    }

    @Override  // com.google.android.gms.common.server.response.FastJsonResponse
    protected final void zaw(Field fastJsonResponse$Field0, String s, ArrayList arrayList0) {
        this.zaG(fastJsonResponse$Field0);
        int v = ((ArrayList)Preconditions.checkNotNull(arrayList0)).size();
        int[] arr_v = new int[v];
        for(int v1 = 0; v1 < v; ++v1) {
            arr_v[v1] = (int)(((Integer)arrayList0.get(v1)));
        }
        SafeParcelWriter.writeIntArray(this.zab, fastJsonResponse$Field0.getSafeParcelableFieldId(), arr_v, true);
    }

    @Override  // com.google.android.gms.common.server.response.FastJsonResponse
    protected final void zaz(Field fastJsonResponse$Field0, String s, ArrayList arrayList0) {
        this.zaG(fastJsonResponse$Field0);
        int v = ((ArrayList)Preconditions.checkNotNull(arrayList0)).size();
        long[] arr_v = new long[v];
        for(int v1 = 0; v1 < v; ++v1) {
            arr_v[v1] = (long)(((Long)arrayList0.get(v1)));
        }
        SafeParcelWriter.writeLongArray(this.zab, fastJsonResponse$Field0.getSafeParcelableFieldId(), arr_v, true);
    }
}

