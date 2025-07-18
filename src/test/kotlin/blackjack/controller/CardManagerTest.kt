package blackjack.controller

import blackjack.model.PlayingCard
import io.kotest.matchers.collections.shouldNotContainInOrder
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CardManagerTest {
    @Test
    fun `card manager has random ordered cards`() {
        val manager = CardManager()
        val orderedCards = PlayingCard.Deck
        manager.cards.shouldNotContainInOrder(orderedCards)
    }

    @Test
    fun `getCard() - using giveCard(), card manager can take a card and give to return`() {
        val manager = CardManager()
        val wasFirstCard = manager.cards[0]
        val takenCard = manager.giveCard()
        assertEquals(wasFirstCard, takenCard)
        assertEquals(51, manager.cards.size)
    }

    @Test
    fun `updateCardMap() - using giveCard() and checkCardMap(), card manager manage availability of a card`() {
        val manager = CardManager()
        val sample = manager.cards[0]
        val beforeState = manager.cards.contains(sample)
        val takenCard = manager.giveCard()
        val afterState = manager.cards.contains(sample)
        assertEquals(sample, takenCard)
        assertEquals(true, beforeState)
        assertEquals(false, afterState)
    }
}
