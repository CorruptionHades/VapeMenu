package me.corruptionhades.vapemenu.event.impl;

import me.corruptionhades.vapemenu.event.Event;
import net.minecraft.network.message.ChatMessageSigner;
import net.minecraft.text.Text;

public class ChatMessageSentEvent extends Event {

    private ChatMessageSigner signer;
    private String message;
    private Text preview;

    public ChatMessageSentEvent(ChatMessageSigner signer, String message, Text preview) {
        this.signer = signer;
        this.message = message;
        this.preview = preview;
    }

    public ChatMessageSigner getSigner() {
        return signer;
    }

    public void setSigner(ChatMessageSigner signer) {
        this.signer = signer;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Text getPreview() {
        return preview;
    }

    public void setPreview(Text preview) {
        this.preview = preview;
    }
}
