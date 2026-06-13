package karcianki;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DealerTest {

    private Dealer dealer;
    private Deck deck;

    @BeforeEach
    void setUp() {
        deck = new Deck();
        dealer = new Dealer();
    }

    @Test
    void dealer_stops_at_17_or_more() {
        dealer.playTurn(deck);
        assertTrue(dealer.countScore() >= 17);
    }

    @Test
    void dealer_does_not_draw_if_already_at_17() {
        // ustaw ręcznie rękę z wynikiem 17
        dealer.getHand().setCards(new java.util.ArrayList<>(java.util.List.of(
                new Card(Rank.TEN, Suit.HEARTS),
                new Card(Rank.SEVEN, Suit.HEARTS)
        )));
        int handSizeBefore = dealer.getHand().getCards().size();
        dealer.playTurn(deck);
        assertEquals(handSizeBefore, dealer.getHand().getCards().size());
    }

    @Test
    void dealer_draws_if_score_below_17() {
        dealer.getHand().setCards(new java.util.ArrayList<>(java.util.List.of(
                new Card(Rank.TWO, Suit.HEARTS),
                new Card(Rank.THREE, Suit.HEARTS)
        )));
        dealer.playTurn(deck);
        assertTrue(dealer.countScore() >= 17);
    }
}