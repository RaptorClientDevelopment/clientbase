package me.ritomg.clientbase.settings.values;

import com.lukflug.panelstudio.settings.EnumSetting;
import me.ritomg.clientbase.module.Module;
import me.ritomg.clientbase.settings.Setting;

import java.util.List;

public class ModeSetting extends Setting<String> implements EnumSetting {

    private final List<String> modes;

    public ModeSetting(String name, String Description, Module module, String value, List<String> modes) {
        super(value, name, Description,  module);

        this.modes = modes;
    }

    public List<String> getModes() {
        return this.modes;
    }

    @Override
    public void increment() {
        int modeIndex = modes.indexOf(getValue());
        modeIndex = (modeIndex + 1) % modes.size();
        setValue(modes.get(modeIndex));
    }

    @Override
    public String getValueName() {
        return getValue();
    }
}