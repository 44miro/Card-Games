package karcianki;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Poker {
    private int pot;

    private final List<Player> players;
    private List<Player> activePlayers;
    private Deck deck;
    private final List<Card> communityCards;
    private final Scanner scanner;

    public Poker() {
        this.pot = 0;
        this.players = new ArrayList<>();
        this.communityCards = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }


    public void startSession() {
        System.out.println("=== WITAJ W MODULE POKERA ===");
        System.out.print("Podaj liczbę graczy (od 1 do 5): ");
        int numPlayers = scanner.nextInt();

        if (numPlayers < 1 || numPlayers > 5) {
            System.out.println("Niepoprawna liczba graczy! Ustawiam domyślnie 2 graczy.");
            numPlayers = 2;
        }

        players.clear();
        for (int i = 0; i < numPlayers; i++) {
            Player p = new Player();
            p.addChips(100);
            players.add(p);
        }

        boolean keepPlaying = true;


        while (keepPlaying) {

            playRound();

            long playersWithChips = players.stream().filter(p -> p.getChips() > 0).count();
            if (playersWithChips <= 1) {
                System.out.println("\n=======================================================");
                System.out.println("=== KONIEC GRY: Wszyscy pozostali gracze zbankrutowali! ===");
                System.out.println("=======================================================");
                for (int i = 0; i < players.size(); i++) {
                    if (players.get(i).getChips() > 0) {
                        System.out.println("Ostatecznym królem stołu zostaje Gracz " + (i + 1) + " z kwotą " + players.get(i).getChips() + " żetonów!");
                    }
                }
                break;
            }

            System.out.print("\nCzy chcesz rozegrać kolejną rundę? (wpisz 'quit' aby wyjść, lub cokolwiek innego aby grać dalej): ");
            String response = scanner.next();

            if (response.equalsIgnoreCase("quit")) {
                keepPlaying = false;
                System.out.println("\n=== ZAKOŃCZONO GRĘ NA ŻYCZENIE ===");
                System.out.println("Oto końcowe salda graczy:");
                for (int i = 0; i < players.size(); i++) {
                    System.out.println("  -> Gracz " + (i + 1) + ": " + players.get(i).getChips() + " żetonów");
                }
            }
        }
        System.out.println("\nDzięki za grę!");
    }


    private void playRound() {
        System.out.println("\n========================================");
        System.out.println("=== ROZPOCZĘCIE NOWEGO ROZDANIA ===");
        System.out.println("========================================");

        this.deck = new Deck();
        deck.shuffle();
        communityCards.clear();
        pot = 0;

        activePlayers = new ArrayList<>();
        for (Player p : players) {
            p.getHand().getCards().clear();
            if (p.getChips() > 0) {
                activePlayers.add(p);
            }
        }

        if (activePlayers.size() < 2) return;

        preFlop();
        if (activePlayers.size() > 1) flop();
        if (activePlayers.size() > 1) turn();
        if (activePlayers.size() > 1) river();

        showdown();
    }

    private void preFlop() {
        System.out.println("\n--- PRE-FLOP ---");
        for (int i = 0; i < 2; i++) {
            for (Player player : activePlayers) {
                player.getHand().addCards(deck.drawCard());
            }
        }
        System.out.println("Gracze otrzymali po 2 karty do ręki.");
        bettingRound();
    }

    private void flop() {
        System.out.println("\n--- FLOP (3 karty na stół) ---");
        for (int i = 0; i < 3; i++) {
            communityCards.add(deck.drawCard());
        }
        printCommunityCards();
        bettingRound();
    }

    private void turn() {
        System.out.println("\n--- TURN (4. karta na stół) ---");
        communityCards.add(deck.drawCard());
        printCommunityCards();
        bettingRound();
    }

    private void river() {
        System.out.println("\n--- RIVER (5. karta na stół) ---");
        communityCards.add(deck.drawCard());
        printCommunityCards();
        bettingRound();
    }

    private void bettingRound() {
        System.out.println("\n  >>> ROZPOCZYNA SIĘ LICYTACJA <<<");

        int currentHighestBet = 0;
        Map<Player, Integer> currentBets = new HashMap<>();
        Map<Player, Boolean> hasActed = new HashMap<>();

        for (Player p : activePlayers) {
            currentBets.put(p, 0);
            hasActed.put(p, false);
        }

        while (true) {
            boolean allCalledOrFolded = true;
            for (Player p : activePlayers) {
                if (!hasActed.get(p) || (currentBets.get(p) < currentHighestBet && p.getChips() > 0)) {
                    allCalledOrFolded = false;
                    break;
                }
            }
            if (allCalledOrFolded) break;

            for (int i = 0; i < activePlayers.size(); i++) {
                Player player = activePlayers.get(i);

                if (hasActed.get(player) && (currentBets.get(player) == currentHighestBet || player.getChips() == 0)) {
                    continue;
                }

                int originalNumber = getPlayerNumber(player);
                int amountToCall = currentHighestBet - currentBets.get(player);

                System.out.println("\n--- TURA GRACZA " + originalNumber + " ---");
                System.out.println("Żetony (Saldo): " + player.getChips());
                if (amountToCall > 0) {
                    System.out.println("Do wyrównania (Call): " + amountToCall);
                }
                showPlayerHand(player);

                System.out.println("Co chcesz zrobić?");
                if (amountToCall == 0) {
                    System.out.println("1. Czekaj (Check)");
                    System.out.println("2. Postaw (Bet)");
                } else {
                    System.out.println("1. Sprawdź (Call za " + amountToCall + ")");
                    System.out.println("2. Podbij (Raise)");
                }
                System.out.println("3. Pasuj (Fold)");
                System.out.print("Twój wybór (1-3): ");

                int choice = scanner.nextInt();

                if (choice == 1) { // CHECK / CALL
                    if (amountToCall == 0) {
                        System.out.println("  => Gracz " + originalNumber + " czeka.");
                    } else {
                        int toPay = Math.min(amountToCall, player.getChips());
                        if (toPay > 0) {
                            int placed = player.placeBet(toPay);
                            currentBets.put(player, currentBets.get(player) + placed);
                            addToPot(placed);
                            System.out.println("  => Gracz " + originalNumber + " wyrównuje za " + placed + ".");
                        } else {
                            System.out.println("  => Gracz " + originalNumber + " stuka w stół. Jest All-In.");
                        }
                    }
                    hasActed.put(player, true);

                } else if (choice == 2) { // BET / RAISE
                    System.out.print("  => O ile żetonów chcesz podbić stawkę? ");
                    int raiseAmount = scanner.nextInt();

                    int totalToPay = amountToCall + raiseAmount;
                    int actualToPay = Math.min(totalToPay, player.getChips());

                    if (actualToPay > 0) {
                        int placed = player.placeBet(actualToPay);
                        int newTotalBet = currentBets.get(player) + placed;
                        currentBets.put(player, newTotalBet);
                        addToPot(placed);


                        if (newTotalBet > currentHighestBet) {
                            currentHighestBet = newTotalBet;
                            System.out.println("  => Gracz " + originalNumber + " podbija! (łącznie wrzucił do puli " + newTotalBet + ").");

                            for (Player p : activePlayers) {
                                if (p != player) hasActed.put(p, false);
                            }
                        } else {
                            System.out.println("  => Gracz " + originalNumber + " wrzuca resztę do puli (All-In za " + placed + ").");
                        }
                    } else {
                        System.out.println("  => Nie masz żetonów na podbicie!");
                    }
                    hasActed.put(player, true);

                } else if (choice == 3) { // FOLD
                    System.out.println("  => Gracz " + originalNumber + " pasuje (Fold).");
                    activePlayers.remove(i);
                    i--;
                    if (activePlayers.size() == 1) {
                        System.out.println("\n  $$$ KONIEC LICYTACJI. PULA WYNOSI: " + pot + " $$$");
                        return;
                    }
                } else {
                    System.out.println("  => Nieznany wybór. Tracisz ruch.");
                    hasActed.put(player, true);
                }
            }
        }
        System.out.println("\n  $$$ KONIEC LICYTACJI. PULA WYNOSI: " + pot + " $$$");
    }

    private void showdown() {
        System.out.println("\n=== SHOWDOWN (Odkrycie kart) ===");

        if (activePlayers.size() == 1) {
            Player winner = activePlayers.get(0);
            System.out.println("Wszyscy przeciwnicy spasowali! Wygrywa Gracz " + getPlayerNumber(winner) + " i zgarnia: " + pot);
            winner.addChips(pot);
        } else {
            System.out.println("Do końca dotarło " + activePlayers.size() + " graczy. Oceniamy siłę rąk!");

            Player winner = null;
            int highestScore = -1;
            boolean isTie = false;

            for (Player p : activePlayers) {
                int score = getBestHandScore(p);
                System.out.println("  -> Gracz " + getPlayerNumber(p) + " ułożył układ: " + getHandName(score));

                if (score > highestScore) {
                    highestScore = score;
                    winner = p;
                    isTie = false;
                } else if (score == highestScore) {
                    isTie = true;
                }
            }

            if (isTie) {
                System.out.println("\n*** MAMY REMIS NA STOLE! Gracze dzielą pulę równomiernie! ***");
                int split = pot / activePlayers.size();
                for(Player p : activePlayers) {
                    p.addChips(split);
                }
            } else if (winner != null) {
                System.out.println("\n*** ROZDANIE WYGRYWA Gracz " + getPlayerNumber(winner) + " i zgarnia " + pot + " żetonów! ***");
                winner.addChips(pot);
            }
        }
        pot = 0;
    }


    private int getBestHandScore(Player player) {
        List<Card> allCards = new ArrayList<>();
        allCards.addAll(player.getHand().getCards());
        allCards.addAll(communityCards);

        int maxScore = 0;

        for (int i = 0; i < allCards.size() - 1; i++) {
            for (int j = i + 1; j < allCards.size(); j++) {

                List<Card> fiveCardCombo = new ArrayList<>();

                for (int k = 0; k < allCards.size(); k++) {
                    if (k != i && k != j) {
                        fiveCardCombo.add(allCards.get(k));
                    }
                }

                int currentScore = evaluateFiveCards(fiveCardCombo);
                if (currentScore > maxScore) {
                    maxScore = currentScore;
                }
            }
        }
        return maxScore;
    }

    private int evaluateFiveCards(List<Card> fiveCards) {
        if (hasRoyalFlush(fiveCards)) return 9;
        if (hasStraightFlush(fiveCards)) return 8;
        if (hasFourOfAKind(fiveCards)) return 7;
        if (hasFullHouse(fiveCards)) return 6;
        if (hasFlush(fiveCards)) return 5;
        if (hasStraight(fiveCards)) return 4;
        if (hasThreeOfAKind(fiveCards)) return 3;
        if (hasTwoPairs(fiveCards)) return 2;
        if (hasPair(fiveCards)) return 1;
        return 0;
    }

    private String getHandName(int score) {
        return switch (score) {
            case 9 -> "Poker Królewski (Royal Flush)";
            case 8 -> "Poker (Straight Flush)";
            case 7 -> "Kareta (Four of a Kind)";
            case 6 -> "Full (Full House)";
            case 5 -> "Kolor (Flush)";
            case 4 -> "Strit (Straight)";
            case 3 -> "Trójka (Three of a Kind)";
            case 2 -> "Dwie Pary (Two Pairs)";
            case 1 -> "Para (Pair)";
            default -> "Wysoka Karta (High Card)";
        };
    }


    private int getPlayerNumber(Player player) {
        return players.indexOf(player) + 1;
    }

    private void showPlayerHand(Player player) {
        System.out.print("Twoje karty w ręce: ");
        for (Card c : player.getHand().getCards()) {
            System.out.print("[" + c.getRank() + " of " + c.getSuit() + "] ");
        }
        System.out.println();
    }

    private void printCommunityCards() {
        System.out.print("Karty wspólne: ");
        for (Card c : communityCards) {
            System.out.print("[" + c.getRank() + " of " + c.getSuit() + "] ");
        }
        System.out.println();
    }

    public int getPot() {
        return pot;
    }

    public void addToPot(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Kwota dodawana do puli musi byc wieksza od zera");
        }
        this.pot += amount;
    }

    private Map<Rank, Long> getRankCounts(List<Card> hand) {
        return hand.stream()
                .collect(Collectors.groupingBy(Card::getRank, Collectors.counting()));
    }

    public boolean hasPair(List<Card> hand) {
        return getRankCounts(hand).values().stream().anyMatch(count -> count == 2);
    }

    public boolean hasTwoPairs(List<Card> hand) {
        return getRankCounts(hand).values().stream().filter(count -> count == 2).count() == 2;
    }

    public boolean hasThreeOfAKind(List<Card> hand) {
        return getRankCounts(hand).values().stream().anyMatch(count -> count == 3);
    }

    public boolean hasFlush(List<Card> hand) {
        return hand.stream().map(Card::getSuit).distinct().count() == 1;
    }

    public boolean hasStraight(List<Card> hand) {
        List<Integer> ordinals = hand.stream().map(c -> c.getRank().ordinal()).sorted().toList();
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
        return hasStraightFlush(hand) && hand.stream().anyMatch(c -> c.getRank() == Rank.ACE) && hand.stream().anyMatch(c -> c.getRank() == Rank.TEN);
    }
}