package me.ritomg.clientbase.settings.values;


import me.ritomg.clientbase.module.Module;
import me.ritomg.clientbase.settings.Setting;

import java.awt.*;

public class ColorSetting extends Setting<Color> implements com.lukflug.panelstudio.settings.ColorSetting {

    private boolean rainbow;

    public ColorSetting(String name, String Description, Module module, boolean rainbow, Color value) {
        super(value, name, Description, module);

        this.rainbow = rainbow;
    }

    @Override
    public Color getValue() {
        if (rainbow) return Color.getHSBColor((System.currentTimeMillis() % (360 * 32)) / (360f * 32), 1, 1);
        else return super.getValue();
    }

    public int toInteger() {
        return getValue().getRGB() & 0xFFFFFF + (this.rainbow ? 1 : 0) * 0x1000000;
    }

    public void fromInteger(int number) {
        this.rainbow = ((number & 0x1000000) != 0);

        super.setValue(this.rainbow ? Color.getHSBColor((System.currentTimeMillis() % (360 * 32)) / (360f * 32), 1, 1) : new Color(number & 0xFFFFFF));
    }

    public void setValue(int r,int g, int b, int a) {
        super.setValue(new Color(r,g,b,a));
    }

    public void setValue(int r,int g, int b) {
        super.setValue(new Color(r,g,b));
    }

    @Override
    public Color getColor() {
        return super.getValue();
    }

    @Override
    public boolean getRainbow() {
        return this.rainbow;
    }

    @Override
    public void setRainbow(boolean rainbow) {
        this.rainbow = rainbow;
    }
}