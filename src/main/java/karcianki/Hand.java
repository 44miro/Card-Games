package karcianki;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Hand {
    private ArrayList<Card> cards;

    public Hand() {
        this.cards = new ArrayList<>();
    }

    public Hand(List<Card> initialCards) {
        this.cards = new ArrayList<>(initialCards);
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void clear() {
        cards.clear();
    }

    public void showHand() {
        System.out.println("_________________");
        for (Card card : cards) {
            System.out.println(card);
        }
        System.out.println("_________________");
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    private Map<Rank, Long> getRankCounts() {
        return cards.stream()
                .collect(Collectors.groupingBy(Card::getRank, Collectors.counting()));
    }

    public boolean hasPair() {
        return getRankCounts().values().stream().anyMatch(count -> count == 2);
    }

    public boolean hasTwoPairs() {
        return getRankCounts().values().stream().filter(count -> count == 2).count() == 2;
    }

    public boolean hasThreeOfAKind() {
        return getRankCounts().values().stream().anyMatch(count -> count == 3);
    }

    public boolean hasFlush() {
        return cards.stream().map(Card::getSuit).distinct().count() == 1;
    }

    public boolean hasStraight() {
        List<Integer> ordinals = cards.stream().map(c -> c.getRank().ordinal()).sorted().toList();
        boolean isStandardStraight = true;
        for (int i = 0; i < ordinals.size() - 1; i++) {
            if (ordinals.get(i + 1) - ordinals.get(i) != 1) {
                isStandardStraight = false;
                break;
            }
        }
        if (isStandardStraight) return true;
        List<Integer> lowAceStraight = Arrays.asList(
                Rank.TWO.ordinal(), Rank.THREE.ordinal(), Rank.FOUR.ordinal(), Rank.FIVE.ordinal(), Rank.ACE.ordinal());
        return ordinals.equals(lowAceStraight);
    }

    public boolean hasFullHouse() {
        Map<Rank, Long> rankCounts = getRankCounts();
        return rankCounts.containsValue(3L) && rankCounts.containsValue(2L);
    }

    public boolean hasFourOfAKind() {
        return getRankCounts().containsValue(4L);
    }

    public boolean hasStraightFlush() {
        return hasStraight() && hasFlush();
    }

    public boolean hasRoyalFlush() {
        return hasStraightFlush()
                && cards.stream().anyMatch(c -> c.getRank() == Rank.ACE)
                && cards.stream().anyMatch(c -> c.getRank() == Rank.TEN);
    }

    public HandRank evaluateRank() {
        if (hasRoyalFlush()) return HandRank.ROYAL_FLUSH;
        if (hasStraightFlush()) return HandRank.STRAIGHT_FLUSH;
        if (hasFourOfAKind()) return HandRank.FOUR_OF_A_KIND;
        if (hasFullHouse()) return HandRank.FULL_HOUSE;
        if (hasFlush()) return HandRank.FLUSH;
        if (hasStraight()) return HandRank.STRAIGHT;
        if (hasThreeOfAKind()) return HandRank.THREE_OF_A_KIND;
        if (hasTwoPairs()) return HandRank.TWO_PAIRS;
        if (hasPair()) return HandRank.PAIR;
        return HandRank.HIGH_CARD;
    }
}