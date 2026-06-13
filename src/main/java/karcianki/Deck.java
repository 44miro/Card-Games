package karcianki;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck {
    private final List<Card> cards;

    public Deck() {
        cards = new ArrayList<>();

        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(new Card(suit, rank));
            }
        }
    }

    public int getRemainingCardsCount() {
        return cards.size();
    }

    public Card drawCard(){
        if (cards.isEmpty()){
            throw new IllegalStateException("Talia jest pusta");
        }
        return cards.remove(cards.size() - 1);
    }

    public void shuffle(){
        Random random = new Random();
        for(int i = cards.size() - 1; i > 0; i--){
            int j = random.nextInt(i + 1);
            Card temp = cards.get(i);
            cards.set(i, cards.get(j));
            cards.set(j, temp);
        }
    }
}