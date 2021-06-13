package me.ritomg.clientbase.event;

import net.minecraftforge.fml.common.eventhandler.Event;

public class KeyPressedEvent extends Event {

    private final int key;

    public KeyPressedEvent(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

}
