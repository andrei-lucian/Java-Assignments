package nl.rug.oop.rpg.util;

import nl.rug.oop.rpg.Main;

import java.util.InputMismatchException;

public interface CatchNonInts {

    static int inputOption(){
        boolean right = true;
        int option = 0;
        while(right){
            try {
                option = Main.scanner.nextInt();
                right = false;
            }
            catch (InputMismatchException e) {
                System.out.println("Not an option, try again");
                Main.scanner.next();
                right = true;
            }
        }
        return option;
    }
}
