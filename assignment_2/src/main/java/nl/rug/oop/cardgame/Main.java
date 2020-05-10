package nl.rug.oop.cardgame;

import java.util.Scanner;

public class Main {

    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        /*for (Card card: Card.values()){
            System.out.println(card);
        }*/

        Deck deck = new Deck();
        deck.shuffle();
        for (Card card: deck.getCards()){
            System.out.println(card);
        }

        System.out.println("Top card is " + deck.draw());
    }
}