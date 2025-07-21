package blackjack.model

import io.kotest.matchers.collections.shouldNotContainInOrder
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class DeckTest {
    @Test
    fun `Deck has random ordered cards`() {
        val originalDeck = PlayingCard.deck
        val orderedCards = originalDeck.cards.toList()
        val shuffledDeck = originalDeck.shuffle()
        shuffledDeck.cards.shouldNotContainInOrder(orderedCards)
    }

    @Test
    fun `getCard() - using giveCard(), deck can take a card and give to return`() {
        val target = Deck(PlayingCard.generateAllDeck())
        val wasFirstCard = target.cards[0]
        val takenCard = target.giveCard()
        Assertions.assertEquals(wasFirstCard, takenCard)
        Assertions.assertEquals(51, target.cards.size)
    }

    @Test
    fun `updateCardMap() - using giveCard() and checkCardMap(), card manager manage availability of a card`() {
        val target = Deck(PlayingCard.generateAllDeck())
        val sample = target.cards[0]
        val beforeState = target.cards.contains(sample)
        val takenCard = target.giveCard()
        val afterState = target.cards.contains(sample)
        Assertions.assertEquals(sample, takenCard)
        Assertions.assertEquals(true, beforeState)
        Assertions.assertEquals(false, afterState)
    }
}
