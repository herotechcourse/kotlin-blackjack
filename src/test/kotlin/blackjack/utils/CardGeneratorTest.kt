package blackjack.utils

import blackjack.model.Card
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class CardGeneratorTest {
    @ParameterizedTest(name = "generateSuits - contains {0} value of suit")
    @ValueSource(strings = ["A♥", "3♣", "4♠", "5♥", "6♣", "7♥", "8♠", "9♣", "10♥", "J♦", "Q♣", "K♦", "3♥"])
    fun `generateSuits() - contains valid value of suit`(candidate: String) {
        val suits = CardGenerator.generateSuits()
        assertTrue { suits.contains(candidate) }
    }

    @Test
    fun `generateCard() - create a card`() {
        val card = CardGenerator.generateCard(CardGenerator.suits[0])
        assertEquals(Card("A♥"), card)
    }

    @Test
    fun `generateCards() - create a deck of cards`() {
        val card = CardGenerator.generateCard(CardGenerator.suits[0])
        val cards = CardGenerator.generateCards()
        assertTrue { cards.contains(card) }
    }

    @Test
    fun `generateCards() - cards do not have duplicates`() {
        val cards = CardGenerator.generateCards()
        assertTrue { cards.toSet().size == cards.size }
    }
}
