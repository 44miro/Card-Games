package karcianki;

public class Dealer extends Player {

    public void playTurn(Deck deck) {
        while (countScore() < 17) {
            getHand().addCards(deck.drawCard());
        }
    }
}