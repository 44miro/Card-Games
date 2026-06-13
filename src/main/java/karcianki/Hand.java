package karcianki;

import java.util.ArrayList;

public class Hand {
    private ArrayList<Card> cards;

    public Hand() {
        this.cards = new ArrayList<>();
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void showHand() {
        for (Card card : cards) {
            System.out.println(card);
        }
    }

    public void handReset() {
        cards = new ArrayList<>();
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }
}