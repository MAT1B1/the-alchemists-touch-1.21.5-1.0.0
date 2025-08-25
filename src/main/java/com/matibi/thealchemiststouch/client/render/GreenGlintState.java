package com.matibi.thealchemiststouch.client.render;

public final class GreenGlintState {
    private static final ThreadLocal<Boolean> ENABLED = ThreadLocal.withInitial(() -> false);

    public static boolean isEnabled() { return ENABLED.get(); }
    public static void enable() { ENABLED.set(true); }
    public static void disable() { ENABLED.set(false); }
    private GreenGlintState() {}
}
