package blackjack.utils

import blackjack.model.Card
import blackjack.model.Rank
import blackjack.model.Suit

object CardGenerator {
    fun generateCards(): List<Card> {
        val ranks = Rank.entries.filter { it != Rank.NONE }
        val suits = Suit.entries.filter { it != Suit.NONE }

        return suits.flatMap { suit ->
            ranks.map { rank -> Card(rank, suit) }
        }
    }
}
