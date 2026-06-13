package karcianki;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HandTest {
    @Test
    public void handShouldReturnListIfNotEmpty()
    {
        Hand hand = new Hand();
        Deck deck = new Deck();
        hand.addCards(deck.drawCard());
        assertNotNull(hand.getCards());
    }
}
