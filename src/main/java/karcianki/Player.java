package karcianki;

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
}
