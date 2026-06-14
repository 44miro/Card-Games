package karcianki;

import java.util.ArrayList;
import java.util.Scanner;

public class Player {
    private Hand hand;
    private int chips;

    public Player() {
        this.chips = 0;
        this.hand = new Hand();
    }

    public Hand getHand() {
        return hand;
    }

    public void hit(Deck deck){
        hand.addCard(deck.drawCard());
    }

    public int countScore() {
        ArrayList<Card> cards = hand.getCards();
        int score = 0;
        cards.sort(Card::compareTo);

        for (Card card : cards) {
            switch (card.getRank()) {
                case TWO -> score += 2;
                case THREE -> score += 3;
                case FOUR -> score += 4;
                case FIVE -> score += 5;
                case SIX -> score += 6;
                case SEVEN -> score += 7;
                case EIGHT -> score += 8;
                case NINE -> score += 9;
                case TEN, JACK, QUEEN, KING -> score += 10;
                case ACE -> score += (score + 11 > 21) ? 1 : 11;
            }
        }
        return score;
    }

    public void turn(Scanner scanner, Deck deck) {
        String command;
        while (true) {
            System.out.println("Hit or Stand (H/S) Current Score: " + countScore());
            command = scanner.next();
            if (command.equalsIgnoreCase("H")) {
                hit(deck);
            } else {
                break;
            }
            hand.showHand();
            if (countScore() > 21) {
                break;
            }
        }
    }

    //logika zetonow
    public int getChips() {
        return chips;
    }

    public void addChips(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Nie mozna dodac ujemnej liczby zetonow");
        }
        this.chips += amount;
    }

    public int placeBet(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Zaklad musi byc wiekszy od zera");
        }
        if (amount > chips) {
            throw new IllegalArgumentException("Niewystarczajaca liczba zetonow! Posiadasz: " + chips);
        }

        this.chips -= amount;
        return amount;
    }

}
