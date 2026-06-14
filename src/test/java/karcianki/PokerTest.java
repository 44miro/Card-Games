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
        List<Card> cards = Arrays.asList(
                new Card(Rank.TWO, Suit.HEARTS),
                new Card(Rank.TWO, Suit.SPADES),
                new Card(Rank.FIVE, Suit.CLUBS),
                new Card(Rank.NINE, Suit.DIAMONDS),
                new Card(Rank.KING, Suit.HEARTS)
        );
        Hand hand = new Hand(cards);
        assertTrue(hand.hasPair());
    }

    @Test
    public void shouldDetectTwoPairs() {
        List<Card> cards = Arrays.asList(
                new Card(Rank.TWO, Suit.HEARTS),
                new Card(Rank.TWO, Suit.SPADES),
                new Card(Rank.FIVE, Suit.CLUBS),
                new Card(Rank.FIVE, Suit.DIAMONDS),
                new Card(Rank.KING, Suit.HEARTS)
        );
        Hand hand = new Hand(cards);
        assertTrue(hand.hasTwoPairs());
    }

    @Test
    public void shouldDetectThreeOfAKind() {
        List<Card> cards = Arrays.asList(
                new Card(Rank.SEVEN, Suit.HEARTS),
                new Card(Rank.SEVEN, Suit.SPADES),
                new Card(Rank.SEVEN, Suit.CLUBS),
                new Card(Rank.NINE, Suit.DIAMONDS),
                new Card(Rank.KING, Suit.HEARTS)
        );
        Hand hand = new Hand(cards);
        assertTrue(hand.hasThreeOfAKind());
    }

    @Test
    public void shouldDetectFlush() {
        List<Card> cards = Arrays.asList(
                new Card(Rank.TWO, Suit.HEARTS),
                new Card(Rank.FIVE, Suit.HEARTS),
                new Card(Rank.NINE, Suit.HEARTS),
                new Card(Rank.JACK, Suit.HEARTS),
                new Card(Rank.KING, Suit.HEARTS)
        );
        Hand hand = new Hand(cards);
        assertTrue(hand.hasFlush());
    }

    @Test
    public void shouldDetectStraight() {
        List<Card> cards = Arrays.asList(
                new Card(Rank.FIVE, Suit.HEARTS),
                new Card(Rank.SIX, Suit.SPADES),
                new Card(Rank.SEVEN, Suit.CLUBS),
                new Card(Rank.EIGHT, Suit.DIAMONDS),
                new Card(Rank.NINE, Suit.HEARTS)
        );
        Hand hand = new Hand(cards);
        assertTrue(hand.hasStraight());
    }

    @Test
    public void shouldDetectFullHouse() {
        List<Card> cards = Arrays.asList(
                new Card(Rank.TEN, Suit.HEARTS),
                new Card(Rank.TEN, Suit.SPADES),
                new Card(Rank.TEN, Suit.CLUBS),
                new Card(Rank.KING, Suit.DIAMONDS),
                new Card(Rank.KING, Suit.HEARTS)
        );
        Hand hand = new Hand(cards);
        assertTrue(hand.hasFullHouse());
    }

    @Test
    public void shouldDetectFourOfAKind() {
        List<Card> cards = Arrays.asList(
                new Card(Rank.ACE, Suit.HEARTS),
                new Card(Rank.ACE, Suit.SPADES),
                new Card(Rank.ACE, Suit.CLUBS),
                new Card(Rank.ACE, Suit.DIAMONDS),
                new Card(Rank.NINE, Suit.HEARTS)
        );
        Hand hand = new Hand(cards);
        assertTrue(hand.hasFourOfAKind());
    }

    @Test
    public void shouldDetectStraightFlush() {
        List<Card> cards = Arrays.asList(
                new Card(Rank.NINE, Suit.SPADES),
                new Card(Rank.TEN, Suit.SPADES),
                new Card(Rank.JACK, Suit.SPADES),
                new Card(Rank.QUEEN, Suit.SPADES),
                new Card(Rank.KING, Suit.SPADES)
        );
        Hand hand = new Hand(cards);
        assertTrue(hand.hasStraightFlush());
    }

    @Test
    public void shouldDetectRoyalFlush() {
        Hand hand = new Hand(Arrays.asList(
                new Card(Rank.TEN, Suit.DIAMONDS),
                new Card(Rank.JACK, Suit.DIAMONDS),
                new Card(Rank.QUEEN, Suit.DIAMONDS),
                new Card(Rank.KING, Suit.DIAMONDS),
                new Card(Rank.ACE, Suit.DIAMONDS)
        ));

        assertTrue(hand.hasRoyalFlush());
    }

    @Test
    public void shouldDetectLowAceStraight() {
        List<Card> cards = Arrays.asList(
                new Card(Rank.ACE, Suit.HEARTS),
                new Card(Rank.TWO, Suit.SPADES),
                new Card(Rank.THREE, Suit.CLUBS),
                new Card(Rank.FOUR, Suit.DIAMONDS),
                new Card(Rank.FIVE, Suit.HEARTS)
        );
        Hand hand = new Hand(cards);
        assertTrue(hand.hasStraight());
    }

    // --- TESTY NEGATYWNE ---

    @Test
    public void shouldNotDetectFlushWhenColorsAreMixed() {
        List<Card> cards = Arrays.asList(
                new Card(Rank.TWO, Suit.HEARTS),
                new Card(Rank.FIVE, Suit.SPADES),
                new Card(Rank.NINE, Suit.HEARTS),
                new Card(Rank.JACK, Suit.HEARTS),
                new Card(Rank.KING, Suit.HEARTS)
        );
        Hand hand = new Hand(cards);
        assertFalse(hand.hasFlush());
    }

    @Test
    public void shouldNotDetectStraightWhenCardsAreNotSequential() {
        List<Card> cards = Arrays.asList(
                new Card(Rank.FIVE, Suit.HEARTS),
                new Card(Rank.SIX, Suit.SPADES),
                new Card(Rank.SEVEN, Suit.CLUBS),
                new Card(Rank.EIGHT, Suit.DIAMONDS),
                new Card(Rank.KING, Suit.HEARTS)
        );
        Hand hand = new Hand(cards);
        assertFalse(hand.hasStraight());
    }

    @Test
    public void shouldNotDetectTwoPairsWhenOnlyOnePairIsPresent() {
        List<Card> cards = Arrays.asList(
                new Card(Rank.NINE, Suit.HEARTS),
                new Card(Rank.NINE, Suit.SPADES),
                new Card(Rank.TWO, Suit.CLUBS),
                new Card(Rank.FOUR, Suit.DIAMONDS),
                new Card(Rank.KING, Suit.HEARTS)
        );
        Hand hand = new Hand(cards);
        assertFalse(hand.hasTwoPairs());
    }

    @Test
    public void shouldNotDetectFullHouseWhenOnlyThreeOfAKindIsPresent() {
        List<Card> cards = Arrays.asList(
                new Card(Rank.TEN, Suit.HEARTS),
                new Card(Rank.TEN, Suit.SPADES),
                new Card(Rank.TEN, Suit.CLUBS),
                new Card(Rank.FOUR, Suit.DIAMONDS),
                new Card(Rank.KING, Suit.HEARTS)
        );
        Hand hand = new Hand(cards);
        assertFalse(hand.hasFullHouse());
    }

    @Test
    public void shouldNotDetectStraightWhenCardsWrapAround() {
        List<Card> cards = Arrays.asList(
                new Card(Rank.QUEEN, Suit.HEARTS),
                new Card(Rank.KING, Suit.SPADES),
                new Card(Rank.ACE, Suit.CLUBS),
                new Card(Rank.TWO, Suit.DIAMONDS),
                new Card(Rank.THREE, Suit.HEARTS)
        );
        Hand hand = new Hand(cards);
        assertFalse(hand.hasStraight());
    }

    @Test
    public void shouldReturnFalseForAllWhenGarbageHand() {
        List<Card> cards = Arrays.asList(
                new Card(Rank.TWO, Suit.HEARTS),
                new Card(Rank.FIVE, Suit.SPADES),
                new Card(Rank.NINE, Suit.CLUBS),
                new Card(Rank.JACK, Suit.DIAMONDS),
                new Card(Rank.KING, Suit.HEARTS)
        );
        Hand hand = new Hand(cards);

        assertFalse(hand.hasPair());
        assertFalse(hand.hasTwoPairs());
        assertFalse(hand.hasThreeOfAKind());
        assertFalse(hand.hasStraight());
        assertFalse(hand.hasFlush());
        assertFalse(hand.hasFullHouse());
        assertFalse(hand.hasFourOfAKind());
        assertFalse(hand.hasStraightFlush());
        assertFalse(hand.hasRoyalFlush());
    }
}