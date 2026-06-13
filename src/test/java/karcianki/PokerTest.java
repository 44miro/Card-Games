package karcianki;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PokerTest {

    private Poker pokerGame;
    private Player player1;

    @BeforeEach
    void setUp() {
        pokerGame = new Poker();
        player1 = new Player();
        player1.addChips(1000);
    }

    @Test
    void pot_should_start_at_zero() {
        assertEquals(0, pokerGame.getPot());
    }

    @Test
    void should_add_valid_bet_to_the_pot() {
        int bet = player1.placeBet(200);
        pokerGame.addToPot(bet);

        assertEquals(200, pokerGame.getPot());
        assertEquals(800, player1.getChips());
    }
}
