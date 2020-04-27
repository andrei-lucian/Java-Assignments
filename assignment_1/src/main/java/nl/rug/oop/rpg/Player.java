package nl.rug.oop.rpg;

public class Player implements Attackable{

    private String name;
    private int damage;
    private int health = 100;
    private Room currentRoom;
    private Room previousRoom;
    private boolean isDead = false;

    public Player(String name, int damage){
        this.name = name;
        this.damage = damage;
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

    public int getHealth(){
        return this.health;
    }

    //take damage
    @Override
    public void takeDamage(int damage) {

        if (this.health-damage > 0){
            this.health -= damage;

            System.out.println("You: 'Rude...' \nYour health is: " + this.health);

            //low health warning
            if (this.health < 50){
                System.out.println("Your health is critically low, look for a health wizard!");
            }
        }
        else {
            this.isDead = true;
        }
    }

    public boolean isDead(){
        return this.isDead;
    }

    //deal damage
    @Override
    public int dealDamage() {
        return this.damage;
    }
}
