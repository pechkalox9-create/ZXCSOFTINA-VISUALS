package dev.zxcsoftina.visuals;

import dev.zxcsoftina.visuals.module.ClientModule;
import dev.zxcsoftina.visuals.module.ModuleCategory;
import dev.zxcsoftina.visuals.module.ModuleManager;
import dev.zxcsoftina.visuals.ui.ClickGuiScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import org.lwjgl.glfw.GLFW;

public final class ZxcsoftinaClient implements ClientModInitializer {
    public static final String MOD_ID = "zxcsoftina_visuals";
    public static final ModuleManager MODULES = new ModuleManager();

    private static KeyMapping clickGuiKey;

    @Override
    public void onInitializeClient() {
        MODULES.register(new ClientModule("HUD Info", "Displays basic client information.", ModuleCategory.HUD) { });
        MODULES.register(new ClientModule("Coordinates", "Coordinate display placeholder.", ModuleCategory.HUD) { });
        MODULES.register(new ClientModule("Custom Crosshair", "Crosshair customization placeholder.", ModuleCategory.VISUALS) { });
        MODULES.register(new ClientModule("Clean Screenshots", "Screenshot utility placeholder.", ModuleCategory.UTILITY) { });

        clickGuiKey = KeyBindingHelper.registerKeyBinding(new KeyMapping(
                "key.zxcsoftina_visuals.click_gui",
                GLFW.GLFW_KEY_RIGHT_SHIFT,
                KeyMapping.Category.MISC
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (clickGuiKey.consumeClick()) {
                Minecraft.getInstance().setScreen(new ClickGuiScreen());
            }
        });
    }
}
