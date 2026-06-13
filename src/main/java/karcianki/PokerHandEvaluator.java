package karcianki;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PokerHandEvaluator {

    public boolean hasPair(List<Card> hand) {
        return getRankCounts(hand).values().stream().anyMatch(count -> count == 2);
    }

    public boolean hasTwoPairs(List<Card> hand) {
        return getRankCounts(hand).values().stream().filter(count -> count == 2).count() == 2;
    }

    public boolean hasThreeOfAKind(List<Card> hand) {
        return getRankCounts(hand).values().stream().anyMatch(count -> count == 3);
    }

    // Prywatna metoda pomocnicza (wynik fazy REFACTOR)
    private Map<Rank, Long> getRankCounts(List<Card> hand) {
        return hand.stream()
                .collect(Collectors.groupingBy(Card::getRank, Collectors.counting()));
    }

    public boolean hasFlush(List<Card> hand) {
        return hand.stream()
                .map(Card::getSuit)
                .distinct()
                .count() == 1;
    }
    public boolean hasStraight(List<Card> hand) {
        List<Integer> ordinals = hand.stream()
                .map(c -> c.getRank().ordinal())
                .sorted()
                .toList();

        boolean isStandardStraight = true;
        for (int i = 0; i < ordinals.size() - 1; i++) {
            if (ordinals.get(i + 1) - ordinals.get(i) != 1) {
                isStandardStraight = false;
                break;
            }
        }

        if (isStandardStraight) return true;

        List<Integer> lowAceStraight = Arrays.asList(
                Rank.TWO.ordinal(), Rank.THREE.ordinal(), Rank.FOUR.ordinal(),
                Rank.FIVE.ordinal(), Rank.ACE.ordinal()
        );
        return ordinals.equals(lowAceStraight);
    }

    public boolean hasFullHouse(List<Card> hand) {
        Map<Rank, Long> rankCounts = getRankCounts(hand);
        return rankCounts.containsValue(3L) && rankCounts.containsValue(2L);
    }

    public boolean hasFourOfAKind(List<Card> hand) {
        return getRankCounts(hand).containsValue(4L);
    }

    public boolean hasStraightFlush(List<Card> hand) {
        return hasStraight(hand) && hasFlush(hand);
    }

    public boolean hasRoyalFlush(List<Card> hand) {
        boolean isStraightFlush = hasStraightFlush(hand);
        boolean hasAce = hand.stream().anyMatch(c -> c.getRank() == Rank.ACE);
        boolean hasTen = hand.stream().anyMatch(c -> c.getRank() == Rank.TEN);
        return isStraightFlush && hasAce && hasTen;
    }
}