package dev.zxcsoftina.visuals.module;

public enum ModuleCategory {
    VISUALS("Visuals"),
    HUD("HUD"),
    UTILITY("Utility");

    private final String displayName;

    ModuleCategory(String displayName) {
        this.displayName = displayName;
    }

    public String displayName() {
        return displayName;
    }
}
