package me.ritomg.clientbase.settings.values;

import com.lukflug.panelstudio.settings.NumberSetting;
import me.ritomg.clientbase.module.Module;
import me.ritomg.clientbase.settings.Setting;
import sun.security.krb5.internal.crypto.Des;

public class DoubleSetting extends Setting<Double> implements NumberSetting {

    private final double min;
    private final double max;

    public DoubleSetting(String name, String Description, Module module, double value, double min, double max) {
        super(value, name, Description, module);

        this.min = min;
        this.max = max;
    }

    public double getMin() {
        return this.min;
    }

    public double getMax() {
        return this.max;
    }

    @Override
    public double getNumber() {
        return getValue();
    }

    @Override
    public void setNumber(double value) {
        setValue(value);
    }

    @Override
    public double getMaximumValue() {
        return getMax();
    }

    @Override
    public double getMinimumValue() {
        return getMin();
    }

    @Override
    public int getPrecision() {
        return 2;
    }
}