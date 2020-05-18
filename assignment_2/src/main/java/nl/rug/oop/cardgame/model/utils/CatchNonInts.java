package nl.rug.oop.cardgame.model.utils;

import nl.rug.oop.cardgame.model.Main;
import java.util.InputMismatchException;

/** Interface for scanning integers and catching InputMismatchExceptions */
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
