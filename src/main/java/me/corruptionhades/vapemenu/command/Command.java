package me.corruptionhades.vapemenu.command;

import me.corruptionhades.vapemenu.utils.ChatUtils;
import net.minecraft.client.MinecraftClient;

import java.util.Arrays;
import java.util.List;

public abstract class Command {

    private String name, description;
    private List<String> aliases;
    public ChatUtils chat = new ChatUtils();
    public MinecraftClient mc = MinecraftClient.getInstance();

    public Command(String name, String description, String...aliases) {
        this.name = name;
        this.description = description;
        this.aliases = Arrays.asList(aliases);
    }

    public abstract void onCmd(String message, String[] args);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getAliases() {
        return aliases;
    }

    public void setAliases(List<String> aliases) {
        this.aliases = aliases;
    }

    public boolean isCreative() {
        return mc.player.getAbilities().creativeMode;
    }

    public void creativeError() {
        chat.sendMsg("&cYou need to be in creative mode!");
    }

    public void genError(Exception e) {
        chat.sendMsg("&cSomething went wrong");
        chat.sendMsg(e.getMessage());
    }

    public double[] getCoords(String x, String y, String z) {

        double xCoord;
        double yCoord;
        double zCoord;

        if(x.equalsIgnoreCase("~")) xCoord = mc.player.getX();
        else xCoord = Double.parseDouble(x);
        if(y.equalsIgnoreCase("~")) yCoord = mc.player.getY();
        else yCoord = Double.parseDouble(y);
        if(z.equalsIgnoreCase("~")) zCoord = mc.player.getZ();
        else zCoord = Double.parseDouble(z);

        return new double[]{xCoord, yCoord, zCoord};
    }

    public double getX(String txt) {
        if(txt.equalsIgnoreCase("~")) return mc.player.getX();
        else return Double.parseDouble(txt);
    }

    public double getY(String txt) {
        if(txt.equalsIgnoreCase("~")) return mc.player.getY();
        else return Double.parseDouble(txt);
    }

    public double getZ(String txt) {
        if(txt.equalsIgnoreCase("~")) return mc.player.getZ();
        else return Double.parseDouble(txt);
    }

    public String coordsToText(double x, double y, double z) {
        return "Pos:[" + x + "," + y + "," + z + "]";
    }
}
