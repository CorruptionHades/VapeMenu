package me.corruptionhades.vapemenu.event.impl;

import me.corruptionhades.vapemenu.event.Event;
import net.minecraft.text.Text;

public class ChatMessageReceivedEvent extends Event {

    private Text message;

    public ChatMessageReceivedEvent(Text message) {
        this.message = message;
    }

    public Text getMessage() {
        return message;
    }

    public void setMessage(Text message) {
        this.message = message;
    }
}
