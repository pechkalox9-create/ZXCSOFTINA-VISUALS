package dev.zxcsoftina.visuals;

import dev.zxcsoftina.visuals.module.ModuleManager;
import dev.zxcsoftina.visuals.ui.ClickGuiScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import org.lwjgl.glfw.GLFW;

public final class ZxcsoftinaClient implements ClientModInitializer {
    public static final String MOD_ID = "zxcsoftina_visuals";
    public static final ModuleManager MODULES = new ModuleManager();
    private static KeyMapping openGuiKey;

    @Override
    public void onInitializeClient() {
        MODULES.registerDefaults();
        openGuiKey = KeyBindingHelper.registerKeyBinding(new KeyMapping(
                "key.zxcsoftina_visuals.open_gui",
                GLFW.GLFW_KEY_RIGHT_SHIFT,
                KeyMapping.Category.MISC
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (openGuiKey.consumeClick()) {
                Minecraft.getInstance().setScreen(new ClickGuiScreen());
            }
            MODULES.tick(client);
        });
    }

    public static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }
}
