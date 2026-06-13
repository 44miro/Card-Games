package karcianki;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MenuControllerTest {

    @Test
    public void shouldAcceptValidOptions() {
        MenuController menu = new MenuController();
        //1 blackjack, 2 poker, 0 wyjscie
        assertTrue(menu.isValidOption("1"), "Opcja 1 powinna być akceptowana");
        assertTrue(menu.isValidOption("2"), "Opcja 2 powinna być akceptowana");
        assertTrue(menu.isValidOption("0"), "Opcja 0 powinna być akceptowana");
    }

    @Test
    public void shouldRejectInvalidInput() {
        MenuController menu = new MenuController();
        //odrzucanie bledow
        assertFalse(menu.isValidOption("A"), "Litery powinny być odrzucane");
        assertFalse(menu.isValidOption(""), "Pusty ciąg znaków powinien być odrzucany");
        assertFalse(menu.isValidOption("   "), "Spacje powinny być odrzucane");
        assertFalse(menu.isValidOption("5"), "Opcje spoza zakresu powinny być odrzucane");
    }
}