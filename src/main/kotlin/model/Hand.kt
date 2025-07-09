package model

import service.ScoreCalculator

class Hand {
    private val cards: MutableSet<Card> = mutableSetOf()

    fun getCards(): Set<Card> = cards.toSet()

    fun addCard(card: Card) {
        cards.add(card)
    }

    fun scoreOnHand(): Int {
        return ScoreCalculator.getScore(cards)
    }

    override fun toString(): String {
        return cards.toString()
    }
}
