package me.ritomg.clientbase.settings.values;

import com.lukflug.panelstudio.settings.Toggleable;
import me.ritomg.clientbase.module.Module;
import me.ritomg.clientbase.settings.Setting;

import javax.management.Descriptor;

public class BooleanSetting extends Setting<Boolean> implements Toggleable {

    public BooleanSetting(String name,String Description, Module module, boolean value) {
        super(value, name,Description, module);
    }

    @Override
    public void toggle() {
        setValue(!getValue());
    }

    @Override
    public boolean isOn() {
        return getValue();
    }
}