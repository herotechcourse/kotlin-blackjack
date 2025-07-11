package blackjack

import blackjack.model.Card
import blackjack.model.HandCards
import blackjack.model.Rank
import blackjack.model.Suit
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

val ACE_DIAMONDS = Card(Rank.ACE, Suit.DIAMONDS)
val SIX_DIAMONDS = Card(Rank.SIX, Suit.DIAMONDS)
val QUEEN_CLUBS = Card(Rank.QUEEN, Suit.CLUBS)
val ACE_CLUBS = Card(Rank.ACE, Suit.CLUBS)
val QUEEN_DIAMONDS = Card(Rank.QUEEN, Suit.DIAMONDS)

class TotalTest {
    @Test
    fun `test if one of two aces are reduced`(){
        val handCards = HandCards()
        handCards.add(ACE_DIAMONDS)
        assertEquals(handCards.total, 11)
        handCards.add(ACE_DIAMONDS)
        assertEquals(handCards.total, 12)
    }

    @Test
    fun `test if one ace is reduced`(){
        val handCards = HandCards()
        handCards.add(ACE_DIAMONDS)
        assertEquals(handCards.total, 11)
        handCards.add(SIX_DIAMONDS)
        assertEquals(handCards.total, 17)
        handCards.add(QUEEN_CLUBS)
        assertEquals(handCards.total, 17)
    }

    @Test
    fun `test if many aces are reduced`(){
        val handCards = HandCards()
        handCards.add(ACE_DIAMONDS)
        assertEquals(handCards.total, 11)
        handCards.add(ACE_DIAMONDS)
        assertEquals(handCards.total, 12)
        handCards.add(ACE_CLUBS)
        assertEquals(handCards.total, 13)
        handCards.add(ACE_CLUBS)
        assertEquals(handCards.total, 14)
        handCards.add(QUEEN_CLUBS)
        assertEquals(handCards.total, 14)
    }

    @Test
    fun `test if burned after ace reduced`(){
        val handCards = HandCards()
        handCards.add(ACE_DIAMONDS)
        assertEquals(handCards.total, 11)
        handCards.add(ACE_DIAMONDS)
        assertEquals(handCards.total, 12)
        handCards.add(ACE_CLUBS)
        assertEquals(handCards.total, 13)
        handCards.add(ACE_CLUBS)
        assertEquals(handCards.total, 14)
        handCards.add(QUEEN_CLUBS)
        assertEquals(handCards.total, 14)
        handCards.add(QUEEN_CLUBS)
        assertEquals(handCards.total, 24)
    }

    @Test
    fun `test if no aces reduced`(){
        val handCards = HandCards()
        handCards.add(QUEEN_DIAMONDS)
        assertEquals(handCards.total, 10)
        handCards.add(QUEEN_DIAMONDS)
        assertEquals(handCards.total, 20)
        handCards.add(QUEEN_CLUBS)
        assertEquals(handCards.total, 30)
    }
}
