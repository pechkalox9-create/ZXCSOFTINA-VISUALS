package dev.zxcsoftina.visuals.module;

public abstract class ClientModule {
    private final String name;
    private final String description;
    private final ModuleCategory category;
    private boolean enabled;

    protected ClientModule(String name, String description, ModuleCategory category) {
        this.name = name;
        this.description = description;
        this.category = category;
    }

    public final String name() { return name; }
    public final String description() { return description; }
    public final ModuleCategory category() { return category; }
    public final boolean isEnabled() { return enabled; }

    public final void setEnabled(boolean enabled) {
        if (this.enabled == enabled) return;
        this.enabled = enabled;
        if (enabled) onEnable();
        else onDisable();
    }

    public final void toggle() {
        setEnabled(!enabled);
    }

    protected void onEnable() { }
    protected void onDisable() { }
}
