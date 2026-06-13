package karcianki;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CardTest{
    @Test
    public void shouldCreateCardWithCorrectValues(){
        Card card = new Card(Rank.ACE, Suit.HEARTS);
        assertEquals(Suit.HEARTS, card.getSuit());
        assertEquals(Rank.ACE, card.getRank());
    }
}