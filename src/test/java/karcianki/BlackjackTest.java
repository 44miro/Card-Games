package karcianki;

import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class BlackjackTest {

    @Test
    void game_shouldExitOnQ() {
        Scanner fakeScanner = new Scanner("s\nq");
        Blackjack bj = new Blackjack(new Player(), new Dealer(), fakeScanner);
        assertDoesNotThrow(bj::game);
    }

    @Test
    void game_shouldExitOnUppercaseQ() {
        Scanner fakeScanner = new Scanner("s\nQ");
        Blackjack bj = new Blackjack(new Player(), new Dealer(), fakeScanner);
        assertDoesNotThrow(bj::game);
    }
}
