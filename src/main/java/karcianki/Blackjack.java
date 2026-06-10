package karcianki;

import java.util.Scanner;

public class Blackjack {
    private final Player player;
    private final Dealer dealer;
    private final Scanner scanner;


    public Blackjack(Player player, Dealer dealer, Scanner scanner) {
        this.player = player;
        this.dealer = dealer;
        this.scanner = scanner;
    }

    public Blackjack() {
        this(new Player(), new Dealer(), new Scanner(System.in));
    }

    public void game() {
        do {
            Deck deck = new Deck();
            gameStart(deck, dealer, player);
            player.turn(scanner, deck);
            dealer.playTurn(deck);
            player.getHand().handReset();
            dealer.getHand().handReset();
            //Tutaj nalezy dodac weryfikacje wyniku
            System.out.println("Press Q to Quit game");
        } while (!scanner.next().equalsIgnoreCase("q"));
    }

    private void gameStart(Deck deck, Dealer dealer, Player player) {
        deck.shuffle();
        dealer.hit(deck);
        System.out.println("Dealer Hand (Second Card Hidden)");
        dealer.getHand().showHand();
        dealer.hit(deck);
        player.hit(deck);
        player.hit(deck);
        System.out.println("Your Hand");
        player.getHand().showHand();
    }


}
