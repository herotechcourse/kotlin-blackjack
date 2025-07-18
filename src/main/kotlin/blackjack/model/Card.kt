package blackjack.model

import blackjack.view.Errors

data class Card(val suit: Suit, val rank: Rank) {
    companion object {
        private val cardPool: Map<Pair<Suit, Rank>, Card> =
            buildMap {
                Suit.entries.forEach { suit ->
                    Rank.entries.forEach { rank ->
                        put(Pair(suit, rank), Card(suit, rank))
                    }
                }
            }

        fun of(
            suit: Suit,
            rank: Rank,
        ): Card {
            return cardPool[Pair(suit, rank)] ?: error(Errors.INVALID_CARD.message)
        }

        fun allCards(): MutableList<Card> {
            return cardPool.values.toMutableList()
        }
    }
}
