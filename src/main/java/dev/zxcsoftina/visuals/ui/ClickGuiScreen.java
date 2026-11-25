package dev.zxcsoftina.visuals.ui;

import dev.zxcsoftina.visuals.ZxcsoftinaClient;
import dev.zxcsoftina.visuals.module.ClientModule;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public final class ClickGuiScreen extends Screen {
    public ClickGuiScreen() {
        super(Component.literal("ZXCSOFTINA VISUALS"));
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float delta) {
        graphics.fill(0, 0, width, height, 0xD9080B12);
        graphics.fill(24, 24, width - 24, height - 24, 0xF0121724);
        graphics.fill(24, 24, width - 24, 58, 0xFF171F30);
        graphics.fill(24, 58, width - 24, 60, 0xFF00E5FF);

        graphics.drawString(font, "ZXCSOFTINA VISUALS", 40, 37, 0xFF00E5FF, false);
        graphics.drawString(font, "RIGHT SHIFT  •  CLIENT MENU", width - 220, 38, 0xFF9AA8C0, false);

        int y = 82;
        for (ClientModule module : ZxcsoftinaClient.MODULES.all()) {
            int color = module.isEnabled() ? 0xFF00D7B0 : 0xFF293448;
            graphics.fill(40, y - 5, width - 40, y + 20, 0xFF1C2638);
            graphics.fill(40, y - 5, 44, y + 20, color);
            graphics.drawString(font, module.name(), 54, y + 2, 0xFFEAF2FF, false);
            graphics.drawString(font, module.isEnabled() ? "ON" : "OFF",
                    width - 78, y + 2, module.isEnabled() ? 0xFF00E5B0 : 0xFF8793A8, false);
            y += 34;
        }

        graphics.drawString(font, "Module toggles are being implemented.", 40, height - 48, 0xFF8793A8, false);
        super.render(graphics, mouseX, mouseY, delta);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
