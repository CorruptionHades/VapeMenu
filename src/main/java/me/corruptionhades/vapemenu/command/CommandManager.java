package me.corruptionhades.vapemenu.command;

import java.util.ArrayList;
import java.util.List;

public class CommandManager {

    public static CommandManager INSTANCE = new CommandManager();
    private List<Command> cmds = new ArrayList<>();

    public CommandManager() {
        init();
    }

    private void init() {
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
