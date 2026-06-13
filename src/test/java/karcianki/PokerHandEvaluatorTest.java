package karcianki;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;

public class PokerHandEvaluatorTest {

    @Test
    public void shouldDetectPair() {
        List<Card> hand = Arrays.asList(
                new Card(Rank.TWO, Suit.HEARTS),
                new Card(Rank.TWO, Suit.SPADES),
                new Card(Rank.FIVE, Suit.CLUBS),
                new Card(Rank.NINE, Suit.DIAMONDS),
                new Card(Rank.KING, Suit.HEARTS)
        );
        PokerHandEvaluator evaluator = new PokerHandEvaluator();
        assertTrue(evaluator.hasPair(hand));
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
        PokerHandEvaluator evaluator = new PokerHandEvaluator();
        assertTrue(evaluator.hasTwoPairs(hand));
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
        PokerHandEvaluator evaluator = new PokerHandEvaluator();
        assertTrue(evaluator.hasThreeOfAKind(hand));
    }

    @Test
    public void shouldDetectFlush() {
        // Kolor: 5 kart w tym samym kolorze (nie po kolei)
        List<Card> hand = Arrays.asList(
                new Card(Rank.TWO, Suit.HEARTS),
                new Card(Rank.FIVE, Suit.HEARTS),
                new Card(Rank.NINE, Suit.HEARTS),
                new Card(Rank.JACK, Suit.HEARTS),
                new Card(Rank.KING, Suit.HEARTS)
        );
        PokerHandEvaluator evaluator = new PokerHandEvaluator();
        assertTrue(evaluator.hasFlush(hand));
    }

    @Test
    public void shouldDetectStraight() {
        // Strit: 5 kart po kolei (różne kolory).
        List<Card> hand = Arrays.asList(
                new Card(Rank.FIVE, Suit.HEARTS),
                new Card(Rank.SIX, Suit.SPADES),
                new Card(Rank.SEVEN, Suit.CLUBS),
                new Card(Rank.EIGHT, Suit.DIAMONDS),
                new Card(Rank.NINE, Suit.HEARTS)
        );
        PokerHandEvaluator evaluator = new PokerHandEvaluator();
        assertTrue(evaluator.hasStraight(hand));
    }

    @Test
    public void shouldDetectFullHouse() {
        // Full: Trójka i Para
        List<Card> hand = Arrays.asList(
                new Card(Rank.TEN, Suit.HEARTS),
                new Card(Rank.TEN, Suit.SPADES),
                new Card(Rank.TEN, Suit.CLUBS),
                new Card(Rank.KING, Suit.DIAMONDS),
                new Card(Rank.KING, Suit.HEARTS)
        );
        PokerHandEvaluator evaluator = new PokerHandEvaluator();
        assertTrue(evaluator.hasFullHouse(hand));
    }

    @Test
    public void shouldDetectFourOfAKind() {
        // Kareta: 4 karty tej samej figury
        List<Card> hand = Arrays.asList(
                new Card(Rank.ACE, Suit.HEARTS),
                new Card(Rank.ACE, Suit.SPADES),
                new Card(Rank.ACE, Suit.CLUBS),
                new Card(Rank.ACE, Suit.DIAMONDS),
                new Card(Rank.NINE, Suit.HEARTS)
        );
        PokerHandEvaluator evaluator = new PokerHandEvaluator();
        assertTrue(evaluator.hasFourOfAKind(hand));
    }

    @Test
    public void shouldDetectStraightFlush() {
        // Poker: 5 kart po kolei w tym samym kolorze
        List<Card> hand = Arrays.asList(
                new Card(Rank.NINE, Suit.SPADES),
                new Card(Rank.TEN, Suit.SPADES),
                new Card(Rank.JACK, Suit.SPADES),
                new Card(Rank.QUEEN, Suit.SPADES),
                new Card(Rank.KING, Suit.SPADES)
        );
        PokerHandEvaluator evaluator = new PokerHandEvaluator();
        assertTrue(evaluator.hasStraightFlush(hand));
    }

    @Test
    public void shouldDetectRoyalFlush() {
        // Poker Królewski: 10, J, Q, K, As w tym samym kolorze
        List<Card> hand = Arrays.asList(
                new Card(Rank.TEN, Suit.DIAMONDS),
                new Card(Rank.JACK, Suit.DIAMONDS),
                new Card(Rank.QUEEN, Suit.DIAMONDS),
                new Card(Rank.KING, Suit.DIAMONDS),
                new Card(Rank.ACE, Suit.DIAMONDS)
        );
        PokerHandEvaluator evaluator = new PokerHandEvaluator();

        assertTrue(evaluator.hasRoyalFlush(hand));
    }

    @Test
    public void shouldDetectLowAceStraight() {
        // Specjalny przypadek Strita (As jako jedynka): As, 2, 3, 4, 5
        List<Card> hand = Arrays.asList(
                new Card(Rank.ACE, Suit.HEARTS),
                new Card(Rank.TWO, Suit.SPADES),
                new Card(Rank.THREE, Suit.CLUBS),
                new Card(Rank.FOUR, Suit.DIAMONDS),
                new Card(Rank.FIVE, Suit.HEARTS)
        );
        PokerHandEvaluator evaluator = new PokerHandEvaluator();
        assertTrue(evaluator.hasStraight(hand));
    }
}