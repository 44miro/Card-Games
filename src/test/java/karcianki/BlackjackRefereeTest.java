package karcianki;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BlackjackRefereeTest {

    @Test
    public void shouldLoseWhenPlayerBusts() {
        BlackjackReferee referee = new BlackjackReferee();
        // Gracz ma 22, Krupier ma 18 - przegrana gracza
        assertEquals(GameResult.LOSE, referee.determineWinner(22, 18));
    }

    @Test
    public void shouldWinWhenDealerBusts() {
        BlackjackReferee referee = new BlackjackReferee();
        // Gracz ma 20, Krupier 25 - wygrana gracza
        assertEquals(GameResult.WIN, referee.determineWinner(20, 25));
    }

    @Test
    public void shouldWinWhenPlayerHasHigherScore() {
        BlackjackReferee referee = new BlackjackReferee();
        // Standardowe porównanie bez przekraczania 21
        assertEquals(GameResult.WIN, referee.determineWinner(20, 18));
    }

    @Test
    public void shouldLoseWhenDealerHasHigherScore() {
        BlackjackReferee referee = new BlackjackReferee();
        assertEquals(GameResult.LOSE, referee.determineWinner(17, 19));
    }

    @Test
    public void shouldDrawWhenScoresAreEqual() {
        BlackjackReferee referee = new BlackjackReferee();
        assertEquals(GameResult.DRAW, referee.determineWinner(18, 18));
    }
}

