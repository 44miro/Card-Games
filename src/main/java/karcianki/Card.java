package karcianki;

public class Card implements Comparable<Card> {
    private final Rank rank;
    private final Suit suit;

    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public Rank getRank() {
        return rank;
    }

    public Suit getSuit() {
        return suit;
    }

    @Override
    public int compareTo(Card other) {
        return Integer.compare(this.rank.ordinal(), other.rank.ordinal());
    }

    @Override
    public String toString() {
        return "|" + rank + " " + suit + "|";
    }
}