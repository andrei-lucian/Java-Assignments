package nl.rug.oop.cardgame.model.utils;
import nl.rug.oop.cardgame.model.elements.Card;
import java.util.ArrayList;

public interface ReverseArrayList {

    static ArrayList<Card> reverse(ArrayList<Card> list){
        int size = list.size();
        for (int i = 0; i < size / 2; i++) {
            final Card card = list.get(i);
            list.set(i, list.get(size - i - 1)); // swap
            list.set(size - i - 1, card); // swap
        }
        return list;
    }
}
