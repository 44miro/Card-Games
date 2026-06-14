package karcianki;

import java.util.Scanner;

public class Menu {
    private final Scanner scanner;

    public Menu(Scanner scanner) {
        this.scanner = scanner;
    }

    public Menu() {
        this(new Scanner(System.in));
    }

    public boolean isValidOption(String input) {
        if (input == null || input.trim().isEmpty()) {
            return false;
        }

        try {
            int option = Integer.parseInt(input.trim());
            return option == 0 || option == 1 || option == 2;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void start() {
        String input;

        System.out.println("===CARD GAMES ===");

        do {
            System.out.println("\nDOSTEPNE GRY: ");
            System.out.println("1. Blackjack");
            System.out.println("2. Poker");
            System.out.println("0. Wyjście z programu");
            System.out.print("Wybierz opcję: ");

            input = scanner.next();

            if (!isValidOption(input)) {
                System.out.println("Błąd: Wpisano niepoprawną wartość. Wybierz 0, 1 lub 2.");
                continue;
            }

            switch (input.trim()) {
                case "1" -> {
                    System.out.println("\nUruchamiam grę Blackjack...");
                    Player bjPlayer = new Player();
                    Dealer bjDealer = new Dealer();
                    Blackjack blackjack = new Blackjack(bjPlayer, bjDealer, scanner);
                    blackjack.game();
                }
                case "2" -> {
                    System.out.println("\nUruchamiam grę Poker...");
                    Poker poker = new Poker(scanner);
                    poker.startSession();
                }
                case "0" -> System.out.println("\nZamykanie...");
            }

        } while (!input.trim().equals("0"));
    }
}