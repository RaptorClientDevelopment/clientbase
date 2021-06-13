package me.ritomg.clientbase.module.client;

import me.ritomg.clientbase.module.Module;
import me.ritomg.clientbase.module.ModuleCategory;
import me.ritomg.clientbase.settings.values.BooleanSetting;
import me.ritomg.clientbase.settings.values.ColorSetting;
import me.ritomg.clientbase.settings.values.IntegerSetting;
import me.ritomg.clientbase.settings.values.ModeSetting;

import java.awt.*;
import java.util.Arrays;

@Module.newModule(name = "ClickGui", Description = "see gui", category = ModuleCategory.TestCategory)
public class Gui extends Module {

    public IntegerSetting opacity = new IntegerSetting("Opacity","not now" ,this,150, 50, 255);
    public IntegerSetting scrollSpeed = new IntegerSetting("Scroll Speed", "not now1", this,0, 1, 20);
    public ColorSetting outlineColor = registerColor("Outline", "not", new Color(255, 0, 0, 255));
    public ColorSetting enabledColor = registerColor("Enabled", "aa", new Color(255, 0, 0, 255));
    public ColorSetting backgroundColor = registerColor("Background","aa", new Color(0, 0, 0, 255));
    public ColorSetting  settingBackgroundColor = registerColor("Setting", "", new Color(30, 30, 30, 255));
    public ColorSetting fontColor = registerColor("Font", "",new Color(255, 255, 255, 255));
    public IntegerSetting animationSpeed = new IntegerSetting("Animation Speed", "", this, 200, 0, 1000);
    public ModeSetting scrolling = registerMode("Scrolling","", Arrays.asList("Screen", "Container"), "Screen");
    public BooleanSetting showHUD = registerBoolean("Show HUD Panels", "", false);

}
