package karcianki;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    private Player player;
    private Hand hand;

    @BeforeEach
    void setUp() {
        player = new Player();
        hand = player.getHand();
    }

    private void setHand(Card... cards) {
        ArrayList<Card> cardList = new ArrayList<>();
        for (Card card : cards) cardList.add(card);
        hand.setCards(cardList);
    }

    private Card card(Rank rank) {
        return new Card(Suit.HEARTS, rank);
    }

    // --- testy ---

    @Test
    void ace_plus_ten_equals_21() {
        setHand(card(Rank.ACE), card(Rank.TEN));
        assertEquals(21, player.countScore());
    }

    @Test
    void two_aces_should_not_equal_22() {
        setHand(card(Rank.ACE), card(Rank.ACE));
        assertEquals(12, player.countScore());
    }

    @Test
    void ace_ten_seven_equals_18() {
        setHand(card(Rank.ACE), card(Rank.TEN), card(Rank.SEVEN));
        assertEquals(18, player.countScore());
    }

    @Test
    void two_seven_ace_equals_20() {
        setHand(card(Rank.TWO), card(Rank.SEVEN), card(Rank.ACE));
        assertEquals(20, player.countScore());
    }
}