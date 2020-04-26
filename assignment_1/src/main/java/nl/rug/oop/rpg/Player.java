package nl.rug.oop.rpg;

public class Player implements Attackable{

    private String name;
    private int damage;
    private int health;
    private Room currentRoom;
    private Room previousRoom;

    public Player(String name, int damage, int health){
        this.name = name;
        this.damage = damage;
        this.health = health;
    }

    //inspect the room that the player is currently in
    public void inspect(){
        currentRoom.inspect();
    }

    //set a player's current room
    public void setCurrentRoom(Room room){
        this.currentRoom = room;
    }

    //return a player's current room
    public Room getCurrentRoom(){
        return this.currentRoom;
    }

    //setter for the previous room
    public void setPreviousRoom(Room room){
        this.previousRoom = room;
    }

    //go back to the previous room
    public void goBack(){
        this.setCurrentRoom(previousRoom);
    }

    //take damage and deal damage
    @Override
    public void takeDamage(int damage) {
        this.health -= damage;
        System.out.println("Cunt... \nMy health is: " +
                this.health);
    }

    @Override
    public int dealDamage() {
        return this.damage;
    }
}
