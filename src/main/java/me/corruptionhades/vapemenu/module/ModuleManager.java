package me.corruptionhades.vapemenu.module;

import me.corruptionhades.vapemenu.module.impl.combat.Reach;
import me.corruptionhades.vapemenu.module.impl.misc.JoinExploit;
import me.corruptionhades.vapemenu.module.impl.misc.Plugins;
import me.corruptionhades.vapemenu.module.impl.movement.Fly;
import me.corruptionhades.vapemenu.module.impl.movement.Timer;
import me.corruptionhades.vapemenu.module.impl.render.Arraylist;

import java.util.ArrayList;
import java.util.List;

public class ModuleManager {

    private List<Module> modules = new ArrayList<>();

    public static final ModuleManager INSTANCE = new ModuleManager();

    public ModuleManager() {
        init();
    }

    private void init() {
        add(new Reach());
        add(new Plugins());
        add(new Fly());
        add(new Arraylist());
        add(new Timer());
        add(new JoinExploit());
    }

    public void add(Module m) {
        modules.add(m);
    }

    public void remove(Module m) {
        modules.remove(m);
    }

    public List<Module> getModules() {
        return modules;
    }

    public Module getModuleByName(String name){

        for(Module module : modules) {
            if(module.getName().equals(name)) return module;
        }

        return null;
    }

    public ArrayList<Module> getModulesByCategory(Category category) {
        ArrayList<Module> modules = new ArrayList<>();
        for(Module m : this.modules){
            if(m.getCategory().equals(category)){
                modules.add(m);
            }
        }
        return modules;
    }

    public Module getModuleByClass(Class<? extends Module> cls) {
        for (Module m : modules) {
            if (m.getClass() != cls) {
                continue;
            }
            return m;
        }
        return null;
    }

    public List<Module> getEnabledModules() {
        List<Module> enabled = new ArrayList<>();
        for(Module m : getModules()) {
            if(m.isEnabled()) {
                enabled.add(m);
            }
        }

        return enabled;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }
}
