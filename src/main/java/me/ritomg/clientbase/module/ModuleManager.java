package me.ritomg.clientbase.module;

import me.ritomg.clientbase.module.client.Gui;

import java.util.ArrayList;

public class ModuleManager {

    public ArrayList<Module> modules;

    public ModuleManager() {
        modules = new ArrayList<Module>();
    }

    public Module getModule(String name) {
        for (Module m : modules) {
            if (m.name.toLowerCase().equals(name.toLowerCase())) {
                return m;
            }
        }return null;
    }

    public Module getModule(Class<? extends Module> clazz) {
        for (Module m : modules) {
            if (m.getClass() == clazz)
                return m;
        }

        return null;
    }

    public ArrayList<Module> getModulesFromCategory(ModuleCategory category) {
        final ArrayList<Module> list = new ArrayList<>();
        for (Module m : modules) {
            if (m.category == category)
                list.add(m);
        }

        return list;
    }

}
