package karcianki;

public class Poker {
    private int pot;

    public Poker() {
        this.pot = 0;
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