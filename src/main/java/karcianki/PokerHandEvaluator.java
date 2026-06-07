package karcianki;

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
}