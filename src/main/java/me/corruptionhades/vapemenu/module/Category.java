package me.corruptionhades.vapemenu.module;

public enum Category {
    COMBAT("Combat"),
    MOVEMENT("Movement"),
    PLAYER("Player"),
    MISC("Misc"),
    HIDDEN("Hidden");

    public String name;

    Category(String name) {
        this.name = name;
    }
}
