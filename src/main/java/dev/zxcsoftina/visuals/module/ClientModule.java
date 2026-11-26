package dev.zxcsoftina.visuals.module;

import net.minecraft.client.Minecraft;

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

    public final void toggle(Minecraft client) {
        setEnabled(!enabled, client);
    }

    public final void setEnabled(boolean value, Minecraft client) {
        if (enabled == value) return;
        enabled = value;
        if (value) onEnable(client); else onDisable(client);
    }

    public void tick(Minecraft client) {}
    protected void onEnable(Minecraft client) {}
    protected void onDisable(Minecraft client) {}

    public String name() { return name; }
    public String description() { return description; }
    public ModuleCategory category() { return category; }
    public boolean enabled() { return enabled; }
}
