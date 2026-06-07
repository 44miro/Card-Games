package karcianki;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BlackjackTest {
    @Test
    public void aceShouldBeCountAsElevenIfScoreLessThen11(){
        Blackjack bj = new Blackjack();
        Player player = bj.getPlayer();
        Hand hand = player.getHand();
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new Card(Suit.HEARTS, Rank.ACE));
        cards.add(new Card(Suit.HEARTS, Rank.TEN));
        hand.setCards(cards);
        assertEquals(21,bj.countScore());
    }
    @Test
    public void aceShouldBeCountAsOneIfScoreMoreThen11(){
        Blackjack bj = new Blackjack();
        Player player = bj.getPlayer();
        Hand hand = player.getHand();
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new Card(Suit.HEARTS, Rank.ACE));
        cards.add(new Card(Suit.HEARTS, Rank.ACE));
        hand.setCards(cards);
        assertNotEquals(22,bj.countScore());
    }

    @Test
    public void aceShouldBeCountAsOneIfScoreMoreThen11EvenIfFirstCardIsAce(){
        Blackjack bj = new Blackjack();
        Player player = bj.getPlayer();
        Hand hand = player.getHand();
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new Card(Suit.HEARTS, Rank.ACE));
        cards.add(new Card(Suit.HEARTS, Rank.TEN));
        cards.add(new Card(Suit.HEARTS, Rank.SEVEN));
        hand.setCards(cards);
        assertEquals(18,bj.countScore());
    }

    @Test
    public void aceShouldBeCountAsElevenIfScoreLessThen11EvenIfFirstCardIsNotAce(){
        Blackjack bj = new Blackjack();
        Player player = bj.getPlayer();
        Hand hand = player.getHand();
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new Card(Suit.HEARTS, Rank.TWO));
        cards.add(new Card(Suit.HEARTS, Rank.SEVEN));
        cards.add(new Card(Suit.HEARTS, Rank.ACE));
        hand.setCards(cards);
        assertEquals(20,bj.countScore());
    }

}
