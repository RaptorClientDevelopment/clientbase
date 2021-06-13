package me.ritomg.clientbase.settings;


import me.ritomg.clientbase.module.Module;

public abstract class Setting<R> {

    private R value;
    private final String name;
    private final String configName;
    private final Module module;
    private final String Description;

    public Setting(R value, String name,String Description, Module module) {
        this.value = value;
        this.name = name;
        this.Description = Description;
        this.configName = name.replace(" ", "");
        this.module = module;
    }


    public R getValue() {
        return this.value;
    }

    public void setValue(R value) {
        this.value = value;
    }

    public String getName() {
        return this.name;
    }

    public String getConfigName() {
        return this.configName;
    }

    public Module getModule() {
        return this.module;
    }

    public String getDescription() {
        return Description;
    }
}
