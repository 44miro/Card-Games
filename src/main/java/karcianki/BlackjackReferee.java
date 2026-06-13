package karcianki;

public class BlackjackReferee {

    public GameResult determineWinner(int playerScore, int dealerScore) {
        // sprawdzenie busta, przekroczenie 21 przez gracza
        if (playerScore > 21) {
            return GameResult.LOSE;
        }

        //sprawdzanie dilera
        if (dealerScore > 21) {
            return GameResult.WIN;
        }

        // nikt nie przekroczyl 21, wiec porownywanie wynikow
        if (playerScore > dealerScore) {
            return GameResult.WIN;
        } else if (playerScore < dealerScore) {
            return GameResult.LOSE;
        }

        return GameResult.DRAW;
    }
}
