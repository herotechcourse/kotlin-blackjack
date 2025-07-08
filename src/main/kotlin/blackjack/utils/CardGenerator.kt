package blackjack.utils

import blackjack.model.Card

object CardGenerator {
    val suits = generateSuits()

    // TODO: Might have to resolve depth of indentation issue
    fun generateSuits(): List<String> {
        val digits = listOf("A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K")
        val figures = listOf("♥", "♣", "♠", "♦")
        val values = mutableListOf<String>()
        digits.map { first ->
            figures.map { second ->
                values += "$first$second"
            }
        }
        return values.toList()
    }

    fun generateCard(suit: String): Card {
        val value = Card(suit)
        return value
    }

    fun generateCards(): List<Card> {
        val cards =
            suits.map { suit ->
                generateCard(suit)
            }
        return cards
    }
}
