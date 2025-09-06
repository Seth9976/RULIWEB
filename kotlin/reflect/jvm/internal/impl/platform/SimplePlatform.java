package kotlin.reflect.jvm.internal.impl.platform;

public abstract class SimplePlatform {
    private final String platformName;
    private final TargetPlatformVersion targetPlatformVersion;

    public String getTargetName() {
        return this.getTargetPlatformVersion().getDescription();
    }

    public TargetPlatformVersion getTargetPlatformVersion() {
        return this.targetPlatformVersion;
    }

    @Override
    public String toString() {
        String s = this.getTargetName();
        return s.length() <= 0 ? this.platformName : this.platformName + " (" + s + ')';
    }
}

