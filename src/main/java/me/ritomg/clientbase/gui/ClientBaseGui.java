package me.ritomg.clientbase.gui;

import com.lukflug.panelstudio.CollapsibleContainer;
import com.lukflug.panelstudio.DraggableContainer;
import com.lukflug.panelstudio.SettingsAnimation;
import com.lukflug.panelstudio.hud.HUDClickGUI;
import com.lukflug.panelstudio.mc12.MinecraftHUDGUI;
import com.lukflug.panelstudio.settings.*;
import com.lukflug.panelstudio.theme.*;
import me.ritomg.clientbase.Main;
import me.ritomg.clientbase.gui.components.KeyBind;
import me.ritomg.clientbase.module.Module;
import me.ritomg.clientbase.module.ModuleCategory;
import me.ritomg.clientbase.module.ModuleManager;
import me.ritomg.clientbase.module.client.Gui;
import me.ritomg.clientbase.settings.Setting;
import me.ritomg.clientbase.settings.SettingsManager;
import me.ritomg.clientbase.settings.values.*;
import me.ritomg.clientbase.settings.values.ColorSetting;
import net.minecraft.client.Minecraft;

import java.awt.*;

public class ClientBaseGui extends MinecraftHUDGUI {

    public static ClientBaseGui instance = new ClientBaseGui();

    private static final int WIDTH = 100, HEIGHT = 12;

    Gui clickGuiModule = (Gui) Main.modulemanager.getModule(Gui.class);

    private final HUDClickGUI gui;
    private final GUIInterface Interface;
    private final Theme theme;

    public ClientBaseGui() {

        Interface = new GUIInterface(true) {
            @Override
            protected String getResourcePrefix() {
                return Main.MOD_NAME + "Gui/";
            }

            @Override
            public void drawString(Point pos, String s, Color c) {
                end();
                Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(s, pos.x+2, pos.y+2,c.getRGB());
                begin();
            }

            @Override
            public int getFontWidth(String s) {
                return mc.fontRenderer.getStringWidth(s) + 4;
            }

            @Override
            public int getFontHeight() {
                return mc.fontRenderer.FONT_HEIGHT + 4;
            }
        };

        gui = new HUDClickGUI(Interface, new MouseDescription(new Point(0,0)));

        theme = new GameSenseTheme(new SettingsColorScheme(
                clickGuiModule.enabledColor,
                clickGuiModule.backgroundColor,
                clickGuiModule.settingBackgroundColor,
                clickGuiModule.outlineColor,
                clickGuiModule.fontColor,
                clickGuiModule.opacity
        ), HEIGHT, 2, 5);

    }

    public void init() {
        int x = 10;



        for (final ModuleCategory category : ModuleCategory.values()) {
            final DraggableContainer panel = new DraggableContainer(category.toString(),null, theme.getPanelRenderer(), new SimpleToggleable(false), new SettingsAnimation(clickGuiModule.animationSpeed), null, new Point(x,10), WIDTH) {
                @Override
                protected int getScrollHeight(int childHeight) {
                    return Math.min(childHeight, Math.max(HEIGHT * 4, ClientBaseGui.this.height - getPosition(Interface).y - renderer.getHeight(open.getValue() != 0) - HEIGHT));
                }
            };

            x += 110;
            gui.addComponent(panel);

            for (Module module : Main.modulemanager.getModulesFromCategory(category)) {
                final CollapsibleContainer container = new CollapsibleContainer(module.name, module.Description, theme.getContainerRenderer(), new SimpleToggleable(false), new SettingsAnimation(clickGuiModule.animationSpeed), module);;
                panel.addComponent(container);

                for (Setting property : SettingsManager.getSettingsForModule(module)) {
                    if (property instanceof BooleanSetting) {
                        container.addComponent(new BooleanComponent(property.getName(), property.getDescription(), theme.getComponentRenderer(), (BooleanSetting) property));
                    } else if (property instanceof IntegerSetting) {
                        container.addComponent(new NumberComponent(property.getName(), property.getDescription(), theme.getComponentRenderer(), (IntegerSetting) property, ((IntegerSetting) property).getMin(), ((IntegerSetting) property).getMax()));
                    } else if (property instanceof DoubleSetting) {
                        container.addComponent(new NumberComponent(property.getName(), property.getDescription(), theme.getComponentRenderer(), (DoubleSetting) property, ((DoubleSetting) property).getMin(), ((DoubleSetting) property).getMax()));
                    } else if (property instanceof ModeSetting) {
                        container.addComponent(new EnumComponent(property.getName(), property.getDescription(), theme.getComponentRenderer(), (ModeSetting) property));
                    } else if (property instanceof ColorSetting) {
                        container.addComponent(new ColorComponent(property.getName(), property.getDescription(), theme.getContainerRenderer(), new SettingsAnimation(clickGuiModule.animationSpeed), theme.getComponentRenderer(), (ColorSetting) property, true, ((ColorSetting) property).getRainbow(), new SimpleToggleable(false)));
                    }
                }
                container.addComponent(new KeyBind(theme.getComponentRenderer(), module));
            }
        }
    }



    @Override
    protected HUDClickGUI getHUDGUI() {
        return gui;
    }

    @Override
    protected GUIInterface getInterface() {
        return Interface;
    }

    @Override
    protected int getScrollSpeed() {
        return clickGuiModule.scrollSpeed.getValue();
    }
}
