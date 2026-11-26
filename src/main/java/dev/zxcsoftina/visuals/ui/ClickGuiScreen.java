package dev.zxcsoftina.visuals.ui;

import dev.zxcsoftina.visuals.ZxcsoftinaClient;
import dev.zxcsoftina.visuals.module.ClientModule;
import dev.zxcsoftina.visuals.module.ModuleCategory;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public final class ClickGuiScreen extends Screen {
    private static final int PANEL_W = 330;
    private static final int PANEL_H = 250;

    public ClickGuiScreen() {
        super(Component.literal("ZXCSOFTINA VISUALS"));
    }

    @Override
    protected void init() {
        int left = (width - PANEL_W) / 2;
        int top = (height - PANEL_H) / 2;
        int y = top + 58;

        for (ModuleCategory category : ModuleCategory.values()) {
            addRenderableWidget(Button.builder(
                    Component.literal(category.title()),
                    button -> rebuildCategory(category)
            ).bounds(left + 14, y, 96, 20).build());
            y += 24;
        }
        addModuleButtons();
    }

    private void rebuildCategory(ModuleCategory category) {
        clearWidgets();
        int left = (width - PANEL_W) / 2;
        int y = (height - PANEL_H) / 2 + 58;
        for (ClientModule module : ZxcsoftinaClient.MODULES.byCategory(category)) {
            addRenderableWidget(Button.builder(
                    Component.literal((module.enabled() ? "§aON  " : "§7OFF ") + module.name()),
                    button -> {
                        module.toggle(minecraft);
                        rebuildCategory(category);
                    }
            ).bounds(left + 132, y, 184, 20).build());
            y += 25;
        }
        addRenderableWidget(Button.builder(Component.literal("Back"), b -> rebuild()).bounds(left + 14, y, 96, 20).build());
    }

    private void addModuleButtons() {
        int left = (width - PANEL_W) / 2;
        int top = (height - PANEL_H) / 2;
        addRenderableWidget(Button.builder(Component.literal("Visual modules"), b -> rebuildCategory(ModuleCategory.VISUALS))
                .bounds(left + 132, top + 58, 184, 20).build());
        addRenderableWidget(Button.builder(Component.literal("HUD modules"), b -> rebuildCategory(ModuleCategory.HUD))
                .bounds(left + 132, top + 83, 184, 20).build());
        addRenderableWidget(Button.builder(Component.literal("Utility modules"), b -> rebuildCategory(ModuleCategory.UTILITY))
                .bounds(left + 132, top + 108, 184, 20).build());
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        renderBackground(graphics, mouseX, mouseY, partialTick);
        int left = (width - PANEL_W) / 2;
        int top = (height - PANEL_H) / 2;

        graphics.fill(0, 0, width, height, 0x99050012);
        graphics.fill(left, top, left + PANEL_W, top + PANEL_H, 0xF00D0B16);
        graphics.fill(left, top, left + PANEL_W, top + 3, 0xFF9B4DFF);
        graphics.fill(left + 10, top + 42, left + PANEL_W - 10, top + 43, 0xFF342044);

        graphics.drawString(font, "ZXCSOFTINA", left + 14, top + 14, 0xFFE9D8FF, false);
        graphics.drawString(font, "VISUALS  //  CLIENT", left + 112, top + 15, 0xFF55DDF5, false);
        graphics.drawString(font, "DARK NEON EDITION  •  1.21.11", left + 14, top + PANEL_H - 18, 0xFF9A8AA8, false);

        super.render(graphics, mouseX, mouseY, partialTick);
    }

    @Override
    public boolean isPauseScreen() { return false; }
}
