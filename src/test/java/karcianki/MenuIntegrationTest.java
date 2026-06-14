package karcianki;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;

public class MenuIntegrationTest {

    @Test
    public void shouldNavigateToBlackjackAndReturnToMenu() {
        // 1 -> Wybór Blackjacka w Menu
        // S -> Wybór "Stand" (pas) w turze gracza Blackjacka
        // q -> Wybór "Quit" po zakończeniu rozdania Blackjacka
        // 0 -> Wybór "Wyjście" z głównego Menu
        String simulatedInput = "1\nS\nq\n0\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        Scanner scanner = new Scanner(inputStream);

        Menu menu = new Menu(scanner);
        assertDoesNotThrow(menu::start, "Aplikacja powinna płynnie przejść do Blackjacka, wyjść do menu i zakończyć działanie.");
    }

    @Test
    public void shouldNavigateToPokerAndReturnToMenu() {
        // 2 -> Wybór Pokera w Menu
        // 2 -> Podanie liczby graczy w sesji pokera
        // 3 -> Pierwszy gracz pasuje (Fold) w licytacji Pre-flop (co kończy rozdanie przy 2 graczach)
        // quit -> Rezygnacja z kolejnej rundy pokera i powrót do menu
        // 0 -> Wybór "Wyjście" z głównego Menu
        String simulatedInput = "2\n2\n3\nquit\n0\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        Scanner scanner = new Scanner(inputStream);

        Menu menu = new Menu(scanner);

        assertDoesNotThrow(menu::start, "Aplikacja powinna płynnie przejść do Pokera, wyjść do menu i zakończyć działanie.");
    }
}