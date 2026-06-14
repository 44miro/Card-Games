package karcianki;

public enum HandRank {
    HIGH_CARD("Wysoka Karta (High Card)"),
    PAIR("Para (Pair)"),
    TWO_PAIRS("Dwie Pary (Two Pairs)"),
    THREE_OF_A_KIND("Trójka (Three of a Kind)"),
    STRAIGHT("Strit (Straight)"),
    FLUSH("Kolor (Flush)"),
    FULL_HOUSE("Full (Full House)"),
    FOUR_OF_A_KIND("Kareta (Four of a Kind)"),
    STRAIGHT_FLUSH("Poker (Straight Flush)"),
    ROYAL_FLUSH("Poker Królewski (Royal Flush)");

    private final String displayName;

    HandRank(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}