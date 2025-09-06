package com.google.android.material.color.utilities;

import com.google.errorprone.annotations.CheckReturnValue;

@CheckReturnValue
public class Scheme {
    private int background;
    private int error;
    private int errorContainer;
    private int inverseOnSurface;
    private int inversePrimary;
    private int inverseSurface;
    private int onBackground;
    private int onError;
    private int onErrorContainer;
    private int onPrimary;
    private int onPrimaryContainer;
    private int onSecondary;
    private int onSecondaryContainer;
    private int onSurface;
    private int onSurfaceVariant;
    private int onTertiary;
    private int onTertiaryContainer;
    private int outline;
    private int outlineVariant;
    private int primary;
    private int primaryContainer;
    private int scrim;
    private int secondary;
    private int secondaryContainer;
    private int shadow;
    private int surface;
    private int surfaceVariant;
    private int tertiary;
    private int tertiaryContainer;

    public Scheme() {
    }

    public Scheme(int v, int v1, int v2, int v3, int v4, int v5, int v6, int v7, int v8, int v9, int v10, int v11, int v12, int v13, int v14, int v15, int v16, int v17, int v18, int v19, int v20, int v21, int v22, int v23, int v24, int v25, int v26, int v27, int v28) {
        this.primary = v;
        this.onPrimary = v1;
        this.primaryContainer = v2;
        this.onPrimaryContainer = v3;
        this.secondary = v4;
        this.onSecondary = v5;
        this.secondaryContainer = v6;
        this.onSecondaryContainer = v7;
        this.tertiary = v8;
        this.onTertiary = v9;
        this.tertiaryContainer = v10;
        this.onTertiaryContainer = v11;
        this.error = v12;
        this.onError = v13;
        this.errorContainer = v14;
        this.onErrorContainer = v15;
        this.background = v16;
        this.onBackground = v17;
        this.surface = v18;
        this.onSurface = v19;
        this.surfaceVariant = v20;
        this.onSurfaceVariant = v21;
        this.outline = v22;
        this.outlineVariant = v23;
        this.shadow = v24;
        this.scrim = v25;
        this.inverseSurface = v26;
        this.inverseOnSurface = v27;
        this.inversePrimary = v28;
    }

    public static Scheme dark(int v) {
        return Scheme.darkFromCorePalette(CorePalette.of(v));
    }

    public static Scheme darkContent(int v) {
        return Scheme.darkFromCorePalette(CorePalette.contentOf(v));
    }

    private static Scheme darkFromCorePalette(CorePalette corePalette0) {
        return new Scheme().withPrimary(corePalette0.a1.tone(80)).withOnPrimary(corePalette0.a1.tone(20)).withPrimaryContainer(corePalette0.a1.tone(30)).withOnPrimaryContainer(corePalette0.a1.tone(90)).withSecondary(corePalette0.a2.tone(80)).withOnSecondary(corePalette0.a2.tone(20)).withSecondaryContainer(corePalette0.a2.tone(30)).withOnSecondaryContainer(corePalette0.a2.tone(90)).withTertiary(corePalette0.a3.tone(80)).withOnTertiary(corePalette0.a3.tone(20)).withTertiaryContainer(corePalette0.a3.tone(30)).withOnTertiaryContainer(corePalette0.a3.tone(90)).withError(corePalette0.error.tone(80)).withOnError(corePalette0.error.tone(20)).withErrorContainer(corePalette0.error.tone(30)).withOnErrorContainer(corePalette0.error.tone(80)).withBackground(corePalette0.n1.tone(10)).withOnBackground(corePalette0.n1.tone(90)).withSurface(corePalette0.n1.tone(10)).withOnSurface(corePalette0.n1.tone(90)).withSurfaceVariant(corePalette0.n2.tone(30)).withOnSurfaceVariant(corePalette0.n2.tone(80)).withOutline(corePalette0.n2.tone(60)).withOutlineVariant(corePalette0.n2.tone(30)).withShadow(corePalette0.n1.tone(0)).withScrim(corePalette0.n1.tone(0)).withInverseSurface(corePalette0.n1.tone(90)).withInverseOnSurface(corePalette0.n1.tone(20)).withInversePrimary(corePalette0.a1.tone(40));
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(!(object0 instanceof Scheme)) {
            return false;
        }
        if(!super.equals(object0)) {
            return false;
        }
        Scheme scheme0 = (Scheme)object0;
        if(this.primary != scheme0.primary) {
            return false;
        }
        if(this.onPrimary != scheme0.onPrimary) {
            return false;
        }
        if(this.primaryContainer != scheme0.primaryContainer) {
            return false;
        }
        if(this.onPrimaryContainer != scheme0.onPrimaryContainer) {
            return false;
        }
        if(this.secondary != scheme0.secondary) {
            return false;
        }
        if(this.onSecondary != scheme0.onSecondary) {
            return false;
        }
        if(this.secondaryContainer != scheme0.secondaryContainer) {
            return false;
        }
        if(this.onSecondaryContainer != scheme0.onSecondaryContainer) {
            return false;
        }
        if(this.tertiary != scheme0.tertiary) {
            return false;
        }
        if(this.onTertiary != scheme0.onTertiary) {
            return false;
        }
        if(this.tertiaryContainer != scheme0.tertiaryContainer) {
            return false;
        }
        if(this.onTertiaryContainer != scheme0.onTertiaryContainer) {
            return false;
        }
        if(this.error != scheme0.error) {
            return false;
        }
        if(this.onError != scheme0.onError) {
            return false;
        }
        if(this.errorContainer != scheme0.errorContainer) {
            return false;
        }
        if(this.onErrorContainer != scheme0.onErrorContainer) {
            return false;
        }
        if(this.background != scheme0.background) {
            return false;
        }
        if(this.onBackground != scheme0.onBackground) {
            return false;
        }
        if(this.surface != scheme0.surface) {
            return false;
        }
        if(this.onSurface != scheme0.onSurface) {
            return false;
        }
        if(this.surfaceVariant != scheme0.surfaceVariant) {
            return false;
        }
        if(this.onSurfaceVariant != scheme0.onSurfaceVariant) {
            return false;
        }
        if(this.outline != scheme0.outline) {
            return false;
        }
        if(this.outlineVariant != scheme0.outlineVariant) {
            return false;
        }
        if(this.shadow != scheme0.shadow) {
            return false;
        }
        if(this.scrim != scheme0.scrim) {
            return false;
        }
        if(this.inverseSurface != scheme0.inverseSurface) {
            return false;
        }
        return this.inverseOnSurface == scheme0.inverseOnSurface ? this.inversePrimary == scheme0.inversePrimary : false;
    }

