package karcianki;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Poker {
    private int pot;

    private final List<Player> players;
    private List<Player> activePlayers;
    private Deck deck;
    private final Hand communityCards;
    private final Scanner scanner;

    private enum PlayerAction {
        CALL, RAISE, FOLD, UNKNOWN
    }

    private static class BetState {
        int amount = 0;
        boolean acted = false;
    }

    public Poker() {
        this.pot = 0;
        this.players = new ArrayList<>();
        this.communityCards = new Hand();
        this.scanner = new Scanner(System.in);
    }

    public Poker(Scanner scanner) {
        this.pot = 0;
        this.players = new ArrayList<>();
        this.communityCards = new Hand();
        this.scanner = scanner;
    }

    public void startSession() {
        System.out.println("=== WITAJ W MODULE POKERA ===");
        System.out.print("Podaj liczbę graczy (od 1 do 5): ");
        int numPlayers = scanner.nextInt();

        if (numPlayers <= 1 || numPlayers > 5) {
            System.out.println("Niepoprawna liczba graczy! Ustawiam domyślnie 2 graczy.");
            numPlayers = 2;
        }

        players.clear();
        for (int i = 0; i < numPlayers; i++) {
            Player p = new Player();
            p.addChips(100);
            p.setSeatNumber(i + 1);
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
                for (Player p : players) {
                    if (p.getChips() > 0) {
                        System.out.println("Ostatecznym królem stołu zostaje Gracz " + p.getSeatNumber() + " z kwotą " + p.getChips() + " żetonów!");
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
                for (Player p : players) {
                    System.out.println("  -> Gracz " + p.getSeatNumber() + ": " + p.getChips() + " żetonów");
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
        if (activePlayers.size() > 1) dealCommunityCards(3, "FLOP (3 karty na stół)");
        if (activePlayers.size() > 1) dealCommunityCards(1, "TURN (4. karta na stół)");
        if (activePlayers.size() > 1) dealCommunityCards(1, "RIVER (5. karta na stół)");

        showdown();
    }

    private void preFlop() {
        System.out.println("\n--- PRE-FLOP ---");
        for (int i = 0; i < 2; i++) {
            for (Player player : activePlayers) {
                player.getHand().addCard(deck.drawCard());
            }
        }
        System.out.println("Gracze otrzymali po 2 karty do ręki.");
        bettingRound();
    }

    private void dealCommunityCards(int count, String stageLabel) {
        System.out.println("\n--- " + stageLabel + " ---");
        for (int i = 0; i < count; i++) {
            communityCards.addCard(deck.drawCard());
        }
        System.out.println("Karty wspólne: ");
        communityCards.showHand();
        bettingRound();
    }

    private void bettingRound() {
        System.out.println("\n  >>> ROZPOCZYNA SIĘ LICYTACJA <<<");

        Map<Player, BetState> bets = new HashMap<>();
        for (Player p : activePlayers) {
            bets.put(p, new BetState());
        }

        int currentHighestBet = 0;

        while (!isBettingComplete(bets, currentHighestBet)) {
            for (int i = 0; i < activePlayers.size(); i++) {
                Player player = activePlayers.get(i);
                BetState state = bets.get(player);

                if (state.acted && (state.amount == currentHighestBet || player.getChips() == 0)) {
                    continue;
                }

                int amountToCall = currentHighestBet - state.amount;
                PlayerAction action = askPlayerAction(player, amountToCall);

                switch (action) {
                    case CALL -> handleCall(player, state, amountToCall);
                    case RAISE -> currentHighestBet = handleRaise(player, state, amountToCall, currentHighestBet, bets);
                    case FOLD -> {
                        System.out.println("  => Gracz " + player.getSeatNumber() + " pasuje (Fold).");
                        activePlayers.remove(i);
                        i--;
                        if (activePlayers.size() == 1) {
                            System.out.println("\n  $$$ KONIEC LICYTACJI. PULA WYNOSI: " + pot + " $$$");
                            return;
                        }
                        continue;
                    }
                    case UNKNOWN -> { /* nic nie robimy, gracz traci ruch */ }
                }
                state.acted = true;
            }
        }
        System.out.println("\n  $$$ KONIEC LICYTACJI. PULA WYNOSI: " + pot + " $$$");
    }

    private boolean isBettingComplete(Map<Player, BetState> bets, int currentHighestBet) {
        for (Player p : activePlayers) {
            BetState state = bets.get(p);
            if (!state.acted || (state.amount < currentHighestBet && p.getChips() > 0)) {
                return false;
            }
        }
        return true;
    }

    private PlayerAction askPlayerAction(Player player, int amountToCall) {
        System.out.println("\n--- TURA GRACZA " + player.getSeatNumber() + " ---");
        System.out.println("Żetony (Saldo): " + player.getChips());
        if (amountToCall > 0) {
            System.out.println("Do wyrównania (Call): " + amountToCall);
        }
        player.printHand();

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
        return switch (choice) {
            case 1 -> PlayerAction.CALL;
            case 2 -> PlayerAction.RAISE;
            case 3 -> PlayerAction.FOLD;
            default -> {
                System.out.println("  => Nieznany wybór. Tracisz ruch.");
                yield PlayerAction.UNKNOWN;
            }
        };
    }

    private void handleCall(Player player, BetState state, int amountToCall) {
        if (amountToCall == 0) {
            System.out.println("  => Gracz " + player.getSeatNumber() + " czeka.");
            return;
        }

        int toPay = Math.min(amountToCall, player.getChips());
        if (toPay > 0) {
            int placed = player.placeBet(toPay);
            state.amount += placed;
            addToPot(placed);
            System.out.println("  => Gracz " + player.getSeatNumber() + " wyrównuje za " + placed + ".");
        } else {
            System.out.println("  => Gracz " + player.getSeatNumber() + " stuka w stół. Jest All-In.");
        }
    }

    private int handleRaise(Player player, BetState state, int amountToCall, int currentHighestBet, Map<Player, BetState> bets) {
        System.out.print("  => O ile żetonów chcesz podbić stawkę? ");
        int raiseAmount = scanner.nextInt();

        int totalToPay = amountToCall + raiseAmount;
        int actualToPay = Math.min(totalToPay, player.getChips());

        if (actualToPay <= 0) {
            System.out.println("  => Nie masz żetonów na podbicie!");
            return currentHighestBet;
        }

        int placed = player.placeBet(actualToPay);
        state.amount += placed;
        addToPot(placed);

        if (state.amount > currentHighestBet) {
            System.out.println("  => Gracz " + player.getSeatNumber() + " podbija! (łącznie wrzucił do puli " + state.amount + ").");
            for (Player p : activePlayers) {
                if (p != player) bets.get(p).acted = false;
            }
            return state.amount;
        } else {
            System.out.println("  => Gracz " + player.getSeatNumber() + " wrzuca resztę do puli (All-In za " + placed + ").");
            return currentHighestBet;
        }
    }

    private void showdown() {
        System.out.println("\n=== SHOWDOWN (Odkrycie kart) ===");

        if (activePlayers.size() == 1) {
            Player winner = activePlayers.get(0);
            System.out.println("Wszyscy przeciwnicy spasowali! Wygrywa Gracz " + winner.getSeatNumber() + " i zgarnia: " + pot);
            winner.addChips(pot);
        } else {
            System.out.println("Do końca dotarło " + activePlayers.size() + " graczy. Oceniamy siłę rąk!");

            Player winner = null;
            HandRank bestRank = null;
            boolean isTie = false;

            for (Player p : activePlayers) {
                HandRank rank = p.getBestHandRank(communityCards.getCards());
                System.out.println("  -> Gracz " + p.getSeatNumber() + " ułożył układ: " + rank.getDisplayName());

                if (bestRank == null || rank.compareTo(bestRank) > 0) {
                    bestRank = rank;
                    winner = p;
                    isTie = false;
                } else if (rank == bestRank) {
                    isTie = true;
                }
            }

            if (isTie) {
                System.out.println("\n*** MAMY REMIS NA STOLE! Gracze dzielą pulę równomiernie! ***");
                int split = pot / activePlayers.size();
                for (Player p : activePlayers) {
                    p.addChips(split);
                }
            } else if (winner != null) {
                System.out.println("\n*** ROZDANIE WYGRYWA Gracz " + winner.getSeatNumber() + " i zgarnia " + pot + " żetonów! ***");
                winner.addChips(pot);
            }
        }
        pot = 0;
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
}