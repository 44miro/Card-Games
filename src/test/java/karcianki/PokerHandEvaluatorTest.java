package karcianki;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;

public class PokerHandEvaluatorTest {

    @Test
    public void shouldDetectPair() {
        List<Card> hand = Arrays.asList(
                new Card(Suit.HEARTS, Rank.TWO),
                new Card(Suit.SPADES, Rank.TWO),
                new Card(Suit.CLUBS, Rank.FIVE),
                new Card(Suit.DIAMONDS, Rank.NINE),
                new Card(Suit.HEARTS, Rank.KING)
        );
        PokerHandEvaluator evaluator = new PokerHandEvaluator();
        assertTrue(evaluator.hasPair(hand));
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
        PokerHandEvaluator evaluator = new PokerHandEvaluator();
        assertTrue(evaluator.hasTwoPairs(hand));
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
        PokerHandEvaluator evaluator = new PokerHandEvaluator();
        assertTrue(evaluator.hasThreeOfAKind(hand));
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
        PokerHandEvaluator evaluator = new PokerHandEvaluator();
        assertTrue(evaluator.hasFlush(hand));
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
        PokerHandEvaluator evaluator = new PokerHandEvaluator();
        assertTrue(evaluator.hasStraight(hand));
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
        PokerHandEvaluator evaluator = new PokerHandEvaluator();
        assertTrue(evaluator.hasFullHouse(hand));
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
        PokerHandEvaluator evaluator = new PokerHandEvaluator();
        assertTrue(evaluator.hasFourOfAKind(hand));
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
        PokerHandEvaluator evaluator = new PokerHandEvaluator();
        assertTrue(evaluator.hasStraightFlush(hand));
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
        PokerHandEvaluator evaluator = new PokerHandEvaluator();
        assertTrue(evaluator.hasRoyalFlush(hand));
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
        PokerHandEvaluator evaluator = new PokerHandEvaluator();
        assertTrue(evaluator.hasStraight(hand));
    }


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
        PokerHandEvaluator evaluator = new PokerHandEvaluator();
        assertFalse(evaluator.hasFlush(hand));
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
        PokerHandEvaluator evaluator = new PokerHandEvaluator();
        assertFalse(evaluator.hasStraight(hand));
    }

    @Test
    public void shouldNotDetectTwoPairsWhenOnlyOnePairIsPresent() {
        // Tylko jedna para (Dziewiątki), program nie może tego uznać za Dwie Pary
        List<Card> hand = Arrays.asList(
                new Card(Suit.HEARTS, Rank.NINE),
                new Card(Suit.SPADES, Rank.NINE),
                new Card(Suit.CLUBS, Rank.TWO),
                new Card(Suit.DIAMONDS, Rank.FOUR),
                new Card(Suit.HEARTS, Rank.KING)
        );
        PokerHandEvaluator evaluator = new PokerHandEvaluator();
        assertFalse(evaluator.hasTwoPairs(hand));
    }

    @Test
    public void shouldNotDetectFullHouseWhenOnlyThreeOfAKindIsPresent() {
        // Trójka (Dziesiątki) i dwie RÓŻNE karty (Czwórka, Król) - to nie jest Full
        List<Card> hand = Arrays.asList(
                new Card(Suit.HEARTS, Rank.TEN),
                new Card(Suit.SPADES, Rank.TEN),
                new Card(Suit.CLUBS, Rank.TEN),
                new Card(Suit.DIAMONDS, Rank.FOUR),
                new Card(Suit.HEARTS, Rank.KING)
        );
        PokerHandEvaluator evaluator = new PokerHandEvaluator();
        assertFalse(evaluator.hasFullHouse(hand));
    }

    @Test
    public void shouldNotDetectStraightWhenCardsWrapAround() {
        // "Strit z zawinięciem" (np. Q, K, A, 2, 3) nie jest legalny w klasycznym pokerze
        List<Card> hand = Arrays.asList(
                new Card(Suit.HEARTS, Rank.QUEEN),
                new Card(Suit.SPADES, Rank.KING),
                new Card(Suit.CLUBS, Rank.ACE),
                new Card(Suit.DIAMONDS, Rank.TWO),
                new Card(Suit.HEARTS, Rank.THREE)
        );
        PokerHandEvaluator evaluator = new PokerHandEvaluator();
        assertFalse(evaluator.hasStraight(hand));
    }

    @Test
    public void shouldReturnFalseForAllWhenGarbageHand() {
        // Zwykła, nic niewarta ręka (tzw. High Card / Wysoka Karta)
        List<Card> hand = Arrays.asList(
                new Card(Suit.HEARTS, Rank.TWO),
                new Card(Suit.SPADES, Rank.FIVE),
                new Card(Suit.CLUBS, Rank.NINE),
                new Card(Suit.DIAMONDS, Rank.JACK),
                new Card(Suit.HEARTS, Rank.KING)
        );
        PokerHandEvaluator evaluator = new PokerHandEvaluator();

        assertFalse(evaluator.hasPair(hand));
        assertFalse(evaluator.hasTwoPairs(hand));
        assertFalse(evaluator.hasThreeOfAKind(hand));
        assertFalse(evaluator.hasStraight(hand));
        assertFalse(evaluator.hasFlush(hand));
        assertFalse(evaluator.hasFullHouse(hand));
        assertFalse(evaluator.hasFourOfAKind(hand));
        assertFalse(evaluator.hasStraightFlush(hand));
        assertFalse(evaluator.hasRoyalFlush(hand));
    }
}