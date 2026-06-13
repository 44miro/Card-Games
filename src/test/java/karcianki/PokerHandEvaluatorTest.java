package karcianki;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;

public class PokerHandEvaluatorTest {

    @Test
    public void shouldDetectPair() {
        List<Card> hand = Arrays.asList(
                //poprawka kolejnosci do konstruktora z main
                new Card(Suit.HEARTS, Rank.TWO),
                new Card(Suit.SPADES, Rank.TWO),
                new Card(Suit.CLUBS,Rank.FIVE),
                new Card(Suit.DIAMONDS,Rank.NINE),
                new Card(Suit.HEARTS, Rank.KING)
        );
        PokerHandEvaluator evaluator = new PokerHandEvaluator();
        assertTrue(evaluator.hasPair(hand));
    }

    @Test
    public void shouldDetectTwoPairs() {
        List<Card> hand = Arrays.asList(
                //poprawka kolejnosci do konstruktora z main
                new Card(Suit.HEARTS, Rank.TWO),
                new Card(Suit.SPADES, Rank.TWO),
                new Card(Suit.CLUBS,Rank.FIVE),
                new Card(Suit.DIAMONDS,Rank.FIVE),
                new Card(Suit.HEARTS, Rank.KING)
        );
        PokerHandEvaluator evaluator = new PokerHandEvaluator();
        assertTrue(evaluator.hasTwoPairs(hand));
    }

    @Test
    public void shouldDetectThreeOfAKind() {
        List<Card> hand = Arrays.asList(
                //poprawka kolejnosci do konstruktora z main
                new Card(Suit.HEARTS, Rank.SEVEN),
                new Card(Suit.SPADES, Rank.SEVEN),
                new Card(Suit.CLUBS,Rank.SEVEN),
                new Card(Suit.DIAMONDS, Rank.NINE),
                new Card(Suit.HEARTS, Rank.KING)
        );
        PokerHandEvaluator evaluator = new PokerHandEvaluator();
        assertTrue(evaluator.hasThreeOfAKind(hand));
    }
}