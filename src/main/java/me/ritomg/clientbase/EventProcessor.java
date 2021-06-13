package me.ritomg.clientbase;

import me.ritomg.clientbase.event.KeyPressedEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class EventProcessor {

    public static final EventProcessor INSTANCE = new EventProcessor();

    public void init() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onKeyInput(final InputEvent.KeyInputEvent event) {
        final int eventKey = Keyboard.getEventKey();

        if (eventKey != Keyboard.KEY_NONE && Keyboard.isKeyDown(eventKey) && !Keyboard.isRepeatEvent()) {
            System.out.println("Key pressed: " + Keyboard.getKeyName(eventKey));
            MinecraftForge.EVENT_BUS.post(new KeyPressedEvent(eventKey));
        }
    }

    @SubscribeEvent
    public void onMouseInput(InputEvent.MouseInputEvent event) {
        if (Mouse.getEventButtonState()) {
            MinecraftForge.EVENT_BUS.post(event);
        }
    }

    private EventProcessor() {}

}