    public int getBackground() {
        return this.background;
    }

    public int getError() {
        return this.error;
    }

    public int getErrorContainer() {
        return this.errorContainer;
    }

    public int getInverseOnSurface() {
        return this.inverseOnSurface;
    }

    public int getInversePrimary() {
        return this.inversePrimary;
    }

    public int getInverseSurface() {
        return this.inverseSurface;
    }

    public int getOnBackground() {
        return this.onBackground;
    }

    public int getOnError() {
        return this.onError;
    }

    public int getOnErrorContainer() {
        return this.onErrorContainer;
    }

    public int getOnPrimary() {
        return this.onPrimary;
    }

    public int getOnPrimaryContainer() {
        return this.onPrimaryContainer;
    }

    public int getOnSecondary() {
        return this.onSecondary;
    }

    public int getOnSecondaryContainer() {
        return this.onSecondaryContainer;
    }

    public int getOnSurface() {
        return this.onSurface;
    }

    public int getOnSurfaceVariant() {
        return this.onSurfaceVariant;
    }

    public int getOnTertiary() {
        return this.onTertiary;
    }

    public int getOnTertiaryContainer() {
        return this.onTertiaryContainer;
    }

    public int getOutline() {
        return this.outline;
    }

    public int getOutlineVariant() {
        return this.outlineVariant;
    }

    public int getPrimary() {
        return this.primary;
    }

    public int getPrimaryContainer() {
        return this.primaryContainer;
    }

    public int getScrim() {
        return this.scrim;
    }

    public int getSecondary() {
        return this.secondary;
    }

    public int getSecondaryContainer() {
        return this.secondaryContainer;
    }

    public int getShadow() {
        return this.shadow;
    }

    public int getSurface() {
        return this.surface;
    }

    public int getSurfaceVariant() {
        return this.surfaceVariant;
    }

    public int getTertiary() {
        return this.tertiary;
    }

    public int getTertiaryContainer() {
        return this.tertiaryContainer;
    }

