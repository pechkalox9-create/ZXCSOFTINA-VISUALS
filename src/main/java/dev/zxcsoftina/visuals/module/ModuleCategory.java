package dev.zxcsoftina.visuals.module;

public enum ModuleCategory {
    VISUALS("Visuals"),
    HUD("HUD"),
    UTILITY("Utility");

    private final String title;
    ModuleCategory(String title) { this.title = title; }
    public String title() { return title; }
}
