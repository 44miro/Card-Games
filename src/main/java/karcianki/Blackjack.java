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

            int playerScore = player.countScore();
            int dealerScore = dealer.countScore();

            GameResult result = determineWinner(playerScore, dealerScore);

            System.out.println("\n[WYNIK] Gracz: " + playerScore + " | Krupier: " + dealerScore);
            System.out.println("[WERDYKT] " + result);

            player.getHand().handReset();
            dealer.getHand().handReset();

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

    private GameResult determineWinner(int playerScore, int dealerScore) {
        if (playerScore > 21) {
            return GameResult.LOSE;
        }
        if (dealerScore > 21) {
            return GameResult.WIN;
        }
        if (playerScore > dealerScore) {
            return GameResult.WIN;
        } else if (playerScore < dealerScore) {
            return GameResult.LOSE;
        }
        return GameResult.DRAW;
    }
}