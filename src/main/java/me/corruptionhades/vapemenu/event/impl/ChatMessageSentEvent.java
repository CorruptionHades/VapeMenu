package me.corruptionhades.vapemenu.event.impl;

import me.corruptionhades.vapemenu.event.Event;
import net.minecraft.text.Text;

public class ChatMessageSentEvent extends Event {

    private String message;

    public ChatMessageSentEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