    @Override
    public int hashCode() {
        return ((((((((((((((((((((((((((((super.hashCode() * 0x1F + this.primary) * 0x1F + this.onPrimary) * 0x1F + this.primaryContainer) * 0x1F + this.onPrimaryContainer) * 0x1F + this.secondary) * 0x1F + this.onSecondary) * 0x1F + this.secondaryContainer) * 0x1F + this.onSecondaryContainer) * 0x1F + this.tertiary) * 0x1F + this.onTertiary) * 0x1F + this.tertiaryContainer) * 0x1F + this.onTertiaryContainer) * 0x1F + this.error) * 0x1F + this.onError) * 0x1F + this.errorContainer) * 0x1F + this.onErrorContainer) * 0x1F + this.background) * 0x1F + this.onBackground) * 0x1F + this.surface) * 0x1F + this.onSurface) * 0x1F + this.surfaceVariant) * 0x1F + this.onSurfaceVariant) * 0x1F + this.outline) * 0x1F + this.outlineVariant) * 0x1F + this.shadow) * 0x1F + this.scrim) * 0x1F + this.inverseSurface) * 0x1F + this.inverseOnSurface) * 0x1F + this.inversePrimary;
    }

    public static Scheme light(int v) {
        return Scheme.lightFromCorePalette(CorePalette.of(v));
    }

    public static Scheme lightContent(int v) {
        return Scheme.lightFromCorePalette(CorePalette.contentOf(v));
    }

    private static Scheme lightFromCorePalette(CorePalette corePalette0) {
        return new Scheme().withPrimary(corePalette0.a1.tone(40)).withOnPrimary(corePalette0.a1.tone(100)).withPrimaryContainer(corePalette0.a1.tone(90)).withOnPrimaryContainer(corePalette0.a1.tone(10)).withSecondary(corePalette0.a2.tone(40)).withOnSecondary(corePalette0.a2.tone(100)).withSecondaryContainer(corePalette0.a2.tone(90)).withOnSecondaryContainer(corePalette0.a2.tone(10)).withTertiary(corePalette0.a3.tone(40)).withOnTertiary(corePalette0.a3.tone(100)).withTertiaryContainer(corePalette0.a3.tone(90)).withOnTertiaryContainer(corePalette0.a3.tone(10)).withError(corePalette0.error.tone(40)).withOnError(corePalette0.error.tone(100)).withErrorContainer(corePalette0.error.tone(90)).withOnErrorContainer(corePalette0.error.tone(10)).withBackground(corePalette0.n1.tone(99)).withOnBackground(corePalette0.n1.tone(10)).withSurface(corePalette0.n1.tone(99)).withOnSurface(corePalette0.n1.tone(10)).withSurfaceVariant(corePalette0.n2.tone(90)).withOnSurfaceVariant(corePalette0.n2.tone(30)).withOutline(corePalette0.n2.tone(50)).withOutlineVariant(corePalette0.n2.tone(80)).withShadow(corePalette0.n1.tone(0)).withScrim(corePalette0.n1.tone(0)).withInverseSurface(corePalette0.n1.tone(20)).withInverseOnSurface(corePalette0.n1.tone(0x5F)).withInversePrimary(corePalette0.a1.tone(80));
    }

    public void setBackground(int v) {
        this.background = v;
    }

    public void setError(int v) {
        this.error = v;
    }

    public void setErrorContainer(int v) {
        this.errorContainer = v;
    }

    public void setInverseOnSurface(int v) {
        this.inverseOnSurface = v;
    }

    public void setInversePrimary(int v) {
        this.inversePrimary = v;
    }

    public void setInverseSurface(int v) {
        this.inverseSurface = v;
    }

    public void setOnBackground(int v) {
        this.onBackground = v;
    }

    public void setOnError(int v) {
        this.onError = v;
    }

    public void setOnErrorContainer(int v) {
        this.onErrorContainer = v;
    }

    public void setOnPrimary(int v) {
        this.onPrimary = v;
    }

    public void setOnPrimaryContainer(int v) {
        this.onPrimaryContainer = v;
    }

    public void setOnSecondary(int v) {
        this.onSecondary = v;
    }

    public void setOnSecondaryContainer(int v) {
        this.onSecondaryContainer = v;
    }

    public void setOnSurface(int v) {
        this.onSurface = v;
    }

    public void setOnSurfaceVariant(int v) {
        this.onSurfaceVariant = v;
    }

    public void setOnTertiary(int v) {
        this.onTertiary = v;
    }

    public void setOnTertiaryContainer(int v) {
        this.onTertiaryContainer = v;
    }

    public void setOutline(int v) {
        this.outline = v;
    }

    public void setOutlineVariant(int v) {
        this.outlineVariant = v;
    }

    public void setPrimary(int v) {
        this.primary = v;
    }

    public void setPrimaryContainer(int v) {
        this.primaryContainer = v;
    }

    public void setScrim(int v) {
        this.scrim = v;
    }

    public void setSecondary(int v) {
        this.secondary = v;
    }

    public void setSecondaryContainer(int v) {
        this.secondaryContainer = v;
    }

