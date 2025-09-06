package com.google.android.gms.internal.measurement;

import java.util.List;

public final class zzgd extends zzlb implements zzmj {
    private String zzA;
    private long zzB;
    private int zzC;
    private String zzD;
    private String zzE;
    private boolean zzF;
    private zzli zzG;
    private String zzH;
    private int zzI;
    private int zzJ;
    private int zzK;
    private String zzL;
    private long zzM;
    private long zzN;
    private String zzO;
    private String zzP;
    private int zzQ;
    private String zzR;
    private zzgg zzS;
    private zzlg zzT;
    private long zzU;
    private long zzV;
    private String zzW;
    private String zzX;
    private int zzY;
    private boolean zzZ;
    public static final int zza;
    private String zzaa;
    private boolean zzab;
    private zzfz zzac;
    private String zzad;
    private zzli zzae;
    private String zzaf;
    private long zzag;
    private static final zzgd zzd;
    private int zze;
    private int zzf;
    private int zzg;
    private zzli zzh;
    private zzli zzi;
    private long zzj;
    private long zzk;
    private long zzl;
    private long zzm;
    private long zzn;
    private String zzo;
    private String zzp;
    private String zzq;
    private String zzr;
    private int zzs;
    private String zzt;
    private String zzu;
    private String zzv;
    private long zzw;
    private long zzx;
    private String zzy;
    private boolean zzz;

    static {
        zzgd zzgd0 = new zzgd();
        zzgd.zzd = zzgd0;
        zzlb.zzbO(zzgd.class, zzgd0);
    }

    private zzgd() {
        this.zzh = zzgd.zzbH();
        this.zzi = zzgd.zzbH();
        this.zzo = "";
        this.zzp = "";
        this.zzq = "";
        this.zzr = "";
        this.zzt = "";
        this.zzu = "";
        this.zzv = "";
        this.zzy = "";
        this.zzA = "";
        this.zzD = "";
        this.zzE = "";
        this.zzG = zzgd.zzbH();
        this.zzH = "";
        this.zzL = "";
        this.zzO = "";
        this.zzP = "";
        this.zzR = "";
        this.zzT = zzgd.zzbE();
        this.zzW = "";
        this.zzX = "";
        this.zzaa = "";
        this.zzad = "";
        this.zzae = zzlb.zzbH();
        this.zzaf = "";
    }

    public final String zzA() [...] // 潜在的解密器

    public final String zzB() [...] // 潜在的解密器

    public final String zzC() [...] // 潜在的解密器

    public final String zzD() [...] // 潜在的解密器

    public final String zzE() [...] // 潜在的解密器

    public final String zzF() [...] // 潜在的解密器

    public final String zzG() [...] // 潜在的解密器

    public final String zzH() [...] // 潜在的解密器

    public final String zzI() [...] // 潜在的解密器

    public final String zzJ() [...] // 潜在的解密器

    public final String zzK() [...] // 潜在的解密器

    public final String zzL() [...] // 潜在的解密器

    public final String zzM() [...] // 潜在的解密器

    public final List zzN() {
        return this.zzG;
    }

    public final List zzO() {
        return this.zzh;
    }

    public final List zzP() {
        return this.zzi;
    }

    static void zzQ(zzgd zzgd0) {
        zzgd0.zze &= 0x7FFFFFFF;
        zzgd0.zzO = "";
    }

    static void zzR(zzgd zzgd0, int v) {
        zzgd0.zzf |= 2;
        zzgd0.zzQ = v;
    }

    static void zzS(zzgd zzgd0, int v, zzft zzft0) {
        zzft0.getClass();
        zzgd0.zzbS();
        zzgd0.zzh.set(v, zzft0);
    }

    static void zzT(zzgd zzgd0, String s) {
        s.getClass();
        zzgd0.zzf |= 4;
        zzgd0.zzR = s;
    }

