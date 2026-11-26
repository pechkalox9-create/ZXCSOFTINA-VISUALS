package dev.zxcsoftina.visuals.module;

import net.minecraft.client.Minecraft;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ModuleManager {
    private final List<ClientModule> modules = new ArrayList<>();

    public void registerDefaults() {
        register(new ClientModule("Fullbright", "Raises the client gamma for visibility.", ModuleCategory.VISUALS) {
            private double previousGamma;
            @Override protected void onEnable(Minecraft client) {
                if (client.options != null) {
                    previousGamma = client.options.gamma().get();
                    client.options.gamma().set(10.0);
                }
            }
            @Override protected void onDisable(Minecraft client) {
                if (client.options != null) client.options.gamma().set(previousGamma);
            }
        });
        register(new ClientModule("HUD Info", "Reserved for the compact information HUD.", ModuleCategory.HUD) {});
        register(new ClientModule("Coordinates", "Reserved for the coordinates HUD widget.", ModuleCategory.HUD) {});
        register(new ClientModule("Custom Crosshair", "Reserved for the crosshair editor.", ModuleCategory.VISUALS) {});
        register(new ClientModule("Clean Screenshots", "Hides interface overlays when implemented.", ModuleCategory.UTILITY) {});
    }

    public void register(ClientModule module) { modules.add(module); }
    public List<ClientModule> all() { return Collections.unmodifiableList(modules); }
    public List<ClientModule> byCategory(ModuleCategory category) {
        return modules.stream().filter(m -> m.category() == category).toList();
    }
    public void tick(Minecraft client) {
        for (ClientModule module : modules) if (module.enabled()) module.tick(client);
    }
}
