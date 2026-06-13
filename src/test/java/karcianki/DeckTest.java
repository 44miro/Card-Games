package karcianki;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DeckTest {
    @Test
    public void shouldContain52CardsWhenCreated(){
        Deck deck = new Deck();
        assertEquals(52, deck.getRemainingCardsCount(), "talia powinna miec 52 karty po utworzeniu");
    }

    @Test
    public void shouldDrawCardAndDecreaseDeckSize(){
        Deck deck = new Deck();
        Card drawnCard = deck.drawCard();

        assertNotNull(drawnCard, "Wyciagnieta karta nie moze byc nullem");
        assertEquals(51, deck.getRemainingCardsCount(), "Po wyciagnieciu karty w talii powinno zostac 51 kart");
    }

    @Test
    public void shouldShuffleCards(){
        Deck deck1 = new Deck();
        Deck deck2 = new Deck();

        deck1.shuffle();
        boolean isOrderDifferent = false;

        for(int i = 0; i < 52; i++){
            Card card1 = deck1.drawCard();
            Card card2 = deck2.drawCard();

            if(card1.getSuit() != card2.getSuit() || card1.getRank() != card2.getRank()){
                isOrderDifferent = true;
                break;
            }
        }
        assertTrue(isOrderDifferent, "Karty w talii powinny zmienic kolejnosc po tasowaniu");
    }
}