    static void zzU(zzgd zzgd0, zzgg zzgg0) {
        zzgg0.getClass();
        zzgd0.zzS = zzgg0;
        zzgd0.zzf |= 8;
    }

    static void zzV(zzgd zzgd0, Iterable iterable0) {
        zzlg zzlg0 = zzgd0.zzT;
        if(!zzlg0.zzc()) {
            int v = zzlg0.size();
            zzgd0.zzT = zzlg0.zzg((v == 0 ? 10 : v + v));
        }
        zzjk.zzbw(iterable0, zzgd0.zzT);
    }

    static void zzW(zzgd zzgd0, zzft zzft0) {
        zzft0.getClass();
        zzgd0.zzbS();
        zzgd0.zzh.add(zzft0);
    }

    static void zzX(zzgd zzgd0, long v) {
        zzgd0.zzf |= 16;
        zzgd0.zzU = v;
    }

    static void zzY(zzgd zzgd0, long v) {
        zzgd0.zzf |= 0x20;
        zzgd0.zzV = v;
    }

    static void zzZ(zzgd zzgd0, String s) {
        zzgd0.zzf |= 0x80;
        zzgd0.zzX = s;
    }

    public final int zza() {
        return this.zzI;
    }

    static void zzaA(zzgd zzgd0, String s) {
        s.getClass();
        zzgd0.zze |= 0x800;
        zzgd0.zzt = s;
    }

    static void zzaB(zzgd zzgd0, String s) {
        s.getClass();
        zzgd0.zze |= 0x1000;
        zzgd0.zzu = s;
    }

    static void zzaC(zzgd zzgd0, String s) {
        s.getClass();
        zzgd0.zze |= 0x2000;
        zzgd0.zzv = s;
    }

    static void zzaD(zzgd zzgd0, long v) {
        zzgd0.zze |= 0x4000;
        zzgd0.zzw = v;
    }

    static void zzaE(zzgd zzgd0, long v) {
        zzgd0.zze |= 0x8000;
        zzgd0.zzx = 79000L;
    }

    static void zzaF(zzgd zzgd0, String s) {
        s.getClass();
        zzgd0.zze |= 0x10000;
        zzgd0.zzy = s;
    }

    static void zzaG(zzgd zzgd0) {
        zzgd0.zze &= 0xFFFEFFFF;
        zzgd0.zzy = "";
    }

    static void zzaH(zzgd zzgd0, boolean z) {
        zzgd0.zze |= 0x20000;
        zzgd0.zzz = z;
    }

    static void zzaI(zzgd zzgd0) {
        zzgd0.zze &= 0xFFFDFFFF;
        zzgd0.zzz = false;
    }

    static void zzaJ(zzgd zzgd0, String s) {
        s.getClass();
        zzgd0.zze |= 0x40000;
        zzgd0.zzA = s;
    }

    static void zzaK(zzgd zzgd0) {
        zzgd0.zze &= 0xFFFBFFFF;
        zzgd0.zzA = "";
    }

    static void zzaL(zzgd zzgd0, long v) {
        zzgd0.zze |= 0x80000;
        zzgd0.zzB = v;
    }

    static void zzaM(zzgd zzgd0, int v) {
        zzgd0.zze |= 0x100000;
        zzgd0.zzC = v;
    }

    static void zzaN(zzgd zzgd0, String s) {
        zzgd0.zze |= 0x200000;
        zzgd0.zzD = s;
    }

    static void zzaO(zzgd zzgd0) {
        zzgd0.zze &= 0xFFDFFFFF;
        zzgd0.zzD = "";
    }

    static void zzaP(zzgd zzgd0, String s) {
        s.getClass();
        zzgd0.zze |= 0x400000;
        zzgd0.zzE = s;
    }

    static void zzaQ(zzgd zzgd0, boolean z) {
        zzgd0.zze |= 0x800000;
        zzgd0.zzF = z;
    }

