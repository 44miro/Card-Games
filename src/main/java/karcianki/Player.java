package karcianki;

import java.util.ArrayList;

public class Player {
    private Hand hand;

    public  Player(){
        this.hand = new Hand();
    }

    public void hit(Deck deck){
        hand.addCards(deck.drawCard());
    }

    public Hand getHand() {
        return hand;
    }

    public int countScore() {
        ArrayList<Card> cards = hand.getCards();
        int score = 0;
        cards.sort(Card::compareTo);
        for (int i = 0; i < cards.size(); i++)
            switch (cards.get(i).getRank()) {
                case TWO -> score += 2;
                case THREE -> score += 3;
                case FOUR -> score += 4;
                case FIVE -> score += 5;
                case SIX -> score += 6;
                case SEVEN -> score += 7;
                case EIGHT -> score += 8;
                case NINE -> score += 9;
                case ACE -> score += (score + 11 > 21) ? 1 : 11;
                case TEN, JACK ,QUEEN ,KING ->score+=10;
            }

        return score;
    }

}
