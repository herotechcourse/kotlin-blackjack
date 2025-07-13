package blackjack.controller

import blackjack.utils.CardGenerator
import io.kotest.matchers.collections.shouldNotContainInOrder
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class DeckTest {
    @Test
    fun `card manager has random ordered cards`() {
        val manager = Deck()
        val orderedCards = CardGenerator.generateCards()
        manager.cards.shouldNotContainInOrder(orderedCards)
    }

    @Test
    fun `getCard() - using drawCard(), card manager can take a card and give to return`() {
        val manager = Deck()
        val wasFirstCard = manager.cards[0]
        val takenCard = manager.drawCard()
        assertEquals(wasFirstCard, takenCard)
        assertEquals(51, manager.cards.size)
    }

    @Test
    fun `updateCardMap() - using drawCard() and checkCardMap(), card manager manage availability of a card`() {
        val manager = Deck()
        val beforeState = manager.checkCardMap(manager.cards[0])
        val takenCard = manager.drawCard()
        val afterState = manager.checkCardMap(takenCard)
        assertEquals(true, beforeState)
        assertEquals(false, afterState)
    }
}
