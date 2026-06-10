package karcianki;

import java.util.Scanner;

public class Blackjack {

    public static void main(String[] args) {
        Deck deck;
        Player player = new Player();
        Dealer dealer = new Dealer();
        Scanner scanner = new Scanner(System.in);
        String command;
        while (true)
        {
            deck = new Deck();
            deck.shuffle();
            dealer.hit(deck);
            System.out.println("Dealer Hand (Second Card Hidden)");
            dealer.getHand().showHand();
            dealer.hit(deck);
            player.hit(deck);
            player.hit(deck);
            System.out.println("Your Hand");
            player.turn(scanner, deck);
            dealer.playTurn(deck);



        }
    }




}