    static void zzaR(zzgd zzgd0, Iterable iterable0) {
        zzli zzli0 = zzgd0.zzG;
        if(!zzli0.zzc()) {
            zzgd0.zzG = zzlb.zzbI(zzli0);
        }
        zzjk.zzbw(iterable0, zzgd0.zzG);
    }

    static void zzaS(zzgd zzgd0) {
        zzgd0.zzG = zzgd.zzbH();
    }

    static void zzaT(zzgd zzgd0, String s) {
        s.getClass();
        zzgd0.zze |= 0x1000000;
        zzgd0.zzH = s;
    }

    static void zzaU(zzgd zzgd0, int v) {
        zzgd0.zze |= 0x2000000;
        zzgd0.zzI = v;
    }

    static void zzaV(zzgd zzgd0, int v) {
        zzgd0.zze |= 1;
        zzgd0.zzg = 1;
    }

    static void zzaW(zzgd zzgd0) {
        zzgd0.zze &= 0xEFFFFFFF;
        zzgd0.zzL = "";
    }

    static void zzaX(zzgd zzgd0, long v) {
        zzgd0.zze |= 0x20000000;
        zzgd0.zzM = v;
    }

    public final boolean zzaY() {
        return this.zzz;
    }

    public final boolean zzaZ() {
        return this.zzF;
    }

    static void zzaa(zzgd zzgd0, Iterable iterable0) {
        zzgd0.zzbS();
        zzjk.zzbw(iterable0, zzgd0.zzh);
    }

    static void zzab(zzgd zzgd0, String s) {
        s.getClass();
        zzgd0.zzf |= 0x2000;
        zzgd0.zzad = s;
    }

    static void zzac(zzgd zzgd0) {
        zzgd0.zzf &= 0xFFFFDFFF;
        zzgd0.zzad = "";
    }

    static void zzad(zzgd zzgd0, Iterable iterable0) {
        zzli zzli0 = zzgd0.zzae;
        if(!zzli0.zzc()) {
            zzgd0.zzae = zzlb.zzbI(zzli0);
        }
        zzjk.zzbw(iterable0, zzgd0.zzae);
    }

    static void zzae(zzgd zzgd0) {
        zzgd0.zzh = zzgd.zzbH();
    }

    static void zzaf(zzgd zzgd0, String s) {
        s.getClass();
        zzgd0.zzf |= 0x4000;
        zzgd0.zzaf = s;
    }

    static void zzag(zzgd zzgd0, long v) {
        zzgd0.zzf |= 0x8000;
        zzgd0.zzag = v;
    }

    static void zzah(zzgd zzgd0, int v) {
        zzgd0.zzbS();
        zzgd0.zzh.remove(v);
    }

    static void zzai(zzgd zzgd0, int v, zzgm zzgm0) {
        zzgm0.getClass();
        zzgd0.zzbT();
        zzgd0.zzi.set(v, zzgm0);
    }

    static void zzaj(zzgd zzgd0, zzgm zzgm0) {
        zzgm0.getClass();
        zzgd0.zzbT();
        zzgd0.zzi.add(zzgm0);
    }

    static void zzak(zzgd zzgd0, Iterable iterable0) {
        zzgd0.zzbT();
        zzjk.zzbw(iterable0, zzgd0.zzi);
    }

    static void zzal(zzgd zzgd0, int v) {
        zzgd0.zzbT();
        zzgd0.zzi.remove(v);
    }

    static void zzam(zzgd zzgd0, long v) {
        zzgd0.zze |= 2;
        zzgd0.zzj = v;
    }

    static void zzan(zzgd zzgd0, long v) {
        zzgd0.zze |= 4;
        zzgd0.zzk = v;
    }

    static void zzao(zzgd zzgd0, long v) {
        zzgd0.zze |= 8;
        zzgd0.zzl = v;
    }

