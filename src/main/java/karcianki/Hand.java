package karcianki;

import java.util.ArrayList;

public class Hand {
    private ArrayList<Card> cards;

    public Hand(){
        cards = new ArrayList<Card>();
    }


    public void addCards(Card c)
    {
        cards.add(c);
    }

    public ArrayList<Card> getCards(){
        if (cards.isEmpty())
            return null;
        return cards;
    }

    public void setCards(ArrayList<Card> cards)
    {
        this.cards = cards;
    }
}
