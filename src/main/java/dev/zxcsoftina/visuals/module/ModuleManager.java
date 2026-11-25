package dev.zxcsoftina.visuals.module;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ModuleManager {
    private final List<ClientModule> modules = new ArrayList<>();

    public void register(ClientModule module) {
        modules.add(module);
    }

    public List<ClientModule> all() {
        return Collections.unmodifiableList(modules);
    }

    public List<ClientModule> byCategory(ModuleCategory category) {
        return modules.stream().filter(module -> module.category() == category).toList();
    }
}
