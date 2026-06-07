package karcianki;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private List<Card> hand;

    public Hand(){
        hand = new ArrayList<Card>();
    }


    public void pull(Card c)
    {
        hand.add(c);
    }

    public List<Card> getHand(){
        if (hand.isEmpty())
            return null;
        return hand;
    }

}
