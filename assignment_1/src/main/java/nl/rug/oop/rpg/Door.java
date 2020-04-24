package nl.rug.oop.rpg;
import java.util.ArrayList;

public class Door implements Inspectable {

    private String description;

    public Door(String description){
        this.description = description;
    }

    public void inspect(){
        System.out.println(description);
    }
}