    static void zzap(zzgd zzgd0, long v) {
        zzgd0.zze |= 16;
        zzgd0.zzm = v;
    }

    static void zzaq(zzgd zzgd0) {
        zzgd0.zze &= -17;
        zzgd0.zzm = 0L;
    }

    static void zzar(zzgd zzgd0, long v) {
        zzgd0.zze |= 0x20;
        zzgd0.zzn = v;
    }

    static void zzas(zzgd zzgd0) {
        zzgd0.zze &= -33;
        zzgd0.zzn = 0L;
    }

    static void zzat(zzgd zzgd0, String s) {
        zzgd0.zze |= 0x40;
        zzgd0.zzo = "android";
    }

    static void zzau(zzgd zzgd0, String s) {
        s.getClass();
        zzgd0.zze |= 0x80;
        zzgd0.zzp = s;
    }

    static void zzav(zzgd zzgd0) {
        zzgd0.zze &= 0xFFFFFF7F;
        zzgd0.zzp = "";
    }

    static void zzaw(zzgd zzgd0, String s) {
        s.getClass();
        zzgd0.zze |= 0x100;
        zzgd0.zzq = s;
    }

    static void zzax(zzgd zzgd0) {
        zzgd0.zze &= 0xFFFFFEFF;
        zzgd0.zzq = "";
    }

    static void zzay(zzgd zzgd0, String s) {
        s.getClass();
        zzgd0.zze |= 0x200;
        zzgd0.zzr = s;
    }

    static void zzaz(zzgd zzgd0, int v) {
        zzgd0.zze |= 0x400;
        zzgd0.zzs = v;
    }

    public final int zzb() {
        return this.zzC;
    }

    private final void zzbS() {
        zzli zzli0 = this.zzh;
        if(!zzli0.zzc()) {
            this.zzh = zzlb.zzbI(zzli0);
        }
    }

    private final void zzbT() {
        zzli zzli0 = this.zzi;
        if(!zzli0.zzc()) {
            this.zzi = zzlb.zzbI(zzli0);
        }
    }

    public final boolean zzba() {
        return (this.zze & 0x2000000) != 0;
    }

    public final boolean zzbb() {
        return (this.zze & 0x100000) != 0;
    }

    public final boolean zzbc() {
        return (this.zze & 0x20000000) != 0;
    }

    public final boolean zzbd() {
        return (this.zzf & 0x80) != 0;
    }

    public final boolean zzbe() {
        return (this.zze & 0x80000) != 0;
    }

    public final boolean zzbf() {
        return (this.zzf & 16) != 0;
    }

    public final boolean zzbg() {
        return (this.zze & 8) != 0;
    }

    public final boolean zzbh() {
        return (this.zze & 0x4000) != 0;
    }

    public final boolean zzbi() {
        return (this.zze & 0x20000) != 0;
    }

    public final boolean zzbj() {
        return (this.zze & 0x20) != 0;
    }

    public final boolean zzbk() {
        return (this.zze & 16) != 0;
    }

    public final boolean zzbl() {
        return (this.zze & 1) != 0;
    }

    public final boolean zzbm() {
        return (this.zzf & 2) != 0;
    }

    public final boolean zzbn() {
        return (this.zze & 0x800000) != 0;
    }

    public final boolean zzbo() {
        return (this.zzf & 0x2000) != 0;
    }

    public final boolean zzbp() {
        return (this.zze & 4) != 0;
    }

    public final boolean zzbq() {
        return (this.zzf & 0x8000) != 0;
    }

    public final boolean zzbr() {
        return (this.zze & 0x400) != 0;
    }

    public final boolean zzbs() {
        return (this.zze & 2) != 0;
    }

    public final boolean zzbt() {
        return (this.zze & 0x8000) != 0;
    }

    public final int zzc() {
        return this.zzh.size();
    }

    public final int zzd() {
        return this.zzg;
    }

    public final int zze() {
        return this.zzQ;
    }