    public void setShadow(int v) {
        this.shadow = v;
    }

    public void setSurface(int v) {
        this.surface = v;
    }

    public void setSurfaceVariant(int v) {
        this.surfaceVariant = v;
    }

    public void setTertiary(int v) {
        this.tertiary = v;
    }

    public void setTertiaryContainer(int v) {
        this.tertiaryContainer = v;
    }

    @Override
    public String toString() {
        return "Scheme{primary=" + this.primary + ", onPrimary=" + this.onPrimary + ", primaryContainer=" + this.primaryContainer + ", onPrimaryContainer=" + this.onPrimaryContainer + ", secondary=" + this.secondary + ", onSecondary=" + this.onSecondary + ", secondaryContainer=" + this.secondaryContainer + ", onSecondaryContainer=" + this.onSecondaryContainer + ", tertiary=" + this.tertiary + ", onTertiary=" + this.onTertiary + ", tertiaryContainer=" + this.tertiaryContainer + ", onTertiaryContainer=" + this.onTertiaryContainer + ", error=" + this.error + ", onError=" + this.onError + ", errorContainer=" + this.errorContainer + ", onErrorContainer=" + this.onErrorContainer + ", background=" + this.background + ", onBackground=" + this.onBackground + ", surface=" + this.surface + ", onSurface=" + this.onSurface + ", surfaceVariant=" + this.surfaceVariant + ", onSurfaceVariant=" + this.onSurfaceVariant + ", outline=" + this.outline + ", outlineVariant=" + this.outlineVariant + ", shadow=" + this.shadow + ", scrim=" + this.scrim + ", inverseSurface=" + this.inverseSurface + ", inverseOnSurface=" + this.inverseOnSurface + ", inversePrimary=" + this.inversePrimary + '}';
    }

    public Scheme withBackground(int v) {
        this.background = v;
        return this;
    }

    public Scheme withError(int v) {
        this.error = v;
        return this;
    }

    public Scheme withErrorContainer(int v) {
        this.errorContainer = v;
        return this;
    }

    public Scheme withInverseOnSurface(int v) {
        this.inverseOnSurface = v;
        return this;
    }

    public Scheme withInversePrimary(int v) {
        this.inversePrimary = v;
        return this;
    }

    public Scheme withInverseSurface(int v) {
        this.inverseSurface = v;
        return this;
    }

    public Scheme withOnBackground(int v) {
        this.onBackground = v;
        return this;
    }

    public Scheme withOnError(int v) {
        this.onError = v;
        return this;
    }

    public Scheme withOnErrorContainer(int v) {
        this.onErrorContainer = v;
        return this;
    }

    public Scheme withOnPrimary(int v) {
        this.onPrimary = v;
        return this;
    }

    public Scheme withOnPrimaryContainer(int v) {
        this.onPrimaryContainer = v;
        return this;
    }

    public Scheme withOnSecondary(int v) {
        this.onSecondary = v;
        return this;
    }

    public Scheme withOnSecondaryContainer(int v) {
        this.onSecondaryContainer = v;
        return this;
    }

    public Scheme withOnSurface(int v) {
        this.onSurface = v;
        return this;
    }

    public Scheme withOnSurfaceVariant(int v) {
        this.onSurfaceVariant = v;
        return this;
    }

    public Scheme withOnTertiary(int v) {
        this.onTertiary = v;
        return this;
    }

    public Scheme withOnTertiaryContainer(int v) {
        this.onTertiaryContainer = v;
        return this;
    }

    public Scheme withOutline(int v) {
        this.outline = v;
        return this;
    }

    public Scheme withOutlineVariant(int v) {
        this.outlineVariant = v;
        return this;
    }

    public Scheme withPrimary(int v) {
        this.primary = v;
        return this;
    }

    public Scheme withPrimaryContainer(int v) {
        this.primaryContainer = v;
        return this;
    }

    public Scheme withScrim(int v) {
        this.scrim = v;
        return this;
    }

    public Scheme withSecondary(int v) {
        this.secondary = v;
        return this;
    }

    public Scheme withSecondaryContainer(int v) {
        this.secondaryContainer = v;
        return this;
    }

    public Scheme withShadow(int v) {
        this.shadow = v;
        return this;
    }

    public Scheme withSurface(int v) {
        this.surface = v;
        return this;
    }

    public Scheme withSurfaceVariant(int v) {
        this.surfaceVariant = v;
        return this;
    }

    public Scheme withTertiary(int v) {
        this.tertiary = v;
        return this;
    }

    public Scheme withTertiaryContainer(int v) {
        this.tertiaryContainer = v;
        return this;
    }
}

