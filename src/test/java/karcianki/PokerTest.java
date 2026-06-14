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
                new Card(Rank.TWO, Suit.HEARTS),
                new Card(Rank.TWO, Suit.SPADES),
                new Card(Rank.FIVE, Suit.CLUBS),
                new Card(Rank.NINE, Suit.DIAMONDS),
                new Card(Rank.KING, Suit.HEARTS)
        );
        Poker poker = new Poker();
        assertTrue(poker.hasPair(hand));
    }

    @Test
    public void shouldDetectTwoPairs() {
        List<Card> hand = Arrays.asList(
                new Card(Rank.TWO, Suit.HEARTS),
                new Card(Rank.TWO, Suit.SPADES),
                new Card(Rank.FIVE, Suit.CLUBS),
                new Card(Rank.FIVE, Suit.DIAMONDS),
                new Card(Rank.KING, Suit.HEARTS)
        );
        Poker poker = new Poker();
        assertTrue(poker.hasTwoPairs(hand));
    }

    @Test
    public void shouldDetectThreeOfAKind() {
        List<Card> hand = Arrays.asList(
                new Card(Rank.SEVEN, Suit.HEARTS),
                new Card(Rank.SEVEN, Suit.SPADES),
                new Card(Rank.SEVEN, Suit.CLUBS),
                new Card(Rank.NINE, Suit.DIAMONDS),
                new Card(Rank.KING, Suit.HEARTS)
        );
        Poker poker = new Poker();
        assertTrue(poker.hasThreeOfAKind(hand));
    }

    @Test
    public void shouldDetectFlush() {
        List<Card> hand = Arrays.asList(
                new Card(Rank.TWO, Suit.HEARTS),
                new Card(Rank.FIVE, Suit.HEARTS),
                new Card(Rank.NINE, Suit.HEARTS),
                new Card(Rank.JACK, Suit.HEARTS),
                new Card(Rank.KING, Suit.HEARTS)
        );
        Poker poker = new Poker();
        assertTrue(poker.hasFlush(hand));
    }

    @Test
    public void shouldDetectStraight() {
        List<Card> hand = Arrays.asList(
                new Card(Rank.FIVE, Suit.HEARTS),
                new Card(Rank.SIX, Suit.SPADES),
                new Card(Rank.SEVEN, Suit.CLUBS),
                new Card(Rank.EIGHT, Suit.DIAMONDS),
                new Card(Rank.NINE, Suit.HEARTS)
        );
        Poker poker = new Poker();
        assertTrue(poker.hasStraight(hand));
    }

    @Test
    public void shouldDetectFullHouse() {
        List<Card> hand = Arrays.asList(
                new Card(Rank.TEN, Suit.HEARTS),
                new Card(Rank.TEN, Suit.SPADES),
                new Card(Rank.TEN, Suit.CLUBS),
                new Card(Rank.KING, Suit.DIAMONDS),
                new Card(Rank.KING, Suit.HEARTS)
        );
        Poker poker = new Poker();
        assertTrue(poker.hasFullHouse(hand));
    }

    @Test
    public void shouldDetectFourOfAKind() {
        List<Card> hand = Arrays.asList(
                new Card(Rank.ACE, Suit.HEARTS),
                new Card(Rank.ACE, Suit.SPADES),
                new Card(Rank.ACE, Suit.CLUBS),
                new Card(Rank.ACE, Suit.DIAMONDS),
                new Card(Rank.NINE, Suit.HEARTS)
        );
        Poker poker = new Poker();
        assertTrue(poker.hasFourOfAKind(hand));
    }

    @Test
    public void shouldDetectStraightFlush() {
        List<Card> hand = Arrays.asList(
                new Card(Rank.NINE, Suit.SPADES),
                new Card(Rank.TEN, Suit.SPADES),
                new Card(Rank.JACK, Suit.SPADES),
                new Card(Rank.QUEEN, Suit.SPADES),
                new Card(Rank.KING, Suit.SPADES)
        );
        Poker poker = new Poker();
        assertTrue(poker.hasStraightFlush(hand));
    }

    @Test
    public void shouldDetectRoyalFlush() {
        List<Card> hand = Arrays.asList(
                new Card(Rank.TEN, Suit.DIAMONDS),
                new Card(Rank.JACK, Suit.DIAMONDS),
                new Card(Rank.QUEEN, Suit.DIAMONDS),
                new Card(Rank.KING, Suit.DIAMONDS),
                new Card(Rank.ACE, Suit.DIAMONDS)
        );
        Poker poker = new Poker();
        assertTrue(poker.hasRoyalFlush(hand));
    }

    @Test
    public void shouldDetectLowAceStraight() {
        List<Card> hand = Arrays.asList(
                new Card(Rank.ACE, Suit.HEARTS),
                new Card(Rank.TWO, Suit.SPADES),
                new Card(Rank.THREE, Suit.CLUBS),
                new Card(Rank.FOUR, Suit.DIAMONDS),
                new Card(Rank.FIVE, Suit.HEARTS)
        );
        Poker poker = new Poker();
        assertTrue(poker.hasStraight(hand));
    }

    // --- TESTY NEGATYWNE ---

    @Test
    public void shouldNotDetectFlushWhenColorsAreMixed() {
        List<Card> hand = Arrays.asList(
                new Card(Rank.TWO, Suit.HEARTS),
                new Card(Rank.FIVE, Suit.SPADES),
                new Card(Rank.NINE, Suit.HEARTS),
                new Card(Rank.JACK, Suit.HEARTS),
                new Card(Rank.KING, Suit.HEARTS)
        );
        Poker poker = new Poker();
        assertFalse(poker.hasFlush(hand));
    }

    @Test
    public void shouldNotDetectStraightWhenCardsAreNotSequential() {
        List<Card> hand = Arrays.asList(
                new Card(Rank.FIVE, Suit.HEARTS),
                new Card(Rank.SIX, Suit.SPADES),
                new Card(Rank.SEVEN, Suit.CLUBS),
                new Card(Rank.EIGHT, Suit.DIAMONDS),
                new Card(Rank.KING, Suit.HEARTS)
        );
        Poker poker = new Poker();
        assertFalse(poker.hasStraight(hand));
    }

    @Test
    public void shouldNotDetectTwoPairsWhenOnlyOnePairIsPresent() {
        List<Card> hand = Arrays.asList(
                new Card(Rank.NINE, Suit.HEARTS),
                new Card(Rank.NINE, Suit.SPADES),
                new Card(Rank.TWO, Suit.CLUBS),
                new Card(Rank.FOUR, Suit.DIAMONDS),
                new Card(Rank.KING, Suit.HEARTS)
        );
        Poker poker = new Poker();
        assertFalse(poker.hasTwoPairs(hand));
    }

    @Test
    public void shouldNotDetectFullHouseWhenOnlyThreeOfAKindIsPresent() {
        List<Card> hand = Arrays.asList(
                new Card(Rank.TEN, Suit.HEARTS),
                new Card(Rank.TEN, Suit.SPADES),
                new Card(Rank.TEN, Suit.CLUBS),
                new Card(Rank.FOUR, Suit.DIAMONDS),
                new Card(Rank.KING, Suit.HEARTS)
        );
        Poker poker = new Poker();
        assertFalse(poker.hasFullHouse(hand));
    }

    @Test
    public void shouldNotDetectStraightWhenCardsWrapAround() {
        List<Card> hand = Arrays.asList(
                new Card(Rank.QUEEN, Suit.HEARTS),
                new Card(Rank.KING, Suit.SPADES),
                new Card(Rank.ACE, Suit.CLUBS),
                new Card(Rank.TWO, Suit.DIAMONDS),
                new Card(Rank.THREE, Suit.HEARTS)
        );
        Poker poker = new Poker();
        assertFalse(poker.hasStraight(hand));
    }

    @Test
    public void shouldReturnFalseForAllWhenGarbageHand() {
        List<Card> hand = Arrays.asList(
                new Card(Rank.TWO, Suit.HEARTS),
                new Card(Rank.FIVE, Suit.SPADES),
                new Card(Rank.NINE, Suit.CLUBS),
                new Card(Rank.JACK, Suit.DIAMONDS),
                new Card(Rank.KING, Suit.HEARTS)
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