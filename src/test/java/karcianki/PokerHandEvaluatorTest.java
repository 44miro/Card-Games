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
}