package karcianki;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private List<Card> cards;

    public Hand(){
        cards = new ArrayList<Card>();
    }


    public void addCards(Card c)
    {
        cards.add(c);
    }

    public List<Card> getCards(){
        if (cards.isEmpty())
            return null;
        return cards;
    }

    public void setCards(List<Card> cards)
    {
        this.cards = cards;
    }
}
