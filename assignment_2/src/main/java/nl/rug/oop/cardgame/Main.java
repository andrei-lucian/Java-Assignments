package nl.rug.oop.cardgame;

public class Main {

    public static void main(String[] args) {
        for (Card card: Card.values()){
            System.out.println(card);
        }

        Deck deck = new Deck();
        for (Card card: deck.getCards()){
            System.out.println(card);
        }
    }
}