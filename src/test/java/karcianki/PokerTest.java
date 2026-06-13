package karcianki;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

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

    @Test
    public void shouldDetectPair() {
        List<Card> hand = Arrays.asList(
                new Card(Suit.HEARTS, Rank.TWO),
                new Card(Suit.SPADES, Rank.TWO),
                new Card(Suit.CLUBS, Rank.FIVE),
                new Card(Suit.DIAMONDS, Rank.NINE),
                new Card(Suit.HEARTS, Rank.KING)
        );
        Poker poker = new Poker();
        assertTrue(poker.hasPair(hand));
    }

    @Test
    public void shouldDetectTwoPairs() {
        List<Card> hand = Arrays.asList(
                new Card(Suit.HEARTS, Rank.TWO),
                new Card(Suit.SPADES, Rank.TWO),
                new Card(Suit.CLUBS, Rank.FIVE),
                new Card(Suit.DIAMONDS, Rank.FIVE),
                new Card(Suit.HEARTS, Rank.KING)
        );
        Poker poker = new Poker();
        assertTrue(poker.hasTwoPairs(hand));
    }

    @Test
    public void shouldDetectThreeOfAKind() {
        List<Card> hand = Arrays.asList(
                new Card(Suit.HEARTS, Rank.SEVEN),
                new Card(Suit.SPADES, Rank.SEVEN),
                new Card(Suit.CLUBS, Rank.SEVEN),
                new Card(Suit.DIAMONDS, Rank.NINE),
                new Card(Suit.HEARTS, Rank.KING)
        );
        Poker poker = new Poker();
        assertTrue(poker.hasThreeOfAKind(hand));
    }

    @Test
    public void shouldDetectFlush() {
        List<Card> hand = Arrays.asList(
                new Card(Suit.HEARTS, Rank.TWO),
                new Card(Suit.HEARTS, Rank.FIVE),
                new Card(Suit.HEARTS, Rank.NINE),
                new Card(Suit.HEARTS, Rank.JACK),
                new Card(Suit.HEARTS, Rank.KING)
        );
        Poker poker = new Poker();
        assertTrue(poker.hasFlush(hand));
    }

    @Test
    public void shouldDetectStraight() {
        List<Card> hand = Arrays.asList(
                new Card(Suit.HEARTS, Rank.FIVE),
                new Card(Suit.SPADES, Rank.SIX),
                new Card(Suit.CLUBS, Rank.SEVEN),
                new Card(Suit.DIAMONDS, Rank.EIGHT),
                new Card(Suit.HEARTS, Rank.NINE)
        );
        Poker poker = new Poker();
        assertTrue(poker.hasStraight(hand));
    }

    @Test
    public void shouldDetectFullHouse() {
        List<Card> hand = Arrays.asList(
                new Card(Suit.HEARTS, Rank.TEN),
                new Card(Suit.SPADES, Rank.TEN),
                new Card(Suit.CLUBS, Rank.TEN),
                new Card(Suit.DIAMONDS, Rank.KING),
                new Card(Suit.HEARTS, Rank.KING)
        );
        Poker poker = new Poker();
        assertTrue(poker.hasFullHouse(hand));
    }

    @Test
    public void shouldDetectFourOfAKind() {
        List<Card> hand = Arrays.asList(
                new Card(Suit.HEARTS, Rank.ACE),
                new Card(Suit.SPADES, Rank.ACE),
                new Card(Suit.CLUBS, Rank.ACE),
                new Card(Suit.DIAMONDS, Rank.ACE),
                new Card(Suit.HEARTS, Rank.NINE)
        );
        Poker poker = new Poker();
        assertTrue(poker.hasFourOfAKind(hand));
    }

    @Test
    public void shouldDetectStraightFlush() {
        List<Card> hand = Arrays.asList(
                new Card(Suit.SPADES, Rank.NINE),
                new Card(Suit.SPADES, Rank.TEN),
                new Card(Suit.SPADES, Rank.JACK),
                new Card(Suit.SPADES, Rank.QUEEN),
                new Card(Suit.SPADES, Rank.KING)
        );
        Poker poker = new Poker();
        assertTrue(poker.hasStraightFlush(hand));
    }

    @Test
    public void shouldDetectRoyalFlush() {
        List<Card> hand = Arrays.asList(
                new Card(Suit.DIAMONDS, Rank.TEN),
                new Card(Suit.DIAMONDS, Rank.JACK),
                new Card(Suit.DIAMONDS, Rank.QUEEN),
                new Card(Suit.DIAMONDS, Rank.KING),
                new Card(Suit.DIAMONDS, Rank.ACE)
        );
        Poker poker = new Poker();
        assertTrue(poker.hasRoyalFlush(hand));
    }

    @Test
    public void shouldDetectLowAceStraight() {
        List<Card> hand = Arrays.asList(
                new Card(Suit.HEARTS, Rank.ACE),
                new Card(Suit.SPADES, Rank.TWO),
                new Card(Suit.CLUBS, Rank.THREE),
                new Card(Suit.DIAMONDS, Rank.FOUR),
                new Card(Suit.HEARTS, Rank.FIVE)
        );
        Poker poker = new Poker();
        assertTrue(poker.hasStraight(hand));
    }

    // --- TESTY NEGATYWNE ---

    @Test
    public void shouldNotDetectFlushWhenColorsAreMixed() {
        // 4 kiery i 1 pik - to NIE JEST kolor
        List<Card> hand = Arrays.asList(
                new Card(Suit.HEARTS, Rank.TWO),
                new Card(Suit.SPADES, Rank.FIVE),
                new Card(Suit.HEARTS, Rank.NINE),
                new Card(Suit.HEARTS, Rank.JACK),
                new Card(Suit.HEARTS, Rank.KING)
        );
        Poker poker = new Poker();
        assertFalse(poker.hasFlush(hand));
    }

    @Test
    public void shouldNotDetectStraightWhenCardsAreNotSequential() {
        // 5, 6, 7, 8 i nagle Król - to NIE JEST strit
        List<Card> hand = Arrays.asList(
                new Card(Suit.HEARTS, Rank.FIVE),
                new Card(Suit.SPADES, Rank.SIX),
                new Card(Suit.CLUBS, Rank.SEVEN),
                new Card(Suit.DIAMONDS, Rank.EIGHT),
                new Card(Suit.HEARTS, Rank.KING)
        );
        Poker poker = new Poker();
        assertFalse(poker.hasStraight(hand));
    }

    @Test
    public void shouldNotDetectTwoPairsWhenOnlyOnePairIsPresent() {
        // Tylko jedna para (Dziewiątki)
        List<Card> hand = Arrays.asList(
                new Card(Suit.HEARTS, Rank.NINE),
                new Card(Suit.SPADES, Rank.NINE),
                new Card(Suit.CLUBS, Rank.TWO),
                new Card(Suit.DIAMONDS, Rank.FOUR),
                new Card(Suit.HEARTS, Rank.KING)
        );
        Poker poker = new Poker();
        assertFalse(poker.hasTwoPairs(hand));
    }

    @Test
    public void shouldNotDetectFullHouseWhenOnlyThreeOfAKindIsPresent() {
        // Trójka (Dziesiątki) i dwie różne karty
        List<Card> hand = Arrays.asList(
                new Card(Suit.HEARTS, Rank.TEN),
                new Card(Suit.SPADES, Rank.TEN),
                new Card(Suit.CLUBS, Rank.TEN),
                new Card(Suit.DIAMONDS, Rank.FOUR),
                new Card(Suit.HEARTS, Rank.KING)
        );
        Poker poker = new Poker();
        assertFalse(poker.hasFullHouse(hand));
    }

    @Test
    public void shouldNotDetectStraightWhenCardsWrapAround() {
        // Zawinięcie Q, K, A, 2, 3 nie jest stritem
        List<Card> hand = Arrays.asList(
                new Card(Suit.HEARTS, Rank.QUEEN),
                new Card(Suit.SPADES, Rank.KING),
                new Card(Suit.CLUBS, Rank.ACE),
                new Card(Suit.DIAMONDS, Rank.TWO),
                new Card(Suit.HEARTS, Rank.THREE)
        );
        Poker poker = new Poker();
        assertFalse(poker.hasStraight(hand));
    }

    @Test
    public void shouldReturnFalseForAllWhenGarbageHand() {
        // Zwykła, nic niewarta ręka (High Card)
        List<Card> hand = Arrays.asList(
                new Card(Suit.HEARTS, Rank.TWO),
                new Card(Suit.SPADES, Rank.FIVE),
                new Card(Suit.CLUBS, Rank.NINE),
                new Card(Suit.DIAMONDS, Rank.JACK),
                new Card(Suit.HEARTS, Rank.KING)
        );
        Poker poker = new Poker();

        assertFalse(poker.hasPair(hand));
        assertFalse(poker.hasTwoPairs(hand));
        assertFalse(poker.hasThreeOfAKind(hand));
        assertFalse(poker.hasStraight(hand));
        assertFalse(poker.hasFlush(hand));
        assertFalse(poker.hasFullHouse(hand));
        assertFalse(poker.hasFourOfAKind(hand));
        assertFalse(poker.hasStraightFlush(hand));
        assertFalse(poker.hasRoyalFlush(hand));
    }
}