    public final int zzf() {
        return this.zzs;
    }

    public final int zzg() {
        return this.zzi.size();
    }

    public final long zzh() {
        return this.zzM;
    }

    public final long zzi() {
        return this.zzB;
    }

    public final long zzj() {
        return this.zzU;
    }

    public final long zzk() {
        return this.zzl;
    }

    @Override  // com.google.android.gms.internal.measurement.zzlb
    protected final Object zzl(int v, Object object0, Object object1) {
        if(v - 1 != 0) {
            switch(v - 1) {
                case 2: {
                    return zzgd.zzbL(zzgd.zzd, "\u00015\u0000\u0002\u0001C5\u0000\u0005\u0000\u0001င\u0000\u0002\u001B\u0003\u001B\u0004ဂ\u0001\u0005ဂ\u0002\u0006ဂ\u0003\u0007ဂ\u0005\bဈ\u0006\tဈ\u0007\nဈ\b\u000Bဈ\t\fင\n\rဈ\u000B\u000Eဈ\f\u0010ဈ\r\u0011ဂ\u000E\u0012ဂ\u000F\u0013ဈ\u0010\u0014ဇ\u0011\u0015ဈ\u0012\u0016ဂ\u0013\u0017င\u0014\u0018ဈ\u0015\u0019ဈ\u0016\u001Aဂ\u0004\u001Cဇ\u0017\u001D\u001B\u001Eဈ\u0018\u001Fင\u0019 င\u001A!င\u001B\"ဈ\u001C#ဂ\u001D$ဂ\u001E%ဈ\u001F&ဈ \'င!)ဈ\",ဉ#-\u001D.ဂ$/ဂ%2ဈ&4ဈ\'5ဌ(7ဇ)9ဈ*:ဇ+;ဉ,?ဈ-@\u001AAဈ.Cဂ/", new Object[]{"zze", "zzf", "zzg", "zzh", zzft.class, "zzi", zzgm.class, "zzj", "zzk", "zzl", "zzn", "zzo", "zzp", "zzq", "zzr", "zzs", "zzt", "zzu", "zzv", "zzw", "zzx", "zzy", "zzz", "zzA", "zzB", "zzC", "zzD", "zzE", "zzm", "zzF", "zzG", zzfp.class, "zzH", "zzI", "zzJ", "zzK", "zzL", "zzM", "zzN", "zzO", "zzP", "zzQ", "zzR", "zzS", "zzT", "zzU", "zzV", "zzW", "zzX", "zzY", zzfl.zza, "zzZ", "zzaa", "zzab", "zzac", "zzad", "zzae", "zzaf", "zzag"});
                }
                case 3: {
                    return new zzgd();
                }
                default: {
                    switch(v - 1) {
                        case 4: {
                            return new zzgc(null);
                        }
                        case 5: {
                            return zzgd.zzd;
                        }
                        default: {
                            return null;
                        }
                    }
                }
            }
        }
        return (byte)1;
    }

    public final long zzm() {
        return this.zzw;
    }

    public final long zzn() {
        return this.zzn;
    }

    public final long zzo() {
        return this.zzm;
    }

    public final long zzp() {
        return this.zzk;
    }

    public final long zzq() {
        return this.zzag;
    }

    public final long zzr() {
        return this.zzj;
    }

    public final long zzs() {
        return this.zzx;
    }

    public final zzft zzt(int v) {
        return (zzft)this.zzh.get(v);
    }

    public static zzgc zzu() {
        return (zzgc)zzgd.zzd.zzbA();
    }

    static zzgd zzv() {
        return zzgd.zzd;
    }

    public final zzgm zzw(int v) {
        return (zzgm)this.zzi.get(v);
    }

    public final String zzx() [...] // 潜在的解密器

    public final String zzy() [...] // 潜在的解密器

    public final String zzz() [...] // 潜在的解密器
}

