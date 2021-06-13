package me.ritomg.clientbase.module;

import com.lukflug.panelstudio.settings.KeybindSetting;
import com.lukflug.panelstudio.settings.Toggleable;
import me.ritomg.clientbase.settings.SettingsManager;
import me.ritomg.clientbase.settings.values.*;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import org.lwjgl.input.Keyboard;

import java.awt.*;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Calendar;
import java.util.List;

public class Module implements Toggleable, KeybindSetting {

    public final Minecraft mc = Minecraft.getMinecraft();

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    public @interface newModule {
        String name();
        String Description();
        int Priority() default 0;
        ModuleCategory category();
        int Keybind() default Keyboard.KEY_NONE;
    }

    public newModule getModuleClass() {
        return getClass().getAnnotation(newModule.class);
    }

    public String name = getModuleClass().name();
    public String Description = getModuleClass().Description();
    public int Priority = getModuleClass().Priority();
    public int Keybind = getModuleClass().Keybind();
    public ModuleCategory category = getModuleClass().category();
    public boolean Enabled;

    public void onEnable(){}
    public void onDisable(){}

    public void enable() {
        onEnable();
        this.Enabled = true;
        MinecraftForge.EVENT_BUS.register(this);
    }

    public void disable() {
        onDisable();
        this.Enabled = false;
        MinecraftForge.EVENT_BUS.unregister(this);
    }

    @Override
    public int getKey() {
        return Keybind;
    }

    @Override
    public void setKey(int key) {
        Keybind = key;
    }

    @Override
    public String getKeyName() {
        if (this.Keybind <= 0 || this.Keybind > 255) {
            return "NONE";
        } else {
            return Keyboard.getKeyName(this.Keybind);
        }
    }

    @Override
    public void toggle() {
        if (Enabled) {
            disable();
        } else if (!Enabled) {
            enable();
        }
    }


    protected DoubleSetting registerDouble(String name, String description, double value, double min, double max) {
        DoubleSetting doubleSetting = new DoubleSetting(name, description,this, value, min, max);
        SettingsManager.addSetting(doubleSetting);
        return doubleSetting;
    }

    protected BooleanSetting registerBoolean(String name, String description, boolean value) {
        BooleanSetting booleanSetting = new BooleanSetting(name, description, this, value);
        SettingsManager.addSetting(booleanSetting);
        return booleanSetting;
    }

    protected ModeSetting registerMode(String name, String description, List<String> modes, String value) {
        ModeSetting modeSetting = new ModeSetting(name, description, this, value, modes);
        SettingsManager.addSetting(modeSetting);
        return modeSetting;
    }

    protected ColorSetting registerColor(String name,String desc, Color color) {
        ColorSetting colorSetting = new ColorSetting(name, desc, this, false, color);
        SettingsManager.addSetting(colorSetting);
        return colorSetting;
    }

    protected ColorSetting registerColor(String name, String desc) {
        return registerColor(name, desc,  new Color(90, 145, 240));
    }

    @Override
    public boolean isOn() {
        return this.Enabled;
    }
}
