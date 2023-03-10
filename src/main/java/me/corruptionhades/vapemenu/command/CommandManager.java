package me.corruptionhades.vapemenu.command;

import me.corruptionhades.vapemenu.command.impl.TeleportCommand;

import java.util.ArrayList;
import java.util.List;

public class CommandManager {

    public static CommandManager INSTANCE = new CommandManager();
    private List<Command> cmds = new ArrayList<>();

    public CommandManager() {
        init();
    }

    private void init() {
        add(new TeleportCommand());
    }

    public void add(Command command) {
        if(!cmds.contains(command)) {
            cmds.add(command);
        }
    }

    public void remove(Command command){
        cmds.remove(command);
    }

    public List<Command> getCmds() {
        return cmds;
    }
}
