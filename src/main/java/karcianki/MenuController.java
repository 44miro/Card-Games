package karcianki;

import java.util.Scanner;

public class MenuController {

    public boolean isValidOption(String input) {
        if (input == null || input.trim().isEmpty()) {
            return false;
        }

        try {
            int option = Integer.parseInt(input.trim());
            return option == 0 || option == 1 || option == 2;
        } catch (NumberFormatException e) {
            return false;//blad
        }
    }

    public void showMenu() {
        Scanner scanner = new Scanner(System.in);
        String input;

        do {
            System.out.println("\nCard Games");
            System.out.println("1. Blackjack");
            System.out.println("2. Poker");
            System.out.println("0. Wyjście z programu");
            System.out.print("Wybierz opcję: ");

            input = scanner.nextLine();

            if (!isValidOption(input)) {
                System.out.println("Błąd: Wpisano niepoprawną wartość. Wybierz 0, 1 lub 2.");
            }

        } while (!isValidOption(input));

        System.out.println("Uruchamiam opcję nr " + input + "...");
    }
}