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
    //testy zetonow
    @Test
    void new_player_should_start_with_zero_chips() {
        assertEquals(0, player.getChips());
    }

    @Test
    void player_can_receive_chips() {
        player.addChips(1000);
        assertEquals(1000, player.getChips());
    }

    @Test
    void player_can_place_valid_bet_and_chips_are_deducted() {
        player.addChips(1000);
        int betAmount = player.placeBet(300);

        assertEquals(300, betAmount, "Metoda powinna zwrocic wartosc zakladu");
        assertEquals(700, player.getChips(), "Zetony powinny zostac odjete z konta gracza");
    }

    @Test
    void player_cannot_bet_more_chips_than_owned() {
        player.addChips(500);

        assertThrows(IllegalArgumentException.class, () -> {
            player.placeBet(600);
        });

        assertEquals(500, player.getChips());
    }

    @Test
    void player_cannot_bet_negative_amount() {
        assertThrows(IllegalArgumentException.class, () -> {
            player.placeBet(-50);
        });
    }
}