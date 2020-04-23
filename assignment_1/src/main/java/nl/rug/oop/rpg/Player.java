package nl.rug.oop.rpg;

public class Player extends Room {

    protected String name;
    public Room room;

    private Player(String name, Room room){
        this.name = name;
    }
}
